#!/bin/sh

# Repository intialization script
# This need to be run after the repository is cloned

cp ./scripts/pre-push ./.git/hooks/pre-push
chmod +x ./.git/hooks/pre-push
