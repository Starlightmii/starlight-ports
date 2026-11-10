package com.oplus.systemui.notification.lockscreen.base;

import android.os.Trace;
import androidx.appcompat.widget.ListPopupWindow$;
import androidx.room.util.CursorUtil$;
import com.android.app.displaylib.PerDisplayInstanceRepositoryImpl$;
import com.android.app.tracing.TraceStateLogger;
import com.android.systemui.util.EventLog;
import com.oplus.systemui.notification.lockscreen.ILockScreenExtStateController;
import kotlin.jvm.internal.Intrinsics;

/* JADX INFO: compiled from: go/retraceme fd74bf9514dd0739b92d67a6d48a51eb10aaf8df760d83496b8dcfcf83e5f698 */
/* JADX INFO: loaded from: /home/starlight/Projects/Starlight Ports/.build-tmp/classes4.dex */
public final class OplusCapsuleLog {
    public static final Companion Companion = new Companion();
    private final TraceStateLogger capsuleStateLogger = new TraceStateLogger("LockScreenExtState:state", false, false, false, 12);
    private ILockScreenExtStateController.ExtStateEvent currentEvent;
    private volatile String currentTransitionTraceName;
    private final EventLog eventLog;
    public volatile boolean isTransitionAnimating;

    /* JADX INFO: compiled from: go/retraceme fd74bf9514dd0739b92d67a6d48a51eb10aaf8df760d83496b8dcfcf83e5f698 */
    public final class Companion {
        public static final String access$state(Companion companion, ILockScreenExtStateController.ExtStateEvent extStateEvent) {
            companion.getClass();
            return CursorUtil$.ExternalSyntheticOutline0.m("appScene_", toExtStr(extStateEvent.getOldState()), "_to_", toExtStr(extStateEvent.getNewState()));
        }

        public static String toExtStr(int i) {
            if (i == 0) {
                return "stack";
            }
            if (i == 1) {
                return "capsule";
            }
            if (i != 2) {
                return i != 3 ? ListPopupWindow$.ExternalSyntheticOutline0.m(i, "Unknown:") : "sinking";
            }
            return "immer";
        }
    }

    public OplusCapsuleLog(EventLog eventLog) {
        this.eventLog = eventLog;
    }

    private final void setCurrentEvent(ILockScreenExtStateController.ExtStateEvent extStateEvent) {
        if (Intrinsics.areEqual(this.currentEvent, extStateEvent)) {
            return;
        }
        ILockScreenExtStateController.ExtStateEvent extStateEvent2 = this.currentEvent;
        if (extStateEvent2 != null && extStateEvent == null) {
            TraceStateLogger traceStateLogger = this.capsuleStateLogger;
            Companion companion = Companion;
            int newState = extStateEvent2.getNewState();
            companion.getClass();
            traceStateLogger.log("idle:".concat(Companion.toExtStr(newState)));
        }
        this.currentEvent = extStateEvent;
        if (extStateEvent != null) {
            this.capsuleStateLogger.log("animation:".concat(Companion.access$state(Companion, extStateEvent)));
        }
    }

    public final void endTransitionTrace() {
        if (this.isTransitionAnimating) {
            this.isTransitionAnimating = false;
            String str = this.currentTransitionTraceName;
            if (str != null) {
                Trace.asyncTraceEnd(8L, str, 600);
            }
            this.currentTransitionTraceName = null;
        }
    }

    public final void logExtStateEvent(ILockScreenExtStateController.ExtStateEvent extStateEvent) {
        EventLog eventLog = this.eventLog;
        Companion companion = Companion;
        eventLog.writeEvent(36008, new Object[]{"extStateEvent", PerDisplayInstanceRepositoryImpl$.ExternalSyntheticOutline0.m(extStateEvent.getReason(), Companion.access$state(companion, extStateEvent), ",reason:")});
        setCurrentEvent(extStateEvent);
        if (!extStateEvent.getAnimate()) {
            endTransitionTrace();
            return;
        }
        String strAccess$state = Companion.access$state(companion, extStateEvent);
        endTransitionTrace();
        this.isTransitionAnimating = true;
        this.currentTransitionTraceName = strAccess$state;
        Trace.asyncTraceBegin(8L, strAccess$state, 600);
    }

    public final void onStackAnimationEnd() {
        endTransitionTrace();
        ILockScreenExtStateController.ExtStateEvent extStateEvent = this.currentEvent;
        if (extStateEvent != null) {
            this.eventLog.writeEvent(36008, new Object[]{"extStateAnimationEnd", Companion.access$state(Companion, extStateEvent)});
            setCurrentEvent(null);
        }
    }
}
