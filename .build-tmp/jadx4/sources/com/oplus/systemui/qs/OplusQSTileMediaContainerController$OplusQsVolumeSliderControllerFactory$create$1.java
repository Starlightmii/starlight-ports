package com.oplus.systemui.qs;

import android.content.Context;
import com.oplus.systemui.qs.base.util.QsUserContextProvider;

/* JADX INFO: compiled from: go/retraceme fd74bf9514dd0739b92d67a6d48a51eb10aaf8df760d83496b8dcfcf83e5f698 */
/* JADX INFO: loaded from: /home/starlight/Projects/Starlight Ports/.build-tmp/classes4.dex */
public final class OplusQSTileMediaContainerController$OplusQsVolumeSliderControllerFactory$create$1 implements QsUserContextProvider {
    public /* synthetic */ OplusQSTileMediaContainerController.OplusQsVolumeSliderControllerFactory this$0;

    @Override // com.oplus.systemui.qs.base.util.QsUserContextProvider
    public Context getCurrentUserContext() {
        return this.this$0.getUserContextProvider().getUserContext();
    }
}
