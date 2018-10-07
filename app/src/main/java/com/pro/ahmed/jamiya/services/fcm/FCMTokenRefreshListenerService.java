package com.pro.ahmed.jamiya.services.fcm;



import com.google.firebase.iid.FirebaseInstanceId;
import com.google.firebase.iid.FirebaseInstanceIdService;
import com.pro.ahmed.jamiya.data.DataProcessor;

public class FCMTokenRefreshListenerService extends FirebaseInstanceIdService {
    @Override
    public void onTokenRefresh() {

        // Get updated InstanceID token.
        String token = FirebaseInstanceId.getInstance().getToken();

        // if user is log in then send token to server
        if (DataProcessor.getInt("user_id") != -1)
            sendTokenToServer(token);
    }

    // todo send token to server
    private void sendTokenToServer(String token) {
    }
}
