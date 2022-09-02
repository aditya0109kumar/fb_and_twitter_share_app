package com.aditya.shareapp;

import android.net.Uri;
import android.os.Bundle;
import android.util.Log;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.appcompat.app.AppCompatActivity;

import com.facebook.CallbackManager;
import com.facebook.appevents.AppEventsLogger;
import com.facebook.login.widget.LoginButton;
import com.facebook.share.model.ShareHashtag;
import com.facebook.share.model.ShareLinkContent;
import com.facebook.share.widget.ShareButton;
import com.facebook.share.widget.ShareDialog;
import com.twitter.sdk.android.tweetcomposer.TweetComposer;

public class MainActivity extends AppCompatActivity {

    private ShareButton fbShareBtn;
    private Button twitterShareBtn, shareOnFb;
    private LoginButton loginButton;
    private ImageView profilePic;
    private TextView accountName;
    CallbackManager callbackManager;
    ShareDialog shareDialog;
    String TAG = "MainActivity";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        //        FacebookSdk.sdkInitialize(getApplicationContext());
        AppEventsLogger.activateApp(getApplication());
        fbShareBtn = findViewById(R.id.fbShareBtn);
        twitterShareBtn = findViewById(R.id.twitterShareBtn);
        callbackManager = CallbackManager.Factory.create();
        profilePic = findViewById(R.id.profile_picture_iv);
        accountName = findViewById(R.id.account_name_tv);
        shareOnFb = findViewById(R.id.shareOnFb);
        shareDialog = new ShareDialog(this);
        loginButton = findViewById(R.id.loginButton);

//        loginButton.setReadPermissions(Arrays.asList("email, user_friends, user_gender"));
        // If you are using in a fragment, call loginButton.setFragment(this);

        // Callback registration
//        loginButton.registerCallback(callbackManager, new FacebookCallback<LoginResult>() {
//            @Override
//            public void onError(@NonNull FacebookException e) {
//                Log.d(TAG, "There was an error in login");
//            }
//
//            @Override
//            public void onSuccess(LoginResult loginResult) {
//                Log.d(TAG, "onSuccess: Login Successful");
//                // App code
//            }
//
//            @Override
//            public void onCancel() {
//                Log.d(TAG, "onCancel: Login Canceled");
//                // App code
//            }
//        });

//        LoginManager.getInstance().registerCallback(callbackManager,
//                new FacebookCallback<LoginResult>() {
//                    @Override
//                    public void onSuccess(LoginResult loginResult) {
//                        Log.d(TAG, "onSuccess: Login Successful 2");
//                        // App code
//                    }
//
//                    @Override
//                    public void onCancel() {
//                        Log.d(TAG, "The login was canceled 2");
//                        // App code
//                    }
//
//                    @Override
//                    public void onError(FacebookException exception) {
//                        Log.d(TAG, "There was an error in login 2");
//                        // App code
//                    }
//                });

        clickEvents();


    }

    private void clickEvents() {
//        fbShareBtn.setOnClickListener(v -> {
//            Toast.makeText(this, "FB share clicked", Toast.LENGTH_SHORT).show();
//        });

        twitterShareBtn.setOnClickListener(v -> {
            try {
                TweetComposer.Builder builder = new TweetComposer.Builder(this)
                        .text("https://youtu.be/kcmZX8-A98A");//any sharing text here
                builder.show();
            } catch (Exception e) {
                e.printStackTrace();
                Log.e(TAG, "exception occurred in tweet composer");
            }
        });

        shareOnFb.setOnClickListener(v -> {
            ShareDialog shareDialog = new ShareDialog(this);

            if (ShareDialog.canShow(ShareLinkContent.class)) {
                ShareLinkContent linkContent = new ShareLinkContent.Builder()
                        .setContentUrl(Uri.parse("https://youtu.be/kcmZX8-A98A"))
                        .setShareHashtag(new ShareHashtag.Builder().setHashtag("#video").build())
                        .setQuote("Quote")
                        .build();

                shareDialog.show(linkContent);
            }
        });
    }


//    @Override
//    protected void onActivityResult(int requestCode, int resultCode, @Nullable Intent data) {
//        super.onActivityResult(requestCode, resultCode, data);
//        callbackManager.onActivityResult(requestCode, resultCode, data);
//
//        GraphRequest graphRequest = GraphRequest.newMeRequest(AccessToken.getCurrentAccessToken(),
//                (jsonObject, graphResponse) -> {
//                    Log.d(TAG, jsonObject.toString());
//                    try {
//                        String name = jsonObject.getString("name");
//                        String id = jsonObject.getString("id");
//                        accountName.setText(name);
//                        Picasso.get().load("https://graph.facebook.com/" + id + "/picture?type-large")
//                                .into(profilePic);
//                    } catch (JSONException e) {
//                        e.printStackTrace();
//                    }
//                });

//        Bundle bundle = new Bundle();
//        bundle.putString("fields", "gender, name, id, first_name, last_name");
//
//        graphRequest.setParameters(bundle);
//        graphRequest.executeAsync();

//        ShareLinkContent linkContent = new ShareLinkContent.Builder()
//                .setContentUrl(Uri.parse("https://youtu.be/kcmZX8-A98A"))
//                .setShareHashtag(new ShareHashtag.Builder().setHashtag("#video").build())
//                .setQuote("Quote")
//                .build();
//
//
//        fbShareBtn.setShareContent(linkContent);
//    }

//    AccessTokenTracker accessTokenTracker = new AccessTokenTracker() {
//        @Override
//        protected void onCurrentAccessTokenChanged(@Nullable AccessToken oldAccessToken, @Nullable AccessToken currentAccessToken) {
//            if (currentAccessToken == null) {
//                LoginManager.getInstance().logOut();
//                accountName.setText("");
//                profilePic.setImageDrawable(null);
//            }
//        }
//    };

//    @Override
//    protected void onDestroy() {
//        super.onDestroy();
//        accessTokenTracker.stopTracking();
//    }
}