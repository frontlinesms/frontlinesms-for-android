/**
 * 
 */
package net.frontlinesms.android.data.repository;

import java.util.Collections;
import java.util.HashMap;
import java.util.HashSet;
import java.util.Map;
import java.util.Set;

/**
 * @author aga
 *
 */
public class MemNumberListDao implements NumberListDao {
	private Map<String, Set<String>> lists = new HashMap<String, Set<String>>();
	
	@Override
	public synchronized boolean exists(String group) {
		return lists.containsKey(group);
	}

	@Override
	public synchronized Set<String> getNumbers(String listName) {
		if(!exists(listName)) {
			return Collections.emptySet();
		} else {
			Set<String> saved = lists.get(listName);
			// Duplicate the set so that modifications can be made elsewhere
			return new HashSet<String>(saved);
		}
	}

	@Override
	public synchronized void addToList(String listName, String phoneNumber) {
		if(!exists(listName)) {
			lists.put(listName, new HashSet<String>());
		}
		lists.get(listName).add(phoneNumber);
	}
	
	@Override
	public synchronized void removeFromList(String listName, String phoneNumber) {
		if(exists(listName)) {
			lists.get(listName).remove(phoneNumber);
		}
	}
}
