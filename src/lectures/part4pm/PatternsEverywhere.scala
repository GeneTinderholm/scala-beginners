package lectures.part4pm

object PatternsEverywhere extends App {

    // 1
    try {
        // code
    } catch {
        case e: RuntimeException => "runtime"
        case npe: NullPointerException => "null"
        case _ => "something else"
    }
    // catches are actually matches on the error

    // 2
    val list = List(1, 2, 3, 4)
    val evenOnes = for {
        x <- list if x % 2 == 0 // ?
    } yield 10 * x
    // generators are also based on pattern matching

    val tuples = List((1, 2), (3, 4))
    val filterTuples = for {
        (first, second) <- tuples
    } yield first * second

    // 3
    val tuple = (1, 2, 3)
    val (a, b, c) = tuple
    println(b)

    val head :: tail = list
    println(head)
    println(tail)

    // 4 partial function
    val mappedList = list.map {
        case v if v % 2 == 0 => v + " is even"
        case 1 => "the one"
        case _ => "odd"
    }
    println(mappedList)
    val mappedList2 = list.map(x => x match { // equivalent
        case v if v % 2 == 0 => v + " is even"
        case 1 => "the one"
        case _ => "odd"
    })
    println(mappedList2)
}
