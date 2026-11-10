package com.oplus.systemui.qs.tileex;

import android.os.OplusSystemProperties;
import com.oplus.systemui.aod.display.OplusAODRefreshRateController$RequestRefreshRateRunnable$;
import com.oplusos.systemui.common.util.LogUtil;
import kotlin.Result;
import kotlin.ResultKt;

/* JADX INFO: compiled from: go/retraceme fd74bf9514dd0739b92d67a6d48a51eb10aaf8df760d83496b8dcfcf83e5f698 */
/* JADX INFO: loaded from: /home/starlight/Projects/Starlight Ports/.build-tmp/classes4.dex */
public final class FlashlightTileEx extends QSTileExImpl {
    public FlashlightTileEx(QSTileExParams qSTileExParams) {
        super(qSTileExParams);
    }

    @Override // com.oplus.systemui.qs.tileex.QSTileExImpl
    public final boolean isClickForbidden() {
        return isTileDisable();
    }

    @Override // com.oplus.systemui.qs.tileex.QSTileExImpl
    public final boolean isTileDisable() {
        Object obj;
        try {
            obj = Result.constructor-impl(Boolean.valueOf(OplusSystemProperties.getInt("persist.sys.oplus.camera.open.torch", -1) == 0));
        } catch (Throwable th) {
            obj = Result.constructor-impl(ResultKt.createFailure(th));
        }
        Throwable th2 = Result.exceptionOrNull-impl(obj);
        if (th2 != null) {
            OplusAODRefreshRateController$RequestRefreshRateRunnable$.ExternalSyntheticOutline0.m(8, "isTileDisable: exception due to ", th2.getMessage(), LogUtil.TAG_QS, getTAG());
        }
        Boolean bool = Boolean.FALSE;
        if (obj instanceof Result.Failure) {
            obj = bool;
        }
        return ((Boolean) obj).booleanValue();
    }
}
