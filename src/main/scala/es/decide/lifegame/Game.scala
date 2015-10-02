package es.decide.lifegame

/**
 * Created by jlafuente on 02/10/2015.
 */
object Game {
  def run(height : Int, width: Int, alive : List[(Int, Int)]) = {
    var world = new World(height, width, alive)
    while (true) {
      world = NextWorldGenerator.generate(world)

      world.print

      Thread sleep 2000
    }
  }
}
