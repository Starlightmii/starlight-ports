package com.oplus.systemui.statusbar.controller;

import android.telephony.TelephonyCallback;
import android.telephony.TelephonyManager;
import androidx.appcompat.widget.ListPopupWindow$;
import com.oplusos.systemui.common.util.LogUtil;

/* JADX INFO: compiled from: go/retraceme fd74bf9514dd0739b92d67a6d48a51eb10aaf8df760d83496b8dcfcf83e5f698 */
/* JADX INFO: loaded from: /home/starlight/Projects/Starlight Ports/.build-tmp/classes4.dex */
public final class SystemPromptController$callStateListener$1 implements TelephonyCallback.CallStateListener {
    public /* synthetic */ SystemPromptController this$0;

    @Override // android.telephony.TelephonyCallback.CallStateListener
    public final void onCallStateChanged(int i) {
        LogUtil.i$default(LogUtil.TAG_STATUSBAR, "SystemPromptController", ListPopupWindow$.ExternalSyntheticOutline0.m(i, "onCallStateChanged callState="), null, 8);
        String str = i == 0 ? TelephonyManager.EXTRA_STATE_IDLE : "";
        SystemPromptController systemPromptController = this.this$0;
        str.getClass();
        SystemPromptController.access$updatePhoneStateIdle(systemPromptController, str);
    }
}
