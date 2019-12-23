package com.scalaric.christmas

import cats.data.Kleisli
import cats.effect.IO
import com.scalaric.christmas.tree.Config
import com.scalaric.christmas.tree.ScalaricChristmasConfig
import com.scalaric.christmas.tree.service.ChristmasService


object Main extends App {

  val program: Kleisli[IO, Config, Unit] = for {
    colorConsole <- ChristmasService.createColorConsole
    christmasTree <- ChristmasService.createChristmasTree
    _ <- ChristmasService.showChristmasTree(colorConsole, christmasTree)
  } yield ()

  program.run(ScalaricChristmasConfig).unsafeRunSync()
}
