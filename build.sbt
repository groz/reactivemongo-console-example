name := "reactivemongo-console"

version := "1.0"

scalaVersion := "2.11.7"

resolvers += "Typesafe" at "http://repo.typesafe.com/typesafe/releases/"

libraryDependencies ++= Seq(
  "org.reactivemongo" %% "reactivemongo" % "0.11.7"
)
