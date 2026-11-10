package com.oplus.systemui.qs.fake;

import android.widget.FrameLayout;
import com.oplus.systemui.statusbar.notification.icdf.IcdfNotificationManagerOuterClass$IcdfNotification;
import kotlin.Unit;
import kotlin.jvm.functions.Function2;

/* JADX INFO: compiled from: go/retraceme fd74bf9514dd0739b92d67a6d48a51eb10aaf8df760d83496b8dcfcf83e5f698 */
/* JADX INFO: loaded from: /home/starlight/Projects/Starlight Ports/.build-tmp/classes4.dex */
public final /* synthetic */ class OplusQSFakeStatusController$$ExternalSyntheticLambda0 implements Function2 {
    public final /* synthetic */ int $r8$classId;
    public /* synthetic */ Object f$0;

    public /* synthetic */ OplusQSFakeStatusController$$ExternalSyntheticLambda0(int i) {
        this.$r8$classId = i;
    }

    public final Object invoke(Object obj, Object obj2) {
        int i = this.$r8$classId;
        Object obj3 = this.f$0;
        switch (i) {
            case 0:
                float fFloatValue = ((Float) obj).floatValue();
                ((Float) obj2).getClass();
                return OplusQSFakeStatusController.$r8$lambda$ntxHaEwaoI2mGQ87uLCD5xEAPqA((OplusQSFakeStatusController) obj3, fFloatValue);
            case 1:
                return OplusQSFakeStatusController.$r8$lambda$WVq31py5KiZeEIAJd4z1phCKtdI((OplusQSFakeStatusController) obj3, ((Float) obj).floatValue(), ((Float) obj2).floatValue());
            case 2:
                return OplusQSFakeStatusController.m268$r8$lambda$QnR89D83L0mP1NH5Qpsm0Syjas((OplusQSFakeStatusController) obj3, ((Float) obj).floatValue(), ((Float) obj2).floatValue());
            case 3:
                float fFloatValue2 = ((Float) obj).floatValue();
                ((Float) obj2).getClass();
                return OplusQSFakeStatusController.$r8$lambda$x70NeZHCQzGpVmCcG1L1zwptd_U((OplusQSFakeStatusController) obj3, fFloatValue2);
            case 4:
                float fFloatValue3 = ((Float) obj).floatValue();
                ((Float) obj2).getClass();
                return OplusQSFakeStatusController.m269$r8$lambda$T2FcQXgTF39dswZW475VGyXXpQ((OplusQSFakeStatusController) obj3, fFloatValue3);
            case 5:
                float fFloatValue4 = ((Float) obj).floatValue();
                ((Float) obj2).getClass();
                return OplusQSFakeStatusController.$r8$lambda$V0uGvebCLBv_1v4DWZLYk57JaJY((OplusQSFakeStatusController) obj3, fFloatValue4);
            case 6:
                return OplusQSFakeStatusController.$r8$lambda$ZRIJSCM52JpYFtYGmHHMdYeyj5o((OplusQSFakeStatusController) obj3, ((Float) obj).floatValue(), ((Float) obj2).floatValue());
            case IcdfNotificationManagerOuterClass$IcdfNotification.EXPANDEDCHILD_FIELD_NUMBER /* 7 */:
                return OplusQSFakeStatusController.$r8$lambda$lX_cZletCtThocgO5lBs9YKkLBA((OplusQSFakeStatusController) obj3, ((Float) obj).floatValue(), ((Float) obj2).floatValue());
            case 8:
                return OplusQSFakeStatusController.$r8$lambda$QrWM4TuSOaZxMeHZalxfLGJGiYM((OplusQSFakeStatusController) obj3, ((Float) obj).floatValue(), ((Float) obj2).floatValue());
            case 9:
                return OplusQSFakeStatusController.$r8$lambda$7PPGmTsJ_hG_OugNsDOEFCp51LQ((OplusQSFakeStatusController) obj3, ((Float) obj).floatValue(), ((Float) obj2).floatValue());
            case 10:
                return OplusQSFakeStatusController.m270$r8$lambda$VCL4f1iriyntP3b0JzmXr4ecXk((OplusQSFakeStatusController) obj3, ((Float) obj).floatValue(), ((Float) obj2).floatValue());
            default:
                float fFloatValue5 = ((Float) obj).floatValue();
                ((Float) obj2).floatValue();
                ((FrameLayout) obj3).setTransitionAlpha(fFloatValue5);
                return Unit.INSTANCE;
        }
    }
}
