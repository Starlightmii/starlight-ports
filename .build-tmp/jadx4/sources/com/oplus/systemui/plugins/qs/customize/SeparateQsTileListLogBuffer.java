package com.oplus.systemui.plugins.qs.customize;

import android.util.Log;
import com.android.systemui.log.LogBuffer;
import com.android.systemui.log.core.LogLevel;
import com.oplusos.systemui.common.util.LogUtil;

/* JADX INFO: compiled from: go/retraceme fd74bf9514dd0739b92d67a6d48a51eb10aaf8df760d83496b8dcfcf83e5f698 */
/* JADX INFO: loaded from: /home/starlight/Projects/Starlight Ports/.build-tmp/classes4.dex */
public final class SeparateQsTileListLogBuffer {
    public static final Companion Companion = new Companion();
    public static final String SEP_TILES_LOG = "SeparateTileListLogger";
    private final LogBuffer logger;

    /* JADX INFO: compiled from: go/retraceme fd74bf9514dd0739b92d67a6d48a51eb10aaf8df760d83496b8dcfcf83e5f698 */
    public final class Companion {
    }

    public SeparateQsTileListLogBuffer(LogBuffer logBuffer) {
        this.logger = logBuffer;
    }

    public final LogBuffer getLogger() {
        return this.logger;
    }

    public final void logTile(String str, String str2) {
        if (LogUtil.getNormalDebug() && LogUtil.isTagValid(LogUtil.TAG_QS)) {
            Log.d("SystemUi--QuickSettings", str + LogUtil.SEPARATOR + str2);
        }
        this.logger.log(SEP_TILES_LOG, LogLevel.INFO, str2, (Throwable) null);
    }

    public final void logTile(String str) {
        this.logger.log(SEP_TILES_LOG, LogLevel.INFO, str, (Throwable) null);
    }
}
