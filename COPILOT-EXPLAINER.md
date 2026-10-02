Prompt given to GitHub Copilot (responded using GPT-6 Luna):

```
can you analyse this repo and give me a short summary of how it works, particulay the game play and how "smart" the AI player is and how it relates to the AI topics of Minimax, alpha-beta pruning
```

The game is a Swing Connect Four app. The human clicks any cell in a column; the piece drops to the lowest open row. The board updates the game state, checks for a win, then asks the computer for a move. A game ends on four in a row or a full board. See Board.java, MoveController.java, and State.java.

The AI in MinMaxPlayer.java uses minimax: it searches up to four moves ahead, assuming both sides choose moves optimally. Its score favors the human (player 1), so the AI (player 2) minimizes that score. Wins and losses get large scores; non-terminal positions get a simpler score based on nearby pieces and potential lines. This makes it a reasonable basic opponent, but not an especially strong one: the search is shallow, and the hand-built heuristic looks mainly around the most recent move.

It also uses alpha-beta pruning, which skips exploring branches that cannot change the current best choice. One implementation detail limits the benefit: it builds the full search tree before pruning, so pruning saves evaluation work but not tree construction or memory. Overall: a straightforward, fixed-depth minimax AI with alpha-beta pruning, rather than a fully optimized or expert-strength Connect Four engine.

