package com.oplus.systemui.statusbar.seeding.livealert;

import android.content.Context;
import android.os.Binder;
import android.os.Bundle;
import android.os.Handler;
import android.service.notification.StatusBarNotification;
import android.util.Log;
import android.view.MotionEvent;
import androidx.constraintlayout.widget.ConstraintSet$;
import com.android.systemui.plugins.statusbar.SeedlingPlugin;
import com.oplus.systemui.statusbar.seeding.Listener;
import com.oplus.systemui.statusbar.seeding.SeedlingPluginManager;
import com.oplusos.systemui.common.util.LogUtil;
import java.lang.invoke.VarHandle;
import java.util.List;
import kotlin.Lazy;
import kotlin.LazyKt__LazyJVMKt;

/* JADX INFO: compiled from: go/retraceme fd74bf9514dd0739b92d67a6d48a51eb10aaf8df760d83496b8dcfcf83e5f698 */
/* JADX INFO: loaded from: /home/starlight/Projects/Starlight Ports/.build-tmp/classes4.dex */
public final class LitePluginLoadManager {
    private static final String KEY_SBN = "content";
    private static final String TAG = "LitePluginLoadManager";
    private final SeedlingPluginManager seedlingPluginManager;
    public static final Companion Companion = new Companion();
    private static final Lazy AUTO_LOAD_METHOD_LIST$delegate = LazyKt__LazyJVMKt.lazy(new LitePluginLoadManager$$ExternalSyntheticLambda0());

    /* JADX INFO: compiled from: go/retraceme fd74bf9514dd0739b92d67a6d48a51eb10aaf8df760d83496b8dcfcf83e5f698 */
    public final class Companion {
        /* JADX INFO: Access modifiers changed from: private */
        public final List<String> getAUTO_LOAD_METHOD_LIST() {
            return (List) LitePluginLoadManager.AUTO_LOAD_METHOD_LIST$delegate.getValue();
        }
    }

    /* JADX INFO: renamed from: com.oplus.systemui.statusbar.seeding.livealert.LitePluginLoadManager$call$1, reason: invalid class name */
    /* JADX INFO: compiled from: go/retraceme fd74bf9514dd0739b92d67a6d48a51eb10aaf8df760d83496b8dcfcf83e5f698 */
    public final class AnonymousClass1 implements Runnable {
        public /* synthetic */ String $desc;
        public /* synthetic */ String $method;
        public /* synthetic */ Runnable $runnable;
        public /* synthetic */ LitePluginLoadManager this$0;

        /* JADX INFO: renamed from: com.oplus.systemui.statusbar.seeding.livealert.LitePluginLoadManager$call$1$1, reason: invalid class name and collision with other inner class name */
        /* JADX INFO: compiled from: go/retraceme fd74bf9514dd0739b92d67a6d48a51eb10aaf8df760d83496b8dcfcf83e5f698 */
        public final class C01701 implements Listener {
            public /* synthetic */ Runnable $runnable;
            public /* synthetic */ LitePluginLoadManager this$0;

            @Override // com.oplus.systemui.statusbar.seeding.Listener
            public /* bridge */ /* synthetic */ void onDispatchTouchEvent(MotionEvent motionEvent) {
                super.onDispatchTouchEvent(motionEvent);
            }

            @Override // com.oplus.systemui.statusbar.seeding.Listener
            public final void onPluginAdded(SeedlingPlugin seedlingPlugin, Context context) {
                Runnable runnable = this.$runnable;
                if (LogUtil.getNormalDebug() && LogUtil.isTagValid(LogUtil.TAG_STATUSBAR)) {
                    Log.d("SystemUi--Statusbar", "LitePluginLoadManager-->".concat("plugin added, run callback: " + runnable));
                }
                this.$runnable.run();
                this.this$0.seedlingPluginManager.removeCallback(this);
            }

            @Override // com.oplus.systemui.statusbar.seeding.Listener
            public final void onPluginRemove(SeedlingPlugin seedlingPlugin) {
                this.this$0.seedlingPluginManager.removeCallback(this);
            }
        }

        @Override // java.lang.Runnable
        public final void run() {
            if (this.this$0.seedlingPluginManager.isPluginAvailable || !LitePluginLoadManager.Companion.getAUTO_LOAD_METHOD_LIST().contains(this.$method)) {
                return;
            }
            SeedlingPluginManager seedlingPluginManager = this.this$0.seedlingPluginManager;
            Runnable runnable = this.$runnable;
            LitePluginLoadManager litePluginLoadManager = this.this$0;
            C01701 c01701 = new C01701();
            c01701.$runnable = runnable;
            c01701.this$0 = litePluginLoadManager;
            VarHandle.storeStoreFence();
            seedlingPluginManager.addCallback(c01701);
            LogUtil.i$default(LogUtil.TAG_STATUSBAR, LitePluginLoadManager.TAG, "request load plugin for ".concat(this.$desc), null, 8);
            this.this$0.seedlingPluginManager.requestLoadPlugin(this.$desc);
        }
    }

    public LitePluginLoadManager(SeedlingPluginManager seedlingPluginManager) {
        this.seedlingPluginManager = seedlingPluginManager;
        if (LogUtil.getNormalDebug() && LogUtil.isTagValid(LogUtil.TAG_STATUSBAR)) {
            Log.d("SystemUi--Statusbar", "LitePluginLoadManager-->LitePluginLoadManager init");
        }
    }

    public final void call(String str, Bundle bundle, Runnable runnable) {
        StatusBarNotification statusBarNotification;
        int callingPid = Binder.getCallingPid();
        String key = (bundle == null || (statusBarNotification = (StatusBarNotification) bundle.getParcelable(KEY_SBN, StatusBarNotification.class)) == null) ? null : statusBarNotification.getKey();
        StringBuilder sbM = ConstraintSet$.ExternalSyntheticOutline0.m(callingPid, "default call ", str, " from ", " ");
        sbM.append(key);
        String string = sbM.toString();
        Handler main = Handler.getMain();
        AnonymousClass1 anonymousClass1 = new AnonymousClass1();
        anonymousClass1.this$0 = this;
        anonymousClass1.$method = str;
        anonymousClass1.$desc = string;
        anonymousClass1.$runnable = runnable;
        VarHandle.storeStoreFence();
        main.post(anonymousClass1);
    }
}
