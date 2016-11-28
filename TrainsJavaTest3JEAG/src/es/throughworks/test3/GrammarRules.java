package es.throughworks.test3;

import java.util.ArrayList;

import es.throughworks.test3.utils.UtilsTools;

public class GrammarRules {
	
	
	private static final String asignToken = "is";
	private static final String creditToken = "Credit";
	private static final String sintaxErrorMsg = "Sintax Error";
	private static final String duplicateErrorMsg = "Duplicate Var Error";
	
	private static final String I = "I";
	private static final String V = "V";
	private static final String X = "X";
	private static final String L = "L";
	private static final String M = "M";
	private static final String D = "D";
	
	
	private GrammarRomanicRules gRR; 
	
	
	public GrammarRules(String logID){
		gRR = new GrammarRomanicRules(logID);
	}
		
	
	/**
	 * Evaluate format Grammar asignation
	 * ROMAN VALUES --> NUM VALUES --> UNRESOLVED VALUES --> IS ::= NUMBER 
	 *
	 * @param inputData
	 * @param rl
	 * @return
	 * @throws Exception
	 */
	
	
	public LexicalRules asignGrammarRules(String inputData, LexicalRules rl) throws Exception{
		
		String splitData[] = inputData.split("\\s");
		ArrayList<String> varUnresolved = new ArrayList<String>();
		ArrayList<String> varResolvedRom = new ArrayList<String>();
		ArrayList<String> varResolverNum = new ArrayList<String>();
		
		rl = evaluateSintaxAsignRules(splitData,splitData.length,0,varUnresolved,varResolvedRom,varResolverNum,false, false, false, false, false, rl);
		
		varUnresolved.clear();
		varResolvedRom.clear();
		varResolverNum.clear();
		
		return rl;
	}
	
	/**
	 * Calculate value new lexical variable in Asignation
	 * @param data
	 * @param varResolvedRom
	 * @param varResolverNum
	 * @return
	 */
	
	public String CalculateResponseAsign(String data, ArrayList<String> varResolvedRom,  ArrayList<String> varResolvedNum){
		String result = "";
		
		
		if ((varResolvedRom.size()!=0) ||  (varResolvedNum.size()!=0)){
			
			//resolve Roman Data From Code Vector
			long romanDataFromCodeVector = 0; 
		    if (varResolvedRom.size() > 0){romanDataFromCodeVector = gRR.getNumberFromCodeVector(varResolvedRom);}
		    else{romanDataFromCodeVector = 0;}
		    
		    //resolve arabic Data From Code Vector
			long arabicDataFromCodeVector = 0;
			if (varResolvedNum.size() == 0){arabicDataFromCodeVector = 0;}
			else{
				for(int i=0;i<varResolvedNum.size();i++){arabicDataFromCodeVector += UtilsTools.varLong(varResolvedNum.get(i));}
			}
			
			float dataFloat= UtilsTools.varFloat(data);
			romanDataFromCodeVector = romanDataFromCodeVector + arabicDataFromCodeVector;
			
			//get final value of asignation
			if (romanDataFromCodeVector == 0){dataFloat = 0;}
			else{dataFloat = dataFloat/(float)romanDataFromCodeVector;}
			
			result = String.valueOf(dataFloat);
			
			
		}else{
			
			result = data;
		}
		
		return result;
	}
	
	
	
	/**
	 * Evaluate Sintax of a sentence of asignation
	 * <STRUCTURE> ROMAN NUMBERS/NUM NUMBERS -> NUM NUMBERS/CONSTANTS -> PARAMETER (only one)-> "IS" ::= VALUE 
	 * @param splitData
	 * @param sizeData
	 * @param indexSplit
	 * @param varUnresolved
	 * @param varResolvedRom
	 * @param varResolverNum
	 * @param finishRom
	 * @param finishNum
	 * @param finishUnres
	 * @param finishIS
	 * @param finishNumValue
	 * @param rl
	 * @return
	 */
	
	public LexicalRules evaluateSintaxAsignRules(
			String splitData[],
			int sizeData,
			int indexSplit,
			ArrayList<String> varUnresolved,
			ArrayList<String> varResolvedRom,
			ArrayList<String> varResolverNum,
			boolean finishRom,
			boolean finishNum,
			boolean finishUnres,
			boolean finishIS,
			boolean finishNumValue,
			LexicalRules rl) throws UnderstandableException{
		
		if (indexSplit >= sizeData){
			return rl;
		}else{
			String token = splitData[indexSplit];
			String data = rl.getSymbol(token);
			
			//IS A ROMAN VARIABLE
			if (I.equals(data) || V.equals(data) || X.equals(data) || D.equals(data) || L.equals(data) || M.equals(data)){
				
				if ((!finishRom) && (!finishNum) && (!finishUnres) && (!finishIS) && (!finishNumValue)){
					varResolvedRom.add(data);
					return evaluateSintaxAsignRules(splitData,splitData.length,indexSplit+1,varUnresolved,varResolvedRom,varResolverNum,finishRom, finishNum, finishUnres, finishIS, finishNumValue, rl);
				}else{
					//SINTAX NOT ACCEPTED
					throw new UnderstandableException(sintaxErrorMsg);
					
				}
			}
			
			//IS VARIABLE NOT REGISTERED
			else if (data.equalsIgnoreCase("")){
					
				if (!finishIS){
					//NUMERIC CONSTANT
					if (UtilsTools.IsInteger(token)){
						
						varResolverNum.add(token);
						return evaluateSintaxAsignRules(splitData,splitData.length,indexSplit+1,varUnresolved,varResolvedRom,varResolverNum,finishRom, finishNum, finishUnres, finishIS, finishNumValue, rl);
					
					//ROMAN CONSTANT
					}else if (UtilsTools.IsRomanDigit(token)){
						
						varResolverNum.add(String.valueOf(UtilsTools.getNumberFromString(token)));
						return evaluateSintaxAsignRules(splitData,splitData.length,indexSplit+1,varUnresolved,varResolvedRom,varResolverNum,finishRom, finishNum, finishUnres, finishIS, finishNumValue, rl);
					
					}else if ((varUnresolved.size()==0) && (!finishUnres) &&(!token.equalsIgnoreCase(asignToken))){
							varUnresolved.add(token);
							finishRom = true;
							finishNum = true;
							finishUnres = true;
							finishIS = false;
							finishNumValue = false;
						
							return evaluateSintaxAsignRules(splitData,splitData.length,indexSplit+1,varUnresolved,varResolvedRom,varResolverNum,finishRom, finishNum, finishUnres, finishIS, finishNumValue, rl);
						
					}else{
						//IS TOKEN
						if (token.equalsIgnoreCase(asignToken)){
							if (!finishIS){
								finishIS = true;
								return evaluateSintaxAsignRules(splitData,splitData.length,indexSplit+1,varUnresolved,varResolvedRom,varResolverNum,finishRom, finishNum, finishUnres, finishIS, finishNumValue, rl);
							}else{
								throw new UnderstandableException(sintaxErrorMsg);
							}
						}else{
							throw new UnderstandableException(sintaxErrorMsg);
						}
					}
				}else{
					//IS CONSTANT
					if ((UtilsTools.IsInteger(token) || UtilsTools.IsFloat(token) ||UtilsTools.IsRomanDigit(token)) && (varUnresolved.size() == 1)){
						String tokParam = varUnresolved.get(0);
						finishNumValue = false;
						String tokValue = CalculateResponseAsign(token, varResolvedRom, varResolverNum);
						rl.setNewSymbol(tokParam, tokValue);
						return rl;
					}else{
						throw new UnderstandableException(sintaxErrorMsg);
					}
				}
			}
			
			//IS A NUMBER TOKEN
			else if (!I.equals(data) && !V.equals(data) && !D.equals(data) && !X.equals(data) && !L.equals(data) && !M.equals(data)){
				
				if ((!finishRom) && (!finishNum) && (!finishUnres) && (!finishIS) && (!finishNumValue)){
					varResolverNum.add(data);
					finishRom = true;
					return evaluateSintaxAsignRules(splitData,splitData.length,indexSplit+1,varUnresolved,varResolvedRom,varResolverNum,finishRom, finishNum, finishUnres, finishIS, finishNumValue, rl);
				}else{
					throw new UnderstandableException(sintaxErrorMsg);
				}
			}		
			
			//OTHER
			else{
				throw new UnderstandableException(sintaxErrorMsg);
			}
		}
		
	}
	
	
	/**
	 * Evaluate sintax of a Query
	 * <STRUCTURE> ROMAN VALUES/NUM VALUES --> NUM VALUES/CONSTANTS
	 * @param inputData
	 * @param rl
	 * @return
	 * @throws Exception
	 */
	
	public float queryGrammarRules(String inputData, LexicalRules rl) throws Exception{
		String splitData[] = inputData.split("\\s");
		ArrayList<String> varResolvedRom = new ArrayList<String>();
		ArrayList<String> varResolvedNum = new ArrayList<String>();
		float value = 0;
		
		value = evaluateSintaxQueryRules(splitData, splitData.length, 0, varResolvedRom, varResolvedNum, false, false, rl);
		
		varResolvedRom.clear();
		varResolvedNum.clear();
		
		return value;
		
	}
	
	/**
	 * Evaluate part of sintax of a Query
	 * <STRUCTURE> ROMAN VALUES/NUM VALUES --> NUM VALUES/CONSTANTS
	 * @param splitData
	 * @param size
	 * @param index
	 * @param varResolvedRom
	 * @param varResolvedNum
	 * @param isRoman
	 * @param isNumber
	 * @param rL
	 * @return
	 */
	
	public float evaluateSintaxQueryRules(
			String splitData[], 
			int size, 
			int index, 
			ArrayList<String> varResolvedRom, 
			ArrayList<String> varResolvedNum, 
			boolean isRoman, 
			boolean isNumber,
			LexicalRules rL) throws UnderstandableException{
		int value = 0;
		
		if (index >= size){
			
			return CalculateResponseQuery(varResolvedRom, varResolvedNum);
			
		}else{
			
			String token = splitData[index];
			String data = rL.getSymbol(token);
			

			//IS A ROMAN VARIABLE
			if (I.equals(data) || V.equals(data) || X.equals(data) || D.equals(data) || L.equals(data) || M.equals(data)){
				if (!isRoman){
					varResolvedRom.add(data);
				}
				
				return evaluateSintaxQueryRules(splitData,size,index+1,varResolvedRom,varResolvedNum,isRoman,isNumber,rL);
			
			//IS A INTEGER VARIABLE
			}else if (UtilsTools.IsFloat(data)){
				
				isRoman = true;
				varResolvedNum.add(data);
				return evaluateSintaxQueryRules(splitData,size,index+1,varResolvedRom, varResolvedNum, isRoman, isNumber, rL);
			
			//IS A CONSTANT (OR A UNKNOWN TOKEN)
			}else{
				
				if (UtilsTools.IsInteger(token) || UtilsTools.IsFloat(token)){
					varResolvedNum.add(token);
				}else if (UtilsTools.IsRomanDigit(token)){
					varResolvedNum.add(String.valueOf(UtilsTools.getNumberFromString(token)));
				}
				
				return evaluateSintaxQueryRules(splitData,size,index+1,varResolvedRom, varResolvedNum, isRoman, isNumber, rL);
			}
		}
	}
	
	/**
	 * Calculate Value tokens got during parsing query sentence
	 * @param varResolvedRom
	 * @param varResolvedNum
	 * @return
	 */
	public float CalculateResponseQuery(ArrayList<String> varResolvedRom, ArrayList<String> varResolvedNum){
		
		float result = 0;
		float num_Rom = 0;
		float num_Arab = 0;
		
		if (varResolvedRom.size() == 0){num_Rom = 1;}
		else{
			num_Rom = gRR.getNumberFromCodeVector(varResolvedRom);
		}
		
		if (varResolvedNum.size()==0){num_Arab = 1;}
		else{
			for(int i=0; i<varResolvedNum.size(); i++){
				num_Arab += UtilsTools.varFloat(varResolvedNum.get(i));
			}
		}
		
		result = num_Rom * num_Arab;
		return result;
	}

}
