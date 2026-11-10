package com.oplus.vfxsdk.charge.charging;

import android.content.Context;
import android.content.res.AssetManager;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.graphics.SurfaceTexture;
import android.util.AttributeSet;
import android.util.DisplayMetrics;
import android.view.Surface;
import android.view.TextureView;
import com.oplus.vfxsdk.charge.BaseEngine;
import com.oplus.vfxsdk.charge.Debugger;
import io.grpc.Status$;
import java.io.IOException;
import java.io.InputStream;
import java.lang.invoke.VarHandle;
import java.util.concurrent.ArrayBlockingQueue;
import java.util.concurrent.BlockingQueue;

/* JADX INFO: compiled from: go/retraceme fd74bf9514dd0739b92d67a6d48a51eb10aaf8df760d83496b8dcfcf83e5f698 */
/* JADX INFO: loaded from: /home/starlight/Projects/Starlight Ports/.build-tmp/classes4.dex */
public class VFXChargingTextureView extends TextureView implements TextureView.SurfaceTextureListener {
    public int mHeight;
    public final VFXChargingEngine mInstance;
    public volatile boolean mIsResourceLoaded;
    public volatile boolean mIsSurfaceDestroyed;
    public volatile BlockingQueue mQueue;
    public final Thread mResouceLoadThread;
    public Surface mSurface;
    public int mWidth;

    /* JADX INFO: renamed from: com.oplus.vfxsdk.charge.charging.VFXChargingTextureView$1, reason: invalid class name */
    /* JADX INFO: compiled from: go/retraceme fd74bf9514dd0739b92d67a6d48a51eb10aaf8df760d83496b8dcfcf83e5f698 */
    public final class AnonymousClass1 implements Runnable {
        public final /* synthetic */ int $r8$classId = 1;

        public AnonymousClass1() {
        }

        @Override // java.lang.Runnable
        public final void run() {
            switch (this.$r8$classId) {
                case 0:
                    VFXChargingTextureView vFXChargingTextureView = VFXChargingTextureView.this;
                    try {
                        InputStream inputStreamOpen = vFXChargingTextureView.getContext().getAssets().open("ring_4_in_1.png");
                        Bitmap bitmapDecodeStream = BitmapFactory.decodeStream(inputStreamOpen);
                        vFXChargingTextureView.mInstance.setBitmap(bitmapDecodeStream, BaseEngine.TextureID.TEXTURE3);
                        InputStream inputStreamOpen2 = vFXChargingTextureView.getContext().getAssets().open("glow.png");
                        Bitmap bitmapDecodeStream2 = BitmapFactory.decodeStream(inputStreamOpen2);
                        vFXChargingTextureView.mInstance.setBitmap(bitmapDecodeStream2, BaseEngine.TextureID.TEXTURE5);
                        InputStream inputStreamOpen3 = vFXChargingTextureView.getContext().getAssets().open("flash.png");
                        Bitmap bitmapDecodeStream3 = BitmapFactory.decodeStream(inputStreamOpen3);
                        vFXChargingTextureView.mInstance.setBitmap(bitmapDecodeStream3, BaseEngine.TextureID.TEXTURE9);
                        inputStreamOpen.close();
                        inputStreamOpen2.close();
                        inputStreamOpen3.close();
                        bitmapDecodeStream.recycle();
                        bitmapDecodeStream2.recycle();
                        bitmapDecodeStream3.recycle();
                        VFXChargingTextureView.this.mIsResourceLoaded = true;
                        Debugger.i("VFXChargingTextureView", "resource loaded.");
                        while (!VFXChargingTextureView.this.mQueue.isEmpty()) {
                            try {
                                VFXChargingTextureView.this.post((Runnable) ((ArrayBlockingQueue) VFXChargingTextureView.this.mQueue).take());
                            } catch (InterruptedException e) {
                                Status$.ExternalSyntheticBUOutline0.m(e);
                                return;
                            }
                        }
                        Debugger.i("VFXChargingTextureView", "resource loaded thread ended");
                    } catch (IOException e2) {
                        Debugger.i("VFXChargingTextureView", "load resources failed");
                        Status$.ExternalSyntheticBUOutline0.m(e2);
                        return;
                    }
                    break;
                default:
                    if (!VFXChargingTextureView.this.mIsSurfaceDestroyed) {
                        VFXChargingTextureView vFXChargingTextureView2 = VFXChargingTextureView.this;
                        vFXChargingTextureView2.mInstance.setSurface(vFXChargingTextureView2.mSurface);
                        break;
                    }
                    break;
            }
        }
    }

    /* JADX INFO: renamed from: com.oplus.vfxsdk.charge.charging.VFXChargingTextureView$2, reason: invalid class name */
    /* JADX INFO: compiled from: go/retraceme fd74bf9514dd0739b92d67a6d48a51eb10aaf8df760d83496b8dcfcf83e5f698 */
    public final class AnonymousClass2 implements Runnable {
        @Override // java.lang.Runnable
        public final void run() {
            Runtime.getRuntime().gc();
        }
    }

    /* JADX WARN: Failed to restore enum class, 'enum' modifier and super class removed */
    /* JADX WARN: Unknown enum class pattern. Please report as an issue! */
    /* JADX INFO: compiled from: go/retraceme fd74bf9514dd0739b92d67a6d48a51eb10aaf8df760d83496b8dcfcf83e5f698 */
    public final class ChargineType {
        public static final /* synthetic */ ChargineType[] $VALUES;
        public static final ChargineType HIGH;
        public static final ChargineType LOW;
        public static final ChargineType MEDIUM;

        static {
            ChargineType chargineType = new ChargineType("HIGH", 0);
            HIGH = chargineType;
            ChargineType chargineType2 = new ChargineType("MEDIUM", 1);
            MEDIUM = chargineType2;
            ChargineType chargineType3 = new ChargineType("LOW", 2);
            LOW = chargineType3;
            $VALUES = new ChargineType[]{chargineType, chargineType2, chargineType3};
        }

        public static ChargineType valueOf(String str) {
            return (ChargineType) Enum.valueOf(ChargineType.class, str);
        }

        public static ChargineType[] values() {
            return (ChargineType[]) $VALUES.clone();
        }
    }

    public VFXChargingTextureView(Context context) {
        super(context);
        VFXChargingEngine vFXChargingEngine = new VFXChargingEngine();
        this.mInstance = vFXChargingEngine;
        this.mSurface = null;
        this.mWidth = 0;
        this.mHeight = 0;
        this.mQueue = new ArrayBlockingQueue(10);
        this.mIsResourceLoaded = false;
        this.mIsSurfaceDestroyed = false;
        Thread thread = new Thread(new AnonymousClass1());
        this.mResouceLoadThread = thread;
        setSurfaceTextureListener(this);
        setOpaque(false);
        DisplayMetrics displayMetrics = getResources().getDisplayMetrics();
        vFXChargingEngine.init();
        vFXChargingEngine.setDensity(displayMetrics.density);
        thread.start();
    }

    @Override // android.view.TextureView.SurfaceTextureListener
    public final void onSurfaceTextureAvailable(SurfaceTexture surfaceTexture, int i, int i2) {
        Debugger.i("VFXChargingTextureView", "onSurfaceTextureAvailable");
        this.mSurface = new Surface(surfaceTexture);
        this.mWidth = i;
        this.mHeight = i2;
        setSurfaceAsync();
    }

    @Override // android.view.TextureView.SurfaceTextureListener
    public final boolean onSurfaceTextureDestroyed(SurfaceTexture surfaceTexture) {
        Debugger.i("VFXChargingTextureView", "onSurfaceTextureDestroyed");
        this.mIsSurfaceDestroyed = true;
        this.mInstance.destroySurface();
        this.mInstance.release();
        this.mInstance.exit();
        new Thread(new AnonymousClass2()).start();
        return false;
    }

    @Override // android.view.TextureView.SurfaceTextureListener
    public final void onSurfaceTextureSizeChanged(SurfaceTexture surfaceTexture, int i, int i2) {
        Debugger.i("VFXChargingTextureView", "onSurfaceTextureSizeChanged");
        if (this.mWidth == i && this.mHeight == i2) {
            return;
        }
        this.mWidth = i;
        this.mHeight = i2;
        this.mSurface = new Surface(surfaceTexture);
        setSurfaceAsync();
    }

    @Override // android.view.TextureView.SurfaceTextureListener
    public final void onSurfaceTextureUpdated(SurfaceTexture surfaceTexture) {
    }

    public void setAssetManager(AssetManager assetManager) {
        this.mInstance.setAssetManager(assetManager);
    }

    public void setChargineType(ChargineType chargineType) {
        float f;
        int iOrdinal = chargineType.ordinal();
        if (iOrdinal == 0) {
            this.mInstance.setParticles(256, 30, 0.003f, 15.0f);
            this.mInstance.setParticlesCircle(0.0f, 0.4f);
            setParticlesMoveType(0);
            this.mInstance.setParticlesRing(93.0f, 135.0f);
            this.mInstance.setRingAndParticlesCDDisplacement(2, 12, 0.08f, 0.08f);
            setRingGlowWeight(1.0f);
            setParticlesLuminance(1.11f);
            setParticlesDistortion(1.8f);
            this.mInstance.setRingGlowTime(6.0f, 5.0f, 3.0f, 0.6f);
            this.mInstance.setRingRotateSpeed(0.5f, 0.3f);
            this.mInstance.setBlurWeight(0.01f, 1.0f);
            setRingRadius(190);
            setFlashSize(60);
            setRingStartOffset(0.98f);
            setFlashDuration(1.28f);
            f = 0.0f;
            this.mInstance.setAnimationDuration(20.0f, 1.0f, 0.5f, 0.0f);
        } else if (iOrdinal == 1) {
            this.mInstance.setParticles(120, 30, 0.002f, 15.0f);
            this.mInstance.setParticlesCircle(0.0f, 0.36f);
            setParticlesMoveType(0);
            setParticlesLuminance(1.0f);
            this.mInstance.setParticlesRing(93.0f, 135.0f);
            this.mInstance.setRingAndParticlesCDDisplacement(0, 4, 0.03f, 0.03f);
            setRingGlowWeight(1.0f);
            setParticlesDistortion(1.5f);
            this.mInstance.setRingGlowTime(6.0f, 3.0f, 3.0f, 0.6f);
            this.mInstance.setRingRotateSpeed(0.45f, 0.3f);
            this.mInstance.setBlurWeight(0.01f, 1.0f);
            setFlashSize(60);
            setRingStartOffset(0.98f);
            setFlashDuration(1.28f);
            f = 0.0f;
            this.mInstance.setAnimationDuration(20.0f, 1.0f, 0.5f, 0.0f);
            setRingRadius(190);
        } else if (iOrdinal != 2) {
            f = 0.0f;
        } else {
            this.mInstance.setParticles(196, 20, 6.0E-4f, 6.0f);
            this.mInstance.setParticlesCircle(0.1f, 0.15f);
            setParticlesMoveType(1);
            this.mInstance.setParticlesRing(93.0f, 135.0f);
            this.mInstance.setRingAndParticlesCDDisplacement(0, 1, 0.03f, 0.03f);
            setRingGlowWeight(1.0f);
            setParticlesDistortion(1.1f);
            this.mInstance.setRingGlowTime(6.0f, 3.0f, 3.0f, 0.3f);
            this.mInstance.setBlurWeight(0.02f, 1.0f);
            this.mInstance.setRingRotateSpeed(0.35f, 0.3f);
            setRingRadius(190);
            setParticlesLuminance(1.0f);
            setFlashSize(60);
            setRingStartOffset(0.98f);
            setFlashDuration(1.28f);
            f = 0.0f;
            this.mInstance.setAnimationDuration(20.0f, 1.0f, 0.5f, 0.0f);
        }
        setRingLineWeight(1.0f);
        setRingRingWeight(1.0f);
        this.mInstance.setRingFadeInCurve(0.35f, 0.75f, f, 1.0f);
        this.mInstance.setRingFadeOutCurve(0.36f, 0.36f, 0.59f, 0.59f);
    }

    public void setChargingEngineControl(IChargingEngineControl iChargingEngineControl) {
        this.mInstance.setChargingEngineControl(iChargingEngineControl);
    }

    public void setDensityScale(float f) {
        this.mInstance.setDensityScale(f);
        Debugger.i("VFXChargingTextureView", "setDensityScale: " + f);
    }

    public void setFlashDuration(float f) {
        this.mInstance.setFlashDuration(f);
    }

    public void setFlashEnabled(boolean z) {
        this.mInstance.setFlashEnabled(z);
    }

    public void setFlashSize(int i) {
        this.mInstance.setFlashSize(i);
    }

    public void setFrameInterval(int i) {
        this.mInstance.setFrameInterval(i);
    }

    public void setParticlesDistortion(float f) {
        this.mInstance.setParticlesDistortion(f);
    }

    public void setParticlesLuminance(float f) {
        this.mInstance.setParticlesLuminance(f);
    }

    public void setParticlesMoveType(int i) {
        this.mInstance.setParticlesMoveType(i);
    }

    public void setRingGlowWeight(float f) {
        this.mInstance.setRingGlowWeight(f);
    }

    public void setRingLineWeight(float f) {
        this.mInstance.setRingLineWeight(f);
    }

    public void setRingRadius(int i) {
        this.mInstance.setRingRadius(i);
    }

    public void setRingRingWeight(float f) {
        this.mInstance.setRingRingWeight(f);
    }

    public void setRingStartOffset(float f) {
        this.mInstance.setRingStartOffset(f);
    }

    public final void setSurfaceAsync() {
        if (!this.mResouceLoadThread.isAlive()) {
            this.mInstance.setSurface(this.mSurface);
            return;
        }
        if (!this.mIsResourceLoaded) {
            Debugger.i("VFXChargingTextureView", "add to run after resource loaded");
            BlockingQueue blockingQueue = this.mQueue;
            AnonymousClass1 anonymousClass1 = new AnonymousClass1();
            VFXChargingTextureView.this = this;
            VarHandle.storeStoreFence();
            blockingQueue.add(anonymousClass1);
            Debugger.i("VFXChargingTextureView", "setSurface queued");
            return;
        }
        try {
            Debugger.i("VFXChargingTextureView", "wait for loading resource");
            this.mResouceLoadThread.join();
            if (this.mIsSurfaceDestroyed) {
                return;
            }
            this.mInstance.setSurface(this.mSurface);
        } catch (InterruptedException e) {
            Status$.ExternalSyntheticBUOutline0.m(e);
        }
    }

    public VFXChargingTextureView(Context context, AttributeSet attributeSet) {
        super(context, attributeSet);
        VFXChargingEngine vFXChargingEngine = new VFXChargingEngine();
        this.mInstance = vFXChargingEngine;
        this.mSurface = null;
        this.mWidth = 0;
        this.mHeight = 0;
        this.mQueue = new ArrayBlockingQueue(10);
        this.mIsResourceLoaded = false;
        this.mIsSurfaceDestroyed = false;
        Thread thread = new Thread(new AnonymousClass1());
        this.mResouceLoadThread = thread;
        setSurfaceTextureListener(this);
        setOpaque(false);
        DisplayMetrics displayMetrics = getResources().getDisplayMetrics();
        vFXChargingEngine.init();
        vFXChargingEngine.setDensity(displayMetrics.density);
        thread.start();
    }

    public VFXChargingTextureView(Context context, AttributeSet attributeSet, int i) {
        super(context, attributeSet, i);
        this.mInstance = new VFXChargingEngine();
        this.mSurface = null;
        this.mWidth = 0;
        this.mHeight = 0;
        this.mQueue = new ArrayBlockingQueue(10);
        this.mIsResourceLoaded = false;
        this.mIsSurfaceDestroyed = false;
        this.mResouceLoadThread = new Thread(new AnonymousClass1());
    }

    public VFXChargingTextureView(Context context, AttributeSet attributeSet, int i, int i2) {
        super(context, attributeSet, i, i2);
        this.mInstance = new VFXChargingEngine();
        this.mSurface = null;
        this.mWidth = 0;
        this.mHeight = 0;
        this.mQueue = new ArrayBlockingQueue(10);
        this.mIsResourceLoaded = false;
        this.mIsSurfaceDestroyed = false;
        this.mResouceLoadThread = new Thread(new AnonymousClass1());
    }
}
