package com.oplus.systemui.volume.anim;

import android.content.Context;
import android.os.SystemClock;
import com.oplus.systemui.volume.utils.OplusVolumePanelTiming;
import kotlin.enums.EnumEntries;
import kotlin.enums.EnumEntriesKt;
import kotlin.ranges.RangesKt___RangesKt;

/* JADX INFO: compiled from: go/retraceme fd74bf9514dd0739b92d67a6d48a51eb10aaf8df760d83496b8dcfcf83e5f698 */
/* JADX INFO: loaded from: /home/starlight/Projects/Starlight Ports/.build-tmp/classes4.dex */
public final class PanelTransitionSession {
    public long accessoryFadeDurationMs;
    public long blurFadeDurationMs;
    public boolean expandCommitted;
    public long expandStartUptimeMs;
    public long hideDurationMs;
    public long iconFadeDurationMs;
    public boolean rowHandoffActive;
    public boolean useLowLevelPanelHideTiming;
    private Phase phase = Phase.IDLE;
    private PanelCollapseMode collapseMode = PanelCollapseMode.NORMAL_PANEL;
    public float interruptDurationRatio = 1.0f;

    /* JADX WARN: Failed to restore enum class, 'enum' modifier and super class removed */
    /* JADX WARN: Unknown enum class pattern. Please report as an issue! */
    /* JADX INFO: compiled from: go/retraceme fd74bf9514dd0739b92d67a6d48a51eb10aaf8df760d83496b8dcfcf83e5f698 */
    public final class Phase {
        public static final /* synthetic */ EnumEntries $ENTRIES;
        public static final /* synthetic */ Phase[] $VALUES;
        public static final Phase COLLAPSING;
        public static final Phase EXPANDED;
        public static final Phase EXPANDING;
        public static final Phase HANDOFF;
        public static final Phase IDLE;

        static {
            Phase phase = new Phase("IDLE", 0);
            IDLE = phase;
            Phase phase2 = new Phase("EXPANDING", 1);
            EXPANDING = phase2;
            Phase phase3 = new Phase("EXPANDED", 2);
            EXPANDED = phase3;
            Phase phase4 = new Phase("HANDOFF", 3);
            HANDOFF = phase4;
            Phase phase5 = new Phase("COLLAPSING", 4);
            COLLAPSING = phase5;
            Phase[] phaseArr = {phase, phase2, phase3, phase4, phase5};
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

    public static void markExpandStarted$default(PanelTransitionSession panelTransitionSession) {
        long jUptimeMillis = SystemClock.uptimeMillis();
        panelTransitionSession.getClass();
        panelTransitionSession.phase = Phase.EXPANDING;
        panelTransitionSession.expandStartUptimeMs = jUptimeMillis;
        panelTransitionSession.collapseMode = PanelCollapseMode.NORMAL_PANEL;
        panelTransitionSession.expandCommitted = false;
        panelTransitionSession.interruptDurationRatio = 1.0f;
        panelTransitionSession.hideDurationMs = 0L;
        panelTransitionSession.accessoryFadeDurationMs = 0L;
        panelTransitionSession.blurFadeDurationMs = 0L;
        panelTransitionSession.iconFadeDurationMs = 0L;
        panelTransitionSession.useLowLevelPanelHideTiming = false;
        panelTransitionSession.rowHandoffActive = false;
    }

    public final long getAccessoryFadeDurationMs() {
        return this.accessoryFadeDurationMs;
    }

    public final long getBlurFadeDurationMs() {
        return this.blurFadeDurationMs;
    }

    public final PanelCollapseMode getCollapseMode() {
        return this.collapseMode;
    }

    public final boolean getExpandCommitted() {
        return this.expandCommitted;
    }

    public final long getExpandStartUptimeMs() {
        return this.expandStartUptimeMs;
    }

    public final long getHideDurationMs() {
        return this.hideDurationMs;
    }

    public final long getIconFadeDurationMs() {
        return this.iconFadeDurationMs;
    }

    public final float getInterruptDurationRatio() {
        return this.interruptDurationRatio;
    }

    public final Phase getPhase() {
        return this.phase;
    }

    public final boolean getUseLowLevelPanelHideTiming() {
        return this.useLowLevelPanelHideTiming;
    }

    public final boolean isNormalPanelCollapseMode() {
        return this.collapseMode == PanelCollapseMode.NORMAL_PANEL;
    }

    public final boolean isRowInterruptCollapse() {
        if (!this.rowHandoffActive) {
            return false;
        }
        Phase phase = this.phase;
        return phase == Phase.HANDOFF || phase == Phase.COLLAPSING;
    }

    public final void markCollapsing() {
        Phase phase = this.phase;
        if (phase == Phase.HANDOFF || phase == Phase.EXPANDING || phase == Phase.EXPANDED) {
            this.phase = Phase.COLLAPSING;
        }
    }

    public final void markExpandCompleted() {
        if (this.phase == Phase.EXPANDING) {
            this.phase = Phase.EXPANDED;
        }
    }

    public final void planCollapse(Context context, int i, boolean z, boolean z2, boolean z3, long j) {
        PanelCollapseMode panelCollapseMode;
        long jResolveInterruptPanelHideDurationMs;
        long j2;
        if (i < 1) {
            i = 1;
        }
        this.useLowLevelPanelHideTiming = z3;
        long j3 = j - this.expandStartUptimeMs;
        if (j3 < 0) {
            j3 = 0;
        }
        long jResolvePanelExpandReferenceDurationMs = OplusVolumePanelTiming.resolvePanelExpandReferenceDurationMs(context);
        if (this.phase == Phase.EXPANDING && !z) {
            markExpandCompleted();
        }
        boolean z4 = z2 || (j3 >= jResolvePanelExpandReferenceDurationMs && !z && this.phase == Phase.EXPANDED);
        this.expandCommitted = z4;
        if (z) {
            panelCollapseMode = z4 ? PanelCollapseMode.HANDOFF : PanelCollapseMode.CANCEL_EXPAND;
        } else {
            panelCollapseMode = PanelCollapseMode.NORMAL_PANEL;
        }
        this.collapseMode = panelCollapseMode;
        PanelCollapseMode panelCollapseMode2 = PanelCollapseMode.NORMAL_PANEL;
        if (panelCollapseMode == panelCollapseMode2) {
            j3 = 0;
        }
        float fCoerceIn = 1.0f;
        if (panelCollapseMode != panelCollapseMode2) {
            fCoerceIn = jResolvePanelExpandReferenceDurationMs <= 0 ? 0.4f : RangesKt___RangesKt.coerceIn((RangesKt___RangesKt.coerceIn(j3 / jResolvePanelExpandReferenceDurationMs, 0.0f, 1.0f) * 0.6f) + 0.4f, 0.4f, 1.0f);
        }
        this.interruptDurationRatio = fCoerceIn;
        PanelCollapseMode panelCollapseMode3 = this.collapseMode;
        long j4 = 300;
        if (!z3) {
            jResolveInterruptPanelHideDurationMs = panelCollapseMode3 != panelCollapseMode2 ? OplusVolumePanelTiming.resolveInterruptPanelHideDurationMs(context, i, fCoerceIn) : OplusVolumePanelTiming.getPanelHideDurationMs(context, i);
        } else if (panelCollapseMode3 != panelCollapseMode2) {
            if (i < 1) {
                i = 1;
            }
            int i2 = i - 1;
            if (i2 < 0) {
                i2 = 0;
            }
            jResolveInterruptPanelHideDurationMs = (long) ((Math.max(100L, 433L) + (((long) i2) * 15)) * this.interruptDurationRatio);
            if (jResolveInterruptPanelHideDurationMs < 300) {
                jResolveInterruptPanelHideDurationMs = 300;
            }
        } else {
            if (i < 1) {
                i = 1;
            }
            int i3 = i - 1;
            if (i3 < 0) {
                i3 = 0;
            }
            jResolveInterruptPanelHideDurationMs = (((long) i3) * 15) + Math.max(100L, 433L);
        }
        this.hideDurationMs = jResolveInterruptPanelHideDurationMs;
        PanelCollapseMode panelCollapseMode4 = this.collapseMode;
        boolean z5 = panelCollapseMode4 != panelCollapseMode2;
        float f = this.interruptDurationRatio;
        if (z5) {
            j2 = (long) (150.0f * f);
            if (j2 < 100) {
                j2 = 100;
            }
        } else {
            j2 = 150;
        }
        this.accessoryFadeDurationMs = j2;
        if (z5) {
            long j5 = (long) (jResolveInterruptPanelHideDurationMs * f);
            if (j5 >= 300) {
                j4 = j5;
            }
        } else {
            j4 = jResolveInterruptPanelHideDurationMs;
        }
        this.blurFadeDurationMs = j4;
        if (z5) {
            long j6 = (long) (350.0f * f);
            jResolveInterruptPanelHideDurationMs = j6 >= 100 ? j6 : 100L;
        }
        this.iconFadeDurationMs = jResolveInterruptPanelHideDurationMs;
        this.rowHandoffActive = z5;
        this.phase = panelCollapseMode4 != panelCollapseMode2 ? Phase.HANDOFF : Phase.COLLAPSING;
    }

    public final void reset() {
        this.phase = Phase.IDLE;
        this.expandStartUptimeMs = 0L;
        this.collapseMode = PanelCollapseMode.NORMAL_PANEL;
        this.expandCommitted = false;
        this.interruptDurationRatio = 1.0f;
        this.hideDurationMs = 0L;
        this.accessoryFadeDurationMs = 0L;
        this.blurFadeDurationMs = 0L;
        this.iconFadeDurationMs = 0L;
        this.useLowLevelPanelHideTiming = false;
        this.rowHandoffActive = false;
    }

    public final boolean shouldDeferClearRowHandoff(boolean z, boolean z2) {
        if (!this.rowHandoffActive) {
            return false;
        }
        int iOrdinal = this.phase.ordinal();
        if (iOrdinal != 3) {
            if (iOrdinal != 4) {
                return false;
            }
            if (!z && !z2) {
                return false;
            }
        }
        return true;
    }
}
