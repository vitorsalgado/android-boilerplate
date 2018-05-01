SHELL := /bin/bash
PROJECT := com.example
PROJECT_TEST := $(PROJECT).test
CONTEXT := $$(pwd)
LEVEL := info
QARK_VERSION := 0.9-alpha.10




# build recipes
# ##################################################################################################

build:
	clear && \
	./gradlew clean build --$(LEVEL) -x validateSigningRelease -x packageRelease -x testRelease

build-ci:
	./gradlew build --quiet

infer:
	clear && \
	infer -- ./gradlew clean build --$(LEVEL) -x validateSigningRelease -x packageRelease -x testRelease -x testDebug -x lint -x pmd

infer-docker:
	clear && \
	docker rm -f $(PROJECT_TEST) || true && \
	docker build --build-arg ANDROID_HOME=${ANDROID_HOME} -t $(PROJECT_TEST) . && \
	docker run -d -e "ANDROID_HOME=${ANDROID_HOME}" -v $(CONTEXT):/usr/app -v ${ANDROID_HOME}:${ANDROID_HOME} -v ${HOME}/.gradle:/root/.gradle --name $(PROJECT_TEST) -it $(PROJECT_TEST) /bin/bash && \
	docker exec -it $(PROJECT_TEST) script /dev/null -c "infer -- ./gradlew clean build -x validateSigningRelease -x packageRelease -x testRelease -x testDebug -x lint -x pmd"

infer-docker-ci:
	clear && \
	docker rm -f $(PROJECT_TEST) || true && \
	docker build --build-arg ANDROID_HOME=${ANDROID_HOME} -t $(PROJECT_TEST) . && \
	docker run -d -e "ANDROID_HOME=${ANDROID_HOME}" -v $(CONTEXT):/usr/app -v ${ANDROID_HOME}:${ANDROID_HOME} -v ${HOME}/.gradle:/root/.gradle --name $(PROJECT_TEST) -it $(PROJECT_TEST) /bin/bash && \
	docker exec -it $(PROJECT_TEST) script /dev/null -c "infer -- ./gradlew clean build --$(LEVEL)"

set-licenses:
	mkdir "${ANDROID_HOME}/licenses" || true && \
	echo "8933bad161af4178b1185d1a37fbf41ea5269c55" > "${ANDROID_HOME}/licenses/android-sdk-license" && \
	echo "84831b9409646a918e30573bab4c9c91346d8abd" > "${ANDROID_HOME}/licenses/android-sdk-preview-license" && \
	yes | sdkmanager "platforms;android-27"




# apk recipes
# ##################################################################################################

redex-debug:
	clear && \
	redex ./app/build/outputs/apk/app-debug.apk -o app-debug-final.apk --sign -s ./distribution/debug.keystore -a androiddebugkey -p android




# quality recipes
# ##################################################################################################

check:
	./gradlew check --$(LEVEL) -x pmd

checkstyle:
	./gradlew checkstyle --$(LEVEL)

pmd:
	./gradlew pmd --$(LEVEL)

full-coverage:
	clear && \
	./gradlew fullCoverageReport && \
	echo "JaCoCo Coverage Report" && \
	echo file://$(CONTEXT)/app/build/reports/jacoco/fullCoverageReport/html/index.html

check-security:
	clear && \
	./gradlew dependencyCheckAnalyze --$(LEVEL)

sonar:
	./gradlew sonarqube \
    	-Dsonar.host.url=https://sonarcloud.io \
		-Dsonar.organization=${SONARQUBE_ORG} \
		-Dsonar.login=${SONARQUBE_TOKEN}

qark:
	if [ ! -d "./qark" ]; then \
		wget https://github.com/linkedin/qark/archive/v$(QARK_VERSION).zip -O qark.zip; \
		unzip qark.zip; \
		mv qark-$(QARK_VERSION) qark; \
		rm qark.zip; \
	fi && \
	cd qark && \
	python qark.py -b ${ANDROID_HOME} -s 1 -p $(CONTEXT)/app/build/outputs/apk/app-debug.apk --exploit 1 --install 1

mobsf:
	docker rm -f mobsf || true && \
	docker pull opensecurity/mobile-security-framework-mobsf && \
	docker run -d -it -p 8000:8000 opensecurity/mobile-security-framework-mobsf:latest --name mobsf

mobsf-down:
	docker rm -f mobsf




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
	if [ ! -d "git-semver" ]; then \
		git clone https://github.com/markchalloner/git-semver.git; \
	fi && \
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
	npm install && \
	node cli.js
