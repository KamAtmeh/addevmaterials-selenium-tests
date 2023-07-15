package com.addev.hrportal.pageobjects;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;

public class IntegrationPage extends AbstractPage {

    // ********* Constructor ******** //
    public IntegrationPage(WebDriver driver){
        super(driver);
        PageFactory.initElements(driver, this);
    }

    // ********** Web Elements *********** //

    // Page title
    @FindBy(xpath = "//h4")
    public WebElement titleIntegrationPage;

    // Arrival header
    @FindBy(xpath = "//i[normalize-space()=\"Please, select your region :\"]")
    public WebElement headerArrival;

    // Exit tab
    @FindBy(xpath = "//a[normalize-space()=\"Exit\"]")
    public WebElement buttonExit;

    // Exit header
    @FindBy(xpath = "//i[normalize-space()=\"Exit :\"]")
    public WebElement headerExit;

    // Mobility tab
    @FindBy(xpath = "//a[normalize-space()=\"Mobility\"]")
    public WebElement buttonMobility;

    // Mobility header
    @FindBy(xpath = "//i[normalize-space()=\"Mobility :\"]")
    public WebElement headerMobility;

    // Request follow-up tab
    @FindBy(xpath = "//a[normalize-space()=\"Request Follow-up\"]")
    public WebElement buttonRequestFollowup;

    // Follow-up header
    @FindBy(xpath = "//i[normalize-space()=\"Request Follow-up :\"]")
    public WebElement headerFollowup;
}
