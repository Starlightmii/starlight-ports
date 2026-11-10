package com.oplus.systemui.statusbar.pipeline.mobile.ui.view;

import com.android.systemui.statusbar.pipeline.mobile.domain.model.SignalIconModel;
import kotlin.Unit;
import kotlin.coroutines.Continuation;
import kotlinx.coroutines.flow.FlowCollector;

/* JADX INFO: compiled from: go/retraceme fd74bf9514dd0739b92d67a6d48a51eb10aaf8df760d83496b8dcfcf83e5f698 */
/* JADX INFO: loaded from: /home/starlight/Projects/Starlight Ports/.build-tmp/classes4.dex */
public final class AbstractOplusStatusBarMobileViewBinder$bindCustEx$1$2$1$1$1 implements FlowCollector {
    public /* synthetic */ AbstractOplusStatusBarMobileViewBinder$Binding $binding;
    public final /* synthetic */ int $r8$classId;

    public final Object emit(Object obj, Continuation continuation) {
        switch (this.$r8$classId) {
            case 0:
                int iIntValue = ((Number) obj).intValue();
                AbstractOplusStatusBarMobileViewBinder$Binding abstractOplusStatusBarMobileViewBinder$Binding = this.$binding;
                if (iIntValue == 0) {
                    abstractOplusStatusBarMobileViewBinder$Binding.getMobileGroupView().setVisibility(0);
                    abstractOplusStatusBarMobileViewBinder$Binding.getMobileComboReal().setVisibility(0);
                } else {
                    abstractOplusStatusBarMobileViewBinder$Binding.getMobileGroupView().setVisibility(4);
                    abstractOplusStatusBarMobileViewBinder$Binding.getMobileComboReal().setVisibility(4);
                }
                break;
            case 1:
                this.$binding.updateSignalIcon((SignalIconModel) obj);
                break;
            case 2:
                this.$binding.updateDataActivity(((Number) obj).intValue());
                break;
            default:
                this.$binding.updateTint(((Number) obj).intValue());
                break;
        }
        return Unit.INSTANCE;
    }
}
