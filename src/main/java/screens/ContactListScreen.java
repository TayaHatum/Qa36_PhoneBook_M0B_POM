package screens;

import io.appium.java_client.AppiumDriver;
import io.appium.java_client.MobileElement;
import io.appium.java_client.TouchAction;
import io.appium.java_client.android.AndroidElement;
import io.appium.java_client.touch.offset.PointOption;
import models.Contact;
import org.openqa.selenium.By;
import org.openqa.selenium.Rectangle;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.testng.Assert;

import java.util.List;

public class ContactListScreen extends BaseScreen{
    public ContactListScreen(AppiumDriver<MobileElement> driver) {
        super(driver);
    }

    @FindBy(xpath = "//*[@resource-id='com.sheygam.contactapp:id/action_bar']/android.widget.TextView")
    MobileElement activityTextView;

    @FindBy(xpath = "//*[@content-desc='More options']")
    MobileElement moreOption;
    @FindBy(id="com.sheygam.contactapp:id/title")
    MobileElement logoutButton;

    @FindBy(xpath = "//*[@resource-id='com.sheygam.contactapp:id/add_contact_btn']")
    MobileElement plusButton;
    @FindBy(id="com.sheygam.contactapp:id/rowName")
    List<MobileElement> nameList;
    @FindBy(id="com.sheygam.contactapp:id/rowPhone")
    List<MobileElement> phoneList;
    @FindBy(id="com.sheygam.contactapp:id/rowContainer")
    List<MobileElement> contacts;
    @FindBy(id="android:id/button1")
    MobileElement yesButton;
    @FindBy (id ="com.sheygam.contactapp:id/emptyTxt")
    MobileElement noContactHereTextView;
    int before;
    int after;

    public int removeOneContact(int count){
        MobileElement contact = contacts.get(0);
       // MobileElement contact = contacts.get(count);
        System.out.println("before -->"+contacts.size());
        int before = contacts.size();
        System.out.println("Screen Width:  " +driver.manage().window().getSize().getWidth());
        System.out.println("Screen Height:  " +driver.manage().window().getSize().getHeight());
        Rectangle rectangle = contact.getRect();

        int x_a=rectangle.getX()+rectangle.getWidth()/8;
        int y_a=rectangle.getY()+rectangle.getHeight()/2;

        int x_b=rectangle.getX()+(rectangle.getWidth()/8)*7;
        int y_b=y_a;


        TouchAction<?> touchAction = new TouchAction<>(driver);
        touchAction.longPress(PointOption.point(x_a,y_a))
                .moveTo(PointOption.point(x_b,y_b)).release().perform();
        should(yesButton,3);
        yesButton.click();

        pause(4000);
        System.out.println("after -->"+contacts.size());
        int after = contacts.size();
        return before-after;
    }
    public ContactListScreen removeOneContactBase(int count){
        //MobileElement contact = contacts.get(0);
         MobileElement contact = contacts.get(count);
         before=contacts.size();
        System.out.println("before -->"+contacts.size());
        System.out.println("Screen Width:  " +driver.manage().window().getSize().getWidth());
        System.out.println("Screen Height:  " +driver.manage().window().getSize().getHeight());
        Rectangle rectangle = contact.getRect();

        int x_a=rectangle.getX()+rectangle.getWidth()/8;
        int y_a=rectangle.getY()+rectangle.getHeight()/2;

        int x_b=rectangle.getX()+(rectangle.getWidth()/8)*7;
        int y_b=y_a;


        TouchAction<?> touchAction = new TouchAction<>(driver);
        touchAction.longPress(PointOption.point(x_a,y_a))
                .moveTo(PointOption.point(x_b,y_b)).release().perform();
        should(yesButton,3);
        yesButton.click();

        pause(4000);
        System.out.println("after -->"+contacts.size());
        after =contacts.size();
        return this;
    }
    public ContactListScreen isListSizeLessOne(){

        Assert.assertEquals(before-after,1);
        return this;
    }
    public ContactListScreen removeOneContactAssert(int count){
        MobileElement contact = contacts.get(0);
        // MobileElement contact = contacts.get(count);
        System.out.println("before -->"+contacts.size());
        int before = contacts.size();
        System.out.println("Screen Width:  " +driver.manage().window().getSize().getWidth());
        System.out.println("Screen Height:  " +driver.manage().window().getSize().getHeight());
        Rectangle rectangle = contact.getRect();

        int x_a=rectangle.getX()+rectangle.getWidth()/8;
        int y_a=rectangle.getY()+rectangle.getHeight()/2;

        int x_b=rectangle.getX()+(rectangle.getWidth()/8)*7;
        int y_b=y_a;


        TouchAction<?> touchAction = new TouchAction<>(driver);
        touchAction.longPress(PointOption.point(x_a,y_a))
                .moveTo(PointOption.point(x_b,y_b)).release().perform();
        should(yesButton,3);
        yesButton.click();

        pause(4000);
        System.out.println("after -->"+contacts.size());
        int after = contacts.size();
        Assert.assertEquals(before-after,1);

        return this;
    }
    private void checkContacts(List<MobileElement> list,String text){

        boolean isPresent = false;
        for(MobileElement el:list){
           if( el.getText().contains(text)){
               isPresent = true;
               break;
           }
        }
        Assert.assertTrue(isPresent);
    }

    public ContactListScreen isContactAddedByNameLastName(String name, String lastName){
        isShouldHave(activityTextView,"Contact list",10);
        System.out.println(nameList.size());
        checkContacts(nameList,name+" "+lastName);

        return this;
    }

    public ContactListScreen isContactAddedByPhone(String phone){
        isShouldHave(activityTextView,"Contact list",10);
        checkContacts(phoneList,phone);
        return this;
    }

    public AddNewContactScreen openContactForm(){
       if(activityTextView.getText().equals("Contact list")) {
        //if(plusButton.isDisplayed()){
            plusButton.click();
        }
        return new AddNewContactScreen(driver);
    }


    public boolean isContactListActivityDisplayed (){
        return isShouldHave(activityTextView,"Contact list",15);
    }

    public AuthenticationScreen logout(){

        if(activityTextView.getText().equals("Contact list")) {
            moreOption.click();
            should(logoutButton, 2);
            logoutButton.click();
        }

        return new AuthenticationScreen(driver);
    }

    public ContactListScreen provideContacts(){
        if(contacts.size()<3){
            for (int i = 0; i < 2; i++) {
                addContact();
            }

        }
        return this;
    }

    public void addContact(){
        int i = (int)(System.currentTimeMillis()/1000)%3600;
        Contact contact =Contact.builder()
                .name("Mary"+i)
                .lastName("Wolf"+i)
                .email("wolf"+i+"@mail.com")
                .phone("034567"+i)
                .address("Rehovot")
                .description("The best friend").build();
        new ContactListScreen(driver)
                .openContactForm()
                .fillContactForm(contact)
                .submitContactForm();

    }

    public ContactListScreen removeAllContact() {
        pause(4000);
        while (driver.findElements(By.xpath("//*[@resource-id='com.sheygam.contactapp:id/rowContainer']")).size()>0){
            removeOneContact(0);
        }

        return this;
    }

    public ContactListScreen isNoContactHere(){
        shouldHave(noContactHereTextView,"No Contacts. Add One more!",10);
        return this;
    }
}
