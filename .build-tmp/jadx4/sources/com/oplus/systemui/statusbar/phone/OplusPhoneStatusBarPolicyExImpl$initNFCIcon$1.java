package com.oplus.systemui.statusbar.phone;

import android.os.Handler;
import android.util.Log;
import com.oplusos.systemui.common.util.LogUtil;
import java.lang.invoke.VarHandle;
import java.util.concurrent.atomic.AtomicInteger;

/* JADX INFO: compiled from: go/retraceme fd74bf9514dd0739b92d67a6d48a51eb10aaf8df760d83496b8dcfcf83e5f698 */
/* JADX INFO: loaded from: /home/starlight/Projects/Starlight Ports/.build-tmp/classes4.dex */
public final class OplusPhoneStatusBarPolicyExImpl$initNFCIcon$1 implements Runnable {
    public final /* synthetic */ int $r8$classId;
    public /* synthetic */ OplusPhoneStatusBarPolicyExImpl this$0;

    public /* synthetic */ OplusPhoneStatusBarPolicyExImpl$initNFCIcon$1(int i) {
        this.$r8$classId = i;
    }

    @Override // java.lang.Runnable
    public final void run() {
        switch (this.$r8$classId) {
            case 0:
                boolean nfcEnable = this.this$0.getNfcEnable();
                int i = 1;
                AtomicInteger atomicInteger = this.this$0.mNfcstate;
                if (nfcEnable) {
                    atomicInteger.set(3);
                } else {
                    atomicInteger.set(1);
                }
                Handler handler = this.this$0.handler;
                OplusPhoneStatusBarPolicyExImpl oplusPhoneStatusBarPolicyExImpl = this.this$0;
                OplusPhoneStatusBarPolicyExImpl.RunnableC02961 runnableC02961 = new OplusPhoneStatusBarPolicyExImpl.RunnableC02961(i);
                runnableC02961.this$0 = oplusPhoneStatusBarPolicyExImpl;
                runnableC02961.$emergencyOnly = nfcEnable;
                VarHandle.storeStoreFence();
                handler.post(runnableC02961);
                break;
            case 1:
                if (LogUtil.getNormalDebug() && LogUtil.isTagValid(LogUtil.TAG_STATUSBAR)) {
                    Log.d("SystemUi--Statusbar", "OplusPhoneStatusBarPolicyEx-->bluetoothProfile, onServiceDisconnected: ");
                }
                this.this$0.bluetoothHeadset = null;
                break;
            default:
                OplusPhoneStatusBarPolicyExImpl.access$initNFCIcon(this.this$0);
                break;
        }
    }
}
