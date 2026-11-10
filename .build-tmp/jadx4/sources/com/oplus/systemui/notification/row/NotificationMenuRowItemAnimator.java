package com.oplus.systemui.notification.row;

import android.content.Context;
import android.util.Log;
import android.view.View;
import android.view.ViewGroup;
import androidx.compose.animation.core.MonoSpline$;
import androidx.compose.ui.spatial.RectList$;
import androidx.core.view.insets.Protection$;
import androidx.recyclerview.widget.ChildHelper$;
import com.oplus.systemui.keyguard.clock.WeatherInfoParseHelper$;
import com.oplusos.systemui.common.util.LogUtil;
import com.oplusos.systemui.common.utils.DeviceUtil;
import java.lang.invoke.VarHandle;
import kotlin.Lazy;
import kotlin.LazyKt__LazyJVMKt;

/* JADX INFO: compiled from: go/retraceme fd74bf9514dd0739b92d67a6d48a51eb10aaf8df760d83496b8dcfcf83e5f698 */
/* JADX INFO: loaded from: /home/starlight/Projects/Starlight Ports/.build-tmp/classes4.dex */
public final class NotificationMenuRowItemAnimator {
    private final Lazy animatorStage1$delegate;
    private final Lazy animatorStage2$delegate;
    private final Context context;
    private final Lazy deviceParams$delegate;
    public float lastTranslation;
    private final MenuViewInfoProvider viewInfo;

    public static NotificationMenuRowItemAnimatorStage2 $r8$lambda$2ySJkdW1g1W68dreiwKArsVJ1Qc(NotificationMenuRowItemAnimator notificationMenuRowItemAnimator) {
        return new NotificationMenuRowItemAnimatorStage2(notificationMenuRowItemAnimator.viewInfo, notificationMenuRowItemAnimator.context, notificationMenuRowItemAnimator.getDeviceParams());
    }

    /* JADX INFO: renamed from: $r8$lambda$kQZu0Fns9XnUlCCLd7-c2kFnIK4, reason: not valid java name */
    public static NotificationMenuRowItemAnimatorStage1 m95$r8$lambda$kQZu0Fns9XnUlCCLd7c2kFnIK4(NotificationMenuRowItemAnimator notificationMenuRowItemAnimator) {
        return new NotificationMenuRowItemAnimatorStage1(notificationMenuRowItemAnimator.viewInfo, notificationMenuRowItemAnimator.context, notificationMenuRowItemAnimator.getDeviceParams());
    }

    /* JADX INFO: renamed from: $r8$lambda$oj47KLsr5omCH-_ify28iKrkOLE, reason: not valid java name */
    public static NotificationMenuViewMetaBallDeviceParams m96$r8$lambda$oj47KLsr5omCH_ify28iKrkOLE(NotificationMenuRowItemAnimator notificationMenuRowItemAnimator) {
        return new NotificationMenuViewMetaBallDeviceParams(notificationMenuRowItemAnimator.context);
    }

    public NotificationMenuRowItemAnimator(MenuViewInfoProvider menuViewInfoProvider, Context context) {
        this.viewInfo = menuViewInfoProvider;
        this.context = context;
        NotificationMenuRowItemAnimator$$ExternalSyntheticLambda0 notificationMenuRowItemAnimator$$ExternalSyntheticLambda0 = new NotificationMenuRowItemAnimator$$ExternalSyntheticLambda0(0);
        notificationMenuRowItemAnimator$$ExternalSyntheticLambda0.f$0 = this;
        VarHandle.storeStoreFence();
        this.deviceParams$delegate = LazyKt__LazyJVMKt.lazy(notificationMenuRowItemAnimator$$ExternalSyntheticLambda0);
        NotificationMenuRowItemAnimator$$ExternalSyntheticLambda0 notificationMenuRowItemAnimator$$ExternalSyntheticLambda1 = new NotificationMenuRowItemAnimator$$ExternalSyntheticLambda0(1);
        notificationMenuRowItemAnimator$$ExternalSyntheticLambda1.f$0 = this;
        VarHandle.storeStoreFence();
        this.animatorStage1$delegate = LazyKt__LazyJVMKt.lazy(notificationMenuRowItemAnimator$$ExternalSyntheticLambda1);
        NotificationMenuRowItemAnimator$$ExternalSyntheticLambda0 notificationMenuRowItemAnimator$$ExternalSyntheticLambda2 = new NotificationMenuRowItemAnimator$$ExternalSyntheticLambda0(2);
        notificationMenuRowItemAnimator$$ExternalSyntheticLambda2.f$0 = this;
        VarHandle.storeStoreFence();
        this.animatorStage2$delegate = LazyKt__LazyJVMKt.lazy(notificationMenuRowItemAnimator$$ExternalSyntheticLambda2);
    }

    private final NotificationMenuRowItemAnimatorStage1 getAnimatorStage1() {
        return (NotificationMenuRowItemAnimatorStage1) this.animatorStage1$delegate.getValue();
    }

    private final NotificationMenuRowItemAnimatorStage2 getAnimatorStage2() {
        return (NotificationMenuRowItemAnimatorStage2) this.animatorStage2$delegate.getValue();
    }

    private final NotificationMenuViewMetaBallDeviceParams getDeviceParams() {
        return (NotificationMenuViewMetaBallDeviceParams) this.deviceParams$delegate.getValue();
    }

    private final float getMaxTranslationDelta() {
        return DeviceUtil.dp2px(this.context, NotificationMenuRowParams.AnimateParamTwo.INSTANCE.getTWO_ANIMATE_THRESHOLD().get().floatValue());
    }

    public final void onConfigChange() {
        getDeviceParams().onConfigChange();
    }

    public final void onParentTranslationUpdated(View view, float f, ViewGroup viewGroup, float f2) {
        View view2;
        ViewGroup viewGroup2;
        float f3 = f - this.lastTranslation;
        if (f3 == 0.0f) {
            return;
        }
        boolean zIsLayoutRtl = view.isLayoutRtl();
        if ((zIsLayoutRtl && f < 0.0f) || (!zIsLayoutRtl && f > 0.0f)) {
            viewGroup.setAlpha(0.0f);
            return;
        }
        if (viewGroup.getAlpha() == 0.0f) {
            viewGroup.setAlpha(1.0f);
        }
        int width = view.getWidth();
        int width2 = this.viewInfo.getIconSize(false).getWidth();
        int iconMargin = this.viewInfo.getIconMargin();
        int iconMarginExtra = this.viewInfo.getIconMarginExtra();
        int i = width2 + iconMargin;
        int childCount = viewGroup.getChildCount();
        float f4 = (i * childCount) + iconMarginExtra;
        float fAbs = Math.abs(f) - f4;
        getAnimatorStage1().setTranslationDelete(Math.max(fAbs, 0.0f));
        if (LogUtil.getNormalDebug() && LogUtil.isTagValid(LogUtil.TAG_NOTIFICATION)) {
            StringBuilder sbM = MonoSpline$.ExternalSyntheticOutline0.m("START---- \n lastTranslation = ", this.lastTranslation, "\n translation = ", f, "\n delta = ");
            ChildHelper$.ExternalSyntheticOutline0.m(f3, width, "\n parentWidth = ", "\n childrenWidth = ", sbM);
            RectList$.ExternalSyntheticOutline0.m(width2, iconMargin, "\n childrenMargin = ", "\n gridWidth = ", sbM);
            RectList$.ExternalSyntheticOutline0.m(i, childCount, "\n childCount = ", "\n defaultThreshold = ", sbM);
            sbM.append(f4);
            sbM.append("\n translationDelta = ");
            sbM.append(fAbs);
            WeatherInfoParseHelper$.ExternalSyntheticOutline0.m("NotificationMenuRowMetaBall-", sbM.toString(), "NotificationMenuRowMetaBall-->", "SystemUi--Notification");
        }
        if (fAbs >= getMaxTranslationDelta() || !getAnimatorStage2().isAnimMapEmpty()) {
            view2 = view;
            viewGroup2 = viewGroup;
            if (fAbs > 0) {
                if (LogUtil.getNormalDebug() && LogUtil.isTagValid(LogUtil.TAG_NOTIFICATION)) {
                    Log.d("SystemUi--Notification", "NotificationMenuRowMetaBall-->".concat("NotificationMenuRowMetaBall-stage1 updateChildState"));
                }
                getAnimatorStage1().updateChildState(viewGroup2);
            }
        } else {
            if (LogUtil.getNormalDebug() && LogUtil.isTagValid(LogUtil.TAG_NOTIFICATION)) {
                WeatherInfoParseHelper$.ExternalSyntheticOutline0.m("NotificationMenuRowMetaBall-", Protection$.ExternalSyntheticOutline0.m(fAbs, "stage1,translationDelta = "), "NotificationMenuRowMetaBall-->", "SystemUi--Notification");
            }
            view2 = view;
            viewGroup2 = viewGroup;
            getAnimatorStage1().onParentTranslationUpdated(view2, f, viewGroup2);
        }
        getAnimatorStage1().setLastTranslation(f);
        if (getAnimatorStage1().isRunningAnimation() && fAbs < getMaxTranslationDelta()) {
            if (LogUtil.getNormalDebug() && LogUtil.isTagValid(LogUtil.TAG_NOTIFICATION)) {
                Log.d("SystemUi--Notification", "NotificationMenuRowMetaBall-->".concat("NotificationMenuRowMetaBall-stop stage1,cancel animator"));
                return;
            }
            return;
        }
        if (getAnimatorStage1().isRunningAnimation() && fAbs >= getMaxTranslationDelta() && childCount > 1) {
            getAnimatorStage1().animClear();
            if (LogUtil.getNormalDebug() && LogUtil.isTagValid(LogUtil.TAG_NOTIFICATION)) {
                Log.d("SystemUi--Notification", "NotificationMenuRowMetaBall-->".concat("NotificationMenuRowMetaBall-cancel animator"));
            }
        }
        getAnimatorStage2().onParentTranslationUpdated(view2, f, fAbs, viewGroup2, f2);
        this.lastTranslation = f;
    }

    public final void reset() {
        if (LogUtil.getNormalDebug() && LogUtil.isTagValid(LogUtil.TAG_NOTIFICATION)) {
            Log.d("SystemUi--Notification", "NotificationMenuRowMetaBall-->".concat("NotificationMenuRowMetaBall-NotificationMenuRow reset ~~~"));
        }
        NotificationMenuRowItemAnimatorStage1 animatorStage1 = getAnimatorStage1();
        animatorStage1.getClass();
        if (LogUtil.getNormalDebug() && LogUtil.isTagValid(LogUtil.TAG_NOTIFICATION)) {
            Log.d("SystemUi--Notification", "NotificationMenuRowMetaBall-->".concat("stage1-NotificationMenuRowMetaBall reset ~~~"));
        }
        animatorStage1.animClear();
        animatorStage1.lastTranslation = 0.0f;
        animatorStage1.lastCursor = 0.0f;
        animatorStage1.translationDelete = 0.0f;
        getAnimatorStage2().reset();
    }

    public final void setDismiss() {
        getAnimatorStage2().setDismiss(true);
    }
}
