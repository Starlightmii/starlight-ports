package com.oplus.systemui.qs.base.seek;

import android.view.View;
import com.oplusos.systemui.common.util.LogUtil;

/* JADX INFO: compiled from: go/retraceme fd74bf9514dd0739b92d67a6d48a51eb10aaf8df760d83496b8dcfcf83e5f698 */
/* JADX INFO: loaded from: /home/starlight/Projects/Starlight Ports/.build-tmp/classes4.dex */
public final class OplusQsToggleSliderTouchExt$internalToggleClickProxy$1 implements View.OnClickListener, Runnable {
    public /* synthetic */ OplusQsToggleSliderTouchExt this$0;

    @Override // android.view.View.OnClickListener
    public final void onClick(View view) {
        View.OnClickListener onClickListener = this.this$0.toggleClickListener;
        if (onClickListener == null) {
            return;
        }
        LogUtil.i$default(LogUtil.TAG_QS, "OplusQsToggleSliderTouchExt", "toggleClickListener onClick isPressed=" + (view != null ? Boolean.valueOf(view.isPressed()) : null), null, 8);
        this.this$0.layout.playSoundEffect(0);
        onClickListener.onClick(view);
    }

    @Override // java.lang.Runnable
    public final void run() {
        onClick(this.this$0.toggleView);
    }
}
