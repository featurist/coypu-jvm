package Coypu.Drivers.Tests;

import Coypu.ElementFound;
import org.junit.Test;

import static org.hamcrest.core.Is.is;
import static org.junit.Assert.assertThat;

public class When_uploading_files extends DriverSpecs
{
    @Test
    public void Sets_the_path_to_be_uploaded()
    {
        String someLocalFile = "local.file";
        try
        {
            // TODO: Javaify
            String fullPath = "?";
//            var directoryInfo = new DirectoryInfo(".");
//            var fullPath = Path.Combine(directoryInfo.FullName,someLocalFile);
//            using (File.Create(fullPath)) { }

            ElementFound textField = Driver().FindField("forLabeledFileFieldId", Root());
            Driver().Set(textField, fullPath);

            ElementFound findAgain = Driver().FindField("forLabeledFileFieldId", Root());
            assertThat(findAgain.Value().endsWith("\\" + someLocalFile), is(true));
        }
        finally
        {
            //File.Delete(someLocalFile);
        }
    }
}
