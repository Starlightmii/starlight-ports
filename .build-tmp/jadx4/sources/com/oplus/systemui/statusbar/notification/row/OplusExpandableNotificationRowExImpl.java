package com.oplus.systemui.statusbar.notification.row;

import android.R;
import android.animation.Animator;
import android.animation.ValueAnimator;
import android.app.INotificationManager;
import android.app.Notification;
import android.app.NotificationChannel;
import android.app.PendingIntent;
import android.content.ComponentName;
import android.content.Context;
import android.content.Intent;
import android.content.res.ColorStateList;
import android.content.res.Resources;
import android.graphics.Typeface;
import android.graphics.drawable.BitmapDrawable;
import android.graphics.drawable.Drawable;
import android.os.Bundle;
import android.os.Handler;
import android.os.RemoteException;
import android.os.SystemClock;
import android.service.notification.StatusBarNotification;
import android.text.TextUtils;
import android.util.AttributeSet;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewStub;
import android.widget.DateTimeView;
import android.widget.ImageView;
import android.widget.OplusNotificationDateTimeView;
import android.widget.TextView;
import androidx.collection.FloatList$;
import androidx.compose.material3.internal.TextFieldImplKt$;
import com.android.keyguard.OplusLockIconView$;
import com.android.launcher3.icons.IconProvider$;
import com.android.systemui.Dependency;
import com.android.systemui.DependencyEx;
import com.android.systemui.animation.TransitionAnimator;
import com.android.systemui.application.impl.SystemUIApplicationImpl$;
import com.android.systemui.keyguard.OplusBlackScreenGestureCallback$;
import com.android.systemui.media.NotificationMediaManager;
import com.android.systemui.plugins.statusbar.NotificationMenuRowPlugin;
import com.android.systemui.shade.NotificationPanelViewController;
import com.android.systemui.shade.NotificationPanelViewControllerEx;
import com.android.systemui.shade.OplusSeparateNotificationAndQSEx;
import com.android.systemui.statusbar.OplusLockscreenShadeTransitionControllerEx;
import com.android.systemui.statusbar.notification.LaunchAnimationParameters;
import com.android.systemui.statusbar.notification.OplusPhysicsPropertyAnimator$;
import com.android.systemui.statusbar.notification.OplusPropertyData;
import com.android.systemui.statusbar.notification.PhysicsProperty;
import com.android.systemui.statusbar.notification.RoundableState;
import com.android.systemui.statusbar.notification.animation.RowAnimationStateEx;
import com.android.systemui.statusbar.notification.animation.ScaleState;
import com.android.systemui.statusbar.notification.collection.EntryAdapter;
import com.android.systemui.statusbar.notification.collection.NotificationEntry;
import com.android.systemui.statusbar.notification.collection.OplusNotificationEntryEx;
import com.android.systemui.statusbar.notification.collection.render.GroupExpansionManager;
import com.android.systemui.statusbar.notification.collection.render.GroupExpansionManagerImpl;
import com.android.systemui.statusbar.notification.headsup.HeadsUpManager;
import com.android.systemui.statusbar.notification.helper.PrivacyHelper;
import com.android.systemui.statusbar.notification.people.PeopleNotificationIdentifier;
import com.android.systemui.statusbar.notification.row.ActivatableNotificationView;
import com.android.systemui.statusbar.notification.row.ExpandableNotificationRow;
import com.android.systemui.statusbar.notification.row.ExpandableView;
import com.android.systemui.statusbar.notification.row.NotificationBackgroundView;
import com.android.systemui.statusbar.notification.row.NotificationBackgroundViewExt;
import com.android.systemui.statusbar.notification.row.NotificationContentView;
import com.android.systemui.statusbar.notification.row.NotificationContentViewExt;
import com.android.systemui.statusbar.notification.row.NotificationMenuRow;
import com.android.systemui.statusbar.notification.row.NotificationMenuRowExt;
import com.android.systemui.statusbar.notification.row.OplusExpandableNotificationRowEx;
import com.android.systemui.statusbar.notification.stack.AmbientState;
import com.android.systemui.statusbar.notification.stack.ExpandableViewState;
import com.android.systemui.statusbar.notification.stack.NotificationChildrenContainer;
import com.android.systemui.statusbar.notification.stack.NotificationChildrenContainerExt;
import com.android.systemui.statusbar.notification.stack.NotificationChildrenContainerOverlayEx;
import com.android.systemui.statusbar.notification.stack.NotificationStackScrollLayoutControllerExt;
import com.android.systemui.statusbar.notification.stack.NotificationStackScrollLayoutExt;
import com.android.systemui.statusbar.notification.stack.OplusExpandableViewStateEx;
import com.android.systemui.statusbar.notification.stack.OplusExpandableViewStateExKt;
import com.android.systemui.statusbar.phone.CentralSurfacesImpl;
import com.android.systemui.statusbar.policy.KeyguardStateController;
import com.android.systemui.statusbar.policy.KeyguardStateControllerImpl;
import com.android.systemui.util.HeadsUpToZoomUtils;
import com.android.systemui.util.JankUtils;
import com.coui.appcompat.animation.COUISpringInterpolator;
import com.oplus.keyguard.VFXController$stopAnim$1$;
import com.oplus.systemui.notification.blur.ViewBlurManager;
import com.oplus.systemui.notification.framerate.BoostFrameRateManager;
import com.oplus.systemui.notification.headsup.windowframe.HeadsUpAnimator;
import com.oplus.systemui.notification.headsup.windowframe.HeadsUpContainerWindow;
import com.oplus.systemui.notification.headsup.windowframe.OplusHeadsUpAnimator;
import com.oplus.systemui.notification.lockscreen.ILockScreenExtStateController;
import com.oplus.systemui.notification.log.OplusNotifiEventLog;
import com.oplus.systemui.notification.mcs.OplusNotificationCommercializeControl;
import com.oplus.systemui.notification.row.material.MaterialColorMode;
import com.oplus.systemui.notification.row.material.NotificationBlurMode;
import com.oplus.systemui.notification.row.material.NotificationMaterialColorEx;
import com.oplus.systemui.notification.row.material.NotificationMaterialColorExImpl;
import com.oplus.systemui.notification.row.stacking.KeyguardStackingBackgroundControl;
import com.oplus.systemui.shade.NotificationPanelViewControllerExImp;
import com.oplus.systemui.shade.ShadeExpansionHelperImpl;
import com.oplus.systemui.statusbar.notification.animation.RowAnimationStateExImpl;
import com.oplus.systemui.statusbar.notification.dependence.NotificationDependOnCommon;
import com.oplus.systemui.statusbar.notification.helper.NotificationCenterHelper;
import com.oplus.systemui.statusbar.notification.helper.PanelPullHelper;
import com.oplus.systemui.statusbar.notification.keymagicservice.KeyBaseController;
import com.oplus.systemui.statusbar.notification.stack.NotificationStackScrollLayoutExtImpl;
import com.oplus.systemui.statusbar.notification.stack.OplusNotificationChildrenContainerOverlayExImpl$groupExpandAnimListener$1;
import com.oplus.systemui.statusbar.notification.util.OpNotificationUtils;
import com.oplus.systemui.statusbar.util.MCSUtil;
import com.oplus.systemui.statusbar.util.NotificationStatisticUtil;
import com.oplus.systemui.statusbar.util.NotificationTrackHelper;
import com.oplusos.systemui.common.blurability.ViewBlurProxy;
import com.oplusos.systemui.common.feature.FeatureOption;
import com.oplusos.systemui.common.util.LogUtil;
import com.oplusos.systemui.common.util.OplusTrackProxyUtil;
import com.oplusos.systemui.common.util.ScrimUtil;
import com.oplusos.systemui.common.util.SystemUIThreadUtil;
import com.oplusos.systemui.common.utils.AppInfoUtil;
import com.oplusos.systemui.notification.logger.OplusNotificationLogger;
import dagger.Lazy;
import java.lang.invoke.VarHandle;
import java.util.HashMap;
import java.util.Iterator;
import java.util.LinkedHashSet;
import java.util.List;
import java.util.Set;
import java.util.concurrent.locks.Lock;
import kotlin.LazyKt__LazyJVMKt;
import kotlin.Pair;
import kotlin.jvm.internal.Intrinsics;

/* JADX INFO: compiled from: go/retraceme fd74bf9514dd0739b92d67a6d48a51eb10aaf8df760d83496b8dcfcf83e5f698 */
/* JADX INFO: loaded from: /home/starlight/Projects/Starlight Ports/.build-tmp/classes4.dex */
public class OplusExpandableNotificationRowExImpl extends OplusActivatableNotificationViewExImpl implements OplusExpandableNotificationRowEx {
    public static final Companion Companion = new Companion();
    private static final String TAG = "OplusExpandableNotificationRowExImpl";
    private Boolean _shouldEncrypted;
    private final AmbientState ambientState;
    private final Context context;
    private final View.OnClickListener fakeClicker;
    public float groupExpandContentAlpha;
    public float groupExpandHeaderAlpha;
    private final Set<OplusExpandableNotificationRowEx.ExpandedAnimationStateListener> groupExpandedAnimationStateListeners;
    public float groupExpansionProgress;
    public boolean hasHandleDownEventAnim;
    private final Lazy headsUpAnimator;
    private final Lazy headsUpContainerWindow;
    private final HeadsUpManager headsUpManagerPhone;
    public boolean isBoostFrameRate;
    public boolean isChildrenExpandedAnimating;
    public boolean isKeepGone;
    public boolean isZoomAnimating;
    public boolean isZoomBackUpHeadsUp;
    public boolean isZoomDragable;
    private final Lazy lockScreenExtStateController;
    private final Lazy lockscreenShadeTransitionControllerEx;
    private final Lazy mNotificationClickEffectController;
    private final Lazy mainHandler;
    private MaterialColorMode materialColorMode;
    private final kotlin.Lazy maxExpandedHeightCustom$delegate;
    private final kotlin.Lazy maxSmallHeightCustom$delegate;
    private final NotificationMediaManager mediaManager;
    private NotificationBlurMode notificationBlurMode;
    private final NotificationCenterHelper notificationCenterHelper;
    private final Lazy notificationDependOnCommon;
    private final INotificationManager notificationManager;
    private final Lazy notificationMaterialColorEx;
    private final NotificationPanelViewController notificationPanelViewController;
    private final Lazy nsslControllerExt;
    public float paramsProgress;
    private final PeopleNotificationIdentifier peopleNotificationIdentifier;
    private final PrivacyHelper privacyHelper;
    private final Lazy pullHelper;
    private Pair<Float, Float> roundnessState;
    private final Lazy separateNotificationAndQSEx;
    private Typeface titleTypeface;
    public boolean useZoomRadius;
    private final ViewBlurManager viewBlurManager;
    public long willLaunchTimestamp;
    public float zoomBeginBottomRadius;
    public float zoomBeginTopRadius;
    public float zoomBottomRadius;
    private Bundle zoomBundle;
    private ImageView zoomDragView;
    public float zoomTopRadius;

    /* JADX INFO: compiled from: go/retraceme fd74bf9514dd0739b92d67a6d48a51eb10aaf8df760d83496b8dcfcf83e5f698 */
    public final class Companion {
        public final boolean isRenderExpensiveView(View view) {
            return (view instanceof TextView) || (view instanceof NotificationContentView);
        }
    }

    /* JADX INFO: renamed from: com.oplus.systemui.statusbar.notification.row.OplusExpandableNotificationRowExImpl$onZoomTransitionAnimationStart$1, reason: invalid class name */
    /* JADX INFO: compiled from: go/retraceme fd74bf9514dd0739b92d67a6d48a51eb10aaf8df760d83496b8dcfcf83e5f698 */
    public final class AnonymousClass1 implements Runnable {
        public /* synthetic */ ExpandableNotificationRow $notification;
        public /* synthetic */ String $pkg;

        @Override // java.lang.Runnable
        public final void run() {
            NotificationBackgroundViewExt ext;
            Context context = this.$notification.getContext();
            String str = this.$pkg;
            str.getClass();
            Drawable applicationIcon = HeadsUpToZoomUtils.getApplicationIcon(context, str);
            if (applicationIcon != null) {
                BitmapDrawable bitmapDrawable = new BitmapDrawable(this.$notification.getContext().getResources(), HeadsUpToZoomUtils.drawableToBitmap(applicationIcon));
                NotificationBackgroundView notificationBackgroundView = ((ActivatableNotificationView) this.$notification).mBackgroundNormal;
                if (notificationBackgroundView != null && (ext = notificationBackgroundView.getExt()) != null) {
                    ext.setIcon(bitmapDrawable);
                }
            }
            OplusNotificationLogger.logNotificationZOOM(OplusExpandableNotificationRowExImpl.TAG, "onZoomTransitionAnimationStart row pkg=" + this.$pkg + " drawable=" + applicationIcon);
            Context context2 = this.$notification.getContext();
            StatusBarNotification sbn = this.$notification.getEntry().getSbn();
            String str2 = this.$pkg;
            Lock lock = NotificationStatisticUtil.NOTIFICATION_LOCK;
            if (TextUtils.isEmpty(str2)) {
                return;
            }
            HashMap map = new HashMap();
            map.put("channel_id", sbn.getNotification().getChannelId());
            map.put("channel_name", "");
            map.put("notification_source", MCSUtil.isMCSPkg(sbn.getPackageName(), sbn.getOpPkg()) ? "opush" : "local");
            map.put("notification_id", String.valueOf(sbn.getId()));
            map.put("pkg", sbn.getPackageName());
            map.put(KeyBaseController.MAGIC_KEY_APP_NAME, AppInfoUtil.getAppNameByPkgName(context2, sbn.getPackageName()));
            map.put("event_screen_orientation", NotificationStatisticUtil.getScreenOrientation(context2));
            map.put("oplus_notification_zoom_pkg", str2);
            OplusTrackProxyUtil.onCommon(context2, "20082", "oplus_notification_fling_zoom", map);
        }
    }

    /* JADX INFO: renamed from: com.oplus.systemui.statusbar.notification.row.OplusExpandableNotificationRowExImpl$overlayChildContainerInflateFactory$1, reason: invalid class name and case insensitive filesystem */
    /* JADX INFO: compiled from: go/retraceme fd74bf9514dd0739b92d67a6d48a51eb10aaf8df760d83496b8dcfcf83e5f698 */
    public final class LayoutInflaterFactoryC02891 implements LayoutInflater.Factory {
        public static final LayoutInflaterFactoryC02891 INSTANCE = new LayoutInflaterFactoryC02891();

        @Override // android.view.LayoutInflater.Factory
        public final View onCreateView(String str, Context context, AttributeSet attributeSet) {
            if (Intrinsics.areEqual(str, DateTimeView.class.getName()) || Intrinsics.areEqual(str, "DateTimeView") || Intrinsics.areEqual(str, OplusNotificationDateTimeView.class.getName()) || Intrinsics.areEqual(str, "OplusNotificationDateTimeView")) {
                return new com.oplus.systemui.notification.row.widget.OplusNotificationDateTimeView(context, attributeSet, 4, 0);
            }
            return null;
        }
    }

    public OplusExpandableNotificationRowExImpl(Context context, PeopleNotificationIdentifier peopleNotificationIdentifier, PrivacyHelper privacyHelper, NotificationMediaManager notificationMediaManager, NotificationCenterHelper notificationCenterHelper, Lazy lazy, Lazy lazy2, Lazy lazy3, INotificationManager iNotificationManager, AmbientState ambientState, ViewBlurManager viewBlurManager, Lazy lazy4, HeadsUpManager headsUpManager, NotificationPanelViewController notificationPanelViewController, Lazy lazy5, Lazy lazy6, Lazy lazy7, Lazy lazy8, Lazy lazy9, Lazy lazy10, Lazy lazy11) {
        super(context);
        this.context = context;
        this.peopleNotificationIdentifier = peopleNotificationIdentifier;
        this.privacyHelper = privacyHelper;
        this.mediaManager = notificationMediaManager;
        this.notificationCenterHelper = notificationCenterHelper;
        this.pullHelper = lazy;
        this.mNotificationClickEffectController = lazy2;
        this.notificationDependOnCommon = lazy3;
        this.notificationManager = iNotificationManager;
        this.ambientState = ambientState;
        this.viewBlurManager = viewBlurManager;
        this.separateNotificationAndQSEx = lazy4;
        this.headsUpManagerPhone = headsUpManager;
        this.notificationPanelViewController = notificationPanelViewController;
        this.headsUpContainerWindow = lazy5;
        this.lockscreenShadeTransitionControllerEx = lazy6;
        this.lockScreenExtStateController = lazy7;
        this.headsUpAnimator = lazy8;
        this.mainHandler = lazy9;
        this.nsslControllerExt = lazy10;
        this.notificationMaterialColorEx = lazy11;
        OplusExpandableNotificationRowExImpl$fakeClicker$1 oplusExpandableNotificationRowExImpl$fakeClicker$1 = new OplusExpandableNotificationRowExImpl$fakeClicker$1();
        oplusExpandableNotificationRowExImpl$fakeClicker$1.this$0 = this;
        VarHandle.storeStoreFence();
        this.fakeClicker = oplusExpandableNotificationRowExImpl$fakeClicker$1;
        this.paramsProgress = -1.0f;
        OplusExpandableNotificationRowExImpl$$ExternalSyntheticLambda0 oplusExpandableNotificationRowExImpl$$ExternalSyntheticLambda0 = new OplusExpandableNotificationRowExImpl$$ExternalSyntheticLambda0(0);
        oplusExpandableNotificationRowExImpl$$ExternalSyntheticLambda0.f$0 = this;
        VarHandle.storeStoreFence();
        this.maxSmallHeightCustom$delegate = LazyKt__LazyJVMKt.lazy(oplusExpandableNotificationRowExImpl$$ExternalSyntheticLambda0);
        OplusExpandableNotificationRowExImpl$$ExternalSyntheticLambda0 oplusExpandableNotificationRowExImpl$$ExternalSyntheticLambda1 = new OplusExpandableNotificationRowExImpl$$ExternalSyntheticLambda0(1);
        oplusExpandableNotificationRowExImpl$$ExternalSyntheticLambda1.f$0 = this;
        VarHandle.storeStoreFence();
        this.maxExpandedHeightCustom$delegate = LazyKt__LazyJVMKt.lazy(oplusExpandableNotificationRowExImpl$$ExternalSyntheticLambda1);
        this.groupExpandedAnimationStateListeners = new LinkedHashSet();
        this.groupExpandContentAlpha = 1.0f;
        this.materialColorMode = MaterialColorMode.UNINITIATED;
        this.notificationBlurMode = NotificationBlurMode.UNINITIATED;
    }

    public void addGroupExpandAnimationListener(OplusExpandableNotificationRowEx.ExpandedAnimationStateListener expandedAnimationStateListener) {
        synchronized (this.groupExpandedAnimationStateListeners) {
            this.groupExpandedAnimationStateListeners.add(expandedAnimationStateListener);
        }
    }

    public void applyZoomLaunchAnimationParams(LaunchAnimationParameters launchAnimationParameters, ExpandableNotificationRow expandableNotificationRow) {
        float f;
        float f2;
        Bundle bundle;
        int i;
        Bundle bundle2;
        int i2;
        if (this.isZoomAnimating) {
            float linearProgress = launchAnimationParameters.getLinearProgress();
            if (linearProgress > 1.0f || linearProgress < 0.0f || !this.isZoomAnimating || (bundle2 = this.zoomBundle) == null || (i2 = bundle2.getInt("androidx.activity.LaunchCornerRadius", 0)) == 0) {
                f = this.zoomBeginTopRadius;
            } else {
                Bundle bundle3 = this.zoomBundle;
                bundle3.getClass();
                float f3 = bundle3.getFloat("androidx.activity.LaunchScale", 1.0f);
                float f4 = this.zoomBeginTopRadius;
                f = f3 == 0.0f ? f4 : (((i2 * f3) - f4) * linearProgress) + f4;
            }
            this.zoomTopRadius = f;
            float linearProgress2 = launchAnimationParameters.getLinearProgress();
            if (linearProgress2 > 1.0f || linearProgress2 < 0.0f || !this.isZoomAnimating || (bundle = this.zoomBundle) == null || (i = bundle.getInt("androidx.activity.LaunchCornerRadius", 0)) == 0) {
                f2 = this.zoomBeginBottomRadius;
            } else {
                Bundle bundle4 = this.zoomBundle;
                bundle4.getClass();
                float f5 = bundle4.getFloat("androidx.activity.LaunchScale", 1.0f);
                float f6 = this.zoomBeginBottomRadius;
                f2 = f5 == 0.0f ? f6 : (((i * f5) - f6) * linearProgress2) + f6;
            }
            this.zoomBottomRadius = f2;
            expandableNotificationRow.setContentAlpha(launchAnimationParameters.getContentAlpha());
            float linearProgress3 = launchAnimationParameters.getContentAlpha() == 0.0f ? 1.0f : (launchAnimationParameters.getLinearProgress() * 0.050000012f) + 0.95f;
            expandableNotificationRow.setScaleX(linearProgress3);
            expandableNotificationRow.setScaleY(1.0f);
            RowAnimationStateEx rowAnimationStateEx = OplusExpandableViewStateExKt.getRowAnimationStateEx(expandableNotificationRow);
            if (rowAnimationStateEx != null) {
                ((RowAnimationStateExImpl) rowAnimationStateEx).requestRowScale("headsup_zoom", ScaleState.Companion.invoke(linearProgress3));
            }
            expandableNotificationRow.setAlpha(launchAnimationParameters.getAlpha());
        }
    }

    /* JADX WARN: Multi-variable type inference failed */
    @Override // com.oplus.systemui.statusbar.notification.row.OplusActivatableNotificationViewExImpl, com.oplus.systemui.statusbar.notification.row.OplusExpandableOutlineViewExImpl, com.oplus.systemui.statusbar.notification.row.OplusExpandableViewExImpl
    public /* bridge */ /* synthetic */ void bind(View view) {
        super/*com.oplusos.systemui.common.BaseViewBinder*/.bind(view);
    }

    public void boostFrameRate(Animator animator) {
        animator.addListener(new boostFrameRate.1(this));
    }

    public Bundle calculateFlexibleWindowBounds(ExpandableNotificationRow expandableNotificationRow, boolean z, boolean z2) {
        Bundle zoomBundle;
        if (expandableNotificationRow.getEntry() == null || expandableNotificationRow.getEntry().getSbn() == null || expandableNotificationRow.getEntry().getSbn().getNotification() == null) {
            OplusNotificationLogger.logNotificationZOOM(TAG, "calculateFlexibleWindowBounds null expandableNotificationRow:" + expandableNotificationRow);
            return null;
        }
        Notification notification = expandableNotificationRow.getEntry().getSbn().getNotification();
        PendingIntent pendingIntent = notification.contentIntent;
        if (pendingIntent == null) {
            pendingIntent = notification.fullScreenIntent;
        }
        if (pendingIntent == null || pendingIntent.getIntent() == null) {
            OplusNotificationLogger.logNotificationZOOM(TAG, "calculateFlexibleWindowBounds null pendingIntent:" + pendingIntent);
            return null;
        }
        if (!z && expandableNotificationRow.mRowEx.getZoomBundle() != null && ((zoomBundle = expandableNotificationRow.mRowEx.getZoomBundle()) == null || zoomBundle.getInt("androidx.flexible.ResizeMode", 0) != 4)) {
            if (z2) {
                updateZoomViewVisible(expandableNotificationRow, false);
            }
            return expandableNotificationRow.mRowEx.getZoomBundle();
        }
        Bundle bundleCalculateFlexibleWindowBounds = HeadsUpToZoomUtils.calculateFlexibleWindowBounds(pendingIntent.getIntent(), this.context, expandableNotificationRow.getEntry().getSbn().getUserId());
        expandableNotificationRow.mRowEx.setZoomBundle(bundleCalculateFlexibleWindowBounds);
        if (!z2 || (bundleCalculateFlexibleWindowBounds != null && bundleCalculateFlexibleWindowBounds.getInt("androidx.flexible.ResizeMode", 0) == 4)) {
            return bundleCalculateFlexibleWindowBounds;
        }
        updateZoomViewVisible(expandableNotificationRow, false);
        return bundleCalculateFlexibleWindowBounds;
    }

    public ImageView getDragView() {
        return this.zoomDragView;
    }

    @Override // com.oplus.systemui.statusbar.notification.row.OplusExpandableViewExImpl
    public float getGroupExpandContentAlpha() {
        return this.groupExpandContentAlpha;
    }

    @Override // com.oplus.systemui.statusbar.notification.row.OplusExpandableViewExImpl
    public float getGroupExpandHeaderAlpha() {
        return this.groupExpandHeaderAlpha;
    }

    @Override // com.oplus.systemui.statusbar.notification.row.OplusExpandableViewExImpl
    public float getGroupExpansionProgress() {
        return this.groupExpansionProgress;
    }

    public boolean getHasHandleDownEventAnim() {
        return this.hasHandleDownEventAnim;
    }

    public boolean getIsNonblockableCustom() {
        NotificationEntry entry;
        EntryAdapter entryAdapter;
        ExpandableNotificationRow expandableNotificationRowM451getView = getView();
        if (expandableNotificationRowM451getView != null && (entry = expandableNotificationRowM451getView.getEntry()) != null) {
            ExpandableNotificationRow expandableNotificationRowM451getView2 = getView();
            boolean z = (expandableNotificationRowM451getView2 == null || (entryAdapter = expandableNotificationRowM451getView2.getEntryAdapter()) == null || entryAdapter.isBlockable()) ? false : true;
            NotificationChannel channel = entry.getChannel();
            boolean zIsImportanceLockedByCriticalDeviceFunction = channel != null ? channel.isImportanceLockedByCriticalDeviceFunction() : false;
            if (!OplusNotificationCommercializeControl.isChannelInWhiteList(this.context, entry)) {
                if (!z && zIsImportanceLockedByCriticalDeviceFunction) {
                    try {
                        List list = this.notificationManager.getNotificationChannelsForPackage(entry.getSbn().getPackageName(), entry.getSbn().getUid(), false).getList();
                        if (list != null) {
                            int size = list.size();
                            for (int i = 0; i < size; i++) {
                                Object obj = list.get(i);
                                NotificationChannel notificationChannel = obj instanceof NotificationChannel ? (NotificationChannel) obj : null;
                                if (notificationChannel != null && notificationChannel.isBlockable()) {
                                    return true;
                                }
                            }
                        }
                    } catch (RemoteException e) {
                        LogUtil.w$default(8, LogUtil.TAG_NOTIFICATION, "ExpandableNotificationRowExtImpl", e.getMessage());
                    }
                }
                return z;
            }
        }
        return false;
    }

    public boolean getIsZoomAnimating() {
        return this.isZoomAnimating;
    }

    public boolean getIsZoomBackUpHeadsUp() {
        return this.isZoomBackUpHeadsUp;
    }

    public Drawable getKeyguardStackingBlurDrawableForOverlay() {
        NotificationBackgroundView notificationBackgroundView;
        ExpandableNotificationRow expandableNotificationRowM451getView = getView();
        NotificationBackgroundViewExt ext = (expandableNotificationRowM451getView == null || (notificationBackgroundView = ((ActivatableNotificationView) expandableNotificationRowM451getView).mBackgroundNormal) == null) ? null : notificationBackgroundView.getExt();
        NotificationBackgroundViewExtImp notificationBackgroundViewExtImp = ext instanceof NotificationBackgroundViewExtImp ? (NotificationBackgroundViewExtImp) ext : null;
        if (notificationBackgroundViewExtImp == null) {
            return null;
        }
        KeyguardStackingBackgroundControl keyguardStackingBackgroundControl = notificationBackgroundViewExtImp.getKeyguardStackingBackgroundControl();
        if (keyguardStackingBackgroundControl.getTargetType() == 0) {
            return null;
        }
        Drawable tagStackBlurDrawable = keyguardStackingBackgroundControl.getTagStackBlurDrawable();
        return tagStackBlurDrawable == null ? notificationBackgroundViewExtImp.getOplusColorBg() : tagStackBlurDrawable;
    }

    public int getKeyguardStackingMaskCurrentAlpha() {
        KeyguardStackingBackgroundControl keyguardStackingBackgroundControl;
        NotificationBackgroundView notificationBackgroundView;
        ExpandableNotificationRow expandableNotificationRowM451getView = getView();
        NotificationBackgroundViewExt ext = (expandableNotificationRowM451getView == null || (notificationBackgroundView = ((ActivatableNotificationView) expandableNotificationRowM451getView).mBackgroundNormal) == null) ? null : notificationBackgroundView.getExt();
        NotificationBackgroundViewExtImp notificationBackgroundViewExtImp = ext instanceof NotificationBackgroundViewExtImp ? (NotificationBackgroundViewExtImp) ext : null;
        if (notificationBackgroundViewExtImp == null || (keyguardStackingBackgroundControl = notificationBackgroundViewExtImp.getKeyguardStackingBackgroundControl()) == null || keyguardStackingBackgroundControl.getTargetType() == 0) {
            return 0;
        }
        return keyguardStackingBackgroundControl.getCurrentStackMaskAlpha();
    }

    @Override // com.oplus.systemui.statusbar.notification.row.OplusExpandableViewExImpl
    public MaterialColorMode getMaterialColorMode() {
        return this.materialColorMode;
    }

    public int getMaxExpandedHeightCustom() {
        return ((Number) this.maxExpandedHeightCustom$delegate.getValue()).intValue();
    }

    public int getMaxSmallHeightCustom() {
        return ((Number) this.maxSmallHeightCustom$delegate.getValue()).intValue();
    }

    @Override // com.oplus.systemui.statusbar.notification.row.OplusActivatableNotificationViewExImpl, com.oplus.systemui.statusbar.notification.row.OplusExpandableOutlineViewExImpl, com.oplus.systemui.statusbar.notification.row.OplusExpandableViewExImpl
    public NotificationBackgroundView getNotificationBackgroundView() {
        NotificationBackgroundView notificationBackgroundView;
        ExpandableNotificationRow expandableNotificationRowM451getView = getView();
        if (expandableNotificationRowM451getView != null && (notificationBackgroundView = ((ActivatableNotificationView) expandableNotificationRowM451getView).mBackgroundNormal) != null) {
            return notificationBackgroundView;
        }
        ExpandableNotificationRow expandableNotificationRowM451getView2 = getView();
        if (expandableNotificationRowM451getView2 != null) {
            return expandableNotificationRowM451getView2.findViewById(2131362193);
        }
        return null;
    }

    @Override // com.oplus.systemui.statusbar.notification.row.OplusExpandableViewExImpl
    public NotificationBlurMode getNotificationBlurMode() {
        return this.notificationBlurMode;
    }

    public View.OnClickListener getOnClickListener() {
        return this.fakeClicker;
    }

    public String getPackageFromPendingIntent(ExpandableNotificationRow expandableNotificationRow) {
        ComponentName component;
        if (expandableNotificationRow == null || expandableNotificationRow.getEntry() == null || expandableNotificationRow.getEntry().getSbn() == null || expandableNotificationRow.getEntry().getSbn().getNotification() == null) {
            OplusNotificationLogger.logNotificationZOOM(TAG, "getPackageFromPendingIntent row null:" + expandableNotificationRow);
            return null;
        }
        PendingIntent defaultIntent = OpNotificationUtils.getDefaultIntent(expandableNotificationRow.getEntry().getSbn().getNotification());
        if (defaultIntent == null || defaultIntent.getIntent() == null || defaultIntent.getIntent().getComponent() == null) {
            OplusNotificationLogger.logNotificationZOOM(TAG, "getPackageFromPendingIntent pendingIntent null:" + defaultIntent);
            return null;
        }
        Intent intent = defaultIntent.getIntent();
        if (intent == null || (component = intent.getComponent()) == null) {
            return null;
        }
        return component.getPackageName();
    }

    public boolean getShouldEncrypted() {
        Boolean bool = this._shouldEncrypted;
        if (bool != null) {
            return bool.booleanValue();
        }
        return false;
    }

    public boolean getUseZoomRadius() {
        return this.useZoomRadius;
    }

    public long getWillLaunchTimestamp() {
        return this.willLaunchTimestamp;
    }

    public float getZoomBottomRadius() {
        return this.zoomBottomRadius;
    }

    public Bundle getZoomBundle() {
        return this.zoomBundle;
    }

    public TransitionAnimator.Interpolators getZoomInterpolators(ExpandableNotificationRow expandableNotificationRow) {
        double d = (expandableNotificationRow.getEntry() == null || expandableNotificationRow.getEntry().getSbn() == null || expandableNotificationRow.getEntry().getSbn().getNotification() == null) ? 0.0d : expandableNotificationRow.getEntry().getSbn().getNotification().extras.getFloat("key_zoom_current_speed_y", 0.0f);
        double d2 = d > 6000.0d ? 0.8d : 1.0d;
        return new TransitionAnimator.Interpolators(new COUISpringInterpolator(157.91d, d2, d, 1.0f, 6666.0f), new COUISpringInterpolator(157.91d, d2, d, 1.0f, 6666.0f), new COUISpringInterpolator(438.65d, 1.0d, 0.0d, 1.0f, 6666.0f), new COUISpringInterpolator(438.65d, 1.0d, 0.0d, 1.0f, 6666.0f));
    }

    public float getZoomTopRadius() {
        return this.zoomTopRadius;
    }

    public boolean isAllowResetTranslation(NotificationMenuRowPlugin notificationMenuRowPlugin) {
        return ((notificationMenuRowPlugin instanceof NotificationMenuRow) && ((NotificationMenuRow) notificationMenuRowPlugin).mIsUserTouching) ? false : true;
    }

    public final boolean isChildrenExpandedAnimating() {
        ExpandableNotificationRow expandableNotificationRowM451getView;
        ExpandableNotificationRow notificationParent;
        OplusExpandableNotificationRowEx oplusExpandableNotificationRowEx;
        ExpandableNotificationRow expandableNotificationRowM451getView2 = getView();
        if (expandableNotificationRowM451getView2 != null && expandableNotificationRowM451getView2.mIsSummaryWithChildren) {
            return this.isChildrenExpandedAnimating;
        }
        ExpandableNotificationRow expandableNotificationRowM451getView3 = getView();
        return (expandableNotificationRowM451getView3 == null || !expandableNotificationRowM451getView3.isChildInGroup() || (expandableNotificationRowM451getView = getView()) == null || (notificationParent = expandableNotificationRowM451getView.getNotificationParent()) == null || (oplusExpandableNotificationRowEx = notificationParent.mRowEx) == null || !((OplusExpandableNotificationRowExImpl) oplusExpandableNotificationRowEx).isChildrenExpandedAnimating()) ? false : true;
    }

    public final boolean isDragDownFromHeadsUpDisabled() {
        NotificationPanelViewControllerEx ext = this.notificationPanelViewController.getExt();
        ext.getClass();
        return ext instanceof NotificationPanelViewControllerExImp;
    }

    public boolean isExpandAnimatorRunning(ExpandableNotificationRow expandableNotificationRow) {
        PhysicsProperty physicsProperty = ExpandableView.HEIGHT_PROPERTY;
        Object tag = expandableNotificationRow.getTag(2131363352);
        if (tag == null) {
            return false;
        }
        ValueAnimator valueAnimator = tag instanceof ValueAnimator ? (ValueAnimator) tag : null;
        if (valueAnimator == null || !valueAnimator.isRunning()) {
            OplusPropertyData oplusPropertyData = tag instanceof OplusPropertyData ? (OplusPropertyData) tag : null;
            if (oplusPropertyData == null || !oplusPropertyData.isRunning()) {
                return false;
            }
        }
        if (LogUtil.getNormalDebug() && LogUtil.isTagValid(LogUtil.TAG_NOTIFICATION)) {
            OplusBlackScreenGestureCallback$.ExternalSyntheticOutline0.m(expandableNotificationRow.getMaxExpandHeight(), "Expand animator is running , maxExpandHeight = ", "OplusExpandableNotificationRowExImpl-->", "SystemUi--Notification");
        }
        return true;
    }

    public final boolean isHeadsUpView() {
        return ((HeadsUpContainerWindow) this.headsUpContainerWindow.get()).isHeadsUpView(getView());
    }

    public final boolean isOriginConversation() {
        NotificationEntry entry;
        ExpandableNotificationRow expandableNotificationRowM451getView = getView();
        return (expandableNotificationRowM451getView == null || (entry = expandableNotificationRowM451getView.getEntry()) == null || this.peopleNotificationIdentifier.getPeopleNotificationType(entry) < 2) ? false : true;
    }

    public final boolean isSplitShadeEnabled() {
        return this.notificationPanelViewController.mSplitShadeEnabled;
    }

    public void logLaunchAnimationState(LaunchAnimationParameters launchAnimationParameters, int i, ExpandableNotificationRow expandableNotificationRow) {
        if (!(launchAnimationParameters.getProgress() == 1.0f || launchAnimationParameters.getProgress() == 0.0f) || this.paramsProgress == launchAnimationParameters.getProgress()) {
            return;
        }
        float translationY = expandableNotificationRow != null ? expandableNotificationRow.getTranslationY() : 0.0f;
        this.paramsProgress = launchAnimationParameters.getProgress();
        if (LogUtil.getNormalDebug() && LogUtil.isTagValid(LogUtil.TAG_NOTIFICATION)) {
            int top = launchAnimationParameters.getTop();
            int notificationParentTop = launchAnimationParameters.getNotificationParentTop();
            float f = this.paramsProgress;
            StringBuilder sbM = FloatList$.ExternalSyntheticOutline0.m("top: ", i, top, "  paramsTop: ", " notificationStackTop: ");
            IconProvider$.ExternalSyntheticOutline0.m(translationY, notificationParentTop, " parentTranslationY: ", "  paramsProgress: ", sbM);
            VFXController$stopAnim$1$.ExternalSyntheticOutline0.m(sbM, f, "OplusExpandableNotificationRowExImpl-->", "SystemUi--Notification");
        }
    }

    public void onFinishInflate(ExpandableNotificationRow expandableNotificationRow) {
        this.zoomDragView = (ImageView) expandableNotificationRow.findViewById(2131362952);
    }

    public void onNotificationUnfold(NotificationEntry notificationEntry, boolean z) {
        Context applicationContext = ((NotificationDependOnCommon) this.notificationDependOnCommon.get()).getApplicationContext();
        Lock lock = NotificationStatisticUtil.NOTIFICATION_LOCK;
        if (notificationEntry != null && notificationEntry.getSbn() != null) {
            if (NotificationTrackHelper.getInstance().isFilterTrackEvent(applicationContext, notificationEntry.getSbn())) {
                LogUtil.d(LogUtil.TAG_NOTIFICATION, "NotificationStatisticUtil", "isFilterTrackEvent isFilter is true");
            } else {
                StatusBarNotification sbn = notificationEntry.getSbn();
                String string = sbn.getNotification().extras.getString("PushGlobalId", "");
                HashMap map = new HashMap();
                NotificationChannel channel = notificationEntry.getChannel();
                if (channel != null) {
                    String notificationType = NotificationStatisticUtil.getNotificationType(sbn);
                    String opushNotificationType = NotificationStatisticUtil.getOpushNotificationType(sbn);
                    LogUtil.d(LogUtil.TAG_NOTIFICATION, "NotificationStatisticUtil", "collectNotificationUnfoldType --> \n packageName : " + sbn.getPackageName() + "\n channelName : " + ((Object) channel.getName()) + "\n channleID : " + channel.getId() + "\n notificationID : " + sbn.getId() + "\n strPushGlobalId : " + string + "\n isGroupSummary: " + z + "\n notificationType: " + notificationType + "\n oPushNotificationType: " + opushNotificationType);
                    map.put("user_id", String.valueOf(sbn.getUser().getIdentifier()));
                    map.put("pkg", sbn.getPackageName());
                    map.put("channel_name", String.valueOf(channel.getName()));
                    map.put("channel_id", String.valueOf(channel.getId()));
                    map.put("notification_id", String.valueOf(sbn.getId()));
                    map.put("push_id", string);
                    map.put("label_status", ShadeExpansionHelperImpl.VAL_INVALID);
                    if (z) {
                        map.put("type", OplusNotifiEventLog.TYPE_GROUP);
                    } else {
                        map.put("type", "single");
                        map.put("notification_type", notificationType);
                        map.put("push_notification_type", opushNotificationType);
                    }
                    OplusTrackProxyUtil.onCommon(applicationContext, "20082", "notification_unfold", map);
                } else if (LogUtil.getNormalDebug()) {
                    LogUtil.d(LogUtil.TAG_NOTIFICATION, "NotificationStatisticUtil", "collectNotificationUnfoldType notificationChannel is null --> " + LogUtil.encryptionNotificationKey(sbn));
                }
            }
        }
        notificationEntry.getRow();
    }

    public final void onNotificationUpdated(boolean z) {
        OplusExpandableViewStateEx oplusExpandableViewStateEx;
        ExpandableNotificationRow expandableNotificationRowM451getView = getView();
        if (expandableNotificationRowM451getView != null) {
            if (LogUtil.getNormalDebug() && LogUtil.isTagValid(LogUtil.TAG_NOTIFICATION)) {
                Log.d("SystemUi--Notification", "OplusExpandableNotificationRowExImpl-->".concat("onNotificationUpdated: key:" + expandableNotificationRowM451getView.getKey() + ", materialColorMode=" + ((ExpandableView) expandableNotificationRowM451getView).mExpandableViewEx.getMaterialColorMode() + ", notificationBlurMode=" + ((ExpandableView) expandableNotificationRowM451getView).mExpandableViewEx.getNotificationBlurMode() + ", isInflate=" + z));
            }
            ((ExpandableView) expandableNotificationRowM451getView).mExpandableViewEx.setMaterialColorMode(MaterialColorMode.UNINITIATED);
            if (expandableNotificationRowM451getView.isChildInGroup() && (oplusExpandableViewStateEx = expandableNotificationRowM451getView.getViewState().mExpandableViewStateEx) != null) {
                ((ExpandableView) expandableNotificationRowM451getView).mExpandableViewEx.setMaterialColorMode(oplusExpandableViewStateEx.getMaterialColorMode());
            }
            if (z) {
                ((ExpandableView) expandableNotificationRowM451getView).mExpandableViewEx.setNotificationBlurMode(NotificationBlurMode.UNINITIATED);
            }
            if (expandableNotificationRowM451getView.mPinnedStatus.isPinned() && expandableNotificationRowM451getView.mIsHeadsUp) {
                ((NotificationMaterialColorEx) this.notificationMaterialColorEx.get()).applyColorModeForHeadsUp(expandableNotificationRowM451getView, true);
            }
        }
    }

    public final void onRemoveNotification() {
        ExpandableNotificationRow expandableNotificationRowM451getView = getView();
        Boolean boolValueOf = expandableNotificationRowM451getView != null ? Boolean.valueOf(expandableNotificationRowM451getView.isChildInGroup()) : null;
        Context applicationContext = ((NotificationDependOnCommon) this.notificationDependOnCommon.get()).getApplicationContext();
        ExpandableNotificationRow expandableNotificationRowM451getView2 = getView();
        NotificationStatisticUtil.onNotificationRemoved(applicationContext, expandableNotificationRowM451getView2 != null ? expandableNotificationRowM451getView2.getEntry() : null, 2, 1000, Intrinsics.areEqual(boolValueOf, Boolean.TRUE));
    }

    public void onZoomTransitionAnimationStart(ExpandableNotificationRow expandableNotificationRow) {
        ImageView dragView;
        if (this.isZoomAnimating) {
            OplusNotificationLogger.logNotificationZOOM(TAG, "onZoomTransitionAnimationStart row:" + expandableNotificationRow.mPinnedStatus.isPinned());
            OplusExpandableNotificationRowExImpl$onZoomTransitionAnimationStart$jankTask$1 oplusExpandableNotificationRowExImpl$onZoomTransitionAnimationStart$jankTask$1 = new OplusExpandableNotificationRowExImpl$onZoomTransitionAnimationStart$jankTask$1();
            oplusExpandableNotificationRowExImpl$onZoomTransitionAnimationStart$jankTask$1.$notification = expandableNotificationRow;
            VarHandle.storeStoreFence();
            ((Handler) this.mainHandler.get()).postDelayed(oplusExpandableNotificationRowExImpl$onZoomTransitionAnimationStart$jankTask$1, 64L);
            CentralSurfacesImpl centralSurfacesImpl = (CentralSurfacesImpl) DependencyEx.sDependency.getDependency(CentralSurfacesImpl.class);
            if (centralSurfacesImpl != null && (centralSurfacesImpl.getShadeViewController() instanceof NotificationPanelViewController)) {
                centralSurfacesImpl.getShadeViewController().instantCollapse();
            }
            if (expandableNotificationRow.mIsSummaryWithChildren && expandableNotificationRow.isGroupExpanded()) {
                OplusNotificationLogger.logNotificationZOOM(TAG, "onZoomTransitionAnimationStart collapsed when group is expanded");
                expandableNotificationRow.mExpandClickListener.onClick(expandableNotificationRow);
            }
            ViewBlurProxy blurProxyForView = this.viewBlurManager.getBlurProxyForView(((ActivatableNotificationView) expandableNotificationRow).mBackgroundNormal);
            if (blurProxyForView != null) {
                blurProxyForView.setBlurType(ViewBlurProxy.BlurType.BlurTypeNon.INSTANCE);
            }
            OplusExpandableNotificationRowEx oplusExpandableNotificationRowEx = expandableNotificationRow.mRowEx;
            if (oplusExpandableNotificationRowEx != null && (dragView = oplusExpandableNotificationRowEx.getDragView()) != null) {
                dragView.setVisibility(4);
            }
            this.useZoomRadius = true;
            OplusExpandableNotificationRowEx oplusExpandableNotificationRowEx2 = expandableNotificationRow.mRowEx;
            String packageFromPendingIntent = oplusExpandableNotificationRowEx2 != null ? oplusExpandableNotificationRowEx2.getPackageFromPendingIntent(expandableNotificationRow) : null;
            if (!TextUtils.isEmpty(packageFromPendingIntent)) {
                AnonymousClass1 anonymousClass1 = new AnonymousClass1();
                anonymousClass1.$notification = expandableNotificationRow;
                anonymousClass1.$pkg = packageFromPendingIntent;
                VarHandle.storeStoreFence();
                SystemUIThreadUtil.runOnWorkThread(anonymousClass1);
            }
            setUseZoomRadius(expandableNotificationRow, true);
        }
    }

    public void overlayChildContainerInflateFactory(ViewStub viewStub) {
        viewStub.getLayoutInflater().setFactory(LayoutInflaterFactoryC02891.INSTANCE);
    }

    public final void reInflateViews() {
        ImageView imageView = this.zoomDragView;
        if (imageView != null) {
            if (imageView != null) {
                imageView.setBackground(null);
            }
            ImageView imageView2 = this.zoomDragView;
            if (imageView2 != null) {
                imageView2.setBackgroundResource(2131233363);
            }
        }
    }

    public void removeGroupExpandAnimationListener(OplusExpandableNotificationRowEx.ExpandedAnimationStateListener expandedAnimationStateListener) {
        synchronized (this.groupExpandedAnimationStateListeners) {
            this.groupExpandedAnimationStateListeners.remove(expandedAnimationStateListener);
        }
    }

    public void resetNotificationForZoom(ExpandableNotificationRow expandableNotificationRow) {
        NotificationBackgroundViewExt ext;
        NotificationBackgroundView notificationBackgroundView = ((ActivatableNotificationView) expandableNotificationRow).mBackgroundNormal;
        if (notificationBackgroundView != null && (ext = notificationBackgroundView.getExt()) != null) {
            ext.setIcon((Drawable) null);
        }
        if (this.isZoomAnimating) {
            try {
                JankUtils.gfxSceneEnd(expandableNotificationRow.getContext(), new int[]{47});
                this.ambientState.getExt().setHeadTrackingHeight(0.0f);
                this.isZoomBackUpHeadsUp = false;
                this.zoomBundle = null;
                this.useZoomRadius = false;
                expandableNotificationRow.setScaleX(1.0f);
                RowAnimationStateEx rowAnimationStateEx = OplusExpandableViewStateExKt.getRowAnimationStateEx(expandableNotificationRow);
                if (rowAnimationStateEx != null) {
                    ((RowAnimationStateExImpl) rowAnimationStateEx).requestRowScale("headsup_zoom", ScaleState.Companion.getINVALID());
                }
                expandableNotificationRow.setContentAlpha(1.0f);
                expandableNotificationRow.setAlpha(1.0f);
                setUseZoomRadius(expandableNotificationRow, false);
                CentralSurfacesImpl centralSurfacesImpl = (CentralSurfacesImpl) DependencyEx.sDependency.getDependency(CentralSurfacesImpl.class);
                if (centralSurfacesImpl != null && (centralSurfacesImpl.getShadeViewController() instanceof NotificationPanelViewController)) {
                    NotificationPanelViewController shadeViewController = centralSurfacesImpl.getShadeViewController();
                    if (shadeViewController.isTracking()) {
                        shadeViewController.onTrackingStopped("resetNotificationForZoom", false);
                    }
                    KeyguardStateControllerImpl keyguardStateControllerImpl = (KeyguardStateController) Dependency.sDependency.getDependencyInner(KeyguardStateController.class);
                    if (keyguardStateControllerImpl != null && !keyguardStateControllerImpl.mShowing) {
                        if (LogUtil.getNormalDebug() && LogUtil.isTagValid(LogUtil.TAG_NOTIFICATION)) {
                            Log.d("SystemUi--Notification", "OplusExpandableNotificationRowExImpl-->".concat("resetNotificationForZoom ---> keyguardStateController.isShowing:" + keyguardStateControllerImpl.mShowing));
                        }
                        shadeViewController.instantCollapse();
                    }
                    shadeViewController.getExt().resetZoomAnimationParams();
                }
            } finally {
                this.isZoomAnimating = false;
            }
        }
    }

    public void resetXTranslation(ExpandableNotificationRow expandableNotificationRow) {
        ExpandableViewState viewState;
        if (expandableNotificationRow == null || (viewState = expandableNotificationRow.getViewState()) == null) {
            return;
        }
        viewState.setXTranslation(0.0f);
    }

    public final void restoreRoundness() {
        if (getView() != null) {
            Pair<Float, Float> pair = this.roundnessState;
            if (pair != null) {
                setRoundness(((Number) pair.getFirst()).floatValue(), ((Number) pair.getSecond()).floatValue());
                if (LogUtil.getNormalDebug() && LogUtil.isTagValid(LogUtil.TAG_NOTIFICATION)) {
                    Log.d("SystemUi--Notification", "OplusExpandableNotificationRowExImpl-->".concat("restoreRoundness topRoundness = " + pair.getFirst() + " , bottomRoundness = " + pair.getSecond()));
                }
            }
            this.roundnessState = null;
        }
    }

    public final void saveRoundness() {
        restoreRoundness();
        ExpandableNotificationRow expandableNotificationRowM451getView = getView();
        if (expandableNotificationRowM451getView != null) {
            this.roundnessState = new Pair<>(Float.valueOf(expandableNotificationRowM451getView.getTopRoundness()), Float.valueOf(expandableNotificationRowM451getView.getBottomRoundness()));
        }
    }

    public void setBoostFrameRate(boolean z) {
        if (this.isBoostFrameRate != z) {
            this.isBoostFrameRate = z;
            View view = (ExpandableNotificationRow) getView();
            if (view != null) {
                ((BoostFrameRateManager) DependencyEx.sDependency.getDependency(BoostFrameRateManager.class)).boostFrameRate(view, this.isBoostFrameRate);
            }
        }
    }

    public void setChildrenExpandedAnimating(boolean z) {
        if (this.isChildrenExpandedAnimating != z) {
            this.isChildrenExpandedAnimating = z;
            if (LogUtil.getNormalDebug() && LogUtil.isTagValid(LogUtil.TAG_NOTIFICATION)) {
                OplusLockIconView$.ExternalSyntheticOutline0.m("isChildrenExpandedAnimating: ", "OplusExpandableNotificationRowExImpl-->", "SystemUi--Notification", z);
            }
        }
        synchronized (this.groupExpandedAnimationStateListeners) {
            Iterator<T> it = this.groupExpandedAnimationStateListeners.iterator();
            while (it.hasNext()) {
                ((OplusNotificationChildrenContainerOverlayExImpl$groupExpandAnimListener$1) ((OplusExpandableNotificationRowEx.ExpandedAnimationStateListener) it.next())).onAnimationStateChanged(z);
            }
        }
    }

    @Override // com.oplus.systemui.statusbar.notification.row.OplusExpandableViewExImpl
    public void setGroupExpandContentAlpha(float f) {
        if (this.groupExpandContentAlpha == f) {
            return;
        }
        this.groupExpandContentAlpha = f;
        if ((f == 0.0f || f == 1.0f) && LogUtil.getNormalDebug() && LogUtil.isTagValid(LogUtil.TAG_NOTIFICATION)) {
            OplusPhysicsPropertyAnimator$.ExternalSyntheticOutline0.m("set groupExpandContentAlpha to ", f, "OplusExpandableNotificationRowExImpl-->", "SystemUi--Notification");
        }
        ExpandableNotificationRow expandableNotificationRowM451getView = getView();
        if (expandableNotificationRowM451getView == null || expandableNotificationRowM451getView.mIsSummaryWithChildren) {
            return;
        }
        expandableNotificationRowM451getView.getShowingLayout().setAlpha(f);
    }

    @Override // com.oplus.systemui.statusbar.notification.row.OplusExpandableViewExImpl
    public void setGroupExpandHeaderAlpha(float f) {
        NotificationChildrenContainer childrenContainer;
        NotificationChildrenContainerExt ext;
        if (this.groupExpandHeaderAlpha == f) {
            return;
        }
        this.groupExpandHeaderAlpha = f;
        if ((f == 0.0f || f == 1.0f) && LogUtil.getNormalDebug() && LogUtil.isTagValid(LogUtil.TAG_NOTIFICATION)) {
            OplusPhysicsPropertyAnimator$.ExternalSyntheticOutline0.m("set groupExpandHeaderAlpha to ", f, "OplusExpandableNotificationRowExImpl-->", "SystemUi--Notification");
        }
        ExpandableNotificationRow expandableNotificationRowM451getView = getView();
        if (expandableNotificationRowM451getView == null || !expandableNotificationRowM451getView.mIsSummaryWithChildren || (childrenContainer = expandableNotificationRowM451getView.getChildrenContainer()) == null || (ext = childrenContainer.getExt()) == null) {
            return;
        }
        ext.updateSummaryHeaderBlurRadius(f);
    }

    @Override // com.oplus.systemui.statusbar.notification.row.OplusExpandableViewExImpl
    public void setGroupExpansionProgress(float f) {
        NotificationChildrenContainer childrenContainer;
        if (this.groupExpansionProgress == f) {
            return;
        }
        this.groupExpansionProgress = f;
        if ((f == 0.0f || f == 1.0f) && LogUtil.getNormalDebug() && LogUtil.isTagValid(LogUtil.TAG_NOTIFICATION)) {
            OplusPhysicsPropertyAnimator$.ExternalSyntheticOutline0.m("set groupExpansionProgress to ", f, "OplusExpandableNotificationRowExImpl-->", "SystemUi--Notification");
        }
        ExpandableNotificationRow expandableNotificationRowM451getView = getView();
        if (expandableNotificationRowM451getView == null || !expandableNotificationRowM451getView.mIsSummaryWithChildren || (childrenContainer = expandableNotificationRowM451getView.getChildrenContainer()) == null) {
            return;
        }
        childrenContainer.mOverlayEx.onGroupExpansionProgressChange(f);
    }

    public void setHasHandleDownEventAnim(boolean z) {
        this.hasHandleDownEventAnim = z;
    }

    public void setIsZoomAnimating(boolean z) {
        this.isZoomAnimating = z;
    }

    public void setIsZoomBackUpHeadsUp(boolean z) {
        this.isZoomBackUpHeadsUp = z;
    }

    public void setJustClicked(boolean z) {
        ExpandableNotificationRow expandableNotificationRowM451getView;
        ExpandableNotificationRow notificationParent;
        ExpandableNotificationRow expandableNotificationRowM451getView2 = getView();
        if (expandableNotificationRowM451getView2 == null || !expandableNotificationRowM451getView2.isChildInGroup() || (expandableNotificationRowM451getView = getView()) == null || (notificationParent = expandableNotificationRowM451getView.getNotificationParent()) == null) {
            return;
        }
        notificationParent.setJustClicked(z);
    }

    public void setKeepGone(boolean z) {
        this.isKeepGone = z;
    }

    public void setLabelKey(String str, NotificationChildrenContainer notificationChildrenContainer, ViewStub viewStub) {
        NotificationChildrenContainerExt ext;
        if (notificationChildrenContainer == null) {
            viewStub.inflate();
        }
        if (notificationChildrenContainer == null || (ext = notificationChildrenContainer.getExt()) == null) {
            return;
        }
        ext.setLabelKey(str);
    }

    @Override // com.oplus.systemui.statusbar.notification.row.OplusExpandableViewExImpl
    public void setMaterialColorMode(MaterialColorMode materialColorMode) {
        if (this.materialColorMode != materialColorMode) {
            this.materialColorMode = materialColorMode;
            ExpandableNotificationRow expandableNotificationRowM451getView = getView();
            ExpandableNotificationRow expandableNotificationRow = expandableNotificationRowM451getView instanceof ExpandableNotificationRow ? expandableNotificationRowM451getView : null;
            if (expandableNotificationRow == null) {
                return;
            }
            ((NotificationMaterialColorEx) this.notificationMaterialColorEx.get()).applyColorModeForRow(expandableNotificationRow, materialColorMode);
        }
    }

    @Override // com.oplus.systemui.statusbar.notification.row.OplusExpandableViewExImpl
    public void setNotificationBlurMode(NotificationBlurMode notificationBlurMode) {
        NotificationChildrenContainerOverlayEx notificationChildrenContainerOverlayEx;
        NotificationBackgroundViewExt ext;
        NotificationBackgroundViewExt ext2;
        if (this.notificationBlurMode != notificationBlurMode) {
            this.notificationBlurMode = notificationBlurMode;
            ExpandableNotificationRow expandableNotificationRowM451getView = getView();
            ExpandableNotificationRow expandableNotificationRow = expandableNotificationRowM451getView instanceof ExpandableNotificationRow ? expandableNotificationRowM451getView : null;
            if (expandableNotificationRow == null) {
                return;
            }
            if (LogUtil.getNormalDebug() && LogUtil.isTagValid(LogUtil.TAG_NOTIFICATION)) {
                Log.d("SystemUi--Notification", "OplusExpandableNotificationRowExImpl-->".concat("set notificationBlurMode to " + notificationBlurMode + " for key=" + expandableNotificationRow.getKey()));
            }
            if (notificationBlurMode.getNoBlur()) {
                expandableNotificationRow.updateBackgroundColorsOfSelf();
            }
            NotificationBackgroundView notificationBackgroundView = ((ActivatableNotificationView) expandableNotificationRow).mBackgroundNormal;
            if (notificationBackgroundView != null && (ext2 = notificationBackgroundView.getExt()) != null) {
                ext2.notifyBlurParamChanged(getNotificationBlurMode());
            }
            ((NotificationMaterialColorEx) this.notificationMaterialColorEx.get()).updateColorOnModeChanged(expandableNotificationRow);
            if (expandableNotificationRow.mIsSummaryWithChildren) {
                for (ExpandableNotificationRow expandableNotificationRow2 : expandableNotificationRow.getAttachedChildren()) {
                    NotificationBackgroundView notificationBackgroundView2 = ((ActivatableNotificationView) expandableNotificationRow2).mBackgroundNormal;
                    if (notificationBackgroundView2 != null && (ext = notificationBackgroundView2.getExt()) != null) {
                        ext.notifyBlurParamChanged(getNotificationBlurMode());
                    }
                    ((NotificationMaterialColorEx) this.notificationMaterialColorEx.get()).updateColorOnModeChanged(expandableNotificationRow2);
                }
                NotificationChildrenContainer childrenContainer = expandableNotificationRow.getChildrenContainer();
                if (childrenContainer == null || (notificationChildrenContainerOverlayEx = childrenContainer.mOverlayEx) == null) {
                    return;
                }
                notificationChildrenContainerOverlayEx.onUiModeChanged();
            }
        }
    }

    public void setNotificationTime(NotificationContentView notificationContentView, NotificationEntry notificationEntry) {
        StatusBarNotification sbn;
        Notification notification;
        OplusNotificationEntryEx oplusNotificationEntryEx;
        FeatureOption.INSTANCE.getClass();
        if (FeatureOption.isExpRegion()) {
            if (notificationEntry == null || (sbn = notificationEntry.getSbn()) == null || (notification = sbn.getNotification()) == null || notificationContentView == null) {
                return;
            }
            notificationContentView.setNotificationWhen(notification.getWhen());
            return;
        }
        if (notificationEntry == null || (oplusNotificationEntryEx = notificationEntry.mNotificationEntryEx) == null) {
            return;
        }
        long updateTime = oplusNotificationEntryEx.getUpdateTime();
        if (notificationContentView != null) {
            notificationContentView.setNotificationWhen(updateTime);
        }
    }

    public void setOplusMcsBg(NotificationEntry notificationEntry, NotificationBackgroundView notificationBackgroundView) {
        NotificationBackgroundViewExt ext;
        if (notificationBackgroundView == null || (ext = notificationBackgroundView.getExt()) == null) {
            return;
        }
        ext.setOplusMcsBg(notificationEntry);
    }

    public final void setRoundness(float f, float f2) {
        RoundableState roundableState;
        RoundableState roundableState2;
        RoundableState roundableState3;
        if (f == f2) {
            ExpandableNotificationRow expandableNotificationRowM451getView = getView();
            if (expandableNotificationRowM451getView == null || (roundableState3 = expandableNotificationRowM451getView.getRoundableState()) == null) {
                return;
            }
            roundableState3.setRoundness(f, false);
            return;
        }
        ExpandableNotificationRow expandableNotificationRowM451getView2 = getView();
        if (expandableNotificationRowM451getView2 != null && (roundableState2 = expandableNotificationRowM451getView2.getRoundableState()) != null) {
            roundableState2.setTopRoundness(f, false);
        }
        ExpandableNotificationRow expandableNotificationRowM451getView3 = getView();
        if (expandableNotificationRowM451getView3 == null || (roundableState = expandableNotificationRowM451getView3.getRoundableState()) == null) {
            return;
        }
        roundableState.setBottomRoundness(f2, false);
    }

    public void setTransitionAlpha(float f) {
        ExpandableNotificationRow expandableNotificationRowM451getView;
        NotificationChildrenContainerExt ext;
        if (!NotificationMaterialColorExImpl.Companion.isSupported(this.context) || (expandableNotificationRowM451getView = getView()) == null || expandableNotificationRowM451getView.mPinnedStatus.isPinned() || expandableNotificationRowM451getView.mIsHeadsUp || expandableNotificationRowM451getView.getVisibility() != 0 || !expandableNotificationRowM451getView.mIsSummaryWithChildren) {
            return;
        }
        float alpha = expandableNotificationRowM451getView.getAlpha() * expandableNotificationRowM451getView.getTransitionAlpha();
        NotificationChildrenContainer childrenContainer = expandableNotificationRowM451getView.getChildrenContainer();
        if (childrenContainer == null || (ext = childrenContainer.getExt()) == null) {
            return;
        }
        ext.updateSummaryGroupBadgeShadow(alpha);
    }

    public void setTranslationX(float f, ExpandableNotificationRow expandableNotificationRow) {
    }

    public /* bridge */ /* synthetic */ void setTranslationY(float f, ExpandableNotificationRow expandableNotificationRow) {
        super.setTranslationY(f, expandableNotificationRow);
    }

    public void setUseZoomRadius(ExpandableNotificationRow expandableNotificationRow, boolean z) {
        if (this.useZoomRadius) {
            this.zoomBeginTopRadius = expandableNotificationRow.getTopCornerRadius();
            float bottomCornerRadius = expandableNotificationRow.getBottomCornerRadius();
            this.zoomBeginBottomRadius = bottomCornerRadius;
            if (bottomCornerRadius == 0.0f || this.zoomBeginTopRadius == 0.0f || ScrimUtil.isHighGaussianLevel(expandableNotificationRow.getContext())) {
                float dimensionPixelSize = expandableNotificationRow.getContext().getResources().getDimensionPixelSize(2131170626);
                this.zoomBeginTopRadius = dimensionPixelSize;
                this.zoomBeginBottomRadius = dimensionPixelSize;
            }
            this.zoomBottomRadius = (int) this.zoomBeginBottomRadius;
            this.zoomTopRadius = (int) this.zoomBeginTopRadius;
        }
        this.useZoomRadius = z;
    }

    public void setWillLaunchActivity(boolean z) {
        NotificationContentView privateLayout;
        NotificationContentViewExt notificationContentViewExt;
        if (z) {
            setWillLaunchTimestamp(SystemClock.elapsedRealtime());
        }
        ExpandableNotificationRow expandableNotificationRowM451getView = getView();
        if (expandableNotificationRowM451getView == null || (privateLayout = expandableNotificationRowM451getView.getPrivateLayout()) == null || (notificationContentViewExt = privateLayout.mExt) == null) {
            return;
        }
        notificationContentViewExt.setWillLaunchActivity(z);
    }

    public void setWillLaunchTimestamp(long j) {
        this.willLaunchTimestamp = j;
    }

    public void setZoomBundle(Bundle bundle) {
        this.zoomBundle = bundle;
    }

    public boolean setZoomExtraWidthForClipping(ExpandableNotificationRow expandableNotificationRow, int i, int i2) {
        if (!this.isZoomAnimating) {
            return false;
        }
        expandableNotificationRow.setExtraWidthForClipping(i - (expandableNotificationRow.getScaleX() * expandableNotificationRow.getWidth()));
        return true;
    }

    public boolean setZoomTranslationX(ExpandableNotificationRow expandableNotificationRow, float f, float f2) {
        if (!this.isZoomAnimating) {
            return false;
        }
        float scaleX = (((1.0f - expandableNotificationRow.getScaleX()) * expandableNotificationRow.getWidth()) / 2.0f) + (f2 - f);
        if (Math.abs(scaleX) < 1.0f) {
            return true;
        }
        expandableNotificationRow.setTranslationX(scaleX);
        return true;
    }

    public final boolean shouldSkipExpandBtnOnClick() {
        ExpandableNotificationRow expandableNotificationRowM451getView;
        View.OnClickListener clickerListener;
        OplusExpandableNotificationRowEx oplusExpandableNotificationRowEx;
        List attachedChildren;
        boolean zIsAnimatingToCollapse = ((OplusLockscreenShadeTransitionControllerEx) this.lockscreenShadeTransitionControllerEx.get()).isAnimatingToCollapse();
        boolean zIsCapsuleState = ((ILockScreenExtStateController) this.lockScreenExtStateController.get()).isCapsuleState();
        boolean zIsHeadsUpAnimatorRunning = ((OplusHeadsUpAnimator) ((HeadsUpAnimator) this.headsUpAnimator.get())).isHeadsUpAnimatorRunning();
        ExpandableNotificationRow expandableNotificationRowM451getView2 = getView();
        boolean z = false;
        if (expandableNotificationRowM451getView2 != null) {
            if (expandableNotificationRowM451getView2.mIsSummaryWithChildren && (attachedChildren = expandableNotificationRowM451getView2.getAttachedChildren()) != null && attachedChildren.size() == 1) {
                if (LogUtil.getNormalDebug() && LogUtil.isTagValid(LogUtil.TAG_NOTIFICATION)) {
                    Log.d("SystemUi--Notification", "OplusExpandableNotificationRowExImpl-->AttachedChildren is 1, skipExpandBtnOnClick");
                }
            } else if (expandableNotificationRowM451getView2.isChildInGroup()) {
                ExpandableNotificationRow notificationParent = expandableNotificationRowM451getView2.getNotificationParent();
                float groupExpansionProgress = (notificationParent == null || (oplusExpandableNotificationRowEx = notificationParent.mRowEx) == null) ? 0.0f : oplusExpandableNotificationRowEx.getGroupExpansionProgress();
                boolean zIsChildrenExpandedAnimating = ((OplusExpandableNotificationRowExImpl) expandableNotificationRowM451getView2.mRowEx).isChildrenExpandedAnimating();
                if (!expandableNotificationRowM451getView2.isGroupExpanded() ? !(!zIsChildrenExpandedAnimating || groupExpansionProgress <= 0.0f || groupExpansionProgress >= 1.0f) : !(!zIsChildrenExpandedAnimating || groupExpansionProgress <= 0.0f || groupExpansionProgress >= 0.8f)) {
                }
            }
            z = true;
        }
        if ((this.ambientState.mStatusBarState != 0 && (zIsAnimatingToCollapse || zIsCapsuleState)) || zIsHeadsUpAnimatorRunning || z) {
            if (LogUtil.getNormalDebug() && LogUtil.isTagValid(LogUtil.TAG_NOTIFICATION)) {
                Log.d("SystemUi--Notification", SystemUIApplicationImpl$.ExternalSyntheticOutline0.m(",isGroupExpandingSkip=", "OplusExpandableNotificationRowExImpl-->", TextFieldImplKt$.ExternalSyntheticOutline0.m("shouldSkipExpandBtnOnClick isAnimatingToCollapse = ", ",isCapsuleState = ", "  isHeadsUpAnimatorRunning = ", zIsAnimatingToCollapse, zIsCapsuleState), zIsHeadsUpAnimatorRunning, z));
            }
            return true;
        }
        boolean disablePullStatusBar = ((PanelPullHelper) this.pullHelper.get()).getDisablePullStatusBar(Boolean.valueOf(((OplusSeparateNotificationAndQSEx) this.separateNotificationAndQSEx.get()).enableSeparateNotificationAndQS()));
        if (disablePullStatusBar && (expandableNotificationRowM451getView = getView()) != null && (clickerListener = expandableNotificationRowM451getView.getClickerListener()) != null) {
            clickerListener.onClick(getView());
        }
        return disablePullStatusBar;
    }

    public boolean shouldUpdateBackgroundWhenRemove(NotificationChildrenContainer notificationChildrenContainer) {
        if (notificationChildrenContainer.getNotificationChildCount() != 1) {
            return false;
        }
        NotificationStackScrollLayoutExt notificationStackScrollLayoutExt = (NotificationStackScrollLayoutExt) DependencyEx.sDependency.getDependency(NotificationStackScrollLayoutExt.class);
        return notificationStackScrollLayoutExt == null || !((NotificationStackScrollLayoutExtImpl) notificationStackScrollLayoutExt).isClearAll;
    }

    public boolean snapBackIfNeeded(ExpandableNotificationRow expandableNotificationRow) {
        if (!expandableNotificationRow.mIsSummaryWithChildren) {
            return false;
        }
        NotificationMenuRow provider = expandableNotificationRow.getProvider();
        NotificationMenuRow notificationMenuRow = provider instanceof NotificationMenuRow ? provider : null;
        if (notificationMenuRow == null || !notificationMenuRow.isMenuVisible() || expandableNotificationRow.getTranslation() == 0.0f) {
            return false;
        }
        if (LogUtil.getNormalDebug() && LogUtil.isTagValid(LogUtil.TAG_NOTIFICATION)) {
            OplusPhysicsPropertyAnimator$.ExternalSyntheticOutline0.m("Snap child, because menu visible, translation=", expandableNotificationRow.getTranslation(), "OplusExpandableNotificationRowExImpl-->", "SystemUi--Notification");
        }
        ((NotificationStackScrollLayoutControllerExt) this.nsslControllerExt.get()).snapChildIfNeeded(expandableNotificationRow, true, 0.0f);
        return true;
    }

    public void updateDragViewLocation(ImageView imageView, int i, ExpandableNotificationRow expandableNotificationRow) {
        if (imageView != null) {
            imageView.setTranslationY((i - imageView.getMeasuredHeight()) - imageView.getContext().getResources().getDimensionPixelSize(2131170687));
            getView();
        }
    }

    public void updateDragViewState(ExpandableNotificationRow expandableNotificationRow) {
        if (expandableNotificationRow == null || expandableNotificationRow.getEntry() == null) {
            return;
        }
        if (expandableNotificationRow.mRowEx.getDragView() == null) {
            OplusNotificationLogger.logNotificationZOOM(TAG, "updateDragViewState dragView null");
            return;
        }
        StatusBarNotification sbn = expandableNotificationRow.getEntry().getSbn();
        boolean zIsPinned = expandableNotificationRow.mPinnedStatus.isPinned();
        PendingIntent defaultIntent = OpNotificationUtils.getDefaultIntent(sbn.getNotification());
        boolean z = false;
        if (zIsPinned && OpNotificationUtils.isSupportZoonByIntent(this.context, expandableNotificationRow.getEntry(), defaultIntent, true, false)) {
            z = true;
        }
        Intent intent = defaultIntent != null ? defaultIntent.getIntent() : null;
        StringBuilder sbM = TextFieldImplKt$.ExternalSyntheticOutline0.m("updateDragViewState showZoom=", " isPinned=", " pendingIntent=", z, zIsPinned);
        sbM.append(intent);
        OplusNotificationLogger.logNotificationZOOM(TAG, sbM.toString());
        updateZoomViewVisible(expandableNotificationRow, z);
    }

    /* JADX WARN: Code duplicated, block: B:70:0x00e9  */
    public final boolean updateEncryptPublicContentView() {
        Resources resources;
        String quantityString;
        ExpandableNotificationRow expandableNotificationRowM451getView;
        NotificationChildrenContainer childrenContainer;
        ColorStateList textColors;
        NotificationEntry entry;
        StatusBarNotification sbn;
        Notification notification;
        Resources resources2;
        NotificationEntry entry2;
        NotificationContentView privateLayout;
        NotificationContentView publicLayout;
        NotificationEntry entry3;
        StatusBarNotification sbn2;
        Notification notification2;
        ExpandableNotificationRow expandableNotificationRowM451getView2 = getView();
        Integer numValueOf = null;
        boolean zIsColorized = false;
        if ((expandableNotificationRowM451getView2 != null ? expandableNotificationRowM451getView2.getPublicLayout() : null) == null) {
            return false;
        }
        ExpandableNotificationRow expandableNotificationRowM451getView3 = getView();
        if (((expandableNotificationRowM451getView3 == null || (entry3 = expandableNotificationRowM451getView3.getEntry()) == null || (sbn2 = entry3.getSbn()) == null || (notification2 = sbn2.getNotification()) == null) ? null : notification2.publicVersion) != null) {
            return false;
        }
        ExpandableNotificationRow expandableNotificationRowM451getView4 = getView();
        View contractedChild = (expandableNotificationRowM451getView4 == null || (publicLayout = expandableNotificationRowM451getView4.getPublicLayout()) == null) ? null : publicLayout.getContractedChild();
        ExpandableNotificationRow expandableNotificationRowM451getView5 = getView();
        View contractedChild2 = (expandableNotificationRowM451getView5 == null || (privateLayout = expandableNotificationRowM451getView5.getPrivateLayout()) == null) ? null : privateLayout.getContractedChild();
        TextView textView = contractedChild != null ? (TextView) contractedChild.findViewById(R.id.transition_overlay_view_tag) : null;
        if (textView == null) {
            textView = null;
        }
        TextView textView2 = contractedChild2 != null ? (TextView) contractedChild2.findViewById(R.id.title) : null;
        if (textView2 == null) {
            textView2 = null;
        }
        TextView textView3 = contractedChild != null ? (TextView) contractedChild.findViewById(R.id.blocksDescendants) : null;
        if (this.titleTypeface == null) {
            this.titleTypeface = textView2 != null ? textView2.getTypeface() : null;
        }
        if (textView == null || textView3 == null) {
            return false;
        }
        textView.setVisibility(0);
        ExpandableNotificationRow expandableNotificationRowM451getView6 = getView();
        if (Intrinsics.areEqual((expandableNotificationRowM451getView6 == null || (entry2 = expandableNotificationRowM451getView6.getEntry()) == null) ? null : Boolean.valueOf(this.privacyHelper.isAppLocked(entry2.getSbn().getPackageName(), entry2.getSbn().getUid())), Boolean.TRUE)) {
            ExpandableNotificationRow expandableNotificationRowM451getView7 = getView();
            if (expandableNotificationRowM451getView7 == null || (resources2 = expandableNotificationRowM451getView7.getResources()) == null) {
                quantityString = null;
            } else {
                quantityString = resources2.getString(2131954819);
            }
        } else {
            ExpandableNotificationRow expandableNotificationRowM451getView8 = getView();
            int untruncatedChildCount = (expandableNotificationRowM451getView8 == null || !expandableNotificationRowM451getView8.mIsSummaryWithChildren || (expandableNotificationRowM451getView = getView()) == null || (childrenContainer = expandableNotificationRowM451getView.getChildrenContainer()) == null) ? 1 : childrenContainer.getUntruncatedChildCount();
            ExpandableNotificationRow expandableNotificationRowM451getView9 = getView();
            if (expandableNotificationRowM451getView9 == null || (resources = expandableNotificationRowM451getView9.getResources()) == null) {
                quantityString = null;
            } else {
                quantityString = resources.getQuantityString(2131820564, untruncatedChildCount, Integer.valueOf(untruncatedChildCount));
            }
        }
        if (!Intrinsics.areEqual(textView.getText(), quantityString)) {
            textView3.setTypeface(this.titleTypeface);
            textView3.setTextSize(0, textView.getTextSize());
            textView.setTypeface(Typeface.DEFAULT);
            textView.setText(quantityString);
            ExpandableNotificationRow expandableNotificationRowM451getView10 = getView();
            if (expandableNotificationRowM451getView10 != null && (entry = expandableNotificationRowM451getView10.getEntry()) != null && (sbn = entry.getSbn()) != null && (notification = sbn.getNotification()) != null) {
                zIsColorized = notification.isColorized();
            }
            if (zIsColorized) {
                ExpandableNotificationRow expandableNotificationRowM451getView11 = getView();
                NotificationContentView privateLayout2 = expandableNotificationRowM451getView11 != null ? expandableNotificationRowM451getView11.getPrivateLayout() : null;
                TextView textView4 = privateLayout2 != null ? (TextView) privateLayout2.findViewById(R.id.blocksDescendants) : null;
                if (textView4 != null && (textColors = textView4.getTextColors()) != null) {
                    numValueOf = Integer.valueOf(textColors.getDefaultColor());
                }
                if (numValueOf != null) {
                    textView3.setTextColor(numValueOf.intValue());
                    textView.setTextColor(numValueOf.intValue());
                    TextView textView5 = (TextView) contractedChild.findViewById(R.id.typeWindowContentChanged);
                    if (textView5 != null) {
                        textView5.setTextColor(numValueOf.intValue());
                    }
                }
            }
        }
        return true;
    }

    public void updateExpandButtons(NotificationContentView notificationContentView, boolean z) {
        if (notificationContentView != null) {
            notificationContentView.updateExpandButtonsDuringLayout(z, false);
        }
    }

    public void updateLayoutExpanded(boolean z, boolean z2, GroupExpansionManager groupExpansionManager, NotificationEntry notificationEntry) {
        if (z && z2) {
            if (LogUtil.getNormalDebug() && LogUtil.isTagValid(LogUtil.TAG_NOTIFICATION)) {
                Log.d("SystemUi--Notification", "OplusExpandableNotificationRowExImpl-->updateLayoutExpanded");
            }
            ((GroupExpansionManagerImpl) groupExpansionManager).setGroupExpanded(notificationEntry.getRow().getEntryAdapter(), false);
            ExpandableNotificationRow row = notificationEntry.getRow();
            if (row != null) {
                this.headsUpManagerPhone.setExpanded(notificationEntry.getKey(), row, false);
            }
        }
    }

    public final void updateShouldEncrypted() {
        NotificationEntry entry;
        ExpandableNotificationRow expandableNotificationRowM451getView = getView();
        this._shouldEncrypted = (expandableNotificationRowM451getView == null || (entry = expandableNotificationRowM451getView.getEntry()) == null) ? null : Boolean.valueOf(PrivacyHelper.needEncrypted$default(this.privacyHelper, entry.getSbn().getPackageName(), entry.getSbn().getUid(), entry.getKey(), entry, 16));
    }

    public void updateSnoozeItem(NotificationMenuRowPlugin notificationMenuRowPlugin) {
        if (notificationMenuRowPlugin instanceof NotificationMenuRow) {
            NotificationMenuRowExt notificationMenuRowExt = ((NotificationMenuRow) notificationMenuRowPlugin).mNotificationMenuRowExt;
            boolean z = this.notificationCenterHelper.isShowNotificationSnooze;
            notificationMenuRowExt.getClass();
        }
    }

    public final void updateZoomViewVisible(ExpandableNotificationRow expandableNotificationRow, boolean z) {
        if (!z) {
            resetNotificationForZoom(expandableNotificationRow);
        }
        if (z == this.isZoomDragable) {
            return;
        }
        this.isZoomDragable = z;
        OplusExpandableNotificationRowEx oplusExpandableNotificationRowEx = expandableNotificationRow.mRowEx;
        if (z) {
            ImageView dragView = oplusExpandableNotificationRowEx.getDragView();
            if (dragView != null) {
                dragView.setVisibility(0);
                return;
            }
            return;
        }
        ImageView dragView2 = oplusExpandableNotificationRowEx.getDragView();
        if (dragView2 != null) {
            dragView2.setVisibility(8);
        }
    }

    public void updateLayoutExpanded(boolean z, boolean z2, GroupExpansionManager groupExpansionManager, EntryAdapter entryAdapter) {
        if (z && z2) {
            if (LogUtil.getNormalDebug() && LogUtil.isTagValid(LogUtil.TAG_NOTIFICATION)) {
                Log.d("SystemUi--Notification", "OplusExpandableNotificationRowExImpl-->updateLayoutExpanded");
            }
            ((GroupExpansionManagerImpl) groupExpansionManager).setGroupExpanded(entryAdapter, false);
            ExpandableNotificationRow row = entryAdapter.getRow();
            if (row != null) {
                this.headsUpManagerPhone.setExpanded(entryAdapter.getKey(), row, false);
            }
        }
    }
}
