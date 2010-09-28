package net.frontlinesms.android;

import net.frontlinesms.android.data.domain.KeywordAction;
import net.frontlinesms.android.data.repository.DbKeywordActionDao;
import net.frontlinesms.android.data.repository.KeywordActionDao;

import com.alxndrsn.android.utils.Logger;
import com.alxndrsn.android.utils.sms.WholeSmsMessage;

import android.content.ContentResolver;

public class MessageProcessor {
	private final Logger log = Logger.getLogger(this);
	private final KeywordActionProcessor keywordActionProcessor;
	private final KeywordActionDao keywordActionDao;
	
	public MessageProcessor(ContentResolver resolver) {
		this.keywordActionDao = new DbKeywordActionDao(resolver);
		this.keywordActionProcessor = new KeywordActionProcessor(resolver);
	}
	
	public void process(WholeSmsMessage message) {
		// match keyword actions
//		List<KeywordAction> actions = this.keywordActionProvider.getActions(message.getMessageBody());
//		log.info("Matched KActions: " + actions.size());

		KeywordAction[] actions = this.keywordActionDao.getActions(message.getMessageBody());
		log.info("Matched KActions: " + actions.length);
		
		// process keyword actions
		for(KeywordAction action : actions) {
			this.keywordActionProcessor.process(action, message);
		}
	}
}
