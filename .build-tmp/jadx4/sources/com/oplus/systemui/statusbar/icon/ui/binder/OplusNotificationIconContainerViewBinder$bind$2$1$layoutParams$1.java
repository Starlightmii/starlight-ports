package com.oplus.systemui.statusbar.icon.ui.binder;

import android.widget.FrameLayout;
import kotlin.ResultKt;
import kotlin.Unit;
import kotlin.coroutines.Continuation;
import kotlin.coroutines.intrinsics.IntrinsicsKt__IntrinsicsKt;
import kotlin.coroutines.jvm.internal.SuspendLambda;
import kotlin.jvm.functions.Function4;
import okio.Path$;

/* JADX INFO: compiled from: go/retraceme fd74bf9514dd0739b92d67a6d48a51eb10aaf8df760d83496b8dcfcf83e5f698 */
/* JADX INFO: loaded from: /home/starlight/Projects/Starlight Ports/.build-tmp/classes4.dex */
final class OplusNotificationIconContainerViewBinder$bind$2$1$layoutParams$1 extends SuspendLambda implements Function4 {
    /* synthetic */ int I$0;
    /* synthetic */ int I$1;
    /* synthetic */ int I$2;
    int label;

    public final Object invoke(Object obj, Object obj2, Object obj3, Object obj4) {
        int iIntValue = ((Number) obj).intValue();
        int iIntValue2 = ((Number) obj2).intValue();
        int iIntValue3 = ((Number) obj3).intValue();
        OplusNotificationIconContainerViewBinder$bind$2$1$layoutParams$1 oplusNotificationIconContainerViewBinder$bind$2$1$layoutParams$1 = new OplusNotificationIconContainerViewBinder$bind$2$1$layoutParams$1(4, (Continuation) obj4);
        oplusNotificationIconContainerViewBinder$bind$2$1$layoutParams$1.I$0 = iIntValue;
        oplusNotificationIconContainerViewBinder$bind$2$1$layoutParams$1.I$1 = iIntValue2;
        oplusNotificationIconContainerViewBinder$bind$2$1$layoutParams$1.I$2 = iIntValue3;
        return oplusNotificationIconContainerViewBinder$bind$2$1$layoutParams$1.invokeSuspend(Unit.INSTANCE);
    }

    public final Object invokeSuspend(Object obj) {
        int i = this.I$0;
        int i2 = this.I$1;
        int i3 = this.I$2;
        IntrinsicsKt__IntrinsicsKt.getCOROUTINE_SUSPENDED();
        if (this.label == 0) {
            ResultKt.throwOnFailure(obj);
            return new FrameLayout.LayoutParams((i2 * 2) + i, i3);
        }
        Path$.ExternalSyntheticBUOutline0.m$1("call to 'resume' before 'invoke' with coroutine");
        return null;
    }
}
