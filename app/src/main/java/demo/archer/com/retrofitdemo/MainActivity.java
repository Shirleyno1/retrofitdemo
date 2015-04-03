package demo.archer.com.retrofitdemo;

import android.content.Intent;
import android.os.AsyncTask;
import android.support.v7.app.ActionBarActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;

import demo.archer.com.retrofitdemo.ui.ShakeHomeActivity;
import demo.archer.com.retrofitdemo.utils.MobileMD5;
import demo.archer.com.retrofitdemo.utils.MobileUtil;
import retrofit.RequestInterceptor;
import retrofit.RestAdapter;
import retrofit.http.Body;
import retrofit.http.Field;
import retrofit.http.FormUrlEncoded;
import retrofit.http.GET;
import retrofit.http.POST;


public class MainActivity extends ActionBarActivity {

    private String signkey = "shdfDsdnFds27493749_+)!@%^%&*yWe";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
    }

    public void onClickText(View v) {
         Intent intent  =new Intent(this, ShakeHomeActivity.class);
        startActivity(intent);
    }


}
