package es.throughworks.test3;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.PrintStream;
import java.util.Scanner;

import org.apache.log4j.Logger;

public class ProcessInputData {
	
	private Logger log;
	private String outputFilePath;
	private LexicalRules lR;
	private GrammarRules gR;
	
	private FileOutputStream fos;
	private PrintStream printStream;
	
	
	private static final String queryTag_1 = "how much is";
	private static final String queryTag_2 = "how many Credits is";
	
	private static final String queryFinalTag = "?";
	
	private static final String asignTag = "is";
	private static final String unknown = "I have no idea what you are talking about\n";
		
	private static final String orderNotComputable = "Cannot process sentence ";
	
	public ProcessInputData(String loggerID, String _outputFilePath){
		log = Logger.getLogger(loggerID);
		
		outputFilePath =  (new File("")).getAbsolutePath();
		outputFilePath = outputFilePath.concat(_outputFilePath);
		
		lR = new LexicalRules(loggerID);
		gR = new GrammarRules(loggerID);
		
		fos = null;
		printStream = null;
	}
	
	/**
	 * Open output file with the result of the process
	 * @return
	 * @throws Exception
	 */
	
	public int openOutputFile() throws Exception{
		fos = new FileOutputStream(new File(outputFilePath));
		printStream = new PrintStream(fos);
		return 0;
	}
	
	/**
	 * Close output file with the result of the process
	 * @return
	 * @throws IOException
	 */
	
	public int closeOutputFile() throws IOException{
		
		if (printStream != null){
			printStream.close();
		}
		
		if (fos != null){
			fos.close();
		}
		
		return 0;
	}
	
	
	
	
	/**
	 * Processing Sentences in a File
	 * @param iFile
	 * @return
	 * @throws IOException
	 */
	
	public int  processInputFile(File iFile) throws IOException{
		
		int res = 0;
		
		FileInputStream fis = null;
		Scanner scanner = null;
        

		try{
			fis = new FileInputStream(iFile);
			scanner = new Scanner(fis);
			
			while(scanner.hasNextLine()){
					String line = scanner.nextLine();
					//IS A QUERY
					if ((line.indexOf(queryTag_1)!=-1) || (line.indexOf(queryTag_2)!=-1)){
						lR.printLexicalParameter();
						
						  if (line.indexOf(queryFinalTag)!=-1){
							
							  String inputData = "";
							  //HOW MUCH
							  if (line.indexOf(queryTag_1)!=-1){
								  
								  int size = line.indexOf(queryTag_1) + queryTag_1.length();
								  if (size < line.indexOf(queryFinalTag)){
									  inputData = line.substring(size, line.indexOf(queryFinalTag));
								  }
								  
							   //HOW MANY	  
							   }else{
								  
								  int size = line.indexOf(queryTag_2) + queryTag_2.length();
								  if (size < line.indexOf(queryFinalTag)){
									  inputData = line.substring(size, line.indexOf(queryFinalTag));
								  }
								  
							   }
							  
							  //WELL FORMED QUERY?
							  inputData = inputData.trim();
							  if (inputData.equalsIgnoreCase("")){
								  log.warn(orderNotComputable.concat(line));
								  printStream.print(unknown);
							  }else{
								  float valueInputData = gR.queryGrammarRules(inputData, lR);
								  String result = "";
								  
								  if (line.indexOf(queryTag_1)!=-1){ 
									  result = String.format("%s is %d\n", inputData, (int)Math.ceil(valueInputData));
								  }else{
									  result = String.format("%s is %d Credits\n", inputData, (int)Math.ceil(valueInputData));
								  }
								  
								  log.info(result);
								  printStream.print(result);
							  }
							    
				
						}else{
							
							log.warn(orderNotComputable.concat(line));
							printStream.print(unknown);
							
						}
						
						
					//IS AN ASIGNATION
					}else if (line.indexOf(asignTag)!=-1){
						
						try{
							lR = gR.asignGrammarRules(line, lR);
						}catch(UnderstandableException uE){
							log.warn(orderNotComputable.concat(line).concat(" ").concat(uE.getMessage()));
							printStream.print(unknown);
						}
						
					//DON'T KNOW
					}else{
						printStream.print(unknown);
					}
			}
		}catch(Exception e){
			
			log.error(e.getMessage());
			
			res = -1;
			
		}finally{
			
			if (scanner != null){
				scanner.close();
			}
			
			if (fis != null){
				fis.close();
			}
			
			return res;
		}
		
		
	}
	

}
