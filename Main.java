import algorithms.IntroSort;
import algorithms.Sortings;
import common.Analresult;
import common.Item;

class Main {
    public static void main(String[] args) {

        System.out.println("\nIntroSort time duration");
        Sortings sorter = new IntroSort();

        int[] sizes = {1000, 3000, 6000, 10000, 30000, 60000, 100000, 300000, 600000, 1000000};
        for (int size : sizes) {
            double totaltime = 0;
            long totalspace = 0;
            int sortsuccess = 0;
            int stability = 0;
            int stabilitycheckable = 0;

            int iter = 10;
            int div = 10;
            if (size == 1000) iter = 16;
            if (size > 100000) {
                iter = 1;
                div = 1;
            }
            for (int i = 0; i < iter; i++) {
                Item[] testarr = InputGenerator.inputgenerate(size);
                Analresult results = sorter.analysis(testarr);

                //System.out.printf("%7s iter %d time = %9.2f ms\n","", i, results.duration);

                // JVM의 JIT 컴파일러로 인해, 첫실행의 warm-up 단계는 반영하지 않음 (for better accuracy)
                if(iter==16 && i<6) continue;

                totaltime += results.duration;
                totalspace += results.usedMemory;
                if(results.isSorted) sortsuccess++;
                if(results.isStable) stability++;
                if(results.stabilitycheckable) stabilitycheckable++;
            }
            System.out.printf("%8d_size : time = %9.2f ms\n", size, totaltime/div);
            System.out.printf("%14s space = %9.2f bytes\n","",(double)totalspace/div);
            System.out.printf("%14s sort success %2d case out of %2d\n","",sortsuccess,div);
            System.out.printf("%10s stability ensure %2d case out of %2d\n","",stability,stabilitycheckable);
        }
        System.out.println();
    }
}