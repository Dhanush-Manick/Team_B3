package com.example.scene;

import android.app.Activity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.CompoundButton;
import android.widget.Switch;

import com.huawei.hms.scene.sdk.ARView;

public class ARViewActivity extends Activity {
    private ARView mARView;
    private Button mButton;
    private boolean isLoadResource = false;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_ar_view);
        mARView = findViewById(R.id.ar_view);
        mButton = findViewById(R.id.button);
        Switch mSwitch = findViewById(R.id.show_plane_view);
        mSwitch.setChecked(true);
        mSwitch.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(CompoundButton buttonView, boolean isChecked) {
                mARView.enablePlaneDisplay(isChecked);
            }
        });
    }

    // Callback upon a button tap.
    public void onBtnClearResourceClicked(View view) {
        if (!isLoadResource) {
            mARView.loadAsset("sampledata/snow/snow_03_4k.gltf");
            float[] scale = new float[] { 0.1f, 0.1f, 0.1f };
            float[] rotation = new float[] { 0.707f, 0.0f, -0.707f, 0.0f };
            mARView.setInitialPose(scale, rotation);
            isLoadResource = true;
            mButton.setText("clear_resource");
        } else {
            mARView.clearResource();
            mARView.loadAsset("");
            isLoadResource = false;
            mButton.setText("load");
        }
    }

    // Synchronously call the onPause() method of the ARView.
    @Override
    protected void onPause() {
        super.onPause();
        mARView.onPause();
    }

    // Synchronously call the onResume() method of the ARView.
    @Override
    protected void onResume() {
        super.onResume();
        mARView.onResume();
    }

    // Synchronously call the destroy() method of the ARView.
    @Override
    protected void onDestroy() {
        super.onDestroy();
        mARView.destroy();
    }
}
