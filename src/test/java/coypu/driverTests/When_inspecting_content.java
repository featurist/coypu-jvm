package coypu.driverTests;

import org.junit.Test;

import java.util.regex.Pattern;

import static org.hamcrest.core.Is.is;
import static org.junit.Assert.assertThat;

public class When_inspecting_content extends DriverSpecs
{
    @Test
    public void does_not_find_missing_text()
    {
        assertThat(driver().hasContent("Some missing text", root()), is(false));
    }


    @Test
    public void finds_text_with_parts_marked_up_variously()
    {
        assertThat(driver().hasContent("Some text with parts marked up variously", root()), is(true));
    }


    @Test
    public void finds_text_in_a_table_row()
    {
        assertThat(driver().hasContent("Some text in a table row", root()), is(true));
    }


    @Test
    public void finds_text_in_a_list()
    {
        assertThat(driver().hasContent("Some\ntext\nin\na\nlist", root()), is(true));
    }


    @Test
    public void finds_text_split_over_multiple_lines_in_source()
    {
        assertThat(driver().hasContent("Some text split over multiple lines in source", root()), is(true));
    }


    @Test
    public void finds_text_displayed_over_multiple_lines_in_source()
    {
        assertThat(driver().hasContent("Some text displayed over\nmultiple lines", root()), is(true));
        assertThat(driver().hasContent("Some text displayed over\ntwo paragraphs", root()), is(true));
    }


    @Test
    public void does_not_find_single_line_text_displayed_over_multiple_lines_in_source()
    {
        assertThat(driver().hasContent("Some text displayed over multiple lines", root()), is(false));
    }


    @Test
    public void finds_text_by_regex()
    {
        assertThat(driver().hasContentMatch(Pattern.compile("\\bSome (text)? with [Pp]arts marked \\w* variously"), root()), is(true));
    }
}
