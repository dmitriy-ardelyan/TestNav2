package trash;

import java.util.Formatter;

public class wtf {
    private Formatter file;

    public void openFile(){
        try {
            file = new Formatter("C:\\test\\writeTest.txt");
            System.out.println("File created");
        } catch (Exception ex){
            System.out.println("Something went wrong");
            ex.printStackTrace();
        }
    }

    public void addRecord(){
        file.format("%s\t%s\n","1", "Bender Rodriges");
        file.format("%s\t%s\n","2", "Ivanka Pavlovna");
        file.format("%s\t%s\n","3", "Petrova Pavlovna");
        file.format("%s\t%s\n","4", "Billy Jefferson");
    }

    public void closeFile(){
        file.close();
    }
}
