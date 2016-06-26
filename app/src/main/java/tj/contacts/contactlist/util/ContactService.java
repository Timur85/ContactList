package tj.contacts.contactlist.util;

import android.util.Log;

import io.realm.Realm;
import io.realm.RealmResults;

/**
 * Created by Timur on 10.05.2016.
 */
public class ContactService implements IContactMapper {

    // Add contact data to database
    public void addContact(Realm realm, Contacts contact){
        realm.beginTransaction();
        Contacts contacts = realm.createObject(Contacts.class);
        contacts.setName(contact.getName());
        contacts.setLastname(contact.getLastname());
        contacts.setCompany(contact.getCompany());
        contacts.setPhone(contact.getPhone());
        contacts.setEmail(contact.getEmail());
        contacts.setFavorits(contact.getFavorits());
        realm.commitTransaction();
        realm.close();
    }

    // Writing Queries from DB
    public void getContact(Realm realm){
        RealmResults<Contacts> results1 =
                realm.where(Contacts.class).findAll();

        for(Contacts c:results1) {
            Log.d("results1", c.getName());
        }
    }
}
