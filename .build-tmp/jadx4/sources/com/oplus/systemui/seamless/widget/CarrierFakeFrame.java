package com.oplus.systemui.seamless.widget;

import android.content.Context;
import android.view.View;
import android.widget.TextView;

/* JADX INFO: compiled from: go/retraceme fd74bf9514dd0739b92d67a6d48a51eb10aaf8df760d83496b8dcfcf83e5f698 */
/* JADX INFO: loaded from: /home/starlight/Projects/Starlight Ports/.build-tmp/classes4.dex */
public final class CarrierFakeFrame extends StartSideFakeFrame {
    public CarrierFakeFrame(Context context) {
        super(context);
    }

    public final boolean shouldAnimation() {
        CharSequence text;
        View mView = getMView();
        TextView textView = mView instanceof TextView ? (TextView) mView : null;
        return (textView == null || (text = textView.getText()) == null || text.length() == 0) ? false : true;
    }
}
