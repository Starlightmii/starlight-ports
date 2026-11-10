package com.oplus.systemui.notification.interruption.fullscreenbanner;

import _COROUTINE.CoroutineDebuggingKt$;
import android.R;
import android.animation.Animator;
import android.animation.AnimatorListenerAdapter;
import android.animation.ObjectAnimator;
import android.animation.ValueAnimator;
import android.app.Notification;
import android.app.PendingIntent;
import android.app.Person;
import android.app.RemoteInput;
import android.content.ContentResolver;
import android.content.Context;
import android.content.Intent;
import android.content.pm.UserInfo;
import android.content.res.Configuration;
import android.content.res.Resources;
import android.database.ContentObserver;
import android.graphics.Path;
import android.graphics.Region;
import android.graphics.drawable.Drawable;
import android.graphics.drawable.Icon;
import android.net.Uri;
import android.os.Bundle;
import android.os.Handler;
import android.os.Looper;
import android.os.Message;
import android.os.Parcelable;
import android.os.UserManager;
import android.provider.Settings;
import android.service.notification.StatusBarNotification;
import android.text.TextUtils;
import android.util.Log;
import android.view.VelocityTracker;
import android.view.View;
import android.view.ViewTreeObserver;
import android.view.WindowManager;
import android.view.animation.Interpolator;
import android.view.animation.PathInterpolator;
import android.view.inputmethod.InputMethodManager;
import android.widget.FrameLayout;
import android.widget.LinearLayout;
import androidx.slice.widget.RowView$;
import com.android.internal.widget.PeopleHelper;
import com.android.keyguard.logging.KeyguardLogger$;
import com.android.systemui.DependencyEx;
import com.android.systemui.animation.ActivityTransitionAnimator;
import com.android.systemui.shade.OplusNotificationShadeWindowControllerEx;
import com.android.systemui.shade.ShadeController;
import com.android.systemui.shade.ShadeSurface;
import com.android.systemui.shade.domain.interactor.ShadeAnimationInteractor;
import com.android.systemui.shared.system.ActivityManagerWrapper;
import com.android.systemui.statusbar.CommandQueue;
import com.android.systemui.statusbar.NotificationLockscreenUserManager;
import com.android.systemui.statusbar.NotificationShadeWindowController;
import com.android.systemui.statusbar.notification.collection.NotificationEntry;
import com.android.systemui.statusbar.notification.headsup.HeadsUpManager;
import com.android.systemui.statusbar.notification.helper.PrivacyHelper;
import com.android.systemui.statusbar.notification.stack.NotificationStackScrollLayoutController;
import com.android.systemui.statusbar.notification.stack.OplusAmbientStateEx;
import com.android.systemui.statusbar.phone.StatusBarTransitionAnimatorController;
import com.android.systemui.statusbar.policy.ConfigurationController;
import com.android.systemui.statusbar.window.StatusBarWindowController;
import com.android.systemui.statusbar.window.StatusBarWindowControllerStore;
import com.android.systemui.statusbar.window.StatusBarWindowStateListener;
import com.android.systemui.util.HeadsUpToZoomUtils;
import com.android.systemui.util.LargeScreenUtils;
import com.oplus.compatui.OplusCompatDcsUtils$;
import com.oplus.systemui.aod.utils.AodUtils;
import com.oplus.systemui.common.receiver.ScreenStateReceiver;
import com.oplus.systemui.keyguard.smartlock.OplusSmartlockController$mSmartLockCallback$1$;
import com.oplus.systemui.notification.NotificationIconBadgePackages;
import com.oplus.systemui.notification.aonaccess.AonAuthStarter;
import com.oplus.systemui.notification.base.radius.NotificationPath;
import com.oplus.systemui.notification.blur.ViewBlurManager;
import com.oplus.systemui.notification.headsup.windowframe.HeadsUpCOUItouchMotionAnimator;
import com.oplus.systemui.notification.headsup.windowframe.HeadsUpCOUItouchMotionAnimator$couiMotionForwarder$1;
import com.oplus.systemui.notification.interruption.fullscreenbanner.view.FullScreenBackgroundView;
import com.oplus.systemui.notification.interruption.fullscreenbanner.view.FullScreenBanner;
import com.oplus.systemui.notification.interruption.fullscreenbanner.view.FullScreenBannerAnimatorController;
import com.oplus.systemui.notification.interruption.fullscreenbanner.view.FullScreenBannerContainer;
import com.oplus.systemui.notification.interruption.fullscreenbanner.view.FullScreenBannerTouchHandler;
import com.oplus.systemui.notification.material.HeadsUpShadowEdgeController;
import com.oplus.systemui.notification.material.NotificationSpotLightDelegate;
import com.oplus.systemui.notification.material.NotificationSpotLightParamsKind;
import com.oplus.systemui.notification.row.material.NotificationBlurMode;
import com.oplus.systemui.notification.row.material.NotificationMaterialColorEx;
import com.oplus.systemui.notification.utlils.DrawableWithBadge;
import com.oplus.systemui.notification.utlils.NotificationZoomStartUtil;
import com.oplus.systemui.plugins.qs.customize.view.animation.drag.animator.MultiAnimViewDelegate;
import com.oplus.systemui.shade.ShadeExpansionHelperImpl;
import com.oplus.systemui.statusbar.notification.collection.OplusNotificationEntryExImpl;
import com.oplus.systemui.statusbar.notification.collection.inflation.NotifInflateDelayStrategyImpl;
import com.oplus.systemui.statusbar.notification.dependence.NotificationDependOnCommon;
import com.oplus.systemui.statusbar.notification.dependence.NotificationDependOnKeyguard;
import com.oplus.systemui.statusbar.notification.dependence.NotificationDependOnKeyguardImpl;
import com.oplus.systemui.statusbar.notification.helper.HeadsUpHelper;
import com.oplus.systemui.statusbar.notification.stack.HeadsUpAppearInterpolator;
import com.oplus.systemui.statusbar.notification.stack.OplusAmbientStateExImpl;
import com.oplus.systemui.statusbar.notification.util.OpNotificationUtils;
import com.oplus.systemui.statusbar.notification.util.RoundRectDrawableUtil;
import com.oplus.systemui.statusbar.seeding.SeedlingPluginManager;
import com.oplus.systemui.statusbar.util.NotificationStatisticUtil;
import com.oplus.systemui.util.NotificationUtil;
import com.oplus.zoomwindow.OplusZoomWindowManager;
import com.oplusos.systemui.common.feature.FeatureOption;
import com.oplusos.systemui.common.observer.ObserverDispatcher;
import com.oplusos.systemui.common.receiver.IReceiverListener;
import com.oplusos.systemui.common.util.LogUtil;
import com.oplusos.systemui.common.util.OplusTrackProxyUtil;
import com.oplusos.systemui.common.util.ThreadManager;
import com.oplusos.systemui.common.utils.AppInfoUtil;
import com.oplusos.systemui.common.utils.DeviceUtil;
import com.oplusos.systemui.common.utils.OplusMultiAppUtils;
import com.oplusos.systemui.notification.logger.OplusNotificationLogger;
import dagger.Lazy;
import java.lang.invoke.VarHandle;
import java.util.ArrayList;
import java.util.Collection;
import java.util.Collections;
import java.util.HashMap;
import java.util.LinkedList;
import java.util.List;
import java.util.Map;
import java.util.Set;
import java.util.concurrent.locks.Lock;

/* JADX INFO: compiled from: go/retraceme fd74bf9514dd0739b92d67a6d48a51eb10aaf8df760d83496b8dcfcf83e5f698 */
/* JADX INFO: loaded from: /home/starlight/Projects/Starlight Ports/.build-tmp/classes4.dex */
public class FullScreenBannerHelper implements OplusZoomWindowManager.OnConfigChangedListener {
    public static final Interpolator INTERPOLATOR_BANNER_MOVE = new PathInterpolator(0.3f, 0.0f, 0.1f, 1.0f);
    Lazy mActivityStarterEx;
    Lazy mActivityTransitionAnimator;
    Lazy mAmbientStateEx;
    Lazy mAonAuthStarter;
    public FullScreenBackgroundView mBackgroundNormal;
    public boolean mBannerCollpseAniming;
    public ValueAnimator mBannerExpandAnimator;
    public ValueAnimator mBannerMoveAnimator;
    public String mBannerOperator;
    public int mBannerRealHeight;
    public FrameLayout mBannerRootLayout;
    public final Object mBannerSwitchLock = new Object();
    public final Map mBannerSwitchMap;
    public final Region mBannerTouchRegion;
    public boolean mBannerZoom;
    Lazy mCentralSurfaces;
    Lazy mCommandQueue;
    public final Context mContext;
    public FullScreenBanner mFullScreenBanner;
    Lazy mFullScreenBannerColorController;
    public final BannerDisplayHander mHandler;
    public boolean mHasDialogNotification;
    public boolean mHasTouchableRegionListener;
    HeadsUpHelper mHeadsUpHelper;
    HeadsUpManager mHeadsUpManager;
    public float mIconRadiusRatio;
    public int mIconSize;
    public int mIconZoomSize;
    public boolean mIsBannerShowing;
    public boolean mIsFolded;
    public boolean mIsMoving;
    public boolean mIsStartFloatWindow;
    public NotificationEntry mLastNotificationEntry;
    public int mLastOrientation;
    public int mLastTargetY;
    Lazy mLockscreenUserManager;
    Lazy mNotifInflateDelayStrategy;
    Lazy mNotificationDependOnCommon;
    Lazy mNotificationDependOnKeyguard;
    Lazy mNotificationMaterialColorEx;
    Lazy mNotificationShadeWindowController;
    Lazy mNotificationShadeWindowControllerEx;
    Lazy mNotificationStackScrollLayoutController;
    public final AnonymousClass3 mOnComputeInternalInsetsListener;
    public final AnonymousClass2 mOnStartAnimationCallback;
    Lazy mPanelViewControllerEx;
    public final LinkedList mPendingDialogNotificationEntrys;
    public PendingIntent mPendingIntent;
    public final LinkedList mPendingNotificationEntrys;
    public PeopleHelper mPeopleHelper;
    Lazy mPrivacyHelper;
    public RemoteInput mRemoteInput;
    public RemoteInput[] mRemoteInputs;
    public FullScreenBannerContainer mRootView;
    public final AnonymousClass1 mScreenStateListener;
    SeedlingPluginManager mSeedlingPluginManager;
    Lazy mShadeAnimationInteractor;
    Lazy mShadeController;
    Lazy mShadeSurface;
    public boolean mShouldDoCallBackInMidAnimation;
    public final AnonymousClass4 mSimpleBannerObserver;
    Lazy mStatusBarWindowControllerStore;
    public final AnonymousClass5 mStatusBarWindowStateListener;
    public List mSupportFreeformPkgs;
    public int mTargetY;
    public FullScreenBannerTouchHandler mTouchHandler;
    public HeadsUpCOUItouchMotionAnimator mTouchMotion;
    public final WindowManager mWindowManager;
    public Bundle mZoomBunld;

    /* JADX INFO: renamed from: com.oplus.systemui.notification.interruption.fullscreenbanner.FullScreenBannerHelper$1, reason: invalid class name */
    /* JADX INFO: compiled from: go/retraceme fd74bf9514dd0739b92d67a6d48a51eb10aaf8df760d83496b8dcfcf83e5f698 */
    public final class AnonymousClass1 implements IReceiverListener {
        public /* synthetic */ FullScreenBannerHelper this$0;

        @Override // com.oplusos.systemui.common.receiver.IReceiverListener
        public final void onReceive(Context context, Intent intent) {
            LogUtil.d(LogUtil.TAG_NOTIFICATION, "FullScreenHelper", "FULL_SCREEN_BANNER-->receive action: " + intent.getAction() + " start");
            if ("android.intent.action.SCREEN_OFF".equals(intent.getAction())) {
                FullScreenBannerHelper fullScreenBannerHelper = this.this$0;
                if (fullScreenBannerHelper.mFullScreenBanner != null) {
                    fullScreenBannerHelper.executeBannerCollpseAnim(false, false, new Runnable[0]);
                }
            }
            LogUtil.d(LogUtil.TAG_NOTIFICATION, "FullScreenHelper", "FULL_SCREEN_BANNER-->receive action: " + intent.getAction() + " end");
        }
    }

    /* JADX INFO: renamed from: com.oplus.systemui.notification.interruption.fullscreenbanner.FullScreenBannerHelper$11, reason: invalid class name */
    /* JADX INFO: compiled from: go/retraceme fd74bf9514dd0739b92d67a6d48a51eb10aaf8df760d83496b8dcfcf83e5f698 */
    public final class AnonymousClass11 implements Animator.AnimatorListener {
        public /* synthetic */ FullScreenBannerHelper this$0;
        public /* synthetic */ Runnable[] val$asyncAnimationEndCallBacks;
        public /* synthetic */ boolean val$dismiss;

        @Override // android.animation.Animator.AnimatorListener
        public final void onAnimationCancel(Animator animator) {
        }

        @Override // android.animation.Animator.AnimatorListener
        public final void onAnimationEnd(Animator animator) {
            NotificationEntry notificationEntry;
            NotificationEntry notificationEntry2 = this.this$0.mLastNotificationEntry;
            if (notificationEntry2 != null && notificationEntry2.getEx() != null) {
                ((OplusNotificationEntryExImpl) notificationEntry2.getEx()).setHeadsUpEnd();
            }
            if (this.this$0.mShouldDoCallBackInMidAnimation) {
                Runnable[] runnableArr = this.val$asyncAnimationEndCallBacks;
                if (runnableArr.length != 0) {
                    for (Runnable runnable : runnableArr) {
                        ThreadManager.getInstance().getThreadHandler().post(runnable);
                    }
                }
                this.this$0.mShouldDoCallBackInMidAnimation = false;
            }
            this.this$0.mHandler.removeMessages(0);
            if (this.this$0.mBannerRootLayout.isAttachedToWindow()) {
                FullScreenBannerHelper fullScreenBannerHelper = this.this$0;
                if (!fullScreenBannerHelper.mIsStartFloatWindow) {
                    FullScreenBannerColorController fullScreenBannerColorController = (FullScreenBannerColorController) fullScreenBannerHelper.mFullScreenBannerColorController.get();
                    if (fullScreenBannerColorController != null) {
                        fullScreenBannerColorController.unbind();
                    }
                    ((NotificationDependOnCommon) this.this$0.mNotificationDependOnCommon.get()).removeListener(this.this$0.mStatusBarWindowStateListener);
                    this.this$0.releaseTouchMotion();
                    FullScreenBannerHelper fullScreenBannerHelper2 = this.this$0;
                    fullScreenBannerHelper2.mWindowManager.removeView(fullScreenBannerHelper2.mBannerRootLayout);
                    FullScreenBannerHelper fullScreenBannerHelper3 = this.this$0;
                    fullScreenBannerHelper3.mBackgroundNormal = null;
                    fullScreenBannerHelper3.updateBannerTouchableRegion(false);
                    FullScreenBannerHelper fullScreenBannerHelper4 = this.this$0;
                    fullScreenBannerHelper4.mRootView = null;
                    fullScreenBannerHelper4.mFullScreenBanner = null;
                    fullScreenBannerHelper4.mTouchHandler = null;
                    ((AonAuthStarter) fullScreenBannerHelper4.mAonAuthStarter.get()).tryCloseAonCamera("banner collapsed fully");
                }
            }
            FullScreenBannerHelper fullScreenBannerHelper5 = this.this$0;
            fullScreenBannerHelper5.mIsBannerShowing = false;
            fullScreenBannerHelper5.mBannerCollpseAniming = false;
            if (this.val$dismiss && (notificationEntry = fullScreenBannerHelper5.mLastNotificationEntry) != null && notificationEntry.getRow() != null) {
                ((NotificationStackScrollLayoutController) this.this$0.mNotificationStackScrollLayoutController.get()).getNotificationListContainer().getSwipeActionHelper().dismiss(this.this$0.mLastNotificationEntry.getRow(), 0.0f);
            }
            FullScreenBannerHelper fullScreenBannerHelper6 = this.this$0;
            fullScreenBannerHelper6.mLastNotificationEntry = null;
            Object objPeek = fullScreenBannerHelper6.mPendingDialogNotificationEntrys.peek();
            FullScreenBannerHelper fullScreenBannerHelper7 = this.this$0;
            if (objPeek != null) {
                fullScreenBannerHelper7.showBanner((NotificationEntry) fullScreenBannerHelper7.mPendingDialogNotificationEntrys.poll());
            } else {
                fullScreenBannerHelper7.mHasDialogNotification = false;
            }
            if (this.this$0.mPendingNotificationEntrys.peek() != null) {
                FullScreenBannerHelper fullScreenBannerHelper8 = this.this$0;
                fullScreenBannerHelper8.showBanner((NotificationEntry) fullScreenBannerHelper8.mPendingNotificationEntrys.poll());
            }
        }

        @Override // android.animation.Animator.AnimatorListener
        public final void onAnimationRepeat(Animator animator) {
        }

        @Override // android.animation.Animator.AnimatorListener
        public final void onAnimationStart(Animator animator) {
            FullScreenBannerHelper fullScreenBannerHelper = this.this$0;
            fullScreenBannerHelper.mBannerCollpseAniming = true;
            fullScreenBannerHelper.mShouldDoCallBackInMidAnimation = true;
        }
    }

    /* JADX INFO: renamed from: com.oplus.systemui.notification.interruption.fullscreenbanner.FullScreenBannerHelper$2, reason: invalid class name */
    /* JADX INFO: compiled from: go/retraceme fd74bf9514dd0739b92d67a6d48a51eb10aaf8df760d83496b8dcfcf83e5f698 */
    public final class AnonymousClass2 implements Runnable {
        public final /* synthetic */ int $r8$classId;
        public /* synthetic */ FullScreenBannerHelper this$0;

        public /* synthetic */ AnonymousClass2(int i) {
            this.$r8$classId = i;
        }

        @Override // java.lang.Runnable
        public final void run() {
            FullScreenBanner fullScreenBanner;
            ViewBlurManager viewBlurManager;
            switch (this.$r8$classId) {
                case 0:
                    FullScreenBannerHelper fullScreenBannerHelper = this.this$0;
                    FullScreenBannerContainer fullScreenBannerContainer = fullScreenBannerHelper.mRootView;
                    if (fullScreenBannerContainer != null && !fullScreenBannerHelper.mBannerCollpseAniming && (fullScreenBanner = fullScreenBannerContainer.mFullScreenBanner) != null && (viewBlurManager = fullScreenBannerContainer.mViewBlurManager) != null) {
                        viewBlurManager.releaseBlurProxyForView(fullScreenBanner, true, "");
                        fullScreenBannerContainer.mViewBlurProxy = null;
                        FullScreenBannerHelper fullScreenBannerHelper2 = this.this$0;
                        FullScreenBackgroundView fullScreenBackgroundView = fullScreenBannerHelper2.mBackgroundNormal;
                        if (fullScreenBackgroundView != null) {
                            FullScreenBanner fullScreenBanner2 = fullScreenBannerHelper2.mFullScreenBanner;
                            if (fullScreenBanner2 != null) {
                                int[] iArr = new int[2];
                                fullScreenBanner2.getLocationInWindow(iArr);
                                int i = iArr[0];
                                int i2 = iArr[1];
                                int width = fullScreenBanner2.getWidth();
                                int height = fullScreenBanner2.getHeight();
                                float dimension = fullScreenBackgroundView.mContext.getResources().getDimension(2131168360);
                                fullScreenBackgroundView.mRectF.set(i, i2, i + width, i2 + height);
                                fullScreenBackgroundView.updateIconRect();
                                fullScreenBackgroundView.invalidate();
                                fullScreenBackgroundView.setVisibility(0);
                                fullScreenBackgroundView.setCornerRadius(dimension);
                            } else {
                                LogUtil.w("FullScreenBackgroundView", "syncWithBanner: banner is null");
                            }
                        }
                        break;
                    }
                    break;
                default:
                    FullScreenBannerHelper fullScreenBannerHelper3 = this.this$0;
                    Interpolator interpolator = FullScreenBannerHelper.INTERPOLATOR_BANNER_MOVE;
                    fullScreenBannerHelper3.hideBannerImmediately();
                    break;
            }
        }
    }

    /* JADX INFO: renamed from: com.oplus.systemui.notification.interruption.fullscreenbanner.FullScreenBannerHelper$3, reason: invalid class name */
    /* JADX INFO: compiled from: go/retraceme fd74bf9514dd0739b92d67a6d48a51eb10aaf8df760d83496b8dcfcf83e5f698 */
    public final class AnonymousClass3 implements ViewTreeObserver.OnComputeInternalInsetsListener {
        public /* synthetic */ FullScreenBannerHelper this$0;

        public final void onComputeInternalInsets(ViewTreeObserver.InternalInsetsInfo internalInsetsInfo) {
            FullScreenBannerHelper fullScreenBannerHelper = this.this$0;
            if (fullScreenBannerHelper.mRootView == null || fullScreenBannerHelper.mFullScreenBanner == null) {
                return;
            }
            internalInsetsInfo.setTouchableInsets(3);
            internalInsetsInfo.touchableRegion.set(this.this$0.getBannerTouchableRegion());
        }
    }

    /* JADX INFO: renamed from: com.oplus.systemui.notification.interruption.fullscreenbanner.FullScreenBannerHelper$5, reason: invalid class name */
    /* JADX INFO: compiled from: go/retraceme fd74bf9514dd0739b92d67a6d48a51eb10aaf8df760d83496b8dcfcf83e5f698 */
    public final class AnonymousClass5 implements StatusBarWindowStateListener {
        public /* synthetic */ FullScreenBannerHelper this$0;

        public final void onStatusBarWindowStateChanged(int i) {
            FullScreenBannerHelper fullScreenBannerHelper = this.this$0;
            if (fullScreenBannerHelper.mLastNotificationEntry == null || fullScreenBannerHelper.refreshTargetY(i) == fullScreenBannerHelper.mLastTargetY) {
                return;
            }
            ValueAnimator valueAnimator = fullScreenBannerHelper.mBannerExpandAnimator;
            if (valueAnimator != null && valueAnimator.isRunning()) {
                fullScreenBannerHelper.mBannerExpandAnimator.cancel();
                LogUtil.d(LogUtil.TAG_NOTIFICATION, "FullScreenHelper", " cancel mBannerExpandAnimator and startMoveAnimator");
                FullScreenBannerHelper.m65$$Nest$mstartMoveAnimator(fullScreenBannerHelper);
            } else if (fullScreenBannerHelper.mBannerCollpseAniming) {
                LogUtil.d(LogUtil.TAG_NOTIFICATION, "FullScreenHelper", "executeBannerCollpseAnim mBannerCollpseAniming == true");
            } else {
                FullScreenBannerHelper.m65$$Nest$mstartMoveAnimator(fullScreenBannerHelper);
            }
        }
    }

    /* JADX INFO: renamed from: com.oplus.systemui.notification.interruption.fullscreenbanner.FullScreenBannerHelper$6, reason: invalid class name */
    /* JADX INFO: compiled from: go/retraceme fd74bf9514dd0739b92d67a6d48a51eb10aaf8df760d83496b8dcfcf83e5f698 */
    public final class AnonymousClass6 implements Animator.AnimatorListener {
        public /* synthetic */ FullScreenBannerHelper this$0;

        @Override // android.animation.Animator.AnimatorListener
        public final void onAnimationCancel(Animator animator) {
            FullScreenBannerHelper fullScreenBannerHelper = this.this$0;
            Interpolator interpolator = FullScreenBannerHelper.INTERPOLATOR_BANNER_MOVE;
            LogUtil.d(LogUtil.TAG_NOTIFICATION, "FullScreenHelper", "bannerMoveAnimator finish");
            fullScreenBannerHelper.mIsMoving = false;
            FullScreenBannerHelper fullScreenBannerHelper2 = this.this$0;
            FullScreenBanner fullScreenBanner = fullScreenBannerHelper2.mFullScreenBanner;
            if (fullScreenBanner != null) {
                float translationY = fullScreenBanner.getTranslationY();
                HeadsUpCOUItouchMotionAnimator headsUpCOUItouchMotionAnimator = fullScreenBannerHelper2.mTouchMotion;
                if (headsUpCOUItouchMotionAnimator == null) {
                    return;
                }
                headsUpCOUItouchMotionAnimator.setBaseTranslationY(translationY);
            }
        }

        @Override // android.animation.Animator.AnimatorListener
        public final void onAnimationEnd(Animator animator) {
            FullScreenBannerHelper fullScreenBannerHelper = this.this$0;
            Interpolator interpolator = FullScreenBannerHelper.INTERPOLATOR_BANNER_MOVE;
            LogUtil.d(LogUtil.TAG_NOTIFICATION, "FullScreenHelper", "bannerMoveAnimator finish");
            fullScreenBannerHelper.mIsMoving = false;
            FullScreenBannerHelper fullScreenBannerHelper2 = this.this$0;
            float f = fullScreenBannerHelper2.mTargetY;
            HeadsUpCOUItouchMotionAnimator headsUpCOUItouchMotionAnimator = fullScreenBannerHelper2.mTouchMotion;
            if (headsUpCOUItouchMotionAnimator == null) {
                return;
            }
            headsUpCOUItouchMotionAnimator.setBaseTranslationY(f);
        }

        @Override // android.animation.Animator.AnimatorListener
        public final void onAnimationRepeat(Animator animator) {
            this.this$0.mIsMoving = true;
        }

        @Override // android.animation.Animator.AnimatorListener
        public final void onAnimationStart(Animator animator) {
            this.this$0.mIsMoving = true;
        }
    }

    /* JADX INFO: renamed from: com.oplus.systemui.notification.interruption.fullscreenbanner.FullScreenBannerHelper$7, reason: invalid class name */
    /* JADX INFO: compiled from: go/retraceme fd74bf9514dd0739b92d67a6d48a51eb10aaf8df760d83496b8dcfcf83e5f698 */
    public final class AnonymousClass7 implements ConfigurationController.ConfigurationListener {
        public /* synthetic */ FullScreenBannerHelper this$0;
        public /* synthetic */ Context val$context;

        public final void onConfigChanged(Configuration configuration) {
            FullScreenBanner fullScreenBanner;
            FullScreenBannerHelper fullScreenBannerHelper = this.this$0;
            Interpolator interpolator = FullScreenBannerHelper.INTERPOLATOR_BANNER_MOVE;
            fullScreenBannerHelper.initResource();
            Resources resources = fullScreenBannerHelper.mContext.getResources();
            FeatureOption.INSTANCE.getClass();
            boolean z = FeatureOption.isFoldDevice() && DeviceUtil.shouldUseLargeFoldConfig(resources);
            int i = configuration.orientation;
            if (i != fullScreenBannerHelper.mLastOrientation || fullScreenBannerHelper.mIsFolded != z) {
                fullScreenBannerHelper.mLastOrientation = i;
                fullScreenBannerHelper.mIsFolded = z;
                if (fullScreenBannerHelper.mLastNotificationEntry != null) {
                    if (fullScreenBannerHelper.mFullScreenBanner == null || fullScreenBannerHelper.mBannerCollpseAniming || fullScreenBannerHelper.mIsStartFloatWindow) {
                        LogUtil.d(LogUtil.TAG_NOTIFICATION, "FullScreenHelper", "onConfigChanged not need updateViewLayout");
                        return;
                    }
                    fullScreenBannerHelper.stopMoveAnimator();
                    ValueAnimator valueAnimator = fullScreenBannerHelper.mBannerExpandAnimator;
                    if (valueAnimator != null && valueAnimator.isRunning()) {
                        LogUtil.d(LogUtil.TAG_NOTIFICATION, "FullScreenHelper", "FULL_SCREEN_BANNER-->BannerExpandAnimator cancel ");
                        fullScreenBannerHelper.mBannerExpandAnimator.cancel();
                    }
                    fullScreenBannerHelper.mHandler.removeMessages(0);
                    fullScreenBannerHelper.mFullScreenBanner.refreshResource(fullScreenBannerHelper.mContext);
                    fullScreenBannerHelper.mFullScreenBanner.updateBanner(fullScreenBannerHelper.buildNotification(this.val$context, fullScreenBannerHelper.mLastNotificationEntry));
                    int earScreenTranslationY = fullScreenBannerHelper.getEarScreenTranslationY();
                    float f = earScreenTranslationY;
                    fullScreenBannerHelper.mFullScreenBanner.setTranslationY(f);
                    HeadsUpCOUItouchMotionAnimator headsUpCOUItouchMotionAnimator = fullScreenBannerHelper.mTouchMotion;
                    if (headsUpCOUItouchMotionAnimator != null) {
                        headsUpCOUItouchMotionAnimator.setBaseTranslationY(f);
                    }
                    HeadsUpCOUItouchMotionAnimator headsUpCOUItouchMotionAnimator2 = fullScreenBannerHelper.mTouchMotion;
                    if (headsUpCOUItouchMotionAnimator2 != null) {
                        headsUpCOUItouchMotionAnimator2.onConfigChanged();
                    }
                    fullScreenBannerHelper.setClipTop(earScreenTranslationY);
                    fullScreenBannerHelper.mHandler.sendEmptyMessageDelayed(0, fullScreenBannerHelper.mHasDialogNotification ? 10000L : 5000L);
                }
            }
            if (fullScreenBannerHelper.mRootView == null || (fullScreenBanner = fullScreenBannerHelper.mFullScreenBanner) == null || !(fullScreenBanner.getParent() instanceof FullScreenBannerContainer)) {
                LogUtil.d(LogUtil.TAG_NOTIFICATION, "FullScreenHelper", "not need updateViewLayout");
                return;
            }
            FullScreenBannerContainer fullScreenBannerContainer = fullScreenBannerHelper.mRootView;
            FullScreenBanner fullScreenBanner2 = fullScreenBannerHelper.mFullScreenBanner;
            fullScreenBannerContainer.updateViewLayout(fullScreenBanner2, fullScreenBanner2.getLayoutParams());
        }
    }

    /* JADX INFO: renamed from: com.oplus.systemui.notification.interruption.fullscreenbanner.FullScreenBannerHelper$8, reason: invalid class name */
    /* JADX INFO: compiled from: go/retraceme fd74bf9514dd0739b92d67a6d48a51eb10aaf8df760d83496b8dcfcf83e5f698 */
    public final class AnonymousClass8 implements FullScreenBanner.Callback {
        public /* synthetic */ FullScreenBannerHelper this$0;
        public /* synthetic */ NotificationEntry val$entry;
        public /* synthetic */ Notification val$notification;

        public final void onBannerUpdate() {
            FullScreenBanner fullScreenBanner;
            FullScreenBannerHelper fullScreenBannerHelper = this.this$0;
            FullScreenBannerColorController fullScreenBannerColorController = (FullScreenBannerColorController) fullScreenBannerHelper.mFullScreenBannerColorController.get();
            if (fullScreenBannerColorController == null || (fullScreenBanner = fullScreenBannerHelper.mFullScreenBanner) == null) {
                return;
            }
            fullScreenBannerColorController.updateColor(fullScreenBanner);
        }
    }

    /* JADX INFO: renamed from: com.oplus.systemui.notification.interruption.fullscreenbanner.FullScreenBannerHelper$9, reason: invalid class name */
    /* JADX INFO: compiled from: go/retraceme fd74bf9514dd0739b92d67a6d48a51eb10aaf8df760d83496b8dcfcf83e5f698 */
    public final class AnonymousClass9 implements FullScreenBannerTouchHandler.Callback {
        public /* synthetic */ FullScreenBannerHelper this$0;
        public /* synthetic */ NotificationEntry val$entry;

        public final void hide() {
            FullScreenBannerHelper fullScreenBannerHelper = this.this$0;
            fullScreenBannerHelper.mBannerOperator = ShadeExpansionHelperImpl.VAL_EXPANDED_PANEL_QS_BOTH;
            fullScreenBannerHelper.hideBanner(new Runnable[0]);
            fullScreenBannerHelper.mHeadsUpManager.snooze(this.val$entry.getSbn());
        }
    }

    /* JADX INFO: compiled from: go/retraceme fd74bf9514dd0739b92d67a6d48a51eb10aaf8df760d83496b8dcfcf83e5f698 */
    public final class BannerDisplayHander extends Handler {
        public BannerDisplayHander(Looper looper) {
            super(looper);
        }

        @Override // android.os.Handler
        public final void handleMessage(Message message) {
            super.handleMessage(message);
            int i = message.what;
            FullScreenBannerHelper fullScreenBannerHelper = FullScreenBannerHelper.this;
            if (i == 0) {
                fullScreenBannerHelper.hideBanner(new Runnable[0]);
            } else {
                if (i != 1) {
                    return;
                }
                fullScreenBannerHelper.showBanner(fullScreenBannerHelper.mLastNotificationEntry);
            }
        }
    }

    /* JADX INFO: renamed from: -$$Nest$mstartMoveAnimator, reason: not valid java name */
    public static void m65$$Nest$mstartMoveAnimator(FullScreenBannerHelper fullScreenBannerHelper) {
        fullScreenBannerHelper.stopMoveAnimator();
        FullScreenBanner fullScreenBanner = fullScreenBannerHelper.mFullScreenBanner;
        fullScreenBannerHelper.mBannerMoveAnimator = ObjectAnimator.ofFloat(fullScreenBanner, MultiAnimViewDelegate.PROPERTY_TRANSLATION_Y, fullScreenBanner.getTranslationY(), fullScreenBannerHelper.mTargetY);
        fullScreenBannerHelper.setClipTop(fullScreenBannerHelper.mTargetY);
        fullScreenBannerHelper.mBannerMoveAnimator.setDuration(200L);
        fullScreenBannerHelper.mBannerMoveAnimator.setInterpolator(INTERPOLATOR_BANNER_MOVE);
        ValueAnimator valueAnimator = fullScreenBannerHelper.mBannerMoveAnimator;
        AnonymousClass6 anonymousClass6 = new AnonymousClass6();
        anonymousClass6.this$0 = fullScreenBannerHelper;
        VarHandle.storeStoreFence();
        valueAnimator.addListener(anonymousClass6);
        fullScreenBannerHelper.mBannerMoveAnimator.start();
    }

    /* JADX WARN: Type inference failed for: r3v0, types: [com.oplus.systemui.notification.interruption.fullscreenbanner.FullScreenBannerHelper$4] */
    public FullScreenBannerHelper(Context context, ConfigurationController configurationController) {
        AnonymousClass1 anonymousClass1 = new AnonymousClass1();
        anonymousClass1.this$0 = this;
        VarHandle.storeStoreFence();
        this.mScreenStateListener = anonymousClass1;
        AnonymousClass2 anonymousClass2 = new AnonymousClass2(0);
        anonymousClass2.this$0 = this;
        VarHandle.storeStoreFence();
        this.mOnStartAnimationCallback = anonymousClass2;
        AnonymousClass3 anonymousClass3 = new AnonymousClass3();
        anonymousClass3.this$0 = this;
        VarHandle.storeStoreFence();
        this.mOnComputeInternalInsetsListener = anonymousClass3;
        new ArrayList();
        this.mBannerSwitchMap = new HashMap();
        this.mBannerOperator = "0";
        this.mBannerCollpseAniming = false;
        this.mShouldDoCallBackInMidAnimation = true;
        this.mBannerTouchRegion = new Region();
        this.mIconSize = 0;
        this.mIconRadiusRatio = 0.0f;
        this.mIconZoomSize = 0;
        this.mIsStartFloatWindow = false;
        this.mBannerZoom = false;
        this.mHasTouchableRegionListener = false;
        this.mTargetY = 0;
        this.mLastTargetY = 0;
        this.mIsMoving = false;
        this.mBannerMoveAnimator = null;
        this.mPeopleHelper = null;
        this.mIsFolded = false;
        this.mSimpleBannerObserver = new ContentObserver(new Handler()) { // from class: com.oplus.systemui.notification.interruption.fullscreenbanner.FullScreenBannerHelper.4
            public final void onChange(boolean z, Collection collection, int i, int i2) {
                boolean z2 = Settings.System.getIntForUser(FullScreenBannerHelper.this.mContext.getContentResolver(), "simple_banner_switch_state", 1, i2) == 1;
                FullScreenBannerHelper.this.setBannerSwitchEnable(i2, z2);
                OplusNotificationLogger.logFullScreenBannerNotShow("simple banner changed, new value simpleBannerEnable = " + z2 + ", userId = " + i2);
            }
        };
        AnonymousClass5 anonymousClass5 = new AnonymousClass5();
        anonymousClass5.this$0 = this;
        VarHandle.storeStoreFence();
        this.mStatusBarWindowStateListener = anonymousClass5;
        this.mZoomBunld = null;
        this.mContext = context;
        this.mWindowManager = (WindowManager) context.getSystemService("window");
        this.mHandler = new BannerDisplayHander(context.getMainLooper());
        this.mPendingNotificationEntrys = new LinkedList();
        this.mPendingDialogNotificationEntrys = new LinkedList();
        initResource();
        Resources resources = context.getResources();
        FeatureOption.INSTANCE.getClass();
        this.mIsFolded = FeatureOption.isFoldDevice() && DeviceUtil.shouldUseLargeFoldConfig(resources);
        AnonymousClass7 anonymousClass7 = new AnonymousClass7();
        anonymousClass7.this$0 = this;
        anonymousClass7.val$context = context;
        VarHandle.storeStoreFence();
        configurationController.addCallback(anonymousClass7);
        ContentResolver contentResolver = context.getContentResolver();
        for (UserInfo userInfo : UserManager.get(context).getUsers()) {
            setBannerSwitchEnable(userInfo.id, Settings.System.getIntForUser(contentResolver, "simple_banner_switch_state", 1, userInfo.id) == 1);
        }
        ObserverDispatcher.getInstance().registerObserver(Settings.System.getUriFor("simple_banner_switch_state"), this.mSimpleBannerObserver, -1);
        OplusZoomWindowManager.getInstance().addOnConfigChangedListener(this);
        try {
            this.mSupportFreeformPkgs = OplusZoomWindowManager.getInstance().getZoomAppConfigList(3);
        } catch (Exception unused) {
        }
        ScreenStateReceiver.getInstance().register(context);
        ScreenStateReceiver.getInstance().addListener(this.mScreenStateListener);
    }

    private View.OnTouchListener getBannerOuterTouchListener() {
        FullScreenBannerHelper$$ExternalSyntheticLambda1 fullScreenBannerHelper$$ExternalSyntheticLambda1 = new FullScreenBannerHelper$$ExternalSyntheticLambda1();
        fullScreenBannerHelper$$ExternalSyntheticLambda1.f$0 = this;
        VarHandle.storeStoreFence();
        return fullScreenBannerHelper$$ExternalSyntheticLambda1;
    }

    /* JADX INFO: Access modifiers changed from: private */
    public Region getBannerTouchableRegion() {
        int[] iArr = new int[2];
        this.mFullScreenBanner.getLocationInWindow(iArr);
        Region region = this.mBannerTouchRegion;
        int i = iArr[0];
        region.set(i, iArr[1], this.mFullScreenBanner.getMeasuredWidth() + i, iArr[1] + this.mBannerRealHeight);
        return this.mBannerTouchRegion;
    }

    private WindowManager.LayoutParams getGestureLayoutParams() {
        WindowManager.LayoutParams layoutParams = new WindowManager.LayoutParams(-1, -1, 2017, 8652072, -3);
        layoutParams.flags |= 16777216;
        layoutParams.gravity = 49;
        layoutParams.setTitle("Simple Banner Window");
        layoutParams.setFitInsetsTypes(0);
        return layoutParams;
    }

    private FrameLayout.LayoutParams getRootViewLayoutParams() {
        FrameLayout.LayoutParams layoutParams = new FrameLayout.LayoutParams(-2, -1);
        layoutParams.gravity = 1;
        return layoutParams;
    }

    /* JADX INFO: Access modifiers changed from: private */
    public void setClipTop(int i) {
        FullScreenBannerContainer fullScreenBannerContainer = this.mRootView;
        if (fullScreenBannerContainer != null) {
            fullScreenBannerContainer.setClipTop(i);
        }
    }

    public static void startZoomWindow(StatusBarNotification statusBarNotification, PendingIntent pendingIntent, Context context) {
        Intent intent = new Intent("android.intent.action.MAIN", (Uri) null);
        intent.setPackage(statusBarNotification.getPackageName());
        intent.addCategory("android.intent.category.LAUNCHER");
        intent.addFlags(268435456);
        List listQueryIntentActivitiesAsUser = context.getPackageManager().queryIntentActivitiesAsUser(intent, 0, statusBarNotification.getUserId());
        if (listQueryIntentActivitiesAsUser == null || listQueryIntentActivitiesAsUser.size() == 0) {
            LogUtil.d(LogUtil.TAG_NOTIFICATION, "FullScreenHelper", "FULL_SCREEN_BANNER--> list is null or empty ");
        }
        Intent intent2 = statusBarNotification.getNotification().contentIntent.getIntent();
        if (pendingIntent != null && pendingIntent.getIntent() != null) {
            intent2 = pendingIntent.getIntent();
        }
        Intent intent3 = intent2;
        int userId = statusBarNotification.getUserId();
        if (userId == -1) {
            userId = ActivityManagerWrapper.getInstance().getCurrentUserId();
        }
        NotificationZoomStartUtil.startZoomFromNotification(intent3, statusBarNotification.getKey(), Integer.valueOf(userId), context.getPackageName(), 21, statusBarNotification.getOpPkg());
    }

    /* JADX WARN: Code duplicated, block: B:141:0x0375  */
    /* JADX WARN: Code duplicated, block: B:142:0x0377  */
    /* JADX WARN: Code duplicated, block: B:146:0x0386  */
    /* JADX WARN: Code duplicated, block: B:148:0x0392  */
    /* JADX WARN: Code duplicated, block: B:149:0x0395  */
    /* JADX WARN: Code duplicated, block: B:152:0x03a2  */
    /* JADX WARN: Code duplicated, block: B:155:0x03b4  */
    /* JADX WARN: Code duplicated, block: B:157:0x03cc  */
    /* JADX WARN: Code duplicated, block: B:158:0x03d1  */
    /* JADX WARN: Code duplicated, block: B:160:0x03e6  */
    /* JADX WARN: Code duplicated, block: B:161:0x03e8  */
    /* JADX WARN: Code duplicated, block: B:164:0x03f4  */
    /* JADX WARN: Code duplicated, block: B:166:0x03fa  */
    /* JADX WARN: Code duplicated, block: B:167:0x03fc  */
    /* JADX WARN: Code duplicated, block: B:173:0x041d  */
    /* JADX WARN: Code duplicated, block: B:176:0x0424  */
    /* JADX WARN: Code duplicated, block: B:179:0x042b  */
    /* JADX WARN: Code duplicated, block: B:180:0x042d  */
    /* JADX WARN: Code duplicated, block: B:183:0x0434  */
    /* JADX WARN: Code duplicated, block: B:187:0x0441  */
    /* JADX WARN: Code duplicated, block: B:189:0x0447  */
    /* JADX WARN: Code duplicated, block: B:195:0x045b  */
    /* JADX WARN: Code duplicated, block: B:198:0x0462  */
    /* JADX WARN: Code duplicated, block: B:19:0x00f2  */
    /* JADX WARN: Code duplicated, block: B:201:0x0468  */
    /* JADX WARN: Code duplicated, block: B:203:0x0488  */
    /* JADX WARN: Code duplicated, block: B:204:0x0494  */
    /* JADX WARN: Code duplicated, block: B:207:0x04be  */
    /* JADX WARN: Code duplicated, block: B:209:0x04c2  */
    /* JADX WARN: Code duplicated, block: B:211:0x04c6  */
    /* JADX WARN: Code duplicated, block: B:219:0x04e9  */
    /* JADX WARN: Code duplicated, block: B:221:0x04f1  */
    /* JADX WARN: Code duplicated, block: B:224:0x04fa A[LOOP:2: B:220:0x04ef->B:224:0x04fa, LOOP_END] */
    /* JADX WARN: Code duplicated, block: B:225:0x04fd A[EDGE_INSN: B:225:0x04fd->B:226:0x04fe BREAK  A[LOOP:2: B:220:0x04ef->B:224:0x04fa]] */
    /* JADX WARN: Code duplicated, block: B:227:0x0500  */
    /* JADX WARN: Code duplicated, block: B:229:0x0504  */
    /* JADX WARN: Code duplicated, block: B:244:0x0569  */
    /* JADX WARN: Code duplicated, block: B:252:0x044f A[SYNTHETIC] */
    /* JADX WARN: Code duplicated, block: B:258:0x04fd A[SYNTHETIC] */
    /* JADX WARN: Code duplicated, block: B:259:0x04fe A[EDGE_INSN: B:259:0x04fe->B:226:0x04fe BREAK  A[LOOP:2: B:220:0x04ef->B:224:0x04fa], SYNTHETIC] */
    /* JADX WARN: Code duplicated, block: B:40:0x0151  */
    /* JADX WARN: Code duplicated, block: B:61:0x01ef A[PHI: r7
      0x01ef: PHI (r7v6 java.lang.String) = (r7v3 java.lang.String), (r7v15 java.lang.String) binds: [B:68:0x0207, B:59:0x01de] A[DONT_GENERATE, DONT_INLINE]] */
    /* JADX WARN: Instruction removed from duplicated block: B:209:0x04c2, please report this as an issue */
    /* JADX WARN: Instruction removed from duplicated block: B:227:0x0500, please report this as an issue */
    public final FullScreenBanner.Builder buildNotification(Context context, NotificationEntry notificationEntry) {
        String string;
        String string2;
        boolean z;
        CharSequence[] charSequenceArray;
        Drawable drawableMutate;
        Drawable drawableMutate2;
        Drawable drawable;
        Class notificationStyle;
        Notification.Action[] actionArr;
        int i;
        RemoteInput remoteInput;
        int i2;
        RemoteInput[] remoteInputs;
        int length;
        int i3;
        boolean zIsAppLocked;
        Context context2;
        String quantityString;
        Notification.MetricStyle style;
        StringBuilder sb;
        List metrics;
        int i4;
        String string3;
        Notification.Metric metric;
        Notification.Metric.MetricValue.ValueString valueString;
        CharSequence string4;
        String string5;
        Notification.Metric.MetricValue value;
        List listTextVariants;
        String string6;
        Object obj;
        String string7;
        String prefix;
        Icon iconCreateAvatarSymbol;
        boolean zNeedEncrypted = ((PrivacyHelper) this.mPrivacyHelper.get()).needEncrypted(notificationEntry.getSbn().getPackageName(), notificationEntry.getSbn().getUid(), notificationEntry.getKey(), notificationEntry, true);
        Context fixContext = AodUtils.getFixContext(context);
        Notification notification = notificationEntry.getSbn().getNotification();
        Object obj2 = notification.extras.get("android.title");
        FullScreenBanner.Builder builder = new FullScreenBanner.Builder(fixContext);
        Drawable drawableLoadDrawable = notification.getSmallIcon().loadDrawable(fixContext);
        float f = this.mIconRadiusRatio;
        int i5 = this.mIconZoomSize;
        FullScreenBanner.Builder fullScreenBannerHelper = builder.setIcon(RoundRectDrawableUtil.getRoundRectDrawable(drawableLoadDrawable, f, i5, i5, this.mContext)).setLabel(obj2 == null ? "" : obj2.toString() + "").setCloned(OplusMultiAppUtils.isOplusMultiAppUser(Integer.valueOf(notificationEntry.getSbn().getUserId()))).setFullScreenBannerHelper(this);
        AnonymousClass8 anonymousClass8 = new AnonymousClass8();
        anonymousClass8.this$0 = this;
        anonymousClass8.val$entry = notificationEntry;
        anonymousClass8.val$notification = notification;
        VarHandle.storeStoreFence();
        FullScreenBanner.Builder callback = fullScreenBannerHelper.setCallback(anonymousClass8);
        this.mTouchHandler.setEntry(notificationEntry);
        FullScreenBannerTouchHandler fullScreenBannerTouchHandler = this.mTouchHandler;
        AnonymousClass9 anonymousClass9 = new AnonymousClass9();
        anonymousClass9.this$0 = this;
        anonymousClass9.val$entry = notificationEntry;
        VarHandle.storeStoreFence();
        fullScreenBannerTouchHandler.setCallback(anonymousClass9);
        Parcelable[] parcelableArray = notification.extras.getParcelableArray("android.messages");
        if (parcelableArray == null || parcelableArray.length <= 0) {
            if (notification.extras.get("android.text") == null) {
                z = zNeedEncrypted;
                string2 = "";
            } else {
                StatusBarNotification sbn = notificationEntry.getSbn();
                Bundle bundle = notification.extras;
                String packageName = sbn.getPackageName();
                Class notificationStyle2 = sbn.getNotification().getNotificationStyle();
                if (bundle == null) {
                    string = sbn.getNotification().extras.get("android.title") != null ? sbn.getNotification().extras.get("android.title").toString() : "";
                    if (sbn.getNotification().extras.get("android.text") != null) {
                        string2 = sbn.getNotification().extras.get("android.text").toString();
                    } else {
                        string2 = "";
                    }
                } else {
                    string = bundle.get("android.title") != null ? bundle.get("android.title").toString() : "";
                    if (bundle.get("android.text") != null) {
                        string2 = bundle.get("android.text").toString();
                    } else {
                        string2 = "";
                    }
                }
                z = zNeedEncrypted;
                if ("com.tencent.mm".equals(packageName) && sbn.getNotification().contentIntent != null && sbn.getNotification().contentIntent.getIntent() != null) {
                    try {
                        String stringExtra = sbn.getNotification().contentIntent.getIntent().getStringExtra("Main_User");
                        if (!TextUtils.isEmpty(stringExtra) && !stringExtra.contains("@chatroom") && !TextUtils.isEmpty(string) && !TextUtils.isEmpty(string2)) {
                            string2 = string2.replace("\"" + string + "\"", "");
                        }
                    } catch (Exception unused) {
                    }
                } else if (Notification.InboxStyle.class.equals(notificationStyle2) && bundle != null && (charSequenceArray = bundle.getCharSequenceArray("android.textLines")) != null && charSequenceArray.length > 0) {
                    string2 = NotificationUtil.FIRST_CONTENT_IN_TEXTLINES_PKGS[0].equals(packageName) ? charSequenceArray[0].toString() : charSequenceArray[charSequenceArray.length - 1].toString();
                }
            }
            callback.setContent(string2);
        } else {
            Notification.MessagingStyle.Message messageFromBundle = Notification.MessagingStyle.Message.getMessageFromBundle((Bundle) parcelableArray[parcelableArray.length - 1]);
            if (messageFromBundle != null && !zNeedEncrypted) {
                callback.setContent(messageFromBundle.getText() == null ? "" : messageFromBundle.getText().toString());
                Notification.Builder builderRecoverBuilder = Notification.Builder.recoverBuilder(this.mContext, notification);
                Notification.Style style2 = builderRecoverBuilder.getStyle();
                if (style2 instanceof Notification.MessagingStyle) {
                    Notification.MessagingStyle messagingStyle = (Notification.MessagingStyle) style2;
                    if (messagingStyle.isGroupConversation()) {
                        LogUtil.d(LogUtil.TAG_NOTIFICATION, "FullScreenHelper", "loadConversationAvatar isGroupConversation");
                        iconCreateAvatarSymbol = messagingStyle.getShortcutIcon();
                        if (iconCreateAvatarSymbol == null) {
                            iconCreateAvatarSymbol = notification.getLargeIcon();
                        }
                    } else {
                        Person senderPerson = messageFromBundle.getSenderPerson();
                        if (senderPerson == null) {
                            iconCreateAvatarSymbol = null;
                        } else {
                            Icon icon = senderPerson.getIcon();
                            if (icon != null) {
                                iconCreateAvatarSymbol = icon;
                            } else {
                                if (this.mPeopleHelper == null) {
                                    PeopleHelper peopleHelper = new PeopleHelper();
                                    this.mPeopleHelper = peopleHelper;
                                    peopleHelper.init(this.mContext);
                                }
                                CharSequence name = senderPerson.getName();
                                if (TextUtils.isEmpty(name)) {
                                    prefix = "";
                                } else {
                                    PeopleHelper.NameToPrefixMap nameToPrefixMapMapUniqueNamesToPrefixWithGroupList = this.mPeopleHelper.mapUniqueNamesToPrefixWithGroupList(Collections.singletonList(Collections.singletonList(messageFromBundle)));
                                    if (nameToPrefixMapMapUniqueNamesToPrefixWithGroupList != null) {
                                        prefix = nameToPrefixMapMapUniqueNamesToPrefixWithGroupList.getPrefix(name);
                                    } else {
                                        prefix = "";
                                    }
                                }
                                LogUtil.d(LogUtil.TAG_NOTIFICATION, "FullScreenHelper", "loadConversationAvatar createAvatarSymbol");
                                PeopleHelper peopleHelper2 = this.mPeopleHelper;
                                if (name == null) {
                                    name = "";
                                }
                                iconCreateAvatarSymbol = peopleHelper2.createAvatarSymbol(name, prefix.toString(), builderRecoverBuilder.getSmallIconColor(false));
                            }
                        }
                    }
                } else {
                    iconCreateAvatarSymbol = null;
                }
                if (iconCreateAvatarSymbol != null) {
                    callback.setMessageStyle(true);
                    Drawable drawableLoadDrawable2 = iconCreateAvatarSymbol.loadDrawable(fixContext);
                    float dimension = this.mContext.getResources().getDimension(2131170730);
                    int i6 = this.mIconZoomSize;
                    callback.setAvatarIcon(RoundRectDrawableUtil.getRoundRectDrawable(drawableLoadDrawable2, dimension, i6, i6, this.mContext));
                    z = zNeedEncrypted;
                }
                notificationStyle = notificationEntry.getSbn().getNotification().getNotificationStyle();
                if (notification.extras.get("android.text") == null && Notification.BigTextStyle.class.equals(notificationStyle)) {
                    obj = notificationEntry.getSbn().getNotification().extras.get("android.bigText");
                    if (obj == null) {
                        string7 = "";
                    } else {
                        string7 = obj.toString();
                    }
                    callback.setContent(string7);
                }
                if (Notification.MetricStyle.class.equals(notificationStyle)) {
                    if (Notification.MetricStyle.class.equals(notification.getNotificationStyle())) {
                        style = Notification.Builder.recoverBuilder(fixContext, notification).getStyle();
                        if (style instanceof Notification.MetricStyle) {
                            sb = new StringBuilder();
                            metrics = style.getMetrics();
                            for (i4 = 0; i4 < metrics.size(); i4++) {
                                metric = (Notification.Metric) metrics.get(i4);
                                valueString = metric.getValue().toValueString(fixContext);
                                if (TextUtils.isEmpty(valueString.subtext())) {
                                    string4 = metric.getLabel();
                                } else {
                                    string4 = fixContext.getString(R.string.permdesc_receiveWapPush, metric.getLabel(), valueString.subtext());
                                }
                                if (string4 == null) {
                                    string5 = "";
                                } else {
                                    string5 = string4.toString();
                                }
                                value = metric.getValue();
                                if (value instanceof Notification.Metric.TimeDifference) {
                                    listTextVariants = value.toValueString(fixContext).textVariants();
                                    if (!listTextVariants.isEmpty() || listTextVariants.get(0) == null) {
                                        string6 = "";
                                    } else {
                                        string6 = ((String) listTextVariants.get(0)).toString();
                                    }
                                    if (TextUtils.isEmpty(string5)) {
                                        if (TextUtils.isEmpty(string6)) {
                                            string5 = null;
                                        } else {
                                            string5 = string6;
                                        }
                                    } else if (!TextUtils.isEmpty(string6)) {
                                        string5 = CoroutineDebuggingKt$.ExternalSyntheticOutline0.m(string5, ":", string6);
                                    }
                                } else if (TextUtils.isEmpty(string5)) {
                                    string5 = null;
                                }
                                if (!TextUtils.isEmpty(string5)) {
                                    if (sb.length() > 0) {
                                        sb.append(" · ");
                                    }
                                    sb.append(string5);
                                }
                            }
                            if (sb.length() == 0) {
                                string3 = null;
                            } else {
                                string3 = sb.toString();
                            }
                        } else {
                            string3 = null;
                        }
                    } else {
                        string3 = null;
                    }
                    callback.setContent(string3 != null ? string3 : "");
                }
                if (z) {
                    zIsAppLocked = ((PrivacyHelper) this.mPrivacyHelper.get()).isAppLocked(notificationEntry.getSbn().getPackageName(), notificationEntry.getSbn().getUid());
                    context2 = this.mContext;
                    if (zIsAppLocked) {
                        quantityString = context2.getResources().getString(2131954819);
                    } else {
                        quantityString = context2.getResources().getQuantityString(2131820564, 1, 1);
                    }
                    callback.setLabel(AppInfoUtil.getAppNameByPkgName(this.mContext, notificationEntry.getSbn().getPackageName())).setContent(quantityString);
                    return callback;
                }
                actionArr = notification.actions;
                if (actionArr != null) {
                    for (Notification.Action action : actionArr) {
                        if (action.title != null && action.getExtras().getInt("tag_smart_reply", 0) != 1) {
                            if (action.getRemoteInputs() != null) {
                                remoteInput = null;
                                break;
                            }
                            remoteInputs = action.getRemoteInputs();
                            length = remoteInputs.length;
                            i3 = 0;
                            while (true) {
                                if (i3 < length) {
                                    remoteInput = null;
                                    break;
                                }
                                remoteInput = remoteInputs[i3];
                                if (remoteInput.getAllowFreeFormInput()) {
                                    break;
                                }
                                i3++;
                            }
                            if (remoteInput != null) {
                                for (Notification.Action action2 : actionArr) {
                                    RemoteInput[] remoteInputs2 = action2.getRemoteInputs();
                                    if (action2.actionIntent == null && remoteInputs2 != null) {
                                        RemoteInput remoteInput2 = null;
                                        for (RemoteInput remoteInput3 : remoteInputs2) {
                                            if (remoteInput3.getAllowFreeFormInput()) {
                                                remoteInput2 = remoteInput3;
                                            }
                                        }
                                        if (remoteInput2 != null) {
                                            this.mPendingIntent = action2.actionIntent;
                                            this.mRemoteInputs = remoteInputs2;
                                            this.mRemoteInput = remoteInput2;
                                            break;
                                        }
                                    }
                                }
                                String string8 = action.title.toString();
                                FullScreenBannerHelper$.ExternalSyntheticLambda2 externalSyntheticLambda2 = new FullScreenBannerHelper$.ExternalSyntheticLambda2(0);
                                externalSyntheticLambda2.f$0 = this;
                                externalSyntheticLambda2.f$1 = context;
                                externalSyntheticLambda2.f$2 = remoteInput;
                                VarHandle.storeStoreFence();
                                FullScreenBannerHelper$.ExternalSyntheticLambda3 externalSyntheticLambda3 = new FullScreenBannerHelper$.ExternalSyntheticLambda3();
                                externalSyntheticLambda3.f$0 = this;
                                externalSyntheticLambda3.f$1 = context;
                                VarHandle.storeStoreFence();
                                callback.getClass();
                                FullScreenBanner.2 r10 = new FullScreenBanner.2(1);
                                r10.this$0 = externalSyntheticLambda2;
                                VarHandle.storeStoreFence();
                                callback.mBtnMap.put(string8, r10);
                                callback.mSendListener = externalSyntheticLambda3;
                            } else {
                                String string9 = action.title.toString();
                                FullScreenBannerHelper$.ExternalSyntheticLambda2 externalSyntheticLambda4 = new FullScreenBannerHelper$.ExternalSyntheticLambda2(1);
                                externalSyntheticLambda4.f$0 = this;
                                externalSyntheticLambda4.f$1 = action;
                                externalSyntheticLambda4.f$2 = notificationEntry;
                                VarHandle.storeStoreFence();
                                callback.mBtnMap.put(string9, externalSyntheticLambda4);
                            }
                        }
                    }
                }
                return callback;
            }
            z = zNeedEncrypted;
        }
        if (NotificationIconBadgePackages.getInstance().contains(notificationEntry.getSbn().getPackageName())) {
            Icon largeIcon = notification.getLargeIcon();
            Drawable drawableLoadDrawable3 = largeIcon == null ? null : largeIcon.loadDrawable(fixContext);
            if (drawableLoadDrawable3 == null) {
                drawableMutate = null;
            } else {
                Drawable.ConstantState constantState = drawableLoadDrawable3.getConstantState();
                if (constantState != null) {
                    drawableLoadDrawable3 = constantState.newDrawable();
                }
                drawableMutate = drawableLoadDrawable3.mutate();
            }
            Icon smallIcon = notification.getSmallIcon();
            Drawable drawableLoadDrawable4 = smallIcon != null ? smallIcon.loadDrawable(fixContext) : null;
            if (drawableLoadDrawable4 == null) {
                drawableMutate2 = null;
            } else {
                Drawable.ConstantState constantState2 = drawableLoadDrawable4.getConstantState();
                if (constantState2 != null) {
                    drawableLoadDrawable4 = constantState2.newDrawable();
                }
                drawableMutate2 = drawableLoadDrawable4.mutate();
            }
            if (drawableMutate != null && drawableMutate2 != null) {
                int i7 = this.mIconZoomSize;
                Drawable roundRectDrawable = RoundRectDrawableUtil.getRoundRectDrawable(drawableMutate, i7 / 2, i7, i7, this.mContext);
                if (roundRectDrawable != null) {
                    drawableMutate = roundRectDrawable;
                }
                DrawableWithBadge drawableWithBadge = new DrawableWithBadge(drawableMutate);
                drawableWithBadge.mainPaddingFactor = 0.045f;
                drawableWithBadge.badgeSizeScale = 0.33f;
                drawableWithBadge.intrinsicContentSizePx = 0;
                drawableWithBadge.relayoutIfHasBounds();
                drawableWithBadge.setBadgeCornerRadiusFraction(this.mIconRadiusRatio);
                if (!OplusMultiAppUtils.isOplusMultiAppUser(Integer.valueOf(notificationEntry.getSbn().getUserId())) || (drawable = fixContext.getDrawable(2131233559)) == null) {
                    drawableWithBadge.setBadge(drawableMutate2);
                } else {
                    drawableWithBadge.setBadge(drawableMutate2, drawable);
                }
                callback.setLargeIconWithBadge(drawableWithBadge);
            }
        }
        notificationStyle = notificationEntry.getSbn().getNotification().getNotificationStyle();
        if (notification.extras.get("android.text") == null) {
            obj = notificationEntry.getSbn().getNotification().extras.get("android.bigText");
            if (obj == null) {
                string7 = "";
            } else {
                string7 = obj.toString();
            }
            callback.setContent(string7);
        }
        if (Notification.MetricStyle.class.equals(notificationStyle)) {
            if (Notification.MetricStyle.class.equals(notification.getNotificationStyle())) {
                string3 = null;
            } else {
                style = Notification.Builder.recoverBuilder(fixContext, notification).getStyle();
                if (style instanceof Notification.MetricStyle) {
                    string3 = null;
                } else {
                    sb = new StringBuilder();
                    metrics = style.getMetrics();
                    while (i4 < metrics.size()) {
                        metric = (Notification.Metric) metrics.get(i4);
                        valueString = metric.getValue().toValueString(fixContext);
                        if (TextUtils.isEmpty(valueString.subtext())) {
                            string4 = metric.getLabel();
                        } else {
                            string4 = fixContext.getString(R.string.permdesc_receiveWapPush, metric.getLabel(), valueString.subtext());
                        }
                        if (string4 == null) {
                            string5 = "";
                        } else {
                            string5 = string4.toString();
                        }
                        value = metric.getValue();
                        if (value instanceof Notification.Metric.TimeDifference) {
                            listTextVariants = value.toValueString(fixContext).textVariants();
                            if (listTextVariants.isEmpty()) {
                                string6 = "";
                            } else {
                                string6 = "";
                            }
                            if (TextUtils.isEmpty(string5)) {
                                if (TextUtils.isEmpty(string6)) {
                                    string5 = null;
                                } else {
                                    string5 = string6;
                                }
                            } else if (!TextUtils.isEmpty(string6)) {
                                string5 = CoroutineDebuggingKt$.ExternalSyntheticOutline0.m(string5, ":", string6);
                            }
                        } else if (TextUtils.isEmpty(string5)) {
                            string5 = null;
                        }
                        if (!TextUtils.isEmpty(string5)) {
                            if (sb.length() > 0) {
                                sb.append(" · ");
                            }
                            sb.append(string5);
                        }
                    }
                    if (sb.length() == 0) {
                        string3 = null;
                    } else {
                        string3 = sb.toString();
                    }
                }
            }
            callback.setContent(string3 != null ? string3 : "");
        }
        if (z) {
            zIsAppLocked = ((PrivacyHelper) this.mPrivacyHelper.get()).isAppLocked(notificationEntry.getSbn().getPackageName(), notificationEntry.getSbn().getUid());
            context2 = this.mContext;
            if (zIsAppLocked) {
                quantityString = context2.getResources().getString(2131954819);
            } else {
                quantityString = context2.getResources().getQuantityString(2131820564, 1, 1);
            }
            callback.setLabel(AppInfoUtil.getAppNameByPkgName(this.mContext, notificationEntry.getSbn().getPackageName())).setContent(quantityString);
            return callback;
        }
        actionArr = notification.actions;
        if (actionArr != null) {
            while (i < r3) {
                if (action.title != null) {
                    if (action.getRemoteInputs() != null) {
                        remoteInput = null;
                        break;
                    }
                    remoteInputs = action.getRemoteInputs();
                    length = remoteInputs.length;
                    i3 = 0;
                    while (true) {
                        if (i3 < length) {
                            remoteInput = null;
                            break;
                        }
                        remoteInput = remoteInputs[i3];
                        if (remoteInput.getAllowFreeFormInput()) {
                            break;
                            break;
                        }
                        i3++;
                    }
                    if (remoteInput != null) {
                        while (i2 < r7) {
                            RemoteInput[] remoteInputs3 = action2.getRemoteInputs();
                            if (action2.actionIntent == null) {
                            }
                        }
                        String string10 = action.title.toString();
                        FullScreenBannerHelper$.ExternalSyntheticLambda2 externalSyntheticLambda5 = new FullScreenBannerHelper$.ExternalSyntheticLambda2(0);
                        externalSyntheticLambda5.f$0 = this;
                        externalSyntheticLambda5.f$1 = context;
                        externalSyntheticLambda5.f$2 = remoteInput;
                        VarHandle.storeStoreFence();
                        FullScreenBannerHelper$.ExternalSyntheticLambda3 externalSyntheticLambda6 = new FullScreenBannerHelper$.ExternalSyntheticLambda3();
                        externalSyntheticLambda6.f$0 = this;
                        externalSyntheticLambda6.f$1 = context;
                        VarHandle.storeStoreFence();
                        callback.getClass();
                        FullScreenBanner.2 r11 = new FullScreenBanner.2(1);
                        r11.this$0 = externalSyntheticLambda5;
                        VarHandle.storeStoreFence();
                        callback.mBtnMap.put(string10, r11);
                        callback.mSendListener = externalSyntheticLambda6;
                    } else {
                        String string11 = action.title.toString();
                        FullScreenBannerHelper$.ExternalSyntheticLambda2 externalSyntheticLambda7 = new FullScreenBannerHelper$.ExternalSyntheticLambda2(1);
                        externalSyntheticLambda7.f$0 = this;
                        externalSyntheticLambda7.f$1 = action;
                        externalSyntheticLambda7.f$2 = notificationEntry;
                        VarHandle.storeStoreFence();
                        callback.mBtnMap.put(string11, externalSyntheticLambda7);
                    }
                }
            }
        }
        return callback;
    }

    public final void closeRemoteInputs() {
        if (this.mFullScreenBanner == null) {
            LogUtil.d(LogUtil.TAG_NOTIFICATION, "FullScreenHelper", "FULL_SCREEN_BANNER-->mFullScreenBanner is null");
            return;
        }
        InputMethodManager.getInstance().hideSoftInputFromWindow(this.mRootView.getWindowToken(), 2);
        FullScreenBanner fullScreenBanner = this.mFullScreenBanner;
        int i = fullScreenBanner.mOrientation;
        LinearLayout linearLayout = fullScreenBanner.mTopBtnLayout;
        if (i == 1) {
            linearLayout.setVisibility(8);
            fullScreenBanner.mBottomBtnLayout.setVisibility(0);
        } else {
            linearLayout.setVisibility(0);
            fullScreenBanner.mBottomBtnLayout.setVisibility(8);
        }
        fullScreenBanner.mReplyLayout.setVisibility(8);
        updateFullBanner(this.mContext, false);
    }

    public final StatusBarTransitionAnimatorController createStatusBarTransitionAnimatorController(FullScreenBannerAnimatorController fullScreenBannerAnimatorController, PendingIntent pendingIntent, NotificationEntry notificationEntry) {
        Bundle bundleCalculateFlexibleWindowBounds = HeadsUpToZoomUtils.calculateFlexibleWindowBounds(pendingIntent.getIntent(), this.mContext, notificationEntry.getSbn().getId());
        this.mZoomBunld = bundleCalculateFlexibleWindowBounds;
        return new StatusBarTransitionAnimatorController(fullScreenBannerAnimatorController, (ShadeAnimationInteractor) this.mShadeAnimationInteractor.get(), (ShadeController) this.mShadeController.get(), (NotificationShadeWindowController) this.mNotificationShadeWindowController.get(), (CommandQueue) this.mCommandQueue.get(), 0, true, Boolean.TRUE, bundleCalculateFlexibleWindowBounds, this.mBackgroundNormal);
    }

    public final void executeBannerCollpseAnim(boolean z, boolean z2, Runnable... runnableArr) {
        if (this.mFullScreenBanner == null) {
            LogUtil.d(LogUtil.TAG_NOTIFICATION, "FullScreenHelper", "executeBannerCollpseAnim mFullScreenBanner == null");
            return;
        }
        if (this.mBannerCollpseAniming) {
            LogUtil.d(LogUtil.TAG_NOTIFICATION, "FullScreenHelper", "executeBannerCollpseAnim mBannerCollpseAniming == true");
            return;
        }
        if (this.mIsStartFloatWindow) {
            LogUtil.d(LogUtil.TAG_NOTIFICATION, "FullScreenHelper", "executeBannerCollpseAnim mIsStartFloatWindow == true");
            return;
        }
        stopMoveAnimator();
        HeadsUpCOUItouchMotionAnimator headsUpCOUItouchMotionAnimator = this.mTouchMotion;
        if (headsUpCOUItouchMotionAnimator != null) {
            ((HeadsUpCOUItouchMotionAnimator$couiMotionForwarder$1) headsUpCOUItouchMotionAnimator.getCouiMotionForwarder()).forwardCancel();
        }
        LogUtil.d(LogUtil.TAG_NOTIFICATION, "FullScreenHelper", "executeBannerCollpseAnim start");
        this.mContext.getResources().getConfiguration();
        FullScreenBanner fullScreenBanner = this.mFullScreenBanner;
        ObjectAnimator objectAnimatorOfFloat = ObjectAnimator.ofFloat(fullScreenBanner, MultiAnimViewDelegate.PROPERTY_TRANSLATION_Y, fullScreenBanner.getTranslationY(), (-this.mBannerRealHeight) - 10);
        if (z) {
            objectAnimatorOfFloat.setDuration(600L);
        } else {
            objectAnimatorOfFloat.setDuration(0L);
        }
        objectAnimatorOfFloat.setInterpolator(new HeadsUpAppearInterpolator());
        AnonymousClass11 anonymousClass11 = new AnonymousClass11();
        anonymousClass11.this$0 = this;
        anonymousClass11.val$asyncAnimationEndCallBacks = runnableArr;
        anonymousClass11.val$dismiss = z2;
        VarHandle.storeStoreFence();
        objectAnimatorOfFloat.addListener(anonymousClass11);
        this.mFullScreenBanner.getBannerBoostFrameRateRateHandler().boostFrameRate(objectAnimatorOfFloat);
        NotificationEntry notificationEntry = this.mLastNotificationEntry;
        if (notificationEntry != null) {
            this.mSeedlingPluginManager.onHeadsUpPinnedOrUnPinned(false, notificationEntry);
        }
        FullScreenBannerHelper$$ExternalSyntheticLambda0 fullScreenBannerHelper$$ExternalSyntheticLambda0 = new FullScreenBannerHelper$$ExternalSyntheticLambda0();
        fullScreenBannerHelper$$ExternalSyntheticLambda0.f$0 = this;
        fullScreenBannerHelper$$ExternalSyntheticLambda0.f$1 = runnableArr;
        VarHandle.storeStoreFence();
        objectAnimatorOfFloat.addUpdateListener(fullScreenBannerHelper$$ExternalSyntheticLambda0);
        objectAnimatorOfFloat.start();
    }

    public final NotificationBlurMode getBlurModeForRow(NotificationEntry notificationEntry) {
        NotificationMaterialColorEx notificationMaterialColorEx;
        if (notificationEntry != null && (notificationMaterialColorEx = (NotificationMaterialColorEx) this.mNotificationMaterialColorEx.get()) != null) {
            return notificationMaterialColorEx.getBlurModeForRow(notificationEntry.getRow(), true);
        }
        return NotificationBlurMode.UNINITIATED;
    }

    public int getEarScreenTranslationY() {
        return this.mHeadsUpHelper.getTopIsFullscreen() ? refreshTargetY(2) : refreshTargetY(0);
    }

    public final void hideBanner(Runnable... runnableArr) {
        if (this.mFullScreenBanner != null) {
            executeBannerCollpseAnim(true, false, runnableArr);
            if (this.mLastNotificationEntry != null) {
                if (LogUtil.getNormalDebug()) {
                    Log.i("FullScreenHelper", "hide simple banner! " + this.mLastNotificationEntry.getSbn().getPackageName());
                }
                Context context = this.mContext;
                String packageName = this.mLastNotificationEntry.getSbn().getPackageName();
                Set set = AppInfoUtil.BLACK_DND_PACKAGES;
                String str = this.mBannerOperator;
                Lock lock = NotificationStatisticUtil.NOTIFICATION_LOCK;
                HashMap mapM = OplusCompatDcsUtils$.ExternalSyntheticOutline0.m("banner_package_name", packageName, "top_package_name", "");
                mapM.put("operator_type", str);
                OplusTrackProxyUtil.onCommon(context, "20082", "simple_full_banner_statistics", mapM);
            }
        }
    }

    public final void hideBannerImmediately() {
        if (this.mFullScreenBanner != null) {
            ValueAnimator valueAnimator = this.mBannerExpandAnimator;
            if (valueAnimator != null && valueAnimator.isRunning()) {
                this.mBannerExpandAnimator.cancel();
            }
            executeBannerCollpseAnim(false, false, new Runnable[0]);
            if (this.mLastNotificationEntry != null) {
                if (LogUtil.getNormalDebug()) {
                    Log.i("FullScreenHelper", "hide simple banner! " + this.mLastNotificationEntry.getSbn().getPackageName());
                }
                Context context = this.mContext;
                String packageName = this.mLastNotificationEntry.getSbn().getPackageName();
                Set set = AppInfoUtil.BLACK_DND_PACKAGES;
                String str = this.mBannerOperator;
                Lock lock = NotificationStatisticUtil.NOTIFICATION_LOCK;
                HashMap mapM = OplusCompatDcsUtils$.ExternalSyntheticOutline0.m("banner_package_name", packageName, "top_package_name", "");
                mapM.put("operator_type", str);
                OplusTrackProxyUtil.onCommon(context, "20082", "simple_full_banner_statistics", mapM);
            }
        }
    }

    public final void initResource() {
        Resources resources = this.mContext.getResources();
        int dimensionPixelSize = resources.getDimensionPixelSize(2131170678);
        this.mIconSize = dimensionPixelSize;
        this.mIconRadiusRatio = dimensionPixelSize != 0 ? resources.getDimension(2131168361) / this.mIconSize : 0.0f;
        this.mIconZoomSize = resources.getDimensionPixelSize(2131170767);
        resources.getDimensionPixelSize(2131170773);
        resources.getDimensionPixelSize(2131172246);
        LargeScreenUtils.shouldUseSplitNotificationShade(resources);
    }

    public final boolean isSimpleBannerEnable(NotificationEntry notificationEntry, Bundle bundle) {
        Object obj;
        Object obj2;
        boolean z;
        boolean zBooleanValue;
        Bundle bundle2;
        Bundle bundle3;
        if (bundle != null) {
            obj = bundle.get("android.title");
            obj2 = bundle.get("android.text");
            z = bundle.getBoolean("force_simple_banner", false);
        } else {
            obj = notificationEntry.getSbn().getNotification().extras.get("android.title");
            obj2 = notificationEntry.getSbn().getNotification().extras.get("android.text");
            z = false;
        }
        int currentUser = ((NotificationDependOnCommon) this.mNotificationDependOnCommon.get()).getCurrentUser();
        String strValueOf = String.valueOf(currentUser);
        synchronized (this.mBannerSwitchLock) {
            try {
                if (this.mBannerSwitchMap.containsKey(strValueOf)) {
                    OplusNotificationLogger.logFullScreenBannerNotShow("isBannerEnableForUser return " + ((Boolean) this.mBannerSwitchMap.get(strValueOf)).booleanValue() + " , uid = " + strValueOf + " , userId = " + currentUser);
                    zBooleanValue = ((Boolean) this.mBannerSwitchMap.get(strValueOf)).booleanValue();
                } else {
                    OplusNotificationLogger.logFullScreenBannerNotShow("isBannerEnableForUser default true , uid = " + strValueOf + " , userId = " + currentUser);
                    zBooleanValue = true;
                }
            } catch (Throwable th) {
                throw th;
            }
        }
        HeadsUpHelper headsUpHelper = this.mHeadsUpHelper;
        Notification notification = notificationEntry.getSbn().getNotification();
        boolean z2 = notification != null && (bundle3 = notification.extras) != null && bundle3.getBoolean("in_full_screen_mode") && headsUpHelper.getTopIsFullscreen();
        Notification notification2 = notificationEntry.getSbn().getNotification();
        boolean z3 = (notification2 == null || (bundle2 = notification2.extras) == null || !bundle2.getBoolean("in_conference_scene_mode")) ? false : true;
        boolean z4 = OpNotificationUtils.isNormalScreen() && DeviceUtil.isLandscape(this.mContext);
        boolean z5 = zBooleanValue && this.mCentralSurfaces.get() != null && (z4 || z2 || z3 || z) && ((notificationEntry.getSbn().getNotification().fullScreenIntent == null || this.mHeadsUpHelper.isThirdPartInCallNotification(notificationEntry.getSbn())) && (((obj instanceof CharSequence) && !TextUtils.isEmpty((CharSequence) obj)) || ((obj2 instanceof CharSequence) && !TextUtils.isEmpty((CharSequence) obj2))));
        if (!z5 && LogUtil.getNormalDebug()) {
            StringBuilder sbM = RowView$.ExternalSyntheticOutline0.m("notShow : isSimpleBannerEnable = false. , isBannerEnableForCurrentUser = ", ", mStatusBar != null ? ", zBooleanValue);
            KeyguardLogger$.ExternalSyntheticOutline0.m(", IsFullscreen = ", ", inConferenceSceneMode = ", sbM, this.mCentralSurfaces.get() != null, z2);
            KeyguardLogger$.ExternalSyntheticOutline0.m(", isPhoneLandscape = ", ", isBlackScreenEnable = false, isCurrentAppForcingFullScreenBanner = ", sbM, z3, z4);
            sbM.append(z);
            sbM.append(", thirdPartyInCall = ");
            sbM.append(this.mHeadsUpHelper.isThirdPartInCallNotification(notificationEntry.getSbn()));
            sbM.append(", isFullScreenIntentnull = ");
            sbM.append(notificationEntry.getSbn().getNotification().fullScreenIntent == null);
            OplusNotificationLogger.logFullScreenBannerNotShow(sbM.toString());
        }
        return z5;
    }

    public final void onConfigSwitchChanged(boolean z) {
        try {
            this.mSupportFreeformPkgs = OplusZoomWindowManager.getInstance().getZoomAppConfigList(3);
        } catch (Exception unused) {
        }
    }

    public final void onConfigTypeChanged(int i) {
        try {
            this.mSupportFreeformPkgs = OplusZoomWindowManager.getInstance().getZoomAppConfigList(3);
        } catch (Exception unused) {
        }
    }

    public final void onStartFloatWindow(NotificationEntry notificationEntry) {
        if (!this.mBannerZoom) {
            LogUtil.d(LogUtil.TAG_NOTIFICATION, "FullScreenHelper", "onStartFloatWindow: showZoom = false");
            return;
        }
        if (notificationEntry == null || this.mFullScreenBanner == null || this.mBackgroundNormal == null) {
            LogUtil.d(LogUtil.TAG_NOTIFICATION, "FullScreenHelper", "onStartFloatWindow: banner recycled, entry = " + notificationEntry + ", mFullScreenBanner = " + this.mFullScreenBanner + ", mBackgroundNormal = " + this.mBackgroundNormal);
            return;
        }
        FullScreenBannerContainer fullScreenBannerContainer = this.mRootView;
        if (fullScreenBannerContainer != null) {
            NotificationSpotLightDelegate.suppressUntilPointerUp(fullScreenBannerContainer, false);
            FullScreenBannerContainer fullScreenBannerContainer2 = this.mRootView;
            HeadsUpShadowEdgeController headsUpShadowEdgeController = fullScreenBannerContainer2.mShadowEdgeController;
            if (headsUpShadowEdgeController != null) {
                headsUpShadowEdgeController.release();
                fullScreenBannerContainer2.mShadowEdgeController = null;
            }
        }
        try {
            HeadsUpCOUItouchMotionAnimator headsUpCOUItouchMotionAnimator = this.mTouchMotion;
            int i = 1;
            if (headsUpCOUItouchMotionAnimator != null && !headsUpCOUItouchMotionAnimator.ignoredTouchMotionCallbackEnabledInternal) {
                headsUpCOUItouchMotionAnimator.ignoredTouchMotionCallbackEnabledInternal = true;
            }
            FullScreenBanner fullScreenBanner = this.mFullScreenBanner;
            AnonymousClass2 anonymousClass2 = new AnonymousClass2(i);
            anonymousClass2.this$0 = this;
            VarHandle.storeStoreFence();
            FullScreenBannerAnimatorController fullScreenBannerAnimatorController = new FullScreenBannerAnimatorController(this, fullScreenBanner, anonymousClass2, this.mOnStartAnimationCallback);
            Notification notification = notificationEntry.getSbn().getNotification();
            PendingIntent pendingIntent = notification.fullScreenIntent;
            if (pendingIntent == null) {
                pendingIntent = notification.contentIntent;
            }
            StatusBarTransitionAnimatorController statusBarTransitionAnimatorControllerCreateStatusBarTransitionAnimatorController = createStatusBarTransitionAnimatorController(fullScreenBannerAnimatorController, pendingIntent, notificationEntry);
            Intent intentPutExtra = TextUtils.isEmpty(notificationEntry.remoteInputText) ? null : new Intent().putExtra("android.remoteInputDraft", notificationEntry.remoteInputText.toString());
            ActivityTransitionAnimator activityTransitionAnimator = (ActivityTransitionAnimator) this.mActivityTransitionAnimator.get();
            String creatorPackage = pendingIntent.getCreatorPackage();
            FullScreenBannerHelper$.ExternalSyntheticLambda8 externalSyntheticLambda8 = new FullScreenBannerHelper$.ExternalSyntheticLambda8();
            externalSyntheticLambda8.f$0 = this;
            externalSyntheticLambda8.f$1 = notificationEntry;
            externalSyntheticLambda8.f$2 = pendingIntent;
            externalSyntheticLambda8.f$3 = intentPutExtra;
            VarHandle.storeStoreFence();
            activityTransitionAnimator.startPendingIntentWithAnimation(statusBarTransitionAnimatorControllerCreateStatusBarTransitionAnimatorController, true, creatorPackage, false, true, externalSyntheticLambda8);
        } catch (PendingIntent.CanceledException e) {
            LogUtil.e(LogUtil.TAG_NOTIFICATION, "FullScreenHelper", "onStartFloatWindow failed: " + e.getMessage());
        }
    }

    public final void pipReply(StatusBarNotification statusBarNotification, PendingIntent pendingIntent) {
        if (statusBarNotification == null) {
            LogUtil.w(LogUtil.TAG_NOTIFICATION, "FullScreenHelper", "FULL_SCREEN_BANNER-->Notification--pipReply: sbn is null");
            return;
        }
        LogUtil.d(LogUtil.TAG_NOTIFICATION, "FullScreenHelper", "FULL_SCREEN_BANNER-->Notification--pip reply for pkg: " + statusBarNotification.getPackageName() + " id: " + statusBarNotification.getId());
        try {
            startZoomWindow(statusBarNotification, pendingIntent, this.mContext);
        } catch (Exception e) {
            LogUtil.e(LogUtil.TAG_NOTIFICATION, "FullScreenHelper", "FULL_SCREEN_BANNER-->pipReply wrong", e);
        } catch (ExceptionInInitializerError e2) {
            LogUtil.e(LogUtil.TAG_NOTIFICATION, "FullScreenHelper", "FULL_SCREEN_BANNER-->pipReply ExceptionInInitializerError wrong", e2);
        }
        this.mHeadsUpManager.removeNotification(statusBarNotification.getKey(), true, "full screen pipReply");
        Context context = this.mContext;
        String packageName = statusBarNotification.getPackageName();
        boolean zIsOplusMultiAppUser = OplusMultiAppUtils.isOplusMultiAppUser(Integer.valueOf(statusBarNotification.getUserId()));
        Lock lock = NotificationStatisticUtil.NOTIFICATION_LOCK;
        HashMap mapM = OplusSmartlockController$mSmartLockCallback$1$.ExternalSyntheticOutline0.m("package_name", packageName);
        mapM.put("clone_app", String.valueOf(zIsOplusMultiAppUser));
        mapM.put("sequence", String.valueOf(0));
        mapM.put("launch_result", "success");
        Set set = AppInfoUtil.BLACK_DND_PACKAGES;
        mapM.put("root_app_pkg", "");
        mapM.put("root_app_name", AppInfoUtil.getAppNameByPkgName(context, ""));
        mapM.put("entrance", "banner");
        OplusTrackProxyUtil.onCommon(context, "20082", "launch_freeform_app", mapM);
    }

    public final int refreshTargetY(int i) {
        this.mLastTargetY = this.mTargetY;
        Resources resources = this.mContext.getResources();
        int statusBarHeight = this.mStatusBarWindowControllerStore.get() != null ? ((StatusBarWindowController) ((StatusBarWindowControllerStore) this.mStatusBarWindowControllerStore.get()).getDefaultDisplay()).getStatusBarHeight() : resources.getDimensionPixelOffset(R.dimen.text_size_subhead_material);
        int dimensionPixelSize = resources.getDimensionPixelSize(2131168426);
        if (DeviceUtil.isLandscape(this.mContext) && (i == 2 || i == 1)) {
            this.mTargetY = dimensionPixelSize;
        } else {
            this.mTargetY = dimensionPixelSize + statusBarHeight;
        }
        return this.mTargetY;
    }

    public final void releaseTouchMotion() {
        FullScreenBannerTouchHandler fullScreenBannerTouchHandler = this.mTouchHandler;
        if (fullScreenBannerTouchHandler != null) {
            fullScreenBannerTouchHandler.setCouiMotionForwarder(null);
        }
        HeadsUpCOUItouchMotionAnimator headsUpCOUItouchMotionAnimator = this.mTouchMotion;
        if (headsUpCOUItouchMotionAnimator != null) {
            FullScreenBanner fullScreenBanner = this.mFullScreenBanner;
            if (fullScreenBanner != null) {
                headsUpCOUItouchMotionAnimator.setBaseTranslationY(fullScreenBanner.getTranslationY());
            }
            this.mTouchMotion.detachView(this.mFullScreenBanner);
            this.mTouchMotion = null;
        }
    }

    public void setBannerDragTranslationY(float f) {
        FullScreenBanner fullScreenBanner = this.mFullScreenBanner;
        if (fullScreenBanner == null) {
            return;
        }
        HeadsUpCOUItouchMotionAnimator headsUpCOUItouchMotionAnimator = this.mTouchMotion;
        if (headsUpCOUItouchMotionAnimator != null) {
            headsUpCOUItouchMotionAnimator.setBaseTranslationY(f);
        } else {
            fullScreenBanner.setTranslationY(f);
        }
    }

    public void setBannerRealHeight(int i) {
        this.mBannerRealHeight = i;
    }

    public final void setBannerSwitchEnable(int i, boolean z) {
        String strValueOf = String.valueOf(i);
        synchronized (this.mBannerSwitchLock) {
            try {
                if (this.mBannerSwitchMap.containsKey(strValueOf)) {
                    if (((Boolean) this.mBannerSwitchMap.get(strValueOf)).booleanValue() == z) {
                        return;
                    } else {
                        this.mBannerSwitchMap.remove(strValueOf);
                    }
                }
                this.mBannerSwitchMap.put(strValueOf, Boolean.valueOf(z));
            } catch (Throwable th) {
                throw th;
            }
        }
    }

    public void setBannerTranslationY(float f) {
        if (this.mBannerZoom) {
            setBannerDragTranslationY(f);
        }
    }

    public void setIsStartFloatWindow(boolean z) {
        this.mIsStartFloatWindow = z;
    }

    public void setPanelExpanded(boolean z) {
    }

    public final void showBanner(NotificationEntry notificationEntry) {
        if (!((ShadeSurface) this.mShadeSurface.get()).isFullyCollapsed() || ((OplusAmbientStateExImpl) ((OplusAmbientStateEx) this.mAmbientStateEx.get())).mIsShadeOpening) {
            OplusNotificationLogger.logFullScreenBannerNotShow("notShow : panel fullyCollapsed = false");
            return;
        }
        Context context = this.mContext;
        if (notificationEntry == null || ((NotificationDependOnKeyguardImpl) ((NotificationDependOnKeyguard) this.mNotificationDependOnKeyguard.get())).isKeyguardShowing()) {
            OplusNotificationLogger.logFullScreenBannerNotShow("notShow : entry == " + notificationEntry + " , isKeyguardShowing = " + ((NotificationDependOnKeyguardImpl) ((NotificationDependOnKeyguard) this.mNotificationDependOnKeyguard.get())).isKeyguardShowing());
            return;
        }
        Bundle bundle = notificationEntry.getSbn().getNotification().extras;
        if (this.mHasDialogNotification && !bundle.getBoolean("simplebanner", false)) {
            OplusNotificationLogger.logFullScreenBannerNotShow("notShow : mHasDialogNotification = " + this.mHasDialogNotification + " , bundle get simplebanner = false");
            return;
        }
        if (!((NotificationLockscreenUserManager) this.mLockscreenUserManager.get()).isCurrentProfile(notificationEntry.getSbn().getUserId())) {
            OplusNotificationLogger.logFullScreenBannerNotShow("notShow : Does not match the current user, current entry user " + notificationEntry.getSbn().getUserId());
            return;
        }
        if (bundle.getBoolean("simplebanner", false)) {
            this.mHasDialogNotification = true;
        }
        String packageName = notificationEntry.getSbn().getPackageName();
        if (this.mHeadsUpHelper.suppressForGameSpaceOrSA(packageName, notificationEntry.getSbn()) && !bundle.getBoolean("force_show", false) && !packageName.equals(this.mHeadsUpHelper.getThirdPartTelAppPkg())) {
            OplusNotificationLogger.logFullScreenBannerNotShow("notShow : suppressForGameSpaceOrSA: pkg = " + packageName + ", entry = " + notificationEntry + " thirdPartTelApp = " + this.mHeadsUpHelper.getThirdPartTelAppPkg());
            return;
        }
        FullScreenBanner fullScreenBanner = this.mFullScreenBanner;
        if (fullScreenBanner != null) {
            if (fullScreenBanner.mReplyLayout.getVisibility() == 0) {
                OplusNotificationLogger.logFullScreenBannerNotShow("notShow : banner is RemoteInputActive");
                return;
            }
            this.mPendingNotificationEntrys.clear();
            this.mPendingDialogNotificationEntrys.clear();
            NotificationEntry notificationEntry2 = this.mLastNotificationEntry;
            if (notificationEntry2 != null && notificationEntry2.getSbn().getId() != notificationEntry.getSbn().getId()) {
                if (this.mHasDialogNotification) {
                    this.mPendingDialogNotificationEntrys.offer(notificationEntry);
                } else {
                    this.mPendingNotificationEntrys.offer(notificationEntry);
                }
                this.mHandler.removeMessages(0);
                hideBannerImmediately();
                return;
            }
            this.mFullScreenBanner.updateBanner(buildNotification(context, notificationEntry));
            this.mHandler.removeMessages(0);
            if (this.mHeadsUpHelper.isThirdPartInCallNotification(notificationEntry.getSbn())) {
                LogUtil.d(LogUtil.TAG_NOTIFICATION, "FullScreenHelper", "mHeadsUpHelper.isThirdPartInCallNotification==true");
                return;
            } else {
                this.mHandler.sendEmptyMessageDelayed(0, 5000L);
                return;
            }
        }
        ((OplusNotificationShadeWindowControllerEx) this.mNotificationShadeWindowControllerEx.get()).adjustShadeUxFlag(this.mContext, true);
        FullScreenBannerTouchHandler fullScreenBannerTouchHandler = new FullScreenBannerTouchHandler();
        fullScreenBannerTouchHandler.mSwipeWantsIt = false;
        fullScreenBannerTouchHandler.mScrollWantsIt = false;
        fullScreenBannerTouchHandler.mInitialTranslationY = 0.0f;
        fullScreenBannerTouchHandler.mFloatWindowTriggered = false;
        fullScreenBannerTouchHandler.mVelocityTracker = VelocityTracker.obtain();
        this.mTouchHandler = fullScreenBannerTouchHandler;
        FullScreenBanner.Builder builderBuildNotification = buildNotification(context, notificationEntry);
        builderBuildNotification.getClass();
        FullScreenBanner fullScreenBanner2 = new FullScreenBanner(builderBuildNotification.mContext);
        fullScreenBanner2.setIcon(builderBuildNotification.mIcon, builderBuildNotification.mClonedApp, builderBuildNotification.mAvatarIcon, builderBuildNotification.mIsMessageStyle, builderBuildNotification.mLargeIconWithBadge);
        fullScreenBanner2.setLabel(builderBuildNotification.mLabel);
        fullScreenBanner2.setContent(builderBuildNotification.mContent);
        fullScreenBanner2.setButtonPosition(builderBuildNotification.mBtnMap);
        fullScreenBanner2.setCallback(builderBuildNotification.mCallback);
        fullScreenBanner2.setSendListener(builderBuildNotification.mSendListener);
        fullScreenBanner2.setBannerHelper(builderBuildNotification.mFullScreenBannerHelper);
        this.mFullScreenBanner = fullScreenBanner2;
        this.mTouchHandler.setFullscreenBannerHelper(this);
        this.mTouchHandler.setAnimView(this.mFullScreenBanner);
        FrameLayout frameLayout = new FrameLayout(this.mContext);
        this.mBannerRootLayout = frameLayout;
        this.mWindowManager.addView(frameLayout, getGestureLayoutParams());
        ((NotificationDependOnCommon) this.mNotificationDependOnCommon.get()).addListener(this.mStatusBarWindowStateListener);
        Context context2 = this.mContext;
        OplusNotificationShadeWindowControllerEx oplusNotificationShadeWindowControllerEx = (OplusNotificationShadeWindowControllerEx) this.mNotificationShadeWindowControllerEx.get();
        FullScreenBannerContainer fullScreenBannerContainer = new FullScreenBannerContainer(context2);
        Path path = new Path();
        fullScreenBannerContainer.mPath = path;
        fullScreenBannerContainer.mOplusPath = new NotificationPath(path);
        fullScreenBannerContainer.mClipTop = 0;
        fullScreenBannerContainer.mSpotLightRadius = 0;
        fullScreenBannerContainer.mNotificationBlurMode = NotificationBlurMode.UNINITIATED;
        fullScreenBannerContainer.mHelper = this;
        fullScreenBannerContainer.mViewBlurManager = (ViewBlurManager) DependencyEx.sDependency.getDependency(ViewBlurManager.class);
        fullScreenBannerContainer.mNotificationShadeWindowControllerEx = oplusNotificationShadeWindowControllerEx;
        fullScreenBannerContainer.mSpotLightRadius = fullScreenBannerContainer.getContext().getResources().getDimensionPixelSize(2131168360);
        NotificationSpotLightDelegate.markLazyParamsKind(fullScreenBannerContainer, NotificationSpotLightParamsKind.FULLSCREEN_BANNER);
        VarHandle.storeStoreFence();
        this.mRootView = fullScreenBannerContainer;
        fullScreenBannerContainer.setTouchHandler(this.mTouchHandler);
        this.mRootView.setNotificationBlurMode(getBlurModeForRow(notificationEntry));
        Context context3 = this.mContext;
        FullScreenBackgroundView fullScreenBackgroundView = new FullScreenBackgroundView(context3);
        this.mBackgroundNormal = fullScreenBackgroundView;
        fullScreenBackgroundView.setClickable(false);
        this.mBackgroundNormal.setFocusable(false);
        this.mBackgroundNormal.setFocusableInTouchMode(false);
        this.mBackgroundNormal.setEnabled(false);
        Notification notification = notificationEntry.getSbn().getNotification();
        Context fixContext = AodUtils.getFixContext(context3);
        FullScreenBackgroundView fullScreenBackgroundView2 = this.mBackgroundNormal;
        Drawable drawableLoadDrawable = notification.getSmallIcon().loadDrawable(fixContext);
        float f = this.mIconRadiusRatio;
        int i = this.mIconZoomSize;
        fullScreenBackgroundView2.setIcon(RoundRectDrawableUtil.getRoundRectDrawable(drawableLoadDrawable, f, i, i, this.mContext));
        FullScreenBackgroundView fullScreenBackgroundView3 = this.mBackgroundNormal;
        this.mBackgroundNormal = fullScreenBackgroundView3;
        this.mBannerRootLayout.addView(fullScreenBackgroundView3, -1, -1);
        this.mBannerRootLayout.addView(this.mRootView, getRootViewLayoutParams());
        this.mRootView.addView(this.mFullScreenBanner, -2, -2);
        this.mRootView.setOnTouchListener(getBannerOuterTouchListener());
        FullScreenBannerColorController fullScreenBannerColorController = (FullScreenBannerColorController) this.mFullScreenBannerColorController.get();
        if (fullScreenBannerColorController != null) {
            fullScreenBannerColorController.bind(this.mFullScreenBanner);
        }
        this.mBannerZoom = OpNotificationUtils.isSupportZoonByIntent(this.mContext, notificationEntry, null, false, false);
        releaseTouchMotion();
        if (this.mBannerZoom && this.mFullScreenBanner != null && this.mTouchHandler != null) {
            Context context4 = this.mContext;
            FullScreenBannerHelper$.ExternalSyntheticLambda5 externalSyntheticLambda5 = new FullScreenBannerHelper$.ExternalSyntheticLambda5(0);
            externalSyntheticLambda5.f$0 = this;
            VarHandle.storeStoreFence();
            FullScreenBannerHelper$.ExternalSyntheticLambda5 externalSyntheticLambda6 = new FullScreenBannerHelper$.ExternalSyntheticLambda5(1);
            externalSyntheticLambda6.f$0 = this;
            VarHandle.storeStoreFence();
            this.mTouchMotion = HeadsUpCOUItouchMotionAnimator.create(context4, externalSyntheticLambda5, externalSyntheticLambda6, new FullScreenBannerHelper$.ExternalSyntheticLambda7());
        }
        if (this.mFullScreenBanner != null) {
            stopMoveAnimator();
            float f2 = -this.mBannerRealHeight;
            this.mBannerOperator = "0";
            final int earScreenTranslationY = getEarScreenTranslationY();
            setClipTop(earScreenTranslationY);
            updateBannerTouchableRegion(true);
            if (f2 == 0.0f) {
                f2 = -earScreenTranslationY;
            }
            ObjectAnimator objectAnimatorOfFloat = ObjectAnimator.ofFloat(this.mFullScreenBanner, MultiAnimViewDelegate.PROPERTY_TRANSLATION_Y, f2, earScreenTranslationY);
            this.mBannerExpandAnimator = objectAnimatorOfFloat;
            objectAnimatorOfFloat.setDuration(800L);
            this.mFullScreenBanner.getBannerBoostFrameRateRateHandler().boostFrameRate(this.mBannerExpandAnimator);
            this.mBannerExpandAnimator.setInterpolator(new HeadsUpAppearInterpolator());
            this.mBannerExpandAnimator.addListener(new AnimatorListenerAdapter() { // from class: com.oplus.systemui.notification.interruption.fullscreenbanner.FullScreenBannerHelper.10
                public boolean mCanceled;

                @Override // android.animation.AnimatorListenerAdapter, android.animation.Animator.AnimatorListener
                public final void onAnimationCancel(Animator animator) {
                    this.mCanceled = true;
                }

                @Override // android.animation.AnimatorListenerAdapter, android.animation.Animator.AnimatorListener
                public final void onAnimationEnd(Animator animator) {
                    HeadsUpCOUItouchMotionAnimator headsUpCOUItouchMotionAnimator;
                    ((NotifInflateDelayStrategyImpl) FullScreenBannerHelper.this.mNotifInflateDelayStrategy.get()).stopWaitAction(6);
                    if (this.mCanceled) {
                        return;
                    }
                    FullScreenBannerHelper fullScreenBannerHelper = FullScreenBannerHelper.this;
                    float f3 = earScreenTranslationY;
                    if (!fullScreenBannerHelper.mBannerZoom || (headsUpCOUItouchMotionAnimator = fullScreenBannerHelper.mTouchMotion) == null || fullScreenBannerHelper.mFullScreenBanner == null || fullScreenBannerHelper.mTouchHandler == null) {
                        return;
                    }
                    headsUpCOUItouchMotionAnimator.setBaseTranslationY(f3);
                    fullScreenBannerHelper.mTouchMotion.attachView(fullScreenBannerHelper.mFullScreenBanner);
                    fullScreenBannerHelper.mTouchHandler.setCouiMotionForwarder(fullScreenBannerHelper.mTouchMotion.getCouiMotionForwarder());
                }

                @Override // android.animation.AnimatorListenerAdapter, android.animation.Animator.AnimatorListener
                public final void onAnimationStart(Animator animator) {
                    this.mCanceled = false;
                    ((NotifInflateDelayStrategyImpl) FullScreenBannerHelper.this.mNotifInflateDelayStrategy.get()).startWaitAction(6);
                }
            });
            this.mBannerExpandAnimator.start();
            this.mIsBannerShowing = true;
        }
        ((AonAuthStarter) this.mAonAuthStarter.get()).openCamera("showBanner");
        this.mLastNotificationEntry = notificationEntry;
        this.mHandler.removeMessages(0);
        if (this.mHeadsUpHelper.isThirdPartInCallNotification(notificationEntry.getSbn())) {
            LogUtil.d(LogUtil.TAG_NOTIFICATION, "FullScreenHelper", "mHeadsUpHelper.isThirdPartInCallNotification==true");
        } else {
            this.mSeedlingPluginManager.onHeadsUpPinnedOrUnPinned(true, notificationEntry);
            this.mHandler.sendEmptyMessageDelayed(0, this.mHasDialogNotification ? 10000L : 5000L);
        }
    }

    public final void stopMoveAnimator() {
        ValueAnimator valueAnimator = this.mBannerMoveAnimator;
        if (valueAnimator == null || !valueAnimator.isRunning()) {
            return;
        }
        this.mBannerMoveAnimator.cancel();
    }

    public final void updateBannerTouchableRegion(boolean z) {
        if (this.mRootView == null || this.mFullScreenBanner == null) {
            return;
        }
        boolean z2 = this.mHasTouchableRegionListener;
        if (z) {
            if (z2) {
                return;
            }
            LogUtil.d(LogUtil.TAG_NOTIFICATION, "FullScreenHelper", "updateBannerTouchableRegion : addListener");
            this.mRootView.getViewTreeObserver().addOnComputeInternalInsetsListener(this.mOnComputeInternalInsetsListener);
            this.mHasTouchableRegionListener = true;
            return;
        }
        if (z2) {
            LogUtil.d(LogUtil.TAG_NOTIFICATION, "FullScreenHelper", "updateBannerTouchableRegion : removeListener");
            this.mRootView.getViewTreeObserver().removeOnComputeInternalInsetsListener(this.mOnComputeInternalInsetsListener);
            this.mHasTouchableRegionListener = false;
        }
    }

    public final void updateFullBanner(Context context, boolean z) {
        WindowManager.LayoutParams gestureLayoutParams = getGestureLayoutParams();
        WindowManager windowManager = (WindowManager) context.getSystemService("window");
        if (z) {
            gestureLayoutParams.flags &= -131081;
            gestureLayoutParams.softInputMode = 48;
        } else {
            gestureLayoutParams.flags = (gestureLayoutParams.flags | 8) & (-131073);
        }
        windowManager.updateViewLayout(this.mBannerRootLayout, gestureLayoutParams);
    }
}
