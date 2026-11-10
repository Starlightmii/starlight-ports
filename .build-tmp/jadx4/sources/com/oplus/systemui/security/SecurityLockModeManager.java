package com.oplus.systemui.security;

import android.content.Context;
import android.database.ContentObserver;
import android.net.Uri;
import android.os.Handler;
import com.android.systemui.CoreStartable;
import com.android.systemui.navigationbar.gestural.domain.GestureInteractor$getTopActivity$2$;
import com.android.systemui.statusbar.OlockStateUtil;
import com.oplusos.systemui.common.feature.FeatureOption;
import com.oplusos.systemui.common.util.LogUtil;
import com.oplusos.systemui.common.util.SystemUIThreadUtil;
import java.io.PrintWriter;
import java.lang.invoke.VarHandle;

/* JADX INFO: compiled from: go/retraceme fd74bf9514dd0739b92d67a6d48a51eb10aaf8df760d83496b8dcfcf83e5f698 */
/* JADX INFO: loaded from: /home/starlight/Projects/Starlight Ports/.build-tmp/classes4.dex */
public final class SecurityLockModeManager implements CoreStartable {
    public static final Companion Companion = new Companion();
    private static final String TAG = "SecurityLockModeManager";
    private static final String URI_SECURITY_LOCK_MODE_AUTHORITY = "com.oplus.safecenter.provider.security_lock";
    private static final String URI_SECURITY_LOCK_MODE_URI = "content://com.oplus.safecenter.provider.security_lock";
    private final Context context;
    public int securityLockMode;
    private final ContentObserver securityLockModeObserver;

    /* JADX INFO: compiled from: go/retraceme fd74bf9514dd0739b92d67a6d48a51eb10aaf8df760d83496b8dcfcf83e5f698 */
    public final class Companion {
    }

    /* JADX INFO: renamed from: com.oplus.systemui.security.SecurityLockModeManager$start$1, reason: invalid class name */
    /* JADX INFO: compiled from: go/retraceme fd74bf9514dd0739b92d67a6d48a51eb10aaf8df760d83496b8dcfcf83e5f698 */
    public final class AnonymousClass1 implements Runnable {
        public /* synthetic */ SecurityLockModeManager this$0;

        @Override // java.lang.Runnable
        public final void run() {
            if (this.this$0.getContext().getPackageManager().resolveContentProvider(SecurityLockModeManager.URI_SECURITY_LOCK_MODE_AUTHORITY, 0) != null) {
                this.this$0.getContext().getContentResolver().registerContentObserver(Uri.parse(SecurityLockModeManager.URI_SECURITY_LOCK_MODE_URI), true, this.this$0.securityLockModeObserver);
                LogUtil.i$default(LogUtil.TAG_COMMON, SecurityLockModeManager.TAG, "security_lock_mode registerContentObserver", null, 8);
                SecurityLockModeManager.access$querySecurityMode(this.this$0);
            }
        }
    }

    public SecurityLockModeManager(Context context) {
        this.context = context;
        final Handler workHandler = SystemUIThreadUtil.getWorkHandler();
        this.securityLockModeObserver = new ContentObserver(workHandler) { // from class: com.oplus.systemui.security.SecurityLockModeManager$securityLockModeObserver$1
            @Override // android.database.ContentObserver
            public final void onChange(boolean z, Uri uri) {
                LogUtil.i$default(LogUtil.TAG_COMMON, "SecurityLockModeManager", "security_lock_mode onChange", null, 8);
                SecurityLockModeManager.access$querySecurityMode(this.this$0);
            }
        };
    }

    public static final void access$querySecurityMode(SecurityLockModeManager securityLockModeManager) {
        securityLockModeManager.securityLockMode = OlockStateUtil.callSafeCenterGetSafeMode(securityLockModeManager.context);
    }

    public void dump(PrintWriter printWriter, String[] strArr) {
        printWriter.println();
        printWriter.println("SecurityLockModeManager:");
        printWriter.println();
        printWriter.print("securityLockMode=" + this.securityLockMode);
    }

    public final Context getContext() {
        return this.context;
    }

    public final void start() {
        FeatureOption.INSTANCE.getClass();
        if (FeatureOption.isExpRegion()) {
            try {
                AnonymousClass1 anonymousClass1 = new AnonymousClass1();
                anonymousClass1.this$0 = this;
                VarHandle.storeStoreFence();
                SystemUIThreadUtil.runOnWorkThread(anonymousClass1);
            } catch (SecurityException e) {
                GestureInteractor$getTopActivity$2$.ExternalSyntheticOutline0.m(8, "register security_lock_mode provider error: ", e.getMessage(), LogUtil.TAG_COMMON, TAG);
            }
        }
    }
}
