package com.scalaric.christmas.tree.model

import cats.Show
import cats.implicits._


case class ChristmasTree(size: Int, treeChar: String)

object ChristmasTree {

  implicit val treeShow: Show[ChristmasTree] = Show { tree =>
    val rows = for {
      h <- 0 to tree.size
    } yield TreeRow(Star(tree.treeChar), tree.size - h, h)

    rows.map(_.show).mkString("\n")
  }
}
