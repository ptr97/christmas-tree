package com.scalaric.christmas.tree.service

import cats.effect.IO
import cats.implicits._
import com.scalaric.christmas.ColorConsoleIO
import com.scalaric.christmas.tree.model.ChristmasTree

import scala.language.postfixOps


trait ChristmasTreeService {

  private def showChristmasTreeChar(c: Char, treeChar: String, waitMillis: Int): IO[Unit] = {
    for {
      _ <- ColorConsoleIO.changeColor
      _ <- ColorConsoleIO.printChar(c)
      _ <- if (c.toString === treeChar) ColorConsoleIO.waitFor(waitMillis) else IO.pure()
    } yield ()
  }

  def showChristmasTree(christmasTree: ChristmasTree, waitMillis: Int): IO[Unit] = {
    val chars: String = christmasTree.show
    val list: List[IO[Unit]] = chars map { c: Char => showChristmasTreeChar(c, christmasTree.treeChar, waitMillis) } toList

    list.sequence.map { _ => ()}
  }
}

object ChristmasTreeService extends ChristmasTreeService
