package nz.co.airnz.testng.object;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;

public class FlightSearch {

    WebDriver driver;

    public FlightSearch(WebDriver driver) {
        this.driver = driver;
        PageFactory.initElements(driver, this);
    }

    @FindBy(id = "depart-to")
    WebElement departTo;

    @FindBy(id = "leaveDate")
    WebElement leaveDate;

    @FindBy(id = "returnDate")
    WebElement returnDate;

    @FindBy(xpath = "//*[@id='calendarpanel-0']/div[1]/div/select")
    WebElement calendarPanel_0;

    @FindBy(xpath = "//*[@id='calendarpanel-1']/div[1]/div/select")
    WebElement calendarPanel_1;


    @FindBy(xpath = "//button[contains(text(),'Search')]")
    WebElement searchButton;



    public WebElement departTo() {

        return departTo;
    }

    public WebElement leaveDate() {

        return leaveDate;
    }

    public WebElement returnDate() {

        return returnDate;
    }

    public WebElement calendarPanel_0() {

        return calendarPanel_0;
    }

    public WebElement calendarPanel_1() {

        return calendarPanel_1;
    }

    public WebElement searchButton() {

        return searchButton;
    }




}
