package game

// Class representing the game grid and player movements
class Grid(val layout: Array[Array[Option[Item]]]) {
  // Dimensions of the grid
  private val rows: Int = layout.length
  private val cols: Int = layout(0).length

  // Check if the provided position is within grid bounds
  def isValidPosition(pos: (Int, Int)): Boolean =
    pos._1 >= 0 && pos._1 < rows && pos._2 >= 0 && pos._2 < cols

  // Get the event that occurs at the given position
  def getEvent(pos: (Int, Int)): Event = {
    if (!isValidPosition(pos)) {
      InvalidSpace // position is invalid
    } else {
      layout(pos._1)(pos._2) match {
        case Some(item) => TreasureFound(item) // Return found item event
        case None if pos == (rows - 1, cols - 1) => EndGame // Check if it's the end position
        case _ => EmptySpace // Default case for an empty space
      }
    }
  }
}
