package com.oplus.systemui.separate;

import com.oplus.systemui.qs.pipeline.data.repository.SuperPowerSaveRepositoryImpl;
import com.oplusos.systemui.common.observer.IObserverListener;
import com.oplusos.systemui.common.util.LogUtil;

/* JADX INFO: compiled from: go/retraceme fd74bf9514dd0739b92d67a6d48a51eb10aaf8df760d83496b8dcfcf83e5f698 */
/* JADX INFO: loaded from: /home/starlight/Projects/Starlight Ports/.build-tmp/classes4.dex */
public final class OplusQSSimpleHeader$superPowerListener$1 implements IObserverListener {
    public /* synthetic */ OplusQSSimpleHeader this$0;

    @Override // com.oplusos.systemui.common.observer.IObserverListener
    public final void onChange(boolean z) {
        OplusQSSimpleHeader oplusQSSimpleHeader = this.this$0;
        boolean z2 = oplusQSSimpleHeader.isInSuperPowerSaveMode;
        SuperPowerSaveRepositoryImpl.Companion.getClass();
        if (z2 != SuperPowerSaveRepositoryImpl.Companion.isSuperPowerSaveModel()) {
            LogUtil.i$default(LogUtil.TAG_NOTIFICATION, OplusQSSimpleHeader.TAG, "superPower state changed", null, 8);
            oplusQSSimpleHeader.isInSuperPowerSaveMode = SuperPowerSaveRepositoryImpl.Companion.isSuperPowerSaveModel();
            oplusQSSimpleHeader.updateClickAbilities();
        }
    }
}
