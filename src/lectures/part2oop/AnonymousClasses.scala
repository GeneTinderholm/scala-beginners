package lectures.part2oop

object AnonymousClasses extends App {
    abstract class Animal {
        def eat(): Unit
    }

    val funnyAnimal: Animal = new Animal {
        override def eat(): Unit = println("Mooooooooooooooooo!!!!!!!!!!")
    }
    val secondAnimal: Animal = () => println("What?")  // functional interface basically?
    funnyAnimal.eat()
    secondAnimal.eat()

    class Person(val name: String) {
        def sayHi(): Unit = println(s"Hi, I'm $name")
    }
    val jim = new Person("Jim") {
        override def sayHi(): Unit = println("I'm Jim")
    }
}
