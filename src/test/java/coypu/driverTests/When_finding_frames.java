package coypu.driverTests;

import coypu.acceptanceTests.ApiExamples;
import org.junit.Test;

import static org.hamcrest.core.Is.is;
import static org.hamcrest.core.IsEqual.equalTo;
import static org.junit.Assert.assertThat;

public class When_finding_frames extends DriverSpecs
{
    public String testPage()
    {
        return ApiExamples.testPage("frameset.htm");
    }

    @Test
    public void finds_by_header_text()
    {
        assertThat(driver().findFrame("I am frame one", root()).getId(), is(equalTo("frame1id")));
        assertThat(driver().findFrame("I am frame two", root()).getId(), is(equalTo("frame2id")));
    }

    @Test
    public void finds_by_id()
    {
        assertThat(driver().findFrame("frame1id", root()).getName(), is(equalTo("frame1")));
        assertThat(driver().findFrame("frame2id", root()).getName(), is(equalTo("frame2")));
    }

    @Test
    public void finds_by_name()
    {
        assertThat(driver().findFrame("frame1", root()).getId(), is(equalTo("frame1id")));
        assertThat(driver().findFrame("frame2", root()).getId(), is(equalTo("frame2id")));
    }
}