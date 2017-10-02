package trash;

public class FilesTest {
    public static void main(String[] args) {

        rf file = new rf();
        file.openFile();
        file.readFile();
        file.closeFile();

        /*wtf f  = new wtf();
        f.openFile();
        f.addRecord();
        f.closeFile();*/

        /*final Formatter file;

        try {
            file = new Formatter("C:\\test\\test2.txt");
            System.out.println("File created");
        } catch (Exception ex){
            ex.printStackTrace();
            System.out.println("File cretion failed");
        }*/

        /*File fileTest = new File("c:\\test\\test.txt");
        if (fileTest.exists()){
            System.out.println("File " + fileTest.getName() + " exists");
        } else {
            System.out.println("File does not exist");
        }*/
    }
}
