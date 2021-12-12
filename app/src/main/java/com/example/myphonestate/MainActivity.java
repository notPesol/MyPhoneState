package com.example.myphonestate;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.telephony.PhoneStateListener;
import android.telephony.TelephonyManager;
import android.widget.TextView;
import android.widget.Toast;

public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        TextView textView = findViewById(R.id.textView);

        TelephonyManager telephonyManager = (TelephonyManager) getSystemService(TELEPHONY_SERVICE);
        telephonyManager.listen(new PhoneStateListener() {
            @Override
            public void onCallStateChanged(int state, String phoneNumber) {
                super.onCallStateChanged(state, phoneNumber);
                String msg = "";
                switch (state) {
                    case TelephonyManager.CALL_STATE_IDLE: {
                        msg = "ไม่ได้ใช้งานเกี่ยวกับการโทร";
                        break;
                    }
                    case TelephonyManager.CALL_STATE_RINGING: {
                        msg = "มีสายเข้าจาก: " + phoneNumber;
                        break;
                    }
                    case TelephonyManager.CALL_STATE_OFFHOOK: {
                        msg = "กำลังอยู่ในสาย หรือกำลังโทรออก";
                        break;
                    }
                }
                textView.setText(msg);
                Toast.makeText(getBaseContext(), msg, Toast.LENGTH_SHORT).show();
            }
        }, PhoneStateListener.LISTEN_CALL_STATE);
    }
}