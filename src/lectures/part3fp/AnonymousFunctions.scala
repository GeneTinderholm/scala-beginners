package lectures.part3fp

object AnonymousFunctions extends App {
    val doubler: Int => Int = _ * 2
    val anotherDoubler = (el: Int) => el * 2

    val justDoSomething: () => Int = () => 3

    println(justDoSomething)
    println(justDoSomething())

    val funcList = List(() => 1, () => 2, () => 3, () => 4)
    val nums = funcList.map(_())
    println(nums)

    // both work
    val complexLambda = (str: String) => {
        println(str)
        str
    }
    val blockLambda = { (str: String) =>
        println(str)
        str
    }
    println(complexLambda("Hello"))
    println(blockLambda("World"))
    // multi arg underscores
    val adder: (Int, Int) => Int = _ + _
    println(adder(4, 5))
}
