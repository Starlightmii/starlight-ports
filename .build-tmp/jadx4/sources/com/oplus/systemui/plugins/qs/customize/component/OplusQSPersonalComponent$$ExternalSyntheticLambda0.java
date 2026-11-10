package com.oplus.systemui.plugins.qs.customize.component;

import android.graphics.drawable.GradientDrawable;
import com.oplus.systemui.statusbar.notification.icdf.IcdfNotificationManagerOuterClass$IcdfNotification;
import java.lang.invoke.VarHandle;
import kotlin.jvm.functions.Function0;

/* JADX INFO: compiled from: go/retraceme fd74bf9514dd0739b92d67a6d48a51eb10aaf8df760d83496b8dcfcf83e5f698 */
/* JADX INFO: loaded from: /home/starlight/Projects/Starlight Ports/.build-tmp/classes4.dex */
public final /* synthetic */ class OplusQSPersonalComponent$$ExternalSyntheticLambda0 implements Function0 {
    public final /* synthetic */ int $r8$classId;
    public /* synthetic */ OplusQSPersonalComponent f$0;

    public /* synthetic */ OplusQSPersonalComponent$$ExternalSyntheticLambda0(int i) {
        this.$r8$classId = i;
    }

    public final Object invoke() {
        int i = this.$r8$classId;
        OplusQSPersonalComponent oplusQSPersonalComponent = this.f$0;
        switch (i) {
            case 0:
                return OplusQSPersonalComponent.m131$r8$lambda$Vh_tTdcAR9E9IDba24WilogCo4(oplusQSPersonalComponent);
            case 1:
                return OplusQSPersonalComponent.m132$r8$lambda$W6QGl1RxfCi2FME2j7mCT8LsIQ(oplusQSPersonalComponent);
            case 2:
                return OplusQSPersonalComponent.$r8$lambda$GR8D_DMEyiR0ZJP1jfym0G_IPWg(oplusQSPersonalComponent);
            case 3:
                return oplusQSPersonalComponent.getCellSizeChangeProxy().getLayoutManagerFromCellInfo();
            case 4:
                return OplusQSPersonalComponent.m133$r8$lambda$_xgnxFmNYv7K2RdMkageftqho(oplusQSPersonalComponent);
            case 5:
                return OplusQSPersonalComponent.$r8$lambda$Q_i7CPCITN1CEfflkTNOklM3n0Q(oplusQSPersonalComponent);
            case 6:
                OplusQSPersonalComponent.Companion companion = OplusQSPersonalComponent.Companion;
                OplusQSPersonalComponent$resizeCallBack$2$1 oplusQSPersonalComponent$resizeCallBack$2$1 = new OplusQSPersonalComponent$resizeCallBack$2$1();
                oplusQSPersonalComponent$resizeCallBack$2$1.this$0 = oplusQSPersonalComponent;
                VarHandle.storeStoreFence();
                return oplusQSPersonalComponent$resizeCallBack$2$1;
            case IcdfNotificationManagerOuterClass$IcdfNotification.EXPANDEDCHILD_FIELD_NUMBER /* 7 */:
                return OplusQSPersonalComponent.$r8$lambda$glWe3Pm2IX7q8se92DJVZkXmQhU(oplusQSPersonalComponent);
            default:
                OplusQSPersonalComponent.Companion companion2 = OplusQSPersonalComponent.Companion;
                return new QSPersonalBorderDrawable(oplusQSPersonalComponent.getContext(), (GradientDrawable) oplusQSPersonalComponent.getContext().getDrawable(2131236298));
        }
    }
}
