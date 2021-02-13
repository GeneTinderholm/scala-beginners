package lectures.part3fp

import scala.util.Random

object Options extends App {
    val myFirstOption: Option[Int] = Some(4)
    val mySecondOption: Option[Int] = None

    println(myFirstOption)
    println(mySecondOption)

    def unsafeMethod(): String = null

    val result = Option(unsafeMethod())
    println(result)
    def safeMethod(): String = "String"
    val chainedResult = Option(unsafeMethod()).orElse(Option(safeMethod()))
    println(chainedResult)

    def betterUnsafeMethod(): Option[String] = None
    def betterSafeMethod(): Option[String] = Some("String")
    val betterChainedResult = betterSafeMethod() orElse betterUnsafeMethod()
    println(betterChainedResult)

    println(myFirstOption.isEmpty)
    println(myFirstOption.get) // unsafe
    println(myFirstOption.map(_ * 2))
    println(myFirstOption.filter(_ % 2 != 0))
    println(myFirstOption.flatMap(el => Option(el * 10)))
    val config: Map[String, String] = Map("host" -> "176.45.36.1", "port" -> "80")
    class Connection {
        def connect = "connected"
    }
    object Connection {
        val random = new Random(System.nanoTime())
        def apply(host: String, port: String): Option[Connection] =
            if (random.nextBoolean()) Some(new Connection)
            else None
    }
    // try to establish a connection if so - print the connect method
    val connection = Connection(config("host"), config("port"))
    println(connection)

    for {
        con <- connection
    } println(con.connect)

    if (connection.nonEmpty)
        println(connection.get.connect)

    // etc
}
