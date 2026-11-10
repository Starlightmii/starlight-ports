package com.oplus.systemui.plugins.qs.animator;

import android.view.ViewGroup;
import android.widget.FrameLayout;
import com.android.systemui.plugins.qs.OplusSeparateQSPlugin;
import com.oplus.systemui.plugins.qs.OplusQSRootView;
import com.oplus.systemui.plugins.qs.dagger.SeparateQSViewModule_Companion_ProvidesQSQuickEntranceContainerViewFactory;
import com.oplus.systemui.plugins.qs.dagger.SeparateQSViewModule_Companion_ProvidesSeparaQsFakeViewFactory;
import com.oplus.systemui.plugins.qs.dagger.SeparateQSViewModule_Companion_ProvidesStatusBarContainerFactory;
import com.oplus.systemui.plugins.qs.quickentrance.ui.view.OplusQSQuickEntranceContainerView;
import dagger.internal.Factory;
import dagger.internal.InstanceFactory;

/* JADX INFO: compiled from: go/retraceme fd74bf9514dd0739b92d67a6d48a51eb10aaf8df760d83496b8dcfcf83e5f698 */
/* JADX INFO: loaded from: /home/starlight/Projects/Starlight Ports/.build-tmp/classes4.dex */
public final class QSPanelAnimatorManager_Factory implements Factory {
    public SeparateQSViewModule_Companion_ProvidesQSQuickEntranceContainerViewFactory oplusQSQuickEntranceContainerViewProvider;
    public InstanceFactory qsRootViewProvider;
    public SeparateQSViewModule_Companion_ProvidesSeparaQsFakeViewFactory separateQsFakeLayoutProvider;
    public SeparateQSViewModule_Companion_ProvidesStatusBarContainerFactory statusBarContainerProvider;
    public InstanceFactory stubProvider;

    /* JADX INFO: renamed from: get, reason: merged with bridge method [inline-methods] */
    public QSPanelAnimatorManager m121get() {
        return new QSPanelAnimatorManager((OplusQSRootView) this.qsRootViewProvider.get(), (OplusQSQuickEntranceContainerView) this.oplusQSQuickEntranceContainerViewProvider.get(), (OplusSeparateQSPlugin.IStub) this.stubProvider.get(), (ViewGroup) this.statusBarContainerProvider.get(), (FrameLayout) this.separateQsFakeLayoutProvider.get());
    }
}
