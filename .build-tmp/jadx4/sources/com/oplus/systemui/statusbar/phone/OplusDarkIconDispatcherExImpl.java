package com.oplus.systemui.statusbar.phone;

import android.content.Context;
import com.android.systemui.statusbar.phone.OplusDarkIconDispatcherEx;
import com.oplusos.systemui.common.util.ScrimUtil;
import com.oplusos.systemui.util.OpUtils;

/* JADX INFO: compiled from: go/retraceme fd74bf9514dd0739b92d67a6d48a51eb10aaf8df760d83496b8dcfcf83e5f698 */
/* JADX INFO: loaded from: /home/starlight/Projects/Starlight Ports/.build-tmp/classes4.dex */
public final class OplusDarkIconDispatcherExImpl extends OplusDarkIconDispatcherEx {
    private final Context context;

    public OplusDarkIconDispatcherExImpl(Context context) {
        super(context);
        this.context = context;
    }

    public int getTintAnimationDuration() {
        return ScrimUtil.isLowGaussianLevel(this.context) ? 0 : 100;
    }

    public final int updateIconTint() {
        boolean z = OpUtils.sIsClosedSuperFirewall;
        Context context = this.context;
        return z ? context.getColor(2131101482) : context.getColor(2131101481);
    }

    public final int updateLightIconTint() {
        boolean z = OpUtils.sIsClosedSuperFirewall;
        Context context = this.context;
        return z ? context.getColor(2131101841) : context.getColor(2131101840);
    }
}
