package com.oplus.systemui.qs.base.widget.recyclerview;

import android.util.Log;
import androidx.recyclerview.widget.RecyclerView;
import com.oplusos.systemui.common.util.LogUtil;
import java.lang.invoke.VarHandle;

/* JADX INFO: compiled from: go/retraceme fd74bf9514dd0739b92d67a6d48a51eb10aaf8df760d83496b8dcfcf83e5f698 */
/* JADX INFO: loaded from: /home/starlight/Projects/Starlight Ports/.build-tmp/classes4.dex */
public final class OplusPagerStaggerSnapHelper$scrollListener$1 extends RecyclerView.OnScrollListener {
    public boolean scrolled;
    public /* synthetic */ OplusPagerStaggerSnapHelper this$0;

    public final boolean getScrolled() {
        return this.scrolled;
    }

    public final void onScrollStateChanged(RecyclerView recyclerView, int i) {
        OplusPagerStaggerSnapHelper oplusPagerStaggerSnapHelper = this.this$0;
        super.onScrollStateChanged(recyclerView, i);
        if (i == 1) {
            oplusPagerStaggerSnapHelper.getOffsetXSpringAnimation().cancel();
            oplusPagerStaggerSnapHelper.getOffsetYSpringAnimation().cancel();
            return;
        }
        if (i == 2) {
            this.scrolled = true;
            return;
        }
        if (i == 0 && this.scrolled) {
            if (LogUtil.getNormalDebug() && LogUtil.isTagValid(LogUtil.TAG_QS)) {
                Log.d("SystemUi--QuickSettings", "OplusPagerStaggerSnapHelper-->[onScrollStateChanged], post scrollToCurrPageCenter() ");
            }
            onScrollStateChanged.2 r5 = new onScrollStateChanged.2();
            r5.$recyclerView = recyclerView;
            r5.this$0 = this;
            r5.this$1 = oplusPagerStaggerSnapHelper;
            VarHandle.storeStoreFence();
            recyclerView.post(r5);
        }
    }

    public final void onScrolled(RecyclerView recyclerView, int i, int i2) {
        if (i == 0 && i2 == 0) {
            return;
        }
        this.scrolled = true;
    }

    public final void setScrolled(boolean z) {
        this.scrolled = z;
    }
}
