package com.oplus.systemui.notification.headsup.windowframe;

import android.util.Log;
import com.oplusos.systemui.common.util.LogUtil;
import kotlin.Unit;
import kotlin.jvm.functions.Function0;

/* JADX INFO: compiled from: go/retraceme fd74bf9514dd0739b92d67a6d48a51eb10aaf8df760d83496b8dcfcf83e5f698 */
/* JADX INFO: loaded from: /home/starlight/Projects/Starlight Ports/.build-tmp/classes4.dex */
public final /* synthetic */ class OplusHeadsUpAnimator$$ExternalSyntheticLambda4 implements Function0 {
    public final /* synthetic */ int $r8$classId;

    public /* synthetic */ OplusHeadsUpAnimator$$ExternalSyntheticLambda4(int i) {
        this.$r8$classId = i;
    }

    public final Object invoke() {
        switch (this.$r8$classId) {
            case 0:
                Log.e("HeadsUpAnimator", "onImeHeightChanged:end");
                break;
            default:
                if (LogUtil.getNormalDebug() && LogUtil.isTagValid(LogUtil.TAG_NOTIFICATION)) {
                    Log.d("SystemUi--Notification", "HeadsUpAnimator-->move: end");
                }
                break;
        }
        return Unit.INSTANCE;
    }
}
