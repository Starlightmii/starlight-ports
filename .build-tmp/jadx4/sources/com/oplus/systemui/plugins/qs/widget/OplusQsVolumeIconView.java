package com.oplus.systemui.plugins.qs.widget;

import android.content.Context;
import android.util.AttributeSet;
import com.oplus.systemui.qs.base.util.QsColorfulConfigUtil;
import kotlin.jvm.internal.DefaultConstructorMarker;

/* JADX INFO: compiled from: go/retraceme fd74bf9514dd0739b92d67a6d48a51eb10aaf8df760d83496b8dcfcf83e5f698 */
/* JADX INFO: loaded from: /home/starlight/Projects/Starlight Ports/.build-tmp/classes4.dex */
public final class OplusQsVolumeIconView extends com.oplus.systemui.qs.base.seek.OplusQsVolumeIconView {
    public static final Companion Companion = new Companion();
    public static final String TAG = "OplusQsSepVolumeIconView";

    /* JADX INFO: compiled from: go/retraceme fd74bf9514dd0739b92d67a6d48a51eb10aaf8df760d83496b8dcfcf83e5f698 */
    public final class Companion {
    }

    public OplusQsVolumeIconView(Context context, AttributeSet attributeSet) {
        this(context, attributeSet, 0, 4, null);
    }

    @Override // com.oplus.systemui.qs.base.seek.OplusQsVolumeIconView
    public int getUnMuteIconColor() {
        return QsColorfulConfigUtil.INSTANCE.getEnableColorfulConfig() ? QsColorfulConfigUtil.QS_COLORFUL_ICON_ACTIVE_BLUE_COLOR : super.getUnMuteIconColor();
    }

    public /* synthetic */ OplusQsVolumeIconView(Context context, AttributeSet attributeSet, int i, int i2, DefaultConstructorMarker defaultConstructorMarker) {
        this(context, attributeSet, (i2 & 4) != 0 ? 0 : i);
    }

    public OplusQsVolumeIconView(Context context, AttributeSet attributeSet, int i) {
        super(context, attributeSet, i);
    }
}
