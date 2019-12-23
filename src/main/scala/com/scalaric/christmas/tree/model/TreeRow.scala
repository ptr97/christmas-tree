package com.scalaric.christmas.tree.model

import cats.Show
import cats.implicits._

case class TreeRow(star: Star, spaces: Int, stars: Int)

object TreeRow {
  implicit val treeRowShow: Show[TreeRow] = Show { row =>
    val emptySpace = " " * row.spaces
    val starRepr = row.star.show
    val sideStars = starRepr * row.stars
    s"""$emptySpace$sideStars$starRepr$sideStars$emptySpace"""
  }
}
