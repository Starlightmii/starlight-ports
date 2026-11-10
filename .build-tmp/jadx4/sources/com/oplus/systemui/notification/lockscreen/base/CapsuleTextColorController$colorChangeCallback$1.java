package com.oplus.systemui.notification.lockscreen.base;

import android.graphics.Bitmap;
import android.util.Log;
import com.android.systemui.keyguard.helper.ColorChangeCallback;
import com.oplusos.systemui.common.util.LogUtil;
import java.util.Locale;

/* JADX INFO: compiled from: go/retraceme fd74bf9514dd0739b92d67a6d48a51eb10aaf8df760d83496b8dcfcf83e5f698 */
/* JADX INFO: loaded from: /home/starlight/Projects/Starlight Ports/.build-tmp/classes4.dex */
public final class CapsuleTextColorController$colorChangeCallback$1 implements ColorChangeCallback {
    public /* synthetic */ CapsuleTextColorController this$0;

    public /* bridge */ /* synthetic */ void onColorBitmapChanged(boolean z, Bitmap bitmap) {
        super.onColorBitmapChanged(z, bitmap);
    }

    public final void onColorChanged(int i, boolean z) {
        if (LogUtil.getNormalDebug() && LogUtil.isTagValid(LogUtil.TAG_NOTIFICATION)) {
            Log.d("SystemUi--Notification", "CapsuleTextColorController-->".concat("onColorChanged isLight: " + z + " color: #" + Integer.toHexString(i).toUpperCase(Locale.ROOT)));
        }
        CapsuleTextColorController capsuleTextColorController = this.this$0;
        capsuleTextColorController.keyguardLightColor = z;
        capsuleTextColorController.applyNewTextColorModel("keyguardColorChange");
    }
}
