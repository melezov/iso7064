@echo off

rem First, clean all targets to ensure no artifacts remained from previous compilations.
rem Then, a compilation will be triggered by the publish command - since it is a Java project, no cross-compilation is necessary.
rem Finally (if the compilation was successful), publish the artifacts to the binary repository.

echo Publishing project to the binary repository ...
call "%~dp0sbt.bat" %* clean publish
