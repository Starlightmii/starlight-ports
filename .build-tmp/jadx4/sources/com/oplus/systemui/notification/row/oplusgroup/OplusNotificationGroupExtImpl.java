package com.oplus.systemui.notification.row.oplusgroup;

import android.R;
import android.content.Context;
import android.content.res.ColorStateList;
import android.content.res.Resources;
import android.graphics.RenderEffect;
import android.graphics.Shader;
import android.graphics.drawable.Drawable;
import android.os.Trace;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.NotificationHeaderView;
import android.view.NotificationTopLineView;
import android.view.View;
import android.view.ViewGroup;
import android.view.ViewParent;
import android.widget.FrameLayout;
import android.widget.ImageView;
import android.widget.RelativeLayout;
import android.widget.TextView;
import com.android.internal.widget.CachingIconView;
import com.android.systemui.media.controls.domain.pipeline.MediaTimeoutListener$PlaybackStateListener$;
import com.android.systemui.statusbar.notification.NotificationUtils;
import com.android.systemui.statusbar.notification.collection.NotificationEntry;
import com.android.systemui.statusbar.notification.row.ExpandableNotificationRow;
import com.android.systemui.statusbar.notification.row.HybridNotificationView;
import com.android.systemui.statusbar.notification.row.wrapper.NotificationHeaderViewWrapper;
import com.android.systemui.statusbar.notification.row.wrapper.NotificationViewWrapper;
import com.android.systemui.statusbar.notification.stack.NotificationChildrenContainer;
import com.android.systemui.statusbar.notification.stack.NotificationChildrenContainerOverlayEx;
import com.oplus.systemui.notification.aod.notification.NotificationAodTransition;
import com.oplus.systemui.notification.row.group.OplusNotificationGroupExt;
import com.oplus.systemui.notification.utlils.NotificationColorUtil;
import com.oplusos.systemui.common.feature.FlavorTwoFeatureOption;
import com.oplusos.systemui.common.util.LogUtil;
import com.oplusos.systemui.common.util.ScrimUtil;
import dagger.Lazy;
import java.lang.invoke.VarHandle;
import kotlin.Unit;
import kotlin.jvm.internal.Intrinsics;
import kotlin.ranges.RangesKt___RangesKt;

/* JADX INFO: compiled from: go/retraceme fd74bf9514dd0739b92d67a6d48a51eb10aaf8df760d83496b8dcfcf83e5f698 */
/* JADX INFO: loaded from: /home/starlight/Projects/Starlight Ports/.build-tmp/classes4.dex */
public final class OplusNotificationGroupExtImpl implements OplusNotificationGroupExt {
    public static final Companion Companion = new Companion();
    private static final String TAG = "OplusNotificationGroupExtImpl";
    private final Lazy aodTransition;
    public int collapsedBottomPadding;
    public int collapsedBottomPaddingSingle;
    public int contentPaddingEnd;
    public int contentPaddingStart;
    private final Context context;
    private final OplusNotificationGroupExtImpl$expandHeaderButtonDelegate$1 expandHeaderButtonDelegate = new OplusNotificationGroupExtImpl$expandHeaderButtonDelegate$1();
    private FrameLayout expandedPill;
    public int groupCollapsedTopPadding;
    public int groupCollapsedTopPaddingSingle;
    public int groupCollapsedTopPaddingSingleTransition;
    private final GroupIconManager groupIconManager;
    private NotificationHeaderViewWrapper headerWrapper;
    private CachingIconView icon;
    public final boolean isMiddleOrHighAnimLevel;
    private TextView numberView;
    private ViewGroup oplusCollapsedHeaderBage;
    private ViewGroup oplusExpandHeader;
    private OplusGroupNotificationRowIconView oplusIcon;
    public int scaleHeaderHeight;

    /* JADX INFO: compiled from: go/retraceme fd74bf9514dd0739b92d67a6d48a51eb10aaf8df760d83496b8dcfcf83e5f698 */
    public final class Companion {
    }

    /* JADX INFO: renamed from: $r8$lambda$6S4n19S-AfjPyv31aK67ZmrtTAA, reason: not valid java name */
    public static Unit m97$r8$lambda$6S4n19SAfjPyv31aK67ZmrtTAA(OplusNotificationGroupExtImpl oplusNotificationGroupExtImpl, boolean z) {
        int i;
        FlavorTwoFeatureOption.INSTANCE.getClass();
        boolean zIsFlavorTwoDeviceExpLessThanC = FlavorTwoFeatureOption.isFlavorTwoDeviceExpLessThanC();
        if (zIsFlavorTwoDeviceExpLessThanC) {
            i = 2131236002;
        } else {
            i = FlavorTwoFeatureOption.isFlavorTwoDeviceExp() ? 2131236003 : 2131236001;
        }
        OplusGroupNotificationRowIconView oplusGroupNotificationRowIconView = oplusNotificationGroupExtImpl.oplusIcon;
        if (oplusGroupNotificationRowIconView != null) {
            oplusGroupNotificationRowIconView.setBackground(oplusNotificationGroupExtImpl.context.getDrawable(i));
        }
        OplusGroupNotificationRowIconView oplusGroupNotificationRowIconView2 = oplusNotificationGroupExtImpl.oplusIcon;
        if (oplusGroupNotificationRowIconView2 != null) {
            oplusGroupNotificationRowIconView2.setClipToOutline((zIsFlavorTwoDeviceExpLessThanC && z) ? false : true);
        }
        OplusGroupNotificationRowIconView oplusGroupNotificationRowIconView3 = oplusNotificationGroupExtImpl.oplusIcon;
        if (oplusGroupNotificationRowIconView3 != null) {
            oplusGroupNotificationRowIconView3.invalidateOutline();
        }
        return Unit.INSTANCE;
    }

    public OplusNotificationGroupExtImpl(GroupIconManager groupIconManager, Context context, Lazy lazy) {
        this.groupIconManager = groupIconManager;
        this.context = context;
        this.aodTransition = lazy;
        this.isMiddleOrHighAnimLevel = ScrimUtil.isMiddleOrHighAnimLevel(context);
    }

    public static boolean isSingleLineEmpty(NotificationChildrenContainer notificationChildrenContainer) {
        for (ExpandableNotificationRow expandableNotificationRow : notificationChildrenContainer.getAttachedChildren()) {
            if (expandableNotificationRow.getSingleLineView() != null && expandableNotificationRow.getSingleLineView().getMeasuredHeight() > 0) {
                return false;
            }
        }
        return true;
    }

    /* JADX WARN: Code duplicated, block: B:19:0x004e  */
    /* JADX WARN: Code duplicated, block: B:21:0x0052  */
    /* JADX WARN: Code duplicated, block: B:22:0x0057  */
    /* JADX WARN: Code duplicated, block: B:25:0x005c  */
    /* JADX WARN: Code duplicated, block: B:27:0x0061  */
    public final void attachCollapsedGroupBadge(NotificationHeaderView notificationHeaderView, CachingIconView cachingIconView, NotificationChildrenContainer notificationChildrenContainer) {
        ViewGroup viewGroup;
        ViewParent parent;
        ViewGroup viewGroup2;
        Drawable drawable = cachingIconView.getDrawable();
        if (drawable == null) {
            return;
        }
        this.groupIconManager.updateGroupBadgePillShadow(this.expandedPill, false, false, 1.0f);
        ViewGroup viewGroup3 = this.oplusCollapsedHeaderBage;
        if (viewGroup3 == null) {
            viewGroup = this.oplusCollapsedHeaderBage;
            if (viewGroup != null) {
                parent = viewGroup.getParent();
            } else {
                parent = null;
            }
            viewGroup2 = parent instanceof ViewGroup ? (ViewGroup) parent : null;
            if (viewGroup2 != null) {
                viewGroup2.removeView(this.oplusCollapsedHeaderBage);
            }
            Trace.traceBegin(8L, "inflateCollapseGroupHeader");
            ViewGroup viewGroup4 = (ViewGroup) LayoutInflater.from(this.context).inflate(2131559311, (ViewGroup) notificationHeaderView, false);
            Trace.traceEnd(8L);
            RelativeLayout.LayoutParams layoutParams = new RelativeLayout.LayoutParams(-1, -1);
            layoutParams.addRule(15, -1);
            notificationHeaderView.addView(viewGroup4, layoutParams);
            this.oplusCollapsedHeaderBage = viewGroup4;
            this.numberView = (TextView) viewGroup4.findViewById(2131364314);
            this.expandedPill = (FrameLayout) viewGroup4.findViewById(2131364315);
            this.oplusIcon = (OplusGroupNotificationRowIconView) viewGroup4.findViewById(2131363381);
        } else {
            if (viewGroup3.getParent() != notificationHeaderView) {
                viewGroup3 = null;
            }
            if (viewGroup3 == null) {
                viewGroup = this.oplusCollapsedHeaderBage;
                if (viewGroup != null) {
                    parent = viewGroup.getParent();
                } else {
                    parent = null;
                }
                if (parent instanceof ViewGroup) {
                }
                if (viewGroup2 != null) {
                    viewGroup2.removeView(this.oplusCollapsedHeaderBage);
                }
                Trace.traceBegin(8L, "inflateCollapseGroupHeader");
                ViewGroup viewGroup5 = (ViewGroup) LayoutInflater.from(this.context).inflate(2131559311, (ViewGroup) notificationHeaderView, false);
                Trace.traceEnd(8L);
                RelativeLayout.LayoutParams layoutParams2 = new RelativeLayout.LayoutParams(-1, -1);
                layoutParams2.addRule(15, -1);
                notificationHeaderView.addView(viewGroup5, layoutParams2);
                this.oplusCollapsedHeaderBage = viewGroup5;
                this.numberView = (TextView) viewGroup5.findViewById(2131364314);
                this.expandedPill = (FrameLayout) viewGroup5.findViewById(2131364315);
                this.oplusIcon = (OplusGroupNotificationRowIconView) viewGroup5.findViewById(2131363381);
            } else if (this.numberView == null || this.expandedPill == null || this.oplusIcon == null) {
                this.numberView = (TextView) viewGroup3.findViewById(2131364314);
                this.expandedPill = (FrameLayout) viewGroup3.findViewById(2131364315);
                this.oplusIcon = (OplusGroupNotificationRowIconView) viewGroup3.findViewById(2131363381);
            }
        }
        OplusGroupNotificationRowIconView oplusGroupNotificationRowIconView = this.oplusIcon;
        if (oplusGroupNotificationRowIconView != null) {
            oplusGroupNotificationRowIconView.setTag(2131363396, cachingIconView.getTag(2131363396));
        }
        OplusGroupNotificationRowIconView oplusGroupNotificationRowIconView2 = this.oplusIcon;
        if (oplusGroupNotificationRowIconView2 != null) {
            oplusGroupNotificationRowIconView2.setImageDrawable(drawable);
        }
        initIcon(notificationChildrenContainer);
        cachingIconView.setVisibility(8);
        setExpandButtonNumber(notificationChildrenContainer.getUntruncatedChildCount());
    }

    @Override // com.oplus.systemui.notification.row.group.OplusNotificationGroupExt
    public void flushIcon(NotificationChildrenContainer notificationChildrenContainer) {
        NotificationHeaderView groupHeader;
        ExpandableNotificationRow containingNotification = notificationChildrenContainer.getContainingNotification();
        if ((containingNotification == null || !containingNotification.isChildInGroup()) && (groupHeader = notificationChildrenContainer.getGroupHeader()) != null) {
            NotificationHeaderViewWrapper notificationHeaderViewWrapper = this.headerWrapper;
            CachingIconView cachingIconView = notificationHeaderViewWrapper != null ? notificationHeaderViewWrapper.mIcon : null;
            this.icon = cachingIconView;
            if (cachingIconView != null) {
                attachCollapsedGroupBadge(groupHeader, cachingIconView, notificationChildrenContainer);
            }
        }
    }

    @Override // com.oplus.systemui.notification.row.group.OplusNotificationGroupExt
    public int getCollapsedBottomPadding(Resources resources, int i, boolean z) {
        return z ? this.collapsedBottomPaddingSingle : this.collapsedBottomPadding;
    }

    @Override // com.oplus.systemui.notification.row.group.OplusNotificationGroupExt
    public final int getExpandedBottomPadding(int i, boolean z) {
        throw null;
    }

    @Override // com.oplus.systemui.notification.row.group.OplusNotificationGroupExt
    public final int getFirstYPosition(boolean z, float f, boolean z2, boolean z3, int i) {
        if (z2 || !z3) {
            return (!z || z3) ? i : (int) (i * f);
        }
        return 0;
    }

    @Override // com.oplus.systemui.notification.row.group.OplusNotificationGroupExt
    public int getGroupTopMargin(boolean z, boolean z2, float f, int i, boolean z3, boolean z4, NotificationChildrenContainer notificationChildrenContainer) {
        int i2;
        if (z3 || isSingleLineEmpty(notificationChildrenContainer)) {
            i2 = z4 ? this.groupCollapsedTopPaddingSingleTransition : this.groupCollapsedTopPaddingSingle;
        } else {
            i2 = this.groupCollapsedTopPadding;
        }
        if (z) {
            return (int) NotificationUtils.interpolate(i2, i, f);
        }
        return z2 ? i : i2;
    }

    @Override // com.oplus.systemui.notification.row.group.OplusNotificationGroupExt
    public int getGroupTopPaddingWhenCollapsed(int i, boolean z, NotificationChildrenContainer notificationChildrenContainer) {
        return (z || isSingleLineEmpty(notificationChildrenContainer)) ? this.groupCollapsedTopPaddingSingleTransition : this.groupCollapsedTopPadding;
    }

    @Override // com.oplus.systemui.notification.row.group.OplusNotificationGroupExt
    public int getMinExpandHeight(HybridNotificationView hybridNotificationView, int i) {
        if (hybridNotificationView != null) {
            int height = hybridNotificationView.getHeight();
            if (height == 0) {
                height = Math.max(height, hybridNotificationView.getMeasuredHeight());
            }
            if (height > 0) {
                return height;
            }
        }
        return i;
    }

    @Override // com.oplus.systemui.notification.row.group.OplusNotificationGroupExt
    public ViewGroup getOplusExpandGroupHeaderView() {
        return this.oplusExpandHeader;
    }

    @Override // com.oplus.systemui.notification.row.group.OplusNotificationGroupExt
    public final int getScaleHeaderHeight(int i) {
        throw null;
    }

    @Override // com.oplus.systemui.notification.row.group.OplusNotificationGroupExt
    public void initDimens(Resources resources) {
        this.groupCollapsedTopPadding = resources.getDimensionPixelOffset(2131170658);
        this.groupCollapsedTopPaddingSingle = resources.getDimensionPixelOffset(2131170659);
        this.groupCollapsedTopPaddingSingleTransition = resources.getDimensionPixelOffset(2131170660);
        this.contentPaddingStart = resources.getDimensionPixelOffset(2131171065);
        this.contentPaddingEnd = resources.getDimensionPixelOffset(2131171064);
        resources.getDimensionPixelOffset(2131170635);
        this.collapsedBottomPadding = resources.getDimensionPixelOffset(2131170605);
        this.collapsedBottomPaddingSingle = resources.getDimensionPixelOffset(2131170606);
        this.scaleHeaderHeight = NotificationUtils.getFontScaledHeight(2131170609, this.context) + NotificationUtils.getFontScaledHeight(2131170608, this.context);
    }

    public final void initIcon(NotificationChildrenContainer notificationChildrenContainer) {
        NotificationEntry entry;
        CachingIconView cachingIconView;
        NotificationHeaderViewWrapper notificationHeaderWrapper;
        ExpandableNotificationRow expandableNotificationRow = (notificationChildrenContainer == null || (notificationHeaderWrapper = notificationChildrenContainer.getNotificationHeaderWrapper()) == null) ? null : ((NotificationViewWrapper) notificationHeaderWrapper).mRow;
        if (expandableNotificationRow == null || !expandableNotificationRow.isChildInGroup()) {
            CachingIconView cachingIconView2 = this.icon;
            if (cachingIconView2 != null) {
                this.groupIconManager.setOriginalIconColor(Integer.valueOf(cachingIconView2.getOriginalIconColor()));
            }
            if (expandableNotificationRow == null || (entry = expandableNotificationRow.getEntry()) == null || (cachingIconView = this.oplusIcon) == null) {
                return;
            }
            GroupIconManager groupIconManager = this.groupIconManager;
            TextView textView = this.numberView;
            FrameLayout frameLayout = this.expandedPill;
            OplusNotificationGroupExtImpl$$ExternalSyntheticLambda0 oplusNotificationGroupExtImpl$$ExternalSyntheticLambda0 = new OplusNotificationGroupExtImpl$$ExternalSyntheticLambda0();
            oplusNotificationGroupExtImpl$$ExternalSyntheticLambda0.f$0 = this;
            VarHandle.storeStoreFence();
            groupIconManager.initEntryIconDrawable(entry, cachingIconView, textView, frameLayout, false, oplusNotificationGroupExtImpl$$ExternalSyntheticLambda0);
        }
    }

    @Override // com.oplus.systemui.notification.row.group.OplusNotificationGroupExt
    public void layoutHeader(NotificationChildrenContainer notificationChildrenContainer) {
        View view;
        int collapsedHeight = notificationChildrenContainer.getCollapsedHeight();
        NotificationHeaderViewWrapper notificationHeaderViewWrapper = this.headerWrapper;
        if (notificationHeaderViewWrapper != null && (view = ((NotificationViewWrapper) notificationHeaderViewWrapper).mView) != null) {
            view.layout(0, 0, view.getMeasuredWidth(), collapsedHeight);
        }
        ViewGroup viewGroup = this.oplusCollapsedHeaderBage;
        if (viewGroup != null) {
            viewGroup.layout(0, 0, viewGroup.getMeasuredWidth(), collapsedHeight);
        }
        ViewGroup viewGroup2 = this.oplusExpandHeader;
        if (viewGroup2 != null) {
            viewGroup2.layout(0, 0, viewGroup2.getMeasuredWidth(), notificationChildrenContainer.getHeaderHeight());
        }
    }

    @Override // com.oplus.systemui.notification.row.group.OplusNotificationGroupExt
    public void measureOplusExpandHeader(NotificationChildrenContainer notificationChildrenContainer, int i, int i2) {
        ViewGroup viewGroup = this.oplusExpandHeader;
        if (viewGroup != null) {
            viewGroup.measure(i, i2);
        }
    }

    @Override // com.oplus.systemui.notification.row.group.OplusNotificationGroupExt
    public void onChildrenViewAdded(NotificationChildrenContainer notificationChildrenContainer, View view) {
        NotificationHeaderViewWrapper notificationHeaderWrapper;
        View view2;
        NotificationChildrenContainerOverlayEx notificationChildrenContainerOverlayEx;
        View overlay;
        ViewGroup viewGroup = this.oplusExpandHeader;
        if (viewGroup != null) {
            viewGroup.bringToFront();
        }
        if (notificationChildrenContainer != null && (notificationChildrenContainerOverlayEx = notificationChildrenContainer.mOverlayEx) != null && (overlay = notificationChildrenContainerOverlayEx.getOverlay()) != null) {
            overlay.bringToFront();
        }
        if (notificationChildrenContainer == null || (notificationHeaderWrapper = notificationChildrenContainer.getNotificationHeaderWrapper()) == null || (view2 = ((NotificationViewWrapper) notificationHeaderWrapper).mView) == null) {
            return;
        }
        view2.bringToFront();
    }

    @Override // com.oplus.systemui.notification.row.group.OplusNotificationGroupExt
    public void onRemoveTransientView(View view) {
        super.onRemoveTransientView(view);
        ((NotificationAodTransition) this.aodTransition.get()).onRemoveView(view);
    }

    @Override // com.oplus.systemui.notification.row.group.OplusNotificationGroupExt
    public void reInflate(NotificationChildrenContainer notificationChildrenContainer, View.OnClickListener onClickListener) {
        NotificationHeaderView groupHeader = notificationChildrenContainer.getGroupHeader();
        if (groupHeader == null) {
            return;
        }
        this.icon = groupHeader.findViewById(R.id.icon);
        notificationChildrenContainer.removeView(this.oplusExpandHeader);
        LayoutInflater layoutInflaterCloneInContext = LayoutInflater.from(this.context).cloneInContext(this.context);
        Trace.traceBegin(8L, "inflateExpandGroupHeader");
        ViewGroup viewGroup = (ViewGroup) layoutInflaterCloneInContext.inflate(2131559312, (ViewGroup) notificationChildrenContainer, false);
        Trace.traceEnd(8L);
        this.oplusExpandHeader = viewGroup;
        TextView textView = (TextView) groupHeader.findViewById(R.id.blocksDescendants);
        CharSequence text = textView != null ? textView.getText() : null;
        String string = this.context.getString(R.string.fingerprint_error_lockout);
        TextView textView2 = (TextView) viewGroup.findViewById(2131364386);
        ImageView imageView = (ImageView) viewGroup.findViewById(2131364385);
        if (textView2 != null) {
            textView2.setText(text);
        }
        if (imageView != null) {
            imageView.setContentDescription(string);
            imageView.setAccessibilityDelegate(this.expandHeaderButtonDelegate);
        }
        updateHeaderColor(textView2, imageView);
        viewGroup.setAlpha(0.0f);
        viewGroup.setOnClickListener(onClickListener);
        notificationChildrenContainer.addView(viewGroup, 0);
        CachingIconView cachingIconView = this.icon;
        if (cachingIconView != null) {
            attachCollapsedGroupBadge(groupHeader, cachingIconView, notificationChildrenContainer);
        }
    }

    @Override // com.oplus.systemui.notification.row.group.OplusNotificationGroupExt
    public void recreateOplusNotificationHeader(NotificationChildrenContainer notificationChildrenContainer, View.OnClickListener onClickListener, boolean z) {
        ExpandableNotificationRow containingNotification = notificationChildrenContainer.getContainingNotification();
        if (containingNotification == null || !containingNotification.isChildInGroup()) {
            this.headerWrapper = notificationChildrenContainer.getNotificationHeaderWrapper();
            NotificationHeaderView groupHeader = notificationChildrenContainer.getGroupHeader();
            NotificationTopLineView notificationTopLineViewFindViewById = groupHeader != null ? groupHeader.findViewById(R.id.pin_message) : null;
            if (notificationTopLineViewFindViewById != null) {
                notificationTopLineViewFindViewById.setVisibility(8);
            }
            if (notificationChildrenContainer.mChildrenExpanded) {
                ViewGroup viewGroup = this.oplusExpandHeader;
                if (Intrinsics.areEqual(viewGroup != null ? Float.valueOf(viewGroup.getAlpha()) : null, 1.0f)) {
                    if (LogUtil.getNormalDebug() && LogUtil.isTagValid(LogUtil.TAG_NOTIFICATION)) {
                        ExpandableNotificationRow containingNotification2 = notificationChildrenContainer.getContainingNotification();
                        MediaTimeoutListener$PlaybackStateListener$.ExternalSyntheticOutline0.m("Should skip groupHeader recreate of row ", containingNotification2 != null ? containingNotification2.getKey() : null, "OplusNotificationGroupExtImpl-->", "SystemUi--Notification");
                    }
                    NotificationHeaderView groupHeader2 = notificationChildrenContainer.getGroupHeader();
                    if (groupHeader2 != null) {
                        groupHeader2.setVisibility(8);
                    }
                    flushIcon(notificationChildrenContainer);
                    notificationChildrenContainer.mOverlayEx.onOplusNotificationHeaderCreated(notificationChildrenContainer.getGroupHeader(), this.oplusExpandHeader);
                    return;
                }
            }
            reInflate(notificationChildrenContainer, onClickListener);
            notificationChildrenContainer.mOverlayEx.onOplusNotificationHeaderCreated(notificationChildrenContainer.getGroupHeader(), this.oplusExpandHeader);
        }
    }

    @Override // com.oplus.systemui.notification.row.group.OplusNotificationGroupExt
    public void setExpandButtonNumber(int i) {
        TextView textView;
        CharSequence text;
        String strValueOf = String.valueOf(i);
        TextView textView2 = this.numberView;
        if (Intrinsics.areEqual((textView2 == null || (text = textView2.getText()) == null) ? null : text.toString(), strValueOf) || (textView = this.numberView) == null) {
            return;
        }
        textView.setText(strValueOf);
    }

    /* JADX WARN: Multi-variable type inference failed */
    /* JADX WARN: Type inference failed for: r0v2, types: [android.widget.ImageView, com.oplus.systemui.notification.row.oplusgroup.OplusNotificationExpandGroupHeaderImageView] */
    @Override // com.oplus.systemui.notification.row.group.OplusNotificationGroupExt
    public final void updateGroupHeaderColor() {
        ViewGroup viewGroup = this.oplusExpandHeader;
        if (viewGroup == null) {
            return;
        }
        TextView textView = (TextView) viewGroup.findViewById(2131364386);
        ?? r0 = (OplusNotificationExpandGroupHeaderImageView) viewGroup.findViewById(2131364385);
        updateHeaderColor(textView, r0);
        if (r0 != 0) {
            r0.refreshMaterialBackground();
        }
    }

    public final void updateHeaderColor(TextView textView, ImageView imageView) {
        int qSDateTimeColor = NotificationColorUtil.getQSDateTimeColor(this.context);
        if (LogUtil.getNormalDebug() && LogUtil.isTagValid(LogUtil.TAG_NOTIFICATION)) {
            Log.d("SystemUi--Notification", "OplusNotificationGroupExtImpl-->updateHeaderColor");
        }
        if (textView != null) {
            textView.setTextColor(qSDateTimeColor);
        }
        if (imageView != null) {
            imageView.setImageTintList(ColorStateList.valueOf(qSDateTimeColor));
        }
    }

    @Override // com.oplus.systemui.notification.row.group.OplusNotificationGroupExt
    public final void updatePillBgAndNumberColor() {
        ExpandableNotificationRow expandableNotificationRow;
        NotificationEntry entry;
        NotificationHeaderViewWrapper notificationHeaderViewWrapper = this.headerWrapper;
        if (notificationHeaderViewWrapper == null || (expandableNotificationRow = ((NotificationViewWrapper) notificationHeaderViewWrapper).mRow) == null || (entry = expandableNotificationRow.getEntry()) == null) {
            return;
        }
        this.groupIconManager.updatePillBgAndNumberColor(this.expandedPill, this.numberView, entry);
    }

    @Override // com.oplus.systemui.notification.row.group.OplusNotificationGroupExt
    public void updateSingleLinePadding(ExpandableNotificationRow expandableNotificationRow, int i) {
        NotificationHeaderViewWrapper notificationHeaderViewWrapper;
        View expandButton;
        int i2 = this.contentPaddingStart;
        int measuredWidth = (i != 0 || (notificationHeaderViewWrapper = this.headerWrapper) == null || (expandButton = notificationHeaderViewWrapper.getExpandButton()) == null) ? this.contentPaddingEnd : expandButton.getMeasuredWidth();
        HybridNotificationView singleLineView = expandableNotificationRow.getSingleLineView();
        if (singleLineView != null) {
            singleLineView.setPaddingRelative(i2, singleLineView.getPaddingTop(), measuredWidth, singleLineView.getPaddingBottom());
        }
    }

    @Override // com.oplus.systemui.notification.row.group.OplusNotificationGroupExt
    public final void updateSummaryGroupBadgeShadow(float f) {
        this.groupIconManager.updateGroupBadgePillShadow(this.expandedPill, false, true, f);
    }

    @Override // com.oplus.systemui.notification.row.group.OplusNotificationGroupExt
    public final void updateSummaryHeaderBlurRadius(float f) {
        ViewGroup viewGroup;
        ViewGroup viewGroup2 = this.oplusExpandHeader;
        if (viewGroup2 != null) {
            viewGroup2.setAlpha(f);
        }
        float fCoerceIn = RangesKt___RangesKt.coerceIn(1.0f - f, 0.0f, 1.0f);
        FrameLayout frameLayout = this.expandedPill;
        if (frameLayout != null) {
            frameLayout.setScaleX(fCoerceIn);
            frameLayout.setScaleY(fCoerceIn);
        }
        if (this.isMiddleOrHighAnimLevel && (viewGroup = this.oplusExpandHeader) != null) {
            if (f <= 0.0f) {
                viewGroup.setRenderEffect(null);
            } else {
                float f2 = (1.0f - f) * 30.0f;
                viewGroup.setRenderEffect(RenderEffect.createBlurEffect(f2, f2, Shader.TileMode.CLAMP));
            }
        }
    }
}
