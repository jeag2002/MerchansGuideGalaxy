package es.throughworks.test3;

import java.util.ArrayList;

import org.apache.log4j.Logger;

import es.throughworks.test3.utils.UtilsTools;

/*
 
The symbols "I", "X", "C", and "M" can be repeated three times in succession, but no more. 
(They may appear four times if the third and fourth are separated by a smaller value, such as XXXIX.) 
"D", "L", and "V" can never be repeated.
"I" can be subtracted from "V" and "X" only. 
"X" can be subtracted from "L" and "C" only. 
"C" can be subtracted from "D" and "M" only. 
"V", "L", and "D" can never be subtracted.

Only one small-value symbol may be subtracted from any large-value symbol.
A number written in Arabic numerals can be broken into digits. 
For example, 1903 is composed of 1, 9, 0, and 3. 
To write the Roman numeral, 
each of the non-zero digits should be treated separately. 
In the above example, 1,000 = M, 900 = CM, and 3 = III. 
Therefore, 1903 = MCMIII.  
 */

public class GrammarRomanicRules {
	
	private static final String I = "I";
	private static final String V = "V";
	private static final String X = "X";
	private static final String L = "L";
	private static final String D = "D";
	private static final String C = "C";
	private static final String M = "M";
	
	private Logger log;

	public GrammarRomanicRules(String logID){
		log = Logger.getLogger(logID);
	}
	
	
	/**
	 * Transform a vector for Roman Digits in one isolate Roman Number; after process it as Arabic Number
	 * @param Code
	 * @return
	 */
	
	public long getNumberFromCodeVector(ArrayList<String> Code){
		long finalValue = 0;
		
		if (Code.size() > 0){
			String dataStr = "";
			for(int i=0; i<Code.size(); i++){
				dataStr = dataStr.concat(Code.get(i));
			}
			try{
				finalValue = UtilsTools.getNumberFromString(dataStr);
			}catch(UnderstandableException e){
				log.warn("Roman Value [".concat(dataStr).concat("] Cannot be processed!"));
				finalValue = 0;
			}
			
			return finalValue;
			
		}else{
			return finalValue;
		}
	
	}
	
	

	
	
	
}
