package com.addev.hrportal.test;

import com.addev.hrportal.utils.ScreenshotOnFailureExtension;
import org.junit.jupiter.api.Test;
import com.addev.hrportal.pageobjects.AccueilPage;
import com.addev.hrportal.pageobjects.JobsPage;
import org.junit.jupiter.api.extension.ExtendWith;

import java.util.LinkedHashMap;
import java.util.Map;

import static com.addev.hrportal.utils.DatabaseSQL.getEmailInfo;
import static com.addev.hrportal.utils.DatabaseSQL.getEmailTools;
import static org.junit.jupiter.api.Assertions.*;
import static com.addev.hrportal.utils.Toolbox.*;

@ExtendWith(ScreenshotOnFailureExtension.class)
public class _02_SaisieSimpleTest extends _00_AbstractTest {

	/**
	 * TEST STARTS HERE
	 * @throws Throwable
	 */
	@Test
    public void saisieSimpleTest() throws Throwable {

		String titreDeLaPageAccueil = "Portail - RH";
		String titreJobOpening = "New job opening";
		String jobNumber = generateRandomString();
		String name = generateRandomString(false);
		// Create a HashMap and add keys and values in one instance
		Map<String, String> infoMap = new LinkedHashMap<String, String>() {{
			put("Lastname", name);
			put("Firstname", name);
			put("Date", getCurrentDate());
		}};

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
		setValue(wait, Jobs.inputJobNumber, jobNumber);
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
		setValue(wait, Jobs.inputFirstName, name);
		LOGGER.info("Input Surname");
		setValue(wait, Jobs.inputLastName, name);
		LOGGER.info("Input Other information");
		setValue(wait, Jobs.inputOtherInformation, "This is a test");
		LOGGER.info("Click on Save & Exit");
		clickElement(wait, Jobs.buttonSaveExit);
		LOGGER.info("Verify that trigramme is provided in email");
		Map<String, String> emailInfo = getEmailInfo(name);
		assertFalse(emailInfo.get("Trigramme").isEmpty(), "[KO] Trigramme is not provided in email");
		LOGGER.info("Verify that email contains the correct information");
		emailInfo.remove("Trigramme");
		assertEquals(infoMap, emailInfo, "[KO] Information in email are not correct");
		LOGGER.info("[OK] Submission completed");

    }
}
