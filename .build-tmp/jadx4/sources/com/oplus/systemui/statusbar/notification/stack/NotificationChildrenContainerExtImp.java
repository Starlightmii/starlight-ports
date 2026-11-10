package com.oplus.systemui.statusbar.notification.stack;

import android.R;
import android.content.Context;
import android.content.res.Resources;
import android.graphics.Canvas;
import android.graphics.ColorFilter;
import android.graphics.drawable.ColorDrawable;
import android.graphics.drawable.Drawable;
import android.util.Log;
import android.view.NotificationHeaderView;
import android.view.View;
import android.view.ViewGroup;
import android.view.ViewParent;
import android.widget.ImageView;
import com.android.systemui.DependencyEx;
import com.android.systemui.panelanimation.notification.NotificationRowShowAnimationEx;
import com.android.systemui.statusbar.SysuiStatusBarStateController;
import com.android.systemui.statusbar.notification.collection.OplusNotificationEntryEx;
import com.android.systemui.statusbar.notification.row.ExpandableNotificationRow;
import com.android.systemui.statusbar.notification.row.HybridNotificationView;
import com.android.systemui.statusbar.notification.row.OplusExpandableNotificationRowEx;
import com.android.systemui.statusbar.notification.row.OplusExpandableOutlineViewEx;
import com.android.systemui.statusbar.notification.row.wrapper.NotificationHeaderViewWrapper;
import com.android.systemui.statusbar.notification.row.wrapper.OplusNotificationHeaderViewWrapperEx;
import com.android.systemui.statusbar.notification.stack.NotificationChildrenContainer;
import com.android.systemui.statusbar.notification.stack.NotificationChildrenContainerExt;
import com.android.systemui.statusbar.phone.ScrimControllerEx;
import com.oplus.systemui.blur.GaussBlurUtils;
import com.oplus.systemui.notification.NotificationExtFlag;
import com.oplus.systemui.notification.base.radius.SmoothRoundEx;
import com.oplus.systemui.notification.blur.ViewBlurManager;
import com.oplus.systemui.notification.color.MaterialColorManager;
import com.oplus.systemui.notification.extend.OplusNotificationEx;
import com.oplus.systemui.notification.headsup.windowframe.HeadsUpContainerWindow;
import com.oplus.systemui.notification.row.oplusgroup.OplusNotificationGroupExtImpl;
import com.oplus.systemui.notification.stackednotification.ruler.ShadeNotificationStackedRuler;
import com.oplus.systemui.statusbar.notification.collection.OplusNotificationEntryExImpl;
import com.oplus.systemui.statusbar.notification.row.OplusExpandableNotificationRowExImpl;
import com.oplus.systemui.statusbar.notification.row.wrapper.OplusNotificationHeaderViewWrapperExImp;
import com.oplusos.systemui.common.blurability.BlurConfig;
import com.oplusos.systemui.common.blurability.ViewBlurProxy;
import com.oplusos.systemui.common.feature.FeatureOption;
import com.oplusos.systemui.common.util.LogUtil;
import com.oplusos.systemui.common.util.ScrimUtil;
import dagger.Lazy;
import java.lang.invoke.VarHandle;
import java.util.List;
import kotlin.Deprecated;
import kotlin.jvm.functions.Function0;

/* JADX INFO: compiled from: go/retraceme fd74bf9514dd0739b92d67a6d48a51eb10aaf8df760d83496b8dcfcf83e5f698 */
/* JADX INFO: loaded from: /home/starlight/Projects/Starlight Ports/.build-tmp/classes4.dex */
public final class NotificationChildrenContainerExtImp extends NotificationChildrenContainerExt implements OplusNotificationEx {
    public static final Companion Companion = new Companion();
    private static final String TAG = "NotificationChildrenContainerExtImp";
    private final Context context;
    private final Lazy headsUpContainerWindow;
    public boolean isShowLabel;
    private ImageView labelIcon;
    private String labelKey;
    private final Lazy materialColorManager;
    private final Lazy notificationRowShowAnimationEx;
    private final NotificationChildrenContainerExtImp$onAttachStatusChangeListener$1 onAttachStatusChangeListener;
    private final OplusNotificationGroupExtImpl oplusNotificationGroupExtImpl;
    private final Lazy shadeNotificationStackedRuler;
    public int stackedNotificationMaskColor;
    private final Lazy statusBarStateController;
    public final ViewBlurManager viewBlurManager = (ViewBlurManager) DependencyEx.sDependency.getDependency(ViewBlurManager.class);

    /* JADX INFO: compiled from: go/retraceme fd74bf9514dd0739b92d67a6d48a51eb10aaf8df760d83496b8dcfcf83e5f698 */
    public final class Companion {
    }

    /* JADX INFO: compiled from: go/retraceme fd74bf9514dd0739b92d67a6d48a51eb10aaf8df760d83496b8dcfcf83e5f698 */
    public final class NotificationHeaderMaskColorDrawable extends Drawable implements Drawable.Callback {
        private final Drawable drawable;
        private final Function0 maskColor;

        public NotificationHeaderMaskColorDrawable(Drawable drawable, Function0 function0) {
            this.drawable = drawable;
            this.maskColor = function0;
        }

        @Override // android.graphics.drawable.Drawable
        public void draw(Canvas canvas) {
            this.drawable.setBounds(getBounds());
            this.drawable.draw(canvas);
            int iIntValue = ((Number) this.maskColor.invoke()).intValue();
            if (iIntValue != 0) {
                canvas.drawColor(iIntValue);
            }
        }

        @Override // android.graphics.drawable.Drawable
        @Deprecated
        public int getOpacity() {
            return this.drawable.getOpacity();
        }

        @Override // android.graphics.drawable.Drawable.Callback
        public void invalidateDrawable(Drawable drawable) {
            invalidateSelf();
        }

        @Override // android.graphics.drawable.Drawable.Callback
        public void scheduleDrawable(Drawable drawable, Runnable runnable, long j) {
            scheduleSelf(runnable, j);
        }

        @Override // android.graphics.drawable.Drawable
        public void setAlpha(int i) {
            this.drawable.setAlpha(i);
        }

        @Override // android.graphics.drawable.Drawable
        public void setColorFilter(ColorFilter colorFilter) {
            this.drawable.setColorFilter(colorFilter);
        }

        @Override // android.graphics.drawable.Drawable.Callback
        public void unscheduleDrawable(Drawable drawable, Runnable runnable) {
            unscheduleSelf(runnable);
        }
    }

    public NotificationChildrenContainerExtImp(Context context, Lazy lazy, OplusNotificationGroupExtImpl oplusNotificationGroupExtImpl, Lazy lazy2, Lazy lazy3, Lazy lazy4, Lazy lazy5) {
        this.context = context;
        this.headsUpContainerWindow = lazy;
        this.oplusNotificationGroupExtImpl = oplusNotificationGroupExtImpl;
        this.materialColorManager = lazy2;
        this.statusBarStateController = lazy3;
        this.shadeNotificationStackedRuler = lazy4;
        this.notificationRowShowAnimationEx = lazy5;
        NotificationChildrenContainerExtImp$onAttachStatusChangeListener$1 notificationChildrenContainerExtImp$onAttachStatusChangeListener$1 = new NotificationChildrenContainerExtImp$onAttachStatusChangeListener$1();
        notificationChildrenContainerExtImp$onAttachStatusChangeListener$1.this$0 = this;
        VarHandle.storeStoreFence();
        this.onAttachStatusChangeListener = notificationChildrenContainerExtImp$onAttachStatusChangeListener$1;
    }

    private final ColorDrawable getColorDrawable() {
        int currentMaterialColor = ((MaterialColorManager) this.materialColorManager.get()).getCurrentMaterialColor();
        return currentMaterialColor != 0 ? new ColorDrawable(currentMaterialColor) : new ColorDrawable(this.context.getColor(2131102886));
    }

    public void applyCollapsedGroupHeaderExpandButton(View view) {
        View viewFindViewById;
        View viewFindViewById2;
        if (view == null || (viewFindViewById = view.findViewById(R.id.flagNoExtractUi)) == null) {
            return;
        }
        viewFindViewById.setVisibility(0);
        FeatureOption.INSTANCE.getClass();
        if (FeatureOption.isExpRegion() || (viewFindViewById2 = viewFindViewById.findViewById(R.id.flagReportViewIds)) == null) {
            return;
        }
        viewFindViewById2.setVisibility(8);
    }

    public void childResetRoundness(List<ExpandableNotificationRow> list) {
        int size = list.size() - 1;
        if (size < 0) {
            return;
        }
        while (true) {
            int i = size - 1;
            ExpandableNotificationRow expandableNotificationRow = list.get(size);
            if (expandableNotificationRow.getVisibility() != 8) {
                expandableNotificationRow.requestRoundness(1.0f, 1.0f, OplusExpandableOutlineViewEx.Companion.getForceUpdateSource(), false);
            }
            if (i < 0) {
                return;
            } else {
                size = i;
            }
        }
    }

    public int fixChildClipBottomAmount(int i, ExpandableNotificationRow expandableNotificationRow, ExpandableNotificationRow expandableNotificationRow2) {
        OplusExpandableNotificationRowEx oplusExpandableNotificationRowEx = expandableNotificationRow2.mRowEx;
        return (!(oplusExpandableNotificationRowEx != null ? ((OplusExpandableNotificationRowExImpl) oplusExpandableNotificationRowEx).isChildrenExpandedAnimating() : false) || i == 0 || expandableNotificationRow.getScaleY() == 0.0f) ? i : (int) (i / expandableNotificationRow.getScaleY());
    }

    public void flushIcon(NotificationChildrenContainer notificationChildrenContainer) {
        this.oplusNotificationGroupExtImpl.flushIcon(notificationChildrenContainer);
    }

    public int getCollapsedBottomPadding(Resources resources, int i, boolean z) {
        return this.oplusNotificationGroupExtImpl.getCollapsedBottomPadding(resources, i, z);
    }

    public final int getExpandedBottomPadding(int i, boolean z) {
        this.oplusNotificationGroupExtImpl.getClass();
        if (z) {
            return 0;
        }
        return i;
    }

    public final int getFirstYPosition(boolean z, float f, boolean z2, boolean z3, int i) {
        return this.oplusNotificationGroupExtImpl.getFirstYPosition(z, f, z2, z3, i);
    }

    public int getGroupTopMargin(boolean z, boolean z2, float f, int i, boolean z3, boolean z4, NotificationChildrenContainer notificationChildrenContainer) {
        return this.oplusNotificationGroupExtImpl.getGroupTopMargin(z, z2, f, i, z3, z4, notificationChildrenContainer);
    }

    public int getGroupTopPaddingWhenCollapsed(int i, boolean z, NotificationChildrenContainer notificationChildrenContainer) {
        return this.oplusNotificationGroupExtImpl.getGroupTopPaddingWhenCollapsed(i, z, notificationChildrenContainer);
    }

    public Drawable getHeaderBgDrawable(View view, boolean z, ExpandableNotificationRow expandableNotificationRow) {
        ViewBlurProxy.BlurType blurType;
        Drawable colorDrawable = null;
        if (!GaussBlurUtils.isGaussBlurDisabled() && !ScrimUtil.isBAnimLevel()) {
            view.addOnAttachStateChangeListener(this.onAttachStatusChangeListener);
            boolean z2 = expandableNotificationRow.mPinnedStatus.isPinned() || expandableNotificationRow.mHeadsupDisappearRunning;
            ViewBlurManager viewBlurManager = this.viewBlurManager;
            ViewBlurProxy viewBlurProxyRequireBlurProxyForView = viewBlurManager.requireBlurProxyForView(view, (6 & 2) == 0, SmoothRoundEx.INSTANCE.getDefaultWeight());
            if (z2) {
                viewBlurManager.blurForHeadsUp(view, false);
            } else {
                ScrimControllerEx.Companion.getClass();
                if (ScrimControllerEx.isThirdTheme) {
                    blurType = ViewBlurProxy.BlurType.BlurTypeNon.INSTANCE;
                } else if (((HeadsUpContainerWindow) this.headsUpContainerWindow.get()).isHeadsUpView(view)) {
                    blurType = ViewBlurProxy.BlurType.BlurTypeMotion.INSTANCE;
                } else {
                    blurType = ScrimUtil.isBPAnimLevel() ? ViewBlurProxy.BlurType.BlurTypeBlendWallpaper.INSTANCE : ViewBlurProxy.BlurType.BlurTypePlatformStatic.INSTANCE;
                }
                viewBlurProxyRequireBlurProxyForView.setBlurType(blurType);
            }
            BlurConfig blurConfig = viewBlurProxyRequireBlurProxyForView.getBlurConfig();
            if (expandableNotificationRow.getTopCornerRadius() != blurConfig.getLeftTopCornerRadius() || 0.0f != blurConfig.getLeftBottomCornerRadius()) {
                blurConfig.setLeftTopCornerRadius(expandableNotificationRow.getTopCornerRadius());
                blurConfig.setRightTopCornerRadius(expandableNotificationRow.getTopCornerRadius());
                blurConfig.setLeftBottomCornerRadius(0.0f);
                blurConfig.setRightBottomCornerRadius(0.0f);
                viewBlurProxyRequireBlurProxyForView.applyBlurConfig();
            }
            colorDrawable = viewBlurProxyRequireBlurProxyForView.getBlurDrawable(null);
            if (LogUtil.getNormalDebug() && LogUtil.isTagValid(LogUtil.TAG_NOTIFICATION)) {
                Log.d("SystemUi--Notification", "NotificationChildrenContainerExtImp-->".concat("[getHeaderBlurDrawable] " + colorDrawable + ", expanded=" + z));
            }
        }
        if (colorDrawable == null) {
            colorDrawable = getColorDrawable();
        }
        NotificationChildrenContainerExtImp$$ExternalSyntheticLambda0 notificationChildrenContainerExtImp$$ExternalSyntheticLambda0 = new NotificationChildrenContainerExtImp$$ExternalSyntheticLambda0();
        notificationChildrenContainerExtImp$$ExternalSyntheticLambda0.f$0 = this;
        VarHandle.storeStoreFence();
        return new NotificationHeaderMaskColorDrawable(colorDrawable, notificationChildrenContainerExtImp$$ExternalSyntheticLambda0);
    }

    public int getMinExpandHeight(HybridNotificationView hybridNotificationView, int i) {
        return this.oplusNotificationGroupExtImpl.getMinExpandHeight(hybridNotificationView, i);
    }

    public ViewGroup getOplusExpandGroupHeaderView() {
        return this.oplusNotificationGroupExtImpl.getOplusExpandGroupHeaderView();
    }

    public final int getScaleHeaderHeight(int i) {
        return Math.max(i, this.oplusNotificationGroupExtImpl.scaleHeaderHeight);
    }

    public int getStackedNotificationMaskColor() {
        return this.stackedNotificationMaskColor;
    }

    public void initDimens(Resources resources) {
        this.oplusNotificationGroupExtImpl.initDimens(resources);
    }

    public void initLabel(NotificationHeaderViewWrapper notificationHeaderViewWrapper) {
        if (notificationHeaderViewWrapper != null) {
            OplusNotificationHeaderViewWrapperEx oplusNotificationHeaderViewWrapperEx = notificationHeaderViewWrapper.mHeaderViewWrapperEx;
            if (oplusNotificationHeaderViewWrapperEx instanceof OplusNotificationHeaderViewWrapperExImp) {
                this.labelIcon = ((OplusNotificationHeaderViewWrapperExImp) oplusNotificationHeaderViewWrapperEx).getMLabelIcon();
            }
        }
    }

    public final boolean isStackState() {
        return ((SysuiStatusBarStateController) this.statusBarStateController.get()).getState() != 0 || ((ShadeNotificationStackedRuler) this.shadeNotificationStackedRuler.get()).getCurrentIsStackedNotification();
    }

    public void layoutHeader(NotificationChildrenContainer notificationChildrenContainer) {
        this.oplusNotificationGroupExtImpl.layoutHeader(notificationChildrenContainer);
    }

    public void measureOplusExpandHeader(NotificationChildrenContainer notificationChildrenContainer, int i, int i2) {
        this.oplusNotificationGroupExtImpl.measureOplusExpandHeader(notificationChildrenContainer, i, i2);
    }

    public boolean needShowDivider(Context context) {
        ScrimControllerEx.Companion.getClass();
        return !(!ScrimControllerEx.isThirdTheme && (ScrimUtil.isLowGaussianLevel(context) || GaussBlurUtils.isGaussBlurDisabled()));
    }

    public boolean needSkipChildrenClipping(ExpandableNotificationRow expandableNotificationRow) {
        if (NotificationExtFlag.OPLUS_HEADSUP) {
            return expandableNotificationRow.isHeadsUpState();
        }
        return false;
    }

    public void onAddNotification(ExpandableNotificationRow expandableNotificationRow) {
        if (expandableNotificationRow != null) {
            ((NotificationRowShowAnimationEx) this.notificationRowShowAnimationEx.get()).clearShowAnimationState(expandableNotificationRow);
        }
        ViewParent parent = expandableNotificationRow != null ? expandableNotificationRow.getParent() : null;
        if (parent == null || !(parent instanceof ViewGroup)) {
            return;
        }
        LogUtil.w$default(8, LogUtil.TAG_NOTIFICATION, TAG, "viewGroup is: " + parent);
        ViewGroup viewGroup = (ViewGroup) parent;
        viewGroup.removeView(expandableNotificationRow);
        viewGroup.removeTransientView(expandableNotificationRow);
    }

    public void onChildrenViewAdded(NotificationChildrenContainer notificationChildrenContainer, View view) {
        this.oplusNotificationGroupExtImpl.onChildrenViewAdded(notificationChildrenContainer, view);
    }

    public void onRemoveTransientView(View view) {
        this.oplusNotificationGroupExtImpl.onRemoveTransientView(view);
    }

    public void reInflate(NotificationChildrenContainer notificationChildrenContainer, View.OnClickListener onClickListener) {
        this.oplusNotificationGroupExtImpl.reInflate(notificationChildrenContainer, onClickListener);
    }

    public void recreateOplusNotificationHeader(NotificationChildrenContainer notificationChildrenContainer, View.OnClickListener onClickListener, boolean z) {
        this.oplusNotificationGroupExtImpl.recreateOplusNotificationHeader(notificationChildrenContainer, onClickListener, z);
    }

    public void resetChildDefaultRoundness(boolean z, List<ExpandableNotificationRow> list) {
        if (z) {
            childResetRoundness(list);
        }
    }

    public void setExpandButtonDefaultState(boolean z) {
        ImageView imageView;
        if (!z || (imageView = this.labelIcon) == null) {
            return;
        }
        imageView.setVisibility(8);
    }

    public void setExpandButtonLabelState(ExpandableNotificationRow expandableNotificationRow, boolean z) {
        ImageView imageView;
        if (z || !this.isShowLabel || (imageView = this.labelIcon) == null) {
            return;
        }
        imageView.setVisibility(0);
        OplusNotificationEntryEx ex = expandableNotificationRow.getEntry().getEx();
        if (ex instanceof OplusNotificationEntryExImpl) {
            ImageView imageView2 = this.labelIcon;
            imageView2.getClass();
            imageView2.setImageDrawable(((OplusNotificationEntryExImpl) ex).getOplusLabel());
        }
    }

    public void setExpandButtonNumber(int i) {
        this.oplusNotificationGroupExtImpl.setExpandButtonNumber(i);
    }

    public void setLabelKey(String str) {
        if (str == null || str.equals(this.labelKey)) {
            return;
        }
        this.labelKey = str;
        this.isShowLabel = true;
    }

    public void setStackedNotificationMaskColor(int i) {
        if (this.stackedNotificationMaskColor == i) {
            return;
        }
        this.stackedNotificationMaskColor = i;
        NotificationHeaderView groupHeader = getNotificationChildrenContainer().getGroupHeader();
        if (groupHeader != null) {
            groupHeader.invalidate();
        }
    }

    public final void updateChildrenContainerHeaderText() {
        View viewFindViewById;
        NotificationHeaderView notificationHeaderView = getNotificationChildrenContainer().mGroupHeader;
        if (notificationHeaderView == null || (viewFindViewById = notificationHeaderView.findViewById(R.id.unchecked)) == null) {
            return;
        }
        viewFindViewById.setVisibility(8);
    }

    public final void updateGroupHeaderColor() {
        this.oplusNotificationGroupExtImpl.updateGroupHeaderColor();
    }

    public int updateIntrinsicHeight(NotificationChildrenContainer notificationChildrenContainer, int i, int i2, int i3, int i4) {
        int collapsedHeight = (notificationChildrenContainer.mChildrenExpanded || !((OplusExpandableNotificationRowExImpl) notificationChildrenContainer.getContainingNotification().mRowEx).isChildrenExpandedAnimating()) ? i3 : notificationChildrenContainer.getCollapsedHeight();
        if (i4 != 1 || !notificationChildrenContainer.mChildrenExpanded) {
            return collapsedHeight;
        }
        int i5 = i3 - (i + i2);
        NotificationHeaderView groupHeader = notificationChildrenContainer.getGroupHeader();
        if (groupHeader != null) {
            groupHeader.setOnClickListener((View.OnClickListener) null);
        }
        return i5;
    }

    public final void updatePillBgAndNumberColor() {
        this.oplusNotificationGroupExtImpl.updatePillBgAndNumberColor();
    }

    public void updateSingleLinePadding(ExpandableNotificationRow expandableNotificationRow, int i) {
        this.oplusNotificationGroupExtImpl.updateSingleLinePadding(expandableNotificationRow, i);
    }

    public final void updateSummaryGroupBadgeShadow(float f) {
        this.oplusNotificationGroupExtImpl.updateSummaryGroupBadgeShadow(f);
    }

    public final void updateSummaryHeaderBlurRadius(float f) {
        this.oplusNotificationGroupExtImpl.updateSummaryHeaderBlurRadius(f);
    }
}
