package com.oplus.systemui.notification.aod.notification;

import android.content.Context;
import android.content.res.ColorStateList;
import android.graphics.drawable.Drawable;
import com.android.systemui.media.controls.ui.drawable.IlluminationDrawable;
import com.oplus.systemui.media.controls.ui.OplusMediaCarouselView;

/* JADX INFO: compiled from: go/retraceme fd74bf9514dd0739b92d67a6d48a51eb10aaf8df760d83496b8dcfcf83e5f698 */
/* JADX INFO: loaded from: /home/starlight/Projects/Starlight Ports/.build-tmp/classes4.dex */
public final class MediaAodBackgroundChanger {
    private static final Companion Companion = new Companion();
    private final Drawable mediaBg;

    public MediaAodBackgroundChanger(Context context) {
        IlluminationDrawable drawable = context.getDrawable(2131236350);
        if (drawable instanceof IlluminationDrawable) {
            drawable.setTintList(ColorStateList.valueOf(context.getColor(2131102906)));
        }
        if (drawable == null) {
            drawable = new OplusAodRowBackgroundDrawable(context);
            drawable.setColor(context.getColor(2131102906));
        }
        this.mediaBg = drawable;
    }

    public final void changeAodViewColor(OplusMediaCarouselView oplusMediaCarouselView, float f) {
        this.mediaBg.setAlpha((int) (255.0f * f));
        if (f == 0.0f) {
            oplusMediaCarouselView.setPanoramicAodDrawable(null);
        } else {
            oplusMediaCarouselView.setPanoramicAodDrawable(this.mediaBg);
        }
        oplusMediaCarouselView.invalidate();
    }
}
