package algorithms;
import common.Item;
import java.util.Arrays;

public class LibrarySort extends Sortings {

    @Override
    public Item[] getsorted(Item[] tobesorted){
        Item[] bigarr = new Item[tobesorted.length*2];
        bigarr[bigarr.length/2] = tobesorted[0];

        for(int i = 1; i < tobesorted.length; i++){
            // rebalancing 구현
            if ((i & (i + 1)) == 0){
                Item[] temp = new Item[tobesorted.length];
                int tempi = 0;
                for (Item item : bigarr){
                    if (item!=null) {
                        temp[tempi] = item;
                        tempi++;
                    }
                }
                Arrays.fill(bigarr, null);

                int gap = bigarr.length / (tempi+1);
                int idx = gap;

                for (Item item : temp){
                    if (idx >= bigarr.length) break;
                    bigarr[idx] = item;
                    idx += gap;
                }
            }

            int inserti = libinary(bigarr, tobesorted[i].value);

            // 그자리가 null 이면 그냥 넣고 아니면 밀어서 넣는 부분
            if (bigarr[inserti] != null){
                int nulli = nearnull(bigarr, inserti);
                // 가장가까운 nulli 쪽으로 밀어서 넣는 코드
                if (nulli < inserti){
                    for (int j = nulli; j < inserti; j++){
                        bigarr[j] = bigarr[j+1];
                    }
                    bigarr[inserti] = tobesorted[i];
                }
                else {
                    for (int j = nulli; j > inserti; j--){
                        bigarr[j] = bigarr[j-1];
                    }
                    bigarr[inserti] = tobesorted[i];
                }
            }
            else bigarr[inserti] = tobesorted[i];
        }
        // 최종 정렬된 tobesorted를 반환하는 코드
        int idx = 0;
        for (Item item : bigarr){
            if (item != null){
                tobesorted[idx++] = item;
                if(idx == tobesorted.length) break;
            }
        }
        return tobesorted;
    }

    private int libinary(Item[] bigarr, int val){
        int left = 0;
        int right = bigarr.length - 1;
        int mid = (left+right) / 2;

        while(left <= right){
            mid = (left + right) / 2;
            int finded = findnear(bigarr, mid, left, right);
            if (finded == -1) break;
            else if(val < bigarr[finded].value) right = finded-1;
            else if(val >= bigarr[finded].value) left = finded+1;
        }
        return mid;
    }

    // 만약 타겟 값이 null이면 가장 가까운 null이아닌 값의 위치 / null이 아니면 그 위치를 반환
    private int findnear(Item[] bigarr, int index, int start, int end){
        if (bigarr[index] != null) return index;
        else {
            int low = index-1;
            int high = index+1;
            while (low>=start || high<=end){
                if (low >= start && bigarr[low] != null) return low;
                if (high <= end && bigarr[high] != null) return high;
                low--;
                high++;
            }
            return -1;
        }
    }

    private int nearnull(Item[] bigarr, int index){
        int low = index-1;
        int high = index+1;
        while (low >=0 || high < bigarr.length){
            if (low >= 0 && bigarr[low] == null) return low;
            if (high < bigarr.length && bigarr[high] == null) return high;
            low--;
            high++;
        }
        return -1;
    }
    
}
