package es.decide.lifetime

import es.decide.lifegame.{NextWorldGenerator, World}
import org.scalatest.{Matchers, FlatSpec}

/**
 * Created by jlafuente on 02/10/2015.
 */
class NextWorldGeneratorTest extends FlatSpec with Matchers {
  "A dead world" should "evolve into another dead world" in {
    val alive : List[(Int, Int)] = Nil
    val world = new World(10, 10, alive)
    val nextWorld = NextWorldGenerator.generate(world)

    nextWorld.numAlive should equal(0)
  }

  it should "evolve into another empty and dead world" in {
    val alive : List[(Int, Int)] = Nil
    val world = new World(0, 0, alive)
    val nextWorld = NextWorldGenerator.generate(world)

    nextWorld.numAlive should equal(0)
    nextWorld.width should equal(0)
    nextWorld.height should equal(0)
  }

  "A world with 1 alive" should "evolve into an empty world" in {
    val alive : List[(Int, Int)] = (5, 5) :: Nil
    val world = new World(10, 10, alive)
    val nextWorld = NextWorldGenerator.generate(world)

    nextWorld.numAlive should equal(0)
    nextWorld.width should equal(10)
    nextWorld.height should equal(10)
  }

  "A world with 2 alive" should "evolve into an empty world" in {
    val alive : List[(Int, Int)] = (5, 4):: (4, 5) :: Nil
    val world = new World(10, 10, alive)
    val nextWorld = NextWorldGenerator.generate(world)

    nextWorld.numAlive should equal(0)
    nextWorld.isAlive(5, 5) should equal (false)
    nextWorld.width should equal(10)
    nextWorld.height should equal(10)
  }

  "A world with 3 alive around 1 dead" should "revive the dead one" in {
    val alive : List[(Int, Int)] = (6, 4):: (4, 5) :: (5, 4) :: Nil
    val world = new World(10, 10, alive)

    world.print

    val nextWorld = NextWorldGenerator.generate(world)

    nextWorld.print

    nextWorld.numAlive should equal(2)
    nextWorld.isAlive(5, 5) should equal(true)
    nextWorld.isAlive(5, 4) should equal(true)
    nextWorld.isAlive(6, 4) should equal(false)
    nextWorld.isAlive(4, 5) should equal(false)
    nextWorld.width should equal(10)
    nextWorld.height should equal(10)
  }

  "A live cell with just 1 live around" should "die" in {
    val alive : List[(Int, Int)] = (6, 4):: (5, 5) :: Nil
    val world = new World(10, 10, alive)
    val nextWorld = NextWorldGenerator.generate(world)

    nextWorld.numAlive should equal(0)
    nextWorld.isAlive(6, 4) should equal(false)
    nextWorld.isAlive(5, 5) should equal(false)
    nextWorld.width should equal(10)
    nextWorld.height should equal(10)
  }
}
