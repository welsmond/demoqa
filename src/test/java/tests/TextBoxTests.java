package tests;

import com.codeborne.selenide.Condition;
import com.codeborne.selenide.Configuration;
import org.junit.jupiter.api.AfterAll;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Test;

import static com.codeborne.selenide.Condition.text;
import static com.codeborne.selenide.Selenide.*;

public class TextBoxTests {

    @BeforeAll
    static void beforeAll(){
        Configuration.browserSize = "1920x1080";
        Configuration.baseUrl = "https://demoqa.com";
        Configuration.pageLoadStrategy = "eager";
        //Configuration.holdBrowserOpen = true;
        Configuration.timeout = 5000; // default 4000
    }



    @Test
    void fillFormTests() {
        open("/text-box");
        $("#userName").setValue("Vladislav");
        $("#userEmail").setValue("alex@egorov.com");
        $("#currentAddress").setValue("Some street 1");
        $("#permanentAddress").setValue("Another street 1");
        $("#submit").click();
        $("#output #name").shouldHave(text("Vladislav"));
        $("#output #email").shouldHave(text("alex@egorov.com"));
        $("#output #currentAddress").shouldHave(text("Some street 1"));
        $("#output #permanentAddress").shouldHave(text("Another street 1"));
    }

    @Test
    void fillPracticeFormTests(){
        open("/automation-practice-form");
        $("#firstName").setValue("Vladislav");
        $("#lastName").setValue("Senko");
        $("#userEmail").setValue("senjkove@gmail.com");
        $("#permanentAddress").setValue("Another street 1");
        $("#submit").click();
        $("#output #name").shouldHave(text("Vladislav"));
        $("#output #email").shouldHave(text("alex@egorov.com"));
        $("#output #currentAddress").shouldHave(text("Some street 1"));
        $("#output #permanentAddress").shouldHave(text("Another street 1"));

    }
    @Test
    void checkDemoFormTest(){
        open("/automation-practice-form");
        $("#firstName").setValue("Vlad");
        $("#lastName").setValue("Senko");
        $("#userEmail").setValue("alexalex@gmail.com");
        $("label[class=custom-control-label]").click();
        $("#userNumber").setValue("89999999999");
        $("#dateOfBirthInput").click();
        $("[class=react-datepicker__year-select]>[value='2000']").click();
        $("[class=react-datepicker__month-select]>[value='3']").click();
        $(".react-datepicker__month .react-datepicker__day--024").click();
        $("#subjectsContainer").click();
        $("#subjectsInput").setValue("E").pressEnter();
        $("#subjectsInput").setValue("ch").pressEnter();
        $("#hobbies-checkbox-1+label").click();
        $("#uploadPicture").scrollIntoView(true);
        $("#uploadPicture").uploadFromClasspath("rg.jpg");
        $("#currentAddress").setValue("USA");
        $("#react-select-3-input").setValue("c").pressEnter();
        $("#react-select-4-input").setValue("g").pressEnter();
        $("#submit").click();

        $("[class=table-responsive]").shouldHave(Condition.text("Vlad Senko"));
        $("[class=table-responsive]").shouldHave(Condition.text("alexalex@gmail.com"));
        $("[class=table-responsive]").shouldHave(Condition.text("24 April,2000"));
        $("[class=table-responsive]").shouldHave(Condition.text("Male"));
        $("[class=table-responsive]").shouldHave(Condition.text("8999999999"));
        $("[class=table-responsive]").shouldHave(Condition.text("English"));
        $("[class=table-responsive]").shouldHave(Condition.text("Chemistry"));
        $("[class=table-responsive]").shouldHave(Condition.text("Sports"));
        $("[class=table-responsive]").shouldHave(Condition.text("rg.jpg"));
        $("[class=table-responsive]").shouldHave(Condition.text("USA"));
        $("[class=table-responsive]").shouldHave(Condition.text("NCR Gurgaon"));
        $("#closeLargeModal").click();





    }
}
