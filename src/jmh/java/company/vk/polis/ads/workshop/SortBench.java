package company.vk.polis.ads.workshop;

import org.openjdk.jmh.annotations.Benchmark;
import org.openjdk.jmh.annotations.BenchmarkMode;
import org.openjdk.jmh.annotations.Level;
import org.openjdk.jmh.annotations.Mode;
import org.openjdk.jmh.annotations.OutputTimeUnit;
import org.openjdk.jmh.annotations.Param;
import org.openjdk.jmh.annotations.Scope;
import org.openjdk.jmh.annotations.Setup;
import org.openjdk.jmh.annotations.State;
import org.openjdk.jmh.infra.Blackhole;
import org.openjdk.jmh.runner.Runner;
import org.openjdk.jmh.runner.RunnerException;
import org.openjdk.jmh.runner.options.Options;
import org.openjdk.jmh.runner.options.OptionsBuilder;

import java.util.concurrent.ThreadLocalRandom;
import java.util.concurrent.TimeUnit;
import java.util.stream.IntStream;

@BenchmarkMode(Mode.AverageTime)
@OutputTimeUnit(TimeUnit.MILLISECONDS)
@State(Scope.Thread)
public class SortBench {
    @Param({"100", "1000", "10000", "100000", "1000000"})
    private int length;
    private Integer[] array;

    @Setup(value = Level.Invocation)
    public void setUpInvocation() {
        array = IntStream
                .generate(() -> ThreadLocalRandom.current().nextInt())
                .limit(length)
                .boxed()
                .toArray(Integer[]::new);
    }

    @Benchmark
    public void heapSort(Blackhole bh){
        bh.consume(HeapSort.sort(array));
    }

    @Benchmark
    public void improvedInsertionSort(Blackhole bh){
        bh.consume(ImprovedInsertionSort.sort(array));
    }

    @Benchmark
    public void InsertionSort(Blackhole bh){
        bh.consume(InsertionSort.sort(array));
    }

    @Benchmark
    public void MergeSort(Blackhole bh){
        bh.consume(MergeSort.sort(array));
    }

    @Benchmark
    public void QuickSort(Blackhole bh){
        bh.consume(QuickSort.sort(array));
    }

    public static void main(String[] args) throws RunnerException {
        Options opt = new OptionsBuilder()
                .include(SortBench.class.getSimpleName())
                .forks(1)
                .jvmArgs("-Xms1G", "-Xmx1G")
                .warmupIterations(3)
                .measurementIterations(3)
                .build();

        new Runner(opt).run();
    }
}
