package me.li.ginger.androidcamerapreview;

import android.app.Activity;
import android.graphics.SurfaceTexture;
import android.os.Bundle;
import android.widget.FrameLayout;

public class PreviewTextureViewActivity extends Activity implements CameraInterface.CamHasOpenedCallback {

    private CameraPreviewTextureView mPreviewTextureView;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_preview_texture_view);

        mPreviewTextureView = new CameraPreviewTextureView(this);

        FrameLayout mFrameLayout = (FrameLayout) findViewById(R.id.preview_texture_view);

        mFrameLayout.addView(mPreviewTextureView);

//        Thread mOpenCameraThread = new Thread() {
//            @Override
//            public void run() {
//                CameraInterface.getInstance().doOpenCamera(PreviewTextureViewActivity.this);
//            }
//        };
//        mOpenCameraThread.start();
    }

    @Override
    public void camHasOpened() {
        SurfaceTexture sTexture = mPreviewTextureView.getSurfaceTexture();
        CameraInterface.getInstance().doStartPreview(sTexture, 1.33f);
    }
}
