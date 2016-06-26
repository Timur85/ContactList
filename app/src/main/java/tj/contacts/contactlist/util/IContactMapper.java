package tj.contacts.contactlist.util;

import io.realm.Realm;

/**
 * Created by Timur on 10.05.2016.
 */
public interface IContactMapper {

    // Add data to database contacts
    void addContact(Realm realm, Contacts contact);
    // Writing Queries from DB
    void getContact(Realm realm);
}
