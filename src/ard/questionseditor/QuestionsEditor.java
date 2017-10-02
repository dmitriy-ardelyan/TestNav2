package ard.questionseditor;
import javax.swing.*;
import javax.swing.border.Border;
import java.awt.*;

public class QuestionsEditor {
    JPanel questionsRootPanel;
    private JPanel westLabelsPanel;
    JPanel eastFieldsPanel;
    private JPanel southButtonsPanel;
    private JPanel northHeaderPanel;
    JButton newButton;
    JButton editButton;
    JButton saveButton;
    JButton cancelButton;
    JButton deleteButton;
    private JLabel topicLabel;
    JComboBox<String> topicComboBox;
    private JLabel questionNumberLabel;
    private JLabel questionDescriptionLabel;
    private JLabel correctAnswerLabel;
    private JLabel firstInvalidAnswerLabel;
    private JLabel secondInvalidAnswerLabel;
    private JLabel thirdInvalidAnswerLabel;
    private JLabel questionInfoLabel;
    JComboBox questionNumberComboBox;
     JTextArea questionDescriptionArea;
     JTextArea answerArea;
     JTextArea firstInvalidAnswerArea;
     JTextArea secondInvalidAnswerArea;
     JTextArea thirdInvalidAnswerArea;
     JTextArea answerInfoArea;
    private JPanel leftHeaderSubPanel;
    private JPanel rightHeaderSubPanel;
    private JPanel topHeaderSubPanel;
    JButton newTopicButton;
    JLabel topLabel;
    Question currentQuestion;

    QuestionsEditor(){
        super();
        QEController qController = new QEController(this);

    }

    public static void main(String[] args) {
        formSetup();
    }

    public static void formSetup(){
        QuestionsEditor qe1 = new QuestionsEditor();
        Border border = BorderFactory.createLineBorder(Color.BLUE, 1);

        qe1.questionDescriptionArea.setBorder(border);
        qe1.answerArea.setBorder(border);
        qe1.firstInvalidAnswerArea.setBorder(border);
        qe1.secondInvalidAnswerArea.setBorder(border);
        qe1.thirdInvalidAnswerArea.setBorder(border);
        qe1.answerInfoArea.setBorder(border);
        qe1.topicComboBox.setBorder(border);
        qe1.questionNumberComboBox.setBorder(border);
        JFrame frame = new JFrame("Questions Editor");
        frame.setContentPane(qe1.questionsRootPanel);
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.pack();
        frame.setVisible(true);
    }

    public void displayQuestion(Question question){
        this.questionDescriptionArea.setText(question.getQuestion());
        this.answerArea.setText(question.getAnswer());
        this.firstInvalidAnswerArea.setText(question.getInvAns1());
        this.secondInvalidAnswerArea.setText(question.getInvAns2());
        this.thirdInvalidAnswerArea.setText(question.getInvAns3());
        this.answerInfoArea.setText(question.getAnsInfo());
        this.currentQuestion = question;
    }

    public boolean isQuestionFormFilled(){
        boolean result = true;
        if(this.questionDescriptionArea.getText().length()==0) result = false;
        if(this.answerArea.getText().length()==0) result = false;
        if(this.firstInvalidAnswerArea.getText().length()==0) result = false;
        if(this.secondInvalidAnswerArea.getText().length()==0) result = false;
        if(this.thirdInvalidAnswerArea.getText().length()==0) result = false;
        if(this.answerInfoArea.getText().length()==0) result = false;
        return result;
    }

}
