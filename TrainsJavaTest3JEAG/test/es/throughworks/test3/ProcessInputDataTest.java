package es.throughworks.test3;


import org.junit.Test;
import static org.junit.Assert.assertEquals;

import java.io.File;

public class ProcessInputDataTest {
	
	@Test
	public void testProcessInputDataTest(){
		try{
			
			ProcessInputData pID = new ProcessInputData("TEST3","/foutput/resultsTest.txt");
			pID.openOutputFile();
			
			String inputFilePath =  (new File("")).getAbsolutePath();
			inputFilePath = inputFilePath.concat("/finput/inputData.txt");
			
			int valor  = pID.processInputFile(new File(inputFilePath));
			pID.closeOutputFile();
			
			assertEquals("res 0", 0, valor);	
		}catch(Exception e){
			e.printStackTrace();
		}
		
	}
	
	@Test
	public void testProcessInputDataTestInputFileMalformed(){
		try{
			
			ProcessInputData pID = new ProcessInputData("TEST3","/foutput/resultsTest.txt");
			pID.openOutputFile();
			
			String inputFilePath =  (new File("")).getAbsolutePath();
			inputFilePath = inputFilePath.concat("/finput/dummy.txt");
			
			int valor  = pID.processInputFile(new File(inputFilePath));
			pID.closeOutputFile();
			
			assertEquals("res -1", -1, valor);	
		}catch(Exception e){
			e.printStackTrace();
		}
		
	}
	
	
	

}
