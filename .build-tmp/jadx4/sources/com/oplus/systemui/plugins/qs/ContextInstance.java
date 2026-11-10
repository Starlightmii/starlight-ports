package com.oplus.systemui.plugins.qs;

import android.content.Context;
import kotlin.jvm.internal.Intrinsics;

/* JADX INFO: compiled from: go/retraceme fd74bf9514dd0739b92d67a6d48a51eb10aaf8df760d83496b8dcfcf83e5f698 */
/* JADX INFO: loaded from: /home/starlight/Projects/Starlight Ports/.build-tmp/classes4.dex */
public final class ContextInstance {
    private final Context pluginContext;
    private final Context sysUiContext;

    public ContextInstance(Context context, Context context2) {
        this.pluginContext = context;
        this.sysUiContext = context2;
    }

    public final Context component1() {
        return this.pluginContext;
    }

    public final Context component2() {
        return this.sysUiContext;
    }

    public final ContextInstance copy(Context context, Context context2) {
        return new ContextInstance(context, context2);
    }

    public boolean equals(Object obj) {
        if (this == obj) {
            return true;
        }
        if (!(obj instanceof ContextInstance)) {
            return false;
        }
        ContextInstance contextInstance = (ContextInstance) obj;
        return Intrinsics.areEqual(this.pluginContext, contextInstance.pluginContext) && Intrinsics.areEqual(this.sysUiContext, contextInstance.sysUiContext);
    }

    public final Context getPluginContext() {
        return this.pluginContext;
    }

    public final Context getSysUiContext() {
        return this.sysUiContext;
    }

    public final int hashCode() {
        return this.sysUiContext.hashCode() + (this.pluginContext.hashCode() * 31);
    }

    public String toString() {
        return "ContextInstance(pluginContext=" + this.pluginContext + ", sysUiContext=" + this.sysUiContext + ")";
    }
}
