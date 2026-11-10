package com.oplus.systemui.scenario.host.impl.domain.binder;

import androidx.compose.runtime.State;
import com.oplus.systemui.qs.base.scenario.PrivacyScenarioCapsuleViewModel;
import com.oplus.systemui.qs.scenario.domain.viewmodel.PrivacyScenarioCapsuleViewModelImpl;
import com.oplus.systemui.statusbar.notification.icdf.IcdfNotificationManagerOuterClass$IcdfNotification;
import java.util.List;
import kotlin.Unit;
import kotlin.jvm.functions.Function0;

/* JADX INFO: compiled from: go/retraceme fd74bf9514dd0739b92d67a6d48a51eb10aaf8df760d83496b8dcfcf83e5f698 */
/* JADX INFO: loaded from: /home/starlight/Projects/Starlight Ports/.build-tmp/classes4.dex */
public final /* synthetic */ class ScenarioCapsuleViewBinder$$ExternalSyntheticLambda19 implements Function0 {
    public final /* synthetic */ int $r8$classId;
    public /* synthetic */ Object f$0;

    public /* synthetic */ ScenarioCapsuleViewBinder$$ExternalSyntheticLambda19(int i) {
        this.$r8$classId = i;
    }

    public final Object invoke() {
        int i = this.$r8$classId;
        Object obj = this.f$0;
        switch (i) {
            case 0:
                return (String) ((State) obj).getValue();
            case 1:
                return Integer.valueOf(((Number) ((State) obj).getValue()).intValue());
            case 2:
                return (String) ((State) obj).getValue();
            case 3:
                Boolean bool = (Boolean) ((State) obj).getValue();
                bool.booleanValue();
                return bool;
            case 4:
                return Boolean.valueOf(((List) ((State) obj).getValue()).contains("android.permission-group.CAMERA"));
            case 5:
                Boolean bool2 = (Boolean) ((State) obj).getValue();
                bool2.booleanValue();
                return bool2;
            case 6:
                return Boolean.valueOf(((List) ((State) obj).getValue()).contains("android.permission-group.MICROPHONE"));
            case IcdfNotificationManagerOuterClass$IcdfNotification.EXPANDEDCHILD_FIELD_NUMBER /* 7 */:
                return Boolean.valueOf(((List) ((State) obj).getValue()).contains("android.permission-group.LOCATION"));
            case 8:
                Boolean bool3 = (Boolean) ((State) obj).getValue();
                bool3.booleanValue();
                return bool3;
            case 9:
                Boolean bool4 = (Boolean) ((State) obj).getValue();
                bool4.booleanValue();
                return bool4;
            case 10:
                return Integer.valueOf(((Boolean) ((State) obj).getValue()).booleanValue() ? 2131236405 : 2131236404);
            case 11:
                return Integer.valueOf(((Boolean) ((State) obj).getValue()).booleanValue() ? 2131236403 : 2131236402);
            case 12:
                return Integer.valueOf(((Boolean) ((State) obj).getValue()).booleanValue() ? 2131236398 : 2131236397);
            default:
                ((PrivacyScenarioCapsuleViewModelImpl) ((PrivacyScenarioCapsuleViewModel) obj)).exitingToPrivacy = false;
                return Unit.INSTANCE;
        }
    }
}
