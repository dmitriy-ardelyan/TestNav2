package trash;

import javax.swing.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.Random;

public class TestNavEngine implements ActionListener {

    TestNav parent;
    Test test = new Test();
    int currentQuestionIndex;

    TestNavEngine(TestNav parent){
        this.parent = parent;
        this.displayQuestion(test.questions[0]);
        currentQuestionIndex = 0;
    }

    public void displayQuestion(Question question){

        parent.questionLabel.setText(question.getQuestion());
        parent.answerDetailsLabel.setText("Select on of the 4 options above!");
        int[] indexes = this.getRandomIndexes();

        parent.answer1Button.setText(question.getAnswers()[indexes[0]]);
        parent.answer2Button.setText(question.getAnswers()[indexes[1]]);
        parent.answer3Button.setText(question.getAnswers()[indexes[2]]);
        parent.answer4Button.setText(question.getAnswers()[indexes[3]]);

    }

    @Override
    public void actionPerformed(ActionEvent e) {
        JButton clickedButton = (JButton)e.getSource();
        Object src = e.getSource();

        Question currentQuestion = test.questions[currentQuestionIndex];
        String selectedAnswer = clickedButton.getText();

        if (src.equals(parent.nextButton)){
            if (currentQuestionIndex<test.questions.length){
            currentQuestionIndex++;
            displayQuestion(test.questions[currentQuestionIndex]);
            }
        } else {
            if (currentQuestion.checkAnswer(selectedAnswer)) {
                String stringToDisplayInInfo = String.format("<html>CORRECT:<br>%s</html>", currentQuestion.getInfo());
                parent.answerDetailsLabel.setText(stringToDisplayInInfo);
            } else {
                String stringToDisplayInInfo = String.format("<html>WRONG:<br>%s</html>", currentQuestion.getInfo());
                parent.answerDetailsLabel.setText(stringToDisplayInInfo);
            }

        }
    }

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
