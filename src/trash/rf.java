package trash;

import java.io.File;
import java.util.Scanner;

public class rf {
    private Scanner file;

    public void openFile(){
        try {
            file = new Scanner(new File("C:\\test\\writeTest.txt"));
        } catch(Exception ex){
            ex.printStackTrace();
            System.out.println("File not found!");
        }
    }

    public void readFile(){
        while (file.hasNext()){
            String id = file.next();
            String name = file.next();
            String surname = file.next();
            System.out.printf("%s %s %s\n", id, name, surname);
        }
    }

    public void closeFile(){
        file.close();
    }
}
