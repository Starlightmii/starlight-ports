package com.oplus.systemui.privacy;

import com.android.systemui.keyguard.WakefulnessLifecycle;
import java.lang.invoke.VarHandle;

/* JADX INFO: compiled from: go/retraceme fd74bf9514dd0739b92d67a6d48a51eb10aaf8df760d83496b8dcfcf83e5f698 */
/* JADX INFO: loaded from: /home/starlight/Projects/Starlight Ports/.build-tmp/classes4.dex */
public final class OplusPrivacyDotViewControllerImpl$mWakefulnessObserver$1 implements WakefulnessLifecycle.Observer {
    public /* synthetic */ OplusPrivacyDotViewControllerImpl this$0;

    public final void onFinishedWakingUp() {
        OplusPrivacyDotViewControllerImplKt.access$dlog("onFinishedWakingUp wakeAllowsPrivacyDot=true");
        OplusPrivacyDotViewControllerImpl oplusPrivacyDotViewControllerImpl = this.this$0;
        OplusPrivacyDotViewControllerImpl$init$1$$ExternalSyntheticLambda1 oplusPrivacyDotViewControllerImpl$init$1$$ExternalSyntheticLambda1 = new OplusPrivacyDotViewControllerImpl$init$1$$ExternalSyntheticLambda1(5);
        oplusPrivacyDotViewControllerImpl$init$1$$ExternalSyntheticLambda1.f$0 = oplusPrivacyDotViewControllerImpl;
        VarHandle.storeStoreFence();
        oplusPrivacyDotViewControllerImpl.runOnSdThread(oplusPrivacyDotViewControllerImpl$init$1$$ExternalSyntheticLambda1);
    }

    public final void onStartedGoingToSleep() {
        OplusPrivacyDotViewControllerImplKt.access$dlog("onStartedGoingToSleep, wakeAllowsPrivacyDot=false");
        OplusPrivacyDotViewControllerImpl oplusPrivacyDotViewControllerImpl = this.this$0;
        OplusPrivacyDotViewControllerImpl$init$1$$ExternalSyntheticLambda1 oplusPrivacyDotViewControllerImpl$init$1$$ExternalSyntheticLambda1 = new OplusPrivacyDotViewControllerImpl$init$1$$ExternalSyntheticLambda1(4);
        oplusPrivacyDotViewControllerImpl$init$1$$ExternalSyntheticLambda1.f$0 = oplusPrivacyDotViewControllerImpl;
        VarHandle.storeStoreFence();
        oplusPrivacyDotViewControllerImpl.runOnSdThread(oplusPrivacyDotViewControllerImpl$init$1$$ExternalSyntheticLambda1);
    }
}
