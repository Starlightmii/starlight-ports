package com.oplus.systemui.volume.anim;

import android.util.Log;
import androidx.car.app.model.TimerSpan$;
import com.android.keyguard.OplusLockIconView$;
import com.oplus.systemui.aod.anim.OplusAODMaskAnimController$;
import com.oplusos.systemui.common.util.LogUtil;
import kotlin.enums.EnumEntries;
import kotlin.enums.EnumEntriesKt;
import kotlin.jvm.functions.Function0;

/* JADX INFO: compiled from: go/retraceme fd74bf9514dd0739b92d67a6d48a51eb10aaf8df760d83496b8dcfcf83e5f698 */
/* JADX INFO: loaded from: /home/starlight/Projects/Starlight Ports/.build-tmp/classes4.dex */
public final class OplusVolumeSideShowSession {
    public static final Companion Companion = new Companion();
    private static final String TAG = "OplusVolumeSideShowSession";
    public long enterGeneration;
    public boolean pendingStructuralUpdate;
    private Phase phase = Phase.IDLE;

    /* JADX INFO: compiled from: go/retraceme fd74bf9514dd0739b92d67a6d48a51eb10aaf8df760d83496b8dcfcf83e5f698 */
    public final class Companion {
    }

    /* JADX WARN: Failed to restore enum class, 'enum' modifier and super class removed */
    /* JADX WARN: Unknown enum class pattern. Please report as an issue! */
    /* JADX INFO: compiled from: go/retraceme fd74bf9514dd0739b92d67a6d48a51eb10aaf8df760d83496b8dcfcf83e5f698 */
    public final class Phase {
        public static final /* synthetic */ EnumEntries $ENTRIES;
        public static final /* synthetic */ Phase[] $VALUES;
        public static final Phase ENTERING;
        public static final Phase EXITING;
        public static final Phase IDLE;
        public static final Phase VISIBLE;

        static {
            Phase phase = new Phase("IDLE", 0);
            IDLE = phase;
            Phase phase2 = new Phase("ENTERING", 1);
            ENTERING = phase2;
            Phase phase3 = new Phase("VISIBLE", 2);
            VISIBLE = phase3;
            Phase phase4 = new Phase("EXITING", 3);
            EXITING = phase4;
            Phase[] phaseArr = {phase, phase2, phase3, phase4};
            $VALUES = phaseArr;
            $ENTRIES = EnumEntriesKt.enumEntries(phaseArr);
        }

        public static EnumEntries getEntries() {
            return $ENTRIES;
        }

        public static Phase valueOf(String str) {
            return (Phase) Enum.valueOf(Phase.class, str);
        }

        public static Phase[] values() {
            return (Phase[]) $VALUES.clone();
        }
    }

    public final long beginEnter() {
        this.enterGeneration++;
        this.phase = Phase.ENTERING;
        this.pendingStructuralUpdate = false;
        if (LogUtil.getNormalDebug() && LogUtil.isTagValid(LogUtil.TAG_VOLUME)) {
            OplusAODMaskAnimController$.ExternalSyntheticOutline0.m(this.enterGeneration, "beginEnter generation=", "OplusVolumeSideShowSession-->", "SystemUi--Volume");
        }
        return this.enterGeneration;
    }

    public final void beginExit() {
        this.enterGeneration++;
        this.phase = Phase.EXITING;
        this.pendingStructuralUpdate = false;
        if (LogUtil.getNormalDebug() && LogUtil.isTagValid(LogUtil.TAG_VOLUME)) {
            Log.d("SystemUi--Volume", "OplusVolumeSideShowSession-->beginExit");
        }
    }

    public final void enqueueStructuralUpdate() {
        this.pendingStructuralUpdate = true;
        if (LogUtil.getNormalDebug() && LogUtil.isTagValid(LogUtil.TAG_VOLUME)) {
            Log.d("SystemUi--Volume", "OplusVolumeSideShowSession-->".concat("enqueueStructuralUpdate phase=" + this.phase));
        }
    }

    public final void flushPendingStructuralUpdate(Function0 function0) {
        if (this.pendingStructuralUpdate && this.phase == Phase.VISIBLE && ((Boolean) function0.invoke()).booleanValue()) {
            this.pendingStructuralUpdate = false;
        }
    }

    public final Phase getPhase() {
        return this.phase;
    }

    public final boolean markEnterCompleted(long j, Function0 function0) {
        if (j == this.enterGeneration && this.phase == Phase.ENTERING) {
            this.phase = Phase.VISIBLE;
            if (LogUtil.getNormalDebug() && LogUtil.isTagValid(LogUtil.TAG_VOLUME)) {
                OplusLockIconView$.ExternalSyntheticOutline0.m("markEnterCompleted pending=", "OplusVolumeSideShowSession-->", "SystemUi--Volume", this.pendingStructuralUpdate);
            }
            if (!this.pendingStructuralUpdate || !((Boolean) function0.invoke()).booleanValue()) {
                return true;
            }
            this.pendingStructuralUpdate = false;
            return true;
        }
        if (LogUtil.getNormalDebug() && LogUtil.isTagValid(LogUtil.TAG_VOLUME)) {
            long j2 = this.enterGeneration;
            Phase phase = this.phase;
            StringBuilder sbM = TimerSpan$.ExternalSyntheticOutline0.m(j, "ignore stale enter completion generation=", " current=");
            sbM.append(j2);
            sbM.append(" phase=");
            sbM.append(phase);
            Log.d("SystemUi--Volume", "OplusVolumeSideShowSession-->".concat(sbM.toString()));
        }
        return false;
    }

    public final void reset() {
        this.enterGeneration++;
        this.phase = Phase.IDLE;
        this.pendingStructuralUpdate = false;
        if (LogUtil.getNormalDebug() && LogUtil.isTagValid(LogUtil.TAG_VOLUME)) {
            Log.d("SystemUi--Volume", "OplusVolumeSideShowSession-->reset");
        }
    }

    public final boolean shouldDeferStructuralSubmit() {
        return this.phase == Phase.ENTERING;
    }
}
