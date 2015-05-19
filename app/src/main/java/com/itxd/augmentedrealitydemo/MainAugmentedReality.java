package com.itxd.augmentedrealitydemo;

import android.app.Activity;
import android.support.v7.app.ActionBarActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.Menu;
import android.view.MenuItem;

import com.metaio.sdk.ARViewActivity;
import com.metaio.sdk.MetaioDebug;
import com.metaio.sdk.jni.IGeometry;
import com.metaio.sdk.jni.IMetaioSDKCallback;
import com.metaio.sdk.jni.Vector3d;
import com.metaio.tools.io.AssetsManager;

import java.io.File;
import java.util.logging.FileHandler;


public class MainAugmentedReality extends ARViewActivity {

//    @Override
//    public void onCreate(Bundle savedInstanceState) {
//        super.onCreate(savedInstanceState);
//        setContentView(R.layout.activity_main_augmented_reality);
//    }
//
//    @Override
//    public boolean onCreateOptionsMenu(Menu menu) {
//        // Inflate the menu; this adds items to the action bar if it is present.
//        getMenuInflater().inflate(R.menu.menu_main_augmented_reality, menu);
//        return true;
//    }
//
//    @Override
//    public boolean onOptionsItemSelected(MenuItem item) {
//        // Handle action bar item clicks here. The action bar will
//        // automatically handle clicks on the Home/Up button, so long
//        // as you specify a parent activity in AndroidManifest.xml.
//        int id = item.getItemId();
//
//        //noinspection SimplifiableIfStatement
//        if (id == R.id.action_settings) {
//            return true;
//        }
//
//        return super.onOptionsItemSelected(item);
//    }

    @Override
    protected int getGUILayout() {
        return 0;
    }

    @Override
    protected IMetaioSDKCallback getMetaioSDKCallbackHandler()
    {
        // No callbacks needed in this tutorial
        return null;
    }

    @Override
    protected void loadContents() {
        try {
            // Get the reference tracking file from the XML
            AssetsManager.extractAllAssets(this, true);
            final File trackingConfigFile = AssetsManager.getAssetPathAsFile(getApplicationContext(), "AugmentedRealityAssets/TrackingData_MarkerlessAugmented.xml");

            MetaioDebug.log("This is the file" + trackingConfigFile);
            // Set tracking configuration
            boolean result = metaioSDK.setTrackingConfiguration(trackingConfigFile);
            MetaioDebug.log("Tracking data Added " + result);

            // Load model geometry
            final File filepath = AssetsManager.getAssetPathAsFile(getApplicationContext(), "AugmentedRealityAssets/Building.obj");
            if (filepath != null) {
                IGeometry geometry = metaioSDK.createGeometry(filepath);
                if (geometry != null){
                    geometry.setScale(new Vector3d(1f,1f,1f));
                    MetaioDebug.log("Loaded geometry" + filepath);
                } else {
                    MetaioDebug.log(Log.ERROR, "Error loading geometry" + filepath);
                }
            }
        } catch (Exception e)  {
            MetaioDebug.printStackTrace(Log.ERROR, e);
        }
    }

    @Override
    protected void onGeometryTouched(IGeometry geometry) {

    }
}
