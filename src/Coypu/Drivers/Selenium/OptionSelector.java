package Coypu.Drivers.Selenium;

import Coypu.Element;
import Coypu.Iterators;
import Coypu.MissingHtmlException;
import com.google.common.base.Predicate;
import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;

import javax.annotation.Nullable;

class OptionSelector
{
    public void Select(Element element, final String option) throws MissingHtmlException {
        WebElement select = (WebElement)element.Native();

        WebElement optionToSelect =
            Iterators.FirstOrDefault(select.findElements(By.tagName("option")),new Predicate<WebElement>() {
                @Override
                public boolean apply(@Nullable WebElement e) {
                    return e.getText() == option || e.getAttribute("value") == option;
                }
            });

        if (optionToSelect == null)
            throw new MissingHtmlException("No such option: " + option);

        optionToSelect.click();
    }
}