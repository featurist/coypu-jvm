package Coypu.Tests.When_interacting_with_the_browser;

import org.hamcrest.BaseMatcher;
import org.hamcrest.Description;

import java.util.List;

public class IsEmpty extends BaseMatcher<List> {

    public static IsEmpty empty() {
        return new IsEmpty();
    }

    @Override
    public boolean matches(Object o) {
        List actual = (List) o;
        return actual.size() == 0;
    }

    @Override
    public void describeTo(Description description) {
        description.appendText("Empty");
    }

}
