/**
 * 
 */
package net.frontlinesms.android;

import java.util.Set;

import net.frontlinesms.android.data.domain.KeywordAction;
import net.frontlinesms.android.data.repository.DbNumberListDao;
import net.frontlinesms.android.data.repository.NumberListDao;

import com.alxndrsn.android.utils.sms.SmsSender;
import com.alxndrsn.android.utils.sms.WholeSmsMessage;

import android.app.PendingIntent;
import android.content.ContentResolver;

/**
 * @author aga
 */
public class KeywordActionProcessor {
	private final SmsSender smsSender = new SmsSender();
	private final PropertySubstituter propSub;
	private final NumberListDao listDao;
	
	public KeywordActionProcessor(ContentResolver contentResolver) {
		this.propSub = new PropertySubstituter(contentResolver);
		this.listDao = new DbNumberListDao(contentResolver);
	}

	public void process(KeywordAction action, WholeSmsMessage message) {
		switch(action.getType()) {
		case REPLY:
			processReply(action, message);
			break;
		case FORWARD:
			processForward(action, message);
			break;
		case JOIN:
			processJoin(action, message);
			break;
		case LEAVE:
			processLeave(action, message);
			break;
		default:
			throw new IllegalStateException("Unknown action type: " + action.getClass());
		}
	}
	
	private void processJoin(KeywordAction action, WholeSmsMessage message) {
		if(action.getType() != KeywordAction.Type.JOIN) throw new IllegalStateException();
		this.listDao.addToList(action.getList(), message.getOriginatingAddress());
	}
	
	private void processLeave(KeywordAction action, WholeSmsMessage message) {
		if(action.getType() != KeywordAction.Type.LEAVE) throw new IllegalStateException();
		this.listDao.removeFromList(action.getList(), message.getOriginatingAddress());
	}

	private void processForward(KeywordAction action, WholeSmsMessage message) {
		if(action.getType() != KeywordAction.Type.FORWARD) throw new IllegalStateException();
		String unformattedText = action.getText();
		
		// get the group to send to
		Set<String> recipients = this.listDao.getNumbers(action.getList());
		// remove the original sender from the forward group
		recipients.remove(message.getOriginatingAddress());
		
		for(String recipient : recipients) {
			String forwardText = this.propSub.substitute(action, message, recipient, unformattedText);
			this.smsSender.sendSms(recipient, forwardText, getSentIntent(), getDeliveryIntent());
		}
	}

	private void processReply(KeywordAction action, WholeSmsMessage message) {
		if(action.getType() != KeywordAction.Type.REPLY) throw new IllegalStateException();
		String unformattedReplyText = action.getText();
		String replyText = this.propSub.substitute(action, message, message.getOriginatingAddress(), unformattedReplyText);
		this.smsSender.sendSms(message.getOriginatingAddress(), 
				replyText, getSentIntent(), getDeliveryIntent());
	}

	private PendingIntent getDeliveryIntent() {
		return null;
	}

	private PendingIntent getSentIntent() {
		return null;
	}
}
