package trash;

import javax.swing.*;
import javax.swing.border.EmptyBorder;
import java.awt.*;

public class TestNav {
    JLabel questionLabel;
    JLabel answerDetailsLabel;
    JButton answer1Button;
    JButton answer2Button;
    JButton answer3Button;
    JButton answer4Button;
    JButton nextButton;
    JPanel windowContentPanel;

    TestNav(){

        windowContentPanel = new JPanel();

        questionLabel = new JLabel("<html>Question goes here</html>");
        questionLabel.setHorizontalAlignment(SwingConstants.CENTER);
        answerDetailsLabel = new JLabel("Answer details");
        answerDetailsLabel.setHorizontalAlignment(SwingConstants.LEFT);
        answerDetailsLabel.setVerticalAlignment(SwingConstants.TOP);
        answerDetailsLabel.setBorder(new EmptyBorder(5,5,5,5));

        GridLayout gl = new GridLayout(7,1);
        windowContentPanel.setLayout(gl);

        answer1Button = new JButton("Answer 1");
        answer2Button = new JButton("Answer 2");
        answer3Button = new JButton("Answer 3");
        answer4Button = new JButton("Answer 4");
        nextButton = new JButton("Next");

        windowContentPanel.add(questionLabel);
        windowContentPanel.add(answer1Button);
        windowContentPanel.add(answer2Button);
        windowContentPanel.add(answer3Button);
        windowContentPanel.add(answer4Button);
        windowContentPanel.add(answerDetailsLabel);
        windowContentPanel.add(nextButton);

        JFrame frame = new JFrame("Test navigator");
        frame.setContentPane(windowContentPanel);
        frame.setSize(800,700);
        frame.setVisible(true);

        TestNavEngine testNavEngine = new TestNavEngine(this);

        answer1Button.addActionListener(testNavEngine);
        answer2Button.addActionListener(testNavEngine);
        answer3Button.addActionListener(testNavEngine);
        answer4Button.addActionListener(testNavEngine);
        nextButton.addActionListener(testNavEngine);
        //JOptionPane.showMessageDialog(null,"opa");
    }

    public static void main(String[] args){

        TestNav testNav = new TestNav();
    }
}
