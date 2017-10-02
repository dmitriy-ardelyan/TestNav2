package trash;

public class Question {
    private String question;
    private String[]answers;
    private String info;

    public void setQuestion(String receivedQuestion){
        this.question = receivedQuestion;
    }

    public void setAnswers(String[] receivedAnswers){
        this.answers = receivedAnswers;
    }

    public void setInfo(String receivedInfo){
        this.info = receivedInfo;
    }

    public String getInfo() {
        return info;
    }

    public String getQuestion() {
        return question;
    }

    public String[] getAnswers() {
        return answers;
    }

    public boolean checkAnswer(String answer){
        if(answer.equals(this.answers[0])) return true;
        else return false;
    }
}
