package com.oplus.systemui.statusbar;

import android.content.Context;
import com.oplus.systemui.OplusDependencyImpl;
import com.oplus.systemui.statusbar.notification.icdf.IcdfNotificationManagerOuterClass$IcdfNotification;
import com.oplus.zoom.common.zoomparameter.SmallScreenZoomParameter;

/* JADX INFO: compiled from: go/retraceme fd74bf9514dd0739b92d67a6d48a51eb10aaf8df760d83496b8dcfcf83e5f698 */
/* JADX INFO: loaded from: /home/starlight/Projects/Starlight Ports/.build-tmp/classes4.dex */
public final /* synthetic */ class OplusNotificationDependencyEx$$ExternalSyntheticLambda1 implements OplusDependencyImpl.LazyDependencyCreator {
    public final /* synthetic */ int $r8$classId;
    public /* synthetic */ OplusNotificationDependencyEx f$0;

    public final Object createDependency(Context context) {
        int i = this.$r8$classId;
        OplusNotificationDependencyEx oplusNotificationDependencyEx = this.f$0;
        switch (i) {
            case 0:
                return oplusNotificationDependencyEx.mActivatableNotificationViewEx.get();
            case 1:
                return oplusNotificationDependencyEx.mCallTemplateViewWrapperEx.get();
            case 2:
                return oplusNotificationDependencyEx.mBigPictureTemplateViewWrapperEx.get();
            case 3:
                return oplusNotificationDependencyEx.mBigTextTemplateViewWrapperEx.get();
            case 4:
                return oplusNotificationDependencyEx.mMediaTemplateViewWrapperEx.get();
            case 5:
                return oplusNotificationDependencyEx.mMessagingTemplateViewWrapperEx.get();
            case 6:
                return oplusNotificationDependencyEx.mExpandableViewStateEx.get();
            case IcdfNotificationManagerOuterClass$IcdfNotification.EXPANDEDCHILD_FIELD_NUMBER /* 7 */:
                return oplusNotificationDependencyEx.mViewStateEx.get();
            case 8:
                return oplusNotificationDependencyEx.mNotificationSnoozeEx.get();
            case 9:
                return oplusNotificationDependencyEx.mOsenseHelper.get();
            case 10:
                return oplusNotificationDependencyEx.mAfterSaleModeAlertHelper.get();
            case 11:
                return oplusNotificationDependencyEx.mNotificationCenterManagerEx.get();
            case 12:
                return oplusNotificationDependencyEx.mNotificationMediaManagerExt.get();
            case 13:
                return oplusNotificationDependencyEx.mNotificationChannelsEx.get();
            case 14:
                return oplusNotificationDependencyEx.mNotificationKeyguardHelper.get();
            case 15:
                return oplusNotificationDependencyEx.mPrivacyHelper.get();
            case 16:
                return oplusNotificationDependencyEx.mAonAuthManager.get();
            case 17:
                return oplusNotificationDependencyEx.mExpandableNotificationRowEx.get();
            case 18:
                return oplusNotificationDependencyEx.mOplusNotificationRowUi.get();
            case 19:
                return oplusNotificationDependencyEx.mLockScreenNotificationDispatcherLazy.get();
            case SmallScreenZoomParameter.SMALL_PORT_SCREEN_MINI_LEFT_RIGHT_OFFSET_DP /* 20 */:
                return oplusNotificationDependencyEx.mSmallAppHelper.get();
            case SmallScreenZoomParameter.SMALL_PORT_SCREEN_SUPER_MINI_LEFT_RIGHT_OFFSET_DP /* 21 */:
                return oplusNotificationDependencyEx.mNotificationChildrenContainerExt.get();
            case 22:
                return oplusNotificationDependencyEx.mNotificationGroupingUtilExt.get();
            case 23:
                return oplusNotificationDependencyEx.mSectionHeaderViewEx.get();
            case 24:
                return oplusNotificationDependencyEx.mEmptyShadeViewEx.get();
            case 25:
                return oplusNotificationDependencyEx.mNotificationContentInflaterEx.get();
            case 26:
                return oplusNotificationDependencyEx.mOplusStatusBarIconViewEx.get();
            case 27:
                return oplusNotificationDependencyEx.mShelfEx.get();
            case 28:
                return oplusNotificationDependencyEx.mNotificationSwipeHelperEx.get();
            default:
                return oplusNotificationDependencyEx.mClickEffectEx.get();
        }
    }
}
