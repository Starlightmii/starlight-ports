package com.oplus.systemui.qs.policy;

import android.os.Handler;
import android.view.Window;
import androidx.appcompat.app.AlertDialog;
import com.android.systemui.statusbar.policy.SecurityController;
import com.coui.appcompat.dialog.COUIAlertDialogBuilder;
import java.lang.invoke.VarHandle;

/* JADX INFO: compiled from: go/retraceme fd74bf9514dd0739b92d67a6d48a51eb10aaf8df760d83496b8dcfcf83e5f698 */
/* JADX INFO: loaded from: /home/starlight/Projects/Starlight Ports/.build-tmp/classes4.dex */
public final class OplusQSSecurityController$callback$1 implements SecurityController.SecurityControllerCallback {
    public /* synthetic */ OplusQSSecurityController this$0;

    /* JADX INFO: renamed from: com.oplus.systemui.qs.policy.OplusQSSecurityController$callback$1$1, reason: invalid class name */
    /* JADX INFO: compiled from: go/retraceme fd74bf9514dd0739b92d67a6d48a51eb10aaf8df760d83496b8dcfcf83e5f698 */
    public final class AnonymousClass1 implements Runnable {
        public final /* synthetic */ int $r8$classId;
        public /* synthetic */ OplusQSSecurityController this$0;

        public /* synthetic */ AnonymousClass1(int i) {
            this.$r8$classId = i;
        }

        @Override // java.lang.Runnable
        public final void run() {
            Window window;
            switch (this.$r8$classId) {
                case 0:
                    OplusQSSecurityController.access$handleRefreshState(this.this$0);
                    break;
                case 1:
                    OplusQSSecurityController.access$handleRefreshState(this.this$0);
                    break;
                case 2:
                    OplusQSSecurityController.access$handleRefreshState(this.this$0);
                    OplusQSSecurityController oplusQSSecurityController = this.this$0;
                    OplusQSSecurityController.access$createDialog(oplusQSSecurityController, (CharSequence) oplusQSSecurityController.getContentText().get());
                    break;
                default:
                    COUIAlertDialogBuilder cOUIAlertDialogBuilder = this.this$0.couiAlertDialogBuilder;
                    if (cOUIAlertDialogBuilder != null) {
                        OplusQSSecurityController oplusQSSecurityController2 = this.this$0;
                        oplusQSSecurityController2.dialog = cOUIAlertDialogBuilder.create();
                        AlertDialog alertDialog = oplusQSSecurityController2.dialog;
                        if (alertDialog != null && (window = alertDialog.getWindow()) != null) {
                            window.setType(2008);
                        }
                        AlertDialog alertDialog2 = oplusQSSecurityController2.dialog;
                        if (alertDialog2 != null) {
                            alertDialog2.show();
                        }
                    }
                    break;
            }
        }
    }

    public final void onStateChanged() {
        OplusQSSecurityController oplusQSSecurityController = this.this$0;
        oplusQSSecurityController.bgHandler.removeCallbacksAndMessages(null);
        Handler handler = oplusQSSecurityController.bgHandler;
        AnonymousClass1 anonymousClass1 = new AnonymousClass1(0);
        anonymousClass1.this$0 = oplusQSSecurityController;
        VarHandle.storeStoreFence();
        handler.post(anonymousClass1);
    }
}
