package com.oplusos.systemui.common.blurability.screenshot;

import android.graphics.Bitmap;
import kotlin.coroutines.Continuation;

/* JADX INFO: compiled from: go/retraceme fd74bf9514dd0739b92d67a6d48a51eb10aaf8df760d83496b8dcfcf83e5f698 */
/* JADX INFO: loaded from: /home/starlight/Projects/Starlight Ports/.build-tmp/classes4.dex */
public interface ScreenshotsCallback {
    Object onFinished(Bitmap bitmap, Continuation continuation);
}
