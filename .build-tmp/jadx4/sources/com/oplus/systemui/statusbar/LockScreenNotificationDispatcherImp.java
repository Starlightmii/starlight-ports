package com.oplus.systemui.statusbar;

import android.content.Context;
import android.service.notification.StatusBarNotification;
import com.android.internal.util.ContrastColorUtil;
import com.android.systemui.DependencyEx;
import com.android.systemui.media.MediaSeedlingControlEx;
import com.android.systemui.statusbar.LockScreenNotificationDispatcher;
import com.android.systemui.statusbar.notification.collection.GroupEntry;
import com.android.systemui.statusbar.notification.collection.ListEntry;
import com.android.systemui.statusbar.notification.collection.NotificationEntry;
import com.android.systemui.statusbar.notification.collection.NotificationResolverEx;
import com.android.systemui.statusbar.notification.collection.PipelineEntry;
import com.oplus.systemui.notification.lockscreen.notification.CapsuleNotificationViewModel;
import dagger.Lazy;
import java.lang.invoke.VarHandle;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;
import java.util.concurrent.CopyOnWriteArrayList;
import kotlin.LazyKt__LazyJVMKt;
import kotlin.collections.CollectionsKt__IterablesKt;
import kotlin.coroutines.CoroutineContext;
import kotlin.coroutines.jvm.internal.ContinuationImpl;
import kotlin.jvm.internal.Intrinsics;
import kotlinx.coroutines.BuildersKt;
import kotlinx.coroutines.CoroutineScope;
import kotlinx.coroutines.CoroutineScopeKt;
import kotlinx.coroutines.CoroutineStart;
import kotlinx.coroutines.flow.MutableStateFlow;
import kotlinx.coroutines.flow.StateFlow;
import kotlinx.coroutines.flow.StateFlowKt;

/* JADX INFO: compiled from: go/retraceme fd74bf9514dd0739b92d67a6d48a51eb10aaf8df760d83496b8dcfcf83e5f698 */
/* JADX INFO: loaded from: /home/starlight/Projects/Starlight Ports/.build-tmp/classes4.dex */
public final class LockScreenNotificationDispatcherImp extends LockScreenNotificationDispatcher {
    public static final Companion Companion = new Companion();
    private static final String TAG = "LockScreenNotificationDispatcherImp";
    private final MutableStateFlow _lockScreenNotificationIconData;
    private final Lazy capsuleNotificationViewModel;
    private final kotlin.Lazy colorUtil$delegate;
    private final Context context;
    private final CopyOnWriteArrayList<ListEntryWrapper> entrys;
    private final Lazy keyguardMediaController;
    private final Object lock;
    public final NotificationResolverEx mNotificationResolverEx;
    private final CoroutineScope mainScope;
    private final MediaSeedlingControlEx mediaSeedlingControlEx;
    private final List<LockScreenNotificationDispatcher.LockScreenNotificationObserver> observers;

    /* JADX INFO: compiled from: go/retraceme fd74bf9514dd0739b92d67a6d48a51eb10aaf8df760d83496b8dcfcf83e5f698 */
    public final class Companion {
    }

    /* JADX INFO: renamed from: com.oplus.systemui.statusbar.LockScreenNotificationDispatcherImp$addMediaNotificationIconIfNeed$1, reason: invalid class name */
    /* JADX INFO: compiled from: go/retraceme fd74bf9514dd0739b92d67a6d48a51eb10aaf8df760d83496b8dcfcf83e5f698 */
    final class AnonymousClass1 extends ContinuationImpl {
        int I$0;
        int I$1;
        int I$2;
        int I$3;
        int I$4;
        int I$5;
        Object L$0;
        Object L$1;
        Object L$2;
        Object L$3;
        Object L$4;
        Object L$5;
        Object L$6;
        Object L$7;
        int label;
        /* synthetic */ Object result;

        public AnonymousClass1(ContinuationImpl continuationImpl) {
            super(continuationImpl);
        }

        public final Object invokeSuspend(Object obj) {
            this.result = obj;
            this.label |= Integer.MIN_VALUE;
            return LockScreenNotificationDispatcherImp.this.addMediaNotificationIconIfNeed(null, 0, this);
        }
    }

    public LockScreenNotificationDispatcherImp(Context context, Lazy lazy, MediaSeedlingControlEx mediaSeedlingControlEx, Lazy lazy2) {
        super(context);
        this.context = context;
        this.keyguardMediaController = lazy;
        this.mediaSeedlingControlEx = mediaSeedlingControlEx;
        this.capsuleNotificationViewModel = lazy2;
        this.observers = new ArrayList();
        this.entrys = new CopyOnWriteArrayList<>();
        this.lock = new Object();
        this.mainScope = CoroutineScopeKt.MainScope();
        this.mNotificationResolverEx = (NotificationResolverEx) DependencyEx.sDependency.getDependency(NotificationResolverEx.class);
        LockScreenNotificationDispatcherImp$$ExternalSyntheticLambda0 lockScreenNotificationDispatcherImp$$ExternalSyntheticLambda0 = new LockScreenNotificationDispatcherImp$$ExternalSyntheticLambda0();
        lockScreenNotificationDispatcherImp$$ExternalSyntheticLambda0.f$0 = this;
        VarHandle.storeStoreFence();
        this.colorUtil$delegate = LazyKt__LazyJVMKt.lazy(lockScreenNotificationDispatcherImp$$ExternalSyntheticLambda0);
        this._lockScreenNotificationIconData = StateFlowKt.MutableStateFlow(new ArrayList());
    }

    /* JADX WARN: Code duplicated, block: B:100:0x021a  */
    /* JADX WARN: Code duplicated, block: B:102:0x0222  */
    /* JADX WARN: Code duplicated, block: B:106:0x0237  */
    /* JADX WARN: Code duplicated, block: B:111:0x0255  */
    /* JADX WARN: Code duplicated, block: B:127:0x02b3 A[ADDED_TO_REGION] */
    /* JADX WARN: Code duplicated, block: B:132:0x02cf  */
    /* JADX WARN: Code duplicated, block: B:136:0x02e8  */
    /* JADX WARN: Code duplicated, block: B:141:0x02fd  */
    /* JADX WARN: Code duplicated, block: B:143:0x0300  */
    /* JADX WARN: Code duplicated, block: B:144:0x0309  */
    /* JADX WARN: Code duplicated, block: B:146:0x030c  */
    /* JADX WARN: Code duplicated, block: B:147:0x0315  */
    /* JADX WARN: Code duplicated, block: B:151:0x0249 A[SYNTHETIC] */
    /* JADX WARN: Code duplicated, block: B:152:0x0246 A[SYNTHETIC] */
    /* JADX WARN: Code duplicated, block: B:153:0x0246 A[SYNTHETIC] */
    /* JADX WARN: Code duplicated, block: B:154:0x0232 A[SYNTHETIC] */
    /* JADX WARN: Code duplicated, block: B:155:0x0232 A[SYNTHETIC] */
    /* JADX WARN: Code duplicated, block: B:37:0x00cc  */
    /* JADX WARN: Code duplicated, block: B:39:0x00e6  */
    /* JADX WARN: Code duplicated, block: B:41:0x00ef  */
    /* JADX WARN: Code duplicated, block: B:46:0x0103  */
    /* JADX WARN: Code duplicated, block: B:49:0x012a  */
    /* JADX WARN: Code duplicated, block: B:52:0x0137  */
    /* JADX WARN: Code duplicated, block: B:57:0x0148  */
    /* JADX WARN: Code duplicated, block: B:60:0x014f  */
    /* JADX WARN: Code duplicated, block: B:63:0x015c  */
    /* JADX WARN: Code duplicated, block: B:66:0x0165  */
    /* JADX WARN: Code duplicated, block: B:72:0x0178  */
    /* JADX WARN: Code duplicated, block: B:74:0x017e  */
    /* JADX WARN: Code duplicated, block: B:76:0x0187  */
    /* JADX WARN: Code duplicated, block: B:77:0x018c  */
    /* JADX WARN: Code duplicated, block: B:7:0x001a  */
    /* JADX WARN: Code duplicated, block: B:80:0x0195  */
    /* JADX WARN: Code duplicated, block: B:81:0x01b4  */
    /* JADX WARN: Code duplicated, block: B:84:0x01cb  */
    /* JADX WARN: Code duplicated, block: B:85:0x01d2  */
    /* JADX WARN: Code duplicated, block: B:88:0x01df  */
    /* JADX WARN: Code duplicated, block: B:90:0x01e6  */
    /* JADX WARN: Code duplicated, block: B:93:0x01f5  */
    /* JADX WARN: Code duplicated, block: B:97:0x020a  */
    /* JADX WARN: Unsupported multi-entry loop pattern (BACK_EDGE: B:108:0x0246 -> B:149:0x0358). Please report as a decompilation issue!!! */
    /* JADX WARN: Unsupported multi-entry loop pattern (BACK_EDGE: B:122:0x0298 -> B:124:0x029b). Please report as a decompilation issue!!! */
    /* JADX WARN: Unsupported multi-entry loop pattern (BACK_EDGE: B:129:0x02bc -> B:130:0x02bd). Please report as a decompilation issue!!! */
    /*  JADX ERROR: StackOverflowError in pass: RegionMakerVisitor
        java.lang.StackOverflowError
        	at jadx.core.utils.BlockUtils.traverseSuccessorsUntil(BlockUtils.java:731)
        	at jadx.core.utils.BlockUtils.traverseSuccessorsUntil(BlockUtils.java:749)
        */
    public static final java.lang.Object access$getNotificationIconList(com.oplus.systemui.statusbar.LockScreenNotificationDispatcherImp r19, java.util.List r20, kotlin.coroutines.jvm.internal.ContinuationImpl r21) {
        /*
            Method dump skipped, instruction units count: 862
            To view this dump add '--comments-level debug' option
        */
        throw new UnsupportedOperationException("Method not decompiled: com.oplus.systemui.statusbar.LockScreenNotificationDispatcherImp.access$getNotificationIconList(com.oplus.systemui.statusbar.LockScreenNotificationDispatcherImp, java.util.List, kotlin.coroutines.jvm.internal.ContinuationImpl):java.lang.Object");
    }

    public static final int access$listIsChange(LockScreenNotificationDispatcherImp lockScreenNotificationDispatcherImp, List list) {
        NotificationEntry representativeEntry;
        StatusBarNotification sbn;
        if (lockScreenNotificationDispatcherImp.entrys.size() != list.size()) {
            return 2;
        }
        if (lockScreenNotificationDispatcherImp.entrys.size() != 0) {
            int size = lockScreenNotificationDispatcherImp.entrys.size();
            for (int i = 0; i < size; i++) {
                if (!Intrinsics.areEqual(lockScreenNotificationDispatcherImp.entrys.get(i).getListEntry(), list.get(i))) {
                    return 1;
                }
                Long postTime = lockScreenNotificationDispatcherImp.entrys.get(i).getPostTime();
                ListEntry listEntryAsListEntry = ((PipelineEntry) list.get(i)).asListEntry();
                if (!Intrinsics.areEqual(postTime, (listEntryAsListEntry == null || (representativeEntry = listEntryAsListEntry.getRepresentativeEntry()) == null || (sbn = representativeEntry.getSbn()) == null) ? null : Long.valueOf(sbn.getPostTime())) || (list.get(i) instanceof GroupEntry)) {
                    return 1;
                }
            }
        }
        return 0;
    }

    /* JADX WARN: Code duplicated, block: B:21:0x00a4  */
    /* JADX WARN: Code duplicated, block: B:23:0x00ac  */
    /* JADX WARN: Code duplicated, block: B:25:0x00b5  */
    /* JADX WARN: Code duplicated, block: B:28:0x00cb  */
    /* JADX WARN: Code duplicated, block: B:34:0x00e8  */
    /* JADX WARN: Code duplicated, block: B:37:0x00f7  */
    /* JADX WARN: Code duplicated, block: B:40:0x0136 A[RETURN] */
    /* JADX WARN: Code duplicated, block: B:41:0x0137  */
    /* JADX WARN: Code duplicated, block: B:44:0x0147  */
    /* JADX WARN: Code duplicated, block: B:45:0x014c  */
    /* JADX WARN: Code duplicated, block: B:48:0x015a  */
    /* JADX WARN: Code duplicated, block: B:52:0x016e  */
    /* JADX WARN: Code duplicated, block: B:54:0x0180  */
    /* JADX WARN: Code duplicated, block: B:58:0x00e2 A[SYNTHETIC] */
    /* JADX WARN: Code duplicated, block: B:7:0x0017  */
    /* JADX WARN: Multi-variable type inference failed */
    /* JADX WARN: Unsupported multi-entry loop pattern (BACK_EDGE: B:41:0x0137 -> B:42:0x013d). Please report as a decompilation issue!!! */
    /* JADX WARN: Unsupported multi-entry loop pattern (BACK_EDGE: B:52:0x016e -> B:53:0x017c). Please report as a decompilation issue!!! */
    /*  JADX ERROR: StackOverflowError in pass: RegionMakerVisitor
        java.lang.StackOverflowError
        	at jadx.core.utils.BlockUtils.traverseSuccessorsUntil(BlockUtils.java:731)
        	at jadx.core.utils.BlockUtils.traverseSuccessorsUntil(BlockUtils.java:749)
        */
    public final java.lang.Object addMediaNotificationIconIfNeed(java.util.List r20, int r21, kotlin.coroutines.jvm.internal.ContinuationImpl r22) {
        /*
            Method dump skipped, instruction units count: 391
            To view this dump add '--comments-level debug' option
        */
        throw new UnsupportedOperationException("Method not decompiled: com.oplus.systemui.statusbar.LockScreenNotificationDispatcherImp.addMediaNotificationIconIfNeed(java.util.List, int, kotlin.coroutines.jvm.internal.ContinuationImpl):java.lang.Object");
    }

    public final Lazy getCapsuleNotificationViewModel() {
        return this.capsuleNotificationViewModel;
    }

    public final ContrastColorUtil getColorUtil() {
        return (ContrastColorUtil) this.colorUtil$delegate.getValue();
    }

    public final Context getContext() {
        return this.context;
    }

    public final Lazy getKeyguardMediaController() {
        return this.keyguardMediaController;
    }

    public StateFlow getLockScreenNotificationIconData() {
        return this._lockScreenNotificationIconData;
    }

    public final MediaSeedlingControlEx getMediaSeedlingControlEx() {
        return this.mediaSeedlingControlEx;
    }

    public List<PipelineEntry> getNotificationListOnKg() {
        CopyOnWriteArrayList<ListEntryWrapper> copyOnWriteArrayList = this.entrys;
        ArrayList arrayList = new ArrayList(CollectionsKt__IterablesKt.collectionSizeOrDefault(copyOnWriteArrayList, 10));
        Iterator<T> it = copyOnWriteArrayList.iterator();
        while (it.hasNext()) {
            arrayList.add(((ListEntryWrapper) it.next()).getListEntry());
        }
        return arrayList;
    }

    public boolean registerObserver(LockScreenNotificationDispatcher.LockScreenNotificationObserver lockScreenNotificationObserver) {
        synchronized (this.lock) {
            if (this.observers.contains(lockScreenNotificationObserver)) {
                return false;
            }
            this.observers.add(lockScreenNotificationObserver);
            return true;
        }
    }

    public boolean unregisterObserver(LockScreenNotificationDispatcher.LockScreenNotificationObserver lockScreenNotificationObserver) {
        synchronized (this.lock) {
            if (!this.observers.contains(lockScreenNotificationObserver)) {
                return false;
            }
            this.observers.remove(lockScreenNotificationObserver);
            return true;
        }
    }

    public final void updateNotificationListOnKg() {
        synchronized (this.lock) {
            BuildersKt.launch$default(this.mainScope, (CoroutineContext) null, (CoroutineStart) null, new LockScreenNotificationDispatcherImp$updateNotificationListOnKg$2$1(this, null), 3);
            CapsuleNotificationViewModel capsuleNotificationViewModel = (CapsuleNotificationViewModel) this.capsuleNotificationViewModel.get();
            if (capsuleNotificationViewModel != null) {
                capsuleNotificationViewModel.refreshCapsuleDataByMedia();
            }
        }
    }

    public void updateNotificationListOnKg(List<? extends PipelineEntry> list) {
        synchronized (this.lock) {
            BuildersKt.launch$default(this.mainScope, (CoroutineContext) null, (CoroutineStart) null, new LockScreenNotificationDispatcherImp$updateNotificationListOnKg$1$1(this, new ArrayList(list), null), 3);
        }
    }
}
