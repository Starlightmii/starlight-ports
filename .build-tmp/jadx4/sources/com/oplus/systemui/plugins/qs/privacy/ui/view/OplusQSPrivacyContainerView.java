package com.oplus.systemui.plugins.qs.privacy.ui.view;

import android.content.Context;
import android.graphics.Rect;
import android.util.AttributeSet;
import com.android.systemui.animation.view.LaunchableFrameLayout;
import kotlin.jvm.internal.DefaultConstructorMarker;

/* JADX INFO: compiled from: go/retraceme fd74bf9514dd0739b92d67a6d48a51eb10aaf8df760d83496b8dcfcf83e5f698 */
/* JADX INFO: loaded from: /home/starlight/Projects/Starlight Ports/.build-tmp/classes4.dex */
public final class OplusQSPrivacyContainerView extends LaunchableFrameLayout {
    public /* synthetic */ OplusQSPrivacyContainerView(Context context, AttributeSet attributeSet, int i, int i2, DefaultConstructorMarker defaultConstructorMarker) {
        this(context, (i2 & 2) != 0 ? null : attributeSet, (i2 & 4) != 0 ? 0 : i);
    }

    public /* bridge */ /* synthetic */ Rect getPaddingForLaunchAnimation() {
        return super/*com.android.systemui.animation.LaunchableView*/.getPaddingForLaunchAnimation();
    }

    public OplusQSPrivacyContainerView(Context context, AttributeSet attributeSet) {
        this(context, attributeSet, 0, 4, null);
    }

    public OplusQSPrivacyContainerView(Context context) {
        this(context, null, 0, 6, null);
    }

    public OplusQSPrivacyContainerView(Context context, AttributeSet attributeSet, int i) {
        super(context, attributeSet, i);
    }
}
