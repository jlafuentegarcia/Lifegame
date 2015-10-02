package es.decide.lifegame

/**
 * Created by jlafuente on 02/10/2015.
 */
object CellProcessor {
  def process(world : World, x : Int, y : Int ) : Boolean = world.isAlive(x, y) match {
    case true => processAlive(world, world.getNbAliveNeighbours(x, y))
    case false => processDead(world, world.getNbAliveNeighbours(x, y))
  }

  private def processAlive(world : World, nbAliveNeighbours : Int ) = nbAliveNeighbours >= 2 && nbAliveNeighbours <= 3

  private def processDead(world : World, nbAliveNeighbours : Int ) = nbAliveNeighbours == 3
}
