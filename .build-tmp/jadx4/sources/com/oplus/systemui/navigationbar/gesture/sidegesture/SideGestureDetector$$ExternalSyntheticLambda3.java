package com.oplus.systemui.navigationbar.gesture.sidegesture;

import android.view.MotionEvent;
import android.view.View;
import com.android.keyguard.logging.KeyguardLogger$;
import com.oplus.systemui.navigationbar.utils.NavBarUtils;
import com.oplusos.systemui.common.feature.NavBarFeatureOption;
import com.oplusos.systemui.common.util.LogUtil;

/* JADX INFO: compiled from: go/retraceme fd74bf9514dd0739b92d67a6d48a51eb10aaf8df760d83496b8dcfcf83e5f698 */
/* JADX INFO: loaded from: /home/starlight/Projects/Starlight Ports/.build-tmp/classes4.dex */
public final /* synthetic */ class SideGestureDetector$$ExternalSyntheticLambda3 implements Runnable {
    public final /* synthetic */ int $r8$classId;
    public /* synthetic */ SideGestureDetector f$0;
    public /* synthetic */ MotionEvent f$1;

    public /* synthetic */ SideGestureDetector$$ExternalSyntheticLambda3(int i) {
        this.$r8$classId = i;
    }

    @Override // java.lang.Runnable
    public final void run() {
        boolean z = false;
        switch (this.$r8$classId) {
            case 0:
                SideGestureDetector sideGestureDetector = this.f$0;
                MotionEvent motionEvent = this.f$1;
                SideGestureHomeHandleAnimation sideGestureHomeHandleAnimation = sideGestureDetector.mSideGestureHomeHandleAnimation;
                long j = sideGestureDetector.mSysUiFlags;
                View view = sideGestureHomeHandleAnimation.mHome;
                boolean z2 = view != null && (view instanceof OplusNavigationHandle);
                motionEvent.getX();
                boolean z3 = ((int) motionEvent.getY()) >= sideGestureHomeHandleAnimation.mDisplaySize.y - sideGestureHomeHandleAnimation.mSideGestureConfiguration.getBottomGestureAreaHeight();
                if (z3 && !NavBarUtils.isSideGestureBarHide() && !NavBarUtils.getTaskbarStatus() && z2) {
                    long j2 = 2 & j;
                    if (j2 == 0) {
                        boolean z4 = ((j2 > 0L ? 1 : (j2 == 0L ? 0 : -1)) == 0) || (((131072 & j) > 0L ? 1 : ((131072 & j) == 0L ? 0 : -1)) != 0);
                        boolean z5 = (4 & j) != 0;
                        boolean z6 = (2048 & j) != 0;
                        boolean z7 = (64 & j) != 0;
                        boolean z8 = (256 & j) == 0 || (j & 128) == 0;
                        NavBarFeatureOption.INSTANCE.getClass();
                        if (!NavBarFeatureOption.sIsSupportTaskbar ? !(!z4 || ((z5 && !z7) || z6 || !z8)) : !(!z4 || z5 || z6 || !z8)) {
                            z = true;
                        }
                    }
                }
                sideGestureHomeHandleAnimation.mHandleNavBarAnimation = z;
                if (z3) {
                    LogUtil.i(LogUtil.TAG_NAVBAR, "NoBackGesture", KeyguardLogger$.ExternalSyntheticOutline0.m(new StringBuilder("gestureBar animation: gestureBarNotHide = "), true ^ NavBarUtils.isSideGestureBarHide(), ", isCorrectHomeHandle = ", z2));
                    if (sideGestureHomeHandleAnimation.mHandleNavBarAnimation) {
                        LogUtil.i(LogUtil.TAG_NAVBAR, "NoBackGesture", "send down event to NavigationBarHandle");
                        ((OplusNavigationHandle) sideGestureHomeHandleAnimation.mHome).handleValidTouchEvent(motionEvent);
                    }
                }
                break;
            default:
                SideGestureDetector sideGestureDetector2 = this.f$0;
                MotionEvent motionEvent2 = this.f$1;
                SideGestureHomeHandleAnimation sideGestureHomeHandleAnimation2 = sideGestureDetector2.mSideGestureHomeHandleAnimation;
                if (sideGestureHomeHandleAnimation2.mHandleNavBarAnimation) {
                    int actionMasked = motionEvent2.getActionMasked();
                    if (actionMasked == 1 || actionMasked == 3) {
                        sideGestureHomeHandleAnimation2.mHandleNavBarAnimation = false;
                    }
                    View view2 = sideGestureHomeHandleAnimation2.mHome;
                    if (view2 != null && (view2 instanceof OplusNavigationHandle)) {
                        ((OplusNavigationHandle) view2).handleValidTouchEvent(motionEvent2);
                        break;
                    }
                }
                break;
        }
    }
}
