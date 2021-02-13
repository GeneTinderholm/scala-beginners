package lectures.part4pm

import scala.util.Random

object PatternMatching extends App {
    val random = new Random()
    val x = random.nextInt(5)
    val description = x match {
        case 0 => "NULL"
        case 1 => "the ONE"
        case 2 => "double or nothing"
        case 3 => "third time is the charm"
        case _ => "something else" // _ = wildcard
    }
    println(x)
    println(description)

    // 1. decompose values
    case class Person(name: String, age: Int)
    val bob = Person("Bob", 20)
    val greeting = bob match {
        case Person(name, age) if age < 21 => s"Hi, my name is $name, and I am less than 21"
        case Person(name, age) => s"Hi, my name is $name, and I am $age years old"
        case _ => "I don't know who I am"
    }
    println(bob)
    println(greeting)

    sealed class Animal
    case class Dog(breed: String) extends Animal
    case class Parrot(greeting: String) extends Animal

    val animal: Animal = Dog("Terra Nova")
    animal match {
        case Dog(breed) => println(s"Dog: $breed")
        case Parrot(greeting) => println(s"Parrot says $greeting")
    }
    /*
        Exercise
        use pattern matching
        takes Expr => human readable form

     */
    trait Expr
    case class Number(n: Int) extends Expr
    case class Sum(e1: Expr, e2: Expr) extends Expr
    case class Prod(e1: Expr, e2: Expr) extends Expr

    def getProdString(e: Expr): String = e match {
        case Sum(_, _) => s"(${humanReadable(e)})"
        case _ => humanReadable(e)
    }

    def humanReadable(e: Expr): String = e match {
        case Number(n) => n.toString
        case Sum(e1, e2) => s"${humanReadable(e1)} + ${humanReadable(e2)}"
        case Prod(e1, e2) => s"${getProdString(e1)} * ${getProdString(e2)}"
        case _ => "unknown expression"
    }
    println(humanReadable(Sum(Number(3), Number(4))))
    println(humanReadable(Prod(Number(3), Number(4))))
    println(humanReadable(Prod(Sum(Number(3), Number(5)), Number(4))))
}

