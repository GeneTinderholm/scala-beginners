package lectures.part2oop

object ScalaObjects extends App {
    object Person {
        // "class" level functionality
        val N_EYES = 2
        def canFly: Boolean = false
        def apply(name: String): Person = new Person(name)
    }
    class Person(val name: String) {
        // instance level functionality
    }
    // these are companions
    println(Person.N_EYES)
    println(Person.canFly)

    val steve = Person("Steve")
    println(steve.name)

    // Scala Applications = scala object with:
    // def main(args: Array[String]): Unit

}
