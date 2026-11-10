package com.oplus.systemui.statusbar.pipeline.mobile.ui.view;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.ViewGroup;
import com.android.systemui.statusbar.pipeline.mobile.ui.viewmodel.LocationBasedMobileViewModel;
import com.oplus.systemui.statusbar.phone.signal.widget.OplusModernStatusBarMobileView;
import com.oplus.systemui.statusbar.pipeline.mobile.ui.viewmodel.OplusMobileIconViewModel;
import com.oplus.systemui.statusbar.pipeline.mobile.ui.viewmodel.OplusMobileIconsViewModel;

/* JADX INFO: compiled from: go/retraceme fd74bf9514dd0739b92d67a6d48a51eb10aaf8df760d83496b8dcfcf83e5f698 */
/* JADX INFO: loaded from: /home/starlight/Projects/Starlight Ports/.build-tmp/classes4.dex */
public final class BigTypeStatusBarMobileViewBinder extends OplusStatusBarMobileViewBinder {
    public static final Companion Companion = new Companion();
    private static final String TAG = "BigTypeStatusBarMobileViewBinder";

    public BigTypeStatusBarMobileViewBinder(Context context, OplusMobileIconsViewModel oplusMobileIconsViewModel) {
        super(context, oplusMobileIconsViewModel);
    }

    @Override // com.oplus.systemui.statusbar.pipeline.mobile.ui.view.OplusStatusBarMobileViewBinder
    public AbstractOplusStatusBarMobileViewBinder$Binding createBinding(ViewGroup viewGroup, LocationBasedMobileViewModel locationBasedMobileViewModel, OplusMobileIconViewModel oplusMobileIconViewModel) {
        return super.createBinding(viewGroup, locationBasedMobileViewModel, oplusMobileIconViewModel);
    }

    @Override // com.oplus.systemui.statusbar.pipeline.mobile.ui.view.OplusStatusBarMobileViewBinder
    public OplusModernStatusBarMobileView fromContext(Context context) {
        return (OplusModernStatusBarMobileView) LayoutInflater.from(context).inflate(2131559399, (ViewGroup) null);
    }

    @Override // com.oplus.systemui.statusbar.pipeline.mobile.ui.view.OplusStatusBarMobileViewBinder
    public int getNetworkTypeTextPrefixDimenRes() {
        return 2131173013;
    }

    @Override // com.oplus.systemui.statusbar.pipeline.mobile.ui.view.OplusStatusBarMobileViewBinder
    public int getNetworkTypeTextSuffixDimenRes() {
        return 2131173014;
    }
}
