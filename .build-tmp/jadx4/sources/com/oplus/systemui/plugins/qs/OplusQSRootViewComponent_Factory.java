package com.oplus.systemui.plugins.qs;

import android.view.ViewGroup;
import androidx.constraintlayout.widget.ConstraintLayout;
import com.android.systemui.log.LogBuffer;
import com.android.systemui.plugins.qs.OplusSeparateQSPlugin;
import com.android.systemui.plugins.qs.QSElement;
import com.android.systemui.plugins.qs.QSSecurity;
import com.oplus.systemui.plugins.qs.addtile.AddTileComponent;
import com.oplus.systemui.plugins.qs.animator.QSPanelAnimatorManager;
import com.oplus.systemui.plugins.qs.bottom.OplusQSBottomComponent;
import com.oplus.systemui.plugins.qs.customize.component.OplusQSDragComponent;
import com.oplus.systemui.plugins.qs.customize.component.OplusQSLeftBTilesComponent;
import com.oplus.systemui.plugins.qs.customize.component.OplusQSPersonalComponent;
import com.oplus.systemui.plugins.qs.customize.component.OplusQSTilesComponent;
import com.oplus.systemui.plugins.qs.customize.component.QSEmptyListTipComponent;
import com.oplus.systemui.plugins.qs.customize.view.animation.drag.helper.RecyclerViewDragHelper;
import com.oplus.systemui.plugins.qs.editmodeswitch.EditModeSwitchAnimationManager;
import com.oplus.systemui.plugins.qs.edittop.EditTopComponent;
import com.oplus.systemui.plugins.qs.privacy.OplusQSPrivacyComponent;
import com.oplus.systemui.plugins.qs.privacy.ui.view.OplusQSPrivacyContainerView;
import com.oplus.systemui.plugins.qs.quickentrance.OplusQSQuickEntranceComponent;
import com.oplus.systemui.plugins.qs.quickentrance.popupwindow.OplusQSMoreButtonPopupWindowController;
import com.oplus.systemui.plugins.qs.seamless.SeparateQSFakeStatusController;
import dagger.internal.DoubleCheck;
import dagger.internal.Factory;
import dagger.internal.InstanceFactory;
import dagger.internal.Provider;

/* JADX INFO: compiled from: go/retraceme fd74bf9514dd0739b92d67a6d48a51eb10aaf8df760d83496b8dcfcf83e5f698 */
/* JADX INFO: loaded from: /home/starlight/Projects/Starlight Ports/.build-tmp/classes4.dex */
public final class OplusQSRootViewComponent_Factory implements Factory {
    public Provider addTileControllerProvider;
    public Provider cellAreaProvider;
    public DoubleCheck dragComponentProvider;
    public Provider dragHelperProvider;
    public Provider editModeSwitchAnimationManagerProvider;
    public Provider editTopControllerProvider;
    public Provider fakeStatusContainerControllerProvider;
    public DoubleCheck logBufferProvider;
    public Provider panelContainerProvider;
    public Provider privacyViewProvider;
    public Provider qsBottomComponentProvider;
    public Provider qsEmptyListTipComponentProvider;
    public Provider qsLeftBTilesComponentProvider;
    public Provider qsMoreButtonPopupWindowControllerProvider;
    public Provider qsOtherTilesComponentProvider;
    public Provider qsPanelAnimatorManagerProvider;
    public Provider qsPersonalComponentProvider;
    public Provider qsPrivacyComponentProvider;
    public Provider qsQuickEntranceComponentProvider;
    public Provider qsSecurityProvider;
    public Provider statusBarContainerProvider;
    public Provider statusBarHeaderProvider;
    public InstanceFactory stubProvider;
    public InstanceFactory viewProvider;

    /* JADX INFO: renamed from: get, reason: merged with bridge method [inline-methods] */
    public OplusQSRootViewComponent m117get() {
        return new OplusQSRootViewComponent((OplusQSRootView) this.viewProvider.get(), (OplusQSPersonalComponent) this.qsPersonalComponentProvider.get(), (OplusQSPrivacyContainerView) this.privacyViewProvider.get(), (OplusQSLeftBTilesComponent) this.qsLeftBTilesComponentProvider.get(), (OplusQSTilesComponent) this.qsOtherTilesComponentProvider.get(), (QSEmptyListTipComponent) this.qsEmptyListTipComponentProvider.get(), (ViewGroup) this.statusBarContainerProvider.get(), (ConstraintLayout) this.panelContainerProvider.get(), (OplusSeparateQSPlugin.IStub) this.stubProvider.get(), (QSElement) this.statusBarHeaderProvider.get(), (SeparateQSFakeStatusController) this.fakeStatusContainerControllerProvider.get(), (OplusQSQuickEntranceComponent) this.qsQuickEntranceComponentProvider.get(), (OplusQSPrivacyComponent) this.qsPrivacyComponentProvider.get(), (OplusQSBottomComponent) this.qsBottomComponentProvider.get(), (EditTopComponent) this.editTopControllerProvider.get(), (AddTileComponent) this.addTileControllerProvider.get(), (QSPanelAnimatorManager) this.qsPanelAnimatorManagerProvider.get(), (QSSecurity) this.qsSecurityProvider.get(), (EditModeSwitchAnimationManager) this.editModeSwitchAnimationManagerProvider.get(), (CellAreaProvider) this.cellAreaProvider.get(), (OplusQSDragComponent) this.dragComponentProvider.get(), (OplusQSMoreButtonPopupWindowController) this.qsMoreButtonPopupWindowControllerProvider.get(), (RecyclerViewDragHelper) this.dragHelperProvider.get(), (LogBuffer) this.logBufferProvider.get());
    }
}
