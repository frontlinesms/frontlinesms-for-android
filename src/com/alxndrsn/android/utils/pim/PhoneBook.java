/**
 * 
 */
package com.alxndrsn.android.utils.pim;

import android.content.ContentResolver;
import android.database.Cursor;
import android.net.Uri;
import android.provider.Contacts;
import android.provider.Contacts.People.Phones;

/**
 * @author aga
 */
public class PhoneBook {
	private final ContentResolver contentResolver;
	
	public PhoneBook(ContentResolver contentResolver) {
		this.contentResolver = contentResolver;
	}
	
	/** @return the name of the contact, or <code>null</code> if he is not known */
	public String getContactName(String phoneNumber) {
		// TODO add reflection check to see if we can use ContactsContract class instead
		Uri lookupUri = Uri.withAppendedPath(Contacts.Phones.CONTENT_FILTER_URL, Uri.encode(phoneNumber));
		Cursor cursor = this.contentResolver.query(lookupUri,
				new String[]{Phones.DISPLAY_NAME},
				null, null, null);
		if(cursor == null) {
			return null;
		} if(!cursor.moveToFirst()) {
			cursor.close();
			return null;
		} else {
			cursor.close();
			return cursor.getString(1);
		}
	}
}
