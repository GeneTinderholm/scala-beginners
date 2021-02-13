package lectures.part3fp

object MapFlatmapFilterFor extends App {
    val list = List(1, 2, 3)
    println(list)
    println(list.head)
    println(list.tail)

    // map
    println(list.map(_ + 1))
    println(list.map(_ + " is a number"))

    // filter
    println(list.filter(_ % 2 == 0))

    // flatMap
    val toPair = (x: Int) => List(x, x + 1)
    println(list.flatMap(toPair))

    def permutations[A, B](listOne: List[A], listTwo: List[B]): Unit = {
        listOne.flatMap(el => listTwo.map(other => "" + el + other)).foreach(println)

    }
    permutations(List(1, 2, 3, 4), List('a', 'b', 'c', 'd'))

    // for comprehension
    val numbers = List(1, 2, 3, 4)
    val chars = List('a', 'b', 'c', 'd')
    val colors = List("White", "Black")
    val forCombinations = for {
        n <- numbers if n % 2 == 0
        c <- chars
        color <- colors
    } yield "" + c + n + "-" + color
    println(forCombinations)

    // can be used with side effects
    for {
        n <- numbers
    } println(n)
    numbers.map { x =>
        x * 2
    }
    abstract class Maybe[+T] {
        def map[S](f: T => S): Maybe[S]
        def flatMap[S](f: T => Maybe[S]): Maybe[S]
        def filter(f: T => Boolean): Maybe[T]
    }
    case object MaybeNo extends Maybe[Nothing] {
        override def map[S](f: Nothing => S): Maybe[S] = MaybeNo
        override def flatMap[S](f: Nothing => Maybe[S]): Maybe[S] = MaybeNo
        override def filter(f: Nothing => Boolean): Maybe[Nothing] = MaybeNo
    }
    case class MaybeYes[+T](value: T) extends Maybe[T] {
        override def map[S](f: T => S): Maybe[S] = MaybeYes(f(value))
        override def flatMap[S](f: T => Maybe[S]): Maybe[S] = f(value)
        override def filter(f: T => Boolean): Maybe[T] =
            if (f(value)) this
            else MaybeNo
    }
}
