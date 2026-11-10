package com.oplus.systemui.notification.lockscreen.capsule;

import android.graphics.Rect;
import android.util.Log;
import android.view.ViewGroup;
import android.widget.FrameLayout;
import androidx.collection.FloatList$;
import androidx.compose.ui.spatial.RectList$;
import androidx.fragment.app.Fragment$5$;
import com.android.systemui.statusbar.notification.stack.ExpandableViewState;
import com.android.systemui.statusbar.policy.FlashlightControllerImpl$2$;
import com.oplus.systemui.notification.lockscreen.config.CapsuleConfig;
import com.oplusos.systemui.common.util.LogUtil;

/* JADX INFO: compiled from: go/retraceme fd74bf9514dd0739b92d67a6d48a51eb10aaf8df760d83496b8dcfcf83e5f698 */
/* JADX INFO: loaded from: /home/starlight/Projects/Starlight Ports/.build-tmp/classes4.dex */
public final class CapsuleItemLocation {
    public static final Companion Companion = new Companion();
    private static final String TAG = "CapsuleItemLocation";
    public int actualHeight;
    public int actualWidth;
    public int clipBottom;
    public int clipLeft;
    public int clipRight;
    public int clipTop;
    public int contentHeight;
    public float contentScale;
    public int contentWidth;
    private final CapsuleItemView view;

    /* JADX INFO: compiled from: go/retraceme fd74bf9514dd0739b92d67a6d48a51eb10aaf8df760d83496b8dcfcf83e5f698 */
    public final class Companion {
    }

    public CapsuleItemLocation(CapsuleItemView capsuleItemView) {
        this.view = capsuleItemView;
        CapsuleConfig capsuleConfig = CapsuleConfig.INSTANCE;
        this.contentWidth = capsuleConfig.getItemWidth();
        this.contentHeight = capsuleItemView.getIntrinsicHeight();
        this.actualWidth = capsuleConfig.getItemWidth();
        this.actualHeight = capsuleConfig.getItemHeight();
        this.contentScale = 1.0f;
    }

    public final int getActualHeight() {
        return this.actualHeight;
    }

    public final int getActualWidth() {
        return this.actualWidth;
    }

    public final int getClipBottom() {
        return this.clipBottom;
    }

    public final int getClipLeft() {
        return this.clipLeft;
    }

    public final Rect getClipRect() {
        float f = this.actualWidth;
        float f2 = this.contentScale;
        int i = (int) (((1.0f - f2) * f) / 2.0f);
        int i2 = (int) (((1.0f - f2) * this.actualHeight) / 2.0f);
        int iMax = Math.max(this.clipTop, i2);
        int iMax2 = this.actualHeight - Math.max(this.clipBottom, i2);
        int width = this.view.getWidth();
        return new Rect(Math.max(this.clipLeft, i) + ((width - this.actualWidth) / 2), iMax, ((width + this.actualWidth) / 2) - Math.max(this.clipRight, i), iMax2);
    }

    public final int getClipRight() {
        return this.clipRight;
    }

    public final int getClipTop() {
        return this.clipTop;
    }

    public final int getContentHeight() {
        return this.contentHeight;
    }

    public final float getContentScale() {
        return this.contentScale;
    }

    public final int getContentWidth() {
        return this.contentWidth;
    }

    public final Rect getLocationWithoutClip() {
        int i = this.actualHeight;
        int width = this.view.getWidth();
        int i2 = this.actualWidth;
        return new Rect((width - i2) / 2, 0, (width + i2) / 2, i);
    }

    public final CapsuleItemView getView() {
        return this.view;
    }

    public final void loadScrollClipInfo(CapsuleViewState.ScrollClipInfo scrollClipInfo) {
        this.clipLeft = scrollClipInfo.getClipLeft();
        this.clipRight = scrollClipInfo.getClipRight();
        this.clipTop = scrollClipInfo.getClipTop();
        this.clipBottom = scrollClipInfo.getClipBottom();
    }

    public final void loadViewStateClipInfo(CapsuleViewState capsuleViewState) {
        this.clipLeft = ((ExpandableViewState) capsuleViewState).mExpandableViewStateEx.getClipWidth() / 2;
        this.clipRight = ((ExpandableViewState) capsuleViewState).mExpandableViewStateEx.getClipWidth() / 2;
        this.clipTop = ((ExpandableViewState) capsuleViewState).clipTopAmount;
        this.clipBottom = ((ExpandableViewState) capsuleViewState).clipBottomAmount;
    }

    public final void setActualHeight(int i) {
        this.actualHeight = i;
    }

    public final void setActualWidth(int i) {
        this.actualWidth = i;
    }

    public final void setClipBottom(int i) {
        this.clipBottom = i;
    }

    public final void setClipLeft(int i) {
        this.clipLeft = i;
    }

    public final void setClipRight(int i) {
        this.clipRight = i;
    }

    public final void setClipTop(int i) {
        this.clipTop = i;
    }

    public final void setContentHeight(int i) {
        if (this.contentHeight != i) {
            this.contentHeight = i;
            FrameLayout contentContainer = this.view.getContentContainer();
            ViewGroup.LayoutParams layoutParams = contentContainer.getLayoutParams();
            layoutParams.height = i;
            contentContainer.setLayoutParams(layoutParams);
        }
    }

    public final void setContentScale(float f) {
        if (this.contentScale == f) {
            return;
        }
        this.contentScale = f;
        if ((f == 1.0f || f == this.view.getViewState().getContentScale()) && LogUtil.getNormalDebug() && LogUtil.isTagValid(LogUtil.TAG_NOTIFICATION)) {
            Log.d("SystemUi--Notification", FlashlightControllerImpl$2$.ExternalSyntheticOutline0.m("view: contentScale:", f, " ,", this.view.getAlpha(), "CapsuleItemLocation-->"));
        }
        this.view.updateContentScale();
    }

    public final void setContentWidth(int i) {
        if (this.contentWidth != i) {
            this.contentWidth = i;
            FrameLayout contentContainer = this.view.getContentContainer();
            ViewGroup.LayoutParams layoutParams = contentContainer.getLayoutParams();
            layoutParams.width = i;
            contentContainer.setLayoutParams(layoutParams);
        }
    }

    public String toString() {
        int i = this.clipLeft;
        int i2 = this.clipRight;
        int i3 = this.clipTop;
        int i4 = this.clipBottom;
        int i5 = this.actualWidth;
        int i6 = this.actualHeight;
        int i7 = this.contentWidth;
        int i8 = this.contentHeight;
        StringBuilder sbM = FloatList$.ExternalSyntheticOutline0.m("clipLeft:", i, i2, ", clipRight:", ", clipTop:");
        RectList$.ExternalSyntheticOutline0.m(i3, i4, ", clipBottom:", ", actualWidth:", sbM);
        RectList$.ExternalSyntheticOutline0.m(i5, i6, ",actualHeight:", ", contentWidth:", sbM);
        return Fragment$5$.ExternalSyntheticOutline0.m(sbM, i7, i8, ", contentHeight:");
    }
}
