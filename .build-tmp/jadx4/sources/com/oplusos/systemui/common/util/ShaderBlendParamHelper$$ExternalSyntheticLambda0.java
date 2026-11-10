package com.oplusos.systemui.common.util;

import android.graphics.Color;
import androidx.core.graphics.ColorUtils;
import kotlin.jvm.functions.Function2;

/* JADX INFO: compiled from: go/retraceme fd74bf9514dd0739b92d67a6d48a51eb10aaf8df760d83496b8dcfcf83e5f698 */
/* JADX INFO: loaded from: /home/starlight/Projects/Starlight Ports/.build-tmp/classes4.dex */
public final /* synthetic */ class ShaderBlendParamHelper$$ExternalSyntheticLambda0 implements Function2 {
    public final Object invoke(Object obj, Object obj2) {
        int iIntValue = ((Integer) obj).intValue();
        return Integer.valueOf(ColorUtils.setAlphaComponent(iIntValue, (int) (Color.alpha(iIntValue) * ((Float) obj2).floatValue())));
    }
}
