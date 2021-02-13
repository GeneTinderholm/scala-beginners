package lectures.part2oop

import scala.language.postfixOps

object MethodNotations extends App {
    class Person(val name: String, favoriteMovie: String, val age: Int = 0) {
        def likes(movie: String): Boolean = favoriteMovie == movie
        def hangOutWith(person: Person): String = f"$name is hanging out with ${person.name}"
        def unary_! : String = "WHARGHARBLE!"
        def isAlive: Boolean = true
        def apply(): String = s"Hi, my name is $name, and I like $favoriteMovie"
        // exercises
        def +(nickname: String) = new Person(s"$name ($nickname)", favoriteMovie)
        def unary_+ : Person = new Person(name, favoriteMovie, age + 1)
        def learns(subject: String): String = s"$name learns $subject"
        def learnsScala: String = learns("Scala")
        def apply(n: Int): String = s"$name watched $favoriteMovie $n time${if (n == 1) "" else "s"}"
    }

    // infix
    val mary = new Person("Mary", "Inception")
    println(mary.likes("Inception"))
    println(mary likes "Inception")
    val tom = new Person("Tom", "Fight Club")
    println(mary hangOutWith tom)
    println(4.+(2))

    // prefix
    val x = -1
    val y = 1.unary_-
    println(!mary)

    // postfix
    // have to import postfixOps
    println(mary isAlive)

    // apply
    println(mary())

    println("========= EXERCISES ===========")
    // overload +, String => Person Name (nickname)
    println((mary + "The rockstar").name)
    // add age to Person class, default 0, add unary + operator that increments age
    println((+mary).age)
    // add a learns method, String => "$name learns $param", learnsScala () => learns("Scala"), use in postFix
    println(mary learns "things")
    println(mary learnsScala)
    // overload apply Int => String "$name watched $favoriteMove $number times"
    println(mary(1))
    println(mary(5))
}
