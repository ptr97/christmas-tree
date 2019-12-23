package com.scalaric.christmas

import cats.effect.IO
import com.scalaric.christmas.Config.Color
import scala.util.Random

trait ColorConsole[F[_]] {
  def colors: List[Color]
  def printChar(c: Char): F[Unit]
  def changeColor: F[Unit]
  def waitFor(millis: Int): F[Unit]
}

class ColorConsoleIO(withColors: List[String]) extends ColorConsole[IO] {
  override def colors: List[Color] = withColors
  override def printChar(c: Char): IO[Unit] = IO { print(c) }
  override def changeColor: IO[Unit] = IO { print(colors(Random.nextInt(colors.size))) }
  override def waitFor(millis: Int): IO[Unit] = IO { Thread.sleep(millis) }
}

object ColorConsoleIO {
  def apply(config: Config): ColorConsoleIO = {
    val colors: List[Color] = config.colorsWithProbability.foldLeft(List.empty[Color]) { case (acc, (color, prob)) =>
      List.fill(prob)(color) ::: acc
    }
    new ColorConsoleIO(colors)
  }
}
