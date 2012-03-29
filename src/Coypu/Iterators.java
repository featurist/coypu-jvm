package Coypu;

import com.google.common.base.Predicate;
import org.openqa.selenium.WebElement;

import java.util.ArrayList;

public class Iterators {

    public static WebElement FirstOrDefault(Iterable<WebElement> iterable, DriverScope scope) {
        for (WebElement item : iterable) {
            if (IsDisplayed(item, scope))
                return item;
        }
        return null;
    }

    public static WebElement FirstOrDefault(Iterable<WebElement> iterable, Predicate<WebElement> predicate) {
        for (WebElement item : iterable) {
            if (predicate.apply(item))
                return item;
        }
        return null;
    }

    public static WebElement FirstOrDefault(Iterable<WebElement> iterable, Predicate<WebElement> predicate, DriverScope scope) {
        for (WebElement item : iterable) {
            if (predicate.apply(item) && IsDisplayed(item, scope))
                return item;
        }
        return null;
    }

    public static ArrayList<WebElement> AllVisible(Iterable<WebElement> iterable, DriverScope scope) {
        ArrayList<WebElement> allVisible = new ArrayList<WebElement>();
        for (WebElement item : iterable) {
            if (IsDisplayed(item, scope))
                allVisible.add(item);
        }
        return allVisible;
    }

    public static boolean IsDisplayed(WebElement e, DriverScope scope) {
        return scope.ConsiderInvisibleElements() || e.isDisplayed();
    }
}
