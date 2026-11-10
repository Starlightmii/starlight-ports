package com.oplus.systemui.media.controls.ui;

import android.content.Context;
import android.content.res.ColorStateList;
import android.content.res.Configuration;
import android.content.res.Resources;
import android.graphics.Canvas;
import android.graphics.drawable.Drawable;
import android.util.AttributeSet;
import android.util.Log;
import android.view.ViewGroup;
import android.widget.LinearLayout;
import com.android.keyguard.OplusLockIconView$;
import com.android.systemui.DependencyEx;
import com.android.systemui.media.controls.ui.drawable.IlluminationDrawable;
import com.oplus.systemui.notification.aod.OplusPanoramicAodRepository;
import com.oplus.systemui.notification.base.radius.SmoothRoundEx;
import com.oplus.systemui.notification.blur.ViewBlurManager;
import com.oplusos.systemui.common.blurability.DebugTool;
import com.oplusos.systemui.common.blurability.ViewBlurProxy;
import com.oplusos.systemui.common.blurability.platformblur.PlatformBlurDrawable;
import com.oplusos.systemui.common.util.LogUtil;
import kotlin.jvm.internal.Intrinsics;

/* JADX INFO: compiled from: go/retraceme fd74bf9514dd0739b92d67a6d48a51eb10aaf8df760d83496b8dcfcf83e5f698 */
/* JADX INFO: loaded from: /home/starlight/Projects/Starlight Ports/.build-tmp/classes4.dex */
public final class OplusMediaCarouselView extends LinearLayout {
    public static final Companion Companion = new Companion();
    private static final String TAG = "OplusMediaCarouselView";
    public int bgScrollerAmount;
    public float contextViewTransitionX;
    private final Drawable defaultBgDrawable;
    public final OplusPanoramicAodRepository oplusPanoramicAodRepository;
    private Drawable panoramicAodDrawable;
    public final ViewBlurManager viewBlurManager;
    private ViewBlurProxy viewBlurProxy;

    /* JADX INFO: compiled from: go/retraceme fd74bf9514dd0739b92d67a6d48a51eb10aaf8df760d83496b8dcfcf83e5f698 */
    public final class Companion {
    }

    public OplusMediaCarouselView(Context context, AttributeSet attributeSet) {
        super(context, attributeSet);
        this.defaultBgDrawable = getResources().getDrawable(2131236350);
        this.oplusPanoramicAodRepository = (OplusPanoramicAodRepository) DependencyEx.sDependency.getDependency(OplusPanoramicAodRepository.class);
        this.viewBlurManager = (ViewBlurManager) DependencyEx.sDependency.getDependency(ViewBlurManager.class);
        setWillNotDraw(false);
        updateBgColor();
    }

    public final Drawable getBlurDrawable() {
        Drawable blurDrawable;
        ViewBlurProxy viewBlurProxy = this.viewBlurProxy;
        return (viewBlurProxy == null || (blurDrawable = viewBlurProxy.getBlurDrawable(this.defaultBgDrawable)) == null) ? this.defaultBgDrawable : blurDrawable;
    }

    public final Drawable getPanoramicAodDrawable() {
        if (this.oplusPanoramicAodRepository.getPanoramicAodProgress() == 0.0f) {
            return null;
        }
        return this.panoramicAodDrawable;
    }

    public final ViewBlurProxy getViewBlurProxy() {
        return this.viewBlurProxy;
    }

    @Override // android.view.ViewGroup, android.view.View
    public final void onAttachedToWindow() {
        super.onAttachedToWindow();
        this.viewBlurProxy = this.viewBlurManager.requireBlurProxyForView(this, (6 & 2) == 0, SmoothRoundEx.INSTANCE.getDefaultWeight());
        this.viewBlurManager.refreshBlurType(this);
    }

    @Override // android.view.View
    public void onConfigurationChanged(Configuration configuration) {
        super.onConfigurationChanged(configuration);
        if (this.defaultBgDrawable instanceof IlluminationDrawable) {
            this.defaultBgDrawable.setTintList(ColorStateList.valueOf(getContext().getResources().getColor(2131102729)));
            invalidate();
        }
    }

    @Override // android.view.ViewGroup, android.view.View
    public final void onDetachedFromWindow() {
        super.onDetachedFromWindow();
        this.viewBlurManager.releaseBlurProxyForView(this, true, "mediaCarouselView detach");
        ViewBlurProxy viewBlurProxy = this.viewBlurProxy;
        if (viewBlurProxy != null) {
            viewBlurProxy.setBlurEnabled(false);
        }
        this.viewBlurProxy = null;
    }

    @Override // android.widget.LinearLayout, android.view.View
    public void onDraw(Canvas canvas) {
        Drawable blurDrawable;
        Drawable panoramicAodDrawable;
        ViewBlurProxy viewBlurProxy = this.viewBlurProxy;
        if (viewBlurProxy == null || (blurDrawable = viewBlurProxy.getBlurDrawable(this.defaultBgDrawable)) == null) {
            blurDrawable = this.defaultBgDrawable;
        }
        ViewBlurProxy viewBlurProxy2 = this.viewBlurProxy;
        boolean z = true;
        if (!Intrinsics.areEqual(viewBlurProxy2 != null ? viewBlurProxy2.getBlurType() : null, ViewBlurProxy.BlurType.BlurTypeMotion.INSTANCE) || Intrinsics.areEqual(blurDrawable, this.defaultBgDrawable)) {
            blurDrawable.setBounds(0, 0, getWidth(), getHeight());
            if (getPanoramicAodDrawable() == null || (blurDrawable instanceof PlatformBlurDrawable)) {
                z = false;
            }
        } else {
            int width = ((ViewGroup) getParent()).getWidth();
            int i = this.bgScrollerAmount;
            int i2 = width + i;
            float f = this.contextViewTransitionX;
            if (f < 0.0f) {
                i = (int) (i - f);
            } else if (f > 0.0f) {
                i2 = (int) (i2 - f);
            }
            blurDrawable.setBounds(i, 0, i2, getHeight());
        }
        if (DebugTool.INSTANCE.getOpenDebug() && LogUtil.getNormalDebug() && LogUtil.isTagValid(LogUtil.TAG_NOTIFICATION)) {
            Log.d("SystemUi--Notification", "OplusMediaCarouselView-->".concat("[onDraw] " + blurDrawable + ", bounds= " + blurDrawable.getBounds()));
        }
        blurDrawable.draw(canvas);
        if (z && (panoramicAodDrawable = getPanoramicAodDrawable()) != null) {
            panoramicAodDrawable.setBounds(blurDrawable.getBounds());
            panoramicAodDrawable.draw(canvas);
        }
        super.onDraw(canvas);
    }

    @Override // android.view.View
    public final void onVisibilityAggregated(boolean z) {
        if (LogUtil.getNormalDebug() && LogUtil.isTagValid(LogUtil.TAG_NOTIFICATION)) {
            OplusLockIconView$.ExternalSyntheticOutline0.m("[onVisibilityAggregated] ", "OplusMediaCarouselView-->", "SystemUi--Notification", z);
        }
        super.onVisibilityAggregated(z);
        ViewBlurProxy viewBlurProxy = this.viewBlurProxy;
        if (viewBlurProxy != null) {
            viewBlurProxy.onVisibilityAggregated(z);
        }
    }

    public final void setPanoramicAodDrawable(Drawable drawable) {
        this.panoramicAodDrawable = drawable;
    }

    public final void updateBgColor() {
        Resources resources;
        if (this.defaultBgDrawable instanceof IlluminationDrawable) {
            Context context = getContext();
            Integer numValueOf = (context == null || (resources = context.getResources()) == null) ? null : Integer.valueOf(resources.getColor(2131102729));
            if (numValueOf != null) {
                this.defaultBgDrawable.setTintList(ColorStateList.valueOf(numValueOf.intValue()));
            }
        }
    }

    @Override // android.view.View
    public boolean verifyDrawable(Drawable drawable) {
        ViewBlurProxy viewBlurProxy;
        return super.verifyDrawable(drawable) || ((viewBlurProxy = this.viewBlurProxy) != null && viewBlurProxy.verifyDrawable(drawable));
    }
}
