package me.li.ginger.androidcamerapreview;

import android.content.Context;
import android.graphics.SurfaceTexture;
import android.opengl.GLES11Ext;
import android.opengl.GLES20;
import android.opengl.GLSurfaceView;
import android.util.AttributeSet;
import android.util.Log;
import android.view.TextureView;

import javax.microedition.khronos.egl.EGLConfig;
import javax.microedition.khronos.opengles.GL10;

/**
 * Created by astri on 7/2/2018.
 */

public class CameraPreviewGLSurfaceView extends GLSurfaceView
        implements GLSurfaceView.Renderer, SurfaceTexture.OnFrameAvailableListener {

    private static final String TAG = "PreviewGLSurfaceView";
    SurfaceTexture mSurfaceTexture;
    DirectDrawer mDirectDrawer;
    int mTextureId = -1;

    public CameraPreviewGLSurfaceView(Context context) {
        super(context);

        setEGLContextClientVersion(2);
        setRenderer(this);
        setRenderMode(RENDERMODE_WHEN_DIRTY);
    }

    @Override
    public void onSurfaceCreated(GL10 gl10, EGLConfig eglConfig) {
        Log.d(TAG, "20180207 onSurfaceCreated");
        mTextureId = createTextureId();
        mSurfaceTexture = new SurfaceTexture(mTextureId);
        mSurfaceTexture.setOnFrameAvailableListener(this);
        mDirectDrawer = new DirectDrawer(mTextureId);
        CameraInterface.getInstance().doOpenCamera(new CameraInterface.CamHasOpenedCallback() {
            @Override
            public void camHasOpened() {
                //Log.d(TAG, "20180207 callback ... do nothing ...");
                CameraInterface.getInstance().doStartPreview(mSurfaceTexture, 1.33f);
            }
        });
    }

    @Override
    public void onSurfaceChanged(GL10 gl10, int width, int height) {
        Log.d(TAG, "20180207 onSurfaceChanged");
        GLES20.glViewport(0, 0, width, height);
        //CameraInterface.getInstance().doStartPreview(mSurfaceTexture, 1.33f);
    }

    @Override
    public void onDrawFrame(GL10 gl10) {
        Log.d(TAG, "20180207 onDrawFrame");
        GLES20.glClearColor(1.0f, 1.0f, 1.0f, 1.0f);
        GLES20.glClear(GLES20.GL_COLOR_BUFFER_BIT | GLES20.GL_DEPTH_BUFFER_BIT);
       mSurfaceTexture.updateTexImage();
        float[] mtx = new float[16];
        mSurfaceTexture.getTransformMatrix(mtx);
        mDirectDrawer.draw(mtx);
    }

    @Override
    public void onFrameAvailable(SurfaceTexture surfaceTexture) {
        Log.d(TAG, "20180207 onFrameAvailable");
        this.requestRender();
    }

    @Override
    public void onPause() {
        CameraInterface.getInstance().doStopCamera();
        super.onPause();
    }

    private int createTextureId() {
        int[] texture = new int[1];

        GLES20.glGenTextures(1, texture, 0);
        GLES20.glBindTexture(GLES11Ext.GL_TEXTURE_EXTERNAL_OES, texture[0]);
        GLES20.glTexParameterf(GLES11Ext.GL_TEXTURE_EXTERNAL_OES,
                GL10.GL_TEXTURE_MIN_FILTER,GL10.GL_LINEAR);
        GLES20.glTexParameterf(GLES11Ext.GL_TEXTURE_EXTERNAL_OES,
                GL10.GL_TEXTURE_MAG_FILTER, GL10.GL_LINEAR);
        GLES20.glTexParameteri(GLES11Ext.GL_TEXTURE_EXTERNAL_OES,
                GL10.GL_TEXTURE_WRAP_S, GL10.GL_CLAMP_TO_EDGE);
        GLES20.glTexParameteri(GLES11Ext.GL_TEXTURE_EXTERNAL_OES,
                GL10.GL_TEXTURE_WRAP_T, GL10.GL_CLAMP_TO_EDGE);

        return texture[0];
    }

    public SurfaceTexture getSurfaceTexture() {
        return mSurfaceTexture;
    }
}
