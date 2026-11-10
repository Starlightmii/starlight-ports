package com.oplus.systemui.shutdown;

import android.app.Dialog;
import android.os.Bundle;
import android.util.Log;
import android.view.WindowManagerGlobal;
import com.android.internal.widget.LockPatternUtils;
import com.android.systemui.DependencyEx;
import com.android.systemui.shutdown.ShutDownDependencyEx;
import java.lang.invoke.VarHandle;

/* JADX INFO: compiled from: go/retraceme fd74bf9514dd0739b92d67a6d48a51eb10aaf8df760d83496b8dcfcf83e5f698 */
/* JADX INFO: loaded from: /home/starlight/Projects/Starlight Ports/.build-tmp/classes4.dex */
public final class BaseGlobalActionsDialogExt {
    public static final BaseGlobalActionsDialogExt INSTANCE;
    public Dialog mDialog;
    public LockPatternUtils mLockPatternUtils;
    public AnonymousClass1 mOnManuallyLock;

    /* JADX INFO: renamed from: com.oplus.systemui.shutdown.BaseGlobalActionsDialogExt$1, reason: invalid class name */
    /* JADX INFO: compiled from: go/retraceme fd74bf9514dd0739b92d67a6d48a51eb10aaf8df760d83496b8dcfcf83e5f698 */
    public final class AnonymousClass1 implements ShutdownViewControl$OnManuallyLockListener {
        public /* synthetic */ BaseGlobalActionsDialogExt this$0;

        public final void onManuallyLockChanged() {
            BaseGlobalActionsDialogExt baseGlobalActionsDialogExt = this.this$0;
            ((ShutDownDependencyEx) DependencyEx.sDependency.getDependency(ShutDownDependencyEx.class)).getOplusManuallyLockEx().setManuallyLockedOn(true);
            try {
                baseGlobalActionsDialogExt.mLockPatternUtils.requireStrongAuth(32, -1);
                WindowManagerGlobal.getWindowManagerService().lockNow((Bundle) null);
            } catch (Exception e) {
                Log.e("BaseGlobalActionsDialogExt", "Error while trying to lock device.", e);
            }
            Dialog dialog = baseGlobalActionsDialogExt.mDialog;
            if (dialog != null) {
                dialog.dismiss();
            }
        }
    }

    static {
        BaseGlobalActionsDialogExt baseGlobalActionsDialogExt = new BaseGlobalActionsDialogExt();
        AnonymousClass1 anonymousClass1 = new AnonymousClass1();
        anonymousClass1.this$0 = baseGlobalActionsDialogExt;
        VarHandle.storeStoreFence();
        baseGlobalActionsDialogExt.mOnManuallyLock = anonymousClass1;
        INSTANCE = baseGlobalActionsDialogExt;
    }

    public static BaseGlobalActionsDialogExt getInstance() {
        return INSTANCE;
    }
}
