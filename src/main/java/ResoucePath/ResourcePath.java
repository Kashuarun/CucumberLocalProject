/*
 * @author ArunKumar
 * 
 */
package ResoucePath;

import java.io.File;


import TestBase.TestBase;

public class ResourcePath extends TestBase{
	
	public static final String USER_DIR=System.getProperty("user.dir")+File.separator;
	
	//Log4j properties file path
	public static final String LOG4J_PROPERTIES=USER_DIR+"src\\main\\Log4J\\Log4j.properties";
	
	//Config.properties file path
	public static final String CONFIG_PROPERTIES=USER_DIR+"\\src\\main\\Config.Properties"+File.separator;
	
	//General Utilities properties file path
	public static final String GENERAL_UTILITIES=CONFIG_PROPERTIES+"GeneralUitilites.properties";
	
	
}
