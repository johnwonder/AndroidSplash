package com.facipeople.jn.faci;

import android.app.Activity;
import android.content.Intent;
import android.content.pm.PackageInfo;
import android.content.pm.PackageManager;
import android.graphics.PixelFormat;
import android.os.Bundle;
import android.os.Handler;
import android.view.WindowManager;
import android.widget.TextView;

/**
 * Created by jn on 2015/8/18.
 */
public class splashScreen extends Activity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        getWindow().setFormat(PixelFormat.RGBA_8888);
//        getWindow().addFlags(WindowManager.LayoutParams.FLAG_DI);


        setContentView(R.layout.splashscreen);
        getActionBar().hide();
        PackageManager pm = getPackageManager();
        try {
            PackageInfo pi = pm.getPackageInfo("com.faci.jn",0);
            TextView versionNumber = (TextView) findViewById(R.id.versionNumber);
            versionNumber.setText("Version: "+pi.versionName);

        }catch (PackageManager.NameNotFoundException e){
            e.printStackTrace();
        }

        new Handler().postDelayed(new Runnable() {
            @Override
            public void run() {
                Intent mainIntent = new Intent(splashScreen.this,fanciMain.class);
                splashScreen.this.startActivity(mainIntent);
                splashScreen.this.finish();

            }
        },2900);
    }
}
