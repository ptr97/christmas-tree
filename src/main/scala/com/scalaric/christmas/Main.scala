package com.scalaric.christmas

import com.scalaric.christmas.tree.model.ChristmasTree
import com.scalaric.christmas.tree.service.ChristmasTreeService

import scala.language.postfixOps


object Main extends App {
  private val treeSize = 28
  private val treeChar = "*"
  private val waitMillis = 70


  val christmasTree = ChristmasTree(treeSize, treeChar)
  val program = ChristmasTreeService.showChristmasTree(christmasTree, waitMillis)

  program.unsafeRunSync()
}

