package lectures.part4pm

import exercises.{Cons, MyList, Empty}

object AllThePatterns extends App {

    // 1 - constants
    val x: Any = "Scala"
    val constants = x match {
        case 1 => "a number"
        case "Scala" => "THE SCALA"
        case true => "The Truth"
        case AllThePatterns => "A singleton object"
    }
    println(constants)

    // 2 - match anything
    // 2.1 wildcard
    val matchAnything = x match {
        case _ => "All of the things"
    }
    println(matchAnything)

    // 2.2 variable
    val matchAVariable = x match {
        case something => s"I've found $something" // something has the value of x
    }
    println(matchAVariable)

    // 3 - tuples
    val aTuple = (1, 2)
    val matchATuple = aTuple match {
        case (1, 1) => "(1, 1)"
        case (something, 2) => s"something = $something"
        case (a, b) => s"($a $b)"
    }
    println(matchATuple)
    val nestedTuple = (1, (2, 3))
    val matchANestedTuple = nestedTuple match {
        case (_, (2, v)) => s"v = $v" // v = 3
    }
    println(matchANestedTuple)

    // 4 - case classes - constructor pattern
    val aList: MyList[Int] = Cons(1, Cons(2))
    val matchAList = aList match {
        case Empty => "Empty"
        case Cons(head, tail) => s"Cons($head, $tail)"
    }
    println(matchAList)

    //  5 - list patterns
    val aStandardList = List(1, 2, 3, 42)
    val aStandardListMatch = aStandardList match {
        case List(1, _, _, _) => "extractor pattern" // extractor
        case List(1, _*) => "vararg pattern" // list of arbitrary length
        case 1 :: List(_) => "infix pattern"
        case List(1, 2, 3) :+ 42 => "also infix pattern"
    }
    println(aStandardListMatch)

    // 6 - type specifiers
    val unknown: Any = 2
    val unknownMatch = unknown match {
        case list: List[Int] => f"matches on list of ints: $list" // explicit type specifier
        case _ => "Not a list of ints"
    }
    println(unknownMatch)

    // 7 - name binding
    val nameBindingMatch = aList match {
        case notEmptyList @ Cons(_, _) => s"name binding $notEmptyList" // use the name here
        case Cons(1, rest @ Cons(2, _)) => s"nested name binding $rest"
    }
    println(nameBindingMatch)

    // 8 - multi patterns
    val multiList: MyList[Int] = Cons(0, Cons(14))
    val multiPattern = multiList match {
        case Empty | Cons(0, _) => "compound pattern (multi-pattern)"
    }
    println(multiPattern)

    // 9 - if guards
    val secondElementSpecial = aList match {
        case Cons(_, Cons(specialElement, _)) if specialElement % 2 == 0 => s"if guard: $specialElement is even"
    }
    println(secondElementSpecial)

    // beware type erasure
    val numbers: Any = List(1, 2, 3)
    val numbersMatch = numbers match {
        case listOfStrings: List[String] => s"Strings: $listOfStrings"
        case listOfInts: List[Int] => s"Ints: $listOfInts"
        case _ => ""
    }
    println(numbersMatch)
}
