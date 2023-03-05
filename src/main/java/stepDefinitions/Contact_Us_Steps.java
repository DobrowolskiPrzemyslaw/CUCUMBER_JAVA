package stepDefinitions;

import io.cucumber.java.After;
import io.cucumber.java.Before;
import io.cucumber.java.en.And;
import io.cucumber.java.en.Given;
import io.cucumber.java.en.Then;
import io.cucumber.java.en.When;
import org.openqa.selenium.By;
import org.openqa.selenium.PageLoadStrategy;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.chrome.ChromeOptions;
import org.testng.Assert;
import java.util.concurrent.TimeUnit;

public class Contact_Us_Steps {

    private WebDriver driver;

    @Before
    public void steup(){
        System.setProperty("webdriver.chrome.driver", System.getProperty("user.dir") + "/src/main/java/driver/chromedriver");
        ChromeOptions chromeOptions = new ChromeOptions();
        chromeOptions.setPageLoadStrategy(PageLoadStrategy.NORMAL);
        driver = new ChromeDriver(chromeOptions);
        driver.manage().window().maximize();
        driver.manage().timeouts().pageLoadTimeout(60, TimeUnit.SECONDS);
        driver.manage().timeouts().setScriptTimeout(60, TimeUnit.SECONDS);
    }

    @After
    public void tearDown(){
        driver.manage().deleteAllCookies();
        driver.close();
    }

    @Given("I access the webdriver university contact us page")
    public void i_access_the_webdriver_university_contact_us_page() throws InterruptedException {
        driver.get("http://www.webdriveruniversity.com/Contact-Us/contactus.html");
        Thread.sleep(3000);
    }
    @When("I enter a first name")
    public void i_enter_a_first_name() {
        driver.findElement(By.name("first_name")).sendKeys("Monika");
    }
    @And("I enter a last name")
    public void i_enter_a_last_name() {
        driver.findElement(By.name("last_name")).sendKeys("Dobrowolska");
    }
    @And("I enter an email address")
    public void i_enter_an_email_address() {
        driver.findElement(By.name("email")).sendKeys("monika@wp.pl");
    }
    @And("I enter a comment")
    public void i_enter_a_comment() {
        driver.findElement(By.name("message")).sendKeys("message");
    }
    @And("I click on the submit button")
    public void i_click_on_the_submit_button() {
        driver.findElement(By.xpath("//input[@value='SUBMIT']")).click();
    }
    @Then("I should be presented with a successful contact us submission message")
    public void i_should_be_presented_with_a_successful_contact_us_submission_message() throws InterruptedException {
        Thread.sleep(3000);
        WebElement webElement = driver.findElement(By.xpath("//div[@id='contact_reply']//h1"));
        Assert.assertEquals(true, webElement.isDisplayed());
        Assert.assertEquals("Thank You for your Message!", webElement.getText());
    }
}
