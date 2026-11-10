package com.oplus.systemui.notification.headsup.windowframe;

import android.view.View;
import kotlin.Unit;
import kotlin.jvm.functions.Function2;
import kotlin.jvm.internal.CallableReference;
import kotlin.jvm.internal.FunctionReferenceImpl;

/* JADX INFO: compiled from: go/retraceme fd74bf9514dd0739b92d67a6d48a51eb10aaf8df760d83496b8dcfcf83e5f698 */
/* JADX INFO: loaded from: /home/starlight/Projects/Starlight Ports/.build-tmp/classes4.dex */
final /* synthetic */ class HeadsUpContainerWindow$touchMotion$3 extends FunctionReferenceImpl implements Function2 {
    public final Object invoke(Object obj, Object obj2) {
        float fFloatValue = ((Number) obj2).floatValue();
        ((HeadsUpContainerWindow) ((CallableReference) this).receiver).handleOneHandedMode(fFloatValue, (View) obj);
        return Unit.INSTANCE;
    }
}
