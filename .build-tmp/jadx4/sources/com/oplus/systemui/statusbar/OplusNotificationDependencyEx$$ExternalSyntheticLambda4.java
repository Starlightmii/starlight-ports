package com.oplus.systemui.statusbar;

import android.content.Context;
import com.oplus.systemui.OplusDependencyImpl;
import com.oplus.systemui.statusbar.notification.icdf.IcdfNotificationManagerOuterClass$IcdfNotification;
import com.oplus.zoom.common.zoomparameter.SmallScreenZoomParameter;

/* JADX INFO: compiled from: go/retraceme fd74bf9514dd0739b92d67a6d48a51eb10aaf8df760d83496b8dcfcf83e5f698 */
/* JADX INFO: loaded from: /home/starlight/Projects/Starlight Ports/.build-tmp/classes4.dex */
public final /* synthetic */ class OplusNotificationDependencyEx$$ExternalSyntheticLambda4 implements OplusDependencyImpl.LazyDependencyCreator {
    public final /* synthetic */ int $r8$classId;
    public /* synthetic */ OplusNotificationDependencyEx f$0;

    public final Object createDependency(Context context) {
        int i = this.$r8$classId;
        OplusNotificationDependencyEx oplusNotificationDependencyEx = this.f$0;
        switch (i) {
            case 0:
                return oplusNotificationDependencyEx.mNotificationEntryEx.get();
            case 1:
                return oplusNotificationDependencyEx.mNotificationCenterHelper.get();
            case 2:
                return oplusNotificationDependencyEx.mStackScrollAlgorithmEx.get();
            case 3:
                return oplusNotificationDependencyEx.mStackStateAnimatorEx.get();
            case 4:
                return oplusNotificationDependencyEx.mExpandHelperEx.get();
            case 5:
                return oplusNotificationDependencyEx.mOplusCustomRowControllerManager.get();
            case 6:
                return oplusNotificationDependencyEx.mUsbResolverActivityEx.get();
            case IcdfNotificationManagerOuterClass$IcdfNotification.EXPANDEDCHILD_FIELD_NUMBER /* 7 */:
                return oplusNotificationDependencyEx.mUsbConfirmActivityEx.get();
            case 8:
                return oplusNotificationDependencyEx.mRowAppearanceCoordinatorEx.get();
            case 9:
                return oplusNotificationDependencyEx.mShadeViewDifferEx.get();
            case 10:
                return oplusNotificationDependencyEx.mVisualStabilityCoordinatorEx.get();
            case 11:
                return oplusNotificationDependencyEx.mNotificationPlayerEx.get();
            case 12:
                return oplusNotificationDependencyEx.mDeleteSoundHelper.get();
            case 13:
                return oplusNotificationDependencyEx.mNotificationResolverEx.get();
            case 14:
                return oplusNotificationDependencyEx.mNotificationApplyDelayHelper.get();
            case 15:
                return oplusNotificationDependencyEx.mNotificationHeadsupAnimationEx.get();
            case 16:
                return oplusNotificationDependencyEx.mListEntryEx.get();
            case 17:
                return oplusNotificationDependencyEx.mNotificationDependOnCommon.get();
            case 18:
                return oplusNotificationDependencyEx.mOplusAmbientStateEx.get();
            case 19:
                return oplusNotificationDependencyEx.mShadeListBuilderLazy.get();
            case SmallScreenZoomParameter.SMALL_PORT_SCREEN_MINI_LEFT_RIGHT_OFFSET_DP /* 20 */:
                return oplusNotificationDependencyEx.StatusBarRemoteInputCallbackEx.get();
            case SmallScreenZoomParameter.SMALL_PORT_SCREEN_SUPER_MINI_LEFT_RIGHT_OFFSET_DP /* 21 */:
                return oplusNotificationDependencyEx.mLevelPositionAlgorithm.get();
            case 22:
                return oplusNotificationDependencyEx.mNotificationPullDownAnimationEx.get();
            case 23:
                return oplusNotificationDependencyEx.mNotificationStackScrollLayoutExtImpl.get();
            case 24:
                return oplusNotificationDependencyEx.mStatusBarStateControllerImpl.get();
            case 25:
                return oplusNotificationDependencyEx.mNotificationMaterialColorController.get();
            case 26:
                return oplusNotificationDependencyEx.mOplusSimpleQSManagerImpl.get();
            case 27:
                return oplusNotificationDependencyEx.mGroupEntryEx.get();
            case 28:
                return oplusNotificationDependencyEx.viewBlurManagerLazy.get();
            default:
                return oplusNotificationDependencyEx.mMaterialColorManagerLazy.get();
        }
    }
}
