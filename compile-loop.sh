#!/bin/bash

cwd="$( pwd )"; cd "$( dirname "$0" )"; dir="$( pwd )"; cd "$cwd"

echo Entering continuous compilation loop ...
"$dir/sbt.sh" ~compile
