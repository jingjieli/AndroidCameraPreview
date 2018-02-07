package me.li.ginger.androidcamerapreview;

import android.app.Activity;
import android.hardware.Camera;
import android.os.Bundle;
import android.widget.FrameLayout;

public class PreviewSurfaceViewActivity extends Activity {

    private Camera mCamera;
    private CameraPreviewSurfaceView mPreviewSurfaceView;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_preview_surface_view);

        mCamera = getCameraInstance();

        mPreviewSurfaceView = new CameraPreviewSurfaceView(this, mCamera);

        FrameLayout mFrameLayout = (FrameLayout) findViewById(R.id.preview_surface_view);

        mFrameLayout.addView(mPreviewSurfaceView);

    }

    public static Camera getCameraInstance() {
        Camera c = null;

        try {
            c = Camera.open();
        } catch (Exception e) {

        }

        return c;
    }
}
