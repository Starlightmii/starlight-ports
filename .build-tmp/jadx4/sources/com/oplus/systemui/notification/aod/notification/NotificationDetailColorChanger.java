package com.oplus.systemui.notification.aod.notification;

import android.content.Context;
import android.graphics.drawable.Drawable;
import android.service.notification.StatusBarNotification;
import android.util.Log;
import android.view.View;
import androidx.appcompat.widget.AppCompatTextView;
import androidx.car.app.model.constraints.ActionsConstraints$;
import com.android.systemui.plugins.custom.CustomRowEntry;
import com.android.systemui.statusbar.notification.NotificationUtilsKt;
import com.android.systemui.statusbar.notification.collection.NotificationEntry;
import com.android.systemui.statusbar.notification.row.ActivatableNotificationView;
import com.android.systemui.statusbar.notification.row.ExpandableNotificationRow;
import com.android.systemui.statusbar.notification.row.NotificationBackgroundView;
import com.android.systemui.statusbar.notification.row.NotificationBackgroundViewExt;
import com.android.systemui.statusbar.notification.row.OplusExpandableNotificationRowEx;
import com.android.systemui.statusbar.notification.row.wrapper.NotificationCustomViewWrapper;
import com.android.systemui.statusbar.notification.stack.MediaContainerView;
import com.android.systemui.statusbar.notification.stack.NotificationChildrenContainer;
import com.android.systemui.statusbar.policy.ConfigurationController;
import com.oplus.systemui.aod.display.OplusAODRefreshRateController$RequestRefreshRateRunnable$;
import com.oplus.systemui.media.PageIndicator;
import com.oplus.systemui.media.controls.ui.OplusMediaCarouselView;
import com.oplus.systemui.notification.lockscreen.notification.CapsuleNotificationCardView;
import com.oplus.systemui.notification.lockscreen.notification.CapsuleNotificationContainerView;
import com.oplus.systemui.notification.lockscreen.notification.CapsuleNotificationDataController;
import com.oplus.systemui.notification.row.overlay.NotificationInfoTextView;
import com.oplus.systemui.notification.row.overlay.NotificationOverlayBackgroundView;
import com.oplus.systemui.statusbar.notification.customcard.OplusCustomRow;
import com.oplus.systemui.statusbar.notification.row.NotificationBackgroundViewExtImp;
import com.oplus.systemui.statusbar.notification.row.OplusExpandableNotificationRowExImpl;
import com.oplus.util.OplusDarkModeUtil;
import com.oplusos.systemui.common.blurability.ViewBlurProxy;
import com.oplusos.systemui.common.blurability.platformblur.PlatformBlurDrawable;
import com.oplusos.systemui.common.util.LogUtil;
import dagger.Lazy;
import java.util.List;
import java.util.Set;
import kotlin.collections.CollectionsKt__CollectionsKt;
import kotlin.collections.SetsKt__SetsKt;

/* JADX INFO: compiled from: go/retraceme fd74bf9514dd0739b92d67a6d48a51eb10aaf8df760d83496b8dcfcf83e5f698 */
/* JADX INFO: loaded from: /home/starlight/Projects/Starlight Ports/.build-tmp/classes4.dex */
public final class NotificationDetailColorChanger implements ConfigurationController.ConfigurationListener {
    private static final String TAG = "NotificationDetailColorChanger";
    private final NotificationAodLogger aodLog;
    private final ConfigurationController configurationController;
    private final Context context;
    private final CustomRowBgAodWrapper customRowBgAodWrapper;
    private final Lazy drawableBgWrapper;
    private final ImageViewAodWrapper imageViewWrapper;
    public boolean isGlobalIdle;
    private final Lazy mediaBgAodWrapper;
    private final NormalBgAodWrapper normalBgAodWrapper;
    private final Lazy oplusCustomRowAodBackgroundChanger;
    private final Lazy pageIndicator;
    private final Lazy seekbarAodWrapper;
    private final TextViewAodWrapper textViewWrapper;
    private final Lazy timeViewWrapper;
    public static final Companion Companion = new Companion();
    private static final List<Integer> supportNightTypes = CollectionsKt__CollectionsKt.listOf(new Integer[]{5, 4});
    private static final List<Integer> excludeViewIds = CollectionsKt__CollectionsKt.listOf(new Integer[]{2131364314, 2131364415});
    private static final Set<String> CUSTOM_CONTENT_AOD_PACKAGE_WHITELIST = SetsKt__SetsKt.setOf(new String[]{"com.app.abhibus", "com.coffye.mcaffeine"});

    public NotificationDetailColorChanger(Context context, TextViewAodWrapper textViewAodWrapper, ImageViewAodWrapper imageViewAodWrapper, NormalBgAodWrapper normalBgAodWrapper, CustomRowBgAodWrapper customRowBgAodWrapper, Lazy lazy, Lazy lazy2, Lazy lazy3, Lazy lazy4, Lazy lazy5, NotificationAodLogger notificationAodLogger, Lazy lazy6, ConfigurationController configurationController) {
        this.context = context;
        this.textViewWrapper = textViewAodWrapper;
        this.imageViewWrapper = imageViewAodWrapper;
        this.normalBgAodWrapper = normalBgAodWrapper;
        this.customRowBgAodWrapper = customRowBgAodWrapper;
        this.mediaBgAodWrapper = lazy;
        this.pageIndicator = lazy2;
        this.seekbarAodWrapper = lazy3;
        this.timeViewWrapper = lazy4;
        this.drawableBgWrapper = lazy5;
        this.aodLog = notificationAodLogger;
        this.oplusCustomRowAodBackgroundChanger = lazy6;
        this.configurationController = configurationController;
        configurationController.addCallback(this);
    }

    public static void changeAodColor(AodViewWrapper aodViewWrapper, float f, int i) {
        AodColor aodColor = aodViewWrapper.getAodColor();
        int i2 = 0;
        int state = aodColor != null ? aodColor.getState() : 0;
        if (f > 0.0f) {
            i2 = 2;
        } else if (f != 0.0f) {
            i2 = state;
        }
        if (i2 == 0) {
            if (aodColor == null) {
                return;
            }
            if (aodViewWrapper.isSame(aodColor)) {
                aodViewWrapper.restoreColor(aodColor);
            }
            aodViewWrapper.setAodColor(null);
            return;
        }
        if (i2 != 2) {
            return;
        }
        if (aodColor == null || !aodViewWrapper.isSame(aodColor)) {
            if (aodColor != null && LogUtil.getNormalDebug() && LogUtil.isTagValid(LogUtil.TAG_NOTIFICATION)) {
                Log.d("SystemUi--Notification", "NotificationDetailColorChanger-->changeAodTextColor:color changed");
            }
            aodColor = aodViewWrapper.createAodColor(i);
            aodViewWrapper.setAodColor(aodColor);
        }
        aodViewWrapper.updateColor(aodColor, f);
        aodColor.setState(i2);
    }

    /* JADX WARN: Code restructure failed: missing block: B:27:0x003b, code lost:
    
        r13 = (android.view.ViewGroup) r10;
     */
    /* JADX WARN: Code restructure failed: missing block: B:28:0x0042, code lost:
    
        if (r13.getBackground() == null) goto L30;
     */
    /* JADX WARN: Code restructure failed: missing block: B:29:0x0044, code lost:
    
        ((com.oplus.systemui.notification.aod.notification.DrawableBgWrapper) r9.drawableBgWrapper.get()).setView(r13);
        changeAodColor((com.oplus.systemui.notification.aod.notification.AodViewWrapper) r9.drawableBgWrapper.get(), r11, r12);
     */
    /*
        Code decompiled incorrectly, please refer to instructions dump.
        To view partially-correct add '--show-bad-code' argument
    */
    public static void changeAodViewColorInternal$default(com.oplus.systemui.notification.aod.notification.NotificationDetailColorChanger r9, android.view.View r10, float r11, int r12, boolean r13, java.lang.String r14, int r15) {
        /*
            Method dump skipped, instruction units count: 305
            To view this dump add '--comments-level debug' option
        */
        throw new UnsupportedOperationException("Method not decompiled: com.oplus.systemui.notification.aod.notification.NotificationDetailColorChanger.changeAodViewColorInternal$default(com.oplus.systemui.notification.aod.notification.NotificationDetailColorChanger, android.view.View, float, int, boolean, java.lang.String, int):void");
    }

    public static final int resolveRowRootType(Object obj) {
        return Companion.resolveRowRootType(obj);
    }

    public static final int resolveViewType(View view, int i) {
        return Companion.resolveViewType(view, i);
    }

    public final void addBackgroundView(ExpandableNotificationRow expandableNotificationRow) {
        NotificationOverlayBackgroundView notificationOverlayBackgroundView = (NotificationOverlayBackgroundView) expandableNotificationRow.findViewById(2131364389);
        if (notificationOverlayBackgroundView != null) {
            this.normalBgAodWrapper.setView(expandableNotificationRow);
            this.normalBgAodWrapper.setStackColor(notificationOverlayBackgroundView);
        }
    }

    /* JADX WARN: Multi-variable type inference failed */
    public final void changeAodCustomRowColor(OplusCustomRow oplusCustomRow, float f, boolean z) {
        NotificationDetailColorChanger notificationDetailColorChanger;
        float f2;
        ((OplusCustomRowAodBackgroundChanger) this.oplusCustomRowAodBackgroundChanger.get()).changeAodOverlayViewColor(oplusCustomRow, f);
        AppCompatTextView appCompatTextView = (NotificationInfoTextView) oplusCustomRow.findViewById(2131364390);
        if (appCompatTextView != null) {
            notificationDetailColorChanger = this;
            f2 = f;
            changeAodViewColorInternal$default(notificationDetailColorChanger, appCompatTextView, f2, 0, z, null, 20);
        } else {
            notificationDetailColorChanger = this;
            f2 = f;
        }
        notificationDetailColorChanger.customRowBgAodWrapper.setView(oplusCustomRow);
        boolean zIsSecondStackOverlay = notificationDetailColorChanger.customRowBgAodWrapper.isSecondStackOverlay(oplusCustomRow);
        AodViewWrapper.Companion companion = AodViewWrapper.Companion;
        boolean z2 = (companion.getAodColor(oplusCustomRow) == null && notificationDetailColorChanger.customRowBgAodWrapper.getAodColor() == null) ? false : true;
        if (zIsSecondStackOverlay || z2) {
            if (!zIsSecondStackOverlay || notificationDetailColorChanger.isIdle(f2)) {
                changeAodColor(notificationDetailColorChanger.customRowBgAodWrapper, 0.0f, 2);
                companion.setAodColor(oplusCustomRow, (AodColor) null);
            } else if (oplusCustomRow.getVisibility() == 0) {
                companion.setAodColor(oplusCustomRow, AodColor.Companion.getEMPTY());
                changeAodColor(notificationDetailColorChanger.customRowBgAodWrapper, f2, 2);
            }
        }
        OplusCustomRow.Companion companion2 = OplusCustomRow.Companion;
        CustomRowEntry entry = oplusCustomRow.getEntry();
        if (companion2.isOplusMediaCustomRow(entry != null ? entry.getServiceId() : null)) {
            if (notificationDetailColorChanger.isIdle(f2)) {
                ((OplusCustomRowAodBackgroundChanger) notificationDetailColorChanger.oplusCustomRowAodBackgroundChanger.get()).clearMask(oplusCustomRow);
            } else {
                if (oplusCustomRow.getVisibility() != 0) {
                    return;
                }
                ((OplusCustomRowAodBackgroundChanger) notificationDetailColorChanger.oplusCustomRowAodBackgroundChanger.get()).applyMask(oplusCustomRow, f2);
            }
        }
    }

    public final void changeAodViewColor(ExpandableNotificationRow expandableNotificationRow, float f, boolean z) {
        NotificationChildrenContainer childrenContainer;
        List<ExpandableNotificationRow> attachedChildren;
        StatusBarNotification sbn;
        NotificationChildrenContainer childrenContainer2;
        List<ExpandableNotificationRow> attachedChildren2;
        OplusExpandableNotificationRowEx oplusExpandableNotificationRowEx;
        NotificationChildrenContainer childrenContainer3;
        List<ExpandableNotificationRow> attachedChildren3;
        NotificationChildrenContainer childrenContainer4;
        List<ExpandableNotificationRow> attachedChildren4;
        this.normalBgAodWrapper.getAodStrokeInnerShadowChanger().changeAodEffectAlpha(expandableNotificationRow, f, z);
        if (!((ActivatableNotificationView) expandableNotificationRow).mBackgroundNormal.getExt().noColorized()) {
            this.normalBgAodWrapper.setView(expandableNotificationRow);
            NormalBgAodWrapper normalBgAodWrapper = this.normalBgAodWrapper;
            if (z) {
                f = 0.0f;
            }
            changeAodColor(normalBgAodWrapper, f, 2);
            return;
        }
        String packageName = null;
        if (isIdle(f)) {
            AodViewWrapper.Companion companion = AodViewWrapper.Companion;
            if (companion.getAodColor(expandableNotificationRow) == null) {
                return;
            }
            companion.setAodColor(expandableNotificationRow, (AodColor) null);
            if (expandableNotificationRow.mIsSummaryWithChildren && (childrenContainer4 = expandableNotificationRow.getChildrenContainer()) != null && (attachedChildren4 = childrenContainer4.getAttachedChildren()) != null) {
                for (ExpandableNotificationRow expandableNotificationRow2 : attachedChildren4) {
                    AodViewWrapper.Companion companion2 = AodViewWrapper.Companion;
                    expandableNotificationRow2.getClass();
                    if (companion2.getAodColor(expandableNotificationRow2) != null) {
                        companion2.setAodColor(expandableNotificationRow2, (AodColor) null);
                    }
                }
            }
        } else {
            if (expandableNotificationRow.getVisibility() != 0) {
                return;
            }
            AodViewWrapper.Companion.setAodColor(expandableNotificationRow, AodColor.Companion.getEMPTY());
            if (expandableNotificationRow.mIsSummaryWithChildren && (childrenContainer = expandableNotificationRow.getChildrenContainer()) != null && (attachedChildren = childrenContainer.getAttachedChildren()) != null) {
                for (ExpandableNotificationRow expandableNotificationRow3 : attachedChildren) {
                    if (expandableNotificationRow.getVisibility() == 0) {
                        AodViewWrapper.Companion companion3 = AodViewWrapper.Companion;
                        expandableNotificationRow3.getClass();
                        companion3.setAodColor(expandableNotificationRow3, AodColor.Companion.getEMPTY());
                    }
                }
            }
        }
        this.normalBgAodWrapper.setView(expandableNotificationRow);
        changeAodColor(this.normalBgAodWrapper, f, 2);
        if (!isIdle(f) && expandableNotificationRow.mIsSummaryWithChildren && (oplusExpandableNotificationRowEx = expandableNotificationRow.mRowEx) != null && ((OplusExpandableNotificationRowExImpl) oplusExpandableNotificationRowEx).isChildrenExpandedAnimating() && (childrenContainer3 = expandableNotificationRow.getChildrenContainer()) != null && (attachedChildren3 = childrenContainer3.getAttachedChildren()) != null) {
            for (ExpandableNotificationRow expandableNotificationRow4 : attachedChildren3) {
                NormalBgAodWrapper normalBgAodWrapper2 = this.normalBgAodWrapper;
                expandableNotificationRow4.getClass();
                normalBgAodWrapper2.setView(expandableNotificationRow4);
                changeAodColor(this.normalBgAodWrapper, f, 2);
            }
        }
        if (isIdle(f) && expandableNotificationRow.mIsSummaryWithChildren && (childrenContainer2 = expandableNotificationRow.getChildrenContainer()) != null && (attachedChildren2 = childrenContainer2.getAttachedChildren()) != null) {
            for (ExpandableNotificationRow expandableNotificationRow5 : attachedChildren2) {
                NormalBgAodWrapper normalBgAodWrapper3 = this.normalBgAodWrapper;
                expandableNotificationRow5.getClass();
                normalBgAodWrapper3.setView(expandableNotificationRow5);
                changeAodColor(this.normalBgAodWrapper, 0.0f, 2);
            }
        }
        int i = expandableNotificationRow.getVisibleNotificationViewWrapper() instanceof NotificationCustomViewWrapper ? 8 : 2;
        NotificationEntry entry = expandableNotificationRow.getEntry();
        if (entry != null && (sbn = entry.getSbn()) != null) {
            packageName = sbn.getPackageName();
        }
        changeAodViewColorInternal$default(this, expandableNotificationRow, f, i, false, packageName, 8);
    }

    /* JADX WARN: Code duplicated, block: B:12:0x001c  */
    public final void changeCapsuleViewColor(View view, float f) {
        int i;
        if (view instanceof CapsuleNotificationContainerView) {
            CapsuleNotificationCardView currentView = ((CapsuleNotificationContainerView) view).getCurrentView();
            if ((currentView != null ? currentView.getCardType() : null) == CapsuleNotificationDataController.CardType.AGGREGATE) {
                i = 10;
            } else {
                i = 2;
            }
        } else {
            i = 2;
        }
        int i2 = i;
        if (isIdle(f)) {
            AodViewWrapper.Companion companion = AodViewWrapper.Companion;
            if (companion.getAodColor(view) == null) {
                return;
            } else {
                companion.setAodColor(view, (AodColor) null);
            }
        } else if (view.getVisibility() != 0) {
            return;
        } else {
            AodViewWrapper.Companion.setAodColor(view, AodColor.Companion.getEMPTY());
        }
        changeAodViewColorInternal$default(this, view, f, i2, false, null, 24);
    }

    public final void changeMediaViewColor(MediaContainerView mediaContainerView, float f) {
        PageIndicator pageIndicator;
        OplusMediaCarouselView oplusMediaCarouselView = (OplusMediaCarouselView) mediaContainerView.findViewById(2131363895);
        if (oplusMediaCarouselView == null || (pageIndicator = (PageIndicator) mediaContainerView.findViewById(2131363913)) == null) {
            return;
        }
        if (isIdle(f)) {
            AodViewWrapper.Companion companion = AodViewWrapper.Companion;
            if (companion.getAodColor(mediaContainerView) == null) {
                return;
            } else {
                companion.setAodColor(mediaContainerView, (AodColor) null);
            }
        } else if (mediaContainerView.getVisibility() != 0) {
            return;
        } else {
            AodViewWrapper.Companion.setAodColor(mediaContainerView, AodColor.Companion.getEMPTY());
        }
        boolean zIsNightMode = OplusDarkModeUtil.isNightMode(this.context);
        if (!zIsNightMode) {
            ((MediaBgAodWrapper) this.mediaBgAodWrapper.get()).setView(oplusMediaCarouselView);
            changeAodColor((AodViewWrapper) this.mediaBgAodWrapper.get(), f, 6);
            if (pageIndicator.getVisibility() == 0) {
                ((PageIndicatorAodWrapper) this.pageIndicator.get()).setView(pageIndicator);
                changeAodColor((AodViewWrapper) this.pageIndicator.get(), f, 6);
            }
        }
        changeAodViewColorInternal$default(this, mediaContainerView, f, 6, zIsNightMode, null, 16);
    }

    public final void checkFirstStackIdle(ExpandableNotificationRow expandableNotificationRow) {
        Drawable[] allStackBlurDrawable = ((NotificationBackgroundViewExtImp) ((ActivatableNotificationView) expandableNotificationRow).mBackgroundNormal.getExt()).getKeyguardStackingBackgroundControl().getAllStackBlurDrawable(true);
        if (allStackBlurDrawable != null) {
            int length = allStackBlurDrawable.length;
            int i = 0;
            int i2 = 0;
            while (i < length) {
                Drawable drawable = allStackBlurDrawable[i];
                int i3 = i2 + 1;
                if (drawable instanceof PlatformBlurDrawable) {
                    PlatformBlurDrawable platformBlurDrawable = (PlatformBlurDrawable) drawable;
                    if (platformBlurDrawable.isAodDirty) {
                        LogUtil.w$default(8, LogUtil.TAG_NOTIFICATION, TAG, "checkFirstStackIdle:fail");
                        this.aodLog.logClearAodDirty(drawable, ActionsConstraints$.ExternalSyntheticOutline0.m(i2, "checkIdle(", ")"), expandableNotificationRow, 1);
                        platformBlurDrawable.changeBlurColor(platformBlurDrawable.getCurrentBlurMixConfig(), true);
                    }
                }
                i++;
                i2 = i3;
            }
        }
    }

    public final void checkNormalBgIdle(ExpandableNotificationRow expandableNotificationRow) {
        ViewBlurProxy viewBlurProxy;
        PlatformBlurDrawable cachePlatformStaticBlurDrawable;
        NotificationBackgroundView notificationBackgroundView;
        NotificationBackgroundViewExt ext = (expandableNotificationRow == null || (notificationBackgroundView = ((ActivatableNotificationView) expandableNotificationRow).mBackgroundNormal) == null) ? null : notificationBackgroundView.getExt();
        NotificationBackgroundViewExtImp notificationBackgroundViewExtImp = ext instanceof NotificationBackgroundViewExtImp ? (NotificationBackgroundViewExtImp) ext : null;
        if (notificationBackgroundViewExtImp == null || (viewBlurProxy = notificationBackgroundViewExtImp.getViewBlurProxy()) == null || (cachePlatformStaticBlurDrawable = viewBlurProxy.getCachePlatformStaticBlurDrawable()) == null || !cachePlatformStaticBlurDrawable.isAodDirty) {
            return;
        }
        OplusAODRefreshRateController$RequestRefreshRateRunnable$.ExternalSyntheticOutline0.m(8, "checkNormalBgIdle:fail,", NotificationUtilsKt.getLogKeyNonNull(expandableNotificationRow.getEntry()), LogUtil.TAG_NOTIFICATION, TAG);
        this.aodLog.logClearAodDirty(cachePlatformStaticBlurDrawable, "checkNormalBgIdle", expandableNotificationRow, 0);
        cachePlatformStaticBlurDrawable.changeBlurColor(cachePlatformStaticBlurDrawable.getCurrentBlurMixConfig(), true);
        changeAodViewColor(expandableNotificationRow, 0.0f, false);
    }

    public final void clearContentView(View view) {
        changeAodViewColorInternal$default(this, view, 0.0f, 2, false, null, 16);
        NotificationOverlayBackgroundView notificationOverlayBackgroundView = (NotificationOverlayBackgroundView) view.findViewById(2131364389);
        if (notificationOverlayBackgroundView != null) {
            this.normalBgAodWrapper.clearStackColor(notificationOverlayBackgroundView);
            this.customRowBgAodWrapper.clearStackColor(notificationOverlayBackgroundView);
        }
    }

    public final Context getContext() {
        return this.context;
    }

    public final boolean isIdle(float f) {
        return this.isGlobalIdle || f == 0.0f;
    }

    public final void onUiModeChanged() {
        this.normalBgAodWrapper.updateResource();
        this.customRowBgAodWrapper.updateResource();
        ((MediaBgAodWrapper) this.mediaBgAodWrapper.get()).updateResource();
    }

    public final void removeConfigurationCallback() {
        this.configurationController.removeCallback(this);
    }

    public final void setGlobalIdle(boolean z) {
        this.isGlobalIdle = z;
    }

    /* JADX WARN: Multi-variable type inference failed */
    public final void addBackgroundView(OplusCustomRow oplusCustomRow) {
        NotificationOverlayBackgroundView notificationOverlayBackgroundView = (NotificationOverlayBackgroundView) oplusCustomRow.findViewById(2131364389);
        if (notificationOverlayBackgroundView != null) {
            this.customRowBgAodWrapper.setView(oplusCustomRow);
            this.customRowBgAodWrapper.setStackColor(notificationOverlayBackgroundView);
        }
    }
}
