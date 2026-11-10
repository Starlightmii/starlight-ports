package com.oplus.systemui.qs.media;

import android.view.View;
import com.oplus.systemui.qs.base.util.QsFastClickUtil;

/* JADX INFO: compiled from: go/retraceme fd74bf9514dd0739b92d67a6d48a51eb10aaf8df760d83496b8dcfcf83e5f698 */
/* JADX INFO: loaded from: /home/starlight/Projects/Starlight Ports/.build-tmp/classes4.dex */
public final class OplusQsBaseMediaPanelView$handleDefaultClickListener$1 implements View.OnClickListener {
    public /* synthetic */ OplusQsBaseMediaPanelView this$0;

    @Override // android.view.View.OnClickListener
    public final void onClick(View view) {
        if (QsFastClickUtil.isFastClick(QsFastClickUtil.TYPE_QS_SHOW_DIALOG)) {
            return;
        }
        OplusQsBaseMediaPanelView.access$handleClickMediaOutputBtn(this.this$0, null);
    }
}
