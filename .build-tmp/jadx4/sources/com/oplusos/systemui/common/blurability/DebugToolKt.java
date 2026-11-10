package com.oplusos.systemui.common.blurability;

import android.view.View;
import com.oplusos.systemui.common.util.ReflectionUtils;

/* JADX INFO: compiled from: go/retraceme fd74bf9514dd0739b92d67a6d48a51eb10aaf8df760d83496b8dcfcf83e5f698 */
/* JADX INFO: loaded from: /home/starlight/Projects/Starlight Ports/.build-tmp/classes4.dex */
public abstract class DebugToolKt {
    public static final String getName(View view, boolean z) {
        if (!DebugTool.INSTANCE.getOpenDebug() && !z) {
            return view.getClass().getSimpleName() + '@' + Integer.toHexString(System.identityHashCode(view));
        }
        StringBuilder sb = new StringBuilder();
        ReflectionUtils.methodInvoke(view, View.class, "appendId", new Class[]{StringBuilder.class}, sb);
        return ((Object) sb) + '@' + Integer.toHexString(System.identityHashCode(view));
    }
}
