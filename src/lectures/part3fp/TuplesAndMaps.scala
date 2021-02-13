package lectures.part3fp

import scala.annotation.tailrec
// import scala.collection.mutable.{Map => MutableMap}

object TuplesAndMaps extends App {
    // Tuples
    val aTuple = Tuple2[Int, String](2, "Hello, Scala!")
    val stillATuple = Tuple2(2, "Hello, Scala!")
    val alsoATuple = (2, "Hello, Scala")

    println(aTuple._1)
    println(aTuple.copy(_2 = "goodbye Java"))
    println(aTuple.swap)

    // Maps
    val aMap: Map[String, Int] = Map()
    val phoneBook = Map(("Jim", 555), ("Daniel", 789))
    val anotherMap = Map("Jim" -> 555, "Daniel" -> 789)
    val aThirdMap = Map("Jim" -> 555, "Daniel" -> 789).withDefaultValue(-1)
    println(phoneBook)
    println(anotherMap)
    println(phoneBook.contains("Jim"))
    println(phoneBook("Jim"))
    println(aThirdMap("Mary")) // blows up without default value
    val newPairing = "Mary" -> 567
    val newPhonebook = phoneBook + newPairing
    println(newPhonebook)
    println(phoneBook.map(pair => pair._1.toLowerCase -> pair._2))
    println(phoneBook.filter(pair => pair._1.startsWith("J")))

    val names = List("Bob", "James", "Angela", "Mary", "Daniel", "Jim")
    println(names.groupBy(_.charAt(0)))

    // exercises
    val names2 = Map("Jim" -> 555, "JIM" -> 999)
    println(names2.map(pair => pair._1.toLowerCase -> pair._2))
    // person: String
    // add person
    // remove person
    // friend (mutual)
    // unfriend (mutual)
    // number of friends of a given person
    // person with most friends
    // how many people have no friends
    // if there is a social connection between two people, (direct or not)
    case class SocialNetwork(network: Map[String, Set[String]] = Map()) {
        def add(person: String): SocialNetwork = SocialNetwork(network + (person -> Set[String]()))
        def remove(person: String): SocialNetwork = {
            val newNetwork = network.filter(_._1 != person)
            val purgedNetwork = newNetwork.map(entry => entry._1 -> entry._2.filter(_ != person))
            SocialNetwork(purgedNetwork)
        }
        def friend(person: String, other: String): SocialNetwork = {
            val personEntry: Set[String] = network(person) + other
            val otherEntry: Set[String] = network(other) + person
            SocialNetwork(network + (person -> personEntry) + (other -> otherEntry))
        }
        def unfriend(person: String, other: String): SocialNetwork = {
            val personEntry: Set[String] = network(person).filter(_ != other)
            val otherEntry: Set[String] = network(other).filter(_ != person)
            SocialNetwork(network + (person -> personEntry) + (other -> otherEntry))
        }
        def socialConnection(person: String, other: String): Boolean = {
            @tailrec
            def bfs(target: String, consideredPeople: Set[String], discoveredPeople: Set[String]): Boolean = {
                val newDiscoveredPeople = discoveredPeople.diff(consideredPeople)
                if (newDiscoveredPeople.isEmpty) false
                else {
                    val current = newDiscoveredPeople.head
                    if (current == target) true
                    else bfs(target, consideredPeople + current, newDiscoveredPeople.tail ++ network(current))
                }
            }
            bfs(other, Set(), network(person) + person)
        }
    }
}
// first implementation
//        def isConnectedTo(person: String, other: String): Boolean = {
//            val searched = MutableMap[String, Boolean]()
//            def checkConnections(current: String) =
//                network(current).foldLeft(false)((acc, entry) => {
//                    if(acc) acc
//                    else {
//                        searched(entry) = true
//                        entry == other
//                    }
//                })
//            def checkPerson(personFriends: List[String]): Boolean =
//                if(personFriends.isEmpty) false
//                else {
//                    personFriends.map(checkConnections).fold(false)(_ || _) ||
//                        personFriends
//                            .filter(!searched.contains(_))
//                            .map(thing => checkPerson(network(thing)))
//                            .reduce(_ || _)
//                }
//            checkPerson(network(person))
//        }
