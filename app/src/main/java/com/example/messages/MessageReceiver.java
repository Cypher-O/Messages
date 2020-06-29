package com.example.messages;
//
//import android.content.BroadcastReceiver;
//import android.content.Context;
//import android.content.Intent;
//import android.os.Bundle;
//import android.telephony.SmsMessage;
//import android.widget.Toast;
//
//import java.util.Objects;
//
//public class MessageReceiver extends BroadcastReceiver{
//
//    @Override
//    public void onReceive(Context context, Intent intent) {
//        //get msg passed in
//        Bundle bundle = intent.getExtras();
//        SmsMessage[] messages;
//        String x = "";
//
//        if(bundle != null){
//            Object[] pdus = (Object[]) bundle.get("pdus");
//            messages = new SmsMessage[pdus !=null ? pdus.length: 0];
//            for(int i=0; i<messages.length; i++){
//                messages[i] = SmsMessage.createFromPdu((byte[]) (pdus != null ? pdus[i] : null));
//                x += messages[i].getOriginatingAddress();
//                x += ": ";
//                x += messages[i].getMessageBody();
//                x += "\n";
//            }
//
//            //display the message
//            Toast.makeText(context, x, Toast.LENGTH_SHORT).show();
//
//            //send a broadcast intent to update the sms received in a textview
//            Intent broadcastIntent = new Intent();
//            broadcastIntent.setAction("SMS_RECEIVED_ACTION");
//            broadcastIntent.putExtra("message", x);
//            context.sendBroadcast(broadcastIntent);
//        }
//    }
//}

import android.annotation.TargetApi;
import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import android.os.Build;
import android.os.Bundle;
import android.telephony.SmsMessage;
import android.util.Log;
import android.widget.Toast;

public class MessageReceiver extends BroadcastReceiver {
//    private static final String TAG = MessageReceiver.class.getSimpleName();
//    public static final String pdu_type = "pdus";

    public static final String SMS_BUNDLE = "pdus";

    /**
     * Called when the BroadcastReceiver is receiving an Intent broadcast.
     *
     * @param context  The Context in which the receiver is running.
     * @param intent   The Intent received.
     */
//    @TargetApi(Build.VERSION_CODES.M)
    @Override
    public void onReceive(Context context, Intent intent) {
        // Get the SMS message.
        Bundle intentExtras = intent.getExtras();
        if (intentExtras != null) {
            Object[] sms = (Object[]) intentExtras.get(SMS_BUNDLE);
            String smsMessageStr = "";
            for (int i = 0; i < sms.length; ++i) {
                SmsMessage smsMessage = SmsMessage.createFromPdu((byte[]) sms[i]);

                String smsBody = smsMessage.getMessageBody().toString();
                String address = smsMessage.getOriginatingAddress();

                smsMessageStr += "SMS From: " + address + "\n";
                smsMessageStr += smsBody + "\n";
            }
            Toast.makeText(context, smsMessageStr, Toast.LENGTH_SHORT).show();

            //this will update the UI with message
            MessageMenu inst = MessageMenu.instance();
            inst.updateList(smsMessageStr);
        }
//        SmsMessage[] msgs;
//        String strMessage = "";
//        String format = bundle.getString("format");
//        // Retrieve the SMS message received.
//        Object[] pdus = (Object[]) bundle.get(pdu_type);
//        if (pdus != null) {
//            // Check the Android version.
//            boolean isVersionM = (Build.VERSION.SDK_INT >= Build.VERSION_CODES.M);
//            // Fill the msgs array.
//            msgs = new SmsMessage[pdus.length];
//            for (int i = 0; i < msgs.length; i++) {
//                // Check Android version and use appropriate createFromPdu.
//                if (isVersionM) {
//                    // If Android version M or newer:
//                    msgs[i] = SmsMessage.createFromPdu((byte[]) pdus[i], format);
//                } else {
//                    // If Android version L or older:
//                    msgs[i] = SmsMessage.createFromPdu((byte[]) pdus[i]);
//                }
//                // Build the message to show.
//                strMessage += "SMS from " + msgs[i].getOriginatingAddress();
//                strMessage += " :" + msgs[i].getMessageBody() + "\n";
//                // Log and display the SMS message.
//                Log.d(TAG, "onReceive: " + strMessage);
//                Toast.makeText(context, strMessage, Toast.LENGTH_LONG).show();
//            }
//        }
    }
}
