package algorithms;
import common.Item;

public class InsertionSort extends Sortings{

    @Override
    public Item[] getsorted(Item[] tobesorted){
        int size = tobesorted.length;

        for (int i = 1; i < size; i++) {
            Item target = tobesorted[i];
            int j = i-1;
            while(j>=0){
                if(tobesorted[j].value>target.value){
                    tobesorted[j+1] = tobesorted[j];
                    j--;
                }
                else break;
            }
            tobesorted[j+1] = target;
        }

        return tobesorted;
    }
}