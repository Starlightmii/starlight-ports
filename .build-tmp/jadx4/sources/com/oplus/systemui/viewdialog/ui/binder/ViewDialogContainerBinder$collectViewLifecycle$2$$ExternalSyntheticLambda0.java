package com.oplus.systemui.viewdialog.ui.binder;

import android.view.ViewGroup;
import com.oplusos.systemui.common.util.LogUtil;
import kotlin.Unit;
import kotlin.jvm.functions.Function0;

/* JADX INFO: compiled from: go/retraceme fd74bf9514dd0739b92d67a6d48a51eb10aaf8df760d83496b8dcfcf83e5f698 */
/* JADX INFO: loaded from: /home/starlight/Projects/Starlight Ports/.build-tmp/classes4.dex */
public final /* synthetic */ class ViewDialogContainerBinder$collectViewLifecycle$2$$ExternalSyntheticLambda0 implements Function0 {
    public /* synthetic */ ViewGroup f$0;

    public final Object invoke() {
        ViewGroup viewGroup = this.f$0;
        ViewDialogContainerBinder.INSTANCE.getClass();
        LogUtil.i$default(LogUtil.TAG_QS, "ViewDialogContainerBinder", "onDestroy: ", null, 8);
        viewGroup.setOnApplyWindowInsetsListener(null);
        return Unit.INSTANCE;
    }
}
