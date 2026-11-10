package com.oplus.systemui.qs.base.seek;

import android.view.View;
import com.oplusos.systemui.common.util.LogUtil;

/* JADX INFO: compiled from: go/retraceme fd74bf9514dd0739b92d67a6d48a51eb10aaf8df760d83496b8dcfcf83e5f698 */
/* JADX INFO: loaded from: /home/starlight/Projects/Starlight Ports/.build-tmp/classes4.dex */
public final class OplusQsToggleSliderTouchExt$internalLongClickProxy$1 implements View.OnLongClickListener {
    public /* synthetic */ OplusQsToggleSliderTouchExt this$0;

    @Override // android.view.View.OnLongClickListener
    public final boolean onLongClick(View view) {
        LogUtil.i$default(LogUtil.TAG_QS, "OplusQsToggleSliderTouchExt", "OnLongClickListener onLongClick", null, 8);
        View.OnLongClickListener onLongClickListener = this.this$0.longClickListener;
        return onLongClickListener != null && onLongClickListener.onLongClick(view);
    }
}
