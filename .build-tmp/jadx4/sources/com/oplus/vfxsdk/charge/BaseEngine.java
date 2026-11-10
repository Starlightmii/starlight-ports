package com.oplus.vfxsdk.charge;

import android.content.res.AssetManager;
import android.graphics.Bitmap;
import android.view.Surface;

/* JADX INFO: compiled from: go/retraceme fd74bf9514dd0739b92d67a6d48a51eb10aaf8df760d83496b8dcfcf83e5f698 */
/* JADX INFO: loaded from: /home/starlight/Projects/Starlight Ports/.build-tmp/classes4.dex */
public abstract class BaseEngine implements IEngine {
    static final String TAG = "BaseEngine";
    protected long mHandle = 0;

    /* JADX INFO: compiled from: go/retraceme fd74bf9514dd0739b92d67a6d48a51eb10aaf8df760d83496b8dcfcf83e5f698 */
    public enum TextureID {
        /* JADX INFO: Fake field, exist only in values array */
        BACKGROUND(0),
        /* JADX INFO: Fake field, exist only in values array */
        FOREGROUND(1),
        TEXTURE3(2),
        /* JADX INFO: Fake field, exist only in values array */
        TEXTURE4(3),
        TEXTURE5(4),
        /* JADX INFO: Fake field, exist only in values array */
        TEXTURE6(5),
        /* JADX INFO: Fake field, exist only in values array */
        TEXTURE7(6),
        /* JADX INFO: Fake field, exist only in values array */
        TEXTURE8(7),
        TEXTURE9(8),
        /* JADX INFO: Fake field, exist only in values array */
        TEXTURE10(9),
        /* JADX INFO: Fake field, exist only in values array */
        TEXTURE11(10),
        /* JADX INFO: Fake field, exist only in values array */
        TEXTURE12(11);

        public final int id;

        TextureID(int i) {
            this.id = i;
        }
    }

    static {
        System.loadLibrary("nativeChargingRing");
    }

    public static native void nativeDestroySurface(long j);

    public static native void nativeExit(long j);

    public static native void nativeSetAssetManager(long j, AssetManager assetManager);

    public static native void nativeSetBitmap(long j, Bitmap bitmap, int i);

    public static native void nativeSetDensity(long j, double d);

    public static native void nativeSetSurface(long j, Surface surface);

    public void destroySurface() {
        nativeDestroySurface(this.mHandle);
    }

    public void exit() {
        nativeExit(this.mHandle);
        this.mHandle = 0L;
    }

    @Override // com.oplus.vfxsdk.charge.IEngine
    public void setAssetManager(AssetManager assetManager) {
        nativeSetAssetManager(this.mHandle, assetManager);
    }

    public void setBitmap(Bitmap bitmap, TextureID textureID) {
        Debugger.i(TAG, "setBitmap handler" + this.mHandle);
        nativeSetBitmap(this.mHandle, bitmap, textureID.id);
    }

    @Override // com.oplus.vfxsdk.charge.IEngine
    public void setDensity(float f) {
        nativeSetDensity(this.mHandle, f);
    }

    @Override // com.oplus.vfxsdk.charge.IEngine
    public void setSurface(Surface surface) {
        Debugger.i(TAG, "setSurface");
        nativeSetSurface(this.mHandle, surface);
    }
}
