package com.oplus.systemui.statusbar.icon.ui.viewmodel;

import com.oplus.systemui.statusbar.icon.data.NotificationIconAreaType;
import kotlin.LazyKt__LazyJVMKt$;
import kotlin.ResultKt;
import kotlin.Unit;
import kotlin.coroutines.Continuation;
import kotlin.coroutines.intrinsics.IntrinsicsKt__IntrinsicsKt;
import kotlin.coroutines.jvm.internal.Boxing;
import kotlin.coroutines.jvm.internal.SuspendLambda;
import kotlin.jvm.functions.Function3;
import okio.Path$;

/* JADX INFO: compiled from: go/retraceme fd74bf9514dd0739b92d67a6d48a51eb10aaf8df760d83496b8dcfcf83e5f698 */
/* JADX INFO: loaded from: /home/starlight/Projects/Starlight Ports/.build-tmp/classes4.dex */
final class OplusNotificationIconAreaViewModel$notificationBadgeViewVisibility$1 extends SuspendLambda implements Function3 {
    /* synthetic */ int I$0;
    /* synthetic */ Object L$0;
    int label;

    public final Object invoke(Object obj, Object obj2, Object obj3) {
        int iIntValue = ((Number) obj).intValue();
        OplusNotificationIconAreaViewModel$notificationBadgeViewVisibility$1 oplusNotificationIconAreaViewModel$notificationBadgeViewVisibility$1 = new OplusNotificationIconAreaViewModel$notificationBadgeViewVisibility$1(3, (Continuation) obj3);
        oplusNotificationIconAreaViewModel$notificationBadgeViewVisibility$1.I$0 = iIntValue;
        oplusNotificationIconAreaViewModel$notificationBadgeViewVisibility$1.L$0 = (NotificationIconAreaType) obj2;
        return oplusNotificationIconAreaViewModel$notificationBadgeViewVisibility$1.invokeSuspend(Unit.INSTANCE);
    }

    public final Object invokeSuspend(Object obj) {
        int i = this.I$0;
        NotificationIconAreaType notificationIconAreaType = (NotificationIconAreaType) this.L$0;
        IntrinsicsKt__IntrinsicsKt.getCOROUTINE_SUSPENDED();
        if (this.label != 0) {
            Path$.ExternalSyntheticBUOutline0.m$1("call to 'resume' before 'invoke' with coroutine");
            return null;
        }
        ResultKt.throwOnFailure(obj);
        int iOrdinal = notificationIconAreaType.ordinal();
        int i2 = 8;
        if (iOrdinal != 0) {
            if (iOrdinal != 1) {
                if (iOrdinal != 2) {
                    LazyKt__LazyJVMKt$.ExternalSyntheticBUOutline0.m();
                    return null;
                }
            } else if (i > 0) {
                i2 = 0;
            }
        }
        return Boxing.boxInt(i2);
    }
}
