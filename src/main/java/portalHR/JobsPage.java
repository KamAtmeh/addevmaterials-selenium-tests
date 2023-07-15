package portalHR;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;

import java.util.List;

public class JobsPage extends AbstractPage{

    // ********* Constructor ******** //
    public JobsPage(WebDriver driver){
        super(driver);
        PageFactory.initElements(driver, this);
    }

    // ********** INPUT *********** //

    //Titre de la page
    @FindBy(tagName = "h2")
    public WebElement titreNewJobOpening;

    //Input Job Number
    @FindBy(id = "input-0")
    public WebElement inputJobNumber;

    // Input Job Title
    @FindBy(id = "input-2")
    public WebElement inputJobTitle;

    //Input Job Number
    @FindBy(id = "input-4")
    public WebElement inputManager;

    // Input Localisation
    @FindBy(id = "input-15")
    public WebElement inputLocalisation;

    // Input Company
    @FindBy(id = "input-18")
    public WebElement inputCompany;

    // Input Pays
    @FindBy(id = "input-21")
    public WebElement inputPays;

    // Input First name
    @FindBy(xpath = "//label[text()=\"First name\"]/following-sibling::input")
    public WebElement inputFirstName;

    // Input Surname
    @FindBy(xpath = "//label[text()=\"Last name\"]/following-sibling::input")
    public WebElement inputLastName;

    // Input Surname
    @FindBy(xpath = "//label[text()=\"Other information\"]/following-sibling::textarea")
    public WebElement inputOtherInformation;


    // ********** RADIO BUTTONS *********** //
    // Radio button for Business Group
    @FindBy(name = "radio-group-7")
    public List<WebElement> radioBusinessGroup;


    // ********** BUTTONS *********** //
    // Button Save and Exit
    @FindBy(xpath = "//button[normalize-space()='Save & Exit']")
    public WebElement buttonSaveExit;

    // Button Continue
    @FindBy(xpath = "//button[normalize-space()='Continue']")
    public WebElement buttonContinue;

}
