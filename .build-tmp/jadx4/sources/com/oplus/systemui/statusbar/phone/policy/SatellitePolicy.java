package com.oplus.systemui.statusbar.phone.policy;

import android.content.Context;
import android.os.Handler;
import com.android.keyguard.OplusLockIconView$;
import com.android.systemui.statusbar.phone.ui.StatusBarIconController;
import com.android.systemui.statusbar.policy.CallbackController;
import com.oplus.evolution.EvolutionConnector;
import com.oplus.evolution.SatelliteCallback;
import com.oplus.evolution.SatelliteProxy;
import com.oplus.evolution.SatelliteReadyCallback;
import com.oplus.evolution.SatelliteServiceState;
import com.oplusos.systemui.common.feature.FeatureOption;
import com.oplusos.systemui.common.util.LogUtil;
import java.io.PrintWriter;
import java.lang.invoke.VarHandle;
import java.util.List;
import java.util.concurrent.CopyOnWriteArrayList;
import java.util.concurrent.Executor;
import kotlin.collections.ArraysKt___ArraysKt;

/* JADX INFO: compiled from: go/retraceme fd74bf9514dd0739b92d67a6d48a51eb10aaf8df760d83496b8dcfcf83e5f698 */
/* JADX INFO: loaded from: /home/starlight/Projects/Starlight Ports/.build-tmp/classes4.dex */
public final class SatellitePolicy implements IconBlockPolicy, CallbackController {
    public static final Companion Companion = new Companion();
    private static final String KEY_CARRIER_NAME = "SATELLITE_CARRIER_NAME";
    private static final String TAG = "SatellitePolicy";
    private final Executor bgExecutor;
    private final Handler bgHandler;
    private final CopyOnWriteArrayList<IConnectionCallback> callbacks = new CopyOnWriteArrayList<>();
    private String carrierName = "";
    private final SatellitePolicy$carrierNameObserver$1 carrierNameObserver = new SatellitePolicy$carrierNameObserver$1();
    private final Context context;
    private EvolutionConnector evolutionConnector;
    public boolean inservice;
    private final Handler mainHandler;
    private final OplusPolicyObserver oplusPolicyObserver;
    private SatelliteCallback satelliteCallback;
    public boolean satelliteEnable;
    private SatelliteProxy satelliteProxy;
    public boolean satelliteReady;
    private SatelliteReadyCallback satelliteReadyCallback;
    private final String slot;
    private StatusBarIconController statusBarIconController;

    /* JADX INFO: compiled from: go/retraceme fd74bf9514dd0739b92d67a6d48a51eb10aaf8df760d83496b8dcfcf83e5f698 */
    public final class Companion {
    }

    /* JADX INFO: compiled from: go/retraceme fd74bf9514dd0739b92d67a6d48a51eb10aaf8df760d83496b8dcfcf83e5f698 */
    public interface IConnectionCallback {
        default void onCarrierNameChanged(String str) {
        }

        void onSatelliteConnected();

        void onSatelliteDisConnected();

        default void onServiceStateChanged(boolean z) {
        }
    }

    /* JADX INFO: compiled from: go/retraceme fd74bf9514dd0739b92d67a6d48a51eb10aaf8df760d83496b8dcfcf83e5f698 */
    public final class InnerSatelliteCallback extends SatelliteCallback implements SatelliteCallback.ModeStateChangedListener, SatelliteCallback.ServiceStateChangedListener {
        public InnerSatelliteCallback() {
        }

        public final void onModeStateStateChanged(int i, boolean z) {
            Handler handler = SatellitePolicy.this.mainHandler;
            SatellitePolicy satellitePolicy = SatellitePolicy.this;
            onModeStateStateChanged.1 r1 = new onModeStateStateChanged.1();
            r1.$mode = i;
            r1.$enable = z;
            r1.this$0 = satellitePolicy;
            VarHandle.storeStoreFence();
            handler.post(r1);
        }

        public void onServiceStateChanged(SatelliteServiceState satelliteServiceState) {
            SatellitePolicy.this.inservice = satelliteServiceState.isInservice();
            SatellitePolicy satellitePolicy = SatellitePolicy.this;
            if (LogUtil.getNormalDebug() && LogUtil.isTagValid(LogUtil.TAG_STATUSBAR)) {
                OplusLockIconView$.ExternalSyntheticOutline0.m("onServiceStateChanged inservice: ", "SatellitePolicy-->", "SystemUi--Statusbar", satellitePolicy.inservice);
            }
            CopyOnWriteArrayList<IConnectionCallback> copyOnWriteArrayList = SatellitePolicy.this.callbacks;
            SatellitePolicy satellitePolicy2 = SatellitePolicy.this;
            for (IConnectionCallback iConnectionCallback : copyOnWriteArrayList) {
                if (iConnectionCallback != null) {
                    iConnectionCallback.onServiceStateChanged(satellitePolicy2.inservice);
                }
            }
        }
    }

    public SatellitePolicy(Context context, OplusPolicyObserver oplusPolicyObserver, StatusBarIconController statusBarIconController, Handler handler, Handler handler2, Executor executor) {
        this.context = context;
        this.oplusPolicyObserver = oplusPolicyObserver;
        this.statusBarIconController = statusBarIconController;
        this.bgHandler = handler;
        this.mainHandler = handler2;
        this.bgExecutor = executor;
        this.slot = context.getResources().getString(2131956632);
    }

    public void addCallback(IConnectionCallback iConnectionCallback) {
        if (FeatureOption.isOplusSatelliteSupport() && !this.callbacks.contains(iConnectionCallback)) {
            this.callbacks.add(iConnectionCallback);
            if (this.satelliteReady) {
                iConnectionCallback.onSatelliteConnected();
            } else {
                iConnectionCallback.onSatelliteDisConnected();
            }
            for (IConnectionCallback iConnectionCallback2 : this.callbacks) {
                if (iConnectionCallback2 != null) {
                    iConnectionCallback2.onServiceStateChanged(this.inservice);
                }
            }
        }
    }

    public void dump(PrintWriter printWriter, String[] strArr) {
        printWriter.println("SatellitePolicy:");
        printWriter.println("  isSupport: " + FeatureOption.isOplusSatelliteSupport() + " " + FeatureOption.isOplusSatelliteSupport());
        OplusLockIconView$.ExternalSyntheticOutline0.m(printWriter, "  satelliteEnable: ", this.satelliteEnable);
        OplusLockIconView$.ExternalSyntheticOutline0.m(printWriter, "  satelliteReady: ", this.satelliteReady);
        printWriter.println("  evolutionConnector: " + this.evolutionConnector);
        printWriter.println("  callbacks: " + this.callbacks);
    }

    @Override // com.oplus.systemui.statusbar.phone.policy.IconBlockPolicy
    public List<String> getBlockList() {
        return this.satelliteEnable ? ArraysKt___ArraysKt.toList(this.context.getResources().getStringArray(2130903168)) : super.getBlockList();
    }

    public final void init() {
        if (!FeatureOption.isOplusSatelliteSupport()) {
            LogUtil.e$default(8, LogUtil.TAG_STATUSBAR, TAG, "not support satellite feature");
            return;
        }
        LogUtil.i$default(LogUtil.TAG_STATUSBAR, TAG, " init:SatelliteObserver", null, 8);
        Handler handler = this.bgHandler;
        init.1 r1 = new init.1();
        r1.this$0 = this;
        VarHandle.storeStoreFence();
        handler.post(r1);
    }

    public void removeCallback(IConnectionCallback iConnectionCallback) {
        this.callbacks.remove(iConnectionCallback);
    }
}
