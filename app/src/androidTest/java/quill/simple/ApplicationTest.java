package quill.simple;

import android.app.Application;
import android.support.annotation.RequiresPermission;
import android.test.ApplicationTestCase;
import android.test.suitebuilder.annotation.MediumTest;

/**
 * <a href="http://d.android.com/tools/testing/testing_android.html">Testing Fundamentals</a>
 */
public class ApplicationTest extends ApplicationTestCase<Application> {
    public ApplicationTest() {
        super(Application.class);
    }
    @MediumTest
    public void testToFail(){
        fail("nil");

    }




}