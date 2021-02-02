import java.io.File;
import java.io.FileNotFoundException;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Scanner;

public class day2 {
	private List<String> strList = new ArrayList<String>();
	private List<Integer> intCodeList = new ArrayList<Integer>();
	private final int INPUT_ONE = 12;
	private final int INPUT_TWO = 2;
	private final int OUTPUT = 19690720;

	public day2 (File file) {
		try {
			Scanner scnr = new Scanner(file);
			// probably some way to make this a lot more concise
			// https://stackoverflow.com/questions/23057549/lambda-expression-to-convert-array-list-of-string-to-array-list-of-integers
			// stringList.stream().map(Integer::parseInt).collect(Collectors.toList());
			while (scnr.hasNext()) {
				strList = Arrays.asList(scnr.nextLine().split(","));
			}
			for (String element : strList) {
				intCodeList.add(Integer.valueOf(element));
			}
			scnr.close();
		} catch (FileNotFoundException e) {
			e.printStackTrace();
		}
	}
	public Integer intCode() {
		return intCode(INPUT_ONE, INPUT_TWO);
	}
	private Integer intCode(int replaceFirst, int replaceSecond) {
		intCodeList.set(1, replaceFirst);
		intCodeList.set(2, replaceSecond);
		List<Integer> updatedList = new ArrayList<>(intCodeList); // copy list 
		int i = 0;
		while (i < updatedList.size()) {
			int opCode = updatedList.get(i); // look at first position of every 4 int elements
			switch (opCode) {
			case 99: // something went wrong, so we don't have to look at any more intcodes
				return updatedList.get(0);
			case 1: // add next 2 intcodes together and store value at 4th element's position
				updatedList.set(updatedList.get(i + 3),
						getIntCodeValue((i + 1), updatedList) + getIntCodeValue((i + 2), updatedList));
				break;
			case 2: // multiply next 2 intcodes together and store value at 4th element's position
				updatedList.set(updatedList.get(i + 3),
						getIntCodeValue((i + 1), updatedList) * getIntCodeValue((i + 2), updatedList));
				break;
			}
			i += 4;
		}
		return -1;
	}

	private Integer getIntCodeValue(int position, List<Integer> intcodeList) {
		return intcodeList.get(intcodeList.get(position));
	}
	
	public Integer getNounVerb() {
		for (int noun = 0; noun <= 99; noun++) {
			for (int verb = 0; verb <= 99; verb++) {
				if (intCode(noun,verb) == OUTPUT) {
					return 100 * noun + verb;
				}
			}
		}
		return -1;
	}
	public static void main(String[] args) {
		File file = new File("D:\\AdventOfCode19\\Day2\\src\\day2.txt");
		day2 day2part1 = new day2(file);
		System.out.println("Day2Part1 Answer: " + day2part1.intCode());
		day2 day2part2 = new day2(file);
		System.out.println("Day2Part2 Answer: " + day2part2.getNounVerb());
	}
}
