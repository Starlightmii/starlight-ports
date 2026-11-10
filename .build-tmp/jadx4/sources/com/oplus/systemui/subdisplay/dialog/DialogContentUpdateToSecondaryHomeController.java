package com.oplus.systemui.subdisplay.dialog;

import android.content.Context;
import android.net.Uri;
import android.os.Handler;
import android.os.UserHandle;
import com.android.keyguard.KeyguardUpdateMonitor;
import java.lang.invoke.VarHandle;
import java.util.concurrent.CopyOnWriteArrayList;

/* JADX INFO: compiled from: go/retraceme fd74bf9514dd0739b92d67a6d48a51eb10aaf8df760d83496b8dcfcf83e5f698 */
/* JADX INFO: loaded from: /home/starlight/Projects/Starlight Ports/.build-tmp/classes4.dex */
public class DialogContentUpdateToSecondaryHomeController {
    public static final Companion Companion = new Companion();
    public static final Uri DIALOG_PROVIDER_URI = Uri.parse("content://com.oplus.secondaryhome.systemui.SubSysUiProvider");
    private static final String DIALOG_SHOWING = "dialog_showing";
    private static final String METHOD_UPDATE_DIALOG = "dialog_update";
    private static final String SYSUI_STATE_DIALOG_NAME = "sysui_state_dialog_name";
    private static final String TAG = "DialogContentUpdateToSecondaryHomeController";
    private final Handler bgHandler;
    private Context context;
    private CopyOnWriteArrayList<String> systemuiDialogSaved = new CopyOnWriteArrayList<>();
    private final DialogContentUpdateToSecondaryHomeController$updateMonitorCallback$1 updateMonitorCallback;
    private Context userContext;

    /* JADX INFO: compiled from: go/retraceme fd74bf9514dd0739b92d67a6d48a51eb10aaf8df760d83496b8dcfcf83e5f698 */
    public final class Companion {
    }

    public DialogContentUpdateToSecondaryHomeController(Context context, KeyguardUpdateMonitor keyguardUpdateMonitor, Handler handler) {
        this.context = context;
        this.bgHandler = handler;
        DialogContentUpdateToSecondaryHomeController$updateMonitorCallback$1 dialogContentUpdateToSecondaryHomeController$updateMonitorCallback$1 = new DialogContentUpdateToSecondaryHomeController$updateMonitorCallback$1();
        dialogContentUpdateToSecondaryHomeController$updateMonitorCallback$1.this$0 = this;
        VarHandle.storeStoreFence();
        this.updateMonitorCallback = dialogContentUpdateToSecondaryHomeController$updateMonitorCallback$1;
        Context context2 = this.context;
        this.userContext = context2.createContextAsUser(new UserHandle(context2.getUserId()), 0);
        keyguardUpdateMonitor.registerCallback(dialogContentUpdateToSecondaryHomeController$updateMonitorCallback$1);
    }

    public final void passDataToSecondaryHomeAfterConnected() {
        int size = this.systemuiDialogSaved.size();
        for (int i = 0; i < size; i++) {
            String str = this.systemuiDialogSaved.get(i);
            Handler handler = this.bgHandler;
            callForProvider.1 r4 = new callForProvider.1();
            r4.$name = str;
            r4.$show = true;
            r4.this$0 = this;
            VarHandle.storeStoreFence();
            handler.post(r4);
        }
    }

    public void updateDialogContent(String str, boolean z) {
        Handler handler = this.bgHandler;
        callForProvider.1 r1 = new callForProvider.1();
        r1.$name = str;
        r1.$show = z;
        r1.this$0 = this;
        VarHandle.storeStoreFence();
        handler.post(r1);
        CopyOnWriteArrayList<String> copyOnWriteArrayList = this.systemuiDialogSaved;
        if (z) {
            if (copyOnWriteArrayList.contains(str)) {
                return;
            }
            this.systemuiDialogSaved.add(str);
        } else if (copyOnWriteArrayList.contains(str)) {
            this.systemuiDialogSaved.remove(str);
        }
    }
}
