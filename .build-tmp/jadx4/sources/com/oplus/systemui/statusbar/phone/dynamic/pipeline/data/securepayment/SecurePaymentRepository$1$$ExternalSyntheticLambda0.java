package com.oplus.systemui.statusbar.phone.dynamic.pipeline.data.securepayment;

import android.database.ContentObserver;
import kotlin.Unit;
import kotlin.jvm.functions.Function0;
import kotlin.jvm.internal.Ref;

/* JADX INFO: compiled from: go/retraceme fd74bf9514dd0739b92d67a6d48a51eb10aaf8df760d83496b8dcfcf83e5f698 */
/* JADX INFO: loaded from: /home/starlight/Projects/Starlight Ports/.build-tmp/classes4.dex */
public final /* synthetic */ class SecurePaymentRepository$1$$ExternalSyntheticLambda0 implements Function0 {
    public /* synthetic */ SecurePaymentRepository f$0;
    public /* synthetic */ SecurePaymentRepository$1$processObserver$1 f$1;
    public /* synthetic */ Ref.ObjectRef f$2;

    public final Object invoke() {
        SecurePaymentRepository securePaymentRepository = this.f$0;
        SecurePaymentRepository$1$processObserver$1 securePaymentRepository$1$processObserver$1 = this.f$1;
        Ref.ObjectRef objectRef = this.f$2;
        securePaymentRepository.commonProcessObserver.removeProcessObserver(securePaymentRepository$1$processObserver$1);
        ContentObserver contentObserver = (ContentObserver) objectRef.element;
        if (contentObserver != null) {
            securePaymentRepository.globalSettings.unregisterContentObserverSync(contentObserver);
        }
        return Unit.INSTANCE;
    }
}
