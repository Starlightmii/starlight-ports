package com.oplus.systemui.statusbar.notification.row.wrapper;

import android.R;
import android.app.Notification;
import android.content.res.ColorStateList;
import android.content.res.Resources;
import android.graphics.PorterDuff;
import android.graphics.drawable.Drawable;
import android.graphics.drawable.GradientDrawable;
import android.service.notification.StatusBarNotification;
import android.util.Log;
import android.view.NotificationHeaderView;
import android.view.View;
import android.view.ViewGroup;
import android.view.ViewParent;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.OplusNotificationDateTimeView;
import android.widget.ProgressBar;
import android.widget.TextView;
import com.android.internal.widget.EmphasizedNotificationButton;
import com.android.internal.widget.MessagingLinearLayout;
import com.android.internal.widget.NotificationActionListLayout;
import com.android.internal.widget.NotificationExpandButton;
import com.android.internal.widget.NotificationProgressBar;
import com.android.systemui.media.controls.domain.pipeline.MediaTimeoutListener$PlaybackStateListener$;
import com.android.systemui.shared.utils.DeviceUtils;
import com.android.systemui.statusbar.notification.collection.NotificationEntry;
import com.android.systemui.statusbar.notification.row.ExpandableNotificationRow;
import com.android.systemui.statusbar.notification.row.NotificationContentView;
import com.android.systemui.statusbar.notification.row.OplusExpandableNotificationRowEx;
import com.android.systemui.statusbar.notification.row.wrapper.NotificationDecoratedCustomViewWrapper;
import com.android.systemui.statusbar.notification.row.wrapper.NotificationHeaderViewWrapper;
import com.android.systemui.statusbar.notification.row.wrapper.NotificationMessagingTemplateViewWrapper;
import com.android.systemui.statusbar.notification.row.wrapper.NotificationTemplateViewWrapper;
import com.android.systemui.statusbar.notification.row.wrapper.NotificationViewWrapper;
import com.android.systemui.statusbar.notification.row.wrapper.OplusNotificationTemplateViewWrapperEx;
import com.coui.appcompat.contextutil.COUIContextUtil;
import com.oplus.systemui.aod.display.OplusAODRefreshRateController$RequestRefreshRateRunnable$;
import com.oplus.systemui.qs.helper.QSFragmentHelper;
import com.oplus.systemui.statusbar.notification.row.OplusExpandableNotificationRowExImpl;
import com.oplusos.systemui.common.util.LogUtil;
import java.lang.invoke.VarHandle;
import java.lang.reflect.Method;
import kotlin.Lazy;
import kotlin.LazyKt__LazyJVMKt;
import kotlin.jvm.internal.Intrinsics;

/* JADX INFO: compiled from: go/retraceme fd74bf9514dd0739b92d67a6d48a51eb10aaf8df760d83496b8dcfcf83e5f698 */
/* JADX INFO: loaded from: /home/starlight/Projects/Starlight Ports/.build-tmp/classes4.dex */
public class OplusNotificationTemplateViewWrapperExImpl extends OplusNotificationHeaderViewWrapperExImp implements OplusNotificationTemplateViewWrapperEx {
    public static final String BASE_TAG = "base";
    public static final String BIG_TAG = "big";
    public static final Companion Companion = new Companion();
    public static final String HEADS_UP_TAG = "headsUp";
    public static final String INBOX_TAG = "inbox";
    public static final String MEDIA_TAG = "media";
    private static final String TAG = "OplusNotificationTempla";
    private View actionsContainerLayout;
    private View conversationText;
    private final Lazy expandButtonMarginTop$delegate;
    private final Lazy iconMargin$delegate;
    private TextView inboxText;
    public boolean isChildInGroup;
    private String lastTimeText = "";
    private NotificationActionListLayout mActions;
    private View mActionsContainer;
    private ViewGroup mColumnContainer;
    private ViewGroup mMainContainer;
    private NotificationHeaderView mNotificationHeader;
    private final Lazy notificationActionStandardSize$delegate;
    public int progressBarPaddingEnd;

    /* JADX INFO: compiled from: go/retraceme fd74bf9514dd0739b92d67a6d48a51eb10aaf8df760d83496b8dcfcf83e5f698 */
    public final class Companion {
    }

    public OplusNotificationTemplateViewWrapperExImpl() {
        OplusNotificationTemplateViewWrapperExImpl$$ExternalSyntheticLambda0 oplusNotificationTemplateViewWrapperExImpl$$ExternalSyntheticLambda0 = new OplusNotificationTemplateViewWrapperExImpl$$ExternalSyntheticLambda0(0);
        oplusNotificationTemplateViewWrapperExImpl$$ExternalSyntheticLambda0.f$0 = this;
        VarHandle.storeStoreFence();
        this.notificationActionStandardSize$delegate = LazyKt__LazyJVMKt.lazy(oplusNotificationTemplateViewWrapperExImpl$$ExternalSyntheticLambda0);
        OplusNotificationTemplateViewWrapperExImpl$$ExternalSyntheticLambda0 oplusNotificationTemplateViewWrapperExImpl$$ExternalSyntheticLambda1 = new OplusNotificationTemplateViewWrapperExImpl$$ExternalSyntheticLambda0(1);
        oplusNotificationTemplateViewWrapperExImpl$$ExternalSyntheticLambda1.f$0 = this;
        VarHandle.storeStoreFence();
        this.iconMargin$delegate = LazyKt__LazyJVMKt.lazy(oplusNotificationTemplateViewWrapperExImpl$$ExternalSyntheticLambda1);
        OplusNotificationTemplateViewWrapperExImpl$$ExternalSyntheticLambda0 oplusNotificationTemplateViewWrapperExImpl$$ExternalSyntheticLambda2 = new OplusNotificationTemplateViewWrapperExImpl$$ExternalSyntheticLambda0(2);
        oplusNotificationTemplateViewWrapperExImpl$$ExternalSyntheticLambda2.f$0 = this;
        VarHandle.storeStoreFence();
        this.expandButtonMarginTop$delegate = LazyKt__LazyJVMKt.lazy(oplusNotificationTemplateViewWrapperExImpl$$ExternalSyntheticLambda2);
    }

    public static void setMarginStart$1(int i, View view) {
        ViewGroup.LayoutParams layoutParams = view.getLayoutParams();
        ViewGroup.MarginLayoutParams marginLayoutParams = layoutParams instanceof ViewGroup.MarginLayoutParams ? (ViewGroup.MarginLayoutParams) layoutParams : null;
        if (marginLayoutParams != null) {
            marginLayoutParams.setMarginStart(i);
        }
        view.setLayoutParams(marginLayoutParams);
    }

    public static void setMarginTop(int i, View view) {
        ViewGroup.LayoutParams layoutParams = view.getLayoutParams();
        ViewGroup.MarginLayoutParams marginLayoutParams = layoutParams instanceof ViewGroup.MarginLayoutParams ? (ViewGroup.MarginLayoutParams) layoutParams : null;
        if (marginLayoutParams != null) {
            marginLayoutParams.topMargin = i;
        }
        view.setLayoutParams(marginLayoutParams);
    }

    public final View getActionsContainerLayout() {
        return this.actionsContainerLayout;
    }

    public final View getConversationText() {
        return this.conversationText;
    }

    public final int getExpandButtonMarginTop() {
        return ((Number) this.expandButtonMarginTop$delegate.getValue()).intValue();
    }

    public final int getIconMargin() {
        return ((Number) this.iconMargin$delegate.getValue()).intValue();
    }

    public final TextView getInboxText() {
        return this.inboxText;
    }

    public final String getLastTimeText() {
        return this.lastTimeText;
    }

    public final NotificationActionListLayout getMActions() {
        return this.mActions;
    }

    public final View getMActionsContainer() {
        return this.mActionsContainer;
    }

    public final ViewGroup getMColumnContainer() {
        return this.mColumnContainer;
    }

    public final ViewGroup getMMainContainer() {
        return this.mMainContainer;
    }

    public final NotificationHeaderView getMNotificationHeader() {
        return this.mNotificationHeader;
    }

    public final float getNotificationActionStandardSize() {
        return ((Number) this.notificationActionStandardSize$delegate.getValue()).floatValue();
    }

    public final int getProgressBarPaddingEnd() {
        return this.progressBarPaddingEnd;
    }

    public void onFindViews() {
        View view;
        View view2;
        NotificationTemplateViewWrapper notificationTemplateViewWrapperM459getBase = m459getBase();
        if (notificationTemplateViewWrapperM459getBase != null && (view = ((NotificationViewWrapper) notificationTemplateViewWrapperM459getBase).mView) != null) {
            this.mNotificationHeader = view.findViewById(R.id.permission_icon);
            setMRightIcon((ImageView) view.findViewById(R.id.shortcut));
            this.mMainContainer = (ViewGroup) view.findViewById(R.id.persistAcrossReboots);
            this.mColumnContainer = (ViewGroup) view.findViewById(R.id.permission_list);
            NotificationTemplateViewWrapper notificationTemplateViewWrapperM459getBase2 = m459getBase();
            this.conversationText = (notificationTemplateViewWrapperM459getBase2 == null || (view2 = ((NotificationViewWrapper) notificationTemplateViewWrapperM459getBase2).mView) == null) ? null : view2.findViewById(R.id.title);
            this.mActions = view.findViewById(R.id.alertTitle);
            View viewFindViewById = view.findViewById(R.id.alerted_icon);
            this.mActionsContainer = viewFindViewById;
            this.actionsContainerLayout = viewFindViewById != null ? viewFindViewById.findViewById(R.id.alias) : null;
            resolveActionsMargin(this.isChildInGroup);
            this.inboxText = (TextView) view.findViewById(R.id.issued_on_header);
            View viewFindViewById2 = view.findViewById(R.id.progress);
            if (viewFindViewById2 instanceof ProgressBar) {
                ((ProgressBar) viewFindViewById2).setLayerType(1, null);
            }
        }
        resolveNotificationActionSize();
    }

    @Override // com.oplus.systemui.statusbar.notification.row.wrapper.OplusNotificationViewWrapperExImpl
    public final void onReinflated() {
        ExpandableNotificationRow expandableNotificationRow;
        NotificationEntry entry;
        StatusBarNotification sbn;
        Notification notification;
        ExpandableNotificationRow expandableNotificationRow2;
        NotificationEntry entry2;
        ExpandableNotificationRow expandableNotificationRow3;
        NotificationEntry entry3;
        View view;
        ExpandableNotificationRow expandableNotificationRow4;
        NotificationEntry entry4;
        View view2;
        resolveActions();
        resolveTitleAndText();
        NotificationTemplateViewWrapper notificationTemplateViewWrapperM459getBase = m459getBase();
        if (notificationTemplateViewWrapperM459getBase != null && (expandableNotificationRow = ((NotificationViewWrapper) notificationTemplateViewWrapperM459getBase).mRow) != null && (entry = expandableNotificationRow.getEntry()) != null && (sbn = entry.getSbn()) != null && (notification = sbn.getNotification()) != null) {
            NotificationTemplateViewWrapper notificationTemplateViewWrapperM459getBase2 = m459getBase();
            View viewFindViewById = (notificationTemplateViewWrapperM459getBase2 == null || (view2 = ((NotificationViewWrapper) notificationTemplateViewWrapperM459getBase2).mView) == null) ? null : view2.findViewById(R.id.progress);
            if (!notification.isColorized()) {
                NotificationTemplateViewWrapper notificationTemplateViewWrapperM459getBase3 = m459getBase();
                if (!Intrinsics.areEqual((notificationTemplateViewWrapperM459getBase3 == null || (expandableNotificationRow4 = ((NotificationViewWrapper) notificationTemplateViewWrapperM459getBase3).mRow) == null || (entry4 = expandableNotificationRow4.getEntry()) == null) ? null : entry4.getNotificationStyle(), Notification.ProgressStyle.class.getSimpleName())) {
                    NotificationTemplateViewWrapper notificationTemplateViewWrapperM459getBase4 = m459getBase();
                    if (!NotificationDecoratedCustomViewWrapper.hasCustomView(notificationTemplateViewWrapperM459getBase4 != null ? ((NotificationViewWrapper) notificationTemplateViewWrapperM459getBase4).mView : null) && (viewFindViewById instanceof ProgressBar)) {
                        ProgressBar progressBar = (ProgressBar) viewFindViewById;
                        if (progressBar.getVisibility() == 0) {
                            ColorStateList colorStateListValueOf = ColorStateList.valueOf(QSFragmentHelper.getInstance().getActiveColorWithDarkMode(getContext()));
                            ColorStateList colorStateListValueOf2 = ColorStateList.valueOf(COUIContextUtil.getAttrColor(getContext(), 2130969262));
                            progressBar.setProgressBackgroundTintMode(PorterDuff.Mode.SRC);
                            progressBar.setProgressBackgroundTintList(colorStateListValueOf2);
                            progressBar.setProgressTintList(colorStateListValueOf);
                        }
                    }
                }
            }
            NotificationTemplateViewWrapper notificationTemplateViewWrapperM459getBase5 = m459getBase();
            View viewFindViewById2 = (notificationTemplateViewWrapperM459getBase5 == null || (view = ((NotificationViewWrapper) notificationTemplateViewWrapperM459getBase5).mView) == null) ? null : view.findViewById(R.id.shortcut);
            if (this.progressBarPaddingEnd == 0) {
                this.progressBarPaddingEnd = getContext().getResources().getDimensionPixelSize(2131170761);
            }
            int i = (!(viewFindViewById2 instanceof ImageView) || ((ImageView) viewFindViewById2).getDrawable() == null) ? 0 : this.progressBarPaddingEnd;
            NotificationTemplateViewWrapper notificationTemplateViewWrapperM459getBase6 = m459getBase();
            if (!Intrinsics.areEqual((notificationTemplateViewWrapperM459getBase6 == null || (expandableNotificationRow3 = ((NotificationViewWrapper) notificationTemplateViewWrapperM459getBase6).mRow) == null || (entry3 = expandableNotificationRow3.getEntry()) == null) ? null : entry3.getNotificationStyle(), Notification.ProgressStyle.class.getSimpleName()) && (viewFindViewById instanceof ProgressBar)) {
                ProgressBar progressBar2 = (ProgressBar) viewFindViewById;
                if (progressBar2.getPaddingEnd() != i) {
                    progressBar2.setPaddingRelative(0, 0, i, 0);
                    updateProgressDrawableBounds(progressBar2);
                }
            }
            NotificationTemplateViewWrapper notificationTemplateViewWrapperM459getBase7 = m459getBase();
            if (Intrinsics.areEqual((notificationTemplateViewWrapperM459getBase7 == null || (expandableNotificationRow2 = ((NotificationViewWrapper) notificationTemplateViewWrapperM459getBase7).mRow) == null || (entry2 = expandableNotificationRow2.getEntry()) == null) ? null : entry2.getNotificationStyle(), Notification.ProgressStyle.class.getSimpleName()) && (viewFindViewById instanceof NotificationProgressBar)) {
                ViewParent parent = ((NotificationProgressBar) viewFindViewById).getParent();
                LinearLayout linearLayout = parent instanceof LinearLayout ? (LinearLayout) parent : null;
                ViewGroup.LayoutParams layoutParams = linearLayout != null ? linearLayout.getLayoutParams() : null;
                LinearLayout.LayoutParams layoutParams2 = layoutParams instanceof LinearLayout.LayoutParams ? (LinearLayout.LayoutParams) layoutParams : null;
                if (layoutParams2 != null) {
                    layoutParams2.setMarginEnd(i);
                }
                if (linearLayout != null) {
                    linearLayout.setLayoutParams(layoutParams2);
                }
            }
        }
        resolveNotificationActionSize();
    }

    public void resolveActions() {
        ExpandableNotificationRow expandableNotificationRow;
        NotificationEntry entry;
        StatusBarNotification sbn;
        Notification notification;
        NotificationTemplateViewWrapper notificationTemplateViewWrapperM459getBase;
        ExpandableNotificationRow expandableNotificationRow2;
        NotificationEntry entry2;
        ExpandableNotificationRow row;
        ExpandableNotificationRow expandableNotificationRow3;
        NotificationEntry entry3;
        StatusBarNotification sbn2;
        View view;
        ViewGroup viewGroup;
        ExpandableNotificationRow expandableNotificationRow4;
        NotificationEntry entry4;
        StatusBarNotification sbn3;
        NotificationTemplateViewWrapper notificationTemplateViewWrapperM459getBase2 = m459getBase();
        if (notificationTemplateViewWrapperM459getBase2 == null || (expandableNotificationRow = ((NotificationViewWrapper) notificationTemplateViewWrapperM459getBase2).mRow) == null || (entry = expandableNotificationRow.getEntry()) == null || (sbn = entry.getSbn()) == null || (notification = sbn.getNotification()) == null) {
            return;
        }
        String packageName = null;
        if (notification.isColorized() || notification.fullScreenIntent != null) {
            if (!notification.isColorized() || notification.color != 0 || (notificationTemplateViewWrapperM459getBase = m459getBase()) == null || (expandableNotificationRow2 = ((NotificationViewWrapper) notificationTemplateViewWrapperM459getBase).mRow) == null || (entry2 = expandableNotificationRow2.getEntry()) == null || (row = entry2.getRow()) == null || row.getCurrentBackgroundTint() != 0) {
                return;
            }
            NotificationTemplateViewWrapper notificationTemplateViewWrapperM459getBase3 = m459getBase();
            if (notificationTemplateViewWrapperM459getBase3 != null && (expandableNotificationRow3 = ((NotificationViewWrapper) notificationTemplateViewWrapperM459getBase3).mRow) != null && (entry3 = expandableNotificationRow3.getEntry()) != null && (sbn2 = entry3.getSbn()) != null) {
                packageName = sbn2.getPackageName();
            }
            OplusAODRefreshRateController$RequestRefreshRateRunnable$.ExternalSyntheticOutline0.m(8, "resolveActions error packageName:", packageName, LogUtil.TAG_COMMON, TAG);
            return;
        }
        NotificationTemplateViewWrapper notificationTemplateViewWrapperM459getBase4 = m459getBase();
        if (notificationTemplateViewWrapperM459getBase4 == null || (view = ((NotificationViewWrapper) notificationTemplateViewWrapperM459getBase4).mView) == null || (viewGroup = (ViewGroup) view.findViewById(R.id.alertTitle)) == null || viewGroup.getChildCount() <= 0) {
            return;
        }
        if (viewGroup.getChildAt(0) instanceof EmphasizedNotificationButton) {
            if (LogUtil.getNormalDebug() && LogUtil.isTagValid(LogUtil.TAG_NOTIFICATION)) {
                NotificationTemplateViewWrapper notificationTemplateViewWrapperM459getBase5 = m459getBase();
                if (notificationTemplateViewWrapperM459getBase5 != null && (expandableNotificationRow4 = ((NotificationViewWrapper) notificationTemplateViewWrapperM459getBase5).mRow) != null && (entry4 = expandableNotificationRow4.getEntry()) != null && (sbn3 = entry4.getSbn()) != null) {
                    packageName = sbn3.getPackageName();
                }
                MediaTimeoutListener$PlaybackStateListener$.ExternalSyntheticOutline0.m("resolveActions skip legacy crush for Emphasized buttons, pkg=", packageName, "OplusNotificationTempla-->", "SystemUi--Notification");
                return;
            }
            return;
        }
        Resources resources = getContext().getResources();
        int dimensionPixelSize = resources.getDimensionPixelSize(2131170616);
        int dimensionPixelSize2 = resources.getDimensionPixelSize(2131170575);
        int dimensionPixelSize3 = resources.getDimensionPixelSize(2131165408);
        int dimensionPixelSize4 = resources.getDimensionPixelSize(2131165407);
        ViewGroup.LayoutParams layoutParams = viewGroup.getLayoutParams();
        layoutParams.height = dimensionPixelSize2;
        viewGroup.setLayoutParams(layoutParams);
        viewGroup.setPadding(0, dimensionPixelSize3, 0, dimensionPixelSize4);
        int childCount = viewGroup.getChildCount();
        for (int i = 0; i < childCount; i++) {
            View childAt = viewGroup.getChildAt(i);
            if (childAt instanceof Button) {
                Drawable drawable = resources.getDrawable(2131235704, getContext().getTheme());
                if (drawable instanceof GradientDrawable) {
                    ((Button) childAt).setBackground(drawable);
                }
            }
            if (childAt != null) {
                childAt.setPadding(childAt.getPaddingLeft(), 0, childAt.getPaddingRight(), 0);
            }
            ViewGroup.LayoutParams layoutParams2 = childAt != null ? childAt.getLayoutParams() : null;
            if (layoutParams2 != null) {
                layoutParams2.height = dimensionPixelSize;
            }
            if (childAt != null) {
                childAt.setLayoutParams(layoutParams2);
            }
        }
    }

    public final void resolveActionsMargin(boolean z) {
        View view;
        View view2;
        View view3;
        NotificationTemplateViewWrapper notificationTemplateViewWrapperM459getBase = m459getBase();
        View viewFindViewById = null;
        Object tag = (notificationTemplateViewWrapperM459getBase == null || (view3 = ((NotificationViewWrapper) notificationTemplateViewWrapperM459getBase).mView) == null) ? null : view3.getTag();
        NotificationTemplateViewWrapper notificationTemplateViewWrapperM459getBase2 = m459getBase();
        if (Intrinsics.areEqual((notificationTemplateViewWrapperM459getBase2 == null || (view2 = ((NotificationViewWrapper) notificationTemplateViewWrapperM459getBase2).mView) == null) ? null : view2.getTag(), "call")) {
            View view4 = this.conversationText;
            if (view4 != null) {
                view4.setSelected(true);
                return;
            }
            return;
        }
        int dimensionPixelSize = 0;
        if (!z || (m459getBase() instanceof NotificationMessagingTemplateViewWrapper)) {
            View view5 = this.actionsContainerLayout;
            if (view5 != null) {
                setMarginStart$1(-getIconMargin(), view5);
            }
            View view6 = this.mActionsContainer;
            if (view6 != null) {
                setMarginStart$1(0, view6);
            }
        } else {
            View view7 = this.actionsContainerLayout;
            if (view7 != null) {
                setMarginStart$1(0, view7);
            }
            View view8 = this.mActionsContainer;
            if (view8 != null) {
                setMarginStart$1(-getIconMargin(), view8);
            }
        }
        if (HEADS_UP_TAG.equals(tag) || "progress".equals(tag)) {
            return;
        }
        NotificationTemplateViewWrapper notificationTemplateViewWrapperM459getBase3 = m459getBase();
        if (notificationTemplateViewWrapperM459getBase3 != null && (view = ((NotificationViewWrapper) notificationTemplateViewWrapperM459getBase3).mView) != null) {
            viewFindViewById = view.findViewById(R.id.progress);
        }
        if (viewFindViewById != null && viewFindViewById.getVisibility() == 0) {
            dimensionPixelSize = getContext().getResources().getDimensionPixelSize(2131170581);
        }
        View view9 = this.mActionsContainer;
        if (view9 != null) {
            setMarginTop(-dimensionPixelSize, view9);
        }
    }

    public final void resolveNotificationActionSize() {
        NotificationActionListLayout notificationActionListLayout;
        ExpandableNotificationRow expandableNotificationRow;
        DeviceUtils.INSTANCE.getClass();
        if (DeviceUtils.isTablet() || (notificationActionListLayout = this.mActions) == null || notificationActionListLayout.getChildCount() <= 0) {
            return;
        }
        NotificationTemplateViewWrapper notificationTemplateViewWrapperM459getBase = m459getBase();
        OplusExpandableNotificationRowEx oplusExpandableNotificationRowEx = (notificationTemplateViewWrapperM459getBase == null || (expandableNotificationRow = ((NotificationViewWrapper) notificationTemplateViewWrapperM459getBase).mRow) == null) ? null : expandableNotificationRow.mRowEx;
        boolean zIsSplitShadeEnabled = oplusExpandableNotificationRowEx != null ? ((OplusExpandableNotificationRowExImpl) oplusExpandableNotificationRowEx).isSplitShadeEnabled() : false;
        float dimension = getContext().getResources().getDimension(2131170580);
        if (zIsSplitShadeEnabled && dimension > getNotificationActionStandardSize()) {
            dimension = getNotificationActionStandardSize();
        }
        int childCount = notificationActionListLayout.getChildCount();
        for (int i = 0; i < childCount; i++) {
            View childAt = notificationActionListLayout.getChildAt(i);
            if (childAt instanceof Button) {
                Button button = (Button) childAt;
                if (button.getTextSize() != dimension) {
                    button.setTextSize(0, dimension);
                }
            }
        }
    }

    public final void resolveNotificationMainContainer() {
        View expandButton;
        View view;
        View view2;
        Resources resources;
        View view3;
        View view4;
        ExpandableNotificationRow expandableNotificationRow;
        ExpandableNotificationRow expandableNotificationRow2;
        MessagingLinearLayout imageMessageContainer;
        ViewGroup viewGroup = this.mMainContainer;
        if (viewGroup != null) {
            MessagingLinearLayout imageMessageContainer2 = getImageMessageContainer();
            boolean z = imageMessageContainer2 != null && imageMessageContainer2.getVisibility() == 0;
            ImageView mRightIcon = getMRightIcon();
            boolean z2 = mRightIcon != null && mRightIcon.getVisibility() == 0;
            if (viewGroup.getVisibility() == 0 && ((z || z2) && (imageMessageContainer = getImageMessageContainer()) != null)) {
                OplusNotificationTemplateViewWrapperExImpl$resolveNotificationMainContainer$1$1 oplusNotificationTemplateViewWrapperExImpl$resolveNotificationMainContainer$1$1 = new OplusNotificationTemplateViewWrapperExImpl$resolveNotificationMainContainer$1$1();
                oplusNotificationTemplateViewWrapperExImpl$resolveNotificationMainContainer$1$1.this$0 = this;
                oplusNotificationTemplateViewWrapperExImpl$resolveNotificationMainContainer$1$1.$imageMessageContainerVisible = z;
                oplusNotificationTemplateViewWrapperExImpl$resolveNotificationMainContainer$1$1.$rightIconVisible = z2;
                oplusNotificationTemplateViewWrapperExImpl$resolveNotificationMainContainer$1$1.$it = viewGroup;
                VarHandle.storeStoreFence();
                imageMessageContainer.post(oplusNotificationTemplateViewWrapperExImpl$resolveNotificationMainContainer$1$1);
            }
        }
        if (!this.isChildInGroup) {
            NotificationTemplateViewWrapper notificationTemplateViewWrapperM459getBase = m459getBase();
            this.isChildInGroup = (notificationTemplateViewWrapperM459getBase == null || (expandableNotificationRow2 = ((NotificationViewWrapper) notificationTemplateViewWrapperM459getBase).mRow) == null || !expandableNotificationRow2.isChildInGroup()) ? false : true;
        }
        setIsChildInGroup(this.isChildInGroup);
        Resources resources2 = getContext().getResources();
        if (resources2 != null) {
            int dimensionPixelOffset = resources2.getDimensionPixelOffset(2131170628);
            NotificationTemplateViewWrapper notificationTemplateViewWrapperM459getBase2 = m459getBase();
            ViewGroup.LayoutParams layoutParams = null;
            NotificationContentView publicLayout = (notificationTemplateViewWrapperM459getBase2 == null || (expandableNotificationRow = ((NotificationViewWrapper) notificationTemplateViewWrapperM459getBase2).mRow) == null) ? null : expandableNotificationRow.getPublicLayout();
            NotificationTemplateViewWrapper notificationTemplateViewWrapperM459getBase3 = m459getBase();
            boolean zAreEqual = Intrinsics.areEqual(publicLayout, (notificationTemplateViewWrapperM459getBase3 == null || (view4 = ((NotificationViewWrapper) notificationTemplateViewWrapperM459getBase3).mView) == null) ? null : view4.getParent());
            if (m459getBase() instanceof NotificationDecoratedCustomViewWrapper) {
                NotificationTemplateViewWrapper notificationTemplateViewWrapperM459getBase4 = m459getBase();
                if (BIG_TAG.equals((notificationTemplateViewWrapperM459getBase4 == null || (view3 = ((NotificationViewWrapper) notificationTemplateViewWrapperM459getBase4).mView) == null) ? null : view3.getTag()) && !zAreEqual && (resources = getContext().getResources()) != null) {
                    int dimensionPixelOffset2 = resources.getDimensionPixelOffset(2131171067);
                    ViewGroup viewGroup2 = this.mMainContainer;
                    if (viewGroup2 != null) {
                        int i = dimensionPixelOffset2 + dimensionPixelOffset;
                        Resources resources3 = getContext().getResources();
                        setMarginTop(i + (resources3 != null ? resources3.getDimensionPixelOffset(R.dimen.preference_screen_bottom_margin) : 0), viewGroup2);
                    }
                }
            }
            if (m459getBase() instanceof NotificationDecoratedCustomViewWrapper) {
                NotificationTemplateViewWrapper notificationTemplateViewWrapperM459getBase5 = m459getBase();
                if (!BASE_TAG.equals((notificationTemplateViewWrapperM459getBase5 == null || (view2 = ((NotificationViewWrapper) notificationTemplateViewWrapperM459getBase5).mView) == null) ? null : view2.getTag())) {
                    NotificationTemplateViewWrapper notificationTemplateViewWrapperM459getBase6 = m459getBase();
                    if (!HEADS_UP_TAG.equals((notificationTemplateViewWrapperM459getBase6 == null || (view = ((NotificationViewWrapper) notificationTemplateViewWrapperM459getBase6).mView) == null) ? null : view.getTag())) {
                        return;
                    }
                }
                if (zAreEqual) {
                    return;
                }
                ViewGroup viewGroup3 = this.mMainContainer;
                if (viewGroup3 != null) {
                    setMarginTop(dimensionPixelOffset, viewGroup3);
                }
                Resources resources4 = getContext().getResources();
                if (resources4 != null) {
                    int dimensionPixelOffset3 = resources4.getDimensionPixelOffset(2131171067);
                    ImageView mRightIcon2 = getMRightIcon();
                    if (mRightIcon2 == null || mRightIcon2.getVisibility() != 8) {
                        ImageView mRightIcon3 = getMRightIcon();
                        int measuredWidthOrMeasure = dimensionPixelOffset3 + (mRightIcon3 != null ? getMeasuredWidthOrMeasure(mRightIcon3, Boolean.FALSE) : 0);
                        ViewGroup viewGroup4 = this.mMainContainer;
                        if (viewGroup4 != null) {
                            ViewGroup.LayoutParams layoutParams2 = viewGroup4.getLayoutParams();
                            ViewGroup.MarginLayoutParams marginLayoutParams = layoutParams2 instanceof ViewGroup.MarginLayoutParams ? (ViewGroup.MarginLayoutParams) layoutParams2 : null;
                            if (marginLayoutParams != null) {
                                marginLayoutParams.setMarginEnd(measuredWidthOrMeasure);
                            }
                            viewGroup4.setLayoutParams(marginLayoutParams);
                        }
                    }
                }
                NotificationHeaderViewWrapper notificationHeaderViewWrapperM459getBase = m459getBase();
                if (notificationHeaderViewWrapperM459getBase == null || (expandButton = notificationHeaderViewWrapperM459getBase.getExpandButton()) == null) {
                    return;
                }
                ViewGroup.LayoutParams layoutParams3 = expandButton.getLayoutParams();
                if (layoutParams3 != null) {
                    layoutParams3.height = -2;
                    layoutParams = layoutParams3;
                }
                expandButton.setLayoutParams(layoutParams);
            }
        }
    }

    public void resolveTitleAndText() {
        ExpandableNotificationRow expandableNotificationRow;
        NotificationEntry entry;
        StatusBarNotification sbn;
        View view;
        View view2;
        View view3;
        NotificationTemplateViewWrapper notificationTemplateViewWrapperM459getBase = m459getBase();
        if (notificationTemplateViewWrapperM459getBase == null || (expandableNotificationRow = ((NotificationViewWrapper) notificationTemplateViewWrapperM459getBase).mRow) == null || (entry = expandableNotificationRow.getEntry()) == null || (sbn = entry.getSbn()) == null || sbn.getNotification() == null) {
            return;
        }
        NotificationTemplateViewWrapper notificationTemplateViewWrapperM459getBase2 = m459getBase();
        TextView textView = null;
        TextView textView2 = (notificationTemplateViewWrapperM459getBase2 == null || (view3 = ((NotificationViewWrapper) notificationTemplateViewWrapperM459getBase2).mView) == null) ? null : (TextView) view3.findViewById(R.id.title);
        NotificationTemplateViewWrapper notificationTemplateViewWrapperM459getBase3 = m459getBase();
        TextView textView3 = (notificationTemplateViewWrapperM459getBase3 == null || (view2 = ((NotificationViewWrapper) notificationTemplateViewWrapperM459getBase3).mView) == null) ? null : (TextView) view2.findViewById(R.id.assertive);
        NotificationTemplateViewWrapper notificationTemplateViewWrapperM459getBase4 = m459getBase();
        if (notificationTemplateViewWrapperM459getBase4 != null && (view = ((NotificationViewWrapper) notificationTemplateViewWrapperM459getBase4).mView) != null) {
            textView = (TextView) view.findViewById(R.id.transition_overlay_view_tag);
        }
        if (textView2 != null) {
            textView2.setElegantTextHeight(false);
        }
        if (textView3 != null) {
            textView3.setElegantTextHeight(false);
        }
        if (textView != null) {
            textView.setElegantTextHeight(false);
        }
    }

    public final void setActionsContainerLayout(View view) {
        this.actionsContainerLayout = view;
    }

    public final void setChildInGroup(boolean z) {
        this.isChildInGroup = z;
    }

    public final void setConversationText(View view) {
        this.conversationText = view;
    }

    public final void setInboxText(TextView textView) {
        this.inboxText = textView;
    }

    @Override // com.oplus.systemui.statusbar.notification.row.wrapper.OplusNotificationHeaderViewWrapperExImp
    public void setIsChildInGroup(boolean z) {
        super.setIsChildInGroup(z);
        NotificationTemplateViewWrapper notificationTemplateViewWrapperM459getBase = m459getBase();
        NotificationDecoratedCustomViewWrapper.hasCustomView(notificationTemplateViewWrapperM459getBase != null ? ((NotificationViewWrapper) notificationTemplateViewWrapperM459getBase).mView : null);
    }

    public final void setLastTimeText(String str) {
        this.lastTimeText = str;
    }

    public final void setMActions(NotificationActionListLayout notificationActionListLayout) {
        this.mActions = notificationActionListLayout;
    }

    public final void setMActionsContainer(View view) {
        this.mActionsContainer = view;
    }

    public final void setMColumnContainer(ViewGroup viewGroup) {
        this.mColumnContainer = viewGroup;
    }

    public final void setMMainContainer(ViewGroup viewGroup) {
        this.mMainContainer = viewGroup;
    }

    public final void setMNotificationHeader(NotificationHeaderView notificationHeaderView) {
        this.mNotificationHeader = notificationHeaderView;
    }

    public final void setProgressBarPaddingEnd(int i) {
        this.progressBarPaddingEnd = i;
    }

    @Override // com.oplus.systemui.statusbar.notification.row.wrapper.OplusNotificationHeaderViewWrapperExImp
    public final void updateHeaderTopLineMargin() {
        NotificationTemplateViewWrapper notificationTemplateViewWrapperM459getBase;
        NotificationTemplateViewWrapper notificationTemplateViewWrapperM459getBase2;
        NotificationExpandButton notificationExpandButton;
        View view;
        super.updateHeaderTopLineMargin();
        NotificationTemplateViewWrapper notificationTemplateViewWrapperM459getBase3 = m459getBase();
        if (Intrinsics.areEqual((notificationTemplateViewWrapperM459getBase3 == null || (view = ((NotificationViewWrapper) notificationTemplateViewWrapperM459getBase3).mView) == null) ? null : view.getTag(), "call")) {
            return;
        }
        NotificationTemplateViewWrapper notificationTemplateViewWrapperM459getBase4 = m459getBase();
        if ((notificationTemplateViewWrapperM459getBase4 != null ? ((NotificationHeaderViewWrapper) notificationTemplateViewWrapperM459getBase4).mNotificationHeader : null) != null || (notificationTemplateViewWrapperM459getBase = m459getBase()) == null || notificationTemplateViewWrapperM459getBase.getExpandButton() == null) {
            return;
        }
        ViewGroup viewGroup = this.mMainContainer;
        TextView textView = viewGroup != null ? (TextView) viewGroup.findViewById(R.id.title) : null;
        ViewGroup.LayoutParams layoutParams = textView != null ? textView.getLayoutParams() : null;
        ViewGroup.MarginLayoutParams marginLayoutParams = layoutParams instanceof ViewGroup.MarginLayoutParams ? (ViewGroup.MarginLayoutParams) layoutParams : null;
        if (marginLayoutParams == null || (notificationTemplateViewWrapperM459getBase2 = m459getBase()) == null || (notificationExpandButton = ((NotificationHeaderViewWrapper) notificationTemplateViewWrapperM459getBase2).mExpandButton) == null) {
            return;
        }
        OplusNotificationDateTimeView dateView = getDateView();
        int expandButtonMarginLeft = getExpandButtonMarginLeft() + (getMeasuredWidthOrMeasure(notificationExpandButton, dateView != null ? Boolean.valueOf(dateView.isLayoutRequested()) : null) - marginLayoutParams.getMarginEnd());
        if (textView.getPaddingEnd() != expandButtonMarginLeft) {
            textView.setPaddingRelative(textView.getPaddingStart(), textView.getPaddingTop(), expandButtonMarginLeft, textView.getPaddingBottom());
        }
    }

    public void updateProgressDrawableBounds(ProgressBar progressBar) {
        try {
            Class cls = Integer.TYPE;
            Method declaredMethod = ProgressBar.class.getDeclaredMethod("onSizeChanged", cls, cls, cls, cls);
            declaredMethod.setAccessible(true);
            declaredMethod.invoke(progressBar, Integer.valueOf(progressBar.getWidth()), Integer.valueOf(progressBar.getHeight()), Integer.valueOf(progressBar.getWidth()), Integer.valueOf(progressBar.getHeight()));
        } catch (Exception e) {
            if (LogUtil.getNormalDebug() && LogUtil.isTagValid(LogUtil.TAG_NOTIFICATION)) {
                Log.d("SystemUi--Notification", "OplusNotificationTempla-->".concat("updateProgressDrawableBounds error Exception:" + e));
            }
        }
    }
}
