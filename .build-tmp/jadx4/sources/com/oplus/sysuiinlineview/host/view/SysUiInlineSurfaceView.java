package com.oplus.sysuiinlineview.host.view;

import android.content.Context;
import android.content.res.Configuration;
import android.util.AttributeSet;
import android.util.Log;
import android.view.SurfaceControlViewHost;
import android.view.SurfaceHolder;
import android.view.SurfaceView;
import androidx.collection.FloatList$;
import androidx.fragment.app.Fragment$5$;
import kotlin.Result;
import kotlin.ResultKt;
import kotlin.Unit;
import kotlin.jvm.internal.DefaultConstructorMarker;
import kotlin.jvm.internal.Intrinsics;

/* JADX INFO: compiled from: go/retraceme fd74bf9514dd0739b92d67a6d48a51eb10aaf8df760d83496b8dcfcf83e5f698 */
/* JADX INFO: loaded from: /home/starlight/Projects/Starlight Ports/.build-tmp/classes4.dex */
public class SysUiInlineSurfaceView extends SurfaceView implements SurfaceHolder.Callback {
    private final String TAG;
    private SurfaceControlViewHost.SurfacePackage mSurfacePackage;

    public /* synthetic */ SysUiInlineSurfaceView(Context context, AttributeSet attributeSet, int i, int i2, int i3, DefaultConstructorMarker defaultConstructorMarker) {
        this(context, (i3 & 2) != 0 ? null : attributeSet, (i3 & 4) != 0 ? 0 : i, (i3 & 8) != 0 ? 0 : i2);
    }

    public final SurfaceControlViewHost.SurfacePackage getSurfacePackage() {
        return this.mSurfacePackage;
    }

    public final String getTAG() {
        return this.TAG;
    }

    public final void notifySurfacePackageConfigurationChanged(Configuration configuration) {
        Object obj;
        if (configuration == null) {
            Log.e(this.TAG, "notifySurfacePackageConfigurationChanged: newConfig is null");
            return;
        }
        if (!isAttachedToWindow()) {
            Log.d(this.TAG, "notifySurfacePackageConfigurationChanged: has not attached to window");
            return;
        }
        SurfaceControlViewHost.SurfacePackage surfacePackage = getSurfacePackage();
        if (surfacePackage == null) {
            return;
        }
        try {
            surfacePackage.notifyConfigurationChanged(configuration);
            obj = Result.constructor-impl(Unit.INSTANCE);
        } catch (Throwable th) {
            obj = Result.constructor-impl(ResultKt.createFailure(th));
        }
        Throwable th2 = Result.exceptionOrNull-impl(obj);
        if (th2 != null) {
            Log.e(getTAG(), "notifySurfacePackageConfigurationChanged failed!", th2);
        }
    }

    @Override // android.view.SurfaceView, android.view.View
    public final void onAttachedToWindow() {
        super.onAttachedToWindow();
        Log.d(this.TAG, "onAttachedToWindow: ");
        getHolder().addCallback(this);
    }

    @Override // android.view.View
    public void onConfigurationChanged(Configuration configuration) {
        super.onConfigurationChanged(configuration);
        Log.d(this.TAG, "onConfigurationChanged: ");
        notifySurfacePackageConfigurationChanged(configuration);
    }

    @Override // android.view.SurfaceView, android.view.View
    public final void onDetachedFromWindow() {
        super.onDetachedFromWindow();
        Log.d(this.TAG, "onDetachedFromWindow: ");
        getHolder().removeCallback(this);
        setSurfacePackage(null);
    }

    @Override // android.view.SurfaceView
    public void setChildSurfacePackage(SurfaceControlViewHost.SurfacePackage surfacePackage) {
        super.setChildSurfacePackage(surfacePackage);
        Log.d(this.TAG, Intrinsics.stringPlus(surfacePackage, "setChildSurfacePackage: "));
        setSurfacePackage(surfacePackage);
    }

    public final void setSurfacePackage(SurfaceControlViewHost.SurfacePackage surfacePackage) {
        this.mSurfacePackage = surfacePackage;
    }

    @Override // android.view.SurfaceHolder.Callback
    public void surfaceChanged(SurfaceHolder surfaceHolder, int i, int i2, int i3) {
        Fragment$5$.ExternalSyntheticOutline0.m(i3, this.TAG, FloatList$.ExternalSyntheticOutline0.m("surfaceChanged: format=", i, i2, ", width=", ", height="));
    }

    @Override // android.view.SurfaceHolder.Callback
    public void surfaceCreated(SurfaceHolder surfaceHolder) {
        Log.d(this.TAG, "surfaceCreated: ");
    }

    @Override // android.view.SurfaceHolder.Callback
    public void surfaceDestroyed(SurfaceHolder surfaceHolder) {
        Log.d(this.TAG, "surfaceDestroyed: ");
    }

    public SysUiInlineSurfaceView(Context context, AttributeSet attributeSet) {
        this(context, attributeSet, 0, 0, 12, null);
    }

    public SysUiInlineSurfaceView(Context context, AttributeSet attributeSet, int i) {
        this(context, attributeSet, i, 0, 8, null);
    }

    public SysUiInlineSurfaceView(Context context) {
        this(context, null, 0, 0, 14, null);
    }

    public SysUiInlineSurfaceView(Context context, AttributeSet attributeSet, int i, int i2) {
        super(context, attributeSet, i, i2);
        this.TAG = getClass().getSimpleName();
    }
}
