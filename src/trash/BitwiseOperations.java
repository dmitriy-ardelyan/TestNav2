package trash;

import java.util.Random;

public class BitwiseOperations {

    public static void display(int value){
        System.out.println("Binary = " + Integer.toBinaryString(value) + " Decimal = " + value);
    }

    public static void main(String[] args) {
        // ~ Унарное отрицание (NOT)

        /*int b1 = 0b1010101010101010101010101010101;
        int b2 = ~b1;
        display(b1);
        display(b2);*/
        Random rnd = new Random();
        boolean mon = rnd.nextInt(2) == 1;
        boolean tue = rnd.nextInt(2) == 1;
        boolean wed = rnd.nextInt(2) == 1;
        boolean thu = rnd.nextInt(2) == 1;
        boolean fri = rnd.nextInt(2) == 1;
        boolean sat = rnd.nextInt(2) == 1;
        boolean sun = rnd.nextInt(2) == 1;


        int days =  (mon? Days.MONDAY:0) |
                    (tue? Days.TUESDAY:0) |
                    (wed? Days.WEDNESDAY:0)|
                    (thu? Days.THURSDAY:0)|
                    (fri? Days.FRIDAY:0)|
                    (sat? Days.SATURDAY:0)|
                    (sun? Days.SUNDAY:0);

        display(days);

        System.out.println("Boolean mon = " + mon + " while days&Days.Monday = " + (days & Days.MONDAY));
    }
}

