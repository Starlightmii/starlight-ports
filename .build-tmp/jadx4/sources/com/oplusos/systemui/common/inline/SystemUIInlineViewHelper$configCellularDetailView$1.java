package com.oplusos.systemui.common.inline;

import android.content.ComponentName;
import java.util.function.Supplier;

/* JADX INFO: compiled from: go/retraceme fd74bf9514dd0739b92d67a6d48a51eb10aaf8df760d83496b8dcfcf83e5f698 */
/* JADX INFO: loaded from: /home/starlight/Projects/Starlight Ports/.build-tmp/classes4.dex */
public final class SystemUIInlineViewHelper$configCellularDetailView$1 implements Supplier {
    public static final SystemUIInlineViewHelper$configCellularDetailView$1 INSTANCE = new SystemUIInlineViewHelper$configCellularDetailView$1();

    @Override // java.util.function.Supplier
    public final ComponentName get() {
        return SystemUIInlineViewHelper.COMPONENT_CELLULAR_DETAIL;
    }
}
