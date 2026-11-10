package com.oplus.systemui.statusbar.shared.lifecycle;

import com.android.systemui.statusbar.core.StatusBarInitializer;
import dagger.Lazy;
import java.util.Set;
import kotlin.collections.SetsKt__SetsJVMKt;

/* JADX INFO: compiled from: go/retraceme fd74bf9514dd0739b92d67a6d48a51eb10aaf8df760d83496b8dcfcf83e5f698 */
/* JADX INFO: loaded from: /home/starlight/Projects/Starlight Ports/.build-tmp/classes4.dex */
public final class OplusStatusBarViewLifecycleListenerModule {
    public static final OplusStatusBarViewLifecycleListenerModule INSTANCE = new OplusStatusBarViewLifecycleListenerModule();

    public final Set<StatusBarInitializer.StatusBarViewLifecycleListener> repositoryAsLifecycleListener(Lazy lazy) {
        return SetsKt__SetsJVMKt.setOf(lazy.get());
    }
}
