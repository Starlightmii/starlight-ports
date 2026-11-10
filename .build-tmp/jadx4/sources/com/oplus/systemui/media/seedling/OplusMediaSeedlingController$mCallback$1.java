package com.oplus.systemui.media.seedling;

import com.android.systemui.Dependency;
import com.android.systemui.statusbar.policy.KeyguardStateController;
import com.oplusos.systemui.notification.logger.OplusNotificationLogger;

/* JADX INFO: compiled from: go/retraceme fd74bf9514dd0739b92d67a6d48a51eb10aaf8df760d83496b8dcfcf83e5f698 */
/* JADX INFO: loaded from: /home/starlight/Projects/Starlight Ports/.build-tmp/classes4.dex */
public final class OplusMediaSeedlingController$mCallback$1 implements KeyguardStateController.Callback {
    public /* synthetic */ OplusMediaSeedlingController this$0;

    public final void onKeyguardShowingChanged() {
        boolean z = ((KeyguardStateController) Dependency.sDependency.getDependencyInner(KeyguardStateController.class)).mShowing;
        boolean z2 = ((KeyguardStateController) Dependency.sDependency.getDependencyInner(KeyguardStateController.class)).mOccluded;
        OplusNotificationLogger.logMediaSeedling("OplusMediaSeedlingController", "Keyguard isShowing: " + z + "; isOccluded: " + z2);
        MediaSeedling mediaSeedling = (MediaSeedling) this.this$0.mediaSeedling.get();
        if (mediaSeedling.isShowKeyguard != z) {
            mediaSeedling.isShowKeyguard = z;
            MediaSeedling.postSeedlingsUpdate$default(mediaSeedling, "setKeyguardShowingChanged " + z, 7);
        }
        if (mediaSeedling.isOccluded != z2) {
            mediaSeedling.isOccluded = z2;
            MediaSeedling.postSeedlingsUpdate$default(mediaSeedling, "setKeyguardShowingChanged " + z2, 7);
        }
    }
}
