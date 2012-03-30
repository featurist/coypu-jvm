package Coypu.Tests.When_interacting_with_the_browser;

import org.hamcrest.BaseMatcher;
import org.hamcrest.Description;

import java.util.Collections;
import java.util.Enumeration;

public class HasEnumerationMember<T> extends BaseMatcher<Enumeration<T>> {

    private T expected;
    private Enumeration<T> actual;

    public HasEnumerationMember(T expected) {
        this.expected = expected;
    }

    public static <T> HasEnumerationMember<T> hasEnumerationMember(T expected) {
        return new HasEnumerationMember<T>(expected);
    }

    @Override
    public boolean matches(Object o) {
        actual = (Enumeration<T>) o;
        return Collections.list(actual).contains(expected);
    }

    @Override
    public void describeTo(Description description) {
        description.appendText(expected.toString());
    }
}

