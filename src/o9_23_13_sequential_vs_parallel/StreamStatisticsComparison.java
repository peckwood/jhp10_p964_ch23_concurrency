package o9_23_13_sequential_vs_parallel;

import java.security.SecureRandom;
import java.time.Duration;
import java.time.Instant;
import java.util.Arrays;
import java.util.LongSummaryStatistics;

public class StreamStatisticsComparison {
	public static void main(String[] args) {
		SecureRandom random = new SecureRandom();
		long[] values = random.longs(10_000_000, 1, 1001).toArray();
		Instant serialStart1 = Instant.now();
		System.out.println("Statistics");
		System.out.printf("    count: %,d%n", Arrays.stream(values).count());
		System.out.printf("      sum: %,d%n", Arrays.stream(values).sum());
		System.out.printf("      min: %,d%n", Arrays.stream(values).min().getAsLong());
		System.out.printf("      max: %,d%n", Arrays.stream(values).max().getAsLong());
		System.out.printf("  average: %f%n", Arrays.stream(values).average().getAsDouble());
		Instant serialEnd1 = Instant.now();
		Duration duration1 = Duration.between(serialStart1, serialEnd1);
		System.out.println("time spent: " + duration1.toMillis());
		
		
		Instant serialStart2 = Instant.now();
		displayStatistics(Arrays.stream(values).summaryStatistics());
		Instant serialEnd2 = Instant.now();
		Duration duration2 = Duration.between(serialStart2, serialEnd2);
		System.out.println("time spent: " + duration2.toMillis());
		
		Instant serialStart3 = Instant.now();
		displayStatistics(Arrays.stream(values).parallel().summaryStatistics());
		Instant serialEnd3 = Instant.now();
		Duration duration3 = Duration.between(serialStart3, serialEnd3);
		System.out.println("time spent: " + duration3.toMillis());
	}

	// display's LongSummaryStatistics values
	private static void displayStatistics(LongSummaryStatistics stats) {
		System.out.println("\n Statistics");
		System.out.printf("    count: %,d%n", stats.getCount());
		System.out.printf("      sum: %,d%n", stats.getSum());
		System.out.printf("      min: %,d%n", stats.getMin());
		System.out.printf("      max: %,d%n", stats.getMax());
		System.out.printf("  average: %f%n", stats.getAverage());
	}
}
