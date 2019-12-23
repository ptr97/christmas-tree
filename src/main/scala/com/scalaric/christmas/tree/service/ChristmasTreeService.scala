package com.scalaric.christmas.tree.service

import cats.data.Kleisli
import cats.effect.IO
import cats.implicits._
import com.scalaric.christmas.ColorConsoleIO
import com.scalaric.christmas.tree.Config
import com.scalaric.christmas.tree.model.ChristmasTree

import scala.language.postfixOps


trait ChristmasTreeService {

  def createChristmasTree: Kleisli[IO, Config, ChristmasTree] = Kleisli { config =>
    IO { ChristmasTree(config.treeSize, config.treeChar) }
  }

  def createColorConsole: Kleisli[IO, Config, ColorConsoleIO] = Kleisli { config =>
    IO { ColorConsoleIO(config) }
  }

  def showChristmasTree(colorConsoleIO: ColorConsoleIO, christmasTree: ChristmasTree): Kleisli[IO, Config, Unit] = Kleisli { config =>
    val treeChars = christmasTree.show
    val printActions = treeChars.map { showChristmasTreeChar(_, colorConsoleIO, config.treeChar, config.waitMillis) }.toList
    printActions.sequence.map { _ => () }
  }

  private def showChristmasTreeChar(c: Char, colorConsoleIO: ColorConsoleIO, treeChar: String, waitMillis: Int): IO[Unit] = {
    for {
      _ <- colorConsoleIO.changeColor
      _ <- colorConsoleIO.printChar(c)
      _ <- if (c.toString === treeChar) colorConsoleIO.waitFor(waitMillis) else IO.pure()
    } yield ()
  }
}

object ChristmasTreeService extends ChristmasTreeService
