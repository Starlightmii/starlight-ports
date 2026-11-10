package com.oplus.systemui.statusbar.notification.customcard;

import com.oplus.systemui.notification.row.NotificationBackgroundCustomRowHandler;
import com.oplus.systemui.notification.row.NotificationMenuRowProviderKt;
import com.oplusos.systemui.common.blurability.ViewBlurProxy;
import kotlin.Unit;
import kotlin.jvm.functions.Function1;

/* JADX INFO: compiled from: go/retraceme fd74bf9514dd0739b92d67a6d48a51eb10aaf8df760d83496b8dcfcf83e5f698 */
/* JADX INFO: loaded from: /home/starlight/Projects/Starlight Ports/.build-tmp/classes4.dex */
public final /* synthetic */ class OplusCustomRowControllerManager$$ExternalSyntheticLambda0 implements Function1 {
    public /* synthetic */ OplusCustomRowController f$0;
    public /* synthetic */ ViewBlurProxy f$1;

    public final Object invoke(Object obj) {
        OplusCustomRowController oplusCustomRowController = this.f$0;
        NotificationBackgroundCustomRowHandler.INSTANCE.updateDrawableMetaColor(oplusCustomRowController.getView().getContext(), oplusCustomRowController.getView().getEntry(), NotificationMenuRowProviderKt.tryGetMetaBallBlurDrawable(this.f$1.getBlurDrawable(null)));
        return Unit.INSTANCE;
    }
}
