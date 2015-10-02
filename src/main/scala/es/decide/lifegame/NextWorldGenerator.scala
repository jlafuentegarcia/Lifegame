package es.decide.lifegame

/**
 * Created by jlafuente on 02/10/2015.
 */
object NextWorldGenerator {
  def generate(world : World) : World = {
    var alive : List[(Int, Int)] = Nil;

    for ( x <- 0 until world.width;
          y <- 0 until world.height ) if ( CellProcessor.process(world, x, y) ) alive = (x, y) :: alive

    new World(world.height, world.width, alive)
  }
}
