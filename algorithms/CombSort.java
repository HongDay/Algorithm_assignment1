package algorithms;

import common.Item;

public class CombSort extends Sortings {
    
    @Override
    public Item[] getsorted(Item[] tobesorted){
        int gap = tobesorted.length;
        double shrink = 1.3;
        boolean sorted = false;

        while (sorted == false){
            gap = (int)(gap / shrink);
            if (gap > 1) sorted = false;
            else {
                sorted = true;
                gap = 1;
            }

            int i = 0;
            while (i+gap < tobesorted.length){
                if(tobesorted[i].value > tobesorted[i+gap].value){
                    Item temp = tobesorted[i];
                    tobesorted[i] = tobesorted[i+gap];
                    tobesorted[i+gap] = temp;
                    sorted = false;
                }
                i++;
            }
        }
        return tobesorted;
    }
}
