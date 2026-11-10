package com.oplus.systemui.viewdialog.ui.binder;

import android.view.ViewGroup;
import com.oplus.systemui.viewdialog.ui.viewmodel.ViewDialogContainerViewModel;
import com.oplusos.systemui.common.util.LogUtil;
import kotlin.Unit;
import kotlin.coroutines.Continuation;
import kotlinx.coroutines.flow.FlowCollector;

/* JADX INFO: compiled from: go/retraceme fd74bf9514dd0739b92d67a6d48a51eb10aaf8df760d83496b8dcfcf83e5f698 */
/* JADX INFO: loaded from: /home/starlight/Projects/Starlight Ports/.build-tmp/classes4.dex */
public final class ViewDialogContainerBinder$collectDismissDialogEvent$2 implements FlowCollector {
    public /* synthetic */ ViewGroup $container;
    public /* synthetic */ ViewDialogContainerViewModel $viewModel;

    public final Object emit(Object obj, Continuation continuation) {
        LogUtil.i$default(LogUtil.TAG_QS, "ViewDialogContainerBinder", "collectDismissDialogEvent: ", null, 8);
        if (this.$container.getChildCount() != 0) {
            this.$container.removeAllViews();
        }
        if (this.$container.getVisibility() != 8) {
            this.$container.setVisibility(8);
        }
        ViewDialogContainerBinder viewDialogContainerBinder = ViewDialogContainerBinder.INSTANCE;
        ViewGroup viewGroup = this.$container;
        ViewDialogContainerViewModel viewDialogContainerViewModel = this.$viewModel;
        viewDialogContainerBinder.getClass();
        LogUtil.i$default(LogUtil.TAG_QS, "ViewDialogContainerBinder", "onDialogDismiss: ", null, 8);
        viewGroup.setOnClickListener(null);
        viewGroup.setClickable(false);
        viewGroup.setOnTouchListener(null);
        viewDialogContainerViewModel.setDialogDismiss();
        return Unit.INSTANCE;
    }
}
