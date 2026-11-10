package com.oplus.systemui.viewdialog.ui.binder;

import com.oplus.systemui.viewdialog.ui.viewmodel.ViewDialogContentViewModel;
import com.oplusos.systemui.common.viewdialog.model.ViewDialogAnimationManager;
import kotlin.Unit;
import kotlin.coroutines.Continuation;
import kotlinx.coroutines.flow.FlowCollector;

/* JADX INFO: compiled from: go/retraceme fd74bf9514dd0739b92d67a6d48a51eb10aaf8df760d83496b8dcfcf83e5f698 */
/* JADX INFO: loaded from: /home/starlight/Projects/Starlight Ports/.build-tmp/classes4.dex */
public final class ViewDialogContentBinder$collectDismissDialogEvent$2 implements FlowCollector {
    public /* synthetic */ ViewDialogAnimationManager $animationManager;
    public /* synthetic */ ViewDialogContentViewModel $viewModel;

    public final Object emit(Object obj, Continuation continuation) {
        ViewDialogContentBinder viewDialogContentBinder = ViewDialogContentBinder.INSTANCE;
        ViewDialogAnimationManager viewDialogAnimationManager = this.$animationManager;
        ViewDialogContentViewModel viewDialogContentViewModel = this.$viewModel;
        viewDialogContentBinder.getClass();
        viewDialogAnimationManager.hideDetailToView(viewDialogContentViewModel.getOriginView(), (String) obj, viewDialogContentViewModel.getHideContentViewId());
        return Unit.INSTANCE;
    }
}
