package com.oplus.systemui.statusbar.notification.collection.inflation;

import android.os.Handler;
import android.util.Log;
import android.view.Choreographer;
import com.android.systemui.keyguard.OplusBlackScreenGestureCallback$;
import com.android.systemui.statusbar.notification.collection.inflation.NotifInflateDelayStrategy;
import com.oplus.keyguard.security.view.OplusKeyguardSimInputViewController$;
import com.oplusos.systemui.common.util.LogUtil;
import java.lang.invoke.VarHandle;
import java.util.LinkedList;
import kotlin.collections.CollectionsKt__MutableCollectionsKt;

/* JADX INFO: compiled from: go/retraceme fd74bf9514dd0739b92d67a6d48a51eb10aaf8df760d83496b8dcfcf83e5f698 */
/* JADX INFO: loaded from: /home/starlight/Projects/Starlight Ports/.build-tmp/classes4.dex */
public final class NotifInflateDelayStrategyImpl extends NotifInflateDelayStrategy {
    public static final Companion Companion = new Companion();
    private static final String TAG = "NotifInflateDelay";
    public int currentType;
    private final Handler handler;
    public boolean isWait;
    private final Runnable timeOut;
    private final Choreographer viewChoreographer;
    private final LinkedList<Runnable> waitTasks = new LinkedList<>();

    /* JADX INFO: compiled from: go/retraceme fd74bf9514dd0739b92d67a6d48a51eb10aaf8df760d83496b8dcfcf83e5f698 */
    public final class Companion {
    }

    public NotifInflateDelayStrategyImpl(Handler handler, Choreographer choreographer) {
        this.handler = handler;
        this.viewChoreographer = choreographer;
        NotifInflateDelayStrategyImpl$timeOut$1 notifInflateDelayStrategyImpl$timeOut$1 = new NotifInflateDelayStrategyImpl$timeOut$1();
        notifInflateDelayStrategyImpl$timeOut$1.this$0 = this;
        VarHandle.storeStoreFence();
        this.timeOut = notifInflateDelayStrategyImpl$timeOut$1;
        this.currentType = -1;
    }

    public void addTask(Runnable runnable) {
        if (this.isWait || this.waitTasks.size() > 0) {
            if (LogUtil.getNormalDebug() && LogUtil.isTagValid(LogUtil.TAG_NOTIFICATION)) {
                OplusBlackScreenGestureCallback$.ExternalSyntheticOutline0.m(this.waitTasks.size(), "addTask: needWait:", "NotifInflateDelay-->", "SystemUi--Notification");
            }
            this.waitTasks.add(runnable);
            return;
        }
        if (LogUtil.getNormalDebug() && LogUtil.isTagValid(LogUtil.TAG_NOTIFICATION)) {
            Log.d("SystemUi--Notification", "NotifInflateDelay-->addTask: noNeedWait");
        }
        this.waitTasks.add(runnable);
        checkNext();
    }

    public final void checkNext() {
        if (this.isWait) {
            if (LogUtil.getNormalDebug() && LogUtil.isTagValid(LogUtil.TAG_NOTIFICATION)) {
                Log.d("SystemUi--Notification", "NotifInflateDelay-->checkNext:has next:but wait");
                return;
            }
            return;
        }
        Runnable runnable = (Runnable) CollectionsKt__MutableCollectionsKt.removeFirstOrNull(this.waitTasks);
        if (runnable != null) {
            if (LogUtil.getNormalDebug() && LogUtil.isTagValid(LogUtil.TAG_NOTIFICATION)) {
                Log.d("SystemUi--Notification", "NotifInflateDelay-->checkNext:has next");
            }
            Choreographer choreographer = this.viewChoreographer;
            NotifInflateDelayStrategyImpl$checkNext$2$2 notifInflateDelayStrategyImpl$checkNext$2$2 = new NotifInflateDelayStrategyImpl$checkNext$2$2();
            notifInflateDelayStrategyImpl$checkNext$2$2.$task = runnable;
            notifInflateDelayStrategyImpl$checkNext$2$2.this$0 = this;
            VarHandle.storeStoreFence();
            choreographer.postFrameCallback(notifInflateDelayStrategyImpl$checkNext$2$2);
        }
    }

    public final void startWaitAction(int i) {
        long j;
        if (LogUtil.getNormalDebug() && LogUtil.isTagValid(LogUtil.TAG_NOTIFICATION)) {
            Log.d("SystemUi--Notification", OplusKeyguardSimInputViewController$.ExternalSyntheticOutline0.m("startWaitAction::current=", this.currentType, i, ", type = ", "NotifInflateDelay-->"));
        }
        if (i == 5) {
            if (this.currentType == i && LogUtil.getNormalDebug() && LogUtil.isTagValid(LogUtil.TAG_NOTIFICATION)) {
                Log.d("SystemUi--Notification", "NotifInflateDelay-->last scroll not end, new scroll start");
            }
            j = 3000;
        } else {
            j = 1000;
        }
        this.currentType = i;
        this.isWait = true;
        this.handler.removeCallbacks(this.timeOut);
        this.handler.postDelayed(this.timeOut, j);
    }

    public final void stopWaitAction(int i) {
        if (i != this.currentType) {
            if (LogUtil.getNormalDebug() && LogUtil.isTagValid(LogUtil.TAG_NOTIFICATION)) {
                Log.d("SystemUi--Notification", OplusKeyguardSimInputViewController$.ExternalSyntheticOutline0.m("stopWaitAction:: fail type = ", i, this.currentType, ",not current=", "NotifInflateDelay-->"));
                return;
            }
            return;
        }
        if (LogUtil.getNormalDebug() && LogUtil.isTagValid(LogUtil.TAG_NOTIFICATION)) {
            OplusBlackScreenGestureCallback$.ExternalSyntheticOutline0.m(i, "stopWaitAction:: type = ", "NotifInflateDelay-->", "SystemUi--Notification");
        }
        this.currentType = -1;
        this.handler.removeCallbacks(this.timeOut);
        this.isWait = false;
        checkNext();
    }
}
