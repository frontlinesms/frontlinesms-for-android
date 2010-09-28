/**
 * 
 */
package net.frontlinesms.android.data.repository;

import java.util.Set;

/**
 * @author aga
 */
public interface NumberListDao {
	/** @return the numbers in the specified list */
	Set<String> getNumbers(String listName);
	/** @return <code>true</code> if the list exists; <code>false</code> otherwise */
	boolean exists(String listName);
	void addToList(String listName, String phoneNumber);
	void removeFromList(String listName, String phoneNumber);
}
