package com.oplus.systemui.media.seedling;

import android.content.Context;
import com.android.systemui.settings.UserTracker;
import com.oplusos.systemui.common.multiuser.IMultiUserListener;
import com.oplusos.systemui.notification.logger.OplusNotificationLogger;
import java.util.List;

/* JADX INFO: compiled from: go/retraceme fd74bf9514dd0739b92d67a6d48a51eb10aaf8df760d83496b8dcfcf83e5f698 */
/* JADX INFO: loaded from: /home/starlight/Projects/Starlight Ports/.build-tmp/classes4.dex */
public final class OplusMediaSeedlingController$userCallback$1 implements UserTracker.Callback, IMultiUserListener {
    public /* synthetic */ OplusMediaSeedlingController this$0;

    public /* bridge */ /* synthetic */ void onBeforeUserSwitching(int i, Runnable runnable) {
        super.onBeforeUserSwitching(i, runnable);
    }

    public /* bridge */ /* synthetic */ void onProfilesChanged(List list) {
        super.onProfilesChanged(list);
    }

    @Override // com.oplusos.systemui.common.multiuser.IMultiUserListener
    public final void onUnlocked() {
        OplusMediaSeedlingController oplusMediaSeedlingController = this.this$0;
        oplusMediaSeedlingController.bgHandler.post(new OplusMediaSeedlingController.AnonymousClass1(oplusMediaSeedlingController.queryRunnable));
    }

    public final void onUserChanged(int i, Context context) {
        super.onUserChanged(i, context);
        OplusNotificationLogger.logMediaSeedling("OplusMediaSeedlingController", "onUserChanged registerMediaDataCallBack: " + i);
        this.this$0.registerMediaDataCallBack();
    }

    public final void onUserChanging(int i, Context context) {
        super.onUserChanging(i, context);
        OplusNotificationLogger.logMediaSeedling("OplusMediaSeedlingController", "onUserChanging unRegisterMediaDataCallBack: " + i);
        this.this$0.unRegisterMediaDataCallBack();
    }

    public /* bridge */ /* synthetic */ void onUserChanging(int i, Context context, Runnable runnable) {
        super.onUserChanging(i, context, runnable);
    }
}
