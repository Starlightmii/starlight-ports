package com.oplus.systemui.plugins.qs.customize.view.animation.drag.data;

import com.oplusos.systemui.common.util.LogUtil;
import kotlin.properties.ObservableProperty;
import kotlin.reflect.KProperty;

/* JADX INFO: compiled from: go/retraceme fd74bf9514dd0739b92d67a6d48a51eb10aaf8df760d83496b8dcfcf83e5f698 */
/* JADX INFO: loaded from: /home/starlight/Projects/Starlight Ports/.build-tmp/classes4.dex */
public final class CrossDragDataDelegate$special$$inlined$observable$1 extends ObservableProperty {
    public final void afterChange(KProperty kProperty, Object obj, Object obj2) {
        LogUtil.i$default(LogUtil.TAG_QS, "CrossDragDataDelegate", "update dragData: " + ((CrossRegionDragData) obj2), null, 8);
    }
}
