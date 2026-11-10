package com.oplus.systemui.statusbar;

import android.content.Context;
import android.util.ArrayMap;
import com.android.systemui.LogWindowBase;
import com.android.systemui.OplusExpandHelperEx;
import com.android.systemui.media.NotificationPlayerEx;
import com.android.systemui.media.controls.models.player.MediaViewHolderExt;
import com.android.systemui.notification.stackednotification.NotificationGroupSplitEx;
import com.android.systemui.panelanimation.LevelPositionAlgorithm;
import com.android.systemui.panelanimation.notification.NotificationRowShowAnimationEx;
import com.android.systemui.scrim.ScrimViewEx;
import com.android.systemui.separate.OplusSimpleQSManager;
import com.android.systemui.statusbar.EmptyShadeViewEx;
import com.android.systemui.statusbar.LockScreenNotificationDispatcher;
import com.android.systemui.statusbar.NotificationGroupingUtilEx;
import com.android.systemui.statusbar.NotificationRemoteInputManagerEx;
import com.android.systemui.statusbar.OplusLockscreenShadeTransitionControllerEx;
import com.android.systemui.statusbar.OplusNotificationMediaManagerExt;
import com.android.systemui.statusbar.OplusStatusBarIconViewEx;
import com.android.systemui.statusbar.SysuiStatusBarStateController;
import com.android.systemui.statusbar.notification.animation.RowAppearAnimationEx;
import com.android.systemui.statusbar.notification.collection.NotificationCenterManagerEx;
import com.android.systemui.statusbar.notification.collection.NotificationChannelsEx;
import com.android.systemui.statusbar.notification.collection.NotificationResolverEx;
import com.android.systemui.statusbar.notification.collection.OplusGroupEntryEx;
import com.android.systemui.statusbar.notification.collection.OplusListEntryEx;
import com.android.systemui.statusbar.notification.collection.OplusNotificationEntryEx;
import com.android.systemui.statusbar.notification.collection.ShadeListBuilder;
import com.android.systemui.statusbar.notification.collection.VisualStabilityCoordinatorEx;
import com.android.systemui.statusbar.notification.collection.coordinator.RowAppearanceCoordinatorEx;
import com.android.systemui.statusbar.notification.collection.inflation.NotifInflateDelayStrategy;
import com.android.systemui.statusbar.notification.collection.render.OplusNodeSpecBuilderEx;
import com.android.systemui.statusbar.notification.collection.render.ShadeViewDifferEx;
import com.android.systemui.statusbar.notification.helper.NotificationApplyDelayHelper;
import com.android.systemui.statusbar.notification.helper.OsenseHelper;
import com.android.systemui.statusbar.notification.helper.PrivacyHelper;
import com.android.systemui.statusbar.notification.row.NotificationBackgroundViewExt;
import com.android.systemui.statusbar.notification.row.NotificationContentViewExt;
import com.android.systemui.statusbar.notification.row.NotificationMenuRowExt;
import com.android.systemui.statusbar.notification.row.NotificationSnoozeExt;
import com.android.systemui.statusbar.notification.row.OplusActivatableNotificationViewEx;
import com.android.systemui.statusbar.notification.row.OplusExpandableNotificationRowEx;
import com.android.systemui.statusbar.notification.row.OplusExpandableOutlineViewEx;
import com.android.systemui.statusbar.notification.row.OplusExpandableViewEx;
import com.android.systemui.statusbar.notification.row.OplusNotifRemoteViewsFactoryContainerEx;
import com.android.systemui.statusbar.notification.row.OplusNotificationContentInflaterEx;
import com.android.systemui.statusbar.notification.row.wrapper.OplusNotificationBigPictureTemplateViewWrapperEx;
import com.android.systemui.statusbar.notification.row.wrapper.OplusNotificationBigTextTemplateViewWrapperEx;
import com.android.systemui.statusbar.notification.row.wrapper.OplusNotificationCallTemplateViewWrapperEx;
import com.android.systemui.statusbar.notification.row.wrapper.OplusNotificationHeaderViewWrapperEx;
import com.android.systemui.statusbar.notification.row.wrapper.OplusNotificationMediaTemplateViewWrapperEx;
import com.android.systemui.statusbar.notification.row.wrapper.OplusNotificationMessagingTemplateViewWrapperEx;
import com.android.systemui.statusbar.notification.row.wrapper.OplusNotificationTemplateViewWrapperEx;
import com.android.systemui.statusbar.notification.row.wrapper.OplusNotificationViewWrapperEx;
import com.android.systemui.statusbar.notification.stack.NotificationChildrenContainerExt;
import com.android.systemui.statusbar.notification.stack.NotificationChildrenContainerOverlayEx;
import com.android.systemui.statusbar.notification.stack.NotificationHeadsUpAnimationEx;
import com.android.systemui.statusbar.notification.stack.NotificationStackScrollLayoutExt;
import com.android.systemui.statusbar.notification.stack.NotificationSwipeHelperEx;
import com.android.systemui.statusbar.notification.stack.OplusAmbientStateEx;
import com.android.systemui.statusbar.notification.stack.OplusExpandableViewStateEx;
import com.android.systemui.statusbar.notification.stack.OplusStackScrollAlgorithmEx;
import com.android.systemui.statusbar.notification.stack.OplusStackStateAnimatorEx;
import com.android.systemui.statusbar.notification.stack.OplusViewStateEx;
import com.android.systemui.statusbar.notification.stack.SectionHeaderViewEx;
import com.android.systemui.statusbar.notification.usb.UsbConfirmActivityEx;
import com.android.systemui.statusbar.notification.usb.UsbResolverActivityEx;
import com.android.systemui.statusbar.phone.ScrimStateEx;
import com.android.systemui.statusbar.phone.StatusBarRemoteInputCallbackEx;
import com.android.systemui.statusbar.policy.RemoteInputQuickSettingsDisablerEx;
import com.android.systemui.statusbar.policy.RemoteInputViewExt;
import com.oplus.systemui.OplusDependencyImpl;
import com.oplus.systemui.capsule.FlavorOneCapsuleController;
import com.oplus.systemui.media.controls.model.OplusMediaViewHolderExt;
import com.oplus.systemui.notification.aod.OplusPanoramicAodRepository;
import com.oplus.systemui.notification.aon.AonAuthManager;
import com.oplus.systemui.notification.blur.ViewBlurManager;
import com.oplus.systemui.notification.color.MaterialColorManager;
import com.oplus.systemui.notification.dagger.OplusNotificationDependexcyExAdder;
import com.oplus.systemui.notification.framerate.BoostFrameRateManager;
import com.oplus.systemui.notification.lockscreen.immersive.ImmersiveCardControl;
import com.oplus.systemui.notification.row.NotificationMenuRowExtImpl;
import com.oplus.systemui.notification.row.OplusNotificationRowUi;
import com.oplus.systemui.notification.row.OplusShelfEx;
import com.oplus.systemui.notification.row.click.NotificationClickEffectController;
import com.oplus.systemui.notification.row.material.NotificationMaterialColorEx;
import com.oplus.systemui.notification.row.material.NotificationMaterialColorModeController;
import com.oplus.systemui.notification.row.overlay.OplusNotificationOverlayInteractor;
import com.oplus.systemui.scrim.ScrimViewExImp;
import com.oplus.systemui.statusbar.notification.OplusCloseAllController;
import com.oplus.systemui.statusbar.notification.collection.render.OplusNodeSpecBuilderExImpl;
import com.oplus.systemui.statusbar.notification.customcard.OplusCustomRowControllerManager;
import com.oplus.systemui.statusbar.notification.dependence.NotificationDependOnCommon;
import com.oplus.systemui.statusbar.notification.helper.AfterSaleModeAlertHelper;
import com.oplus.systemui.statusbar.notification.helper.DeleteSoundHelper;
import com.oplus.systemui.statusbar.notification.helper.NotificationCenterHelper;
import com.oplus.systemui.statusbar.notification.helper.NotificationKeyguardHelper;
import com.oplus.systemui.statusbar.notification.helper.SmallAppHelper;
import com.oplus.systemui.statusbar.notification.icdf.IcdfNotificationManagerOuterClass$IcdfNotification;
import com.oplus.systemui.statusbar.notification.keymagicservice.KeyBaseController;
import com.oplus.systemui.statusbar.notification.keymagicservice.KeyDndController;
import com.oplus.systemui.statusbar.notification.keymagicservice.KeyFlashlightController;
import com.oplus.systemui.statusbar.notification.row.NotificationBackgroundViewExtImp;
import com.oplus.systemui.statusbar.notification.row.NotificationContentViewExtImp;
import com.oplus.systemui.statusbar.phone.ScrimStateExImp;
import com.oplus.systemui.statusbar.policy.RemoteInputQuickSettingsDisablerExImpl;
import com.oplus.systemui.statusbar.policy.RemoteInputViewExtImpl;
import com.oplus.systemui.util.LogWindowUtils;
import com.oplusos.systemui.common.feature.FlavorOneFeatureOption;
import dagger.Lazy;
import java.lang.invoke.VarHandle;
import javax.inject.Provider;

/* JADX INFO: compiled from: go/retraceme fd74bf9514dd0739b92d67a6d48a51eb10aaf8df760d83496b8dcfcf83e5f698 */
/* JADX INFO: loaded from: /home/starlight/Projects/Starlight Ports/.build-tmp/classes4.dex */
public class OplusNotificationDependencyEx {
    Provider StatusBarRemoteInputCallbackEx;
    Lazy boostRefreshRateManagerLazy;
    Provider mActivatableNotificationViewEx;
    Lazy mAfterSaleModeAlertHelper;
    Lazy mAonAuthManager;
    Provider mBigPictureTemplateViewWrapperEx;
    Provider mBigTextTemplateViewWrapperEx;
    Provider mCallTemplateViewWrapperEx;
    Lazy mClickEffectEx;
    Lazy mDeleteSoundHelper;
    Provider mEmptyShadeViewEx;
    Lazy mExpandHelperEx;
    Provider mExpandableNotificationRowEx;
    Provider mExpandableOutlineViewEx;
    Provider mExpandableViewEx;
    Provider mExpandableViewStateEx;
    Lazy mFlavorOneCapsuleController;
    Provider mGroupEntryEx;
    Provider mHeaderViewWrapperEx;
    Lazy mImmersiveCardControl;
    Lazy mKeyBaseController;
    Lazy mKeyDndController;
    Lazy mKeyFlashlightController;
    Lazy mLevelPositionAlgorithm;
    Provider mListEntryEx;
    Lazy mLockScreenNotificationDispatcherLazy;
    Lazy mLockscreenNotificationTransitionControllerExLazy;
    Lazy mLogWindow;
    Lazy mMaterialColorManagerLazy;
    Provider mMediaTemplateViewWrapperEx;
    Provider mMessagingTemplateViewWrapperEx;
    Lazy mNotifInflateDelayStrategyLazy;
    Lazy mNotifRemoteViewsFactoryContainerEx;
    Lazy mNotificationApplyDelayHelper;
    Lazy mNotificationCenterHelper;
    Lazy mNotificationCenterManagerEx;
    Lazy mNotificationChannelsEx;
    Provider mNotificationChildrenContainerExt;
    Provider mNotificationChildrenContainerOverlayEx;
    Lazy mNotificationContentInflaterEx;
    Provider mNotificationDependOnCommon;
    Provider mNotificationEntryEx;
    Lazy mNotificationGroupSplitEx;
    Provider mNotificationGroupingUtilExt;
    Provider mNotificationHeadsupAnimationEx;
    Lazy mNotificationKeyguardHelper;
    Lazy mNotificationMaterialColorController;
    Lazy mNotificationMaterialColorEx;
    Lazy mNotificationMediaManagerExt;
    Lazy mNotificationPlayerEx;
    Lazy mNotificationPullDownAnimationEx;
    Provider mNotificationRemoteInputManagerEX;
    Lazy mNotificationResolverEx;
    Provider mNotificationSnoozeEx;
    Lazy mNotificationStackScrollLayoutExtImpl;
    Lazy mNotificationSwipeHelperEx;
    Lazy mOplusAmbientStateEx;
    Lazy mOplusCloseAllControllerLazy;
    Lazy mOplusCustomRowControllerManager;
    Lazy mOplusNotificationDependexcyExAdder;
    Lazy mOplusNotificationOverlayInteractor;
    Lazy mOplusNotificationRowUi;
    Lazy mOplusPanoramicAodCoreStartable;
    Lazy mOplusSimpleQSManagerImpl;
    Provider mOplusStatusBarIconViewEx;
    Lazy mOsenseHelper;
    Lazy mPrivacyHelper;
    Lazy mRowAppearAnimationExLazy;
    Lazy mRowAppearanceCoordinatorEx;
    Provider mSectionHeaderViewEx;
    Lazy mShadeListBuilderLazy;
    Lazy mShadeViewDifferEx;
    Provider mShelfEx;
    Lazy mSmallAppHelper;
    Lazy mStackScrollAlgorithmEx;
    Lazy mStackStateAnimatorEx;
    Lazy mStatusBarStateControllerImpl;
    Provider mTemplateViewWrapperEx;
    Lazy mUsbConfirmActivityEx;
    Lazy mUsbResolverActivityEx;
    Provider mViewStateEx;
    Provider mViewWrapperEx;
    Lazy mVisualStabilityCoordinatorEx;
    Lazy viewBlurManagerLazy;

    public final void startOplus(ArrayMap arrayMap) {
        ((OplusNotificationDependexcyExAdder) this.mOplusNotificationDependexcyExAdder.get()).start(arrayMap);
        final int i = 0;
        arrayMap.put(ScrimViewEx.class, new OplusDependencyImpl.LazyDependencyCreator() { // from class: com.oplus.systemui.statusbar.OplusNotificationDependencyEx$$ExternalSyntheticLambda0
            public final Object createDependency(Context context) {
                switch (i) {
                    case 0:
                        return new ScrimViewExImp(context);
                    case 1:
                        return new ScrimStateExImp(context);
                    case 2:
                        return new NotificationBackgroundViewExtImp(context);
                    case 3:
                        return new OplusNodeSpecBuilderExImpl(context);
                    case 4:
                        return new LogWindowUtils(context);
                    case 5:
                        return new NotificationContentViewExtImp(context);
                    case 6:
                        return new RemoteInputViewExtImpl(context);
                    case IcdfNotificationManagerOuterClass$IcdfNotification.EXPANDEDCHILD_FIELD_NUMBER /* 7 */:
                        return new OplusMediaViewHolderExt(context);
                    case 8:
                        return new RemoteInputQuickSettingsDisablerExImpl(context);
                    default:
                        return new NotificationMenuRowExtImpl(context);
                }
            }
        });
        final int i2 = 1;
        arrayMap.put(ScrimStateEx.class, new OplusDependencyImpl.LazyDependencyCreator() { // from class: com.oplus.systemui.statusbar.OplusNotificationDependencyEx$$ExternalSyntheticLambda0
            public final Object createDependency(Context context) {
                switch (i2) {
                    case 0:
                        return new ScrimViewExImp(context);
                    case 1:
                        return new ScrimStateExImp(context);
                    case 2:
                        return new NotificationBackgroundViewExtImp(context);
                    case 3:
                        return new OplusNodeSpecBuilderExImpl(context);
                    case 4:
                        return new LogWindowUtils(context);
                    case 5:
                        return new NotificationContentViewExtImp(context);
                    case 6:
                        return new RemoteInputViewExtImpl(context);
                    case IcdfNotificationManagerOuterClass$IcdfNotification.EXPANDEDCHILD_FIELD_NUMBER /* 7 */:
                        return new OplusMediaViewHolderExt(context);
                    case 8:
                        return new RemoteInputQuickSettingsDisablerExImpl(context);
                    default:
                        return new NotificationMenuRowExtImpl(context);
                }
            }
        });
        OplusNotificationDependencyEx$$ExternalSyntheticLambda1 oplusNotificationDependencyEx$$ExternalSyntheticLambda1 = new OplusNotificationDependencyEx$$ExternalSyntheticLambda1(12);
        oplusNotificationDependencyEx$$ExternalSyntheticLambda1.f$0 = this;
        VarHandle.storeStoreFence();
        arrayMap.put(OplusNotificationMediaManagerExt.class, oplusNotificationDependencyEx$$ExternalSyntheticLambda1);
        OplusNotificationDependencyEx$$ExternalSyntheticLambda1 oplusNotificationDependencyEx$$ExternalSyntheticLambda2 = new OplusNotificationDependencyEx$$ExternalSyntheticLambda1(20);
        oplusNotificationDependencyEx$$ExternalSyntheticLambda2.f$0 = this;
        VarHandle.storeStoreFence();
        arrayMap.put(SmallAppHelper.class, oplusNotificationDependencyEx$$ExternalSyntheticLambda2);
        OplusNotificationDependencyEx$$ExternalSyntheticLambda4 oplusNotificationDependencyEx$$ExternalSyntheticLambda4 = new OplusNotificationDependencyEx$$ExternalSyntheticLambda4(1);
        oplusNotificationDependencyEx$$ExternalSyntheticLambda4.f$0 = this;
        VarHandle.storeStoreFence();
        arrayMap.put(NotificationCenterHelper.class, oplusNotificationDependencyEx$$ExternalSyntheticLambda4);
        OplusNotificationDependencyEx$$ExternalSyntheticLambda4 oplusNotificationDependencyEx$$ExternalSyntheticLambda5 = new OplusNotificationDependencyEx$$ExternalSyntheticLambda4(12);
        oplusNotificationDependencyEx$$ExternalSyntheticLambda5.f$0 = this;
        VarHandle.storeStoreFence();
        arrayMap.put(DeleteSoundHelper.class, oplusNotificationDependencyEx$$ExternalSyntheticLambda5);
        OplusNotificationDependencyEx$$ExternalSyntheticLambda4 oplusNotificationDependencyEx$$ExternalSyntheticLambda6 = new OplusNotificationDependencyEx$$ExternalSyntheticLambda4(23);
        oplusNotificationDependencyEx$$ExternalSyntheticLambda6.f$0 = this;
        VarHandle.storeStoreFence();
        arrayMap.put(NotificationStackScrollLayoutExt.class, oplusNotificationDependencyEx$$ExternalSyntheticLambda6);
        final int i3 = 9;
        arrayMap.put(NotificationMenuRowExt.class, new OplusDependencyImpl.LazyDependencyCreator() { // from class: com.oplus.systemui.statusbar.OplusNotificationDependencyEx$$ExternalSyntheticLambda0
            public final Object createDependency(Context context) {
                switch (i3) {
                    case 0:
                        return new ScrimViewExImp(context);
                    case 1:
                        return new ScrimStateExImp(context);
                    case 2:
                        return new NotificationBackgroundViewExtImp(context);
                    case 3:
                        return new OplusNodeSpecBuilderExImpl(context);
                    case 4:
                        return new LogWindowUtils(context);
                    case 5:
                        return new NotificationContentViewExtImp(context);
                    case 6:
                        return new RemoteInputViewExtImpl(context);
                    case IcdfNotificationManagerOuterClass$IcdfNotification.EXPANDEDCHILD_FIELD_NUMBER /* 7 */:
                        return new OplusMediaViewHolderExt(context);
                    case 8:
                        return new RemoteInputQuickSettingsDisablerExImpl(context);
                    default:
                        return new NotificationMenuRowExtImpl(context);
                }
            }
        });
        OplusNotificationDependencyEx$$ExternalSyntheticLambda7 oplusNotificationDependencyEx$$ExternalSyntheticLambda7 = new OplusNotificationDependencyEx$$ExternalSyntheticLambda7(16);
        oplusNotificationDependencyEx$$ExternalSyntheticLambda7.f$0 = this;
        VarHandle.storeStoreFence();
        arrayMap.put(OplusExpandableViewEx.class, oplusNotificationDependencyEx$$ExternalSyntheticLambda7);
        OplusNotificationDependencyEx$$ExternalSyntheticLambda7 oplusNotificationDependencyEx$$ExternalSyntheticLambda8 = new OplusNotificationDependencyEx$$ExternalSyntheticLambda7(20);
        oplusNotificationDependencyEx$$ExternalSyntheticLambda8.f$0 = this;
        VarHandle.storeStoreFence();
        arrayMap.put(OplusExpandableOutlineViewEx.class, oplusNotificationDependencyEx$$ExternalSyntheticLambda8);
        OplusNotificationDependencyEx$$ExternalSyntheticLambda1 oplusNotificationDependencyEx$$ExternalSyntheticLambda3 = new OplusNotificationDependencyEx$$ExternalSyntheticLambda1(0);
        oplusNotificationDependencyEx$$ExternalSyntheticLambda3.f$0 = this;
        VarHandle.storeStoreFence();
        arrayMap.put(OplusActivatableNotificationViewEx.class, oplusNotificationDependencyEx$$ExternalSyntheticLambda3);
        OplusNotificationDependencyEx$$ExternalSyntheticLambda1 oplusNotificationDependencyEx$$ExternalSyntheticLambda9 = new OplusNotificationDependencyEx$$ExternalSyntheticLambda1(17);
        oplusNotificationDependencyEx$$ExternalSyntheticLambda9.f$0 = this;
        VarHandle.storeStoreFence();
        arrayMap.put(OplusExpandableNotificationRowEx.class, oplusNotificationDependencyEx$$ExternalSyntheticLambda9);
        OplusNotificationDependencyEx$$ExternalSyntheticLambda1 oplusNotificationDependencyEx$$ExternalSyntheticLambda10 = new OplusNotificationDependencyEx$$ExternalSyntheticLambda1(27);
        oplusNotificationDependencyEx$$ExternalSyntheticLambda10.f$0 = this;
        VarHandle.storeStoreFence();
        arrayMap.put(OplusShelfEx.class, oplusNotificationDependencyEx$$ExternalSyntheticLambda10);
        OplusNotificationDependencyEx$$ExternalSyntheticLambda4 oplusNotificationDependencyEx$$ExternalSyntheticLambda11 = new OplusNotificationDependencyEx$$ExternalSyntheticLambda4(0);
        oplusNotificationDependencyEx$$ExternalSyntheticLambda11.f$0 = this;
        VarHandle.storeStoreFence();
        arrayMap.put(OplusNotificationEntryEx.class, oplusNotificationDependencyEx$$ExternalSyntheticLambda11);
        OplusNotificationDependencyEx$$ExternalSyntheticLambda4 oplusNotificationDependencyEx$$ExternalSyntheticLambda12 = new OplusNotificationDependencyEx$$ExternalSyntheticLambda4(16);
        oplusNotificationDependencyEx$$ExternalSyntheticLambda12.f$0 = this;
        VarHandle.storeStoreFence();
        arrayMap.put(OplusListEntryEx.class, oplusNotificationDependencyEx$$ExternalSyntheticLambda12);
        OplusNotificationDependencyEx$$ExternalSyntheticLambda4 oplusNotificationDependencyEx$$ExternalSyntheticLambda13 = new OplusNotificationDependencyEx$$ExternalSyntheticLambda4(27);
        oplusNotificationDependencyEx$$ExternalSyntheticLambda13.f$0 = this;
        VarHandle.storeStoreFence();
        arrayMap.put(OplusGroupEntryEx.class, oplusNotificationDependencyEx$$ExternalSyntheticLambda13);
        OplusNotificationDependencyEx$$ExternalSyntheticLambda7 oplusNotificationDependencyEx$$ExternalSyntheticLambda14 = new OplusNotificationDependencyEx$$ExternalSyntheticLambda7(0);
        oplusNotificationDependencyEx$$ExternalSyntheticLambda14.f$0 = this;
        VarHandle.storeStoreFence();
        arrayMap.put(OplusNotificationViewWrapperEx.class, oplusNotificationDependencyEx$$ExternalSyntheticLambda14);
        OplusNotificationDependencyEx$$ExternalSyntheticLambda7 oplusNotificationDependencyEx$$ExternalSyntheticLambda15 = new OplusNotificationDependencyEx$$ExternalSyntheticLambda7(18);
        oplusNotificationDependencyEx$$ExternalSyntheticLambda15.f$0 = this;
        VarHandle.storeStoreFence();
        arrayMap.put(OplusNotificationTemplateViewWrapperEx.class, oplusNotificationDependencyEx$$ExternalSyntheticLambda15);
        OplusNotificationDependencyEx$$ExternalSyntheticLambda7 oplusNotificationDependencyEx$$ExternalSyntheticLambda16 = new OplusNotificationDependencyEx$$ExternalSyntheticLambda7(21);
        oplusNotificationDependencyEx$$ExternalSyntheticLambda16.f$0 = this;
        VarHandle.storeStoreFence();
        arrayMap.put(OplusNotificationHeaderViewWrapperEx.class, oplusNotificationDependencyEx$$ExternalSyntheticLambda16);
        OplusNotificationDependencyEx$$ExternalSyntheticLambda1 oplusNotificationDependencyEx$$ExternalSyntheticLambda17 = new OplusNotificationDependencyEx$$ExternalSyntheticLambda1(1);
        oplusNotificationDependencyEx$$ExternalSyntheticLambda17.f$0 = this;
        VarHandle.storeStoreFence();
        arrayMap.put(OplusNotificationCallTemplateViewWrapperEx.class, oplusNotificationDependencyEx$$ExternalSyntheticLambda17);
        final int i4 = 2;
        OplusNotificationDependencyEx$$ExternalSyntheticLambda1 oplusNotificationDependencyEx$$ExternalSyntheticLambda18 = new OplusNotificationDependencyEx$$ExternalSyntheticLambda1(2);
        oplusNotificationDependencyEx$$ExternalSyntheticLambda18.f$0 = this;
        VarHandle.storeStoreFence();
        arrayMap.put(OplusNotificationBigPictureTemplateViewWrapperEx.class, oplusNotificationDependencyEx$$ExternalSyntheticLambda18);
        final int i5 = 3;
        OplusNotificationDependencyEx$$ExternalSyntheticLambda1 oplusNotificationDependencyEx$$ExternalSyntheticLambda19 = new OplusNotificationDependencyEx$$ExternalSyntheticLambda1(3);
        oplusNotificationDependencyEx$$ExternalSyntheticLambda19.f$0 = this;
        VarHandle.storeStoreFence();
        arrayMap.put(OplusNotificationBigTextTemplateViewWrapperEx.class, oplusNotificationDependencyEx$$ExternalSyntheticLambda19);
        final int i6 = 4;
        OplusNotificationDependencyEx$$ExternalSyntheticLambda1 oplusNotificationDependencyEx$$ExternalSyntheticLambda20 = new OplusNotificationDependencyEx$$ExternalSyntheticLambda1(4);
        oplusNotificationDependencyEx$$ExternalSyntheticLambda20.f$0 = this;
        VarHandle.storeStoreFence();
        arrayMap.put(OplusNotificationMediaTemplateViewWrapperEx.class, oplusNotificationDependencyEx$$ExternalSyntheticLambda20);
        final int i7 = 5;
        OplusNotificationDependencyEx$$ExternalSyntheticLambda1 oplusNotificationDependencyEx$$ExternalSyntheticLambda21 = new OplusNotificationDependencyEx$$ExternalSyntheticLambda1(5);
        oplusNotificationDependencyEx$$ExternalSyntheticLambda21.f$0 = this;
        VarHandle.storeStoreFence();
        arrayMap.put(OplusNotificationMessagingTemplateViewWrapperEx.class, oplusNotificationDependencyEx$$ExternalSyntheticLambda21);
        final int i8 = 6;
        OplusNotificationDependencyEx$$ExternalSyntheticLambda1 oplusNotificationDependencyEx$$ExternalSyntheticLambda22 = new OplusNotificationDependencyEx$$ExternalSyntheticLambda1(6);
        oplusNotificationDependencyEx$$ExternalSyntheticLambda22.f$0 = this;
        VarHandle.storeStoreFence();
        arrayMap.put(OplusExpandableViewStateEx.class, oplusNotificationDependencyEx$$ExternalSyntheticLambda22);
        final int i9 = 7;
        OplusNotificationDependencyEx$$ExternalSyntheticLambda1 oplusNotificationDependencyEx$$ExternalSyntheticLambda23 = new OplusNotificationDependencyEx$$ExternalSyntheticLambda1(7);
        oplusNotificationDependencyEx$$ExternalSyntheticLambda23.f$0 = this;
        VarHandle.storeStoreFence();
        arrayMap.put(OplusViewStateEx.class, oplusNotificationDependencyEx$$ExternalSyntheticLambda23);
        final int i10 = 8;
        OplusNotificationDependencyEx$$ExternalSyntheticLambda1 oplusNotificationDependencyEx$$ExternalSyntheticLambda24 = new OplusNotificationDependencyEx$$ExternalSyntheticLambda1(8);
        oplusNotificationDependencyEx$$ExternalSyntheticLambda24.f$0 = this;
        VarHandle.storeStoreFence();
        arrayMap.put(NotificationSnoozeExt.class, oplusNotificationDependencyEx$$ExternalSyntheticLambda24);
        OplusNotificationDependencyEx$$ExternalSyntheticLambda1 oplusNotificationDependencyEx$$ExternalSyntheticLambda25 = new OplusNotificationDependencyEx$$ExternalSyntheticLambda1(9);
        oplusNotificationDependencyEx$$ExternalSyntheticLambda25.f$0 = this;
        VarHandle.storeStoreFence();
        arrayMap.put(OsenseHelper.class, oplusNotificationDependencyEx$$ExternalSyntheticLambda25);
        OplusNotificationDependencyEx$$ExternalSyntheticLambda1 oplusNotificationDependencyEx$$ExternalSyntheticLambda26 = new OplusNotificationDependencyEx$$ExternalSyntheticLambda1(10);
        oplusNotificationDependencyEx$$ExternalSyntheticLambda26.f$0 = this;
        VarHandle.storeStoreFence();
        arrayMap.put(AfterSaleModeAlertHelper.class, oplusNotificationDependencyEx$$ExternalSyntheticLambda26);
        OplusNotificationDependencyEx$$ExternalSyntheticLambda1 oplusNotificationDependencyEx$$ExternalSyntheticLambda27 = new OplusNotificationDependencyEx$$ExternalSyntheticLambda1(11);
        oplusNotificationDependencyEx$$ExternalSyntheticLambda27.f$0 = this;
        VarHandle.storeStoreFence();
        arrayMap.put(NotificationCenterManagerEx.class, oplusNotificationDependencyEx$$ExternalSyntheticLambda27);
        OplusNotificationDependencyEx$$ExternalSyntheticLambda1 oplusNotificationDependencyEx$$ExternalSyntheticLambda28 = new OplusNotificationDependencyEx$$ExternalSyntheticLambda1(13);
        oplusNotificationDependencyEx$$ExternalSyntheticLambda28.f$0 = this;
        VarHandle.storeStoreFence();
        arrayMap.put(NotificationChannelsEx.class, oplusNotificationDependencyEx$$ExternalSyntheticLambda28);
        OplusNotificationDependencyEx$$ExternalSyntheticLambda1 oplusNotificationDependencyEx$$ExternalSyntheticLambda29 = new OplusNotificationDependencyEx$$ExternalSyntheticLambda1(14);
        oplusNotificationDependencyEx$$ExternalSyntheticLambda29.f$0 = this;
        VarHandle.storeStoreFence();
        arrayMap.put(NotificationKeyguardHelper.class, oplusNotificationDependencyEx$$ExternalSyntheticLambda29);
        OplusNotificationDependencyEx$$ExternalSyntheticLambda1 oplusNotificationDependencyEx$$ExternalSyntheticLambda30 = new OplusNotificationDependencyEx$$ExternalSyntheticLambda1(15);
        oplusNotificationDependencyEx$$ExternalSyntheticLambda30.f$0 = this;
        VarHandle.storeStoreFence();
        arrayMap.put(PrivacyHelper.class, oplusNotificationDependencyEx$$ExternalSyntheticLambda30);
        OplusNotificationDependencyEx$$ExternalSyntheticLambda1 oplusNotificationDependencyEx$$ExternalSyntheticLambda31 = new OplusNotificationDependencyEx$$ExternalSyntheticLambda1(16);
        oplusNotificationDependencyEx$$ExternalSyntheticLambda31.f$0 = this;
        VarHandle.storeStoreFence();
        arrayMap.put(AonAuthManager.class, oplusNotificationDependencyEx$$ExternalSyntheticLambda31);
        arrayMap.put(NotificationBackgroundViewExt.class, new OplusDependencyImpl.LazyDependencyCreator() { // from class: com.oplus.systemui.statusbar.OplusNotificationDependencyEx$$ExternalSyntheticLambda0
            public final Object createDependency(Context context) {
                switch (i4) {
                    case 0:
                        return new ScrimViewExImp(context);
                    case 1:
                        return new ScrimStateExImp(context);
                    case 2:
                        return new NotificationBackgroundViewExtImp(context);
                    case 3:
                        return new OplusNodeSpecBuilderExImpl(context);
                    case 4:
                        return new LogWindowUtils(context);
                    case 5:
                        return new NotificationContentViewExtImp(context);
                    case 6:
                        return new RemoteInputViewExtImpl(context);
                    case IcdfNotificationManagerOuterClass$IcdfNotification.EXPANDEDCHILD_FIELD_NUMBER /* 7 */:
                        return new OplusMediaViewHolderExt(context);
                    case 8:
                        return new RemoteInputQuickSettingsDisablerExImpl(context);
                    default:
                        return new NotificationMenuRowExtImpl(context);
                }
            }
        });
        arrayMap.put(OplusNodeSpecBuilderEx.class, new OplusDependencyImpl.LazyDependencyCreator() { // from class: com.oplus.systemui.statusbar.OplusNotificationDependencyEx$$ExternalSyntheticLambda0
            public final Object createDependency(Context context) {
                switch (i5) {
                    case 0:
                        return new ScrimViewExImp(context);
                    case 1:
                        return new ScrimStateExImp(context);
                    case 2:
                        return new NotificationBackgroundViewExtImp(context);
                    case 3:
                        return new OplusNodeSpecBuilderExImpl(context);
                    case 4:
                        return new LogWindowUtils(context);
                    case 5:
                        return new NotificationContentViewExtImp(context);
                    case 6:
                        return new RemoteInputViewExtImpl(context);
                    case IcdfNotificationManagerOuterClass$IcdfNotification.EXPANDEDCHILD_FIELD_NUMBER /* 7 */:
                        return new OplusMediaViewHolderExt(context);
                    case 8:
                        return new RemoteInputQuickSettingsDisablerExImpl(context);
                    default:
                        return new NotificationMenuRowExtImpl(context);
                }
            }
        });
        arrayMap.put(LogWindowBase.class, new OplusDependencyImpl.LazyDependencyCreator() { // from class: com.oplus.systemui.statusbar.OplusNotificationDependencyEx$$ExternalSyntheticLambda0
            public final Object createDependency(Context context) {
                switch (i6) {
                    case 0:
                        return new ScrimViewExImp(context);
                    case 1:
                        return new ScrimStateExImp(context);
                    case 2:
                        return new NotificationBackgroundViewExtImp(context);
                    case 3:
                        return new OplusNodeSpecBuilderExImpl(context);
                    case 4:
                        return new LogWindowUtils(context);
                    case 5:
                        return new NotificationContentViewExtImp(context);
                    case 6:
                        return new RemoteInputViewExtImpl(context);
                    case IcdfNotificationManagerOuterClass$IcdfNotification.EXPANDEDCHILD_FIELD_NUMBER /* 7 */:
                        return new OplusMediaViewHolderExt(context);
                    case 8:
                        return new RemoteInputQuickSettingsDisablerExImpl(context);
                    default:
                        return new NotificationMenuRowExtImpl(context);
                }
            }
        });
        arrayMap.put(NotificationContentViewExt.class, new OplusDependencyImpl.LazyDependencyCreator() { // from class: com.oplus.systemui.statusbar.OplusNotificationDependencyEx$$ExternalSyntheticLambda0
            public final Object createDependency(Context context) {
                switch (i7) {
                    case 0:
                        return new ScrimViewExImp(context);
                    case 1:
                        return new ScrimStateExImp(context);
                    case 2:
                        return new NotificationBackgroundViewExtImp(context);
                    case 3:
                        return new OplusNodeSpecBuilderExImpl(context);
                    case 4:
                        return new LogWindowUtils(context);
                    case 5:
                        return new NotificationContentViewExtImp(context);
                    case 6:
                        return new RemoteInputViewExtImpl(context);
                    case IcdfNotificationManagerOuterClass$IcdfNotification.EXPANDEDCHILD_FIELD_NUMBER /* 7 */:
                        return new OplusMediaViewHolderExt(context);
                    case 8:
                        return new RemoteInputQuickSettingsDisablerExImpl(context);
                    default:
                        return new NotificationMenuRowExtImpl(context);
                }
            }
        });
        OplusNotificationDependencyEx$$ExternalSyntheticLambda1 oplusNotificationDependencyEx$$ExternalSyntheticLambda32 = new OplusNotificationDependencyEx$$ExternalSyntheticLambda1(18);
        oplusNotificationDependencyEx$$ExternalSyntheticLambda32.f$0 = this;
        VarHandle.storeStoreFence();
        arrayMap.put(OplusNotificationRowUi.class, oplusNotificationDependencyEx$$ExternalSyntheticLambda32);
        OplusNotificationDependencyEx$$ExternalSyntheticLambda1 oplusNotificationDependencyEx$$ExternalSyntheticLambda33 = new OplusNotificationDependencyEx$$ExternalSyntheticLambda1(19);
        oplusNotificationDependencyEx$$ExternalSyntheticLambda33.f$0 = this;
        VarHandle.storeStoreFence();
        arrayMap.put(LockScreenNotificationDispatcher.class, oplusNotificationDependencyEx$$ExternalSyntheticLambda33);
        OplusNotificationDependencyEx$$ExternalSyntheticLambda1 oplusNotificationDependencyEx$$ExternalSyntheticLambda34 = new OplusNotificationDependencyEx$$ExternalSyntheticLambda1(21);
        oplusNotificationDependencyEx$$ExternalSyntheticLambda34.f$0 = this;
        VarHandle.storeStoreFence();
        arrayMap.put(NotificationChildrenContainerExt.class, oplusNotificationDependencyEx$$ExternalSyntheticLambda34);
        OplusNotificationDependencyEx$$ExternalSyntheticLambda1 oplusNotificationDependencyEx$$ExternalSyntheticLambda35 = new OplusNotificationDependencyEx$$ExternalSyntheticLambda1(22);
        oplusNotificationDependencyEx$$ExternalSyntheticLambda35.f$0 = this;
        VarHandle.storeStoreFence();
        arrayMap.put(NotificationGroupingUtilEx.class, oplusNotificationDependencyEx$$ExternalSyntheticLambda35);
        OplusNotificationDependencyEx$$ExternalSyntheticLambda1 oplusNotificationDependencyEx$$ExternalSyntheticLambda36 = new OplusNotificationDependencyEx$$ExternalSyntheticLambda1(23);
        oplusNotificationDependencyEx$$ExternalSyntheticLambda36.f$0 = this;
        VarHandle.storeStoreFence();
        arrayMap.put(SectionHeaderViewEx.class, oplusNotificationDependencyEx$$ExternalSyntheticLambda36);
        OplusNotificationDependencyEx$$ExternalSyntheticLambda1 oplusNotificationDependencyEx$$ExternalSyntheticLambda37 = new OplusNotificationDependencyEx$$ExternalSyntheticLambda1(24);
        oplusNotificationDependencyEx$$ExternalSyntheticLambda37.f$0 = this;
        VarHandle.storeStoreFence();
        arrayMap.put(EmptyShadeViewEx.class, oplusNotificationDependencyEx$$ExternalSyntheticLambda37);
        OplusNotificationDependencyEx$$ExternalSyntheticLambda1 oplusNotificationDependencyEx$$ExternalSyntheticLambda38 = new OplusNotificationDependencyEx$$ExternalSyntheticLambda1(25);
        oplusNotificationDependencyEx$$ExternalSyntheticLambda38.f$0 = this;
        VarHandle.storeStoreFence();
        arrayMap.put(OplusNotificationContentInflaterEx.class, oplusNotificationDependencyEx$$ExternalSyntheticLambda38);
        OplusNotificationDependencyEx$$ExternalSyntheticLambda1 oplusNotificationDependencyEx$$ExternalSyntheticLambda39 = new OplusNotificationDependencyEx$$ExternalSyntheticLambda1(26);
        oplusNotificationDependencyEx$$ExternalSyntheticLambda39.f$0 = this;
        VarHandle.storeStoreFence();
        arrayMap.put(OplusStatusBarIconViewEx.class, oplusNotificationDependencyEx$$ExternalSyntheticLambda39);
        OplusNotificationDependencyEx$$ExternalSyntheticLambda1 oplusNotificationDependencyEx$$ExternalSyntheticLambda40 = new OplusNotificationDependencyEx$$ExternalSyntheticLambda1(28);
        oplusNotificationDependencyEx$$ExternalSyntheticLambda40.f$0 = this;
        VarHandle.storeStoreFence();
        arrayMap.put(NotificationSwipeHelperEx.class, oplusNotificationDependencyEx$$ExternalSyntheticLambda40);
        OplusNotificationDependencyEx$$ExternalSyntheticLambda1 oplusNotificationDependencyEx$$ExternalSyntheticLambda41 = new OplusNotificationDependencyEx$$ExternalSyntheticLambda1(29);
        oplusNotificationDependencyEx$$ExternalSyntheticLambda41.f$0 = this;
        VarHandle.storeStoreFence();
        arrayMap.put(NotificationClickEffectController.class, oplusNotificationDependencyEx$$ExternalSyntheticLambda41);
        arrayMap.put(RemoteInputViewExt.class, new OplusDependencyImpl.LazyDependencyCreator() { // from class: com.oplus.systemui.statusbar.OplusNotificationDependencyEx$$ExternalSyntheticLambda0
            public final Object createDependency(Context context) {
                switch (i8) {
                    case 0:
                        return new ScrimViewExImp(context);
                    case 1:
                        return new ScrimStateExImp(context);
                    case 2:
                        return new NotificationBackgroundViewExtImp(context);
                    case 3:
                        return new OplusNodeSpecBuilderExImpl(context);
                    case 4:
                        return new LogWindowUtils(context);
                    case 5:
                        return new NotificationContentViewExtImp(context);
                    case 6:
                        return new RemoteInputViewExtImpl(context);
                    case IcdfNotificationManagerOuterClass$IcdfNotification.EXPANDEDCHILD_FIELD_NUMBER /* 7 */:
                        return new OplusMediaViewHolderExt(context);
                    case 8:
                        return new RemoteInputQuickSettingsDisablerExImpl(context);
                    default:
                        return new NotificationMenuRowExtImpl(context);
                }
            }
        });
        arrayMap.put(MediaViewHolderExt.class, new OplusDependencyImpl.LazyDependencyCreator() { // from class: com.oplus.systemui.statusbar.OplusNotificationDependencyEx$$ExternalSyntheticLambda0
            public final Object createDependency(Context context) {
                switch (i9) {
                    case 0:
                        return new ScrimViewExImp(context);
                    case 1:
                        return new ScrimStateExImp(context);
                    case 2:
                        return new NotificationBackgroundViewExtImp(context);
                    case 3:
                        return new OplusNodeSpecBuilderExImpl(context);
                    case 4:
                        return new LogWindowUtils(context);
                    case 5:
                        return new NotificationContentViewExtImp(context);
                    case 6:
                        return new RemoteInputViewExtImpl(context);
                    case IcdfNotificationManagerOuterClass$IcdfNotification.EXPANDEDCHILD_FIELD_NUMBER /* 7 */:
                        return new OplusMediaViewHolderExt(context);
                    case 8:
                        return new RemoteInputQuickSettingsDisablerExImpl(context);
                    default:
                        return new NotificationMenuRowExtImpl(context);
                }
            }
        });
        OplusNotificationDependencyEx$$ExternalSyntheticLambda4 oplusNotificationDependencyEx$$ExternalSyntheticLambda42 = new OplusNotificationDependencyEx$$ExternalSyntheticLambda4(2);
        oplusNotificationDependencyEx$$ExternalSyntheticLambda42.f$0 = this;
        VarHandle.storeStoreFence();
        arrayMap.put(OplusStackScrollAlgorithmEx.class, oplusNotificationDependencyEx$$ExternalSyntheticLambda42);
        OplusNotificationDependencyEx$$ExternalSyntheticLambda4 oplusNotificationDependencyEx$$ExternalSyntheticLambda43 = new OplusNotificationDependencyEx$$ExternalSyntheticLambda4(3);
        oplusNotificationDependencyEx$$ExternalSyntheticLambda43.f$0 = this;
        VarHandle.storeStoreFence();
        arrayMap.put(OplusStackStateAnimatorEx.class, oplusNotificationDependencyEx$$ExternalSyntheticLambda43);
        OplusNotificationDependencyEx$$ExternalSyntheticLambda4 oplusNotificationDependencyEx$$ExternalSyntheticLambda44 = new OplusNotificationDependencyEx$$ExternalSyntheticLambda4(4);
        oplusNotificationDependencyEx$$ExternalSyntheticLambda44.f$0 = this;
        VarHandle.storeStoreFence();
        arrayMap.put(OplusExpandHelperEx.class, oplusNotificationDependencyEx$$ExternalSyntheticLambda44);
        OplusNotificationDependencyEx$$ExternalSyntheticLambda4 oplusNotificationDependencyEx$$ExternalSyntheticLambda45 = new OplusNotificationDependencyEx$$ExternalSyntheticLambda4(5);
        oplusNotificationDependencyEx$$ExternalSyntheticLambda45.f$0 = this;
        VarHandle.storeStoreFence();
        arrayMap.put(OplusCustomRowControllerManager.class, oplusNotificationDependencyEx$$ExternalSyntheticLambda45);
        OplusNotificationDependencyEx$$ExternalSyntheticLambda4 oplusNotificationDependencyEx$$ExternalSyntheticLambda46 = new OplusNotificationDependencyEx$$ExternalSyntheticLambda4(6);
        oplusNotificationDependencyEx$$ExternalSyntheticLambda46.f$0 = this;
        VarHandle.storeStoreFence();
        arrayMap.put(UsbResolverActivityEx.class, oplusNotificationDependencyEx$$ExternalSyntheticLambda46);
        OplusNotificationDependencyEx$$ExternalSyntheticLambda4 oplusNotificationDependencyEx$$ExternalSyntheticLambda47 = new OplusNotificationDependencyEx$$ExternalSyntheticLambda4(7);
        oplusNotificationDependencyEx$$ExternalSyntheticLambda47.f$0 = this;
        VarHandle.storeStoreFence();
        arrayMap.put(UsbConfirmActivityEx.class, oplusNotificationDependencyEx$$ExternalSyntheticLambda47);
        OplusNotificationDependencyEx$$ExternalSyntheticLambda4 oplusNotificationDependencyEx$$ExternalSyntheticLambda48 = new OplusNotificationDependencyEx$$ExternalSyntheticLambda4(8);
        oplusNotificationDependencyEx$$ExternalSyntheticLambda48.f$0 = this;
        VarHandle.storeStoreFence();
        arrayMap.put(RowAppearanceCoordinatorEx.class, oplusNotificationDependencyEx$$ExternalSyntheticLambda48);
        OplusNotificationDependencyEx$$ExternalSyntheticLambda4 oplusNotificationDependencyEx$$ExternalSyntheticLambda49 = new OplusNotificationDependencyEx$$ExternalSyntheticLambda4(9);
        oplusNotificationDependencyEx$$ExternalSyntheticLambda49.f$0 = this;
        VarHandle.storeStoreFence();
        arrayMap.put(ShadeViewDifferEx.class, oplusNotificationDependencyEx$$ExternalSyntheticLambda49);
        OplusNotificationDependencyEx$$ExternalSyntheticLambda4 oplusNotificationDependencyEx$$ExternalSyntheticLambda50 = new OplusNotificationDependencyEx$$ExternalSyntheticLambda4(10);
        oplusNotificationDependencyEx$$ExternalSyntheticLambda50.f$0 = this;
        VarHandle.storeStoreFence();
        arrayMap.put(VisualStabilityCoordinatorEx.class, oplusNotificationDependencyEx$$ExternalSyntheticLambda50);
        OplusNotificationDependencyEx$$ExternalSyntheticLambda4 oplusNotificationDependencyEx$$ExternalSyntheticLambda51 = new OplusNotificationDependencyEx$$ExternalSyntheticLambda4(11);
        oplusNotificationDependencyEx$$ExternalSyntheticLambda51.f$0 = this;
        VarHandle.storeStoreFence();
        arrayMap.put(NotificationPlayerEx.class, oplusNotificationDependencyEx$$ExternalSyntheticLambda51);
        OplusNotificationDependencyEx$$ExternalSyntheticLambda4 oplusNotificationDependencyEx$$ExternalSyntheticLambda52 = new OplusNotificationDependencyEx$$ExternalSyntheticLambda4(13);
        oplusNotificationDependencyEx$$ExternalSyntheticLambda52.f$0 = this;
        VarHandle.storeStoreFence();
        arrayMap.put(NotificationResolverEx.class, oplusNotificationDependencyEx$$ExternalSyntheticLambda52);
        arrayMap.put(RemoteInputQuickSettingsDisablerEx.class, new OplusDependencyImpl.LazyDependencyCreator() { // from class: com.oplus.systemui.statusbar.OplusNotificationDependencyEx$$ExternalSyntheticLambda0
            public final Object createDependency(Context context) {
                switch (i10) {
                    case 0:
                        return new ScrimViewExImp(context);
                    case 1:
                        return new ScrimStateExImp(context);
                    case 2:
                        return new NotificationBackgroundViewExtImp(context);
                    case 3:
                        return new OplusNodeSpecBuilderExImpl(context);
                    case 4:
                        return new LogWindowUtils(context);
                    case 5:
                        return new NotificationContentViewExtImp(context);
                    case 6:
                        return new RemoteInputViewExtImpl(context);
                    case IcdfNotificationManagerOuterClass$IcdfNotification.EXPANDEDCHILD_FIELD_NUMBER /* 7 */:
                        return new OplusMediaViewHolderExt(context);
                    case 8:
                        return new RemoteInputQuickSettingsDisablerExImpl(context);
                    default:
                        return new NotificationMenuRowExtImpl(context);
                }
            }
        });
        OplusNotificationDependencyEx$$ExternalSyntheticLambda4 oplusNotificationDependencyEx$$ExternalSyntheticLambda53 = new OplusNotificationDependencyEx$$ExternalSyntheticLambda4(14);
        oplusNotificationDependencyEx$$ExternalSyntheticLambda53.f$0 = this;
        VarHandle.storeStoreFence();
        arrayMap.put(NotificationApplyDelayHelper.class, oplusNotificationDependencyEx$$ExternalSyntheticLambda53);
        OplusNotificationDependencyEx$$ExternalSyntheticLambda4 oplusNotificationDependencyEx$$ExternalSyntheticLambda54 = new OplusNotificationDependencyEx$$ExternalSyntheticLambda4(15);
        oplusNotificationDependencyEx$$ExternalSyntheticLambda54.f$0 = this;
        VarHandle.storeStoreFence();
        arrayMap.put(NotificationHeadsUpAnimationEx.class, oplusNotificationDependencyEx$$ExternalSyntheticLambda54);
        OplusNotificationDependencyEx$$ExternalSyntheticLambda4 oplusNotificationDependencyEx$$ExternalSyntheticLambda55 = new OplusNotificationDependencyEx$$ExternalSyntheticLambda4(17);
        oplusNotificationDependencyEx$$ExternalSyntheticLambda55.f$0 = this;
        VarHandle.storeStoreFence();
        arrayMap.put(NotificationDependOnCommon.class, oplusNotificationDependencyEx$$ExternalSyntheticLambda55);
        OplusNotificationDependencyEx$$ExternalSyntheticLambda4 oplusNotificationDependencyEx$$ExternalSyntheticLambda56 = new OplusNotificationDependencyEx$$ExternalSyntheticLambda4(18);
        oplusNotificationDependencyEx$$ExternalSyntheticLambda56.f$0 = this;
        VarHandle.storeStoreFence();
        arrayMap.put(OplusAmbientStateEx.class, oplusNotificationDependencyEx$$ExternalSyntheticLambda56);
        OplusNotificationDependencyEx$$ExternalSyntheticLambda4 oplusNotificationDependencyEx$$ExternalSyntheticLambda57 = new OplusNotificationDependencyEx$$ExternalSyntheticLambda4(19);
        oplusNotificationDependencyEx$$ExternalSyntheticLambda57.f$0 = this;
        VarHandle.storeStoreFence();
        arrayMap.put(ShadeListBuilder.class, oplusNotificationDependencyEx$$ExternalSyntheticLambda57);
        OplusNotificationDependencyEx$$ExternalSyntheticLambda4 oplusNotificationDependencyEx$$ExternalSyntheticLambda58 = new OplusNotificationDependencyEx$$ExternalSyntheticLambda4(20);
        oplusNotificationDependencyEx$$ExternalSyntheticLambda58.f$0 = this;
        VarHandle.storeStoreFence();
        arrayMap.put(StatusBarRemoteInputCallbackEx.class, oplusNotificationDependencyEx$$ExternalSyntheticLambda58);
        OplusNotificationDependencyEx$$ExternalSyntheticLambda4 oplusNotificationDependencyEx$$ExternalSyntheticLambda59 = new OplusNotificationDependencyEx$$ExternalSyntheticLambda4(21);
        oplusNotificationDependencyEx$$ExternalSyntheticLambda59.f$0 = this;
        VarHandle.storeStoreFence();
        arrayMap.put(LevelPositionAlgorithm.class, oplusNotificationDependencyEx$$ExternalSyntheticLambda59);
        OplusNotificationDependencyEx$$ExternalSyntheticLambda4 oplusNotificationDependencyEx$$ExternalSyntheticLambda60 = new OplusNotificationDependencyEx$$ExternalSyntheticLambda4(22);
        oplusNotificationDependencyEx$$ExternalSyntheticLambda60.f$0 = this;
        VarHandle.storeStoreFence();
        arrayMap.put(NotificationRowShowAnimationEx.class, oplusNotificationDependencyEx$$ExternalSyntheticLambda60);
        OplusNotificationDependencyEx$$ExternalSyntheticLambda4 oplusNotificationDependencyEx$$ExternalSyntheticLambda61 = new OplusNotificationDependencyEx$$ExternalSyntheticLambda4(24);
        oplusNotificationDependencyEx$$ExternalSyntheticLambda61.f$0 = this;
        VarHandle.storeStoreFence();
        arrayMap.put(SysuiStatusBarStateController.class, oplusNotificationDependencyEx$$ExternalSyntheticLambda61);
        OplusNotificationDependencyEx$$ExternalSyntheticLambda4 oplusNotificationDependencyEx$$ExternalSyntheticLambda62 = new OplusNotificationDependencyEx$$ExternalSyntheticLambda4(25);
        oplusNotificationDependencyEx$$ExternalSyntheticLambda62.f$0 = this;
        VarHandle.storeStoreFence();
        arrayMap.put(NotificationMaterialColorModeController.class, oplusNotificationDependencyEx$$ExternalSyntheticLambda62);
        OplusNotificationDependencyEx$$ExternalSyntheticLambda4 oplusNotificationDependencyEx$$ExternalSyntheticLambda63 = new OplusNotificationDependencyEx$$ExternalSyntheticLambda4(26);
        oplusNotificationDependencyEx$$ExternalSyntheticLambda63.f$0 = this;
        VarHandle.storeStoreFence();
        arrayMap.put(OplusSimpleQSManager.class, oplusNotificationDependencyEx$$ExternalSyntheticLambda63);
        OplusNotificationDependencyEx$$ExternalSyntheticLambda4 oplusNotificationDependencyEx$$ExternalSyntheticLambda64 = new OplusNotificationDependencyEx$$ExternalSyntheticLambda4(28);
        oplusNotificationDependencyEx$$ExternalSyntheticLambda64.f$0 = this;
        VarHandle.storeStoreFence();
        arrayMap.put(ViewBlurManager.class, oplusNotificationDependencyEx$$ExternalSyntheticLambda64);
        OplusNotificationDependencyEx$$ExternalSyntheticLambda4 oplusNotificationDependencyEx$$ExternalSyntheticLambda65 = new OplusNotificationDependencyEx$$ExternalSyntheticLambda4(29);
        oplusNotificationDependencyEx$$ExternalSyntheticLambda65.f$0 = this;
        VarHandle.storeStoreFence();
        arrayMap.put(MaterialColorManager.class, oplusNotificationDependencyEx$$ExternalSyntheticLambda65);
        OplusNotificationDependencyEx$$ExternalSyntheticLambda7 oplusNotificationDependencyEx$$ExternalSyntheticLambda66 = new OplusNotificationDependencyEx$$ExternalSyntheticLambda7(1);
        oplusNotificationDependencyEx$$ExternalSyntheticLambda66.f$0 = this;
        VarHandle.storeStoreFence();
        arrayMap.put(ImmersiveCardControl.class, oplusNotificationDependencyEx$$ExternalSyntheticLambda66);
        OplusNotificationDependencyEx$$ExternalSyntheticLambda7 oplusNotificationDependencyEx$$ExternalSyntheticLambda67 = new OplusNotificationDependencyEx$$ExternalSyntheticLambda7(2);
        oplusNotificationDependencyEx$$ExternalSyntheticLambda67.f$0 = this;
        VarHandle.storeStoreFence();
        arrayMap.put(OplusPanoramicAodRepository.class, oplusNotificationDependencyEx$$ExternalSyntheticLambda67);
        OplusNotificationDependencyEx$$ExternalSyntheticLambda7 oplusNotificationDependencyEx$$ExternalSyntheticLambda68 = new OplusNotificationDependencyEx$$ExternalSyntheticLambda7(3);
        oplusNotificationDependencyEx$$ExternalSyntheticLambda68.f$0 = this;
        VarHandle.storeStoreFence();
        arrayMap.put(RowAppearAnimationEx.class, oplusNotificationDependencyEx$$ExternalSyntheticLambda68);
        OplusNotificationDependencyEx$$ExternalSyntheticLambda7 oplusNotificationDependencyEx$$ExternalSyntheticLambda69 = new OplusNotificationDependencyEx$$ExternalSyntheticLambda7(4);
        oplusNotificationDependencyEx$$ExternalSyntheticLambda69.f$0 = this;
        VarHandle.storeStoreFence();
        arrayMap.put(BoostFrameRateManager.class, oplusNotificationDependencyEx$$ExternalSyntheticLambda69);
        OplusNotificationDependencyEx$$ExternalSyntheticLambda7 oplusNotificationDependencyEx$$ExternalSyntheticLambda70 = new OplusNotificationDependencyEx$$ExternalSyntheticLambda7(5);
        oplusNotificationDependencyEx$$ExternalSyntheticLambda70.f$0 = this;
        VarHandle.storeStoreFence();
        arrayMap.put(OplusLockscreenShadeTransitionControllerEx.class, oplusNotificationDependencyEx$$ExternalSyntheticLambda70);
        OplusNotificationDependencyEx$$ExternalSyntheticLambda7 oplusNotificationDependencyEx$$ExternalSyntheticLambda71 = new OplusNotificationDependencyEx$$ExternalSyntheticLambda7(6);
        oplusNotificationDependencyEx$$ExternalSyntheticLambda71.f$0 = this;
        VarHandle.storeStoreFence();
        arrayMap.put(OplusCloseAllController.class, oplusNotificationDependencyEx$$ExternalSyntheticLambda71);
        OplusNotificationDependencyEx$$ExternalSyntheticLambda7 oplusNotificationDependencyEx$$ExternalSyntheticLambda72 = new OplusNotificationDependencyEx$$ExternalSyntheticLambda7(7);
        oplusNotificationDependencyEx$$ExternalSyntheticLambda72.f$0 = this;
        VarHandle.storeStoreFence();
        arrayMap.put(NotifInflateDelayStrategy.class, oplusNotificationDependencyEx$$ExternalSyntheticLambda72);
        OplusNotificationDependencyEx$$ExternalSyntheticLambda7 oplusNotificationDependencyEx$$ExternalSyntheticLambda73 = new OplusNotificationDependencyEx$$ExternalSyntheticLambda7(8);
        oplusNotificationDependencyEx$$ExternalSyntheticLambda73.f$0 = this;
        VarHandle.storeStoreFence();
        arrayMap.put(NotificationRemoteInputManagerEx.class, oplusNotificationDependencyEx$$ExternalSyntheticLambda73);
        OplusNotificationDependencyEx$$ExternalSyntheticLambda7 oplusNotificationDependencyEx$$ExternalSyntheticLambda74 = new OplusNotificationDependencyEx$$ExternalSyntheticLambda7(9);
        oplusNotificationDependencyEx$$ExternalSyntheticLambda74.f$0 = this;
        VarHandle.storeStoreFence();
        arrayMap.put(KeyFlashlightController.class, oplusNotificationDependencyEx$$ExternalSyntheticLambda74);
        OplusNotificationDependencyEx$$ExternalSyntheticLambda7 oplusNotificationDependencyEx$$ExternalSyntheticLambda75 = new OplusNotificationDependencyEx$$ExternalSyntheticLambda7(10);
        oplusNotificationDependencyEx$$ExternalSyntheticLambda75.f$0 = this;
        VarHandle.storeStoreFence();
        arrayMap.put(KeyDndController.class, oplusNotificationDependencyEx$$ExternalSyntheticLambda75);
        OplusNotificationDependencyEx$$ExternalSyntheticLambda7 oplusNotificationDependencyEx$$ExternalSyntheticLambda76 = new OplusNotificationDependencyEx$$ExternalSyntheticLambda7(11);
        oplusNotificationDependencyEx$$ExternalSyntheticLambda76.f$0 = this;
        VarHandle.storeStoreFence();
        arrayMap.put(KeyBaseController.class, oplusNotificationDependencyEx$$ExternalSyntheticLambda76);
        FlavorOneFeatureOption.INSTANCE.getClass();
        if (FlavorOneFeatureOption.isSupportMiniCapsule()) {
            OplusNotificationDependencyEx$$ExternalSyntheticLambda7 oplusNotificationDependencyEx$$ExternalSyntheticLambda77 = new OplusNotificationDependencyEx$$ExternalSyntheticLambda7(12);
            oplusNotificationDependencyEx$$ExternalSyntheticLambda77.f$0 = this;
            VarHandle.storeStoreFence();
            arrayMap.put(FlavorOneCapsuleController.class, oplusNotificationDependencyEx$$ExternalSyntheticLambda77);
        }
        OplusNotificationDependencyEx$$ExternalSyntheticLambda7 oplusNotificationDependencyEx$$ExternalSyntheticLambda78 = new OplusNotificationDependencyEx$$ExternalSyntheticLambda7(13);
        oplusNotificationDependencyEx$$ExternalSyntheticLambda78.f$0 = this;
        VarHandle.storeStoreFence();
        arrayMap.put(OplusNotifRemoteViewsFactoryContainerEx.class, oplusNotificationDependencyEx$$ExternalSyntheticLambda78);
        OplusNotificationDependencyEx$$ExternalSyntheticLambda7 oplusNotificationDependencyEx$$ExternalSyntheticLambda79 = new OplusNotificationDependencyEx$$ExternalSyntheticLambda7(14);
        oplusNotificationDependencyEx$$ExternalSyntheticLambda79.f$0 = this;
        VarHandle.storeStoreFence();
        arrayMap.put(NotificationGroupSplitEx.class, oplusNotificationDependencyEx$$ExternalSyntheticLambda79);
        OplusNotificationDependencyEx$$ExternalSyntheticLambda7 oplusNotificationDependencyEx$$ExternalSyntheticLambda80 = new OplusNotificationDependencyEx$$ExternalSyntheticLambda7(15);
        oplusNotificationDependencyEx$$ExternalSyntheticLambda80.f$0 = this;
        VarHandle.storeStoreFence();
        arrayMap.put(NotificationChildrenContainerOverlayEx.class, oplusNotificationDependencyEx$$ExternalSyntheticLambda80);
        OplusNotificationDependencyEx$$ExternalSyntheticLambda7 oplusNotificationDependencyEx$$ExternalSyntheticLambda81 = new OplusNotificationDependencyEx$$ExternalSyntheticLambda7(17);
        oplusNotificationDependencyEx$$ExternalSyntheticLambda81.f$0 = this;
        VarHandle.storeStoreFence();
        arrayMap.put(OplusNotificationOverlayInteractor.class, oplusNotificationDependencyEx$$ExternalSyntheticLambda81);
        OplusNotificationDependencyEx$$ExternalSyntheticLambda7 oplusNotificationDependencyEx$$ExternalSyntheticLambda82 = new OplusNotificationDependencyEx$$ExternalSyntheticLambda7(19);
        oplusNotificationDependencyEx$$ExternalSyntheticLambda82.f$0 = this;
        VarHandle.storeStoreFence();
        arrayMap.put(NotificationMaterialColorEx.class, oplusNotificationDependencyEx$$ExternalSyntheticLambda82);
    }
}
