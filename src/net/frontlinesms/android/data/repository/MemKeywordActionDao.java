package net.frontlinesms.android.data.repository;

import java.util.HashMap;
import java.util.LinkedList;
import java.util.List;
import java.util.Map;

import net.frontlinesms.android.PropertySubstituter;
import net.frontlinesms.android.data.domain.KeywordAction;


/**
 * In-memory implementation of {@link KeywordActionDao} used until a
 * persistent version is developed.
 * @author aga
 */
public class MemKeywordActionDao implements KeywordActionDao {
	private static final KeywordAction[] NO_KEYWORD_ACTIONS = new KeywordAction[0];
	private Map<String, List<KeywordAction>> actions = new HashMap<String, List<KeywordAction>>();
	
	public MemKeywordActionDao() {
		// Populate the map with some keyword actions
		addAction(KeywordAction.createReplyAction("hi", "Hello there, " + PropertySubstituter.KEY_SENDER_NAME
				+ ", i do like your phone number (" + PropertySubstituter.KEY_SENDER_PHONENUMBER + ")"));
		addAction(KeywordAction.createJoinAction("join", "demoGroup"));
		addAction(KeywordAction.createJoinAction("stop", "demoGroup"));
		addAction(KeywordAction.createForwardAction("say", 
				PropertySubstituter.KEY_SENDER_NAME + " says " + PropertySubstituter.KEY_ORIGINAL_MESSAGE,
				"demoGroup"));
	}

	public synchronized String[] getKeywords() {
		return actions.keySet().toArray(new String[0]);
	}
	
	public synchronized void addAction(KeywordAction action) {
		String keyword = action.getKeyword();
		if(!actions.containsKey(keyword)) {
			actions.put(keyword, new LinkedList<KeywordAction>());
		}
		actions.get(keyword).add(action);
	}
	
	public synchronized KeywordAction[] getActions(String messageContent) {
		String firstWord = KeywordAction.getKeyword(messageContent);
		if(firstWord==null || !this.actions.containsKey(firstWord))
			return NO_KEYWORD_ACTIONS;
		else return this.actions.get(firstWord).toArray(NO_KEYWORD_ACTIONS);
	}
}
