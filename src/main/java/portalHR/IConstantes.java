package portalHR;

import java.time.Duration;
import java.util.Properties;

import static utils.toolbox.initProp;

public interface IConstantes {

	 // Définition des variables de connexion
    Properties prop = initProp("src/main/resources/data/variablesGlobales.properties");
    String NAVIGATEUR = prop.getProperty("NAVIGATEUR");
    String URL = prop.getProperty("URL_PORTAL");
    String USER = prop.getProperty("USER");
    String PWD = prop.getProperty("PWD");
    Boolean MAXIMIZE_DRIVER = Boolean.parseBoolean(prop.getProperty("MAXIMIZE_DRIVER"));
    Integer IMP_WAIT = Integer.parseInt(prop.getProperty("IMP_WAIT"));
    Duration EXP_WAIT = Duration.ofSeconds(Integer.parseInt(prop.getProperty("EXP_WAIT")));
    String WINDOW_POS = prop.getProperty("WINDOW_POS");
	
	
}
