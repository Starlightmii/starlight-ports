package com.oplus.systemui.qs.tileex;

import android.os.customize.OplusCustomizeConnectivityManager;
import com.android.systemui.plugins.qs.QSTile;
import com.android.systemui.qs.tileimpl.QSTileImpl;
import com.android.systemui.util.JankUtils;
import com.oplus.systemui.aod.display.OplusAODRefreshRateController$RequestRefreshRateRunnable$;
import com.oplusos.systemui.common.util.LogUtil;
import java.lang.invoke.VarHandle;
import kotlin.Lazy;
import kotlin.LazyKt__LazyJVMKt;
import kotlin.Result;
import kotlin.ResultKt;

/* JADX INFO: compiled from: go/retraceme fd74bf9514dd0739b92d67a6d48a51eb10aaf8df760d83496b8dcfcf83e5f698 */
/* JADX INFO: loaded from: /home/starlight/Projects/Starlight Ports/.build-tmp/classes4.dex */
public final class WifiTileEx extends QSTileExImpl {
    public static final /* synthetic */ int $r8$clinit = 0;
    private final Lazy customizeConnectivityManager$delegate;

    public WifiTileEx(QSTileExParams qSTileExParams) {
        super(qSTileExParams);
        WifiTileEx$$ExternalSyntheticLambda0 wifiTileEx$$ExternalSyntheticLambda0 = new WifiTileEx$$ExternalSyntheticLambda0();
        wifiTileEx$$ExternalSyntheticLambda0.f$0 = this;
        VarHandle.storeStoreFence();
        this.customizeConnectivityManager$delegate = LazyKt__LazyJVMKt.lazy(wifiTileEx$$ExternalSyntheticLambda0);
    }

    private final OplusCustomizeConnectivityManager getCustomizeConnectivityManager() {
        return (OplusCustomizeConnectivityManager) this.customizeConnectivityManager$delegate.getValue();
    }

    @Override // com.oplus.systemui.qs.tileex.QSTileExImpl
    public final boolean isClickForbidden() {
        Object obj;
        try {
            int wlanPolicies = getCustomizeConnectivityManager().getWlanPolicies(getComponentName());
            boolean z = true;
            if (wlanPolicies != 0 && wlanPolicies != 1 && wlanPolicies != 5) {
                z = false;
            }
            obj = Result.constructor-impl(Boolean.valueOf(z));
        } catch (Throwable th) {
            obj = Result.constructor-impl(ResultKt.createFailure(th));
        }
        Throwable th2 = Result.exceptionOrNull-impl(obj);
        if (th2 != null) {
            OplusAODRefreshRateController$RequestRefreshRateRunnable$.ExternalSyntheticOutline0.m(8, "forbiddenForEnterpriseVersion: exception due to ", th2.getMessage(), LogUtil.TAG_QS, getTAG());
        }
        Boolean bool = Boolean.FALSE;
        if (obj instanceof Result.Failure) {
            obj = bool;
        }
        return ((Boolean) obj).booleanValue();
    }

    @Override // com.oplus.systemui.qs.tileex.QSTileExImpl
    public final boolean isTileDisable() {
        Object obj;
        try {
            int wlanPolicies = getCustomizeConnectivityManager().getWlanPolicies(getComponentName());
            boolean z = true;
            if (wlanPolicies != 0 && wlanPolicies != 1) {
                z = false;
            }
            obj = Result.constructor-impl(Boolean.valueOf(z));
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
    public TileUiFirst newUiFirstInteractor() {
        return TileUiFirstHelper.INSTANCE;
    }

    @Override // com.oplus.systemui.qs.tileex.QSTileExImpl
    public final void onClick() {
        QSTileExImpl.requestUxForTileThread();
        JankUtils.wlanSwitchSceneBegin(getContext());
    }

    @Override // com.oplus.systemui.qs.tileex.QSTileExImpl
    public final void onSecondaryClick() {
        QSTileExImpl.requestUxForTileThread();
        JankUtils.wlanPanelSceneBegin(getContext());
    }

    @Override // com.oplus.systemui.qs.tileex.QSTileExImpl
    public void resetIconForDisable(QSTile.State state) {
        super.resetIconForDisable(state);
        state.icon = QSTileImpl.ResourceIcon.get(2131237281);
    }
}
