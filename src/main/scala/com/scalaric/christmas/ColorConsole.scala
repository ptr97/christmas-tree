package com.scalaric.christmas

import cats.effect.IO

import scala.util.Random


trait ColorConsole[F[_]] {
  type Color = String
  val cyan = List.fill(45)(Console.CYAN)
  val blue = List.fill(45)(Console.BLUE)
  val red = List.fill(10)(Console.RED)
  final val colors: List[Color] = cyan ::: blue ::: red

  def printChar(c: Char): F[Unit]

  def changeColor: F[Unit]

  def waitFor(millis: Int): F[Unit]
}

object ColorConsoleIO extends ColorConsole[IO] {

  override def printChar(c: Char): IO[Unit] = IO { print(c) }

  override def changeColor: IO[Unit] = IO {
    val index = Random.nextInt(colors.size)
    print(s"${colors(index)}")
  }

  override def waitFor(millis: Int): IO[Unit] = IO { Thread.sleep(millis) }
}
