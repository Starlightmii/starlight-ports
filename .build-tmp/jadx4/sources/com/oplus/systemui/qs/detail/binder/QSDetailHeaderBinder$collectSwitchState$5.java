package com.oplus.systemui.qs.detail.binder;

import android.view.View;
import com.android.keyguard.OplusLockIconView$;
import com.coui.appcompat.couiswitch.COUISwitch;
import com.oplus.systemui.qs.detail.viewmodel.QSDetailHeaderViewModel;
import com.oplusos.systemui.common.util.LogUtil;
import kotlin.Unit;
import kotlin.coroutines.Continuation;
import kotlinx.coroutines.flow.FlowCollector;

/* JADX INFO: compiled from: go/retraceme fd74bf9514dd0739b92d67a6d48a51eb10aaf8df760d83496b8dcfcf83e5f698 */
/* JADX INFO: loaded from: /home/starlight/Projects/Starlight Ports/.build-tmp/classes4.dex */
public final class QSDetailHeaderBinder$collectSwitchState$5 implements FlowCollector {
    public /* synthetic */ View $detailHeader;
    public /* synthetic */ COUISwitch $switch;
    public /* synthetic */ QSDetailHeaderViewModel $viewModel;

    public final Object emit(Object obj, Continuation continuation) {
        boolean zBooleanValue = ((Boolean) obj).booleanValue();
        COUISwitch cOUISwitch = this.$switch;
        if (LogUtil.getNormalDebug() && LogUtil.isTagValid(LogUtil.TAG_QS)) {
            OplusLockIconView$.ExternalSyntheticOutline0.m("collectSwitchState: state=", "QSDetailHeaderBinder-->", "SystemUi--QuickSettings", zBooleanValue);
        }
        QSDetailHeaderBinder qSDetailHeaderBinder = QSDetailHeaderBinder.INSTANCE;
        if (qSDetailHeaderBinder.isFirstShow(this.$detailHeader)) {
            qSDetailHeaderBinder.setFirstShow(this.$detailHeader, false);
            cOUISwitch.setChecked(zBooleanValue, false);
            if (!zBooleanValue && this.$viewModel.turnOnSwitchWhenAttach()) {
                cOUISwitch.startLoading();
            }
        } else {
            cOUISwitch.stopLoading$1();
            cOUISwitch.setChecked(zBooleanValue);
        }
        return Unit.INSTANCE;
    }
}
