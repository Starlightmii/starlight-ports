package com.oplus.systemui.qs.tileex;

import android.content.ComponentName;
import android.os.customize.OplusCustomizeRestrictionManager;
import android.os.customize.OplusCustomizeSecurityManager;
import android.telephony.OplusTelephonyManager;
import android.telephony.SubscriptionManager;
import com.android.systemui.navigationbar.gestural.domain.GestureInteractor$getTopActivity$2$;
import com.android.systemui.plugins.qs.QSTile;
import com.android.systemui.qs.tileimpl.QSTileImpl;
import com.android.systemui.util.JankUtils;
import com.oplus.systemui.aod.display.OplusAODRefreshRateController$RequestRefreshRateRunnable$;
import com.oplus.systemui.qs.OplusQSCarrierTextController;
import com.oplusos.systemui.common.util.LogUtil;
import java.lang.invoke.VarHandle;
import kotlin.Lazy;
import kotlin.LazyKt__LazyJVMKt;
import kotlin.Result;
import kotlin.ResultKt;

/* JADX INFO: compiled from: go/retraceme fd74bf9514dd0739b92d67a6d48a51eb10aaf8df760d83496b8dcfcf83e5f698 */
/* JADX INFO: loaded from: /home/starlight/Projects/Starlight Ports/.build-tmp/classes4.dex */
public final class CellularTileEx extends QSTileExImpl {
    public static final Companion Companion = new Companion();
    private final Lazy customizeRestrictionManager$delegate;
    private final Lazy customizeSecurityManager$delegate;
    public final OplusTelephonyManager oplusTelephonyManager;

    /* JADX INFO: compiled from: go/retraceme fd74bf9514dd0739b92d67a6d48a51eb10aaf8df760d83496b8dcfcf83e5f698 */
    public final class Companion {
    }

    public CellularTileEx(QSTileExParams qSTileExParams) {
        super(qSTileExParams);
        CellularTileEx$$ExternalSyntheticLambda0 cellularTileEx$$ExternalSyntheticLambda0 = new CellularTileEx$$ExternalSyntheticLambda0(0);
        cellularTileEx$$ExternalSyntheticLambda0.f$0 = this;
        VarHandle.storeStoreFence();
        this.customizeRestrictionManager$delegate = LazyKt__LazyJVMKt.lazy(cellularTileEx$$ExternalSyntheticLambda0);
        CellularTileEx$$ExternalSyntheticLambda0 cellularTileEx$$ExternalSyntheticLambda1 = new CellularTileEx$$ExternalSyntheticLambda0(1);
        cellularTileEx$$ExternalSyntheticLambda1.f$0 = this;
        VarHandle.storeStoreFence();
        this.customizeSecurityManager$delegate = LazyKt__LazyJVMKt.lazy(cellularTileEx$$ExternalSyntheticLambda1);
        this.oplusTelephonyManager = OplusTelephonyManager.getInstance(getContext());
    }

    private final OplusCustomizeRestrictionManager getCustomizeRestrictionManager() {
        return (OplusCustomizeRestrictionManager) this.customizeRestrictionManager$delegate.getValue();
    }

    private final OplusCustomizeSecurityManager getCustomizeSecurityManager() {
        return (OplusCustomizeSecurityManager) this.customizeSecurityManager$delegate.getValue();
    }

    public final int getMobileCommSettings(String str) {
        Object obj;
        try {
            obj = Result.constructor-impl(Integer.valueOf(getCustomizeSecurityManager().getMobileCommSettings((ComponentName) null, str, "BLOCK").getInt("BLOCK", -1)));
        } catch (Throwable th) {
            obj = Result.constructor-impl(ResultKt.createFailure(th));
        }
        Throwable th2 = Result.exceptionOrNull-impl(obj);
        if (th2 != null) {
            GestureInteractor$getTopActivity$2$.ExternalSyntheticOutline0.m(8, "getMobileCommSettings: fail due to ", th2.getMessage(), LogUtil.TAG_QS, getTAG());
        }
        if (obj instanceof Result.Failure) {
            obj = -1;
        }
        return ((Number) obj).intValue();
    }

    public final int getSlotState(ComponentName componentName) {
        boolean[] simInsertedStatus = OplusQSCarrierTextController.getSimInsertedStatus();
        int slot2DataConnectivityDisabled = -1;
        if (!simInsertedStatus[0] && !simInsertedStatus[1]) {
            return -1;
        }
        int phoneId = SubscriptionManager.getPhoneId(SubscriptionManager.getDefaultDataSubscriptionId());
        if (phoneId == 0) {
            slot2DataConnectivityDisabled = getCustomizeRestrictionManager().getSlot1DataConnectivityDisabled(componentName);
        } else if (1 == phoneId) {
            slot2DataConnectivityDisabled = getCustomizeRestrictionManager().getSlot2DataConnectivityDisabled(componentName);
        }
        if (this.oplusTelephonyManager.getCardType(phoneId) == 1) {
            if (getMobileCommSettings("CT-DATA") == 2) {
                return 1;
            }
            if (getMobileCommSettings("CT-DATA") == 0) {
                return 0;
            }
        } else if (getMobileCommSettings("NCT-DATA") == 0) {
            return 0;
        }
        return slot2DataConnectivityDisabled;
    }

    @Override // com.oplus.systemui.qs.tileex.QSTileExImpl
    public final boolean isClickForbidden() {
        Object obj;
        int slotState;
        try {
            int mobileDataMode = getCustomizeRestrictionManager().getMobileDataMode(getComponentName());
            boolean z = true;
            if (mobileDataMode != 0 && mobileDataMode != 1 && (slotState = getSlotState(getComponentName())) != 0 && slotState != 1) {
                z = false;
            }
            obj = Result.constructor-impl(Boolean.valueOf(z));
        } catch (Throwable th) {
            obj = Result.constructor-impl(ResultKt.createFailure(th));
        }
        Throwable th2 = Result.exceptionOrNull-impl(obj);
        if (th2 != null) {
            OplusAODRefreshRateController$RequestRefreshRateRunnable$.ExternalSyntheticOutline0.m(8, "forbiddenForEnterpriseVersion: e=", th2.getMessage(), LogUtil.TAG_QS, getTAG());
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
            boolean z = true;
            if (getCustomizeRestrictionManager().getMobileDataMode(getComponentName()) != 0 && getSlotState(getComponentName()) != 0) {
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
    public final void onClick() {
        QSTileExImpl.requestUxForTileThread();
        JankUtils.cellularSwitchSceneBegin(getContext());
    }

    @Override // com.oplus.systemui.qs.tileex.QSTileExImpl
    public void resetIconForDisable(QSTile.State state) {
        super.resetIconForDisable(state);
        state.icon = QSTileImpl.ResourceIcon.get(2131237139);
    }
}
