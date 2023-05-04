package autom;

import org.junit.jupiter.api.Test;
import autom._00_abstractTest;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.interactions.Actions;
import org.openqa.selenium.support.ui.WebDriverWait;
import portalHR.accueilPage;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertTrue;

import static utils.toolbox.*;

public class _01_A_navigationClassiqueTest extends _00_abstractTest {

	/*
     *********** TEST STARTS HERE ***********
     */
	@Test
    public void _01_A_navigationClassiqueTest() throws Throwable {

		String titreDeLaPage = "Portail - RH";

		LOGGER.info("******************************* DEBUT DU TEST *******************************");
		LOGGER.info("Instanciation de la page de l'accueil");
		accueilPage Accueil = new accueilPage(driver);
		LOGGER.info("Verification de la présence et de la valeur du titre de la page");
		assertTrue(Accueil.titrePageAccueil.isDisplayed(), "[KO] Le titre de la page n'est pas affiché");
		assertEquals(titreDeLaPage, Accueil.titrePageAccueil.getText(),"[KO] Le titre de la page n'est pas celui attendu");
		LOGGER.info("[OK] Verification terminée, la navigation est correcte");


    }
	
}
