package lectures.part1basics

import scala.annotation.tailrec

object Recursion extends App {
    def factorial(n: Int): Int =
        if (n <= 1) 1
        else n * factorial(n - 1)

    def anotherFactorial(n: Int): Int = {
        @tailrec
        def factHelper(i: Int, acc: Int): Int =
            if (i <= 1) acc
            else factHelper(i - 1, acc * i)

        factHelper(n, 1)
    }

    // Exercises
    // 1. rewrite string concat function tail recursively
    def greeting(name: String, age: Int): String = {
        @tailrec
        def greetHelp(pieces: List[String], greetString: String): String =
            if (pieces.isEmpty) greetString
            else greetHelp(pieces.slice(1, pieces.length), greetString + pieces.head)

        greetHelp(List("Hello, my name is ", name, " I am ", age.toString, " years old."), "")
    }

    println(greeting("Todd", 42))

    // 2. isPrime function
    def isPrime(n: Int): Boolean = {
        @tailrec
        def primeHelper(i: Int): Boolean = {
            if (i <= 1) true
            else n % i != 0 && primeHelper(i - 1)
        }

        primeHelper(math.sqrt(n).toInt)
    }

    // 3. Fib tail recursive
    def fibonacci(n: Int): Int = {
        @tailrec
        def fibHelp(a: Int, b: Int, count: Int): Int = if (count == n) a else fibHelp(b, a + b, count + 1)

        fibHelp(1, 1, 1)
    }

}
