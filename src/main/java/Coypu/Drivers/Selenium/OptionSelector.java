package Coypu.Drivers.Selenium;

import Coypu.Element;
import Coypu.Iterators;
import Coypu.MissingHtmlException;
import com.google.common.base.Predicate;
import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;

import javax.annotation.Nullable;

class OptionSelector {
    public void select(Element element, final String option) {
        WebElement select = (WebElement) element.getNative();

        WebElement optionToSelect =
                Iterators.firstOrDefault(select.findElements(By.tagName("option")), new Predicate<WebElement>() {
                    @Override
                    public boolean apply(@Nullable WebElement e) {
                        return e.getText().equals(option) || e.getAttribute("value").equals(option);
                    }
                });

        if (optionToSelect == null)
            throw new MissingHtmlException("No such option: " + option);

        optionToSelect.click();
    }
}
