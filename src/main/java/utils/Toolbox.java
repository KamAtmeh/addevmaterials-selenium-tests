package utils;


import org.apache.commons.io.FileUtils;
import org.openqa.selenium.*;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.chrome.ChromeOptions;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.openqa.selenium.firefox.FirefoxOptions;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;
import portalHR.JobsPage;

import java.io.*;
import java.time.Duration;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.List;
import java.util.Map;
import java.util.Properties;

import static portalHR.AbstractPage.driver;

/* Class for defining tools that can be used in any project
such as clear field, input value and click button
 */

public class Toolbox extends Logging {

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
    public static WebDriver openBrowser(String browser, Boolean maximizeDriver, Integer implicitWaitingTime, String windowPosition, String windowSize, Boolean headless, String url) {

        WebDriver driver = null;

        switch (browser.toLowerCase()) {
            case "firefox" :
                System.setProperty("webdriver.gecko.driver", "src/main/resources/drivers/geckodriver.exe");
                FirefoxOptions firefoxOptions = new FirefoxOptions();
                firefoxOptions.addArguments(windowPosition); // opens browser on second monitor if possible
                firefoxOptions.addArguments(windowSize); // opens browser with specific dimensions
                if(headless){firefoxOptions.addArguments("--headless");} // run in headless if needed
                firefoxOptions.setBinary("C:\\Program Files\\Mozilla Firefox\\firefox.exe");
                driver = new FirefoxDriver(firefoxOptions);
                break;
            case "chrome" :
                System.setProperty("webdriver.chrome.driver", "src/main/resources/drivers/chromedriver.exe");
                ChromeOptions chromeOptions = new ChromeOptions();
                chromeOptions.addArguments(windowPosition); // opens browser on second monitor if possible
                chromeOptions.addArguments(windowSize); // opens browser with specific dimensions
                chromeOptions.addArguments("--remote-allow-origins=*");
                if(headless){chromeOptions.addArguments("--headless");} // run in headless if needed
                driver = new ChromeDriver(chromeOptions);
                break;
        }
        LOGGER.info(browser + " running");
        // initiate driver and wait
        if (maximizeDriver) {
            LOGGER.info("Maximize browser window");
            driver.manage().window().maximize();
        }
        LOGGER.info("Delete cookies and cache");
        driver.manage().deleteAllCookies();
        LOGGER.info("Initialize implicit wait of " + implicitWaitingTime + " seconds");
        driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(implicitWaitingTime));
        LOGGER.info("Open website " + url);
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
        boolean stale = true;
        while (stale){
            try{
                // Wait for the document to be ready
                // We use this instead of visibility of element because the labels of inputs are positioned over the
                // input, thus making the inputs not visible
                wait.until(ExpectedConditions.jsReturnsValue("return document.readyState === 'complete'"));
                element.click();
                stale = false;
            } catch (StaleElementReferenceException ex) {
                stale = true;
            } catch (ElementClickInterceptedException ex2) {
                // Use JavaScript to click on elements if hidden behind other elements
                ((JavascriptExecutor) driver).executeScript("arguments[0].click();", element);
                element.findElement(By.xpath("ancestor::div[@class=\"v-field__field\"]/following-sibling::div/i")).click();
                stale = false;
            }
        }
    }

    // function to fill fields with text after clearing them
    public static void setValue(WebDriverWait wait, WebElement element, String string) throws Throwable {
        // Click on element
        clickElement(wait, element);

        // Clear input field
        element.clear();

        // Input text into field
        element.sendKeys(string);
    }


    // function to select value in dropdown menu
    // function to select a value from a field menu
    public static void selectValueMenu(WebDriverWait wait, WebElement element, String... valeurs) throws Throwable {

        // click on element first
        clickElement(wait, element);

        // define xpath using value to choose
        String xpathValeur = "//div[@class=\"v-list-item-title\" and contains(text(), \"" + valeurs[0] + "\")]";

        try {
            // Récupérer l'élément à sélectionner dans le menu déroulant
            WebElement valeurChoisie = wait.until(ExpectedConditions.elementToBeClickable(By.xpath(xpathValeur)));
            clickElement(wait, valeurChoisie);
        } catch (StaleElementReferenceException ex) {
            WebElement valeurChoisie = wait.until(ExpectedConditions.visibilityOfElementLocated(By.xpath(xpathValeur)));
            clickElement(wait, valeurChoisie);
        }
    }

    // function to select radio button
    public static void selectRadioButton(WebDriverWait wait, List<WebElement> radioElements, String choice) throws Throwable {
        if(!choice.equals("null")){
            // Iterate through the list of radio button elements
            for (WebElement radio : radioElements) {
                // Find the following sibling span element
                WebElement label = radio.findElement(By.xpath("ancestor::div[@class=\"v-selection-control__wrapper\"]/following-sibling::label"));
                // Retrieve the text from the span element
                String spanText = label.getText().trim();
                // Compare text and select the desired radio button
                if (spanText.equals(choice)) {
                    clickElement(wait, radio);
                    break;
                }
            }
        }
    }

    public static void selectPortalTools(WebDriverWait wait, Map<String, String> toolsMap) throws Throwable {

        String xpath = null;
        WebElement element = null;

        // Use the mapped column values
        for (Map.Entry<String, String> entry : toolsMap.entrySet()) {
            // Define xpath based on values in the map
            xpath = "//span[contains(@class, 'v-chip--link') and translate(normalize-space(),'ABCDEFGHIJKLMNOPQRSTUVWXYZ', 'abcdefghijklmnopqrstuvwxyz')=\"" + entry.getKey().toLowerCase() + "\"]";
            // Retrieve the corresponding element to the value
            element = wait.until(ExpectedConditions.visibilityOfElementLocated(By.xpath(xpath)));
            // Click on the element
            clickElement(wait, element);
        }
    }

    // Method to close the sql popup after Save & Continue
    public static void closeDialog(){
        boolean popupPresent = true;
        while (popupPresent){
            try {
                driver.findElement(By.xpath("//span[text()=\"Close Dialog\"]")).click();
                popupPresent = true;
            } catch (ElementClickInterceptedException e1) {
                popupPresent = true;
            } catch (NoSuchElementException e) {
                popupPresent = false;
            }
        }
    }

    // take screenshot of webpage and stock it in folder
    public static String takeSnapShot(WebDriver driver) throws Exception{
        //Convert web driver object to TakeScreenshot
        TakesScreenshot scrShot =((TakesScreenshot)driver);
        //Call getScreenshotAs method to create image file
        LOGGER.info("Capture d'écran de la page web");
        File SrcFile = scrShot.getScreenshotAs(OutputType.FILE);
        //Move image file to new destination
        LocalDateTime currentDate = LocalDateTime.now();
        // Format the date as per your requirement
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("ddMMyyyyHHmmss");
        String formattedDate = currentDate.format(formatter);
        String fileName = className + "_" + formattedDate + ".jpg";
        LOGGER.info("Sauvegarde de la capture d'écran \"" + fileName + "\" dans le dossier \"target\"");
        String fileWithPath = ".\\target\\screenshots\\" + fileName;
        File DestFile = new File(fileWithPath);
        //Copy file at destination
        FileUtils.copyFile(SrcFile, DestFile);
        return DestFile.getCanonicalPath();
    }

}
