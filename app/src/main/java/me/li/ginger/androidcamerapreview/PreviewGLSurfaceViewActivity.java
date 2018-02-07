package me.li.ginger.androidcamerapreview;

import android.app.Activity;
import android.os.Bundle;
import android.widget.FrameLayout;

public class PreviewGLSurfaceViewActivity extends Activity {

    private CameraPreviewGLSurfaceView mPreviewGLSurfaceView;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_preview_glsurface_view);

        mPreviewGLSurfaceView = new CameraPreviewGLSurfaceView(this);

        FrameLayout mFrameLayout = (FrameLayout) findViewById(R.id.preview_glsurface_view);

        mFrameLayout.addView(mPreviewGLSurfaceView);
    }

    @Override
    protected void onResume() {
        super.onResume();
        mPreviewGLSurfaceView.bringToFront();
    }

    @Override
    protected void onPause() {
        mPreviewGLSurfaceView.onPause();
        super.onPause();
    }
}
