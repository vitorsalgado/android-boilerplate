#######################################
# build recipes
#######################################

build:
	./gradlew clean build --info -x validateSigningRelease -x packageRelease -x testRelease

infer:
	infer -- ./gradlew clean build -x validateSigningRelease -x packageRelease -x testRelease -x testDebug -x lint -x findBugs -x pmd

redex:
	redex ./app/build/outputs/apk/app-debug.apk -o app-debug-final.apk --sign -s ./distribution/debug.keystore -a androiddebugkey -p android

mock:
	docker-compose -f ./docker-compose-mock.yml up




#######################################
# versioning
#######################################

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
