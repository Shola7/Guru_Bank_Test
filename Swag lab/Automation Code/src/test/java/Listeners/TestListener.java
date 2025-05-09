package Listeners;

import org.testng.ITestListener;
import org.testng.ITestResult;
import com.aventstack.extentreports.Status;
import Base.BaseTest;

public class TestListener implements ITestListener {
    @Override
    public void onTestFailure(ITestResult result) {
        BaseTest.test.log(Status.FAIL, "Test Failed: " + result.getThrowable());
    }

    @Override
    public void onTestSuccess(ITestResult result) {
        BaseTest.test.log(Status.PASS, "Test Passed");
    }
}