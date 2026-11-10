package com.oplus.systemui.statusbar;

import android.content.Context;
import com.oplus.systemui.OplusDependencyImpl;
import com.oplus.systemui.statusbar.notification.icdf.IcdfNotificationManagerOuterClass$IcdfNotification;
import com.oplus.zoom.common.zoomparameter.SmallScreenZoomParameter;

/* JADX INFO: compiled from: go/retraceme fd74bf9514dd0739b92d67a6d48a51eb10aaf8df760d83496b8dcfcf83e5f698 */
/* JADX INFO: loaded from: /home/starlight/Projects/Starlight Ports/.build-tmp/classes4.dex */
public final /* synthetic */ class OplusNotificationDependencyEx$$ExternalSyntheticLambda7 implements OplusDependencyImpl.LazyDependencyCreator {
    public final /* synthetic */ int $r8$classId;
    public /* synthetic */ OplusNotificationDependencyEx f$0;

    public final Object createDependency(Context context) {
        int i = this.$r8$classId;
        OplusNotificationDependencyEx oplusNotificationDependencyEx = this.f$0;
        switch (i) {
            case 0:
                return oplusNotificationDependencyEx.mViewWrapperEx.get();
            case 1:
                return oplusNotificationDependencyEx.mImmersiveCardControl.get();
            case 2:
                return oplusNotificationDependencyEx.mOplusPanoramicAodCoreStartable.get();
            case 3:
                return oplusNotificationDependencyEx.mRowAppearAnimationExLazy.get();
            case 4:
                return oplusNotificationDependencyEx.boostRefreshRateManagerLazy.get();
            case 5:
                return oplusNotificationDependencyEx.mLockscreenNotificationTransitionControllerExLazy.get();
            case 6:
                return oplusNotificationDependencyEx.mOplusCloseAllControllerLazy.get();
            case IcdfNotificationManagerOuterClass$IcdfNotification.EXPANDEDCHILD_FIELD_NUMBER /* 7 */:
                return oplusNotificationDependencyEx.mNotifInflateDelayStrategyLazy.get();
            case 8:
                return oplusNotificationDependencyEx.mNotificationRemoteInputManagerEX.get();
            case 9:
                return oplusNotificationDependencyEx.mKeyFlashlightController.get();
            case 10:
                return oplusNotificationDependencyEx.mKeyDndController.get();
            case 11:
                return oplusNotificationDependencyEx.mKeyBaseController.get();
            case 12:
                return oplusNotificationDependencyEx.mFlavorOneCapsuleController.get();
            case 13:
                return oplusNotificationDependencyEx.mNotifRemoteViewsFactoryContainerEx.get();
            case 14:
                return oplusNotificationDependencyEx.mNotificationGroupSplitEx.get();
            case 15:
                return oplusNotificationDependencyEx.mNotificationChildrenContainerOverlayEx.get();
            case 16:
                return oplusNotificationDependencyEx.mExpandableViewEx.get();
            case 17:
                return oplusNotificationDependencyEx.mOplusNotificationOverlayInteractor.get();
            case 18:
                return oplusNotificationDependencyEx.mTemplateViewWrapperEx.get();
            case 19:
                return oplusNotificationDependencyEx.mNotificationMaterialColorEx.get();
            case SmallScreenZoomParameter.SMALL_PORT_SCREEN_MINI_LEFT_RIGHT_OFFSET_DP /* 20 */:
                return oplusNotificationDependencyEx.mExpandableOutlineViewEx.get();
            default:
                return oplusNotificationDependencyEx.mHeaderViewWrapperEx.get();
        }
    }
}
