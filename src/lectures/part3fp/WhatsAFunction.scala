package lectures.part3fp

object WhatsAFunction extends App{
    val doubler = new MyFunction[Int, Int] {
        override def apply(el: Int): Int = el * 2
    }
    val tripler: MyFunction[Int, Int] = (el) => el * 3
    val quadrupler: MyFunction[Int, Int] = _ * 4
    println(doubler(2))
    println(tripler(2))
    println(quadrupler(2))
    val adder: (Int, Int) => Int = (a, b) => a + b // function type
    println(adder(4, 5))
    val lambdaAdder: Int => Int => Int = a => b => a + b
    println(lambdaAdder(4)(5))
}

trait MyFunction[A, B] {
    def apply(el: A): B
}