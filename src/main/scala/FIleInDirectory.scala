package com.knoldus
import java.io.File

import scala.concurrent.Future
import scala.concurrent.ExecutionContext.Implicits.global
import scala.util.{Failure, Success}

class FIleInDirectory {

  def getListOfFiles(dir: String): List[File] = {
    val file = new File(dir)
    if (file.exists && file.isDirectory) {
      file.listFiles.toList
    } else {
      List[File]()
    }
  }

  def iteratingOverDirectory(list: List[File], listOfFile: List[String]): List[String] = {
    list match {
      case Nil => listOfFile
      case headOfFile :: Nil => {
        if (headOfFile.isFile) headOfFile.getName :: listOfFile
        else iteratingOverDirectory(headOfFile.listFiles().toList, listOfFile)
      }
      case headOfFile :: tailOfFile => {
        if (headOfFile.isFile)
          iteratingOverDirectory(tailOfFile, headOfFile.getName :: listOfFile)
        else
          iteratingOverDirectory(list.drop(1) ::: headOfFile.listFiles().toList, listOfFile)
      }
    }
  }


  def callingMain(input: String):String = {
    //val fileReference = new FIleInDirectory
    val listOfMainFiles = this.getListOfFiles(input)
    val result = Future {
      this.iteratingOverDirectory(listOfMainFiles, List(""))
    }
    //println(result)

  //  println(result.mkString("--"))
    result.onComplete {
      case Success(listOfMainFiles) =>listOfMainFiles.mkString("--")
      case Failure(exception) => exception

    }
    Thread.sleep(5000)
result.toString
  }

}
/*
object FIleInDirectory extends App{

  println(new FIleInDirectory().callingMain("/home/knoldus/Downloads/Check"))

}*/
