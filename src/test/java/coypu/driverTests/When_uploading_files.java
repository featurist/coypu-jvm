package coypu.driverTests;

import coypu.ElementFound;
import org.junit.Test;

import java.io.File;
import java.io.IOException;

import static org.hamcrest.core.Is.is;
import static org.junit.Assert.assertThat;

public class When_uploading_files extends DriverSpecs
{
    @Test
    public void sets_the_path_to_be_uploaded() throws IOException {
        File someLocalFile = new File(".","local.file");
        someLocalFile.createNewFile();
        try
        {
            ElementFound textField = driver().findField("forLabeledFileFieldId", root());
            driver().set(textField, someLocalFile.getAbsolutePath());

            ElementFound findAgain = driver().findField("forLabeledFileFieldId", root());
            assertThat(findAgain.getValue().endsWith("/local.file"), is(true));
        }
        finally
        {
            someLocalFile.delete();
        }
    }
}
