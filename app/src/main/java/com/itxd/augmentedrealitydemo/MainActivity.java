package com.itxd.augmentedrealitydemo;

import android.app.Activity;
import android.content.Intent;
import android.os.AsyncTask;
import android.support.v7.app.ActionBarActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;

import com.metaio.sdk.MetaioDebug;
import com.metaio.tools.io.AssetsManager;

import java.io.IOException;


public class MainActivity extends Activity {

    private Button main_virtual_tour;
    private Button main_augmented_reality;
    private Button main_360_view;
    private Button main_undefined;
    private Button main_projects;
    private EditText clickedText;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
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

    public void onClick_main_virtual_tour  (View v) {
        clickedText = (EditText) findViewById(R.id.clickedText);
        clickedText.setText("Pressed virtual tour");
        // Intent intent = new Intent(this, MainVirtualTour.class);
    }
    public void onClick_main_augmented_reality  (View v) {
        clickedText = (EditText) findViewById(R.id.clickedText);
        clickedText.setText("Pressed augmented reality");
        Intent intent = new Intent(this, MainAugmentedReality.class);
        startActivity(intent);
    }
    public void onClick_main_360_view  (View v) {
        clickedText = (EditText) findViewById(R.id.clickedText);
        clickedText.setText("Pressed 360 view");
        // Intent intent = new Intent(this, MainThreeSixty.class);
    }
    public void onClick_main_undefined  (View v) {
        clickedText = (EditText) findViewById(R.id.clickedText);
        clickedText.setText("Pressed undefined");
        // Intent intent = new Intent(this, MainUndefined.class);
    }
    public void onClick_main_projects  (View v) {
        clickedText = (EditText) findViewById(R.id.clickedText);
        clickedText.setText("Pressed projects");
        // Intent intent = new Intent(this, MainProyect.class);
    }

    /**
     * This task extracts all the assets to an external or internal location
     * to make them accessible to Metaio SDK
     */
    private class AssetsExtracter extends AsyncTask<Integer, Integer, Boolean>
    {

        @Override
        protected Boolean doInBackground(Integer... params) {
            try
            {
                // Extract all assets except Menu. Overwrite existing files for debug build only.
                final String[] ignoreList = {"Menu", "webkit", "sounds", "images", "webkitsec"};
                AssetsManager.extractAllAssets(getApplicationContext(), "", ignoreList, BuildConfig.DEBUG);
            }
            catch (IOException e)
            {
                MetaioDebug.printStackTrace(Log.ERROR, e);
                return false;
            }

            return true;
        }
    }
}
