package com.lcp.imagebrowsing;

import android.os.Bundle;
import android.support.design.widget.FloatingActionButton;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.Toast;

import com.nostra13.universalimageloader.cache.disc.naming.Md5FileNameGenerator;
import com.nostra13.universalimageloader.core.ImageLoader;
import com.nostra13.universalimageloader.core.ImageLoaderConfiguration;
import com.nostra13.universalimageloader.core.assist.QueueProcessingType;

import java.util.ArrayList;

public class MainActivity extends AppCompatActivity {

    private ArrayList<String> picList;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);
        FloatingActionButton fab = (FloatingActionButton) findViewById(R.id.fab);

        initImageLoader();
        picList = new ArrayList<>();
        picList.add("http://img.ivsky.com/img/bizhi/pre/201601/27/february_2016-001.jpg");
        picList.add("http://img.ivsky.com/img/bizhi/pre/201601/27/february_2016-002.jpg");
        picList.add("http://img.ivsky.com/img/bizhi/pre/201601/27/february_2016-003.jpg");
        picList.add("http://img.ivsky.com/img/bizhi/pre/201601/27/february_2016-004.jpg");
        picList.add("http://img.ivsky.com/img/tupian/pre/201511/16/chongwugou.jpg");
        final String content ="I'm alone above the atmosphere，And no one looking up can find me here" +
                "Cuz I can close my eyes and disappear，When I climb the stairs to watch the sun" +
                "Above the station walls  the colors run";

        //点击缩略图看大图
        findViewById(R.id.ll_image_content).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                //传入一个list
                Toast.makeText(MainActivity.this, "List", Toast.LENGTH_SHORT).show();
                ImagPagerUtil imagPagerUtil = new ImagPagerUtil(MainActivity.this, picList);
                imagPagerUtil.setContentText(content);
                imagPagerUtil.show();
            }
        });
    }

    public void initImageLoader() {
        // This configuration tuning is custom. You can tune every option, you
        // may tune some of them,
        // or you can create default configuration by
        // ImageLoaderConfiguration.createDefault(this);
        // method.
        ImageLoaderConfiguration config = new ImageLoaderConfiguration.Builder(
                getApplicationContext()).threadPriority(Thread.NORM_PRIORITY - 2)
                .denyCacheImageMultipleSizesInMemory()
                .diskCacheFileNameGenerator(new Md5FileNameGenerator())
                .tasksProcessingOrder(QueueProcessingType.LIFO)
                .writeDebugLogs() // Remove for release app
                .build();
        // Initialize ImageLoader with configuration.
        ImageLoader.getInstance().init(config);
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.menu_main, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        // Handle action bar item clicks here. The action bar will
        // automatically handle clicks on the Home/Up button, so long
        // as you specify a parent activity in AndroidManifest.xml.
        int id = item.getItemId();

        //noinspection SimplifiableIfStatement
        if (id == R.id.action_settings) {
            return true;
        }

        return super.onOptionsItemSelected(item);
    }
}
