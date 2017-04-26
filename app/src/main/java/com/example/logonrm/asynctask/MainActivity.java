package com.example.logonrm.asynctask;

import android.app.ProgressDialog;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.os.AsyncTask;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.ImageView;

import java.io.IOException;
import java.io.InputStream;
import java.net.URL;

public class MainActivity extends AppCompatActivity {

    private static final String IMG_URL = "https://images8.alphacoders.com/565/thumb-1920-565549.png";
    private ProgressDialog mProgressDialog;
    private ImageView imgDownload;



    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        imgDownload = (ImageView) findViewById(R.id.imgDownload);
    }

    public void download(View view) {
        DownloadSyncTask downloadSyncTask = new DownloadSyncTask();
        downloadSyncTask.execute(IMG_URL);
    }

    private class DownloadSyncTask extends AsyncTask<String, Void, Bitmap>{

        @Override
        protected  void onPreExecute(){
            super.onPreExecute();
            mProgressDialog = ProgressDialog.show(MainActivity.this, "Aguarde","Fazendo download da imagem da mina");
        }

        @Override
        protected Bitmap doInBackground(String... strings) {
            try{
                InputStream inputStream;
                Bitmap imagem;

                URL endereco = new URL( strings[0] );
                inputStream = endereco.openStream();
                imagem = BitmapFactory.decodeStream(inputStream);
                inputStream.close();
                return  imagem;
            }catch (IOException e){
                e.printStackTrace();
            }

            return null;
        }

        @Override
        protected void onPostExecute(Bitmap bitmap) {
            super.onPostExecute(bitmap);
            if (bitmap != null){
                imgDownload.setImageBitmap(bitmap);
            }
            mProgressDialog.dismiss();
        }
    }

}
