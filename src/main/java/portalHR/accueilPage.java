package portalHR;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;

public class accueilPage extends abstractPage {

    // ********* Constructor ******** //
    public accueilPage(WebDriver driver){
        super(driver);
        PageFactory.initElements(driver, this);
    }

    // ********** Web Elements *********** //

    //Titre de la page
    @FindBy(xpath = "//h4")
    public WebElement titrePageAccueil;

    //Bouton Jobs Vers V2
    @FindBy(xpath = "//a[@class='shadow rounded btn']]")
    public WebElement boutonJOBSv2;

    //Bouton vers Entry/Exit V1
    @FindBy(xpath = "//button[contains(text(), 'Entry / Exit')]")
    public WebElement boutonEntryExitV1;

    //Bouton vers Entry/Exit V1
    @FindBy(xpath = "//button[contains(text(), 'Calcul indemnisation')]")
    public WebElement boutonCalculIndemnisation;

    //Bouton déconnexion
    @FindBy(xpath = "//a[contains(@href, 'logout')]")
    public WebElement boutonDeconnexion;

    //Bouton navigation retour accueil
    @FindBy(xpath = "//a[contains(@href, 'portail')]")
    public WebElement boutonRetourAccueil;




}


