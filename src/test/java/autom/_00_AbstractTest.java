package autom;

import org.junit.jupiter.api.*;
import org.openqa.selenium.*;
import org.openqa.selenium.interactions.Actions;
import org.openqa.selenium.support.ui.WebDriverWait;

import portalHR.IConstantes;
import portalHR.ConnexionPage;
import utils.Logging;

import static utils.Toolbox.*;

public class _00_AbstractTest extends Logging implements IConstantes {

    /*
    ********* INITIALIZE PARAMETERS ************
    */

    // Initiate variables
    public WebDriver driver;
    public WebDriverWait wait;
    public Actions actions;

    /*
     *********** DEFINE ACTIONS THAT TAKE PLACE BEFORE TESTING ***********
    */
    @BeforeEach
    public void setup() throws Throwable {
        LOGGER.info("******************************* SETUP *******************************");
        LOGGER.info("Opening " + NAVIGATEUR + " browser");
        driver = openBrowser(NAVIGATEUR, MAXIMIZE_DRIVER, IMP_WAIT, WINDOW_POS, WINDOW_SIZE, HEADLESS, URL);
        LOGGER.info("Initialize explicit wait of " + EXP_WAIT + " seconds");
        wait = new WebDriverWait(driver, EXP_WAIT);
        LOGGER.info("Initialize Actions class");
        actions = new Actions(driver);
        LOGGER.info("Initialize login page");
        ConnexionPage pageConnexion = new ConnexionPage(driver);
        LOGGER.info("Login to website");
        connectPortal(wait, pageConnexion.connexionChampLogin, USER, pageConnexion.connexionChampMdp, PWD,
                pageConnexion.connexionBoutonConnexion);

    }

    /*
     *********** DEFINE ACTIONS THAT TAKE PLACE AFTER TESTING ***********
    */
    @AfterEach
    public void teardown() throws Exception {

        LOGGER.info("******************************* TEARDOWN *******************************");
        LOGGER.info("Close webdriver");
        driver.quit();

    }
}