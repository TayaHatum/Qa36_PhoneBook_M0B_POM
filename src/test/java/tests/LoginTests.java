package tests;

import config.AppiumConfig;
import models.Auth;
import org.testng.Assert;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.Test;
import screens.AuthenticationScreen;
import screens.ContactListScreen;
import screens.SplashScreen;

public class LoginTests extends AppiumConfig {

    @Test
    public void loginSuccess(){
//       boolean res = new SplashScreen(driver)
//                .checkVersion("Version 1.0.0")
        boolean res = new AuthenticationScreen(driver)
                .fillEmail("noa@gmail.com")
                .fillPassword("Nnoa12345$")
                .submitLogin()
                .isContactListActivityDisplayed();
        Assert.assertTrue(res);
        // logout

    }

    @Test
    public void loginSuccessModel(){
        Auth auth = Auth.builder().email("noa@gmail.com").password("Nnoa12345$").build();

//        boolean res =new SplashScreen(driver)
//                .checkVersion("Version 1.0.0")
        boolean res = new AuthenticationScreen(driver)
                .fillLoginRegistrationForm(auth)
                . submitLogin()
                .isContactListActivityDisplayed();
        Assert.assertTrue(res);
        // logout


    }
    @Test
    public void loginWrongEmail(){
        Auth auth = Auth.builder().email("noagmail.com").password("Nnoa12345$").build();
        new AuthenticationScreen(driver)
                .fillLoginRegistrationForm(auth)
                .submitLoginNegative()
                .isErrorMessageContainsText("Login or Password incorrect");
    }

    @AfterMethod
    public void postCondition(){
        new ContactListScreen(driver)
                .logout();

    }
}
