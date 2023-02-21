package tests;

import config.AppiumConfig;
import models.Auth;
import org.testng.annotations.BeforeClass;
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

    //providerContactData

    @Test
    public void removeOneContactSuccess(){
        new ContactListScreen(driver)
                .removeOneContact(2);
    }
}
