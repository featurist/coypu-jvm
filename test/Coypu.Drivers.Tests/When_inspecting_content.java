package Coypu.Drivers.Tests;

import org.junit.Test;

import java.util.regex.Pattern;

import static org.hamcrest.core.Is.is;
import static org.junit.Assert.assertThat;

public class When_inspecting_content extends DriverSpecs
{
    @Test
    public void Does_not_find_missing_text()
    {
        assertThat(Driver().HasContent("Some missing text", Root()), is(false));
    }


    @Test
    public void Finds_text_with_parts_marked_up_variously()
    {
        assertThat(Driver().HasContent("Some text with parts marked up variously", Root()), is(true));
    }


    @Test
    public void Finds_text_in_a_table_row()
    {
        assertThat(Driver().HasContent("Some text in a table row", Root()), is(true));
    }


    @Test
    public void Finds_text_in_a_list()
    {
        assertThat(Driver().HasContent("Some\r\ntext\r\nin\r\na\r\nlist", Root()), is(true));
    }


    @Test
    public void Finds_text_split_over_multiple_lines_in_source()
    {
        assertThat(Driver().HasContent("Some text split over multiple lines in source", Root()), is(true));
    }


    @Test
    public void Finds_text_displayed_over_multiple_lines_in_source()
    {
        assertThat(Driver().HasContent("Some text displayed over\r\nmultiple lines", Root()), is(true));
        assertThat(Driver().HasContent("Some text displayed over\r\ntwo paragraphs", Root()), is(true));
    }


    @Test
    public void Does_not_find_single_line_text_displayed_over_multiple_lines_in_source()
    {
        assertThat(Driver().HasContent("Some text displayed over multiple lines", Root()), is(false));
    }


    @Test
    public void Finds_text_by_regex()
    {
        assertThat(Driver().HasContentMatch(Pattern.compile("\\bSome (text)? with [Pp]arts marked \\w* variously"), Root()), is(true));
    }
}