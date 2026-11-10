package com.oplus.systemui.notification.lockscreen.capsule.modle;

import android.util.Log;
import com.oplus.systemui.notification.lockscreen.notification.CapsuleNotificationData;
import com.oplusos.systemui.common.util.LogUtil;
import java.util.ArrayList;
import java.util.List;
import kotlin.ResultKt;
import kotlin.Unit;
import kotlin.coroutines.Continuation;
import kotlin.coroutines.intrinsics.IntrinsicsKt__IntrinsicsKt;
import kotlin.coroutines.jvm.internal.SuspendLambda;
import kotlin.jvm.functions.Function4;
import okio.Path$;

/* JADX INFO: compiled from: go/retraceme fd74bf9514dd0739b92d67a6d48a51eb10aaf8df760d83496b8dcfcf83e5f698 */
/* JADX INFO: loaded from: /home/starlight/Projects/Starlight Ports/.build-tmp/classes4.dex */
final class CapsuleViewModel$capsuleData$1 extends SuspendLambda implements Function4 {
    /* synthetic */ Object L$0;
    /* synthetic */ Object L$1;
    /* synthetic */ boolean Z$0;
    int label;
    final /* synthetic */ CapsuleViewModel this$0;

    /* JADX WARN: 'super' call moved to the top of the method (can break code semantics) */
    public CapsuleViewModel$capsuleData$1(CapsuleViewModel capsuleViewModel, Continuation continuation) {
        super(4, continuation);
        this.this$0 = capsuleViewModel;
    }

    public final Object invoke(Object obj, Object obj2, Object obj3, Object obj4) {
        boolean zBooleanValue = ((Boolean) obj3).booleanValue();
        CapsuleViewModel$capsuleData$1 capsuleViewModel$capsuleData$1 = new CapsuleViewModel$capsuleData$1(this.this$0, (Continuation) obj4);
        capsuleViewModel$capsuleData$1.L$0 = (CapsuleNotificationData) obj;
        capsuleViewModel$capsuleData$1.L$1 = (List) obj2;
        capsuleViewModel$capsuleData$1.Z$0 = zBooleanValue;
        return capsuleViewModel$capsuleData$1.invokeSuspend(Unit.INSTANCE);
    }

    public final Object invokeSuspend(Object obj) {
        CapsuleNotificationData capsuleNotificationData = (CapsuleNotificationData) this.L$0;
        List list = (List) this.L$1;
        boolean z = this.Z$0;
        IntrinsicsKt__IntrinsicsKt.getCOROUTINE_SUSPENDED();
        if (this.label != 0) {
            Path$.ExternalSyntheticBUOutline0.m$1("call to 'resume' before 'invoke' with coroutine");
            return null;
        }
        ResultKt.throwOnFailure(obj);
        if (z) {
            if (LogUtil.getNormalDebug() && LogUtil.isTagValid(LogUtil.TAG_NOTIFICATION)) {
                Log.d("SystemUi--Notification", "CapsuleViewModel-->CapsuleDataChangedIntercepted: hold current data");
            }
            return this.this$0.lastCapsuleData;
        }
        if (LogUtil.getNormalDebug() && LogUtil.isTagValid(LogUtil.TAG_NOTIFICATION)) {
            Log.d("SystemUi--Notification", "CapsuleViewModel-->CapsuleDataChangedUnIntercepted: transform new data");
        }
        this.this$0.getClass();
        ArrayList arrayList = new ArrayList();
        arrayList.addAll(list);
        if (capsuleNotificationData != null) {
            arrayList.add(capsuleNotificationData);
        }
        this.this$0.lastCapsuleData = arrayList;
        return arrayList;
    }
}
