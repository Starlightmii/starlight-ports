package com.oplusos.systemui.common.view;

import android.view.View;
import kotlin.jvm.functions.Function0;
import kotlin.jvm.internal.Intrinsics;

/* JADX INFO: compiled from: go/retraceme fd74bf9514dd0739b92d67a6d48a51eb10aaf8df760d83496b8dcfcf83e5f698 */
/* JADX INFO: loaded from: /home/starlight/Projects/Starlight Ports/.build-tmp/classes4.dex */
public final class ViewExtKt$preloadAndRelease$onTouchListener$1$delayTask$1 implements Runnable {
    public /* synthetic */ Function0 $preloadCancelAction;
    public /* synthetic */ View $this_preloadAndRelease;

    @Override // java.lang.Runnable
    public final void run() {
        Object tag = this.$this_preloadAndRelease.getTag(2131364527);
        if (Intrinsics.areEqual(tag instanceof Boolean ? (Boolean) tag : null, Boolean.TRUE)) {
            return;
        }
        this.$preloadCancelAction.invoke();
    }
}
