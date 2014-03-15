#!/bin/bash

cwd="$( pwd )"; cd "$( dirname "$0" )"; dir="$( pwd )"; cd "$cwd"

echo Firing up the Scala REPL ...
"$dir/sbt.sh" 'set autoScalaLibrary := true' console "$@"
