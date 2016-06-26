package tj.contacts.contactlist.conf;

import android.app.AlertDialog;
import android.content.Context;
import android.content.DialogInterface;
import android.util.Log;
import android.webkit.JavascriptInterface;
import android.webkit.WebView;
import android.widget.Toast;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;

import io.realm.Realm;
import io.realm.RealmResults;
import tj.contacts.contactlist.util.Contacts;

/**
 * Created by Timur on 10.05.2016.
 */
public class JavaScriptFunctions {

    Context context;
    WebView webView;

    public JavaScriptFunctions(Context context1, WebView webView1){
        this.context = context1;
        this.webView = webView1;
    }

    // Add contact data which come from javaScript and add to database
    @JavascriptInterface
    public void addContact(final String name, final String lastname, final String company, final String phone, final String email){
        Realm realm = Realm.getInstance(context);
        // Obtain a Realm instance
        realm.beginTransaction();
        Contacts contacts = realm.createObject(Contacts.class);
        // increatement index
        int nextID = (int) (realm.where(Contacts.class).maximumInt("id") + 1);
        contacts.setId(nextID);
        contacts.setName(name);
        contacts.setLastname(lastname);
        contacts.setCompany(company);
        contacts.setPhone(phone);
        contacts.setEmail(email);
        contacts.setFavorits(false);
        realm.commitTransaction();
        realm.close();
    }

    // Get Data from databse
    @JavascriptInterface
    public String getContact(){
        Realm realm = Realm.getInstance(context);
        RealmResults<Contacts> results1 =
                realm.where(Contacts.class).findAll();

        JSONObject obj = new JSONObject();
        ArrayList array = new ArrayList();
        for(Contacts c:results1) {
            try {
                obj.put("id", c.getId());
                obj.put("name", c.getName());
                obj.put("lastname", c.getLastname());
                obj.put("phone", c.getPhone());
                obj.put("company", c.getCompany());
                obj.put("email", c.getEmail());
                obj.put("favourite", c.getFavorits());
                array.add(obj);
                obj = new JSONObject();

            } catch (JSONException e) {
                e.printStackTrace();
            }
        }

        JSONArray jsArray = new JSONArray(array);
        return jsArray.toString();
    }

    // Change javaScript alert to android Toast Short
    @JavascriptInterface
    public void toastShort(String message){
        Toast.makeText(context, message, Toast.LENGTH_SHORT).show();
    }

    // Update Contact by id
    @JavascriptInterface
    public void updateContact(String id, final String name, final String lastname, final String company, final String phone, final String email){
        Realm realm = Realm.getInstance(context);
        // Obtain a Realm instance
        realm.beginTransaction();
        // find contact data by id
        Contacts find =
                realm.where(Contacts.class).equalTo("id", Integer.valueOf(id)).findFirst();
        find.setName(name);
        find.setLastname(lastname);
        find.setCompany(company);
        find.setPhone(phone);
        find.setEmail(email);
        realm.commitTransaction();
        realm.close();
        toastShort("Updated successfully!");
    }

    // Yes/No alert dialog
    @JavascriptInterface
    public void alertDialogYesNo(String msg, DialogInterface.OnClickListener yesListener){
        AlertDialog.Builder builder = new AlertDialog.Builder(context);
        builder.setMessage(msg)
        .setCancelable(false)
        .setPositiveButton("Yes", yesListener)
        .setNegativeButton("No", new DialogInterface.OnClickListener() {
            public void onClick(DialogInterface dialog, int id) {
                dialog.cancel();
            }
        })
        .show();
    }

    // Contact delete listener
    @JavascriptInterface
    public void deleteContacts(final int position){
        DialogInterface.OnClickListener yesListener;
        yesListener = new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialog, int which) {
                deleteContact(position);
            }
        };

    }

    // Set favorites
    @JavascriptInterface
    public void setFavorit(String id, String bool){
        Realm realm = Realm.getInstance(context);
        // Obtain a Realm instance
        realm.beginTransaction();
        // find contact data by id
        Contacts find =
                realm.where(Contacts.class).equalTo("id", Integer.valueOf(id)).findFirst();
        find.setFavorits(Boolean.valueOf(bool));
        realm.commitTransaction();
        realm.close();
        toastShort("Favorite setted up!");
    }

    // Delete contact by id
    @JavascriptInterface
    public void deleteContact(int position){
        Realm realm = Realm.getInstance(context);
        realm.beginTransaction();
        // find contact data by id
        Contacts delete =
                realm.where(Contacts.class).equalTo("id", position).findFirst();
        delete.removeFromRealm();
        realm.commitTransaction();
        realm.close();
        toastShort("Contact deleted! ");
    }

    public void dropContact(){
        Realm realm = Realm.getInstance(context);
        realm.beginTransaction();
        realm.clear(Contacts.class);
        realm.commitTransaction();
    }

    // Change javaScript alert to android Toast
    @JavascriptInterface
    public void toast(String message){
        Toast.makeText(context, message, Toast.LENGTH_LONG).show();
    }

    // Console in android
    @JavascriptInterface
    public void getLog(String log){
        Log.v("JavaScriptLog", log);
    }

    // Get Data from databse
    @JavascriptInterface
    public String getFavorites(){
        Realm realm = Realm.getInstance(context);
        RealmResults<Contacts> results1 =
                realm.where(Contacts.class).equalTo("favorits", true).findAll();
        JSONObject obj = new JSONObject();
        ArrayList array = new ArrayList();
        for(Contacts c:results1) {
            try {
                obj.put("id", c.getId());
                obj.put("name", c.getName());
                obj.put("lastname", c.getLastname());
                obj.put("phone", c.getPhone());
                obj.put("company", c.getCompany());
                obj.put("email", c.getEmail());
                obj.put("favourite", c.getFavorits());
                array.add(obj);
                obj = new JSONObject();

            } catch (JSONException e) {
                e.printStackTrace();
            }
        }
        JSONArray jsArray = new JSONArray(array);
        return jsArray.toString();

    }

}
