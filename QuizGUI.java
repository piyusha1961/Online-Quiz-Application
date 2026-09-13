import java.awt.*;
import java.awt.event.*;
import javax.swing.*;

// Question Class
class Question {
    String question, opt1, opt2, opt3, opt4;
    int correct;

    public Question(String q, String o1, String o2, String o3, String o4, int correct) {
        this.question = q;
        this.opt1 = o1;
        this.opt2 = o2;
        this.opt3 = o3;
        this.opt4 = o4;
        this.correct = correct;
    }
}

public class QuizGUI extends JFrame implements ActionListener {

    Question[] questions;
    int current = 0, score = 0, correctAns = 0, wrongAns = 0;
    int timeLeft = 10;

    String username = "";

    JLabel questionLabel, timerLabel, userLabel;
    JRadioButton opt1, opt2, opt3, opt4;
    ButtonGroup group;
    JButton nextBtn, startBtn, restartBtn;
    JTextField nameField;

    Timer timer;
    JProgressBar progressBar;

    CardLayout cardLayout;
    JPanel mainPanel, startPanel, quizPanel, resultPanel;

    JLabel resultLabel;

    // Constructor
    public QuizGUI() {
        setTitle("Online Quiz Application");
        setSize(600, 400);

        cardLayout = new CardLayout();
        mainPanel = new JPanel(cardLayout);
        	// Three screens :
        createStartPanel();
        createQuizPanel();
        createResultPanel();

        mainPanel.add(startPanel, "Start");
        mainPanel.add(quizPanel, "Quiz");
        mainPanel.add(resultPanel, "Result");

        add(mainPanel);

        setDefaultCloseOperation(EXIT_ON_CLOSE);
        setVisible(true);
    }

    // START PANEL
    void createStartPanel() {
        startPanel = new JPanel();
        startPanel.setLayout(new BoxLayout(startPanel, BoxLayout.Y_AXIS));

        JLabel welcome = new JLabel("Enter Your Name");
        welcome.setAlignmentX(Component.CENTER_ALIGNMENT);

        nameField = new JTextField(15);
        nameField.setMaximumSize(new Dimension(200, 30));
        nameField.setAlignmentX(Component.CENTER_ALIGNMENT);

        startBtn = new JButton("Start Quiz");
        startBtn.setAlignmentX(Component.CENTER_ALIGNMENT);

        startBtn.addActionListener(e -> {
            username = nameField.getText().trim();

            if (username.isEmpty()) {
                JOptionPane.showMessageDialog(this, "Enter name first!");
                nameField.requestFocusInWindow(); // focus again
                return;
            }

            cardLayout.show(mainPanel, "Quiz");
            loadQuestion();
            startTimer();
        });

        startPanel.add(Box.createVerticalStrut(40));
        startPanel.add(welcome);
        startPanel.add(Box.createVerticalStrut(10));
        startPanel.add(nameField);
        startPanel.add(Box.createVerticalStrut(20));
        startPanel.add(startBtn);

     
        SwingUtilities.invokeLater(() -> nameField.requestFocusInWindow());
    }

    // QUIZ PANEL
    void createQuizPanel() {
        quizPanel = new JPanel(new BorderLayout());

        userLabel = new JLabel();
        questionLabel = new JLabel();
        timerLabel = new JLabel("Time: 10");

        progressBar = new JProgressBar(0, 100);
        progressBar.setValue(0);

        JPanel topPanel = new JPanel(new GridLayout(4,1));
        topPanel.add(userLabel);
        topPanel.add(questionLabel);
        topPanel.add(timerLabel);
        topPanel.add(progressBar);

        quizPanel.add(topPanel, BorderLayout.NORTH);

        opt1 = new JRadioButton();
        opt2 = new JRadioButton();
        opt3 = new JRadioButton();
        opt4 = new JRadioButton();

        group = new ButtonGroup();
        group.add(opt1);
        group.add(opt2);
        group.add(opt3);
        group.add(opt4);

        JPanel optionsPanel = new JPanel(new GridLayout(4,1));
        optionsPanel.add(opt1);
        optionsPanel.add(opt2);
        optionsPanel.add(opt3);
        optionsPanel.add(opt4);

        quizPanel.add(optionsPanel, BorderLayout.CENTER);

        nextBtn = new JButton("Next");
        nextBtn.addActionListener(this);

        quizPanel.add(nextBtn, BorderLayout.SOUTH);

        //  10 QUESTIONS
        questions = new Question[] {
            new Question("What is Java?", "Language", "OS", "Browser", "Database", 1),
            new Question("Which keyword is used for inheritance?", "this", "super", "extends", "implements", 3),
            new Question("Which is not primitive?", "int", "float", "String", "char", 3),
            new Question("Which is OOP concept?", "Loop", "Class", "Array", "Pointer", 2),
            new Question("Which company developed Java?", "Apple", "Microsoft", "Sun Microsystems", "Google", 3),

            new Question("Which method is the entry point in Java?", "start()", "main()", "run()", "init()", 2),
            new Question("Which keyword is used to create object?", "class", "new", "this", "object", 2),
            new Question("Which package contains Scanner class?", "java.io", "java.util", "java.lang", "java.awt", 2),
            new Question("Which loop is guaranteed to run at least once?", "for", "while", "do-while", "foreach", 3),
            new Question("Which symbol is used for single line comment?", "//", "/* */", "#", "<!-- -->", 1)
        };
    }

    // RESULT PANEL
    void createResultPanel() {
        resultPanel = new JPanel(new BorderLayout());

        resultLabel = new JLabel("", JLabel.CENTER);

        restartBtn = new JButton("Restart");
        restartBtn.addActionListener(e -> restartQuiz());

        resultPanel.add(resultLabel, BorderLayout.CENTER);
        resultPanel.add(restartBtn, BorderLayout.SOUTH);
    }

    // LOAD QUESTION
    void loadQuestion() {
        if (current < questions.length) {
            Question q = questions[current];

            userLabel.setText("Player: " + username);
            questionLabel.setText("Q" + (current+1) + ": " + q.question);

            opt1.setText(q.opt1);
            opt2.setText(q.opt2);
            opt3.setText(q.opt3);
            opt4.setText(q.opt4);

            group.clearSelection();
            timeLeft = 10;

            int progress = (int)(((double)current / questions.length) * 100);
            progressBar.setValue(progress);
        }
    }

    // TIMER
    void startTimer() {
        timer = new Timer(1000, e -> {
            timeLeft--;
            timerLabel.setText("Time: " + timeLeft);

            if (timeLeft <= 0) {
                nextQuestion();
            }
        });
        timer.start();
    }

    // GET SELECTED OPTION
    int getSelectedOption() {
        if (opt1.isSelected()) return 1;
        if (opt2.isSelected()) return 2;
        if (opt3.isSelected()) return 3;
        if (opt4.isSelected()) return 4;
        return 0;
    }

    // NEXT QUESTION
    void nextQuestion() {
        timer.stop();

        int selected = getSelectedOption();
        Question q = questions[current];

        if (selected == q.correct) {
            score += 2;
            correctAns++;
            JOptionPane.showMessageDialog(this, "Correct!");
        } else {
            score -= 1;
            wrongAns++;

            String correctText = "";
            switch(q.correct) {
                case 1: correctText = q.opt1; break;
                case 2: correctText = q.opt2; break;
                case 3: correctText = q.opt3; break;
                case 4: correctText = q.opt4; break;
            }

            JOptionPane.showMessageDialog(this,
                    "Wrong!\nCorrect Answer: " + correctText);
        }

        current++;

        if (current < questions.length) {
            loadQuestion();
            startTimer();
        } else {
            showResult();
        }
    }

    // SHOW RESULT
    void showResult() {
        progressBar.setValue(100);

        resultLabel.setText("<html>Player: " + username +
                "<br>Final Score: " + score +
                "<br>Correct: " + correctAns +
                "<br>Wrong: " + wrongAns + "</html>");

        cardLayout.show(mainPanel, "Result");
    }

    // RESTART
    void restartQuiz() {
        current = 0;
        score = 0;
        correctAns = 0;
        wrongAns = 0;
        username = "";
        nameField.setText("");

        cardLayout.show(mainPanel, "Start");
    }

    public void actionPerformed(ActionEvent e) {
        nextQuestion();
    }

    public static void main(String[] args) {
        new QuizGUI();
    }
}