package com.oplus.systemui.plugins.qs.seamless;

import android.view.ViewGroup;
import android.widget.FrameLayout;
import com.android.systemui.plugins.qs.OplusSeparateQSPlugin;
import com.android.systemui.plugins.qs.QSElement;
import com.android.systemui.plugins.qs.QSFakeStatusElement;
import com.oplus.systemui.plugins.qs.CellAreaProvider;
import com.oplus.systemui.plugins.qs.animator.QSPanelAnimatorManager;
import com.oplus.systemui.plugins.qs.privacy.ui.view.OplusQSPrivacyContainerView;
import com.oplus.systemui.plugins.qs.quickentrance.OplusQSQuickEntranceComponent;
import dagger.internal.DoubleCheck;
import dagger.internal.Factory;
import dagger.internal.InstanceFactory;
import dagger.internal.Provider;

/* JADX INFO: compiled from: go/retraceme fd74bf9514dd0739b92d67a6d48a51eb10aaf8df760d83496b8dcfcf83e5f698 */
/* JADX INFO: loaded from: /home/starlight/Projects/Starlight Ports/.build-tmp/classes4.dex */
public final class SeparateQSFakeStatusController_Factory implements Factory {
    public Provider cellAreaProvider;
    public Provider qsFakeCarrierProvider;
    public Provider qsFakeClockProvider;
    public DoubleCheck qsFakeKeyguardCarrierProvider;
    public Provider qsFakeNotificationIconProvider;
    public Provider qsFakePrivacyIconProvider;
    public Provider qsFakeStatusIconProvider;
    public Provider qsKeyguardFakeStatusIconProvider;
    public Provider qsPanelAnimatorManagerProvider;
    public Provider quickEntranceContainerViewControllerProvider;
    public Provider separateQSPrivacyViewProvider;
    public Provider separateQsFakeLayoutProvider;
    public Provider statusBarContainerProvider;
    public Provider statusBarHeaderProvider;
    public InstanceFactory stubProvider;

    /* JADX INFO: renamed from: get, reason: merged with bridge method [inline-methods] */
    public SeparateQSFakeStatusController m237get() {
        return new SeparateQSFakeStatusController((QSPanelAnimatorManager) this.qsPanelAnimatorManagerProvider.get(), (FrameLayout) this.separateQsFakeLayoutProvider.get(), (OplusSeparateQSPlugin.IStub) this.stubProvider.get(), (OplusQSQuickEntranceComponent) this.quickEntranceContainerViewControllerProvider.get(), (OplusQSPrivacyContainerView) this.separateQSPrivacyViewProvider.get(), (ViewGroup) this.statusBarContainerProvider.get(), (CellAreaProvider) this.cellAreaProvider.get(), (QSElement) this.statusBarHeaderProvider.get(), (QSFakeStatusElement) this.qsFakeStatusIconProvider.get(), (QSFakeStatusElement) this.qsFakePrivacyIconProvider.get(), (QSFakeStatusElement) this.qsKeyguardFakeStatusIconProvider.get(), (QSFakeStatusElement) this.qsFakeNotificationIconProvider.get(), (QSFakeStatusElement) this.qsFakeClockProvider.get(), (QSFakeStatusElement) this.qsFakeCarrierProvider.get(), (QSFakeStatusElement) this.qsFakeKeyguardCarrierProvider.get());
    }
}
