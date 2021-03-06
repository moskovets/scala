package lectures.functions

/**
  *
  * В объекте 'Computation' в методе computation сравниваются 2 массива.
  * Результатом метода будет массив, содержащий 2 элемента Array("Клара", "Карла")
  *
  * С помощью Thread.sleep имитируеться прододжительное вычисление
  */
trait Data {
  val filterData = "Клара у Карла украла корралы, Карл у Клары украл кларнет"
  val dataArray = "Клара Цеткин обожала Карла Маркса".split(" ")
}

object Computation extends App with Data {

  def computation(filterData: String, dataProducer: Array[String]): Array[String] = {
    //EMULATE HEAVY LOAD
    Thread.sleep(10)
    //PRODUCE WORDS ARRAY FROM A STRING
    val filterArray = filterData.split(" ")

    //EMULATE HEAVY LOAD
    Thread.sleep(100)
    // LEAVE ONLY EQUAL WORDS IN BOTH ARRAYS
    dataProducer.filter(dataItem => filterArray.contains(dataItem))
  }

  val result = computation(filterData, dataArray)
  result.foreach(println)
}

/**
  * Допишите curriedComputation, так, что бы после вызова partiallyAppliedCurriedFunction
  * результат был бы тем же, что и в предыдущем случае
  *
  * Раскомментируйте последнюю строчку
  *
  * Какой тип имеет partiallyAppliedCurriedFunction - ?
  */
object CurriedComputation extends App with Data {

  def curriedComputation(filterData: String)(dataProducer: Array[String]): Array[String] = {
  val filterArray = filterData.split(" ")
  dataProducer.filter(dataItem => filterArray.contains(dataItem))
}

  val partiallyAppliedCurriedFunction = curriedComputation(filterData)_

  val result = partiallyAppliedCurriedFunction(dataArray)
  result.foreach(print)
}

/**
  * Допишите реализации методов так, что бы результат совпадал с предыдущими.
  */
object FunctionalComputation extends App with Data {
  def functionalComputation(filterData: String): (Array[String]) => Array[String] =
    (dataProducer: Array[String]) => dataProducer.filter(dataItem => filterData.contains(dataItem))
  // 2 вариант
  /*def functionalComputation(filterData: String): (Array[String]) => Array[String] = {
    def f(dataProducer: Array[String]): Array[String] = {
      dataProducer.filter(dataItem => filterData.contains(dataItem))
    }
    f
  }*/

  val filterApplied = functionalComputation(filterData)

  val result = filterApplied(dataArray)
  result.foreach(print)
}



