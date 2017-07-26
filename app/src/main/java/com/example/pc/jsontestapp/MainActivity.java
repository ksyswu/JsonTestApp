package com.example.pc.jsontestapp;

import android.app.ProgressDialog;
import android.content.Intent;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.os.AsyncTask;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.widget.ImageView;
import android.widget.ListView;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import com.stanfy.gsonxml.GsonXml;
import com.stanfy.gsonxml.GsonXmlBuilder;
import com.stanfy.gsonxml.XmlParserCreator;

import org.xmlpull.v1.XmlPullParser;
import org.xmlpull.v1.XmlPullParserFactory;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.URL;

public class MainActivity extends AppCompatActivity {

    private ImageView imageView;
    private ListView mlistView;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        mlistView = (ListView)findViewById(R.id.listView);
        imageView = (ImageView)findViewById(R.id.imgThumbnail);

        new JsonTask().execute("http://www.showpun.com/api/common/v1/adDeal/selectAdDealListAjax.ans?adDealGrpId=1");
    }

    class JsonTask extends AsyncTask<String , String, JsonBean>{

        private ProgressDialog prd;

        @Override
        protected void onPreExecute() {     //네트워크 통신시 progressdialog띄워야함
            prd = new ProgressDialog(MainActivity.this); //MainActivity.this 써도됨
            prd.setMessage("광고리스트를 가져오는 중입니다...");
            prd.setCancelable(false);
            prd.show();
        }

        @Override
        protected JsonBean doInBackground(String... params) {

            StringBuilder output = new StringBuilder();

            try {
                URL url = new URL(params[0]);

                BufferedReader reader = new BufferedReader(new InputStreamReader(url.openStream()));

                String line = null;
                while (true) {
                    line = reader.readLine();
                    if (line == null) break;
                    output.append(line + "\n");
                }
                reader.close();

                String jsonData = output.toString();

                //파싱을 시작한다.
                Gson gson = new GsonBuilder().create();

                JsonBean jsonBean = gson.fromJson(jsonData, JsonBean.class);

                return jsonBean;
            }
            catch (Exception e) {
                e.printStackTrace();
            }
            return null;
        }//end doInBackground

        @Override
        protected void onPostExecute(JsonBean bean) {
            prd.dismiss();

            if(bean != null){
                JsonAdapter adapter = new JsonAdapter(MainActivity.this, bean);
                mlistView.setAdapter(adapter);
            }
        }//end onPostExecute

    }//end Class JsonTask

}
