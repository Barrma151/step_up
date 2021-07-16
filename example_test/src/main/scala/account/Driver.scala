package account

import sparksession.SparkSessionTestWrapper

object Driver extends SparkSessionTestWrapper{
  def main(args: Array[String]): Unit = {
    println("Starting the spark job")
    val spark = SparkSession.builder().appName("SparkUnitTestApp").master("local[*]").getOrCreate()

    val account = new Account()

    account.executeQuery(spark, "select *from test_db.test_table", "local/target")
  }

}
