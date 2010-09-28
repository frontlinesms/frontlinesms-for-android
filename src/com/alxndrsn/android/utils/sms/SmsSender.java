/**
 * 
 */
package com.alxndrsn.android.utils.sms;

import com.alxndrsn.android.utils.Logger;

import android.app.PendingIntent;
import android.telephony.SmsManager;

/**
 * Looks like SMS splitting etc. is not provided by the android API, so
 * this class will handle that.
 * @author aga
 */
public class SmsSender {
	private Logger log = Logger.getLogger(this);
	/** SMS Service centre number.  Set this to <code>null</code> to use the default. */
	private String scAddress;
	private SmsManager smsManager = SmsManager.getDefault();
	
	public void sendSms(String destinationAddress, String text, PendingIntent sentIntent, PendingIntent deliveryIntent) {
		// TODO handle splitting for long SMS
		log.info("Sending 1 SMS to <" + destinationAddress + ">: " + text);
		this.smsManager.sendTextMessage(destinationAddress, this.scAddress, text, sentIntent, deliveryIntent);
	}
}
