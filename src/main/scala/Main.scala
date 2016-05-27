import java.io.File

import com.google.common.hash.Hashing
import com.google.common.io.Files

import scala.collection.convert.Wrappers.JIteratorWrapper

object Main {

  def main(args: Array[String]) {
    println("Starting, will include the following dirs:")
    args.foreach(println)
    val dirs = args.toList.map(fn => new File(fn)).filter(f => f.exists() && f.isDirectory)

    val allFiles = dirs.flatMap { d =>
      JIteratorWrapper(Files.fileTreeTraverser().breadthFirstTraversal(d).iterator())
    }.filterNot(_.isDirectory)

    val dupes = allFiles.map(f => (f, Files.hash(f, Hashing.crc32c())))
            .groupBy { case (f, hash) => hash }
            .filter {
              case (hash, h1::h2::tail) => true
              case _ => false
            }.mapValues { list => list.map { case (f, hash) => f } }
    dupes.foreach {
      case (hash, files) =>
        println(s"> Dupes with hash $hash")
        files.foreach(println)
    }
  }

}
