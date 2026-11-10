package com.oplus.systemui.notification.lockscreen.config;

import android.content.Context;
import android.util.Log;
import android.view.MotionEvent;
import android.view.ViewConfiguration;
import androidx.constraintlayout.widget.ConstraintSet$;
import com.android.internal.policy.SystemBarUtils;
import com.android.mechanics.effects.VerticalTactileSurfaceRevealEffect$;
import com.android.systemui.statusbar.notification.OplusPhysicsPropertyAnimator;
import com.android.systemui.util.DimensionKt;
import com.oplus.keyguard.VFXController$stopAnim$1$;
import com.oplus.keyguard.utils.KeyguardUtils;
import com.oplus.systemui.notification.lockscreen.effect.ExtStateAnimParams;
import com.oplusos.systemui.common.util.LogUtil;
import com.oplusos.systemui.common.util.ScrimUtil;
import kotlin.Lazy;
import kotlin.LazyKt__LazyJVMKt;
import kotlin.jvm.functions.Function1;

/* JADX INFO: compiled from: go/retraceme fd74bf9514dd0739b92d67a6d48a51eb10aaf8df760d83496b8dcfcf83e5f698 */
/* JADX INFO: loaded from: /home/starlight/Projects/Starlight Ports/.build-tmp/classes4.dex */
public final class CapsuleConfig {
    public static final CapsuleConfig INSTANCE;
    private static final String TAG = "CapsuleConfig";
    public static float capsuleDragLimit;
    public static int cardMarginBottom;
    public static int cardRadius;
    public static float defaultRadius;
    public static float displacementNotifyThresholdPx;
    public static int earMargin;
    public static int earWidth;
    public static boolean isLightOs;
    public static boolean isMiddleOrHighAnimLevel;
    private static final Lazy originContext$delegate;
    public static float slopMultiplier;
    public static float stackUpDragLimit;
    public static int statusBarHeight;
    public static int touchSlop;

    static {
        CapsuleConfig capsuleConfig = new CapsuleConfig();
        INSTANCE = capsuleConfig;
        originContext$delegate = LazyKt__LazyJVMKt.lazy(new CapsuleConfig$$ExternalSyntheticLambda0());
        statusBarHeight = SystemBarUtils.getStatusBarHeight(capsuleConfig.getOriginContext());
        cardMarginBottom = capsuleConfig.getOriginContext().getResources().getDimensionPixelSize(2131165903);
        cardRadius = capsuleConfig.getOriginContext().getResources().getDimensionPixelSize(2131170626);
        earMargin = capsuleConfig.getOriginContext().getResources().getDimensionPixelSize(2131165906);
        earWidth = capsuleConfig.getOriginContext().getResources().getDimensionPixelSize(2131165907);
        capsuleDragLimit = 1.0f;
        stackUpDragLimit = 1.0f;
        displacementNotifyThresholdPx = 1.0f;
    }

    private final Context getOriginContext() {
        return (Context) originContext$delegate.getValue();
    }

    public final boolean checkIsLightOS(Context context) {
        return ScrimUtil.isBAnimLevel();
    }

    public final float getCapsuleDragLimit() {
        return capsuleDragLimit;
    }

    public final int getCardMarginBottom() {
        return cardMarginBottom;
    }

    public final int getCardRadius() {
        return cardRadius;
    }

    public final float getDefaultRadius() {
        return defaultRadius;
    }

    public final float getDisplacementNotifyThresholdPx() {
        return displacementNotifyThresholdPx;
    }

    public final int getEarMargin() {
        return earMargin;
    }

    public final int getEarWidth() {
        return earWidth;
    }

    public final int getItemHeight() {
        return CapsuleFoldableDimen.ITEM_HEIGHT.getValue();
    }

    public final int getItemWidth() {
        return CapsuleFoldableDimen.ITEM_WIDTH_SINGLE.getValue();
    }

    public final int getItemWidthMultiCard() {
        return CapsuleFoldableDimen.ITEM_WIDTH_MULTI.getValue();
    }

    public final float getStackUpDragLimit() {
        return stackUpDragLimit;
    }

    public final int getStatusBarHeight() {
        return statusBarHeight;
    }

    public final float getTouchSlop(MotionEvent motionEvent) {
        return motionEvent.getClassification() == 1 ? touchSlop * slopMultiplier : touchSlop;
    }

    public final void initConfig(Context context) {
        loadConfig(context, "initConfig");
    }

    public final void loadConfig(Context context, String str) {
        Context contextUpdateDisplayConfiguration$default = KeyguardUtils.Companion.updateDisplayConfiguration$default(KeyguardUtils.Companion, getOriginContext(), true, 4);
        contextUpdateDisplayConfiguration$default.getClass();
        statusBarHeight = SystemBarUtils.getStatusBarHeight(context);
        CapsuleFoldableDimen.Companion.loadFoldableDimens(context, str);
        defaultRadius = getItemHeight() / 2.0f;
        ViewConfiguration viewConfiguration = ViewConfiguration.get(getOriginContext());
        touchSlop = viewConfiguration.getScaledTouchSlop();
        slopMultiplier = viewConfiguration.getScaledAmbiguousGestureMultiplier();
        cardMarginBottom = contextUpdateDisplayConfiguration$default.getResources().getDimensionPixelSize(2131165903);
        cardRadius = getOriginContext().getResources().getDimensionPixelSize(2131170626);
        earMargin = contextUpdateDisplayConfiguration$default.getResources().getDimensionPixelSize(2131165906);
        earWidth = contextUpdateDisplayConfiguration$default.getResources().getDimensionPixelSize(2131165907);
        isLightOs = checkIsLightOS(context);
        boolean zIsMiddleOrHighAnimLevel = ScrimUtil.isMiddleOrHighAnimLevel(context);
        isMiddleOrHighAnimLevel = zIsMiddleOrHighAnimLevel;
        OplusPhysicsPropertyAnimator.isMiddleOrHighAnimLevel = zIsMiddleOrHighAnimLevel;
        capsuleDragLimit = DimensionKt.dpToPx(ExtStateAnimParams.DraggerParams.INSTANCE.getCapsule().getMAX_ACTION().get(), getOriginContext());
        stackUpDragLimit = DimensionKt.dpToPx(50, getOriginContext());
        displacementNotifyThresholdPx = DimensionKt.dpToPx(6, getOriginContext());
        if (LogUtil.getNormalDebug() && LogUtil.isTagValid(LogUtil.TAG_NOTIFICATION)) {
            int i = touchSlop;
            float f = slopMultiplier;
            float f2 = capsuleDragLimit;
            float f3 = stackUpDragLimit;
            float f4 = displacementNotifyThresholdPx;
            StringBuilder sbM = ConstraintSet$.ExternalSyntheticOutline0.m(i, "loadTouchConfig[", str, "]. touchSlop:", ", slopMultiplier:");
            VerticalTactileSurfaceRevealEffect$.ExternalSyntheticOutline0.m(sbM, f, ", capsuleDragLimit:", f2, ", stackUpDragLimit:");
            Log.d("SystemUi--Notification", VFXController$stopAnim$1$.ExternalSyntheticOutline0.m(sbM, f3, ", displacementNotifyThresholdPx:", f4, "CapsuleConfig-->"));
        }
    }

    public final void onConfigurationChanged(Context context, Function1 function1) {
        loadConfig(context, "onConfigurationChanged");
        function1.invoke(Integer.valueOf(getItemWidth()));
    }

    public final void setCardMarginBottom(int i) {
        cardMarginBottom = i;
    }

    public final void setCardRadius(int i) {
        cardRadius = i;
    }

    public final void setEarMargin(int i) {
        earMargin = i;
    }

    public final void setEarWidth(int i) {
        earWidth = i;
    }
}
