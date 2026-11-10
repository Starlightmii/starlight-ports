package com.oplus.systemui.statusbar.phone;

import androidx.compose.material3.internal.TextFieldImplKt$;
import com.android.keyguard.logging.KeyguardLogger$;
import com.oplus.charge.viewmodel.OplusChargeAnimController$;
import com.oplus.systemui.blur.GaussBlurUtils;
import com.oplusos.systemui.common.feature.KeyguardFeatureOption;
import kotlin.jvm.functions.Function0;

/* JADX INFO: compiled from: go/retraceme fd74bf9514dd0739b92d67a6d48a51eb10aaf8df760d83496b8dcfcf83e5f698 */
/* JADX INFO: loaded from: /home/starlight/Projects/Starlight Ports/.build-tmp/classes4.dex */
public final /* synthetic */ class ScrimControllerExImp$$ExternalSyntheticLambda0 implements Function0 {
    public final /* synthetic */ int $r8$classId;

    public /* synthetic */ ScrimControllerExImp$$ExternalSyntheticLambda0(int i) {
        this.$r8$classId = i;
    }

    public final Object invoke() {
        switch (this.$r8$classId) {
            case 0:
                ScrimControllerExImp.Companion companion = ScrimControllerExImp.Companion;
                boolean zIsGaussBlurDisabled = GaussBlurUtils.isGaussBlurDisabled();
                KeyguardFeatureOption.INSTANCE.getClass();
                boolean z = KeyguardFeatureOption.isDisableBouncerBlurScrim;
                boolean z2 = KeyguardFeatureOption.isEnableBouncerWallpaperBlur;
                boolean zIsNonLocalHbmSupportOpticalFp = KeyguardFeatureOption.isNonLocalHbmSupportOpticalFp();
                boolean zIs8BitScreen = KeyguardFeatureOption.is8BitScreen();
                StringBuilder sbM = TextFieldImplKt$.ExternalSyntheticOutline0.m("GaussBlurUtils.isGaussBlurDisabled()=", " KeyguardFeatureOption.isDisableBouncerBlurScrim: ", "  KeyguardFeatureOption.isEnableBouncerWallpaperBlur=", zIsGaussBlurDisabled, z);
                KeyguardLogger$.ExternalSyntheticOutline0.m(" KeyguardFeatureOption.isNonLocalHbmSupportOpticalFp()=", " KeyguardFeatureOption.is8BitScreen=", sbM, z2, zIsNonLocalHbmSupportOpticalFp);
                OplusChargeAnimController$.ExternalSyntheticOutline0.m("ScrimControllerExImp", sbM, zIs8BitScreen);
                return Boolean.valueOf((KeyguardFeatureOption.isDisableBouncerBlurScrim || !KeyguardFeatureOption.isEnableBouncerWallpaperBlur || KeyguardFeatureOption.isNonLocalHbmSupportOpticalFp() || KeyguardFeatureOption.is8BitScreen()) ? false : true);
            default:
                ScrimControllerExImp.Companion companion2 = ScrimControllerExImp.Companion;
                return "blur State to STATIC_BLUR_KEYGUARD ";
        }
    }
}
