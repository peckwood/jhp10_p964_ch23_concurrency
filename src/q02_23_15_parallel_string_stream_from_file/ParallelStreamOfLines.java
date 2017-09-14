package q02_23_15_parallel_string_stream_from_file;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.time.Duration;
import java.time.Instant;
import java.util.Map;
import java.util.TreeMap;
import java.util.regex.Pattern;
import java.util.stream.Collectors;

public class ParallelStreamOfLines {
	public static void main(String[] args) throws IOException
	{
		// Regex that matches one or more consecutive whitespace characters
		Pattern pattern = Pattern.compile("\\s+"); 
		
		// count occurrences of each word in a Stream<String> sorted by word
		Map<String, Long> wordCounts = 
				Files.lines(Paths.get("src/q1_23_15_parallel_string_stream_from_file/Chapter2Paragraph.txt"))
				.map(line -> line.replaceAll("(?!')\\p{P}", ""))
				.flatMap(line -> pattern.splitAsStream(line))
				.collect(Collectors.groupingBy(String::toLowerCase,
						TreeMap::new, Collectors.counting()));

		Instant startNormal = Instant.now();
		// display the words grouped by starting letter
		wordCounts.entrySet()
		.stream()
		.collect(
				Collectors.groupingBy(entry -> entry.getKey().charAt(0), 
						TreeMap::new, Collectors.toList()))
		.forEach((letter, wordList) -> 
		{ 
			System.out.printf("%n%C%n", letter);
			wordList.stream().forEach(word -> System.out.printf(
					"%13s: %d%n", word.getKey(), word.getValue()));
		});
		Instant endNormal = Instant.now();
		
		Instant startParallel = Instant.now();
		// display the words grouped by starting letter
		wordCounts.entrySet()
		.stream()
		.collect(
				Collectors.groupingBy(entry -> entry.getKey().charAt(0), 
						TreeMap::new, Collectors.toList()))
		.forEach((letter, wordList) -> 
		{ 
			System.out.printf("%n%C%n", letter);
			wordList.stream().forEach(word -> System.out.printf(
					"%13s: %d%n", word.getKey(), word.getValue()));
		});
		Instant endParallel = Instant.now();
		
		Duration normalTimespent = Duration.between(startNormal, endNormal);
		System.out.println("normal time spent: "+ normalTimespent.toMillis());
		Duration parallelTimespent = Duration.between(startParallel, endParallel);
		System.out.println("parallel time spent: "+ parallelTimespent.toMillis());
		
		
	}
}
