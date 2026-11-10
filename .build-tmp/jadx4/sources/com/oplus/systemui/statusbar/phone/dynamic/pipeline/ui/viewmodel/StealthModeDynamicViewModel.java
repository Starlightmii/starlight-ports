package com.oplus.systemui.statusbar.phone.dynamic.pipeline.ui.viewmodel;

import android.content.Context;
import com.oplus.systemui.statusbar.phone.dynamic.pipeline.data.stealthmode.StealthModeRepository;
import com.oplus.systemui.statusbar.phone.dynamic.pipeline.ui.model.DynamicIconModel;
import com.oplus.systemui.statusbar.phone.dynamic.pipeline.ui.model.IconSource;
import java.lang.invoke.VarHandle;
import kotlinx.coroutines.CoroutineScope;
import kotlinx.coroutines.flow.FlowKt;
import kotlinx.coroutines.flow.SharingStarted;
import kotlinx.coroutines.flow.StateFlow;

/* JADX INFO: compiled from: go/retraceme fd74bf9514dd0739b92d67a6d48a51eb10aaf8df760d83496b8dcfcf83e5f698 */
/* JADX INFO: loaded from: /home/starlight/Projects/Starlight Ports/.build-tmp/classes4.dex */
public final class StealthModeDynamicViewModel implements DynamicIconViewModel {
    public static final Companion Companion = new Companion();
    private static final String TAG = "StealthModeDynamicViewModel";
    private final DynamicIconModel defaultIconModel;
    private final StateFlow iconModel;
    private final String slot;
    private final String text;

    /* JADX INFO: compiled from: go/retraceme fd74bf9514dd0739b92d67a6d48a51eb10aaf8df760d83496b8dcfcf83e5f698 */
    public final class Companion {
    }

    public StealthModeDynamicViewModel(Context context, CoroutineScope coroutineScope, StealthModeRepository stealthModeRepository) {
        String string = context.getResources().getString(2131956640);
        this.slot = string;
        String string2 = context.getResources().getString(2131955300);
        this.text = string2;
        this.defaultIconModel = new DynamicIconModel(3, 30, string2, -1L, new IconSource.ResourceIconSource(2131236931, false), string, string2);
        StateFlow stealthModeState = stealthModeRepository.getStealthModeState();
        StealthModeDynamicViewModel$special$$inlined$map$1 stealthModeDynamicViewModel$special$$inlined$map$1 = new StealthModeDynamicViewModel$special$$inlined$map$1();
        stealthModeDynamicViewModel$special$$inlined$map$1.$this_unsafeTransform$inlined = stealthModeState;
        stealthModeDynamicViewModel$special$$inlined$map$1.this$0 = this;
        VarHandle.storeStoreFence();
        this.iconModel = FlowKt.stateIn(FlowKt.distinctUntilChanged(stealthModeDynamicViewModel$special$$inlined$map$1), coroutineScope, SharingStarted.Companion.WhileSubscribed$default(SharingStarted.Companion, 0L, 3), (Object) null);
    }

    @Override // com.oplus.systemui.statusbar.phone.dynamic.pipeline.ui.viewmodel.DynamicIconViewModel
    public StateFlow getIconModel() {
        return this.iconModel;
    }
}
