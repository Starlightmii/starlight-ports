package com.oplus.systemui.statusbar;

import android.service.notification.StatusBarNotification;
import androidx.compose.foundation.text.input.TextFieldBuffer$;
import com.android.systemui.statusbar.LockScreenNotificationDispatcher;
import com.android.systemui.statusbar.notification.collection.ListEntry;
import com.android.systemui.statusbar.notification.collection.NotificationEntry;
import com.android.systemui.statusbar.notification.collection.PipelineEntry;
import com.oplusos.systemui.common.util.LogUtil;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;
import java.util.concurrent.CopyOnWriteArrayList;
import kotlin.ResultKt;
import kotlin.Unit;
import kotlin.collections.CollectionsKt__IterablesKt;
import kotlin.coroutines.Continuation;
import kotlin.coroutines.intrinsics.IntrinsicsKt__IntrinsicsKt;
import kotlin.coroutines.jvm.internal.Boxing;
import kotlin.coroutines.jvm.internal.SuspendLambda;
import kotlin.jvm.functions.Function2;
import kotlinx.coroutines.CoroutineScope;
import kotlinx.coroutines.flow.MutableStateFlow;
import okio.Path$;

/* JADX INFO: compiled from: go/retraceme fd74bf9514dd0739b92d67a6d48a51eb10aaf8df760d83496b8dcfcf83e5f698 */
/* JADX INFO: loaded from: /home/starlight/Projects/Starlight Ports/.build-tmp/classes4.dex */
final class LockScreenNotificationDispatcherImp$updateNotificationListOnKg$1$1 extends SuspendLambda implements Function2 {
    final /* synthetic */ ArrayList<PipelineEntry> $snapshot;
    int I$0;
    Object L$0;
    Object L$1;
    int label;
    final /* synthetic */ LockScreenNotificationDispatcherImp this$0;

    /* JADX WARN: 'super' call moved to the top of the method (can break code semantics) */
    public LockScreenNotificationDispatcherImp$updateNotificationListOnKg$1$1(LockScreenNotificationDispatcherImp lockScreenNotificationDispatcherImp, ArrayList arrayList, Continuation continuation) {
        super(2, continuation);
        this.this$0 = lockScreenNotificationDispatcherImp;
        this.$snapshot = arrayList;
    }

    public final Continuation create(Object obj, Continuation continuation) {
        return new LockScreenNotificationDispatcherImp$updateNotificationListOnKg$1$1(this.this$0, this.$snapshot, continuation);
    }

    public final Object invoke(Object obj, Object obj2) {
        return create((CoroutineScope) obj, (Continuation) obj2).invokeSuspend(Unit.INSTANCE);
    }

    public final Object invokeSuspend(Object obj) {
        MutableStateFlow mutableStateFlow;
        int i;
        List list;
        NotificationEntry representativeEntry;
        StatusBarNotification sbn;
        Object coroutine_suspended = IntrinsicsKt__IntrinsicsKt.getCOROUTINE_SUSPENDED();
        int i2 = this.label;
        if (i2 == 0) {
            ResultKt.throwOnFailure(obj);
            int iAccess$listIsChange = LockScreenNotificationDispatcherImp.access$listIsChange(this.this$0, this.$snapshot);
            if (iAccess$listIsChange > 0) {
                this.this$0.entrys.clear();
                ArrayList<PipelineEntry> arrayList = this.$snapshot;
                int size = arrayList.size();
                int i3 = 0;
                while (i3 < size) {
                    PipelineEntry pipelineEntry = arrayList.get(i3);
                    i3++;
                    PipelineEntry pipelineEntry2 = pipelineEntry;
                    CopyOnWriteArrayList copyOnWriteArrayList = this.this$0.entrys;
                    pipelineEntry2.getClass();
                    ListEntry listEntryAsListEntry = pipelineEntry2.asListEntry();
                    copyOnWriteArrayList.add(new ListEntryWrapper(pipelineEntry2, (listEntryAsListEntry == null || (representativeEntry = listEntryAsListEntry.getRepresentativeEntry()) == null || (sbn = representativeEntry.getSbn()) == null) ? null : Boxing.boxLong(sbn.getPostTime())));
                }
                CopyOnWriteArrayList copyOnWriteArrayList2 = this.this$0.entrys;
                ArrayList arrayList2 = new ArrayList(CollectionsKt__IterablesKt.collectionSizeOrDefault(copyOnWriteArrayList2, 10));
                Iterator it = copyOnWriteArrayList2.iterator();
                while (it.hasNext()) {
                    arrayList2.add(((ListEntryWrapper) it.next()).getListEntry());
                }
                LogUtil.i$default(LogUtil.TAG_NOTIFICATION, "LockScreenNotificationDispatcherImp", TextFieldBuffer$.ExternalSyntheticOutline0.m(iAccess$listIsChange, arrayList2.size(), "updateLockScreenNotifications resultCode: ", ", size: "), null, 8);
                mutableStateFlow = this.this$0._lockScreenNotificationIconData;
                LockScreenNotificationDispatcherImp lockScreenNotificationDispatcherImp = this.this$0;
                this.L$0 = arrayList2;
                this.L$1 = mutableStateFlow;
                this.I$0 = iAccess$listIsChange;
                this.label = 1;
                Object objAccess$getNotificationIconList = LockScreenNotificationDispatcherImp.access$getNotificationIconList(lockScreenNotificationDispatcherImp, arrayList2, this);
                if (objAccess$getNotificationIconList == coroutine_suspended) {
                    return coroutine_suspended;
                }
                i = iAccess$listIsChange;
                obj = objAccess$getNotificationIconList;
                list = arrayList2;
            }
            return Unit.INSTANCE;
        }
        if (i2 != 1) {
            Path$.ExternalSyntheticBUOutline0.m$1("call to 'resume' before 'invoke' with coroutine");
            return null;
        }
        i = this.I$0;
        mutableStateFlow = (MutableStateFlow) this.L$1;
        list = (List) this.L$0;
        ResultKt.throwOnFailure(obj);
        mutableStateFlow.setValue(obj);
        Iterator it2 = this.this$0.observers.iterator();
        while (it2.hasNext()) {
            ((LockScreenNotificationDispatcher.LockScreenNotificationObserver) it2.next()).updateLockScreenNotifications(list, i);
        }
        return Unit.INSTANCE;
    }
}
