package com.oplus.systemui.statusbar.phone.dynamic.pipeline.data.privacyspeaker;

import com.android.systemui.util.settings.GlobalSettings;
import java.lang.invoke.VarHandle;
import kotlin.Unit;
import kotlin.coroutines.Continuation;
import kotlin.coroutines.intrinsics.IntrinsicsKt__IntrinsicsKt;
import kotlinx.coroutines.flow.Flow;
import kotlinx.coroutines.flow.FlowCollector;

/* JADX INFO: compiled from: go/retraceme fd74bf9514dd0739b92d67a6d48a51eb10aaf8df760d83496b8dcfcf83e5f698 */
/* JADX INFO: loaded from: /home/starlight/Projects/Starlight Ports/.build-tmp/classes4.dex */
public final class PrivacySpeakerRepository$special$$inlined$map$1 implements Flow {
    public /* synthetic */ GlobalSettings $globalSettings$inlined;
    public /* synthetic */ Flow $this_unsafeTransform$inlined;

    public final Object collect(FlowCollector flowCollector, Continuation continuation) {
        Flow flow = this.$this_unsafeTransform$inlined;
        GlobalSettings globalSettings = this.$globalSettings$inlined;
        2 r1 = new 2();
        r1.$this_unsafeFlow = flowCollector;
        r1.$globalSettings$inlined = globalSettings;
        VarHandle.storeStoreFence();
        Object objCollect = flow.collect(r1, continuation);
        return objCollect == IntrinsicsKt__IntrinsicsKt.getCOROUTINE_SUSPENDED() ? objCollect : Unit.INSTANCE;
    }
}
