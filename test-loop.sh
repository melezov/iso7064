#!/bin/bash

cwd="$( pwd )"; cd "$( dirname "$0" )"; dir="$( pwd )"; cd "$cwd"

echo Entering continuous test loop ...
"$dir/sbt.sh" ~test "$@"
