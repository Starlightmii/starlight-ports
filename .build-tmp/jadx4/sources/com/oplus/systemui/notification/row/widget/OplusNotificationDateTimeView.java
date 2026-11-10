package com.oplus.systemui.notification.row.widget;

import android.content.Context;
import android.util.AttributeSet;
import kotlin.jvm.functions.Function0;

/* JADX INFO: compiled from: go/retraceme fd74bf9514dd0739b92d67a6d48a51eb10aaf8df760d83496b8dcfcf83e5f698 */
/* JADX INFO: loaded from: /home/starlight/Projects/Starlight Ports/.build-tmp/classes4.dex */
public final class OplusNotificationDateTimeView extends android.widget.OplusNotificationDateTimeView {
    private Function0 onUpdateListener;

    public /* synthetic */ OplusNotificationDateTimeView(Context context, AttributeSet attributeSet, int i, int i2) {
        this(context, (i & 2) != 0 ? null : attributeSet, 0);
    }

    public final void setOnUpdateListener(Function0 function0) {
        this.onUpdateListener = function0;
    }

    public final void update() {
        super.update();
        Function0 function0 = this.onUpdateListener;
        if (function0 != null) {
            function0.invoke();
        }
    }

    public OplusNotificationDateTimeView(Context context, AttributeSet attributeSet) {
        this(context, attributeSet, 4, 0);
    }

    public OplusNotificationDateTimeView(Context context) {
        this(context, null, 6, 0);
    }

    public OplusNotificationDateTimeView(Context context, AttributeSet attributeSet, int i) {
        super(context, attributeSet, i);
    }
}
