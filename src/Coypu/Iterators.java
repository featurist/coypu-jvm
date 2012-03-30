package Coypu;

import com.google.common.base.Predicate;
import org.openqa.selenium.WebElement;

import java.util.ArrayList;
import java.util.List;

public class Iterators {

    public static WebElement FirstOrDefault(List<WebElement> list, DriverScope scope) {
        for (WebElement item : list) {
            if (IsDisplayed(item, scope))
                return item;
        }
        return null;
    }

    public static WebElement FirstOrDefault(List<WebElement> list, Predicate<WebElement> predicate) {
        for (WebElement item : list) {
            if (predicate.apply(item))
                return item;
        }
        return null;
    }

    public static WebElement FirstOrDefault(List<WebElement> list, Predicate<WebElement> predicate, DriverScope scope) {
        for (WebElement item : list) {
            if (predicate.apply(item) && IsDisplayed(item, scope))
                return item;
        }
        return null;
    }

    public static ArrayList<WebElement> AllVisible(List<WebElement> list, DriverScope scope) {
        ArrayList<WebElement> allVisible = new ArrayList<WebElement>();
        for (WebElement item : list) {
            if (IsDisplayed(item, scope))
                allVisible.add(item);
        }
        return allVisible;
    }

    public static boolean IsDisplayed(WebElement e, DriverScope scope) {
        return scope.ConsiderInvisibleElements() || e.isDisplayed();
    }
}
