package algorithms;

import common.Item;

public class TournamentSort extends Sortings{

    @Override
    public Item[] getsorted(Item[] tobesorted){
        int n = tobesorted.length;
        int nleaf = 1;
        while (nleaf < n) nleaf *= 2;

        Item[] tree = new Item[2*nleaf-1];
        
        // leaf node (값)
        for (int i = 0; i < n; i++){
            tree[nleaf-1+i] = tobesorted[i];
        }
        // leaf node (나머지 null)
        for (int i = n; i < nleaf; i++){
            tree[nleaf-1+i] = new Item(1000001,n+1);
        }
        // 토너먼트 시키기
        for (int i = nleaf-2; i >=0; i--){
            if (tree[2*i+1].value > tree[2*i+2].value) tree[i] = tree[2*i+2];
            else tree[i] = tree[2*i+1];
        }

        // root를 우리 배열에 추가, 기존 원소 무한대로 바꾼 후, 다시 개신
        for (int i = 0; i < n; i++){
            tobesorted[i] = tree[0];
            int target = tree[0].value;
            int j = 0;
            while(j < nleaf-1){
                if (tree[2*j+1].value == target) j = 2*j+1;
                else j = 2*j+2;
            }
            tree[j] = new Item(1000001,n+1);
            while(j>0){
                j = (int)((j-1)/2);
                if (tree[2*j+1].value > tree[2*j+2].value) tree[j] = tree[2*j+2];
            else tree[j] = tree[2*j+1];
            }
        }
        return tobesorted;
    }
    
}
