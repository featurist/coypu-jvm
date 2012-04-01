package Coypu.Drivers.Tests;

import Coypu.ElementFound;
import org.junit.Test;

import static org.hamcrest.core.Is.is;
import static org.junit.Assert.assertThat;

public class When_uploading_files extends DriverSpecs
{
    @Test
    public void sets_the_path_to_be_uploaded()
    {
        String someLocalFile = "local.file";
        try
        {
            // TODO: Javaify
            String fullPath = "?";
//            var directoryInfo = new DirectoryInfo(".");
//            var fullPath = Path.combine(directoryInfo.FullName,someLocalFile);
//            using (File.create(fullPath)) { }

            ElementFound textField = driver().findField("forLabeledFileFieldId", root());
            driver().set(textField, fullPath);

            ElementFound findAgain = driver().findField("forLabeledFileFieldId", root());
            assertThat(findAgain.getValue().endsWith("\\" + someLocalFile), is(true));
        }
        finally
        {
            //File.delete(someLocalFile);
        }
    }
}
