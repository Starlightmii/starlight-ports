package com.oplus.systemui.notification.row.stacking;

import android.content.Context;
import android.graphics.Canvas;
import android.graphics.Rect;
import android.graphics.drawable.Drawable;
import android.util.Log;
import androidx.core.animation.DecelerateInterpolator;
import androidx.core.animation.ValueAnimator;
import com.android.systemui.DependencyEx;
import com.android.systemui.statusbar.OplusLockscreenShadeTransitionControllerEx;
import com.android.systemui.statusbar.SysuiStatusBarStateController;
import com.android.systemui.statusbar.notification.row.ExpandableNotificationRow;
import com.android.systemui.statusbar.notification.row.NotificationBackgroundView;
import com.oplus.systemui.notification.aod.OplusPanoramicAodRepository;
import com.oplus.util.OplusDarkModeUtil;
import com.oplusos.systemui.common.blurability.ViewBlurProxy;
import com.oplusos.systemui.common.blurability.platformblur.MultiPlatformBlurHelper;
import com.oplusos.systemui.common.blurability.platformblur.PlatformBlurDrawable;
import com.oplusos.systemui.common.feature.FeatureOption;
import com.oplusos.systemui.common.util.LogUtil;
import com.oplusos.systemui.common.util.ScrimUtil;
import java.lang.invoke.VarHandle;
import kotlin.jvm.functions.Function0;

/* JADX INFO: compiled from: go/retraceme fd74bf9514dd0739b92d67a6d48a51eb10aaf8df760d83496b8dcfcf83e5f698 */
/* JADX INFO: loaded from: /home/starlight/Projects/Starlight Ports/.build-tmp/classes4.dex */
public final class KeyguardStackingBackgroundControl {
    public static final Companion Companion = new Companion();
    private static final String TAG = "KeyguardStackingControl";
    private ValueAnimator animator;
    private final Function0 aodDirtyCallback;
    private NotificationBackgroundView backgroundView;
    private final Context context;
    public int currentAlpha = 255;
    public int lastType;
    private final OplusPanoramicAodRepository oplusPanoramicAodRepository;
    public float targetProgress;
    public int targetType;
    private ViewBlurProxy viewBlurProxy;

    /* JADX INFO: compiled from: go/retraceme fd74bf9514dd0739b92d67a6d48a51eb10aaf8df760d83496b8dcfcf83e5f698 */
    public final class Companion {
    }

    public KeyguardStackingBackgroundControl(Context context, OplusPanoramicAodRepository oplusPanoramicAodRepository, Function0 function0) {
        this.context = context;
        this.oplusPanoramicAodRepository = oplusPanoramicAodRepository;
        this.aodDirtyCallback = function0;
    }

    private final Drawable getAodDrawable() {
        if (this.targetType == 0 && this.lastType == 0) {
            return null;
        }
        float f = this.targetProgress;
        ViewBlurProxy viewBlurProxy = this.viewBlurProxy;
        if (f == 1.0f) {
            if (viewBlurProxy != null) {
                return viewBlurProxy.getStackBlurDrawable(2, null);
            }
            return null;
        }
        if (viewBlurProxy != null) {
            return viewBlurProxy.getStackBlurDrawable(1, null);
        }
        return null;
    }

    public final void draw(Canvas canvas, Rect rect) {
        if (useStackBlurDrawable(false)) {
            if (this.oplusPanoramicAodRepository.getPanoramicAodProgress() <= 0.0f) {
                ViewBlurProxy viewBlurProxy = this.viewBlurProxy;
                Drawable stackBlurDrawable = viewBlurProxy != null ? viewBlurProxy.getStackBlurDrawable(this.targetType, null) : null;
                ViewBlurProxy viewBlurProxy2 = this.viewBlurProxy;
                ExpandableNotificationRow stackBlurDrawable2 = viewBlurProxy2 != null ? viewBlurProxy2.getStackBlurDrawable(this.lastType, null) : null;
                if (stackBlurDrawable != null) {
                    stackBlurDrawable.setAlpha(this.currentAlpha);
                    stackBlurDrawable.setBounds(rect);
                    stackBlurDrawable.draw(canvas);
                }
                int i = this.currentAlpha;
                if (i == 255 || stackBlurDrawable2 == null) {
                    return;
                }
                stackBlurDrawable2.setAlpha(255 - i);
                stackBlurDrawable2.setBounds(rect);
                stackBlurDrawable2.draw(canvas);
                return;
            }
            if (this.targetType == 2) {
                NotificationBackgroundView notificationBackgroundView = this.backgroundView;
                if ((notificationBackgroundView != null ? notificationBackgroundView.getParent() : null) instanceof ExpandableNotificationRow) {
                    NotificationBackgroundView notificationBackgroundView2 = this.backgroundView;
                    if (((ExpandableNotificationRow) (notificationBackgroundView2 != null ? notificationBackgroundView2.getParent() : null)).findViewById(2131364388) != null && this.oplusPanoramicAodRepository.getPanoramicAodProgress() == 1.0f) {
                        return;
                    }
                }
            }
            Drawable aodDrawable = getAodDrawable();
            if (aodDrawable != null) {
                if ((aodDrawable instanceof PlatformBlurDrawable) && !((PlatformBlurDrawable) aodDrawable).isAodDirty && !OplusDarkModeUtil.isNightMode(this.context)) {
                    Function0 function0 = this.aodDirtyCallback;
                    if (function0 != null) {
                        function0.invoke();
                        return;
                    }
                    return;
                }
                aodDrawable.setAlpha(255);
                if (this.targetType == 2) {
                    NotificationBackgroundView notificationBackgroundView3 = this.backgroundView;
                    if ((notificationBackgroundView3 != null ? notificationBackgroundView3.getParent() : null) instanceof ExpandableNotificationRow) {
                        NotificationBackgroundView notificationBackgroundView4 = this.backgroundView;
                        if ((notificationBackgroundView4 != null ? notificationBackgroundView4.getParent() : null).findViewById(2131364388) == null) {
                            rect.inset(-1, -1);
                        }
                    }
                }
                aodDrawable.setBounds(rect);
                aodDrawable.draw(canvas);
            }
        }
    }

    public final Drawable[] getAllStackBlurDrawable(boolean z) {
        if (!useStackBlurDrawable(z)) {
            return null;
        }
        if (this.oplusPanoramicAodRepository.getPanoramicAodProgress() > 0.0f && !z) {
            return new Drawable[]{getAodDrawable()};
        }
        int i = this.currentAlpha;
        if (i == 0 && !z) {
            Drawable[] drawableArr = new Drawable[1];
            ViewBlurProxy viewBlurProxy = this.viewBlurProxy;
            drawableArr[0] = viewBlurProxy != null ? viewBlurProxy.getStackBlurDrawable(this.lastType, null) : null;
            return drawableArr;
        }
        if (i == 255 && !z) {
            Drawable[] drawableArr2 = new Drawable[1];
            ViewBlurProxy viewBlurProxy2 = this.viewBlurProxy;
            drawableArr2[0] = viewBlurProxy2 != null ? viewBlurProxy2.getStackBlurDrawable(this.targetType, null) : null;
            return drawableArr2;
        }
        if (z) {
            Drawable[] drawableArr3 = new Drawable[2];
            ViewBlurProxy viewBlurProxy3 = this.viewBlurProxy;
            drawableArr3[0] = viewBlurProxy3 != null ? viewBlurProxy3.getCacheStackBlurDrawable(this.targetType) : null;
            ViewBlurProxy viewBlurProxy4 = this.viewBlurProxy;
            drawableArr3[1] = viewBlurProxy4 != null ? viewBlurProxy4.getCacheStackBlurDrawable(this.lastType) : null;
            return drawableArr3;
        }
        Drawable[] drawableArr4 = new Drawable[2];
        ViewBlurProxy viewBlurProxy5 = this.viewBlurProxy;
        drawableArr4[0] = viewBlurProxy5 != null ? viewBlurProxy5.getStackBlurDrawable(this.targetType, null) : null;
        ViewBlurProxy viewBlurProxy6 = this.viewBlurProxy;
        drawableArr4[1] = viewBlurProxy6 != null ? viewBlurProxy6.getStackBlurDrawable(this.lastType, null) : null;
        return drawableArr4;
    }

    public final NotificationBackgroundView getBackgroundView() {
        return this.backgroundView;
    }

    public final Context getContext() {
        return this.context;
    }

    public final int getCurrentStackMaskAlpha() {
        return this.currentAlpha;
    }

    public final Drawable getTagStackBlurDrawable() {
        if (useStackBlurDrawable(false)) {
            if (this.oplusPanoramicAodRepository.getPanoramicAodProgress() > 0.0f) {
                return getAodDrawable();
            }
            ViewBlurProxy viewBlurProxy = this.viewBlurProxy;
            if (viewBlurProxy != null) {
                return viewBlurProxy.getStackBlurDrawable(this.targetType, null);
            }
        }
        return null;
    }

    public final float getTargetProgress() {
        return this.targetProgress;
    }

    public final int getTargetType() {
        float f = this.targetProgress;
        if (f == 0.5f) {
            return 1;
        }
        return f == 1.0f ? 2 : 0;
    }

    public final ViewBlurProxy getViewBlurProxy() {
        return this.viewBlurProxy;
    }

    public final void setBackgroundView(NotificationBackgroundView notificationBackgroundView) {
        this.backgroundView = notificationBackgroundView;
    }

    public final void setTargetProgress(float f) {
        this.targetProgress = f;
        int targetType = getTargetType();
        this.lastType = this.targetType;
        this.targetType = targetType;
        if (!useStackBlurDrawable(false)) {
            updateKeyguardStackingBackgroundEnable(false);
            return;
        }
        updateKeyguardStackingBackgroundEnable(true);
        int i = 255 - this.currentAlpha;
        ValueAnimator valueAnimator = this.animator;
        if (valueAnimator != null) {
            valueAnimator.cancel();
        }
        ValueAnimator valueAnimatorOfInt = ValueAnimator.ofInt(new int[]{i, 255});
        valueAnimatorOfInt.setDuration(300L);
        DecelerateInterpolator decelerateInterpolator = new DecelerateInterpolator();
        decelerateInterpolator.mFactor = 1.0f;
        valueAnimatorOfInt.setInterpolator(decelerateInterpolator);
        KeyguardStackingBackgroundControl$restartAnimation$1$1 keyguardStackingBackgroundControl$restartAnimation$1$1 = new KeyguardStackingBackgroundControl$restartAnimation$1$1();
        keyguardStackingBackgroundControl$restartAnimation$1$1.this$0 = this;
        VarHandle.storeStoreFence();
        valueAnimatorOfInt.addUpdateListener(keyguardStackingBackgroundControl$restartAnimation$1$1);
        this.animator = valueAnimatorOfInt;
        valueAnimatorOfInt.start(false);
    }

    public final void setViewBlurProxy(ViewBlurProxy viewBlurProxy) {
        this.viewBlurProxy = viewBlurProxy;
    }

    public final void updateKeyguardStackingBackgroundEnable(boolean z) {
        MultiPlatformBlurHelper multiPlatformBlurHelper;
        ViewBlurProxy viewBlurProxy = this.viewBlurProxy;
        if (viewBlurProxy == null || (multiPlatformBlurHelper = viewBlurProxy.getMultiPlatformBlurHelper()) == null) {
            return;
        }
        multiPlatformBlurHelper.setBlurEnabled(z);
    }

    /* JADX WARN: Code duplicated, block: B:19:0x0044  */
    /* JADX WARN: Code duplicated, block: B:22:0x004d A[RETURN] */
    public final boolean useStackBlurDrawable(boolean z) {
        if (FeatureOption.getDisableKeyguardBlurStacking()) {
            if (LogUtil.getNormalDebug() && LogUtil.isTagValid(LogUtil.TAG_NOTIFICATION)) {
                Log.d("SystemUi--Notification", "KeyguardStackingControl-->FeatureOption.disableKeyguardBlurStacking:true");
                return false;
            }
        } else if (!z) {
            if (((OplusLockscreenShadeTransitionControllerEx) DependencyEx.sDependency.getDependency(OplusLockscreenShadeTransitionControllerEx.class)).isOnOrGoingKeyguard(((SysuiStatusBarStateController) DependencyEx.sDependency.getDependency(SysuiStatusBarStateController.class)).getState() == 1)) {
                if (!ScrimUtil.isHighGaussianLevel(this.context)) {
                    return true;
                }
            }
        } else if (!ScrimUtil.isHighGaussianLevel(this.context)) {
            return true;
        }
        return false;
    }
}
