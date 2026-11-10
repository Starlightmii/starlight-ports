package com.oplusos.systemui.common.blurability;

import com.android.systemui.log.LogBuffer;

/* JADX INFO: compiled from: go/retraceme fd74bf9514dd0739b92d67a6d48a51eb10aaf8df760d83496b8dcfcf83e5f698 */
/* JADX INFO: loaded from: /home/starlight/Projects/Starlight Ports/.build-tmp/classes4.dex */
public final class DebugTool {
    public static final DebugTool INSTANCE = new DebugTool();
    private static LogBuffer logBuffer;
    public static boolean openDebug;

    public final LogBuffer getLogBuffer() {
        return logBuffer;
    }

    public final boolean getOpenDebug() {
        return openDebug;
    }

    public final void setLogBuffer(LogBuffer logBuffer2) {
        logBuffer = logBuffer2;
    }

    public final void setOpenDebug(boolean z) {
        openDebug = z;
    }
}
