package com.oplus.systemui.plugins.qs.dagger;

import androidx.constraintlayout.widget.ConstraintLayout;
import com.oplus.systemui.plugins.qs.OplusQSRootView;
import dagger.internal.Factory;
import dagger.internal.InstanceFactory;

/* JADX INFO: compiled from: go/retraceme fd74bf9514dd0739b92d67a6d48a51eb10aaf8df760d83496b8dcfcf83e5f698 */
/* JADX INFO: loaded from: /home/starlight/Projects/Starlight Ports/.build-tmp/classes4.dex */
public final class SeparateQSViewModule_Companion_ProvidesPanelContainerFactory implements Factory {
    public InstanceFactory viewProvider;

    /* JADX INFO: renamed from: get, reason: merged with bridge method [inline-methods] */
    public ConstraintLayout m215get() {
        ConstraintLayout constraintLayoutProvidesPanelContainer = SeparateQSViewModule.Companion.providesPanelContainer((OplusQSRootView) this.viewProvider.get());
        constraintLayoutProvidesPanelContainer.getClass();
        return constraintLayoutProvidesPanelContainer;
    }
}
