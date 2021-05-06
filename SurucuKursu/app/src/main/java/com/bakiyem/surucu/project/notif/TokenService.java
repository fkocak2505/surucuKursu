package com.bakiyem.surucu.project.notif;

import android.content.Intent;

import com.google.firebase.iid.FirebaseInstanceId;
import com.google.firebase.iid.FirebaseInstanceIdService;
import com.orhanobut.hawk.Hawk;

public class TokenService extends FirebaseInstanceIdService {

    @Override
    public void onTokenRefresh() {
        super.onTokenRefresh();

        String token = FirebaseInstanceId.getInstance().getToken();
        Hawk.put("fcmToken", token);

        Intent tokenIntent = new Intent("TOKEN_GENERATED");
        tokenIntent.putExtra("token", token);
        sendBroadcast(tokenIntent);
    }
}