package com.oplus.systemui.qs.base.widget;

import android.graphics.drawable.Drawable;
import com.oplusos.systemui.common.blurability.ViewBlurProxy;
import kotlin.jvm.functions.Function0;

/* JADX INFO: compiled from: go/retraceme fd74bf9514dd0739b92d67a6d48a51eb10aaf8df760d83496b8dcfcf83e5f698 */
/* JADX INFO: loaded from: /home/starlight/Projects/Starlight Ports/.build-tmp/classes4.dex */
public final /* synthetic */ class LightStyleStrokeDrawable$$ExternalSyntheticLambda0 implements Function0 {
    public /* synthetic */ ViewBlurProxy f$0;
    public /* synthetic */ Drawable f$1;

    public final Object invoke() {
        ViewBlurProxy viewBlurProxy = this.f$0;
        Drawable drawable = this.f$1;
        int i = LightStyleStrokeDrawable.$r8$clinit;
        return viewBlurProxy.getBlurDrawable(drawable);
    }
}
