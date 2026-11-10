package com.oplus.systemui.notification.notificationcenter.antivoyeur;

import java.lang.invoke.VarHandle;
import kotlin.jvm.functions.Function0;

/* JADX INFO: compiled from: go/retraceme fd74bf9514dd0739b92d67a6d48a51eb10aaf8df760d83496b8dcfcf83e5f698 */
/* JADX INFO: loaded from: /home/starlight/Projects/Starlight Ports/.build-tmp/classes4.dex */
public final /* synthetic */ class AonConfigMangerImpl$$ExternalSyntheticLambda0 implements Function0 {
    public final /* synthetic */ int $r8$classId;
    public /* synthetic */ AonConfigMangerImpl f$0;

    public /* synthetic */ AonConfigMangerImpl$$ExternalSyntheticLambda0(int i) {
        this.$r8$classId = i;
    }

    public final Object invoke() {
        int i = this.$r8$classId;
        AonConfigMangerImpl aonConfigMangerImpl = this.f$0;
        switch (i) {
            case 0:
                UriProviderObserver.Companion companion = UriProviderObserver.Companion;
                AonConfigMangerImpl$$ExternalSyntheticLambda3 aonConfigMangerImpl$$ExternalSyntheticLambda3 = new AonConfigMangerImpl$$ExternalSyntheticLambda3(2);
                aonConfigMangerImpl$$ExternalSyntheticLambda3.f$0 = aonConfigMangerImpl;
                VarHandle.storeStoreFence();
                return companion.createFaceForEncryptAppObserver(aonConfigMangerImpl$$ExternalSyntheticLambda3);
            case 1:
                UriProviderObserver.Companion companion2 = UriProviderObserver.Companion;
                AonConfigMangerImpl$$ExternalSyntheticLambda3 aonConfigMangerImpl$$ExternalSyntheticLambda4 = new AonConfigMangerImpl$$ExternalSyntheticLambda3(1);
                aonConfigMangerImpl$$ExternalSyntheticLambda4.f$0 = aonConfigMangerImpl;
                VarHandle.storeStoreFence();
                return companion2.createEncryptAppsObserver(aonConfigMangerImpl$$ExternalSyntheticLambda4);
            default:
                UriProviderObserver.Companion companion3 = UriProviderObserver.Companion;
                AonConfigMangerImpl$$ExternalSyntheticLambda3 aonConfigMangerImpl$$ExternalSyntheticLambda5 = new AonConfigMangerImpl$$ExternalSyntheticLambda3(0);
                aonConfigMangerImpl$$ExternalSyntheticLambda5.f$0 = aonConfigMangerImpl;
                VarHandle.storeStoreFence();
                return companion3.createFaceEnrollObserver(aonConfigMangerImpl$$ExternalSyntheticLambda5);
        }
    }
}
