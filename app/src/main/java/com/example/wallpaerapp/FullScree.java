package com.example.wallpaerapp;

import androidx.appcompat.app.AppCompatActivity;

import android.app.DownloadManager;
import android.app.WallpaperManager;
import android.content.Context;
import android.content.Intent;
import android.graphics.Bitmap;
import android.graphics.drawable.BitmapDrawable;
import android.net.Uri;
import android.os.Bundle;
import android.view.View;
import android.widget.Toast;

import com.bumptech.glide.Glide;
import com.github.chrisbanes.photoview.PhotoView;

import java.io.IOException;

public class FullScree extends AppCompatActivity {
String originalurl="";
PhotoView photoView;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_full_scree);
        getSupportActionBar().hide();
        Intent intent=getIntent();

        originalurl=intent.getStringExtra("originalurl");
        photoView=findViewById(R.id.potoview);
        Glide.with(this).load(originalurl).into(photoView);


    }

    public void setwallpaperevet(View view) {

        WallpaperManager wallpaperManager = WallpaperManager.getInstance(this);
        Bitmap bitmap  =((BitmapDrawable)photoView.getDrawable()).getBitmap() ;

try {
    wallpaperManager.setBitmap(bitmap );
    Toast.makeText(this, "Wallpaper Set ", Toast.LENGTH_SHORT).show();

}



catch (IOException e){
e.printStackTrace();
}


    }




    public void dowaloadwallpaperevet(View view) {

        DownloadManager downloadManager=(DownloadManager)getSystemService(Context.DOWNLOAD_SERVICE);
        Uri uri=Uri.parse(originalurl);
        DownloadManager.Request request=new DownloadManager.Request(uri);
        request.setNotificationVisibility(DownloadManager.Request.VISIBILITY_VISIBLE_NOTIFY_COMPLETED);

        downloadManager.enqueue(request);
        Toast.makeText(this, "Dowlaodiag Start", Toast.LENGTH_SHORT).show();
    }


}