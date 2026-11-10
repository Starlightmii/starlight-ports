package com.oplus.systemui.notification.headsup.windowframe;

import android.util.Log;
import android.view.MotionEvent;
import com.android.keyguard.OplusLockIconView$;
import com.oplusos.systemui.common.util.LogUtil;

/* JADX INFO: compiled from: go/retraceme fd74bf9514dd0739b92d67a6d48a51eb10aaf8df760d83496b8dcfcf83e5f698 */
/* JADX INFO: loaded from: /home/starlight/Projects/Starlight Ports/.build-tmp/classes4.dex */
public final class HeadsUpCOUItouchMotionAnimator$couiMotionForwarder$1 implements CouiMotionForwarder {
    public /* synthetic */ HeadsUpCOUItouchMotionAnimator this$0;

    public final void forwardCancel() {
        HeadsUpCOUItouchMotionAnimator headsUpCOUItouchMotionAnimator = this.this$0;
        if (LogUtil.getNormalDebug() && LogUtil.isTagValid(LogUtil.TAG_NOTIFICATION)) {
            OplusLockIconView$.ExternalSyntheticOutline0.m("forwardCancel: feeding=", "HeadsUpCOUIMotion-->", "SystemUi--Notification", headsUpCOUItouchMotionAnimator.couiFeeding);
        }
        headsUpCOUItouchMotionAnimator.ignoredTouchMotionCallbackEnabledInternal = false;
        headsUpCOUItouchMotionAnimator.forceCancelAndReset("forwardCancel");
    }

    @Override // com.oplus.systemui.notification.headsup.windowframe.CouiMotionForwarder
    public final void forwardMotionEvent(MotionEvent motionEvent) {
        HeadsUpCOUItouchMotionAnimator headsUpCOUItouchMotionAnimator = this.this$0;
        if (headsUpCOUItouchMotionAnimator.ignoredTouchMotionCallbackEnabledInternal) {
            return;
        }
        int actionMasked = motionEvent.getActionMasked();
        if (actionMasked != 0) {
            if (actionMasked != 1) {
                if (actionMasked != 2) {
                    if (actionMasked != 3 && actionMasked != 4) {
                        if (LogUtil.getNormalDebug() && LogUtil.isTagValid(LogUtil.TAG_NOTIFICATION)) {
                            Log.d("SystemUi--Notification", "HeadsUpCOUIMotion-->error: unknown action");
                            return;
                        }
                        return;
                    }
                }
            }
            HeadsUpCOUItouchMotionAnimator.access$couiDispatchEndEvent(headsUpCOUItouchMotionAnimator, motionEvent);
            return;
        }
        HeadsUpCOUItouchMotionAnimator.access$couiDispatchStreamEvent(headsUpCOUItouchMotionAnimator, motionEvent);
    }

    @Override // com.oplus.systemui.notification.headsup.windowframe.CouiMotionForwarder
    public void setDragYCallback(HeadsUpDragYCallback headsUpDragYCallback) {
        this.this$0.dragYCallback = headsUpDragYCallback;
    }
}
