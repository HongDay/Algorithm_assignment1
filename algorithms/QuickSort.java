package algorithms;
import common.Item;

public class QuickSort extends Sortings {
    
    @Override
    public Item[] getsorted(Item[] tobesorted){
        quick_sort(tobesorted, 0, tobesorted.length-1);
        return tobesorted;
    }

    private void quick_sort(Item[] tobesorted, int left, int right){
        if(left<right){
            int q = partition(tobesorted,left,right);
            quick_sort(tobesorted, left, q-1);
            quick_sort(tobesorted, q+1, right);
        }
    }

    public int partition(Item[] tobesorted, int left, int right){
        Item pivot = tobesorted[right];
        int i = left - 1;
        for (int j = left; j < right; j++){
            if(tobesorted[j].value <= pivot.value){
                i++;
                Item forswap = tobesorted[i];
                tobesorted[i] = tobesorted[j];
                tobesorted[j] = forswap;
            }
        }
        Item forswap2 = tobesorted[i+1];
        tobesorted[i+1] = tobesorted[right];
        tobesorted[right] = forswap2;

        return i+1;
    }
}
