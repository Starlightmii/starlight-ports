package com.oplus.systemui.statusbar.notification.collection.inflation;

import android.os.Handler;
import android.util.Log;
import com.android.systemui.DependencyEx;
import com.android.systemui.application.impl.SystemUIApplicationImpl$;
import com.android.systemui.media.controls.domain.pipeline.MediaTimeoutListener$PlaybackStateListener$;
import com.android.systemui.statusbar.notification.NotificationUtilsKt;
import com.android.systemui.statusbar.notification.collection.NotificationEntry;
import com.android.systemui.statusbar.notification.collection.PipelineEntry;
import com.android.systemui.statusbar.notification.collection.inflation.NotifInflateDelayStrategy;
import com.oplusos.systemui.common.util.LogUtil;
import java.lang.invoke.VarHandle;
import kotlin.Pair;
import kotlin.TuplesKt;
import kotlin.jvm.functions.Function0;
import kotlin.jvm.internal.Intrinsics;

/* JADX INFO: compiled from: go/retraceme fd74bf9514dd0739b92d67a6d48a51eb10aaf8df760d83496b8dcfcf83e5f698 */
/* JADX INFO: loaded from: /home/starlight/Projects/Starlight Ports/.build-tmp/classes4.dex */
public final class OplusHeadsUpAvalancheControl {
    public static final Companion Companion = new Companion();
    private static final String TAG = "OplusHeadsUpAvalancheControl";
    private Pair<? extends NotificationEntry, ? extends Runnable> current;
    private final Handler handler;
    private Pair<? extends NotificationEntry, ? extends Runnable> next;
    private Function0 nextActionChanged;
    private final Runnable timeOut;

    /* JADX INFO: compiled from: go/retraceme fd74bf9514dd0739b92d67a6d48a51eb10aaf8df760d83496b8dcfcf83e5f698 */
    public final class Companion {
    }

    public OplusHeadsUpAvalancheControl(Handler handler) {
        this.handler = handler;
        OplusHeadsUpAvalancheControl$timeOut$1 oplusHeadsUpAvalancheControl$timeOut$1 = new OplusHeadsUpAvalancheControl$timeOut$1();
        oplusHeadsUpAvalancheControl$timeOut$1.this$0 = this;
        VarHandle.storeStoreFence();
        this.timeOut = oplusHeadsUpAvalancheControl$timeOut$1;
    }

    private final void setNext(Pair<? extends NotificationEntry, ? extends Runnable> pair) {
        if (!Intrinsics.areEqual(this.next, pair)) {
            this.next = pair;
            Function0 function0 = this.nextActionChanged;
            if (function0 != null) {
                function0.invoke();
            }
        }
        this.handler.removeCallbacks(this.timeOut);
        if (pair != null) {
            this.handler.postDelayed(this.timeOut, 1200L);
        }
    }

    public final void endEntry(NotificationEntry notificationEntry) {
        NotificationEntry notificationEntry2;
        NotificationEntry notificationEntry3;
        NotificationEntry notificationEntry4;
        String key = notificationEntry.getKey();
        Pair<? extends NotificationEntry, ? extends Runnable> pair = this.next;
        String key2 = null;
        if (Intrinsics.areEqual(key, (pair == null || (notificationEntry4 = (NotificationEntry) pair.getFirst()) == null) ? null : notificationEntry4.getKey())) {
            if (LogUtil.getNormalDebug() && LogUtil.isTagValid(LogUtil.TAG_NOTIFICATION)) {
                Pair<? extends NotificationEntry, ? extends Runnable> pair2 = this.next;
                MediaTimeoutListener$PlaybackStateListener$.ExternalSyntheticOutline0.m("endEntry:", (pair2 == null || (notificationEntry3 = (NotificationEntry) pair2.getFirst()) == null) ? null : notificationEntry3.getKey(), "OplusHeadsUpAvalancheControl-->", "SystemUi--Notification");
            }
            setNext(null);
        }
        String key3 = notificationEntry.getKey();
        Pair<? extends NotificationEntry, ? extends Runnable> pair3 = this.current;
        if (pair3 != null && (notificationEntry2 = (NotificationEntry) pair3.getFirst()) != null) {
            key2 = notificationEntry2.getKey();
        }
        if (Intrinsics.areEqual(key3, key2)) {
            ((NotifInflateDelayStrategy) DependencyEx.sDependency.getDependency(NotifInflateDelayStrategy.class)).stopWaitAction(1);
            if (LogUtil.getNormalDebug() && LogUtil.isTagValid(LogUtil.TAG_NOTIFICATION)) {
                Log.d("SystemUi--Notification", "OplusHeadsUpAvalancheControl-->endEntry:showNext");
            }
            showNext();
        }
    }

    public final Pair<NotificationEntry, Runnable> getNext() {
        return this.next;
    }

    public final Function0 getNextActionChanged() {
        return this.nextActionChanged;
    }

    public final void setNextActionChanged(Function0 function0) {
        this.nextActionChanged = function0;
    }

    public final void show(NotificationEntry notificationEntry, Runnable runnable) {
        if (this.current == null) {
            if (LogUtil.getNormalDebug() && LogUtil.isTagValid(LogUtil.TAG_NOTIFICATION)) {
                MediaTimeoutListener$PlaybackStateListener$.ExternalSyntheticOutline0.m("show:", NotificationUtilsKt.getLogKeyNonNull(notificationEntry), "OplusHeadsUpAvalancheControl-->", "SystemUi--Notification");
            }
            this.current = TuplesKt.to(notificationEntry, runnable);
            runnable.run();
            return;
        }
        Pair<? extends NotificationEntry, ? extends Runnable> pair = this.next;
        if (pair != null) {
            if (LogUtil.getNormalDebug() && LogUtil.isTagValid(LogUtil.TAG_NOTIFICATION)) {
                Log.d("SystemUi--Notification", SystemUIApplicationImpl$.ExternalSyntheticOutline0.m("show:next ", NotificationUtilsKt.getLogKeyNonNull((PipelineEntry) pair.getFirst()), " replace by ", NotificationUtilsKt.getLogKeyNonNull(notificationEntry), "OplusHeadsUpAvalancheControl-->"));
            }
        } else if (LogUtil.getNormalDebug() && LogUtil.isTagValid(LogUtil.TAG_NOTIFICATION)) {
            MediaTimeoutListener$PlaybackStateListener$.ExternalSyntheticOutline0.m("show:wait: ", NotificationUtilsKt.getLogKeyNonNull(notificationEntry), "OplusHeadsUpAvalancheControl-->", "SystemUi--Notification");
        }
        setNext(TuplesKt.to(notificationEntry, runnable));
    }

    public final void showNext() {
        Runnable runnable;
        NotificationEntry notificationEntry;
        if (this.next == null) {
            if (LogUtil.getNormalDebug() && LogUtil.isTagValid(LogUtil.TAG_NOTIFICATION)) {
                Log.d("SystemUi--Notification", "OplusHeadsUpAvalancheControl-->showNext:clear current");
            }
            this.current = null;
            return;
        }
        if (LogUtil.getNormalDebug() && LogUtil.isTagValid(LogUtil.TAG_NOTIFICATION)) {
            StringBuilder sb = new StringBuilder("OplusHeadsUpAvalancheControl-->");
            Pair<? extends NotificationEntry, ? extends Runnable> pair = this.next;
            sb.append("showNext:" + ((pair == null || (notificationEntry = (NotificationEntry) pair.getFirst()) == null) ? null : notificationEntry.getKey()));
            Log.d("SystemUi--Notification", sb.toString());
        }
        Pair<? extends NotificationEntry, ? extends Runnable> pair2 = this.next;
        this.current = pair2;
        if (pair2 != null && (runnable = (Runnable) pair2.getSecond()) != null) {
            runnable.run();
        }
        setNext(null);
    }
}
