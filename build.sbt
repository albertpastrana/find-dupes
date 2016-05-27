import sbt._

organization := "com.albertpastrana"

organizationName := "Albert Pastrana"

organizationHomepage := Some(url("http://albertpastrana.com"))

scalaVersion := "2.11.8"

scalacOptions ++= Seq(
  "-Xlint",
  "-unchecked",
  "-deprecation",
  "-feature")

libraryDependencies ++= Seq(
  "com.google.guava" % "guava" % "19.0"
)

enablePlugins(UniversalPlugin)