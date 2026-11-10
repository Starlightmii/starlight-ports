package com.oplus.systemui.statusbar.shared.lifecycle;

import android.util.Log;
import com.android.systemui.application.impl.SystemUIApplicationImpl$;
import com.android.systemui.statusbar.phone.StatusIconContainer;
import com.android.systemui.statusbar.phone.StatusIconContainer$;
import com.oplus.systemui.statusbar.seeding.HiddenIconDispatcher;
import com.oplusos.systemui.common.util.LogUtil;
import java.io.PrintWriter;
import java.lang.invoke.VarHandle;
import java.util.ArrayList;

/* JADX INFO: compiled from: go/retraceme fd74bf9514dd0739b92d67a6d48a51eb10aaf8df760d83496b8dcfcf83e5f698 */
/* JADX INFO: loaded from: /home/starlight/Projects/Starlight Ports/.build-tmp/classes4.dex */
public final class OplusStatusBarViewLifecycleListenerImpl$bindSICHiddenDispatcher$hiddenReceiver$1 implements HiddenIconDispatcher.HiddenReceiver {
    public /* synthetic */ StatusIconContainer $statusIconContainer;
    public boolean allowUpdate;

    public final void dump(PrintWriter printWriter, String[] strArr) {
    }

    public final boolean getAllowUpdate() {
        return this.allowUpdate;
    }

    @Override // com.oplus.systemui.statusbar.seeding.HiddenIconDispatcher.HiddenReceiver
    public final void onHiddenChanged(ArrayList arrayList, boolean z) {
        StatusIconContainer statusIconContainer = this.$statusIconContainer;
        if (LogUtil.getNormalDebug() && LogUtil.isTagValid(LogUtil.TAG_STATUSBAR)) {
            Log.d("SystemUi--Statusbar", SystemUIApplicationImpl$.ExternalSyntheticOutline0.m("allowUpdate:", ", requested:", "OplusStatusBarViewLifecycleListenerImpl-->", this.allowUpdate, statusIconContainer.isLayoutRequested()));
        }
        if (!this.allowUpdate || statusIconContainer.isLayoutRequested()) {
            return;
        }
        statusIconContainer.mIsPostUpdateStates = true;
        statusIconContainer.mForceDisappearAnimateOnce = true;
        StatusIconContainer$.ExternalSyntheticLambda0 externalSyntheticLambda0 = new StatusIconContainer$.ExternalSyntheticLambda0();
        externalSyntheticLambda0.f$0 = statusIconContainer;
        externalSyntheticLambda0.f$1 = true;
        VarHandle.storeStoreFence();
        statusIconContainer.post(externalSyntheticLambda0);
    }

    public final void setAllowUpdate(boolean z) {
        this.allowUpdate = z;
    }

    @Override // com.oplus.systemui.statusbar.seeding.HiddenIconDispatcher.HiddenReceiver
    public /* bridge */ /* synthetic */ void setHiddenIconListChanged(boolean z, ArrayList arrayList) {
        super.setHiddenIconListChanged(z, arrayList);
    }
}
