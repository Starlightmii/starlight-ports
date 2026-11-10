package com.oplus.systemui.plugins.qs.customize.view.animation.drag;

import androidx.recyclerview.widget.RecyclerView;
import java.util.List;

/* JADX INFO: compiled from: go/retraceme fd74bf9514dd0739b92d67a6d48a51eb10aaf8df760d83496b8dcfcf83e5f698 */
/* JADX INFO: loaded from: /home/starlight/Projects/Starlight Ports/.build-tmp/classes4.dex */
public interface ISwapOrMove {
    boolean onAfterSwap(Object obj);

    void onAnimateAdd(Object obj, RecyclerView.ViewHolder viewHolder);

    void onBeforeSwap(List<Object> list);
}
