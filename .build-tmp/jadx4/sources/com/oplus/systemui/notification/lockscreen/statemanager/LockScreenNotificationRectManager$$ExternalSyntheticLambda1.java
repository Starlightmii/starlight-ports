package com.oplus.systemui.notification.lockscreen.statemanager;

import com.oplus.systemui.notification.lockscreen.ILockScreenExtStateController;
import kotlin.Unit;
import kotlin.jvm.functions.Function1;

/* JADX INFO: compiled from: go/retraceme fd74bf9514dd0739b92d67a6d48a51eb10aaf8df760d83496b8dcfcf83e5f698 */
/* JADX INFO: loaded from: /home/starlight/Projects/Starlight Ports/.build-tmp/classes4.dex */
public final /* synthetic */ class LockScreenNotificationRectManager$$ExternalSyntheticLambda1 implements Function1 {
    public /* synthetic */ LockScreenNotificationRectManager f$0;

    public final Object invoke(Object obj) {
        LockScreenNotificationRectManager lockScreenNotificationRectManager = this.f$0;
        int iIntValue = ((Integer) obj).intValue();
        ILockScreenExtStateController.Companion.getClass();
        if (iIntValue == 0 || iIntValue == 3) {
            LockScreenNotificationRectManager.tryRequestNewHeight$default(lockScreenNotificationRectManager, 1);
        }
        return Unit.INSTANCE;
    }
}
