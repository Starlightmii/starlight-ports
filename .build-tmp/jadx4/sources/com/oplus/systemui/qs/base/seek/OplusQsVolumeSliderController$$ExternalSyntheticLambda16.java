package com.oplus.systemui.qs.base.seek;

import kotlin.Unit;
import kotlin.jvm.functions.Function3;

/* JADX INFO: compiled from: go/retraceme fd74bf9514dd0739b92d67a6d48a51eb10aaf8df760d83496b8dcfcf83e5f698 */
/* JADX INFO: loaded from: /home/starlight/Projects/Starlight Ports/.build-tmp/classes4.dex */
public final /* synthetic */ class OplusQsVolumeSliderController$$ExternalSyntheticLambda16 implements Function3 {
    public /* synthetic */ OplusQsVolumeSliderController f$0;

    public final Object invoke(Object obj, Object obj2, Object obj3) {
        OplusQsVolumeSliderController oplusQsVolumeSliderController = this.f$0;
        float fFloatValue = ((Float) obj).floatValue();
        float fFloatValue2 = ((Float) obj2).floatValue();
        float fFloatValue3 = ((Float) obj3).floatValue();
        if (oplusQsVolumeSliderController.isNeedtouchFeedback()) {
            OplusQsBaseToggleSliderLayout oplusQsBaseToggleSliderLayout = (OplusQsBaseToggleSliderLayout) oplusQsVolumeSliderController.mView;
            oplusQsBaseToggleSliderLayout.setTranslationY(fFloatValue);
            oplusQsBaseToggleSliderLayout.setScaleX(fFloatValue2);
            oplusQsBaseToggleSliderLayout.setScaleY(fFloatValue3);
        }
        return Unit.INSTANCE;
    }
}
