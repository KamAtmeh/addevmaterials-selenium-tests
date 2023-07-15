package autom;

import org.junit.jupiter.api.Test;
import portalHR.AccueilPage;
import portalHR.IntegrationPage;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertTrue;
import static utils.Toolbox.clickElement;

public class _01_A_NavigationClassiqueTest extends _00_AbstractTest {

	/*
     *********** TEST STARTS HERE ***********
     */
	@Test
    public void _01_A_NavigationClassiqueTest() throws Throwable {

		String titreDeLaPageAccueil = "Portail - RH";
		String IntegrationPageTitle = "HR Integration";

		LOGGER.info("******************************* DEBUT DU TEST *******************************");
		LOGGER.info("Initialize homepage");
		AccueilPage Accueil = new AccueilPage(driver);
		LOGGER.info("Verify that title is displayed");
		assertTrue(Accueil.titrePageAccueil.isDisplayed(), "[KO] Title of homepage is not displayed");
		LOGGER.info("Verify that title is correct");
		assertEquals(titreDeLaPageAccueil, Accueil.titrePageAccueil.getText(),"[KO] Homepage title is not as expected");
		LOGGER.info("Click on Entry / Exit");
		clickElement(wait, Accueil.buttonEntryExitV1);
		LOGGER.info("Initialize HR Integration page");
		IntegrationPage Integration = new IntegrationPage(driver);
		LOGGER.info("Verify that title is displayed");
		assertTrue(Integration.titleIntegrationPage.isDisplayed(), "[KO] HR Integration page title is not displayed");
		LOGGER.info("Verify that title is correct");
		assertEquals(IntegrationPageTitle, Integration.titleIntegrationPage.getText(),"[KO] HR Integration page title is not as expected");
		LOGGER.info("Verify that Arrival header is correct");
		assertEquals("PLEASE, SELECT YOUR REGION :", Integration.headerArrival.getText(),"[KO] Arrival header is not as expected");
		LOGGER.info("Click on Exit");
		clickElement(wait, Integration.buttonExit);
		LOGGER.info("Verify that header is correct");
		assertEquals("EXIT :", Integration.headerExit.getText(),"[KO] Exit header is not as expected");
		LOGGER.info("Click on Mobility");
		clickElement(wait, Integration.buttonMobility);
		LOGGER.info("Verify that header is correct");
		assertEquals("MOBILITY :", Integration.headerMobility.getText(),"[KO] Mobility header is not as expected");
		LOGGER.info("Click on Request Follow-up");
		clickElement(wait, Integration.buttonRequestFollowup);
		LOGGER.info("Verify that header is correct");
		assertEquals("REQUEST FOLLOW-UP :", Integration.headerFollowup.getText(),"[KO] Request Follow-up header is not as expected");
		LOGGER.info("Click on Home icon");
		clickElement(wait, Accueil.buttonRetourAccueil);
		LOGGER.info("Verify that title is displayed");
		assertTrue(Accueil.titrePageAccueil.isDisplayed(), "[KO] Le titre de la page n'est pas affiché");
		LOGGER.info("Verify that title is correct");
		assertEquals(titreDeLaPageAccueil, Accueil.titrePageAccueil.getText(),"[KO] Le titre de la page n'est pas celui attendu");
		LOGGER.info("[OK] Verification complete. Navigation is correct.");
    }
}
