#!/usr/bin/python
# -*- coding: utf-8 -*-

from __future__ import print_function

with open('./VERSION') as version_file:
    version = version_file.read()

with open('./VERSION_CODE') as version_code_file:
    version_code = version_code_file.read()

print('{version}({version_code})'
    .format(version=version, version_code=version_code)
    .strip()
    .replace('\n', ''), end='')
