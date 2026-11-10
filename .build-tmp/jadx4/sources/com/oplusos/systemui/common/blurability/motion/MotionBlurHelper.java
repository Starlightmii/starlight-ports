package com.oplusos.systemui.common.blurability.motion;

import android.util.Log;
import android.view.View;
import com.android.internal.graphics.drawable.BackgroundBlurDrawable;
import com.android.keyguard.OplusLockIconView$;
import com.oplus.graphics.OplusBlurParam;
import com.oplus.view.ViewRootManager;
import com.oplusos.systemui.common.blurability.BlurConfig;
import com.oplusos.systemui.common.blurability.DebugToolKt;
import com.oplusos.systemui.common.util.LogUtil;
import java.lang.invoke.VarHandle;
import kotlin.jvm.functions.Function1;
import kotlin.jvm.internal.Intrinsics;

/* JADX INFO: compiled from: go/retraceme fd74bf9514dd0739b92d67a6d48a51eb10aaf8df760d83496b8dcfcf83e5f698 */
/* JADX INFO: loaded from: /home/starlight/Projects/Starlight Ports/.build-tmp/classes4.dex */
public final class MotionBlurHelper {
    public static final Companion Companion = new Companion();
    private static final String TAG = "MotionBlurHelper";
    private final BlurConfig blurConfig;
    private BackgroundBlurDrawable blurDrawable;
    public boolean blurEnabled;
    private final View hostView;
    private final MotionBlurHelper$onAttachListener$1 onAttachListener;
    private Function1 onBlurDrawableCreated;
    private Function1 onViewAlphaToUser;

    /* JADX INFO: compiled from: go/retraceme fd74bf9514dd0739b92d67a6d48a51eb10aaf8df760d83496b8dcfcf83e5f698 */
    public final class Companion {
    }

    public MotionBlurHelper(View view, BlurConfig blurConfig) {
        this.hostView = view;
        this.blurConfig = blurConfig;
        MotionBlurHelper$onAttachListener$1 motionBlurHelper$onAttachListener$1 = new MotionBlurHelper$onAttachListener$1();
        motionBlurHelper$onAttachListener$1.this$0 = this;
        VarHandle.storeStoreFence();
        this.onAttachListener = motionBlurHelper$onAttachListener$1;
    }

    private final ViewRootManager getViewRootManager() {
        ViewRootManager viewRootManager = new ViewRootManager(this.hostView);
        if (!this.blurConfig.getEnableMotionSmoothCorner()) {
            OplusBlurParam oplusBlurParam = new OplusBlurParam();
            Float radiusWeight = this.blurConfig.getRadiusWeight();
            if (radiusWeight != null) {
                oplusBlurParam.setSmoothCornerWeight(radiusWeight.floatValue());
            }
            viewRootManager.setBlurParams(oplusBlurParam);
            return viewRootManager;
        }
        if (this.blurConfig.getBlurRadius() > 0) {
            viewRootManager.setBlurRadius(this.blurConfig.getBlurRadius());
        }
        float cornerRadius = this.blurConfig.getCornerRadius();
        if (cornerRadius > 0.0f) {
            viewRootManager.setCornerRadius(cornerRadius);
        }
        OplusBlurParam oplusBlurParam2 = new OplusBlurParam();
        Float radiusWeight2 = this.blurConfig.getRadiusWeight();
        if (radiusWeight2 != null) {
            float fFloatValue = radiusWeight2.floatValue();
            if (fFloatValue > 0.0f) {
                oplusBlurParam2.setSmoothCornerWeight(fFloatValue);
            }
        }
        viewRootManager.setBlurParams(oplusBlurParam2);
        return viewRootManager;
    }

    public final void ensureBlurDrawable() {
        Function1 function1;
        if (this.blurDrawable == null && this.blurEnabled && this.hostView.isAttachedToWindow()) {
            BackgroundBlurDrawable backgroundBlurDrawable = getViewRootManager().getBackgroundBlurDrawable();
            BackgroundBlurDrawable backgroundBlurDrawable2 = backgroundBlurDrawable instanceof BackgroundBlurDrawable ? backgroundBlurDrawable : null;
            this.blurDrawable = backgroundBlurDrawable2;
            if (backgroundBlurDrawable2 != null && (function1 = this.onBlurDrawableCreated) != null) {
                function1.invoke(backgroundBlurDrawable2);
            }
            if (LogUtil.getNormalDebug() && LogUtil.isTagValid(LogUtil.TAG_COMMON)) {
                Log.d("SystemUi--Common", "MotionBlurHelper-->".concat("[ensureBlurDrawable] " + DebugToolKt.getName(this.hostView, false) + " blurDrawable=" + this.blurDrawable + " created!"));
            }
        }
    }

    public final BackgroundBlurDrawable getBlurDrawable() {
        return this.blurDrawable;
    }

    public final boolean getBlurEnabled() {
        return this.blurEnabled;
    }

    public final Function1 getOnBlurDrawableCreated$vendor__oplus__packages__apps__systemui__oplus_common__android_common__OplusCommon() {
        return this.onBlurDrawableCreated;
    }

    public final Function1 getOnViewAlphaToUser$vendor__oplus__packages__apps__systemui__oplus_common__android_common__OplusCommon() {
        return this.onViewAlphaToUser;
    }

    public final void onVisibilityAggregated(boolean z) {
        boolean z2 = this.blurEnabled && z;
        BackgroundBlurDrawable backgroundBlurDrawable = this.blurDrawable;
        if (backgroundBlurDrawable != null) {
            backgroundBlurDrawable.setVisible(z2, false);
        }
        if (LogUtil.getNormalDebug() && LogUtil.isTagValid(LogUtil.TAG_COMMON)) {
            Log.d("SystemUi--Common", "MotionBlurHelper-->".concat("[dispatchVisibilityChanged] " + DebugToolKt.getName(this.hostView, false) + ' ' + z2));
        }
    }

    public final void release(String str) {
        if (LogUtil.getNormalDebug() && LogUtil.isTagValid(LogUtil.TAG_COMMON)) {
            Log.d("SystemUi--Common", "MotionBlurHelper-->".concat("[release] " + DebugToolKt.getName(this.hostView, false) + " release because " + str));
        }
        BackgroundBlurDrawable backgroundBlurDrawable = this.blurDrawable;
        if (backgroundBlurDrawable != null) {
            backgroundBlurDrawable.setVisible(false, false);
            if (LogUtil.getNormalDebug() && LogUtil.isTagValid(LogUtil.TAG_COMMON)) {
                Log.d("SystemUi--Common", "MotionBlurHelper-->".concat("[release] " + DebugToolKt.getName(this.hostView, false) + " set visible false"));
            }
            this.blurDrawable = null;
        }
    }

    public final void setBlurEnabled$vendor__oplus__packages__apps__systemui__oplus_common__android_common__OplusCommon(boolean z) {
        if (this.blurEnabled != z) {
            if (LogUtil.getNormalDebug() && LogUtil.isTagValid(LogUtil.TAG_COMMON)) {
                OplusLockIconView$.ExternalSyntheticOutline0.m("[setBlurEnabled] ", "MotionBlurHelper-->", "SystemUi--Common", z);
            }
            this.blurEnabled = z;
            if (z) {
                ensureBlurDrawable();
                BackgroundBlurDrawable backgroundBlurDrawable = this.blurDrawable;
                if (backgroundBlurDrawable != null) {
                    backgroundBlurDrawable.setVisible(this.hostView.getWindowVisibility() == 0 && this.hostView.isShown(), false);
                    if (LogUtil.getNormalDebug() && LogUtil.isTagValid(LogUtil.TAG_COMMON)) {
                        StringBuilder sb = new StringBuilder("MotionBlurHelper-->");
                        StringBuilder sb2 = new StringBuilder("[setBlurEnabled] ");
                        sb2.append(DebugToolKt.getName(this.hostView, false));
                        sb2.append(" set visible ");
                        sb2.append(this.hostView.getWindowVisibility() == 0 && this.hostView.isShown());
                        sb2.append(",hostView.windowVisible=");
                        sb2.append(this.hostView.getWindowVisibility() == 0);
                        sb2.append(",hostView.isShown=");
                        sb2.append(this.hostView.isShown());
                        sb.append(sb2.toString());
                        Log.d("SystemUi--Common", sb.toString());
                    }
                }
            } else {
                release("blurEnabled false");
                if (LogUtil.getNormalDebug() && LogUtil.isTagValid(LogUtil.TAG_COMMON)) {
                    Log.d("SystemUi--Common", "MotionBlurHelper-->".concat("[setBlurEnabled] " + DebugToolKt.getName(this.hostView, false) + " set visible false"));
                }
            }
            View view = this.hostView;
            MotionBlurHelper$onAttachListener$1 motionBlurHelper$onAttachListener$1 = this.onAttachListener;
            if (z) {
                view.addOnAttachStateChangeListener(motionBlurHelper$onAttachListener$1);
            } else {
                view.removeOnAttachStateChangeListener(motionBlurHelper$onAttachListener$1);
            }
        }
    }

    public final void setOnBlurDrawableCreated$vendor__oplus__packages__apps__systemui__oplus_common__android_common__OplusCommon(Function1 function1) {
        this.onBlurDrawableCreated = function1;
    }

    public final void setOnViewAlphaToUser$vendor__oplus__packages__apps__systemui__oplus_common__android_common__OplusCommon(Function1 function1) {
        Function1 function2;
        if (Intrinsics.areEqual(this.onViewAlphaToUser, function1)) {
            return;
        }
        this.onViewAlphaToUser = function1;
        BackgroundBlurDrawable backgroundBlurDrawable = this.blurDrawable;
        if (backgroundBlurDrawable == null || !backgroundBlurDrawable.isVisible() || (function2 = this.onViewAlphaToUser) == null) {
            return;
        }
        float transitionAlpha = this.hostView.getTransitionAlpha() * this.hostView.getAlpha();
        Object parent = this.hostView.getParent();
        while (parent instanceof View) {
            View view = (View) parent;
            transitionAlpha *= view.getTransitionAlpha() * view.getAlpha();
            parent = view.getParent();
        }
        function2.invoke(Float.valueOf(transitionAlpha));
    }
}
