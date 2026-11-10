package com.oplus.systemui.statusbar.phone.dynamic.pipeline.ui.viewmodel;

import android.content.Context;
import com.oplus.systemui.statusbar.phone.dynamic.pipeline.data.dynamicevent.DynamicEventRepository;
import java.lang.invoke.VarHandle;
import kotlinx.coroutines.CoroutineScope;
import kotlinx.coroutines.flow.FlowKt;
import kotlinx.coroutines.flow.SharingStarted;
import kotlinx.coroutines.flow.StateFlow;

/* JADX INFO: compiled from: go/retraceme fd74bf9514dd0739b92d67a6d48a51eb10aaf8df760d83496b8dcfcf83e5f698 */
/* JADX INFO: loaded from: /home/starlight/Projects/Starlight Ports/.build-tmp/classes4.dex */
public final class DynamicEventViewModel implements DynamicIconViewModel {
    private final StateFlow iconModel;
    private final String slot;

    public DynamicEventViewModel(Context context, CoroutineScope coroutineScope, DynamicEventRepository dynamicEventRepository) {
        this.slot = context.getResources().getString(2131956602);
        StateFlow dynamicEventFlow = dynamicEventRepository.getDynamicEventFlow();
        DynamicEventViewModel$special$$inlined$map$1 dynamicEventViewModel$special$$inlined$map$1 = new DynamicEventViewModel$special$$inlined$map$1();
        dynamicEventViewModel$special$$inlined$map$1.$this_unsafeTransform$inlined = dynamicEventFlow;
        dynamicEventViewModel$special$$inlined$map$1.this$0 = this;
        VarHandle.storeStoreFence();
        this.iconModel = FlowKt.stateIn(FlowKt.distinctUntilChanged(dynamicEventViewModel$special$$inlined$map$1), coroutineScope, SharingStarted.Companion.WhileSubscribed$default(SharingStarted.Companion, 0L, 3), (Object) null);
    }

    @Override // com.oplus.systemui.statusbar.phone.dynamic.pipeline.ui.viewmodel.DynamicIconViewModel
    public StateFlow getIconModel() {
        return this.iconModel;
    }
}
