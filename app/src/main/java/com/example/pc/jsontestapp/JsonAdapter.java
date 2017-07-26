package com.example.pc.jsontestapp;

import android.content.Context;
import android.content.Intent;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.net.Uri;
import android.os.AsyncTask;
import android.util.Base64;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.ImageView;
import android.widget.TextView;

import java.io.InputStream;
import java.net.URL;
import java.text.DecimalFormat;

/**
 * Created by pc on 2017-07-11.
 */

public class JsonAdapter extends BaseAdapter{

    private Context context;
    private JsonBean bean;

    public JsonAdapter(Context context, JsonBean bean){
        this.context = context;
        this.bean = bean;
    }

    @Override
    public int getCount() {
        return bean.getAdDealList().size();
    }

    @Override
    public Object getItem(int position) {
        return bean.getAdDealList().get(position);
    }

    @Override
    public long getItemId(int position) {
        return position;
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        //1. ListView에 표시하고자 하는 layout을 inflate함
        LayoutInflater li = (LayoutInflater)context.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
        convertView = li.inflate(R.layout.lay_json, null);

        //2. convertview에 뿌려줄 데이터를 취득한다.(1건)
        final JsonBean.AdDeal adDeallist = bean.getAdDealList().get(position);

        TextView txtDealName = (TextView)convertView.findViewById(R.id.txtDealName);
        ImageView imgThumbnail = (ImageView)convertView.findViewById(R.id.imgThumbnail);
        TextView txtPrice = (TextView)convertView.findViewById(R.id.txtPrice);

        txtDealName.setText(adDeallist.getDealName());
        String price = String.format("%,d", new Integer(adDeallist.getPrice()));
        txtPrice.setText(price);

        //이미지 비동기 표시
        new ImageLoaderTask(imgThumbnail).execute(adDeallist.getThumbnailPath());

        return convertView;
    }//end getView

    class ImageLoaderTask extends AsyncTask<String, Void, Bitmap>{
        private ImageView dispImgView;
        public ImageLoaderTask(ImageView dispImgView){
            this.dispImgView = dispImgView;
        }

        @Override
        protected Bitmap doInBackground(String... params) {
            String imgUrl = params[0];

            Bitmap bmp = null;

            try{
                bmp = BitmapFactory.decodeStream( (InputStream) new URL(imgUrl).getContent() );
            }catch (Exception e){
                e.printStackTrace();
            }
            return bmp;
        }//end doInBackground()

        @Override
        protected void onPostExecute(Bitmap bitmap) {
            if(bitmap != null){
                //표시
                dispImgView.setImageBitmap(bitmap);
            }
        }//end onPostExecute
    }//end class ImageLoaderTask
}
