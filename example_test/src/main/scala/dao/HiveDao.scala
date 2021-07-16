package dao

import com.sun.rowset.internal.Row

class HiveDao {

  /* This method loads the data from the hive for give database and give table, your query must contain dt_name.table_name
* @param: spark: SparkSession
* @param: query: query which need to fired on hive db
* @return : Dataframe from hive*/
  def load(spark: SparkSession, query: String): Dataset[Row] = {

    spark.sql(query)
  }

}
