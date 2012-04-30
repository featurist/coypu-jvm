package coypu;

import com.google.common.base.Predicate;
import org.openqa.selenium.WebElement;

import java.util.ArrayList;
import java.util.List;

public class Iterators {

    public static WebElement firstOrDefault(List<WebElement> list, Scope scope) {
        for (WebElement item : list) {
            if (isDisplayed(item, scope))
                return item;
        }
        return null;
    }

    public static WebElement firstOrDefault(List<WebElement> list, Predicate<WebElement> predicate) {
        for (WebElement item : list) {
            if (predicate.apply(item))
                return item;
        }
        return null;
    }

    public static WebElement firstOrDefault(List<WebElement> list, Predicate<WebElement> predicate, Scope scope) {
        for (WebElement item : list) {
            if (predicate.apply(item) && isDisplayed(item, scope))
                return item;
        }
        return null;
    }

    public static ArrayList<WebElement> allVisible(List<WebElement> list, Scope scope) {
        ArrayList<WebElement> allVisible = new ArrayList<WebElement>();
        for (WebElement item : list) {
            if (isDisplayed(item, scope))
                allVisible.add(item);
        }
        return allVisible;
    }

    public static boolean isDisplayed(WebElement e, Scope scope) {
        return scope.considerInvisibleElements() || e.isDisplayed();
    }
}
