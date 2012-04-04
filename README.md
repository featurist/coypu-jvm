# Coypu

Coypu supports browser automation on the JVM to help make tests readable, robust, fast to write and less tightly coupled to the UI. If your tests are littered with sleeps, retries, complex XPath expressions and IDs dug out of the source with FireBug then Coypu might help.

Discuss Coypu and get help on the [Google Group](http://groups.google.com/group/coypu)

## Coypu is
* A robust wrapper for browser automation tools on the JVM, such as Selenium WebDriver that eases automating ajax-heavy websites and reduces coupling to the HTML, CSS & JS
* A more intuitive DSL for interacting with the browser in the way a human being would, inspired by the ruby framework Capybara - http://github.com/jnicklas/capybara

### Maven

To use Coypu from Maven add the following dependencies in your [POM](http://maven.apache.org/pom.html):

```xml
<dependency>
    <groupId>net.featurist</groupId>
    <artifactId>coypu</artifactId>
    <version>0.8.0-SNAPSHOT</version>
    <scope>test</scope>
</dependency>
```

## Using Coypu

#### Browser session

Open a browser session like so:

	import coypu.BrowserSession;

	BrowserSession browser = new BrowserSession();
	
When you are done with the browser session:

	browser.dispose();
	
### Configuration

To configure Coypu pass an instance of `SessionConfiguration` to the constructor of BrowserSession:

    SessionConfiguration sessionConfiguration = new SessionConfiguration();
    sessionConfiguration.AppHost = "relishapp.com";

    BrowserSession browserSession = new BrowserSession(sessionConfiguration);

#### Website under test

Configure the website you are testing as follows

	SessionConfiguration sessionConfiguration = new SessionConfiguration();
	sessionConfiguration.AppHost = "autotrader.co.uk";
	sessionConfiguration.Port = "5555";
	sessionConfiguration.SSL = true|false;

If you don't specify any of these, Coypu will default to http, localhost and port 80.

#### Driver

Coypu drivers implement the `coypu.Driver` interface and read the `SessionConfiguration.Browser` setting to pick the correct browser.

Choose your driver/browser combination like so:

	sessionConfiguration.Driver = SeleniumWebDriver.class;
	sessionConfiguration.Browser = Browser.Firefox;
 
These settings are the default sessionConfiguration.

##### Selenium WebDriver
`coypu.Drivers.Selenium.SeleniumWebDriver` tracks the latest version of WebDriver and supports Firefox, IE (slowest) and Chrome (Fastest) as the browser. Any other Selenium implementation of RemoteWebDriver can be configured by subclassing `SeleniumWebDriver` and passing an instance of RemoteWebDriver to the base constructor.

<!--TODO: Maven... -->
The Selenium Driver is included in the Coypu package.

###### Firefox
WebDriver is generally stable with the last but one release of FireFox until they have had time to make a subsequent release, usually 1-2 weeks.

For example, as of 02 Apr 2012 the lastest version of WebDriver was 2.20. Firefox 11 was released after WebDriver 2.20, so you should not upgrade to Firefox 11 until WebDriver 2.21 is released at the earliest.

###### Internet Explorer

You will need the new standalone InternetExplorerDriver.exe in your PATH or in the bin of your test project. [Download from google code](http://code.google.com/p/selenium/wiki/InternetExplorerDriver)

Only IE9 supports CSS & XPath and certain HTML features. The WatiN driver is notably faster in IE than the WebDriver IE driver, so is recommended for testing in Internet Explorer. The WatiN driver comes in a seperate package (see below).

###### Chrome
<!-- TODO better step-by-step instructions -->
You will need the chromedriver binary. [Download from google code](http://code.google.com/p/chromedriver/downloads/list)

###### HtmlUnit
To run the headless HtmlUnit driver for Selenium you just need to run up HtmlUnit:

1. Configure Coypu for HtmlUnit/HtmlUnitWithJavascript: `sessionConfiguration.Browser = Drivers.Browser.HtmlUnit|HtmlUnitWithJavascript;`
2. Download the Selenium Server (selenium-server-standalone-x.x.x.jar) from [Selenium HQ](http://seleniumhq.org/download)
3. Run "java -jar selenium-server-standalone-x.x.x.jar"

And off you go.

###### Android
Selenium WebDriver also supports Android so long as you have the Android remote driver running (Selenium defaults to port 8080).

<!-- TODO: look into wattage
##### WatiN

There is a seperate package called Coypu.WatiN containing a driver for WatiN which is now almost fully featured (thanks to citizenmatt) and runs considerably faster than the WebDriver IE driver.

This driver only supports Internet Explorer as the browser.

You will need to nuget `Install-Package Coypu.Watin` and then configure Coypu like so:

	sessionConfiguration.Driver = typeof (Coypu.Drivers.Watin.WatiNDriver);
	sessionConfiguration.Browser = Drivers.Browser.InternetExplorer;
-->

#### Waits, retries and timeout

Most of the methods in the Coypu DSL are automatically retried on any driver error until a configurable timeout is reached. It just catches exceptions and retries -- mainly the `Drivers.MissingHtmlException` that a driver should throw when it cannot find something, but also any internal driver errors that the driver might throw up.

This is a rather blunt approach that goes well beyond WebDriver's ImplicitWait, for example, but the only truly robust strategy for heavily asynchronous websites, where elements are flying in and out of the DOM constantly, that I have found.

All methods use this wait and retry strategy *except*: `visit()`, `findAllCss()` and `findAllXPath()` which call the driver once immediately.

Setup timeout/retry like so:

	sessionConfiguration.Timeout = TimeSpan.fromSeconds(1);
	sessionConfiguration.RetryInterval = TimeSpan.fromSeconds(0.1);
	
These settings are the default sessionConfiguration.

All methods in the API take an optional final parameter of a `Options`. By passing this in you can override these timing settings for just that call.

### Visible elements

Coypu drivers filter out any elements that are not visible on the page -- this includes hidden inputs. 

Non-visible elements can get in the way of finding the elements that we are really looking for and cause often errors when trying to interact with them. 

What we are really trying to do here is interact with the browser in the way that a human would. It's probably best to avoid hacking around with elements not accessible to the user where possible to avoid invalidating our tests in any case.

#### However...

If you really need this for some intractable problem where you cannot control the browser without cheating like this, then there is `sessionConfiguration/options.ConsideringInvisibleElements = true` which overrides this restriction.

### Missing features

There is plenty Coypu doesn't cover yet, what there is is pretty well tested however, and hopefully simple to extend.

If there's something you need that's not part of the DSL then please you may need to dive into the native driver which you can always do by casting the native driver to whatever underlying driver you know you are using:

	RemoteWebDriver selenium = (RemoteWebDriver) browserSession.getNative();
	
But if you need to do this, please consider forking Coypu, adding what you need and sending a pull request. Thanks!

### DSL

Here are some examples to get you started using Coypu
	
#### Navigating
	
	browser.visit("/used-cars")
	
If you need to step away and visit a site outside of the `SessionConfiguration.AppHost` then you can use a fully qualified Uri:

	browser.visit("https://gmail.com")
	browser.visit("file:///C:/users/adiel/localstuff.htm")

#### Completing forms

Form fields are found by label text, partial label text, id, name, placeholder or radio button value
	
	// Drop downs
	browser.select("toyota").from("make");
	
	// Text inputs
	browser.fillIn("keywords").with("hybrid");
	
	// File inputs
	browser.fillIn("Avatar").with("c:\\users\\adiel\\photos\\avatar.jpg");
	
	// Radio button lists
	browser.choose("Trade");
	browser.choose("Private");
	
	// Checkboxes
	browser.check("Additional ads")
	browser.uncheck("Additional ads")	
	
#### Clicking

Buttons are found by value/text, id or name. 

	browser.clickButton("Search");
	browser.clickButton("search-used-vehicles");

Links are found by the text of the link

	browser.clickLink("Reset search");

Click any other element by calling the Click method on the returned `ElementScope`:
	
	browser.findCss("span#i-should-be-a-link").Click();

In this example, due to the way Coypu defers execution of finders, the FindCss will also be retried, should the Click fail. For example if the DOM is shifting under the driver's feet, the link may have become stale after it is found but before the click is actioned while part of the page is reloaded.

This introduces the idea of `Scope`. The browser.Find methods return a Scope on which you may perform actions, or make further scoped queries. There is more on scope below.
	
#### Finding single elements

Find methods return a `ElementScope` that is scoped to the first matching element. The locator arguments are case sensitive.

	ElementScope element = browser.findField("Username");
	ElementScope element = browser.findButton("GO");
	ElementScope element = browser.findLink("Home");
	ElementScope element = browser.findCss("table#menu");
	ElementScope element = browser.findXPath("Username");
	
You can read attributes of these elements like so:

    browser.findLink("Home").getId();
    browser.findLink("Home").getText();
    browser.findLink("Home").getAttribute("href");
    browser.findLink("Home").getAttribute("rel");

#### Finding multiple elements	
	
findAll methods return all matching elements:

	for(ElementFound link : browser.findAllCss("a")) 
	{
		String attributeValue = a.getAttribute("href");
		...
	}

#### Hover

Hover over an element

	browser.findCss("span#hoverOnMe").hover();

#### Fieldsets / Sections

To find this:

	<fieldset>	
		<legend>Advanced search</legend>
		...
	</fieldset>

use this:	
	
	ElementFound element = browser.findFieldset("Advanced search");
	
To find this:

	<div>	
		<h2>Search results</h2>
		...
	</div>

or this:

	<section>
		<h3>Search results</h3>
		...
	</section>

use this:
	
	ElementFound element = browser.findSection("Search results");

**These work particularly well when used as scopes:**

#### Scope

When you want to perform operations only within a particular part of the page, find the scope you want then use this as the scope for further finds and interactions as in the previous fieldset/section example.

    ElementScope advancedSearch = browser.findFieldset("Advanced search");
    ElementScope searchResults = browser.findSection("Search results");

    advancedSearch.fillIn("First name").with("Philip");
    advancedSearch.fillIn("Middle initial").with("J");
    advancedSearch.fillIn("Last name").with("Fry");

    advancedSearch.click("Find");

    Assert.That(searchResults.hasContent("1 friend found"));
    Assert.That(searchResults.hasContent("Philip J Fry"));

The actual finding of the scope is deferred until the driver needs to interact with or find any element inside the Scope. If the scope becomes stale at any time it will be re-found.

**So in the above example, it doesn't matter what happens between clicking 'find' and the search results loading. The search results area could be ripped out of the DOM and refreshed, there could be a full page refresh, or even a pop up window closed and reopened, so long as the session remains active.**

This means you have tests much more loosely coupled to the implementation of your website. Consider the search example above and the possible permutations of HTML and JS that would satisfy that test.

#### Scoping within iframes

To restrict the scope to an iframe, locate the iframe by its id, title or the text of an h1 element within the frame:

	var twitterFrame = browser.findIFrame("@coypu_news on Twitter");

	Assert.That(twitterFrame.hasContent("Coypu 0.8.0 released"));	


#### Scoping within windows

To restrict the scope to a browser window (or tab), locate the window by its title or name:

	var surveyPopup = browser.findWindow("Customer Survey");

	surveyPopup.select("Not Satisfied").from("How did we handle your enquiry?");	
	surveyPopup.clickButton("Submit");
	
	browser.clickLink("Logout"); // Using the original window scope again - there is no need to switch back, just use the correct scope
	
Switching between frames and windows is a particular pain in WebDriver as you may well know. Check out this example of how Coypu handles windows from a Coypu acceptance test:

    browser.visit("InteractionTestsPage.htm");

    browser.clickLink("Open pop up window");

    BrowserWindow popUp = browser.findWindow("Pop Up Window");
    ElementScope button = popUp.findButton("button in popup");

    assertThat(button.exists());
    assertThat(popUp.hasContent("I am a pop up window"));

    popUp.executeScript("self.close()");

    assertThat(button.missing());

    browser.clickLink("Open pop up window");

    assertThat(popUp.hasContent("I am a pop up window"));
    assertThat(button.exists());
    
    button.click();

#### Executing javascript in the browser

You can execute javascript like so:

	browser.executeScript("document.getElementById('SomeContainer').innerHTML = '<h2>Hello</h2>';");
	
Anything is returned from the javascript will be returned from `browser.executeScript()`

	var innerHtml = browser.executeScript("return document.getElementById('SomeContainer').innerHTML;");
	
#### Querying

Look for text anywhere in the page:

	boolean hasContent = browser.hasContent("In France, the coypu is known as a ragondin");
	boolean hasContent = browser.hasContentMatch("In [Ss]pain, the coypu is known as a (\w*)");
	
Check for the presence of an element:

	boolean hasElement = browser.hasCss("ul.menu > li");
	boolean hasElement = browser.hasXPath("//ul[@class = 'menu']/li");
	
The positive queries above will wait up to the configured timeout for a matching element to appear and return as soon as it does.

The negative versions will wait for the element NOT to be present:

	boolean hasNoContent = browser.hasNoContent("In France, the coypu is known as a ragondin");
	boolean hasNoElement = browser.hasNoCss("ul.menu > li");
	boolean hasNoElement = browser.hasNoXPath("//ul[@class = 'menu']/li");

N.B: Use the version you are expecting to ensure your test returns fast under normal circumstances

#### Dialogs

Check for the presence of a modal dialog with expected text:

	boolean hasDialog = browser.hasDialog("Are you sure you want to cancel your account?");
	boolean hasNoDialog = browser.hasDialog("Are you sure you want to cancel your account?");
	
Waits are as for the other Has/HasNo methods.

Interact with the current dialog like so:

	browser.acceptDialog();
	browser.cancelDialog();
  
#### Varying the timeout

When you need an unusually long (or short) timeout for a particular interaction you can override the timeout just for this call by passing in a `Options` like this:

	browser.fillIn("Attachment").with(@"c:\coypu\bigfile.mp4");
	browser.clickButton("Upload");
	browser.hasContent("File bigfile.mp4 (10.5mb) uploaded successfully", new Options { Timeout = TimeSpan.FromSeconds(60) } );
	
#### Finding states (nondeterministic testing)

Sometimes you just can't predict what state the browser will be in. Not ideal for a reliable test, but if it's unavoidable then you can use the `Session.findState` like this:

    State signedIn = new State() {
        public Boolean predicate() {
            return browser.hasContent("Signed in in as:");
        }
    };
        
    State signedOut = new State() {
        public Boolean predicate() {
            return browser.hasContent("Please sign in");
        }
    };
	
	if (browser.findState(signedIn,signedOut) == signedIn) 
	{
	  browser.clickLink("Sign out");
	}

It will return as soon as the first from your list of states is found, and throw if none of the states are found within the `SessionConfiguration.Timeout`

Avoid this:
  
	if (browser.hasContent("Signed in in as:")) 
	{
	  ...
	}
  
otherwise you will have to wait for the full `SessionConfiguration.Timeout` in the negitive case.
  
## More tricks/tips

So, you are using Coypu but sometimes links or buttons still don't seem to be clicked when you expect them to. Well there are a couple more techniques that Coypu can help you with in this situation. 

If the driver reports it had found and clicked your element successfully but nothing happens then it may simply be that your app isn't wiring up events at the right time. But if you have exhausted this angle and cannot fix the problem in the site itself, then you could try a couple of things:

#### Tell Coypu to keep clicking at regular intervals until you see the result you expect:

    PredicateQuery until = new PredicateQuery() {
        public Boolean run() {
            return browser.hasContent("Search Results:");
        }
    };
    browser.clickButton("asdf", until, waitBetweenRetries);

    var waitBetweenRetries = TimeSpan.Seconds(2);

    browser.clickButton("Search", until, waitBetweenRetries);

This is far from ideal as you are coupling the click to the expected result rather than verifying what you expect in a separate step, but as a last resort we have found this useful.

#### Tell Coypu to wait a short time between first finding links/buttons and clicking them:

	sessionConfiguration.WaitBeforeClick = TimeSpan.FromMilliseconds(0.2);
		
WARNING: Setting this in your driver sessionConfiguration means adding time to *every* click in your tests. You might be better off doing this just when you need it:

    Options options = new Options();
    options.WaitBeforeClick = TimeSpan.FromMilliseconds(0.2);
    
    browser.clickButton("Search", options);

## License

(The MIT License)

Copyright &copy; Adrian Longley, ITV plc & Contributors 2012

Permission is hereby granted, free of charge, to any person obtaining a copy of this software and associated documentation files (the 'Software'), to deal in the Software without restriction, including without limitation the rights to use, copy, modify, merge, publish, distribute, sublicense, and/or sell copies of the Software, and to permit persons to whom the Software is furnished to do so, subject to the following conditions:

The above copyright notice and this permission notice shall be included in all copies or substantial portions of the Software.

THE SOFTWARE IS PROVIDED 'AS IS', WITHOUT WARRANTY OF ANY KIND, EXPRESS OR IMPLIED, INCLUDING BUT NOT LIMITED TO THE WARRANTIES OF MERCHANTABILITY, FITNESS FOR A PARTICULAR PURPOSE AND NONINFRINGEMENT. IN NO EVENT SHALL THE AUTHORS OR COPYRIGHT HOLDERS BE LIABLE FOR ANY CLAIM, DAMAGES OR OTHER LIABILITY, WHETHER IN AN ACTION OF CONTRACT, TORT OR OTHERWISE, ARISING FROM, OUT OF OR IN CONNECTION WITH THE SOFTWARE OR THE USE OR OTHER DEALINGS IN THE SOFTWARE. 
