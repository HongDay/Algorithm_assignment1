package algorithms;

import common.Item;

public class TimSort extends Sortings {

    int minrun;
    Run[] runstack;
    int stacki;

    @Override
    public Item[] getsorted(Item[] tobesorted){

        // run의 개수 = n / minrun을 2^k로 만드는, 즉 minrun = n / 2^k (32<=minrun<64)
        this.minrun = tobesorted.length;
        while (minrun >= 64) minrun /= 2;
        if (minrun < 32) minrun = 32;

        this.runstack = new Run[(int)(tobesorted.length/minrun)+1];
        pushruns(tobesorted);

        // final merge
        while (this.stacki > 1) partial_merge(tobesorted, stacki-2);

        return tobesorted;
    }

    private void invariant(Item[] tobesorted){
        while (this.stacki > 1){
            int top = this.stacki-1;
            if (top >= 2){
                Run X = this.runstack[top-2];
                Run Y = this.runstack[top-1];
                Run Z = this.runstack[top];

                if (Z.length <= X.length + Y.length || X.length >= Y.length){
                    if (X.length < Z.length) partial_merge(tobesorted, top-2);
                    else partial_merge(tobesorted, top-1);
                } else break;

            }
            else if (top == 1){
                Run Y = this.runstack[top-1];
                Run Z = this.runstack[top];

                if (Z.length <= Y.length) partial_merge(tobesorted, top-1);
                else break;
            }
            else break;
        }
    }

    private void partial_merge(Item[] tobesorted, int top){
        // top 이랑 top+1이랑 merge하고 top 자리에 삽입한 후, top+1쪽으로 뒤에있는것들 땡겨오고
        Run A = this.runstack [top];
        Run B = this.runstack [top+1];
        Item[] merged = new Item[A.length + B.length];

        int i = 0, j = 0, k = 0;
        int countA = 0, countB = 0;
        while (i < A.length || j < B.length){
            if (i == A.length) {
                merged[k++] = tobesorted[B.start+j];
                j++;
                countB ++;
                countA = 0;
            }
            else if (j == B.length) {
                merged[k++] = tobesorted[A.start+i];
                i++;
                countA ++;
                countB = 0;
            }
            else if (tobesorted[A.start+i].value > tobesorted[B.start+j].value) {
                merged[k++] = tobesorted[B.start+j];
                j++;
                countB ++;
                countA = 0;
            }
            else if (tobesorted[A.start+i].value <= tobesorted[B.start+j].value) {
                merged[k++] = tobesorted[A.start+i];
                i++;
                countA ++;
                countB = 0;
            }
            // galloping
            if (countA >= 7){
                int gal = gallop(tobesorted, tobesorted[B.start+j].value, A, i);
                gal = Math.min(gal, A.length - i);
                for (int g = 0; g < gal; g++){
                    merged[k+g] = tobesorted[A.start+i+g];
                }
                i += gal;
                k += gal;
                countA = 0;
                countB = 0;
            }
            else if (countB >= 7){
                int gal = gallop(tobesorted, tobesorted[A.start+i].value, B, j);
                gal = Math.min(gal, B.length - j);
                for (int g = 0; g < gal; g++){
                    merged[k+g] = tobesorted[B.start+j+g];
                }
                j += gal;
                k += gal;
                countA = 0;
                countB = 0;
            }

        }
        for (int part = 0; part < A.length+B.length; part++){
            tobesorted[A.start+part] = merged[part];
        }
        this.runstack [top] = new Run(A.start, A.length+B.length);
        for (int s = top + 1; s < this.stacki - 1; s++) {
            this.runstack[s] = this.runstack[s + 1];
        }
        this.runstack[--this.stacki] = null;
    }

    private int gallop(Item[] tobesorted, int target, Run X, int start){
        int offset = 1;
        int idx = start;

        while (idx + offset < X.length && tobesorted[X.start+idx+offset].value < target) offset *= 2;

        int low = idx + (offset / 2);
        int high = Math.min(idx+offset, X.length);
        while (low < high){
            int mid = (low+high)/2;
            if (tobesorted[X.start+mid].value < target) low = mid + 1;
            else high = mid;
        }
        return low-start;
    }

    private void pushruns(Item[] tobesorted){
        int runstart;
        int i = 0;
        this.stacki = 0;
        while (i < tobesorted.length - 1){
            int runlen = 1;
            boolean asc = (tobesorted[i].value <= tobesorted[i+1].value);
    
            if (asc){
                runstart = i;
                do {
                    i++;
                    runlen++;
                } while(i < tobesorted.length-1 && tobesorted[i].value <= tobesorted[i+1].value);
            }
            else {
                runstart = i;
                do {
                    i++;
                    runlen++;
                } while(i < tobesorted.length-1 && tobesorted[i].value > tobesorted[i+1].value);
            }
            
            // reverse
            if (!asc) {
                int l = runstart;
                int r = runstart + runlen - 1;
                while (l < r){
                    Item temp = tobesorted[l];
                    tobesorted[l] = tobesorted[r];
                    tobesorted[r] = temp;
                    l++;
                    r--;
                }
            }

            if (runlen < this.minrun){
                int end = Math.min(runstart+this.minrun, tobesorted.length);
                insertionRun(tobesorted, runstart, end);
                int newlen = end-runstart;
                this.runstack[this.stacki] = new Run(runstart,newlen);
                i = end;
            }
            else {
                this.runstack[this.stacki] = new Run(runstart,runlen);
                i++;
            }
            this.stacki++;
            // invariant
            invariant(tobesorted);
        }
        if (i==tobesorted.length-1) {
            this.runstack[this.stacki] = new Run(i, 1);
            this.stacki++;
            // invariant
            invariant(tobesorted);
        }
    }

    private void insertionRun(Item[] tobesorted, int runstart, int end){
        for (int i = runstart; i < end; i++) {
            Item target = tobesorted[i];
            int j = i-1;
            while(j>=runstart){
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

class Run{
    int start;
    int length;

    Run(int start, int length){
        this.start = start;
        this.length = length;
    }
}
