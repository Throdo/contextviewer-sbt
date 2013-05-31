import sbt._
import play.Project._

object ApplicationBuild extends Build {

  val appName = "contextviewer"
  val appVersion = "1.0-SNAPSHOT"

  val appDependencies = Seq(
    // Add your project dependencies here,
    javaCore,
    javaJdbc,
    javaEbean ,
    "com.google.code.gson" % "gson" % "2.2.4" withSources(),
    "com.sun.jersey" % "jersey-client" % "1.17.1" withSources(),
    "org.hamcrest" % "hamcrest-all" % "1.3",
    "couchbase" % "couchbase-client" % "1.1.6"
  )

  val main = play.Project(appName, appVersion, appDependencies)
    .settings(
    // Add your own project settings here
  )

}