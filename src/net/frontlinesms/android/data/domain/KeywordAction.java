/**
 * 
 */
package net.frontlinesms.android.data.domain;

import com.alxndrsn.android.utils.db.DbEntity;

/**
 * @author aga
 *
 */
public final class KeywordAction implements DbEntity {
	private Long _id;
	private Type type;
	private String keyword;
	private String text;
	private String list;
	
	public KeywordAction() {}
	
	private KeywordAction(Type type, String keyword, String text, String list) {
		this.type = type;
		// convert all keywords to lower-case so they take up less space
		this.keyword = keyword.toLowerCase();
		
		if(!type.hasText() && text!=null) {
			throw new IllegalStateException("Cannot set text on an action of type: " + type);
		}
		this.text = text;
		
		if(!type.hasList() && list!=null) {
			throw new IllegalStateException("Cannot set list on an action of type: " + type);
		}
		this.list = list;
	}
	
//> ACCESSORS
	@Override
	public Long getDbId() {
		return _id;
	}
	
	public Type getType() {
		return type;
	}
	public String getKeyword() {
		return keyword;
	}
	public void setKeyword(String keyword) {
		this.keyword = keyword;
	}
	public String getText() {
		return text;
	}
	public void setText(String text) {
		if(!type.hasText()) throw new IllegalStateException("Cannot set text on an action of type: " + type);
		this.text = text;
	}
	public String getList() {
		return list;
	}
	public void setList(String list) {
		if(!type.hasList()) throw new IllegalStateException("Cannot set list on an action of type: " + type);
		this.list = list;
	}
	
//> STATIC FACTORY METHODS
	public static KeywordAction createReplyAction(String keyword, String replyText) {
		return new KeywordAction(Type.REPLY, keyword, replyText, null);
	}
	public static KeywordAction createForwardAction(String keyword, String forwardText, String list) {
		return new KeywordAction(Type.FORWARD, keyword, forwardText, list);
	}
	public static KeywordAction createJoinAction(String keyword, String list) {
		return new KeywordAction(Type.JOIN, keyword, null, list);
	}
	public static KeywordAction createLeaveAction(String keyword, String list) {
		return new KeywordAction(Type.LEAVE, keyword, null, list);
	}
	
//> ENUMS
	public enum Type {
		REPLY(true, false),
		FORWARD(true, true),
		JOIN(false, true),
		LEAVE(false, true);

		private final boolean hasText;
		private final boolean hasList;
		
		private Type(boolean hasText, boolean hasList) {
			this.hasText = hasText;
			this.hasList = hasList;
		}

		boolean hasText() {
			return this.hasText;
		}
		boolean hasList() {
			return this.hasList;
		}
	}
	
	/** @return the first word from the message, or <code>null</code> if there
	 * don't appear to be any */
	public static String getKeyword(String messageContent) {
		// convert to lower case to save space
		messageContent = messageContent.toLowerCase().trim();
		if(messageContent.length() == 0) {
			return null;
		} else {
			return messageContent.split("\\s", 2)[0];
		}
	}
}