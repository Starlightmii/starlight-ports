package com.oplus.systemui.navigationbar.gesture.sidegesture;

import com.android.systemui.DependencyEx;
import com.android.systemui.navigationbar.SideGestureConfigurationEx;
import com.android.systemui.navigationbar.SideGestureViewManagerEx;
import com.android.systemui.statusbar.notification.helper.OsenseHelper;
import com.oplus.systemui.aod.aodclock.util.CommonUtils$;
import com.oplus.systemui.statusbar.notification.helper.OsenseHelperImpl;
import com.oplusos.systemui.common.util.LogUtil;

/* JADX INFO: compiled from: go/retraceme fd74bf9514dd0739b92d67a6d48a51eb10aaf8df760d83496b8dcfcf83e5f698 */
/* JADX INFO: loaded from: /home/starlight/Projects/Starlight Ports/.build-tmp/classes4.dex */
public final /* synthetic */ class SideGestureDetector$$ExternalSyntheticLambda2 implements Runnable {
    public final /* synthetic */ int $r8$classId;
    public /* synthetic */ Object f$0;

    public /* synthetic */ SideGestureDetector$$ExternalSyntheticLambda2(int i) {
        this.$r8$classId = i;
    }

    @Override // java.lang.Runnable
    public final void run() {
        int i = this.$r8$classId;
        Object obj = this.f$0;
        switch (i) {
            case 0:
                SideGestureDetector sideGestureDetector = (SideGestureDetector) obj;
                SideGestureViewManagerEx sideGestureViewManagerEx = sideGestureDetector.mSideGestureViewManager;
                if (sideGestureViewManagerEx != null) {
                    sideGestureViewManagerEx.onConfigChanged(sideGestureDetector.mRotation);
                }
                break;
            case 1:
                SideGestureDetector sideGestureDetector2 = (SideGestureDetector) obj;
                long jCurrentTimeMillis = System.currentTimeMillis();
                if (jCurrentTimeMillis - sideGestureDetector2.mLastBoostRequestTime > 100) {
                    try {
                        OsenseHelper osenseHelper = (OsenseHelper) DependencyEx.sDependency.getDependency(OsenseHelper.class);
                        if (osenseHelper != null) {
                            ((OsenseHelperImpl) osenseHelper).requestSystemUIEdegSwipeDetect();
                            sideGestureDetector2.mLastBoostRequestTime = jCurrentTimeMillis;
                        }
                    } catch (Exception e) {
                        CommonUtils$.ExternalSyntheticOutline0.m(e, new StringBuilder("requestSystemUIEdegSwipeDetect failed: "), LogUtil.TAG_NAVBAR, "SideGestureDetector");
                        return;
                    }
                }
                break;
            case 2:
                SideGestureDetector.$r8$lambda$amvcDnjWy2zYLg4ZSWHWWPVzOWg((SideGestureDetector) obj);
                break;
            case 3:
                SideGestureDetector sideGestureDetector3 = (SideGestureDetector) obj;
                SideGestureConfigurationEx sideGestureConfigurationEx = sideGestureDetector3.mSideGestureConfiguration;
                sideGestureConfigurationEx.onSideBarSceneRegionChanged(sideGestureDetector3.mSideBarSceneRegion);
                sideGestureConfigurationEx.updateGestureAreaSize(sideGestureDetector3.mRotation);
                break;
            default:
                SideGestureDetector sideGestureDetector4 = ((SideGestureDetector.AnonymousClass2) obj).this$0;
                sideGestureDetector4.mSideGestureConfiguration.checkSideGestureAreaWidth(sideGestureDetector4.mRotation);
                break;
        }
    }
}
