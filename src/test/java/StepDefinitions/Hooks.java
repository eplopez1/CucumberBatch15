package StepDefinitions;

import Utils.CommonMethods;
import io.cucumber.java.After;
import io.cucumber.java.Before;
import io.cucumber.java.Scenario;

public class Hooks extends CommonMethods {
    @Before
    public void preCondition(){
        openBrowserAndLaunchApplication();
    }


// scenario class holds the complete information to your tests execution in cucumber framework


    @After
    public void postCondition(Scenario scenario){
        byte[] pic;
        if(scenario.isFailed()){
          pic=  takeScreenshot("failed/"+scenario.getName());
        }
        else {
           pic= takeScreenshot("passed/"+scenario.getName());
        }
        //attach the screenshot in my report
        scenario.attach(pic,"image/png",scenario.getName());

        closeBrowser();
    }

}
