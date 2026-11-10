package com.oplus.systemui.notification.row.overlay;

import kotlin.Unit;
import kotlin.jvm.functions.Function1;
import kotlin.jvm.internal.CallableReference;
import kotlin.jvm.internal.FunctionReferenceImpl;

/* JADX INFO: compiled from: go/retraceme fd74bf9514dd0739b92d67a6d48a51eb10aaf8df760d83496b8dcfcf83e5f698 */
/* JADX INFO: loaded from: /home/starlight/Projects/Starlight Ports/.build-tmp/classes4.dex */
final /* synthetic */ class NotificationInfoTextView$changeText$1 extends FunctionReferenceImpl implements Function1 {
    public final Object invoke(Object obj) {
        ((NotificationInfoTextView) ((CallableReference) this).receiver).setAlpha(((Number) obj).floatValue());
        return Unit.INSTANCE;
    }
}
