package es.throughworks.test3;

import org.junit.Test;
import static org.junit.Assert.assertEquals;

import java.util.ArrayList;

public class GrammarRomanicRulesTest {
	
	@Test
	public void testWellFormedRomanNumberAsArray(){
		try{
			GrammarRomanicRules gRR = new GrammarRomanicRules("TEST3");
			ArrayList<String> Code = new ArrayList<String>();
			Code.add("V");
			Code.add("I");
			Code.add("I");
			Code.add("I");
			long data = gRR.getNumberFromCodeVector(Code);
			assertEquals(8, data, 0);
			
		}catch(Exception e){
			e.printStackTrace();
		}
	}
}
