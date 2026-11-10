package com.oplus.systemui.statusbar.notification.row.menupanel.notificationtop;

import android.content.Context;
import kotlin.ResultKt;
import kotlin.Unit;
import kotlin.coroutines.Continuation;
import kotlin.coroutines.intrinsics.IntrinsicsKt__IntrinsicsKt;
import kotlin.coroutines.jvm.internal.SuspendLambda;
import kotlin.jvm.functions.Function2;
import kotlinx.coroutines.CoroutineScope;
import okio.Path$;

/* JADX INFO: compiled from: go/retraceme fd74bf9514dd0739b92d67a6d48a51eb10aaf8df760d83496b8dcfcf83e5f698 */
/* JADX INFO: loaded from: /home/starlight/Projects/Starlight Ports/.build-tmp/classes4.dex */
final class NotificationTopListManageImpl$initData$1 extends SuspendLambda implements Function2 {
    final /* synthetic */ Context $context;
    int label;
    final /* synthetic */ NotificationTopListManageImpl this$0;

    /* JADX WARN: 'super' call moved to the top of the method (can break code semantics) */
    public NotificationTopListManageImpl$initData$1(Context context, NotificationTopListManageImpl notificationTopListManageImpl, Continuation continuation) {
        super(2, continuation);
        this.this$0 = notificationTopListManageImpl;
        this.$context = context;
    }

    public final Continuation create(Object obj, Continuation continuation) {
        return new NotificationTopListManageImpl$initData$1(this.$context, this.this$0, continuation);
    }

    public final Object invoke(Object obj, Object obj2) {
        return create((CoroutineScope) obj, (Continuation) obj2).invokeSuspend(Unit.INSTANCE);
    }

    public final Object invokeSuspend(Object obj) {
        IntrinsicsKt__IntrinsicsKt.getCOROUTINE_SUSPENDED();
        if (this.label != 0) {
            Path$.ExternalSyntheticBUOutline0.m$1("call to 'resume' before 'invoke' with coroutine");
            return null;
        }
        ResultKt.throwOnFailure(obj);
        this.this$0.currentNotificationTopSet.clear();
        this.this$0.currentNotificationTopSet.addAll(this.this$0.getNotificationTopListFromSettings(this.$context));
        this.this$0.preNotificationTopSet.addAll(this.this$0.currentNotificationTopSet);
        return Unit.INSTANCE;
    }
}
