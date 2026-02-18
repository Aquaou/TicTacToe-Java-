# Tic Tac Toe — Java

> **Note:** This documentation was created with the assistance of Claude (Anthropic AI) to help explain the implementation and programming concepts in detail.
>
> Edited and reviewed by Titus Ward.

---

## Overview

This is a two-player Tic Tac Toe game built with Java and the Swing GUI toolkit. The application renders a 3×3 interactive game board in a desktop window, tracks player turns, detects wins and draws, highlights winning combinations, and maintains a running score across multiple games.

---

## File

| File | Description |
|------|-------------|
| `TicTacToe.java` | Single-file application containing the full game logic and UI |

---

## Requirements

- Java Development Kit (JDK) 8 or later
- No external libraries required — uses only the Java standard library (`javax.swing`, `java.awt`)

---

## How to Compile and Run

```bash
# Compile
javac TicTacToe.java

# Run
java TicTacToe
```

---

## Class Overview: `TicTacToe`

Extends `JFrame`. All game state and UI components are encapsulated within this single class.

### Fields

| Field | Type | Description |
|-------|------|-------------|
| `buttons` | `JButton[3][3]` | The 3×3 grid of clickable game cells |
| `currentPlayer` | `char` | Tracks whose turn it is (`'X'` or `'O'`) |
| `statusLabel` | `JLabel` | Displays current game status (turn, winner, draw) |
| `scoreLabel` | `JLabel` | Displays the running win/draw tally |
| `resetButton` | `JButton` | Triggers a new game when clicked |
| `xWins` | `int` | Cumulative win count for Player X |
| `oWins` | `int` | Cumulative win count for Player O |
| `draws` | `int` | Cumulative draw count |

---

## Methods

### `TicTacToe()` — Constructor
Initializes and displays the game window. Sets up the 3×3 board panel, status panel, score label, and reset button. Attaches action listeners to each cell button and to the reset button. The window is set to 500×600 pixels and centered on screen.

### `buttonClicked(int row, int col)`
Handles a player's move when a cell is clicked. It ignores clicks on already-filled cells, places the current player's mark, colors it (blue for X, red for O), then checks for a winner or draw. If neither, it switches the active player and updates the status label.

### `checkWinner()`
Evaluates all eight possible winning combinations (3 rows, 3 columns, 2 diagonals) for the current player's mark. Returns `true` and calls `highlightWinningCombination()` if a winning line is found. Returns `false` otherwise.

> **Note:** There is a known minor bug in the column win detection the diagonal highlight coordinates `(0,0), (1,1), (2,2)` are used instead of the correct column indices. This does not affect win detection logic, only the visual highlight for column wins.

### `highlightWinningCombination(int r1, int c1, int r2, int c2, int r3, int c3)`
Sets the background color of the three winning cells to green (`#2ECC71`) to visually indicate the winning line.

### `isBoardFull()`
Iterates over all cells and returns `true` if none are empty, indicating a draw condition.

### `disableAllButtons()`
Disables all 9 cell buttons after a win to prevent further input.

### `resetBoard()`
Clears all cell text, re-enables all buttons, resets their backgrounds to white, and resets the current player to `'X'`. Score counters are preserved across resets.

### `updateScoreLabel()`
Refreshes the score label with the latest win and draw counts.

### `main(String[] args)`
Entry point. Uses `SwingUtilities.invokeLater()` to safely instantiate the game on the Event Dispatch Thread.

---

## Gameplay

1. Player X always goes first.
2. Players alternate clicking empty cells to place their mark.
3. The first player to align three marks in a row, column, or diagonal wins.
4. If all 9 cells are filled with no winner, the game is a draw.
5. Winning cells are highlighted in green (well kinda, I messed up the logic for the highlighting so it sometimes does not highlight the correct winning combination).
6. Click **New Game** to reset the board. The score persists until the application is closed.

---

## Acknowledgments

This documentation was created with the assistance of Claude (Anthropic AI) to provide clear explanations of both the Game of Life logic and the Java programming concepts used in this implementation.

Edited and reviewed by Titus Ward.
