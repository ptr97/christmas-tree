package com.scalaric.christmas.tree.model

import cats.Show

case class Star(c: String)

object Star {
  implicit val starShow: Show[Star] = Show { star =>
    s"""${star.c}"""
  }
}
