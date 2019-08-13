package com.danhtran.androidbaseproject.extras.onesignal;

/**
 * Created by DanhTran on 7/15/2019.
 */
/*
public class MyNotificationOpenedHandler implements OneSignal.NotificationOpenedHandler {
    public static final String KEY_NOTIFICATION_DATA = "KEY_NOTIFICATION_DATA";
    public static final String KEY_NOTIFICATION_ACTION = "KEY_NOTIFICATION_ACTION";

    @Override
    public void notificationOpened(OSNotificationOpenResult result) {
        OSNotificationAction.ActionType actionType = result.action.type;
        JSONObject data = result.notification.payload.additionalData;
        Notification_Type notification_type = null;

        if (data != null) {
            int type = data.optInt("type", -1);
            notification_type = Notification_Type.fromValue(type);
            if (notification_type != null) {
                Logger.d("MyNotificationOpenedHandler: " + notification_type.getValue());
            }
        }

        if (actionType == OSNotificationAction.ActionType.ActionTaken) {
//            Log.i("OneSignalExample", "Button pressed with id: " + result.action.actionID);

            // The following can be used to open an Activity of your choice.
            // Replace - getApplicationContext() - with any Android Context.
            // Intent intent = new Intent(getApplicationContext(), YourActivity.class);
            // intent.setFlags(Intent.FLAG_ACTIVITY_REORDER_TO_FRONT | Intent.FLAG_ACTIVITY_NEW_TASK);
            // startActivity(intent);
        }

        Context context = MyApplication.Instance().getApplicationContext();
        Intent intent = new Intent(context, MainActivity.class);
        intent.putExtra(KEY_NOTIFICATION_DATA, notification_type);
        intent.putExtra(KEY_NOTIFICATION_ACTION, actionType);
        intent.setFlags(Intent.FLAG_ACTIVITY_CLEAR_TASK | Intent.FLAG_ACTIVITY_NEW_TASK);
        context.startActivity(intent);
    }
}*/
