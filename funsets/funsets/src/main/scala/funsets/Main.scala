package funsets

object Main extends App {
  import FunSets._
  println(contains(singletonSet(1), 1))

  val union_1 = union(singletonSet(1), singletonSet(2))
  val union_2 = union(union_1, singletonSet(3))


  printSet(union_2)

}
