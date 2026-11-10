package com.oplus.systemui.notification.row;

import android.animation.ObjectAnimator;
import android.view.View;
import androidx.dynamicanimation.animation.FloatValueHolder;
import com.coui.appcompat.animation.dynamicanimation.COUISpringAnimation;
import com.oplusos.systemui.common.olivelink.Spring;
import java.lang.invoke.VarHandle;
import java.util.LinkedHashMap;
import java.util.Map;

/* JADX INFO: compiled from: go/retraceme fd74bf9514dd0739b92d67a6d48a51eb10aaf8df760d83496b8dcfcf83e5f698 */
/* JADX INFO: loaded from: /home/starlight/Projects/Starlight Ports/.build-tmp/classes4.dex */
public final class NotificationMenuRowItemAnimatorDismiss {
    private final Spring alphaChange;
    private final Spring blurChange;
    private final Map<String, COUISpringAnimation> dismissAnimMap;
    public final long lottieDuration;
    public final float minimumVisibleChange;

    public NotificationMenuRowItemAnimatorDismiss() {
        NotificationMenuRowParams.AnimateParamThree animateParamThree = NotificationMenuRowParams.AnimateParamThree.INSTANCE;
        this.blurChange = animateParamThree.getBlurChange();
        this.alphaChange = animateParamThree.getAlphaChange();
        this.lottieDuration = animateParamThree.getLOTTIE_DURATION().get().longValue();
        this.minimumVisibleChange = animateParamThree.getMinimumVisibleChange_DISMISS().get().floatValue();
        this.dismissAnimMap = new LinkedHashMap();
    }

    public final void startAlphaChange(View view) {
        COUISpringAnimation cOUISpringAnimation = new COUISpringAnimation(new FloatValueHolder());
        cOUISpringAnimation.setMinimumVisibleChange(this.minimumVisibleChange);
        NotificationMenuRowParams.INSTANCE.loadOLiveLinkParam(cOUISpringAnimation, this.alphaChange);
        cOUISpringAnimation.setStartValue(100.0f);
        int i = 1;
        NotificationMenuRowItemAnimatorDismiss$startBlurChange$1$1 notificationMenuRowItemAnimatorDismiss$startBlurChange$1$1 = new NotificationMenuRowItemAnimatorDismiss$startBlurChange$1$1(i);
        notificationMenuRowItemAnimatorDismiss$startBlurChange$1$1.$deleteView = view;
        VarHandle.storeStoreFence();
        cOUISpringAnimation.addUpdateListener(notificationMenuRowItemAnimatorDismiss$startBlurChange$1$1);
        NotificationMenuRowItemAnimatorDismiss$startBlurChange$1$2 notificationMenuRowItemAnimatorDismiss$startBlurChange$1$2 = new NotificationMenuRowItemAnimatorDismiss$startBlurChange$1$2(i);
        notificationMenuRowItemAnimatorDismiss$startBlurChange$1$2.$deleteView = view;
        notificationMenuRowItemAnimatorDismiss$startBlurChange$1$2.this$0 = this;
        VarHandle.storeStoreFence();
        cOUISpringAnimation.addEndListener(notificationMenuRowItemAnimatorDismiss$startBlurChange$1$2);
        cOUISpringAnimation.animateToFinalPosition(0.0f);
        this.dismissAnimMap.put("startAlphaChange", cOUISpringAnimation);
    }

    public final void startBlurChange(View view) {
        COUISpringAnimation cOUISpringAnimation = new COUISpringAnimation(new FloatValueHolder());
        cOUISpringAnimation.setMinimumVisibleChange(this.minimumVisibleChange);
        NotificationMenuRowParams.INSTANCE.loadOLiveLinkParam(cOUISpringAnimation, this.blurChange);
        cOUISpringAnimation.setStartValue(100.0f);
        int i = 0;
        NotificationMenuRowItemAnimatorDismiss$startBlurChange$1$1 notificationMenuRowItemAnimatorDismiss$startBlurChange$1$1 = new NotificationMenuRowItemAnimatorDismiss$startBlurChange$1$1(i);
        notificationMenuRowItemAnimatorDismiss$startBlurChange$1$1.$deleteView = view;
        VarHandle.storeStoreFence();
        cOUISpringAnimation.addUpdateListener(notificationMenuRowItemAnimatorDismiss$startBlurChange$1$1);
        NotificationMenuRowItemAnimatorDismiss$startBlurChange$1$2 notificationMenuRowItemAnimatorDismiss$startBlurChange$1$2 = new NotificationMenuRowItemAnimatorDismiss$startBlurChange$1$2(i);
        notificationMenuRowItemAnimatorDismiss$startBlurChange$1$2.$deleteView = view;
        notificationMenuRowItemAnimatorDismiss$startBlurChange$1$2.this$0 = this;
        VarHandle.storeStoreFence();
        cOUISpringAnimation.addEndListener(notificationMenuRowItemAnimatorDismiss$startBlurChange$1$2);
        cOUISpringAnimation.animateToFinalPosition(0.0f);
        this.dismissAnimMap.put("startBlurChange", cOUISpringAnimation);
    }

    public final void startDismissRowAnimator(View view, View view2) {
        startBlurChange(view);
        startAlphaChange(view);
        startLottieChange(view);
    }

    public final void startLottieChange(View view) {
        if ((view instanceof NotificationMenuRowItemLottieView ? (NotificationMenuRowItemLottieView) view : null) == null) {
            return;
        }
        ObjectAnimator objectAnimatorOfFloat = ObjectAnimator.ofFloat(((NotificationMenuRowItemLottieView) view).getLottieView(), "progress", 0.5f, 1.0f);
        objectAnimatorOfFloat.setDuration(this.lottieDuration);
        objectAnimatorOfFloat.start();
    }
}
