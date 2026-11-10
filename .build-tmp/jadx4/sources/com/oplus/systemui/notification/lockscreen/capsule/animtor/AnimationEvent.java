package com.oplus.systemui.notification.lockscreen.capsule.animtor;

import androidx.compose.animation.ChangeSize$;
import androidx.compose.animation.core.MonoSpline$;
import androidx.fragment.app.Fragment$5$;
import com.android.systemui.statusbar.notification.row.ExpandableView;
import com.android.systemui.statusbar.notification.stack.AnimationFilter;
import com.oplus.systemui.notification.lockscreen.capsule.CapsuleItemView;
import java.util.Iterator;
import java.util.List;
import kotlin.jvm.internal.Intrinsics;
import kotlin.text.Regex$;

/* JADX INFO: compiled from: go/retraceme fd74bf9514dd0739b92d67a6d48a51eb10aaf8df760d83496b8dcfcf83e5f698 */
/* JADX INFO: loaded from: /home/starlight/Projects/Starlight Ports/.build-tmp/classes4.dex */
public final class AnimationEvent {
    public static final String COMMON_ANIMATION_BOTTOM_CHANGED = "bottomChanged";
    public static final String COMMON_ANIMATION_CONTENT_WIDTH_CHANGED = "contentWidthChanged";
    public static final String COMMON_ANIMATION_TOUCH_BACK = "touchBack";
    public static final Companion Companion = new Companion();
    public final int animationType;
    private String commonTag;
    private final AnimationFilter filter;
    public final long length;
    private final CapsuleItemView otherView;
    private ExpandableView referNotificationView;
    private final CapsuleItemView view;

    /* JADX INFO: compiled from: go/retraceme fd74bf9514dd0739b92d67a6d48a51eb10aaf8df760d83496b8dcfcf83e5f698 */
    public final class Companion {
        public final long combineLength(List<AnimationEvent> list) {
            if (list.isEmpty()) {
                return 384L;
            }
            Iterator<T> it = list.iterator();
            if (!it.hasNext()) {
                Regex$.ExternalSyntheticBUOutline0.m();
                return 0L;
            }
            long length = ((AnimationEvent) it.next()).getLength();
            while (it.hasNext()) {
                long length2 = ((AnimationEvent) it.next()).getLength();
                if (length < length2) {
                    length = length2;
                }
            }
            return length;
        }
    }

    public /* synthetic */ AnimationEvent(CapsuleItemView capsuleItemView, int i, long j, OplusAnimationFilter oplusAnimationFilter, CapsuleItemView capsuleItemView2, ExpandableView expandableView, int i2) {
        this(capsuleItemView, i, j, oplusAnimationFilter, capsuleItemView2, (i2 & 32) != 0 ? null : expandableView, (String) null);
    }

    public final CapsuleItemView component1() {
        return this.view;
    }

    public final AnimationFilter component4() {
        return this.filter;
    }

    public final CapsuleItemView component5() {
        return this.otherView;
    }

    public final ExpandableView component6() {
        return this.referNotificationView;
    }

    public final String component7() {
        return this.commonTag;
    }

    public final AnimationEvent copy(CapsuleItemView capsuleItemView, int i, long j, AnimationFilter animationFilter, CapsuleItemView capsuleItemView2, ExpandableView expandableView, String str) {
        return new AnimationEvent(capsuleItemView, i, j, animationFilter, capsuleItemView2, expandableView, str);
    }

    public boolean equals(Object obj) {
        if (this == obj) {
            return true;
        }
        if (!(obj instanceof AnimationEvent)) {
            return false;
        }
        AnimationEvent animationEvent = (AnimationEvent) obj;
        return Intrinsics.areEqual(this.view, animationEvent.view) && this.animationType == animationEvent.animationType && this.length == animationEvent.length && Intrinsics.areEqual(this.filter, animationEvent.filter) && Intrinsics.areEqual(this.otherView, animationEvent.otherView) && Intrinsics.areEqual(this.referNotificationView, animationEvent.referNotificationView) && Intrinsics.areEqual(this.commonTag, animationEvent.commonTag);
    }

    public final int getAnimationType() {
        return this.animationType;
    }

    public final String getCommonTag() {
        return this.commonTag;
    }

    public final AnimationFilter getFilter() {
        return this.filter;
    }

    public final long getLength() {
        return this.length;
    }

    public final CapsuleItemView getOtherView() {
        return this.otherView;
    }

    public final ExpandableView getReferNotificationView() {
        return this.referNotificationView;
    }

    public final CapsuleItemView getView() {
        return this.view;
    }

    public final int hashCode() {
        ExpandableView expandableView = this.view;
        int iHashCode = (this.filter.hashCode() + ChangeSize$.ExternalSyntheticOutline0.m(MonoSpline$.ExternalSyntheticOutline0.m(this.animationType, (expandableView == null ? 0 : expandableView.hashCode()) * 31, 31), 31, this.length)) * 31;
        ExpandableView expandableView2 = this.otherView;
        int iHashCode2 = (iHashCode + (expandableView2 == null ? 0 : expandableView2.hashCode())) * 31;
        ExpandableView expandableView3 = this.referNotificationView;
        int iHashCode3 = (iHashCode2 + (expandableView3 == null ? 0 : expandableView3.hashCode())) * 31;
        String str = this.commonTag;
        return iHashCode3 + (str != null ? str.hashCode() : 0);
    }

    public final void setCommonTag(String str) {
        this.commonTag = str;
    }

    public final void setReferNotificationView(ExpandableView expandableView) {
        this.referNotificationView = expandableView;
    }

    public String toString() {
        CapsuleItemView capsuleItemView = this.view;
        AnimationFilter animationFilter = this.filter;
        CapsuleItemView capsuleItemView2 = this.otherView;
        ExpandableView expandableView = this.referNotificationView;
        String str = this.commonTag;
        StringBuilder sb = new StringBuilder("AnimationEvent(view=");
        sb.append(capsuleItemView);
        sb.append(", animationType=");
        sb.append(this.animationType);
        sb.append(", length=");
        sb.append(this.length);
        sb.append(", filter=");
        sb.append(animationFilter);
        sb.append(", otherView=");
        sb.append(capsuleItemView2);
        sb.append(", referNotificationView=");
        sb.append(expandableView);
        return Fragment$5$.ExternalSyntheticOutline0.m(", commonTag=", str, ")", sb);
    }

    public AnimationEvent(CapsuleItemView capsuleItemView, int i, long j, AnimationFilter animationFilter, CapsuleItemView capsuleItemView2, ExpandableView expandableView, String str) {
        this.view = capsuleItemView;
        this.animationType = i;
        this.length = j;
        this.filter = animationFilter;
        this.otherView = capsuleItemView2;
        this.referNotificationView = expandableView;
        this.commonTag = str;
    }
}
