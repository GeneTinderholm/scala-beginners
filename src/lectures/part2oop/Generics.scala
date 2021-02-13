package lectures.part2oop

import exercises.MyList

object Generics extends App {
//    class MyList[A] {
//
//    }
//    val listOfInts = new MyList[Int]
//    val listOfStrings = new MyList[String]
//
//    object MyList {
//        def empty[A]: MyList[A] = new MyList[A]
//    }
//    val secondListOfInts = MyList.empty[Int]
    // variance problem
    class Animal
    class Cat extends Animal
    class Dog extends Animal

    // does List[Cat] extend List[Animal]?
    // 1. Yes = COVARIANCE
    class CovariantList[+A]
    val animal: Animal = new Cat
    val listOfAnimal: CovariantList[Animal] = new CovariantList[Cat]
    // listOfAnimal.add(new Dog) ??? Hard question.

    // 2. No = INVARIANT
    class InvariantList[A]
    // val listOfAnimal: InvariantList[Animal] = new InvariantList[Cat] <- doesn't work
    val secondListOfAnimal = new InvariantList[Animal]

    // 3. Very much no. Contravariant
    class ContravariantList[-A]
    var contravariantList: ContravariantList[Cat] = new ContravariantList[Animal]
    class Trainer[-A]
    var trainer: Trainer[Cat] = new Trainer[Animal] // can train all animals

    // bounded types
    class Cage[A <: Animal](animal: A) // only accepts A that are subtypes of Animal
    val cage = new Cage(new Cat)

    class Car
    // val newCage = new Cage(new Car) // fails
    class SecondList[+A] {
        def add[B >: A](el: B): SecondList[B] = new SecondList[B] // B is a supertype of A
    }
    val secList = new SecondList[Cat]
    val newList = secList.add(new Dog)

    abstract class MyList {
        def head: Int
        def tail: MyList
        def isEmpty: Boolean
        def add(i: Int): MyList
        def printElements: String
        override def toString: String = "( " + printElements + ")"
    }

    //    object Empty extends MyList[Nothing] {
//        def head: Nothing = throw new NoSuchElementException
//        def tail: Nothing = throw new NotImplementedError()
//        def isEmpty: Boolean = true
//        def add[B >: Nothing](i: B): MyList[B] = new ThirdList[B](i, Empty)
//        def printElements: String = ""
//    }
//    class ThirdList[+A](el: A, remainder: MyList[A] = Empty) extends MyList[A] {
//        def head: A = el
//        def tail[B >: A]: MyList[B] = remainder
//        def isEmpty: Boolean = false
//        def add[B >: A](i: B): ThirdList[B] = new ThirdList[B](i, this)
//        def printElements: String = s"${el.toString} ${remainder.printElements}"
//    }

    trait MyPredicate[-T] {
        // test (T) => Boolean
    }
    trait MyTransformer[-A, B] {
        // convert from a -> b
    }
    // map my-transformer -> MyList[B]
    // filter MyPredicate
    // flatmap(transformer from A to MyList[B]) => MyList[B]
}
