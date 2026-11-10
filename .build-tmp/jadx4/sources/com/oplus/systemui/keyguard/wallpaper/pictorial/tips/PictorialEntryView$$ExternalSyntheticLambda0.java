package com.oplus.systemui.keyguard.wallpaper.pictorial.tips;

import android.view.View;
import com.oplus.systemui.keyguard.wallpaper.pictorial.slider.KeyguardSliderLayout;
import com.oplus.systemui.keyguard.wallpaper.pictorial.slider.KeyguardSliderLayout$$ExternalSyntheticLambda3;
import com.oplusos.keyguard.utils.KgdLogUtil;
import java.lang.reflect.Method;

/* JADX INFO: compiled from: go/retraceme fd74bf9514dd0739b92d67a6d48a51eb10aaf8df760d83496b8dcfcf83e5f698 */
/* JADX INFO: loaded from: /home/starlight/Projects/Starlight Ports/.build-tmp/classes4.dex */
public final /* synthetic */ class PictorialEntryView$$ExternalSyntheticLambda0 implements View.OnClickListener {
    public /* synthetic */ PictorialEntryView f$0;

    /* JADX WARN: Type inference failed for: r2v1, types: [android.widget.ImageView, com.oplus.systemui.keyguard.wallpaper.pictorial.tips.PictorialEntryView, com.oplus.systemui.keyguard.wallpaper.pictorial.tips.PictorialTipsView] */
    @Override // android.view.View.OnClickListener
    public final void onClick(View view) {
        ?? r2 = this.f$0;
        int i = PictorialEntryView.$r8$clinit;
        KgdLogUtil.i("TipsView-PictorialEntryView", "clickEnterPreloadView");
        if (r2.getTranslationX() > 0.0f) {
            KgdLogUtil.i("TipsView-PictorialEntryView", "clickEnterPreloadView is Translation, return");
            return;
        }
        OnTipsListener onTipsListener = r2.mOnTipsListener;
        if (onTipsListener != null) {
            KeyguardSliderLayout keyguardSliderLayout = ((KeyguardSliderLayout$$ExternalSyntheticLambda3) onTipsListener).f$0;
            Method method = KeyguardSliderLayout.sSetMeasuredDimensionRawMethod;
            keyguardSliderLayout.entryIcon(false);
        }
        r2.invalidate();
    }
}
