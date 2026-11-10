package com.oplus.systemui.statusbar.phone.dynamic.pipeline.ui.viewmodel;

import android.content.Context;
import com.oplus.systemui.statusbar.phone.dynamic.pipeline.data.privacyspeaker.PrivacySpeakerRepository;
import com.oplus.systemui.statusbar.phone.dynamic.pipeline.ui.model.DynamicIconModel;
import com.oplus.systemui.statusbar.phone.dynamic.pipeline.ui.model.IconSource;
import java.lang.invoke.VarHandle;
import kotlinx.coroutines.CoroutineScope;
import kotlinx.coroutines.flow.FlowKt;
import kotlinx.coroutines.flow.SharingStarted;
import kotlinx.coroutines.flow.StateFlow;

/* JADX INFO: compiled from: go/retraceme fd74bf9514dd0739b92d67a6d48a51eb10aaf8df760d83496b8dcfcf83e5f698 */
/* JADX INFO: loaded from: /home/starlight/Projects/Starlight Ports/.build-tmp/classes4.dex */
public final class PrivacySpeakerDynamicViewModel implements DynamicIconViewModel {
    public static final Companion Companion = new Companion();
    private static final String TAG = "PrivacySpeakerDynamicViewModel";
    private final DynamicIconModel defaultIconModel;
    private final StateFlow iconModel;
    public long lastTimeStamp;

    /* JADX INFO: compiled from: go/retraceme fd74bf9514dd0739b92d67a6d48a51eb10aaf8df760d83496b8dcfcf83e5f698 */
    public final class Companion {
    }

    public PrivacySpeakerDynamicViewModel(Context context, CoroutineScope coroutineScope, PrivacySpeakerRepository privacySpeakerRepository) {
        this.defaultIconModel = new DynamicIconModel(1, 20, context.getResources().getString(2131955075), 3000L, new IconSource.ResourceIconSource(2131236740, false), context.getResources().getString(2131956627), context.getResources().getString(2131955075));
        StateFlow callPrivacyMode = privacySpeakerRepository.getCallPrivacyMode();
        PrivacySpeakerDynamicViewModel$special$$inlined$map$1 privacySpeakerDynamicViewModel$special$$inlined$map$1 = new PrivacySpeakerDynamicViewModel$special$$inlined$map$1();
        privacySpeakerDynamicViewModel$special$$inlined$map$1.$this_unsafeTransform$inlined = callPrivacyMode;
        privacySpeakerDynamicViewModel$special$$inlined$map$1.this$0 = this;
        VarHandle.storeStoreFence();
        this.iconModel = FlowKt.stateIn(FlowKt.distinctUntilChanged(privacySpeakerDynamicViewModel$special$$inlined$map$1), coroutineScope, SharingStarted.Companion.WhileSubscribed$default(SharingStarted.Companion, 0L, 3), (Object) null);
    }

    @Override // com.oplus.systemui.statusbar.phone.dynamic.pipeline.ui.viewmodel.DynamicIconViewModel
    public StateFlow getIconModel() {
        return this.iconModel;
    }
}
