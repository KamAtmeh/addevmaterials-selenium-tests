package com.addev.hrportal.test;

import com.addev.hrportal.utils.ScreenshotOnFailureExtension;
import org.junit.jupiter.api.Test;
import com.addev.hrportal.pageobjects.AccueilPage;
import com.addev.hrportal.pageobjects.JobsPage;
import org.junit.jupiter.api.extension.ExtendWith;

import static org.junit.jupiter.api.Assertions.*;
import static com.addev.hrportal.utils.Toolbox.*;

@ExtendWith(ScreenshotOnFailureExtension.class)
public class _02_A_SaisieSimpleTest extends _00_AbstractTest {

	/*
     *********** TEST STARTS HERE ***********
     */
	@Test
    public void _02_A_SaisieSimpleTest() throws Throwable {

		String titreDeLaPage = "Portail - RH";
		String titreJobOpening = "New job opening";

		LOGGER.info("******************************* DEBUT DU TEST *******************************");
		LOGGER.info("Initialize homepage");
		AccueilPage Accueil = new AccueilPage(driver);
		LOGGER.info("Verify title page");
		assertTrue(Accueil.titrePageAccueil.isDisplayed(), "[KO] Page title is not displayed");
		assertEquals(titreDeLaPage, Accueil.titrePageAccueil.getText(),"[KO] Page title is not as expected");
		LOGGER.info("Click on JOBS");
		clickElement(wait, Accueil.buttonJOBSv2);
		LOGGER.info("Initialize new job opening page");
		JobsPage Jobs = new JobsPage(driver);
		LOGGER.info("Verify title plage");
		assertTrue(Jobs.titreNewJobOpening.isDisplayed(), "[KO] Page title is not displayed");
		assertEquals(titreJobOpening, Jobs.titreNewJobOpening.getText(), "[KO] Jobs page title is not as expected");
		LOGGER.info("Input Job Number");
		setValue(wait, Jobs.inputJobNumber, "001");
		LOGGER.info("Input Job Title");
		setValue(wait, Jobs.inputJobTitle, "QA Engineer");
		LOGGER.info("Select Manager");
		selectValueMenu(wait, Jobs.inputManager, "Aaron COBBOLD (201)");
		LOGGER.info("Select Business Group");
		selectRadioButton(wait, Jobs.radioBusinessGroup, "A&D");
		LOGGER.info("Select Localisation");
		selectValueMenu(wait, Jobs.inputLocalisation, "San Diego (addev vms)");
		LOGGER.info("Select Company");
		selectValueMenu(wait, Jobs.inputCompany, "ICTDP");
		LOGGER.info("Select Pays");
		selectValueMenu(wait, Jobs.inputPays, "Poland");
		LOGGER.info("Click on Continue");
		clickElement(wait, Jobs.buttonContinue);
		LOGGER.info("Input First Name");
		setValue(wait, Jobs.inputFirstName, "Test1");
		LOGGER.info("Input Surname");
		setValue(wait, Jobs.inputLastName, "Test1");
		LOGGER.info("Input Other information");
		setValue(wait, Jobs.inputOtherInformation, "This is a test");
		LOGGER.info("Click on Save & Exit");
		clickElement(wait, Jobs.buttonSaveExit);
		LOGGER.info("[OK] Submission completed");
    }
	
}
