/**
 * 
 */
package net.frontlinesms.android.data.repository;

import net.frontlinesms.android.data.domain.KeywordAction;

/**
 * @author aga
 */
public interface KeywordActionDao {
	String[] getKeywords();
	KeywordAction[] getActions(String messageContent);
	void addAction(KeywordAction action);
}
