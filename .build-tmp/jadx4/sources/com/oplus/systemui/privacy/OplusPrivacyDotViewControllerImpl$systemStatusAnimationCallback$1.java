package com.oplus.systemui.privacy;

import androidx.core.animation.Animator;
import com.android.systemui.privacy.PrivacyConfig;
import com.android.systemui.statusbar.events.SystemStatusAnimationCallback;
import java.lang.invoke.VarHandle;
import java.util.List;
import kotlin.collections.CollectionsKt__CollectionsKt;

/* JADX INFO: compiled from: go/retraceme fd74bf9514dd0739b92d67a6d48a51eb10aaf8df760d83496b8dcfcf83e5f698 */
/* JADX INFO: loaded from: /home/starlight/Projects/Starlight Ports/.build-tmp/classes4.dex */
public final class OplusPrivacyDotViewControllerImpl$systemStatusAnimationCallback$1 implements SystemStatusAnimationCallback {
    public /* synthetic */ OplusPrivacyDotViewControllerImpl this$0;

    public final Animator onHidePersistentDot() {
        OplusPrivacyDotViewControllerImplKt.access$dlog("onHidePersistentDot");
        OplusPrivacyDotViewControllerImpl oplusPrivacyDotViewControllerImpl = this.this$0;
        OplusPrivacyDotViewControllerImpl$init$1$$ExternalSyntheticLambda1 oplusPrivacyDotViewControllerImpl$init$1$$ExternalSyntheticLambda1 = new OplusPrivacyDotViewControllerImpl$init$1$$ExternalSyntheticLambda1(6);
        oplusPrivacyDotViewControllerImpl$init$1$$ExternalSyntheticLambda1.f$0 = oplusPrivacyDotViewControllerImpl;
        VarHandle.storeStoreFence();
        oplusPrivacyDotViewControllerImpl.runOnSdThread(oplusPrivacyDotViewControllerImpl$init$1$$ExternalSyntheticLambda1);
        return null;
    }

    public /* bridge */ /* synthetic */ Animator onSystemEventAnimationBegin() {
        return super.onSystemEventAnimationBegin();
    }

    public /* bridge */ /* synthetic */ Animator onSystemEventAnimationFinish(boolean z) {
        return super.onSystemEventAnimationFinish(z);
    }

    public final Animator onSystemStatusAnimationTransitionToPersistentDot(String str, List list) {
        OplusPrivacyDotViewControllerImplKt.access$dlog("onSystemStatusAnimationTransitionToPersistentDot");
        if (list == null) {
            list = CollectionsKt__CollectionsKt.emptyList();
        }
        int privacyColorRes = PrivacyConfig.Companion.getPrivacyColorRes(list);
        OplusPrivacyDotViewControllerImpl oplusPrivacyDotViewControllerImpl = this.this$0;
        OplusPrivacyDotViewControllerImpl$systemStatusAnimationCallback$1$$ExternalSyntheticLambda0 oplusPrivacyDotViewControllerImpl$systemStatusAnimationCallback$1$$ExternalSyntheticLambda0 = new OplusPrivacyDotViewControllerImpl$systemStatusAnimationCallback$1$$ExternalSyntheticLambda0();
        oplusPrivacyDotViewControllerImpl$systemStatusAnimationCallback$1$$ExternalSyntheticLambda0.f$0 = oplusPrivacyDotViewControllerImpl;
        oplusPrivacyDotViewControllerImpl$systemStatusAnimationCallback$1$$ExternalSyntheticLambda0.f$1 = privacyColorRes;
        oplusPrivacyDotViewControllerImpl$systemStatusAnimationCallback$1$$ExternalSyntheticLambda0.f$2 = str;
        VarHandle.storeStoreFence();
        oplusPrivacyDotViewControllerImpl.runOnSdThread(oplusPrivacyDotViewControllerImpl$systemStatusAnimationCallback$1$$ExternalSyntheticLambda0);
        return null;
    }
}
