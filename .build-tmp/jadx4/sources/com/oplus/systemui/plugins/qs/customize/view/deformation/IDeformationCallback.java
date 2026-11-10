package com.oplus.systemui.plugins.qs.customize.view.deformation;

import com.oplusos.systemui.common.model.SpanSize;
import java.util.List;

/* JADX INFO: compiled from: go/retraceme fd74bf9514dd0739b92d67a6d48a51eb10aaf8df760d83496b8dcfcf83e5f698 */
/* JADX INFO: loaded from: /home/starlight/Projects/Starlight Ports/.build-tmp/classes4.dex */
public interface IDeformationCallback extends IDeformStateChanged {
    List<SpanSize> getActualSupportSize(List<SpanSize> list);

    void onSizeUpdated(SpanSize spanSize);
}
