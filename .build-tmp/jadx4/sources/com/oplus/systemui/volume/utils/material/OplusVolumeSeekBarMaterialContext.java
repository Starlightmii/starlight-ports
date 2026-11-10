package com.oplus.systemui.volume.utils.material;

import android.content.Context;
import android.graphics.Rect;
import android.graphics.drawable.Drawable;
import android.view.View;

/* JADX INFO: compiled from: go/retraceme fd74bf9514dd0739b92d67a6d48a51eb10aaf8df760d83496b8dcfcf83e5f698 */
/* JADX INFO: loaded from: /home/starlight/Projects/Starlight Ports/.build-tmp/classes4.dex */
public interface OplusVolumeSeekBarMaterialContext {
    void copyBackgroundRect(Rect rect);

    void copyClipProgressRect(Rect rect);

    void copyProgressRect(Rect rect);

    Context getAppContext();

    float getBackgroundRoundCornerWeight();

    float getCurBackgroundRadius();

    View getMaterialHostView();

    Drawable getSeekBarBackground();
}
