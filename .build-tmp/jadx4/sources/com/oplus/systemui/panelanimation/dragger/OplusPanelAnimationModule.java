package com.oplus.systemui.panelanimation.dragger;

import com.android.systemui.log.LogBuffer;
import com.android.systemui.log.LogBufferFactory;

/* JADX INFO: compiled from: go/retraceme fd74bf9514dd0739b92d67a6d48a51eb10aaf8df760d83496b8dcfcf83e5f698 */
/* JADX INFO: loaded from: /home/starlight/Projects/Starlight Ports/.build-tmp/classes4.dex */
public final class OplusPanelAnimationModule {
    public static final OplusPanelAnimationModule INSTANCE = new OplusPanelAnimationModule();

    public final LogBuffer providePanelAnimationLogBuffer(LogBufferFactory logBufferFactory) {
        return LogBufferFactory.create$default(logBufferFactory, "PanelAnimationLog", 1000, false, (String) null, 28);
    }
}
