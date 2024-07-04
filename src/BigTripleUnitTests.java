import static org.junit.jupiter.api.Assertions.*;

import java.io.PrintWriter;
import java.io.StringWriter;
import java.util.Iterator;

import org.junit.jupiter.api.Test;

class BigTripleUnitTests {

	 @Test
	 public void testSmallPositive() {
        // Test case 1: current is a small positive number
		 int input = 30;
        Operation actual1 = new Operation(input);
        Operation goal1 = new Operation(input*3);
        
    	BestFirstIDA s = new BestFirstIDA();
        Iterator<BestFirstIDA.State> it = s.solve(actual1,goal1);
        		
        int result = 0;
    	while(it.hasNext()) {
			BestFirstIDA.State i = it.next();
			if (!it.hasNext()) {
				result = (int) i.getG();
			}
		}
	 
        assertEquals(18, result);
	 }
	 
	 @Test
	 public void testSmallNegative() {
        // Test case 2: current is a small negative number
		 int input = -70;
	        Operation actual1 = new Operation(input);
	        Operation goal1 = new Operation(input*3);
	        
	    	BestFirstIDA s = new BestFirstIDA();
	        Iterator<BestFirstIDA.State> it = s.solve(actual1,goal1);
	        		
	        int result = 0;
	    	while(it.hasNext()) {
				BestFirstIDA.State i = it.next();
				if (!it.hasNext()) {
					result = (int) i.getG();
				}
			}
		 
	        assertEquals(24, result);
	 }
	 
	 @Test
	 public void testBigPositive() {
        // Test case 1: current is a big positive number
		 int input = 2200;
	        Operation actual1 = new Operation(input);
	        Operation goal1 = new Operation(input*3);
	        
	    	BestFirstIDA s = new BestFirstIDA();
	        Iterator<BestFirstIDA.State> it = s.solve(actual1,goal1);
	        		
	        int result = 0;
	    	while(it.hasNext()) {
				BestFirstIDA.State i = it.next();
				if (!it.hasNext()) {
					result = (int) i.getG();
				}
			}
		 
	        assertEquals(1103, result);
	 }
	 
	 @Test
	 public void testZero() {
       // Test case 4: current is zero
		 int input = 0;
	        Operation actual1 = new Operation(input);
	        Operation goal1 = new Operation(input*3);
	        
	    	BestFirstIDA s = new BestFirstIDA();
	        Iterator<BestFirstIDA.State> it = s.solve(actual1,goal1);
	        		
	        int result = 0;
	    	while(it.hasNext()) {
				BestFirstIDA.State i = it.next();
				if (!it.hasNext()) {
					result = (int) i.getG();
				}
			}
		 
	        assertEquals(0, result);
	 }
	 
	 @Test
	 public void testRandom1() {
		 int input = 54;
	        Operation actual1 = new Operation(input);
	        Operation goal1 = new Operation(input*3);
	        
	    	BestFirstIDA s = new BestFirstIDA();
	        Iterator<BestFirstIDA.State> it = s.solve(actual1,goal1);
	        		
	        int result = 0;
	    	while(it.hasNext()) {
				BestFirstIDA.State i = it.next();
				if (!it.hasNext()) {
					result = (int) i.getG();
				}
			}
		 
	        assertEquals(30, result);
	 }
	 
	 @Test
	 public void testRandom2() {
        // Test case 2: current is a big negative number
		 int input = -6;
	        Operation actual1 = new Operation(input);
	        Operation goal1 = new Operation(input*3);
	        
	    	BestFirstIDA s = new BestFirstIDA();
	        Iterator<BestFirstIDA.State> it = s.solve(actual1,goal1);
	        		
	        int result = 0;
	    	while(it.hasNext()) {
				BestFirstIDA.State i = it.next();
				if (!it.hasNext()) {
					result = (int) i.getG();
				}
			}
		 
	        assertEquals(8, result);
	 }
	 
	 @Test
	 public void testRandom3() {
        // Test case 2: current is a big negative number
		 int input = -2000;
	        Operation actual1 = new Operation(input);
	        Operation goal1 = new Operation(input*3);
	        
	    	BestFirstIDA s = new BestFirstIDA();
	        Iterator<BestFirstIDA.State> it = s.solve(actual1,goal1);
	        		
	        int result = 0;
	    	while(it.hasNext()) {
				BestFirstIDA.State i = it.next();
				if (!it.hasNext()) {
					result = (int) i.getG();
				}
			}
		 
	        assertEquals(506, result);
	 }
	 
	 @Test
	 public void testRandom4() {
        // Test case 2: current is a big negative number
		 int input = 1000;
	        Operation actual1 = new Operation(input);
	        Operation goal1 = new Operation(input*3);
	        
	    	BestFirstIDA s = new BestFirstIDA();
	        Iterator<BestFirstIDA.State> it = s.solve(actual1,goal1);
	        		
	        int result = 0;
	    	while(it.hasNext()) {
				BestFirstIDA.State i = it.next();
				if (!it.hasNext()) {
					result = (int) i.getG();
				}
			}
		 
	        assertEquals(503, result);
	 }
	 
	 @Test
	 public void testRandom5() {
        // Test case 2: current is a big negative number
		 int input = -7;
	        Operation actual1 = new Operation(input);
	        Operation goal1 = new Operation(input*3);
	        
	    	BestFirstIDA s = new BestFirstIDA();
	        Iterator<BestFirstIDA.State> it = s.solve(actual1,goal1);
	        		
	        int result = 0;
	    	while(it.hasNext()) {
				BestFirstIDA.State i = it.next();
				if (!it.hasNext()) {
					result = (int) i.getG();
				}
			}
		 
	        assertEquals(9, result);
	 }
	 
	 @Test
	 public void testRandom6() {
        // Test case 2: current is a big negative number
		 int input = -23;
	        Operation actual1 = new Operation(input);
	        Operation goal1 = new Operation(input*3);
	        
	    	BestFirstIDA s = new BestFirstIDA();
	        Iterator<BestFirstIDA.State> it = s.solve(actual1,goal1);
	        		
	        int result = 0;
	    	while(it.hasNext()) {
				BestFirstIDA.State i = it.next();
				if (!it.hasNext()) {
					result = (int) i.getG();
				}
			}
		 
	        assertEquals(13, result);
	 }
	 
	 @Test
	 public void testBigNegative() {
        // Test case 2: current is a big negative number
		 int input = -3500;
	        Operation actual1 = new Operation(input);
	        Operation goal1 = new Operation(input*3);
	        
	    	BestFirstIDA s = new BestFirstIDA();
	        Iterator<BestFirstIDA.State> it = s.solve(actual1,goal1);
	        		
	        int result = 0;
	    	while(it.hasNext()) {
				BestFirstIDA.State i = it.next();
				if (!it.hasNext()) {
					result = (int) i.getG();
				}
			}
		 
	        assertEquals(881, result);
	 }
	 

}
