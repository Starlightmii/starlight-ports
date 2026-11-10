package com.oplus.systemui.separate;

import android.content.Context;
import android.content.res.Configuration;
import android.util.AttributeSet;
import android.util.Log;
import android.view.MotionEvent;
import android.view.View;
import android.widget.FrameLayout;
import com.android.keyguard.OplusKeyguardDependencyEx;
import com.android.systemui.DependencyEx;
import com.android.systemui.keyguard.clockstyle.IKeyguardStyleClockController;
import com.android.systemui.navigationbar.gestural.domain.GestureInteractor$getTopActivity$2$;
import com.android.systemui.shade.NotificationPanelView;
import com.android.systemui.util.ViewController;
import com.oplusos.systemui.common.util.GlobalUtils;
import com.oplusos.systemui.common.util.LogUtil;
import kotlin.jvm.internal.DefaultConstructorMarker;

/* JADX INFO: compiled from: go/retraceme fd74bf9514dd0739b92d67a6d48a51eb10aaf8df760d83496b8dcfcf83e5f698 */
/* JADX INFO: loaded from: /home/starlight/Projects/Starlight Ports/.build-tmp/classes4.dex */
public final class OplusPanelViewPager extends FrameLayout {
    public static final Companion Companion = new Companion();
    private static final String TAG = "OplusPanelViewPager";
    private IKeyguardStyleClockController keyguardStyleClockController;
    public float lastLiveClockRedrawAlpha;
    private ViewController.OnConfigurationChangedListener mConfigurationListener;
    private OplusPanelViewPagerController.TouchHandler touchHandler;

    /* JADX INFO: compiled from: go/retraceme fd74bf9514dd0739b92d67a6d48a51eb10aaf8df760d83496b8dcfcf83e5f698 */
    public final class Companion {
    }

    public OplusPanelViewPager(Context context, AttributeSet attributeSet, int i) {
        super(context, attributeSet, i);
        setClipChildren(false);
        setClipToPadding(false);
        this.lastLiveClockRedrawAlpha = -1.0f;
    }

    @Override // android.view.ViewGroup, android.view.View
    public boolean dispatchTouchEvent(MotionEvent motionEvent) {
        if (motionEvent.getActionMasked() != 5 || !GlobalUtils.hasDialogShowing()) {
            return super.dispatchTouchEvent(motionEvent);
        }
        if (!LogUtil.getNormalDebug() || !LogUtil.isTagValid(LogUtil.TAG_COMMON)) {
            return false;
        }
        Log.d("SystemUi--Common", "OplusPanelViewPager-->interceptMultiTouchEvent: hasDialogShowing");
        return false;
    }

    @Override // android.view.ViewGroup, android.view.View
    public final void onAttachedToWindow() {
        super.onAttachedToWindow();
        OplusKeyguardDependencyEx oplusKeyguardDependencyEx = (OplusKeyguardDependencyEx) DependencyEx.sDependency.getDependency(OplusKeyguardDependencyEx.class);
        if (oplusKeyguardDependencyEx != null) {
            this.keyguardStyleClockController = oplusKeyguardDependencyEx.getKeyguardStyleClockController();
        }
    }

    @Override // android.view.View
    public void onConfigurationChanged(Configuration configuration) {
        super.onConfigurationChanged(configuration);
        ViewController.OnConfigurationChangedListener onConfigurationChangedListener = this.mConfigurationListener;
        if (onConfigurationChangedListener != null) {
            onConfigurationChangedListener.onConfigurationChange(configuration);
        }
    }

    @Override // android.view.ViewGroup, android.view.View
    public final void onDetachedFromWindow() {
        this.keyguardStyleClockController = null;
        this.lastLiveClockRedrawAlpha = -1.0f;
        super.onDetachedFromWindow();
    }

    @Override // android.view.View
    public final void onFinishInflate() {
        super.onFinishInflate();
    }

    @Override // android.view.ViewGroup
    public boolean onInterceptTouchEvent(MotionEvent motionEvent) {
        OplusPanelViewPagerController.TouchHandler touchHandler = this.touchHandler;
        if (touchHandler != null) {
            return touchHandler.onInterceptTouchEvent(motionEvent);
        }
        return false;
    }

    @Override // android.view.View
    public boolean onTouchEvent(MotionEvent motionEvent) {
        OplusPanelViewPagerController.TouchHandler touchHandler = this.touchHandler;
        if (touchHandler != null) {
            return touchHandler.onTouchEvent(motionEvent);
        }
        return false;
    }

    @Override // android.view.ViewGroup, android.view.ViewManager
    public void removeView(View view) {
        if (view instanceof NotificationPanelView) {
            GestureInteractor$getTopActivity$2$.ExternalSyntheticOutline0.m(8, "removeView error ", Log.getStackTraceString(new Throwable("")), LogUtil.TAG_COMMON, TAG);
        }
        super.removeView(view);
    }

    @Override // android.view.View
    public void setAlpha(float f) {
        super.setAlpha(f);
        IKeyguardStyleClockController iKeyguardStyleClockController = this.keyguardStyleClockController;
        if (iKeyguardStyleClockController == null || f == this.lastLiveClockRedrawAlpha) {
            return;
        }
        this.lastLiveClockRedrawAlpha = f;
        if (iKeyguardStyleClockController != null) {
            iKeyguardStyleClockController.requestLiveClockMirrorRedraw();
        }
    }

    public final void setConfigurationChangeListener(ViewController.OnConfigurationChangedListener onConfigurationChangedListener) {
        this.mConfigurationListener = onConfigurationChangedListener;
    }

    public final void setOnTouchListener(OplusPanelViewPagerController.TouchHandler touchHandler) {
        this.touchHandler = touchHandler;
    }

    public OplusPanelViewPager(Context context, AttributeSet attributeSet) {
        this(context, attributeSet, 0, 4, null);
    }

    public /* synthetic */ OplusPanelViewPager(Context context, AttributeSet attributeSet, int i, int i2, DefaultConstructorMarker defaultConstructorMarker) {
        this(context, (i2 & 2) != 0 ? null : attributeSet, (i2 & 4) != 0 ? 0 : i);
    }

    public OplusPanelViewPager(Context context) {
        this(context, null, 0, 6, null);
    }
}
