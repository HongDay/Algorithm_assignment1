package algorithms;

import common.Item;

public class TimSort extends Sortings {

    int minrun;
    Run[] runstack;
    int stacki;
    Item[] mergeBuffer;

    @Override
    public Item[] getsorted(Item[] tobesorted) {
        // minrun 계산 (Java 표준 기반)
        int n = tobesorted.length;
        int r = 0;
        while (n >= 64) {
            r |= (n & 1);
            n >>= 1;
        }
        this.minrun = n + r;

        this.runstack = new Run[(tobesorted.length / minrun) + 1];
        this.stacki = 0;

        // run들을 스택에 쌓음
        pushruns(tobesorted);

        // run 병합 (stack이 하나가 될 때까지)
        while (this.stacki > 1) {
            partial_merge(tobesorted, this.stacki-1);
        }
        return tobesorted;
    }

    private void invariant(Item[] tobesorted) {
        while (this.stacki > 1) {
            int top = this.stacki - 1;
            if (top >= 2) {
                Run X = this.runstack[top - 2];
                Run Y = this.runstack[top - 1];
                Run Z = this.runstack[top];
                if (Z.length <= X.length + Y.length || X.length >= Y.length) {
                    if (X.length < Z.length)
                        partial_merge(tobesorted, top - 2);
                    else
                        partial_merge(tobesorted, top - 1);
                } else break;
            } else if (top == 1) {
                Run Y = this.runstack[top - 1];
                Run Z = this.runstack[top];
                if (Z.length <= Y.length)
                    partial_merge(tobesorted, top - 1);
                else break;
            } else break;
        }
    }

    private void partial_merge(Item[] tobesorted, int top) {
        Run A = this.runstack[top];
        Run B = this.runstack[top + 1];

        int total = A.length + B.length;
        if (mergeBuffer == null || mergeBuffer.length < total) {
            mergeBuffer = new Item[total];
        }
        Item[] merged = mergeBuffer;

        int i = 0, j = 0, k = 0;
        int countA = 0, countB = 0;
        while (i < A.length || j < B.length) {
            if (i == A.length) {
                merged[k++] = tobesorted[B.start + j++];
                countB++;
                countA = 0;
            } else if (j == B.length) {
                merged[k++] = tobesorted[A.start + i++];
                countA++;
                countB = 0;
            } else if (tobesorted[A.start + i].value <= tobesorted[B.start + j].value) {
                merged[k++] = tobesorted[A.start + i++];
                countA++;
                countB = 0;
            } else {
                merged[k++] = tobesorted[B.start + j++];
                countB++;
                countA = 0;
            }

            // Galloping
            if (countA >= 7 && j < B.length) {
                int gal = gallop(tobesorted, tobesorted[B.start + j].value, A, i);
                gal = Math.min(gal, A.length - i);
                if (gal > 0) {
                    System.arraycopy(tobesorted, A.start + i, merged, k, gal);
                    i += gal;
                    k += gal;
                    countA = 0;
                    countB = 0;
                }
            } else if (countB >= 7 && i < A.length) {
                int gal = gallop(tobesorted, tobesorted[A.start + i].value, B, j);
                gal = Math.min(gal, B.length - j);
                if (gal > 0) {
                    System.arraycopy(tobesorted, B.start + j, merged, k, gal);
                    j += gal;
                    k += gal;
                    countA = 0;
                    countB = 0;
                }
            }
        }

        System.arraycopy(merged, 0, tobesorted, A.start, A.length + B.length);
        this.runstack[top] = new Run(A.start, A.length + B.length);

        // stack 조정
        for (int s = top + 1; s < this.stacki - 1; s++) {
            this.runstack[s] = this.runstack[s + 1];
        }
        this.runstack[--this.stacki] = null;
    }

    private int gallop(Item[] tobesorted, int target, Run X, int start) {
        int offset = 1;
        int idx = start;

        while (idx + offset < X.length && tobesorted[X.start + idx + offset].value < target)
            offset *= 2;

        int low = idx + (offset / 2);
        int high = Math.min(idx + offset, X.length);

        while (low < high) {
            int mid = (low + high) / 2;
            if (tobesorted[X.start + mid].value < target)
                low = mid + 1;
            else
                high = mid;
        }
        return low - start;
    }

    private void pushruns(Item[] tobesorted) {
        int i = 0;
        while (i < tobesorted.length) {
            int runstart = i;
            int runlen = 1;

            // strictly increasing or decreasing
            boolean asc = (i + 1 < tobesorted.length) && (tobesorted[i].value <= tobesorted[i + 1].value);
            while (i + 1 < tobesorted.length &&
                   (asc ? tobesorted[i].value <= tobesorted[i + 1].value
                        : tobesorted[i].value > tobesorted[i + 1].value)) {
                i++;
                runlen++;
            }

            // reverse if descending
            if (!asc) {
                int l = runstart, r = runstart + runlen - 1;
                while (l < r) {
                    Item temp = tobesorted[l];
                    tobesorted[l++] = tobesorted[r];
                    tobesorted[r--] = temp;
                }
            }

            // pad to minrun
            int end = runstart + runlen;
            if (runlen < this.minrun) {
                end = Math.min(runstart + this.minrun, tobesorted.length);
                insertionRun(tobesorted, runstart, end);
                runlen = end - runstart;
            } else {
                i++;
            }

            this.runstack[this.stacki++] = new Run(runstart, runlen);
            i = end;
            invariant(tobesorted);
        }
    }

    private void insertionRun(Item[] tobesorted, int runstart, int end) {
        for (int i = runstart + 1; i < end; i++) {
            Item target = tobesorted[i];
            int j = i - 1;
            while (j >= runstart && tobesorted[j].value > target.value) {
                tobesorted[j + 1] = tobesorted[j];
                j--;
            }
            tobesorted[j + 1] = target;
        }
    }
}

class Run {
    int start;
    int length;

    Run(int start, int length) {
        this.start = start;
        this.length = length;
    }
}
