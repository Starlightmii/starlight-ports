package com.oplus.systemui.statusbar.pipeline.mobile.domain.interactor;

import com.android.systemui.statusbar.pipeline.mobile.domain.interactor.OplusMobileIconsInteractorEx;
import java.lang.invoke.VarHandle;
import kotlinx.coroutines.CoroutineScope;
import kotlinx.coroutines.flow.FlowKt;
import kotlinx.coroutines.flow.SharingStarted;
import kotlinx.coroutines.flow.StateFlow;

/* JADX INFO: compiled from: go/retraceme fd74bf9514dd0739b92d67a6d48a51eb10aaf8df760d83496b8dcfcf83e5f698 */
/* JADX INFO: loaded from: /home/starlight/Projects/Starlight Ports/.build-tmp/classes4.dex */
public final class OplusMobileIconsInteractorExImpl extends OplusMobileIconsInteractorEx {
    private final StateFlow isStackableMode;
    private final CoroutineScope scope;

    public OplusMobileIconsInteractorExImpl(OplusMobileIconsInteractor oplusMobileIconsInteractor, CoroutineScope coroutineScope) {
        this.scope = coroutineScope;
        StateFlow signalIconDisplayMode = oplusMobileIconsInteractor.getSignalIconDisplayMode();
        OplusMobileIconsInteractorExImpl$special$$inlined$map$1 oplusMobileIconsInteractorExImpl$special$$inlined$map$1 = new OplusMobileIconsInteractorExImpl$special$$inlined$map$1();
        oplusMobileIconsInteractorExImpl$special$$inlined$map$1.$this_unsafeTransform$inlined = signalIconDisplayMode;
        VarHandle.storeStoreFence();
        this.isStackableMode = FlowKt.stateIn(oplusMobileIconsInteractorExImpl$special$$inlined$map$1, coroutineScope, SharingStarted.Companion.WhileSubscribed$default(SharingStarted.Companion, 0L, 3), Boolean.TRUE);
    }

    public StateFlow isStackableMode() {
        return this.isStackableMode;
    }
}
