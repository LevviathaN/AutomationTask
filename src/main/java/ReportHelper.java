import com.relevantcodes.extentreports.ExtentReports;
import com.relevantcodes.extentreports.ExtentTest;

import java.nio.file.Path;
import java.nio.file.Paths;
import java.time.LocalDateTime;

public class ReportHelper {

    public static final String root = System.getProperty("user.dir");
    public static ExtentTest test;
    public static ExtentReports report;

    private static Path getNewReportPath() {
        LocalDateTime dateTime = LocalDateTime.now();
        String reportName = "report" + "_" + dateTime.toLocalDate() + "_" + dateTime.toLocalTime().getHour() + "_"
                + dateTime.toLocalTime().getMinute() + "_" + dateTime.toLocalTime().getSecond();
        return Paths.get(root, "reports", reportName);
    }

    public static void startReporting() {
        report = new ExtentReports(getNewReportPath() + "\\ExtentReportResults.html");
        test = report.startTest("PrivatCurrencyTest"); //todo to add test name dynamically
    }

    public static void stopReporting() {
        report.endTest(test);
        report.flush();
    }
}
