val ElementNexus     = "Element Nexus"     at "http://repo.element.hr/nexus/content/groups/public/"
val ElementReleases  = "Element Releases"  at "http://repo.element.hr/nexus/content/repositories/releases/"
val ElementSnapshots = "Element Snapshots" at "http://repo.element.hr/nexus/content/repositories/snapshots/"

organization := "hr.element.iso"

name := "iso7064"

version := "0.0.1"

scalaVersion := "2.11.0-RC1"

javacOptions in doc := Seq(
  "-encoding", "UTF-8"
, "-source", "1.6"
) ++ (sys.env.get("JDK16_HOME") match {
  case Some(jdk16Home) => Seq("-bootclasspath", jdk16Home + "/jre/lib/rt.jar")
  case _ => Nil
})

javacOptions := Seq(
  "-deprecation"
, "-Xlint"
, "-target", "1.6"
) ++ (javacOptions in doc).value

crossPaths := false

autoScalaLibrary := false

unmanagedSourceDirectories in Compile := Seq((javaSource in Compile).value)

unmanagedResourceDirectories in Compile := Seq(sourceDirectory.value / "main" / "resources")

unmanagedSourceDirectories in Test := Seq((javaSource in Test).value)

EclipseKeys.createSrc := EclipseCreateSrc.Default + EclipseCreateSrc.Resource

EclipseKeys.eclipseOutput := Some(".target")

EclipseKeys.executionEnvironment := Some(EclipseExecutionEnvironment.JavaSE16)

EclipseKeys.projectFlavor := EclipseProjectFlavor.Java

resolvers := Seq(ElementNexus, ElementSnapshots)

externalResolvers := Resolver.withDefaultResolvers(resolvers.value, mavenCentral = false)

publishTo in ThisBuild := Some(if (version.value endsWith "-SNAPSHOT") ElementSnapshots else ElementReleases)

credentials in ThisBuild ++= {
  val creds = Path.userHome / ".config" / "iso7064" / "nexus.config"
  if (creds.exists) Some(Credentials(creds)) else None
}.toSeq
