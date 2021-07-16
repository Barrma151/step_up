package sparksession

trait SparkSessionTestWrapper {

  lazy val spark: SparkSession = {
    SparkSession
      .builder()
      .master("local[2]")
      .appName("SampleSparkScalaTest")
      .getOrCreate()
  }

}
