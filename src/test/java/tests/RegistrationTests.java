package tests;

import config.AppiumConfig;
import models.Auth;
import org.testng.Assert;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.Test;
import screens.AuthenticationScreen;
import screens.ContactListScreen;

import java.util.Random;

public class RegistrationTests extends AppiumConfig {

    @Test
    public void registrationSuccess(){
        int i = new Random().nextInt(1000)+100;
        boolean result = new AuthenticationScreen(driver)
                .fillEmail("dex"+i+"@mail.com")
                .fillPassword("Ww12345$")
                .submitRegistration()
                .isContactListActivityDisplayed();
        Assert.assertTrue(result);

    }
    @Test
    public void registrationSuccessModel(){
        int i = new Random().nextInt(1000)+100;
        Auth auth = Auth.builder().email("dod"+i+"@mail.com").password("Dd12345$").build();
        boolean result = new AuthenticationScreen(driver)
                .fillLoginRegistrationForm(auth)
                .submitRegistration()
                .isContactListActivityDisplayed();
        Assert.assertTrue(result);
    }

    @AfterMethod
    public void postCondition(){
        new ContactListScreen(driver)
                .logout();

    }

}
