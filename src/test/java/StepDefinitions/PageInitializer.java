package StepDefinitions;

import Pages.LoginPage;
import Pages.AddEmployeePage;

public class PageInitializer {
    public static  LoginPage login;
    public static AddEmployeePage addEmployeePage;
    public static void initializePageObjects()
    {
        login =new LoginPage();
        addEmployeePage= new AddEmployeePage();


    }
}

// this class will mana the object creation of different page objects in our framework
//instead of calling the age objects again and again, this class will take good care of that step
