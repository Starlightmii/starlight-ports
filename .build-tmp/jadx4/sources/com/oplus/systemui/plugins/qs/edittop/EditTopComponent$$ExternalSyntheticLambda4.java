package com.oplus.systemui.plugins.qs.edittop;

import com.oplus.systemui.plugins.qs.OplusQSRootView;
import kotlin.jvm.functions.Function0;

/* JADX INFO: compiled from: go/retraceme fd74bf9514dd0739b92d67a6d48a51eb10aaf8df760d83496b8dcfcf83e5f698 */
/* JADX INFO: loaded from: /home/starlight/Projects/Starlight Ports/.build-tmp/classes4.dex */
public final /* synthetic */ class EditTopComponent$$ExternalSyntheticLambda4 implements Function0 {
    public final /* synthetic */ int $r8$classId;
    public /* synthetic */ OplusQSRootView f$0;

    public final Object invoke() {
        float dimension;
        int i = this.$r8$classId;
        OplusQSRootView oplusQSRootView = this.f$0;
        switch (i) {
            case 0:
                EditTopComponent.Companion companion = EditTopComponent.Companion;
                dimension = oplusQSRootView.getContext().getResources().getDimension(2131171794);
                break;
            case 1:
                EditTopComponent.Companion companion2 = EditTopComponent.Companion;
                dimension = oplusQSRootView.getContext().getResources().getDimension(2131171794);
                break;
            default:
                EditTopComponent.Companion companion3 = EditTopComponent.Companion;
                dimension = oplusQSRootView.getContext().getResources().getDimension(2131171794);
                break;
        }
        return Float.valueOf(dimension / 2.0f);
    }
}
