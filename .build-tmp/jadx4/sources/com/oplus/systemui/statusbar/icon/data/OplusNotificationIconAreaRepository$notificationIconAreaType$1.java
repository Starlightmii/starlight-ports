package com.oplus.systemui.statusbar.icon.data;

import kotlin.ResultKt;
import kotlin.Unit;
import kotlin.coroutines.Continuation;
import kotlin.coroutines.intrinsics.IntrinsicsKt__IntrinsicsKt;
import kotlin.coroutines.jvm.internal.SuspendLambda;
import kotlin.jvm.functions.Function4;
import okio.Path$;

/* JADX INFO: compiled from: go/retraceme fd74bf9514dd0739b92d67a6d48a51eb10aaf8df760d83496b8dcfcf83e5f698 */
/* JADX INFO: loaded from: /home/starlight/Projects/Starlight Ports/.build-tmp/classes4.dex */
final class OplusNotificationIconAreaRepository$notificationIconAreaType$1 extends SuspendLambda implements Function4 {
    /* synthetic */ int I$0;
    /* synthetic */ boolean Z$0;
    /* synthetic */ boolean Z$1;
    int label;

    public final Object invoke(Object obj, Object obj2, Object obj3, Object obj4) {
        int iIntValue = ((Number) obj).intValue();
        boolean zBooleanValue = ((Boolean) obj2).booleanValue();
        boolean zBooleanValue2 = ((Boolean) obj3).booleanValue();
        OplusNotificationIconAreaRepository$notificationIconAreaType$1 oplusNotificationIconAreaRepository$notificationIconAreaType$1 = new OplusNotificationIconAreaRepository$notificationIconAreaType$1(4, (Continuation) obj4);
        oplusNotificationIconAreaRepository$notificationIconAreaType$1.I$0 = iIntValue;
        oplusNotificationIconAreaRepository$notificationIconAreaType$1.Z$0 = zBooleanValue;
        oplusNotificationIconAreaRepository$notificationIconAreaType$1.Z$1 = zBooleanValue2;
        return oplusNotificationIconAreaRepository$notificationIconAreaType$1.invokeSuspend(Unit.INSTANCE);
    }

    public final Object invokeSuspend(Object obj) {
        int i = this.I$0;
        boolean z = this.Z$0;
        boolean z2 = this.Z$1;
        IntrinsicsKt__IntrinsicsKt.getCOROUTINE_SUSPENDED();
        if (this.label != 0) {
            Path$.ExternalSyntheticBUOutline0.m$1("call to 'resume' before 'invoke' with coroutine");
            return null;
        }
        ResultKt.throwOnFailure(obj);
        if (z || z2) {
            return NotificationIconAreaType.NOTIFICATION_NOT_SHOW;
        }
        if (i == 2) {
            return NotificationIconAreaType.NOTIFICATION_NOT_SHOW;
        }
        return i == 1 ? NotificationIconAreaType.NOTIFICATION_SHOW_NUMBER : NotificationIconAreaType.NOTIFICATION_SHOW_ICON;
    }
}
