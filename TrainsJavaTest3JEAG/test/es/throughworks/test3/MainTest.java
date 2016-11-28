package es.throughworks.test3;

import java.io.File;

import org.junit.Test;
import static org.junit.Assert.assertEquals;

public class MainTest {
	
	@Test
	public void testRunFileFolderExample(){
		try{
			Main main = new Main();
			int valor = main.Run("/finput", ".txt");
			assertEquals("res 0", 0, valor);
			
		}catch(Exception e){
			e.printStackTrace();
		}
		
	}
	
	
	@Test
	public void testRunFileFolderMalformedExample(){
		try{
			Main main = new Main();
			int valor = main.Run("/fdummy", ".txt");
			assertEquals("res -1", -1, valor);
			
		}catch(Exception e){
			e.printStackTrace();
		}
		
	}
	

}
