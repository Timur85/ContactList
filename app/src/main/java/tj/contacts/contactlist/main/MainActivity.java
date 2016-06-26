package tj.contacts.contactlist.main;

import android.app.AlertDialog;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.Menu;
import android.view.MenuItem;
import android.webkit.JsResult;
import android.webkit.WebChromeClient;
import android.webkit.WebView;

import io.realm.Realm;
import tj.contacts.contactlist.R;
import tj.contacts.contactlist.conf.JavaScriptFunctions;

/**
 * Created by Timur on 9.05.2016.
 */
public class MainActivity extends AppCompatActivity {

    private WebView webView;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        getSupportActionBar().hide();
        webView = (WebView) findViewById(R.id.webView);
        Realm myRealm = Realm.getInstance(this);
        // Bridge JavaScript to Java code
        webView.addJavascriptInterface(new JavaScriptFunctions(this, webView), "AndroidNative");
        // Access to files and pages
        webView.getSettings().setAllowFileAccess(true);
        webView.getSettings().setAllowFileAccessFromFileURLs(true);
        webView.getSettings().setAllowUniversalAccessFromFileURLs(true);
        // Add JavaScript support in webView
        webView.getSettings().setJavaScriptEnabled(true);
        webView.setWebChromeClient(new WebChromeClient() {
            @Override
            public boolean onJsAlert(WebView view, String url, String message, JsResult result) {
                new AlertDialog.Builder(MainActivity.this)
                        .setMessage(message)
                        .setPositiveButton("Ok", null)
                        .create().show();
                return true;
            }
        });
        webView.loadUrl("file:///android_asset/www/index.html");
        webView.setWebChromeClient(new WebChromeClient());
        webView.getSettings().setDomStorageEnabled(true);
        //String realmPath = getFilesDir() + "/default.realm"; // location of the default realm file.
        /*JavaScriptFunctions js = new JavaScriptFunctions(this);
        js.dropContact();*/
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.menu_main, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        // Handle action bar item clicks here. The action bar will
        // automatically handle clicks on the Home/Up button, so long
        // as you specify a parent activity in AndroidManifest.xml.
        int id = item.getItemId();

        //noinspection SimplifiableIfStatement
        if (id == R.id.action_settings) {
            return true;
        }

        return super.onOptionsItemSelected(item);
    }
}
