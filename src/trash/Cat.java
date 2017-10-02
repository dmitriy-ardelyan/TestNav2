package trash;

import com.sun.istack.internal.NotNull;

public class Cat implements Comparable{

    String name;
    int tailLength;

    Cat(String name, int tailLength){
        this.name = name;
        this.tailLength = tailLength;
    }


    @Override
    public int compareTo(@NotNull Object o) {
        Cat entry = (Cat) o;
        int result = this.name.compareTo(entry.name);
        if (result!=0) return result;
        result = this.tailLength - entry.tailLength;
        if (result!=0) return result / Math.abs(result);
        return 0;
    }
}
