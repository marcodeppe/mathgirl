package com.bitsbytesbacon.marco.mathgirl;

import android.content.Context;
import android.net.Uri;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.text.Editable;
import android.text.TextWatcher;
import android.util.Log;
import android.view.View;
import android.view.WindowManager;
import android.view.animation.AlphaAnimation;
import android.view.animation.Animation;
import android.view.inputmethod.InputMethodManager;
import android.widget.EditText;
import android.widget.ImageButton;
import android.widget.ImageView;
import android.widget.TextView;

import com.google.android.gms.appindexing.Action;
import com.google.android.gms.appindexing.AppIndex;
import com.google.android.gms.common.api.GoogleApiClient;

import java.util.Random;

public class MainActivity extends AppCompatActivity {

    private static final int UPPER = 20;
    private static final String TAG = "mathgirl";
    private int resultInt;
    /**
     * ATTENTION: This was auto-generated to implement the App Indexing API.
     * See https://g.co/AppIndexing/AndroidStudio for more information.
     */
    private GoogleApiClient client;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        Log.d(TAG, "onCreate initializing.");
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        final InputMethodManager imm = (InputMethodManager) getSystemService(Context.INPUT_METHOD_SERVICE);
        final ImageButton nextQuestion = (ImageButton) findViewById(R.id.imageButton);
        final TextView equation = (TextView) findViewById(R.id.textEquation);
        final EditText answer = (EditText) findViewById(R.id.editAnswer);
        final ImageView checkImage = (ImageView) findViewById(R.id.imageCheck);

        final Animation startIn = new AlphaAnimation(0, 1);
        startIn.setDuration(600);

        final Animation fadeIn = new AlphaAnimation(0, 1);
        fadeIn.setDuration(600);

        final Animation fadeOut = new AlphaAnimation(1, 0);
        fadeOut.setStartOffset(1000);
        fadeOut.setDuration(600);

        startIn.setAnimationListener(new Animation.AnimationListener() {
            @Override
            public void onAnimationStart(Animation animation) {
                checkImage.setVisibility(View.INVISIBLE);
                answer.setVisibility(View.INVISIBLE);
                answer.setText("");
                equation.setVisibility(View.INVISIBLE);
                nextQuestion.setVisibility(View.VISIBLE);
                Log.d(TAG, "Start startIn.");
            }

            @Override
            public void onAnimationEnd(Animation animation) {

            }

            @Override
            public void onAnimationRepeat(Animation animation) {

            }
        });

        fadeIn.setAnimationListener(new Animation.AnimationListener() {
            @Override
            public void onAnimationStart(Animation animation) {
                checkImage.setVisibility(View.VISIBLE);
                Log.d(TAG, "Start fadeIn.");
            }

            @Override
            public void onAnimationEnd(Animation animation) {
                checkImage.setVisibility(View.INVISIBLE);
                nextQuestion.startAnimation(startIn);
                Log.d(TAG, "End fadeIn.");
            }

            @Override
            public void onAnimationRepeat(Animation animation) {
                Log.d(TAG, "Repeat fadeIn?!?");
            }
        });

        fadeOut.setAnimationListener(new Animation.AnimationListener() {
            @Override
            public void onAnimationStart(Animation animation) {
                checkImage.setVisibility(View.INVISIBLE);
                equation.setVisibility(View.INVISIBLE);
                answer.setVisibility(View.INVISIBLE);
                Log.d(TAG, "Start fadeOut.");
            }

            @Override
            public void onAnimationEnd(Animation animation) {
                nextQuestion.setVisibility(View.INVISIBLE);
                equation.setVisibility(View.VISIBLE);
                answer.setVisibility(View.VISIBLE);
                imm.showSoftInput(answer, InputMethodManager.SHOW_IMPLICIT);
                Log.d(TAG, "End fadeOut.");
            }

            @Override
            public void onAnimationRepeat(Animation animation) {

            }
        });

        nextQuestion.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Log.d(TAG, "Button clicked.");

                Random r = new Random();
                int i = r.nextInt(UPPER);
                int j;
                if (i == 0) {
                    j = 0;
                } else {
                    j = r.nextInt(i);
                }

                resultInt = i - j;

                equation.setText(" " + i + " - " + j + " ");
                nextQuestion.startAnimation(fadeOut);
            }
        });

        answer.addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence s, int start, int count, int after) {

            }

            @Override
            public void onTextChanged(CharSequence s, int start, int before, int count) {

            }

            @Override
            public void afterTextChanged(Editable s) {
                if (s != null && s.length() > 0) {
                    if (resultInt == Integer.parseInt(s.toString())) {
                        Log.d(TAG, s + " == " + resultInt + " is equal!");
                        imm.hideSoftInputFromWindow(answer.getWindowToken(), 0);
                        checkImage.startAnimation(fadeIn);
                    } else {
                        Log.d(TAG, s + " <> " + resultInt);
                    }
                } else {
                    Log.d(TAG, "answer is 0 length.");
                }
            }
        });

        Log.d(TAG, "Starting Animation.");
        nextQuestion.startAnimation(startIn);

        // ATTENTION: This was auto-generated to implement the App Indexing API.
        // See https://g.co/AppIndexing/AndroidStudio for more information.
        client = new GoogleApiClient.Builder(this).addApi(AppIndex.API).build();
    }

    @Override
    public void onStart() {
        super.onStart();

        // ATTENTION: This was auto-generated to implement the App Indexing API.
        // See https://g.co/AppIndexing/AndroidStudio for more information.
        client.connect();
        Action viewAction = Action.newAction(
                Action.TYPE_VIEW, // TODO: choose an action type.
                "Main Page", // TODO: Define a title for the content shown.
                // TODO: If you have web page content that matches this app activity's content,
                // make sure this auto-generated web page URL is correct.
                // Otherwise, set the URL to null.
                Uri.parse("http://host/path"),
                // TODO: Make sure this auto-generated app deep link URI is correct.
                Uri.parse("android-app://com.bitsbytesbacon.marco.mathgirl/http/host/path")
        );
        AppIndex.AppIndexApi.start(client, viewAction);
    }

    @Override
    public void onStop() {
        super.onStop();

        // ATTENTION: This was auto-generated to implement the App Indexing API.
        // See https://g.co/AppIndexing/AndroidStudio for more information.
        Action viewAction = Action.newAction(
                Action.TYPE_VIEW, // TODO: choose an action type.
                "Main Page", // TODO: Define a title for the content shown.
                // TODO: If you have web page content that matches this app activity's content,
                // make sure this auto-generated web page URL is correct.
                // Otherwise, set the URL to null.
                Uri.parse("http://host/path"),
                // TODO: Make sure this auto-generated app deep link URI is correct.
                Uri.parse("android-app://com.bitsbytesbacon.marco.mathgirl/http/host/path")
        );
        AppIndex.AppIndexApi.end(client, viewAction);
        client.disconnect();
    }
}
