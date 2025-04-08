import common.Item;
import java.util.Random;
public class InputGenerator {
    public static Item[] inputgenerate(int size){
        Random rand = new Random();
        Item[] randarray = new Item[size];
        for(int i=0; i<size;i++){
            int value = rand.nextInt(1000000);
            randarray[i] = new Item(value, i);
        }
        return randarray;
    }
}