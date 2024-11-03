package game

// Represents the player in the game with their position on the grid and collected items
case class Player(var position: (Int, Int), var items: List[Item] = List(), var hiddenObjects: List[HiddenObject] = List()) {

  // Method to reset player position if needed
  def resetPosition(): Unit = {
    position = (0, 0)
    items = List() // Clear collected items
  }

  def collectHiddenObject(hiddenObject: HiddenObject): Unit = {
    hiddenObjects = hiddenObject :: hiddenObjects
  }

  // Method to collect an item
  def collectItem(item: Item): Unit = {
    items = item :: items
  }

  def hasHiddenObject(name: String): Boolean = {
    hiddenObjects.exists(_.name == name)
  }

  // Method to check if the player has collected enough items to win
  def hasWon(requiredItems: Int): Boolean = {
    items.size == requiredItems
  }
}