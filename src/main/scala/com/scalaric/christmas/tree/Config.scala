package com.scalaric.christmas.tree

trait Config {
  def treeSize: Int
  def treeChar: String
  def waitMillis: Int
  def colorsWithProbability: Map[Config.Color, Config.Probability]
}

object Config {
  type Color = String
  type Probability = Int
}

object ScalaricChristmasConfig extends Config {
  override def treeSize: Int = 18
  override def treeChar: String = "*"
  override def waitMillis: Int = 45
  override def colorsWithProbability: Map[Config.Color, Config.Probability] = Map(
    Console.CYAN -> 1,
    Console.BLUE -> 1
  )
}
