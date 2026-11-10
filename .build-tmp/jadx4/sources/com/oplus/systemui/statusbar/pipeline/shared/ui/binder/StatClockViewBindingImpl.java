package com.oplus.systemui.statusbar.pipeline.shared.ui.binder;

import com.oplus.systemui.statusbar.pipeline.shared.ui.viewmodel.OplusHomeStatusBarViewModel;
import kotlin.Pair;

/* JADX INFO: compiled from: go/retraceme fd74bf9514dd0739b92d67a6d48a51eb10aaf8df760d83496b8dcfcf83e5f698 */
/* JADX INFO: loaded from: /home/starlight/Projects/Starlight Ports/.build-tmp/classes4.dex */
public final class StatClockViewBindingImpl implements StatClockViewBinding {
    private final OplusHomeStatusBarViewModel viewModel;

    public StatClockViewBindingImpl(OplusHomeStatusBarViewModel oplusHomeStatusBarViewModel) {
        this.viewModel = oplusHomeStatusBarViewModel;
    }

    @Override // com.oplus.systemui.statusbar.pipeline.shared.ui.binder.StatClockViewBinding
    public void onLayoutAreasChanged(Pair<Integer, Integer> pair, Pair<Integer, Integer> pair2) {
        this.viewModel.notifyClockLayoutAreasChanged(pair, pair2);
    }
}
