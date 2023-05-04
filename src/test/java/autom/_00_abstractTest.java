package autom;

import org.junit.jupiter.api.*;
import org.openqa.selenium.*;
import org.openqa.selenium.interactions.Actions;
import org.openqa.selenium.support.ui.WebDriverWait;

import portalHR.IConstantes;
import portalHR.connexionPage;
import utils.logger;

import static utils.toolbox.*;

public class _00_abstractTest extends logger implements IConstantes {

    /*
    ********* INITIALISATION DES PARAMETRES ************
    */

    // Initiate variables
    public WebDriver driver;
    public WebDriverWait wait;
    public Actions actions;

    // Path of browser and connection data
    //String pathConnectData = "src/main/resources/data/variablesGlobales.properties";

    /*
     *********** DEFINE ACTIONS THAT TAKE PLACE BEFORE TESTING ***********
    */
    @BeforeEach
    public void startUp() throws Throwable {
        LOGGER.info("******************************* STARTUP *******************************");
        LOGGER.info("Lancement du navigateur " + NAVIGATEUR);
        driver = openBrowser(NAVIGATEUR, MAXIMIZE_DRIVER, IMP_WAIT, WINDOW_POS, URL);
        LOGGER.info("Mise en place d'un wait explicit de " + EXP_WAIT + " secondes");
        wait = new WebDriverWait(driver, EXP_WAIT);
        LOGGER.info("Instanciation de la classe Actions");
        actions = new Actions(driver);
        LOGGER.info("Instanciation de la page de connexion");
        connexionPage pageConnexion = new connexionPage(driver);
        LOGGER.info("Connexion à l'application");
        connectPortal(wait, pageConnexion.connexionChampLogin, USER, pageConnexion.connexionChampMdp, PWD,
                pageConnexion.connexionBoutonConnexion);

    }

    /*
     *********** DEFINE ACTIONS THAT TAKE PLACE AFTER TESTING ***********
    */
    @AfterEach
    public void tearDown() throws Exception {

        LOGGER.info("******************************* TEARDOWN *******************************");
        LOGGER.info("Capture d'écran de la page web");
        takeSnapShot(driver, "test_end.png");
        LOGGER.info("Fermeture du driver");
        driver.quit();

    }
}