import java.io.File;
import java.io.FileNotFoundException;
import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

public class day1 {
	// Day 1 Part 1
	private static List<Integer> convertDataToList(File file) {
		List<Integer> list = new ArrayList<Integer>();
		try {
			Scanner scnr = new Scanner(file);
			while (scnr.hasNextLine()) {
				Integer data = Integer.parseInt(scnr.nextLine());
				int mass = getFuelReq(data);
				list.add(mass);
			}
			scnr.close();
		} catch (FileNotFoundException e) {
			e.printStackTrace();
		}
		return list;
	}
	private static Integer getFuelReq(int mass) {
		return mass / 3 - 2;
	}
	private static Integer getTotalFuelReq(List<Integer> list) {
		int sum = 0;
		for (int i: list) {
			sum += i;
		}
		return sum;
	}
	// Day 1 Part 2
	private static Integer getTotalFuelReqPartTwo(List<Integer> list) {
		int mass;
		int totalFuel = 0;
		for (int i : list) {
			totalFuel += i;
			mass =  getFuelReq(i);
			while (mass > 0) {
				totalFuel += mass;
				mass = getFuelReq(mass);
			}
		}
		return totalFuel;
	}
	public static void main(String[]args) {
		File file = new File("D:\\AdventOfCode19\\Day1\\src\\day1.txt");
		List<Integer> listOfFuelReq = convertDataToList(file);
		System.out.println("Day1Part1 Answer: " + getTotalFuelReq(listOfFuelReq));
		System.out.println("Day1Part2 Answer: " + getTotalFuelReqPartTwo(listOfFuelReq));
	}
}
