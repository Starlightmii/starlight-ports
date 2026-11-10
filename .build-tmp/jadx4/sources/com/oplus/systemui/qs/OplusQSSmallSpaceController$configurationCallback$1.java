package com.oplus.systemui.qs;

import android.content.res.Configuration;
import com.android.compose.nestedscroll.PriorityNestedScrollConnection$;
import com.android.systemui.statusbar.policy.ConfigurationController;
import com.oplusos.systemui.common.util.LogUtil;

/* JADX INFO: compiled from: go/retraceme fd74bf9514dd0739b92d67a6d48a51eb10aaf8df760d83496b8dcfcf83e5f698 */
/* JADX INFO: loaded from: /home/starlight/Projects/Starlight Ports/.build-tmp/classes4.dex */
public final class OplusQSSmallSpaceController$configurationCallback$1 implements ConfigurationController.ConfigurationListener {
    public /* synthetic */ OplusQSSmallSpaceController this$0;

    public final void onConfigChanged(Configuration configuration) {
        OplusQSSmallSpaceController.Companion companion = OplusQSSmallSpaceController.Companion;
        OplusQSSmallSpaceController oplusQSSmallSpaceController = this.this$0;
        boolean zIsSmallSpace = companion.isSmallSpace(oplusQSSmallSpaceController.context, false);
        LogUtil.i$default(LogUtil.TAG_QS, "OplusQSSmallSpaceController", PriorityNestedScrollConnection$.ExternalSyntheticOutline0.m("onConfigChanged: isSmallSpace= ", " -> ", OplusQSSmallSpaceController.isSmallSpace, zIsSmallSpace), null, 8);
        oplusQSSmallSpaceController.qsLogger.i("OplusQSSmallSpaceController", PriorityNestedScrollConnection$.ExternalSyntheticOutline0.m("onConfigChanged: isSmallSpace= ", " -> ", OplusQSSmallSpaceController.isSmallSpace, zIsSmallSpace));
        OplusQSSmallSpaceController.isSmallSpace = zIsSmallSpace;
    }
}
