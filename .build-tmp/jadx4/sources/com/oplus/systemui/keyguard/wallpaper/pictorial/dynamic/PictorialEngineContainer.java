package com.oplus.systemui.keyguard.wallpaper.pictorial.dynamic;

import android.content.Context;
import android.content.res.Configuration;
import android.util.AttributeSet;
import android.util.Log;
import android.view.MotionEvent;
import android.view.View;
import android.widget.FrameLayout;
import com.android.keyguard.OplusKeyguardDependencyEx;
import com.android.keyguard.base.OplusKeyguardCtrl$;
import com.android.systemui.DependencyEx;
import com.oplus.keyguard.security.view.OplusKeyguardSimInputViewController$;
import com.oplus.keyguard.utils.KeyguardUtils;
import com.oplus.systemui.keyguard.wallpaper.pictorial.slider.KeyguardSliderLayout;
import com.oplusos.systemui.common.util.LogUtil;

/* JADX INFO: compiled from: go/retraceme fd74bf9514dd0739b92d67a6d48a51eb10aaf8df760d83496b8dcfcf83e5f698 */
/* JADX INFO: loaded from: /home/starlight/Projects/Starlight Ports/.build-tmp/classes4.dex */
public final class PictorialEngineContainer extends FrameLayout {
    public static final Companion Companion = new Companion();
    private static final String TAG = "KeyguardEngineContainer";
    public long mDownTime;
    public float mDownX;
    public float mDownY;
    private View mOplusKeyguardView;
    private PictorialEngineContainerController mPictorialEngineContainerController;

    /* JADX INFO: compiled from: go/retraceme fd74bf9514dd0739b92d67a6d48a51eb10aaf8df760d83496b8dcfcf83e5f698 */
    public final class Companion {
    }

    public PictorialEngineContainer(Context context) {
        this(context, null);
    }

    public final View getMOplusKeyguardView() {
        return this.mOplusKeyguardView;
    }

    public final PictorialEngineContainerController getMPictorialEngineContainerController() {
        return this.mPictorialEngineContainerController;
    }

    @Override // android.view.ViewGroup, android.view.View
    public final void onAttachedToWindow() {
        super.onAttachedToWindow();
        PictorialEngineContainerController pictorialEngineContainerController = this.mPictorialEngineContainerController;
        if (pictorialEngineContainerController != null) {
            pictorialEngineContainerController.onViewAttached();
        }
    }

    @Override // android.view.View
    public void onConfigurationChanged(Configuration configuration) {
        PictorialEngineContainerController pictorialEngineContainerController = this.mPictorialEngineContainerController;
        if (pictorialEngineContainerController != null) {
            pictorialEngineContainerController.onConfigChanged(configuration);
        }
    }

    @Override // android.view.ViewGroup, android.view.View
    public final void onDetachedFromWindow() {
        super.onDetachedFromWindow();
        PictorialEngineContainerController pictorialEngineContainerController = this.mPictorialEngineContainerController;
        if (pictorialEngineContainerController != null) {
            pictorialEngineContainerController.onViewDetached();
        }
    }

    @Override // android.view.ViewGroup
    public boolean onInterceptTouchEvent(MotionEvent motionEvent) {
        Object sliderLayout;
        OplusKeyguardCtrl$.ExternalSyntheticOutline0.m("onInterceptTouchEvent :  ", motionEvent.getActionMasked(), getVisibility(), ", ", TAG);
        if (getVisibility() != 0) {
            return super.onInterceptTouchEvent(motionEvent);
        }
        if (motionEvent.getActionMasked() == 0) {
            this.mDownX = motionEvent.getX();
            this.mDownY = motionEvent.getY();
            this.mDownTime = motionEvent.getEventTime();
            return false;
        }
        if (motionEvent.getActionMasked() != 1 || motionEvent.getEventTime() - this.mDownTime >= 500) {
            return false;
        }
        int realScreenWidth = KeyguardUtils.Companion.getRealScreenWidth() / 3;
        int i = realScreenWidth / 2;
        int x = (int) (motionEvent.getX() - this.mDownX);
        int iAbs = Math.abs((int) (motionEvent.getY() - this.mDownY));
        if (x >= (-realScreenWidth) || iAbs >= i || (sliderLayout = ((OplusKeyguardDependencyEx) DependencyEx.sDependency.getDependency(OplusKeyguardDependencyEx.class)).getOplusKeyguardBottomAreaController().getSliderLayout()) == null || !(sliderLayout instanceof KeyguardSliderLayout) || !((KeyguardSliderLayout) sliderLayout).goSwipeEntry()) {
            return false;
        }
        if (LogUtil.getNormalDebug() && LogUtil.isTagValid(LogUtil.TAG_KEYGUARD)) {
            Log.d("SystemUi--Keyguard", OplusKeyguardSimInputViewController$.ExternalSyntheticOutline0.m("onInterceptTouchEvent: swipe for goSwipeEntry swipeDistance=", x, realScreenWidth, " swipeDistanceThreshold=", "KeyguardEngineContainer-->"));
        }
        return true;
    }

    @Override // android.widget.FrameLayout, android.view.ViewGroup, android.view.View
    public final void onLayout(boolean z, int i, int i2, int i3, int i4) {
        super.onLayout(z, i, i2, i3, i4);
        View view = this.mOplusKeyguardView;
        if (view != null) {
            view.setTop(0);
        }
        View view2 = this.mOplusKeyguardView;
        if (view2 != null) {
            view2.getClass();
            view2.setBottom(view2.getMeasuredHeight());
        }
    }

    @Override // android.view.View
    public boolean onTouchEvent(MotionEvent motionEvent) {
        return this.mOplusKeyguardView != null && getVisibility() == 0;
    }

    public final void setMOplusKeyguardView(View view) {
        this.mOplusKeyguardView = view;
    }

    public final void setMPictorialEngineContainerController(PictorialEngineContainerController pictorialEngineContainerController) {
        this.mPictorialEngineContainerController = pictorialEngineContainerController;
    }

    public PictorialEngineContainer(Context context, AttributeSet attributeSet, int i, int i2) {
        super(context, attributeSet, i, i2);
    }

    public PictorialEngineContainer(Context context, AttributeSet attributeSet) {
        this(context, attributeSet, -1);
    }

    public PictorialEngineContainer(Context context, AttributeSet attributeSet, int i) {
        this(context, attributeSet, i, -1);
    }
}
