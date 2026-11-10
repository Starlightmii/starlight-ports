package com.oplus.systemui.qs;

import android.view.ViewGroup;
import com.android.systemui.panelanimation.PanelAnimationEx;
import com.android.systemui.util.ViewController;
import com.oplus.systemui.qs.base.temporarilyinfo.TemporarilyInfoManager;
import kotlin.jvm.internal.Intrinsics;

/* JADX INFO: compiled from: go/retraceme fd74bf9514dd0739b92d67a6d48a51eb10aaf8df760d83496b8dcfcf83e5f698 */
/* JADX INFO: loaded from: /home/starlight/Projects/Starlight Ports/.build-tmp/classes4.dex */
public final class OplusQSContainerImplController$panelExpandFractionListener$1 implements PanelAnimationEx.ExpandedFractionChangeListener {
    public /* synthetic */ OplusQSContainerImplController this$0;

    public final void onFractionChanged(float f) {
        TemporarilyInfoManager.Companion companion = TemporarilyInfoManager.Companion;
        ViewGroup qsRootView = companion.getInstance().getQsRootView();
        OplusQSContainerImplController oplusQSContainerImplController = this.this$0;
        if (Intrinsics.areEqual(qsRootView, ((ViewController) oplusQSContainerImplController).mView)) {
            companion.getInstance().setStarted(1.0f == oplusQSContainerImplController.mPanelAnimationEx.getPanelExpandFraction().getRawFraction());
        }
    }
}
