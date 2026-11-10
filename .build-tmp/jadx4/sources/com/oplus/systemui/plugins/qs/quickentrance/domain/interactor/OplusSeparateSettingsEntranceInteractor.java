package com.oplus.systemui.plugins.qs.quickentrance.domain.interactor;

import android.os.oplusdevicepolicy.OplusDevicepolicyManager;
import com.oplus.keyguard.folder.OplusBouncerLocationController$clickListener$1$;
import com.oplusos.systemui.common.util.LogUtil;
import com.oplusos.systemui.common.util.SystemUIThreadUtil;
import java.lang.invoke.VarHandle;
import kotlinx.coroutines.flow.FlowKt;
import kotlinx.coroutines.flow.MutableStateFlow;
import kotlinx.coroutines.flow.StateFlow;
import kotlinx.coroutines.flow.StateFlowKt;

/* JADX INFO: compiled from: go/retraceme fd74bf9514dd0739b92d67a6d48a51eb10aaf8df760d83496b8dcfcf83e5f698 */
/* JADX INFO: loaded from: /home/starlight/Projects/Starlight Ports/.build-tmp/classes4.dex */
public final class OplusSeparateSettingsEntranceInteractor {
    public static final Companion Companion = new Companion();
    private static final String SETTINGS_APPLICATION_DISABLED_STATE = "setting_application_disable_state";
    private static final String TAG = "OplusSeparateSettingsEntranceInteractor";
    private final MutableStateFlow _hideSettingsEntrance;
    private final StateFlow isHideSettingsEntrance;
    private final OplusDevicepolicyManager.OplusDevicePolicyObserver settingDisableStatePolicyObserver;

    /* JADX INFO: compiled from: go/retraceme fd74bf9514dd0739b92d67a6d48a51eb10aaf8df760d83496b8dcfcf83e5f698 */
    public final class Companion {
    }

    public OplusSeparateSettingsEntranceInteractor() {
        MutableStateFlow MutableStateFlow = StateFlowKt.MutableStateFlow(Boolean.FALSE);
        this._hideSettingsEntrance = MutableStateFlow;
        this.isHideSettingsEntrance = FlowKt.asStateFlow(MutableStateFlow);
        OplusSeparateSettingsEntranceInteractor$settingDisableStatePolicyObserver$1 oplusSeparateSettingsEntranceInteractor$settingDisableStatePolicyObserver$1 = new OplusSeparateSettingsEntranceInteractor$settingDisableStatePolicyObserver$1();
        oplusSeparateSettingsEntranceInteractor$settingDisableStatePolicyObserver$1.this$0 = this;
        VarHandle.storeStoreFence();
        this.settingDisableStatePolicyObserver = oplusSeparateSettingsEntranceInteractor$settingDisableStatePolicyObserver$1;
    }

    public static final void access$updateHideSettingsEntranceState(OplusSeparateSettingsEntranceInteractor oplusSeparateSettingsEntranceInteractor) {
        oplusSeparateSettingsEntranceInteractor._hideSettingsEntrance.setValue(Boolean.valueOf(OplusDevicepolicyManager.getInstance().getBoolean(SETTINGS_APPLICATION_DISABLED_STATE, 1, false)));
        if (LogUtil.getNormalDebug() && LogUtil.isTagValid(LogUtil.TAG_QS)) {
            OplusBouncerLocationController$clickListener$1$.ExternalSyntheticOutline0.m("onOplusDevicePolicyUpdate: ", oplusSeparateSettingsEntranceInteractor._hideSettingsEntrance.getValue(), "OplusSeparateSettingsEntranceInteractor-->", "SystemUi--QuickSettings");
        }
    }

    public final StateFlow isHideSettingsEntrance() {
        return this.isHideSettingsEntrance;
    }

    public final void onBind() {
        OplusDevicepolicyManager oplusDevicepolicyManager = OplusDevicepolicyManager.getInstance();
        if (oplusDevicepolicyManager != null) {
            oplusDevicepolicyManager.registerOplusDevicepolicyObserver(SETTINGS_APPLICATION_DISABLED_STATE, this.settingDisableStatePolicyObserver);
            OplusSeparateSettingsEntranceInteractor$registerOplusDevicePolicyObserver$1$1 oplusSeparateSettingsEntranceInteractor$registerOplusDevicePolicyObserver$1$1 = new OplusSeparateSettingsEntranceInteractor$registerOplusDevicePolicyObserver$1$1(0);
            oplusSeparateSettingsEntranceInteractor$registerOplusDevicePolicyObserver$1$1.this$0 = this;
            VarHandle.storeStoreFence();
            SystemUIThreadUtil.runOnWorkThread(oplusSeparateSettingsEntranceInteractor$registerOplusDevicePolicyObserver$1$1);
        }
    }

    public final void onDestroy() {
        OplusDevicepolicyManager oplusDevicepolicyManager = OplusDevicepolicyManager.getInstance();
        if (oplusDevicepolicyManager != null) {
            oplusDevicepolicyManager.unregisterOplusDevicePolicyObserver(this.settingDisableStatePolicyObserver);
        }
    }
}
