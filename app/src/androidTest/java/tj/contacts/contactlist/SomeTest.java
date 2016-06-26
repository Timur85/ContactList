package tj.contacts.contactlist;

import android.test.suitebuilder.annotation.SmallTest;

import junit.framework.Assert;
import junit.framework.TestCase;


/**
 * Created by Timur on 13.05.2016.
 */
public class SomeTest extends TestCase {

    public void testSomething() throws Throwable {
        Assert.assertTrue(1 + 1 == 2);
    }

    @SmallTest
    public void testSomethingElse() throws Throwable {
        Assert.assertTrue(2 == 3);
    }
}
