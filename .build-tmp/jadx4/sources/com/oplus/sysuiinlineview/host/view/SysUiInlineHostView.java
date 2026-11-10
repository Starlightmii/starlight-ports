package com.oplus.sysuiinlineview.host.view;

import android.content.Context;
import android.content.res.Configuration;
import android.os.Bundle;
import android.os.IBinder;
import android.util.AttributeSet;
import android.util.Log;
import android.view.KeyEvent;
import android.view.SurfaceControlViewHost;
import android.view.View;
import android.widget.ViewAnimator;
import com.oplus.sysuiinlineview.host.controller.SysUiInlineHostViewController;
import java.lang.invoke.VarHandle;
import java.util.concurrent.atomic.AtomicBoolean;
import java.util.concurrent.atomic.AtomicReference;
import kotlin.jvm.internal.DefaultConstructorMarker;

/* JADX INFO: compiled from: go/retraceme fd74bf9514dd0739b92d67a6d48a51eb10aaf8df760d83496b8dcfcf83e5f698 */
/* JADX INFO: loaded from: /home/starlight/Projects/Starlight Ports/.build-tmp/classes4.dex */
public class SysUiInlineHostView extends ViewAnimator implements IBinder.DeathRecipient {
    public static final /* synthetic */ int $r8$clinit = 0;
    private final String TAG;
    private final View.OnLayoutChangeListener hostViewLayoutChangeListener;
    private final SysUiInlineHostView$inlineViewCallback$1 inlineViewCallback;
    private final AtomicReference<SysUiInlineHostViewController> mController;
    private final AtomicReference<View> previewViewReference;
    private final String previewViewTag;
    private final AtomicReference<SysUiInlineSurfaceView> surfaceViewReference;
    private final String surfaceViewTag;
    private final AtomicBoolean visible;

    public SysUiInlineHostView(Context context, AttributeSet attributeSet) {
        super(context, attributeSet);
        this.TAG = getClass().getSimpleName();
        this.surfaceViewReference = new AtomicReference<>(null);
        this.previewViewReference = new AtomicReference<>(null);
        this.previewViewTag = context.getString(2131956750);
        this.surfaceViewTag = context.getString(2131956751);
        this.mController = new AtomicReference<>(null);
        this.visible = new AtomicBoolean(false);
        this.inlineViewCallback = new SysUiInlineHostView$inlineViewCallback$1(this);
        SysUiInlineHostView$$ExternalSyntheticLambda0 sysUiInlineHostView$$ExternalSyntheticLambda0 = new SysUiInlineHostView$$ExternalSyntheticLambda0();
        sysUiInlineHostView$$ExternalSyntheticLambda0.f$0 = this;
        VarHandle.storeStoreFence();
        this.hostViewLayoutChangeListener = sysUiInlineHostView$$ExternalSyntheticLambda0;
    }

    @Override // android.os.IBinder.DeathRecipient
    public final void binderDied() {
        SysUiInlineHostView$.ExternalSyntheticLambda1 externalSyntheticLambda1 = new SysUiInlineHostView$.ExternalSyntheticLambda1();
        externalSyntheticLambda1.f$0 = this;
        VarHandle.storeStoreFence();
        post(externalSyntheticLambda1);
    }

    public View configPreviewView(View view) {
        view.setTag(this.previewViewTag);
        addView(view);
        return view;
    }

    public SysUiInlineSurfaceView configSurfaceView(SysUiInlineSurfaceView sysUiInlineSurfaceView) {
        sysUiInlineSurfaceView.setTag(this.surfaceViewTag);
        sysUiInlineSurfaceView.getHolder().setFormat(-2);
        sysUiInlineSurfaceView.setZOrderOnTop(true);
        addView(sysUiInlineSurfaceView);
        return sysUiInlineSurfaceView;
    }

    public final SysUiInlineHostViewController getController() {
        return this.mController.get();
    }

    public final View.OnLayoutChangeListener getHostViewLayoutChangeListener() {
        return this.hostViewLayoutChangeListener;
    }

    public final View getPreviewView() {
        return this.previewViewReference.get();
    }

    public final SysUiInlineSurfaceView getSurfaceView() {
        return this.surfaceViewReference.get();
    }

    public final String getTAG() {
        return this.TAG;
    }

    @Override // android.view.ViewGroup, android.view.View
    public final void onAttachedToWindow() {
        super.onAttachedToWindow();
        onHostViewCreate();
        addOnLayoutChangeListener(this.hostViewLayoutChangeListener);
    }

    @Override // android.view.View
    public void onConfigurationChanged(Configuration configuration) {
        super.onConfigurationChanged(configuration);
        SysUiInlineHostViewController controller = getController();
        if (controller == null) {
            return;
        }
        controller.notifyHostViewConfigurationChanged(configuration);
    }

    @Override // android.view.ViewGroup, android.view.View
    public final void onDetachedFromWindow() {
        super.onDetachedFromWindow();
        removeOnLayoutChangeListener(this.hostViewLayoutChangeListener);
        this.visible.set(false);
        updateRunning();
        SysUiInlineHostViewController controller = getController();
        if (controller == null) {
            return;
        }
        controller.notifyHostViewDestroyed();
    }

    public final void onHostViewCreate() {
        SysUiInlineHostViewController controller = getController();
        if (controller == null) {
            return;
        }
        controller.notifyHostViewCreated(this.inlineViewCallback);
    }

    public final void onHostViewLayout() {
        SysUiInlineHostViewController controller;
        Log.d(this.TAG, "onHostViewLayout: width=" + getWidth() + ", height=" + getHeight());
        SysUiInlineSurfaceView surfaceView = getSurfaceView();
        if (surfaceView == null || (controller = getController()) == null) {
            return;
        }
        controller.notifyHostViewLaidOut(surfaceView.getHostToken(), surfaceView.getDisplay().getDisplayId(), getWidth(), getHeight());
    }

    /* JADX WARN: Failed to analyze thrown exceptions
    java.util.ConcurrentModificationException
    	at java.base/java.util.ArrayList$Itr.checkForComodification(ArrayList.java:1013)
    	at java.base/java.util.ArrayList$Itr.next(ArrayList.java:967)
    	at jadx.core.dex.visitors.MethodThrowsVisitor.processInstructions(MethodThrowsVisitor.java:130)
    	at jadx.core.dex.visitors.MethodThrowsVisitor.visit(MethodThrowsVisitor.java:68)
    	at jadx.core.dex.visitors.MethodThrowsVisitor.checkInsn(MethodThrowsVisitor.java:178)
    	at jadx.core.dex.visitors.MethodThrowsVisitor.processInstructions(MethodThrowsVisitor.java:131)
    	at jadx.core.dex.visitors.MethodThrowsVisitor.visit(MethodThrowsVisitor.java:68)
     */
    public void onInlineViewCreate(SurfaceControlViewHost.SurfacePackage surfacePackage) {
        SysUiInlineSurfaceView surfaceView = getSurfaceView();
        String str = this.TAG;
        StringBuilder sb = new StringBuilder("onInlineViewCreate: surfacePackage = ");
        sb.append(surfacePackage);
        sb.append(", isZOrderedOnTop = ");
        sb.append(surfaceView == null ? null : Boolean.valueOf(surfaceView.isZOrderedOnTop()));
        Log.d(str, sb.toString());
        if (surfacePackage == null) {
            return;
        }
        if (!isAttachedToWindow()) {
            Log.d(this.TAG, "onInlineViewCreate: isAttachedToWindow = false}");
        } else {
            if (!surfacePackage.getSurfaceControl().isValid()) {
                Log.d(this.TAG, "onInlineViewCreate: surfaceControl.isValid() = false}");
                return;
            }
            if (surfaceView != null) {
                surfaceView.setChildSurfacePackage(surfacePackage);
            }
            showView(surfaceView);
        }
    }

    public void onInlineViewKeyEvent(int i, KeyEvent keyEvent) {
        Log.d(this.TAG, "onInlineViewKeyEvent: keyCode=" + i + ", keyEvent=" + keyEvent);
        View rootView = getRootView();
        if (rootView == null) {
            return;
        }
        rootView.dispatchKeyEvent(keyEvent);
    }

    public void onInlineViewRequest(Bundle bundle) {
    }

    @Override // android.view.View
    public void onVisibilityChanged(View view, int i) {
        super.onVisibilityChanged(view, i);
        updateRunning();
    }

    @Override // android.view.View
    public final void onWindowVisibilityChanged(int i) {
        super.onWindowVisibilityChanged(i);
        this.visible.set(i == 0);
        updateRunning();
    }

    public final boolean setController(SysUiInlineHostViewController sysUiInlineHostViewController) {
        return this.mController.compareAndSet(null, sysUiInlineHostViewController);
    }

    public final boolean setPreviewView(View view) {
        View view2 = null;
        if (view != null && this.previewViewReference.get() == null) {
            view2 = view;
        }
        if (view2 == null) {
            return false;
        }
        this.previewViewReference.set(configPreviewView(view));
        return true;
    }

    public final boolean setSurfaceView(SysUiInlineSurfaceView sysUiInlineSurfaceView) {
        SysUiInlineSurfaceView sysUiInlineSurfaceView2 = null;
        if (sysUiInlineSurfaceView != null && this.surfaceViewReference.get() == null) {
            sysUiInlineSurfaceView2 = sysUiInlineSurfaceView;
        }
        if (sysUiInlineSurfaceView2 == null) {
            return false;
        }
        this.surfaceViewReference.set(configSurfaceView(sysUiInlineSurfaceView));
        return true;
    }

    public void showView(View view) {
        int iIndexOfChild = indexOfChild(view);
        String str = this.TAG;
        StringBuilder sb = new StringBuilder("setViewVisible: view=");
        sb.append(view == null ? null : view.getTag());
        sb.append(", indexOfView=");
        sb.append(iIndexOfChild);
        Log.d(str, sb.toString());
        setDisplayedChild(iIndexOfChild);
    }

    public final void updateRunning() {
        SysUiInlineHostViewController controller = getController();
        if (controller == null) {
            return;
        }
        controller.updateRunning(this.visible.get() && isShown());
    }

    /* JADX WARN: Illegal instructions before constructor call */
    public SysUiInlineHostView(Context context) {
        AttributeSet attributeSet = null;
        this(context, attributeSet, 2, attributeSet);
    }

    public /* synthetic */ SysUiInlineHostView(Context context, AttributeSet attributeSet, int i, DefaultConstructorMarker defaultConstructorMarker) {
        this(context, (i & 2) != 0 ? null : attributeSet);
    }
}
