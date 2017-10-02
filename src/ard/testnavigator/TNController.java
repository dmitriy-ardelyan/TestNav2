package ard.testnavigator;

import ard.questionseditor.DataBaseEditor;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class TNController implements ActionListener{
    TestNavigator tnView;
    private static int currentQuestionIndex;
    private static int selectedButtonIndex;
    private int amountOfCorrectAnswers, amountOfWrongAnswers;
    Boolean isAnswerConfirmed;
    TestModel currentTest;
    Boolean isTestInProgress;
    String selectedAnswer;
    String headerText;

    //Constructor.
    TNController(TestNavigator tnView){
        this.headerText = "Select options and click Start Test";
        this.isAnswerConfirmed = false;
        this.isTestInProgress = false;
        this.tnView = tnView;
        this.tnView.nextQuestionButton.setText("Next Question");
        this.tnView.displayHeader(this.headerText);
        this.setDefaultColorForAllButtons();
        this.setupTopicsComboBox();
        this.setupQuestionAMountComboBox();
        this.currentQuestionIndex = 0;
        this.selectedButtonIndex = 0;
        this.amountOfCorrectAnswers = 0;
        this.amountOfWrongAnswers = 0;
        //this.currentTest = new TestModel();
        this.tnView.startTestButton.addActionListener(this);
        this.tnView.answer1Button.addActionListener(this);
        this.tnView.answer2Button.addActionListener(this);
        this.tnView.answer3Button.addActionListener(this);
        this.tnView.answer4Button.addActionListener(this);
        this.tnView.confirmAnswerButton.addActionListener(this);
        this.tnView.nextQuestionButton.addActionListener(this);
    }

    @Override
    public void actionPerformed(ActionEvent e) {
        Color selectedOptionColor = new Color(100,200,255);
        Color correctAnswerColor = new Color(152,245, 177);
        Color wrongAnswerColor = new Color(255, 159, 130);

        //Next Question.
        if(e.getSource() == this.tnView.nextQuestionButton){
            this.currentQuestionIndex++;
            if (this.currentQuestionIndex<this.currentTest.questions.size()&&this.isTestInProgress) {
                this.setDefaultColorForAllButtons();
                this.isAnswerConfirmed = false;
                this.tnView.displayQuestion(this.currentTest.questions.get(this.currentQuestionIndex));
                this.tnView.displayHeader(this.generateHeaderString());
            } else{
                String strToDisplay = String.format("Test finished. Topic: %s. Wrong: %d / Correct: %d",
                        this.currentTest.topic,
                        this.amountOfWrongAnswers,
                        this.amountOfCorrectAnswers);
                JOptionPane.showMessageDialog(null,
                        strToDisplay,
                        "Notification",
                        JOptionPane.PLAIN_MESSAGE);
                this.isTestInProgress = false;
                this.isAnswerConfirmed = false;
                this.setDefaultColorForAllButtons();
                this.currentQuestionIndex = 0;
                this.selectedButtonIndex = 0;
                this.amountOfCorrectAnswers = 0;
                this.amountOfWrongAnswers = 0;
                this.headerText = "Select options and click Start Test";
                this.tnView.displayHeader(this.headerText);
                this.tnView.questionLabel.setText("Select options and click Start Test");
                this.tnView.questionInfoLabel.setText("INFO:");
                this.tnView.answer1Button.setText("Answer 1");
                this.tnView.answer2Button.setText("Answer 2");
                this.tnView.answer3Button.setText("Answer 3");
                this.tnView.answer4Button.setText("Answer 4");
            }
        }

        //Answer confirmation.
        if (e.getSource() == this.tnView.confirmAnswerButton){
            Boolean isReplyCorrect = true;
            if (this.isTestInProgress) {
                if (!this.isAnswerConfirmed) {
                    if(this.selectedAnswer==null){
                        JOptionPane.showMessageDialog(null,
                                "Please select an answer first.",
                                "Warning",
                                JOptionPane.PLAIN_MESSAGE);
                    }
                    else {
                        if (this.selectedAnswer.equals(this.currentTest.questions.get(this.currentQuestionIndex).getAnswer())) {
                            this.amountOfCorrectAnswers++;
                            isReplyCorrect = true;
                            switch (this.selectedButtonIndex) {
                                case 1: {
                                    this.tnView.answer1Button.setBackground(correctAnswerColor);
                                }
                                break;
                                case 2: {
                                    this.tnView.answer2Button.setBackground(correctAnswerColor);
                                }
                                break;
                                case 3: {
                                    this.tnView.answer3Button.setBackground(correctAnswerColor);
                                }
                                break;
                                case 4: {
                                    this.tnView.answer4Button.setBackground(correctAnswerColor);
                                }
                                break;
                            }
                            this.tnView.displayHeader(this.generateHeaderString());
                        } else {
                            isReplyCorrect = false;
                            this.amountOfWrongAnswers++;
                            switch (this.selectedButtonIndex) {
                                case 1: {
                                    this.tnView.answer1Button.setBackground(wrongAnswerColor);
                                }
                                break;
                                case 2: {
                                    this.tnView.answer2Button.setBackground(wrongAnswerColor);
                                }
                                break;
                                case 3: {
                                    this.tnView.answer3Button.setBackground(wrongAnswerColor);
                                }
                                break;
                                case 4: {
                                    this.tnView.answer4Button.setBackground(wrongAnswerColor);
                                }
                                break;
                            }
                            this.tnView.displayHeader(this.generateHeaderString());
                            if (this.tnView.answer1Button.getText().equals(this.currentTest.questions.get(this.currentQuestionIndex).getAnswer())) {
                                this.tnView.answer1Button.setBackground(correctAnswerColor);
                            }
                            if (this.tnView.answer2Button.getText().equals(this.currentTest.questions.get(this.currentQuestionIndex).getAnswer())) {
                                this.tnView.answer2Button.setBackground(correctAnswerColor);
                            }
                            if (this.tnView.answer3Button.getText().equals(this.currentTest.questions.get(this.currentQuestionIndex).getAnswer())) {
                                this.tnView.answer3Button.setBackground(correctAnswerColor);
                            }
                            if (this.tnView.answer4Button.getText().equals(this.currentTest.questions.get(this.currentQuestionIndex).getAnswer())) {
                                this.tnView.answer4Button.setBackground(correctAnswerColor);
                            }
                        }

                        this.tnView.displayInfo(this.currentTest.questions.get(this.currentQuestionIndex), isReplyCorrect);
                        this.isAnswerConfirmed = true;
                    }
                } else {
                    JOptionPane.showMessageDialog(null,
                            "Answer confirmed. Click Next Question to proceed.",
                            "Warning",
                            JOptionPane.PLAIN_MESSAGE);
                }
            } else{
                JOptionPane.showMessageDialog(null,
                        "Please start a test.",
                        "Warning",
                        JOptionPane.PLAIN_MESSAGE);
            }
        }

        //Answer1 selection.
        if (e.getSource() == this.tnView.answer1Button){
            if (!this.isAnswerConfirmed) {
                if (isTestInProgress) {
                    this.setDefaultColorForAllButtons();
                    this.tnView.answer1Button.setBackground(selectedOptionColor);
                    this.selectedAnswer = this.tnView.answer1Button.getText();
                    this.selectedButtonIndex = 1;
                } else {
                    JOptionPane.showMessageDialog(null,
                            "Start a test first!",
                            "Warning",
                            JOptionPane.PLAIN_MESSAGE);
                }
            }
        }

        //Answer2 selection.
        if (e.getSource() == this.tnView.answer2Button){
            if (!this.isAnswerConfirmed) {
                if (isTestInProgress) {
                    this.setDefaultColorForAllButtons();
                    this.tnView.answer2Button.setBackground(selectedOptionColor);
                    this.selectedAnswer = this.tnView.answer2Button.getText();
                    this.selectedButtonIndex = 2;
                } else {
                    JOptionPane.showMessageDialog(null,
                            "Start a test first!",
                            "Warning",
                            JOptionPane.PLAIN_MESSAGE);
                }
            }
        }

        //Answer3 selection.
        if (e.getSource() == this.tnView.answer3Button){
            if(!this.isAnswerConfirmed) {
                if (isTestInProgress) {
                    this.setDefaultColorForAllButtons();
                    this.tnView.answer3Button.setBackground(selectedOptionColor);
                    this.selectedAnswer = this.tnView.answer3Button.getText();
                    this.selectedButtonIndex = 3;
                } else {
                    JOptionPane.showMessageDialog(null,
                            "Start a test first!",
                            "Warning",
                            JOptionPane.PLAIN_MESSAGE);
                }
            }
        }

        //Answer4 selection.
        if (e.getSource() == this.tnView.answer4Button){
            if (!this.isAnswerConfirmed) {
                if (isTestInProgress) {
                    this.setDefaultColorForAllButtons();
                    this.tnView.answer4Button.setBackground(selectedOptionColor);
                    this.selectedAnswer = this.tnView.answer4Button.getText();
                    this.selectedButtonIndex = 4;
                } else {
                    JOptionPane.showMessageDialog(null,
                            "Start a test first!",
                            "Warning",
                            JOptionPane.PLAIN_MESSAGE);
                }
            }
        }

        //Start Test.
        if (e.getSource() == this.tnView.startTestButton){
            if (isTestInProgress){
                JOptionPane.showMessageDialog(null,"Test in progress!","Warning",JOptionPane.PLAIN_MESSAGE);
            } else {
                this.isTestInProgress = true;
                this.generateTestModel();
                this.tnView.displayQuestion(this.currentTest.questions.get(this.currentQuestionIndex));
            }
            this.tnView.displayHeader(this.generateHeaderString());
        }
    }

    //Setup methods.
    public void setupTopicsComboBox(){
        String[] topics = DataBaseEditor.getTopicsStrings();
        this.tnView.topicComboBox.addItem("All");
        for (String str:topics){
            this.tnView.topicComboBox.addItem(str);
        }
    }

    public void setupQuestionAMountComboBox(){
        this.tnView.questionAmountComboBox.addItem("All");
        this.tnView.questionAmountComboBox.addItem("20");
        this.tnView.questionAmountComboBox.addItem("50");
    }

    private void generateTestModel(){
        int amountOfQuestions;
        String selectedAmountOfQuestions = this.tnView.questionAmountComboBox.getSelectedItem().toString();
        if (selectedAmountOfQuestions.equals("All")) {
            amountOfQuestions = DataBaseEditor.countQuestionsInTopic(this.tnView.topicComboBox.getSelectedItem().toString());
        } else if (selectedAmountOfQuestions.equals("20")){
            amountOfQuestions = 20;
        } else {
            amountOfQuestions = 50;
        }
        this.currentTest = new TestModel(this.tnView.topicComboBox.getSelectedItem().toString(),amountOfQuestions);
    }

    //Additional methods.
    private void setDefaultColorForAllButtons(){
        this.tnView.answer1Button.setBackground(Color.lightGray);
        this.tnView.answer2Button.setBackground(Color.lightGray);
        this.tnView.answer3Button.setBackground(Color.lightGray);
        this.tnView.answer4Button.setBackground(Color.lightGray);
    }

    private String generateHeaderString(){
        String headerStr = String.format("Topic: %s.      Question: %d/%d.      Wrong: %d / Correct: %d",
                this.currentTest.topic,
                this.currentQuestionIndex + 1,
                this.currentTest.amountOfQuestions,
                this.amountOfWrongAnswers,
                this.amountOfCorrectAnswers);
        this.headerText = headerStr;
        return headerStr;
    }
}
