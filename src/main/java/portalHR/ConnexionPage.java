package portalHR;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;

public class ConnexionPage extends AbstractPage {

    // ********* Constructor ******** //
    public ConnexionPage(WebDriver driver){
        super(driver);
        PageFactory.initElements(driver, this);
    }

    // ********** Web Elements *********** //

    //champ IDENTIFIANT
    @FindBy(xpath = "//input[@name=\'username\']")
    public WebElement connexionChampLogin;

    //champ MOT DE PASSE
    @FindBy(xpath = "//input[@name=\'password\']")
    public WebElement connexionChampMdp;

    //bouton CONNEXION
    @FindBy(xpath = "//input[@name=\'submit\']")
    public WebElement connexionBoutonConnexion;
}