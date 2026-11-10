package com.oplusos.systemui.common.util;

import android.widget.TextView;

/* JADX INFO: compiled from: go/retraceme fd74bf9514dd0739b92d67a6d48a51eb10aaf8df760d83496b8dcfcf83e5f698 */
/* JADX INFO: loaded from: /home/starlight/Projects/Starlight Ports/.build-tmp/classes4.dex */
public final class ChangeTextSizeUtil {
    public static final ChangeTextSizeUtil INSTANCE = new ChangeTextSizeUtil();

    public static final void adaptG3FontSize(TextView textView) {
        if (textView != null) {
            float f = textView.getResources().getConfiguration().fontScale;
            textView.setTextSize(0, getG3FontSize(Math.round(textView.getTextSize() / f), f));
        }
    }

    public static final float getG3FontSize(float f, float f2) {
        return f2 <= 1.0f ? f * f2 : f * 1.15f;
    }
}
