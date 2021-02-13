package lectures.part1basics

object SmartOperationsOnStrings extends App {
    val str = "Hello, I am learning Scala"

    println(str)
    println(str.charAt(2))
    println(str charAt 2)
    println(str.substring(7, 11))
    println(str.split(" ").toList)
    println(str.replace(" ", "-"))
    println(str.toLowerCase)
    println(str.length)

    val aNumberString = "45"
    val aNumber = aNumberString.toInt
    println(aNumber)
    println('a' +: aNumberString :+ 'z')
    println('a' + aNumberString + 'z')
    println(str.reverse)
    println(str take 2)

    println('a' +: aNumberString :+ 'z')

    // s interpolators
    println(s"This is an interpolated string, aNumberString = $aNumberString")
    println(s"You can do expressions too aNumber + 5 = ${aNumber + 5}")

    // f interpolators
    val name = "David"
    val speed = 1.2f
    println(f"$name%s can eat $speed%2.2f burgers per minute")

    // raw interpolator
    println(raw"This is a \n newline")
    val test = "This is a \n newline"
    println(raw"$test")
}