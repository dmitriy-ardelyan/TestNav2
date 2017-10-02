package ard.questionseditor;
import javax.swing.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.ArrayList;

public class QEController implements ActionListener {

    //Fields.
    QuestionsEditor qEdtior;
    ArrayList<Question> questions;
    String selectedTopic;
    Question newQuestion;
    Boolean isNewQuestionModeEnabled;
    Boolean isEditModeEnabled;
    Question previousQuestion;

    //Getters and Setters.
    public Boolean getEditModeEnabled() {
        return isEditModeEnabled;
    }

    public void setEditModeEnabled(Boolean editModeEnabled) {
        this.isEditModeEnabled = editModeEnabled;
    }

    public Boolean getNewQuestionModeEnabled() {
        return isNewQuestionModeEnabled;
    }

    public void setNewQuestionModeEnabled(Boolean newQuestionModeEnabled) {
        this.isNewQuestionModeEnabled = newQuestionModeEnabled;
    }

    //Constructor.
    QEController(QuestionsEditor editor){
        this.qEdtior = editor;
        this.isEditModeEnabled = false;
        this.isNewQuestionModeEnabled = false;
        this.setNewQuestionModeEnabled(false);
        this.setEditModeEnabled(false);
        this.newQuestion = new Question();
        this.previousQuestion = new Question();
        this.setupTopicsComboBox();
        this.setupQuestionsByTopics(this.selectedTopic);
        this.setupQuestionNumberComboBox();
        this.displayQuestionById(0);
        this.qEdtior.topLabel.setText(this.generateTopLabel());
        this.qEdtior.currentQuestion = this.questions.get(0);
        this.qEdtior.topicComboBox.addActionListener(this);
        this.qEdtior.questionNumberComboBox.addActionListener(this);
        this.qEdtior.newTopicButton.addActionListener(this);
        this.qEdtior.newButton.addActionListener(this);
        this.qEdtior.saveButton.addActionListener(this);
        this.qEdtior.deleteButton.addActionListener(this);
        this.qEdtior.editButton.addActionListener(this);
        this.qEdtior.cancelButton.addActionListener(this);
    }

    @Override
    public void actionPerformed(ActionEvent e) {

        //Cancel button handling.
        if (e.getSource() == this.qEdtior.cancelButton){
            if (this.isEditModeEnabled){
                this.isEditModeEnabled = false;
                this.qEdtior.displayQuestion(this.qEdtior.currentQuestion);
                this.qEdtior.topLabel.setText(this.generateTopLabel());
            } else if (this.isNewQuestionModeEnabled){
                this.isNewQuestionModeEnabled = false;
                this.qEdtior.displayQuestion(this.previousQuestion);
                this.qEdtior.topLabel.setText(this.generateTopLabel());
            } else {
                JOptionPane.showMessageDialog(null,
                        "You are not in NEW or EDIT mode!",
                        "Warning",
                        JOptionPane.PLAIN_MESSAGE);
            }
        }

        //Question editing.
        if (e.getSource() == this.qEdtior.editButton){
            this.setEditModeEnabled(true);
            this.qEdtior.topLabel.setText(this.generateTopLabel());
        }

        //Question Deletion.
        if (e.getSource() == this.qEdtior.deleteButton){
            int tmpAmountOfQuestions = this.qEdtior.questionNumberComboBox.getItemCount();

            if (questions.size()==1){
                DataBaseEditor.deleteQuestion(this.qEdtior.currentQuestion);
                ArrayList<String> tmpTopicsList = DataBaseEditor.getTopics();
                this.selectedTopic = tmpTopicsList.get(0);

                this.setupQuestionsByTopics(this.selectedTopic);
                this.setupQuestionNumberComboBox();
                this.clearQuestionNumberComboBox(tmpAmountOfQuestions);
                this.qEdtior.topicComboBox.setSelectedItem(this.selectedTopic);
                this.displayQuestionById(0);

            } else {
                DataBaseEditor.deleteQuestion(this.qEdtior.currentQuestion);
                this.setupQuestionsByTopics(this.selectedTopic);
                this.setupQuestionNumberComboBox();
                this.qEdtior.topicComboBox.setSelectedItem(this.selectedTopic);
                this.displayQuestionById(0);
            }
        }

        //Question selection handling.
        if (e.getSource() == this.qEdtior.questionNumberComboBox){
            this.displayQuestionById(this.qEdtior.questionNumberComboBox.getSelectedIndex());
            this.qEdtior.topLabel.setText(this.generateTopLabel());
        }

        //Topic selection handling.
        if (e.getSource() == this.qEdtior.topicComboBox){

            int tmpAmountOfQuestions = this.qEdtior.questionNumberComboBox.getItemCount();
            JComboBox tmp = (JComboBox) e.getSource();
            this.selectedTopic = (String) tmp.getSelectedItem();

            ArrayList questionsTmpList = DataBaseEditor.getQuestionsByTopic(this.selectedTopic);

            if (questionsTmpList.size()>0) {
                this.setupQuestionsByTopics(this.selectedTopic);
                this.setupQuestionNumberComboBox();
                this.clearQuestionNumberComboBox(tmpAmountOfQuestions);
                this.qEdtior.topicComboBox.setSelectedItem(this.selectedTopic);
                this.displayQuestionById(0);
            } else{
                JOptionPane.showMessageDialog(null,
                        "You need at least 1 question in a topic please add it!",
                        "Warning",
                        JOptionPane.PLAIN_MESSAGE);

                this.qEdtior.questionNumberComboBox.addItem(1);
                this.clearQuestionNumberComboBox(tmpAmountOfQuestions);
                this.qEdtior.displayQuestion(this.newQuestion);
                this.isNewQuestionModeEnabled = true;
            }
            this.qEdtior.topLabel.setText(this.generateTopLabel());
        }

        //Topic Creation.
        if(e.getSource()==this.qEdtior.newTopicButton){
            String newTopic = JOptionPane.showInputDialog("New topic name pls");
            DataBaseEditor.addTopic(newTopic);
            this.updateTopics();
        }

        //NewQuestion creation.
        if (e.getSource()==this.qEdtior.newButton){
            this.previousQuestion = this.qEdtior.currentQuestion;
            this.qEdtior.displayQuestion(this.newQuestion);
            this.setNewQuestionModeEnabled(true);
            this.qEdtior.topLabel.setText(this.generateTopLabel());
        }

        //Save Question action.
        if(e.getSource()== this.qEdtior.saveButton){
            if (!this.qEdtior.isQuestionFormFilled()) {
                JOptionPane.showMessageDialog(null,
                        "All fields are required!",
                        "Warning",
                        JOptionPane.PLAIN_MESSAGE);
            } else {
                if(this.isNewQuestionModeEnabled) {
                    //Pulling values from a view.
                    this.newQuestion.setTopic(this.selectedTopic);
                    this.newQuestion.setQuestion(this.qEdtior.questionDescriptionArea.getText());
                    this.newQuestion.setAnswer(this.qEdtior.answerArea.getText());
                    this.newQuestion.setInvAns1(this.qEdtior.firstInvalidAnswerArea.getText());
                    this.newQuestion.setInvAns2(this.qEdtior.secondInvalidAnswerArea.getText());
                    this.newQuestion.setInvAns3(this.qEdtior.thirdInvalidAnswerArea.getText());
                    this.newQuestion.setAnsInfo(this.qEdtior.answerInfoArea.getText());

                    //Adding question to DB and updating GUI.
                    DataBaseEditor.addQuestion(newQuestion);
                    this.updateQuestionNumberComboBox();
                    this.qEdtior.questionNumberComboBox.setSelectedIndex(this.qEdtior.questionNumberComboBox.getItemCount() - 1);
                    this.setupQuestionsByTopics(this.selectedTopic);
                    this.displayQuestionById(this.qEdtior.questionNumberComboBox.getSelectedIndex());
                    JOptionPane.showMessageDialog(null,
                            "Question added!",
                            "Success",
                            JOptionPane.PLAIN_MESSAGE);

                    //Return newQuestion switchers to default "Pending" values.
                    this.newQuestion.setQuestion("");
                    this.newQuestion.setAnswer("");
                    this.newQuestion.setInvAns1("");
                    this.newQuestion.setInvAns2("");
                    this.newQuestion.setInvAns3("");
                    this.newQuestion.setAnsInfo("");
                    this.setNewQuestionModeEnabled(false);
                } else if(this.isEditModeEnabled){
                    Question tmp = new Question();
                    tmp.setDbId(this.qEdtior.currentQuestion.getDbId());
                    tmp.setTopic(this.qEdtior.currentQuestion.getTopic());
                    tmp.setQuestion(this.qEdtior.questionDescriptionArea.getText());
                    tmp.setAnswer(this.qEdtior.answerArea.getText());
                    tmp.setInvAns1(this.qEdtior.firstInvalidAnswerArea.getText());
                    tmp.setInvAns2(this.qEdtior.secondInvalidAnswerArea.getText());
                    tmp.setInvAns3(this.qEdtior.thirdInvalidAnswerArea.getText());
                    tmp.setAnsInfo(this.qEdtior.answerInfoArea.getText());
                    DataBaseEditor.updateQuestion(tmp);
                    this.setupQuestionsByTopics(this.selectedTopic);
                    this.displayQuestionById(this.qEdtior.questionNumberComboBox.getSelectedIndex());
                    JOptionPane.showMessageDialog(null,
                            "Question Updated!",
                            "Success",
                            JOptionPane.PLAIN_MESSAGE);

                    this.setEditModeEnabled(false);
                } else {
                    JOptionPane.showMessageDialog(null,
                            "Select NEW or EDIT mode using corresponding buttons!",
                            "Warning",
                            JOptionPane.PLAIN_MESSAGE);
                }
            }
            this.qEdtior.topLabel.setText(this.generateTopLabel());
        }
    }

    //Setup methods.
    public void setupQuestionNumberComboBox() {
        for (int i = 0 ; i<this.questions.size();i++){
            this.qEdtior.questionNumberComboBox.addItem(i+1);
        }
        this.qEdtior.topicComboBox.setSelectedIndex(0);
    }

    public void setupTopicsComboBox(){
        String[] topics = DataBaseEditor.getTopicsStrings();
        for (String str:topics){
            this.qEdtior.topicComboBox.addItem(str);
        }
        this.selectedTopic = (String) this.qEdtior.topicComboBox.getSelectedItem();
    }

    public void setupQuestionsByTopics(String topic){
        this.questions = DataBaseEditor.getQuestionsByTopic(topic);
    }

    public String generateTopLabel(){

        String mode = "Ordinary";
        if (this.isEditModeEnabled) mode = "Edit";
        if (this.isNewQuestionModeEnabled) mode = "New";
        String topLabelString = String.format("Topic : %s.      Question : %d / %d.      Mode : %s.",
                this.selectedTopic,
                this.qEdtior.questionNumberComboBox.getSelectedItem(),
                this.qEdtior.questionNumberComboBox.getItemCount(),
                mode);
        return topLabelString;
    }

    //Additional methods.
    public void displayQuestionById(int id){
        if (this.questions.size()>id){
            this.qEdtior.displayQuestion(this.questions.get(id));
        }
    }

    public void clearQuestionNumberComboBox(int n2Delete){
            for (int i = 0; i<n2Delete;i++){
            this.qEdtior.questionNumberComboBox.removeItemAt(0);
        }
    }

    public void clearTopicsComboBox(int amountOfTopics2Delete) {
        for (int i = 0; i < amountOfTopics2Delete; i++) {
            this.qEdtior.topicComboBox.removeItemAt(0);
        }
    }

    //Update methods.
    public void updateTopics(){
        String[] topics = DataBaseEditor.getTopicsStrings();
        this.qEdtior.topicComboBox.addItem(topics[topics.length-1]);
    }

    public void updateQuestionNumberComboBox() {
        ArrayList questionsTmpList = DataBaseEditor.getQuestionsByTopic(this.selectedTopic);
        if (questionsTmpList.size()>1) {
            this.qEdtior.questionNumberComboBox.addItem(this.qEdtior.questionNumberComboBox.getItemCount() + 1);
        }
    }
}
