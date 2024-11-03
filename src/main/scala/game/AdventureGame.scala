package game

import scala.io.StdIn.readLine

// Main object for running the adventure game
object AdventureGame {
  // Initialize the game grid with items
  val items: Array[Array[Option[Item]]] = Array(
    Array(None, Some(Item("Gold Coin", "A shiny gold coin.")), None),
    Array(None, None, Some(Item("Ancient Scroll", "A scroll with mystical writings."))),
    Array[Option[Item]](None, None, None)
  )
  val requiredItems = items.flatten.flatten.length

  // Hidden objects in the game
  val hiddenObjects: List[HiddenObject] = List(
    HiddenObject("Hidden Gem", "A rare gem hidden in a shadowy corner.", "Look for the darkest corner.", (2, 0)),
    HiddenObject("Secret Key", "A key to unlock a mysterious path.", "Seek near the ancient scroll.", (1, 2))
  )

  // Create a grid with the items
  val grid: Grid = new Grid(items, hiddenObjects)
  // Initialize the player at the starting position
  var player: Player = Player((0, 0))

  // Print the current state of the grid
  def printGrid(): Unit = {
    for (row <- grid.layout.indices) {
      for (col <- grid.layout(row).indices) {
        // Display player's position
        if ((row, col) == (player.position._1, player.position._2)) {
          print("[P] ") // P for Player
        } else {
          print("[ ] ") // Display space
        }
      }
      println() // New line after each row
    }
  }

  // Method to move the player based on input direction
  def move(direction: String): Unit = {
    val (x, y) = player.position // Current player position
    direction.toLowerCase match {
      case "up" if x > 0 => player.position = (x - 1, y) // Move up
      case "down" if x < grid.layout.length - 1 => player.position = (x + 1, y) // Move down
      case "left" if y > 0 => player.position = (x, y - 1) // Move left
      case "right" if y < grid.layout(x).length - 1 => player.position = (x, y + 1) // Move right
      case _ => println("You can't move in that direction!") // Invalid move message
    }

    // Check for events at the new position and display the corresponding message
    val event = grid.getEvent(player.position)
    println(event.message)
    event match {
      case TreasureFound(item) => player.collectItem(item)
      case HiddenObjectFound(hiddenObject) =>
        println(s"Hint: ${hiddenObject.clue}")
        player.collectHiddenObject(hiddenObject)
      case EndGame =>
        if player.hasWon(requiredItems) then println("Congratulation! YOU WON")
        else println("YOU LOST :( Try again!")
        System.exit(0)
      case _ => None
    }
    grid.revealSecretPaths(player)
  }

  // Main entry point for the game
  def main(args: Array[String]): Unit = {
    println("Welcome to the Grid Adventure Game!") // Game introduction
    var continuePlaying: Boolean = true // Game loop control

    // Game loop
    while (continuePlaying) {
      printGrid() // Display current grid state
      println("Enter your move (up, down, left, right) or 'quit' to exit:") // User prompt
      val input: String = readLine() // Read user input

      input match {
        case "quit" =>
          continuePlaying = false // Exit the game loop
          println("Thanks for playing!") // Exit message
        case direction =>
          move(direction) // Move the player
      }
    }
  }
}
