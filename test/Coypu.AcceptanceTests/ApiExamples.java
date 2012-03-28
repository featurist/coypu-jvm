package Coypu.AcceptanceTests;
/// <summary>
/// Simple examples for each API method - to show usage and check everything is wired up properly
/// </summary>

import Coypu.*;
import org.junit.AfterClass;
import org.junit.Before;
import org.junit.BeforeClass;
import org.junit.Test;
import org.openqa.selenium.WebElement;

import java.util.List;
import java.util.regex.Pattern;

import static org.hamcrest.CoreMatchers.equalTo;
import static org.hamcrest.core.Is.is;
import static org.junit.Assert.*;

public class ApiExamples
{
    private static BrowserSession browser;
    private static Options tempOptions;

    @BeforeClass
    public static void SetUpFixture()
    {
        Configuration configuration = new Configuration();
        configuration.Timeout = TimeSpan.FromMilliseconds(2000);
        browser = new BrowserSession(configuration);
        tempOptions = null;
    }

    @AfterClass
    public static void TearDown()
    {
        browser.Dispose();
    }

    @Before
    public void SetUp()  {
        ReloadTestPageWithDelay();
    }

    private void ApplyAsyncDelay()  {
        // Hide the HTML then bring back after a short delay to test robustness
        browser.ExecuteScript("window.holdIt = document.body.innerHTML;document.body.innerHTML = ''");
        browser.ExecuteScript("setTimeout(function() {document.body.innerHTML = window.holdIt},250)");
    }

    private void ReloadTestPage()
    {
        browser.Visit("file://localhost/Users/adrian/Documents/dev/coypu.java/test/Coypu.AcceptanceTests/html/InteractionTestsPage.htm");
    }

    private void ReloadTestPageWithDelay()  {
        ReloadTestPage();
        ApplyAsyncDelay();
    }

    @Test
    public void AcceptModalDialog_example()  {
        browser.ClickLink("Trigger an alert",tempOptions);
        assertThat(browser.HasDialog("You have triggered an alert and this is the text.", tempOptions),is(true));

        browser.AcceptModalDialog(null);
        assertThat(browser.HasNoDialog("You have triggered an alert and this is the text.", tempOptions),is(true));
    }

    @Test
    public void CancelModalDialog_example()  {
        browser.ClickLink("Trigger a confirm", tempOptions);
        browser.CancelModalDialog(tempOptions);
        browser.FindLink("Trigger a confirm - cancelled", tempOptions).Now();
    }

    @Test
    public void Check_example()  {
        browser.Check("uncheckedBox",tempOptions);
        assertTrue(browser.FindField("uncheckedBox",tempOptions).Selected());
    }

    @Test
    public void Uncheck_example()  {
        browser.Uncheck("checkedBox",tempOptions);
        assertFalse(browser.FindField("checkedBox",tempOptions).Selected());
    }

    @Test
    public void Choose_example()  {
        browser.Choose("chooseRadio1",tempOptions);

        assertTrue(browser.FindField("chooseRadio1",tempOptions).Selected());

        browser.Choose("chooseRadio2",tempOptions);

        assertTrue(browser.FindField("chooseRadio2",tempOptions).Selected());
        assertFalse(browser.FindField("chooseRadio1",tempOptions).Selected());
    }

    @Test
    public void Click_example()  {
        ElementScope element = browser.FindButton("clickMeTest",null);
        assertThat(browser.FindButton("clickMeTest", tempOptions).Value(), is(equalTo("Click me")));

        element.Click(null);
        assertThat(browser.FindButton("clickMeTest",tempOptions).Value(), is(equalTo("Click me - clicked")));
    }



    @Test
    public void ClickButton_example()  {
        browser.ClickButton("clickMeTest",null);
        assertThat(browser.FindButton("clickMeTest", tempOptions).Value(), is(equalTo("Click me - clicked")));
    }

    @Test
    public void ClickLink_example()  {
        browser.ClickLink("Trigger a confirm",tempOptions);
        browser.CancelModalDialog(tempOptions);
    }

    @Test
    public void ExecuteScript_example()  {
        ReloadTestPage();
        assertThat(browser.ExecuteScript("return document.getElementById('firstButtonId').innerHTML;"),
                    is(equalTo("first button")));
    }

    @Test
    public void FillInWith_example()  {
        browser.FillIn("containerLabeledTextInputFieldName",tempOptions).With("New text input value");
        assertThat(browser.FindField("containerLabeledTextInputFieldName",tempOptions).Value(), is(equalTo("New text input value")));
    }

    @Test
    public void FindAllCss_example()  {
        ReloadTestPage();

        final String shouldFind = "#inspectingContent ul#cssTest li";
        List<ElementFound> all = browser.FindAllCss(shouldFind, tempOptions);
        assertThat(all.size(), is(equalTo(3)));
        assertThat(all.get(1).Text(), is(equalTo("two")));
        assertThat(all.get(2).Text(), is(equalTo("Me! Pick me!")));
    }

    @Test
    public void FindAllXPath_example()  {
        ReloadTestPage();

        final String shouldFind = "//*[@id='inspectingContent']//ul[@id='cssTest']/li";
        List<ElementFound> all = browser.FindAllXPath(shouldFind, tempOptions);
        assertThat(all.size(), is(equalTo(3)));
        assertThat(all.get(1).Text(), is(equalTo("two")));
        assertThat(all.get(2).Text(), is(equalTo("Me! Pick me!")));
    }

    @Test
    public void FindButton_example()  {
        assertThat(browser.FindButton("Click me",tempOptions).Id(), is(equalTo("clickMeTest")));
    }

    @Test
    public void FindCss_example()  {
        ElementScope first = browser.FindCss("#inspectingContent ul#cssTest li", tempOptions);
        assertThat(first.Text(), is(equalTo("one")));
    }

    @Test
    public void FindXPath_example()  {
        ElementScope first = browser.FindXPath("//*[@id='inspectingContent']//ul[@id='cssTest']/li",tempOptions);
        assertThat(first.Text(), is(equalTo("one")));
    }

    @Test
    public void FindField_examples()  {
        assertThat(browser.FindField("text input field linked by for",tempOptions).Id(), is(equalTo("forLabeledTextInputFieldId")));
        assertThat(browser.FindField("checkbox field in a label container",tempOptions).Id(), is(equalTo("containerLabeledCheckboxFieldId")));
        assertThat(browser.FindField("containerLabeledSelectFieldId",tempOptions).Name(), is(equalTo("containerLabeledSelectFieldName")));
        assertThat(browser.FindField("containerLabeledPasswordFieldName",tempOptions).Id(), is(equalTo("containerLabeledPasswordFieldId")));
    }

    @Test
    public void FindFieldset_example()  {
        assertThat(browser.FindFieldset("scope 1", tempOptions).Id(), is(equalTo("fieldsetScope1")));
    }

    @Test
    public void FindId_example()  {
        assertThat(browser.FindId("containerLabeledSelectFieldId", tempOptions).Name(), is(equalTo("containerLabeledSelectFieldName")));
    }

    @Test
    public void FindLink_example()  {
        assertThat(browser.FindLink("Trigger an alert",tempOptions).Id(), is(equalTo("alertTriggerLink")));
    }

    @Test
    public void FindSection_example()  {
        assertThat(browser.FindSection("Inspecting Content", tempOptions).Id(), is(equalTo("inspectingContent")));
        assertThat(browser.FindSection("Div Section Two h2 with link",tempOptions).Id(), is(equalTo("divSectionTwoWithLink")));
    }

    @Test
    public void SelectFrom_example()  {
        ElementScope textField = browser.FindField("containerLabeledSelectFieldId",tempOptions);
        assertThat(textField.SelectedOption(), is(equalTo("select two option one")));

        browser.Select("select2value2",tempOptions).From("containerLabeledSelectFieldId");

        textField = browser.FindField("containerLabeledSelectFieldId",tempOptions);
        assertThat(textField.SelectedOption(), is(equalTo("select two option two")));
    }

    @Test
    public void Has_example()  {
        assertTrue(browser.Has(browser.FindSection("Inspecting Content",tempOptions),tempOptions));
        assertFalse(browser.Has(browser.FindCss("#no-such-element",tempOptions),tempOptions));
    }

    @Test
    public void HasNo_example()  {
        browser.ExecuteScript("document.body.innerHTML = '<div id=\"no-such-element\">asdf</div>'");
        assertTrue(browser.HasNo(browser.FindCss("#no-such-element",tempOptions),tempOptions));

        ReloadTestPage();
        assertFalse(browser.HasNo(browser.FindSection("Inspecting Content",tempOptions),tempOptions));
    }

    @Test
    public void HasContent_example()  {
        assertTrue(browser.HasContent("This is what we are looking for",tempOptions));
        assertFalse(browser.HasContent("This is not in the page",tempOptions));
    }

    @Test
    public void HasNoContent_example()  {
        browser.ExecuteScript("document.body.innerHTML = '<div id=\"no-such-element\">This is not in the page</div>'");
        assertTrue(browser.HasNoContent("This is not in the page",tempOptions));

        ReloadTestPage();
        assertFalse(browser.HasNoContent("This is what we are looking for",tempOptions));
    }

    @Test
    public void HasContentMatch_example()  {
        assertThat(browser.HasContentMatch(Pattern.compile("This is what (we are|I am) looking for"), tempOptions), is(true));
        assertThat(browser.HasContentMatch(Pattern.compile("This is ?n[o|']t in the page"), tempOptions), is(false));
    }

    @Test
    public void HasNoContentMatch_example()  {
        browser.ExecuteScript("document.body.innerHTML = '<div id=\"no-such-element\">This is not in the page</div>'");
        assertTrue(browser.HasNoContentMatch(Pattern.compile("This is ?n[o|']t in the page"),tempOptions));

        ReloadTestPage();
        assertFalse(browser.HasNoContentMatch(Pattern.compile("This is what (we are|I am) looking for"), tempOptions));
    }

    @Test
    public void HasCss_example()  {
        assertTrue(browser.HasCss("#inspectingContent ul#cssTest",tempOptions));
        assertFalse(browser.HasCss("#inspectingContent ul#nope",tempOptions));
    }

    @Test
    public void HasNoCss_example()  {
        browser.ExecuteScript("document.body.innerHTML = '<div id=\"inspectingContent\"><ul id=\"nope\"><li>This is not in the page</li></ul></div>'");
        assertTrue(browser.HasNoCss("#inspectingContent ul#nope",tempOptions));

        ReloadTestPage();
        assertFalse(browser.HasNoCss("#inspectingContent ul#cssTest",tempOptions));
    }

    @Test
    public void HasXPath_example()  {
        assertTrue(browser.HasXPath("//*[@id='inspectingContent']//ul[@id='cssTest']",tempOptions));

        ReloadTestPage();
        assertFalse(browser.HasXPath("//*[@id='inspectingContent']//ul[@id='nope']",tempOptions));
    }

    @Test
    public void HasNoXpath_example()  {
        browser.ExecuteScript("document.body.innerHTML = '<div id=\"inspectingContent\"><ul id=\"nope\"><li>This is not in the page</li></ul></div>'");
        assertTrue(browser.HasNoXPath("//*[@id='inspectingContent']//ul[@id='nope']",tempOptions));

        ReloadTestPage();
        assertFalse(browser.HasNoXPath("//*[@id='inspectingContent']//ul[@id='cssTest']",tempOptions));
    }


    @Test
    public void Hover_example()  {
        assertThat(browser.FindId("hoverOnMeTest",tempOptions).Text(), is(equalTo("Hover on me")));
        browser.FindId("hoverOnMeTest",tempOptions).Hover(tempOptions);
        assertThat(browser.FindId("hoverOnMeTest",tempOptions).Text(), is(equalTo("Hover on me - hovered")));
    }

    @Test
    public void Native_example()  {
        WebElement button = (WebElement) browser.FindButton("clickMeTest",tempOptions).Native();
        button.click();
        assertThat(browser.FindButton("clickMeTest",tempOptions).Value(), is(equalTo("Click me - clicked")));
    }


    @Test
    public void Within_example()  {
        String locatorThatAppearsInMultipleScopes = "scoped text input field linked by for";

        ElementScope expectingScope1 = browser.FindId("scope1",tempOptions).FindField(locatorThatAppearsInMultipleScopes,tempOptions);
        ElementScope expectingScope2 = browser.FindId("scope2",tempOptions).FindField(locatorThatAppearsInMultipleScopes,tempOptions);

        assertThat(expectingScope1.Id(), is(equalTo("scope1TextInputFieldId")));
        assertThat(expectingScope2.Id(), is(equalTo("scope2TextInputFieldId")));
    }

    @Test
    public void WithinFieldset_example()  {
        String locatorThatAppearsInMultipleScopes = "scoped text input field linked by for";

        ElementScope expectingScope1 = browser.FindFieldset("scope 1",tempOptions)
                                     .FindField(locatorThatAppearsInMultipleScopes,tempOptions);

        ElementScope expectingScope2 = browser.FindFieldset("scope 2",tempOptions)
                                     .FindField(locatorThatAppearsInMultipleScopes, tempOptions);

        assertThat(expectingScope1.Id(), is(equalTo("scope1TextInputFieldId")));
        assertThat(expectingScope2.Id(), is(equalTo("scope2TextInputFieldId")));
    }

    @Test
    public void WithinSection_example()  {
        String selectorThatAppearsInMultipleScopes = "h2";

        ElementScope expectingScope1 = browser.FindSection("Section One h1",tempOptions).FindCss(selectorThatAppearsInMultipleScopes,tempOptions);
        ElementScope expectingScope2 = browser.FindSection("Div Section Two h1",tempOptions).FindCss(selectorThatAppearsInMultipleScopes,tempOptions);

        assertThat(expectingScope1.Text(), is(equalTo("Section One h2")));
        assertThat(expectingScope2.Text(), is(equalTo("Div Section Two h2")));
    }

    @Test
    public void WithinIFrame_example()  {
        String selectorThatAppearsInMultipleScopes = "scoped button";

        ElementScope expectingScope1 = browser.FindIFrame("iframe1",tempOptions).FindButton(selectorThatAppearsInMultipleScopes,tempOptions);
        ElementScope expectingScope2 = browser.FindIFrame("iframe2",tempOptions).FindButton(selectorThatAppearsInMultipleScopes,tempOptions);

        assertThat(expectingScope1.Id(), is(equalTo("iframe1ButtonId")));
        assertThat(expectingScope2.Id(), is(equalTo("iframe2ButtonId")));
    }

    @Test
    public void Multiple_interactions_within_iframe_example()  {
        IFrameElementScope iframe = browser.FindIFrame("I am iframe one",tempOptions);
        iframe.FillIn("text input in iframe",tempOptions).With("filled in");
        assertThat(iframe.FindField("text input in iframe",tempOptions).Value(), is(equalTo("filled in")));
    }

//    @Test
//    public void  FillIn_file_example()
//    {
//        String someLocalFile = "local.file";
//        try
//        {
//            var directoryInfo = new DirectoryInfo(".");
//            var fullPath = Path.Combine(directoryInfo.FullName, someLocalFile);
//            using (File.Create(fullPath)) { }
//
//            browser.FillIn("forLabeledFileFieldId").With(fullPath);
//
//            var findAgain = browser.FindField("forLabeledFileFieldId");
//            assertThat(findAgain.Value, Is.StringEnding("\\" + someLocalFile));
//        }
//        finally
//        {
//            File.Delete(someLocalFile);
//        }
//    }

    @Test
    public void ConsideringInvisibleElements()  {
        Options options =  new Options();
        options.ConsiderInvisibleElements = true;
        browser.FindButton("firstInvisibleInputId",options).Now();
    }

    @Test
    public void ConsideringOnlyVisibleElements() {
        try {
            browser.FindButton("firstInvisibleInputId", tempOptions).Now();
            fail("Expected exception");
        }
        catch (MissingHtmlException ex)
        {
        }
    }

    @Test
    public void WindowScoping_example()  {
        BrowserSession mainWindow = browser;

        mainWindow.ClickLink("Open pop up window",tempOptions);

        BrowserWindow popUp = mainWindow.FindWindow("Pop Up Window",tempOptions);

        assertThat(mainWindow.FindButton("scoped button",tempOptions).Id(), is(equalTo("scope1ButtonId")));
        assertThat(popUp.FindButton("scoped button",tempOptions).Id(), is(equalTo("popUpButtonId")));

        // And back
        assertThat(mainWindow.FindButton("scoped button",tempOptions).Id(), is(equalTo("scope1ButtonId")));
    }

    @Test
    public void CustomProfile()
    {
        Configuration configuration = new Configuration();
        configuration.Driver = CustomFirefoxProfileSeleniumWebDriver.class;

        BrowserSession custom = new BrowserSession(configuration);
        try
        {
            custom.Visit("https://www.relishapp.com/");
        }
        finally {
            custom.Dispose();
        }
    }
}

