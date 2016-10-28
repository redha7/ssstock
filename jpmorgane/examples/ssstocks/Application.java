package jpmorgane.examples.ssstocks;

import jpmorgane.examples.ssstockstest.TestCaseStock;

import org.junit.runner.JUnitCore;
import org.junit.runner.Result;
import org.junit.runner.notification.Failure;

/**
 * Application class for testing
 * 
 * @author Redha
 *
 */
public class Application {
	public static void main(String args[]) throws Exception{
		  Result result = JUnitCore.runClasses(TestCaseStock.class);
			
	      for (Failure failure : result.getFailures()) {
	         System.out.println(failure.toString());
	      }
			
	      System.out.println(result.wasSuccessful());
	   }
		
	
}
