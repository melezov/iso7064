@echo off

echo Entering continuous compilation loop ...
call "%~dp0sbt.bat" ~compile %*
