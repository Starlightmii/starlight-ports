package com.oplus.systemui.statusbar;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.concurrent.CopyOnWriteArrayList;
import kotlin.ResultKt;
import kotlin.Unit;
import kotlin.collections.CollectionsKt__IterablesKt;
import kotlin.coroutines.Continuation;
import kotlin.coroutines.intrinsics.IntrinsicsKt__IntrinsicsKt;
import kotlin.coroutines.jvm.internal.SuspendLambda;
import kotlin.jvm.functions.Function2;
import kotlinx.coroutines.CoroutineScope;
import kotlinx.coroutines.flow.MutableStateFlow;
import okio.Path$;

/* JADX INFO: compiled from: go/retraceme fd74bf9514dd0739b92d67a6d48a51eb10aaf8df760d83496b8dcfcf83e5f698 */
/* JADX INFO: loaded from: /home/starlight/Projects/Starlight Ports/.build-tmp/classes4.dex */
final class LockScreenNotificationDispatcherImp$updateNotificationListOnKg$2$1 extends SuspendLambda implements Function2 {
    Object L$0;
    int label;
    final /* synthetic */ LockScreenNotificationDispatcherImp this$0;

    /* JADX WARN: 'super' call moved to the top of the method (can break code semantics) */
    public LockScreenNotificationDispatcherImp$updateNotificationListOnKg$2$1(LockScreenNotificationDispatcherImp lockScreenNotificationDispatcherImp, Continuation continuation) {
        super(2, continuation);
        this.this$0 = lockScreenNotificationDispatcherImp;
    }

    public final Continuation create(Object obj, Continuation continuation) {
        return new LockScreenNotificationDispatcherImp$updateNotificationListOnKg$2$1(this.this$0, continuation);
    }

    public final Object invoke(Object obj, Object obj2) {
        return create((CoroutineScope) obj, (Continuation) obj2).invokeSuspend(Unit.INSTANCE);
    }

    public final Object invokeSuspend(Object obj) {
        MutableStateFlow mutableStateFlow;
        Object coroutine_suspended = IntrinsicsKt__IntrinsicsKt.getCOROUTINE_SUSPENDED();
        int i = this.label;
        if (i == 0) {
            ResultKt.throwOnFailure(obj);
            MutableStateFlow mutableStateFlow2 = this.this$0._lockScreenNotificationIconData;
            LockScreenNotificationDispatcherImp lockScreenNotificationDispatcherImp = this.this$0;
            CopyOnWriteArrayList copyOnWriteArrayList = lockScreenNotificationDispatcherImp.entrys;
            ArrayList arrayList = new ArrayList(CollectionsKt__IterablesKt.collectionSizeOrDefault(copyOnWriteArrayList, 10));
            Iterator it = copyOnWriteArrayList.iterator();
            while (it.hasNext()) {
                arrayList.add(((ListEntryWrapper) it.next()).getListEntry());
            }
            this.L$0 = mutableStateFlow2;
            this.label = 1;
            Object objAccess$getNotificationIconList = LockScreenNotificationDispatcherImp.access$getNotificationIconList(lockScreenNotificationDispatcherImp, arrayList, this);
            if (objAccess$getNotificationIconList == coroutine_suspended) {
                return coroutine_suspended;
            }
            obj = objAccess$getNotificationIconList;
            mutableStateFlow = mutableStateFlow2;
        } else {
            if (i != 1) {
                Path$.ExternalSyntheticBUOutline0.m$1("call to 'resume' before 'invoke' with coroutine");
                return null;
            }
            mutableStateFlow = (MutableStateFlow) this.L$0;
            ResultKt.throwOnFailure(obj);
        }
        mutableStateFlow.setValue(obj);
        return Unit.INSTANCE;
    }
}
