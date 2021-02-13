package lectures.part3fp

import scala.util.Random

object Sequences extends App {
    // sequences
    val aSequence = Seq(1, 2, 3, 4) // actually a list under the hood
    println(aSequence)
    println(aSequence.reverse)
    println(aSequence(2))
    println(aSequence ++ Seq(5, 6, 7)) // concatenation method
    println((Seq(5, 6, 7) ++ aSequence).sorted)

    // ranges
    val aRange = 1 to 10 // inclusive
    val anotherRange = 1 until 10 // non-inclusive
    aRange.foreach(println)
    anotherRange.foreach(println)

    // lists
    val aList = List(1, 2, 3, 4)
    val prepended = 42 +: aList
    println(prepended)
    val appended = aList :+ 13
    println(appended)

    val apples5 = List.fill(5)("apple")
    println(apples5)
    println(aList.mkString("-|-"))

    // arrays
    val numbers = Array(1, 2, 3, 4)
    val threeElements = Array.ofDim[Int](3)
    numbers(2) = 0 // numbers.update(2, 0)
    println(numbers.mkString(" "))

    val numbersSeq: Seq[Int] = numbers // implicit conversion
    println(numbersSeq)

    // vectors
    val vector: Vector[Int] = Vector(1, 2, 3)
    println(vector)

    // vectors vs lists
    val maxRuns = 1000
    val maxCapacity = 1000000
    def getWriteTime(collection: Seq[Int]): Double = {
        val r = new Random
        val times = for {
            it <- 1 to maxRuns
        } yield {
            val currentTime = System.nanoTime()
            collection.updated(r.nextInt(maxCapacity), 0)
            System.nanoTime() - currentTime
        }
        times.sum * 1.0 / maxRuns
    }
    val numbersList = (1 to maxCapacity).toList
    val numbersVector = (1 to maxCapacity).toVector
    // keeps reference to tail
    // updating an element in the middle takes long
    println(f"List:   ${getWriteTime(numbersList)} milliseconds")
    // depth of tree is small
    // needs to replace an entire 32 element chunk
    println(f"Vector: ${getWriteTime(numbersVector)} milliseconds")
}
