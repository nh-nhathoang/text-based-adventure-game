package game

import org.scalatest.flatspec.AnyFlatSpec
import org.scalatest.matchers.should.Matchers

class AdventureGameTest extends AnyFlatSpec with Matchers {

  "Grid" should "return valid event for existing item" in {
    val grid = new Grid(Array(Array[Option[Item]](Some(Item("Treasure", "A precious item.")))))
    val event = grid.getEvent((0, 0))
    event should be(TreasureFound(Item("Treasure", "A precious item.")))
  }

  it should "return EndGame when reaching the end position" in {
    val grid = new Grid(Array(Array[Option[Item]](None, None), Array[Option[Item]](None, None)))
    val event = grid.getEvent((1, 1))
    event should be(EndGame)
  }

  it should "return EmptySpace for a non-existing item" in {
    val grid = new Grid(Array(Array[Option[Item]](None)))
    val event = grid.getEvent((0, 0))
    event should be(EmptySpace)
  }
}