package common;

public class Analresult{
    public boolean isSorted;
    public boolean isStable;
    public boolean stabilitycheckable;
    public long usedMemory;     // bytes
    public double duration;     // ms

    public Analresult(boolean isSorted, boolean isStable, boolean stabilitycheckable, long usedMemory, double duration) {
        this.isSorted = isSorted;
        this.isStable = isStable;
        this.stabilitycheckable = stabilitycheckable;
        this.usedMemory = usedMemory;
        this.duration = duration;
    }
}
