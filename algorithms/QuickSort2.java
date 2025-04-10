package algorithms;
import common.Item;

public class QuickSort2 extends Sortings {

    private int recursionDepth = 0;

    @Override
    public Item[] getsorted(Item[] tobesorted){
        recursionDepth = 0;
        try {
            quick_sort(tobesorted, 0, tobesorted.length - 1);
        } catch (StackOverflowError e) {
            System.err.println("StackOverflow Occured!! / recurse stopped at depth : " + recursionDepth);
            throw e;
        }
        return tobesorted;
    }

    private void quick_sort(Item[] tobesorted, int left, int right){
        if (left < right) {
            recursionDepth++;
            int q = partition(tobesorted, left, right);
            quick_sort(tobesorted, left, q - 1);
            quick_sort(tobesorted, q + 1, right);
        }
    }

    public static int partition(Item[] tobesorted, int left, int right){
        int pivotIndex = left + (right - left) / 2;
        Item pivot = tobesorted[pivotIndex];

        Item temp = tobesorted[pivotIndex];
        tobesorted[pivotIndex] = tobesorted[right];
        tobesorted[right] = temp;

        int i = left - 1;
        for (int j = left; j < right; j++) {
            if (tobesorted[j].value <= pivot.value) {
                i++;
                Item temp2 = tobesorted[i];
                tobesorted[i] = tobesorted[j];
                tobesorted[j] = temp2;
            }
        }
        Item temp3 = tobesorted[i+1];
        tobesorted[i+1] = tobesorted[right];
        tobesorted[right] = temp3;
        return i + 1;
    }

    private static void swap(Item[] array, int i, int j) {
        Item temp = array[i];
        array[i] = array[j];
        array[j] = temp;
    }
}
