package mymongo

import reactivemongo.api._
import scala.concurrent.ExecutionContext.Implicits.global

import reactivemongo.bson.BSONDocument
import reactivemongo.api.collections.bson.BSONCollection

import scala.concurrent.Future

/*
Get mongodb at http://www.mongodb.org/downloads

Run mongodb:
$ mongod --dbpath ~/mongodb

Integration with Scala with ReactiveMongo:
http://reactivemongo.org/#step-by-step-example
http://reactivemongo.org/releases/0.11/documentation/tutorial/write-documents.html

Integration with Play Framework
http://reactivemongo.org/releases/0.11/documentation/tutorial/play2.html
*/

object MainApp extends App {
  lazy val driver = new MongoDriver
  lazy val connection = driver.connection(List("localhost"))

  // Gets a reference to the database
  lazy val db = connection("university")

  // Gets a reference to the collection
  // By default, you get a BSONCollection.
  lazy val collection: BSONCollection = db("students")

  def insert(firstName: String, lastName: String) = {
    val doc = BSONDocument(
      "firstName" -> firstName,
      "lastName" -> lastName,
      "age" -> 30)

    collection.insert(doc)
  }

  def find(n: String) = {

    val resultsFuture: Future[List[BSONDocument]] =
      collection
        .find(BSONDocument("lastName" -> n))
        .cursor()
        .collect[List]()

    resultsFuture.foreach { (lst: List[BSONDocument]) =>
      lst.foreach { doc =>
        println(doc.get("firstName"))
        println(doc.get("age"))
        println(s"Found: ${BSONDocument pretty doc}")
      }
    }
  }

}
