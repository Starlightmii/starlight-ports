package com.oplus.systemui.statusbar.notification.customcard.popupmenu;

import android.os.Trace;
import android.view.View;
import kotlin.Unit;
import kotlin.jvm.functions.Function0;
import kotlin.jvm.functions.Function1;

/* JADX INFO: compiled from: go/retraceme fd74bf9514dd0739b92d67a6d48a51eb10aaf8df760d83496b8dcfcf83e5f698 */
/* JADX INFO: loaded from: /home/starlight/Projects/Starlight Ports/.build-tmp/classes4.dex */
public final /* synthetic */ class OplusCustomPopupMenuControllerImpl$$ExternalSyntheticLambda5 implements Function1 {
    public final /* synthetic */ int $r8$classId;
    public /* synthetic */ OplusCustomPopupMenuControllerImpl f$0;
    public /* synthetic */ View f$1;
    public /* synthetic */ Function0 f$2;

    public final Object invoke(Object obj) {
        switch (this.$r8$classId) {
            case 0:
                OplusCustomPopupMenuControllerImpl oplusCustomPopupMenuControllerImpl = this.f$0;
                View view = this.f$1;
                Function0 function0 = this.f$2;
                oplusCustomPopupMenuControllerImpl.removePanelView((OplusCustomMenuPanelView) obj);
                OplusCustomPopupMenuControllerImpl.boostFrameRate(view, false);
                if (function0 != null) {
                    function0.invoke();
                }
                break;
            default:
                OplusCustomPopupMenuControllerImpl$.ExternalSyntheticLambda6 externalSyntheticLambda6 = this.f$2;
                View view2 = this.f$1;
                externalSyntheticLambda6.invoke();
                OplusCustomPopupMenuControllerImpl.boostFrameRate(view2, false);
                Trace.traceEnd(8L);
                break;
        }
        return Unit.INSTANCE;
    }
}
