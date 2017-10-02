package ard.testnavigator;

import ard.questionseditor.*;

import javax.swing.*;
import javax.swing.border.Border;
import java.awt.*;
import java.util.Random;

public class TestNavigator {
    private JPanel testNavRootPanel;
    private JPanel northSubPanel;
    private JPanel southSubPanel;
    private JPanel centerSubPanel;
    private JLabel headerLabel;
    private JLabel testSettingsLabel;
    private JPanel testSettingsSubPanel;
    private JPanel testBodyPanel;
    private JLabel topicLabel;
    JComboBox topicComboBox;
    private JLabel questionAmountLabel;
    JComboBox questionAmountComboBox;
    JLabel questionLabel;
    JButton answer2Button;
    JButton answer3Button;
    JButton answer4Button;
    JLabel questionInfoLabel;
    JButton startTestButton;
    JButton confirmAnswerButton;
    JButton nextQuestionButton;
    JButton answer1Button;

    TestNavigator(){
        super();
        TNController tnControllerObject = new TNController(this);
    }

    public static void main(String[] args) {
        TestNavigator navigatorView = new TestNavigator();

        Border border = BorderFactory.createLineBorder(Color.BLUE, 1);
        navigatorView.questionLabel.setBorder(border);
        navigatorView.questionInfoLabel.setBorder(border);
        JFrame frame = new JFrame("Questions Editor");
        frame.setContentPane(navigatorView.testNavRootPanel);
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.pack();
        frame.setVisible(true);

    }

    public void displayQuestion(Question question){
        this.questionLabel.setText(question.getQuestion());
        this.questionInfoLabel.setText("INFO:");
        int[] indexes = this.getRandomIndexes();
        String[] allAnswers = question.getAllAnswers();
        this.answer1Button.setText(allAnswers[indexes[0]]);
        this.answer2Button.setText(allAnswers[indexes[1]]);
        this.answer3Button.setText(allAnswers[indexes[2]]);
        this.answer4Button.setText(allAnswers[indexes[3]]);
    }

    public void displayInfo(Question question, Boolean isAnswerCorrect){
        String strToDisplay = new String();
        if (isAnswerCorrect) {
            strToDisplay = String.format("CORRECT : " + question.getAnsInfo() );
        } else{
            strToDisplay = String.format("WRONG : " + question.getAnsInfo() );
        }
        this.questionInfoLabel.setText(strToDisplay);
    }

    public void displayHeader(String headerString){
        this.headerLabel.setText(headerString);
    }

    //Additional Methods.
    private int[] getRandomIndexes(){
        int[] indexes =  {10,10,10,10};
        Random random = new Random();
        boolean repeatFlag;
        for (int i = 0;i<indexes.length;i++){
            repeatFlag = true;
            while (repeatFlag){
                int num = random.nextInt(4);
                if (num!=indexes[0]&&num!=indexes[1]&&num!=indexes[2]&&num!=indexes[3]) {
                    indexes[i] = num;
                    repeatFlag = false;
                }
            }
        }
        return indexes;
    }
}
