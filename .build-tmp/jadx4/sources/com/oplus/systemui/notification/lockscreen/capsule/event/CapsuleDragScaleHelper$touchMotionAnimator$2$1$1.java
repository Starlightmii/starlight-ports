package com.oplus.systemui.notification.lockscreen.capsule.event;

import android.util.Log;
import com.coui.appcompat.animation.touchanimation.COUIIComposeMotionUnit;
import com.coui.appcompat.animation.touchanimation.OnAnimationEndListener;
import com.oplusos.systemui.common.util.LogUtil;

/* JADX INFO: compiled from: go/retraceme fd74bf9514dd0739b92d67a6d48a51eb10aaf8df760d83496b8dcfcf83e5f698 */
/* JADX INFO: loaded from: /home/starlight/Projects/Starlight Ports/.build-tmp/classes4.dex */
public final class CapsuleDragScaleHelper$touchMotionAnimator$2$1$1 implements OnAnimationEndListener {
    public static final CapsuleDragScaleHelper$touchMotionAnimator$2$1$1 INSTANCE = new CapsuleDragScaleHelper$touchMotionAnimator$2$1$1();

    public final void onAnimationEnd(COUIIComposeMotionUnit cOUIIComposeMotionUnit, boolean z, float f, float f2) {
        if (LogUtil.getNormalDebug() && LogUtil.isTagValid(LogUtil.TAG_NOTIFICATION)) {
            Log.d("SystemUi--Notification", "CapsuleDragScaleHelper-->".concat("OnAnimationEnd: canceled:" + z + ", value:" + f));
        }
    }
}
