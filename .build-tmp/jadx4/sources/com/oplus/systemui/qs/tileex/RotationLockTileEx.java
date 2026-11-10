package com.oplus.systemui.qs.tileex;

import android.os.oplusdevicepolicy.OplusDevicepolicyManager;
import com.android.systemui.util.JankUtils;
import com.oplus.systemui.aod.display.OplusAODRefreshRateController$RequestRefreshRateRunnable$;
import com.oplusos.systemui.common.util.LogUtil;
import kotlin.Result;
import kotlin.ResultKt;

/* JADX INFO: compiled from: go/retraceme fd74bf9514dd0739b92d67a6d48a51eb10aaf8df760d83496b8dcfcf83e5f698 */
/* JADX INFO: loaded from: /home/starlight/Projects/Starlight Ports/.build-tmp/classes4.dex */
public final class RotationLockTileEx extends QSTileExImpl {
    private static final String AUTO_ROTATE_POLICY = "persist.sys.auto_rotate_policy";
    public static final Companion Companion = new Companion();
    private static final String ROTATION_LOCK_FORCE_OFF = "4";
    private static final String ROTATION_LOCK_FORCE_ON = "3";
    public final OplusDevicepolicyManager devicePolicyManager;

    /* JADX INFO: compiled from: go/retraceme fd74bf9514dd0739b92d67a6d48a51eb10aaf8df760d83496b8dcfcf83e5f698 */
    public final class Companion {
    }

    public RotationLockTileEx(QSTileExParams qSTileExParams) {
        super(qSTileExParams);
        this.devicePolicyManager = OplusDevicepolicyManager.getInstance();
    }

    @Override // com.oplus.systemui.qs.tileex.QSTileExImpl
    public final boolean isClickForbidden() {
        Object obj;
        try {
            boolean z = true;
            String data = this.devicePolicyManager.getData(AUTO_ROTATE_POLICY, 1);
            if (!ROTATION_LOCK_FORCE_OFF.equals(data) && !ROTATION_LOCK_FORCE_ON.equals(data)) {
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
    public final void onClick() {
        QSTileExImpl.requestUxForTileThread();
        JankUtils.rotationSwitchSceneBegin(getContext());
    }
}
