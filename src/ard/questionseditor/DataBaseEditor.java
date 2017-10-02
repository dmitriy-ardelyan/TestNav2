package ard.questionseditor;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.Statement;
import java.util.ArrayList;

public class DataBaseEditor {

    //Requests to DB.
    public static int countQuestionsInTopic(String topic){
        int result = 0;
        if (topic.equals("All")){
            try {
                Connection con = establishConnection();
                Statement stmt = con.createStatement();
                String query = "SELECT COUNT(Id) FROM [TestNavDB].[dbo].[Questions]";
                ResultSet rs = stmt.executeQuery(query);
                if (rs.next()){
                    result = (rs.getInt(1));
                }
            } catch (Exception ex){
                System.out.println("Error: " + ex);
            }
        } else {
            try {
                Connection con = establishConnection();
                Statement stmt = con.createStatement();
                String query = String.format("SELECT COUNT(Id) FROM [TestNavDB].[dbo].[Questions] WHERE (Topic_Id = %d)",getTopicId(topic));
                ResultSet rs = stmt.executeQuery(query);
                if (rs.next()){
                    result = (rs.getInt(1));
                }
            } catch (Exception ex){
                System.out.println("Error: " + ex);
            }
        }
        return result;
    }

    public static String getTopicById(int id){
        String result = new String();
        try {
            Connection con = establishConnection();
            Statement stmt = con.createStatement();
            String query = String.format("SELECT * FROM [TestNavDB].[dbo].[Topics] WHERE(Id = %d)",id);
            ResultSet rs = stmt.executeQuery(query);
            if (rs.next()){
                result = (rs.getString("Topic"));
            }
        } catch (Exception ex){
            System.out.println("Error: " + ex);
        }
        return result;
    }

    public static Question getQuestionByQuestionId(int id){
        Question result = new Question();
        try{
            Connection con = establishConnection();
            Statement stmt = con.createStatement();
            String query = String.format("SELECT * FROM [TestNavDB].[dbo].[Questions] WHERE(Id = %d)",id);
            ResultSet rs = stmt.executeQuery(query);
            if (rs.next()){
                result.setTopic(getTopicById(rs.getInt("Topic_Id")));
                result.setDbId(rs.getInt("Id"));
                result.setQuestion(rs.getString("Question"));
                result.setAnswer(rs.getString("Answer"));
                result.setInvAns1(rs.getString("InvAns1"));
                result.setInvAns2(rs.getString("InvAns2"));
                result.setInvAns3(rs.getString("InvAns3"));
                result.setAnsInfo(rs.getString("AnsInfo"));
            }
        } catch (Exception ex){
            ex.printStackTrace();
        }
         return result;
    }

    public static ArrayList getQuestionsByTopic(String topic){
        ArrayList questionsList = new ArrayList();
        try {
            Connection con = establishConnection();
            Statement stmt = con.createStatement();
            String query = String.format("SELECT * FROM [TestNavDB].[dbo].[Questions] WHERE(Topic_Id = %d)",getTopicId(topic));
            ResultSet rs = stmt.executeQuery(query);
            while(rs.next()){
                Question questionToAdd = new Question();
                questionToAdd.setTopic(topic);
                questionToAdd.setDbId(rs.getInt("Id"));
                questionToAdd.setQuestion(rs.getString("Question"));
                questionToAdd.setAnswer(rs.getString("Answer"));
                questionToAdd.setInvAns1(rs.getString("InvAns1"));
                questionToAdd.setInvAns2(rs.getString("InvAns2"));
                questionToAdd.setInvAns3(rs.getString("InvAns3"));
                questionToAdd.setAnsInfo(rs.getString("AnsInfo"));
                questionsList.add(questionToAdd);
            }
        } catch (Exception ex){
            ex.printStackTrace();
        }
        return questionsList;
    }

    public static ArrayList getAllQuestions(){
        ArrayList questionsList = new ArrayList();
        try {
            Connection con = establishConnection();
            Statement stmt = con.createStatement();
            String query = String.format("SELECT * FROM [TestNavDB].[dbo].[Questions]");
            ResultSet rs = stmt.executeQuery(query);
            while(rs.next()){
                Question questionToAdd = new Question();
                questionToAdd.setTopic(getTopicById(rs.getInt("Topic_Id")));
                questionToAdd.setDbId(rs.getInt("Id"));
                questionToAdd.setQuestion(rs.getString("Question"));
                questionToAdd.setAnswer(rs.getString("Answer"));
                questionToAdd.setInvAns1(rs.getString("InvAns1"));
                questionToAdd.setInvAns2(rs.getString("InvAns2"));
                questionToAdd.setInvAns3(rs.getString("InvAns3"));
                questionToAdd.setAnsInfo(rs.getString("AnsInfo"));
                questionsList.add(questionToAdd);
            }
        } catch (Exception ex){
            ex.printStackTrace();
        }
        return questionsList;
    }

    public static void addQuestion(Question question){
        try{
            Connection con = establishConnection();
            Statement stmt = con.createStatement();
            String values = String.format("%d,'%s','%s','%s','%s','%s','%s'",
                    getTopicId(question.getTopic()),
                    question.getQuestion(),
                    question.getAnswer(),
                    question.getInvAns1(),
                    question.getInvAns2(),
                    question.getInvAns3(),
                    question.getAnsInfo());
            String requestStr = ("INSERT INTO [TestNavDB].[dbo].[Questions]" +
                    "(Topic_Id, Question, Answer, InvAns1, InvAns2, InvAns3, AnsInfo) " +
                    "VALUES(" + values + ")");
            stmt.execute(requestStr);
        }catch (Exception ex){
            ex.printStackTrace();
        }
    }

    public static ArrayList getTopics(){
        ArrayList resultInStrings = null;
        try {
            Connection con = establishConnection();
            Statement stmt = con.createStatement();
            ResultSet rs = stmt.executeQuery("SELECT * FROM [TestNavDB].[dbo].[Topics]");
            resultInStrings = getStringArrayForColumnLabel(rs,"Topic");
        } catch (Exception ex){
            System.out.println("Error: " + ex);
        }
        return resultInStrings;
    }

    public static void addTopic(String topic){
        try{
            Connection con = establishConnection();
            Statement stmt = con.createStatement();
            String requestStr = new String("INSERT INTO [TestNavDB].[dbo].[Topics](Topic) VALUES('"+topic+"')");
            stmt.execute(requestStr);
        }catch (Exception ex){
            ex.printStackTrace();
        }
    }

    public static int getTopicId(String topic){
        int topicId = 0;
        try{
            Connection con = establishConnection();
            Statement stmt = con.createStatement();
            ResultSet rs = stmt.executeQuery("SELECT * FROM [TestNavDB].[dbo].[Topics] WHERE [Topic]='" + topic + "'");
            if (rs.next()){
                topicId = rs.getInt(1);
            }
        }catch (Exception ex){
            ex.printStackTrace();
        }
        return topicId;
    }

    public static void deleteQuestion(Question question){
        try {
            Connection con = establishConnection();
            Statement stmt = con.createStatement();
            String requestStr = String.format("DELETE FROM Questions WHERE Id=%d",question.getDbId());
            stmt.execute(requestStr);
        } catch (Exception ex){
            System.out.println("Error: " + ex);
        }
    }

    public static void updateQuestion(Question question){
        try {
            Connection con = establishConnection();
            Statement stmt = con.createStatement();
            String requestStr = String.format("UPDATE Questions SET Question = '%s', Answer= '%s', InvAns1 = '%s', InvAns2 = '%s', " +
                    "InvAns3 = '%s', AnsInfo = '%s' WHERE Id = %d;",
                    question.getQuestion(),
                    question.getAnswer(),
                    question.getInvAns1(),
                    question.getInvAns2(),
                    question.getInvAns3(),
                    question.getAnsInfo(),
                    question.getDbId());
            stmt.execute(requestStr);
        } catch (Exception ex){
            System.out.println("Error: " + ex);
        }
    }

    //Additional methods.
    public static ArrayList getListOfQuestionIndexesByTopic(String topic){
        int amountOfQuestionsInTopic = countQuestionsInTopic(topic);
        ArrayList result = new ArrayList();

        if (topic.equals("All")){
            ArrayList<Question> tmpList = getAllQuestions();
            for (int i = 0 ; i<amountOfQuestionsInTopic;i++){
                result.add(tmpList.get(i).getDbId());
            }
        } else {
            ArrayList<Question> tmpList = getQuestionsByTopic(topic);
            for (int i = 0 ; i<amountOfQuestionsInTopic;i++){
                result.add(tmpList.get(i).getDbId());
            }
        }
        return result;
    }

    public static String[] getTopicsStrings(){
        ArrayList<String> topicsList = getTopics();
        String[] result = new String[topicsList.size()];
        result = topicsList.toArray(result);
        return result;
    }

    public static void printTopics(){
        ArrayList<String> topics = DataBaseEditor.getTopics();
        for (int i=0; i<topics.size();i++){
            System.out.printf("Topic %d : %s\n",i+1,topics.get(i));
        }
    }

    private static Connection establishConnection(){
        Connection con = null;
        try {
            Class.forName("com.microsoft.sqlserver.jdbc.SQLServerDriver");
            String server = "localhost\\sqlexpress";
            int port = 50222;
            String user = "sa";
            String password = "Q1w2e3r4";
            String database = "TestNavDB";
            String jdbcURL = "jdbc:sqlserver://" + server + ":" + port + ";user=" + user + ";password=" + password + ";databaseName=" + database;

            con = DriverManager.getConnection(jdbcURL);

        } catch(Exception ex){
            System.out.println("Error: " + ex);
        }
        return con;
    }

    private static ArrayList getStringArrayForColumnLabel(ResultSet rs, String label){
        ArrayList result = new ArrayList();
        try {
            int i = 0;
            while (rs.next()) {
                result.add(rs.getString(label));
                i++;
            }
        } catch(Exception ex){
            ex.printStackTrace();
        }
        return  result;
    }

}
