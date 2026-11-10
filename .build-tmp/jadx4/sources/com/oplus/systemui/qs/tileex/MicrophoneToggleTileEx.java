package com.oplus.systemui.qs.tileex;

import android.os.Bundle;
import android.os.customize.OplusCustomizeCommonManager;
import com.oplus.systemui.aod.display.OplusAODRefreshRateController$RequestRefreshRateRunnable$;
import com.oplusos.systemui.common.util.LogUtil;
import java.lang.invoke.VarHandle;
import kotlin.Lazy;
import kotlin.LazyKt__LazyJVMKt;
import kotlin.Result;
import kotlin.ResultKt;

/* JADX INFO: compiled from: go/retraceme fd74bf9514dd0739b92d67a6d48a51eb10aaf8df760d83496b8dcfcf83e5f698 */
/* JADX INFO: loaded from: /home/starlight/Projects/Starlight Ports/.build-tmp/classes4.dex */
public final class MicrophoneToggleTileEx extends QSTileExImpl {
    public static final Companion Companion = new Companion();
    private static final String MIC_PERMISSION_POLICY = "MIC_PERMISSION_POLICY";
    private final Lazy customizeCommonManager$delegate;

    /* JADX INFO: compiled from: go/retraceme fd74bf9514dd0739b92d67a6d48a51eb10aaf8df760d83496b8dcfcf83e5f698 */
    public final class Companion {
    }

    public MicrophoneToggleTileEx(QSTileExParams qSTileExParams) {
        super(qSTileExParams);
        MicrophoneToggleTileEx$$ExternalSyntheticLambda0 microphoneToggleTileEx$$ExternalSyntheticLambda0 = new MicrophoneToggleTileEx$$ExternalSyntheticLambda0();
        microphoneToggleTileEx$$ExternalSyntheticLambda0.f$0 = this;
        VarHandle.storeStoreFence();
        this.customizeCommonManager$delegate = LazyKt__LazyJVMKt.lazy(microphoneToggleTileEx$$ExternalSyntheticLambda0);
    }

    private final OplusCustomizeCommonManager getCustomizeCommonManager() {
        return (OplusCustomizeCommonManager) this.customizeCommonManager$delegate.getValue();
    }

    private final int getMicPermissionPolicy() {
        Object obj;
        try {
            obj = Result.constructor-impl(Integer.valueOf(getCustomizeCommonManager().getPolicy(18012, new Bundle()).getInt(MIC_PERMISSION_POLICY, -1)));
        } catch (Throwable th) {
            obj = Result.constructor-impl(ResultKt.createFailure(th));
        }
        Throwable th2 = Result.exceptionOrNull-impl(obj);
        if (th2 != null) {
            OplusAODRefreshRateController$RequestRefreshRateRunnable$.ExternalSyntheticOutline0.m(8, "getMicPermissionPolicy: exception due to ", th2.getMessage(), LogUtil.TAG_QS, getTAG());
        }
        if (obj instanceof Result.Failure) {
            obj = -1;
        }
        return ((Number) obj).intValue();
    }

    @Override // com.oplus.systemui.qs.tileex.QSTileExImpl
    public final boolean isClickForbidden() {
        Object obj;
        try {
            int micPermissionPolicy = getMicPermissionPolicy();
            obj = Result.constructor-impl(Boolean.valueOf(micPermissionPolicy == 2 || micPermissionPolicy == 3));
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
        return getMicPermissionPolicy() == 3;
    }
}
