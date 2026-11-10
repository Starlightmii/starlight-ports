package com.oplusos.systemui.common.typeface;

import android.graphics.Typeface;
import androidx.compose.ui.text.font.AndroidTypeface_androidKt;
import androidx.compose.ui.text.font.FontWeight;
import kotlin.jvm.functions.Function0;

/* JADX INFO: compiled from: go/retraceme fd74bf9514dd0739b92d67a6d48a51eb10aaf8df760d83496b8dcfcf83e5f698 */
/* JADX INFO: loaded from: /home/starlight/Projects/Starlight Ports/.build-tmp/classes4.dex */
public final /* synthetic */ class SystemTypefaceHelper$$ExternalSyntheticLambda0 implements Function0 {
    public final /* synthetic */ int $r8$classId;
    public /* synthetic */ Object f$0;
    public /* synthetic */ SystemTypefaceHelper.FontParams f$1;

    public final Object invoke() {
        switch (this.$r8$classId) {
            case 0:
                return ((SystemTypefaceHelper) this.f$0).createVariationSettingsTypeface(Typeface.SANS_SERIF, this.f$1);
            default:
                Typeface typeface = (Typeface) this.f$0;
                SystemTypefaceHelper.FontParams fontParams = this.f$1;
                return new SystemTypefaceHelper.ComposeFontInfo(AndroidTypeface_androidKt.FontFamily(typeface), new FontWeight(fontParams.getWeight()), fontParams.getWidth());
        }
    }
}
