import sparksession.SparkSessionTestWrapper
import org.apache.spark.sql.functions.upper
import org.apache.spark.sql.types.{StringType, StructField, StructType}
import org.apache.spark.sql.{Dataset, Row}
import org.junit.{Before, Test}
import org.mockito.{ArgumentCaptor, Matchers, Mockito}

class AccountSpec extends  SparkSessionTestWrapper{

  val accountService = new Account()
  var hiveDaoMock: HiveDao = null
  var saveDaoMock: SaveDao = null

  /*Initializing the all the class members with appropriate object values*/
  @Before
  def initialize(): Unit = {
    hiveDaoMock = Mockito.mock(classOf[HiveDao])
    saveDaoMock = Mockito.mock(classOf[SaveDao])
    accountService.hiveDao = hiveDaoMock
    accountService.save = saveDaoMock

  }
  /*Creating the dummy source data and it will be returned when source mocked dao method will be called */
  private def mockDFTestData(): Dataset[Row] = {

    val sourceDfSchema = List(
      StructField("name", StringType, true),
      StructField("greeting", StringType, false)
    )

    val expectedDfData = spark.sparkContext.parallelize(Seq(
      Row("miguel", "hello world"),
      Row("luisa", "hello world")
    ))

    val sourcdDF = spark.createDataFrame(expectedDfData,
      StructType(sourceDfSchema))

    sourcdDF.show()
    sourcdDF;
  }
  /*It is the actual test case which will success for the give logic and data*/
  @Test
  def this_test_should_validate_executeQuery_method: Unit = {

    Mockito.when(hiveDaoMock.load(spark, "select *from test_db.test_table")).thenReturn(mockDFTestData())

    accountService.executeQuery(spark, "select *from test_db.test_table", "local/target")

    val dfCapture = ArgumentCaptor.forClass(classOf[Dataset[Row]])

    Mockito.verify(saveDaoMock).write(dfCapture.capture(),Matchers.eq("local/target"))

    val sourceDf = mockDFTestData()

    val expectedDf = sourceDf.withColumn("upper_greeting", upper(sourceDf.col("greeting")))

    assert(dfCapture.getValue.except(expectedDf).count() == 0)

  }



  /*It is the actual test case which will success for the give logic and data*/
  @Test
  def this_test_should_validate_changeString_method: Unit = {

    val expectedData = sourceStringTestData().split(" ").map(_.capitalize).mkString(" ")

    assert(expectedData.equals(accountService.changeString(sourceStringTestData())))
  }

  private def sourceStringTestData(): String = {
    "Hello John, How are you"
  }
}

}
