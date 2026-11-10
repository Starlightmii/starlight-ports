package com.oplus.systemui.qs;

import androidx.constraintlayout.widget.ConstraintLayout;
import com.android.systemui.util.ViewController;
import com.oplusos.systemui.common.observer.IObserverListener;
import java.lang.invoke.VarHandle;

/* JADX INFO: compiled from: go/retraceme fd74bf9514dd0739b92d67a6d48a51eb10aaf8df760d83496b8dcfcf83e5f698 */
/* JADX INFO: loaded from: /home/starlight/Projects/Starlight Ports/.build-tmp/classes4.dex */
public final class OplusQSTileMediaContainerController$mSuperPowerObserver$1 implements IObserverListener {
    public /* synthetic */ OplusQSTileMediaContainerController this$0;

    @Override // com.oplusos.systemui.common.observer.IObserverListener
    public final void onChange(boolean z) {
        OplusQSTileMediaContainerController oplusQSTileMediaContainerController = this.this$0;
        ConstraintLayout constraintLayout = (OplusQSTileMediaContainer) ((ViewController) oplusQSTileMediaContainerController).mView;
        if (constraintLayout != null) {
            Runnable runnable = new onChange.1();
            ((onChange.1) runnable).this$0 = oplusQSTileMediaContainerController;
            VarHandle.storeStoreFence();
            constraintLayout.post(runnable);
        }
    }
}
