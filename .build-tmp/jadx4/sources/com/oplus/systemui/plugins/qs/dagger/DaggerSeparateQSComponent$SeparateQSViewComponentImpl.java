package com.oplus.systemui.plugins.qs.dagger;

import com.oplus.systemui.plugins.qs.OplusQSRootViewComponent;
import dagger.internal.DoubleCheck;
import dagger.internal.InstanceFactory;

/* JADX INFO: compiled from: go/retraceme fd74bf9514dd0739b92d67a6d48a51eb10aaf8df760d83496b8dcfcf83e5f698 */
/* JADX INFO: loaded from: /home/starlight/Projects/Starlight Ports/.build-tmp/classes4.dex */
public final class DaggerSeparateQSComponent$SeparateQSViewComponentImpl implements SeparateQSViewComponent {
    public DoubleCheck addTileComponentProvider;
    public DoubleCheck editModeSwitchAnimationManagerProvider;
    public DoubleCheck editTopComponentProvider;
    public DoubleCheck oplusQSBottomComponentProvider;
    public DoubleCheck oplusQSDragComponentProvider;
    public DoubleCheck oplusQSFgsDialogControllerProvider;
    public DoubleCheck oplusQSLeftBTilesComponentProvider;
    public DoubleCheck oplusQSMoreButtonPopupWindowControllerProvider;
    public DoubleCheck oplusQSPersonalComponentProvider;
    public DoubleCheck oplusQSPrivacyComponentProvider;
    public DoubleCheck oplusQSQuickEntranceComponentProvider;
    public DoubleCheck oplusQSQuickEntranceRepositoryProvider;
    public DoubleCheck oplusQSQuickEntranceViewModelProvider;
    public DoubleCheck oplusQSRootViewComponentProvider;
    public DoubleCheck oplusQSTilesComponentProvider;
    public DoubleCheck oplusSeparateSettingsEntranceInteractorProvider;
    public DoubleCheck provideCellAreaProvider;
    public SeparateQSViewModule_Companion_ProvideContextFactory provideContextProvider;
    public SeparateQSViewModule_Companion_ProvideQSBottomContainerFactory provideQSBottomContainerProvider;
    public DoubleCheck provideQSFakeCarrierProvider;
    public DoubleCheck provideQSFakeClockProvider;
    public DoubleCheck provideQSFakeKeyguardCarrierProvider;
    public DoubleCheck provideQSFakeNotificationProvider;
    public DoubleCheck provideQSFakePrivacyProvider;
    public DoubleCheck provideQSFakeStatusProvider;
    public DoubleCheck provideQSFgsProvider;
    public DoubleCheck provideQSKeyguardFakeStatusProvider;
    public DoubleCheck provideQSPrivaryProvider;
    public DoubleCheck provideQSQuickEntranceProvider;
    public DoubleCheck provideQSSecurityProvider;
    public DoubleCheck provideQSStatusBarHeaderProvider;
    public DoubleCheck provideRecyclerViewDragHelperProvider;
    public SeparateQSViewModule_Companion_ProvidersLeftBTilesContainerFactory providersLeftBTilesContainerProvider;
    public SeparateQSViewModule_Companion_ProvidesIndicatorFactory providesIndicatorProvider;
    public SeparateQSViewModule_Companion_ProvidesOtherTilesContainerFactory providesOtherTilesContainerProvider;
    public SeparateQSViewModule_Companion_ProvidesPanelContainerFactory providesPanelContainerProvider;
    public SeparateQSViewModule_Companion_ProvidesPersonalContainerFactory providesPersonalContainerProvider;
    public DoubleCheck providesQSPrivacyContainerViewProvider;
    public SeparateQSViewModule_Companion_ProvidesQSQuickEntranceContainerViewFactory providesQSQuickEntranceContainerViewProvider;
    public SeparateQSViewModule_Companion_ProvidesSeparaQsFakeViewFactory providesSeparaQsFakeViewProvider;
    public SeparateQSViewModule_Companion_ProvidesStatusBarContainerFactory providesStatusBarContainerProvider;
    public DoubleCheck qSEmptyListTipComponentProvider;
    public DoubleCheck qSPanelAnimatorManagerProvider;
    public InstanceFactory qsRootViewProvider;
    public DoubleCheck separateQSFakeStatusControllerProvider;
    public DoubleCheck tileEditViewModelProvider;
    public DoubleCheck tileOperationHintActionProvider;

    @Override // com.oplus.systemui.plugins.qs.dagger.SeparateQSViewComponent
    public OplusQSRootViewComponent getQSPanelViewController() {
        return (OplusQSRootViewComponent) this.oplusQSRootViewComponentProvider.get();
    }
}
