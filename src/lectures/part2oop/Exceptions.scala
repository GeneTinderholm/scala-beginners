package lectures.part2oop

object Exceptions extends App {
    val x: String = null
//    println(x.length) // throws
    // throwable classes extend the Throwable class
    // Exception and Error
    def getInt(withExceptions: Boolean): Int =
        if (withExceptions)
            throw new RuntimeException("No int for you")
        else 42

    val result = try {
        getInt(true)
    } catch {
        case e: RuntimeException => 43
        case e: NullPointerException => 44
    } finally {
        println("In finally")
    }

    println(result)
    // Custom exceptions
    class MyException extends Exception
    val exception = new MyException
//    throw exception
    def x(i: Int): Int = i + x(i)
//    x(1)
}
