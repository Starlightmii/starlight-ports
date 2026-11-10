package com.oplus.systemui.plugins.qs;

import android.view.View;
import com.oplusos.systemui.common.util.LogUtil;
import kotlin.jvm.internal.Intrinsics;

/* JADX INFO: compiled from: go/retraceme fd74bf9514dd0739b92d67a6d48a51eb10aaf8df760d83496b8dcfcf83e5f698 */
/* JADX INFO: loaded from: /home/starlight/Projects/Starlight Ports/.build-tmp/classes4.dex */
public final class OplusSeparateQSPluginImpl$onCreateQSPanelView$1$2 implements View.OnAttachStateChangeListener {
    public /* synthetic */ OplusSeparateQSPluginImpl this$0;

    @Override // android.view.View.OnAttachStateChangeListener
    public final void onViewAttachedToWindow(View view) {
    }

    @Override // android.view.View.OnAttachStateChangeListener
    public final void onViewDetachedFromWindow(View view) {
        if (Intrinsics.areEqual(this.this$0.qsPanelView, view)) {
            this.this$0.qsPanelView = null;
            this.this$0.qsPanelViewController = null;
        }
        LogUtil.i$default(LogUtil.TAG_QS, "OplusSeparateQSPluginImpl", "qsPanelView detach", null, 8);
    }
}
