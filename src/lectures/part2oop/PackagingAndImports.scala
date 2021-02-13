package lectures.part2oop

import lectures.part2oop.OOBasics.Writer

//import java.util.{Date, Properties} // multiple imports
//import java.sql.{Date => SqlDate} // rename on import
//import java.awt._ // import *

class PackagingAndImports extends App {
    val writer = new Writer("Daniel", "Guy", 1703)
    val person = new Person // also in OOBasics file, but in the lectures.part2oop package
    freeFunc() // in package.scala

    // default imports
    // java.lang
    // scala
    // scala.Predef
}
