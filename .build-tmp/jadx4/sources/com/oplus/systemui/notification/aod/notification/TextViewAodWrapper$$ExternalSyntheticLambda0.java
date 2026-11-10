package com.oplus.systemui.notification.aod.notification;

import kotlin.jvm.functions.Function0;

/* JADX INFO: compiled from: go/retraceme fd74bf9514dd0739b92d67a6d48a51eb10aaf8df760d83496b8dcfcf83e5f698 */
/* JADX INFO: loaded from: /home/starlight/Projects/Starlight Ports/.build-tmp/classes4.dex */
public final /* synthetic */ class TextViewAodWrapper$$ExternalSyntheticLambda0 implements Function0 {
    public final /* synthetic */ int $r8$classId;
    public /* synthetic */ TextViewAodWrapper f$0;

    public /* synthetic */ TextViewAodWrapper$$ExternalSyntheticLambda0(int i) {
        this.$r8$classId = i;
    }

    public final Object invoke() {
        int color;
        int i = this.$r8$classId;
        TextViewAodWrapper textViewAodWrapper = this.f$0;
        switch (i) {
            case 0:
                color = textViewAodWrapper.context.getColor(2131102911);
                break;
            default:
                color = textViewAodWrapper.context.getColor(2131102910);
                break;
        }
        return Integer.valueOf(color);
    }
}
