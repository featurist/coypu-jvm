package coypu.acceptanceTests;
/// <summary>
/// Simple examples for each API method - to show usage and check everything is wired up properly
/// </summary>

import coypu.*;
import org.junit.AfterClass;
import org.junit.Before;
import org.junit.BeforeClass;
import org.junit.Test;
import org.openqa.selenium.WebElement;

import java.io.File;
import java.io.IOException;
import java.util.List;
import java.util.regex.Pattern;

import static org.hamcrest.CoreMatchers.equalTo;
import static org.hamcrest.core.Is.is;
import static org.junit.Assert.*;

public class ApiExamples
{
    private static BrowserSession browser;
    private static SessionConfiguration sessionConfiguration;

    @BeforeClass
    public static void setUpFixture()
    {
        sessionConfiguration = new SessionConfiguration();
        sessionConfiguration.Timeout = TimeSpan.fromMilliseconds(2000);
    }

    @AfterClass
    public static void tearDown()
    {
        browser.dispose();
    }

    @Before
    public void setUp()  {
        ensureBrowser();
        reloadTestPageWithDelay();
    }

    private void ensureBrowser() {
        if (browser == null || browser.wasDisposed()){
            browser = new BrowserSession(sessionConfiguration);
        }
    }

    private void applyAsyncDelay()  {
        // Hide the HTML then bring back after a short delay to test robustness
        browser.executeScript("window.holdIt = document.body.innerHTML;document.body.innerHTML = ''");
        browser.executeScript("setTimeout(function() {document.body.innerHTML = window.holdIt},250)");
    }

    public void reloadTestPage()
    {
        browser.visit(InteractionTestsPage());
    }

    public static String InteractionTestsPage() {
        return TestPage("InteractionTestsPage.htm");
    }

    public static String TestPage(String page) {
        String directory = new File(".").getAbsolutePath();
        return "file:///" + directory + "/src/test/java/coypu/acceptanceTests/html/" + page;
    }

    private void reloadTestPageWithDelay()  {
        reloadTestPage();
        applyAsyncDelay();
    }

    @Test
    public void acceptModalDialog_example()  {
        try {
            browser.clickLink("Trigger an alert");
            assertThat(browser.hasDialog("You have triggered an alert and this is the text."),is(true));

            browser.acceptModalDialog(null);
            assertThat(browser.hasNoDialog("You have triggered an alert and this is the text."),is(true));
        }
        finally {
            browser.dispose();
        }
    }

    @Test
    public void cancelModalDialog_example()  {
        try {
            browser.clickLink("Trigger a confirm");
            browser.cancelModalDialog();
            browser.findLink("Trigger a confirm - cancelled").now();
        }
        finally{
            browser.dispose();
        }
    }

    @Test
    public void check_example()  {
        browser.check("uncheckedBox");
        assertTrue(browser.findField("uncheckedBox").getSelected());
    }

    @Test
    public void uncheck_example()  {
        browser.uncheck("checkedBox");
        assertFalse(browser.findField("checkedBox").getSelected());
    }

    @Test
    public void choose_example()  {
        browser.choose("chooseRadio1");

        assertTrue(browser.findField("chooseRadio1").getSelected());

        browser.choose("chooseRadio2");

        assertTrue(browser.findField("chooseRadio2").getSelected());
        assertFalse(browser.findField("chooseRadio1").getSelected());
    }

    @Test
    public void click_example()  {
        ElementScope element = browser.findButton("clickMeTest",null);
        assertThat(browser.findButton("clickMeTest").getValue(), is(equalTo("Click me")));

        element.click(null);
        assertThat(browser.findButton("clickMeTest").getValue(), is(equalTo("Click me - clicked")));
    }



    @Test
    public void clickButton_example()  {
        browser.clickButton("clickMeTest",null);
        assertThat(browser.findButton("clickMeTest").getValue(), is(equalTo("Click me - clicked")));
    }

    @Test
    public void clickLink_example()  {
        try {
        browser.clickLink("Trigger a confirm");
        browser.cancelModalDialog();
        }
        finally {
            browser.dispose();
        }
    }

    @Test
    public void executeScript_example()  {
        reloadTestPage();
        assertThat(browser.executeScript("return document.getElementById('firstButtonId').innerHTML;"),
                    is(equalTo("first button")));
    }

    @Test
    public void fillInWith_example()  {
        browser.fillIn("containerLabeledTextInputFieldName").with("New text input value");
        assertThat(browser.findField("containerLabeledTextInputFieldName").getValue(), is(equalTo("New text input value")));
    }

    @Test
    public void findAllCss_example()  {
        reloadTestPage();

        final String shouldFind = "#inspectingContent ul#cssTest li";
        List<ElementFound> all = browser.findAllCss(shouldFind);
        assertThat(all.size(), is(equalTo(3)));
        assertThat(all.get(1).getText(), is(equalTo("two")));
        assertThat(all.get(2).getText(), is(equalTo("Me! Pick me!")));
    }

    @Test
    public void findAllXPath_example()  {
        reloadTestPage();

        final String shouldFind = "//*[@id='inspectingContent']//ul[@id='cssTest']/li";
        List<ElementFound> all = browser.findAllXPath(shouldFind);
        assertThat(all.size(), is(equalTo(3)));
        assertThat(all.get(1).getText(), is(equalTo("two")));
        assertThat(all.get(2).getText(), is(equalTo("Me! Pick me!")));
    }

    @Test
    public void findButton_example()  {
        assertThat(browser.findButton("Click me").getId(), is(equalTo("clickMeTest")));
    }

    @Test
    public void findCss_example()  {
        ElementScope first = browser.findCss("#inspectingContent ul#cssTest li");
        assertThat(first.getText(), is(equalTo("one")));
    }

    @Test
    public void findXPath_example()  {
        ElementScope first = browser.findXPath("//*[@id='inspectingContent']//ul[@id='cssTest']/li");
        assertThat(first.getText(), is(equalTo("one")));
    }

    @Test
    public void findField_examples()  {
        assertThat(browser.findField("text input field linked by for").getId(), is(equalTo("forLabeledTextInputFieldId")));
        assertThat(browser.findField("checkbox field in a label container").getId(), is(equalTo("containerLabeledCheckboxFieldId")));
        assertThat(browser.findField("containerLabeledSelectFieldId").getName(), is(equalTo("containerLabeledSelectFieldName")));
        assertThat(browser.findField("containerLabeledPasswordFieldName").getId(), is(equalTo("containerLabeledPasswordFieldId")));
    }

    @Test
    public void findFieldset_example()  {
        assertThat(browser.findFieldset("Scope 1").getId(), is(equalTo("fieldsetScope1")));
    }

    @Test
    public void findId_example()  {
        assertThat(browser.findId("containerLabeledSelectFieldId").getName(), is(equalTo("containerLabeledSelectFieldName")));
    }

    @Test
    public void findLink_example()  {
        assertThat(browser.findLink("Trigger an alert").getId(), is(equalTo("alertTriggerLink")));
    }

    @Test
    public void findSection_example()  {
        assertThat(browser.findSection("Inspecting Content").getId(), is(equalTo("inspectingContent")));
        assertThat(browser.findSection("Div Section Two h2 with link").getId(), is(equalTo("divSectionTwoWithLink")));
    }

    @Test
    public void selectFrom_example()  {
        ElementScope textField = browser.findField("containerLabeledSelectFieldId");
        assertThat(textField.getSelectedOption(), is(equalTo("select two option one")));

        browser.select("select2value2").from("containerLabeledSelectFieldId");

        textField = browser.findField("containerLabeledSelectFieldId");
        assertThat(textField.getSelectedOption(), is(equalTo("select two option two")));
    }

    @Test
    public void has_example()  {
        assertTrue(browser.has(browser.findSection("Inspecting Content")));
        assertFalse(browser.has(browser.findCss("#no-such-element")));
    }

    @Test
    public void hasNo_example()  {
        browser.executeScript("document.body.innerHTML = '<div Id=\"no-such-element\">asdf</div>'");
        assertTrue(browser.hasNo(browser.findCss("#no-such-element")));

        reloadTestPage();
        assertFalse(browser.hasNo(browser.findSection("Inspecting Content")));
    }

    @Test
    public void hasContent_example()  {
        assertTrue(browser.hasContent("This is what we are looking for"));
        assertFalse(browser.hasContent("This is not in the page"));
    }

    @Test
    public void hasNoContent_example()  {
        browser.executeScript("document.body.innerHTML = '<div Id=\"no-such-element\">This is not in the page</div>'");
        assertTrue(browser.hasNoContent("This is not in the page"));

        reloadTestPage();
        assertFalse(browser.hasNoContent("This is what we are looking for"));
    }

    @Test
    public void hasContentMatch_example()  {
        assertThat(browser.hasContentMatch(Pattern.compile("This is what (we are|I am) looking for")), is(true));
        assertThat(browser.hasContentMatch(Pattern.compile("This is ?n[o|']t in the page")), is(false));
    }

    @Test
    public void hasNoContentMatch_example()  {
        browser.executeScript("document.body.innerHTML = '<div Id=\"no-such-element\">This is not in the page</div>'");
        assertTrue(browser.hasNoContentMatch(Pattern.compile("This is ?n[o|']t in the page")));

        reloadTestPage();
        assertFalse(browser.hasNoContentMatch(Pattern.compile("This is what (we are|I am) looking for")));
    }

    @Test
    public void hasCss_example()  {
        assertTrue(browser.hasCss("#inspectingContent ul#cssTest"));
        assertFalse(browser.hasCss("#inspectingContent ul#nope"));
    }

    @Test
    public void hasNoCss_example()  {
        browser.executeScript("document.body.innerHTML = '<div Id=\"inspectingContent\"><ul Id=\"nope\"><li>This is not in the page</li></ul></div>'");
        assertTrue(browser.hasNoCss("#inspectingContent ul#nope"));

        reloadTestPage();
        assertFalse(browser.hasNoCss("#inspectingContent ul#cssTest"));
    }

    @Test
    public void hasXPath_example()  {
        assertTrue(browser.hasXPath("//*[@id='inspectingContent']//ul[@id='cssTest']"));

        reloadTestPage();
        assertFalse(browser.hasXPath("//*[@id='inspectingContent']//ul[@id='nope']"));
    }

    @Test
    public void hasNoXpath_example()  {
        browser.executeScript("document.body.innerHTML = '<div Id=\"inspectingContent\"><ul Id=\"nope\"><li>This is not in the page</li></ul></div>'");
        assertTrue(browser.hasNoXPath("//*[@id='inspectingContent']//ul[@id='nope']"));

        reloadTestPage();
        assertFalse(browser.hasNoXPath("//*[@id='inspectingContent']//ul[@id='cssTest']"));
    }


    @Test
    public void hover_example()  {
        assertThat(browser.findId("hoverOnMeTest").getText(), is(equalTo("Hover on me")));
        browser.findId("hoverOnMeTest").hover();
        assertThat(browser.findId("hoverOnMeTest").getText(), is(equalTo("Hover on me - hovered")));
    }

    @Test
    public void getNative_example()  {
        WebElement button = (WebElement) browser.findButton("clickMeTest").getNative();
        button.click();
        assertThat(browser.findButton("clickMeTest").getValue(), is(equalTo("Click me - clicked")));
    }


    @Test
    public void within_example()  {
        String locatorThatAppearsInMultipleScopes = "scoped text input field linked by for";

        ElementScope expectingScope1 = browser.findId("scope1").findField(locatorThatAppearsInMultipleScopes);
        ElementScope expectingScope2 = browser.findId("scope2").findField(locatorThatAppearsInMultipleScopes);

        assertThat(expectingScope1.getId(), is(equalTo("scope1TextInputFieldId")));
        assertThat(expectingScope2.getId(), is(equalTo("scope2TextInputFieldId")));
    }

    @Test
    public void withinFieldset_example()  {
        String locatorThatAppearsInMultipleScopes = "scoped text input field linked by for";

        ElementScope expectingScope1 = browser.findFieldset("Scope 1")
                                     .findField(locatorThatAppearsInMultipleScopes);

        ElementScope expectingScope2 = browser.findFieldset("Scope 2")
                                     .findField(locatorThatAppearsInMultipleScopes);

        assertThat(expectingScope1.getId(), is(equalTo("scope1TextInputFieldId")));
        assertThat(expectingScope2.getId(), is(equalTo("scope2TextInputFieldId")));
    }

    @Test
    public void withinSection_example()  {
        String selectorThatAppearsInMultipleScopes = "h2";

        ElementScope expectingScope1 = browser.findSection("Section One h1").findCss(selectorThatAppearsInMultipleScopes);
        ElementScope expectingScope2 = browser.findSection("Div Section Two h1").findCss(selectorThatAppearsInMultipleScopes);

        assertThat(expectingScope1.getText(), is(equalTo("Section One h2")));
        assertThat(expectingScope2.getText(), is(equalTo("Div Section Two h2")));
    }

    @Test
    public void withinIFrame_example()  {
        String selectorThatAppearsInMultipleScopes = "scoped button";

        ElementScope expectingScope1 = browser.findIFrame("iframe1").findButton(selectorThatAppearsInMultipleScopes);
        ElementScope expectingScope2 = browser.findIFrame("iframe2").findButton(selectorThatAppearsInMultipleScopes);

        assertThat(expectingScope1.getId(), is(equalTo("iframe1ButtonId")));
        assertThat(expectingScope2.getId(), is(equalTo("iframe2ButtonId")));
    }

    @Test
    public void multiple_interactions_within_iframe_example()  {
        IFrameElementScope iframe = browser.findIFrame("I am iframe one");
        iframe.fillIn("text input in iframe").with("filled in");
        assertThat(iframe.findField("text input in iframe").getValue(), is(equalTo("filled in")));
    }

    @Test
    public void  fillIn_file_example() throws IOException {
        File someLocalFile = new File(".","local.file");
        someLocalFile.createNewFile();
        try
        {
            browser.fillIn("forLabeledFileFieldId").with(someLocalFile.getAbsolutePath());

            ElementScope findAgain = browser.findField("forLabeledFileFieldId");
            assertThat(findAgain.getValue().endsWith("/local.file"), is(true));
        }
        finally
        {
            someLocalFile.delete();
        }
    }

    @Test
    public void consideringInvisibleElements()  {
        Options options =  new Options();
        options.ConsiderInvisibleElements = true;
        browser.findButton("firstInvisibleInputId",options).now();
    }

    @Test
    public void consideringOnlyVisibleElements() {
        try {
            browser.findButton("firstInvisibleInputId").now();
            fail("Expected exception");
        }
        catch (MissingHtmlException ex)
        {
        }
    }

    @Test
    public void windowScoping_example()  {
        BrowserSession mainWindow = browser;

        mainWindow.clickLink("Open pop up window");

        BrowserWindow popUp = mainWindow.findWindow("Pop Up Window");

        assertThat(mainWindow.findButton("scoped button").getId(), is(equalTo("scope1ButtonId")));
        assertThat(popUp.findButton("scoped button").getId(), is(equalTo("popUpButtonId")));

        // And back
        assertThat(mainWindow.findButton("scoped button").getId(), is(equalTo("scope1ButtonId")));
    }

    @Test
    public void customProfile()
    {
        SessionConfiguration sessionConfiguration = new SessionConfiguration();
        sessionConfiguration.Driver = CustomFirefoxProfileSeleniumWebDriver.class;

        BrowserSession custom = new BrowserSession(sessionConfiguration);
        try
        {
            custom.visit("https://www.relishapp.com/");
        }
        finally {
            custom.dispose();
        }
    }
}

