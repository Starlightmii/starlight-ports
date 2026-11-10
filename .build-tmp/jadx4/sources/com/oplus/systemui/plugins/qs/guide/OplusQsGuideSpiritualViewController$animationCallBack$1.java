package com.oplus.systemui.plugins.qs.guide;

import android.os.Handler;
import java.lang.invoke.VarHandle;

/* JADX INFO: compiled from: go/retraceme fd74bf9514dd0739b92d67a6d48a51eb10aaf8df760d83496b8dcfcf83e5f698 */
/* JADX INFO: loaded from: /home/starlight/Projects/Starlight Ports/.build-tmp/classes4.dex */
public final class OplusQsGuideSpiritualViewController$animationCallBack$1 implements OplusQsGuideSpiritualView.AnimationCallBack {
    public /* synthetic */ OplusQsGuideSpiritualViewController this$0;

    @Override // com.oplus.systemui.plugins.qs.guide.OplusQsGuideSpiritualView.AnimationCallBack
    public final void onAnimationCancel(OplusQsGuideSpiritualView.AnimationType animationType) {
        OplusQsGuideSpiritualViewController oplusQsGuideSpiritualViewController = this.this$0;
        Handler handler = oplusQsGuideSpiritualViewController.handler;
        OplusQsGuideSpiritualViewController.AnonymousClass1 anonymousClass1 = new OplusQsGuideSpiritualViewController.AnonymousClass1(1);
        anonymousClass1.$animationType = animationType;
        anonymousClass1.this$0 = oplusQsGuideSpiritualViewController;
        VarHandle.storeStoreFence();
        handler.post(anonymousClass1);
    }

    @Override // com.oplus.systemui.plugins.qs.guide.OplusQsGuideSpiritualView.AnimationCallBack
    public final void onAnimationEnd(OplusQsGuideSpiritualView.AnimationType animationType) {
        OplusQsGuideSpiritualViewController oplusQsGuideSpiritualViewController = this.this$0;
        Handler handler = oplusQsGuideSpiritualViewController.handler;
        OplusQsGuideSpiritualViewController.AnonymousClass1 anonymousClass1 = new OplusQsGuideSpiritualViewController.AnonymousClass1(2);
        anonymousClass1.$animationType = animationType;
        anonymousClass1.this$0 = oplusQsGuideSpiritualViewController;
        VarHandle.storeStoreFence();
        handler.post(anonymousClass1);
    }

    @Override // com.oplus.systemui.plugins.qs.guide.OplusQsGuideSpiritualView.AnimationCallBack
    public final void onAnimationStart(OplusQsGuideSpiritualView.AnimationType animationType) {
        OplusQsGuideSpiritualViewController oplusQsGuideSpiritualViewController = this.this$0;
        Handler handler = oplusQsGuideSpiritualViewController.handler;
        OplusQsGuideSpiritualViewController.AnonymousClass1 anonymousClass1 = new OplusQsGuideSpiritualViewController.AnonymousClass1(3);
        anonymousClass1.$animationType = animationType;
        anonymousClass1.this$0 = oplusQsGuideSpiritualViewController;
        VarHandle.storeStoreFence();
        handler.post(anonymousClass1);
    }
}
