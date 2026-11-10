package com.oplus.systemui.statusbar.phone;

import android.content.Context;
import android.os.Bundle;
import android.util.Log;
import com.android.systemui.statusbar.phone.OplusStatusBarInfoControllerEx;
import com.oplus.systemui.statusbar.phone.dynamic.pipeline.data.dynamicevent.DynamicEventRepository;
import com.oplusos.systemui.common.util.LogUtil;
import com.oplusos.systemui.statusbar.StatusBarWindowStateControllerForSubDisplay;

/* JADX INFO: compiled from: go/retraceme fd74bf9514dd0739b92d67a6d48a51eb10aaf8df760d83496b8dcfcf83e5f698 */
/* JADX INFO: loaded from: /home/starlight/Projects/Starlight Ports/.build-tmp/classes4.dex */
public final class OplusStatusBarInfoControllerExImpl extends OplusStatusBarInfoControllerEx {
    public static final Companion Companion = new Companion();
    private static final String TAG = "OplusStatusBarInfoControllerExImpl";
    private final DynamicEventRepository dynamicEventRepository;

    public OplusStatusBarInfoControllerExImpl(DynamicEventRepository dynamicEventRepository) {
        this.dynamicEventRepository = dynamicEventRepository;
    }

    public void getInstanceWindowState(Context context) {
        StatusBarWindowStateControllerForSubDisplay.Companion.getInstance(context);
    }

    public void sendDynamicEvent(String str, int i, Bundle bundle) {
        if (LogUtil.getNormalDebug() && LogUtil.isTagValid(LogUtil.TAG_STATUSBAR)) {
            Log.d("SystemUi--Statusbar", "OplusStatusBarInfoControllerExImpl-->".concat("sendDynamicEvent extras: " + bundle));
        }
        this.dynamicEventRepository.updateDynamicEvent(str, i, bundle);
    }
}
