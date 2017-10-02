package ard.testnavigator;

import ard.questionseditor.DataBaseEditor;
import ard.questionseditor.Question;

import java.util.ArrayList;
import java.util.Random;

public class TestModel {
    String topic;
    int amountOfQuestions;
    ArrayList<Question> questions;

    TestModel(String topic, int amountOfQuestions){
            this.topic = topic;
            this.amountOfQuestions = amountOfQuestions;
            this.questions = new ArrayList<Question>();
            ArrayList<Integer> indexList = new ArrayList();

            if (this.topic.equals("All")) {
                if (amountOfQuestions >= DataBaseEditor.countQuestionsInTopic("All")) {
                    this.questions = DataBaseEditor.getAllQuestions();
                } else {
                    indexList = DataBaseEditor.getListOfQuestionIndexesByTopic("All");
                    while (indexList.size() > this.amountOfQuestions) {
                        Random random = new Random();
                        int randomIndexToDelete = random.nextInt(indexList.size());
                        indexList.remove(randomIndexToDelete);
                    }
                }
            } else {
                if (amountOfQuestions >= DataBaseEditor.countQuestionsInTopic(this.topic)) {
                    this.questions = DataBaseEditor.getQuestionsByTopic(this.topic);
                } else {
                    indexList = DataBaseEditor.getListOfQuestionIndexesByTopic(this.topic);
                    while (indexList.size() > this.amountOfQuestions) {
                        Random random = new Random();
                        int randomIndexToDelete = random.nextInt(indexList.size());
                        indexList.remove(randomIndexToDelete);
                    }
                }
            }

            for (int i = 0; i < indexList.size(); i++) {
                this.questions.add(DataBaseEditor.getQuestionByQuestionId(indexList.get(i).intValue()));
            }
            this.setRandomOrder();
    }

    //Additional Methods.
    private void setRandomOrder(){
        ArrayList<Question> tmpDecrementList = (ArrayList<Question>) this.questions.clone();
        for(int i = 0;tmpDecrementList.size()>0;i++){
            Random rnd = new Random();
            int random = rnd.nextInt(tmpDecrementList.size());
            this.questions.set(i,tmpDecrementList.get(random));
            tmpDecrementList.remove(random);
        }
    }
}
