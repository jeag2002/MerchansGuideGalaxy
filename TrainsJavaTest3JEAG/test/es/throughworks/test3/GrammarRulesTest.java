package es.throughworks.test3;

import org.junit.Test;
import static org.junit.Assert.assertEquals;

public class GrammarRulesTest {
	
	@Test
	public void testGrammarRulesAsignSingleRoman(){
		try{
			GrammarRules gR = new GrammarRules("TEST3");
			LexicalRules lR = new LexicalRules("TEST3");
			
			lR = gR.asignGrammarRules("ponga is I", lR);
			
			assertEquals("size of Lexical Rule ", 1, lR.getLexicalParameterSize());
			assertEquals("value of ponga ", "I", lR.getSymbol("ponga"));
			
		}catch(Exception e){
			e.printStackTrace();
		}
	}
	
	@Test
	public void testGrammarRulesAsignSingleNum(){
		
		try{
			
			GrammarRules gR = new GrammarRules("TEST3");
			LexicalRules lR = new LexicalRules("TEST3");
			
			lR = gR.asignGrammarRules("ponga is 99.0", lR);
			
			assertEquals("size of Lexical Rule ", 1, lR.getLexicalParameterSize());
			assertEquals("value of ponga ", "99.0", lR.getSymbol("ponga"));
			
			
		}catch(Exception e){
			e.printStackTrace();
		}
	}
	
	@Test
	public void testGrammarMalformedAsignSentence_1(){
		try{
			GrammarRules gR = new GrammarRules("TEST3");
			LexicalRules lR = new LexicalRules("TEST3");
			lR = gR.asignGrammarRules("ponga ponga Silver is 24", lR);
		}catch(Exception e){
			assertEquals("Exception malformedAsignSentence ", "Sintax Error", e.getMessage());
		}
	}
	
	
	@Test
	public void testGrammarMalformedAsignSentence_2(){
		try{
			GrammarRules gR = new GrammarRules("TEST3");
			LexicalRules lR = new LexicalRules("TEST3");
			lR = gR.asignGrammarRules("ponga is I", lR);
			lR = gR.asignGrammarRules("ponga Silver ponga is 24", lR);
		}catch(Exception e){
			assertEquals("Exception malformedAsignSentence ", "Sintax Error", e.getMessage());
		}
	}
	
	

	@Test
	public void testGrammarMalformedAsignSentence_3(){
		try{
			GrammarRules gR = new GrammarRules("TEST3");
			LexicalRules lR = new LexicalRules("TEST3");
			lR = gR.asignGrammarRules("is 24", lR);
		}catch(Exception e){
			assertEquals("Exception malformedAsignSentence ", "Sintax Error", e.getMessage());
		}
	}
	
	@Test
	public void testGrammarMalformedAsignSentence_4(){
		try{
			GrammarRules gR = new GrammarRules("TEST3");
			LexicalRules lR = new LexicalRules("TEST3");
			lR = gR.asignGrammarRules("ponga is I", lR);
			lR = gR.asignGrammarRules("ponga ponga Silver 24 is", lR);
		}catch(Exception e){
			assertEquals("Exception malformedAsignSentence ", "Sintax Error", e.getMessage());
		}
	}
	
	
	@Test
	public void testGrammarMalformedAsignSentence_5(){
		try{
			GrammarRules gR = new GrammarRules("TEST3");
			LexicalRules lR = new LexicalRules("TEST3");
			lR = gR.asignGrammarRules("ponga is I", lR);
			lR = gR.asignGrammarRules("ponga is I", lR);
		}catch(Exception e){
			assertEquals("Exception malformedAsignSentence ", "Sintax Error", e.getMessage());
		}
	}
	
	
	
	
	@Test
	public void testGrammarRulesAsignRomanAndNumeric(){
		
		try{
			GrammarRules gR = new GrammarRules("TEST3");
			LexicalRules lR = new LexicalRules("TEST3");
			
			lR = gR.asignGrammarRules("ponga is I", lR);
			
			assertEquals("size of Lexical Rule ", 1, lR.getLexicalParameterSize());
			assertEquals("value of ponga ", "I", lR.getSymbol("ponga"));
			
			lR = gR.asignGrammarRules("ponga ponga Silver is 24", lR);
			assertEquals("size of Lexical Rule ", 2, lR.getLexicalParameterSize());
			assertEquals("value of Silver ", "12.0", lR.getSymbol("Silver"));
			
			
		}catch(Exception e){
			e.printStackTrace();
		}
	}
	
	@Test
	public void testGrammarRulesQueryRoman(){
		
		try{
			GrammarRules gR = new GrammarRules("TEST3");
			LexicalRules lR = new LexicalRules("TEST3");
			
			lR = gR.asignGrammarRules("ponga is I", lR);
			
			assertEquals("size of Lexical Rule ", 1, lR.getLexicalParameterSize());
			assertEquals("value of ponga ", "I", lR.getSymbol("ponga"));
			
			lR = gR.asignGrammarRules("bunky is V", lR);
			
			assertEquals("size of Lexical Rule ", 2, lR.getLexicalParameterSize());
			assertEquals("value of bunky ", "V", lR.getSymbol("bunky"));
			
			float data = gR.queryGrammarRules("bunky ponga ponga ponga", lR);
			assertEquals(8.0f, data, 2);
			
			
		}catch(Exception e){
			e.printStackTrace();
		}
	}
	
	@Test
	public void testGrammarRulesQueryRomanAndValue(){
		
		try{
			GrammarRules gR = new GrammarRules("TEST3");
			LexicalRules lR = new LexicalRules("TEST3");
			
			lR = gR.asignGrammarRules("glob is I", lR);
			lR = gR.asignGrammarRules("prok is V", lR);
			lR = gR.asignGrammarRules("glob glob Iron is 24 Credits", lR);
			
			float data = gR.queryGrammarRules("glob prok Iron", lR);
			assertEquals(48.0f, data, 2);
			
		}catch(Exception e){
			e.printStackTrace();
		}
			
	}


}
