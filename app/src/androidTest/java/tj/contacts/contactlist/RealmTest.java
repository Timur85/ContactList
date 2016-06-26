package tj.contacts.contactlist;

import android.content.Context;
import android.test.AndroidTestCase;
import android.test.suitebuilder.annotation.SmallTest;
import android.webkit.WebView;

import junit.framework.Assert;

import tj.contacts.contactlist.conf.JavaScriptFunctions;

/**
 * Created by Timur on 13.05.2016.
 */
public class RealmTest extends AndroidTestCase {

    JavaScriptFunctions js;
    Context context;
    WebView webView;

    public RealmTest(Context context1, WebView webView1){
        this.context = context1;
        this.webView = webView1;
    }

    @SmallTest
    public void testSomething() throws Throwable {
        Assert.assertTrue(1 + 1 == 2);
    }

    @SmallTest
    public void testSomethingElse() throws Throwable {
        Assert.assertTrue(1 + 1 == 3);
    }

    @Override
    protected void setUp() throws Exception {
        super.setUp();
        js = new JavaScriptFunctions(context, webView);
    }

    @SmallTest
    public void testaddontact(){
        /*This test method / called from Angular page when the user create
         a new contact and press button create contact
         */
        try {
            //js.addContact("Name", "LastName", "Co", "992927087875", "email@mail.ru");

        }catch (Exception e){
            e.printStackTrace();
            fail();
        }
    }

    @Override
    protected void tearDown() throws Exception {
        super.tearDown();
    }
}
