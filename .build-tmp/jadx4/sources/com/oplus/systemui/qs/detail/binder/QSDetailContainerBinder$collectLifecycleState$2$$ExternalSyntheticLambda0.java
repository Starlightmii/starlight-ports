package com.oplus.systemui.qs.detail.binder;

import com.oplus.systemui.qs.detail.viewmodel.QSDetailContainerViewModel;
import com.oplusos.systemui.common.inline.OplusQsInlineHostView;
import kotlin.Unit;
import kotlin.jvm.functions.Function0;

/* JADX INFO: compiled from: go/retraceme fd74bf9514dd0739b92d67a6d48a51eb10aaf8df760d83496b8dcfcf83e5f698 */
/* JADX INFO: loaded from: /home/starlight/Projects/Starlight Ports/.build-tmp/classes4.dex */
public final /* synthetic */ class QSDetailContainerBinder$collectLifecycleState$2$$ExternalSyntheticLambda0 implements Function0 {
    public final /* synthetic */ int $r8$classId;
    public /* synthetic */ Object f$0;

    public final Object invoke() {
        int i = this.$r8$classId;
        Object obj = this.f$0;
        switch (i) {
            case 0:
                ((QSDetailContainerViewModel) obj).onDestroy();
                break;
            default:
                ((OplusQsInlineHostView) obj).setSysInlineRequestListener(null);
                break;
        }
        return Unit.INSTANCE;
    }
}
