import org.apache.commons.io.FileUtils;
import org.junit.runner.notification.Failure;
import org.junit.runner.notification.RunListener;
import org.openqa.selenium.OutputType;
import org.openqa.selenium.TakesScreenshot;

import java.io.File;
import java.io.IOException;

public class ScreenShotListener extends RunListener {

    private TakesScreenshot takesScreenshot;

    public ScreenShotListener(TakesScreenshot takesScreenshot) {
        this.takesScreenshot = takesScreenshot;
    }

    @Override
    public void testFailure(Failure failure) throws Exception {
        File scrFile = takesScreenshot.getScreenshotAs(OutputType.FILE);
        try {
            FileUtils.copyFile(scrFile, new File("/work/screen/screenshot.png"));
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
