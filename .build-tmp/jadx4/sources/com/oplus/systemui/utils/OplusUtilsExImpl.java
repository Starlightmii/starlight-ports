package com.oplus.systemui.utils;

import android.content.Context;
import android.util.Log;
import com.android.systemui.OplusUtilsEx;
import com.oplusos.systemui.common.util.LogUtil;

/* JADX INFO: compiled from: go/retraceme fd74bf9514dd0739b92d67a6d48a51eb10aaf8df760d83496b8dcfcf83e5f698 */
/* JADX INFO: loaded from: /home/starlight/Projects/Starlight Ports/.build-tmp/classes4.dex */
public final class OplusUtilsExImpl extends OplusUtilsEx {
    private final Context context;
    public boolean isOplusLogic;

    public OplusUtilsExImpl(Context context) {
        super(context);
        this.context = context;
        this.isOplusLogic = true;
    }

    public final boolean isOplusLogic() {
        return this.isOplusLogic;
    }

    public void setOplusLogic(boolean z) {
        this.isOplusLogic = z;
    }

    public final boolean useMediaResumption() {
        if (!LogUtil.getNormalDebug() || !LogUtil.isTagValid(LogUtil.TAG_COMMON)) {
            return false;
        }
        Log.d("SystemUi--Common", "Media-->useMediaResumption  false");
        return false;
    }
}
