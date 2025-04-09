package algorithms;

import common.Item;

public class HeapSort extends Sortings {
    
    @Override
    public Item[] getsorted(Item[] tobesorted){
        buildmaxheap(tobesorted);
        for (int i = tobesorted.length-1; i >= 1; i--){
            Item root = tobesorted[0];
            tobesorted[0] = tobesorted[i];
            tobesorted[i] = root;
            maxheapify(tobesorted,i,0);
        }
        return tobesorted;
    }

    private void buildmaxheap(Item[] tobesorted){
        for (int i = tobesorted.length / 2 - 1; i >= 0; i--){
            maxheapify(tobesorted, tobesorted.length, i);
        }
    }

    public static void maxheapify(Item[] tobesorted, int len, int i){
        int l = 2*i + 1;
        int r = 2*i + 2;
        int largest;

        if (l < len && tobesorted[l].value > tobesorted[i].value) largest = l;
        else largest = i;
        if (r < len && tobesorted[r].value > tobesorted[largest].value) largest = r;

        if (largest != i){
            Item forswap = tobesorted[largest];
            tobesorted[largest] = tobesorted[i];
            tobesorted[i] = forswap;
            maxheapify(tobesorted, len, largest);
        }
    }
}
