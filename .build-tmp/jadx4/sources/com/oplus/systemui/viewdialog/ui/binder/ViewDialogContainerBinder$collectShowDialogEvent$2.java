package com.oplus.systemui.viewdialog.ui.binder;

import android.support.v4.content.FileProvider$;
import android.view.View;
import android.view.ViewGroup;
import android.widget.FrameLayout;
import com.oplus.systemui.viewdialog.ui.viewmodel.ViewDialogContainerViewModel;
import com.oplus.systemui.viewdialog.ui.viewmodel.ViewDialogContentViewModel;
import com.oplusos.systemui.common.util.LogUtil;
import com.oplusos.systemui.common.viewdialog.model.ViewDialogAdapter;
import kotlin.Unit;
import kotlin.coroutines.Continuation;
import kotlin.coroutines.jvm.internal.Boxing;
import kotlinx.coroutines.flow.FlowCollector;

/* JADX INFO: compiled from: go/retraceme fd74bf9514dd0739b92d67a6d48a51eb10aaf8df760d83496b8dcfcf83e5f698 */
/* JADX INFO: loaded from: /home/starlight/Projects/Starlight Ports/.build-tmp/classes4.dex */
public final class ViewDialogContainerBinder$collectShowDialogEvent$2 implements FlowCollector {
    public /* synthetic */ ViewGroup $container;
    public /* synthetic */ ViewDialogContentViewModel.Factory $contentViewModelFactory;
    public /* synthetic */ ViewDialogContainerViewModel $viewModel;

    public final Object emit(Object obj, Continuation continuation) {
        ViewDialogAdapter viewDialogAdapter = (ViewDialogAdapter) obj;
        ViewDialogContainerViewModel viewDialogContainerViewModel = this.$viewModel;
        if (this.$container.getChildCount() != 0) {
            LogUtil.i$default(LogUtil.TAG_QS, "ViewDialogContainerBinder", FileProvider$.ExternalSyntheticOutline0.m("collectShowDialogEvent: view dialog showing, ignore ", viewDialogAdapter.getViewTag()), null, 8);
            viewDialogAdapter.onIntercept();
            return Unit.INSTANCE;
        }
        if (!viewDialogContainerViewModel.isNotificationShadeExpanded()) {
            LogUtil.i$default(LogUtil.TAG_QS, "ViewDialogContainerBinder", "collectShowDialogEvent: notificationShadeExpanded is false", null, 8);
            viewDialogAdapter.onIntercept();
            return Unit.INSTANCE;
        }
        if (viewDialogContainerViewModel.isStartedGoingToSleep()) {
            LogUtil.i$default(LogUtil.TAG_QS, "ViewDialogContainerBinder", "collectShowDialogEvent: isStartedGoingToSleep", null, 8);
            viewDialogAdapter.onIntercept();
            return Unit.INSTANCE;
        }
        View viewCreateDialogView = viewDialogAdapter.createDialogView(this.$container.getContext());
        if (viewCreateDialogView == null) {
            LogUtil.i$default(LogUtil.TAG_QS, "ViewDialogContainerBinder", "collectShowDialogEvent: dialogContent is null", null, 8);
            viewDialogAdapter.onIntercept();
            return Unit.INSTANCE;
        }
        ViewDialogContainerBinder._windowInsetsEnabled.setValue(Boxing.boxBoolean(viewDialogAdapter.windowInsetsEnabled()));
        this.$container.addView(viewCreateDialogView, new FrameLayout.LayoutParams(-1, -1, 17));
        this.$container.setVisibility(0);
        ViewDialogContentBinder.INSTANCE.bind(viewCreateDialogView, viewDialogAdapter, viewDialogContainerViewModel, this.$contentViewModelFactory);
        ViewDialogContainerBinder viewDialogContainerBinder = ViewDialogContainerBinder.INSTANCE;
        ViewGroup viewGroup = this.$container;
        viewDialogContainerBinder.getClass();
        LogUtil.i$default(LogUtil.TAG_QS, "ViewDialogContainerBinder", FileProvider$.ExternalSyntheticOutline0.m("onDialogShow: tag=", viewDialogAdapter.getViewTag()), null, 8);
        viewGroup.setOnClickListener(viewDialogContainerViewModel.getOutsideTouchListener());
        viewGroup.setClickable(true);
        viewGroup.setOnTouchListener(viewDialogContainerViewModel.getTouchListener());
        viewDialogContainerViewModel.setDialogShowing(viewDialogAdapter.needInterceptShadeTouchOnShow());
        return Unit.INSTANCE;
    }
}
