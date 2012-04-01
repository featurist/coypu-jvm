package Coypu.AcceptanceTests;

import org.junit.Test;

import static org.hamcrest.core.Is.is;
import static org.hamcrest.core.IsEqual.equalTo;
import static org.junit.Assert.assertThat;

public class StringFormatInvestigation
{
    @Test
    public void wat() {
        assertThat(("asdf").toString(), is(equalTo("asdf")));
        assertThat(((Object)"asdf").toString(), is(equalTo("asdf")));
        
        Object[] args = varArgify("asdf","fdsa");
        assertThat(args[0].toString(), is(equalTo("asdf")));
        System.out.println(args[0]);
    }
    
    public Object[] varArgify(Object... args) {
        return args;
    }
}
