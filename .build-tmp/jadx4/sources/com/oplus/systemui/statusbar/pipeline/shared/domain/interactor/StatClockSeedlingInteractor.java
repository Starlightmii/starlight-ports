package com.oplus.systemui.statusbar.pipeline.shared.domain.interactor;

import com.android.systemui.statusbar.policy.ShouldShowForDisplay;
import com.oplus.systemui.statusbar.pipeline.shared.domain.model.StatClockSeedlingState;
import com.oplus.systemui.statusbar.seeding.HiddenIconDispatcher;
import kotlinx.coroutines.CoroutineScope;
import kotlinx.coroutines.flow.FlowKt;
import kotlinx.coroutines.flow.SharingStarted;
import kotlinx.coroutines.flow.StateFlow;

/* JADX INFO: compiled from: go/retraceme fd74bf9514dd0739b92d67a6d48a51eb10aaf8df760d83496b8dcfcf83e5f698 */
/* JADX INFO: loaded from: /home/starlight/Projects/Starlight Ports/.build-tmp/classes4.dex */
public final class StatClockSeedlingInteractor {
    public static final Companion Companion = new Companion();
    private static final String TAG = "StatClockSeedlingInteractor";
    private final HiddenIconDispatcher hiddenIconDispatcher;
    private final CoroutineScope scope;
    private final StateFlow seedlingState;
    private final ShouldShowForDisplay shouldShowForDisplay;

    /* JADX INFO: compiled from: go/retraceme fd74bf9514dd0739b92d67a6d48a51eb10aaf8df760d83496b8dcfcf83e5f698 */
    public final class Companion {
    }

    public StatClockSeedlingInteractor(HiddenIconDispatcher hiddenIconDispatcher, ShouldShowForDisplay shouldShowForDisplay, CoroutineScope coroutineScope) {
        this.hiddenIconDispatcher = hiddenIconDispatcher;
        this.shouldShowForDisplay = shouldShowForDisplay;
        this.scope = coroutineScope;
        this.seedlingState = !shouldShowForDisplay.getShowSeedling() ? FlowKt.stateIn(FlowKt.flowOf(new StatClockSeedlingState()), coroutineScope, SharingStarted.Companion.WhileSubscribed$default(SharingStarted.Companion, 0L, 3), new StatClockSeedlingState()) : FlowKt.stateIn(FlowKt.callbackFlow(new StatClockSeedlingInteractor$createSeedlingStateFlow$1(this, null)), coroutineScope, SharingStarted.Companion.WhileSubscribed$default(SharingStarted.Companion, 0L, 3), new StatClockSeedlingState());
    }

    public final StateFlow getSeedlingState() {
        return this.seedlingState;
    }
}
