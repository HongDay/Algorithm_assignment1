package algorithms;
import common.Item;

public class CocktailSort extends Sortings {
    
    @Override
    public Item[] getsorted(Item[] tobesorted){
        int start = 0;
        int size = tobesorted.length - 1;
        
        for (int i = 0; i < tobesorted.length-1; i++){
            if(i % 2 == 0){
                for (int j = start; j < size; j++){
                    if (tobesorted[j].value>tobesorted[j+1].value){
                        Item target = tobesorted[j];
                        tobesorted[j] = tobesorted[j+1];
                        tobesorted[j+1] = target;
                    }
                }
                size--;
            }
            else{
                for (int j = size; j > start; j--){
                    if (tobesorted[j].value < tobesorted[j-1].value){
                        Item target = tobesorted[j];
                        tobesorted[j] = tobesorted[j-1];
                        tobesorted[j-1] = target;
                    }
                }
                start++;
            }
        }
        return tobesorted;
    }
}
