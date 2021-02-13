package lectures.part2oop

object CaseClasses extends App {
    case class Person(name: String, age: Int)
    // 1. class parameters are fields
    val jim = new Person("Jim", 34)
    // 2. sensible toString
    println(jim)
    // 3. Person.apply implemented
    val jimTwo = Person("Jim", 34)
    // 4. equals/hashCode implemented
    println(jim == jimTwo)
    // 5. copy method
    val jimThree = jim.copy()
    // 6. case classes have companion objects
    val person = Person

    // 7. case classes are serializable
    // 8. case classes have extractor patterns == CCs can be used in pattern matching
    // 9. case objects are also a thing
}
