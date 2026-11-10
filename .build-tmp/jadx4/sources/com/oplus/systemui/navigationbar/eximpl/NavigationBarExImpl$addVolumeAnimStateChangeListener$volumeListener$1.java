package com.oplus.systemui.navigationbar.eximpl;

import com.android.systemui.mediaprojection.permission.MediaProjectionPermissionActivity$;
import com.android.systemui.navigationbar.NavigationBarEx;
import com.android.systemui.shared.navigationbar.RegionSamplingHelper;
import com.oplus.systemui.volume.manager.VolumeAnimStateManager;
import com.oplusos.systemui.common.util.LogUtil;

/* JADX INFO: compiled from: go/retraceme fd74bf9514dd0739b92d67a6d48a51eb10aaf8df760d83496b8dcfcf83e5f698 */
/* JADX INFO: loaded from: /home/starlight/Projects/Starlight Ports/.build-tmp/classes4.dex */
public final class NavigationBarExImpl$addVolumeAnimStateChangeListener$volumeListener$1 implements VolumeAnimStateManager.VolumeAnimStateChangeListener {
    public /* synthetic */ NavigationBarEx.OnVolumeAnimStateChangeListener $listener;

    public final void onVolumeAnimStateChanged(boolean z) {
        RegionSamplingHelper regionSamplingHelper = this.$listener.f$0.mRegionSamplingHelper;
        if (RegionSamplingHelper.IS_INVERT_COLOR_DISABLED) {
            regionSamplingHelper.mNeedRestrainInvertColorWhenAnimation = z;
            regionSamplingHelper.updateSamplingListener();
        } else {
            regionSamplingHelper.getClass();
        }
        MediaProjectionPermissionActivity$.ExternalSyntheticOutline0.m("restrainNavigationBarInvertColor mVolumeStateListener, isPlay = ", LogUtil.TAG_NAVBAR, "NavigationBar", z);
    }
}
