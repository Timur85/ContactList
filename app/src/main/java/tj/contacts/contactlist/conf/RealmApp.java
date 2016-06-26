package tj.contacts.contactlist.conf;

import android.app.Application;

import io.realm.Realm;
import io.realm.RealmConfiguration;

/**
 * Created by Timur on 11.05.2016.
 */
public class RealmApp extends Application {

    private static RealmApp instance;

    @Override
    public void onCreate() {
        super.onCreate();
        instance = this;
        RealmConfiguration config = new RealmConfiguration.Builder(getApplicationContext()).setModules(new RealmModule()).build();
        Realm.setDefaultConfiguration(config);
    }
    public static RealmApp getInstance() {
        return instance;
    }

}
