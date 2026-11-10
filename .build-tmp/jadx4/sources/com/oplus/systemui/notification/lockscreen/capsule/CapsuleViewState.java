package com.oplus.systemui.notification.lockscreen.capsule;

import android.view.View;
import androidx.car.app.model.TimerSpan$;
import androidx.collection.FloatList$;
import androidx.compose.animation.core.MonoSpline$;
import androidx.compose.material3.internal.TextFieldImplKt$;
import androidx.compose.ui.spatial.RectList$;
import androidx.recyclerview.widget.ChildHelper$;
import com.android.launcher3.icons.IconProvider$;
import com.android.systemui.statusbar.notification.AnimatableProperty;
import com.android.systemui.statusbar.notification.row.ExpandableView;
import com.android.systemui.statusbar.notification.stack.AnimationProperties;
import com.android.systemui.statusbar.notification.stack.ExpandableViewState;
import com.android.systemui.statusbar.notification.stack.OplusExpandableViewStateEx;
import com.android.systemui.statusbar.notification.stack.ViewState;
import com.oplus.systemui.notification.NotificationExtFlag;
import kotlin.jvm.internal.Intrinsics;

/* JADX INFO: compiled from: go/retraceme fd74bf9514dd0739b92d67a6d48a51eb10aaf8df760d83496b8dcfcf83e5f698 */
/* JADX INFO: loaded from: /home/starlight/Projects/Starlight Ports/.build-tmp/classes4.dex */
public final class CapsuleViewState extends ExpandableViewState {
    private static final String TAG = "CapsuleViewState";
    public float contentScale = 1.0f;
    public int contentWidth;
    public int finalWidth;
    private ScrollClipInfo scrollClipInfo;
    public int width;
    public static final Companion Companion = new Companion();
    private static final AnimatableProperty WIDTH_PROPERTY = AnimatableProperty.from("width_anim", CapsuleViewState$Companion$WIDTH_PROPERTY$1.INSTANCE, CapsuleViewState$Companion$WIDTH_PROPERTY$2.INSTANCE, 2131366070, 2131366069, 2131366068);
    private static final AnimatableProperty CONTENT_WIDTH_PROPERTY = AnimatableProperty.from("content_width_anim", CapsuleViewState$Companion$CONTENT_WIDTH_PROPERTY$1.INSTANCE, CapsuleViewState$Companion$CONTENT_WIDTH_PROPERTY$2.INSTANCE, 2131362650, 2131362649, 2131362648);
    private static final AnimatableProperty CONTENT_SCALE_PROPERTY = AnimatableProperty.from("content_scale_anim", CapsuleViewState$Companion$CONTENT_SCALE_PROPERTY$1.INSTANCE, CapsuleViewState$Companion$CONTENT_SCALE_PROPERTY$2.INSTANCE, 2131362645, 2131362644, 2131362643);

    /* JADX INFO: compiled from: go/retraceme fd74bf9514dd0739b92d67a6d48a51eb10aaf8df760d83496b8dcfcf83e5f698 */
    public final class Companion {
        public final AnimatableProperty getCONTENT_SCALE_PROPERTY() {
            return CapsuleViewState.CONTENT_SCALE_PROPERTY;
        }

        public final AnimatableProperty getCONTENT_WIDTH_PROPERTY() {
            return CapsuleViewState.CONTENT_WIDTH_PROPERTY;
        }

        public final AnimatableProperty getWIDTH_PROPERTY() {
            return CapsuleViewState.WIDTH_PROPERTY;
        }
    }

    public CapsuleViewState() {
        int i = 0;
        this.scrollClipInfo = new ScrollClipInfo(i, i, 15);
    }

    /* JADX WARN: Multi-variable type inference failed */
    public void animateTo(View view, AnimationProperties animationProperties) {
        if ((view instanceof CapsuleItemView) && ((CapsuleItemView) view)._expandAnimationRunning) {
            return;
        }
        super.animateTo(view, animationProperties);
        if (view instanceof CapsuleItemView) {
            ExpandableView expandableView = (ExpandableView) view;
            CapsuleItemView capsuleItemView = (CapsuleItemView) view;
            OplusExpandableViewStateEx.animatePropertyIfChanged$default(((ExpandableViewState) this).mExpandableViewStateEx, expandableView, animationProperties, Float.valueOf(capsuleItemView.getActualWidth$vendor__oplus__packages__apps__systemui__oplus_systemui__android_common__Oplus_SystemUI_application_domestic()), this.width, WIDTH_PROPERTY, NotificationExtFlag.USE_OPLUS_PHYSICAL, 64);
            OplusExpandableViewStateEx.animatePropertyIfChanged$default(((ExpandableViewState) this).mExpandableViewStateEx, expandableView, animationProperties, Float.valueOf(capsuleItemView.getContentWidth()), this.contentWidth, CONTENT_WIDTH_PROPERTY, false, 64);
            OplusExpandableViewStateEx.animatePropertyIfChanged$default(((ExpandableViewState) this).mExpandableViewStateEx, expandableView, animationProperties, Float.valueOf(capsuleItemView.getContentScale()), this.contentScale, CONTENT_SCALE_PROPERTY, false, 64);
            if (Intrinsics.areEqual(this.scrollClipInfo, capsuleItemView.getScrollClipInfo$vendor__oplus__packages__apps__systemui__oplus_systemui__android_common__Oplus_SystemUI_application_domestic())) {
                return;
            }
            capsuleItemView.setScrollClipInfo$vendor__oplus__packages__apps__systemui__oplus_systemui__android_common__Oplus_SystemUI_application_domestic(this.scrollClipInfo);
        }
    }

    /* JADX WARN: Multi-variable type inference failed */
    public void applyToView(View view) {
        boolean z = view instanceof CapsuleItemView;
        if (z && ((CapsuleItemView) view)._expandAnimationRunning) {
            return;
        }
        super.applyToView(view);
        if (z) {
            CapsuleItemView capsuleItemView = (CapsuleItemView) view;
            capsuleItemView.setActualWidth$vendor__oplus__packages__apps__systemui__oplus_systemui__android_common__Oplus_SystemUI_application_domestic(this.width);
            if (((ExpandableViewState) this).clipBottomAmount != capsuleItemView.getClipBottomAmount()) {
                capsuleItemView.setClipBottomAmount(((ExpandableViewState) this).clipBottomAmount);
            }
            if (this.contentWidth != capsuleItemView.getContentWidth()) {
                capsuleItemView.setContentWidth(this.contentWidth);
            }
            if (this.contentScale != capsuleItemView.getContentScale()) {
                capsuleItemView.setContentScale(this.contentScale);
            }
            if (!Intrinsics.areEqual(this.scrollClipInfo, capsuleItemView.getScrollClipInfo$vendor__oplus__packages__apps__systemui__oplus_systemui__android_common__Oplus_SystemUI_application_domestic())) {
                capsuleItemView.setScrollClipInfo$vendor__oplus__packages__apps__systemui__oplus_systemui__android_common__Oplus_SystemUI_application_domestic(this.scrollClipInfo);
            }
            capsuleItemView.invalidate();
        }
    }

    public void cancelAnimations(View view) {
        super.cancelAnimations(view);
        ViewState.abortAnimation(view, WIDTH_PROPERTY.getAnimatorTag());
        ViewState.abortAnimation(view, CONTENT_WIDTH_PROPERTY.getAnimatorTag());
        ViewState.abortAnimation(view, CONTENT_SCALE_PROPERTY.getAnimatorTag());
    }

    public void copyFrom(ViewState viewState) {
        super.copyFrom(viewState);
        CapsuleViewState capsuleViewState = viewState instanceof CapsuleViewState ? (CapsuleViewState) viewState : null;
        if (capsuleViewState != null) {
            this.width = capsuleViewState.width;
            this.contentWidth = capsuleViewState.contentWidth;
            this.contentScale = capsuleViewState.contentScale;
            this.scrollClipInfo = ScrollClipInfo.copy$default(capsuleViewState.scrollClipInfo, 0, 0, 15);
        }
    }

    public final float getContentScale() {
        return this.contentScale;
    }

    public final int getContentWidth() {
        return this.contentWidth;
    }

    public final int getFinalWidth() {
        return this.finalWidth;
    }

    public final ScrollClipInfo getScrollClipInfo() {
        return this.scrollClipInfo;
    }

    public final int getWidth() {
        return this.width;
    }

    public final void setContentScale(float f) {
        this.contentScale = f;
    }

    public final void setContentWidth(int i) {
        this.contentWidth = i;
    }

    public final void setFinalWidth(int i) {
        this.finalWidth = i;
    }

    public final void setScrollClipInfo(ScrollClipInfo scrollClipInfo) {
        this.scrollClipInfo = scrollClipInfo;
    }

    public final void setWidth(int i) {
        this.width = i;
    }

    public String toString() {
        boolean z = ((ViewState) this).gone;
        boolean z2 = ((ViewState) this).hidden;
        float alpha = getAlpha();
        int i = this.width;
        int i2 = ((ExpandableViewState) this).height;
        int i3 = this.contentWidth;
        float f = this.contentScale;
        int clipWidth = (i - ((ExpandableViewState) this).mExpandableViewStateEx.getClipWidth()) - this.scrollClipInfo.getHorizontalClipTotal();
        int verticalClipTotal = ((((ExpandableViewState) this).height - ((ExpandableViewState) this).clipTopAmount) - ((ExpandableViewState) this).clipBottomAmount) - this.scrollClipInfo.getVerticalClipTotal();
        int i4 = this.finalWidth;
        float xTranslation = getXTranslation();
        float yTranslation = getYTranslation();
        int clipWidth2 = ((ExpandableViewState) this).mExpandableViewStateEx.getClipWidth();
        int i5 = ((ExpandableViewState) this).clipTopAmount;
        int i6 = ((ExpandableViewState) this).clipBottomAmount;
        ScrollClipInfo scrollClipInfo = this.scrollClipInfo;
        StringBuilder sbM = TextFieldImplKt$.ExternalSyntheticOutline0.m("CapsuleViewState{gone:", ", hide:", ", alpha:", z, z2);
        ChildHelper$.ExternalSyntheticOutline0.m(alpha, i, ", width:", ", height:", sbM);
        RectList$.ExternalSyntheticOutline0.m(i2, i3, ", contentWidth:", ", contentScale:", sbM);
        ChildHelper$.ExternalSyntheticOutline0.m(f, clipWidth, ", showWidth: ", ", showHeight:", sbM);
        RectList$.ExternalSyntheticOutline0.m(verticalClipTotal, i4, ", disappearStartWidth:", ", appearEndWidth:", sbM);
        IconProvider$.ExternalSyntheticOutline0.m(xTranslation, i4, ", tranX:", ", tranY:", sbM);
        ChildHelper$.ExternalSyntheticOutline0.m(yTranslation, clipWidth2, ", clipW:", ", clipTop:", sbM);
        RectList$.ExternalSyntheticOutline0.m(i5, i6, ", clipBottom:", ", ", sbM);
        sbM.append(scrollClipInfo);
        sbM.append("}}");
        return sbM.toString();
    }

    /* JADX INFO: compiled from: go/retraceme fd74bf9514dd0739b92d67a6d48a51eb10aaf8df760d83496b8dcfcf83e5f698 */
    public final class ScrollClipInfo {
        public final int clipBottom;
        public final int clipLeft;
        public final int clipRight;
        public final int clipTop;

        public /* synthetic */ ScrollClipInfo(int i, int i2, int i3) {
            this((i3 & 1) != 0 ? 0 : i, (i3 & 2) != 0 ? 0 : i2, 0, 0);
        }

        public static /* synthetic */ ScrollClipInfo copy$default(ScrollClipInfo scrollClipInfo, int i, int i2, int i3) {
            int i4 = scrollClipInfo.clipLeft;
            int i5 = scrollClipInfo.clipRight;
            if ((i3 & 4) != 0) {
                i = scrollClipInfo.clipTop;
            }
            if ((i3 & 8) != 0) {
                i2 = scrollClipInfo.clipBottom;
            }
            return scrollClipInfo.copy(i4, i5, i, i2);
        }

        public final ScrollClipInfo copy(int i, int i2, int i3, int i4) {
            return new ScrollClipInfo(i, i2, i3, i4);
        }

        public boolean equals(Object obj) {
            if (this == obj) {
                return true;
            }
            if (!(obj instanceof ScrollClipInfo)) {
                return false;
            }
            ScrollClipInfo scrollClipInfo = (ScrollClipInfo) obj;
            return this.clipLeft == scrollClipInfo.clipLeft && this.clipRight == scrollClipInfo.clipRight && this.clipTop == scrollClipInfo.clipTop && this.clipBottom == scrollClipInfo.clipBottom;
        }

        public final int getClipBottom() {
            return this.clipBottom;
        }

        public final int getClipLeft() {
            return this.clipLeft;
        }

        public final int getClipRight() {
            return this.clipRight;
        }

        public final int getClipTop() {
            return this.clipTop;
        }

        public final int getHorizontalClipTotal() {
            return this.clipLeft + this.clipRight;
        }

        public final int getVerticalClipTotal() {
            return this.clipTop + this.clipBottom;
        }

        public final int hashCode() {
            return Integer.hashCode(this.clipBottom) + MonoSpline$.ExternalSyntheticOutline0.m(this.clipTop, MonoSpline$.ExternalSyntheticOutline0.m(this.clipRight, Integer.hashCode(this.clipLeft) * 31, 31), 31);
        }

        public String toString() {
            return TimerSpan$.ExternalSyntheticOutline0.m(this.clipTop, this.clipBottom, ", clipBottom=", ")", FloatList$.ExternalSyntheticOutline0.m("ScrollClipInfo(clipLeft=", this.clipLeft, this.clipRight, ", clipRight=", ", clipTop="));
        }

        public ScrollClipInfo(int i, int i2, int i3, int i4) {
            this.clipLeft = i;
            this.clipRight = i2;
            this.clipTop = i3;
            this.clipBottom = i4;
        }
    }
}
