package com.scalaric.christmas

import cats.Show
import cats.implicits._

case class Star(c: String)
object Star {
  implicit val starShow: Show[Star] = Show { star => s"""${star.c}"""}
}

case class TreeRow(star: Star, spaces: Int, stars: Int)
object TreeRow {
  implicit val treeRowShow: Show[TreeRow] = Show { row =>
    val emptySpace = " " * row.spaces
    val starRepr = row.star.show
    val sideStars = starRepr * row.stars
    s"""$emptySpace$sideStars$starRepr$sideStars$emptySpace"""
  }
}

case class ChristmasTree(size: Int, treeChar: String)
object ChristmasTree {
  implicit val treeShow: Show[ChristmasTree] = Show { tree =>
    val rows = for {
      h <- 0 to tree.size
    } yield TreeRow(Star(tree.treeChar), tree.size - h, h)
    rows.map(_.show).mkString("\n")
  }
}
