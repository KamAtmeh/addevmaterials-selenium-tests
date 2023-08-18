package com.addev.hrportal.test;

import com.addev.hrportal.utils.ScreenshotOnFailureExtension;
import org.junit.jupiter.api.Test;
import com.addev.hrportal.pageobjects.AccueilPage;
import com.addev.hrportal.pageobjects.IntegrationPage;
import org.junit.jupiter.api.extension.ExtendWith;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertTrue;
import static com.addev.hrportal.utils.Toolbox.clickElement;

@ExtendWith(ScreenshotOnFailureExtension.class)
public class _01_A_NavigationClassiqueTest extends _00_AbstractTest {

	/**
	 * TEST STARTS HERE
	 * @throws Throwable
	 */
	@Test
    public void _01_A_NavigationClassiqueTest() throws Throwable {

		String titreDeLaPageAccueil = "Portail - RH";
		String IntegrationPageTitle = "HR Integration";

		LOGGER.info("******************************* DEBUT DU TEST *******************************");
		LOGGER.info("Initialize homepage");
		AccueilPage Accueil = new AccueilPage(driver);
		LOGGER.info("Verify that title is displayed");
		assertTrue(Accueil.titrePageAccueil.isDisplayed(), "[KO] Page title is not displayed");
		LOGGER.info("Verify that title page is \"{}\"", titreDeLaPageAccueil);
		assertEquals(titreDeLaPageAccueil, Accueil.titrePageAccueil.getText(),"[KO] Page title is not as expected");
		LOGGER.info("Click on Entry / Exit");
		clickElement(wait, Accueil.buttonEntryExitV1);
		LOGGER.info("Initialize HR Integration page");
		IntegrationPage Integration = new IntegrationPage(driver);
		LOGGER.info("Verify that title is displayed");
		assertTrue(Integration.titleIntegrationPage.isDisplayed(), "[KO] HR Integration page title is not displayed");
		LOGGER.info("Verify that title page is \"{}\"", IntegrationPageTitle);
		assertEquals(IntegrationPageTitle, Integration.titleIntegrationPage.getText(),"[KO] HR Integration page title is not as expected");
		LOGGER.info("Verify that header is \"{}\"", "PLEASE, SELECT YOUR REGION :");
		assertEquals("PLEASE, SELECT YOUR REGION :", Integration.headerArrival.getText(),"[KO] Arrival header is not as expected");
		LOGGER.info("Click on Exit");
		clickElement(wait, Integration.buttonExit);
		LOGGER.info("Verify that header is \"{}\"", "EXIT :");
		assertEquals("EXIT :", Integration.headerExit.getText(),"[KO] Exit header is not as expected");
		LOGGER.info("Click on Mobility");
		clickElement(wait, Integration.buttonMobility);
		LOGGER.info("Verify that header is \"{}\"", "MOBILITY :");
		assertEquals("MOBILITY :", Integration.headerMobility.getText(),"[KO] Mobility header is not as expected");
		LOGGER.info("Click on Request Follow-up");
		clickElement(wait, Integration.buttonRequestFollowup);
		LOGGER.info("Verify that header is \"{}\"", "REQUEST FOLLOW-UP :");
		assertEquals("REQUEST FOLLOW-UP :", Integration.headerFollowup.getText(),"[KO] Request Follow-up header is not as expected");
		LOGGER.info("Click on Home icon");
		clickElement(wait, Accueil.buttonRetourAccueil);
		LOGGER.info("Verify that title is displayed");
		assertTrue(Accueil.titrePageAccueil.isDisplayed(), "[KO] The title of the page is not displayed");
		LOGGER.info("Verify that title page is \"{}\"", titreDeLaPageAccueil);
		assertEquals(titreDeLaPageAccueil, Accueil.titrePageAccueil.getText(),"[KO] The title of the page is not as expected");
		LOGGER.info("[OK] Verification complete. Navigation is correct.");

    }
}
