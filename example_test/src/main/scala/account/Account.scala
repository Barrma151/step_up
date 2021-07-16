package account

import dao.{HiveDao, SaveDao}

class Account {

  var hiveDao = new HiveDao();
  var saveDao = new SaveDao();

  /*This method has all the business logics
 * @param spark: SparkSession
 * @param query: sql query for hive
 * @param targetRep: Target repository path*/
  def executeQuery(spark: SparkSession, query: String, targetRep: String): Unit = {

    val df = hiveDao.load(spark, query)
    val transformedDf = df.withColumn("upper_greeting", upper(df.col("greeting")))
    saveDao.write(transformedDf, targetRep)
  }

  /*This method capitalize the strings words
 * @param: msg: A String message
 * @return : A capitalize string
 */
  def changeString(msg: String): String = {
    msg.split(" ").map(_.capitalize).mkString(" ")
  }

}
