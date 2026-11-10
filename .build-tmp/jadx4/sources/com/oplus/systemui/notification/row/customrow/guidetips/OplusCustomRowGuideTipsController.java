package com.oplus.systemui.notification.row.customrow.guidetips;

import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import com.android.systemui.plugins.custom.CustomRowEntry;
import com.android.systemui.statusbar.notification.row.NotificationBackgroundView;
import com.oplus.keyguard.security.view.OplusKeyguardSimInputViewController$;
import com.oplus.systemui.statusbar.notification.customcard.OplusCustomRow;
import com.oplus.systemui.statusbar.notification.customcard.OplusCustomRowControllerManager;
import com.oplusos.systemui.common.util.LogUtil;
import kotlin.ranges.RangesKt___RangesKt;

/* JADX INFO: compiled from: go/retraceme fd74bf9514dd0739b92d67a6d48a51eb10aaf8df760d83496b8dcfcf83e5f698 */
/* JADX INFO: loaded from: /home/starlight/Projects/Starlight Ports/.build-tmp/classes4.dex */
public final class OplusCustomRowGuideTipsController {
    public static final Companion Companion = new Companion();
    private static final String TAG = "OplusCustomRowGuideTipsController";
    private final OplusCustomRowControllerManager customManager;
    private CustomCardGuideTipsView guideTipsView;
    private final OplusCustomRow row;

    /* JADX INFO: compiled from: go/retraceme fd74bf9514dd0739b92d67a6d48a51eb10aaf8df760d83496b8dcfcf83e5f698 */
    public final class Companion {
    }

    public OplusCustomRowGuideTipsController(OplusCustomRow oplusCustomRow, OplusCustomRowControllerManager oplusCustomRowControllerManager) {
        this.row = oplusCustomRow;
        this.customManager = oplusCustomRowControllerManager;
    }

    public final int computeContentIntrinsicHeight(int i) {
        CustomCardGuideTipsView customCardGuideTipsView = this.guideTipsView;
        return i + (customCardGuideTipsView != null ? customCardGuideTipsView.getTipsTextViewHeight() : 0);
    }

    public final CustomCardGuideTipsView getGuideTipsView() {
        return this.guideTipsView;
    }

    public final CustomCardGuideTipsView getOrInflateGuideTipsView() {
        CustomCardGuideTipsView customCardGuideTipsView = this.guideTipsView;
        if (customCardGuideTipsView != null) {
            return customCardGuideTipsView;
        }
        View viewInflate = LayoutInflater.from(this.row.getContext()).inflate(2131559210, (ViewGroup) this.row, false);
        CustomCardGuideTipsView customCardGuideTipsView2 = viewInflate instanceof CustomCardGuideTipsView ? (CustomCardGuideTipsView) viewInflate : null;
        if (customCardGuideTipsView2 != null) {
            this.row.attachGuideTipsViewForController(customCardGuideTipsView2);
            this.guideTipsView = customCardGuideTipsView2;
        }
        return this.guideTipsView;
    }

    public final void hideCustomCardTips() {
        View contentLayout;
        if (LogUtil.getNormalDebug() && LogUtil.isTagValid(LogUtil.TAG_NOTIFICATION)) {
            CustomRowEntry entry = this.row.getEntry();
            String key = entry != null ? entry.getKey() : null;
            CustomCardGuideTipsView customCardGuideTipsView = this.guideTipsView;
            Log.d("SystemUi--Notification", "OplusCustomRowGuideTipsController-->".concat("hideCustomCardTips: key=" + key + ", vis=" + (customCardGuideTipsView != null ? Integer.valueOf(customCardGuideTipsView.getVisibility()) : null)));
        }
        CustomCardGuideTipsView customCardGuideTipsView2 = this.guideTipsView;
        if (customCardGuideTipsView2 == null) {
            return;
        }
        if (customCardGuideTipsView2.getVisibility() != 0) {
            customCardGuideTipsView2.tipsHeightAnimating = false;
            customCardGuideTipsView2.tipsCollapsing = false;
            customCardGuideTipsView2.clearBlur();
            if (LogUtil.getNormalDebug() && LogUtil.isTagValid(LogUtil.TAG_NOTIFICATION)) {
                Log.d("SystemUi--Notification", "CustomCardGuideTipsView-->finishHideTips: tipsView GONE");
            }
            customCardGuideTipsView2.setVisibility(8);
            onTipsHidden();
            return;
        }
        if (customCardGuideTipsView2.getVisibility() != 0) {
            customCardGuideTipsView2.resetTipsHeightAnimationState(true);
        } else {
            OplusCustomRow oplusCustomRow = customCardGuideTipsView2.oplusCustomRow();
            if (oplusCustomRow != null) {
                int actualHeight = oplusCustomRow.getActualHeight();
                OplusCustomRow oplusCustomRow2 = customCardGuideTipsView2.oplusCustomRow();
                if (oplusCustomRow2 != null && (contentLayout = oplusCustomRow2.getContentLayout()) != null) {
                    int height = contentLayout.getHeight();
                    if (LogUtil.getNormalDebug() && LogUtil.isTagValid(LogUtil.TAG_NOTIFICATION)) {
                        Log.d("SystemUi--Notification", OplusKeyguardSimInputViewController$.ExternalSyntheticOutline0.m("hideTips: heightBefore=", actualHeight, height, ", heightAfter=", "CustomCardGuideTipsView-->"));
                    }
                    if (actualHeight == height) {
                        customCardGuideTipsView2.resetTipsHeightAnimationState(true);
                    } else {
                        customCardGuideTipsView2.tipsHeightAnimating = true;
                        customCardGuideTipsView2.tipsCollapsing = true;
                        customCardGuideTipsView2.blurStartHeight = actualHeight;
                        customCardGuideTipsView2.blurEndHeight = height;
                        customCardGuideTipsView2.blurExpanding = false;
                        customCardGuideTipsView2.applyBlurRadius(0.0f);
                    }
                }
            }
        }
        this.row.notifyHeightChanged("OCR: hideCustomCardTips", true);
        this.customManager.requestAnimationOnViewResizeCustom();
    }

    public final boolean isGuideTipsChild(View view) {
        return view == this.guideTipsView;
    }

    public final boolean isTipsViewVisible() {
        CustomCardGuideTipsView customCardGuideTipsView = this.guideTipsView;
        return customCardGuideTipsView != null && customCardGuideTipsView.getVisibility() == 0;
    }

    public final void onMeasureAfterSuper(NotificationBackgroundView notificationBackgroundView) {
        CustomCardGuideTipsView customCardGuideTipsView = this.guideTipsView;
        if (customCardGuideTipsView == null || customCardGuideTipsView.getVisibility() != 0) {
            return;
        }
        CustomCardGuideTipsView customCardGuideTipsView2 = this.guideTipsView;
        int tipsTextViewHeight = customCardGuideTipsView2 != null ? customCardGuideTipsView2.getTipsTextViewHeight() : 0;
        if (tipsTextViewHeight <= 0) {
            return;
        }
        int measuredHeight = this.row.getMeasuredHeight() + tipsTextViewHeight;
        this.row.setMeasuredHeightForGuideTips(measuredHeight);
        if (notificationBackgroundView != null) {
            notificationBackgroundView.measure(View.MeasureSpec.makeMeasureSpec(this.row.getMeasuredWidth(), 1073741824), View.MeasureSpec.makeMeasureSpec(measuredHeight, 1073741824));
        }
        CustomCardGuideTipsView customCardGuideTipsView3 = this.guideTipsView;
        if (customCardGuideTipsView3 != null) {
            customCardGuideTipsView3.measure(View.MeasureSpec.makeMeasureSpec(this.row.getMeasuredWidth(), 1073741824), View.MeasureSpec.makeMeasureSpec(measuredHeight, 1073741824));
        }
    }

    public final void onTipsHidden() {
        CustomCardGuideTipsView customCardGuideTipsView = this.guideTipsView;
        if (customCardGuideTipsView != null && customCardGuideTipsView.getParent() != null) {
            if (LogUtil.getNormalDebug() && LogUtil.isTagValid(LogUtil.TAG_NOTIFICATION)) {
                Log.d("SystemUi--Notification", "OplusCustomRowGuideTipsController-->onTipsHidden: remove tips");
            }
            this.row.removeView(customCardGuideTipsView);
            this.guideTipsView = null;
        }
        int height = this.row.getContentLayout().getHeight();
        if (this.row.getActualHeight() == height || height <= 0) {
            return;
        }
        this.row.setActualHeight(height, true);
    }

    public final void setActualHeight(int i, int i2) {
        CustomCardGuideTipsView customCardGuideTipsView = this.guideTipsView;
        if (customCardGuideTipsView == null || customCardGuideTipsView.getVisibility() == 8 || !customCardGuideTipsView.tipsHeightAnimating) {
            return;
        }
        int i3 = customCardGuideTipsView.blurEndHeight;
        int i4 = customCardGuideTipsView.blurStartHeight;
        int i5 = i3 - i4;
        if (i5 != 0) {
            float fCoerceIn = RangesKt___RangesKt.coerceIn((i - i4) / i5, 0.0f, 1.0f);
            customCardGuideTipsView.applyBlurRadius(customCardGuideTipsView.blurExpanding ? (1.0f - fCoerceIn) * 150.0f : fCoerceIn * 150.0f);
        }
        if (i == i2) {
            customCardGuideTipsView.resetTipsHeightAnimationState(false);
        }
    }

    public final void setActualHeightAnimating(boolean z) {
        CustomCardGuideTipsView customCardGuideTipsView;
        if (z || (customCardGuideTipsView = this.guideTipsView) == null || !customCardGuideTipsView.tipsHeightAnimating) {
            return;
        }
        customCardGuideTipsView.resetTipsHeightAnimationState(false);
    }

    public final boolean shouldSyncActualHeightFromLayout() {
        CustomCardGuideTipsView customCardGuideTipsView = this.guideTipsView;
        return (customCardGuideTipsView != null && customCardGuideTipsView.getVisibility() == 0 && customCardGuideTipsView.getTipsHeightAnimating()) ? false : true;
    }

    public final boolean shouldUseGuideTipsIntrinsicHeight() {
        CustomCardGuideTipsView customCardGuideTipsView = this.guideTipsView;
        return (customCardGuideTipsView == null || customCardGuideTipsView.getVisibility() != 0 || customCardGuideTipsView.getTipsCollapsing()) ? false : true;
    }

    public final void showCustomCardTips() {
        if (LogUtil.getNormalDebug() && LogUtil.isTagValid(LogUtil.TAG_NOTIFICATION)) {
            CustomRowEntry entry = this.row.getEntry();
            String key = entry != null ? entry.getKey() : null;
            CustomCardGuideTipsView customCardGuideTipsView = this.guideTipsView;
            Log.d("SystemUi--Notification", "OplusCustomRowGuideTipsController-->".concat("showCustomCardTips: key=" + key + ", vis=" + (customCardGuideTipsView != null ? Integer.valueOf(customCardGuideTipsView.getVisibility()) : null)));
        }
        CustomCardGuideTipsView customCardGuideTipsView2 = this.guideTipsView;
        if (customCardGuideTipsView2 == null || customCardGuideTipsView2.getVisibility() == 0) {
            return;
        }
        customCardGuideTipsView2.showTips();
        this.row.notifyHeightChanged("OCR: showCustomCardTips", true);
        this.customManager.requestAnimationOnViewResizeCustom();
    }
}
