name := "heavy"

version := "1.0"

scalaVersion := "2.11.8"

val akkaVersion = "2.4.8"

libraryDependencies ++= Seq(
    "com.typesafe.akka" %% "akka-actor" % akkaVersion
)