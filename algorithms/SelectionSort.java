package algorithms;
import common.Item;

public class SelectionSort extends Sortings {

    @Override
    public Item[] getsorted(Item[] tobesorted){
        int size = tobesorted.length;

        for (int i = 0; i<size; i++){
            Item target = tobesorted[i];
            int index=i;
            for (int j = i+1; j<size; j++){
                if (tobesorted[j].value < target.value) {
                    target = tobesorted[j];
                    index = j;
                }
            }
            tobesorted[index] = tobesorted[i];
            tobesorted[i] = target;
        }
        return tobesorted;
    }
    
}
