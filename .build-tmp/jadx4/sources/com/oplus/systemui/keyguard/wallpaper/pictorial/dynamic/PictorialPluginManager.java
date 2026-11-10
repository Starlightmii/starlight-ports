package com.oplus.systemui.keyguard.wallpaper.pictorial.dynamic;

import android.content.Context;
import com.android.keyguard.KeyguardUpdateMonitor;
import com.android.keyguard.KeyguardUpdateMonitorCallback;
import com.android.systemui.Dependency;
import com.oplus.systemui.keyguard.pluginer.ForeignPackage;

/* JADX INFO: compiled from: go/retraceme fd74bf9514dd0739b92d67a6d48a51eb10aaf8df760d83496b8dcfcf83e5f698 */
/* JADX INFO: loaded from: /home/starlight/Projects/Starlight Ports/.build-tmp/classes4.dex */
public final class PictorialPluginManager {
    public static final Companion Companion = new Companion();
    private static final String PICTORIAL_DYNAMIC_URI_KEY = "uri_lockstyle";
    private static final String TAG = "KeyguardPluginManager";
    private static final String UIENGINE_CTRL_CLASS = "com.oplus.uiengine.ctrl.EngineKeyguardCtrl";
    private static final String UIENGINE_PACKAGE = "com.oplus.uiengine";
    private final Context mContext;
    private ForeignPackage mForeignPackage;
    private Class<?> mKeyguardCtrlClass;
    private KeyguardUpdateMonitorCallback mPlugInCallback;

    /* JADX INFO: compiled from: go/retraceme fd74bf9514dd0739b92d67a6d48a51eb10aaf8df760d83496b8dcfcf83e5f698 */
    public final class Companion {
    }

    public PictorialPluginManager(Context context) {
        this.mContext = context;
    }

    public final void clearKeyguardPlugIn() {
        if (this.mPlugInCallback != null) {
            ((KeyguardUpdateMonitor) Dependency.sDependency.getDependencyInner(KeyguardUpdateMonitor.class)).removeCallback(this.mPlugInCallback);
            this.mPlugInCallback = null;
        }
    }

    public final Context getMContext() {
        return this.mContext;
    }

    /* JADX WARN: Code duplicated, block: B:44:0x013d  */
    /* JADX WARN: Code restructure failed: missing block: B:31:0x0087, code lost:
    
        if (r9.mKeyguardCtrlClass != null) goto L32;
     */
    /*
        Code decompiled incorrectly, please refer to instructions dump.
        To view partially-correct add '--show-bad-code' argument
    */
    public final com.android.keyguard.base.OplusKeyguardCtrl loadKeyguard(com.oplus.systemui.keyguard.wallpaper.pictorial.dynamic.PictorialEngineContainerController r10) {
        /*
            Method dump skipped, instruction units count: 329
            To view this dump add '--comments-level debug' option
        */
        throw new UnsupportedOperationException("Method not decompiled: com.oplus.systemui.keyguard.wallpaper.pictorial.dynamic.PictorialPluginManager.loadKeyguard(com.oplus.systemui.keyguard.wallpaper.pictorial.dynamic.PictorialEngineContainerController):com.android.keyguard.base.OplusKeyguardCtrl");
    }
}
