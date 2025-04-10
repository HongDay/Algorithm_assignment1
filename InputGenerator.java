import common.Item;
import java.util.Random;
public class InputGenerator {

    public static Item[] randominput(int size){
        Random rand = new Random();
        Item[] array = new Item[size];
        for(int i=0; i<size;i++){
            int value = rand.nextInt(1000000);
            array[i] = new Item(value, i);
        }
        return array;
    }

    public static Item[] sortedinput(int size) {
        Item[] array = new Item[size];
        for (int i = 0; i < size; i++) {
            array[i] = new Item(i, i);
        }
        return array;
    }

    public static Item[] reversedinput(int size) {
        Item[] array = new Item[size];
        for (int i = 0; i < size; i++) {
            array[i] = new Item(size - i, i);
        }
        return array;
    }

    public static Item[] partialinput(int size) {
        Random rand = new Random();
        Item[] array = new Item[size];

        for (int i = 0; i < size; i++) {
            int value = rand.nextInt(1000000);
            array[i] = new Item(value, i);
        }

        int start1 = size / 4;
        int end1 = size / 2;
        for (int i = start1; i < end1; i++){
            array[i].value = i;
        }

        int start2 = (3 * size) / 4;
        int end2 = size;
        for (int i = start2; i < end2; i++){
            array[i].value = i;
        }
        return array;
        }
}