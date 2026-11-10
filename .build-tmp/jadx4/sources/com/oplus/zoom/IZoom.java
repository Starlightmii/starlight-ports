package com.oplus.zoom;

import android.content.res.Configuration;
import android.os.Bundle;
import com.android.wm.shell.shared.annotations.ExternalThread;

/* JADX INFO: compiled from: go/retraceme fd74bf9514dd0739b92d67a6d48a51eb10aaf8df760d83496b8dcfcf83e5f698 */
/* JADX INFO: loaded from: /home/starlight/Projects/Starlight Ports/.build-tmp/classes4.dex */
@ExternalThread
public interface IZoom {
    void hideZoomWindow(int i);

    void onConfigChanged(Configuration configuration);

    void requestChangeZoomState(int i, int i2);

    void startZoomWindow(int i, Bundle bundle);
}
