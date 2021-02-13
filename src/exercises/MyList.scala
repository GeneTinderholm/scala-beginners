package exercises

abstract class MyList[+A] {
    def head: A
    def tail: MyList[A]
    def isEmpty: Boolean
    def add[B >: A](i: B): MyList[B]
    def printElements: String
    override def toString: String = "( " + printElements + ")"
    def map[B](transformer: A => B): MyList[B]
    def filter(predicate: A => Boolean): MyList[A]
    def flatMap[B](predicate: A => MyList[B]): MyList[B]
    def concat[B >: A](lst: MyList[B]): MyList[B]
    def forEach(func: A => Unit): Unit
    def sort(func: (A, A) => Int): MyList[A]
    def zipWith[B, C](other: MyList[B], zipFunc: (A, B) => C): MyList[C]
    def fold[B](start: B)(func: (B, A) => B): B
}

case object Empty extends MyList[Nothing] {
    override def head: Nothing = throw new NoSuchElementException
    override def tail: Nothing = throw new NoSuchElementException
    override def isEmpty: Boolean = true
    override def add[A](i: A): MyList[A] = new Cons(i, Empty)
    override def printElements: String = ""
    override def map[B](transformer: Nothing => B): MyList[B] = Empty
    override def filter(predicate: Nothing => Boolean): MyList[Nothing] = Empty
    override def flatMap[B](predicate: Nothing => MyList[B]): MyList[Nothing] = Empty
    override def concat[B](lst: MyList[B]): MyList[B] = lst
    override def forEach(func: Nothing => Unit): Unit = ()
    override def sort(func: (Nothing, Nothing) => Int): MyList[Nothing] = Empty
    override def zipWith[B, C](other: MyList[B], zipFunc: (Nothing, B) => C): MyList[C] =
        if (!other.isEmpty) throw new RuntimeException("Lists are not the same length")
        else Empty
    override def fold[B](start: B)(func: (B, Nothing) => B): B = start
}

case class Cons[+A](el: A, remainder: MyList[A] = Empty) extends MyList[A] {
    override def head: A = el
    override def tail: MyList[A] = remainder
    override def isEmpty: Boolean = false
    override def add[B >: A](i: B): MyList[B] = new Cons(i, this)
    override def printElements: String = el + " " + remainder.printElements
    override def map[B](transformer: A => B): MyList[B] =
        new Cons(transformer(head), tail.map(transformer))
    override def filter(predicate: A => Boolean): MyList[A] =
        if(predicate(head)) new Cons(head, tail.filter(predicate)) else tail.filter(predicate)
    override def flatMap[B](transformer: A => MyList[B]): MyList[B] =
        transformer(head).concat(tail.flatMap(transformer))
    override def concat[B >: A](lst: MyList[B]): MyList[B] = new Cons(head, remainder.concat(lst))
    override def forEach(func: A => Unit): Unit = {
        func(el)
        remainder.forEach(func)
    }
    override def sort(func: (A, A) => Int): MyList[A] = {
        if (remainder == Empty)
            return this
        val sortedRem = remainder.sort(func)
        val result = func(head, sortedRem.head)
        if(result <= 0)
            return new Cons(head, sortedRem)
        val newRem = new Cons(head, sortedRem.tail).sort(func)
        new Cons(sortedRem.head, newRem)
    }
    override def zipWith[B, C](other: MyList[B], zipFunc: (A, B) => C): MyList[C] =
        if (other.isEmpty) throw new RuntimeException("Lists are not the same length")
        else new Cons(zipFunc(head, other.head), remainder.zipWith(other.tail, zipFunc))

    override def fold[B](start: B)(func: (B, A) => B): B = tail.fold(func(start, head))(func)
}
object Cons {
    def apply[A](el: A, remainder: MyList[A] = Empty): Cons[A] = new Cons(el, remainder)
}

trait MyPredicate[-T] {
    def test(el: T): Boolean
}
trait MyTransformer[-A, B] {
    def convert(el: A): B
}

object Thing extends App {
    val x = new Cons(4)
    val y = x.add(6)
    val z = y.concat(x)
    println(z.toString)
    class FMapTransformer extends MyTransformer[Int, MyList[Int]] {
        def convert(el: Int) = new Cons(el, new Cons(el, Empty))
    }
    val a = z.flatMap((el: Int) => Cons(el, Cons(el + 1)))
    println(a.toString)
    class LessThanFivePredicate extends MyPredicate[Int] {
        def test(el: Int): Boolean = el < 5
    }
    println(z.filter((el: Int) => el < 5).toString)
    class SquareTransformer extends MyTransformer[Int, Int] {
        override def convert(el: Int): Int = el * el
    }
    println(z.map((el: Int) => el * el))
    z.forEach(println)
    println(z.sort((a, b) => a - b))
    println(z.sort((a, b) => b - a))
    println(z.zipWith(new Cons(1, new Cons(2, new Cons(3))), (a: Int, b: Int) => a + b))
    println(z.fold(0)(_ + _))
}
