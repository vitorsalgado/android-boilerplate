#!/usr/bin/python
# -*- coding: utf-8 -*-

from itertools import *

def to_slack_markdown(line):
    if line.startswith('Version'):
        return '*%s*\n' % line.replace('\n', '')
    return line.replace('-', '•').replace('\n', '')

with open('./app/beta-release-notes.txt') as file:
    release_notes = map(to_slack_markdown, takewhile(lambda x: len(x) > 1, file.readlines()))

print('\n'.join(release_notes))
