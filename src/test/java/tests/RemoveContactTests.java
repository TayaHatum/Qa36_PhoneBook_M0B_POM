package tests;

import config.AppiumConfig;
import models.Auth;
import org.testng.Assert;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;
import screens.AuthenticationScreen;
import screens.ContactListScreen;

public class RemoveContactTests extends AppiumConfig {
    @BeforeClass
    public void precondition(){
        new AuthenticationScreen(driver)
                // .fillLoginRegistrationForm(Auth.builder().email("john@gmail.com").password("Aa12345!").build())
                .fillLoginRegistrationForm(Auth.builder().email("noa@gmail.com").password("Nnoa12345$").build())
                .submitLogin()
                .isContactListActivityDisplayed();
    }

    @BeforeMethod
    public void providerOfContacts(){
        new ContactListScreen(driver)
                .provideContacts();
    }

    @Test
    public void removeOneContactSuccess(){
        int result = new ContactListScreen(driver)
                .removeOneContact(2);
        Assert.assertEquals(result,1);
    }


    @Test
    public void removeOneContactSuccessAssert(){
        new ContactListScreen(driver)
                .removeOneContactAssert(2)
                .isListSizeLessOne();

    }

    @Test
    public void removeOneContactSuccess1(){
        new ContactListScreen(driver)
                .removeOneContactBase(1)
                .isListSizeLessOne();
    }

    @Test
    public void removeAllContacts(){
        new ContactListScreen(driver)
                .removeAllContact()
                .isNoContactHere();
    }
}
