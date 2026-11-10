package com.oplus.systemui.statusbar.pipeline.mobile.ui.view;

import com.android.systemui.statusbar.pipeline.OplusMobileSignalEx;
import com.oplus.systemui.statusbar.pipeline.mobile.ui.viewmodel.OplusMobileIconViewModel;
import kotlin.jvm.internal.Ref;

/* JADX INFO: compiled from: go/retraceme fd74bf9514dd0739b92d67a6d48a51eb10aaf8df760d83496b8dcfcf83e5f698 */
/* JADX INFO: loaded from: /home/starlight/Projects/Starlight Ports/.build-tmp/classes4.dex */
public final class AbstractOplusStatusBarMobileViewBinder$bindCustEx$2 implements OplusMobileSignalEx.OplusMobileViewBinding {
    public /* synthetic */ Ref.BooleanRef $isCollecting;
    public /* synthetic */ OplusMobileIconViewModel $oplusViewModel;

    public final boolean isCollecting() {
        return this.$isCollecting.element;
    }

    public final boolean isVisible() {
        return ((Boolean) this.$oplusViewModel.isVisible().getValue()).booleanValue();
    }

    public final boolean oplusBinder() {
        return true;
    }
}
