package com.oplus.systemui.notification.lockscreen.effect;

import android.util.FloatProperty;
import android.util.Log;
import android.view.View;
import android.view.animation.Interpolator;
import androidx.exifinterface.media.ExifInterface$;
import com.android.systemui.statusbar.notification.AnimatableProperty;
import com.android.systemui.statusbar.notification.OplusPhysicsProperty;
import com.android.systemui.statusbar.notification.OplusPhysicsPropertyAnimator;
import com.android.systemui.statusbar.notification.PropertyAnimator;
import com.android.systemui.statusbar.notification.stack.AnimationProperties;
import com.android.systemui.statusbar.notification.stack.ViewState;
import com.oplus.systemui.notification.NotificationExtFlag;
import com.oplus.systemui.notification.base.RowAnimatorExtImplKt;
import com.oplus.systemui.statusbar.notification.stack.OplusExpandableViewStateExImplKt;
import com.oplusos.systemui.common.util.LogUtil;
import kotlin.jvm.internal.Intrinsics;

/* JADX INFO: compiled from: go/retraceme fd74bf9514dd0739b92d67a6d48a51eb10aaf8df760d83496b8dcfcf83e5f698 */
/* JADX INFO: loaded from: /home/starlight/Projects/Starlight Ports/.build-tmp/classes4.dex */
public abstract class OplusPhysicsPropertyAnimatorExtKt {
    private static final String TAG = "OplusPhysicsPropertyAnimatorExt";

    public static final void animatePropertyIfChanged(ViewState viewState, View view, float f, AnimatableProperty animatableProperty, boolean z, Interpolator interpolator, long j, float f2) {
        Float f3 = (Float) ((FloatProperty) animatableProperty.getProperty()).get(view);
        if (Intrinsics.areEqual(f3, f)) {
            int animatorTag = animatableProperty.getAnimatorTag();
            viewState.getClass();
            ViewState.abortAnimation(view, animatorTag);
            return;
        }
        if (LogUtil.getNormalDebug() && LogUtil.isTagValid(LogUtil.TAG_NOTIFICATION)) {
            StringBuilder sbM = ExifInterface$.ExternalSyntheticOutline1.m("animatePropertyIfChanged: ", animatableProperty.getProperty().getName(), ", ", RowAnimatorExtImplKt.tagId(view), ", currentValue=");
            sbM.append(f3);
            sbM.append(", targetValue=");
            sbM.append(f);
            Log.d("SystemUi--Notification", "OplusPhysicsPropertyAnimatorExt-->".concat(sbM.toString()));
        }
        AnimationProperties animationProperties = new AnimationProperties();
        animationProperties.duration = j;
        if (interpolator != null) {
            animationProperties.setCustomInterpolator(animatableProperty.getProperty(), interpolator);
        }
        if (z) {
            OplusPhysicsPropertyAnimator.setProperty$default(view, OplusExpandableViewStateExImplKt.toPhysicsProperty(animatableProperty, f2), f, animationProperties, true, 32);
        } else {
            PropertyAnimator.startAnimation(view, animatableProperty, f, animationProperties);
        }
    }

    public static final void setFinalValue(View view, OplusPhysicsProperty oplusPhysicsProperty, float f) {
        View view2;
        OplusPhysicsProperty oplusPhysicsProperty2;
        float f2;
        if (NotificationExtFlag.USE_OPLUS_PHYSICAL) {
            view2 = view;
            oplusPhysicsProperty2 = oplusPhysicsProperty;
            f2 = f;
            OplusPhysicsPropertyAnimator.setProperty$default(view2, oplusPhysicsProperty2, f2, (AnimationProperties) null, false, 56);
        } else {
            view2 = view;
            oplusPhysicsProperty2 = oplusPhysicsProperty;
            f2 = f;
        }
        oplusPhysicsProperty2.getProperty().set(view2, Float.valueOf(f2));
    }
}
