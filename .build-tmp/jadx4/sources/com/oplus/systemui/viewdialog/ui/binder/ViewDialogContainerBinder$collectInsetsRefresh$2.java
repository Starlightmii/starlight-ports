package com.oplus.systemui.viewdialog.ui.binder;

import android.view.ViewGroup;
import kotlin.Unit;
import kotlin.coroutines.Continuation;
import kotlinx.coroutines.flow.FlowCollector;

/* JADX INFO: compiled from: go/retraceme fd74bf9514dd0739b92d67a6d48a51eb10aaf8df760d83496b8dcfcf83e5f698 */
/* JADX INFO: loaded from: /home/starlight/Projects/Starlight Ports/.build-tmp/classes4.dex */
public final class ViewDialogContainerBinder$collectInsetsRefresh$2 implements FlowCollector {
    public /* synthetic */ ViewGroup $container;

    public final Object emit(Object obj, Continuation continuation) {
        this.$container.setOnApplyWindowInsetsListener(((Boolean) obj).booleanValue() ? ViewDialogContainerBinder.windowInsetsListener : ViewDialogContainerBinder.windowInsetsDisableListener);
        this.$container.requestApplyInsets();
        return Unit.INSTANCE;
    }
}
