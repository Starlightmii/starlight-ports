package com.oplus.systemui.statusbar.phone.dynamic.pipeline.ui.viewmodel;

import com.android.systemui.statusbar.phone.StatusBarLocation;
import kotlin.Unit;
import kotlin.jvm.functions.Function0;

/* JADX INFO: compiled from: go/retraceme fd74bf9514dd0739b92d67a6d48a51eb10aaf8df760d83496b8dcfcf83e5f698 */
/* JADX INFO: loaded from: /home/starlight/Projects/Starlight Ports/.build-tmp/classes4.dex */
public final /* synthetic */ class DynamicIconSetViewModel$isTipHidden$1$$ExternalSyntheticLambda0 implements Function0 {
    public /* synthetic */ DynamicIconSetViewModel f$0;
    public /* synthetic */ DynamicIconSetViewModel$isTipHidden$1$receiver$1 f$1;

    public final Object invoke() {
        DynamicIconSetViewModel dynamicIconSetViewModel = this.f$0;
        dynamicIconSetViewModel.hiddenIconDispatcher.removeHiddenReceiver(StatusBarLocation.HOME, this.f$1);
        return Unit.INSTANCE;
    }
}
