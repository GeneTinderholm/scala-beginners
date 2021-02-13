package lectures.part3fp

import scala.util.{Failure, Random, Success, Try}

object HandlingFailure extends App {
    // create success and failure
    val aSuccess = Success(3)
    val aFailure = Failure(new RuntimeException("Bad things happened"))
    println(aSuccess)
    println(aFailure)

    def unsafeMethod(): String = throw new RuntimeException("More bad things")

    val potentialFailure = Try(unsafeMethod())
    println(potentialFailure)

    val anotherPotentialFailure = Try {
        // code that might throw
        unsafeMethod()
    }
    println(potentialFailure.isSuccess)

    // orElse
    def backupMethod() = "Valid result"
    val fallbackTry = Try(unsafeMethod()).orElse(Try(backupMethod()))
    println(fallbackTry)
    println(fallbackTry.isSuccess)

    def betterUnsafeMethod(): Try[String] = Failure(new RuntimeException)
    def betterBackupMethod(): Try[String] = Success("Valid result")
    val betterFallbackTry = betterUnsafeMethod() orElse betterBackupMethod()
    println(betterFallbackTry)

    // map, flatMap, filter
    println(aSuccess.map(_ * 2))
    println(aSuccess.flatMap(x => Success(x * 10)))
    println(aSuccess.filter(_ > 10))

    /*
        Exercise
     */
    val hostname = "localhost"
    val port = "8080"
    def renderHTML(page: String): Unit = println(page)

    class Connection {
        def get(url: String): String = {
            val random = new Random(System.nanoTime())
            if (random.nextBoolean()) "<html>...</html>"
            else throw new RuntimeException("Connection Interrupted")
        }
    }
    object HttpService {
        val random = new Random(System.nanoTime())
        def getConnection(host: String, port: String): Connection = {
            if (random.nextBoolean()) new Connection
            else throw new RuntimeException("Port in use")
        }
    }
    // try to print html if it all works
    Try(HttpService.getConnection(hostname, port))
        .flatMap(conn => Try(conn.get(f"$hostname:$port")))
        .foreach(renderHTML)

    // for comprehension version
    for {
        connection <- Try(HttpService.getConnection(hostname, port))
        html <- Try(connection.get("/home"))
    } renderHTML(html)
}
