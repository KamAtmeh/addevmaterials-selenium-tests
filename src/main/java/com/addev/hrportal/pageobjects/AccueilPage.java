package com.addev.hrportal.pageobjects;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;

public class AccueilPage extends AbstractPage {

    // ********* Constructor ******** //
    public AccueilPage(WebDriver driver){
        super(driver);
        PageFactory.initElements(driver, this);
    }

    // ********** Web Elements *********** //

    //Titre de la page
    @FindBy(xpath = "//h4")
    public WebElement titrePageAccueil;

    //button Jobs Vers V2
    @FindBy(xpath = "//a[normalize-space()='-JOBS']")
    public WebElement buttonJOBSv2;

    //button vers Entry/Exit V1
    @FindBy(xpath = "//button[contains(text(), 'Entry / Exit')]")
    public WebElement buttonEntryExitV1;

    //button vers Entry/Exit V1
    @FindBy(xpath = "//button[contains(text(), 'Calcul indemnisation')]")
    public WebElement buttonCalculIndemnisation;

    //button déconnexion
    @FindBy(xpath = "//a[contains(@href, 'logout')]")
    public WebElement buttonDeconnexion;

    //button navigation retour accueil
    @FindBy(xpath = "//a[contains(@href, 'portail')]")
    public WebElement buttonRetourAccueil;




}


