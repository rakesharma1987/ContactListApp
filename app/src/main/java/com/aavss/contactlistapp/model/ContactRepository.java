package com.aavss.contactlistapp.model;

import android.annotation.SuppressLint;
import android.content.Context;
import android.database.Cursor;
import android.provider.ContactsContract;
import android.util.Log;

import java.util.ArrayList;
import java.util.List;

public class ContactRepository {
    private Context context;
    private List<Contact> contacts;

    public ContactRepository(Context context) {
        this.context = context;
        contacts = new ArrayList<>();
    }

    public List<Contact> fetchContacts() {

//        List<Contact> contacts = new ArrayList<>();
//        Contact contact = new Contact();

        Cursor cursor = context.getContentResolver().query(ContactsContract.CommonDataKinds.Phone.CONTENT_URI,
                null, null, null,
                ContactsContract.CommonDataKinds.Phone.DISPLAY_NAME + " ASC");

        if ((cursor != null ? cursor.getCount() : 0) > 0) {
            while (cursor.moveToNext()) {

                @SuppressLint("Range") String name = cursor.getString(cursor.getColumnIndex(ContactsContract.Contacts.DISPLAY_NAME));
                @SuppressLint("Range") String phoneNo = cursor.getString(cursor.getColumnIndex(ContactsContract.CommonDataKinds.Phone.NUMBER));
                @SuppressLint("Range") String photoUri = cursor.getString(cursor.getColumnIndex(ContactsContract.CommonDataKinds.Phone.PHOTO_URI));

                Log.e("contact", "getAllContacts: " + name + " " + phoneNo + " " + photoUri);
                Contact contact = new Contact();
                contact.setName(name);
                contact.setPhoneNumber(phoneNo);
                contact.setPhotoUri(photoUri);

                contacts.add(contact);

            }
        }
        if (cursor != null) {
            cursor.close();
        }
        return contacts;
    }
}
