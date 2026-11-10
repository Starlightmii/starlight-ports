package com.oplus.systemui.qs.base.widget.recyclerview;

import java.lang.invoke.VarHandle;
import kotlin.jvm.functions.Function0;

/* JADX INFO: compiled from: go/retraceme fd74bf9514dd0739b92d67a6d48a51eb10aaf8df760d83496b8dcfcf83e5f698 */
/* JADX INFO: loaded from: /home/starlight/Projects/Starlight Ports/.build-tmp/classes4.dex */
public final /* synthetic */ class EmptyPlaceHolderHandler$$ExternalSyntheticLambda2 implements Function0 {
    public final /* synthetic */ int $r8$classId;
    public /* synthetic */ EmptyPlaceHolderHandler f$0;

    public /* synthetic */ EmptyPlaceHolderHandler$$ExternalSyntheticLambda2(int i) {
        this.$r8$classId = i;
    }

    public final Object invoke() {
        int i = this.$r8$classId;
        EmptyPlaceHolderHandler emptyPlaceHolderHandler = this.f$0;
        switch (i) {
            case 0:
                EmptyPlaceHolderHandler.Companion companion = EmptyPlaceHolderHandler.Companion;
                EmptyPlaceHolderHandler.PlaceHolderDecoration placeHolderDecoration = new EmptyPlaceHolderHandler.PlaceHolderDecoration();
                placeHolderDecoration.this$0 = emptyPlaceHolderHandler;
                VarHandle.storeStoreFence();
                return placeHolderDecoration;
            default:
                return EmptyPlaceHolderHandler.$r8$lambda$ZWFmqMKrLK430ph9jzkMqruYdBA(emptyPlaceHolderHandler);
        }
    }
}
