package utilities;

import com.aventstack.extentreports.*;
import com.aventstack.extentreports.reporter.ExtentSparkReporter;

public class ExtentReportManager {

    private static ExtentReports extent;
    private static ExtentTest test;

    public static ExtentReports getInstance() {
        if (extent == null) {
            ExtentSparkReporter reporter = new ExtentSparkReporter("target/ExtentReport.html");
            reporter.config().setDocumentTitle("API Automation Report");
            reporter.config().setReportName("Cucumber API Testing");

            extent = new ExtentReports();
            extent.attachReporter(reporter);
            extent.setSystemInfo("Project", "API Automation");
            extent.setSystemInfo("Environment", "QA");
        }
        return extent;
    }

    public static void flushReports() {
        if (extent != null) {
            extent.flush();
        }
    }
}
