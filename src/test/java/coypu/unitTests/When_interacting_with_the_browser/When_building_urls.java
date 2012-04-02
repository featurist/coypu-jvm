package coypu.unitTests.When_interacting_with_the_browser;

import coypu.SessionConfiguration;
import coypu.FullyQualifiedUrlBuilder;
import org.junit.Before;
import org.junit.Test;

import static org.hamcrest.core.Is.is;
import static org.hamcrest.core.IsEqual.equalTo;
import static org.junit.Assert.assertThat;

public class When_building_urls
{
    @Before
    public void setUp()
    {
        sessionConfiguration = new SessionConfiguration();
        fullyQualifiedUrlBuilder = new FullyQualifiedUrlBuilder();
    }

    private SessionConfiguration sessionConfiguration;
    private FullyQualifiedUrlBuilder fullyQualifiedUrlBuilder;

    @Test
    public void it_defaults_to_localhost()
    {
        sessionConfiguration.Port = 81;
        assertThat(fullyQualifiedUrlBuilder.getFullyQualifiedUrl("/visit/me", sessionConfiguration),
                    is(equalTo("http://localhost:81/visit/me")));
    }

    @Test
    public void it_defaults_to_port_80()
    {
        sessionConfiguration.setAppHost("im.theho.st");
        assertThat(fullyQualifiedUrlBuilder.getFullyQualifiedUrl("/visit/me", sessionConfiguration),
                    is(equalTo("http://im.theho.st/visit/me")));
    }

    @Test
    public void it_forms_url_from_host_port_and_virtual_path()
    {
        sessionConfiguration.setAppHost("im.theho.st");
        sessionConfiguration.Port = 81;
        assertThat(fullyQualifiedUrlBuilder.getFullyQualifiedUrl("/visit/me", sessionConfiguration),
                    is(equalTo("http://im.theho.st:81/visit/me")));
    }

    @Test
    public void it_handles_missing_leading_slashes_in_virtual_path()
    {
        sessionConfiguration.setAppHost("im.theho.st");
        assertThat(fullyQualifiedUrlBuilder.getFullyQualifiedUrl("visit/me", sessionConfiguration),
                    is(equalTo("http://im.theho.st/visit/me")));
    }

    @Test
    public void it_handles_trailing_and_missing_leading_slashes_with_a_port()
    {
        sessionConfiguration.setAppHost("im.theho.st/");
        sessionConfiguration.Port = 123;
        assertThat(fullyQualifiedUrlBuilder.getFullyQualifiedUrl("visit/me", sessionConfiguration),
                    is(equalTo("http://im.theho.st:123/visit/me")));
    }

    @Test
    public void it_handles_trailing_slashes_in_host()
    {
        sessionConfiguration.setAppHost("im.theho.st/");
        assertThat(fullyQualifiedUrlBuilder.getFullyQualifiedUrl("/visit/me", sessionConfiguration),
                    is(equalTo("http://im.theho.st/visit/me")));
    }

    @Test
    public void it_ignores_host_etc_when_supplied_a_fully_qualified_url()
    {
        sessionConfiguration.setAppHost("im.theho.st");
        sessionConfiguration.Port = 321;
        sessionConfiguration.SSL = true;

        assertThat(fullyQualifiedUrlBuilder.getFullyQualifiedUrl("http://www.someother.site/over.here", sessionConfiguration),
                    is(equalTo("http://www.someother.site/over.here")));
        assertThat(fullyQualifiedUrlBuilder.getFullyQualifiedUrl("file:///C:/local/file.here", sessionConfiguration),
                    is(equalTo("file:///C:/local/file.here")));
    }

    @Test
    public void it_supports_SSL()
    {
        sessionConfiguration.setAppHost("im.theho.st");
        sessionConfiguration.SSL = true;
        assertThat(fullyQualifiedUrlBuilder.getFullyQualifiedUrl("/visit/me", sessionConfiguration),
                    is(equalTo("https://im.theho.st/visit/me")));
    }

    @Test
    public void it_supports_SSL_with_ports()
    {
        sessionConfiguration.setAppHost("im.theho.st");
        sessionConfiguration.Port = 321;
        sessionConfiguration.SSL = true;
        assertThat(fullyQualifiedUrlBuilder.getFullyQualifiedUrl("/visit/me", sessionConfiguration), is(equalTo("https://im.theho.st:321/visit/me")));
    }

    @Test
    public void it_ignores_host_when_supplied_a_fully_qualified_url() {
        sessionConfiguration.setAppHost("im.theho.st");
        sessionConfiguration.Port = 321;
        sessionConfiguration.SSL = true;
        assertThat(fullyQualifiedUrlBuilder.getFullyQualifiedUrl("http://www.someother.site/over.here", sessionConfiguration), is(equalTo("http://www.someother.site/over.here")));
        assertThat(fullyQualifiedUrlBuilder.getFullyQualifiedUrl("file:///C:/local/file.here", sessionConfiguration), is(equalTo("file:///C:/local/file.here")));
    }

    @Test
    public void it_ignores_port_when_supplied_a_fully_qualified_url() {
        sessionConfiguration.Port = 321;

        assertThat(fullyQualifiedUrlBuilder.getFullyQualifiedUrl("http://www.someother.site/over.here", sessionConfiguration), is(equalTo("http://www.someother.site/over.here")));
        assertThat(fullyQualifiedUrlBuilder.getFullyQualifiedUrl("file:///C:/local/file.here", sessionConfiguration), is(equalTo("file:///C:/local/file.here")));
    }
}
