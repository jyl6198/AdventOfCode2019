import java.io.File;
import java.util.HashMap;
import java.util.Map;
import java.util.Scanner;

public class day3 {
	private String[] inputArray1;
	private String[] inputArray2;
	private Map<String, Integer> wireHM = new HashMap<String, Integer>();
	private int manhattanDistance = Integer.MAX_VALUE;
	private int shortestWireDistance = Integer.MAX_VALUE;

	public day3(File file) {
		try {
			Scanner scnr = new Scanner(file);
			inputArray1 = scnr.nextLine().split(",");
			inputArray2 = scnr.nextLine().split(",");
			scnr.close();
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	// return direction coordinates
	private int[] getDirection(char c) {
		switch (c) {
		case 'U':
			return new int[] { 0, 1 };
		case 'D':
			return new int[] { 0, -1 };
		case 'L':
			return new int[] { -1, 0 };
		case 'R':
			return new int[] { 1, 0 };
		}
		return null;
	}

	private String getManhattanDist() {

		// local variables for grid + distance
		int xCoord = 0;
		int yCoord = 0;
		int distance = 0;
		for (int i = 0; i < inputArray1.length; i++) {
			// grab coordinate direction; currently [0] holds ("x"), [1] holds ("y")
			int[] directionCoord = getDirection(inputArray1[i].charAt(0));
			// grab wire path length
			int pathLength = Integer.parseInt(inputArray1[i].substring(1));
			for (int j = 0; j < pathLength; j++) {
				// coordinates to store in HM 
				int xCoordHM = xCoord + directionCoord[0];
				int yCoordHM = yCoord + directionCoord[1];
				wireHM.put(xCoordHM + "," + yCoordHM, ++distance);
				// update x and y coordinates to the x and y coordinates currently stored in HM
				xCoord = xCoordHM;
				yCoord = yCoordHM;
			}
		}
		// reset x & y coordinates and distance to read the second input line
		xCoord = 0;
		yCoord = 0;
		distance = 0;
		// should look the same except now we should check for any intersections
		for (int i = 0; i < inputArray2.length; i++) {
			int[] directionCoord = getDirection(inputArray2[i].charAt(0));
			int pathLength = Integer.parseInt(inputArray2[i].substring(1));
			for (int j = 0; j < pathLength; j++) {
				int xCoordHM = xCoord + directionCoord[0];
				int yCoordHM = yCoord + directionCoord[1];
				distance++;
				xCoord = xCoordHM;
				yCoord = yCoordHM;
				if (wireHM.containsKey(xCoordHM + "," + yCoordHM)) {
					manhattanDistance = Math.min(manhattanDistance, Math.abs(xCoordHM) + Math.abs(yCoordHM));
					shortestWireDistance = Math.min(shortestWireDistance,
							wireHM.get(xCoordHM + "," + yCoordHM) + distance);
				}
			}
		}
		return ("Manhattan Distance " + manhattanDistance + " | Shortest Wire Distance " + shortestWireDistance);
	}

	public static void main(String[] args) {
		File file = new File("D:\\AdventOfCode19\\Day3\\src\\day3.txt");
		day3 day3 = new day3(file);
		System.out.println("Day 3 Answer: " + day3.getManhattanDist());
	}
}
