package tj.contacts.contactlist.conf;

import android.content.Context;

import io.realm.Realm;
import io.realm.RealmConfiguration;
import io.realm.exceptions.RealmMigrationNeededException;

/**
 * Created by Timur on 11.05.2016.
 */
public class BaseSetting {

    private Context context;

    public BaseSetting(Context context1){
        this.context = context1;
    }

    // Delete realm db
    public Realm buildDatabase(){
        RealmConfiguration realmConfiguration = new RealmConfiguration.Builder(context).build();

        try {
            return Realm.getInstance(realmConfiguration);
        } catch (RealmMigrationNeededException e){
            try {
                Realm.deleteRealm(realmConfiguration);
                //Realm file has been deleted.
                return Realm.getInstance(realmConfiguration);
            } catch (Exception ex){
                throw ex;
                //No Realm file to remove.
            }
        }
    }

}
