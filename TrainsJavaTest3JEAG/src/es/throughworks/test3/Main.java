package es.throughworks.test3;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FilenameFilter;
import java.io.IOException;
import java.io.InputStream;
import java.util.Properties;

import org.apache.log4j.Logger;
import org.apache.log4j.PropertyConfigurator;

public class Main {
	
	private static final String config_file = "\\conf\\config.properties";
	private static final String logFileID = "logFile";
	private static final String logID = "logID";
	private static final String inputDirID = "inputFiles";
	private static final String inputDirExtID = "inputFilesExt";
	private static final String outputDirID = "outputFile";
	
	
	private static final String iFileProcessing = "File processing: ";
	
	private static final String generalErrorMsg = "General Error: ";
	private static final String ioErrorMsg = "IO Error: ";
	private static final String iFilesWarnMsg = "Input Files Not Found in: ";
	private static final String notFoundWarnMsg = " NOT FOUND ";
	private static final String isNotADirectoryMsg = " IS NOT A DIRECTORY";
	
	
	private ProcessInputData pIData;
	private Properties prop;
	private Logger log;
	

	public Main() throws Exception{
		ConfigData();
		ConfigLog();
		pIData = new ProcessInputData(prop.getProperty(logID),prop.getProperty(outputDirID));
	}
	
	/**
	 * Configuration of global parameters
	 * @throws IOException
	 */
	
	public void ConfigData() throws IOException{
		
		prop = new Properties();
		String filepath = (new File("")).getAbsolutePath();
		filepath = filepath.concat(config_file);
		
		InputStream inputStream = new FileInputStream(filepath);
		if (inputStream != null){
			prop.load(inputStream);
		}else{
			throw new FileNotFoundException(filepath.concat(notFoundWarnMsg));
		}
		inputStream.close();
		inputStream = null;
	}
	
	/**
	 * Configuration of logging engine 
	 * @throws IOException
	 */
	
	public void ConfigLog() throws IOException{
		String data = prop.getProperty(logFileID);
		String filepath = (new File("")).getAbsolutePath();
		filepath = filepath.concat(data);
		
		if (data != null){
			InputStream inputStream = new FileInputStream(new File(filepath));
			PropertyConfigurator.configure(inputStream);
			log = Logger.getLogger(prop.getProperty(logID));
			inputStream.close();
			inputStream = null;
		}else{
			throw new FileNotFoundException(filepath.concat(notFoundWarnMsg));
		}
	}
	
	public String getInputDir(){return prop.getProperty(inputDirID);}
	public String getInputDirExt(){return prop.getProperty(inputDirExtID);}
	
	/**
	 * Evaluation of interstellar goodies files MAIN RUN METHOD
	 * @param inputDir		Input directory
	 * @param inputDirExt	Ext files accepted
	 * @return 				0 if OK -1 else
	 */
	
	public int Run(String inputDir, String inputDirExt){
		
		int value = 0;
		
		try{
			log.info(" ======= INTERSTELLAR GOODIES PROFIT INI ======== ");
			
			String filepath = (new File("")).getAbsolutePath();
		
			String dirInput = filepath.concat(inputDir);
			File dirIn = new File(dirInput);
			
			if (dirIn.exists()){
				if (dirIn.isDirectory()){
					
					File[] files = dirIn.listFiles(new FilenameFilter() {
						public boolean accept(File dir, String name) {
							return name.toLowerCase().endsWith(inputDirExt);
						}
					});
					
					if (files.length > 0){
						
						pIData.openOutputFile();
						
						for(int i=0; i<files.length; i++){
							log.debug(iFileProcessing.concat(files[i].getAbsolutePath()));
							pIData.processInputFile(files[i]);
						}
						
						pIData.closeOutputFile();
						
					}else{
						log.warn(iFilesWarnMsg.concat(inputDir));
					}
					
				}else{
					throw new FileNotFoundException(dirInput.concat(isNotADirectoryMsg));
				}
			}else{
				throw new FileNotFoundException(dirInput.concat(notFoundWarnMsg));
			}
			
			value = 0;
		}catch(IOException e){
			log.error(ioErrorMsg.concat(e.getMessage()));
			value = -1;
		}catch(Exception e1){
			log.error(generalErrorMsg.concat(e1.getMessage()));
			value = -1;
		}finally{
			log.info(" ======= INTERSTELLAR GOODIES PROFIT END ======== ");
			return value;
		}
		
	}
	
	/**
	 * MAIN
	 * @param args
	 * @throws Exception
	 */
	
	public static void main (String [ ] args) throws Exception {
		
		Main main = new Main();
		int return_value = main.Run(main.getInputDir(),main.getInputDirExt());
		System.exit(return_value);
	}

}
