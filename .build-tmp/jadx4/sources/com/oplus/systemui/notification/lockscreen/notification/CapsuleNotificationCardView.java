package com.oplus.systemui.notification.lockscreen.notification;

import android.app.Notification;
import android.app.PendingIntent;
import android.content.Context;
import android.content.res.Resources;
import android.graphics.drawable.Drawable;
import android.service.notification.StatusBarNotification;
import android.view.LayoutInflater;
import android.view.ViewGroup;
import android.view.accessibility.AccessibilityNodeInfo;
import android.widget.FrameLayout;
import android.widget.LinearLayout;
import android.widget.TextView;
import androidx.compose.animation.core.MonoSpline$;
import androidx.compose.foundation.shape.DpCornerSize$;
import com.android.internal.widget.CachingIconView;
import com.android.systemui.statusbar.TransformableView;
import com.android.systemui.statusbar.ViewTransformationHelper;
import com.android.systemui.statusbar.notification.NotificationActivityStarter;
import com.android.systemui.statusbar.notification.TransformState;
import com.android.systemui.statusbar.notification.collection.NotificationEntry;
import com.android.systemui.statusbar.notification.row.ExpandableNotificationRow;
import com.oplus.systemui.notification.row.oplusgroup.GroupIconManager;
import com.oplus.systemui.statusbar.notification.util.RoundRectDrawableUtil;
import com.oplusos.systemui.common.util.ChangeTextSizeUtil;
import com.oplusos.systemui.common.util.LogUtil;
import kotlin.LazyKt__LazyJVMKt$;
import kotlin.text.StringsKt__StringsKt;

/* JADX INFO: compiled from: go/retraceme fd74bf9514dd0739b92d67a6d48a51eb10aaf8df760d83496b8dcfcf83e5f698 */
/* JADX INFO: loaded from: /home/starlight/Projects/Starlight Ports/.build-tmp/classes4.dex */
public final class CapsuleNotificationCardView extends LinearLayout implements TransformableView {
    public static final Companion Companion = new Companion();
    public static final String TAG = "CapsuleNotificationCardView";
    public CachingIconView bottomRightBadge;
    private CapsuleNotificationDataController.CardType cardType;
    public LinearLayout contentLayout;
    private CapsuleNotificationDataController controller;
    private CapsuleNotificationDataController.CapsuleNotificationInnerData currentData;
    private NotificationEntry firstEntry;
    public CachingIconView icon;
    public FrameLayout iconTouchArea;
    public boolean isMessageStyle;
    private final ViewTransformationHelper mTransformationHelper;
    private TextView numView;
    private FrameLayout numViewContainer;
    private FrameLayout textContainer;
    public TextView textView;
    public TextView titleView;

    /* JADX INFO: compiled from: go/retraceme fd74bf9514dd0739b92d67a6d48a51eb10aaf8df760d83496b8dcfcf83e5f698 */
    public final class Companion {
    }

    public CapsuleNotificationCardView(Context context) {
        super(context);
        this.mTransformationHelper = new ViewTransformationHelper();
        this.cardType = CapsuleNotificationDataController.CardType.SINGLE;
        setFocusable(true);
        LayoutInflater.from(getContext()).inflate(2131559271, (ViewGroup) this, true);
        initViews();
    }

    public static void applyG3TextSize(TextView textView) {
        float dimensionPixelSize = textView.getResources().getDimensionPixelSize(2131165929);
        float f = Resources.getSystem().getConfiguration().fontScale;
        float g3FontSize = ChangeTextSizeUtil.getG3FontSize(dimensionPixelSize, f);
        LogUtil.i$default(LogUtil.TAG_NOTIFICATION, TAG, DpCornerSize$.ExternalSyntheticOutline0.m(g3FontSize, "px", MonoSpline$.ExternalSyntheticOutline0.m("noScaleSize=", dimensionPixelSize, "px, systemFontScale=", f, ", adaptedSize=")), null, 8);
        textView.setTextSize(0, g3FontSize);
    }

    private final void setupIconAndBadges(CapsuleNotificationDataController.CapsuleNotificationInnerData capsuleNotificationInnerData) {
        Integer badgeCount;
        getBottomRightBadge().setVisibility(8);
        FrameLayout frameLayout = this.numViewContainer;
        if (frameLayout != null) {
            frameLayout.setVisibility(8);
        }
        TextView textView = this.numView;
        if (textView != null) {
            textView.setVisibility(8);
        }
        getIcon().clearColorFilter();
        int iOrdinal = this.cardType.ordinal();
        if (iOrdinal == 0) {
            CapsuleNotificationDataController.NotificationIconData iconData = capsuleNotificationInnerData.getIconData();
            updateIconforLargeSize(iconData != null && iconData.isCloned);
            CachingIconView icon = getIcon();
            Drawable roundedIcon = getRoundedIcon(capsuleNotificationInnerData.getIconData());
            icon.setImageDrawable(roundedIcon);
            icon.setVisibility(roundedIcon != null ? 0 : 4);
            return;
        }
        if (iOrdinal == 1) {
            updateIconforLargeSize(true);
            CachingIconView icon2 = getIcon();
            Drawable avatarIcon = capsuleNotificationInnerData.getAvatarIcon();
            icon2.setImageDrawable(avatarIcon);
            icon2.setVisibility(avatarIcon != null ? 0 : 4);
            CachingIconView bottomRightBadge = getBottomRightBadge();
            CapsuleNotificationDataController.NotificationIconData iconData2 = capsuleNotificationInnerData.getIconData();
            bottomRightBadge.setImageDrawable(iconData2 != null ? iconData2.getIcon() : null);
            getBottomRightBadge().setVisibility(0);
            return;
        }
        if (iOrdinal != 2) {
            if (iOrdinal != 3) {
                LazyKt__LazyJVMKt$.ExternalSyntheticBUOutline0.m();
                return;
            }
            CachingIconView icon3 = getIcon();
            CapsuleNotificationDataController.NotificationIconData iconData3 = capsuleNotificationInnerData.getIconData();
            Drawable icon4 = iconData3 != null ? iconData3.getIcon() : null;
            icon3.setImageDrawable(icon4);
            icon3.setVisibility(icon4 != null ? 0 : 4);
            CachingIconView icon5 = getIcon();
            CapsuleNotificationDataController capsuleNotificationDataController = this.controller;
            icon5.setColorFilter(capsuleNotificationDataController != null ? capsuleNotificationDataController.getTextColor() : -1);
            return;
        }
        CapsuleNotificationDataController.NotificationIconData iconData4 = capsuleNotificationInnerData.getIconData();
        updateIconforLargeSize(iconData4 != null && iconData4.isCloned);
        CachingIconView icon6 = getIcon();
        Drawable roundedIcon2 = getRoundedIcon(capsuleNotificationInnerData.getIconData());
        icon6.setImageDrawable(roundedIcon2);
        icon6.setVisibility(roundedIcon2 != null ? 0 : 4);
        if (this.cardType == CapsuleNotificationDataController.CardType.GROUP) {
            CapsuleNotificationDataController.CapsuleNotificationInnerData capsuleNotificationInnerData2 = this.currentData;
            if (((capsuleNotificationInnerData2 == null || (badgeCount = capsuleNotificationInnerData2.getBadgeCount()) == null) ? 0 : badgeCount.intValue()) > 1) {
                FrameLayout frameLayout2 = this.numViewContainer;
                if (frameLayout2 != null) {
                    frameLayout2.setVisibility(0);
                }
                TextView textView2 = this.numView;
                if (textView2 != null) {
                    textView2.setVisibility(0);
                }
                TextView textView3 = this.numView;
                if (textView3 != null) {
                    textView3.setText(String.valueOf(capsuleNotificationInnerData.getBadgeCount()));
                }
                updateGroupBadgeColor(this.firstEntry, this.numViewContainer, this.numView, getIcon());
            }
        }
    }

    private final void setupTitleAndContent(CapsuleNotificationDataController.CapsuleNotificationInnerData capsuleNotificationInnerData) {
        getTitleView().setText(capsuleNotificationInnerData.getTitle());
        getTextView().setText(capsuleNotificationInnerData.getContent());
        CapsuleNotificationDataController capsuleNotificationDataController = this.controller;
        if (capsuleNotificationDataController != null) {
            int textColor = capsuleNotificationDataController.getTextColor();
            getTitleView().setTextColor(textColor);
            getTextView().setTextColor(textColor);
        }
        applyG3TextSize(getTitleView());
        applyG3TextSize(getTextView());
        getTitleView().setAlpha(1.0f);
        getTextView().setAlpha(1.0f);
        FrameLayout.LayoutParams layoutParams = (FrameLayout.LayoutParams) getTitleView().getLayoutParams();
        FrameLayout.LayoutParams layoutParams2 = (FrameLayout.LayoutParams) getTextView().getLayoutParams();
        if (StringsKt__StringsKt.isBlank(capsuleNotificationInnerData.getContent())) {
            getTextView().setAlpha(0.0f);
            getTextView().setVisibility(0);
            layoutParams.gravity = 16;
            layoutParams2.gravity = 80;
        } else if (StringsKt__StringsKt.isBlank(capsuleNotificationInnerData.getTitle())) {
            getTitleView().setAlpha(0.0f);
            getTitleView().setVisibility(0);
            layoutParams2.gravity = 16;
            layoutParams.gravity = 48;
        } else {
            layoutParams.gravity = 48;
            layoutParams2.gravity = 80;
        }
        getTitleView().setLayoutParams(layoutParams);
        getTextView().setLayoutParams(layoutParams2);
    }

    public final void applyTextColor(int i) {
        getTitleView().setTextColor(i);
        getTextView().setTextColor(i);
        if (this.cardType == CapsuleNotificationDataController.CardType.AGGREGATE) {
            getIcon().setColorFilter(i);
        }
    }

    public final void bind(CapsuleNotificationDataController.CapsuleNotificationInnerData capsuleNotificationInnerData) {
        this.currentData = capsuleNotificationInnerData;
        CapsuleNotificationDataController.CardType cardType = capsuleNotificationInnerData.getCardType();
        this.cardType = cardType;
        this.isMessageStyle = cardType == CapsuleNotificationDataController.CardType.SINGLE_MESSAGE;
        CapsuleNotificationDataController.NotificationIconData iconData = capsuleNotificationInnerData.getIconData();
        this.firstEntry = iconData != null ? iconData.getEntry() : null;
        setupIconAndBadges(capsuleNotificationInnerData);
        setupTitleAndContent(capsuleNotificationInnerData);
        int dimensionPixelSize = getResources().getDimensionPixelSize(2131165927);
        int dimensionPixelSize2 = (getResources().getDimensionPixelSize(2131165913) - (this.isMessageStyle ? getResources().getDimensionPixelSize(2131165910) : getResources().getDimensionPixelSize(2131165912))) / 2;
        if (this.isMessageStyle) {
            getContentLayout().setPaddingRelative(getResources().getDimensionPixelSize(2131165918), getContentLayout().getPaddingTop(), getContentLayout().getPaddingEnd(), getContentLayout().getPaddingBottom());
        }
        FrameLayout frameLayout = this.textContainer;
        ViewGroup.LayoutParams layoutParams = frameLayout != null ? frameLayout.getLayoutParams() : null;
        LinearLayout.LayoutParams layoutParams2 = layoutParams instanceof LinearLayout.LayoutParams ? (LinearLayout.LayoutParams) layoutParams : null;
        if (layoutParams2 == null) {
            return;
        }
        layoutParams2.setMarginStart(dimensionPixelSize - dimensionPixelSize2);
        FrameLayout frameLayout2 = this.textContainer;
        if (frameLayout2 != null) {
            frameLayout2.setLayoutParams(layoutParams2);
        }
    }

    public final void bindDataController(CapsuleNotificationDataController capsuleNotificationDataController) {
        this.controller = capsuleNotificationDataController;
    }

    public final void clearAnimBlur() {
        getTitleView().setRenderEffect(null);
        getTextView().setRenderEffect(null);
        FrameLayout frameLayout = this.numViewContainer;
        if (frameLayout != null) {
            frameLayout.setRenderEffect(null);
        }
    }

    public final CachingIconView getBottomRightBadge() {
        CachingIconView cachingIconView = this.bottomRightBadge;
        if (cachingIconView != null) {
            return cachingIconView;
        }
        return null;
    }

    public final CapsuleNotificationDataController.CardType getCardType() {
        return this.cardType;
    }

    public final LinearLayout getContentLayout() {
        LinearLayout linearLayout = this.contentLayout;
        if (linearLayout != null) {
            return linearLayout;
        }
        return null;
    }

    public TransformState getCurrentState(int i) {
        return this.mTransformationHelper.getCurrentState(i);
    }

    public final CachingIconView getIcon() {
        CachingIconView cachingIconView = this.icon;
        if (cachingIconView != null) {
            return cachingIconView;
        }
        return null;
    }

    public final FrameLayout getIconTouchArea() {
        FrameLayout frameLayout = this.iconTouchArea;
        if (frameLayout != null) {
            return frameLayout;
        }
        return null;
    }

    public final CapsuleNotificationDataController.CapsuleNotificationInnerData getInnerData() {
        return this.currentData;
    }

    public final ViewTransformationHelper getMTransformationHelper() {
        return this.mTransformationHelper;
    }

    public final TextView getNumView() {
        return this.numView;
    }

    public final FrameLayout getNumViewContainer() {
        return this.numViewContainer;
    }

    public final Drawable getRoundedIcon(CapsuleNotificationDataController.NotificationIconData notificationIconData) {
        Drawable icon;
        if (notificationIconData == null || (icon = notificationIconData.getIcon()) == null) {
            return null;
        }
        if (notificationIconData.isCloned) {
            return icon;
        }
        int dimensionPixelSize = getContext().getResources().getDimensionPixelSize(2131165912);
        return RoundRectDrawableUtil.getRoundRectDrawable(icon, getContext().getResources().getDimensionPixelSize(2131165911) / dimensionPixelSize, dimensionPixelSize, dimensionPixelSize, getContext());
    }

    public final TextView getTextView() {
        TextView textView = this.textView;
        if (textView != null) {
            return textView;
        }
        return null;
    }

    public final TextView getTitleView() {
        TextView textView = this.titleView;
        if (textView != null) {
            return textView;
        }
        return null;
    }

    public final void initViews() {
        setContentLayout((LinearLayout) findViewById(2131362469));
        setIcon((CachingIconView) findViewById(2131363499));
        setIconTouchArea((FrameLayout) findViewById(2131363500));
        setTitleView((TextView) findViewById(2131365800));
        setTextView((TextView) findViewById(2131365799));
        this.numView = (TextView) findViewById(2131364415);
        this.numViewContainer = (FrameLayout) findViewById(2131364294);
        this.textContainer = (FrameLayout) findViewById(2131365627);
        setBottomRightBadge((CachingIconView) findViewById(2131363498));
        getTitleView().setImportantForAccessibility(2);
        getTextView().setImportantForAccessibility(2);
    }

    public final boolean isClickableCard() {
        CapsuleNotificationDataController.CapsuleNotificationInnerData capsuleNotificationInnerData;
        PendingIntent pendingIntent;
        StatusBarNotification sbn;
        NotificationEntry notificationEntry = this.firstEntry;
        PendingIntent pendingIntent2 = null;
        Notification notification = (notificationEntry == null || (sbn = notificationEntry.getSbn()) == null) ? null : sbn.getNotification();
        if (notification != null && (pendingIntent = notification.contentIntent) != null) {
            pendingIntent2 = pendingIntent;
        } else if (notification != null) {
            pendingIntent2 = notification.fullScreenIntent;
        }
        boolean z = pendingIntent2 != null;
        CapsuleNotificationDataController.CardType cardType = this.cardType;
        return (cardType == CapsuleNotificationDataController.CardType.SINGLE || cardType == CapsuleNotificationDataController.CardType.SINGLE_MESSAGE) && z && ((capsuleNotificationInnerData = this.currentData) == null || capsuleNotificationInnerData.getMediaCount() != 1);
    }

    @Override // android.view.View
    public void onInitializeAccessibilityNodeInfo(AccessibilityNodeInfo accessibilityNodeInfo) {
        super.onInitializeAccessibilityNodeInfo(accessibilityNodeInfo);
        accessibilityNodeInfo.setText(((Object) getTitleView().getText()) + "，" + ((Object) getTextView().getText()));
    }

    public final void onSingleCardClick() {
        CapsuleNotificationDataController capsuleNotificationDataController;
        NotificationActivityStarter notificationActivityStarter;
        NotificationEntry notificationEntry = this.firstEntry;
        ExpandableNotificationRow row = notificationEntry != null ? notificationEntry.getRow() : null;
        if (notificationEntry == null || row == null || (capsuleNotificationDataController = this.controller) == null || (notificationActivityStarter = capsuleNotificationDataController.getNotificationActivityStarter()) == null) {
            return;
        }
        notificationActivityStarter.onNotificationClicked(notificationEntry, row);
    }

    public final void reInflateViews() {
        removeAllViews();
        LayoutInflater.from(getContext()).inflate(2131559271, (ViewGroup) this, true);
        initViews();
        CapsuleNotificationDataController.CapsuleNotificationInnerData capsuleNotificationInnerData = this.currentData;
        if (capsuleNotificationInnerData != null) {
            bind(capsuleNotificationInnerData);
        }
    }

    public final void setBottomRightBadge(CachingIconView cachingIconView) {
        this.bottomRightBadge = cachingIconView;
    }

    public final void setContentLayout(LinearLayout linearLayout) {
        this.contentLayout = linearLayout;
    }

    public final void setIcon(CachingIconView cachingIconView) {
        this.icon = cachingIconView;
    }

    public final void setIconTouchArea(FrameLayout frameLayout) {
        this.iconTouchArea = frameLayout;
    }

    public final void setNumView(TextView textView) {
        this.numView = textView;
    }

    public final void setNumViewContainer(FrameLayout frameLayout) {
        this.numViewContainer = frameLayout;
    }

    public final void setTextView(TextView textView) {
        this.textView = textView;
    }

    public final void setTitleView(TextView textView) {
        this.titleView = textView;
    }

    public void setVisible(boolean z) {
        animate().cancel();
        setVisibility(z ? 0 : 4);
        this.mTransformationHelper.setVisible(z);
    }

    public void transformFrom(TransformableView transformableView) {
        this.mTransformationHelper.transformFrom(transformableView);
    }

    public void transformTo(TransformableView transformableView, Runnable runnable) {
        this.mTransformationHelper.transformTo(transformableView, runnable);
    }

    public final void updateColor() {
        CapsuleNotificationDataController.NotificationIconData iconData;
        NotificationEntry entry = this.firstEntry;
        if (entry == null) {
            CapsuleNotificationDataController.CapsuleNotificationInnerData capsuleNotificationInnerData = this.currentData;
            entry = (capsuleNotificationInnerData == null || (iconData = capsuleNotificationInnerData.getIconData()) == null) ? null : iconData.getEntry();
        }
        updateGroupBadgeColor(entry, this.numViewContainer, this.numView, getIcon());
    }

    public final void updateGroupBadgeColor(NotificationEntry notificationEntry, FrameLayout frameLayout, TextView textView, CachingIconView cachingIconView) {
        GroupIconManager groupIconManager;
        Integer badgeCount;
        if (notificationEntry == null || this.cardType != CapsuleNotificationDataController.CardType.GROUP) {
            return;
        }
        CapsuleNotificationDataController.CapsuleNotificationInnerData capsuleNotificationInnerData = this.currentData;
        if (((capsuleNotificationInnerData == null || (badgeCount = capsuleNotificationInnerData.getBadgeCount()) == null) ? 0 : badgeCount.intValue()) > 1) {
            LogUtil.i$default(LogUtil.TAG_NOTIFICATION, TAG, "updateGroupBadgeColor", null, 8);
            CapsuleNotificationDataController capsuleNotificationDataController = this.controller;
            if (capsuleNotificationDataController == null || (groupIconManager = capsuleNotificationDataController.getGroupIconManager()) == null) {
                return;
            }
            groupIconManager.initEntryIconDrawable(notificationEntry, cachingIconView, textView, frameLayout, true, null);
        }
    }

    public final void updateIconforLargeSize(boolean z) {
        if (z) {
            int dimensionPixelSize = getResources().getDimensionPixelSize(2131165910);
            ViewGroup.LayoutParams layoutParams = getIcon().getLayoutParams();
            if (layoutParams.width == dimensionPixelSize && layoutParams.height == dimensionPixelSize) {
                return;
            }
            layoutParams.width = dimensionPixelSize;
            layoutParams.height = dimensionPixelSize;
            getIcon().setLayoutParams(layoutParams);
        }
    }

    public void transformFrom(TransformableView transformableView, float f) {
        this.mTransformationHelper.transformFrom(transformableView, f);
    }

    public void transformTo(TransformableView transformableView, float f) {
        this.mTransformationHelper.transformTo(transformableView, f);
    }
}
