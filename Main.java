import algorithms.CombSort;
import algorithms.HeapSort;
import algorithms.IntroSort;
import algorithms.LibrarySort;
import algorithms.MergeSort;
import algorithms.Sortings;
import algorithms.TimSort;
import algorithms.TournamentSort;
import common.Analresult;
import common.Item;
import java.util.function.IntFunction;

class Main {
    public static void main(String[] args) {

        Sortings[] sorters = {
            //new QuickSort(),
            new HeapSort(),
            new MergeSort(),
            new CombSort(),
            new IntroSort(),
            new LibrarySort(),
            new TimSort(),
            new TournamentSort()
        };
        for (Sortings sorter : sorters) {
            System.out.println("\n"+sorter.getClass().getSimpleName()+" time duration");

            String[] inputNames = {"Sorted", "Reversed", "Partially Sorted"};
            IntFunction<Item[]>[] inputGenerators = new IntFunction[]{
                InputGenerator::sortedinput,
                InputGenerator::reversedinput,
                InputGenerator::partialinput
            };

            for (int genIdx = 0; genIdx < inputGenerators.length; genIdx++) {
                String inputType = inputNames[genIdx];
                IntFunction<Item[]> generator = inputGenerators[genIdx];

                System.out.println("--- Input Type: " + inputType + " ---");

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
                    // only for the O(n^2) algorithms
                    /* 
                    if (size > 100000) {
                        iter = 1;
                        div = 1;
                    }*/
                    for (int i = 0; i < iter; i++) {
                        Item[] testarr = generator.apply(size);
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
    }
}