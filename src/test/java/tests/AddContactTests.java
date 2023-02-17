package tests;

import config.AppiumConfig;
import models.Auth;
import models.Contact;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;
import screens.AuthenticationScreen;
import screens.ContactListScreen;

import java.util.Random;

public class AddContactTests extends AppiumConfig {

    @BeforeMethod
    public void precondition(){
        new AuthenticationScreen(driver)
                .fillLoginRegistrationForm(Auth.builder().email("noa@gmail.com").password("Nnoa12345$").build())
                .submitLogin()
                .isContactListActivityDisplayed();
    }

    @Test
    public void addNewContactSuccess(){
        int i = new Random().nextInt(1000)+1000;
        Contact contact = Contact.builder()
                .name("Bart")
                .lastName("Simpson")
                .email("bart"+i+"@mail.com")
                .phone("1234512345"+i)
                .address("NY")
                .description("Friend").build();

        new ContactListScreen(driver)
                .openContactForm()
                .fillContactForm(contact)
                .submitContactForm();
    }
}
