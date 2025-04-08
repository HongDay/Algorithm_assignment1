package algorithms;
import java.util.Arrays;

import common.Item;

public class MergeSort extends Sortings {

    @Override
    public Item[] getsorted(Item[] tobesorted){
        if (tobesorted.length <= 1) return tobesorted;
        int mid = tobesorted.length / 2;
        Item[] leftsorted = getsorted(Arrays.copyOfRange(tobesorted, 0, mid));
        Item[] righsorted = getsorted(Arrays.copyOfRange(tobesorted, mid, tobesorted.length));
        return Merge(leftsorted, righsorted);
    }
    
    private Item[] Merge(Item[] left, Item[] right){
        int i = 0, j = 0, k = 0;
        Item[] merged = new Item[left.length + right.length];
        while (i < left.length || j < right.length){
            if (i == left.length) merged[k++] = right[j++];
            else if (j == right.length) merged[k++] = left[i++];
            else if (left[i].value > right[j].value) merged[k++] = right[j++];
            else if (left[i].value <= right[j].value) merged[k++] = left[i++];
        }
        return merged;
    }

}
