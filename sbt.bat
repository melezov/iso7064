@echo off

rem This script will configure the JVM memory constraints and fire up the SBT instance.
rem It is using the GRUJ launcher for SBT to minimize the binary footprint of the source repository.
rem If no arguments were provided to the script, SBT will start in shell mode.

java ^
  -Xss2m -Xms2g -Xmx2g ^
  -XX:+TieredCompilation -XX:+CMSClassUnloadingEnabled ^
  -XX:+UseNUMA -XX:+UseParallelGC ^
  -jar project\strap\gruj_vs_sbt-launch-0.13.x.jar ^
  %*
