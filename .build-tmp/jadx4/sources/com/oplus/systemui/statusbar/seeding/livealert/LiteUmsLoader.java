package com.oplus.systemui.statusbar.seeding.livealert;

import android.content.ContentResolver;
import android.content.Context;
import android.net.ConnectivityManager;
import android.net.Network;
import android.net.NetworkCapabilities;
import android.os.Bundle;
import android.os.Handler;
import android.util.Log;
import androidx.compose.material3.internal.TextFieldImplKt$;
import com.android.compose.nestedscroll.PriorityNestedScrollConnection$;
import com.android.keyguard.logging.KeyguardLogger$;
import com.android.systemui.application.impl.SystemUIApplicationImpl$;
import com.oplusos.systemui.common.multiuser.MultiUserContentHelper;
import com.oplusos.systemui.common.multiuser.MultiUserManager;
import com.oplusos.systemui.common.util.LogUtil;
import com.oplusos.systemui.common.util.SystemUIThreadUtil;
import java.lang.invoke.VarHandle;
import kotlin.Lazy;
import kotlin.LazyKt__LazyJVMKt;
import kotlin.LazyKt__LazyJVMKt$;
import kotlin.Result;
import kotlin.ResultKt;
import kotlin.Unit;

/* JADX INFO: compiled from: go/retraceme fd74bf9514dd0739b92d67a6d48a51eb10aaf8df760d83496b8dcfcf83e5f698 */
/* JADX INFO: loaded from: /home/starlight/Projects/Starlight Ports/.build-tmp/classes4.dex */
public final class LiteUmsLoader {
    private static final String AUTHORITY = "com.oplus.pantanal.ums.cardservice.provider.CardServiceClientProvider";
    public static final Companion Companion = new Companion();
    private static final String METHOD_PULL_UMS = "pullUmsProcess";
    private static final String RESULT_KEY = "success";
    private static final String TAG = "LiteUmsLoader";
    private final Context context;
    public boolean needRetry;
    private final Lazy networkMonitor$delegate;
    private final Runnable runnable;
    private final Lazy userListener$delegate;
    private final Lazy retryDelayMs$delegate = LazyKt__LazyJVMKt.lazy(new LiteUmsLoader$$ExternalSyntheticLambda0());
    private Handler workHandler = SystemUIThreadUtil.getWorkHandler();

    /* JADX INFO: compiled from: go/retraceme fd74bf9514dd0739b92d67a6d48a51eb10aaf8df760d83496b8dcfcf83e5f698 */
    public final class Companion {
    }

    /* JADX INFO: compiled from: go/retraceme fd74bf9514dd0739b92d67a6d48a51eb10aaf8df760d83496b8dcfcf83e5f698 */
    public final class NetworkMonitor {
        private final LiteUmsLoader$NetworkMonitor$callback$1 callback;
        private ConnectivityManager connectivityManager;
        public boolean isActive;
        public boolean isRegistered;

        /* JADX WARN: Type inference failed for: r0v3, types: [com.oplus.systemui.statusbar.seeding.livealert.LiteUmsLoader$NetworkMonitor$callback$1] */
        public NetworkMonitor() {
            this.connectivityManager = (ConnectivityManager) LiteUmsLoader.this.context.getSystemService("connectivity");
            this.callback = new ConnectivityManager.NetworkCallback() { // from class: com.oplus.systemui.statusbar.seeding.livealert.LiteUmsLoader$NetworkMonitor$callback$1
                @Override // android.net.ConnectivityManager.NetworkCallback
                public final void onAvailable(Network network) {
                    if (LogUtil.getNormalDebug() && LogUtil.isTagValid(LogUtil.TAG_STATUSBAR)) {
                        Log.d("SystemUi--Statusbar", "LiteUmsLoader-->".concat("NetworkCallback, onAvailable " + network));
                    }
                    LiteUmsLoader.NetworkMonitor.access$notifyIfChanged(this.this$0, network);
                }

                @Override // android.net.ConnectivityManager.NetworkCallback
                public final void onLost(Network network) {
                    if (LogUtil.getNormalDebug() && LogUtil.isTagValid(LogUtil.TAG_STATUSBAR)) {
                        Log.d("SystemUi--Statusbar", "LiteUmsLoader-->NetworkCallback, onLost. delay check.");
                    }
                    Handler handler = liteUmsLoader.workHandler;
                    LiteUmsLoader.NetworkMonitor networkMonitor = this.this$0;
                    LiteUmsLoader.AnonymousClass2 anonymousClass2 = new LiteUmsLoader.AnonymousClass2(2);
                    anonymousClass2.this$0 = networkMonitor;
                    VarHandle.storeStoreFence();
                    handler.postDelayed(anonymousClass2, 1000L);
                }
            };
        }

        public static final void access$notifyIfChanged(NetworkMonitor networkMonitor, Network network) {
            if (network == null) {
                network = networkMonitor.connectivityManager.getActiveNetwork();
            }
            boolean zIsNetworkActive = networkMonitor.isNetworkActive(network);
            if (networkMonitor.isActive != zIsNetworkActive) {
                networkMonitor.isActive = zIsNetworkActive;
                LogUtil.i$default(LogUtil.TAG_STATUSBAR, LiteUmsLoader.TAG, KeyguardLogger$.ExternalSyntheticOutline0.m("notifyIfChanged, isActive=", zIsNetworkActive), null, 8);
                LiteUmsLoader.access$onNetworkChanged(LiteUmsLoader.this, networkMonitor.isActive);
            } else if (LogUtil.getNormalDebug() && LogUtil.isTagValid(LogUtil.TAG_STATUSBAR)) {
                Log.d("SystemUi--Statusbar", "LiteUmsLoader-->".concat("notifyIfChanged, isActive not changed for " + network + " (" + networkMonitor.isActive + ")."));
            }
        }

        public final boolean isNetworkActive(Network network) {
            NetworkCapabilities networkCapabilities;
            if (network == null || (networkCapabilities = this.connectivityManager.getNetworkCapabilities(network)) == null) {
                return false;
            }
            return networkCapabilities.hasTransport(0) || networkCapabilities.hasTransport(1) || networkCapabilities.hasTransport(2) || networkCapabilities.hasTransport(3) || networkCapabilities.hasTransport(4) || networkCapabilities.hasTransport(5);
        }

        public final void start() {
            boolean zIsNetworkActive = isNetworkActive(this.connectivityManager.getActiveNetwork());
            this.isActive = zIsNetworkActive;
            LogUtil.i$default(LogUtil.TAG_STATUSBAR, LiteUmsLoader.TAG, PriorityNestedScrollConnection$.ExternalSyntheticOutline0.m("start monitor, isActive: ", ", isRegistered: ", zIsNetworkActive, this.isRegistered), null, 8);
            boolean z = this.isActive;
            LiteUmsLoader liteUmsLoader = LiteUmsLoader.this;
            if (z) {
                LiteUmsLoader.access$onNetworkChanged(liteUmsLoader, true);
            } else {
                if (this.isRegistered) {
                    return;
                }
                this.isRegistered = true;
                this.connectivityManager.registerDefaultNetworkCallback(this.callback, liteUmsLoader.workHandler);
            }
        }

        public final void stop() {
            Object obj;
            if (LogUtil.getNormalDebug() && LogUtil.isTagValid(LogUtil.TAG_STATUSBAR)) {
                Log.d("SystemUi--Statusbar", "LiteUmsLoader-->stop monitor");
            }
            if (this.isRegistered) {
                try {
                    this.connectivityManager.unregisterNetworkCallback(this.callback);
                    this.isRegistered = false;
                    this.isActive = false;
                    obj = Result.constructor-impl(Unit.INSTANCE);
                } catch (Throwable th) {
                    obj = Result.constructor-impl(ResultKt.createFailure(th));
                }
                Throwable th2 = Result.exceptionOrNull-impl(obj);
                if (th2 != null) {
                    LogUtil.w$default(8, LogUtil.TAG_STATUSBAR, LiteUmsLoader.TAG, "stop monitor error: " + th2);
                }
            }
        }
    }

    /* JADX INFO: renamed from: com.oplus.systemui.statusbar.seeding.livealert.LiteUmsLoader$destroy$2, reason: invalid class name */
    /* JADX INFO: compiled from: go/retraceme fd74bf9514dd0739b92d67a6d48a51eb10aaf8df760d83496b8dcfcf83e5f698 */
    public final class AnonymousClass2 implements Runnable {
        public final /* synthetic */ int $r8$classId;
        public /* synthetic */ Object this$0;

        public /* synthetic */ AnonymousClass2(int i) {
            this.$r8$classId = i;
        }

        /* JADX WARN: Code duplicated, block: B:15:0x0042  */
        @Override // java.lang.Runnable
        public final void run() {
            Bundle bundleCall;
            Unit unit;
            Object obj;
            switch (this.$r8$classId) {
                case 0:
                    LiteUmsLoader.access$cleanup((LiteUmsLoader) this.this$0);
                    break;
                case 1:
                    Context currentUserContext = MultiUserContentHelper.Companion.getCurrentUserContext(((LiteUmsLoader) this.this$0).context);
                    LiteUmsLoader liteUmsLoader = (LiteUmsLoader) this.this$0;
                    if (currentUserContext == null) {
                        unit = null;
                        obj = Result.constructor-impl(unit);
                    } else {
                        try {
                            ContentResolver contentResolver = currentUserContext.getContentResolver();
                            if (contentResolver == null || (bundleCall = contentResolver.call(LiteUmsLoader.AUTHORITY, LiteUmsLoader.METHOD_PULL_UMS, (String) null, (Bundle) null)) == null) {
                                unit = null;
                            } else {
                                LiteUmsLoader.access$onUmsLoadResult(liteUmsLoader, bundleCall.getBoolean(LiteUmsLoader.RESULT_KEY));
                                unit = Unit.INSTANCE;
                            }
                            obj = Result.constructor-impl(unit);
                        } catch (Throwable th) {
                            obj = Result.constructor-impl(ResultKt.createFailure(th));
                        }
                    }
                    Throwable th2 = Result.exceptionOrNull-impl(obj);
                    if (th2 != null) {
                        LogUtil.w$default(8, LogUtil.TAG_STATUSBAR, LiteUmsLoader.TAG, "call ums error for userId: " + (currentUserContext != null ? Integer.valueOf(currentUserContext.getUserId()) : null) + ", error: " + th2);
                    }
                    break;
                default:
                    NetworkMonitor.access$notifyIfChanged((NetworkMonitor) this.this$0, null);
                    break;
            }
        }
    }

    public LiteUmsLoader(Context context) {
        this.context = context;
        LiteUmsLoader$$ExternalSyntheticLambda1 liteUmsLoader$$ExternalSyntheticLambda1 = new LiteUmsLoader$$ExternalSyntheticLambda1(0);
        liteUmsLoader$$ExternalSyntheticLambda1.f$0 = this;
        VarHandle.storeStoreFence();
        this.networkMonitor$delegate = LazyKt__LazyJVMKt.lazy(liteUmsLoader$$ExternalSyntheticLambda1);
        int i = 1;
        this.needRetry = true;
        LiteUmsLoader$$ExternalSyntheticLambda1 liteUmsLoader$$ExternalSyntheticLambda2 = new LiteUmsLoader$$ExternalSyntheticLambda1(i);
        liteUmsLoader$$ExternalSyntheticLambda2.f$0 = this;
        VarHandle.storeStoreFence();
        this.userListener$delegate = LazyKt__LazyJVMKt.lazy(liteUmsLoader$$ExternalSyntheticLambda2);
        AnonymousClass2 anonymousClass2 = new AnonymousClass2(i);
        anonymousClass2.this$0 = this;
        VarHandle.storeStoreFence();
        this.runnable = anonymousClass2;
    }

    public static final void access$cleanup(LiteUmsLoader liteUmsLoader) {
        liteUmsLoader.workHandler.removeCallbacks(liteUmsLoader.runnable);
        liteUmsLoader.getNetworkMonitor().stop();
        liteUmsLoader.needRetry = true;
    }

    public static final void access$onNetworkChanged(LiteUmsLoader liteUmsLoader, boolean z) {
        if (LogUtil.getNormalDebug() && LogUtil.isTagValid(LogUtil.TAG_STATUSBAR)) {
            Log.d("SystemUi--Statusbar", SystemUIApplicationImpl$.ExternalSyntheticOutline0.m("onNetworkChanged isActive: ", ", needRetry: ", "LiteUmsLoader-->", z, liteUmsLoader.needRetry));
        }
        Handler handler = liteUmsLoader.workHandler;
        if (z) {
            liteUmsLoader.needRetry = true;
            handler.post(liteUmsLoader.runnable);
        } else if (z) {
            LazyKt__LazyJVMKt$.ExternalSyntheticBUOutline0.m();
        } else {
            handler.removeCallbacks(liteUmsLoader.runnable);
        }
    }

    public static final void access$onUmsLoadResult(LiteUmsLoader liteUmsLoader, boolean z) {
        if (LogUtil.getNormalDebug() && LogUtil.isTagValid(LogUtil.TAG_STATUSBAR)) {
            boolean z2 = liteUmsLoader.needRetry;
            long retryDelayMs = liteUmsLoader.getRetryDelayMs();
            StringBuilder sbM = TextFieldImplKt$.ExternalSyntheticOutline0.m("onUmsLoadResult: isSuccess=", ", needRetry=", ", delay: ", z, z2);
            sbM.append(retryDelayMs);
            Log.d("SystemUi--Statusbar", "LiteUmsLoader-->".concat(sbM.toString()));
        }
        if (z || !liteUmsLoader.needRetry) {
            liteUmsLoader.getNetworkMonitor().stop();
            return;
        }
        if (LogUtil.getNormalDebug() && LogUtil.isTagValid(LogUtil.TAG_STATUSBAR)) {
            Log.d("SystemUi--Statusbar", "LiteUmsLoader-->schedule retry");
        }
        liteUmsLoader.workHandler.postDelayed(liteUmsLoader.runnable, liteUmsLoader.getRetryDelayMs());
        liteUmsLoader.needRetry = false;
    }

    public static final void access$resetState(LiteUmsLoader liteUmsLoader) {
        liteUmsLoader.workHandler.removeCallbacks(liteUmsLoader.runnable);
        liteUmsLoader.needRetry = true;
        NetworkMonitor networkMonitor = liteUmsLoader.getNetworkMonitor();
        networkMonitor.stop();
        networkMonitor.isActive = false;
    }

    /* JADX INFO: Access modifiers changed from: private */
    public final NetworkMonitor getNetworkMonitor() {
        return (NetworkMonitor) this.networkMonitor$delegate.getValue();
    }

    private final long getRetryDelayMs() {
        return ((Number) this.retryDelayMs$delegate.getValue()).longValue();
    }

    private final LiteUmsLoader$userListener$2$1 getUserListener() {
        return (LiteUmsLoader$userListener$2$1) this.userListener$delegate.getValue();
    }

    public final void destroy() {
        if (LogUtil.getNormalDebug() && LogUtil.isTagValid(LogUtil.TAG_STATUSBAR)) {
            Log.d("SystemUi--Statusbar", "LiteUmsLoader-->destroy");
        }
        MultiUserManager.Companion.getInstance().removeListener(getUserListener());
        Handler handler = this.workHandler;
        AnonymousClass2 anonymousClass2 = new AnonymousClass2(0);
        anonymousClass2.this$0 = this;
        VarHandle.storeStoreFence();
        handler.post(anonymousClass2);
    }

    public final void init() {
        if (LogUtil.getNormalDebug() && LogUtil.isTagValid(LogUtil.TAG_STATUSBAR)) {
            Log.d("SystemUi--Statusbar", "LiteUmsLoader-->init");
        }
        MultiUserManager.Companion.getInstance().addListener(getUserListener());
    }
}
