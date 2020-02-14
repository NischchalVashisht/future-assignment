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
}

object FIleInDirectory extends App {
  val fileReference= new FIleInDirectory
  val listOfMainFiles = fileReference.getListOfFiles("/home/knoldus/Downloads/Check")
   val result=Future{
        fileReference.iteratingOverDirectory(listOfMainFiles, List(""))
   }
  //println(result)
   Thread.sleep(10000)
   println("sjkd"+result)
   result.onComplete{
     case Success(listOfMainFiles)=>println(s"files is $listOfMainFiles is "+result)
     case Failure(exception) =>println(exception.getMessage)

   }


}


