package Coypu.Drivers.Selenium;

import Coypu.Element;
import Coypu.MissingHtmlException;

class OptionSelector
{
    public void Select(Element element, String option) throws MissingHtmlException {
        IWebElement select = (IWebElement)element.Native();

        IWebElement optionToSelect =
            select.FindElements(By.TagName("option")).FirstOrDefault(e => e.Text == option || e.GetAttribute("value") == option);

        if (optionToSelect == null)
            throw new MissingHtmlException("No such option: " + option);

        optionToSelect.Click();
    }
}
