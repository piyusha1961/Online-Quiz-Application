# 📝 Online Quiz Application

A simple **Online Quiz Application built using Java Swing**.
The application provides an interactive GUI where users can enter their name, answer multiple-choice questions within a time limit, and view their final score.

## ✨ Features

* 👤 Enter player name before starting the quiz
* 📝 10 multiple-choice questions
* ⏱️ 10-second timer for each question
* 📊 Progress bar showing quiz progress
* ✅ Instant feedback for correct answers
* ❌ Displays the correct answer for wrong answers
* 🧮 Scoring system:

  * **+2 marks** for a correct answer
  * **-1 mark** for an incorrect answer
* 📈 Displays:

  * Final score
  * Number of correct answers
  * Number of wrong answers
* 🔄 Restart option to attempt the quiz again
* 🖥️ User-friendly graphical interface using Java Swing

## 🛠️ Technologies Used

* **Java**
* **Java Swing**
* **AWT**
* **Event Handling**
* **Object-Oriented Programming**
* **Arrays**
* **Timer**

## 📂 Project Structure

```text
Online-Quiz-Application/
│
├── QuizGUI.java
└── README.md
```

## 🧩 Main Classes

### `Question`

The `Question` class stores the data for each quiz question:

* Question text
* Four answer options
* Correct answer

### `QuizGUI`

The main class responsible for:

* Creating the graphical interface
* Displaying questions
* Handling user input
* Managing the timer
* Calculating the score
* Displaying the result
* Restarting the quiz

## 🎮 How It Works

1. Launch the application.
2. Enter your name.
3. Click **Start Quiz**.
4. A question with four options will appear.
5. Select an answer and click **Next**.
6. You have **10 seconds** to answer each question.
7. If the time runs out, the application automatically moves to the next question.
8. After all 10 questions, your final score and performance are displayed.
9. Click **Restart** to attempt the quiz again.

## 📊 Scoring System

| Result               | Score |
| -------------------- | ----: |
| Correct Answer       |    +2 |
| Wrong Answer         |    -1 |
| No Answer / Time Out |    -1 |

The maximum possible score is **20 marks**.

## ▶️ How to Run

### Prerequisites

Make sure Java is installed on your system.

Check your Java installation using:

```bash
java -version
```

### Compile

```bash
javac QuizGUI.java
```

### Run

```bash
java QuizGUI
```

## 🖥️ Application Screens

The application consists of three main screens:

### 1. Start Screen

The user enters their name before starting the quiz.

### 2. Quiz Screen

Displays:

* Player name
* Current question
* Four options
* Countdown timer
* Progress bar
* Next button

### 3. Result Screen

Displays the player's:

* Final score
* Correct answers
* Wrong answers

## 🚀 Future Improvements

Some possible improvements for future versions:

* Add a larger question bank
* Randomize questions and options
* Add different difficulty levels
* Add categories such as Java, Python, DSA, etc.
* Store high scores
* Add a leaderboard
* Add sound effects and animations
* Save quiz results to a file/database
* Improve the UI with custom colors, fonts, and icons
* Add an admin panel to add/edit questions

## 🎯 Learning Outcomes

This project helped demonstrate practical implementation of:

* Java classes and objects
* Constructors
* Arrays of objects
* GUI development using Swing
* `ActionListener` and event-driven programming
* `CardLayout`
* `JRadioButton` and `ButtonGroup`
* `JProgressBar`
* Swing `Timer`
* Basic exception/input handling
* Application state management

## 👩‍💻 Author

**Piyusha Ghadigaonkar**

First-Year Engineering Student
Electronic and Computer Science

---

⭐ If you found this project useful, feel free to star the repository!

