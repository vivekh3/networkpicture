package com.example.networkpicture;

import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.media.Image;
import android.os.AsyncTask;
import android.os.Bundle;
import android.os.Handler;
import android.provider.ContactsContract;
import android.text.PrecomputedText;
import android.util.Log;
import android.widget.ImageView;

import java.io.BufferedInputStream;
import java.io.BufferedReader;
import java.io.ByteArrayOutputStream;
import java.io.DataInputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.PrintWriter;
import java.net.Socket;
import java.nio.ByteBuffer;
import java.util.Arrays;

import androidx.appcompat.app.AppCompatActivity;

import static java.lang.System.*;

public class MainActivity extends AppCompatActivity {
    //ImageView serverImage;
    //Handler h = new Handler();
    private Socket s;
    //private PrintWriter pw;
    private boolean mconnectexception = false;
    private static final String TAG = "MainActivity";


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        //serverImage = findViewById(R.id.video_frame);
        frame2video f2v = new frame2video();
        f2v.execute();
    }


    public class frame2video extends AsyncTask<Void, Void, Void> {

        @Override
        protected void onPreExecute() {

        }

        @Override
        protected Void doInBackground(Void...params) {
            InputStream inputStream;


            try {
                s = new Socket("192.168.0.132", 5003);

                if (s!=null){
                    Log.d(TAG, "Connection Successful..!");}

                else{
                    mconnectexception=true;
                }
                //String msg="Connection Successful";
                //return msg;

            } catch (IOException e) {
                e.printStackTrace();
                mconnectexception = true;
            }


            if (mconnectexception) {
                Log.d(TAG, "Connection not Available");
            }


            while (true) {
                try {
                    inputStream = s.getInputStream();
                    int bytesread=0;
                    int bytestoread=10;

                    //byte[] input=new byte[bytestoread];
                    byte[] input=new byte[bytestoread];
                    while (bytesread<bytestoread){
                        int result=inputStream.read(input,bytesread,bytestoread-bytesread);
                        if (result==-1){
                            break;
                        }
                        bytesread+=result;
                    }
                    String size=new String(input);
                    Log.d(TAG,"Image Length : "+ size);


                } catch (IOException e) {
                    e.printStackTrace();
                    Log.d(TAG,"Something is wrong");
                    return null;
                }
            }

        }


    }
}


