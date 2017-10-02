package ard.questionseditor;

public class Question {
    //Fields.
    private String topic;
    private String question;
    private String answer;
    private String invAns1;
    private String invAns2;
    private String invAns3;
    private String ansInfo;
    private int dbId;

    //Additional methods.
    public String[] getAllAnswers(){
        String[] allAnswers = {this.answer,this.invAns1, this.invAns2, this.invAns3};
        return allAnswers;
    }

    public boolean checkAnswer(String answer){
        if(answer.equals(this.answer)) return true;
        else return false;
    }

    @Override
    public String toString() {
        String questionDescription = String.format("Question{%s, %s, %s, %s, %s, %s, %s}",topic,question,answer,invAns1,invAns2,invAns3,ansInfo);
        return questionDescription;
    }

    //Getters ans setters.

    public String getTopic() {
        return topic;
    }

    public void setTopic(String topic) {
        this.topic = topic;
    }

    public String getAnswer() {
        return answer;
    }

    public void setAnswer(String answer) {
        this.answer = answer;
    }

    public String getQuestion() {
        return question;
    }

    public void setQuestion(String question) {
        this.question = question;
    }

    public String getInvAns1() {
        return invAns1;
    }

    public void setInvAns1(String invAns1) {
        this.invAns1 = invAns1;
    }

    public String getInvAns2() {
        return invAns2;
    }

    public void setInvAns2(String invAns2) {
        this.invAns2 = invAns2;
    }

    public String getInvAns3() {
        return invAns3;
    }

    public void setInvAns3(String invAns3) {
        this.invAns3 = invAns3;
    }

    public String getAnsInfo() {
        return ansInfo;
    }

    public void setAnsInfo(String ansInfo) {
        this.ansInfo = ansInfo;
    }

    public int getDbId() {
        return dbId;
    }

    public void setDbId(int dbId) {
        this.dbId = dbId;
    }
}
