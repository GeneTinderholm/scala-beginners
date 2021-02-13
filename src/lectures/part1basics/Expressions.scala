package lectures.part1basics

object Expressions extends App {
    val x = 1 + 2
    println(x)

    println(2 + 3 * 4)
    // >>> right shift with zero extension

    println(1 == x)
    println(!(1 == x))

    var aVariable = 2
    aVariable += 3
    println(aVariable)

    val aCondition = true

    val aConditionedValue = if (aCondition) 5 else 3
    println(aConditionedValue)
    println(if (!aCondition) 5 else 3)

    var i = 0
    while (i < 10) {
        println(i)
        i += 1
    }
    val aWeirdValue: Unit = (aVariable = 3)
    println(aWeirdValue)

    // Code blocks
    val aCodeBlock = {
        val y = 2
        val z = y + 1

        if (z > 2) "Hello" else "Goodbye"
    }
    println(aCodeBlock)
}
