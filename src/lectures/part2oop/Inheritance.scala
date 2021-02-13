package lectures.part2oop

object Inheritance extends App {
    // single class inheritance only
    class Animal {
        val creatureType = "Wild"
        def eat(): Unit = println("nomnom")
        protected def moo(): Unit = println("Moo")
    }
    class Cat extends Animal {
        def crunch(): Unit = {
            moo()  // only accessable here
            println("crunch")
        }
    }
    val cat = new Cat
    cat.eat()
    cat.crunch()

    // constructors
    class Person(val name: String, val age: Int)
    class Adult(name: String, age: Int, val idCard: String) extends Person(name, age)

    val tom = new Adult("Tom", 42, "None")
    println(tom.name)

    class Dog extends Animal {
        override val creatureType: String = "domestic" // can override vals
        override def eat(): Unit = println("crunch crunch")
    }
    val dog = new Dog
    dog.eat() // no longer protected
}
