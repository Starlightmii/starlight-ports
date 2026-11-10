package com.oplus.systemui.statusbar.phone;

import android.bluetooth.BluetoothProfile;
import android.os.Handler;
import java.lang.invoke.VarHandle;

/* JADX INFO: compiled from: go/retraceme fd74bf9514dd0739b92d67a6d48a51eb10aaf8df760d83496b8dcfcf83e5f698 */
/* JADX INFO: loaded from: /home/starlight/Projects/Starlight Ports/.build-tmp/classes4.dex */
public final class OplusPhoneStatusBarPolicyExImpl$bluetoothProfileServiceListener$1 implements BluetoothProfile.ServiceListener {
    public /* synthetic */ OplusPhoneStatusBarPolicyExImpl this$0;

    @Override // android.bluetooth.BluetoothProfile.ServiceListener
    public final void onServiceConnected(int i, BluetoothProfile bluetoothProfile) {
        Handler handler = this.this$0.bgHandler;
        OplusPhoneStatusBarPolicyExImpl oplusPhoneStatusBarPolicyExImpl = this.this$0;
        OplusPhoneStatusBarPolicyExImpl.RunnableC02951 runnableC02951 = new OplusPhoneStatusBarPolicyExImpl.RunnableC02951(1);
        runnableC02951.this$0 = oplusPhoneStatusBarPolicyExImpl;
        runnableC02951.$intent = bluetoothProfile;
        VarHandle.storeStoreFence();
        handler.post(runnableC02951);
    }

    @Override // android.bluetooth.BluetoothProfile.ServiceListener
    public final void onServiceDisconnected(int i) {
        Handler handler = this.this$0.bgHandler;
        OplusPhoneStatusBarPolicyExImpl oplusPhoneStatusBarPolicyExImpl = this.this$0;
        OplusPhoneStatusBarPolicyExImpl$initNFCIcon$1 oplusPhoneStatusBarPolicyExImpl$initNFCIcon$1 = new OplusPhoneStatusBarPolicyExImpl$initNFCIcon$1(1);
        oplusPhoneStatusBarPolicyExImpl$initNFCIcon$1.this$0 = oplusPhoneStatusBarPolicyExImpl;
        VarHandle.storeStoreFence();
        handler.post(oplusPhoneStatusBarPolicyExImpl$initNFCIcon$1);
    }
}
