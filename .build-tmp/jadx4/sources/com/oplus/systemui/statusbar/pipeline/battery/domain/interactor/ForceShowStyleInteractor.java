package com.oplus.systemui.statusbar.pipeline.battery.domain.interactor;

import androidx.compose.animation.ChangeSize$;
import kotlin.TuplesKt;
import kotlin.jvm.internal.Intrinsics;
import kotlinx.coroutines.flow.Flow;
import kotlinx.coroutines.flow.FlowKt;
import kotlinx.coroutines.flow.MutableStateFlow;
import kotlinx.coroutines.flow.StateFlow;
import kotlinx.coroutines.flow.StateFlowKt;

/* JADX INFO: compiled from: go/retraceme fd74bf9514dd0739b92d67a6d48a51eb10aaf8df760d83496b8dcfcf83e5f698 */
/* JADX INFO: loaded from: /home/starlight/Projects/Starlight Ports/.build-tmp/classes4.dex */
public final class ForceShowStyleInteractor {
    public final boolean canWriteForceStyle;
    private final Flow filteredForceStyle;
    private final MutableStateFlow forceShowStyle;
    private final Flow isFiltered;
    private final StateFlow isPanelExpanded;

    public ForceShowStyleInteractor(MutableStateFlow mutableStateFlow, Flow flow, boolean z, StateFlow stateFlow) {
        this.forceShowStyle = mutableStateFlow;
        this.isFiltered = flow;
        this.canWriteForceStyle = z;
        this.isPanelExpanded = stateFlow;
        this.filteredForceStyle = FlowKt.distinctUntilChanged(FlowKt.conflate(FlowKt.filterNotNull(com.android.systemui.util.kotlin.FlowKt.pairwiseBy(FlowKt.flowCombine(mutableStateFlow, flow, new ForceShowStyleInteractor$filteredForceStyle$1(3, null)), TuplesKt.to(0, Boolean.FALSE), new ForceShowStyleInteractor$filteredForceStyle$2(3, null)))));
    }

    public final MutableStateFlow component1() {
        return this.forceShowStyle;
    }

    public final Flow component2() {
        return this.isFiltered;
    }

    public final StateFlow component4() {
        return this.isPanelExpanded;
    }

    public final ForceShowStyleInteractor copy(MutableStateFlow mutableStateFlow, Flow flow, boolean z, StateFlow stateFlow) {
        return new ForceShowStyleInteractor(mutableStateFlow, flow, z, stateFlow);
    }

    public boolean equals(Object obj) {
        if (this == obj) {
            return true;
        }
        if (!(obj instanceof ForceShowStyleInteractor)) {
            return false;
        }
        ForceShowStyleInteractor forceShowStyleInteractor = (ForceShowStyleInteractor) obj;
        return Intrinsics.areEqual(this.forceShowStyle, forceShowStyleInteractor.forceShowStyle) && Intrinsics.areEqual(this.isFiltered, forceShowStyleInteractor.isFiltered) && this.canWriteForceStyle == forceShowStyleInteractor.canWriteForceStyle && Intrinsics.areEqual(this.isPanelExpanded, forceShowStyleInteractor.isPanelExpanded);
    }

    public final boolean getCanWriteForceStyle() {
        return this.canWriteForceStyle;
    }

    public final Flow getFilteredForceStyle() {
        return this.filteredForceStyle;
    }

    public final MutableStateFlow getForceShowStyle() {
        return this.forceShowStyle;
    }

    public final int hashCode() {
        return this.isPanelExpanded.hashCode() + ChangeSize$.ExternalSyntheticOutline0.m((this.isFiltered.hashCode() + (this.forceShowStyle.hashCode() * 31)) * 31, 31, this.canWriteForceStyle);
    }

    public final Flow isFiltered() {
        return this.isFiltered;
    }

    public final StateFlow isPanelExpanded() {
        return this.isPanelExpanded;
    }

    public String toString() {
        return "ForceShowStyleInteractor(forceShowStyle=" + this.forceShowStyle + ", isFiltered=" + this.isFiltered + ", canWriteForceStyle=" + this.canWriteForceStyle + ", isPanelExpanded=" + this.isPanelExpanded + ")";
    }

    public /* synthetic */ ForceShowStyleInteractor(MutableStateFlow mutableStateFlow, Flow flow, int i) {
        this((i & 1) != 0 ? StateFlowKt.MutableStateFlow(0) : mutableStateFlow, (i & 2) != 0 ? StateFlowKt.MutableStateFlow(Boolean.FALSE) : flow, (i & 4) != 0, StateFlowKt.MutableStateFlow(Boolean.FALSE));
    }
}
