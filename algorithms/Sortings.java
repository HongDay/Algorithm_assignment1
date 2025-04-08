package algorithms;

import common.Analresult;
import common.Item;

public abstract class Sortings {
    public Analresult analysis(Item[] tobesorted){
        boolean issorted = true;
        boolean isstable = true;
        boolean stabilitycheckable = false;
        Runtime runtime = Runtime.getRuntime();
        runtime.gc();

        long beforebyte = runtime.totalMemory()-runtime.freeMemory();
        long startTime = System.nanoTime();

        Item[] sortedarr = getsorted(tobesorted);

        long endTime = System.nanoTime();
        long afterbyte = runtime.totalMemory()-runtime.freeMemory();

        //System.out.printf("%7s time = %9.2f ms\n","", (endTime-startTime) / 1000000.0);

        for (int i = 1; i < sortedarr.length - 1; i++) {
            if (sortedarr[i].value < sortedarr[i-1].value) issorted=false;
            if (sortedarr[i].value == sortedarr[i-1].value) {
                stabilitycheckable = true;
                if (sortedarr[i].originalIndex < sortedarr[i-1].originalIndex) isstable=false;
            }
        }
        long usedmem = afterbyte-beforebyte; // byte
        double duration = (endTime-startTime) / 1000000.0; // ms

        //System.out.printf("%7s mem = %9.2f ms\n","", (double)usedmem);

        return new Analresult(issorted,isstable&&stabilitycheckable,stabilitycheckable,usedmem,duration);
    }

    public abstract Item[] getsorted(Item[] tobesorted);

}
