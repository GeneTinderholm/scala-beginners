package lectures.part1basics

import scala.annotation.tailrec

object Functions extends App {
    def aFunction(a: String, b: Int) = a + " " + b

    println(aFunction("Hello", 42))

    def aParameterlessFunction(): Int = 42

    println(aParameterlessFunction())
    println(aParameterlessFunction)

    def aRepeatedFunction(aString: String, n: Int): String = {
        if (n == 1) aString
        else aString + aRepeatedFunction(aString, n - 1)
    }

    println(aRepeatedFunction("Hello", 3))

    def callsFunc(func: () => Int): String = {
        println(func())
        "Three"
    }

    callsFunc(() => aParameterlessFunction())

    def aFunctionWithSideEffects(aString: String): Unit = println(aString)

    def aBigFunction(n: Int): Int = {
        def aSmallerFunction(a: Int, b: Int): Int = a + b

        aSmallerFunction(n, n - 1)
    }

    // Exercises
    // 1. A greeting function (name: String, age: Int) => "Hy, my name is $name and I am $age years old"
    def greeting(name: String, age: Int): String = s"Hi, my name is $name, and I am $age years old"

    println(greeting("Tom", 28))

    // 2. Factorial function
    def factorial(n: Int): Int = if (n <= 1) 1 else n * factorial(n - 1)

    println(factorial(1))
    println(factorial(2))
    println(factorial(3))
    println(factorial(4))
    println(factorial(5))

    // 3. A fibonacci function, start at 1
    def fibonacci(n: Int): Int = {
        @tailrec
        def fibHelp(a: Int, b: Int, count: Int): Int = if (count == n) a else fibHelp(b, a + b, count + 1)

        fibHelp(1, 1, 1)
    }

    println(fibonacci(1))
    println(fibonacci(2))
    println(fibonacci(3))
    println(fibonacci(4))
    println(fibonacci(5))
    println(fibonacci(6))
    println(fibonacci(7))
    println(fibonacci(8))
    println(fibonacci(9))
    println(fibonacci(10))

    // 4. Tests if a number is prime
    def isPrime(n: Int): Boolean = {
        @tailrec
        def primeHelper(i: Int): Boolean = {
            if (i <= 1) true
            else n % i != 0 && primeHelper(i - 1)
        }

        primeHelper(math.sqrt(n).toInt)
    }

    println(isPrime(2))
    println(isPrime(3))
    println(isPrime(4))
    println(isPrime(5))
    println(isPrime(6))
    println(isPrime(7))
    println(isPrime(8))
    println(isPrime(9))
    println(isPrime(10))
}
