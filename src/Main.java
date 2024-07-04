import java.util.Iterator;
import java.util.Scanner;

/**
 * A classe Main contém o método principal para a execução do programa.
 */

public class Main {

	public static void main (String [] args) throws Exception { 
		Scanner sc = new Scanner(System.in);
		BestFirstIDA s = new BestFirstIDA();
		int number = sc.nextInt();
		System.gc();
		long start2 = Runtime.getRuntime().totalMemory() - Runtime.getRuntime().freeMemory();
		long startTime = System.currentTimeMillis();
		Iterator<BestFirstIDA.State> it = s.solve(new Operation(number),new Operation(number*3));
		if (it==null) System.out.println("no solution found");
		else {
		while(it.hasNext()) {
			BestFirstIDA.State i = it.next();
			System.out.println(i);
			if (!it.hasNext()) System.out.println("\n" + (int)i.getG());
			}
		}
		sc.close();
		long endTime = System.currentTimeMillis();
		long end2 = Runtime.getRuntime().totalMemory() - Runtime.getRuntime().freeMemory();
		long size = (end2 - start2);
		long executionTime = endTime - startTime;
		//System.out.println("Execution time: " + executionTime + " milliseconds");
		//System.out.println("Memory : " + size/100000 + " mb");
	}
}


