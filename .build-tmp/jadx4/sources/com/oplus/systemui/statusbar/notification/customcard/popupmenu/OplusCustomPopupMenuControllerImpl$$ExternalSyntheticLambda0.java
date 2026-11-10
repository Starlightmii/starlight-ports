package com.oplus.systemui.statusbar.notification.customcard.popupmenu;

import kotlin.Unit;
import kotlin.jvm.functions.Function0;

/* JADX INFO: compiled from: go/retraceme fd74bf9514dd0739b92d67a6d48a51eb10aaf8df760d83496b8dcfcf83e5f698 */
/* JADX INFO: loaded from: /home/starlight/Projects/Starlight Ports/.build-tmp/classes4.dex */
public final /* synthetic */ class OplusCustomPopupMenuControllerImpl$$ExternalSyntheticLambda0 implements Function0 {
    public final /* synthetic */ int $r8$classId;
    public /* synthetic */ OplusCustomPopupMenuControllerImpl f$0;

    public /* synthetic */ OplusCustomPopupMenuControllerImpl$$ExternalSyntheticLambda0(int i) {
        this.$r8$classId = i;
    }

    public final Object invoke() {
        int i = this.$r8$classId;
        OplusCustomPopupMenuControllerImpl oplusCustomPopupMenuControllerImpl = this.f$0;
        switch (i) {
            case 0:
                return OplusCustomPopupMenu.Companion.newInstance(oplusCustomPopupMenuControllerImpl.context, oplusCustomPopupMenuControllerImpl.keyguardViewMediator);
            case 1:
                OplusCustomPopupMenuController.dismissPopupMenu$default(oplusCustomPopupMenuControllerImpl, "windowInsetChangeListener", false, 6);
                return Unit.INSTANCE;
            case 2:
                return OplusCustomPopupMenuControllerImpl.$r8$lambda$YLSIcoToDgWQ3EhJL7iUFYd_E7g(oplusCustomPopupMenuControllerImpl);
            default:
                return OplusCustomViewCloner.Companion.newInstance(oplusCustomPopupMenuControllerImpl.context, oplusCustomPopupMenuControllerImpl.getWindowManager());
        }
    }
}
