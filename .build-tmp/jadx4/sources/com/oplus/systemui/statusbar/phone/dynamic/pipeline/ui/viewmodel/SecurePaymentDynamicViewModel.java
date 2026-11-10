package com.oplus.systemui.statusbar.phone.dynamic.pipeline.ui.viewmodel;

import android.content.Context;
import com.oplus.systemui.statusbar.phone.dynamic.pipeline.data.securepayment.SecurePaymentRepository;
import java.lang.invoke.VarHandle;
import java.util.Map;
import kotlin.Pair;
import kotlin.collections.MapsKt__MapsKt;
import kotlinx.coroutines.CoroutineScope;
import kotlinx.coroutines.flow.FlowKt;
import kotlinx.coroutines.flow.SharingStarted;
import kotlinx.coroutines.flow.StateFlow;

/* JADX INFO: compiled from: go/retraceme fd74bf9514dd0739b92d67a6d48a51eb10aaf8df760d83496b8dcfcf83e5f698 */
/* JADX INFO: loaded from: /home/starlight/Projects/Starlight Ports/.build-tmp/classes4.dex */
public final class SecurePaymentDynamicViewModel implements DynamicIconViewModel {
    public static final Companion Companion = new Companion();
    private static final String TAG = "SecurePaymentDynamicViewModel";
    private final StateFlow iconModel;
    private final String slot;
    private final Map<Integer, String> texts;

    /* JADX INFO: compiled from: go/retraceme fd74bf9514dd0739b92d67a6d48a51eb10aaf8df760d83496b8dcfcf83e5f698 */
    public final class Companion {
    }

    public SecurePaymentDynamicViewModel(Context context, CoroutineScope coroutineScope, SecurePaymentRepository securePaymentRepository) {
        this.slot = context.getResources().getString(2131956635);
        this.texts = MapsKt__MapsKt.mapOf(new Pair[]{new Pair(1000, context.getResources().getString(2131955262)), new Pair(1001, context.getResources().getString(2131955261)), new Pair(1002, context.getResources().getString(2131955260)), new Pair(1003, context.getResources().getString(2131956545)), new Pair(1004, "")});
        StateFlow paymentCheckState = securePaymentRepository.getPaymentCheckState();
        SecurePaymentDynamicViewModel$special$$inlined$map$1 securePaymentDynamicViewModel$special$$inlined$map$1 = new SecurePaymentDynamicViewModel$special$$inlined$map$1();
        securePaymentDynamicViewModel$special$$inlined$map$1.$this_unsafeTransform$inlined = paymentCheckState;
        securePaymentDynamicViewModel$special$$inlined$map$1.this$0 = this;
        VarHandle.storeStoreFence();
        this.iconModel = FlowKt.stateIn(FlowKt.distinctUntilChanged(securePaymentDynamicViewModel$special$$inlined$map$1), coroutineScope, SharingStarted.Companion.WhileSubscribed$default(SharingStarted.Companion, 0L, 3), (Object) null);
    }

    @Override // com.oplus.systemui.statusbar.phone.dynamic.pipeline.ui.viewmodel.DynamicIconViewModel
    public StateFlow getIconModel() {
        return this.iconModel;
    }
}
