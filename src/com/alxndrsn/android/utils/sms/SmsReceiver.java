/**
 * 
 */
package com.alxndrsn.android.utils.sms;

import com.alxndrsn.android.utils.Logger;

import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.telephony.SmsMessage;

/**
 * {@link BroadcastReceiver} for SMS messages.
 * @author aga
 */
public abstract class SmsReceiver extends BroadcastReceiver {
	private Logger log = Logger.getLogger(this);
	
	protected abstract ReceivedSmsHandler getReceivedSmsHandler();
	
	private Context context;
	
	protected Context getContext() {
		return this.context;
	}
	
	/** Initialise any necessary properties.  This must be called before
	 * getting the receivedSmsHandler. */
	// TODO does this make sense here, or should we pass on the context etc.
	// in this.getReceivedSmsHandler().handleReceivedSms(message) ?
	protected abstract void init();
	
	@Override
	public void onReceive(Context context, Intent intent) {
		IntentType intentType = IntentType.get(intent);
		if(intentType != null) {
			this.context = context;
			this.init();
			
			switch(intentType) {
			case SMS_RECEIVED:
				WholeSmsMessage message = getMessagesFromIntent(intent);
				if(message != null) {
					log.trace("Handling received message...");
					this.getReceivedSmsHandler().handleReceivedSms(message);
				} else {
					log.warn("No SmsMessage objects extracted from " + intentType);
				}
				break;
			default: throw new IllegalStateException("Unhandled intent: " + intentType.getAction());
			}
		}
	}

	private WholeSmsMessage getMessagesFromIntent(Intent intent) {
		Bundle bundle = intent.getExtras();
		if(bundle == null) return null;
		else {
			Object[] pdus = (Object[]) bundle.get("pdus");
			SmsMessage[] messages = new SmsMessage[pdus.length];
			for (int i = 0; i < messages.length; i++) {
				byte[] pdu = (byte[]) pdus[i];
				messages[i] = SmsMessage.createFromPdu(pdu);
			}
			return new WholeSmsMessage(messages);
		}
	}

}

enum IntentType {
	SMS_RECEIVED("android.provider.Telephony.SMS_RECEIVED");
	
	private final String action;
	
	IntentType(String action) { this.action = action; }
	
	public String getAction() {
		return action;
	}
	
	static IntentType get(Intent intent) {
		String action = intent.getAction();
		for(IntentType t : values()) {
			if(t.getAction().equals(action)) {
				return t;
			}
		}
		return null;
	}
}