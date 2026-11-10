package com.oplus.systemui.plugins.qs.quickentrance.viewmodel;

import com.oplus.systemui.plugins.qs.OplusQSSpecialModeProvider;
import com.oplus.systemui.plugins.qs.quickentrance.domain.interactor.OplusSeparateSettingsEntranceInteractor;
import com.oplus.systemui.plugins.qs.quickentrance.repository.OplusQSQuickEntranceRepository;
import kotlinx.coroutines.flow.Flow;

/* JADX INFO: compiled from: go/retraceme fd74bf9514dd0739b92d67a6d48a51eb10aaf8df760d83496b8dcfcf83e5f698 */
/* JADX INFO: loaded from: /home/starlight/Projects/Starlight Ports/.build-tmp/classes4.dex */
public final class OplusQSQuickEntranceViewModel {
    public static final Companion Companion = new Companion();
    public static final String TAG = "SeparateQSQuickEntranceViewModel";
    private final Flow hideSettingsEntrance;
    private final OplusSeparateSettingsEntranceInteractor interactor;
    private final Flow isLandSplitMode;
    private final OplusQSQuickEntranceRepository quickEntranceRepository;
    private final Flow superPowerSaveMode;

    /* JADX INFO: compiled from: go/retraceme fd74bf9514dd0739b92d67a6d48a51eb10aaf8df760d83496b8dcfcf83e5f698 */
    public final class Companion {
    }

    public OplusQSQuickEntranceViewModel(OplusQSQuickEntranceRepository oplusQSQuickEntranceRepository, OplusSeparateSettingsEntranceInteractor oplusSeparateSettingsEntranceInteractor) {
        this.quickEntranceRepository = oplusQSQuickEntranceRepository;
        this.interactor = oplusSeparateSettingsEntranceInteractor;
        OplusQSSpecialModeProvider.Companion companion = OplusQSSpecialModeProvider.Companion;
        this.superPowerSaveMode = companion.getInstance().getSuperPowerSaveMode();
        this.isLandSplitMode = companion.getInstance().isLandSplitMode();
        this.hideSettingsEntrance = oplusSeparateSettingsEntranceInteractor.isHideSettingsEntrance();
    }

    public final Flow getHideSettingsEntrance() {
        return this.hideSettingsEntrance;
    }

    public final Flow getSuperPowerSaveMode() {
        return this.superPowerSaveMode;
    }

    public final Flow isLandSplitMode() {
        return this.isLandSplitMode;
    }

    public final void onBind() {
        this.interactor.onBind();
    }

    public final void onDestroy() {
        this.interactor.onDestroy();
    }

    public final void setEditMode(boolean z) {
        this.quickEntranceRepository.setEditMode(z);
    }

    public final void setListening(boolean z) {
        this.quickEntranceRepository.setListening(z);
    }
}
