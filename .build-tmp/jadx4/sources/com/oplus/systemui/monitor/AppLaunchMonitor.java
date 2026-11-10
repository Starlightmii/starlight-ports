package com.oplus.systemui.monitor;

import android.content.Context;
import android.content.Intent;
import android.content.pm.PackageManager;
import android.content.pm.Signature;
import android.content.pm.SigningInfo;
import android.net.Uri;
import android.os.Handler;
import com.android.systemui.CoreStartable;
import com.android.systemui.plugins.ActivityStarter;
import com.android.systemui.shared.system.TaskStackChangeListeners;
import com.oplusos.systemui.common.util.LogUtil;
import dagger.Lazy;
import java.lang.invoke.VarHandle;
import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;
import kotlin.collections.ArraysKt___ArraysKt;
import kotlin.jvm.internal.Intrinsics;

/* JADX INFO: compiled from: go/retraceme fd74bf9514dd0739b92d67a6d48a51eb10aaf8df760d83496b8dcfcf83e5f698 */
/* JADX INFO: loaded from: /home/starlight/Projects/Starlight Ports/.build-tmp/classes4.dex */
public final class AppLaunchMonitor implements CoreStartable {
    public static final Companion Companion = new Companion();
    private static final String EXPECTED_SIGNATURE_SHA256 = "FC98DAE63AD39626C8C67FBE83F2F06F74932A9CD146B92CECFC6A047A904386";
    private static final String MARKET_PACKAGE = "com.heytap.market";
    private static final String TAG = "AppLaunchMonitor";
    private static final String TARGET_PACKAGE = "com.opluskgd.onetaplocking";
    private final Lazy activityStarter;
    private final Context context;
    private final Handler handler;
    private final AppLaunchMonitor$listener$1 listener;
    private final Lazy snackbarWindow;
    private final TaskStackChangeListeners taskStackChangeListeners;

    /* JADX INFO: compiled from: go/retraceme fd74bf9514dd0739b92d67a6d48a51eb10aaf8df760d83496b8dcfcf83e5f698 */
    public final class Companion {
    }

    public AppLaunchMonitor(Context context, Handler handler, TaskStackChangeListeners taskStackChangeListeners, Lazy lazy, Lazy lazy2) {
        this.context = context;
        this.handler = handler;
        this.taskStackChangeListeners = taskStackChangeListeners;
        this.snackbarWindow = lazy;
        this.activityStarter = lazy2;
        AppLaunchMonitor$listener$1 appLaunchMonitor$listener$1 = new AppLaunchMonitor$listener$1();
        appLaunchMonitor$listener$1.this$0 = this;
        VarHandle.storeStoreFence();
        this.listener = appLaunchMonitor$listener$1;
    }

    public static final boolean access$doesPackageSignatureMatch(AppLaunchMonitor appLaunchMonitor) {
        String strJoinToString$default;
        PackageManager packageManager = appLaunchMonitor.context.getPackageManager();
        try {
            packageManager.getClass();
            SigningInfo signingInfo = packageManager.getPackageInfo(TARGET_PACKAGE, 134217728).signingInfo;
            Signature[] apkContentsSigners = signingInfo == null ? null : signingInfo.getApkContentsSigners();
            if (apkContentsSigners == null) {
                return false;
            }
            for (Signature signature : apkContentsSigners) {
                try {
                    byte[] bArrDigest = MessageDigest.getInstance("SHA-256").digest(signature.toByteArray());
                    bArrDigest.getClass();
                    strJoinToString$default = ArraysKt___ArraysKt.joinToString$default(bArrDigest, "", new AppLaunchMonitor$.ExternalSyntheticLambda0(), 30);
                } catch (NoSuchAlgorithmException unused) {
                    strJoinToString$default = "";
                }
                if (Intrinsics.areEqual(strJoinToString$default, EXPECTED_SIGNATURE_SHA256)) {
                    return true;
                }
            }
            return false;
        } catch (PackageManager.NameNotFoundException unused2) {
            return false;
        }
    }

    public static final void access$openAppStore(AppLaunchMonitor appLaunchMonitor) {
        try {
            appLaunchMonitor.context.getPackageManager().getApplicationInfo(MARKET_PACKAGE, 0);
            Intent intent = new Intent("android.intent.action.VIEW", Uri.parse("oaps://mk/home?goback=1"));
            intent.setPackage(MARKET_PACKAGE);
            ((ActivityStarter) appLaunchMonitor.activityStarter.get()).startActivity(intent, true);
        } catch (PackageManager.NameNotFoundException unused) {
            LogUtil.w$default(12, TAG, "openAppStore: com.heytap.market not installed", null);
        }
    }

    public final void start() {
        this.taskStackChangeListeners.registerTaskStackListener(this.listener);
    }
}
