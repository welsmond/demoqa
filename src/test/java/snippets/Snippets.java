package snippets;

import com.codeborne.selenide.*;
import org.openqa.selenium.Cookie;
import org.openqa.selenium.Keys;
import org.openqa.selenium.WebDriver;

import java.io.File;
import java.io.FileNotFoundException;

import static com.codeborne.selenide.CollectionCondition.*;
import static com.codeborne.selenide.Condition.*;
import static com.codeborne.selenide.Selenide.*;

public class Snippets {
    void browser_command_examples(){
        open("https://google.com"); //absolute url
        open("/customers/orders"); //relative url в случае задания абсолютного в предусловиях
        open("/", AuthenticationType.BASIC, new BasicAuthCredentials("","",""));

        Selenide.back();
        Selenide.refresh();

        Selenide.clearBrowserCookies();
        Selenide.clearBrowserLocalStorage();

        Selenide.confirm(); //OK для js алерта
        Selenide.dismiss();

        Selenide.closeWindow();
        Selenide.closeWebDriver();

        Selenide.switchTo().frame("new");
        Selenide.switchTo().defaultContent();

        Selenide.switchTo().window("The Internet");

        var cookie=new Cookie("foo","bar");
        WebDriverRunner.getWebDriver().manage().addCookie(cookie);

    }

    void selectors_examples() {
        $("div").click();
        element("div").click();

        $("div",2).click();

        $x("//h1/div").click();

    }

    void actions_examples(){
        $("").click();
        $("").doubleClick();
        $("").contextClick(); //rightclick on element

        $("").hover(); //держать указатель мыши на элементе, но не нажимать

        $("").setValue("text");
        $("").append("text"); //добавлять текст в поле
        $("").clear(); // лучше нижнее, тк клир работает неправильно иногда
        $("").setValue(""); // тоже как очищение

        $("div").sendKeys("c"); //hotkey C on element
        actions().sendKeys("c").perform(); //hotkey on whole app
        actions().sendKeys(Keys.chord(Keys.CONTROL,"f")).perform(); //ctrl+f
        $("html").sendKeys(Keys.chord(Keys.CONTROL), "f");

        $("").pressEnter();
        $("").pressEscape();
        $("").pressTab();

        //сложные команды с мышкой и клавиатурой, примеры ниже
        actions().moveToElement($("div")).clickAndHold().moveByOffset(300, 200).release().perform();

        //только со старыми дропдаунами
        $("").selectOption("dropdown_option");
        $("").selectRadio("radio_option"); //с чекбоксами проще кликать сразу на текст, т.к. они так тоже работают


    }

    void assertions_examples(){
        //$("").shouldBe(visible);
        $("").should(appear);
        $("").shouldBe(visible);
    }

    void conditions_examples() {

        $("").shouldHave(text("abc")); //нестрогая проверка на текст
        $("").shouldHave(exactText("abc")); //строгая проверка на текст по составу
        $("").shouldHave(exactTextCaseSensitive("abc")); //строгая проверка на текст по составу и апппериннеркейсу
        $("").should(matchText("[0-9]abc$")); //проверка маски текста по регэкспу

        $("").shouldHave(cssClass("red"));
        $("").shouldHave(cssValue("font-size","123")); //так можно проверить размер и стиль текста

        $("").shouldHave(value("25"));
        $("").shouldHave(exactValue("25"));
        $("").shouldBe(empty);
        $("").shouldHave(attribute("disabled"));
        $("").shouldHave(attribute("name","example"));
        $("").shouldHave(attributeMatching("name","[0-9]abc$"));

        $("").shouldBe(checked); //for checkboxes



    }

    void collections_examples(){
        $$("div"); //does nothing
        $$x("//div"); //byXpath

        //selections
        $$("div").filterBy(text("03")).shouldHave(size(1));
        $$("div").excludeWith(text("03")).shouldHave(size(1));

        $$("div").first().click();
        $$("div").get(1).click(); //the SECOND element (arrays starts with 0)
        $$("div").findBy(text("123")).click(); //finds first

        //assertions
        $$("").shouldHave(size(0));
        $$("").shouldBe(CollectionCondition.empty); //the same

        $$("").shouldHave(texts("Alfa", "Beta", "Gamma"));
        $$("").shouldHave(exactTextsCaseSensitiveInAnyOrder("Alfa", "Beta", "Gamma"));

        $$("").shouldHave(itemWithText("Gamma")); //only one text


        $$("").shouldHave(sizeGreaterThan(0));
    }

    void file_operation_examples() throws FileNotFoundException{

        File file1 = $("a.fileLink").download(); //only for <a href=""...>
        File file2 = $("div").download(DownloadOptions.using(FileDownloadMode.FOLDER));

        File file = new File("src/test/resourses/rg.jpg");
        $("#file-upload").uploadFile(file);
        $("#file-upload").uploadFromClasspath("rg.jgp"); //dont forget to submit
    }
}
