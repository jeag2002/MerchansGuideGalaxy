package es.throughworks.test3.utils;

import java.util.regex.Matcher;
import java.util.regex.Pattern;

import es.throughworks.test3.UnderstandableException;

public class UtilsTools {
	
	private static final String I = "I";
	private static final String V = "V";
	private static final String X = "X";
	private static final String L = "L";
	private static final String D = "D";
	private static final String C = "C";
	private static final String M = "M";
	
	/**
	 * String is Integer?
	 * @param Inputdata
	 * @return
	 */
	
	public static boolean IsInteger(String Inputdata){
		boolean res = false;
		try{
			int value = Integer.parseInt(Inputdata);
			res = true;
		}catch(Exception e){
			res = false;
		}
		return res;
	}
	
	/**
	 * String is Float?
	 * @param Inputdata
	 * @return
	 */
	
	
	public static boolean IsFloat(String Inputdata){
		boolean res = false;
		try{
			float value = Float.parseFloat(Inputdata);
			res = true;
		}catch(Exception e){
			res = false;
		}
		return res;
	}
	
	/**
	 * String is Roman Number?
	 * @param Inputdata
	 * @return
	 */
	
	
	public static boolean IsRomanDigit(String Inputdata){
		boolean res = false;
		
		try{
			long value = getNumberFromString(Inputdata);
			res = true;
		}catch(Exception e){
			res = false;
		}
		
		return res;
	}
	
	/**
	 * Roman Number to Arabic Number
	 * @param Inputdata
	 * @return
	 */
	
	
	public static long getNumberFromString(String Code) throws UnderstandableException{
		if (Code.equalsIgnoreCase("")) return 0;
        if (Code.indexOf("M")==0) return 1000 + getNumberFromString(Code.substring(1));
        if (Code.indexOf("CM")==0) return 900 + getNumberFromString(Code.substring(2));
        if (Code.indexOf("D")==0) return 500 + getNumberFromString(Code.substring(1));
        if (Code.indexOf("CD")==0) return 400 + getNumberFromString(Code.substring(2));
        if (Code.indexOf("C")==0) return 100 + getNumberFromString(Code.substring(1));
        if (Code.indexOf("XC")==0) return 90 + getNumberFromString(Code.substring(2));
        if (Code.indexOf("L")==0) return 50 + getNumberFromString(Code.substring(1));
        if (Code.indexOf("XL")==0) return 40 + getNumberFromString(Code.substring(2));
        if (Code.indexOf("X")==0) return 10 + getNumberFromString(Code.substring(1));
        if (Code.indexOf("IX")==0) return 9 + getNumberFromString(Code.substring(2));
        if (Code.indexOf("V")==0) return 5 + getNumberFromString(Code.substring(1));
        if (Code.indexOf("IV")==0) return 4 + getNumberFromString(Code.substring(2));
        if (Code.indexOf("I")==0) return 1 + getNumberFromString(Code.substring(1));
        throw new UnderstandableException("Error");
	}
	
	
	/**
	 * String to int
	 * @param Inputdata
	 * @return
	 */
	
	
	public static int var(String Inputdata){
		
		int res = 0;
		try{
			res = Integer.parseInt(Inputdata);
		}catch(Exception e){
			res = -1;
		}
		
		return res;
	}
	
	/**
	 * String is long
	 * @param Inputdata
	 * @return
	 */
	
	
	public static long varLong(String Inputdata){
		
		long res = 0;
		try{
			res = Long.parseLong(Inputdata);
		}catch(Exception e){
			res = -1;
		}
		
		return res;
	}
	
	/**
	 * String is float?
	 * @param Inputdata
	 * @return
	 */
	
	
	public static float varFloat(String Inputdata){
		float res = 0;
		try{
			res = Float.parseFloat(Inputdata);
		}catch(Exception e){
			res = -1;
		}
		return res;
	}
	
	
	/**
	 * Single Roman Digit to Arabic
	 * @param Inputdata
	 * @return
	 */
	
	
	
	public static long romanToNumberConversion(String valueStr){
		if (valueStr.equalsIgnoreCase(I)){
			return 1;
		}else if(valueStr.equalsIgnoreCase(V)){
			return 4;
		}else if(valueStr.equalsIgnoreCase(X)){
			return 10;
		}else if(valueStr.equalsIgnoreCase(L)){
			return 50;
		}else if(valueStr.equalsIgnoreCase(C)){
			return 100;
		}else if(valueStr.equalsIgnoreCase(D)){
			return 500;
		}else if(valueStr.equalsIgnoreCase(M)){
			return 1000;
		}else{
			return 0;
		}
	}
	

}
