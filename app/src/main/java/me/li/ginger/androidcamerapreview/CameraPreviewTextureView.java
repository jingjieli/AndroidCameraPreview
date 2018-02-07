package me.li.ginger.androidcamerapreview;

import android.content.Context;
import android.graphics.SurfaceTexture;
import android.util.Log;
import android.view.TextureView;

/**
 * Created by astri on 6/2/2018.
 */

public class CameraPreviewTextureView extends TextureView implements TextureView.SurfaceTextureListener {

    private static final String TAG = "PreviewTextureView";

    SurfaceTexture mSurfaceTexture;

    public CameraPreviewTextureView(Context context) {
        super(context);
        this.setSurfaceTextureListener(this);
    }

    @Override
    public void onSurfaceTextureAvailable(SurfaceTexture surfaceTexture, int texWidth, int texHeight) {
        Log.d(TAG, "20180206 onSurfaceTextureAvailable");
        mSurfaceTexture = surfaceTexture;
        CameraInterface.getInstance().doOpenCamera(new CameraInterface.CamHasOpenedCallback() {
            @Override
            public void camHasOpened() {
                CameraInterface.getInstance().doStartPreview(mSurfaceTexture, 1.33f);
            }
        });
        //CameraInterface.getInstance().doStartPreview(surfaceTexture, 1.33f);
    }

    @Override
    public void onSurfaceTextureSizeChanged(SurfaceTexture surfaceTexture, int texWidth, int texHeight) {
        Log.d(TAG, "20180206 onSurfaceTextureSizeChanged");
    }

    @Override
    public boolean onSurfaceTextureDestroyed(SurfaceTexture surfaceTexture) {
        Log.d(TAG, "20180206 onSurfaceTextureDestroyed");
        CameraInterface.getInstance().doStopCamera();
        return false;
    }

    @Override
    public void onSurfaceTextureUpdated(SurfaceTexture surfaceTexture) {
        Log.d(TAG, "20180206 onSurfaceTextureUpdated");

    }

    public SurfaceTexture getSurfaceTexture() {
        return mSurfaceTexture;
    }
}
