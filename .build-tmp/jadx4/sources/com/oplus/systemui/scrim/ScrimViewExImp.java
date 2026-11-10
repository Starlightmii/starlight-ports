package com.oplus.systemui.scrim;

import android.animation.Animator;
import android.animation.ValueAnimator;
import android.content.Context;
import android.graphics.Color;
import android.graphics.drawable.Drawable;
import android.util.MathUtils;
import android.view.animation.DecelerateInterpolator;
import androidx.constraintlayout.core.widgets.ConstraintWidget$;
import androidx.core.graphics.ColorUtils;
import com.android.keyguard.OplusKeyguardDependencyEx;
import com.android.keyguard.logging.KeyguardLogger$;
import com.android.systemui.DependencyEx;
import com.android.systemui.log.LogBuffer;
import com.android.systemui.log.core.LogLevel;
import com.android.systemui.panelanimation.platformblur.PlatformBlurManager;
import com.android.systemui.scrim.ScrimViewEx;
import com.android.systemui.statusbar.OlockStateUtil;
import com.android.systemui.statusbar.phone.ScrimControllerEx;
import com.android.systemui.statusbar.phone.ScrimState;
import com.oplus.systemui.blur.GaussBlurUtils;
import com.oplusos.systemui.common.blurability.BlurMixConfig;
import com.oplusos.systemui.common.blurability.ViewBlurProxy;
import com.oplusos.systemui.common.blurability.drawable.AutoBlurDrawable;
import com.oplusos.systemui.common.util.LogUtil;
import com.oplusos.systemui.common.util.NotifiAndQsPlatformBlurExKt;
import com.oplusos.systemui.common.util.ScrimUtil;
import java.lang.invoke.VarHandle;
import kotlin.Lazy;
import kotlin.LazyKt__LazyJVMKt;

/* JADX INFO: compiled from: go/retraceme fd74bf9514dd0739b92d67a6d48a51eb10aaf8df760d83496b8dcfcf83e5f698 */
/* JADX INFO: loaded from: /home/starlight/Projects/Starlight Ports/.build-tmp/classes4.dex */
public final class ScrimViewExImp extends ScrimViewEx {
    public static final Companion Companion = new Companion();
    private static final String TAG = "ScrimViewExImp";
    public int animEndColor;
    public float blurAmount;
    private ViewBlurProxy.BlurType blurType;
    private Animator colorAnimator;
    public boolean isDisableUpdateColor;
    public boolean isOplusLogic;
    private ViewBlurProxy.BlurType lastBlurType;
    private final Lazy logName$delegate;
    public float targetAlpha;
    public float viewAlpha;

    /* JADX INFO: compiled from: go/retraceme fd74bf9514dd0739b92d67a6d48a51eb10aaf8df760d83496b8dcfcf83e5f698 */
    public final class Companion {
    }

    public ScrimViewExImp(Context context) {
        super(context);
        this.isOplusLogic = true;
        ScrimViewExImp$$ExternalSyntheticLambda0 scrimViewExImp$$ExternalSyntheticLambda0 = new ScrimViewExImp$$ExternalSyntheticLambda0();
        scrimViewExImp$$ExternalSyntheticLambda0.f$0 = this;
        VarHandle.storeStoreFence();
        this.logName$delegate = LazyKt__LazyJVMKt.lazy(scrimViewExImp$$ExternalSyntheticLambda0);
    }

    private final String getLogName() {
        return (String) this.logName$delegate.getValue();
    }

    public ViewBlurProxy.BlurType getBlurType() {
        return this.blurType;
    }

    public ViewBlurProxy.BlurType getLastBlurType() {
        return this.lastBlurType;
    }

    public float getTargetAlpha() {
        return this.targetAlpha;
    }

    public final boolean isOplusLogic() {
        return this.isOplusLogic;
    }

    public void setBlurAmount(float f, String str) {
        LogBuffer logBuffer;
        float fConstrain = MathUtils.constrain(f, 0.0f, 1.0f);
        this.blurAmount = fConstrain;
        if (isViewBlurProxyInitted()) {
            ViewBlurProxy viewBlurProxy = getViewBlurProxy();
            if (viewBlurProxy != null) {
                viewBlurProxy.setBlurAmount(fConstrain);
            }
            ViewBlurProxy viewBlurProxy2 = getViewBlurProxy();
            if (viewBlurProxy2 != null) {
                viewBlurProxy2.setBlurEnabled(!(fConstrain == 0.0f));
            }
        } else {
            Drawable drawable = getScrimView().getDrawable();
            if (drawable != null) {
                drawable.invalidateSelf();
            }
        }
        if (!LogUtil.panicEnabled || (logBuffer = getLogBuffer()) == null) {
            return;
        }
        logBuffer.log(TAG, LogLevel.DEBUG, "[setBlurAmount] blurFraction=" + fConstrain + ", from=" + str + " for " + getLogName(), (Throwable) null);
    }

    public void setBlurAndScaleAmount(float f, String str) {
        float fConstrain = MathUtils.constrain(f, 0.0f, 1.0f);
        if (isViewBlurProxyInitted()) {
            boolean zBooleanValue = ((Boolean) ((PlatformBlurManager) DependencyEx.sDependency.getDependency(PlatformBlurManager.class)).getUseMotionBlur().getValue()).booleanValue();
            ViewBlurProxy viewBlurProxy = getViewBlurProxy();
            if (viewBlurProxy != null && ((ScrimViewEx) this).isBehind) {
                NotifiAndQsPlatformBlurExKt.applyPanelMirrorScale(zBooleanValue, fConstrain, viewBlurProxy);
            }
        }
        setBlurAmount(f, str);
    }

    public void setBlurType(ViewBlurProxy.BlurType blurType) {
        this.blurType = blurType;
    }

    public final void setColor(int i, boolean z) {
        Animator animator;
        LogBuffer logBuffer;
        if (i == this.animEndColor) {
            return;
        }
        if (LogUtil.panicEnabled && (logBuffer = getLogBuffer()) != null) {
            LogLevel logLevel = LogLevel.DEBUG;
            String hexString = Integer.toHexString(i);
            String logName = getLogName();
            StringBuilder sbM = KeyguardLogger$.ExternalSyntheticOutline0.m("[setColor] maskColor= ", hexString, ", animated=", z, " for ");
            sbM.append(logName);
            logBuffer.log(TAG, logLevel, sbM.toString(), (Throwable) null);
        }
        Animator animator2 = this.colorAnimator;
        if (animator2 != null && animator2.isRunning() && (animator = this.colorAnimator) != null) {
            animator.cancel();
        }
        this.animEndColor = i;
        ViewBlurProxy viewBlurProxy = getViewBlurProxy();
        if (viewBlurProxy != null) {
            if (!z) {
                viewBlurProxy.getBlurConfig().setBlurColor(i);
                if ((viewBlurProxy.getBlurConfig().getPlatformMixConfig() instanceof BlurMixConfig.None) || (viewBlurProxy.getBlurConfig().getPlatformMixConfig() instanceof BlurMixConfig.OverlayColor)) {
                    viewBlurProxy.applyBlurConfig();
                    return;
                }
                return;
            }
            int blurColor = viewBlurProxy.getBlurConfig().getBlurColor();
            ValueAnimator valueAnimatorOfFloat = ValueAnimator.ofFloat(0.0f, 1.0f);
            valueAnimatorOfFloat.setDuration(360L);
            ScrimViewExImp$setColor$1$1 scrimViewExImp$setColor$1$1 = new ScrimViewExImp$setColor$1$1();
            scrimViewExImp$setColor$1$1.$it = viewBlurProxy;
            scrimViewExImp$setColor$1$1.$colorFrom = blurColor;
            scrimViewExImp$setColor$1$1.$maskColor = i;
            VarHandle.storeStoreFence();
            valueAnimatorOfFloat.addUpdateListener(scrimViewExImp$setColor$1$1);
            ScrimViewExImp$setColor$lambda$4$$inlined$doOnEnd$1 scrimViewExImp$setColor$lambda$4$$inlined$doOnEnd$1 = new ScrimViewExImp$setColor$lambda$4$$inlined$doOnEnd$1();
            scrimViewExImp$setColor$lambda$4$$inlined$doOnEnd$1.this$0 = this;
            VarHandle.storeStoreFence();
            valueAnimatorOfFloat.addListener(scrimViewExImp$setColor$lambda$4$$inlined$doOnEnd$1);
            valueAnimatorOfFloat.setInterpolator(new DecelerateInterpolator());
            valueAnimatorOfFloat.start();
            this.colorAnimator = valueAnimatorOfFloat;
        }
    }

    public void setDisableUpdateColor(boolean z) {
        if (this.isDisableUpdateColor != z) {
            if (!z) {
                updateColorWithTint(false);
            }
            this.isDisableUpdateColor = z;
        }
    }

    public void setDrawable(Drawable drawable) {
        if (drawable instanceof AutoBlurDrawable) {
            setViewBlurProxy(((AutoBlurDrawable) drawable).getViewBlurProxy());
        } else {
            setViewBlurProxy((ViewBlurProxy) null);
        }
        updateColorWithTint(false);
        setViewAlpha(this.viewAlpha);
        setScaleAmount(0.0f, "setDrawable");
        setBlurAmount(this.blurAmount, "setDrawable");
    }

    public void setLastBlurType(ViewBlurProxy.BlurType blurType) {
        this.lastBlurType = blurType;
    }

    public void setOplusLogic(boolean z) {
        this.isOplusLogic = z;
    }

    public void setScaleAmount(float f, String str) {
        LogBuffer logBuffer;
        float fConstrain = MathUtils.constrain(f, 0.0f, 1.0f);
        if (isViewBlurProxyInitted()) {
            boolean zBooleanValue = ((Boolean) ((PlatformBlurManager) DependencyEx.sDependency.getDependency(PlatformBlurManager.class)).getUseMotionBlur().getValue()).booleanValue();
            ViewBlurProxy viewBlurProxy = getViewBlurProxy();
            if (viewBlurProxy != null && ((ScrimViewEx) this).isBehind) {
                NotifiAndQsPlatformBlurExKt.applyPanelMirrorScale(zBooleanValue, fConstrain, viewBlurProxy);
                viewBlurProxy.applyBlurConfig();
            }
        }
        if (!LogUtil.panicEnabled || (logBuffer = getLogBuffer()) == null) {
            return;
        }
        logBuffer.log(TAG, LogLevel.DEBUG, "[setScaleAmount] constrainScaleRatio=" + fConstrain + ", from=" + str + " for " + getLogName(), (Throwable) null);
    }

    public void setTargetAlpha(float f) {
        this.targetAlpha = f;
    }

    public void setViewAlpha(float f) {
        LogBuffer logBuffer;
        if (OlockStateUtil.INSTANCE.getSafeModeEnable()) {
            return;
        }
        this.viewAlpha = f;
        if (!isViewBlurProxyInitted()) {
            Drawable drawable = getScrimView().getDrawable();
            if (drawable != null) {
                drawable.invalidateSelf();
                return;
            }
            return;
        }
        if (LogUtil.panicEnabled && (logBuffer = getLogBuffer()) != null) {
            logBuffer.log(TAG, LogLevel.DEBUG, "[setViewAlpha] alpha=" + f + " for " + getLogName(), (Throwable) null);
        }
        ViewBlurProxy viewBlurProxy = getViewBlurProxy();
        if (viewBlurProxy != null) {
            setColor(ColorUtils.setAlphaComponent(viewBlurProxy.getBlurConfig().getBlurColor(), (int) (f * 255.0f)), false);
        }
    }

    public final void updateColorWithTint(boolean z) {
        LogBuffer logBuffer;
        if (!isViewBlurProxyInitted()) {
            Drawable drawable = getScrimView().getDrawable();
            if (drawable != null) {
                drawable.invalidateSelf();
                return;
            }
            return;
        }
        if (!this.isDisableUpdateColor || z) {
            boolean zIsLiveWallpaper = ((OplusKeyguardDependencyEx) DependencyEx.sDependency.getDependency(OplusKeyguardDependencyEx.class)).getOplusLockScreenWallpaperController().isLiveWallpaper();
            float fAlpha = Color.alpha(getScrimView().mTintColor) / 255.0f;
            boolean z2 = (ScrimUtil.isLowGaussianLevel(getScrimView().getContext()) && !ScrimUtil.isLightOsGaussianLevel()) || GaussBlurUtils.isGaussBlurDisabled();
            ScrimState oldState = ((ScrimControllerEx) DependencyEx.sDependency.getDependency(ScrimControllerEx.class)).getOldState();
            boolean z3 = ((ScrimControllerEx) DependencyEx.sDependency.getDependency(ScrimControllerEx.class)).isQsVisible;
            ScrimState scrimState = ScrimState.BOUNCER_SCRIMMED;
            if (z2 && z3 && getScrimState() == ScrimState.UNLOCKED && oldState == scrimState) {
                getScrimView().mTintColor = getScrimView().getContext().getColor(2131102930);
                fAlpha = 1.0f;
            }
            int iBlendARGB = ColorUtils.blendARGB((GaussBlurUtils.isGaussBlurDisabled() || zIsLiveWallpaper || getScrimState() == scrimState) ? getScrimView().mColors.getMainColor() : 0, getScrimView().mTintColor, fAlpha);
            if (LogUtil.panicEnabled && (logBuffer = getLogBuffer()) != null) {
                LogLevel logLevel = LogLevel.DEBUG;
                String hexString = Integer.toHexString(iBlendARGB);
                String hexString2 = Integer.toHexString(getScrimView().mColors.getMainColor());
                String hexString3 = Integer.toHexString(getScrimView().mTintColor);
                float f = this.viewAlpha;
                boolean zIsGaussBlurDisabled = GaussBlurUtils.isGaussBlurDisabled();
                String logName = getLogName();
                StringBuilder sb = new StringBuilder("[updateColorWithTint], tintAmount=");
                sb.append(fAlpha);
                sb.append(", mainTinted=");
                sb.append(hexString);
                sb.append(", getMainColor()=");
                ConstraintWidget$.ExternalSyntheticOutline0.m(sb, hexString2, ", mTintColor=", hexString3, ", mViewAlpha=");
                sb.append(f);
                sb.append(", isGaussBlurDisabled=");
                sb.append(zIsGaussBlurDisabled);
                sb.append(", isLiveWallpaper=");
                sb.append(zIsLiveWallpaper);
                sb.append(" for ");
                sb.append(logName);
                logBuffer.log(TAG, logLevel, sb.toString(), (Throwable) null);
            }
            ViewBlurProxy viewBlurProxy = getViewBlurProxy();
            if (viewBlurProxy != null) {
                setColor(ColorUtils.setAlphaComponent(iBlendARGB, Color.alpha(viewBlurProxy.getBlurConfig().getBlurColor())), z);
            }
        }
    }
}
