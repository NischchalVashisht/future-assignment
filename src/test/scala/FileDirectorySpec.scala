
package com.knoldus

import org.scalatest._

class FileDirectorySpec extends FlatSpec with BeforeAndAfterAll {

    var fileDir=new FIleInDirectory
  override def beforeAll(): Unit = {
   // currency = new CurrencyConv()
  }

  override def afterAll(): Unit = {
    if (fileDir != null) {
      fileDir = null
    }
  }

  "file list should return " should "following type of file" in {
    val actualResult = fileDir.callingMain("/home/knoldus/Downloads/Check")
    val expectedResult ="Future(Success(List(SecondInsideSecond.txt, SecondInsideSecond2.txt, InsideFirst2.txt, insideFirst.txt, InsideSecond.txt, main.txt, )))"
    assert(expectedResult == actualResult)
  }

  
}
