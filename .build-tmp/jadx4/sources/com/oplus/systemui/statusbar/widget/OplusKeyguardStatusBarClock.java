package com.oplus.systemui.statusbar.widget;

import android.content.Context;
import android.util.AttributeSet;
import android.widget.TextView;
import com.android.systemui.FontSizeUtils;
import com.android.systemui.statusbar.policy.Clock;

/* JADX INFO: compiled from: go/retraceme fd74bf9514dd0739b92d67a6d48a51eb10aaf8df760d83496b8dcfcf83e5f698 */
/* JADX INFO: loaded from: /home/starlight/Projects/Starlight Ports/.build-tmp/classes4.dex */
public final class OplusKeyguardStatusBarClock extends Clock {
    public OplusKeyguardStatusBarClock(Context context) {
        this(context, null);
    }

    /* JADX WARN: Multi-variable type inference failed */
    public final void reloadDimens() {
        FontSizeUtils.updateFontSize(this, 2131172971);
        setPaddingRelative(((TextView) this).mContext.getResources().getDimensionPixelSize(2131170982), 0, ((TextView) this).mContext.getResources().getDimensionPixelSize(2131170982), ((TextView) this).mContext.getResources().getDimensionPixelSize(2131170878));
        refreshWidth();
    }

    public OplusKeyguardStatusBarClock(Context context, AttributeSet attributeSet) {
        super(context, attributeSet);
    }
}
