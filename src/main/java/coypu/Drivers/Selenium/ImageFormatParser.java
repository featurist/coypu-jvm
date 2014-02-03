//
// Translated by CS2J (http://www.cs2j.com): 03/02/2014 09:15:13
//

package coypu.Drivers.Selenium;

import CS2JNet.System.ObjectSupport;
import java.io.File;

public class ImageFormatParser   
{
    public static ImageFormat getImageFormat(String fileName) throws Exception {
        /* [UNSUPPORTED] 'var' as type is unsupported "var" */ extension = new File(fileName).Extension.ToLower().Replace("jpg", "jpeg");
        ImageFormat format = new ImageFormat();
        if (AreEqual(extension, ImageFormat.Bmp))
            format = ImageFormat.Bmp;
        else if (AreEqual(extension, ImageFormat.Gif))
            format = ImageFormat.Gif;
        else if (AreEqual(extension, ImageFormat.Jpeg))
            format = ImageFormat.Jpeg;
        else if (AreEqual(extension, ImageFormat.Png))
            format = ImageFormat.Png;
        else if (AreEqual(extension, ImageFormat.Bmp))
            format = ImageFormat.Bmp;
        else
            format = ImageFormat.Jpeg;     
        return format;
    }

    private static boolean areEqual(String extension, ImageFormat imageFormat) throws Exception {
        return ObjectSupport.Equals(imageFormat.toString(), StringComparison.InvariantCultureIgnoreCase);
    }

}


