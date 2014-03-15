#!/bin/bash

cwd="$( pwd )"; cd "$( dirname "$0" )"; dir="$( pwd )"; cd "$cwd"

# First, clean all targets to ensure no artifacts remained from previous compilations.
# Then, a compilation will be triggered by the publish-local command - since it is a Java project, no cross-compilation is necessary.
# Finally (if the compilation was successful), publish the artifacts to a local binary repository.

echo Publishing project to a local repository ...
"$dir/sbt.sh" clean publish-local "$@"
