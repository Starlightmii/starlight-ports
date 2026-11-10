package com.oplus.systemui.qs.media;

import android.widget.ImageButton;
import com.coui.appcompat.animation.dynamicanimation.COUIDynamicAnimation;
import java.util.ArrayList;

/* JADX INFO: compiled from: go/retraceme fd74bf9514dd0739b92d67a6d48a51eb10aaf8df760d83496b8dcfcf83e5f698 */
/* JADX INFO: loaded from: /home/starlight/Projects/Starlight Ports/.build-tmp/classes4.dex */
public final class ScaleSpringAnimationHelper$springAnim$2$1$1 implements COUIDynamicAnimation.OnAnimationUpdateListener {
    public /* synthetic */ ScaleSpringAnimationHelper this$0;

    public final void onAnimationUpdate(COUIDynamicAnimation cOUIDynamicAnimation, float f, float f2) {
        ArrayList arrayList = this.this$0.iconView;
        int size = arrayList.size();
        int i = 0;
        while (i < size) {
            Object obj = arrayList.get(i);
            i++;
            ImageButton imageButton = (ImageButton) obj;
            imageButton.setScaleX(f);
            imageButton.setScaleY(f);
        }
    }
}
