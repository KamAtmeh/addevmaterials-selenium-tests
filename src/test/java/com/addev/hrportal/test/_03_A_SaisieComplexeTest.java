package com.addev.hrportal.test;

import com.addev.hrportal.utils.ScreenshotOnFailureExtension;
import org.junit.jupiter.api.Test;
import com.addev.hrportal.pageobjects.AccueilPage;
import com.addev.hrportal.pageobjects.JobsPage;
import org.junit.jupiter.api.extension.ExtendWith;

import java.util.Map;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertTrue;
import static com.addev.hrportal.utils.DatabaseSQL.*;
import static com.addev.hrportal.utils.Toolbox.*;


@ExtendWith(ScreenshotOnFailureExtension.class)
public class _03_A_SaisieComplexeTest extends _00_AbstractTest {

	/**
	 * TEST STARTS HERE
	 * @throws Throwable
	 */
	@Test
    public void saisieComplexeTest() throws Throwable {

		String titreDeLaPageAccueil = "Portail - RH";
		String titreJobOpening = "New job opening";
		String name = generateRandomString(false);

		LOGGER.info("******************************* DEBUT DU TEST *******************************");
		LOGGER.info("Initialize homepage");
		AccueilPage Accueil = new AccueilPage(driver);
		LOGGER.info("Verify that title is displayed");
		assertTrue(Accueil.titrePageAccueil.isDisplayed(), "[KO] Page title is not displayed");
		LOGGER.info("Verify that title page is \"{}\"", titreDeLaPageAccueil);
		assertEquals(titreDeLaPageAccueil, Accueil.titrePageAccueil.getText(),"[KO] Page title is not as expected");
		LOGGER.info("Click on JOBS");
		clickElement(wait, Accueil.buttonJOBSv2);
		LOGGER.info("Initialize new job opening page");
		JobsPage Jobs = new JobsPage(driver);
		LOGGER.info("Verify that title is displayed");
		assertTrue(Jobs.titreNewJobOpening.isDisplayed(), "[KO] Page title is not displayed");
		LOGGER.info("Verify that title page is \"{}\"", titreJobOpening);
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
		setValue(wait, Jobs.inputFirstName, name);
		LOGGER.info("Input Last Name");
		setValue(wait, Jobs.inputLastName, name);
		LOGGER.info("Input Other information");
		setValue(wait, Jobs.inputOtherInformation, "This is a test");
		LOGGER.info("Click on Job Specifications to go back to first page");
		clickElement(wait, Jobs.buttonJobSpecifications);
		LOGGER.info("Verify that title page is \"{}\"", titreJobOpening);
		assertEquals(titreJobOpening, Jobs.titreNewJobOpening.getText(), "[KO] Jobs page title is not as expected");
		LOGGER.info("Click on Continue");
		clickElement(wait, Jobs.buttonContinue);
		LOGGER.info("Click on Save & Exit");
		clickElement(wait, Jobs.buttonSaveExit);
		LOGGER.info("Click on Close Dialog button");
		closeDialog();
		LOGGER.info("Verify that user is back on home page");
		assertTrue(Accueil.titrePageAccueil.isDisplayed(), "[KO] User is not back on homepage");
		LOGGER.info("Verify that emails have been sent to the correct address and that the email contains the chosen tool");
		assertEquals(portalToolsMap, getEmailTools(name), "[KO] Emails and tools are not correct");
		LOGGER.info("[OK] Submission completed");

    }
}
