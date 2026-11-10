package com.oplus.systemui.plugins.qs.customize.view.animation.deform;

import android.view.View;
import com.oplusos.systemui.common.model.SpanSize;

/* JADX INFO: compiled from: go/retraceme fd74bf9514dd0739b92d67a6d48a51eb10aaf8df760d83496b8dcfcf83e5f698 */
/* JADX INFO: loaded from: /home/starlight/Projects/Starlight Ports/.build-tmp/classes4.dex */
public interface IDragResizeHelperCallback {
    View getInnerViewAttachedData(SpanSize spanSize);

    Integer getInnerViewLeft(SpanSize spanSize);

    void recycle(View view);

    void setDeforming(boolean z);

    void updateInnerView(View view, SpanSize spanSize);
}
