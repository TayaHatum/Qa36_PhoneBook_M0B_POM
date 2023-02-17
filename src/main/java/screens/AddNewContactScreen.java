package screens;

import io.appium.java_client.AppiumDriver;
import io.appium.java_client.MobileElement;
import models.Auth;
import models.Contact;
import org.openqa.selenium.support.FindBy;

public class AddNewContactScreen extends BaseScreen{
    public AddNewContactScreen(AppiumDriver<MobileElement> driver) {
        super(driver);
    }
    @FindBy(id="com.sheygam.contactapp:id/inputName")
    MobileElement nameEditText;
    @FindBy(id="com.sheygam.contactapp:id/inputLastName")
    MobileElement lastNameEditText;
    @FindBy(id="com.sheygam.contactapp:id/inputEmail")
    MobileElement emailEditText;
    @FindBy(id="com.sheygam.contactapp:id/inputPhone")
    MobileElement phoneEditText;
    @FindBy(id="com.sheygam.contactapp:id/inputAddress")
    MobileElement addressEditText;
    @FindBy(id="com.sheygam.contactapp:id/inputDesc")
    MobileElement descriptionEditText;
    @FindBy(id="com.sheygam.contactapp:id/createBtn")
    MobileElement createButton;

    public AddNewContactScreen fillContactForm(Contact contact){
        should(nameEditText,10);
        type1(nameEditText,contact.getName());
        type1(lastNameEditText, contact.getLastName());
        type1(emailEditText, contact.getEmail());
        type1(phoneEditText, contact.getPhone());
        type1(addressEditText, contact.getAddress());
        type1(descriptionEditText, contact.getDescription());
        return this;
    }
    public ContactListScreen submitContactForm(){

        createButton.click();
        return new ContactListScreen(driver);
    }
    public AddNewContactScreen submitContactFormNegative(){

        createButton.click();
        return this;
    }
}
