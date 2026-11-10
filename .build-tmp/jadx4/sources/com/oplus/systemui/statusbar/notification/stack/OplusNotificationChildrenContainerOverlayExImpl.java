package com.oplus.systemui.statusbar.notification.stack;

import android.app.Notification;
import android.content.Context;
import android.content.res.Resources;
import android.graphics.Canvas;
import android.graphics.Rect;
import android.graphics.drawable.Drawable;
import android.os.Build;
import android.service.notification.StatusBarNotification;
import android.util.Log;
import android.util.MathUtils;
import android.view.Choreographer;
import android.view.LayoutInflater;
import android.view.NotificationHeaderView;
import android.view.View;
import android.view.ViewGroup;
import android.view.ViewParent;
import android.view.ViewTreeObserver;
import android.widget.LinearLayout;
import android.widget.TextView;
import androidx.appcompat.graphics.drawable.DrawerArrowDrawable$;
import androidx.collection.FloatList$;
import androidx.compose.material3.internal.TextFieldImplKt$;
import androidx.compose.ui.spatial.RectList$;
import androidx.constraintlayout.widget.ConstraintSet$;
import androidx.core.view.ViewGroupKt;
import androidx.exifinterface.media.ExifInterface$;
import androidx.fragment.app.Fragment$5$;
import androidx.recyclerview.widget.ChildHelper$;
import androidx.room.util.CursorUtil$;
import androidx.tracing.Trace;
import com.android.compose.nestedscroll.PriorityNestedScrollConnection$;
import com.android.internal.widget.CachingIconView;
import com.android.keyguard.logging.KeyguardLogger$;
import com.android.launcher3.icons.IconProvider$;
import com.android.mechanics.effects.VerticalTactileSurfaceRevealEffect$;
import com.android.systemui.application.impl.SystemUIApplicationImpl$;
import com.android.systemui.keyboard.backlight.ui.view.KeyboardBacklightDialog$RootProperties$;
import com.android.systemui.media.controls.domain.pipeline.MediaTimeoutListener$PlaybackStateListener$;
import com.android.systemui.notification.stackednotification.NotificationGroupSplitEx;
import com.android.systemui.panelanimation.PanelAnimationEx;
import com.android.systemui.plugins.statusbar.NotificationMenuRowPlugin;
import com.android.systemui.shade.NotificationPanelViewController$;
import com.android.systemui.statusbar.SysuiStatusBarStateController;
import com.android.systemui.statusbar.notification.OplusPhysicsProperty;
import com.android.systemui.statusbar.notification.OplusPhysicsPropertyAnimator;
import com.android.systemui.statusbar.notification.OplusPropertyData;
import com.android.systemui.statusbar.notification.animation.ScaleState;
import com.android.systemui.statusbar.notification.collection.NotificationEntry;
import com.android.systemui.statusbar.notification.collection.OplusNotificationEntryEx;
import com.android.systemui.statusbar.notification.collection.OplusResetContextFontScaleAndDensity;
import com.android.systemui.statusbar.notification.collection.coordinator.VisualStabilityCoordinator;
import com.android.systemui.statusbar.notification.row.ActivatableNotificationView;
import com.android.systemui.statusbar.notification.row.ExpandableNotificationRow;
import com.android.systemui.statusbar.notification.row.HybridConversationNotificationView;
import com.android.systemui.statusbar.notification.row.HybridMetricNotificationView;
import com.android.systemui.statusbar.notification.row.HybridNotificationView;
import com.android.systemui.statusbar.notification.row.NotificationBackgroundView;
import com.android.systemui.statusbar.notification.row.NotificationBackgroundViewExt;
import com.android.systemui.statusbar.notification.row.NotificationContentView;
import com.android.systemui.statusbar.notification.row.OplusExpandableNotificationRowEx;
import com.android.systemui.statusbar.notification.row.SingleLineViewInflater;
import com.android.systemui.statusbar.notification.row.ui.viewbinder.SingleLineViewBinder;
import com.android.systemui.statusbar.notification.row.ui.viewmodel.SingleLineViewModel;
import com.android.systemui.statusbar.notification.row.ui.viewmodel.SingleLineViewPayload;
import com.android.systemui.statusbar.notification.row.wrapper.NotificationViewWrapper;
import com.android.systemui.statusbar.notification.stack.ExpandableViewState;
import com.android.systemui.statusbar.notification.stack.NotificationChildrenContainer;
import com.android.systemui.statusbar.notification.stack.NotificationChildrenContainerOverlayEx;
import com.android.systemui.statusbar.notification.stack.NotificationStackScrollLayout;
import com.android.systemui.statusbar.notification.stack.NotificationStackScrollLayoutController;
import com.android.systemui.statusbar.notification.stack.ViewState;
import com.android.systemui.statusbar.phone.ScrimControllerEx;
import com.android.systemui.statusbar.pipeline.mobile.data.MobileInputLogger$;
import com.oplus.posteffect.params.GradientStrokeLineParams;
import com.oplus.posteffect.params.InnerShadowParams;
import com.oplus.systemui.aod.display.OplusAODRefreshRateController$RequestRefreshRateRunnable$;
import com.oplus.systemui.notification.aod.OplusPanoramicAodCoreStartable;
import com.oplus.systemui.notification.aod.OplusPanoramicAodRepository;
import com.oplus.systemui.notification.blur.NotificationPlatFormBlurParamsManager;
import com.oplus.systemui.notification.blur.ViewBlurManager;
import com.oplus.systemui.notification.color.MaterialColorManager;
import com.oplus.systemui.notification.lockscreen.stack.OplusLockscreenShadeTransitionControllerExImpl;
import com.oplus.systemui.notification.row.material.NotificationBlurMode;
import com.oplus.systemui.notification.row.material.NotificationMaterialColorEx;
import com.oplus.systemui.notification.row.stacking.KeyguardStackingBackgroundControl;
import com.oplus.systemui.notification.stackednotification.olive.GroupExpansionAnimParams;
import com.oplus.systemui.qs.observer.SuperPowerSaveSettingsObserver;
import com.oplus.systemui.separate.OplusSeparateNotificationAndQSState;
import com.oplus.systemui.separate.animation.OplusSlidSwitchAnimation;
import com.oplus.systemui.statusbar.notification.animation.RowAnimationStateExImpl;
import com.oplus.systemui.statusbar.notification.row.NotificationBackgroundViewExtImp;
import com.oplus.systemui.statusbar.notification.row.OplusExpandableNotificationRowExImpl;
import com.oplus.systemui.statusbar.notification.view.GroupOverlayContainerView;
import com.oplusos.systemui.common.blurability.DebugToolKt;
import com.oplusos.systemui.common.blurability.ViewBlurProxy;
import com.oplusos.systemui.common.blurability.drawable.AutoBlurDrawable;
import com.oplusos.systemui.common.blurability.platformblur.PlatformBlurDrawable;
import com.oplusos.systemui.common.util.LogUtil;
import com.oplusos.systemui.common.util.ScrimUtil;
import dagger.Lazy;
import java.lang.invoke.VarHandle;
import java.util.List;
import kotlin.LazyKt__LazyJVMKt;
import kotlin.Result;
import kotlin.ResultKt;
import kotlin.Unit;
import kotlin.collections.CollectionsKt__CollectionsKt;
import kotlin.collections.CollectionsKt___CollectionsKt;
import kotlin.jvm.internal.Intrinsics;
import kotlin.sequences.Sequence;
import kotlin.sequences.SequencesKt___SequencesKt;

/* JADX INFO: compiled from: go/retraceme fd74bf9514dd0739b92d67a6d48a51eb10aaf8df760d83496b8dcfcf83e5f698 */
/* JADX INFO: loaded from: /home/starlight/Projects/Starlight Ports/.build-tmp/classes4.dex */
public final class OplusNotificationChildrenContainerOverlayExImpl extends NotificationChildrenContainerOverlayEx {
    private static final String TAG = "OplusNCCOverlayEx";
    private Drawable aodDrawable;
    public int baseClipBottom;
    public int cachedFirstChildHeight;
    private final Context context;
    private ViewGroup expandGroupHeader;
    public int expandGroupHeaderTranslationWhenCollapsed;
    private NotificationGroupSplitEx.ExpansionFinishedState expansionFinishedState;
    public int groupCollapsedTopPadding;
    private final OplusNotificationChildrenContainerOverlayExImpl$groupExpandAnimListener$1 groupExpandAnimListener;
    public boolean hasPendingRefreshTask;
    private ViewGroup iconContainer;
    public boolean isGroupExpandAnimating;
    public boolean isProgressiveContainerRegistered;
    public boolean isUserLocked;
    public int lastInnerShadowHeight;
    private Float lastLoggedGroupOverlayAlpha;
    private Float lastLoggedOverlayClipProgress;
    public int lastWidth;
    private final Lazy lockscreenShadeTransitionController;
    private final kotlin.Lazy materialColorChangeCallback$delegate;
    private final Lazy materialColorManager;
    public float maxCornerRadius;
    public int normalColor;
    private final Lazy nsslController;
    private final OplusPanoramicAodRepository oplusPanoramicAodRepository;
    private final Lazy oplusResetContextFontScaleAndDensity;
    private ViewGroup opusCollapsedGroupHeader;
    public int overlayActualHeight;
    private GroupOverlayContainerView overlayContainer;
    private final Lazy panelAnimationEx;
    private final Lazy panelViewPagerController;
    private final Runnable pendingRefreshRunnable;
    private ViewTreeObserver.OnPreDrawListener preDrawListener;
    private Boolean savedClipChildren;
    private Boolean savedClipToPadding;
    private final OplusSeparateNotificationAndQSState separateNotificationAndQSState;
    private final Lazy slidSwitchAnimation;
    private final Rect stackMaskSavedBounds;
    private final Lazy statusBarStateController;
    private final Lazy viewBlurManager;
    private final VisualStabilityCoordinator visualStabilityCoordinator;
    public static final Companion Companion = new Companion();
    private static final OplusPhysicsProperty[] needForceSetFinalAnimatableProperties = {OplusPhysicsPropertyAnimator.HEIGHT_PROPERTY, OplusPhysicsPropertyAnimator.Y_TRANSLATION};
    private final kotlin.Lazy notificationMaterialColorEx$delegate = LazyKt__LazyJVMKt.lazy(new OplusNotificationChildrenContainerOverlayExImpl$$ExternalSyntheticLambda0());
    private final HybridNotificationView[] viewCache = new HybridNotificationView[3];
    private String pendingRefreshReason = "";

    /* JADX INFO: compiled from: go/retraceme fd74bf9514dd0739b92d67a6d48a51eb10aaf8df760d83496b8dcfcf83e5f698 */
    public final class Companion {
        public final OplusPhysicsProperty[] getNeedForceSetFinalAnimatableProperties() {
            return OplusNotificationChildrenContainerOverlayExImpl.needForceSetFinalAnimatableProperties;
        }
    }

    /* JADX INFO: renamed from: com.oplus.systemui.statusbar.notification.stack.OplusNotificationChildrenContainerOverlayExImpl$onChildRemove$1, reason: invalid class name */
    /* JADX INFO: compiled from: go/retraceme fd74bf9514dd0739b92d67a6d48a51eb10aaf8df760d83496b8dcfcf83e5f698 */
    public final class AnonymousClass1 implements Runnable {
        public final /* synthetic */ int $r8$classId;
        public /* synthetic */ Object this$0;

        public /* synthetic */ AnonymousClass1(int i) {
            this.$r8$classId = i;
        }

        @Override // java.lang.Runnable
        public final void run() {
            NotificationEntry entry;
            OplusNotificationEntryEx ex;
            NotificationEntry entry2;
            OplusNotificationEntryEx ex2;
            switch (this.$r8$classId) {
                case 0:
                    OplusNotificationChildrenContainerOverlayExImpl.access$requestRebuildIfSingleChild((OplusNotificationChildrenContainerOverlayExImpl) this.this$0);
                    return;
                case 1:
                    Trace.beginSection("OplusNCCOverlayEx pendingRefreshRunnable");
                    try {
                        OplusNotificationChildrenContainerOverlayExImpl oplusNotificationChildrenContainerOverlayExImpl = (OplusNotificationChildrenContainerOverlayExImpl) this.this$0;
                        if (oplusNotificationChildrenContainerOverlayExImpl.hasPendingRefreshTask) {
                            oplusNotificationChildrenContainerOverlayExImpl.hasPendingRefreshTask = false;
                            ExpandableNotificationRow containingNotification = oplusNotificationChildrenContainerOverlayExImpl.getChildrenContainer().getContainingNotification();
                            if (containingNotification != null && containingNotification.isAttachedToWindow()) {
                                OplusNotificationChildrenContainerOverlayExImpl oplusNotificationChildrenContainerOverlayExImpl2 = (OplusNotificationChildrenContainerOverlayExImpl) this.this$0;
                                oplusNotificationChildrenContainerOverlayExImpl2.refreshOverlayContent(containingNotification, "batched-" + oplusNotificationChildrenContainerOverlayExImpl2.pendingRefreshReason);
                                break;
                            }
                        }
                        return;
                    } finally {
                        android.os.Trace.endSection();
                    }
                case 2:
                    if (LogUtil.getNormalDebug() && LogUtil.isTagValid(LogUtil.TAG_NOTIFICATION)) {
                        Log.d("SystemUi--Notification", "OplusNCCOverlayEx-->".concat("request notification list rebuild, reason=onChildRemove"));
                    }
                    OplusNotificationChildrenContainerOverlayExImpl oplusNotificationChildrenContainerOverlayExImpl3 = (OplusNotificationChildrenContainerOverlayExImpl) this.this$0;
                    Companion companion = OplusNotificationChildrenContainerOverlayExImpl.Companion;
                    ExpandableNotificationRow expandableNotificationRow = (ExpandableNotificationRow) CollectionsKt___CollectionsKt.firstOrNull(oplusNotificationChildrenContainerOverlayExImpl3.getChildrenContainer().getAttachedChildren());
                    if (expandableNotificationRow != null && (entry2 = expandableNotificationRow.getEntry()) != null && (ex2 = entry2.getEx()) != null) {
                        ex2.setSuppressPromotionAnimations(true);
                    }
                    ExpandableNotificationRow containingNotification2 = ((OplusNotificationChildrenContainerOverlayExImpl) this.this$0).getChildrenContainer().getContainingNotification();
                    if (containingNotification2 != null && (entry = containingNotification2.getEntry()) != null && (ex = entry.getEx()) != null) {
                        ex.setSuppressPromotionAnimations(true);
                    }
                    ((OplusNotificationChildrenContainerOverlayExImpl) this.this$0).visualStabilityCoordinator.setGroupNeedsPrune(true);
                    ((NotificationStackScrollLayoutController) ((OplusNotificationChildrenContainerOverlayExImpl) this.this$0).nsslController.get()).mNotifPipeline.requestListRebuild("onChildRemove");
                    return;
                default:
                    ((GroupOverlayContainerView) this.this$0).requestLayout();
                    return;
            }
        }
    }

    public static Unit $r8$lambda$eJI6i1Z0Lf9t8pUWlNjtw7CtJh8(OplusNotificationChildrenContainerOverlayExImpl oplusNotificationChildrenContainerOverlayExImpl, Canvas canvas) {
        ExpandableNotificationRow containingNotification;
        Drawable keyguardStackingBlurDrawableForOverlay;
        if (oplusNotificationChildrenContainerOverlayExImpl.shouldDrawStackMask() && (containingNotification = oplusNotificationChildrenContainerOverlayExImpl.getChildrenContainer().getContainingNotification()) != null && (keyguardStackingBlurDrawableForOverlay = containingNotification.mRowEx.getKeyguardStackingBlurDrawableForOverlay()) != null) {
            int keyguardStackingMaskCurrentAlpha = containingNotification.mRowEx.getKeyguardStackingMaskCurrentAlpha();
            GroupOverlayContainerView groupOverlayContainerView = oplusNotificationChildrenContainerOverlayExImpl.overlayContainer;
            if (groupOverlayContainerView == null) {
                return Unit.INSTANCE;
            }
            keyguardStackingBlurDrawableForOverlay.copyBounds(oplusNotificationChildrenContainerOverlayExImpl.stackMaskSavedBounds);
            GroupOverlayContainerView groupOverlayContainerView2 = oplusNotificationChildrenContainerOverlayExImpl.overlayContainer;
            int clipBottomAmount = groupOverlayContainerView2 != null ? groupOverlayContainerView2.getClipBottomAmount() : 0;
            int alpha = keyguardStackingBlurDrawableForOverlay.getAlpha();
            try {
                keyguardStackingBlurDrawableForOverlay.setBounds(0, 0, groupOverlayContainerView.getWidth(), groupOverlayContainerView.getHeight() - clipBottomAmount);
                keyguardStackingBlurDrawableForOverlay.setAlpha(keyguardStackingMaskCurrentAlpha);
                keyguardStackingBlurDrawableForOverlay.draw(canvas);
                return Unit.INSTANCE;
            } finally {
                keyguardStackingBlurDrawableForOverlay.setBounds(oplusNotificationChildrenContainerOverlayExImpl.stackMaskSavedBounds);
                keyguardStackingBlurDrawableForOverlay.setAlpha(alpha);
            }
        }
        return Unit.INSTANCE;
    }

    public static Unit $r8$lambda$ibGxkiG9XzZlaMyhBnx7iuU23hU(OplusNotificationChildrenContainerOverlayExImpl oplusNotificationChildrenContainerOverlayExImpl, Canvas canvas) {
        ExpandableNotificationRow containingNotification;
        if (!ScrimUtil.isLowGaussianLevel(oplusNotificationChildrenContainerOverlayExImpl.context) || (containingNotification = oplusNotificationChildrenContainerOverlayExImpl.getChildrenContainer().getContainingNotification()) == null || !containingNotification.mIsSummaryWithChildren) {
            return Unit.INSTANCE;
        }
        if (oplusNotificationChildrenContainerOverlayExImpl.overlayContainer != null) {
            int currentMaterialColor = ((MaterialColorManager) oplusNotificationChildrenContainerOverlayExImpl.materialColorManager.get()).getCurrentMaterialColor();
            int overlayTint = oplusNotificationChildrenContainerOverlayExImpl.getOverlayTint();
            if (Build.isDebuggable() && LogUtil.getNormalDebug() && LogUtil.isTagValid(LogUtil.TAG_NOTIFICATION)) {
                Log.d("SystemUi--Notification", SystemUIApplicationImpl$.ExternalSyntheticOutline0.m("updateColorTint=", Integer.toHexString(currentMaterialColor), " overlayTint = ", Integer.toHexString(overlayTint), "OplusNCCOverlayEx-->"));
            }
            GroupOverlayContainerView groupOverlayContainerView = oplusNotificationChildrenContainerOverlayExImpl.overlayContainer;
            Drawable background = groupOverlayContainerView != null ? groupOverlayContainerView.getBackground() : null;
            if (background instanceof AutoBlurDrawable) {
                AutoBlurDrawable autoBlurDrawable = (AutoBlurDrawable) background;
                GroupOverlayContainerView groupOverlayContainerView2 = oplusNotificationChildrenContainerOverlayExImpl.overlayContainer;
                groupOverlayContainerView2.getClass();
                int width = groupOverlayContainerView2.getWidth();
                GroupOverlayContainerView groupOverlayContainerView3 = oplusNotificationChildrenContainerOverlayExImpl.overlayContainer;
                groupOverlayContainerView3.getClass();
                autoBlurDrawable.setBounds(0, 0, width, groupOverlayContainerView3.getHeight());
                Drawable defaultDrawable = autoBlurDrawable.getDefaultDrawable();
                if (defaultDrawable != null) {
                    defaultDrawable.setTint(overlayTint);
                }
                Drawable defaultDrawable2 = autoBlurDrawable.getDefaultDrawable();
                if (defaultDrawable2 != null) {
                    defaultDrawable2.setBounds(autoBlurDrawable.getBounds());
                }
                Drawable defaultDrawable3 = autoBlurDrawable.getDefaultDrawable();
                if (defaultDrawable3 != null) {
                    defaultDrawable3.draw(canvas);
                }
            }
        }
        return Unit.INSTANCE;
    }

    public static Unit $r8$lambda$yU_nO6cqeXGX9jR1_nIrjHsoIzo(OplusNotificationChildrenContainerOverlayExImpl oplusNotificationChildrenContainerOverlayExImpl, Canvas canvas) {
        ExpandableNotificationRow containingNotification;
        if (!ScrimUtil.isLowGaussianLevel(oplusNotificationChildrenContainerOverlayExImpl.context) || (containingNotification = oplusNotificationChildrenContainerOverlayExImpl.getChildrenContainer().getContainingNotification()) == null || !containingNotification.mIsSummaryWithChildren) {
            return Unit.INSTANCE;
        }
        if (oplusNotificationChildrenContainerOverlayExImpl.overlayContainer != null) {
            GroupOverlayContainerView groupOverlayContainerView = oplusNotificationChildrenContainerOverlayExImpl.overlayContainer;
            groupOverlayContainerView.getClass();
            int width = groupOverlayContainerView.getWidth();
            GroupOverlayContainerView groupOverlayContainerView2 = oplusNotificationChildrenContainerOverlayExImpl.overlayContainer;
            groupOverlayContainerView2.getClass();
            Rect rect = new Rect(0, 0, width, groupOverlayContainerView2.getHeight());
            rect.inset(-1, -1);
            try {
                Drawable drawable = oplusNotificationChildrenContainerOverlayExImpl.aodDrawable;
                if (drawable != null) {
                    drawable.copyBounds(oplusNotificationChildrenContainerOverlayExImpl.stackMaskSavedBounds);
                }
                Drawable drawable2 = oplusNotificationChildrenContainerOverlayExImpl.aodDrawable;
                if (drawable2 != null) {
                    drawable2.setBounds(rect);
                }
                Drawable drawable3 = oplusNotificationChildrenContainerOverlayExImpl.aodDrawable;
                if (drawable3 != null) {
                    drawable3.draw(canvas);
                }
            } finally {
                Drawable drawable4 = oplusNotificationChildrenContainerOverlayExImpl.aodDrawable;
                if (drawable4 != null) {
                    drawable4.setBounds(oplusNotificationChildrenContainerOverlayExImpl.stackMaskSavedBounds);
                }
            }
        }
        return Unit.INSTANCE;
    }

    public OplusNotificationChildrenContainerOverlayExImpl(Context context, Lazy lazy, Lazy lazy2, Lazy lazy3, Lazy lazy4, Lazy lazy5, VisualStabilityCoordinator visualStabilityCoordinator, OplusPanoramicAodRepository oplusPanoramicAodRepository, Lazy lazy6, Lazy lazy7, Lazy lazy8, OplusSeparateNotificationAndQSState oplusSeparateNotificationAndQSState, Lazy lazy9) {
        this.context = context;
        this.statusBarStateController = lazy;
        this.viewBlurManager = lazy2;
        this.materialColorManager = lazy3;
        this.nsslController = lazy4;
        this.lockscreenShadeTransitionController = lazy5;
        this.visualStabilityCoordinator = visualStabilityCoordinator;
        this.oplusPanoramicAodRepository = oplusPanoramicAodRepository;
        this.panelAnimationEx = lazy6;
        this.oplusResetContextFontScaleAndDensity = lazy7;
        this.slidSwitchAnimation = lazy8;
        this.separateNotificationAndQSState = oplusSeparateNotificationAndQSState;
        this.panelViewPagerController = lazy9;
        AnonymousClass1 anonymousClass1 = new AnonymousClass1(1);
        anonymousClass1.this$0 = this;
        VarHandle.storeStoreFence();
        this.pendingRefreshRunnable = anonymousClass1;
        this.stackMaskSavedBounds = new Rect();
        this.normalColor = context.getColor(2131102886);
        OplusNotificationChildrenContainerOverlayExImpl$groupExpandAnimListener$1 oplusNotificationChildrenContainerOverlayExImpl$groupExpandAnimListener$1 = new OplusNotificationChildrenContainerOverlayExImpl$groupExpandAnimListener$1();
        oplusNotificationChildrenContainerOverlayExImpl$groupExpandAnimListener$1.this$0 = this;
        VarHandle.storeStoreFence();
        this.groupExpandAnimListener = oplusNotificationChildrenContainerOverlayExImpl$groupExpandAnimListener$1;
        OplusNotificationChildrenContainerOverlayExImpl$$ExternalSyntheticLambda1 oplusNotificationChildrenContainerOverlayExImpl$$ExternalSyntheticLambda1 = new OplusNotificationChildrenContainerOverlayExImpl$$ExternalSyntheticLambda1();
        oplusNotificationChildrenContainerOverlayExImpl$$ExternalSyntheticLambda1.f$0 = this;
        VarHandle.storeStoreFence();
        this.materialColorChangeCallback$delegate = LazyKt__LazyJVMKt.lazy(oplusNotificationChildrenContainerOverlayExImpl$$ExternalSyntheticLambda1);
    }

    public static final void access$addPreDrawListener(OplusNotificationChildrenContainerOverlayExImpl oplusNotificationChildrenContainerOverlayExImpl, View view) {
        NotificationEntry entry;
        StatusBarNotification sbn;
        if (view == null || !view.isAttachedToWindow()) {
            return;
        }
        if (oplusNotificationChildrenContainerOverlayExImpl.preDrawListener == null) {
            OplusNotificationChildrenContainerOverlayExImpl$createPreDrawListener$1 oplusNotificationChildrenContainerOverlayExImpl$createPreDrawListener$1 = new OplusNotificationChildrenContainerOverlayExImpl$createPreDrawListener$1();
            oplusNotificationChildrenContainerOverlayExImpl$createPreDrawListener$1.this$0 = oplusNotificationChildrenContainerOverlayExImpl;
            VarHandle.storeStoreFence();
            oplusNotificationChildrenContainerOverlayExImpl.preDrawListener = oplusNotificationChildrenContainerOverlayExImpl$createPreDrawListener$1;
        }
        if (LogUtil.getNormalDebug() && LogUtil.isTagValid(LogUtil.TAG_NOTIFICATION)) {
            ViewTreeObserver.OnPreDrawListener onPreDrawListener = oplusNotificationChildrenContainerOverlayExImpl.preDrawListener;
            ViewTreeObserver viewTreeObserver = view.getViewTreeObserver();
            ExpandableNotificationRow containingNotification = oplusNotificationChildrenContainerOverlayExImpl.getChildrenContainer().getContainingNotification();
            String key = (containingNotification == null || (entry = containingNotification.getEntry()) == null || (sbn = entry.getSbn()) == null) ? null : sbn.getKey();
            StringBuilder sb = new StringBuilder("addPreDrawListener=");
            sb.append(onPreDrawListener);
            sb.append(",viewTreeObserver=");
            sb.append(viewTreeObserver);
            sb.append(",key=");
            MediaTimeoutListener$PlaybackStateListener$.ExternalSyntheticOutline0.m(key, "OplusNCCOverlayEx-->", "SystemUi--Notification", sb);
        }
        view.getViewTreeObserver().removeOnPreDrawListener(oplusNotificationChildrenContainerOverlayExImpl.preDrawListener);
        view.getViewTreeObserver().addOnPreDrawListener(oplusNotificationChildrenContainerOverlayExImpl.preDrawListener);
    }

    public static final void access$releaseRes(OplusNotificationChildrenContainerOverlayExImpl oplusNotificationChildrenContainerOverlayExImpl) {
        NotificationEntry entry;
        StatusBarNotification sbn;
        NotificationEntry entry2;
        StatusBarNotification sbn2;
        GroupOverlayContainerView groupOverlayContainerView = oplusNotificationChildrenContainerOverlayExImpl.overlayContainer;
        if (groupOverlayContainerView != null) {
            if (LogUtil.getNormalDebug() && LogUtil.isTagValid(LogUtil.TAG_NOTIFICATION)) {
                ExpandableNotificationRow containingNotification = oplusNotificationChildrenContainerOverlayExImpl.getChildrenContainer().getContainingNotification();
                String key = (containingNotification == null || (entry2 = containingNotification.getEntry()) == null || (sbn2 = entry2.getSbn()) == null) ? null : sbn2.getKey();
                Log.d("SystemUi--Notification", "OplusNCCOverlayEx-->".concat("release res for row " + key + ",viewTreeObserver=" + groupOverlayContainerView.getViewTreeObserver() + ",reason=view detach"));
            }
            ((ViewBlurManager) oplusNotificationChildrenContainerOverlayExImpl.viewBlurManager.get()).releaseBlurProxyForView(groupOverlayContainerView, true, "view detach");
            GroupOverlayContainerView groupOverlayContainerView2 = oplusNotificationChildrenContainerOverlayExImpl.overlayContainer;
            if (groupOverlayContainerView2 != null && groupOverlayContainerView2.isAttachedToWindow()) {
                if (LogUtil.getNormalDebug() && LogUtil.isTagValid(LogUtil.TAG_NOTIFICATION)) {
                    ViewTreeObserver.OnPreDrawListener onPreDrawListener = oplusNotificationChildrenContainerOverlayExImpl.preDrawListener;
                    ViewTreeObserver viewTreeObserver = groupOverlayContainerView2.getViewTreeObserver();
                    ExpandableNotificationRow containingNotification2 = oplusNotificationChildrenContainerOverlayExImpl.getChildrenContainer().getContainingNotification();
                    String key2 = (containingNotification2 == null || (entry = containingNotification2.getEntry()) == null || (sbn = entry.getSbn()) == null) ? null : sbn.getKey();
                    StringBuilder sb = new StringBuilder("removePreDrawListener=");
                    sb.append(onPreDrawListener);
                    sb.append(",viewTreeObserver=");
                    sb.append(viewTreeObserver);
                    sb.append(",reason=view detach,key=");
                    MediaTimeoutListener$PlaybackStateListener$.ExternalSyntheticOutline0.m(key2, "OplusNCCOverlayEx-->", "SystemUi--Notification", sb);
                }
                if (oplusNotificationChildrenContainerOverlayExImpl.preDrawListener != null) {
                    groupOverlayContainerView2.getViewTreeObserver().removeOnPreDrawListener(oplusNotificationChildrenContainerOverlayExImpl.preDrawListener);
                    oplusNotificationChildrenContainerOverlayExImpl.preDrawListener = null;
                }
            }
            oplusNotificationChildrenContainerOverlayExImpl.clearViewCache();
            if (ScrimUtil.isLowGaussianLevel(groupOverlayContainerView.getContext())) {
                ((MaterialColorManager) oplusNotificationChildrenContainerOverlayExImpl.materialColorManager.get()).removeMaterialColorCallback(oplusNotificationChildrenContainerOverlayExImpl.getMaterialColorChangeCallback());
            }
            oplusNotificationChildrenContainerOverlayExImpl.visualStabilityCoordinator.setGroupNeedsPrune(false);
            oplusNotificationChildrenContainerOverlayExImpl.cancelPendingOverlayRefresh();
        }
    }

    public static final void access$requestRebuildIfSingleChild(OplusNotificationChildrenContainerOverlayExImpl oplusNotificationChildrenContainerOverlayExImpl) {
        if (oplusNotificationChildrenContainerOverlayExImpl.getChildrenContainer().getAttachedChildren().isEmpty() || oplusNotificationChildrenContainerOverlayExImpl.getChildrenContainer().getAttachedChildren().size() != 1) {
            oplusNotificationChildrenContainerOverlayExImpl.visualStabilityCoordinator.setGroupNeedsPrune(false);
            return;
        }
        NotificationChildrenContainer childrenContainer = oplusNotificationChildrenContainerOverlayExImpl.getChildrenContainer();
        AnonymousClass1 anonymousClass1 = new AnonymousClass1(2);
        anonymousClass1.this$0 = oplusNotificationChildrenContainerOverlayExImpl;
        VarHandle.storeStoreFence();
        childrenContainer.post(anonymousClass1);
    }

    /* JADX INFO: Access modifiers changed from: private */
    public final OplusNotificationChildrenContainerOverlayExImpl$materialColorChangeCallback$2$1 getMaterialColorChangeCallback() {
        return (OplusNotificationChildrenContainerOverlayExImpl$materialColorChangeCallback$2$1) this.materialColorChangeCallback$delegate.getValue();
    }

    private final NotificationMaterialColorEx getNotificationMaterialColorEx() {
        return (NotificationMaterialColorEx) this.notificationMaterialColorEx$delegate.getValue();
    }

    private final int getOverlayTint() {
        int currentMaterialColor = ((MaterialColorManager) this.materialColorManager.get()).getCurrentMaterialColor();
        if (!SuperPowerSaveSettingsObserver.Companion.getInstance().isSuperPowerSaveState) {
            ScrimControllerEx.Companion.getClass();
            if (!ScrimControllerEx.isThirdTheme && (!ScrimUtil.isWallpaperBlurDisable() || !ScrimUtil.isLowGaussianLevel(this.context) || currentMaterialColor != 0)) {
                return currentMaterialColor;
            }
        }
        return this.normalColor;
    }

    private final void setExpansionFinishedState(NotificationGroupSplitEx.ExpansionFinishedState expansionFinishedState) {
        if (Intrinsics.areEqual(this.expansionFinishedState, expansionFinishedState)) {
            return;
        }
        this.expansionFinishedState = expansionFinishedState;
        if (LogUtil.getNormalDebug() && LogUtil.isTagValid(LogUtil.TAG_NOTIFICATION)) {
            ExpandableNotificationRow containingNotification = getChildrenContainer().getContainingNotification();
            Log.d("SystemUi--Notification", "OplusNCCOverlayEx-->".concat("recordExpansionAnimState " + expansionFinishedState + ", key=" + (containingNotification != null ? containingNotification.getKey() : null)));
        }
    }

    /* JADX INFO: Access modifiers changed from: private */
    public final void setOverlayVisibility(String str) {
        int i = (this.isUserLocked || this.isGroupExpandAnimating) ? 0 : 4;
        GroupOverlayContainerView groupOverlayContainerView = this.overlayContainer;
        if (groupOverlayContainerView == null || groupOverlayContainerView.getVisibility() == i) {
            return;
        }
        groupOverlayContainerView.setVisibility(i);
        LogUtil.i$default(LogUtil.TAG_NOTIFICATION, TAG, "setOverlayVisibility to " + i + " for reason: " + str, null, 8);
    }

    private final void setupForceSetFinalValue(View view) {
        OplusPropertyData oplusPropertyData;
        OplusPhysicsProperty[] oplusPhysicsPropertyArr = needForceSetFinalAnimatableProperties;
        int length = oplusPhysicsPropertyArr.length;
        int i = 0;
        while (true) {
            if (i >= length) {
                break;
            }
            Object tag = view.getTag(oplusPhysicsPropertyArr[i].getTag());
            oplusPropertyData = tag instanceof OplusPropertyData ? (OplusPropertyData) tag : null;
            if (oplusPropertyData != null) {
                oplusPropertyData.setForceSetFinalValue(true);
            }
            i++;
        }
        Object tag2 = view.getTag(OplusExpandableViewStateExImpl.Companion.getGROUP_EXPAND_CONTENT_ALPHA_PROPERTY().getAnimatorTag());
        oplusPropertyData = tag2 instanceof OplusPropertyData ? (OplusPropertyData) tag2 : null;
        if (oplusPropertyData != null) {
            oplusPropertyData.setForceSetFinalValue(true);
        }
    }

    public static void updateSingleLinePadding(HybridNotificationView hybridNotificationView, HybridNotificationView hybridNotificationView2, ExpandableNotificationRow expandableNotificationRow) {
        if (hybridNotificationView != null) {
            hybridNotificationView.setPaddingRelative(hybridNotificationView2.getPaddingStart(), hybridNotificationView2.getPaddingTop(), hybridNotificationView2.getPaddingEnd(), hybridNotificationView2.getPaddingBottom());
            if (hybridNotificationView2.getMeasuredWidth() > 0) {
                hybridNotificationView.measure(View.MeasureSpec.makeMeasureSpec(hybridNotificationView2.getMeasuredWidth(), 1073741824), View.MeasureSpec.makeMeasureSpec(hybridNotificationView2.getMeasuredHeight(), 1073741824));
                hybridNotificationView.requestLayout();
            }
            if (Build.isDebuggable() && LogUtil.getNormalDebug() && LogUtil.isTagValid(LogUtil.TAG_NOTIFICATION)) {
                StringBuilder sb = new StringBuilder("OplusNCCOverlayEx-->");
                NotificationEntry entry = expandableNotificationRow.getEntry();
                String key = entry != null ? entry.getKey() : null;
                int paddingStart = hybridNotificationView.getPaddingStart();
                int paddingEnd = hybridNotificationView.getPaddingEnd();
                int width = hybridNotificationView2.getWidth();
                int width2 = hybridNotificationView.getWidth();
                TextView textView = hybridNotificationView2.getTextView();
                Integer numValueOf = textView != null ? Integer.valueOf(textView.getMeasuredWidth()) : null;
                TextView textView2 = hybridNotificationView2.getTextView();
                Integer numValueOf2 = textView2 != null ? Integer.valueOf(textView2.getMeasuredHeight()) : null;
                TextView textView3 = hybridNotificationView.getTextView();
                Integer numValueOf3 = textView3 != null ? Integer.valueOf(textView3.getMeasuredWidth()) : null;
                TextView textView4 = hybridNotificationView.getTextView();
                Integer numValueOf4 = textView4 != null ? Integer.valueOf(textView4.getMeasuredHeight()) : null;
                StringBuilder sbM = ConstraintSet$.ExternalSyntheticOutline0.m(paddingStart, "updateSingleLinePadding for child ", key, " paddingStart ", ", paddingEnd ");
                RectList$.ExternalSyntheticOutline0.m(paddingEnd, width, " originalSingleLineView width ", " mirrorSingleLine width ", sbM);
                sbM.append(width2);
                sbM.append(" originalTextView width ");
                sbM.append(numValueOf);
                sbM.append(" +height ");
                sbM.append(numValueOf2);
                sbM.append(" mirrorTextView width ");
                sbM.append(numValueOf3);
                sbM.append(" height ");
                sbM.append(numValueOf4);
                sbM.append(" ");
                sb.append(sbM.toString());
                Log.d("SystemUi--Notification", sb.toString());
            }
        }
    }

    public final void cancelPendingOverlayRefresh() {
        if (this.hasPendingRefreshTask) {
            this.hasPendingRefreshTask = false;
            getChildrenContainer().removeCallbacks(this.pendingRefreshRunnable);
            if (LogUtil.getNormalDebug() && LogUtil.isTagValid(LogUtil.TAG_NOTIFICATION)) {
                Log.d("SystemUi--Notification", "OplusNCCOverlayEx-->cancelPendingOverlayRefresh");
            }
        }
    }

    public void changeAodDrawable(Drawable drawable) {
        this.aodDrawable = drawable;
    }

    public final void clearViewCache() {
        ViewParent parent;
        LinearLayout[] linearLayoutArr = this.viewCache;
        int length = linearLayoutArr.length;
        int i = 0;
        int i2 = 0;
        while (i < length) {
            LinearLayout linearLayout = linearLayoutArr[i];
            int i3 = i2 + 1;
            if (linearLayout != null && (parent = linearLayout.getParent()) != null) {
                ViewGroup viewGroup = parent instanceof ViewGroup ? (ViewGroup) parent : null;
                if (viewGroup != null) {
                    viewGroup.removeView(linearLayout);
                }
            }
            this.viewCache[i2] = null;
            i++;
            i2 = i3;
        }
    }

    public void finishRunningGroupExpansionAnimationsForPanelTransition(boolean z, ExpandableNotificationRow expandableNotificationRow) {
        OplusExpandableNotificationRowEx oplusExpandableNotificationRowEx;
        PanelAnimationEx.ExpandedFraction panelExpandFraction;
        if (z && expandableNotificationRow.mIsSummaryWithChildren && (oplusExpandableNotificationRowEx = expandableNotificationRow.mRowEx) != null && ((OplusExpandableNotificationRowExImpl) oplusExpandableNotificationRowEx).isChildrenExpandedAnimating()) {
            PanelAnimationEx panelAnimationEx = (PanelAnimationEx) this.panelAnimationEx.get();
            float rawFraction = (panelAnimationEx == null || (panelExpandFraction = panelAnimationEx.getPanelExpandFraction()) == null) ? 0.0f : panelExpandFraction.getRawFraction();
            if (rawFraction <= 0.0f || rawFraction >= 1.0f) {
                return;
            }
            if (LogUtil.getNormalDebug() && LogUtil.isTagValid(LogUtil.TAG_NOTIFICATION)) {
                MediaTimeoutListener$PlaybackStateListener$.ExternalSyntheticOutline0.m("finishRunningGroupExpansionAnimationsForPanelTransition key=", expandableNotificationRow.getKey(), "OplusNCCOverlayEx-->", "SystemUi--Notification");
            }
            List<ExpandableNotificationRow> attachedChildren = expandableNotificationRow.getAttachedChildren();
            if (attachedChildren != null) {
                for (ExpandableNotificationRow expandableNotificationRow2 : attachedChildren) {
                    expandableNotificationRow2.getClass();
                    setupForceSetFinalValue(expandableNotificationRow2);
                    expandableNotificationRow2.getViewState().cancelAnimations(expandableNotificationRow2);
                }
            }
            setupForceSetFinalValue(expandableNotificationRow);
            expandableNotificationRow.getViewState().cancelAnimations(expandableNotificationRow);
            expandableNotificationRow.applyViewState();
            expandableNotificationRow.onFinishedExpansionChange();
            NotificationStackScrollLayout view = ((NotificationStackScrollLayoutController) this.nsslController.get()).getView();
            if (view != null) {
                view.requestChildrenUpdate$1();
            }
        }
    }

    public final void flushBackground(View view, ExpandableNotificationRow expandableNotificationRow, String str) {
        Drawable drawableMutate;
        StatusBarNotification sbn;
        Drawable drawableNewDrawable;
        if (view != null) {
            ViewBlurProxy viewBlurProxyRequireBlurProxyForView = ((ViewBlurManager) this.viewBlurManager.get()).requireBlurProxyForView(view, ViewBlurManager.CardType.MENU);
            ((ViewBlurManager) this.viewBlurManager.get()).refreshBlurType(view);
            viewBlurProxyRequireBlurProxyForView.applyBlurConfig();
            NotificationBackgroundView notificationBackgroundView = ((ActivatableNotificationView) expandableNotificationRow).mBackgroundNormal;
            String key = null;
            Drawable baseBackgroundLayer = notificationBackgroundView != null ? notificationBackgroundView.getBaseBackgroundLayer() : null;
            if (baseBackgroundLayer != null) {
                Drawable.ConstantState constantState = baseBackgroundLayer.getConstantState();
                if (constantState == null || (drawableNewDrawable = constantState.newDrawable(view.getContext().getResources())) == null || (drawableMutate = drawableNewDrawable.mutate()) == null) {
                    drawableMutate = baseBackgroundLayer.mutate();
                }
            } else {
                drawableMutate = null;
            }
            if (ScrimUtil.isLowGaussianLevel(view.getContext()) && drawableMutate != null) {
                drawableMutate.setTint(getOverlayTint());
            }
            view.setBackground(new AutoBlurDrawable(viewBlurProxyRequireBlurProxyForView, drawableMutate));
            view.invalidate();
            if (LogUtil.getNormalDebug() && LogUtil.isTagValid(LogUtil.TAG_NOTIFICATION)) {
                NotificationEntry entry = expandableNotificationRow.getEntry();
                if (entry != null && (sbn = entry.getSbn()) != null) {
                    key = sbn.getKey();
                }
                ViewBlurProxy.BlurType blurType = viewBlurProxyRequireBlurProxyForView.getBlurType();
                StringBuilder sbM = ExifInterface$.ExternalSyntheticOutline1.m("flushBackground for row ", key, ", reason ", str, ", blur type ");
                sbM.append(blurType);
                sbM.append(",originDrawable=");
                sbM.append(baseBackgroundLayer);
                sbM.append(", defaultDrawable=");
                sbM.append(drawableMutate);
                Log.d("SystemUi--Notification", "OplusNCCOverlayEx-->".concat(sbM.toString()));
            }
        }
    }

    public final void flushSingleLineView(ExpandableNotificationRow expandableNotificationRow, ViewGroup viewGroup, int i) {
        NotificationEntry entry;
        StatusBarNotification sbn;
        NotificationContentView showingLayout;
        NotificationViewWrapper visibleWrapper;
        SingleLineViewModel singleLineViewModelInflateSingleLineViewModel;
        boolean z;
        if (expandableNotificationRow.getSingleLineView() == null || (entry = expandableNotificationRow.getEntry()) == null || (sbn = entry.getSbn()) == null || (showingLayout = expandableNotificationRow.getShowingLayout()) == null || (visibleWrapper = showingLayout.getVisibleWrapper(0)) == null || visibleWrapper.mView == null) {
            return;
        }
        Context oplusContext = ((OplusResetContextFontScaleAndDensity) this.oplusResetContextFontScaleAndDensity.get()).getOplusContext(expandableNotificationRow.getContext());
        Notification notification = sbn.getNotification();
        HybridNotificationView hybridNotificationView = null;
        if (notification == null) {
            LogUtil.w$default(8, LogUtil.TAG_NOTIFICATION, TAG, "build singleLine viewModel, notification is null");
            singleLineViewModelInflateSingleLineViewModel = null;
        } else {
            Notification.Builder builderRecoverBuilder = Notification.Builder.recoverBuilder(oplusContext, notification);
            Notification.Style style = builderRecoverBuilder.getStyle();
            Notification.MessagingStyle messagingStyle = style instanceof Notification.MessagingStyle ? (Notification.MessagingStyle) style : null;
            Notification.MetricStyle style2 = builderRecoverBuilder.getStyle();
            singleLineViewModelInflateSingleLineViewModel = SingleLineViewInflater.inflateSingleLineViewModel(notification, messagingStyle, style2 instanceof Notification.MetricStyle ? style2 : null, builderRecoverBuilder, oplusContext, false, notification.extras.getCharSequence("android.summarization"));
        }
        if (singleLineViewModelInflateSingleLineViewModel == null) {
            LogUtil.w$default(8, LogUtil.TAG_NOTIFICATION, TAG, "flush singleLine , viewModel is null");
            return;
        }
        int i2 = i % 3;
        HybridNotificationView hybridNotificationView2 = this.viewCache[i2];
        if (hybridNotificationView2 != null) {
            SingleLineViewPayload payload = singleLineViewModelInflateSingleLineViewModel.getPayload();
            if (hybridNotificationView2 instanceof HybridMetricNotificationView) {
                z = payload instanceof SingleLineViewPayload.MetricPayload;
            } else {
                z = hybridNotificationView2 instanceof HybridConversationNotificationView ? payload instanceof SingleLineViewPayload.ConversationData : payload instanceof SingleLineViewPayload.StandardPayload;
            }
            if (!z) {
                ViewParent parent = hybridNotificationView2.getParent();
                ViewGroup viewGroup2 = parent instanceof ViewGroup ? (ViewGroup) parent : null;
                if (viewGroup2 != null) {
                    viewGroup2.removeView(hybridNotificationView2);
                }
                this.viewCache[i2] = null;
                hybridNotificationView2 = null;
            }
        }
        if (hybridNotificationView2 != null) {
            ViewParent parent2 = hybridNotificationView2.getParent();
            hybridNotificationView = parent2 instanceof ViewGroup ? (ViewGroup) parent2 : null;
            if (hybridNotificationView != null) {
                hybridNotificationView.removeView(hybridNotificationView2);
            }
            viewGroup.addView(hybridNotificationView2);
            hybridNotificationView = hybridNotificationView2;
        } else {
            View viewInflate = LayoutInflater.from(oplusContext).inflate(HybridNotificationView.getLayoutResource(singleLineViewModelInflateSingleLineViewModel.getPayload()), viewGroup, false);
            HybridNotificationView hybridNotificationView3 = viewInflate instanceof HybridNotificationView ? (HybridNotificationView) viewInflate : null;
            if (hybridNotificationView3 == null) {
                LogUtil.w$default(8, LogUtil.TAG_NOTIFICATION, TAG, "HybridNotificationView inflate error actual type=null");
            } else {
                viewGroup.addView(hybridNotificationView3);
                this.viewCache[i2] = hybridNotificationView3;
                hybridNotificationView = hybridNotificationView3;
            }
        }
        if (hybridNotificationView == null) {
            LogUtil.w$default(8, LogUtil.TAG_NOTIFICATION, TAG, "flush singleLine , mirror view is null");
        } else {
            SingleLineViewBinder.bind(singleLineViewModelInflateSingleLineViewModel, hybridNotificationView);
        }
    }

    public View getOverlay() {
        return this.overlayContainer;
    }

    public PlatformBlurDrawable getOverlayBlurDrawable() {
        GroupOverlayContainerView groupOverlayContainerView = this.overlayContainer;
        if (groupOverlayContainerView != null) {
            Drawable blurDrawable = ((ViewBlurManager) this.viewBlurManager.get()).requireBlurProxyForView(groupOverlayContainerView, ViewBlurManager.CardType.MENU).getBlurDrawable(null);
            if (blurDrawable instanceof PlatformBlurDrawable) {
                return (PlatformBlurDrawable) blurDrawable;
            }
        }
        return null;
    }

    public void initDimens(Resources resources) {
        this.expandGroupHeaderTranslationWhenCollapsed = resources.getDimensionPixelOffset(2131170661);
        this.maxCornerRadius = resources.getDimensionPixelSize(2131170626);
        this.groupCollapsedTopPadding = resources.getDimensionPixelOffset(2131170658);
    }

    /* JADX WARN: Type inference fix 'apply assigned field type' failed
    java.lang.UnsupportedOperationException: ArgType.getObject(), call class: class jadx.core.dex.instructions.args.ArgType$PrimitiveArg
    	at jadx.core.dex.instructions.args.ArgType.getObject(ArgType.java:596)
    	at jadx.core.dex.attributes.nodes.ClassTypeVarsAttr.getTypeVarsMapFor(ClassTypeVarsAttr.java:35)
    	at jadx.core.dex.nodes.utils.TypeUtils.replaceClassGenerics(TypeUtils.java:177)
    	at jadx.core.dex.visitors.typeinference.FixTypesVisitor.insertExplicitUseCast(FixTypesVisitor.java:397)
    	at jadx.core.dex.visitors.typeinference.FixTypesVisitor.tryFieldTypeWithNewCasts(FixTypesVisitor.java:359)
    	at jadx.core.dex.visitors.typeinference.FixTypesVisitor.applyFieldType(FixTypesVisitor.java:309)
    	at jadx.core.dex.visitors.typeinference.FixTypesVisitor.visit(FixTypesVisitor.java:94)
     */
    public final void layoutGroupOverlay() {
        GroupOverlayContainerView groupOverlayContainerView = this.overlayContainer;
        if (groupOverlayContainerView != null) {
            int groupTopPaddingWhenCollapsed = getChildrenContainer().mExt.getGroupTopPaddingWhenCollapsed(this.groupCollapsedTopPadding, getChildrenContainer().getAttachedChildren().size() == 1, getChildrenContainer());
            int i = this.cachedFirstChildHeight;
            int collapsedHeight = getChildrenContainer().getCollapsedHeight();
            int iMax = Math.max(collapsedHeight, i);
            if (Build.isDebuggable() && LogUtil.getNormalDebug() && LogUtil.isTagValid(LogUtil.TAG_NOTIFICATION)) {
                MobileInputLogger$.ExternalSyntheticOutline0.m(iMax, "OplusNCCOverlayEx-->", "SystemUi--Notification", FloatList$.ExternalSyntheticOutline0.m("layoutGroupOverlay collapsedTopMargin ", groupTopPaddingWhenCollapsed, groupOverlayContainerView.getMeasuredHeight(), " measuredHeight ", " expectedHeight "));
            }
            if (groupOverlayContainerView.getMeasuredHeight() != iMax) {
                LogUtil.w$default(8, LogUtil.TAG_NOTIFICATION, TAG, Fragment$5$.ExternalSyntheticOutline0.m(FloatList$.ExternalSyntheticOutline0.m("layoutGroupOverlay: height mismatch, remeasuring. measuredHeight=", groupOverlayContainerView.getMeasuredHeight(), iMax, ", expectedHeight=", " collapsedHeight="), collapsedHeight, i, ", firstChildHeight="));
                AnonymousClass1 anonymousClass1 = new AnonymousClass1(3);
                anonymousClass1.this$0 = groupOverlayContainerView;
                VarHandle.storeStoreFence();
                groupOverlayContainerView.post(anonymousClass1);
            }
            groupOverlayContainerView.layout(0, 0, groupOverlayContainerView.getMeasuredWidth(), iMax);
            int i2 = 0;
            int measuredHeight = 0;
            for (Object obj : ViewGroupKt.getChildren(groupOverlayContainerView)) {
                int i3 = i2 + 1;
                if (i2 < 0) {
                    CollectionsKt__CollectionsKt.throwIndexOverflow();
                    throw null;
                }
                View view = (View) obj;
                int i4 = i2 == 0 ? measuredHeight + groupTopPaddingWhenCollapsed : measuredHeight + getChildrenContainer().mChildPadding;
                view.layout(0, i4, groupOverlayContainerView.getMeasuredWidth(), view.getMeasuredHeight() + i4);
                measuredHeight = i4 + view.getMeasuredHeight();
                i2 = i3;
            }
        }
    }

    public final void measureGroupOverlay(int i, int i2) {
        List listEmptyList;
        Object obj;
        NotificationEntry entry;
        StatusBarNotification sbn;
        Sequence children;
        Sequence sequenceFilter;
        NotificationEntry entry2;
        StatusBarNotification sbn2;
        ExpandableNotificationRow expandableNotificationRow;
        GroupOverlayContainerView groupOverlayContainerView = this.overlayContainer;
        if (groupOverlayContainerView != null) {
            this.overlayActualHeight = i2;
            List attachedChildren = getChildrenContainer().getAttachedChildren();
            attachedChildren.getClass();
            int expandHeight = (attachedChildren.isEmpty() || (expandableNotificationRow = (ExpandableNotificationRow) CollectionsKt___CollectionsKt.firstOrNull(attachedChildren)) == null) ? 0 : expandableNotificationRow.getShowingLayout().getExpandHeight();
            this.cachedFirstChildHeight = expandHeight;
            int iMax = Math.max(i2, expandHeight);
            if (iMax > i2) {
                this.baseClipBottom = iMax - i2;
            } else {
                this.baseClipBottom = 0;
            }
            if (Build.isDebuggable() && LogUtil.getNormalDebug() && LogUtil.isTagValid(LogUtil.TAG_NOTIFICATION)) {
                Log.d("SystemUi--Notification", NotificationPanelViewController$.ExternalSyntheticOutline0.m(iMax, this.baseClipBottom, ", baseClipBottom ", "OplusNCCOverlayEx-->", FloatList$.ExternalSyntheticOutline0.m("measureGroupOverlay width ", i, i2, ", height ", ", targetHeight ")));
            }
            groupOverlayContainerView.measure(View.MeasureSpec.makeMeasureSpec(i, 1073741824), View.MeasureSpec.makeMeasureSpec(iMax, 1073741824));
            ExpandableNotificationRow containingNotification = getChildrenContainer().getContainingNotification();
            List attachedChildren2 = getChildrenContainer().getAttachedChildren();
            String key = null;
            if (attachedChildren2.isEmpty()) {
                if (containingNotification != null && (entry2 = containingNotification.getEntry()) != null && (sbn2 = entry2.getSbn()) != null) {
                    key = sbn2.getKey();
                }
                OplusAODRefreshRateController$RequestRefreshRateRunnable$.ExternalSyntheticOutline0.m(8, "updatePadding: no attached children in row ", key, LogUtil.TAG_NOTIFICATION, TAG);
                return;
            }
            int iMin = Math.min(attachedChildren2.size(), getChildrenContainer().getMaxAllowedVisibleChildren(true));
            GroupOverlayContainerView groupOverlayContainerView2 = this.overlayContainer;
            if (groupOverlayContainerView2 == null || (children = ViewGroupKt.getChildren(groupOverlayContainerView2)) == null || (sequenceFilter = SequencesKt___SequencesKt.filter(children, OplusNotificationChildrenContainerOverlayExImpl$updateOverlayPadding$$inlined$filterIsInstance$1.INSTANCE)) == null || (listEmptyList = SequencesKt___SequencesKt.toList(sequenceFilter)) == null) {
                listEmptyList = CollectionsKt__CollectionsKt.emptyList();
            }
            if (listEmptyList.isEmpty()) {
                if (containingNotification != null && (entry = containingNotification.getEntry()) != null && (sbn = entry.getSbn()) != null) {
                    key = sbn.getKey();
                }
                OplusAODRefreshRateController$RequestRefreshRateRunnable$.ExternalSyntheticOutline0.m(8, "updatePadding: no mirror single view in row ", key, LogUtil.TAG_NOTIFICATION, TAG);
                return;
            }
            for (int i3 = 0; i3 < iMin; i3++) {
                try {
                    ExpandableNotificationRow expandableNotificationRow2 = (ExpandableNotificationRow) attachedChildren2.get(i3);
                    HybridNotificationView singleLineView = expandableNotificationRow2.getSingleLineView();
                    if (singleLineView != null) {
                        updateSingleLinePadding((HybridNotificationView) CollectionsKt___CollectionsKt.getOrNull(listEmptyList, i3), singleLineView, expandableNotificationRow2);
                    }
                } catch (Throwable th) {
                    obj = Result.constructor-impl(ResultKt.createFailure(th));
                }
            }
            obj = Result.constructor-impl(Unit.INSTANCE);
            Throwable th2 = Result.exceptionOrNull-impl(obj);
            if (th2 != null) {
                OplusAODRefreshRateController$RequestRefreshRateRunnable$.ExternalSyntheticOutline0.m(8, "updatePadding got error ", th2.getMessage(), LogUtil.TAG_NOTIFICATION, TAG);
            }
        }
    }

    public final void onAttachedChildrenCountChanged(int i, String str) {
        OplusExpandableNotificationRowEx oplusExpandableNotificationRowEx;
        StatusBarNotification sbn;
        NotificationEntry entry;
        StatusBarNotification sbn2;
        if (Build.isDebuggable() && LogUtil.getNormalDebug() && LogUtil.isTagValid(LogUtil.TAG_NOTIFICATION)) {
            StringBuilder sb = new StringBuilder("OplusNCCOverlayEx-->");
            ExpandableNotificationRow containingNotification = getChildrenContainer().getContainingNotification();
            Boolean boolValueOf = containingNotification != null ? Boolean.valueOf(containingNotification.isAttachedToWindow()) : null;
            GroupOverlayContainerView groupOverlayContainerView = this.overlayContainer;
            String name = groupOverlayContainerView != null ? DebugToolKt.getName(groupOverlayContainerView, false) : null;
            ExpandableNotificationRow containingNotification2 = getChildrenContainer().getContainingNotification();
            String key = (containingNotification2 == null || (entry = containingNotification2.getEntry()) == null || (sbn2 = entry.getSbn()) == null) ? null : sbn2.getKey();
            StringBuilder sbM = KeyguardLogger$.ExternalSyntheticOutline0.m(i, "onAttachedChildrenCountChanged: ", " from:", str, ",attach state=");
            sbM.append(boolValueOf);
            sbM.append(",overlayContainer=");
            sbM.append(name);
            sbM.append(",key=");
            sbM.append(key);
            sb.append(sbM.toString());
            Log.d("SystemUi--Notification", sb.toString());
        }
        ExpandableNotificationRow containingNotification3 = getChildrenContainer().getContainingNotification();
        if (containingNotification3 == null || !containingNotification3.isAttachedToWindow()) {
            return;
        }
        ExpandableNotificationRow containingNotification4 = getChildrenContainer().getContainingNotification();
        if (containingNotification4 != null && containingNotification4.mIsSummaryWithChildren && this.overlayContainer == null) {
            GroupOverlayContainerView groupOverlayContainerView2 = new GroupOverlayContainerView(((OplusResetContextFontScaleAndDensity) this.oplusResetContextFontScaleAndDensity.get()).getOplusContext(containingNotification4.getContext()));
            groupOverlayContainerView2.setId(View.generateViewId());
            groupOverlayContainerView2.setLayoutParams(new ViewGroup.LayoutParams(-1, -1));
            groupOverlayContainerView2.setClickable(false);
            groupOverlayContainerView2.setFocusable(false);
            groupOverlayContainerView2.setTopCornerRadius(this.maxCornerRadius);
            groupOverlayContainerView2.setBottomCornerRadius(this.maxCornerRadius);
            this.overlayContainer = groupOverlayContainerView2;
            OplusNotificationChildrenContainerOverlayExImpl$createOverlayContainer$2$1 oplusNotificationChildrenContainerOverlayExImpl$createOverlayContainer$2$1 = new OplusNotificationChildrenContainerOverlayExImpl$createOverlayContainer$2$1();
            oplusNotificationChildrenContainerOverlayExImpl$createOverlayContainer$2$1.this$0 = this;
            oplusNotificationChildrenContainerOverlayExImpl$createOverlayContainer$2$1.$this_apply = groupOverlayContainerView2;
            VarHandle.storeStoreFence();
            groupOverlayContainerView2.addOnAttachStateChangeListener(oplusNotificationChildrenContainerOverlayExImpl$createOverlayContainer$2$1);
            getChildrenContainer().addView(groupOverlayContainerView2);
            groupOverlayContainerView2.setAlpha(1.0f);
            setOverlayVisibility("init");
            NotificationEntry entry2 = containingNotification4.getEntry();
            String key2 = (entry2 == null || (sbn = entry2.getSbn()) == null) ? null : sbn.getKey();
            GroupOverlayContainerView groupOverlayContainerView3 = this.overlayContainer;
            Float fValueOf = groupOverlayContainerView3 != null ? Float.valueOf(groupOverlayContainerView3.getTopCornerRadius()) : null;
            GroupOverlayContainerView groupOverlayContainerView4 = this.overlayContainer;
            LogUtil.i$default(LogUtil.TAG_NOTIFICATION, TAG, "createOverlayContainer finish for row: " + key2 + ", radius " + fValueOf + "-" + (groupOverlayContainerView4 != null ? Float.valueOf(groupOverlayContainerView4.getBottomCornerRadius()) : null), null, 8);
            GroupOverlayContainerView groupOverlayContainerView5 = this.overlayContainer;
            if (groupOverlayContainerView5 != null) {
                OplusNotificationChildrenContainerOverlayExImpl$.ExternalSyntheticLambda2 externalSyntheticLambda2 = new OplusNotificationChildrenContainerOverlayExImpl$.ExternalSyntheticLambda2(0);
                externalSyntheticLambda2.f$0 = this;
                VarHandle.storeStoreFence();
                groupOverlayContainerView5.setOverlayMaskDrawer(externalSyntheticLambda2);
            }
            GroupOverlayContainerView groupOverlayContainerView6 = this.overlayContainer;
            if (groupOverlayContainerView6 != null) {
                OplusNotificationChildrenContainerOverlayExImpl$.ExternalSyntheticLambda2 externalSyntheticLambda3 = new OplusNotificationChildrenContainerOverlayExImpl$.ExternalSyntheticLambda2(1);
                externalSyntheticLambda3.f$0 = this;
                VarHandle.storeStoreFence();
                groupOverlayContainerView6.setStackMaskDrawer(externalSyntheticLambda3);
            }
            GroupOverlayContainerView groupOverlayContainerView7 = this.overlayContainer;
            if (groupOverlayContainerView7 != null) {
                OplusNotificationChildrenContainerOverlayExImpl$.ExternalSyntheticLambda2 externalSyntheticLambda4 = new OplusNotificationChildrenContainerOverlayExImpl$.ExternalSyntheticLambda2(2);
                externalSyntheticLambda4.f$0 = this;
                VarHandle.storeStoreFence();
                groupOverlayContainerView7.setAodMaskDrawer(externalSyntheticLambda4);
            }
            ExpandableNotificationRow containingNotification5 = getChildrenContainer().getContainingNotification();
            if (containingNotification5 != null && (oplusExpandableNotificationRowEx = containingNotification5.mRowEx) != null) {
                oplusExpandableNotificationRowEx.addGroupExpandAnimationListener(this.groupExpandAnimListener);
            }
        }
        Trace.beginSection("OplusNCCOverlayEx scheduleOverlayRefresh");
        try {
            if (!this.isUserLocked && !this.isGroupExpandAnimating) {
                if (!this.hasPendingRefreshTask) {
                    this.hasPendingRefreshTask = true;
                    this.pendingRefreshReason = str;
                    getChildrenContainer().post(this.pendingRefreshRunnable);
                }
                return;
            }
            refreshOverlayContent(getChildrenContainer().getContainingNotification(), str);
        } finally {
            android.os.Trace.endSection();
        }
    }

    public final void onChildAdd() {
        onAttachedChildrenCountChanged(getChildrenContainer().getAttachedChildren().size(), "onChildAdd");
    }

    public final void onChildRemove() {
        onAttachedChildrenCountChanged(getChildrenContainer().getAttachedChildren().size(), "onChildRemove");
        ExpandableNotificationRow containingNotification = getChildrenContainer().getContainingNotification();
        if (containingNotification == null || !containingNotification.isAttachedToWindow()) {
            return;
        }
        NotificationStackScrollLayoutController notificationStackScrollLayoutController = (NotificationStackScrollLayoutController) this.nsslController.get();
        AnonymousClass1 anonymousClass1 = new AnonymousClass1(0);
        anonymousClass1.this$0 = this;
        VarHandle.storeStoreFence();
        notificationStackScrollLayoutController.runAfterAnimationFinished$1(anonymousClass1);
    }

    public final void onChildUpdated() {
        onAttachedChildrenCountChanged(getChildrenContainer().getAttachedChildren().size(), "onChildUpdated");
    }

    public final void onDensityOrFontScaleChanged() {
        initDimens(this.context.getResources());
        clearViewCache();
        refreshOverlayContent(getChildrenContainer().getContainingNotification(), "density or font scale change");
    }

    public void onFinishedExpansionChange(Runnable runnable) {
        List<ExpandableNotificationRow> attachedChildren;
        OplusExpandableNotificationRowEx oplusExpandableNotificationRowEx;
        ExpandableNotificationRow view;
        NotificationGroupSplitEx.ExpansionFinishedState expansionFinishedState = this.expansionFinishedState;
        if (expansionFinishedState != null) {
            boolean z = getChildrenContainer().getContainingNotification().mChildrenExpanded;
            if (expansionFinishedState.getHasGroupExpansionAnim() && expansionFinishedState.getExpectedFinishExpansionState() != z) {
                LogUtil.w$default(8, LogUtil.TAG_NOTIFICATION, TAG, PriorityNestedScrollConnection$.ExternalSyntheticOutline0.m("onFinishedExpansion expectExpansionState not match, ignore. current ", " != ", z, expansionFinishedState.getExpectedFinishExpansionState()));
                setExpansionFinishedState(null);
                NotificationStackScrollLayout view2 = ((NotificationStackScrollLayoutController) this.nsslController.get()).getView();
                onFinishedExpansionChange.1.1 r1 = new onFinishedExpansionChange.1.1();
                r1.this$0 = this;
                r1.$finishRunner = runnable;
                VarHandle.storeStoreFence();
                view2.runAfterAnimationFinished$1(r1);
                return;
            }
        }
        runnable.run();
        setExpansionFinishedState(null);
        if (!getChildrenContainer().getContainingNotification().mChildrenExpanded) {
            List<ExpandableNotificationRow> attachedChildren2 = getChildrenContainer().getAttachedChildren();
            if (attachedChildren2 != null) {
                for (ExpandableNotificationRow expandableNotificationRow : attachedChildren2) {
                    expandableNotificationRow.resetUserExpansion();
                    expandableNotificationRow.reset();
                }
            }
            if (LogUtil.getNormalDebug() && LogUtil.isTagValid(LogUtil.TAG_NOTIFICATION)) {
                Log.d("SystemUi--Notification", "OplusNCCOverlayEx-->".concat("resetAllUserOperationState for reason onFinishedExpansion"));
            }
        }
        ViewGroup viewGroup = this.iconContainer;
        if (viewGroup != null) {
            viewGroup.setTranslationY(0.0f);
        }
        NotificationStackScrollLayout view3 = ((NotificationStackScrollLayoutController) this.nsslController.get()).getView();
        if (view3 != null) {
            view3.requestChildrenUpdate$1();
        }
        if (((OplusPanoramicAodCoreStartable) this.oplusPanoramicAodRepository).isPanoramicAod) {
            Choreographer choreographer = Choreographer.getInstance();
            onFinishedExpansionChange.2 r3 = new onFinishedExpansionChange.2();
            r3.this$0 = this;
            VarHandle.storeStoreFence();
            choreographer.postFrameCallback(r3);
        } else if (this.overlayContainer != null) {
            setOverlayVisibility("onFinishedExpansion");
        }
        ExpandableNotificationRow containingNotification = getChildrenContainer().getContainingNotification();
        if (containingNotification == null || containingNotification.mChildrenExpanded || (attachedChildren = getChildrenContainer().getAttachedChildren()) == null) {
            return;
        }
        for (ExpandableNotificationRow expandableNotificationRow2 : attachedChildren) {
            if (expandableNotificationRow2 != null && (oplusExpandableNotificationRowEx = expandableNotificationRow2.mRowEx) != null && (view = ((OplusExpandableNotificationRowExImpl) oplusExpandableNotificationRowEx).getView()) != null) {
                NotificationMenuRowPlugin provider = view.getProvider();
                if (provider != null && provider.isMenuVisible()) {
                    LogUtil.w$default(8, LogUtil.TAG_NOTIFICATION, "OplusExpandableNotificationRowExImpl", "resetCollapsedChildSwipeState: the menu of " + view.getKey() + " is visible, translation: " + view.getTranslation() + ", reset it");
                    NotificationMenuRowPlugin provider2 = view.getProvider();
                    if (provider2 != null) {
                        provider2.resetMenu();
                    }
                }
                if (view.getTranslation() != 0.0f) {
                    LogUtil.w$default(8, LogUtil.TAG_NOTIFICATION, "OplusExpandableNotificationRowExImpl", "resetCollapsedChildSwipeState: translation of " + view.getKey() + " is " + view.getTranslation() + ", set to 0f");
                    view.setTranslation(0.0f);
                }
            }
        }
    }

    public final void onGroupExpandChanged(boolean z) {
        if (z) {
            if (((Boolean) this.separateNotificationAndQSState.getEnableSeparateNotificationAndQS().getValue()).booleanValue() && !this.isProgressiveContainerRegistered) {
                OplusSlidSwitchAnimation.HorizontalTranslationValue horizontalTranslationValue = ((OplusSlidSwitchAnimation) this.slidSwitchAnimation.get()).getHorizontalTranslationValue();
                NotificationChildrenContainer childrenContainer = getChildrenContainer();
                ExpandableNotificationRow containingNotification = getChildrenContainer().getContainingNotification();
                if (containingNotification == null) {
                    return;
                }
                NotificationHeaderView notificationHeaderView = getChildrenContainer().mGroupHeader;
                this.savedClipChildren = Boolean.valueOf(childrenContainer.getClipChildren());
                this.savedClipToPadding = Boolean.valueOf(childrenContainer.getClipToPadding());
                childrenContainer.setClipChildren(false);
                childrenContainer.setClipToPadding(false);
                horizontalTranslationValue.registerContainer(childrenContainer, containingNotification);
                horizontalTranslationValue.register((View) notificationHeaderView);
                this.isProgressiveContainerRegistered = true;
                if (LogUtil.getNormalDebug() && LogUtil.isTagValid(LogUtil.TAG_NOTIFICATION)) {
                    MediaTimeoutListener$PlaybackStateListener$.ExternalSyntheticOutline0.m("registerProgressiveContainer reason=onChildrenExpanded, key=", containingNotification.getKey(), "OplusNCCOverlayEx-->", "SystemUi--Notification");
                    return;
                }
                return;
            }
            return;
        }
        if (this.isProgressiveContainerRegistered) {
            ViewGroup childrenContainer2 = getChildrenContainer();
            View view = getChildrenContainer().mGroupHeader;
            ((OplusSlidSwitchAnimation) this.slidSwitchAnimation.get()).getHorizontalTranslationValue().unRegisterContainer(childrenContainer2);
            ((OplusSlidSwitchAnimation) this.slidSwitchAnimation.get()).getHorizontalTranslationValue().unRegister(view);
            NotificationHeaderView notificationHeaderView2 = getChildrenContainer().mGroupHeader;
            if (notificationHeaderView2 != null) {
                if (notificationHeaderView2.getTranslationX() == 0.0f) {
                    notificationHeaderView2 = null;
                }
                if (notificationHeaderView2 != null) {
                    notificationHeaderView2.setTranslationX(0.0f);
                }
            }
            List<ExpandableNotificationRow> attachedChildren = getChildrenContainer().getAttachedChildren();
            if (attachedChildren != null) {
                for (ExpandableNotificationRow expandableNotificationRow : attachedChildren) {
                    if (expandableNotificationRow.getTranslationX() != 0.0f) {
                        if (LogUtil.getNormalDebug() && LogUtil.isTagValid(LogUtil.TAG_NOTIFICATION)) {
                            Log.d("SystemUi--Notification", "OplusNCCOverlayEx-->".concat("resetGroupHorizontalTranslation reason=onChildrenExpanded key=" + expandableNotificationRow.getKey() + " translationX=" + expandableNotificationRow.getTranslationX()));
                        }
                        expandableNotificationRow.setTranslationX(0.0f);
                    }
                    OplusExpandableNotificationRowEx oplusExpandableNotificationRowEx = expandableNotificationRow.mRowEx;
                    if (oplusExpandableNotificationRowEx != null) {
                        oplusExpandableNotificationRowEx.resetXTranslation(expandableNotificationRow);
                    }
                }
            }
            Boolean bool = this.savedClipChildren;
            if (bool != null) {
                childrenContainer2.setClipChildren(bool.booleanValue());
            }
            Boolean bool2 = this.savedClipToPadding;
            if (bool2 != null) {
                childrenContainer2.setClipToPadding(bool2.booleanValue());
            }
            this.savedClipChildren = null;
            this.savedClipToPadding = null;
            this.isProgressiveContainerRegistered = false;
            if (LogUtil.getNormalDebug() && LogUtil.isTagValid(LogUtil.TAG_NOTIFICATION)) {
                ExpandableNotificationRow containingNotification2 = getChildrenContainer().getContainingNotification();
                MediaTimeoutListener$PlaybackStateListener$.ExternalSyntheticOutline0.m("unregisterProgressiveContainer reason=onChildrenExpanded, key=", containingNotification2 != null ? containingNotification2.getKey() : null, "OplusNCCOverlayEx-->", "SystemUi--Notification");
            }
        }
    }

    public final void onGroupExpansionProgressChange(float f) {
        ViewGroup viewGroup = this.expandGroupHeader;
        if (viewGroup != null) {
            int i = this.expandGroupHeaderTranslationWhenCollapsed;
            viewGroup.setTranslationY(((0 - i) * f) + i);
        }
        GroupExpansionAnimParams groupExpansionAnimParams = GroupExpansionAnimParams.INSTANCE;
        boolean z = getChildrenContainer().mChildrenExpanded;
        groupExpansionAnimParams.getClass();
        float splitPoint = GroupExpansionAnimParams.getSplitPoint(z);
        if (f > splitPoint) {
            updateOverlayClipping(1.0f);
            onGroupOverlayAlphaChange(1.0f - ((MathUtils.constrain(f, splitPoint, 1.0f) - splitPoint) / (1.0f - splitPoint)));
        } else {
            updateOverlayClipping((MathUtils.constrain(f, 0.0f, splitPoint) - 0.0f) / (splitPoint - 0.0f));
            onGroupOverlayAlphaChange(1.0f);
        }
    }

    public final void onGroupOverlayAlphaChange(float f) {
        if (f != 0.0f && f != 1.0f) {
            this.lastLoggedGroupOverlayAlpha = null;
        } else if (!Intrinsics.areEqual(this.lastLoggedGroupOverlayAlpha, f)) {
            if (LogUtil.getNormalDebug() && LogUtil.isTagValid(LogUtil.TAG_NOTIFICATION)) {
                ExpandableNotificationRow containingNotification = getChildrenContainer().getContainingNotification();
                Log.d("SystemUi--Notification", "OplusNCCOverlayEx-->".concat("onGroupOverlayAlphaChange alpha " + f + ",key=" + (containingNotification != null ? containingNotification.getKey() : null)));
            }
            this.lastLoggedGroupOverlayAlpha = Float.valueOf(f);
        }
        GroupOverlayContainerView groupOverlayContainerView = this.overlayContainer;
        if (groupOverlayContainerView != null) {
            groupOverlayContainerView.setAlpha(f);
        }
        ViewGroup viewGroup = this.opusCollapsedGroupHeader;
        if (viewGroup != null) {
            viewGroup.setAlpha(f);
            if (f == 1.0f || f == 0.0f) {
                getChildrenContainer().setCollapsedHeaderVisibleAmount(f);
            }
            int i = f > 0.1f ? 0 : 4;
            if (viewGroup.getVisibility() != i) {
                viewGroup.setVisibility(i);
            }
        }
    }

    public void onOplusNotificationHeaderCreated(NotificationHeaderView notificationHeaderView, ViewGroup viewGroup) {
        this.opusCollapsedGroupHeader = notificationHeaderView;
        this.expandGroupHeader = viewGroup;
        this.iconContainer = notificationHeaderView != null ? (ViewGroup) notificationHeaderView.findViewById(2131363387) : null;
    }

    public final void onUiModeChanged() {
        this.context.getResources().getConfiguration().isNightModeActive();
        this.normalColor = this.context.getResources().getColor(2131102886);
        flushBackground(this.overlayContainer, getChildrenContainer().getContainingNotification(), "uiMode change");
    }

    public void recordExpansionAnimState(NotificationGroupSplitEx.ExpansionFinishedState expansionFinishedState) {
        setExpansionFinishedState(expansionFinishedState);
    }

    public final void refreshOverlayContent(ExpandableNotificationRow expandableNotificationRow, String str) {
        Object obj;
        StatusBarNotification sbn;
        StatusBarNotification sbn2;
        ViewParent parent;
        StatusBarNotification sbn3;
        if (expandableNotificationRow.mIsSummaryWithChildren) {
            cancelPendingOverlayRefresh();
            GroupOverlayContainerView groupOverlayContainerView = this.overlayContainer;
            if (groupOverlayContainerView != null) {
                groupOverlayContainerView.removeAllViews();
                if (getChildrenContainer().getNotificationChildCount() <= 0) {
                    return;
                }
                String key = null;
                try {
                    Trace.beginSection("OplusNCCOverlayEx flushContent");
                    List attachedChildren = getChildrenContainer().getAttachedChildren();
                    if (attachedChildren.isEmpty()) {
                        NotificationEntry entry = expandableNotificationRow.getEntry();
                        LogUtil.w$default(8, LogUtil.TAG_NOTIFICATION, TAG, "flushContent: no attached children in row " + ((entry == null || (sbn3 = entry.getSbn()) == null) ? null : sbn3.getKey()));
                    } else {
                        LinearLayout[] linearLayoutArr = this.viewCache;
                        if (linearLayoutArr != null) {
                            for (LinearLayout linearLayout : linearLayoutArr) {
                                if (linearLayout != null && (parent = linearLayout.getParent()) != null) {
                                    ViewGroup viewGroup = parent instanceof ViewGroup ? (ViewGroup) parent : null;
                                    if (viewGroup != null) {
                                        viewGroup.removeView(linearLayout);
                                    }
                                }
                            }
                        }
                        int iMin = Math.min(attachedChildren.size(), getChildrenContainer().getMaxAllowedVisibleChildren(true));
                        for (int i = 0; i < iMin; i++) {
                            ExpandableNotificationRow expandableNotificationRow2 = (ExpandableNotificationRow) attachedChildren.get(i);
                            expandableNotificationRow2.getClass();
                            flushSingleLineView(expandableNotificationRow2, groupOverlayContainerView, i);
                        }
                        android.os.Trace.endSection();
                        if (LogUtil.getNormalDebug() && LogUtil.isTagValid(LogUtil.TAG_NOTIFICATION)) {
                            String str2 = LogUtil.TAG + LogUtil.TAG_NOTIFICATION;
                            StringBuilder sb = new StringBuilder();
                            sb.append(TAG);
                            sb.append(LogUtil.SEPARATOR);
                            NotificationEntry entry2 = expandableNotificationRow.getEntry();
                            sb.append("flushContent finish for row " + ((entry2 == null || (sbn2 = entry2.getSbn()) == null) ? null : sbn2.getKey()) + ", " + iMin + " children, reason " + str);
                            Log.d(str2, sb.toString());
                        }
                        obj = Result.constructor-impl(Unit.INSTANCE);
                        Throwable th = Result.exceptionOrNull-impl(obj);
                        if (th != null) {
                            NotificationEntry entry3 = expandableNotificationRow.getEntry();
                            if (entry3 != null && (sbn = entry3.getSbn()) != null) {
                                key = sbn.getKey();
                            }
                            LogUtil.e$default(8, LogUtil.TAG_NOTIFICATION, TAG, CursorUtil$.ExternalSyntheticOutline0.m("flushContent for row ", key, " error: ", th.getMessage()));
                        }
                    }
                } catch (Throwable th2) {
                    obj = Result.constructor-impl(ResultKt.createFailure(th2));
                }
                ((ViewBlurManager) this.viewBlurManager.get()).refreshBlurType(groupOverlayContainerView);
                OplusExpandableNotificationRowEx oplusExpandableNotificationRowEx = expandableNotificationRow.mRowEx;
                if (oplusExpandableNotificationRowEx != null) {
                    ((OplusExpandableNotificationRowExImpl) oplusExpandableNotificationRowEx).onNotificationUpdated(false);
                }
            }
        }
    }

    public void setUserLocked(boolean z) {
        this.isUserLocked = z;
        setOverlayVisibility(KeyguardLogger$.ExternalSyntheticOutline0.m("userLocked change to ", z));
    }

    public final boolean shouldDrawStackMask() {
        ExpandableNotificationRow containingNotification;
        KeyguardStackingBackgroundControl keyguardStackingBackgroundControl;
        if (((OplusLockscreenShadeTransitionControllerExImpl) this.lockscreenShadeTransitionController.get()).isOnOrGoingKeyguard(((SysuiStatusBarStateController) this.statusBarStateController.get()).getState() == 1) && (containingNotification = getChildrenContainer().getContainingNotification()) != null && containingNotification.mIsSummaryWithChildren && (this.isUserLocked || this.isGroupExpandAnimating)) {
            NotificationBackgroundView notificationBackgroundView = ((ActivatableNotificationView) containingNotification).mBackgroundNormal;
            NotificationBackgroundViewExt ext = notificationBackgroundView != null ? notificationBackgroundView.getExt() : null;
            NotificationBackgroundViewExtImp notificationBackgroundViewExtImp = ext instanceof NotificationBackgroundViewExtImp ? (NotificationBackgroundViewExtImp) ext : null;
            if (notificationBackgroundViewExtImp != null && (keyguardStackingBackgroundControl = notificationBackgroundViewExtImp.getKeyguardStackingBackgroundControl()) != null && keyguardStackingBackgroundControl.getTargetType() != 0) {
                return true;
            }
        }
        return false;
    }

    public void updateGroupChildStateEx(ExpandableNotificationRow expandableNotificationRow, ExpandableViewState expandableViewState, int i, boolean z, float f, int i2) {
        if (expandableNotificationRow.isChildInGroup()) {
            ExpandableViewState viewState = expandableNotificationRow.getViewState();
            ExpandableNotificationRow notificationParent = expandableNotificationRow.getNotificationParent();
            boolean z2 = false;
            if (notificationParent != null && notificationParent.isGroupExpanded()) {
                z2 = true;
            }
            if (z) {
                float f2 = 1.0f - (i * 0.05f);
                ((RowAnimationStateExImpl) viewState.mExpandableViewStateEx.getRowAnimationStateEx()).requestRowScale("key_grouped_notification_expanding", ScaleState.Companion.invoke(DrawerArrowDrawable$.ExternalSyntheticOutline0.m(1.0f, f2, f, f2)));
            } else if (z2 || !((OplusExpandableNotificationRowExImpl) expandableNotificationRow.mRowEx).isChildrenExpandedAnimating()) {
                ((RowAnimationStateExImpl) viewState.mExpandableViewStateEx.getRowAnimationStateEx()).requestRowScale("key_grouped_notification_expanding", ScaleState.Companion.getINVALID());
                viewState.mExpandableViewStateEx.setGroupExpandContentAlpha(1.0f);
            } else {
                float f3 = 1.0f - (i * 0.05f);
                if (f3 < 0.1f) {
                    f3 = 0.1f;
                }
                ((RowAnimationStateExImpl) viewState.mExpandableViewStateEx.getRowAnimationStateEx()).requestRowScale("key_grouped_notification_expanding", ScaleState.Companion.invoke(f3));
                viewState.mExpandableViewStateEx.setGroupExpandContentAlpha(0.0f);
            }
            if (Build.isDebuggable() && z && LogUtil.getNormalDebug() && LogUtil.isTagValid(LogUtil.TAG_NOTIFICATION)) {
                ScaleState rowScale = ((RowAnimationStateExImpl) viewState.mExpandableViewStateEx.getRowAnimationStateEx()).getRowScale("expanding");
                float groupExpandContentAlpha = viewState.mExpandableViewStateEx.getGroupExpandContentAlpha();
                String key = expandableNotificationRow.getKey();
                StringBuilder sbM = TextFieldImplKt$.ExternalSyntheticOutline0.m("updateGroupChildStateEx groupExpanded ", ", expandingToExpandedGroup ", ", scaleX ", z2, z);
                sbM.append(rowScale);
                sbM.append(", groupExpandContentAlpha ");
                sbM.append(groupExpandContentAlpha);
                sbM.append(", expandFactor ");
                ChildHelper$.ExternalSyntheticOutline0.m(f, i, ", index ", ", topMargin ", sbM);
                sbM.append(i2);
                sbM.append(", key ");
                sbM.append(key);
                Log.d("SystemUi--Notification", "OplusNCCOverlayEx-->".concat(sbM.toString()));
            }
        }
    }

    public void updateNotifParentStateEx(ExpandableViewState expandableViewState, boolean z, float f) {
        OplusExpandableNotificationRowEx oplusExpandableNotificationRowEx;
        ExpandableNotificationRow containingNotification = getChildrenContainer().getContainingNotification();
        if (containingNotification != null) {
            boolean zIsGroupExpanded = containingNotification.isGroupExpanded();
            if (z) {
                expandableViewState.mExpandableViewStateEx.setGroupExpansionProgress(f);
                expandableViewState.mExpandableViewStateEx.setGroupExpandHeaderAlpha(f);
                expandableViewState.mExpandableViewStateEx.setGroupExpandContentAlpha(f);
            } else if (zIsGroupExpanded) {
                expandableViewState.mExpandableViewStateEx.setGroupExpansionProgress(1.0f);
                if (getChildrenContainer().getAttachedChildren().size() == 1 && (((SysuiStatusBarStateController) this.statusBarStateController.get()).getState() == 1 || ((OplusLockscreenShadeTransitionControllerExImpl) this.lockscreenShadeTransitionController.get()).isAnimRunning())) {
                    expandableViewState.mExpandableViewStateEx.setGroupExpandHeaderAlpha(0.0f);
                } else {
                    expandableViewState.mExpandableViewStateEx.setGroupExpandHeaderAlpha(1.0f);
                }
            } else {
                expandableViewState.mExpandableViewStateEx.setGroupExpansionProgress(0.0f);
                expandableViewState.mExpandableViewStateEx.setGroupExpandHeaderAlpha(0.0f);
            }
            if (Build.isDebuggable() && z && LogUtil.getNormalDebug() && LogUtil.isTagValid(LogUtil.TAG_NOTIFICATION)) {
                float groupExpansionProgress = expandableViewState.mExpandableViewStateEx.getGroupExpansionProgress();
                float groupExpandHeaderAlpha = expandableViewState.mExpandableViewStateEx.getGroupExpandHeaderAlpha();
                float groupExpandContentAlpha = expandableViewState.mExpandableViewStateEx.getGroupExpandContentAlpha();
                String key = containingNotification.getKey();
                StringBuilder sbM = TextFieldImplKt$.ExternalSyntheticOutline0.m("updateNotifParentStateEx groupExpanded ", ", expandingToExpandedGroup ", ", expandFactor ", zIsGroupExpanded, z);
                VerticalTactileSurfaceRevealEffect$.ExternalSyntheticOutline0.m(sbM, f, ", groupExpansionProgress ", groupExpansionProgress, ", groupExpandHeaderAlpha ");
                VerticalTactileSurfaceRevealEffect$.ExternalSyntheticOutline0.m(sbM, groupExpandHeaderAlpha, ", groupExpandContentAlpha ", groupExpandContentAlpha, ", key ");
                MediaTimeoutListener$PlaybackStateListener$.ExternalSyntheticOutline0.m(key, "OplusNCCOverlayEx-->", "SystemUi--Notification", sbM);
            }
        }
        boolean zIsAnimating = ViewState.isAnimating(getChildrenContainer().getContainingNotification(), OplusExpandableViewStateExImpl.Companion.getGROUP_EXPANSION_PROGRESS_PROPERTY());
        ExpandableNotificationRow containingNotification2 = getChildrenContainer().getContainingNotification();
        boolean z2 = ((containingNotification2 == null || (oplusExpandableNotificationRowEx = containingNotification2.mRowEx) == null) ? 0.0f : oplusExpandableNotificationRowEx.getGroupExpansionProgress()) > 0.0f && getChildrenContainer().isUserSwipingToExpandRow();
        if (zIsAnimating || z2) {
            return;
        }
        updateOverlayTranslation("update state");
    }

    /* JADX WARN: Code duplicated, block: B:65:0x0157  */
    /* JADX WARN: Code duplicated, block: B:71:0x0168  */
    public final void updateOverlayClipping(float f) {
        ExpandableNotificationRow expandableNotificationRow;
        NotificationContentView showingLayout;
        int i;
        NotificationBlurMode notificationBlurMode;
        NotificationBlurMode notificationBlurMode2;
        Float fValueOf;
        float fFloatValue;
        GroupOverlayContainerView groupOverlayContainerView = this.overlayContainer;
        if (groupOverlayContainerView != null) {
            List attachedChildren = getChildrenContainer().getAttachedChildren();
            if (attachedChildren.isEmpty() || (expandableNotificationRow = (ExpandableNotificationRow) CollectionsKt___CollectionsKt.firstOrNull(attachedChildren)) == null || (showingLayout = expandableNotificationRow.getShowingLayout()) == null) {
                return;
            }
            int expandHeight = expandableNotificationRow.isExpanded(false) ? showingLayout.getExpandHeight() : showingLayout.getMinHeight(true);
            int i2 = this.overlayActualHeight;
            if (i2 >= expandHeight) {
                i = i2 - expandHeight;
                int i3 = i < 0 ? 0 : i;
                if (i3 <= i2) {
                    i2 = i3;
                }
                groupOverlayContainerView.setClipBottomAmount(this.baseClipBottom + ((int) (i2 * f)));
            } else {
                int height = groupOverlayContainerView.getHeight() - (expandHeight - expandableNotificationRow.getClipBottomAmount());
                if (height < 0) {
                    height = 0;
                }
                int i4 = this.baseClipBottom;
                if (height > i4) {
                    height = i4;
                }
                groupOverlayContainerView.setClipBottomAmount(height);
                i2 = 0;
                i = 0;
            }
            int height2 = groupOverlayContainerView.getHeight() - groupOverlayContainerView.getClipBottomAmount();
            if (this.lastInnerShadowHeight != height2 || this.lastWidth != groupOverlayContainerView.getWidth()) {
                this.lastInnerShadowHeight = height2;
                this.lastWidth = groupOverlayContainerView.getWidth();
                groupOverlayContainerView.getBackground().setBounds(0, 0, groupOverlayContainerView.getWidth(), height2);
                ViewBlurProxy viewBlurProxyRequireBlurProxyForView = ((ViewBlurManager) this.viewBlurManager.get()).requireBlurProxyForView(groupOverlayContainerView, ViewBlurManager.CardType.MENU);
                int width = groupOverlayContainerView.getWidth();
                Drawable background = groupOverlayContainerView.getBackground();
                NotificationPlatFormBlurParamsManager notificationPlatFormBlurParamsManager = NotificationPlatFormBlurParamsManager.INSTANCE;
                Context context = this.context;
                GradientStrokeLineParams gradientStrokeLineParam = viewBlurProxyRequireBlurProxyForView.getBlurConfig().getGradientStrokeLineParam();
                float f2 = width;
                float f3 = height2;
                OplusExpandableNotificationRowEx oplusExpandableNotificationRowEx = getChildrenContainer().getContainingNotification().mRowEx;
                if (oplusExpandableNotificationRowEx == null || (notificationBlurMode = oplusExpandableNotificationRowEx.getNotificationBlurMode()) == null) {
                    notificationBlurMode = (NotificationBlurMode) getNotificationMaterialColorEx().getBlurMode().getValue();
                }
                notificationPlatFormBlurParamsManager.updateGradientStrokeLineParamsForNotificationSize(context, gradientStrokeLineParam, f2, f3, notificationBlurMode);
                Context context2 = this.context;
                InnerShadowParams innerShadowParams = viewBlurProxyRequireBlurProxyForView.getBlurConfig().getInnerShadowParams();
                OplusExpandableNotificationRowEx oplusExpandableNotificationRowEx2 = getChildrenContainer().getContainingNotification().mRowEx;
                if (oplusExpandableNotificationRowEx2 == null || (notificationBlurMode2 = oplusExpandableNotificationRowEx2.getNotificationBlurMode()) == null) {
                    notificationBlurMode2 = (NotificationBlurMode) getNotificationMaterialColorEx().getBlurMode().getValue();
                }
                notificationPlatFormBlurParamsManager.updateInnerShadowParamsForNotificationSize(context2, innerShadowParams, f2, f3, notificationBlurMode2);
                AutoBlurDrawable autoBlurDrawable = background instanceof AutoBlurDrawable ? (AutoBlurDrawable) background : null;
                if (autoBlurDrawable != null) {
                    Drawable blurDrawable = viewBlurProxyRequireBlurProxyForView.getBlurDrawable(autoBlurDrawable.getDefaultDrawable());
                    PlatformBlurDrawable platformBlurDrawable = blurDrawable instanceof PlatformBlurDrawable ? (PlatformBlurDrawable) blurDrawable : null;
                    if (platformBlurDrawable != null) {
                        platformBlurDrawable.getBlurDrawable().setInnerShadowParams(viewBlurProxyRequireBlurProxyForView.getBlurConfig().getInnerShadowParams());
                        platformBlurDrawable.getBlurDrawable().setGradientStrokeLineParams(viewBlurProxyRequireBlurProxyForView.getBlurConfig().getGradientStrokeLineParam());
                    }
                }
            }
            NotificationContentView showingLayout2 = expandableNotificationRow.getShowingLayout();
            if (showingLayout2 != null) {
                NotificationViewWrapper visibleWrapper = showingLayout2.getVisibleWrapper();
                CachingIconView icon = visibleWrapper != null ? visibleWrapper.getIcon() : null;
                if (icon != null) {
                    int height3 = icon.getHeight();
                    Integer numValueOf = Integer.valueOf(height3);
                    if (height3 <= 0) {
                        numValueOf = null;
                    }
                    int iIntValue = numValueOf != null ? numValueOf.intValue() : icon.getMeasuredHeight();
                    if (iIntValue > 0) {
                        float translationY = 0.0f;
                        while (true) {
                            if (!Intrinsics.areEqual(icon, showingLayout2)) {
                                translationY += icon.getTranslationY() + icon.getTop();
                                CachingIconView parent = icon.getParent();
                                if (!(parent instanceof View)) {
                                    fValueOf = null;
                                    break;
                                }
                                icon = (View) parent;
                            } else {
                                fValueOf = Float.valueOf((iIntValue / 2.0f) + translationY);
                                break;
                            }
                        }
                    } else {
                        fValueOf = null;
                        break;
                    }
                } else {
                    fValueOf = null;
                    break;
                }
                if (fValueOf != null) {
                    fFloatValue = fValueOf.floatValue();
                } else {
                    fFloatValue = expandHeight / 2.0f;
                }
            } else {
                fFloatValue = expandHeight / 2.0f;
            }
            float f4 = fFloatValue - (this.overlayActualHeight / 2.0f);
            ViewGroup viewGroup = this.iconContainer;
            if (viewGroup != null) {
                viewGroup.setTranslationY(f4 * f);
            }
            if (f != 0.0f && f != 1.0f) {
                groupOverlayContainerView = groupOverlayContainerView;
                this.lastLoggedOverlayClipProgress = null;
            } else if (Intrinsics.areEqual(this.lastLoggedOverlayClipProgress, f)) {
                groupOverlayContainerView = groupOverlayContainerView;
            } else {
                if (LogUtil.getNormalDebug() && LogUtil.isTagValid(LogUtil.TAG_NOTIFICATION)) {
                    StringBuilder sb = new StringBuilder("OplusNCCOverlayEx-->");
                    int height4 = groupOverlayContainerView.getHeight();
                    int measuredHeight = groupOverlayContainerView.getMeasuredHeight();
                    int i5 = this.overlayActualHeight;
                    int i6 = this.baseClipBottom;
                    int clipBottomAmount = groupOverlayContainerView.getClipBottomAmount();
                    int clipBottomAmount2 = expandableNotificationRow.getClipBottomAmount();
                    ExpandableNotificationRow containingNotification = getChildrenContainer().getContainingNotification();
                    String key = containingNotification != null ? containingNotification.getKey() : null;
                    StringBuilder sbM = FloatList$.ExternalSyntheticOutline0.m("updateOverlayClipping height ", height4, measuredHeight, " measuredHeight ", " childHeight ");
                    RectList$.ExternalSyntheticOutline0.m(expandHeight, height2, " innerShadowHeight ", " overlayActualHeight ", sbM);
                    RectList$.ExternalSyntheticOutline0.m(i5, i6, " baseClipBottom ", " heightDiff ", sbM);
                    RectList$.ExternalSyntheticOutline0.m(i, i2, " maxClipBottom ", " progress ", sbM);
                    ChildHelper$.ExternalSyntheticOutline0.m(f, clipBottomAmount, " clipBottomAmount ", " firstChild.clipBottomAmount ", sbM);
                    IconProvider$.ExternalSyntheticOutline0.m(fFloatValue, clipBottomAmount2, " firstChildIconCenterY ", " iconTranslationTarget ", sbM);
                    sbM.append(f4);
                    sbM.append(" key=");
                    sbM.append(key);
                    sb.append(sbM.toString());
                    Log.d("SystemUi--Notification", sb.toString());
                } else {
                    groupOverlayContainerView = groupOverlayContainerView;
                }
                this.lastLoggedOverlayClipProgress = Float.valueOf(f);
            }
            groupOverlayContainerView.invalidateOutline();
        }
    }

    public final void updateOverlayTranslation(String str) {
        GroupOverlayContainerView groupOverlayContainerView;
        ExpandableNotificationRow expandableNotificationRow;
        OplusExpandableNotificationRowEx oplusExpandableNotificationRowEx;
        if (getChildrenContainer().getAttachedChildren().isEmpty() || (groupOverlayContainerView = this.overlayContainer) == null || (expandableNotificationRow = (ExpandableNotificationRow) CollectionsKt___CollectionsKt.firstOrNull(getChildrenContainer().getAttachedChildren())) == null) {
            return;
        }
        boolean z = false;
        int groupTopPaddingWhenCollapsed = getChildrenContainer().mExt.getGroupTopPaddingWhenCollapsed(this.groupCollapsedTopPadding, getChildrenContainer().getAttachedChildren().size() == 1, getChildrenContainer());
        boolean zIsAnimating = ViewState.isAnimating(getChildrenContainer().getContainingNotification(), OplusExpandableViewStateExImpl.Companion.getGROUP_EXPANSION_PROGRESS_PROPERTY());
        ExpandableNotificationRow containingNotification = getChildrenContainer().getContainingNotification();
        float groupExpansionProgress = (containingNotification == null || (oplusExpandableNotificationRowEx = containingNotification.mRowEx) == null) ? 0.0f : oplusExpandableNotificationRowEx.getGroupExpansionProgress();
        if (groupExpansionProgress > 0.0f && getChildrenContainer().isUserSwipingToExpandRow()) {
            z = true;
        }
        float translationY = (zIsAnimating || z) ? expandableNotificationRow.getTranslationY() : expandableNotificationRow.getTranslationY() - groupTopPaddingWhenCollapsed;
        float f = translationY >= 0.0f ? translationY : 0.0f;
        if (f == groupOverlayContainerView.getTranslationY()) {
            ViewGroup viewGroup = this.opusCollapsedGroupHeader;
            if (Intrinsics.areEqual(f, viewGroup != null ? Float.valueOf(viewGroup.getTranslationY()) : null)) {
                return;
            }
        }
        groupOverlayContainerView.setTranslationY(f);
        ViewGroup viewGroup2 = this.opusCollapsedGroupHeader;
        if (viewGroup2 != null) {
            viewGroup2.setTranslationY(f);
        }
        if (Build.isDebuggable() && LogUtil.getNormalDebug() && LogUtil.isTagValid(LogUtil.TAG_NOTIFICATION)) {
            boolean zIsUserSwipingToExpandRow = getChildrenContainer().isUserSwipingToExpandRow();
            String key = expandableNotificationRow.getKey();
            StringBuilder sbM = KeyboardBacklightDialog$RootProperties$.ExternalSyntheticOutline0.m(f, groupTopPaddingWhenCollapsed, "updateOverlayTranslation translationY ", ", topMargin ", ", isAnimating ");
            KeyguardLogger$.ExternalSyntheticOutline0.m(", isUserLocked ", " groupExpansionProgress ", sbM, zIsAnimating, zIsUserSwipingToExpandRow);
            sbM.append(groupExpansionProgress);
            sbM.append(" reason ");
            sbM.append(str);
            sbM.append(", child key ");
            MediaTimeoutListener$PlaybackStateListener$.ExternalSyntheticOutline0.m(key, "OplusNCCOverlayEx-->", "SystemUi--Notification", sbM);
        }
    }
}
