package lectures.part3fp

import scala.annotation.tailrec

object HOFsAndCurries extends App {
    val superFunction: (Int, (String, Int => Boolean) => Int) => Int => Int =
        (h: Int, func: (String, Int => Boolean) => Int) => (k: Int) =>
            h + func("things", _ % 2 == 0) - k
    val result = superFunction(4, (str: String, cb: Int => Boolean) => if (cb(str.length)) 1 else 2)(-37)
    println(result)

    @tailrec
    def nTimes(f: Int => Int, n: Int, x: Int): Int =
        if (n <= 0) x
        else nTimes(f, n - 1, f(x))

    println(nTimes(_ + 1, 10, 1))

    // curried functions
    val superAdder = (x: Int) => (y: Int) => x + y
    // functions with multiple parameter lists
    def curriedFormatter(c: String)(x: Double): String = c.format(x)
    val standardFormat: Double => String = curriedFormatter("%4.2f")
    val preciseFormat: Double => String = curriedFormatter("%10.8f")

    println(standardFormat(Math.PI))
    println(preciseFormat(Math.PI))

    // currying exercise
    def toCurry[A, B, C](func: (A, B) => C): A => B => C =
        (el: A) => (other: B) => func(el, other)
    def fromCurry[A, B, C](func: A => B => C): (A, B) => C =
        (el: A, other: B) => func(el)(other)

    // compose
    def compose[A, B, C](f: A => B, g: C => A)(x: C): B =
        f(g(x))
    def andThen[A, B, C](f: A => B, g: B => C)(x: A): C =
        g(f(x))

    println(compose((el: Int) => el + 2, (el: Int) => el + 1)(4))
    println(andThen((el: Int) => el + 2, (el: Int) => el + 1)(4))
}
