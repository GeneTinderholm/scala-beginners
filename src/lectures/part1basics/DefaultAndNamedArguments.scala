package lectures.part1basics

import scala.annotation.tailrec

object DefaultAndNamedArguments extends App {
    @tailrec
    def trFact(n: Int, acc: Int = 1): Int =
        if (n <= 1) acc
        else trFact(n - 1, n * acc)

    println(trFact(1))
    println(trFact(2))
    println(trFact(3))
    println(trFact(4))

    def savePicture(format: String = "jpg", width: Int = 1920, height: Int = 1080): Unit = println("saving picture")

    savePicture("png")
    savePicture(width = 4)
    savePicture(height = 500, format = "bmp", width = 400)
}
