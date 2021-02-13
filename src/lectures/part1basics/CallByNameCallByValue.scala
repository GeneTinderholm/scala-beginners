package lectures.part1basics

object CallByNameCallByValue extends App {
    def calledByValue(x: Long): Unit = {
        println("by value:" + x)
        println("by value:" + x)
    }

  def calledByName(x: => Long): Unit = {
      println("by name: " + x)
      println("by name: " + x)
  }
  calledByValue(System.nanoTime())
  calledByName(System.nanoTime())
  def infinite (): Int = 1 + infinite()
  def printFirst(x: Int, y: => Int): Unit = println(x)
//  printFirst(infinite(), 34) // runs infinitely
  printFirst(34, infinite())

  /* calling by name delays evaluation until you actually use it (kind of like a lisp macro) */
}
