package com.oplus.systemui.qs;

import com.android.systemui.tuner.TunerService;

/* JADX INFO: compiled from: go/retraceme fd74bf9514dd0739b92d67a6d48a51eb10aaf8df760d83496b8dcfcf83e5f698 */
/* JADX INFO: loaded from: /home/starlight/Projects/Starlight Ports/.build-tmp/classes4.dex */
public final class OplusQSTileMediaContainerController$tunable$1 implements TunerService.Tunable {
    public /* synthetic */ OplusQSTileMediaContainerController this$0;

    public final void onTuningChanged(String str, String str2) {
        OplusQSTileMediaContainerController.access$updateViewVisibilityForTuningValue(this.this$0, str, str2);
    }
}
