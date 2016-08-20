package com.app.tringuyen.loginfacebook;

import android.content.Context;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.util.AttributeSet;
import android.view.View;

import com.facebook.AccessToken;
import com.facebook.FacebookSdk;
import org.greenrobot.eventbus.EventBus;
import org.greenrobot.eventbus.Subscribe;
import org.greenrobot.eventbus.ThreadMode;

public class MainActivity extends AppCompatActivity {
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        FacebookSdk.sdkInitialize(getApplicationContext());
        setContentView(R.layout.activity_main);

        if (AccessToken.getCurrentAccessToken() != null) {
            AfterLoginFragment afterLoginFragment = new AfterLoginFragment();
            getSupportFragmentManager().beginTransaction().add(R.id.fragment_container, afterLoginFragment).addToBackStack("LOGIN").commit();
        }
        else
        {
            LoginFacebookFragment loginFragment = new LoginFacebookFragment();
            getSupportFragmentManager().beginTransaction().add(R.id.fragment_container, loginFragment).addToBackStack("LOGIN").commit();
        }
    }

    @Override
    public View onCreateView(View parent, String name, Context context, AttributeSet attrs) {
        return super.onCreateView(parent, name, context, attrs);
    }

    @Override
    protected void onStart() {
        super.onStart();
        EventBus.getDefault().register(this);
    }

    @Override
    protected void onStop() {
        EventBus.getDefault().unregister(this);
        super.onStop();
    }


    // This method will be called when a MessageEvent is posted (in the UI thread for Toast)
    @Subscribe(threadMode = ThreadMode.MAIN)
    public void onMessageEvent(MessageEvent event) {
        AfterLoginFragment afterLoginFragment = new AfterLoginFragment();
        getSupportFragmentManager().beginTransaction().replace(R.id.fragment_container,afterLoginFragment).addToBackStack("MAIN").commit();
    }

    public static class MessageEvent {
        public final String message;
        public MessageEvent(String message)
        {
            this.message = message;
        }
    }
}