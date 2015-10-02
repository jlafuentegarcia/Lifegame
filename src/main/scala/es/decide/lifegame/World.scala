package es.decide.lifegame

/**
 * Created by jlafuente on 02/10/2015.
 */
class World (val height : Int, val width : Int, alive : List[(Int, Int)]) {
  private val positions = new Array[Array[Boolean]](width)

  for ( i <- 0 until height) {
    positions(i) = Array.fill[Boolean](height)(false)
  }
  for ( (x, y) <- alive ) positions(x)(y) = true

  def numAlive = alive.length

  def isAlive(x : Int, y: Int ) : Boolean = positions(x)(y)

  def getNbAliveNeighbours(x : Int, y : Int ) : Int = (for {x1 <- math.max(0, x - 1) to math.min(width-1, x + 1)
        y1 <- math.max(0, y - 1) to math.min(width-1, y + 1) if  x1 != x || y1 != y }
    yield  if (isAlive(x1, y1)) 1 else 0 ).sum

  def print = {
    println()
    for (line <- positions) {
      println(line.map(if (_) "* " else ". ").foldLeft("")(_ + _))
    }
    println()
  }
}
