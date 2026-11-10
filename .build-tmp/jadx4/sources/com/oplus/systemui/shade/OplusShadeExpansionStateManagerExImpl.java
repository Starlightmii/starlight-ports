package com.oplus.systemui.shade;

import android.os.Trace;
import androidx.compose.animation.ChangeSize$;
import androidx.compose.animation.core.MonoSpline$;
import androidx.compose.material3.internal.TextFieldImplKt$;
import androidx.room.util.CursorUtil$;
import com.android.launcher3.icons.IconProvider$;
import com.android.systemui.plugins.statusbar.StatusBarStateController;
import com.android.systemui.shade.OplusShadeExpansionStateManagerEx;
import com.android.systemui.shade.PanelType;
import com.android.systemui.shade.ShadeExpansionChangeEvent;
import com.android.systemui.shade.ShadeExpansionStateManagerKt;
import com.android.systemui.statusbar.notification.headsup.HeadsUpManager;
import com.android.systemui.statusbar.notification.helper.NotificationApplyDelayHelper;
import com.oplus.systemui.notification.NotificationExtFlag;
import com.oplus.systemui.notification.aon.AonAuthManager;
import com.oplus.systemui.notification.interruption.fullscreenbanner.FullScreenBannerHelper;
import com.oplus.systemui.qs.base.res.util.QSConstant;
import com.oplusos.systemui.common.util.LogUtil;
import dagger.Lazy;
import kotlin.LazyKt__LazyJVMKt$;
import kotlin.jvm.functions.Function1;
import okio.Path$;

/* JADX INFO: compiled from: go/retraceme fd74bf9514dd0739b92d67a6d48a51eb10aaf8df760d83496b8dcfcf83e5f698 */
/* JADX INFO: loaded from: /home/starlight/Projects/Starlight Ports/.build-tmp/classes4.dex */
public final class OplusShadeExpansionStateManagerExImpl extends OplusShadeExpansionStateManagerEx {
    public static final Companion Companion = new Companion();
    private static final String TAG = "OplusShadeExpansionStat";
    private static final String TRACK_NAME = "ShadeExpansionState";
    private final Lazy aonAuthManager;
    private final Lazy fullScreenBannerHelper;
    private final Lazy headsUpManager;
    private final Lazy notificationApplyDelayHelper;
    private final Lazy statusBarStateController;
    private final PanelExpansionState panelExpansionState = new PanelExpansionState(PanelType.SHADE_PANEL, 0, false, 0.0f, false, 0.0f);
    private final PanelExpansionState notificationPanelExpansionState = new PanelExpansionState(PanelType.NOTIFICATION_PANEL, 0, false, 0.0f, false, 0.0f);
    private final PanelExpansionState qsPanelExpansionState = new PanelExpansionState(PanelType.QS_PANEL, 0, false, 0.0f, false, 0.0f);

    /* JADX INFO: compiled from: go/retraceme fd74bf9514dd0739b92d67a6d48a51eb10aaf8df760d83496b8dcfcf83e5f698 */
    public final class Companion {
    }

    /* JADX INFO: compiled from: go/retraceme fd74bf9514dd0739b92d67a6d48a51eb10aaf8df760d83496b8dcfcf83e5f698 */
    public final class PanelExpansionState {
        public float dragDownPxAmount;
        public boolean expanded;
        public float fraction;
        public int state;
        public boolean tracking;
        private PanelType type;

        public PanelExpansionState(PanelType panelType, int i, boolean z, float f, boolean z2, float f2) {
            this.type = panelType;
            this.state = i;
            this.expanded = z;
            this.fraction = f;
            this.tracking = z2;
            this.dragDownPxAmount = f2;
        }

        public final PanelType component1() {
            return this.type;
        }

        public final PanelExpansionState copy(PanelType panelType, int i, boolean z, float f, boolean z2, float f2) {
            return new PanelExpansionState(panelType, i, z, f, z2, f2);
        }

        public boolean equals(Object obj) {
            if (this == obj) {
                return true;
            }
            if (!(obj instanceof PanelExpansionState)) {
                return false;
            }
            PanelExpansionState panelExpansionState = (PanelExpansionState) obj;
            return this.type == panelExpansionState.type && this.state == panelExpansionState.state && this.expanded == panelExpansionState.expanded && Float.compare(this.fraction, panelExpansionState.fraction) == 0 && this.tracking == panelExpansionState.tracking && Float.compare(this.dragDownPxAmount, panelExpansionState.dragDownPxAmount) == 0;
        }

        public final float getDragDownPxAmount() {
            return this.dragDownPxAmount;
        }

        public final boolean getExpanded() {
            return this.expanded;
        }

        public final float getFraction() {
            return this.fraction;
        }

        public final int getState() {
            return this.state;
        }

        public final boolean getTracking() {
            return this.tracking;
        }

        public final PanelType getType() {
            return this.type;
        }

        public final int hashCode() {
            return Float.hashCode(this.dragDownPxAmount) + ChangeSize$.ExternalSyntheticOutline0.m(ChangeSize$.ExternalSyntheticOutline0.m(this.fraction, ChangeSize$.ExternalSyntheticOutline0.m(MonoSpline$.ExternalSyntheticOutline0.m(this.state, this.type.hashCode() * 31, 31), 31, this.expanded), 31), 31, this.tracking);
        }

        public final void setDragDownPxAmount(float f) {
            this.dragDownPxAmount = f;
        }

        public final void setExpanded(boolean z) {
            this.expanded = z;
        }

        public final void setFraction(float f) {
            this.fraction = f;
        }

        public final void setState(int i) {
            this.state = i;
        }

        public final void setTracking(boolean z) {
            this.tracking = z;
        }

        public final void setType(PanelType panelType) {
            this.type = panelType;
        }

        public String toString() {
            return "PanelExpansionState(type=" + this.type + ", state=" + this.state + ", expanded=" + this.expanded + ", fraction=" + this.fraction + ", tracking=" + this.tracking + ", dragDownPxAmount=" + this.dragDownPxAmount + ")";
        }
    }

    public OplusShadeExpansionStateManagerExImpl(Lazy lazy, Lazy lazy2, Lazy lazy3, Lazy lazy4, Lazy lazy5) {
        this.aonAuthManager = lazy;
        this.fullScreenBannerHelper = lazy2;
        this.headsUpManager = lazy3;
        this.statusBarStateController = lazy4;
        this.notificationApplyDelayHelper = lazy5;
    }

    public final PanelExpansionState getExpansionState(PanelType panelType) {
        int iOrdinal = panelType.ordinal();
        if (iOrdinal == 0) {
            return this.notificationPanelExpansionState;
        }
        if (iOrdinal == 1) {
            return this.qsPanelExpansionState;
        }
        if (iOrdinal == 2) {
            return this.panelExpansionState;
        }
        LazyKt__LazyJVMKt$.ExternalSyntheticBUOutline0.m();
        return null;
    }

    public ShadeExpansionChangeEvent getShadeExpansionChangeEvent() {
        return new ShadeExpansionChangeEvent(this.panelExpansionState.getFraction(), this.panelExpansionState.getExpanded(), this.panelExpansionState.getTracking());
    }

    public boolean isShadeClosed(PanelType panelType, int i) {
        return getExpansionState(panelType).getState() == 0;
    }

    public boolean onPanelExpansionChanged(float f, boolean z, int i, boolean z2, String str, PanelType panelType, Function1 function1, Function1 function2, Function1 function3) {
        if (Float.isNaN(f)) {
            Path$.ExternalSyntheticBUOutline0.m("fraction cannot be NaN");
            return false;
        }
        if (panelType == PanelType.NOTIFICATION_PANEL) {
            boolean expanded = this.notificationPanelExpansionState.getExpanded();
            int state = this.notificationPanelExpansionState.getState();
            boolean tracking = this.notificationPanelExpansionState.getTracking();
            if (expanded != z) {
                StringBuilder sbM = TextFieldImplKt$.ExternalSyntheticOutline0.m("panelExpansionChanged expanded:", ",mTracking:", ",mState:", z, tracking);
                IconProvider$.ExternalSyntheticOutline0.m(f, state, ",fraction:", ",reason:", sbM);
                sbM.append(str);
                LogUtil.i$default(LogUtil.TAG_NOTIFICATION, TAG, sbM.toString(), null, 8);
                if (!NotificationExtFlag.OPLUS_HEADSUP && ((AonAuthManager) this.aonAuthManager.get()).isAonEnable() && ((StatusBarStateController) this.statusBarStateController.get()).getState() == 0 && ((z && !((HeadsUpManager) this.headsUpManager.get()).hasPinnedHeadsUp()) || !z)) {
                    ((AonAuthManager) this.aonAuthManager.get()).closeCamera(false);
                }
                if (z && !((HeadsUpManager) this.headsUpManager.get()).hasPinnedHeadsUp()) {
                    ((NotificationApplyDelayHelper) this.notificationApplyDelayHelper.get()).setSeedlingAnimatingState(false);
                }
                ((FullScreenBannerHelper) this.fullScreenBannerHelper.get()).setPanelExpanded(z);
            }
        }
        updatePanelExpansionState(getExpansionState(panelType), f, z, z2, this.notificationPanelExpansionState.getDragDownPxAmount(), function1, function2);
        PanelExpansionState panelExpansionState = this.panelExpansionState;
        float fraction = this.qsPanelExpansionState.getFraction();
        float fraction2 = this.notificationPanelExpansionState.getFraction();
        if (fraction < fraction2) {
            fraction = fraction2;
        }
        boolean z3 = this.qsPanelExpansionState.getExpanded() || this.notificationPanelExpansionState.getExpanded();
        boolean z4 = this.qsPanelExpansionState.getTracking() || this.notificationPanelExpansionState.getTracking();
        float dragDownPxAmount = this.qsPanelExpansionState.getDragDownPxAmount();
        float dragDownPxAmount2 = this.notificationPanelExpansionState.getDragDownPxAmount();
        if (dragDownPxAmount < dragDownPxAmount2) {
            dragDownPxAmount = dragDownPxAmount2;
        }
        updatePanelExpansionState(panelExpansionState, fraction, z3, z4, dragDownPxAmount, function1, function2);
        function3.invoke(new ShadeExpansionChangeEvent(this.panelExpansionState.getFraction(), this.panelExpansionState.getExpanded(), this.panelExpansionState.getTracking()));
        return true;
    }

    public boolean onShadeExpansionFullyChanged(boolean z, PanelType panelType, Function1 function1) {
        getExpansionState(panelType).setExpanded(z);
        this.panelExpansionState.setExpanded(this.qsPanelExpansionState.getExpanded() || this.notificationPanelExpansionState.getExpanded());
        function1.invoke(Boolean.valueOf(this.panelExpansionState.getExpanded()));
        return true;
    }

    public final void updatePanelExpansionState(PanelExpansionState panelExpansionState, float f, boolean z, boolean z2, float f2, Function1 function1, Function1 function2) {
        boolean z3;
        boolean z4;
        int state = panelExpansionState.getState();
        panelExpansionState.setFraction(f);
        panelExpansionState.setExpanded(z);
        panelExpansionState.setTracking(z2);
        panelExpansionState.setDragDownPxAmount(f2);
        if (z) {
            if (panelExpansionState.getState() == 0) {
                updateStateInternal(panelExpansionState, 1, function1, function2);
            }
            z4 = f >= 1.0f;
            z3 = false;
        } else {
            z3 = true;
            z4 = false;
        }
        if (z4 && !z2) {
            updateStateInternal(panelExpansionState, 2, function1, function2);
        } else if (z3 && !z2 && panelExpansionState.getState() != 0) {
            updateStateInternal(panelExpansionState, 0, function1, function2);
        }
        if (Trace.isTagEnabled(4096L)) {
            Trace.traceCounter(4096L, "panel_expansion", (int) (f * 100.0f));
            if (panelExpansionState.getState() != state) {
                Trace.asyncTraceForTrackEnd(4096L, TRACK_NAME, 0);
                Trace.asyncTraceForTrackBegin(4096L, TRACK_NAME, ShadeExpansionStateManagerKt.panelStateToString(panelExpansionState.getState()), 0);
            }
        }
    }

    public boolean updateState(PanelType panelType, int i, Function1 function1, Function1 function2) {
        updateStateInternal(getExpansionState(panelType), i, function1, function2);
        PanelExpansionState panelExpansionState = this.panelExpansionState;
        int state = this.notificationPanelExpansionState.getState();
        int state2 = this.qsPanelExpansionState.getState();
        int i2 = 2;
        if (state != 2 && state2 != 2) {
            i2 = (state == 1 || state2 == 1) ? 1 : 0;
        }
        updateStateInternal(panelExpansionState, i2, function1, function2);
        return true;
    }

    public final void updateStateInternal(PanelExpansionState panelExpansionState, int i, Function1 function1, Function1 function2) {
        if (i == panelExpansionState.getState()) {
            return;
        }
        LogUtil.i$default(LogUtil.TAG_COMMON, TAG, CursorUtil$.ExternalSyntheticOutline0.m("go state: ", ShadeExpansionStateManagerKt.panelStateToString(panelExpansionState.getState()), " -> ", ShadeExpansionStateManagerKt.panelStateToString(i)), null, 8);
        panelExpansionState.setState(i);
        if (panelExpansionState.equals(this.panelExpansionState)) {
            function1.invoke(Integer.valueOf(panelExpansionState.getState()));
            QSConstant.setQsTileAnimationAllowed(panelExpansionState.getState() != 1);
        }
        if (panelExpansionState.equals(this.notificationPanelExpansionState)) {
            function2.invoke(Integer.valueOf(panelExpansionState.getState()));
        }
    }
}
