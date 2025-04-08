package algorithms;
import common.Item;

public class BubbleSort extends Sortings{
    
    @Override
    public Item[] getsorted(Item[] tobesorted){
        int size = tobesorted.length;
        
        for (int i = 0; i < size-1; i++){
            for (int j = 0; j < size-i-1; j++){
                if (tobesorted[j].value>tobesorted[j+1].value){
                    Item target = tobesorted[j];
                    tobesorted[j] = tobesorted[j+1];
                    tobesorted[j+1] = target;
                }
            }
        }
        return tobesorted;
    }
}