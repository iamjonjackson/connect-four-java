Prompt given to Mistral Vibe:

```
can you analyse this repo and give me a short summary of how it works, particulay the game play and how "smart" the AI player is and how it relates to the AI topics of Minimax, alpha-beta pruning
```

## Connect Four Java - Analysis Summary

### **Gameplay Flow**
Standard Connect Four (6×7 grid) with alternating turns:
- **Human** (red) moves first via mouse click
- **AI** (blue) responds automatically
- After each move: win/draw check → prompt to replay
- UI uses Swing (`JPanel`, `GridLayout`)

---

### **AI Implementation: Minimax with Alpha-Beta Pruning**

**Algorithm Core:**
- **Depth:** 4-ply lookahead (searches 4 moves ahead: 2 human + 2 AI)
- **Alpha-Beta:** Implemented correctly with pruning in both `maxValue()` and `minValue()`
  - Alpha starts at -50000, beta at +50000
  - Branches pruned when `beta ≤ alpha`

**Utility/Heuristic Function:**
- **Terminal states:** +10000 (AI win), -10000 (human win), 0 (draw)
- **Positional evaluation:** Scores patterns in all 8 directions (horizontal, vertical, diagonals)
  - 4-in-a-row for AI: **+16**
  - 3-in-a-row for AI: **+4**
  - 2-in-a-row for AI: **+2**
  - 4-in-a-row for opponent: **-8**
  - 3-in-a-row for opponent: **-4**
  - 2-in-a-row for opponent: **-2**

**Search:**
- Generates successor states recursively
- Stops at depth=4 or game over

---

### **How "Smart" is the AI?**

| Capability | Status |
|------------|--------|
| Blocks immediate human win | ✅ Yes |
| Takes winning move | ✅ Yes |
| Prefers building threats | ✅ Yes (via heuristic) |
| Blocks opponent threats | ✅ Yes (penalizes opponent patterns) |
| Deep strategic planning | ❌ Limited (only 4 moves ahead) |

**Assessment:** Competent but **not optimal**. A skilled human can outmaneuver it with deeper strategy. The heuristic is well-designed for Connect Four's pattern-based nature.

---

### **Relation to AI Topics**

| Concept | Implementation |
|---------|----------------|
| **Minimax** | Core algorithm: maximizes AI advantage, assumes human minimizes it |
| **Alpha-Beta Pruning** | Major optimization: eliminates branches that can't affect outcome |
| **Heuristic Evaluation** | Estimates non-terminal positions (critical for incomplete search) |
| **Depth-Limited Search** | Practical constraint: limits to 4 plies for performance |

This is a **textbook implementation** of minimax + alpha-beta for a two-player, perfect-information game.