package com.oplus.systemui.statusbar.pipeline.mobile.ui.view;

import android.content.Context;
import android.view.ViewGroup;
import com.android.systemui.statusbar.pipeline.OplusMobileSignalEx;
import com.android.systemui.statusbar.pipeline.mobile.ui.MobileViewLogger;
import com.android.systemui.statusbar.pipeline.mobile.ui.viewmodel.LocationBasedMobileViewModel;
import com.oplus.systemui.statusbar.phone.signal.widget.OplusModernStatusBarMobileView;
import kotlinx.coroutines.flow.Flow;
import kotlinx.coroutines.flow.StateFlow;

/* JADX INFO: compiled from: go/retraceme fd74bf9514dd0739b92d67a6d48a51eb10aaf8df760d83496b8dcfcf83e5f698 */
/* JADX INFO: loaded from: /home/starlight/Projects/Starlight Ports/.build-tmp/classes4.dex */
public interface ICustStatusBarMobileViewBinder {
    OplusMobileSignalEx.OplusMobileViewBinding bindCustEx(LocationBasedMobileViewModel locationBasedMobileViewModel, ViewGroup viewGroup, Flow flow, StateFlow stateFlow, MobileViewLogger mobileViewLogger);

    OplusModernStatusBarMobileView fromContext(Context context);
}
