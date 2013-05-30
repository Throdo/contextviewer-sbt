// Ensure that settings are separated by blank lines.

// Comment to get more information during initialization
logLevel := Level.Warn

// The Typesafe repository
resolvers += "Typesafe repository" at "http://repo.typesafe.com/typesafe/releases/"

resolvers += "Jboss repository" at "http://repository.jboss.org/nexus/"

resolvers += "Scala-Tools Maven2 Snapshots Repository" at "http://scala-tools.org/repo-snapshots"

resolvers += "Couchbase Repository" at "http://files.couchbase.com/maven2/"

resolvers += "Play 2.1.1 local Repository" at "file://home/throdo/Programmation/Binaires/Play2/play-2.1.1/repository/cache/"

resolvers += "Maven 2 local Repository" at "file://home/throdo/.m2/repository/"

// Use the Play sbt plugin for Play projects
addSbtPlugin("play" % "sbt-plugin" % "2.1.1")
//addSbtPlugin("play" % "sbt-plugin" % Option(System.getProperty("play.version")).getOrElse("2.0"))