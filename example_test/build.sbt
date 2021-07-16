name := "example_test"

version := "0.1"

scalaVersion := "2.13.6"
val sparkVersion = "2.2.0"

libraryDependencies ++= Seq(
  "org.apache.spark" %% "spark-core" % sparkVersion,
  "org.apache.spark" %% "spark-sql" % sparkVersion
)

libraryDependencies ++= Seq("junit" % "junit" % "4.10" % Test, "org.mockito" % "mockito-all" % "1.10.19" % Test)