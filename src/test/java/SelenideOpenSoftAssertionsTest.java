import com.codeborne.selenide.Configuration;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Test;

import static com.codeborne.selenide.Condition.text;
import static com.codeborne.selenide.Selectors.byText;
import static com.codeborne.selenide.Selenide.*;

public class SelenideOpenSoftAssertionsTest {

    @BeforeAll
    static void beforeAll() {
        Configuration.browserSize = "1920x1080";
        Configuration.baseUrl = "https://github.com";
        Configuration.pageLoadStrategy = "eager";
    }

    @Test
    void openSoftAssertionsTest() {
        open("/selenide/selenide");
        $("#wiki-tab").click();
        $("input#wiki-pages-filter").setValue("SoftAssertions");
        $(".filterable-active").shouldHave(text("SoftAssertions"));
        $(".filterable-active").$(byText("SoftAssertions")).click();
        $$(".markdown-heading .heading-element")
                .findBy(text("3. Using JUnit5 extend test class:"))
                .parent()
                .sibling(0)
                .shouldHave(text(
                    "@ExtendWith({SoftAssertsExtension.class})\n" +
                            "class Tests {\n" +
                            "  @Test\n" +
                            "  void test() {\n" +
                            "    Configuration.assertionMode = SOFT;\n" +
                            "    open(\"page.html\");\n" +
                            "\n" +
                            "    $(\"#first\").should(visible).click();\n" +
                            "    $(\"#second\").should(visible).click();\n" +
                            "  }\n" +
                            "}"));
    }
}
