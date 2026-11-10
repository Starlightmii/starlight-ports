package com.oplus.systemui.qs.tileex;

import android.os.customize.OplusCustomizeConnectivityManager;
import com.android.systemui.plugins.qs.QSTile;
import com.android.systemui.qs.tileimpl.QSTileImpl;
import com.oplus.systemui.aod.display.OplusAODRefreshRateController$RequestRefreshRateRunnable$;
import com.oplusos.systemui.common.util.LogUtil;
import java.lang.invoke.VarHandle;
import kotlin.Lazy;
import kotlin.LazyKt__LazyJVMKt;
import kotlin.Result;
import kotlin.ResultKt;

/* JADX INFO: compiled from: go/retraceme fd74bf9514dd0739b92d67a6d48a51eb10aaf8df760d83496b8dcfcf83e5f698 */
/* JADX INFO: loaded from: /home/starlight/Projects/Starlight Ports/.build-tmp/classes4.dex */
public final class HotspotTileEx extends QSTileExImpl {
    public static final /* synthetic */ int $r8$clinit = 0;
    private final Lazy customizeConnectivityManager$delegate;

    public HotspotTileEx(QSTileExParams qSTileExParams) {
        super(qSTileExParams);
        HotspotTileEx$$ExternalSyntheticLambda0 hotspotTileEx$$ExternalSyntheticLambda0 = new HotspotTileEx$$ExternalSyntheticLambda0();
        hotspotTileEx$$ExternalSyntheticLambda0.f$0 = this;
        VarHandle.storeStoreFence();
        this.customizeConnectivityManager$delegate = LazyKt__LazyJVMKt.lazy(hotspotTileEx$$ExternalSyntheticLambda0);
    }

    private final OplusCustomizeConnectivityManager getCustomizeConnectivityManager() {
        return (OplusCustomizeConnectivityManager) this.customizeConnectivityManager$delegate.getValue();
    }

    @Override // com.oplus.systemui.qs.tileex.QSTileExImpl
    public final boolean isClickForbidden() {
        return isTileDisable();
    }

    @Override // com.oplus.systemui.qs.tileex.QSTileExImpl
    public final boolean isTileDisable() {
        Object obj;
        try {
            obj = Result.constructor-impl(Boolean.valueOf(getCustomizeConnectivityManager().isWifiApDisabled()));
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

    @Override // com.oplus.systemui.qs.tileex.QSTileExImpl
    public void resetIconForDisable(QSTile.State state) {
        super.resetIconForDisable(state);
        state.icon = QSTileImpl.ResourceIcon.get(2131237167);
    }
}
