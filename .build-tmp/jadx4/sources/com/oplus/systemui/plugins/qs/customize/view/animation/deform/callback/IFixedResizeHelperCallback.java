package com.oplus.systemui.plugins.qs.customize.view.animation.deform.callback;

import android.view.View;
import com.oplusos.systemui.common.model.SpanSize;

/* JADX INFO: compiled from: go/retraceme fd74bf9514dd0739b92d67a6d48a51eb10aaf8df760d83496b8dcfcf83e5f698 */
/* JADX INFO: loaded from: /home/starlight/Projects/Starlight Ports/.build-tmp/classes4.dex */
public interface IFixedResizeHelperCallback {
    View getInnerViewAttachedData(SpanSize spanSize);

    void recycle(View view);

    void setDeforming(boolean z);

    void setRemoveIconTranslationX(float f);
}
