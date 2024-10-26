
// Text-based Adventure Game

package game

// Represents an item in the game world
case class Item(name: String, description: String) {
  // Provide a string representation of the item for easy debugging
  override def toString: String = s"$name: $description"
}

