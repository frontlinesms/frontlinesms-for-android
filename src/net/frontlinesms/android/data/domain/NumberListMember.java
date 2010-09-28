/**
 * 
 */
package net.frontlinesms.android.data.domain;

import com.alxndrsn.android.utils.db.DbEntity;

/**
 * @author aga
 */
public class NumberListMember implements DbEntity {
	private Long _id;
	private String listName;
	private String phoneNumber;
	
	public NumberListMember() {
	}

	@Override
	public Long getDbId() {
		return _id;
	}
	
	public String getListName() {
		return listName;
	}
	public void setListName(String listName) {
		this.listName = listName;
	}
	
	public String getPhoneNumber() {
		return phoneNumber;
	}
	public void setPhoneNumber(String phoneNumber) {
		this.phoneNumber = phoneNumber;
	}
	
	public static NumberListMember create(String listName, String phoneNumber) {
		NumberListMember mem = new NumberListMember();
		mem.setListName(listName);
		mem.setPhoneNumber(phoneNumber);
		return mem;
	}

}
