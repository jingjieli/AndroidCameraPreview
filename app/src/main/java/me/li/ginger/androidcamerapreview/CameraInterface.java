package me.li.ginger.androidcamerapreview;


import android.graphics.SurfaceTexture;
import android.hardware.Camera;
import android.view.SurfaceHolder;

/**
 * Created by astri on 6/2/2018.
 */

public class CameraInterface {

    public interface CamHasOpenedCallback {
        public void camHasOpened();
    }

    private static CameraInterface mCameraInterface;
    private Camera mCamera;

    private CameraInterface() {

    }

    public static synchronized CameraInterface getInstance() {
        if (mCameraInterface == null) {
            mCameraInterface = new CameraInterface();
        }
        return mCameraInterface;
    }

    public void doOpenCamera(CamHasOpenedCallback callback) {
        try {
            mCamera = Camera.open();
        } catch (Exception e) {

        }
        callback.camHasOpened();
    }

    public void doStopCamera() {
        mCamera.stopPreview();
    }

    public void doStartPreview(SurfaceHolder sHolder, float previewRate) {
        if (mCamera != null) {
            try {
                mCamera.setPreviewDisplay(sHolder);
                mCamera.startPreview();
                initCamera(1.33f);
            } catch (Exception e) {

            }
        }
    }

    public void doStartPreview(SurfaceTexture sTexture, float previewRate) {
        if (mCamera != null) {
            try {
                mCamera.setPreviewTexture(sTexture);
                mCamera.startPreview();
                initCamera(1.33f);
            } catch (Exception e) {

            }
        }
    }

//    public void doStopPreview() {
//        mCamera.stopPreview();
//    }

    private void initCamera(float previewRate) {
        mCamera.setDisplayOrientation(90);
    }

}
