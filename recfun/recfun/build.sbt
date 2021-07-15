import ch.epfl.lamp.MOOCSettings.autoImport.{assignment, course}

course :="progfun1"
assignment :="recfun"
scalaVersion := "2.13.0"


scalacOptions ++= Seq("-language:implicitConversions", "-deprecation")

libraryDependencies += "com.novocode" % "junit-interface" % "0.11" % Test

testOptions in Test += Tests.Argument(TestFrameworks.JUnit, "-a", "-v", "-s")
libraryDependencies += "org.scalatest" %% "scalatest" % "3.0.8" % "test"
// https://mvnrepository.com/artifact/org.scala-sbt/compiler-bridge
libraryDependencies += "org.scala-sbt" %% "compiler-bridge" % "1.4.4" % Test
lazy val scalaTestDependency = "org.scalatest" %% "scalatest" % "3.0.8"