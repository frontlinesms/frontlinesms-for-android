/**
 * 
 */
package net.frontlinesms.android;

import com.alxndrsn.android.utils.sms.ReceivedSmsHandler;
import com.alxndrsn.android.utils.sms.WholeSmsMessage;

/**
 * @author aga
 *
 */
public class SmsReceiver extends com.alxndrsn.android.utils.sms.SmsReceiver implements ReceivedSmsHandler {
	private MessageProcessor messageProcessor;

	@Override
	protected void init() {
		this.messageProcessor = new MessageProcessor(this.getContext().getContentResolver());
	}
	
	@Override
	protected ReceivedSmsHandler getReceivedSmsHandler() {
		return this;
	}

	@Override
	public void handleReceivedSms(WholeSmsMessage message) {
		this.messageProcessor.process(message);
	}

}
