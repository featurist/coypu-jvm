package Coypu.Tests.When_interacting_with_the_browser;

import org.hamcrest.BaseMatcher;
import org.hamcrest.Description;

import java.util.List;

public class HasMember<T> extends BaseMatcher<List<T>> {

    private T expected;
    private List<T> actual;

    public HasMember(T expected) {
        this.expected = expected;
    }

    public static <T> HasMember<T> hasMember(T expected){
        return new HasMember<T>(expected);
    }

    @Override
    public boolean matches(Object o) {
        actual = (List<T>) o;
        return actual.contains(expected);
    }

    @Override
    public void describeTo(Description description) {
        description.appendText(expected.toString());
    }
}
