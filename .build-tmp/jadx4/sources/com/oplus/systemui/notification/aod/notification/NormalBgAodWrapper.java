package com.oplus.systemui.notification.aod.notification;

import android.content.Context;
import android.graphics.drawable.Drawable;
import android.view.View;
import com.android.systemui.statusbar.notification.row.ActivatableNotificationView;
import com.android.systemui.statusbar.notification.row.ExpandableNotificationRow;
import com.android.systemui.statusbar.notification.row.OplusExpandableNotificationRowEx;
import com.android.systemui.statusbar.notification.stack.NotificationChildrenContainer;
import com.android.systemui.statusbar.notification.stack.NotificationChildrenContainerOverlayEx;
import com.oplus.systemui.notification.row.overlay.NotificationOverlayBackgroundView;
import com.oplus.systemui.notification.row.overlay.OplusNotificationOverlayInteractor;
import com.oplus.systemui.statusbar.notification.row.NotificationBackgroundViewExtImp;
import com.oplus.systemui.statusbar.notification.row.OplusExpandableNotificationRowExImpl;
import com.oplus.util.OplusDarkModeUtil;
import com.oplusos.systemui.common.blurability.ViewBlurProxy;
import com.oplusos.systemui.common.blurability.platformblur.PlatformBlurDrawable;
import java.util.LinkedHashSet;
import java.util.Set;
import kotlin.Pair;
import kotlin.TuplesKt;
import kotlin.jvm.internal.Intrinsics;

/* JADX INFO: compiled from: go/retraceme fd74bf9514dd0739b92d67a6d48a51eb10aaf8df760d83496b8dcfcf83e5f698 */
/* JADX INFO: loaded from: /home/starlight/Projects/Starlight Ports/.build-tmp/classes4.dex */
public final class NormalBgAodWrapper implements AodViewWrapper {
    private final NotificationAodBackgroundChanger aodBackgroundChanger;
    private final NotificationAodLogger aodLog;
    private final NotificationAodStrokeInnerShadowChanger aodStrokeInnerShadowChanger;
    private final Context context;
    private Pair<Integer, Integer> layer0;
    private Pair<Integer, Integer> layer1;
    private Pair<Integer, Integer> layer2;
    private final OplusNotificationOverlayInteractor overlayInteractor;
    public ExpandableNotificationRow view;

    public NormalBgAodWrapper(Context context, OplusNotificationOverlayInteractor oplusNotificationOverlayInteractor, NotificationAodBackgroundChanger notificationAodBackgroundChanger, NotificationAodStrokeInnerShadowChanger notificationAodStrokeInnerShadowChanger, NotificationAodLogger notificationAodLogger) {
        this.context = context;
        this.overlayInteractor = oplusNotificationOverlayInteractor;
        this.aodBackgroundChanger = notificationAodBackgroundChanger;
        this.aodStrokeInnerShadowChanger = notificationAodStrokeInnerShadowChanger;
        this.aodLog = notificationAodLogger;
        this.layer0 = TuplesKt.to(Integer.valueOf(context.getColor(2131099868)), Integer.valueOf(context.getColor(2131099867)));
        this.layer1 = TuplesKt.to(Integer.valueOf(context.getColor(2131099866)), Integer.valueOf(context.getColor(2131099865)));
        this.layer2 = TuplesKt.to(Integer.valueOf(context.getColor(2131099870)), Integer.valueOf(context.getColor(2131099869)));
    }

    private final Pair<Integer, Integer> getEndColor() {
        int type = getType();
        if (type != 1) {
            return type != 2 ? this.layer0 : this.layer2;
        }
        return this.layer1;
    }

    /* JADX WARN: Code duplicated, block: B:9:0x0021  */
    public static Pair getOplusDrawableAndType(ExpandableNotificationRow expandableNotificationRow, int i) {
        PlatformBlurDrawable platformBlurDrawable;
        NotificationBackgroundViewExtImp notificationBackgroundViewExtImp = (NotificationBackgroundViewExtImp) ((ActivatableNotificationView) expandableNotificationRow).mBackgroundNormal.getExt();
        PlatformBlurDrawable platformBlurDrawable2 = null;
        if (i != 0) {
            NotificationOverlayBackgroundView notificationOverlayBackgroundView = (NotificationOverlayBackgroundView) expandableNotificationRow.findViewById(2131364389);
            if (notificationOverlayBackgroundView != null) {
                Drawable blurDrawable = notificationOverlayBackgroundView.getBlurDrawable();
                if (blurDrawable instanceof PlatformBlurDrawable) {
                    platformBlurDrawable = (PlatformBlurDrawable) blurDrawable;
                } else {
                    platformBlurDrawable = null;
                }
            } else {
                platformBlurDrawable = null;
            }
            if (platformBlurDrawable == null) {
                Drawable tagStackBlurDrawable = ((NotificationBackgroundViewExtImp) ((ActivatableNotificationView) expandableNotificationRow).mBackgroundNormal.getExt()).getKeyguardStackingBackgroundControl().getTagStackBlurDrawable();
                platformBlurDrawable = tagStackBlurDrawable instanceof PlatformBlurDrawable ? (PlatformBlurDrawable) tagStackBlurDrawable : null;
                if (platformBlurDrawable != null) {
                    i = 1;
                }
            }
        } else {
            platformBlurDrawable = null;
        }
        if (platformBlurDrawable == null) {
            ViewBlurProxy viewBlurProxy = notificationBackgroundViewExtImp.getViewBlurProxy();
            Drawable blurDrawable2 = viewBlurProxy != null ? viewBlurProxy.getBlurDrawable(null) : null;
            if (blurDrawable2 instanceof PlatformBlurDrawable) {
                platformBlurDrawable2 = (PlatformBlurDrawable) blurDrawable2;
            }
        } else {
            platformBlurDrawable2 = platformBlurDrawable;
        }
        return TuplesKt.to(platformBlurDrawable2, Integer.valueOf(i));
    }

    private final int getType() {
        NotificationBackgroundViewExtImp notificationBackgroundViewExtImp = (NotificationBackgroundViewExtImp) ((ActivatableNotificationView) getView()).mBackgroundNormal.getExt();
        if (!this.overlayInteractor.isOverlayShowing(getView(), true) && (notificationBackgroundViewExtImp.getStackedNotificationMaskColor() == 0 || notificationBackgroundViewExtImp.getStackedType() != 2)) {
            return (notificationBackgroundViewExtImp.getStackedNotificationMaskColor() == 0 || notificationBackgroundViewExtImp.getStackedType() != 1) ? 0 : 1;
        }
        return 2;
    }

    public final void clearStackColor(NotificationOverlayBackgroundView notificationOverlayBackgroundView) {
        Drawable blurDrawable = notificationOverlayBackgroundView.getBlurDrawable();
        PlatformBlurDrawable platformBlurDrawable = blurDrawable instanceof PlatformBlurDrawable ? (PlatformBlurDrawable) blurDrawable : null;
        if (platformBlurDrawable == null || !platformBlurDrawable.isAodDirty) {
            return;
        }
        this.aodLog.logClearAodDirty(platformBlurDrawable, "clearStackColor", getView(), 2);
        platformBlurDrawable.changeBlurColor(platformBlurDrawable.getCurrentBlurMixConfig(), true);
    }

    @Override // com.oplus.systemui.notification.aod.notification.AodViewWrapper
    public NormalBgAodColor createAodColor(int i) {
        BgAodData empty;
        Set linkedHashSet;
        PlatformBlurDrawable platformBlurDrawable = (PlatformBlurDrawable) getOplusDrawableAndType(getView(), getType()).getFirst();
        if (platformBlurDrawable == null || (empty = AodBgViewWrapperKt.access$readBgAodData(platformBlurDrawable, getType())) == null) {
            empty = AodBgConstants.INSTANCE.getEMPTY();
        }
        Pair<Integer, Integer> endColor = getEndColor();
        int type = getType();
        NormalBgAodColor aodColor = getAodColor();
        if (aodColor == null || (linkedHashSet = aodColor.getFirstStackCache()) == null) {
            linkedHashSet = new LinkedHashSet();
        }
        return AodBgViewWrapperKt.access$createBgAodColor(empty, endColor, type, linkedHashSet);
    }

    @Override // com.oplus.systemui.notification.aod.notification.AodViewWrapper
    public NormalBgAodColor getAodColor() {
        NormalBgAodColor aodColor = AodViewWrapper.Companion.getAodColor(((ActivatableNotificationView) getView()).mBackgroundNormal);
        if (aodColor instanceof NormalBgAodColor) {
            return aodColor;
        }
        return null;
    }

    public final NotificationAodStrokeInnerShadowChanger getAodStrokeInnerShadowChanger() {
        return this.aodStrokeInnerShadowChanger;
    }

    @Override // com.oplus.systemui.notification.aod.notification.AodViewWrapper
    public ExpandableNotificationRow getView() {
        ExpandableNotificationRow expandableNotificationRow = this.view;
        if (expandableNotificationRow != null) {
            return expandableNotificationRow;
        }
        return null;
    }

    @Override // com.oplus.systemui.notification.aod.notification.AodViewWrapper
    public boolean isSame(NormalBgAodColor normalBgAodColor) {
        BgAodData bgAodDataAccess$readBgAodData;
        PlatformBlurDrawable platformBlurDrawable = (PlatformBlurDrawable) getOplusDrawableAndType(getView(), getType()).getFirst();
        return Intrinsics.areEqual((platformBlurDrawable == null || (bgAodDataAccess$readBgAodData = AodBgViewWrapperKt.access$readBgAodData(platformBlurDrawable, getType())) == null) ? null : bgAodDataAccess$readBgAodData.getOrigin(), normalBgAodColor.getStart().getOrigin());
    }

    @Override // com.oplus.systemui.notification.aod.notification.AodViewWrapper
    public void restoreColor(NormalBgAodColor normalBgAodColor) {
        int type = getType();
        PlatformBlurDrawable platformBlurDrawable = (PlatformBlurDrawable) getOplusDrawableAndType(getView(), type).getFirst();
        if (platformBlurDrawable != null) {
            this.aodLog.logClearAodDirty(platformBlurDrawable, "restoreColor", getView(), type);
            platformBlurDrawable.setAodState(false);
            platformBlurDrawable.changeBlurColor(platformBlurDrawable.getCurrentBlurMixConfig(), true);
        }
        this.aodBackgroundChanger.changeAodViewColor(getView(), 0.0f);
        this.aodStrokeInnerShadowChanger.changeAodEffectAlpha(getView(), 0.0f, OplusDarkModeUtil.isNightMode(this.context));
    }

    @Override // com.oplus.systemui.notification.aod.notification.AodViewWrapper
    public void setAodColor(NormalBgAodColor normalBgAodColor) {
        Set<PlatformBlurDrawable> firstStackCache;
        NormalBgAodColor aodColor = getAodColor();
        AodViewWrapper.Companion.setAodColor(((ActivatableNotificationView) getView()).mBackgroundNormal, normalBgAodColor);
        if (normalBgAodColor == null) {
            PlatformBlurDrawable platformBlurDrawable = (PlatformBlurDrawable) getOplusDrawableAndType(getView(), 0).getFirst();
            if (platformBlurDrawable != null) {
                platformBlurDrawable.setAodState(false);
                if (platformBlurDrawable.isAodDirty) {
                    this.aodLog.logClearAodDirty(platformBlurDrawable, "setNull", getView(), 0);
                    platformBlurDrawable.changeBlurColor(platformBlurDrawable.getCurrentBlurMixConfig(), true);
                }
            }
            if (aodColor == null || (firstStackCache = aodColor.getFirstStackCache()) == null) {
                return;
            }
            for (PlatformBlurDrawable platformBlurDrawable2 : firstStackCache) {
                if (platformBlurDrawable2.isAodDirty) {
                    this.aodLog.logClearAodDirty(platformBlurDrawable2, "setNull", getView(), 1);
                    platformBlurDrawable2.changeBlurColor(platformBlurDrawable2.getCurrentBlurMixConfig(), true);
                }
            }
        }
    }

    public final void setStackColor(NotificationOverlayBackgroundView notificationOverlayBackgroundView) {
        NormalBgAodColor aodColor = getAodColor();
        BgAodData curr = aodColor != null ? aodColor.getCurr() : null;
        notificationOverlayBackgroundView.preApplyBlurConfig();
        Drawable blurDrawable = notificationOverlayBackgroundView.getBlurDrawable();
        PlatformBlurDrawable platformBlurDrawable = blurDrawable instanceof PlatformBlurDrawable ? (PlatformBlurDrawable) blurDrawable : null;
        if (platformBlurDrawable != null) {
            this.aodLog.tryLogStart(platformBlurDrawable, "setStackColor", getView(), 2);
            if (curr == null) {
                curr = AodBgConstants.INSTANCE.getEMPTY();
            }
            AodBgViewWrapperKt.writeBgAodData(platformBlurDrawable, curr);
        }
    }

    /* JADX WARN: Code duplicated, block: B:37:0x00c2  */
    @Override // com.oplus.systemui.notification.aod.notification.AodViewWrapper
    public void updateColor(NormalBgAodColor normalBgAodColor, float f) {
        boolean z;
        PlatformBlurDrawable platformBlurDrawable;
        Drawable[] allStackBlurDrawable;
        NotificationChildrenContainerOverlayEx notificationChildrenContainerOverlayEx;
        View overlay;
        OplusExpandableNotificationRowEx oplusExpandableNotificationRowEx;
        NotificationChildrenContainer childrenContainer;
        NotificationChildrenContainerOverlayEx notificationChildrenContainerOverlayEx2;
        PlatformBlurDrawable overlayBlurDrawable;
        normalBgAodColor.setCurr(normalBgAodColor.getColor(f));
        int type = getType();
        Pair oplusDrawableAndType = getOplusDrawableAndType(getView(), type);
        PlatformBlurDrawable platformBlurDrawable2 = (PlatformBlurDrawable) oplusDrawableAndType.getFirst();
        if (platformBlurDrawable2 != null) {
            this.aodLog.tryLogStart(platformBlurDrawable2, "updateColor", getView(), ((Number) oplusDrawableAndType.getSecond()).intValue());
            if (((Number) oplusDrawableAndType.getSecond()).intValue() == 1) {
                normalBgAodColor.getFirstStackCache().add(platformBlurDrawable2);
            } else if (((Number) oplusDrawableAndType.getSecond()).intValue() == 0) {
                platformBlurDrawable2.setAodState(f > 0.0f);
            }
            AodBgViewWrapperKt.writeBgAodData(platformBlurDrawable2, normalBgAodColor.getCurr());
        }
        NotificationChildrenContainer childrenContainer2 = getView().getChildrenContainer();
        if (childrenContainer2 != null && (notificationChildrenContainerOverlayEx = childrenContainer2.mOverlayEx) != null && (overlay = notificationChildrenContainerOverlayEx.getOverlay()) != null && overlay.getVisibility() == 0 && (oplusExpandableNotificationRowEx = getView().mRowEx) != null && ((OplusExpandableNotificationRowExImpl) oplusExpandableNotificationRowEx).isChildrenExpandedAnimating() && (childrenContainer = getView().getChildrenContainer()) != null && (notificationChildrenContainerOverlayEx2 = childrenContainer.mOverlayEx) != null && (overlayBlurDrawable = notificationChildrenContainerOverlayEx2.getOverlayBlurDrawable()) != null) {
            normalBgAodColor.getFirstStackCache().add(overlayBlurDrawable);
            this.aodLog.tryLogStart(overlayBlurDrawable, "updateColor:group overlay", getView(), 1);
            AodBgViewWrapperKt.writeBgAodData(overlayBlurDrawable, normalBgAodColor.getCurr());
        }
        if (type != 1) {
            if (type != 2) {
                z = false;
            } else {
                View viewFindViewById = getView().findViewById(2131364388);
                z = (viewFindViewById == null || viewFindViewById.getAlpha() == 1.0f) ? false : true;
                if (z && (allStackBlurDrawable = ((NotificationBackgroundViewExtImp) ((ActivatableNotificationView) getView()).mBackgroundNormal.getExt()).getKeyguardStackingBackgroundControl().getAllStackBlurDrawable(false)) != null) {
                    for (Drawable drawable : allStackBlurDrawable) {
                        if (drawable instanceof PlatformBlurDrawable) {
                            normalBgAodColor.getFirstStackCache().add(drawable);
                            PlatformBlurDrawable platformBlurDrawable3 = (PlatformBlurDrawable) drawable;
                            this.aodLog.tryLogStart(platformBlurDrawable3, "updateColor:second", getView(), 1);
                            AodBgViewWrapperKt.writeBgAodData(platformBlurDrawable3, normalBgAodColor.getCurr());
                        }
                    }
                }
            }
        } else if (((ActivatableNotificationView) getView()).mBackgroundNormal.getExt().getStackedNotificationMaskColor() != 0) {
            z = true;
        } else {
            z = false;
        }
        if (z && (platformBlurDrawable = (PlatformBlurDrawable) getOplusDrawableAndType(getView(), 0).getFirst()) != null) {
            this.aodLog.tryLogStart(platformBlurDrawable, "updateColor:normal", getView(), 0);
            AodBgViewWrapperKt.writeBgAodData(platformBlurDrawable, normalBgAodColor.getCurr());
            platformBlurDrawable.setAodState(f > 0.0f);
        }
        this.aodBackgroundChanger.changeAodViewColor(getView(), f);
    }

    public final void updateResource() {
        this.layer0 = TuplesKt.to(Integer.valueOf(this.context.getColor(2131099868)), Integer.valueOf(this.context.getColor(2131099867)));
        this.layer1 = TuplesKt.to(Integer.valueOf(this.context.getColor(2131099866)), Integer.valueOf(this.context.getColor(2131099865)));
        this.layer2 = TuplesKt.to(Integer.valueOf(this.context.getColor(2131099870)), Integer.valueOf(this.context.getColor(2131099869)));
    }

    @Override // com.oplus.systemui.notification.aod.notification.AodViewWrapper
    public void setView(ExpandableNotificationRow expandableNotificationRow) {
        this.view = expandableNotificationRow;
    }
}
