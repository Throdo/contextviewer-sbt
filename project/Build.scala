import sbt._
import Keys._
import play.Project._

object ApplicationBuild extends Build {

  val appName = "contextviewer"
  val appVersion = "1.0-SNAPSHOT"

  val appDependencies = Seq(
    // Add your project dependencies here,
    javaCore,
    javaJdbc,
    javaEbean,
    "com.google.code.gson" % "gson" % "2.2.4" withSources(),
    "com.sun.jersey" % "jersey-client" % "1.17.1" withSources(),
    "com.sun.jersey" % "jersey-core" % "1.17.1" withSources(),
    "com.sun.jersey" % "jersey-bundle" % "1.17.1" withSources(),
    "org.hamcrest" % "hamcrest-all" % "1.3",
    "couchbase" % "couchbase-client" % "1.1.6"
  )

  val main = play.Project(appName, appVersion, appDependencies).settings(

    resolvers += "Typesafe repository" at "http://repo.typesafe.com/typesafe/releases/",
    resolvers += "Jboss repository" at "http://repository.jboss.org/nexus/",
    resolvers += "Scala-Tools Maven2 Snapshots Repository" at "http://scala-tools.org/repo-snapshots",
    resolvers += "Couchbase Repository" at "http://files.couchbase.com/maven2/",
    resolvers += "Play 2.1.1 local Repository" at "file://home/throdo/Programmation/Binaires/Play2/play-2.1.1/repository/cache/",
    resolvers += "Maven 2 local Repository" at "file://home/throdo/.m2/repository/"
  )

}