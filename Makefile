SHELL := /bin/bash
PROJECT := $(shell basename $$(pwd))

increment-build-version:
	read LASTNUM < VERSION_CODE;	\
	NEWNUM=$$(($$LASTNUM + 1));		\
	echo "$$NEWNUM" > VERSION_CODE

build: increment-build-version

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
