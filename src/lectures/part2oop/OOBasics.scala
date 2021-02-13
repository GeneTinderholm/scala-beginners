package lectures.part2oop

import java.time.LocalDate

object OOBasics extends App {
    val person = new Person("Tom", 42)
    println(person.name)
    println(person.age)
    println(person.x)
    person.name = "Steve"
    println(person.name)
    person.greet("Dan")
    person.greet()
    val x = person.otherGreet
    new Person()


    println("========= EXERCISES ==========")
    /*
        Novel and writer class
        Writer: first name, surname, year o' birth
          - method fullname => firstname + surname
        Novel: name, year o' release, author: Writer
          - method authorAge => age of author at release
          - isWrittenBy(author: Writer)
          - copy (new year o' release) => new instance of novel
     */

    class Writer(firstName: String, lastName: String, val yearOfBirth: Int) {
        def fullName = s"$firstName $lastName"
    }
    val writer = new Writer("Ernest", "Hemingway", 1899)
    println(writer.fullName)

    class Novel(val name: String, val yearOfRelease: Int, val author: Writer) {
        def authorAge: Int = yearOfRelease - author.yearOfBirth
        def isWrittenBy(author: Writer): Boolean =
            author.fullName == this.author.fullName && author.yearOfBirth == this.author.yearOfBirth
        def copy(newYearOfRelease: Int): Novel = new Novel(this.name, newYearOfRelease, this.author)
    }
    val novel = new Novel("The Sun Also Rises", 1926, writer)
    println(novel.authorAge)
    println(novel.isWrittenBy(writer))
    println(novel.copy(1935).yearOfRelease)

    /*
        Counter class
        - takes int
        - method: currentCount
        - method inc/dec => new Counter
        - overload inc/dec to take int
     */
    class Counter(val count: Int) {
        def increment: Counter = new Counter(count + 1)
        def decrement: Counter = new Counter(count - 1)
        def increment(offset: Int): Counter = new Counter(count + offset)
        def decrement(offset: Int): Counter = new Counter(count - offset)
    }
    val counter = new Counter(0)
    println(counter.count)
    println(counter.increment.count)
    println(counter.decrement.count)
    println(counter.increment(10).count)
    println(counter.decrement(50).count)
}


class Person(var name: String, val age: Int) {
    println(f"$name $age")
    val x = 13

    def greet(name: String): Unit = println(f"${this.name} says: Hi $name")
    def greet(): Unit = println(f"Hi, I am $name")
    def otherGreet: String =  {
        println("In otherGreet")
        "things n stuff"
    }

    def this(name: String) = this(name, 0)
    def this() = {
        this("John", 14)
        println("things")
    }
}