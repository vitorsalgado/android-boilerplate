PROJECT := com.example
PROJECT_TEST := $(PROJECT).test
CONTEXT := $$(pwd)
LEVEL := info



# build recipes
# ##################################################################################################

build:
	clear && \
	./gradlew clean build --$(LEVEL) -x validateSigningRelease -x packageRelease -x testRelease

build-all:
	./gradlew clean build --$(LEVEL)

infer:
	clear && \
	infer -- ./gradlew clean build --$(LEVEL) -x validateSigningRelease -x packageRelease -x testRelease -x testDebug -x lint -x pmd

infer-docker:
	clear && \
	docker rm -f $(PROJECT_TEST) || true && \
	docker build --build-arg ANDROID_HOME=${ANDROID_HOME} -t $(PROJECT_TEST) . && \
	docker run -d -e "ANDROID_HOME=${ANDROID_HOME}" -v $(CONTEXT):/usr/app -v ${ANDROID_HOME}:${ANDROID_HOME} -v ${HOME}/.gradle:/root/.gradle --name $(PROJECT_TEST) -it $(PROJECT_TEST) /bin/bash && \
	docker exec -it $(PROJECT_TEST) script /dev/null -c "infer -- ./gradlew clean build -x validateSigningRelease -x packageRelease -x testRelease -x testDebug -x lint -x pmd"



# apk recipes
# ##################################################################################################

redex-debug:
	clear && \
	redex ./app/build/outputs/apk/app-debug.apk -o app-debug-final.apk --sign -s ./distribution/debug.keystore -a androiddebugkey -p android




# quality recipes
# ##################################################################################################

checkstyle:
	./gradlew checkstyle --$(LEVEL)

pmd:
	./gradlew pmd --$(LEVEL)

full-coverage:
	clear && \
	./gradlew fullCoverageReport && \
	echo "JaCoCo Coverage Report" && \
	echo file://$$(pwd)/app/build/reports/jacoco/fullCoverageReport/html/index.html

check-security:
	clear && \
	./gradlew dependencyCheckAnalyze --$(LEVEL)




# mocks
# ##################################################################################################

mock:
	docker-compose -f ./docker-compose-mock.yml up




# versioning
# ##################################################################################################

increment-build-version:
	read LASTNUM < VERSION_CODE;	\
	NEWNUM=$$(($$LASTNUM + 1));		\
	echo "$$NEWNUM" > VERSION_CODE; \
	chmod +x ./tools/sanitize.sh;   \
	./tools/sanitize.sh;

gitsemver:
	@SUDO=$$([ $$(id -u) != 0 ] && echo sudo) && \
	$$(git semver help &>/dev/null) || (cd /tmp && $${SUDO} rm -rf git-semver && \
	git clone https://github.com/markchalloner/git-semver.git && \
	cd git-semver && \
	$${SUDO} ./install.sh)

latest-version:
	git semver get

next:
	git semver $(version)




# git utilities
# ##################################################################################################

# @parameter - b - name of new branch
new-branch:
	git checkout -b $(b) && \
	git push origin $(b) && \
	git branch --set-upstream-to origin/$(b) $(b)

# @parameter - m - commit message
cp:
	git add --all && \
	git commit -m "$(m)" && \
	git push




# refactorer
# ##################################################################################################

new-project:
	docker rm -f $(PROJECT_TEST).cli || true && \
	docker build -t $(PROJECT_TEST).cli -f Dockerfile.cli . && \
	docker run --rm -v $(CONTEXT):/usr/app/android --name $(PROJECT_TEST).cli -it $(PROJECT_TEST).cli




.PHONY: build
