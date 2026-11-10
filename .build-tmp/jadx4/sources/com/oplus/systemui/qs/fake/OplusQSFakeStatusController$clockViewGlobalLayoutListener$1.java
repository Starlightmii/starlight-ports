package com.oplus.systemui.qs.fake;

import android.view.View;
import android.view.ViewTreeObserver;
import androidx.core.view.insets.Protection$;
import com.oplusos.systemui.common.util.LogUtil;

/* JADX INFO: compiled from: go/retraceme fd74bf9514dd0739b92d67a6d48a51eb10aaf8df760d83496b8dcfcf83e5f698 */
/* JADX INFO: loaded from: /home/starlight/Projects/Starlight Ports/.build-tmp/classes4.dex */
public final class OplusQSFakeStatusController$clockViewGlobalLayoutListener$1 implements ViewTreeObserver.OnGlobalLayoutListener {
    public /* synthetic */ OplusQSFakeStatusController this$0;

    @Override // android.view.ViewTreeObserver.OnGlobalLayoutListener
    public final void onGlobalLayout() {
        ViewTreeObserver viewTreeObserver;
        View clockView = this.this$0.oplusQSFooterViewController.getClockView();
        if (clockView != null && (viewTreeObserver = clockView.getViewTreeObserver()) != null) {
            viewTreeObserver.removeOnGlobalLayoutListener(this);
        }
        OplusQSFakeStatusController oplusQSFakeStatusController = this.this$0;
        View clockView2 = oplusQSFakeStatusController.oplusQSFooterViewController.getClockView();
        oplusQSFakeStatusController.clockBaseLine = clockView2 != null ? clockView2.getBaseline() : 0.0f;
        this.this$0.oplusQSFooterViewController.setClockBaseline((int) this.this$0.clockBaseLine);
        LogUtil.i$default(LogUtil.TAG_QS, OplusQSFakeStatusController.TAG, Protection$.ExternalSyntheticOutline0.m(this.this$0.clockBaseLine, "clockBaseLine:"), null, 8);
    }
}
