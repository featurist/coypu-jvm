package coypu.unitTests.When_interacting_with_the_browser;

import org.hamcrest.BaseMatcher;
import org.hamcrest.Description;

public class InRange extends BaseMatcher<Long> {

    private Long actual;
    private Long lower;
    private Long upper;

    public InRange(Long lower, Long upper) {
        this.lower = lower;
        this.upper = upper;
    }

    public static InRange inRange(Long lower, Long upper){
        return new InRange(lower,upper);
    }

    @Override
    public boolean matches(Object o) {
        actual = (Long) o;
        return actual >= lower &&
               actual <= upper;
    }

    @Override
    public void describeTo(Description description) {
        description.appendText("In range " + lower + " - " + upper);
    }
}
