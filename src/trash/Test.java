package trash;

public class Test {

    public Question[] questions;

    Test(){

        SeleniumQuestions seleniumQuestions  = new SeleniumQuestions();
        int questionsAmount = seleniumQuestions.getAmountOfQuestions();
        this.questions = new Question[questionsAmount];
        this.questions = seleniumQuestions.getSeleniumQuestions();
    }
}
