package com.oplus.systemui.qs.slider;

import com.android.systemui.util.ViewController;
import com.oplus.systemui.qs.base.seek.OplusQsBaseToggleSliderLayout;
import com.oplusos.systemui.common.observer.IObserverListener;
import java.lang.invoke.VarHandle;

/* JADX INFO: compiled from: go/retraceme fd74bf9514dd0739b92d67a6d48a51eb10aaf8df760d83496b8dcfcf83e5f698 */
/* JADX INFO: loaded from: /home/starlight/Projects/Starlight Ports/.build-tmp/classes4.dex */
public final class OplusQsBrightnessSliderController$mSuperPowerObserver$1 implements IObserverListener {
    public /* synthetic */ OplusQsBrightnessSliderController this$0;

    @Override // com.oplusos.systemui.common.observer.IObserverListener
    public final void onChange(boolean z) {
        OplusQsBrightnessSliderController oplusQsBrightnessSliderController = this.this$0;
        OplusQsBaseToggleSliderLayout oplusQsBaseToggleSliderLayout = (OplusQsBaseToggleSliderLayout) ((ViewController) oplusQsBrightnessSliderController).mView;
        OplusQsBrightnessSliderController.AnonymousClass1 anonymousClass1 = new OplusQsBrightnessSliderController.AnonymousClass1(1);
        anonymousClass1.this$0 = oplusQsBrightnessSliderController;
        VarHandle.storeStoreFence();
        oplusQsBaseToggleSliderLayout.post(anonymousClass1);
    }
}
