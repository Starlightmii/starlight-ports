package com.oplusos.systemui.common.battery;

import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import android.content.IntentFilter;
import androidx.constraintlayout.widget.ConstraintSet$;
import com.android.systemui.common.charge.OplusAddBatteryStatus;
import com.android.systemui.common.charge.OplusBatteryStatus;
import com.android.systemui.statusbar.policy.BatteryControllerLogger;
import com.oplus.systemui.provider.ProviderUtil;
import com.oplusos.systemui.common.util.LogUtil;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;
import kotlin.Lazy;
import kotlin.LazyKt__LazyJVMKt;
import kotlin.LazyThreadSafetyMode;

/* JADX INFO: compiled from: go/retraceme fd74bf9514dd0739b92d67a6d48a51eb10aaf8df760d83496b8dcfcf83e5f698 */
/* JADX INFO: loaded from: /home/starlight/Projects/Starlight Ports/.build-tmp/classes4.dex */
public final class OplusBatteryController extends BaseBatteryControllerExt {
    public static final String ACTION_UI_BATTERY_CHANGED = "android.intent.action.UI_BATTERY_CHANGED";
    public static final String TAG = "BatteryControllerExt";
    public boolean hasRegisterFastCharging;
    public boolean isAdditionalBatteryFirst;
    public int level;
    public static final Companion Companion = new Companion();
    private static final Lazy instance$delegate = LazyKt__LazyJVMKt.lazy(LazyThreadSafetyMode.SYNCHRONIZED, new OplusBatteryController$$ExternalSyntheticLambda0(0));
    private final OplusAddBatteryStatus oplusAddBatteryStatus = new OplusAddBatteryStatus();
    private final List<IBatteryStateChanged> listeners = new ArrayList();
    private final Lazy logger$delegate = LazyKt__LazyJVMKt.lazy(new OplusBatteryController$$ExternalSyntheticLambda0(1));

    /* JADX INFO: compiled from: go/retraceme fd74bf9514dd0739b92d67a6d48a51eb10aaf8df760d83496b8dcfcf83e5f698 */
    public final class Companion {
        public static /* synthetic */ void getInstance$annotations() {
        }

        public final OplusBatteryController getInstance() {
            return (OplusBatteryController) OplusBatteryController.instance$delegate.getValue();
        }
    }

    /* JADX INFO: compiled from: go/retraceme fd74bf9514dd0739b92d67a6d48a51eb10aaf8df760d83496b8dcfcf83e5f698 */
    public interface IBatteryStateChanged {
        default void onAdditionalBatteryStateChanged(OplusAddBatteryStatus oplusAddBatteryStatus) {
        }

        default void onBatteryStateChanged(int i) {
        }

        default void onBatteryStateChanged(OplusBatteryStatus oplusBatteryStatus) {
        }
    }

    public static final OplusBatteryController getInstance() {
        return Companion.getInstance();
    }

    private final BatteryControllerLogger getLogger() {
        return (BatteryControllerLogger) this.logger$delegate.getValue();
    }

    public final void fireAdditionalBatteryStateChanged(OplusAddBatteryStatus oplusAddBatteryStatus) {
        synchronized (this.listeners) {
            Iterator<T> it = this.listeners.iterator();
            while (it.hasNext()) {
                ((IBatteryStateChanged) it.next()).onAdditionalBatteryStateChanged(oplusAddBatteryStatus);
            }
        }
    }

    @Override // com.oplusos.systemui.common.battery.BaseBatteryControllerExt
    public boolean getCharging() {
        return this.oplusAddBatteryStatus.getBatteryCharging();
    }

    public final int getLevel() {
        return this.level;
    }

    public final int getPlugged() {
        return this.oplusAddBatteryStatus.getPlugged();
    }

    public final boolean getWirelessCharging() {
        return this.oplusAddBatteryStatus.isWirelessCharging;
    }

    public final boolean getWirelessChargingReverse() {
        return this.oplusAddBatteryStatus.isWirelessChargingReverse;
    }

    public boolean handleBroadcast(Intent intent) {
        String action = intent.getAction();
        if (action != null) {
            int iHashCode = action.hashCode();
            if (iHashCode != -1538406691) {
                if (iHashCode != -743896036) {
                    if (iHashCode == -429323601 && action.equals("android.intent.action.ADDITIONAL_BATTERY_CHANGED")) {
                        getLogger().logIntentReceived("android.intent.action.ADDITIONAL_BATTERY_CHANGED");
                        this.isAdditionalBatteryFirst = true;
                        this.oplusAddBatteryStatus.updateIntent(this.level, intent);
                        fireAdditionalBatteryStateChanged(this.oplusAddBatteryStatus);
                        return true;
                    }
                } else if (action.equals(ACTION_UI_BATTERY_CHANGED)) {
                    getLogger().logIntentReceived(ACTION_UI_BATTERY_CHANGED);
                    this.isAdditionalBatteryFirst = true;
                    this.oplusAddBatteryStatus.setLevel(this.level);
                    this.oplusAddBatteryStatus.updateChargingStateFromUiIntent(intent);
                    fireAdditionalBatteryStateChanged(this.oplusAddBatteryStatus);
                    return true;
                }
            } else if (action.equals("android.intent.action.BATTERY_CHANGED")) {
                getLogger().logIntentReceived("android.intent.action.BATTERY_CHANGED");
                boolean z = this.level < 20;
                int intExtra = intent.getIntExtra("scale", 100);
                if (intExtra != 0) {
                    float f = intExtra;
                    int intExtra2 = (int) (((1.0f * f) * intent.getIntExtra("level", 0)) / f);
                    if (intExtra2 < 0) {
                        intExtra2 = 0;
                    }
                    this.level = intExtra2 <= 100 ? intExtra2 : 100;
                }
                boolean z2 = this.level < 20;
                StringBuilder sbM = ConstraintSet$.ExternalSyntheticOutline0.m(this.level, "updateBatteryChangeIntent():broadcast=", intent.getAction(), ",level=", ",scale=");
                sbM.append(intExtra);
                LogUtil.i$default(LogUtil.TAG_STATUSBAR, TAG, sbM.toString(), null, 8);
                boolean z3 = z2 != z;
                OplusBatteryStatus oplusBatteryStatus = new OplusBatteryStatus(intent);
                synchronized (this.listeners) {
                    for (IBatteryStateChanged iBatteryStateChanged : this.listeners) {
                        iBatteryStateChanged.onBatteryStateChanged(oplusBatteryStatus);
                        iBatteryStateChanged.onBatteryStateChanged(this.level);
                    }
                }
                if (this.isAdditionalBatteryFirst && z3) {
                    this.oplusAddBatteryStatus.setHighLowLevelChange(true);
                    this.oplusAddBatteryStatus.setLevel(this.level);
                    fireAdditionalBatteryStateChanged(this.oplusAddBatteryStatus);
                }
                return true;
            }
        }
        return false;
    }

    public final boolean isFull() {
        return this.oplusAddBatteryStatus.isFull;
    }

    public final void registerFastChargingBroadcastReceiver(BroadcastReceiver broadcastReceiver, Context context) {
        if (this.hasRegisterFastCharging) {
            return;
        }
        IntentFilter intentFilter = new IntentFilter();
        intentFilter.addAction(ACTION_UI_BATTERY_CHANGED);
        intentFilter.addAction("android.intent.action.ADDITIONAL_BATTERY_CHANGED");
        context.registerReceiver(broadcastReceiver, intentFilter, ProviderUtil.OPLUS_COMPONENT_SAFE, null, 2);
        Intent intentRegisterReceiver = context.registerReceiver(null, new IntentFilter(ACTION_UI_BATTERY_CHANGED));
        if (intentRegisterReceiver != null) {
            broadcastReceiver.onReceive(context, intentRegisterReceiver);
        }
        Intent intentRegisterReceiver2 = context.registerReceiver(null, new IntentFilter("android.intent.action.ADDITIONAL_BATTERY_CHANGED"));
        if (intentRegisterReceiver2 != null) {
            broadcastReceiver.onReceive(context, intentRegisterReceiver2);
        }
        this.hasRegisterFastCharging = true;
    }

    public final void registerIBatteryStateChanged(IBatteryStateChanged iBatteryStateChanged) {
        synchronized (this.listeners) {
            this.listeners.add(iBatteryStateChanged);
            iBatteryStateChanged.onBatteryStateChanged(this.level);
            iBatteryStateChanged.onAdditionalBatteryStateChanged(this.oplusAddBatteryStatus);
        }
    }

    public final void unregisterIBatteryStateChanged(IBatteryStateChanged iBatteryStateChanged) {
        synchronized (this.listeners) {
            this.listeners.remove(iBatteryStateChanged);
        }
    }
}
