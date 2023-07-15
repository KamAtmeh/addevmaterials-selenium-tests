package autom;

import org.junit.jupiter.api.Test;
import portalHR.AccueilPage;
import portalHR.JobsPage;

import java.util.Iterator;
import java.util.Map;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertTrue;
import static utils.DatabaseSQL.*;
import static utils.Toolbox.*;

public class _03_A_SaisieComplexeTest extends _00_AbstractTest {

	/*
     *********** TEST STARTS HERE ***********
     */
	@Test
    public void _03_A_SaisieComplexeTest() throws Throwable {

		String titreDeLaPage = "Portail - RH";
		String titreJobOpening = "New job opening";

		LOGGER.info("******************************* DEBUT DU TEST *******************************");
		LOGGER.info("Initiate homepage");
		AccueilPage Accueil = new AccueilPage(driver);
		LOGGER.info("Verify title page");
		assertTrue(Accueil.titrePageAccueil.isDisplayed(), "[KO] Page title is not displayed");
		assertEquals(titreDeLaPage, Accueil.titrePageAccueil.getText(),"[KO] Page title is not as expected");
		LOGGER.info("Click on JOBS");
		clickElement(wait, Accueil.buttonJOBSv2);
		LOGGER.info("Initiate new job opening page");
		JobsPage Jobs = new JobsPage(driver);
		LOGGER.info("Verify title plage");
		assertTrue(Jobs.titreNewJobOpening.isDisplayed(), "[KO] Page title is not displayed");
		assertEquals(titreJobOpening, Jobs.titreNewJobOpening.getText(), "[KO] Jobs page title is not as expected");
		LOGGER.info("Input Job Number");
		setValue(wait, Jobs.inputJobNumber, "5678");
		LOGGER.info("Input Job Title");
		setValue(wait, Jobs.inputJobTitle, "QA Engineer");
		LOGGER.info("Select Manager");
		selectValueMenu(wait, Jobs.inputManager, "Aaron COBBOLD (201)");
		LOGGER.info("Select Business Group");
		selectRadioButton(wait, Jobs.radioBusinessGroup, "Corporate");
		LOGGER.info("Select Localisation");
		selectValueMenu(wait, Jobs.inputLocalisation, "Lyon (addev materials siege social)");
		LOGGER.info("Select Company");
		selectValueMenu(wait, Jobs.inputCompany, "ADDEV MATERIALS SIEGE SOCIAL");
		LOGGER.info("Select Pays");
		String pays = "France";
		selectValueMenu(wait, Jobs.inputPays, pays);
		LOGGER.info("Select Software Licenses and Professional tools");
		Map<String, String> portalToolsMap = getPortalToolsMap(pays);
		selectPortalTools(wait, portalToolsMap);
		LOGGER.info("Click on Continue");
		clickElement(wait, Jobs.buttonContinue);
		LOGGER.info("Input First Name");
		String name = "Test 24";
		setValue(wait, Jobs.inputFirstName, name);
		LOGGER.info("Input Last Name");
		setValue(wait, Jobs.inputLastName, name);
		LOGGER.info("Input Other information");
		setValue(wait, Jobs.inputOtherInformation, "This is a test");
		LOGGER.info("Click on Save & Exit");
		clickElement(wait, Jobs.buttonSaveExit);
		LOGGER.info("Click on Close Dialog button");
		closeDialog();
		LOGGER.info("Verify that user is back on home page");
		assertTrue(Accueil.titrePageAccueil.isDisplayed(), "[KO] User is not back on homepage");
		LOGGER.info("Verify that emails have been sent to the correct address and that the email contains the chosen tool");
		assertEquals(portalToolsMap, getEmailTrace(name), "[KO] Emails and tools are not correct");
		LOGGER.info("[OK] Submission completed");
    }
}
