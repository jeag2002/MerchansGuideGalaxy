package es.throughworks.test3;

import java.util.HashMap;

import org.apache.log4j.Logger;

public class LexicalRules {
	
	private HashMap<String,String> data;
	private Logger log;
	
	public LexicalRules(String loggerID){
		data = new HashMap<String,String>();
		log = Logger.getLogger(loggerID);
	}
	
	/**
	 * Set new Symbol
	 * @param key
	 * @param value
	 */
	
	public void setNewSymbol(String key, String value){
		data.put(key, value);
	}
	
	/**
	 * Get new Symbol
	 * @param key
	 * @return
	 */
	
	public String getSymbol(String key){
		if (data.containsKey(key)){
			return data.get(key);
		}else{
			return "";
		}
	}
	
	/**
	 * size of Lexical Rules
	 */
	public int getLexicalParameterSize(){
		return data.size();
	}
	
	
	/**
	 * Print all Lexical tokens got
	 */
	
	public void printLexicalParameter(){
		log.debug("=== PARAMETERS ===");
		
		for (HashMap.Entry<String,String> entry : data.entrySet()) {
			  String key = entry.getKey();
			  String value = entry.getValue();
			  log.debug("KEY::["+key+"] VALUE::["+value+"]");
		}
		log.debug("=== PARAMETERS ===");
	}
	
}
