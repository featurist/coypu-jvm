package Coypu.Tests.When_interacting_with_the_browser;

import org.hamcrest.BaseMatcher;
import org.hamcrest.Description;

import java.util.Arrays;
import java.util.Enumeration;

public class HasMember<T> extends BaseMatcher<Enumeration<T>> {

    private T expected;

    public HasMember(T expected) {
        this.expected = expected;
    }

    public static <T> HasMember<T> hasMember(T expected) {
        return new HasMember<T>(expected);
    }

    @Override
    public boolean matches(Object o) {
        Enumeration<T> actual = (Enumeration<T>) o;
        return Arrays.asList(actual).contains(expected);
    }

    @Override
    public void describeTo(Description description) {
        description.appendText("");
    }
}
