package com.oplus.systemui.separate;

import android.graphics.drawable.Drawable;
import android.support.v4.content.FileProvider$;
import com.android.systemui.statusbar.policy.UserInfoController;
import com.oplusos.systemui.common.util.LogUtil;

/* JADX INFO: compiled from: go/retraceme fd74bf9514dd0739b92d67a6d48a51eb10aaf8df760d83496b8dcfcf83e5f698 */
/* JADX INFO: loaded from: /home/starlight/Projects/Starlight Ports/.build-tmp/classes4.dex */
public final class OplusSeparateQSQuickEntranceManager$onUserInfoChangedListener$1 implements UserInfoController.OnUserInfoChangedListener {
    public /* synthetic */ OplusSeparateQSQuickEntranceManager this$0;

    public final void onUserInfoChanged(String str, Drawable drawable, String str2) {
        LogUtil.i$default(LogUtil.TAG_QS, "OplusSeparateQSQuickEntranceManager", FileProvider$.ExternalSyntheticOutline0.m("onUserInfoChanged name = ", str), null, 8);
        OplusSeparateQSQuickEntranceManager.access$onUserInfoChanged(this.this$0, drawable);
    }
}
