package com.oplus.systemui.statusbar.notification.row;

import android.content.Context;
import android.graphics.Path;
import android.graphics.RectF;
import android.view.View;
import androidx.collection.ArrayMap;
import com.android.systemui.plugins.custom.CustomRowEntry;
import com.android.systemui.statusbar.notification.SourceType;
import com.android.systemui.statusbar.notification.row.ExpandableNotificationRow;
import com.android.systemui.statusbar.notification.row.ExpandableOutlineView;
import com.android.systemui.statusbar.notification.row.ExpandableView;
import com.android.systemui.statusbar.notification.row.NotificationBackgroundView;
import com.android.systemui.statusbar.notification.row.NotificationContentView;
import com.android.systemui.statusbar.notification.row.OplusExpandableOutlineViewEx;
import com.android.systemui.statusbar.notification.row.OplusExpandableViewEx;
import com.android.systemui.statusbar.notification.stack.NotificationChildrenContainer;
import com.android.systemui.statusbar.notification.stack.NotificationChildrenContainerOverlayEx;
import com.android.systemui.statusbar.phone.ScrimControllerEx;
import com.oplus.systemui.notification.base.radius.SmoothRoundExKt;
import com.oplus.systemui.notification.material.NotificationSpotLightDelegate;
import com.oplus.systemui.qs.observer.SuperPowerSaveSettingsObserver;
import com.oplus.systemui.statusbar.notification.customcard.OplusCustomRow;
import com.oplus.systemui.statusbar.notification.view.GroupOverlayContainerView;
import com.oplusos.systemui.common.feature.FeatureOption;
import com.oplusos.systemui.common.util.ScrimUtil;
import com.oplusos.systemui.common.util.collection.ArrayMapExtKt;
import java.util.ArrayList;
import java.util.List;
import kotlin.jvm.internal.Intrinsics;

/* JADX INFO: compiled from: go/retraceme fd74bf9514dd0739b92d67a6d48a51eb10aaf8df760d83496b8dcfcf83e5f698 */
/* JADX INFO: loaded from: /home/starlight/Projects/Starlight Ports/.build-tmp/classes4.dex */
public class OplusExpandableOutlineViewExImpl extends OplusExpandableViewExImpl implements OplusExpandableOutlineViewEx {
    private final Context context;
    public boolean isSkipClipForCapture;

    public OplusExpandableOutlineViewExImpl(Context context) {
        super(context);
        this.context = context;
    }

    /* JADX WARN: Multi-variable type inference failed */
    @Override // com.oplus.systemui.statusbar.notification.row.OplusExpandableViewExImpl
    public /* bridge */ /* synthetic */ void bind(View view) {
        super/*com.oplusos.systemui.common.BaseViewBinder*/.bind(view);
    }

    @Override // com.oplus.systemui.statusbar.notification.row.OplusExpandableViewExImpl
    public /* bridge */ /* synthetic */ NotificationBackgroundView getNotificationBackgroundView() {
        return super.getNotificationBackgroundView();
    }

    public void getRoundedRectPath(int i, int i2, int i3, int i4, float[] fArr, Path path, boolean z) {
        if (!z) {
            super.getRoundedRectPath(i, i2, i3, i4, fArr, path, z);
            return;
        }
        SmoothRoundExKt.addSmoothRoundRect$default(path, new RectF(i, i2, i3, i4), fArr[0], fArr[4], z, 24);
    }

    /* JADX WARN: Code duplicated, block: B:46:0x00e9 A[PHI: r0
      0x00e9: PHI (r0v7 int) = (r0v6 int), (r0v6 int), (r0v6 int), (r0v6 int), (r0v6 int), (r0v6 int), (r0v9 int) binds: [B:21:0x009c, B:23:0x00a0, B:25:0x00aa, B:28:0x00b1, B:31:0x00ba, B:41:0x00cf, B:44:0x00e5] A[DONT_GENERATE, DONT_INLINE]] */
    public final NotificationSpotLightDelegate.ClipSpec getSpotLightClipSpec(ExpandableOutlineView expandableOutlineView) {
        int i;
        NotificationChildrenContainer childrenContainer;
        int translation = (int) expandableOutlineView.getTranslation();
        int extraWidthForClipping = (int) (expandableOutlineView.getExtraWidthForClipping() / 2.0f);
        boolean z = expandableOutlineView.mAlwaysRoundBothCorners;
        float maxRadius = z ? expandableOutlineView.getMaxRadius() : expandableOutlineView.getTopCornerRadius();
        int iMax = Math.max(expandableOutlineView.getClipTopAmount(), expandableOutlineView.getTopOverlap());
        ExpandableNotificationRow expandableNotificationRow = expandableOutlineView instanceof ExpandableNotificationRow ? (ExpandableNotificationRow) expandableOutlineView : null;
        if (expandableNotificationRow != null && expandableNotificationRow.mIsSummaryWithChildren && ((OplusExpandableNotificationRowExImpl) expandableNotificationRow.mRowEx).isChildrenExpandedAnimating()) {
            List list = expandableNotificationRow.getChildrenContainer().mAttachedChildren;
            list.getClass();
            if (!list.isEmpty()) {
                iMax += (int) ((ExpandableNotificationRow) ((ArrayList) list).get(0)).getTranslationY();
            }
        }
        OplusExpandableViewEx oplusExpandableViewEx = ((ExpandableView) expandableOutlineView).mExpandableViewEx;
        int clipWidth = ((oplusExpandableViewEx.getClipWidth() / 2) + Math.max(translation, 0)) - extraWidthForClipping;
        int iMin = (Math.min(translation, 0) + (expandableOutlineView.getWidth() + extraWidthForClipping)) - (oplusExpandableViewEx.getClipWidth() / 2);
        int minimumHeightForClipping = expandableOutlineView.getMinimumHeightForClipping();
        int actualHeight = (expandableOutlineView.getActualHeight() - expandableOutlineView.getClipBottomAmount()) - oplusExpandableViewEx.getActualClipHeight();
        int i2 = (int) (iMax + maxRadius);
        int iMax2 = Math.max(minimumHeightForClipping, Math.max(actualHeight, i2));
        if (expandableNotificationRow == null || !expandableNotificationRow.mIsSummaryWithChildren || !((OplusExpandableNotificationRowExImpl) expandableNotificationRow.mRowEx).isChildrenExpandedAnimating() || (childrenContainer = expandableNotificationRow.getChildrenContainer()) == null || childrenContainer.mAttachedChildren.isEmpty()) {
            i = iMax2;
        } else {
            NotificationChildrenContainerOverlayEx notificationChildrenContainerOverlayEx = childrenContainer.mOverlayEx;
            View overlay = notificationChildrenContainerOverlayEx != null ? notificationChildrenContainerOverlayEx.getOverlay() : null;
            GroupOverlayContainerView groupOverlayContainerView = overlay instanceof GroupOverlayContainerView ? (GroupOverlayContainerView) overlay : null;
            if (groupOverlayContainerView != null && (iMax2 = Math.min(iMax2, (groupOverlayContainerView.getHeight() + ((int) groupOverlayContainerView.getTranslationY())) - groupOverlayContainerView.getClipBottomAmount())) < i2) {
                i = i2;
            } else {
                i = iMax2;
            }
        }
        if (i - iMax <= 0) {
            return null;
        }
        return new NotificationSpotLightDelegate.ClipSpec(clipWidth, iMax, iMin, i, maxRadius, z ? expandableOutlineView.getMaxRadius() : expandableOutlineView.getBottomCornerRadius(), true);
    }

    public boolean noChangeClipPath(View view, ExpandableOutlineView expandableOutlineView) {
        OplusExpandableViewEx oplusExpandableViewEx;
        if (!this.isSkipClipForCapture) {
            FeatureOption.INSTANCE.getClass();
            if (!FeatureOption.isExpRegion() && !SuperPowerSaveSettingsObserver.Companion.getInstance().isSuperPowerSaveState && !ScrimUtil.isBPAnimLevel() && !ScrimUtil.isBAnimLevel()) {
                ScrimControllerEx.Companion.getClass();
                if (!ScrimControllerEx.isThirdTheme) {
                    ExpandableOutlineView view2 = getView();
                    boolean zIsLayoutRtl = view2 != null ? view2.isLayoutRtl() : false;
                    ExpandableOutlineView view3 = getView();
                    if (!Intrinsics.areEqual((view3 == null || (oplusExpandableViewEx = ((ExpandableView) view3).mExpandableViewEx) == null) ? null : Float.valueOf(oplusExpandableViewEx.getImmersiveProgress()), 1.0f)) {
                        ExpandableOutlineView view4 = getView();
                        float translation = view4 != null ? view4.getTranslation() : 0.0f;
                        if ((translation >= 0.0f && !zIsLayoutRtl) || ((translation <= 0.0f && zIsLayoutRtl) || (view instanceof NotificationContentView) || (view instanceof NotificationChildrenContainer))) {
                            return true;
                        }
                        boolean z = expandableOutlineView instanceof OplusCustomRow;
                        if (z) {
                            OplusCustomRow.Companion companion = OplusCustomRow.Companion;
                            CustomRowEntry entry = ((OplusCustomRow) expandableOutlineView).getEntry();
                            if (!companion.isOplusMediaCustomRow(entry != null ? entry.getServiceId() : null)) {
                            }
                        }
                        if (!z || (view instanceof NotificationBackgroundView)) {
                        }
                    }
                }
            }
            return true;
        }
        return false;
    }

    public boolean requestBottomRoundness(float f, SourceType sourceType, boolean z) {
        ExpandableOutlineView view = getView();
        if (view != null) {
            ArrayMap bottomRoundnessMap = view.getRoundableState().getBottomRoundnessMap();
            Float f2 = (Float) ArrayMapExtKt.maxOrNull(bottomRoundnessMap);
            float fFloatValue = f2 != null ? f2.floatValue() : 0.0f;
            if (f == 0.0f) {
                bottomRoundnessMap.remove(sourceType);
            } else {
                bottomRoundnessMap.put(sourceType, Float.valueOf(f));
            }
            Float f3 = (Float) ArrayMapExtKt.maxOrNull(bottomRoundnessMap);
            float fFloatValue2 = f3 != null ? f3.floatValue() : 0.0f;
            OplusExpandableOutlineViewEx.Companion companion = OplusExpandableOutlineViewEx.Companion;
            if (!Intrinsics.areEqual(sourceType, companion.getForceUpdateSource())) {
                f = fFloatValue2;
            }
            boolean zAreEqual = Intrinsics.areEqual(sourceType, companion.getForceUpdateSource());
            if (fFloatValue != f || zAreEqual) {
                view.getRoundableState().setBottomRoundness(f, (view.getRoundableState().isBottomAnimating() && (Math.abs(f - fFloatValue) > 0.5f ? 1 : (Math.abs(f - fFloatValue) == 0.5f ? 0 : -1)) > 0) || z);
                return true;
            }
        }
        return false;
    }

    public boolean requestTopRoundness(float f, SourceType sourceType, boolean z) {
        ExpandableOutlineView view = getView();
        if (view != null) {
            ArrayMap topRoundnessMap = view.getRoundableState().getTopRoundnessMap();
            Float f2 = (Float) ArrayMapExtKt.maxOrNull(topRoundnessMap);
            float fFloatValue = f2 != null ? f2.floatValue() : 0.0f;
            if (f == 0.0f) {
                topRoundnessMap.remove(sourceType);
            } else {
                topRoundnessMap.put(sourceType, Float.valueOf(f));
            }
            Float f3 = (Float) ArrayMapExtKt.maxOrNull(topRoundnessMap);
            float fFloatValue2 = f3 != null ? f3.floatValue() : 0.0f;
            OplusExpandableOutlineViewEx.Companion companion = OplusExpandableOutlineViewEx.Companion;
            if (!Intrinsics.areEqual(sourceType, companion.getForceUpdateSource())) {
                f = fFloatValue2;
            }
            boolean zAreEqual = Intrinsics.areEqual(sourceType, companion.getForceUpdateSource());
            if (fFloatValue != f || zAreEqual) {
                view.getRoundableState().setTopRoundness(f, (view.getRoundableState().isTopAnimating() && (Math.abs(f - fFloatValue) > 0.5f ? 1 : (Math.abs(f - fFloatValue) == 0.5f ? 0 : -1)) > 0) || z);
                return true;
            }
        }
        return false;
    }

    public void setSkipClipForCapture(boolean z) {
        this.isSkipClipForCapture = z;
    }

    public boolean shouldClipBackgroundView(View view) {
        return view instanceof NotificationBackgroundView;
    }
}
