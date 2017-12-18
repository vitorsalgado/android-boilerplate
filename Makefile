SHELL := /bin/bash
PROJECT := $(shell basename $$(pwd))

build:
	./gradlew clean build --info -x validateSigningRelease -x packageRelease -x testRelease

infer:
	infer -- ./gradlew clean build --project-cache-dir=$WERCKER_CACHE_DIR -x validateSigningRelease -x packageRelease -x testRelease -x testDebug -x lint -x findBugs -x pmd

mock:
	docker-compose -f ./docker-compose-mock.yml up

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

.PHONY: build