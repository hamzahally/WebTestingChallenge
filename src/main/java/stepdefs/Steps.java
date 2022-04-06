package stepdefs;

import io.cucumber.java.After;
import io.cucumber.java.en.Given;
import io.cucumber.java.en.Then;
import io.cucumber.java.en.When;
import org.junit.Assert;
import org.openqa.selenium.By;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;

import java.time.Duration;
import java.util.concurrent.TimeUnit;

public class Steps {

    WebDriver driver = null;

//    @After
//    public void teardown(){
//        driver.quit();
//    }

    @Given("the browser is Open")
    public void the_browser_is_open() {
        System.setProperty("webdriver.chrome.driver","src/main/resources/drivers/chromedriver.exe");
        driver = new ChromeDriver();
        driver.manage().window().maximize();
    }

    @Given("user navigates is at the exchange markets")
    public void navigateToExchangeMarket() {
        driver.get("https://crypto.com/exchange/markets");
        driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(20));
        driver.findElement(By.xpath("/html/body/div[5]/div/div/div[2]/div/button")).click();
        driver.manage().timeouts().pageLoadTimeout(Duration.ofSeconds(10));
        driver.findElement(By.xpath("/html/body/div[5]/div/div/div[2]/div/button")).click();
        driver.findElement(By.xpath("/html/body/div[1]/div[2]/div[4]/div[2]/div")).click();
    }


    @Given("^user navigates to ZIL/USDT pair$")
    public void user_navigates_to_zil_usdt_pair() throws InterruptedException {
        JavascriptExecutor js = (JavascriptExecutor) driver;
        WebElement element = driver.findElement(By.xpath("//*[@id=\"app\"]/div[2]/div[1]/div/span"));
        js.executeScript("arguments[0].scrollIntoView();",element);
        Thread.sleep(2000);
        driver.findElement(By.xpath("//*[@id=\"app\"]/div[1]/div[1]/div[3]/div[3]/table/tbody/tr[178]/td[2]/div")).click();
    }

    @When("user clicks trade")
    public void user_clicks_trade() {
        // Write code here that turns the phrase above into concrete actions
        throw new io.cucumber.java.PendingException();
    }

    @Then("^the ZIL/USDT trade page will show$")
    public void the_zil_usdt_trade_page_will_show() throws InterruptedException {
        // Write code here that turns the phrase above into concrete actions
        Thread.sleep(5000);
        Assert.assertTrue(driver.findElement(By.xpath("//*[@id=\"app\"]/div[1]/div[1]/div/div[1]/div/div[1]/div/div/div/div[1]/div")).isDisplayed());
    }

}
