package com.oplus.systemui.statusbar.phone;

import android.content.Context;
import android.graphics.drawable.Drawable;
import android.view.View;
import android.view.ViewGroup;
import androidx.compose.material3.internal.TextFieldImplKt$;
import androidx.dynamicanimation.animation.FloatValueHolder;
import com.android.compose.nestedscroll.PriorityNestedScrollConnection$;
import com.android.keyguard.OplusLockIconView$;
import com.android.systemui.media.controls.domain.pipeline.MediaTimeoutListener$PlaybackStateListener$;
import com.android.systemui.scrim.ScrimView;
import com.android.systemui.scrim.ScrimView$;
import com.android.systemui.statusbar.phone.ScrimController;
import com.android.systemui.statusbar.phone.ScrimControllerEx;
import com.coui.appcompat.animation.dynamicanimation.COUIDynamicAnimation;
import com.coui.appcompat.animation.dynamicanimation.COUISpringAnimation;
import com.coui.appcompat.animation.dynamicanimation.COUISpringForce;
import com.oplusos.systemui.common.blurability.BlurConfig;
import com.oplusos.systemui.common.blurability.ViewBlurProxy;
import com.oplusos.systemui.common.blurability.drawable.AutoBlurDrawable;
import com.oplusos.systemui.common.blurability.drawable.MaskDrawable;
import com.oplusos.systemui.common.olivelink.OliveLinkConnector;
import com.oplusos.systemui.common.olivelink.Spring;
import com.oplusos.systemui.common.util.LogUtil;
import com.oplusos.systemui.common.util.ScrimUtil;
import java.lang.invoke.VarHandle;

/* JADX INFO: compiled from: go/retraceme fd74bf9514dd0739b92d67a6d48a51eb10aaf8df760d83496b8dcfcf83e5f698 */
/* JADX INFO: loaded from: /home/starlight/Projects/Starlight Ports/.build-tmp/classes4.dex */
public final class ScrimGlobalThemeTransitionHelper {
    public static final Companion Companion = new Companion();
    private static final String TAG = "ScrimGlobalThemeTransitionHelper";
    private View cloneScrimView;
    private final Context context;
    public float curTransitionProgress;
    public float curVelocity;
    public boolean isEditMode;
    public ScrimController scrimController;
    public ScrimControllerExImp scrimControllerExImp;
    private final Spring springConfig = OliveLinkConnector.safeSpring$default(OliveLinkConnector.INSTANCE, OliveLinkConnector.SEP_QS_SERVER_NAME, "三方主题蒙层过渡动画", 0.0f, 0.3f, 0.0f, 0.0f, 0.0f, null, null, null, 1008);
    private COUISpringAnimation springTransition;

    /* JADX INFO: compiled from: go/retraceme fd74bf9514dd0739b92d67a6d48a51eb10aaf8df760d83496b8dcfcf83e5f698 */
    public final class Companion {
    }

    public ScrimGlobalThemeTransitionHelper(Context context) {
        this.context = context;
    }

    public final void bindScrimController(ScrimController scrimController, ScrimControllerExImp scrimControllerExImp) {
        this.scrimController = scrimController;
        this.scrimControllerExImp = scrimControllerExImp;
    }

    public final void updateEditMode(boolean z) {
        Boolean boolValueOf;
        boolean z2 = this.isEditMode;
        if (z2 == z) {
            float f = this.curTransitionProgress;
            StringBuilder sbM = TextFieldImplKt$.ExternalSyntheticOutline0.m("updateEditMode() ", "=>", ", curTransitionProgress:", z2, z);
            sbM.append(f);
            LogUtil.i$default(LogUtil.TAG_NOTIFICATION, TAG, sbM.toString(), null, 8);
            return;
        }
        LogUtil.i$default(LogUtil.TAG_NOTIFICATION, TAG, PriorityNestedScrollConnection$.ExternalSyntheticOutline0.m("updateEditMode() ", "=>", z2, z), null, 8);
        this.isEditMode = z;
        ScrimController scrimController = this.scrimController;
        if (scrimController == null) {
            return;
        }
        ViewGroup viewGroup = (ViewGroup) scrimController.getScrimBehind().getParent();
        COUISpringAnimation cOUISpringAnimation = this.springTransition;
        Boolean boolValueOf2 = cOUISpringAnimation != null ? Boolean.valueOf(((COUIDynamicAnimation) cOUISpringAnimation).mRunning) : null;
        View view = this.cloneScrimView;
        if (view != null) {
            boolValueOf = Boolean.valueOf(viewGroup.indexOfChild(view) != -1);
        } else {
            boolValueOf = null;
        }
        LogUtil.i$default(LogUtil.TAG_NOTIFICATION, TAG, "resetTransition() springTransition is running:" + boolValueOf2 + ", has cloneScrimView:" + boolValueOf, null, 8);
        COUISpringAnimation cOUISpringAnimation2 = this.springTransition;
        if (cOUISpringAnimation2 != null) {
            cOUISpringAnimation2.cancel();
        }
        View view2 = this.cloneScrimView;
        if (view2 != null) {
            viewGroup.removeView(view2);
            this.cloneScrimView = null;
        }
        ScrimControllerEx.Companion.getClass();
        boolean z3 = ScrimControllerEx.isThirdTheme;
        if (!z3) {
            LogUtil.i$default(LogUtil.TAG_NOTIFICATION, TAG, OplusLockIconView$.ExternalSyntheticOutline0.m("updateEditMode() isThirdTheme:", ", ignore", z3), null, 8);
            return;
        }
        boolean zIsBAnimLevel = ScrimUtil.isBAnimLevel();
        ScrimController scrimController2 = this.scrimController;
        if (zIsBAnimLevel) {
            if (scrimController2 == null) {
                scrimController2 = null;
            }
            scrimController2.getScrimBehind().getExt().setDisableUpdateColor(this.isEditMode);
            boolean z4 = this.isEditMode;
            Context context = this.context;
            int color = z4 ? context.getColor(2131102930) : context.getColor(2131102929);
            if (LogUtil.getNormalDebug() && LogUtil.isTagValid(LogUtil.TAG_NOTIFICATION)) {
                MediaTimeoutListener$PlaybackStateListener$.ExternalSyntheticOutline0.m("[updateEditMode] Tint Color=", Integer.toHexString(color), "ScrimGlobalThemeTransitionHelper-->", "SystemUi--Notification");
            }
            ScrimController scrimController3 = this.scrimController;
            ScrimView scrimBehind = (scrimController3 != null ? scrimController3 : null).getScrimBehind();
            scrimBehind.getClass();
            ScrimView$.ExternalSyntheticLambda4 externalSyntheticLambda4 = new ScrimView$.ExternalSyntheticLambda4();
            externalSyntheticLambda4.f$0 = scrimBehind;
            externalSyntheticLambda4.f$1 = color;
            externalSyntheticLambda4.f$2 = true;
            VarHandle.storeStoreFence();
            scrimBehind.executeOnExecutor(externalSyntheticLambda4);
            return;
        }
        if (scrimController2 == null) {
            scrimController2 = null;
        }
        ViewGroup viewGroup2 = (ViewGroup) scrimController2.getScrimBehind().getParent();
        View view3 = new View(this.context);
        ScrimController scrimController4 = this.scrimController;
        if (scrimController4 == null) {
            scrimController4 = null;
        }
        Drawable drawable = scrimController4.getScrimBehind().getDrawable();
        AutoBlurDrawable autoBlurDrawable = drawable instanceof AutoBlurDrawable ? (AutoBlurDrawable) drawable : null;
        if (autoBlurDrawable != null) {
            ViewBlurProxy viewBlurProxy = autoBlurDrawable.getViewBlurProxy();
            ScrimController scrimController5 = this.scrimController;
            if (scrimController5 == null) {
                scrimController5 = null;
            }
            viewGroup2.addView(view3, viewGroup2.indexOfChild(scrimController5.getScrimBehind()));
            BlurConfig blurConfig = viewBlurProxy.getBlurConfig();
            ViewBlurProxy viewBlurProxy2 = new ViewBlurProxy(view3, blurConfig.copy(blurConfig.blurRadius, blurConfig.blurColor, blurConfig.leftTopCornerRadius, blurConfig.rightTopCornerRadius, blurConfig.rightBottomCornerRadius, blurConfig.leftBottomCornerRadius, blurConfig.radiusWeight, blurConfig.pathProvider, blurConfig.gradientStrokeLineParam, blurConfig.gradientStrokeCornerParam, blurConfig.innerShadowParams, blurConfig.opticsParams, blurConfig.enableStaticBlurCorner, blurConfig.enableMotionAmountWithAlphaToUser, blurConfig.enableMotionSmoothCorner, blurConfig.isFullScreen, blurConfig.platformMixConfig, blurConfig.platformBlurDrawableFullOpacity, blurConfig.scaleBlurRadiusWithAmount, blurConfig.motionBlurMixConfig, blurConfig.motionBlurRadius, blurConfig.windowBlurConfig, blurConfig.isSupportMetaBall, blurConfig.captureRegion, blurConfig.onBlurDrawableCreated), null, 28);
            viewBlurProxy2.setBlurType(viewBlurProxy.getBlurType());
            AutoBlurDrawable autoBlurDrawable2 = new AutoBlurDrawable(viewBlurProxy2, new MaskDrawable(viewBlurProxy2.getBlurConfig()));
            view3.setBackground(autoBlurDrawable2);
            this.cloneScrimView = view3;
            autoBlurDrawable2.getViewBlurProxy().getBlurConfig().setCornerRadius(viewBlurProxy.getBlurConfig().getCornerRadius());
            autoBlurDrawable2.getViewBlurProxy().applyBlurConfig();
        }
        if (this.springTransition == null) {
            COUISpringAnimation cOUISpringAnimation3 = new COUISpringAnimation(new FloatValueHolder());
            COUISpringForce cOUISpringForce = new COUISpringForce();
            cOUISpringForce.setBounce(this.springConfig.bounce());
            cOUISpringForce.setResponse(this.springConfig.response());
            cOUISpringAnimation3.setMinimumVisibleChange(0.00390625f);
            cOUISpringAnimation3.setSpring(cOUISpringForce);
            View view4 = this.cloneScrimView;
            AutoBlurDrawable autoBlurDrawable3 = (AutoBlurDrawable) (view4 != null ? view4.getBackground() : null);
            ScrimController scrimController6 = this.scrimController;
            ViewGroup viewGroup3 = (ViewGroup) (scrimController6 != null ? scrimController6 : null).getScrimBehind().getParent();
            buildTransition.1.2 r2 = new buildTransition.1.2();
            r2.this$0 = this;
            r2.$cloneDrawable = autoBlurDrawable3;
            VarHandle.storeStoreFence();
            cOUISpringAnimation3.addUpdateListener(r2);
            buildTransition.1.3 r0 = new buildTransition.1.3();
            r0.$scrimParent = viewGroup3;
            r0.this$0 = this;
            VarHandle.storeStoreFence();
            cOUISpringAnimation3.addEndListener(r0);
            this.springTransition = cOUISpringAnimation3;
        }
        float f2 = this.curTransitionProgress;
        boolean z5 = f2 == 0.0f;
        COUISpringAnimation cOUISpringAnimation4 = this.springTransition;
        if (z5) {
            if (cOUISpringAnimation4 != null) {
                cOUISpringAnimation4.setStartValue(0.0f);
            }
            COUISpringAnimation cOUISpringAnimation5 = this.springTransition;
            if (cOUISpringAnimation5 != null) {
                cOUISpringAnimation5.setStartVelocity(0.0f);
            }
        } else {
            if (cOUISpringAnimation4 != null) {
                cOUISpringAnimation4.setStartValue(1.0f - f2);
            }
            COUISpringAnimation cOUISpringAnimation6 = this.springTransition;
            if (cOUISpringAnimation6 != null) {
                cOUISpringAnimation6.setStartVelocity(this.curVelocity);
            }
        }
        View view5 = this.cloneScrimView;
        if (view5 != null) {
            updateEditMode.2 r1 = new updateEditMode.2();
            r1.this$0 = this;
            VarHandle.storeStoreFence();
            view5.post(r1);
        }
    }
}
