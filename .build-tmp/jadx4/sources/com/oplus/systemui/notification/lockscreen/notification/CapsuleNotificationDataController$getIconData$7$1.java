package com.oplus.systemui.notification.lockscreen.notification;

import android.content.Context;
import android.graphics.drawable.Drawable;
import kotlin.ResultKt;
import kotlin.Unit;
import kotlin.coroutines.Continuation;
import kotlin.coroutines.intrinsics.IntrinsicsKt__IntrinsicsKt;
import kotlin.coroutines.jvm.internal.Boxing;
import kotlin.coroutines.jvm.internal.SuspendLambda;
import kotlin.jvm.functions.Function2;
import kotlinx.coroutines.CoroutineScope;
import okio.Path$;

/* JADX INFO: compiled from: go/retraceme fd74bf9514dd0739b92d67a6d48a51eb10aaf8df760d83496b8dcfcf83e5f698 */
/* JADX INFO: loaded from: /home/starlight/Projects/Starlight Ports/.build-tmp/classes4.dex */
final class CapsuleNotificationDataController$getIconData$7$1 extends SuspendLambda implements Function2 {
    final /* synthetic */ Context $context;
    final /* synthetic */ String $iconKey;
    final /* synthetic */ Drawable $it;
    int label;
    final /* synthetic */ CapsuleNotificationDataController this$0;

    /* JADX WARN: 'super' call moved to the top of the method (can break code semantics) */
    public CapsuleNotificationDataController$getIconData$7$1(Context context, Drawable drawable, CapsuleNotificationDataController capsuleNotificationDataController, String str, Continuation continuation) {
        super(2, continuation);
        this.$context = context;
        this.$it = drawable;
        this.this$0 = capsuleNotificationDataController;
        this.$iconKey = str;
    }

    public final Continuation create(Object obj, Continuation continuation) {
        return new CapsuleNotificationDataController$getIconData$7$1(this.$context, this.$it, this.this$0, this.$iconKey, continuation);
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
        Integer numBoxInt = Boxing.boxInt(CapsuleNotificationUtils.getIconColor(this.$context, this.$it));
        CapsuleNotificationDataController capsuleNotificationDataController = this.this$0;
        capsuleNotificationDataController.iconInfoCache.put(this.$iconKey, Boxing.boxInt(numBoxInt.intValue()));
        return numBoxInt;
    }
}
