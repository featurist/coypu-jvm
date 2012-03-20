package Coypu.Drivers.Selenium;

import Coypu.Element;
import Coypu.MissingHtmlException;
import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;

class OptionSelector
{
    public void Select(Element element, String option) throws MissingHtmlException {
        WebElement select = (WebElement)element.Native();

        WebElement optionToSelect =
            select.findElements(By.tagName("option")).FirstOrDefault(e => e.Text == option || e.GetAttribute("value") == option);

        if (optionToSelect == null)
            throw new MissingHtmlException("No such option: " + option);

        optionToSelect.click();
    }
}
