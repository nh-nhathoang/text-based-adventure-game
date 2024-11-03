// Text-based Adventure Game

package game

// Trait representing a game event
sealed trait Event {
  // Each event has a message that describes it
  def message: String
}

// Event for finding a hidden object
case class HiddenObjectFound(hiddenObject: HiddenObject) extends Event {
  override def message: String = s"You discovered a hidden object: ${hiddenObject.name}! ${hiddenObject.description}"
}

// When the player finds a treasure item
case class TreasureFound(item: Item) extends Event {
  override def message: String = s"You found a treasure: ${item.name}! ${item.description}"
}

// The game reaches to the end
case object EndGame extends Event {
  override def message: String = "You've reached the end of the game!"
}

// Play move to invalid space
case object InvalidSpace extends Event {
  override def message: String = "You should move in another direction."
}

// Represents an empty space in the game grid
case object EmptySpace extends Event {
  override def message: String = "You moved to an empty space."
}
