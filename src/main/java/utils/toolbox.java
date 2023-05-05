package utils;


import org.apache.commons.io.FileUtils;
import org.openqa.selenium.*;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.chrome.ChromeOptions;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.openqa.selenium.firefox.FirefoxOptions;
import org.openqa.selenium.interactions.Actions;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.ui.ExpectedCondition;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

import java.awt.*;
import java.io.*;
import java.time.Duration;
import java.util.Arrays;
import java.util.Iterator;
import java.util.NoSuchElementException;
import java.util.Properties;

import static portalHR.abstractPage.driver;

/* Class for defining tools that can be used in any project
such as clear field, input value and click button
 */

public class toolbox extends logger{

    // function to initiate properties
	
    public static Properties initProp(String fichierProperties) {
        Properties prop = new Properties();

        try{
            InputStream in = new FileInputStream(fichierProperties);
            prop.load(in);
            in.close();
        } catch (IOException e) {
            e.printStackTrace();
        }

        return prop;

    }
    
    
    // function to open browser
    
    public static WebDriver openBrowser(String browser, Boolean maximizeDriver, Integer implicitWaitingTime, String windowPosition, String url) {

        WebDriver driver = null;

        switch (browser.toLowerCase()) {
            case "firefox" :
                System.setProperty("webdriver.gecko.driver", "src/main/resources/drivers/geckodriver.exe");
                FirefoxOptions firefoxOptions = new FirefoxOptions();
                firefoxOptions.addArguments();
                driver = new FirefoxDriver(firefoxOptions);
                break;
            case "chrome" :
                System.setProperty("webdriver.chrome.driver", "src/main/resources/drivers/chromedriver.exe");
                ChromeOptions chromeOptions = new ChromeOptions();
                chromeOptions.addArguments(windowPosition); // opens browser on second monitor if possible
                driver = new ChromeDriver(chromeOptions);
                break;
        }
        LOGGER.info(browser + " lancé");

        // initiate driver and wait
        if (maximizeDriver) {
            LOGGER.info("Maximization de la fenêtre du navigateur");
            driver.manage().window().maximize();
        }
        LOGGER.info("Suppression des cookies");
        driver.manage().deleteAllCookies();
        LOGGER.info("Mise en place d'un wait implicit de " + implicitWaitingTime + " secondes");
        driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(implicitWaitingTime));
        LOGGER.info("Ouverture du site " + url);
        driver.get(url);
        return driver;
    }

    // function to connect to website
    public static void connectPortal(WebDriverWait wait, WebElement usernameField, String username, WebElement passwordField, String password, WebElement connectButton) throws Throwable {
        // clear username field and input username
        setValue(wait, usernameField, username);

        // clear password field and input password
        setValue(wait, passwordField, password);

        // click on submit button to login
        clickElement(wait, connectButton);

    }

    // function to click on element after waiting
    public static void clickElement(WebDriverWait wait, WebElement element) throws Throwable {
        LOGGER.info("Clique sur " + element.getText());
        boolean stale = true;
        while (stale){
            try{
                wait.until(ExpectedConditions.elementToBeClickable(element));
                element.click();
                stale = false;
            } catch (StaleElementReferenceException ex) {
                stale = true;
            } catch (ElementClickInterceptedException ex2) {
                LOGGER.info("Utiliser Javascript pour cliquer sur l'élément");
                // souvent les menus déroulants restent ouverts et cachent les éléments en dessous
                ((JavascriptExecutor) driver).executeScript("arguments[0].click();", element);
                stale = false;
            }
        }
    }
    
    
    
    // function to fill fields with text after clearing them
    public static void setValue(WebDriverWait wait, WebElement element, String string) throws Throwable {
        // Clique sur l'élément
        clickElement(wait, element);
        LOGGER.info("Vider le champ " + element.getText());
        element.clear();
        LOGGER.info("Saisie du texte \'" + string + "\' dans le champ " + element.getText());
        element.sendKeys(string);
        
       // WebElement body = wait.until(ExpectedConditions.presenceOfElementLocated(By.tagName("body")));
       // body.click();
        
        
        //actions.moveToElement(body, 400, 0).click().build().perform();
        //wait.until(ExpectedConditions.refreshed(ExpectedConditions.textToBePresentInElementValue(element, string)));
        
       /* if(!element.getAttribute("class").contains("has-error")){
            clickElement(wait, element);
        } else {
            while(element.getAttribute("class").contains("has-error") && element.getText().isEmpty()){
                // si le texte du champ ne correspond pas au texte à saisir, on resaisit le texte
                element.sendKeys(string);
                body.click(); 
            } 
        } */
    }
    
    // take screenshot of webpage and stock it in folder
    public static void takeSnapShot(WebDriver driver , String fileName) throws Exception{
        //Convert web driver object to TakeScreenshot
        TakesScreenshot scrShot =((TakesScreenshot)driver);
        //Call getScreenshotAs method to create image file
        File SrcFile = scrShot.getScreenshotAs(OutputType.FILE);
        //Move image file to new destination
        LOGGER.info("Sauvegarde de la capture d'écran \'" + fileName + "\' dans le dossier \'target\'");
        String fileWithPath = ".\\target\\screenshots\\" + fileName;
        File DestFile = new File(fileWithPath);
        //Copy file at destination
        FileUtils.copyFile(SrcFile, DestFile);
    }

    
    
    
 
}
