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
            // 이 방법은 기존의 HeapSort의 함수를 그대로 사용할 수 있지만, 메모리 공간을 추가로 요구함
            Item[] partialheap = new Item[right-left+1];
            System.arraycopy(tobesorted, left, partialheap, 0, partialheap.length);

            HeapSort heaps = new HeapSort();
            partialheap = heaps.getsorted(partialheap);

            System.arraycopy(partialheap, 0, tobesorted, left, partialheap.length);
        }
        else {
            QuickSort quicks = new QuickSort();
            int p = quicks.partition(tobesorted,left,right);
            introsort(tobesorted, maxdepth-1, left, p-1);
            introsort(tobesorted, maxdepth-1, p+1, right);
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
