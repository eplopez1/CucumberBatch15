package StepDefinitions;

import Utils.CommonMethods;
import Utils.ConfigReader;
import io.cucumber.datatable.DataTable;
import io.cucumber.java.en.Given;
import io.cucumber.java.en.Then;
import io.cucumber.java.en.When;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.edge.EdgeDriver;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.openqa.selenium.ie.InternetExplorerDriver;

import java.time.Duration;
import java.util.List;
import java.util.Map;

public class Login  extends CommonMethods {

    @Given("open the browser and launch HRMS application")
    public void open_the_browser_and_launch_hrms_application() {

        openBrowserAndLaunchApplication();


    }
    @When("user enters valid email and valid password")
    public void user_enters_valid_email_and_valid_password() {
        WebElement usernameTextBox=driver.findElement(By.id("txtUsername"));
        sendText(usernameTextBox,ConfigReader.getPropertyValue("username"));

        WebElement passwordTextBox=driver.findElement(By.id("txtPassword"));
        sendText(passwordTextBox,ConfigReader.getPropertyValue("password"));
    // driver.findElement(By.id("txtUsername")).sendKeys(ConfigReader.getPropertyValue("username"));
    // driver.findElement(By.id("txtPassword")).sendKeys(ConfigReader.getPropertyValue("password"));
    }
    @When("click on login button")
    public void click_on_login_button() {

      WebElement loginBTN=driver.findElement(By.id("btnLogin"));
      doClick(loginBTN);
    }
    @Then("user is logged in successfully into the application")
    public void user_is_logged_in_successfully() {
      boolean userloggedIn= driver.findElement(By.xpath("//a[contains(text(), 'Welcome')]")).isDisplayed();
      if (userloggedIn){
          System.out.println("user is logged in succesfully");
      }
    }
    @Then("close the browser")
    public void close_the_browser() {
       closeBrowser();
    }

    @When("user enters valid {string} and valid {string}")
    public void user_enters_valid_and_valid(String username, String password) {

        WebElement usernameTextBox = driver.findElement(By.id("txtUsername"));
        sendText(usernameTextBox, username);

        WebElement passwordTextBox = driver.findElement(By.id("txtPassword"));
        sendText(passwordTextBox, password);
    }
    @When("user enters username and password and verfies login")
    public void user_enters_username_and_password_and_verfies_login(DataTable dataTable) {
      List<Map<String, String>> userCredentials= dataTable.asMaps();
      for(Map<String,String >userCreds:userCredentials){

          String username=userCreds.get("username");
          String password=userCreds.get("password");

          WebElement usernameTextBox = driver.findElement(By.id("txtUsername"));
          sendText(usernameTextBox,username);

          WebElement passwordTextBox = driver.findElement(By.id("txtPassword"));
          sendText(passwordTextBox,password);

          WebElement loginBTN=driver.findElement(By.id("btnLogin"));
          doClick(loginBTN);

         WebElement welcomeIcon= driver.findElement(By.id("welcome"));
         doClick(welcomeIcon);

          WebElement logoutLink= driver.findElement(By.xpath("//a[text()='Logout']"));
          doClick(logoutLink);


      }

    }

    }



