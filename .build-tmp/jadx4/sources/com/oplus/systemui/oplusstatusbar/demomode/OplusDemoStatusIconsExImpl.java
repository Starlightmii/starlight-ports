package com.oplus.systemui.oplusstatusbar.demomode;

import android.content.Context;
import android.content.res.Resources;
import android.graphics.drawable.Icon;
import android.os.Bundle;
import android.os.UserHandle;
import android.util.Log;
import android.widget.LinearLayout;
import com.android.internal.statusbar.StatusBarIcon;
import com.android.systemui.statusbar.StatusIconDisplayable;
import com.android.systemui.statusbar.demomode.OplusDemoStatusIconsEx;
import com.android.systemui.statusbar.phone.DemoStatusIcons;
import com.oplus.systemui.statusbar.phone.OplusStatusBarIcon;
import com.oplus.systemui.statusbar.widget.StatIconView;
import com.oplusos.systemui.common.util.LogUtil;
import java.lang.invoke.VarHandle;
import kotlin.Lazy;
import kotlin.LazyKt__LazyJVMKt;

/* JADX INFO: compiled from: go/retraceme fd74bf9514dd0739b92d67a6d48a51eb10aaf8df760d83496b8dcfcf83e5f698 */
/* JADX INFO: loaded from: /home/starlight/Projects/Starlight Ports/.build-tmp/classes4.dex */
public final class OplusDemoStatusIconsExImpl extends OplusDemoStatusIconsEx {
    public static final Companion Companion = new Companion();
    private static final String EXTRA_SHOW = "show";
    private static final String TAG = "OplusDemoStatusIconsExImpl";
    private final Resources resources;
    private final Lazy slotAlarmClock$delegate;
    private final String slotBlockBanner;
    private final Lazy slotBluetooth$delegate;
    private final Lazy slotConnectedDisplay$delegate;
    private final Lazy slotDataSaver$delegate;
    private final String slotDemoAirplane;
    private final String slotDemoStealth;
    private final String slotDemoWirelessReverse;
    private final Lazy slotHeadset$delegate;
    private final String slotHighPerformance;
    private final Lazy slotLocation$delegate;
    private final String slotLogging;
    private final Lazy slotNfc$delegate;
    private final Lazy slotSatellite$delegate;
    private final Lazy slotVolume$delegate;
    private final Lazy slotVpn$delegate;
    private final Lazy slotWifi$delegate;
    private final Lazy slotZen$delegate;

    public OplusDemoStatusIconsExImpl(Resources resources) {
        this.resources = resources;
        OplusDemoStatusIconsExImpl$.ExternalSyntheticLambda0 externalSyntheticLambda0 = new OplusDemoStatusIconsExImpl$.ExternalSyntheticLambda0(0);
        externalSyntheticLambda0.f$0 = this;
        VarHandle.storeStoreFence();
        this.slotWifi$delegate = LazyKt__LazyJVMKt.lazy(externalSyntheticLambda0);
        OplusDemoStatusIconsExImpl$.ExternalSyntheticLambda0 externalSyntheticLambda1 = new OplusDemoStatusIconsExImpl$.ExternalSyntheticLambda0(5);
        externalSyntheticLambda1.f$0 = this;
        VarHandle.storeStoreFence();
        this.slotBluetooth$delegate = LazyKt__LazyJVMKt.lazy(externalSyntheticLambda1);
        OplusDemoStatusIconsExImpl$.ExternalSyntheticLambda0 externalSyntheticLambda2 = new OplusDemoStatusIconsExImpl$.ExternalSyntheticLambda0(6);
        externalSyntheticLambda2.f$0 = this;
        VarHandle.storeStoreFence();
        this.slotLocation$delegate = LazyKt__LazyJVMKt.lazy(externalSyntheticLambda2);
        OplusDemoStatusIconsExImpl$.ExternalSyntheticLambda0 externalSyntheticLambda3 = new OplusDemoStatusIconsExImpl$.ExternalSyntheticLambda0(7);
        externalSyntheticLambda3.f$0 = this;
        VarHandle.storeStoreFence();
        this.slotVolume$delegate = LazyKt__LazyJVMKt.lazy(externalSyntheticLambda3);
        OplusDemoStatusIconsExImpl$.ExternalSyntheticLambda0 externalSyntheticLambda4 = new OplusDemoStatusIconsExImpl$.ExternalSyntheticLambda0(8);
        externalSyntheticLambda4.f$0 = this;
        VarHandle.storeStoreFence();
        this.slotAlarmClock$delegate = LazyKt__LazyJVMKt.lazy(externalSyntheticLambda4);
        OplusDemoStatusIconsExImpl$.ExternalSyntheticLambda0 externalSyntheticLambda5 = new OplusDemoStatusIconsExImpl$.ExternalSyntheticLambda0(9);
        externalSyntheticLambda5.f$0 = this;
        VarHandle.storeStoreFence();
        this.slotZen$delegate = LazyKt__LazyJVMKt.lazy(externalSyntheticLambda5);
        OplusDemoStatusIconsExImpl$.ExternalSyntheticLambda0 externalSyntheticLambda6 = new OplusDemoStatusIconsExImpl$.ExternalSyntheticLambda0(10);
        externalSyntheticLambda6.f$0 = this;
        VarHandle.storeStoreFence();
        this.slotHeadset$delegate = LazyKt__LazyJVMKt.lazy(externalSyntheticLambda6);
        OplusDemoStatusIconsExImpl$.ExternalSyntheticLambda0 externalSyntheticLambda7 = new OplusDemoStatusIconsExImpl$.ExternalSyntheticLambda0(11);
        externalSyntheticLambda7.f$0 = this;
        VarHandle.storeStoreFence();
        this.slotNfc$delegate = LazyKt__LazyJVMKt.lazy(externalSyntheticLambda7);
        OplusDemoStatusIconsExImpl$.ExternalSyntheticLambda0 externalSyntheticLambda8 = new OplusDemoStatusIconsExImpl$.ExternalSyntheticLambda0(1);
        externalSyntheticLambda8.f$0 = this;
        VarHandle.storeStoreFence();
        this.slotVpn$delegate = LazyKt__LazyJVMKt.lazy(externalSyntheticLambda8);
        OplusDemoStatusIconsExImpl$.ExternalSyntheticLambda0 externalSyntheticLambda9 = new OplusDemoStatusIconsExImpl$.ExternalSyntheticLambda0(2);
        externalSyntheticLambda9.f$0 = this;
        VarHandle.storeStoreFence();
        this.slotDataSaver$delegate = LazyKt__LazyJVMKt.lazy(externalSyntheticLambda9);
        OplusDemoStatusIconsExImpl$.ExternalSyntheticLambda0 externalSyntheticLambda10 = new OplusDemoStatusIconsExImpl$.ExternalSyntheticLambda0(3);
        externalSyntheticLambda10.f$0 = this;
        VarHandle.storeStoreFence();
        this.slotConnectedDisplay$delegate = LazyKt__LazyJVMKt.lazy(externalSyntheticLambda10);
        OplusDemoStatusIconsExImpl$.ExternalSyntheticLambda0 externalSyntheticLambda11 = new OplusDemoStatusIconsExImpl$.ExternalSyntheticLambda0(4);
        externalSyntheticLambda11.f$0 = this;
        VarHandle.storeStoreFence();
        this.slotSatellite$delegate = LazyKt__LazyJVMKt.lazy(externalSyntheticLambda11);
        this.slotHighPerformance = "high_performance";
        this.slotLogging = "logging";
        this.slotBlockBanner = "block_banner";
        this.slotDemoWirelessReverse = "oplus_demo_wireless_reverse";
        this.slotDemoStealth = "oplus_demo_stealth_mode";
        this.slotDemoAirplane = "oplus_demo_airplane";
    }

    private final String getSlotAlarmClock() {
        return (String) this.slotAlarmClock$delegate.getValue();
    }

    private final String getSlotBluetooth() {
        return (String) this.slotBluetooth$delegate.getValue();
    }

    private final String getSlotConnectedDisplay() {
        return (String) this.slotConnectedDisplay$delegate.getValue();
    }

    private final String getSlotDataSaver() {
        return (String) this.slotDataSaver$delegate.getValue();
    }

    private final String getSlotHeadset() {
        return (String) this.slotHeadset$delegate.getValue();
    }

    private final String getSlotLocation() {
        return (String) this.slotLocation$delegate.getValue();
    }

    private final String getSlotNfc() {
        return (String) this.slotNfc$delegate.getValue();
    }

    private final String getSlotSatellite() {
        return (String) this.slotSatellite$delegate.getValue();
    }

    private final String getSlotVolume() {
        return (String) this.slotVolume$delegate.getValue();
    }

    private final String getSlotVpn() {
        return (String) this.slotVpn$delegate.getValue();
    }

    private final String getSlotWifi() {
        return (String) this.slotWifi$delegate.getValue();
    }

    private final String getSlotZen() {
        return (String) this.slotZen$delegate.getValue();
    }

    public static void updateIcon$default(Bundle bundle, DemoStatusIcons demoStatusIcons, String str, String str2, int i) {
        String string;
        if (bundle == null || (string = bundle.getString(str2)) == null) {
            return;
        }
        if (!EXTRA_SHOW.equals(string)) {
            demoStatusIcons.updateSlot(0, str, false);
        } else {
            demoStatusIcons.updateSlot(0, str, false);
            demoStatusIcons.updateSlot(i, str, false);
        }
    }

    public void dispatchDemoCommand(String str, Bundle bundle, DemoStatusIcons demoStatusIcons) {
        String string;
        if (LogUtil.getNormalDebug() && LogUtil.isTagValid(LogUtil.TAG_STATUSBAR)) {
            Log.d("SystemUi--Statusbar", "OplusDemoStatusIconsExImpl-->".concat("dispatchDemoCommand command:" + str + " args:" + bundle));
        }
        updateIcon$default(bundle, demoStatusIcons, getSlotWifi(), "stat_signal_wifi_signal_0", 2131236921);
        updateIcon$default(bundle, demoStatusIcons, getSlotWifi(), "stat_signal_wifi_signal_1", 2131236923);
        updateIcon$default(bundle, demoStatusIcons, getSlotWifi(), "stat_signal_wifi_signal_2", 2131236925);
        updateIcon$default(bundle, demoStatusIcons, getSlotWifi(), "stat_signal_wifi_signal_3", 2131236927);
        updateIcon$default(bundle, demoStatusIcons, getSlotWifi(), "stat_signal_wifi_signal_4", 2131236929);
        updateIcon$default(bundle, demoStatusIcons, getSlotBluetooth(), "stat_sys_data_bluetooth", 2131236940);
        updateIcon$default(bundle, demoStatusIcons, getSlotBluetooth(), "stat_bt_battery_black_0", 2131236716);
        updateIconBluetoothLowComposite(bundle, demoStatusIcons, "stat_bt_battery_low_1", 2131236724);
        updateIconBluetoothLowComposite(bundle, demoStatusIcons, "stat_bt_battery_low_2", 2131236725);
        updateIcon$default(bundle, demoStatusIcons, getSlotBluetooth(), "stat_bt_battery_black_3", 2131236717);
        updateIcon$default(bundle, demoStatusIcons, getSlotBluetooth(), "stat_bt_battery_black_4", 2131236718);
        updateIcon$default(bundle, demoStatusIcons, getSlotBluetooth(), "stat_bt_battery_black_5", 2131236719);
        updateIcon$default(bundle, demoStatusIcons, getSlotBluetooth(), "stat_bt_battery_black_6", 2131236720);
        updateIcon$default(bundle, demoStatusIcons, getSlotBluetooth(), "stat_bt_battery_black_7", 2131236721);
        updateIcon$default(bundle, demoStatusIcons, getSlotBluetooth(), "stat_bt_battery_black_8", 2131236722);
        updateIcon$default(bundle, demoStatusIcons, getSlotBluetooth(), "stat_bt_battery_black_9", 2131236723);
        updateIcon$default(bundle, demoStatusIcons, getSlotBluetooth(), "stat_sys_data_bluetooth_connected_ing", 2131236942);
        updateIcon$default(bundle, demoStatusIcons, getSlotBluetooth(), "stat_sys_data_bluetooth_connected_stop", 2131236943);
        updateIcon$default(bundle, demoStatusIcons, getSlotLocation(), "stat_sys_location", 2131236978);
        updateIcon$default(bundle, demoStatusIcons, getSlotVolume(), "stat_sys_ringer_vibrate", 2131236986);
        updateIcon$default(bundle, demoStatusIcons, getSlotVolume(), "stat_sys_ringer_silent", 2131236985);
        updateIcon$default(bundle, demoStatusIcons, getSlotAlarmClock(), "stat_sys_alarm", 2131236933);
        updateIcon$default(bundle, demoStatusIcons, this.slotHighPerformance, "stat_sys_high_performance", 2131236976);
        updateIcon$default(bundle, demoStatusIcons, getSlotZen(), "stat_sys_dnd", 2131236970);
        updateIcon$default(bundle, demoStatusIcons, getSlotHeadset(), "stat_sys_headset", 2131236974);
        updateIcon$default(bundle, demoStatusIcons, getSlotNfc(), "stat_sys_nfc", 2131236982);
        updateIcon$default(bundle, demoStatusIcons, getSlotVpn(), "stat_sys_vpn_ic", 2131237065);
        updateIcon$default(bundle, demoStatusIcons, getSlotDataSaver(), "stat_sys_data_saver", 2131236969);
        updateIcon$default(bundle, demoStatusIcons, this.slotLogging, "stat_sys_logging", 2131236979);
        updateIcon$default(bundle, demoStatusIcons, this.slotDemoWirelessReverse, "stat_charge_wireless_reverse", 2131236729);
        updateIcon$default(bundle, demoStatusIcons, this.slotBlockBanner, "stat_sys_tool_block_banner_on", 2131237063);
        updateIcon$default(bundle, demoStatusIcons, getSlotSatellite(), "stat_satellite_icon", 2131236743);
        updateIcon$default(bundle, demoStatusIcons, getSlotConnectedDisplay(), "connected_display", 2131236939);
        String str2 = this.slotDemoStealth;
        if (bundle != null && (string = bundle.getString("stat_stealth_mode")) != null) {
            if (EXTRA_SHOW.equals(string)) {
                demoStatusIcons.updateSlot(0, str2, false);
                demoStatusIcons.updateSlot(2131236931, str2, true);
            } else {
                demoStatusIcons.updateSlot(0, str2, false);
            }
        }
        updateIcon$default(bundle, demoStatusIcons, this.slotDemoAirplane, "stat_sys_airplane_mode", 2131236932);
    }

    public final void updateIconBluetoothLowComposite(Bundle bundle, DemoStatusIcons demoStatusIcons, String str, int i) {
        String string;
        if (bundle == null || (string = bundle.getString(str)) == null) {
            return;
        }
        if (!EXTRA_SHOW.equals(string)) {
            demoStatusIcons.updateSlot(0, getSlotBluetooth(), false);
            return;
        }
        demoStatusIcons.updateSlot(0, getSlotBluetooth(), false);
        Context context = demoStatusIcons.getContext();
        String packageName = context.getPackageName();
        UserHandle userHandle = UserHandle.SYSTEM;
        packageName.getClass();
        OplusStatusBarIcon oplusStatusBarIcon = new OplusStatusBarIcon(userHandle, packageName, Icon.createWithResource(packageName, 2131236716), 0, 0, "Demo", StatusBarIcon.Type.SystemIcon);
        ((StatusBarIcon) oplusStatusBarIcon).visible = true;
        oplusStatusBarIcon.setForeDrawableResId(i);
        StatusIconDisplayable statIconView = new StatIconView(context, getSlotBluetooth(), false);
        statIconView.setTag(getSlotBluetooth());
        statIconView.set(oplusStatusBarIcon);
        int demoIconTint = demoStatusIcons.getDemoIconTint();
        demoStatusIcons.getDemoIconContrastTint();
        statIconView.setStaticDrawableColor(demoIconTint);
        statIconView.setDecorColor(demoStatusIcons.getDemoIconTint());
        statIconView.setIsIconColorable(true);
        demoStatusIcons.addView(statIconView, 0, new LinearLayout.LayoutParams(-2, demoStatusIcons.getDemoIconHeightPx()));
    }
}
