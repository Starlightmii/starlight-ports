package com.oplus.systemui.statusbar.phone.netspeed;

import android.content.Context;
import android.net.ConnectivityManager;
import android.net.Network;
import android.net.NetworkCapabilities;
import android.net.TrafficStats;
import android.os.Handler;
import android.os.Looper;
import android.os.Message;
import android.provider.Settings;
import android.util.Log;
import android.view.ViewGroup;
import androidx.car.app.model.TimerSpan$;
import androidx.compose.material3.internal.TextFieldImplKt$;
import com.android.compose.nestedscroll.PriorityNestedScrollConnection$;
import com.android.keyguard.OplusLockIconView$;
import com.android.keyguard.logging.KeyguardLogger$;
import com.android.systemui.animation.DefaultTransitionHelper$;
import com.android.systemui.application.impl.SystemUIApplicationImpl$;
import com.android.systemui.keyguard.ScreenLifecycle;
import com.android.systemui.settings.UserTracker;
import com.android.systemui.statusbar.StatusIconDisplayable;
import com.android.systemui.statusbar.phone.StatusBarIconHolder;
import com.android.systemui.statusbar.phone.networkspeed.OplusNetworkSpeedControllerEx;
import com.oplus.systemui.preventmode.OplusPreventModeData;
import com.oplus.systemui.statusbar.phone.OplusIconController;
import com.oplusos.systemui.common.feature.FeatureOption;
import com.oplusos.systemui.common.observer.NetworkSpeedObserver;
import com.oplusos.systemui.common.util.LogUtil;
import java.lang.invoke.VarHandle;
import kotlin.text.Regex;

/* JADX INFO: compiled from: go/retraceme fd74bf9514dd0739b92d67a6d48a51eb10aaf8df760d83496b8dcfcf83e5f698 */
/* JADX INFO: loaded from: /home/starlight/Projects/Starlight Ports/.build-tmp/classes4.dex */
public final class OplusNetworkSpeedControllerExImpl extends OplusNetworkSpeedControllerEx {
    private static final String LOOPBACK_IFACE = "lo";
    private static final String TAG = "OplusNetworkSpeedControllExImpl";
    private OplusNetworkSpeedControllerExImpl$bgHandler$1 bgHandler;
    private final Looper bgLooper;
    private final ConnectivityManager connectivityManager;
    private final Context context;
    public boolean isConnected;
    public boolean isPause;
    public boolean isSuspend;
    public boolean isSwitchOn;
    public long lastTime;
    public long lastTotalBytes;
    private final ScreenLifecycle.Observer mScreenObserver;
    private final OplusNetworkSpeedControllerExImpl$netWorkCallback$1 netWorkCallback = new ConnectivityManager.NetworkCallback() { // from class: com.oplus.systemui.statusbar.phone.netspeed.OplusNetworkSpeedControllerExImpl$netWorkCallback$1
        @Override // android.net.ConnectivityManager.NetworkCallback
        public final void onAvailable(Network network) {
            OplusNetworkSpeedControllerExImpl oplusNetworkSpeedControllerExImpl = this.this$0;
            LogUtil.i$default(LogUtil.TAG_STATUSBAR, "OplusNetworkSpeedControllExImpl", PriorityNestedScrollConnection$.ExternalSyntheticOutline0.m("onAvailable: isConnected:", ",isSwitchOn:", oplusNetworkSpeedControllerExImpl.isConnected, oplusNetworkSpeedControllerExImpl.isSwitchOn), null, 8);
            OplusNetworkSpeedControllerExImpl.access$updateConnectState(this.this$0, true);
        }

        @Override // android.net.ConnectivityManager.NetworkCallback
        public final void onCapabilitiesChanged(Network network, NetworkCapabilities networkCapabilities) {
            boolean z = !networkCapabilities.hasCapability(21);
            OplusNetworkSpeedControllerExImpl oplusNetworkSpeedControllerExImpl = this.this$0;
            if (oplusNetworkSpeedControllerExImpl.isSuspend != z) {
                oplusNetworkSpeedControllerExImpl.isSuspend = z;
                LogUtil.i$default(LogUtil.TAG_STATUSBAR, "OplusNetworkSpeedControllExImpl", "update capabilities:" + network + OplusPreventModeData.SEPARATOR + oplusNetworkSpeedControllerExImpl.maskPrivacyInfo(networkCapabilities.toString()) + ",isSuspend:" + this.this$0.isSuspend, null, 8);
                this.this$0.postUpdateNetworkSpeedDelay(0L);
            }
        }

        @Override // android.net.ConnectivityManager.NetworkCallback
        public final void onLost(Network network) {
            OplusNetworkSpeedControllerExImpl oplusNetworkSpeedControllerExImpl = this.this$0;
            LogUtil.i$default(LogUtil.TAG_STATUSBAR, "OplusNetworkSpeedControllExImpl", PriorityNestedScrollConnection$.ExternalSyntheticOutline0.m("onLost: isConnected:", ",isSwitchOn:", oplusNetworkSpeedControllerExImpl.isConnected, oplusNetworkSpeedControllerExImpl.isSwitchOn), null, 8);
            OplusNetworkSpeedControllerExImpl.access$updateConnectState(this.this$0, false);
        }
    };
    private final OplusNetworkSpeedControllerExImpl$networkSpeedObserver$1 networkSpeedObserver = new NetworkSpeedObserver() { // from class: com.oplus.systemui.statusbar.phone.netspeed.OplusNetworkSpeedControllerExImpl$networkSpeedObserver$1
        @Override // com.oplusos.systemui.common.observer.AbstractObserver
        public final void onChange(boolean z) {
            super.onChange(z);
            OplusNetworkSpeedControllerExImpl oplusNetworkSpeedControllerExImpl = this.this$0;
            oplusNetworkSpeedControllerExImpl.bgHandler.removeMessages(100002);
            oplusNetworkSpeedControllerExImpl.bgHandler.sendEmptyMessage(100002);
            oplusNetworkSpeedControllerExImpl.postUpdateNetworkSpeedDelay(0L);
            if (LogUtil.getNormalDebug() && LogUtil.isTagValid(LogUtil.TAG_STATUSBAR)) {
                OplusLockIconView$.ExternalSyntheticOutline0.m("onChange() : isConnected = ", "OplusNetworkSpeedControllExImpl-->", "SystemUi--Statusbar", oplusNetworkSpeedControllerExImpl.isConnected);
            }
        }
    };
    public boolean networkVisibility;
    private final OplusIconController oplusIconController;
    private final ScreenLifecycle screenLifecycle;
    private String slotNetworkSpeed;
    private final OplusNetworkSpeedControllerExImpl$uiHandler$1 uiHandler;
    private final UserTracker userTracker;
    public static final Companion Companion = new Companion();
    private static final Regex SSID_PATTERN = new Regex("SSID:\\s*[^,\\]]*");
    private static final Regex IP_PATTERN = new Regex("IP:\\s*[^,\\]]*");

    /* JADX INFO: compiled from: go/retraceme fd74bf9514dd0739b92d67a6d48a51eb10aaf8df760d83496b8dcfcf83e5f698 */
    public final class Companion {
    }

    /* JADX WARN: Type inference failed for: r1v2, types: [com.oplus.systemui.statusbar.phone.netspeed.OplusNetworkSpeedControllerExImpl$bgHandler$1] */
    /* JADX WARN: Type inference failed for: r1v3, types: [com.oplus.systemui.statusbar.phone.netspeed.OplusNetworkSpeedControllerExImpl$netWorkCallback$1] */
    /* JADX WARN: Type inference failed for: r1v4, types: [com.oplus.systemui.statusbar.phone.netspeed.OplusNetworkSpeedControllerExImpl$networkSpeedObserver$1] */
    /* JADX WARN: Type inference failed for: r2v1, types: [com.oplus.systemui.statusbar.phone.netspeed.OplusNetworkSpeedControllerExImpl$uiHandler$1] */
    public OplusNetworkSpeedControllerExImpl(Context context, final Looper looper, ConnectivityManager connectivityManager, OplusIconController oplusIconController, UserTracker userTracker, ScreenLifecycle screenLifecycle) {
        this.context = context;
        this.bgLooper = looper;
        this.connectivityManager = connectivityManager;
        this.oplusIconController = oplusIconController;
        this.userTracker = userTracker;
        this.screenLifecycle = screenLifecycle;
        this.slotNetworkSpeed = context.getString(2131956616);
        this.bgHandler = new Handler(looper) { // from class: com.oplus.systemui.statusbar.phone.netspeed.OplusNetworkSpeedControllerExImpl$bgHandler$1
            @Override // android.os.Handler
            public final void handleMessage(Message message) {
                int i = message.what;
                OplusNetworkSpeedControllerExImpl oplusNetworkSpeedControllerExImpl = this.this$0;
                switch (i) {
                    case 100001:
                        OplusNetworkSpeedControllerExImpl.access$updateNetworkSpeed(oplusNetworkSpeedControllerExImpl);
                        break;
                    case 100002:
                        oplusNetworkSpeedControllerExImpl.lastTime = 0L;
                        oplusNetworkSpeedControllerExImpl.lastTotalBytes = 0L;
                        OplusNetworkSpeedControllerExImpl.access$updateSwitchState(oplusNetworkSpeedControllerExImpl);
                        break;
                }
            }
        };
        final Looper mainLooper = Looper.getMainLooper();
        this.uiHandler = new Handler(mainLooper) { // from class: com.oplus.systemui.statusbar.phone.netspeed.OplusNetworkSpeedControllerExImpl$uiHandler$1
            @Override // android.os.Handler
            public final void handleMessage(Message message) {
                if (message.what == 100000) {
                    NetworkSpeedIconState networkSpeedIconState = new NetworkSpeedIconState();
                    boolean z = message.arg1 == 1;
                    networkSpeedIconState.setVisible(z);
                    Object obj = message.obj;
                    Long l = obj instanceof Long ? (Long) obj : null;
                    networkSpeedIconState.setSpeedText(l != null ? l.longValue() : 0L);
                    OplusNetworkSpeedControllerExImpl oplusNetworkSpeedControllerExImpl = this.this$0;
                    networkSpeedIconState.setSlot(oplusNetworkSpeedControllerExImpl.slotNetworkSpeed);
                    oplusNetworkSpeedControllerExImpl.oplusIconController.setNetWorkSpeedIcon(oplusNetworkSpeedControllerExImpl.slotNetworkSpeed, networkSpeedIconState);
                    if (oplusNetworkSpeedControllerExImpl.networkVisibility != z) {
                        if (LogUtil.getNormalDebug() && LogUtil.isTagValid(LogUtil.TAG_STATUSBAR)) {
                            Log.d("SystemUi--Statusbar", SystemUIApplicationImpl$.ExternalSyntheticOutline0.m("MSG_UPDATE_SPEED_UI : visible: ", " -> ", "OplusNetworkSpeedControllExImpl-->", oplusNetworkSpeedControllerExImpl.networkVisibility, networkSpeedIconState.getVisible()));
                        }
                        oplusNetworkSpeedControllerExImpl.networkVisibility = z;
                    }
                }
            }
        };
        OplusNetworkSpeedControllerExImpl$mScreenObserver$1 oplusNetworkSpeedControllerExImpl$mScreenObserver$1 = new OplusNetworkSpeedControllerExImpl$mScreenObserver$1();
        oplusNetworkSpeedControllerExImpl$mScreenObserver$1.this$0 = this;
        VarHandle.storeStoreFence();
        this.mScreenObserver = oplusNetworkSpeedControllerExImpl$mScreenObserver$1;
    }

    public static final void access$updateConnectState(OplusNetworkSpeedControllerExImpl oplusNetworkSpeedControllerExImpl, boolean z) {
        oplusNetworkSpeedControllerExImpl.isConnected = z;
        if (LogUtil.getNormalDebug() && LogUtil.isTagValid(LogUtil.TAG_STATUSBAR)) {
            OplusLockIconView$.ExternalSyntheticOutline0.m("updateConnectState() : isConnected = ", "OplusNetworkSpeedControllExImpl-->", "SystemUi--Statusbar", oplusNetworkSpeedControllerExImpl.isConnected);
        }
        oplusNetworkSpeedControllerExImpl.postUpdateNetworkSpeedDelay(0L);
    }

    public static final void access$updateNetworkSpeed(OplusNetworkSpeedControllerExImpl oplusNetworkSpeedControllerExImpl) {
        Message messageObtain = Message.obtain();
        messageObtain.what = 100000;
        boolean z = oplusNetworkSpeedControllerExImpl.isConnected;
        boolean z2 = oplusNetworkSpeedControllerExImpl.isSwitchOn;
        LogUtil.i$default(LogUtil.TAG_STATUSBAR, TAG, KeyguardLogger$.ExternalSyntheticOutline0.m(TextFieldImplKt$.ExternalSyntheticOutline0.m("updateNetworkSpeed: isConnected:", ",isSwitchOn:", ",isSuspend:", z, z2), oplusNetworkSpeedControllerExImpl.isSuspend, ",isPause:", oplusNetworkSpeedControllerExImpl.isPause), null, 8);
        long j = 0;
        if (oplusNetworkSpeedControllerExImpl.isConnected && oplusNetworkSpeedControllerExImpl.isSwitchOn) {
            FeatureOption.INSTANCE.getClass();
            if (!FeatureOption.isVersionSellMode()) {
                if (oplusNetworkSpeedControllerExImpl.isSuspend) {
                    messageObtain.arg1 = 1;
                    oplusNetworkSpeedControllerExImpl.uiHandler.removeMessages(100000);
                    oplusNetworkSpeedControllerExImpl.uiHandler.sendMessage(messageObtain);
                    oplusNetworkSpeedControllerExImpl.lastTime = 0L;
                    oplusNetworkSpeedControllerExImpl.lastTotalBytes = 0L;
                    return;
                }
                if (oplusNetworkSpeedControllerExImpl.isPause) {
                    return;
                }
                long jCurrentTimeMillis = System.currentTimeMillis();
                long totalByte = oplusNetworkSpeedControllerExImpl.getTotalByte();
                if (totalByte <= 0) {
                    oplusNetworkSpeedControllerExImpl.lastTime = 0L;
                    oplusNetworkSpeedControllerExImpl.lastTotalBytes = 0L;
                    totalByte = oplusNetworkSpeedControllerExImpl.getTotalByte();
                }
                long j2 = oplusNetworkSpeedControllerExImpl.lastTime;
                if (0 <= j2 && j2 < jCurrentTimeMillis) {
                    long j3 = oplusNetworkSpeedControllerExImpl.lastTotalBytes;
                    if (j3 > 0 && totalByte > 0 && totalByte > j3) {
                        j = ((totalByte - j3) * 1000) / (jCurrentTimeMillis - j2);
                    }
                }
                messageObtain.arg1 = 1;
                messageObtain.obj = Long.valueOf(j);
                oplusNetworkSpeedControllerExImpl.uiHandler.removeMessages(100000);
                oplusNetworkSpeedControllerExImpl.uiHandler.sendMessage(messageObtain);
                oplusNetworkSpeedControllerExImpl.lastTime = jCurrentTimeMillis;
                oplusNetworkSpeedControllerExImpl.lastTotalBytes = totalByte;
                oplusNetworkSpeedControllerExImpl.postUpdateNetworkSpeedDelay(4000L);
                return;
            }
        }
        messageObtain.arg1 = 0;
        oplusNetworkSpeedControllerExImpl.uiHandler.removeMessages(100000);
        oplusNetworkSpeedControllerExImpl.uiHandler.sendMessage(messageObtain);
        oplusNetworkSpeedControllerExImpl.lastTime = 0L;
        oplusNetworkSpeedControllerExImpl.lastTotalBytes = 0L;
    }

    public static final void access$updateSwitchState(OplusNetworkSpeedControllerExImpl oplusNetworkSpeedControllerExImpl) {
        boolean z = Settings.System.getIntForUser(oplusNetworkSpeedControllerExImpl.context.getContentResolver(), NetworkSpeedObserver.SHOW_NETWORK_SPEED, 0, oplusNetworkSpeedControllerExImpl.userTracker.getUserId()) != 0;
        oplusNetworkSpeedControllerExImpl.isSwitchOn = z;
        LogUtil.i$default(LogUtil.TAG_STATUSBAR, TAG, KeyguardLogger$.ExternalSyntheticOutline0.m("updateSwitchState() : isSwitchOn = ", z), null, 8);
    }

    private final long getTotalByte() {
        long totalRxBytes = TrafficStats.getTotalRxBytes();
        long totalTxBytes = TrafficStats.getTotalTxBytes();
        long txBytes = TrafficStats.getTxBytes(LOOPBACK_IFACE);
        long rxBytes = TrafficStats.getRxBytes(LOOPBACK_IFACE);
        long j = ((totalRxBytes + totalTxBytes) - txBytes) - rxBytes;
        StringBuilder sbM = TimerSpan$.ExternalSyntheticOutline0.m(totalRxBytes, "getTotalByte: totalRxBytes:", ",totalTxBytes:");
        sbM.append(totalTxBytes);
        DefaultTransitionHelper$.ExternalSyntheticOutline0.m(sbM, ",loopbackTxBytes:", txBytes, ",loopbackRxBytes:");
        sbM.append(rxBytes);
        sbM.append(" ,totalBytes:");
        sbM.append(j);
        LogUtil.i$default(LogUtil.TAG_STATUSBAR, TAG, sbM.toString(), null, 8);
        if (j >= 0) {
            return j;
        }
        return 0L;
    }

    public StatusIconDisplayable addHolder(int i, String str, ViewGroup viewGroup, StatusBarIconHolder statusBarIconHolder, boolean z) {
        return this.oplusIconController.addHolder(i, str, viewGroup, statusBarIconHolder, z);
    }

    public final void init() {
        if (LogUtil.getNormalDebug() && LogUtil.isTagValid(LogUtil.TAG_STATUSBAR)) {
            OplusLockIconView$.ExternalSyntheticOutline0.m("init() : isConnected = ", "OplusNetworkSpeedControllExImpl-->", "SystemUi--Statusbar", this.isConnected);
        }
        this.connectivityManager.registerDefaultNetworkCallback(this.netWorkCallback, this.bgHandler);
        registerForUserSwitch(this.context, -1, false);
        this.screenLifecycle.addObserver(this.mScreenObserver);
    }

    public final String maskPrivacyInfo(String str) {
        return IP_PATTERN.replace(SSID_PATTERN.replace(str, new OplusNetworkSpeedControllerExImpl$.ExternalSyntheticLambda0(0)), new OplusNetworkSpeedControllerExImpl$.ExternalSyntheticLambda0(1));
    }

    public void onSetIconHolder(int i, StatusBarIconHolder statusBarIconHolder, ViewGroup viewGroup) {
        this.oplusIconController.onSetIconHolder(i, statusBarIconHolder, viewGroup);
    }

    public final void postUpdateNetworkSpeedDelay(long j) {
        removeMessages(100001);
        sendEmptyMessageDelayed(100001, j);
    }
}
