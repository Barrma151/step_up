package dao

import com.sun.rowset.internal.Row

class SaveDao {

  /*This method writes the given dataframe to the give target location
* @param: df: DataFrame which need to be saved
* @param: path: Location to save the data frame */

  def write(df: Dataset[Row], path: String): Unit = {
    df.write.mode("Overwrite").parquet(path)
  }

}
