import org.apache.commons.io.FileUtils;
import org.junit.runner.notification.Failure;
import org.junit.runner.notification.RunListener;
import org.openqa.selenium.OutputType;
import org.openqa.selenium.TakesScreenshot;

import java.io.File;
import java.io.IOException;
import java.text.SimpleDateFormat;
import java.util.Date;

public class ScreenShotListener extends RunListener {

    private TakesScreenshot takesScreenshot;

    public ScreenShotListener(TakesScreenshot takesScreenshot) {
        this.takesScreenshot = takesScreenshot;
    }

    @Override
    public void testFailure(Failure failure) throws Exception {
        File scrFile = takesScreenshot.getScreenshotAs(OutputType.FILE);
        Date date = new Date();
        String formattedDate = new SimpleDateFormat("dd.MM.yyyy HH:mm:ss").format(date);
        String path = String.format("/work/screen/scr_%s_%s.png", failure.getDescription().getDisplayName(), formattedDate);
        try {
            FileUtils.copyFile(scrFile, new File(path));
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
