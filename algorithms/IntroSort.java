package algorithms;

import common.Item;

public class IntroSort extends Sortings{
    
    @Override
    public Item[] getsorted(Item[] tobesorted){
        
        int maxdepth = (int)(Math.log(tobesorted.length) / Math.log(2)) * 2;
        introsort(tobesorted, maxdepth, 0, tobesorted.length-1);

        return tobesorted;
    }

    private void introsort(Item[] tobesorted, int maxdepth, int left, int right){
        if (right-left+1 < 16) {
            partial_insertion(tobesorted, left, right);
        }
        else if (maxdepth == 0){
            partial_heapsort(tobesorted, left, right);
        }
        else {
            int p = QuickSort.partition(tobesorted,left,right);
            introsort(tobesorted, maxdepth-1, left, p-1);
            introsort(tobesorted, maxdepth-1, p+1, right);
        }
    }

    private void partial_heapsort(Item[] tobesorted, int left, int right){
        for (int i = (right-left+1) / 2 - 1; i >= 0; i--){
            partial_maxheapify(tobesorted, right-left+1, i, left);
        }
        for (int i = right-left; i > 0; i--){
            Item root = tobesorted[left];
            tobesorted[left] = tobesorted[left + i];
            tobesorted[left + i] = root;
            partial_maxheapify(tobesorted,i,0, left);
        }
    }

    private void partial_maxheapify(Item[] tobesorted, int len, int i, int offset){
        int l = 2*i + 1;
        int r = 2*i + 2;
        int largest;

        if (l < len && tobesorted[offset+l].value > tobesorted[offset+i].value) largest = l;
        else largest = i;
        if (r < len && tobesorted[offset+r].value > tobesorted[offset+largest].value) largest = r;

        if (largest != i){
            Item forswap = tobesorted[offset+largest];
            tobesorted[offset+largest] = tobesorted[offset+i];
            tobesorted[offset+i] = forswap;
            partial_maxheapify(tobesorted, len, largest, offset);
        }
    }

    private void partial_insertion(Item[] tobesorted, int left, int right){
        for (int i = left+1; i <= right; i++) {
            Item target = tobesorted[i];
            int j = i-1;
            while(j>=left){
                if(tobesorted[j].value>target.value){
                    tobesorted[j+1] = tobesorted[j];
                    j--;
                }
                else break;
            }
            tobesorted[j+1] = target;
        }

    }
}
