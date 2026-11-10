package com.oplus.systemui.plugins.qs.quickentrance.domain.interactor;

import android.os.oplusdevicepolicy.OplusDevicepolicyManager;
import com.oplus.keyguard.folder.OplusBouncerLocationController$clickListener$1$;
import com.oplusos.systemui.common.util.LogUtil;
import com.oplusos.systemui.common.util.SystemUIThreadUtil;
import java.lang.invoke.VarHandle;
import java.util.List;

/* JADX INFO: compiled from: go/retraceme fd74bf9514dd0739b92d67a6d48a51eb10aaf8df760d83496b8dcfcf83e5f698 */
/* JADX INFO: loaded from: /home/starlight/Projects/Starlight Ports/.build-tmp/classes4.dex */
public final class OplusSeparateSettingsEntranceInteractor$settingDisableStatePolicyObserver$1 implements OplusDevicepolicyManager.OplusDevicePolicyObserver {
    public /* synthetic */ OplusSeparateSettingsEntranceInteractor this$0;

    public final void onOplusDevicePolicyUpdate(String str, List list) {
        OplusSeparateSettingsEntranceInteractor oplusSeparateSettingsEntranceInteractor = this.this$0;
        if (LogUtil.getNormalDebug() && LogUtil.isTagValid(LogUtil.TAG_QS)) {
            OplusBouncerLocationController$clickListener$1$.ExternalSyntheticOutline0.m("onOplusDevicePolicyUpdate: ", oplusSeparateSettingsEntranceInteractor._hideSettingsEntrance.getValue(), "OplusSeparateSettingsEntranceInteractor-->", "SystemUi--QuickSettings");
        }
    }

    public final void onOplusDevicePolicyUpdate(String str, String str2) {
        OplusSeparateSettingsEntranceInteractor oplusSeparateSettingsEntranceInteractor = this.this$0;
        OplusSeparateSettingsEntranceInteractor$registerOplusDevicePolicyObserver$1$1 oplusSeparateSettingsEntranceInteractor$registerOplusDevicePolicyObserver$1$1 = new OplusSeparateSettingsEntranceInteractor$registerOplusDevicePolicyObserver$1$1(1);
        oplusSeparateSettingsEntranceInteractor$registerOplusDevicePolicyObserver$1$1.this$0 = oplusSeparateSettingsEntranceInteractor;
        VarHandle.storeStoreFence();
        SystemUIThreadUtil.runOnWorkThread(oplusSeparateSettingsEntranceInteractor$registerOplusDevicePolicyObserver$1$1);
    }
}
