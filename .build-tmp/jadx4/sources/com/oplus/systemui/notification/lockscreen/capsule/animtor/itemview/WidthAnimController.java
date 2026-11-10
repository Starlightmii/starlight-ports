package com.oplus.systemui.notification.lockscreen.capsule.animtor.itemview;

import com.android.systemui.statusbar.notification.OplusPhysicsPropertyAnimator$;
import com.coui.appcompat.animation.dynamicanimation.COUIDynamicAnimation;
import com.coui.appcompat.animation.dynamicanimation.COUISpringAnimation;
import com.oplus.systemui.notification.lockscreen.config.CapsuleConfig;
import com.oplusos.systemui.common.util.LogUtil;

/* JADX INFO: compiled from: go/retraceme fd74bf9514dd0739b92d67a6d48a51eb10aaf8df760d83496b8dcfcf83e5f698 */
/* JADX INFO: loaded from: /home/starlight/Projects/Starlight Ports/.build-tmp/classes4.dex */
public abstract class WidthAnimController {
    public static final Companion Companion = new Companion();
    public static final String TAG = "WidthAnimController";
    private UpdateData curUpdateData;

    /* JADX INFO: compiled from: go/retraceme fd74bf9514dd0739b92d67a6d48a51eb10aaf8df760d83496b8dcfcf83e5f698 */
    public final class Companion {
    }

    public WidthAnimController() {
        CapsuleConfig capsuleConfig = CapsuleConfig.INSTANCE;
        this.curUpdateData = new UpdateData(capsuleConfig.getItemWidth(), capsuleConfig.getItemHeight(), 0.0f, 0.0f, Float.MIN_VALUE, true);
    }

    public final void cancelAnim(COUISpringAnimation cOUISpringAnimation) {
        if (cOUISpringAnimation != null && ((COUIDynamicAnimation) cOUISpringAnimation).mRunning) {
            cOUISpringAnimation.cancel();
        }
    }

    public final UpdateData getCurUpdateData() {
        return this.curUpdateData;
    }

    public final void initEarTransX(float f) {
        if (LogUtil.getNormalDebug() && LogUtil.isTagValid(LogUtil.TAG_NOTIFICATION)) {
            OplusPhysicsPropertyAnimator$.ExternalSyntheticOutline0.m("initEarTransX:", f, "WidthAnimController-->", "SystemUi--Notification");
        }
        this.curUpdateData.setEarTransX(f);
    }

    public final void setCurUpdateData(UpdateData updateData) {
        this.curUpdateData = updateData;
    }

    public abstract void startAnim(ItemAnimatorFinished itemAnimatorFinished, boolean z);

    public final void stopAnim(COUISpringAnimation cOUISpringAnimation) {
        if (cOUISpringAnimation != null && ((COUIDynamicAnimation) cOUISpringAnimation).mRunning && cOUISpringAnimation.canSkipToEnd()) {
            cOUISpringAnimation.skipToEnd();
        }
    }
}
