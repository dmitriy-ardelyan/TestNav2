package trash;

public class SpecialTest {
    public static void main(String[] args) {

        /*Pattern pattern1 = Pattern.compile("[^a-z]+");
        Matcher matcher1 = pattern1.matcher("abcdart");
        System.out.println(matcher1.find());*/


        /*Timer timer = new Timer();
        TimerTask timerTask = new TimerTask() {
            @Override
            public void run() {
                Calendar calendar = new GregorianCalendar();
                SimpleDateFormat simpleDateFormat = new SimpleDateFormat("dd:MMM:yyyy HH:mm:ss");
                String str = simpleDateFormat.format(calendar.getTime());
                System.out.println(str);
            }
        };

        timer.schedule(timerTask,5000,5000);*/

       /* TreeSet<Cat> catTreeSet = new TreeSet<>();
        catTreeSet.add(new Cat("Рыжик", 3));
        catTreeSet.add(new Cat("Рыжик", 16));
        catTreeSet.add(new Cat("Мурзик", 7));
        catTreeSet.add(new Cat("Васька", 11));
        catTreeSet.add(new Cat("Барсик", 5));

        for(Cat cat : catTreeSet) {
            System.out.println("Имя: " + cat.name + ", длина хвоста: " + cat.tailLength);
        }*/
    };

    private static String[] concatArrays(String[] a, String[] b){
        if(a==null) return b;
        if(b==null) return a;
        String[] result = new String[a.length + b.length];
        System.arraycopy(a,0, result,0, a.length);
        System.arraycopy(b,0, result, a.length, b.length);
        return result;
    }
}
