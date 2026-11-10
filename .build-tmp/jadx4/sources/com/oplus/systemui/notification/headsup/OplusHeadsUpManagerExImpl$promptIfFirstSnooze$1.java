package com.oplus.systemui.notification.headsup;

import android.view.View;
import com.oplus.systemui.notification.headsup.windowframe.SnackbarWindow;
import com.oplus.zoom.ui.tips.TipsManager;
import java.lang.invoke.VarHandle;

/* JADX INFO: compiled from: go/retraceme fd74bf9514dd0739b92d67a6d48a51eb10aaf8df760d83496b8dcfcf83e5f698 */
/* JADX INFO: loaded from: /home/starlight/Projects/Starlight Ports/.build-tmp/classes4.dex */
public final class OplusHeadsUpManagerExImpl$promptIfFirstSnooze$1 implements Runnable {
    public /* synthetic */ OplusHeadsUpManagerExImpl this$0;

    /* JADX INFO: renamed from: com.oplus.systemui.notification.headsup.OplusHeadsUpManagerExImpl$promptIfFirstSnooze$1$1, reason: invalid class name */
    /* JADX INFO: compiled from: go/retraceme fd74bf9514dd0739b92d67a6d48a51eb10aaf8df760d83496b8dcfcf83e5f698 */
    public final class AnonymousClass1 implements View.OnClickListener {
        public /* synthetic */ OplusHeadsUpManagerExImpl this$0;

        @Override // android.view.View.OnClickListener
        public final void onClick(View view) {
            OplusHeadsUpManagerExImpl.access$jumpToSnoozeSetting(this.this$0);
        }
    }

    @Override // java.lang.Runnable
    public final void run() {
        SnackbarWindow snackbarWindow = (SnackbarWindow) this.this$0.snackbarWindow.get();
        String string = this.this$0.context.getResources().getString(2131956487);
        String string2 = this.this$0.context.getResources().getString(2131956488);
        OplusHeadsUpManagerExImpl oplusHeadsUpManagerExImpl = this.this$0;
        AnonymousClass1 anonymousClass1 = new AnonymousClass1();
        anonymousClass1.this$0 = oplusHeadsUpManagerExImpl;
        VarHandle.storeStoreFence();
        snackbarWindow.show(string, string2, TipsManager.DELAY_AUTO_HIDE_TIME, anonymousClass1);
    }
}
