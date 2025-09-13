# Rock–Paper–Scissors Game (Java)

## 📌 Problem Statement
Design and implement a **Rock–Paper–Scissors** game using Java.  

### ✅ Functional Requirements
- The system shall allow a human player to enter a move (`rock`, `paper`, `scissors`).
- The system shall generate a random move for the computer.
- The system shall determine the winner of each round.
- The system shall keep track of scores (wins, losses, draws).
- The system shall handle invalid inputs and prompt the user again.

### ⚙️ Non-Functional Requirements
- **Usability**: Simple CLI interface with clear prompts.  
- **Reliability**: Handles invalid inputs gracefully without crashing.  
- **Extensibility**: Easy to add new moves or new player types.  
- **Maintainability**: Clean modular code with OOP principles and unit tests.  
- **Quality**: Must pass **Google Java Style checks**.  
- **Testing**: Ensure **minimum 80% code coverage** via unit tests.

### 📌 Assumptions
- Only **two-players** are supported.  
- Inputs are **case-insensitive** and support **shorthand notations** (e.g., r, rock, or Rock are all valid).  
- The game runs in a **console/terminal environment**.  

---

## 🛠️ Tech Stack
- Java 21 LTS
- Maven (build & dependency management)
- JUnit 5 (testing)

---

## 🚀 Extensibility
- Add new moves by updating the `Move` enum (e.g., Fire, Water).
- Extend `Player` interface for additional player types (e.g., RemotePlayer, AIPlayer, 2 Human players).
- Replace CLI with GUI or REST API without changing core logic.

---

## ⚡ How to Run

### Using Maven
```bash
# Clean, compile, test, verify quality checks (including style & coverage)
mvn clean verify

# Run the game
mvn exec:java
```

### Using Java Directly
```bash
# Compile
javac -d target/classes $(find src/main/java -name "*.java")

# Run
java -cp target/classes com.lld.rps.Application
```

### Testing & Quality
- **Unit Tests**: Implemented using JUnit 5  
- **Code Coverage**: Enforced minimum **80%**  
- **Code Style**: Verified using **Google Java Style**  

---

## 📂 Project Structure
```
src/main/java/com/lld/rps
├── Application.java
├── core/
│   ├── GameOrchestrator.java
│   ├── Move.java
│   ├── Scoreboard.java
│   └── TwoPlayerResult.java
├── player/
│   ├── Player.java
│   ├── HumanPlayer.java
│   └── ComputerPlayer.java
└── util/
    ├── InputHelper.java
    └── PrettyPrintUtil.java

src/test/java/com/lld/rps
├── core/...
├── player/...
└── util/...
```

---

## 🎮 Example Gameplay
```
=== Paper-Rock-Scissors ===
How many rounds do you want to play? 3
Enter name: Dakshin

Round 1 of 3
Dakshin enter your move (rock, paper, scissors): rock
Dakshin: ROCK | Computer: ROCK -> It's a tie!!

Round 2 of 3
Dakshin enter your move (rock, paper, scissors): s
Dakshin: SCISSORS | Computer: SCISSORS -> It's a tie!!

Round 3 of 3
Dakshin enter your move (rock, paper, scissors): paper
Dakshin: PAPER | Computer: ROCK -> 'Dakshin' wins

=== Final Score ===
Dakshin: 1, Computer: 0, Ties: 2
WINNER: Dakshin

Ending game. Thanks for playing!
```
