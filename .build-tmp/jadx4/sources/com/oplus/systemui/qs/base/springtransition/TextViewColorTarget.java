package com.oplus.systemui.qs.base.springtransition;

import android.widget.TextView;
import java.lang.ref.WeakReference;

/* JADX INFO: compiled from: go/retraceme fd74bf9514dd0739b92d67a6d48a51eb10aaf8df760d83496b8dcfcf83e5f698 */
/* JADX INFO: loaded from: /home/starlight/Projects/Starlight Ports/.build-tmp/classes4.dex */
public final class TextViewColorTarget implements IIntTarget {
    private final WeakReference<TextView> textViewWeakRef;

    public TextViewColorTarget(TextView textView) {
        this.textViewWeakRef = new WeakReference<>(textView);
    }

    @Override // com.oplus.systemui.qs.base.springtransition.IIntTarget
    public int get() {
        TextView textView = this.textViewWeakRef.get();
        if (textView != null) {
            return textView.getCurrentTextColor();
        }
        return 0;
    }

    @Override // com.oplus.systemui.qs.base.springtransition.IIntTarget
    public void set(int i) {
        TextView textView = this.textViewWeakRef.get();
        if (textView != null) {
            textView.setTextColor(i);
        }
    }
}
