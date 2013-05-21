package com.example.max;

import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.app.Activity;
import android.speech.tts.TextToSpeech;
import android.telephony.SmsManager;
import android.util.Log;
import android.view.Gravity;
import android.view.Menu;
import android.view.View;
import android.widget.*;

import java.util.Locale;

public class MainActivity extends Activity implements TextToSpeech.OnInitListener  {
    private ListView listView1;
    private EditText textbox;
    private Button sendButton;
    private TextToSpeech tts;
    private EditText mobileNumber;
    private Button clearButton;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        tts = new TextToSpeech(this, this);
        listView1 = (ListView) findViewById(R.id.listView);
       textbox = (EditText) findViewById(R.id.editText);
        mobileNumber = (EditText) findViewById(R.id.mobileNumber);
        sendButton = (Button) findViewById(R.id.button);
        clearButton = (Button) findViewById(R.id.clearButton);

        String[] items = { "Hello - হ্যালো", "How are you? - আপনি কেমন আছেন", "Call me - আমাকে ফোন", "Where are you? - আপনি কোথায়", "I'm Home - আমি বাড়িতে আছি", "come - আসা", "you - আপনি", "ok - ঠিক আছে","what - কি", "time - সময়", "go - যান","shopping - কেনাকাটা","no - কোন","yes - হ্যাঁ","please - দয়া করে","thank you - তোমাকে ধন্যবাদ","happy birthday - শুভ জন্মদিন","goodbye - বিদায়","i don't know - আমি জানি না","problem - সমস্যা","don't - না","i - তোমার দর্শন লগ করা","me - আমার সম্পর্কে" };

        PhraseList adapter = new PhraseList(this,
                android.R.layout.simple_list_item_1, items);

            listView1.setAdapter(adapter);

        listView1.setOnItemClickListener(new AdapterView.OnItemClickListener() {

            @Override
            public void onItemClick(AdapterView<?> arg0, View arg1, int position, long arg3) {

                Object o = listView1.getItemAtPosition(position);
                String msg = textbox.getText().toString();
                String newWord = o.toString();
                if (newWord.contains("-")) {
                    String[] arr =newWord.split("-");
                    newWord =arr[0];
                }
                textbox.setText(msg+" "+newWord);
                speakOut();

            }
        });

        clearButton.setOnClickListener(new View.OnClickListener() {
            public void onClick(View v) {
                textbox.setText("");
            }
        });

        sendButton.setOnClickListener(new View.OnClickListener() {
            public void onClick(View v) {
                String phoneNumber = mobileNumber.getText().toString();
                String toastMsg = "";
                if(phoneNumber == null || phoneNumber.isEmpty())
                {
                    toastMsg="Provide a Mobile Number";
                }else{
                    SmsManager sms = SmsManager.getDefault();
                    sms.sendTextMessage(phoneNumber, null, textbox.getText().toString(), null, null);
                    toastMsg="Sent Message";
                }
                Toast msg = Toast.makeText(MainActivity.this, toastMsg, Toast.LENGTH_LONG);

                msg.setGravity(Gravity.CENTER, msg.getXOffset() / 2, msg.getYOffset() / 2);

                msg.show();

            }
        });

    }
    private void speakOut() {

        String text = textbox.getText().toString();

        tts.speak(text, TextToSpeech.QUEUE_FLUSH, null);
    }

    @Override
    public void onInit(int status) {
        if (status == TextToSpeech.SUCCESS) {

            int result = tts.setLanguage(Locale.US);

            if (result == TextToSpeech.LANG_MISSING_DATA
                    || result == TextToSpeech.LANG_NOT_SUPPORTED) {
                Log.e("TTS", "This Language is not supported");
            } else {
              //  sendButton.setEnabled(true);
                speakOut();
            }

        } else {
            Log.e("TTS", "Initilization Failed!");
        }
    }
}
