import algorithms.BubbleSort;
import common.Item;

public class temp {
    public static void main(String[] args) {

        int[] sizes = {1000, 3000, 6000, 10000, 30000, 60000, 100000, 300000, 600000, 1000000};
        for (int size : sizes) {
            Item[] testarr = new Item[size];
            for (int i = testarr.length-1; i>=0; i--){
                testarr[i] = new Item((testarr.length-1-i)*1000, i);
            }
    
            BubbleSort sorter = new BubbleSort();
            
            Runtime runtime = Runtime.getRuntime();
            runtime.gc();
    
            long beforebyte = runtime.totalMemory()-runtime.freeMemory();
    /*
            for (int i = testarr.length-1; i>=0; i--){
                testarr[i] = new Item((testarr.length-1-i)*1000, i);
            }
            */
            for (int i = 0; i < size-1; i++){
                for (int j = 0; j < size-i-1; j++){
                    if (testarr[j].value>testarr[j+1].value){
                        Item target = testarr[j];
                        testarr[j] = testarr[j+1];
                        testarr[j+1] = target;
                    }
                }
            }
            Item[] finalarr = testarr;
    
            long afterbyte = runtime.totalMemory()-runtime.freeMemory();
    
            System.out.println(beforebyte);
            System.out.println(afterbyte);
        }
    }
}
