package com.oplus.systemui.notification.lockscreen.stack;

import com.oplus.systemui.statusbar.notification.helper.NotificationKeyguardHelper;
import com.oplusos.systemui.common.sample.DisplacementThresholdSampler;
import java.lang.invoke.VarHandle;
import kotlin.jvm.functions.Function0;

/* JADX INFO: compiled from: go/retraceme fd74bf9514dd0739b92d67a6d48a51eb10aaf8df760d83496b8dcfcf83e5f698 */
/* JADX INFO: loaded from: /home/starlight/Projects/Starlight Ports/.build-tmp/classes4.dex */
public final /* synthetic */ class LockscreenNotificationDragHelperExImpl$$ExternalSyntheticLambda0 implements Function0 {
    public final /* synthetic */ int $r8$classId;
    public /* synthetic */ LockscreenNotificationDragHelperExImpl f$0;

    public /* synthetic */ LockscreenNotificationDragHelperExImpl$$ExternalSyntheticLambda0(int i) {
        this.$r8$classId = i;
    }

    public final Object invoke() {
        boolean lockScreenNotificationEnableSinking;
        int i = this.$r8$classId;
        LockscreenNotificationDragHelperExImpl lockscreenNotificationDragHelperExImpl = this.f$0;
        switch (i) {
            case 0:
                lockScreenNotificationEnableSinking = ((NotificationKeyguardHelper) lockscreenNotificationDragHelperExImpl.notificationKeyguardHelper.get()).mLockScreenNotificationEnableSinkingObserver.getLockScreenNotificationEnableSinking();
                break;
            case 1:
                LockscreenNotificationDragHelperExImpl$$ExternalSyntheticLambda3 lockscreenNotificationDragHelperExImpl$$ExternalSyntheticLambda3 = new LockscreenNotificationDragHelperExImpl$$ExternalSyntheticLambda3();
                LockscreenNotificationDragHelperExImpl$$ExternalSyntheticLambda0 lockscreenNotificationDragHelperExImpl$$ExternalSyntheticLambda0 = new LockscreenNotificationDragHelperExImpl$$ExternalSyntheticLambda0(3);
                lockscreenNotificationDragHelperExImpl$$ExternalSyntheticLambda0.f$0 = lockscreenNotificationDragHelperExImpl;
                VarHandle.storeStoreFence();
                LockscreenNotificationDragHelperExImpl$$ExternalSyntheticLambda5 lockscreenNotificationDragHelperExImpl$$ExternalSyntheticLambda5 = new LockscreenNotificationDragHelperExImpl$$ExternalSyntheticLambda5();
                lockscreenNotificationDragHelperExImpl$$ExternalSyntheticLambda5.f$0 = lockscreenNotificationDragHelperExImpl;
                VarHandle.storeStoreFence();
                return new DisplacementThresholdSampler(lockscreenNotificationDragHelperExImpl$$ExternalSyntheticLambda3, lockscreenNotificationDragHelperExImpl$$ExternalSyntheticLambda0, lockscreenNotificationDragHelperExImpl$$ExternalSyntheticLambda5);
            case 2:
                return LockscreenNotificationDragHelperExImpl.m91$r8$lambda$wN4fW_0mA70jWqT7u4JJQEr7BU(lockscreenNotificationDragHelperExImpl);
            default:
                lockScreenNotificationEnableSinking = LockscreenNotificationDragHelperExImpl.$r8$lambda$_IksZ1viw9UXBM32cgYFdHQC43c(lockscreenNotificationDragHelperExImpl);
                break;
        }
        return Boolean.valueOf(lockScreenNotificationEnableSinking);
    }
}
