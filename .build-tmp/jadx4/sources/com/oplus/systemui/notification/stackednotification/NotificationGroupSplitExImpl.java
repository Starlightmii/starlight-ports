package com.oplus.systemui.notification.stackednotification;

import android.content.Context;
import android.service.notification.StatusBarNotification;
import android.util.Log;
import android.util.Property;
import android.view.View;
import androidx.collection.ArraySet;
import com.android.systemui.application.impl.SystemUIApplicationImpl$;
import com.android.systemui.notification.stackednotification.NotificationGroupSplitEx;
import com.android.systemui.statusbar.notification.OplusPhysicsInterpolator;
import com.android.systemui.statusbar.notification.OplusPhysicsPropertyAnimator;
import com.android.systemui.statusbar.notification.collection.NotificationEntry;
import com.android.systemui.statusbar.notification.row.ExpandableNotificationRow;
import com.android.systemui.statusbar.notification.row.ExpandableView;
import com.android.systemui.statusbar.notification.stack.AnimationFilter;
import com.android.systemui.statusbar.notification.stack.AnimationProperties;
import com.android.systemui.statusbar.notification.stack.NotificationChildrenContainer;
import com.android.systemui.statusbar.notification.stack.NotificationChildrenContainerOverlayEx;
import com.android.systemui.statusbar.notification.stack.NotificationStackScrollLayout;
import com.android.systemui.statusbar.notification.stack.StackStateAnimator;
import com.oplus.systemui.aod.display.OplusAODRefreshRateController$RequestRefreshRateRunnable$;
import com.oplus.systemui.notification.stackednotification.olive.GroupExpansionAnimParams;
import com.oplus.systemui.statusbar.notification.row.OplusExpandableNotificationRowExImpl;
import com.oplus.systemui.statusbar.notification.stack.OplusExpandableViewStateExImpl;
import com.oplusos.systemui.common.util.LogUtil;
import java.util.ArrayList;
import kotlin.Result;
import kotlin.ResultKt;
import kotlin.Unit;

/* JADX INFO: compiled from: go/retraceme fd74bf9514dd0739b92d67a6d48a51eb10aaf8df760d83496b8dcfcf83e5f698 */
/* JADX INFO: loaded from: /home/starlight/Projects/Starlight Ports/.build-tmp/classes4.dex */
public final class NotificationGroupSplitExImpl extends NotificationGroupSplitEx {
    public static final Companion Companion = new Companion();
    private static final String TAG = "NotificationGroupSplitExImpl";
    private final Context context;
    private NotificationStackScrollLayout notificationStackScrollLayout;
    private StackStateAnimator stackStateAnimator;

    /* JADX INFO: compiled from: go/retraceme fd74bf9514dd0739b92d67a6d48a51eb10aaf8df760d83496b8dcfcf83e5f698 */
    public final class Companion {
    }

    public NotificationGroupSplitExImpl(Context context) {
        this.context = context;
    }

    public void bind(StackStateAnimator stackStateAnimator, NotificationStackScrollLayout notificationStackScrollLayout) {
        this.stackStateAnimator = stackStateAnimator;
        this.notificationStackScrollLayout = notificationStackScrollLayout;
        GroupExpansionAnimParams.INSTANCE.initOlive(this.context);
    }

    public long getExpandGroupMaxDelay(ExpandableView expandableView) {
        StackStateAnimator stackStateAnimator;
        ArrayList animationEvents;
        if (!(expandableView instanceof ExpandableNotificationRow) || (stackStateAnimator = this.stackStateAnimator) == null || (animationEvents = stackStateAnimator.getAnimationEvents()) == null || animationEvents.isEmpty()) {
            return 0L;
        }
        int size = animationEvents.size();
        int i = 0;
        while (i < size) {
            Object obj = animationEvents.get(i);
            i++;
            if (((NotificationStackScrollLayout.AnimationEvent) obj).getAnimationType() == 10) {
                ExpandableNotificationRow expandableNotificationRow = (ExpandableNotificationRow) expandableView;
                if (!expandableNotificationRow.mIsSummaryWithChildren || expandableNotificationRow.getAttachedChildren() == null || !((OplusExpandableNotificationRowExImpl) expandableNotificationRow.mRowEx).isChildrenExpandedAnimating()) {
                    return 0L;
                }
                boolean z = expandableNotificationRow.mChildrenExpanded;
                int size2 = expandableNotificationRow.getAttachedChildren().size();
                GroupExpansionAnimParams.INSTANCE.getClass();
                return (GroupExpansionAnimParams.getChildScaleDelayExtra(z) * ((long) (size2 - 1))) + GroupExpansionAnimParams.getChildScaleDelayBase(z);
            }
        }
        return 0L;
    }

    public AnimationFilter getFilter() {
        AnimationFilter animationFilter = new AnimationFilter();
        animationFilter.animateHeight = true;
        animationFilter.animateTopInset = true;
        animationFilter.animateY = true;
        animationFilter.animateZ = true;
        animationFilter.animateScale();
        OplusExpandableViewStateExImpl.Companion companion = OplusExpandableViewStateExImpl.Companion;
        Property property = companion.getGROUP_EXPANSION_PROGRESS_PROPERTY().getProperty();
        ArraySet arraySet = animationFilter.mAnimatedProperties;
        arraySet.add(property);
        arraySet.add(companion.getGROUP_EXPAND_HEADER_ALPHA_PROPERTY().getProperty());
        arraySet.add(companion.getGROUP_EXPAND_CONTENT_ALPHA_PROPERTY().getProperty());
        animationFilter.hasDelays = true;
        return animationFilter;
    }

    public void initGroupChildCustomAnimType(ExpandableNotificationRow expandableNotificationRow, AnimationProperties animationProperties, int i) {
        ArrayList animationEvents;
        StackStateAnimator stackStateAnimator = this.stackStateAnimator;
        if (stackStateAnimator == null || (animationEvents = stackStateAnimator.getAnimationEvents()) == null || animationEvents.isEmpty()) {
            return;
        }
        int size = animationEvents.size();
        int i2 = 0;
        while (i2 < size) {
            Object obj = animationEvents.get(i2);
            i2++;
            if (((NotificationStackScrollLayout.AnimationEvent) obj).getAnimationType() == 10) {
                if (expandableNotificationRow.isChildInGroup() && ((OplusExpandableNotificationRowExImpl) expandableNotificationRow.getNotificationParent().mRowEx).isChildrenExpandedAnimating()) {
                    animationProperties.getAnimationFilter().animateHeight = expandableNotificationRow.isExpanded(true);
                    return;
                }
                return;
            }
        }
    }

    public void initGroupChildCustomDelay(ExpandableView expandableView, AnimationProperties animationProperties, int i) {
        StackStateAnimator stackStateAnimator;
        ArrayList animationEvents;
        StatusBarNotification sbn;
        if (!(expandableView instanceof ExpandableNotificationRow) || (stackStateAnimator = this.stackStateAnimator) == null || (animationEvents = stackStateAnimator.getAnimationEvents()) == null || animationEvents.isEmpty()) {
            return;
        }
        int size = animationEvents.size();
        int i2 = 0;
        while (i2 < size) {
            Object obj = animationEvents.get(i2);
            i2++;
            if (((NotificationStackScrollLayout.AnimationEvent) obj).getAnimationType() == 10) {
                ExpandableNotificationRow expandableNotificationRow = (ExpandableNotificationRow) expandableView;
                if (((OplusExpandableNotificationRowExImpl) expandableNotificationRow.mRowEx).isChildrenExpandedAnimating() && expandableNotificationRow.isChildInGroup()) {
                    boolean z = expandableNotificationRow.mChildrenExpanded;
                    GroupExpansionAnimParams.INSTANCE.getClass();
                    long childScaleDelayExtra = (GroupExpansionAnimParams.getChildScaleDelayExtra(z) * ((long) i)) + GroupExpansionAnimParams.getChildScaleDelayBase(z);
                    animationProperties.delay = childScaleDelayExtra;
                    if (!z && childScaleDelayExtra > ((NotificationGroupSplitEx) this).maxDelay) {
                        ((NotificationGroupSplitEx) this).maxDelay = childScaleDelayExtra;
                    }
                    if (LogUtil.getNormalDebug() && LogUtil.isTagValid(LogUtil.TAG_NOTIFICATION)) {
                        StringBuilder sb = new StringBuilder("NotificationGroupSplitExImpl-->");
                        NotificationEntry entry = expandableNotificationRow.getEntry();
                        StringBuilder sbM = SystemUIApplicationImpl$.ExternalSyntheticOutline0.m(childScaleDelayExtra, "initPropertyCustomDelay for ", (entry == null || (sbn = entry.getSbn()) == null) ? null : sbn.getKey(), ", delay ");
                        sbM.append(", areChildrenExpanded ");
                        sbM.append(z);
                        sb.append(sbM.toString());
                        Log.d("SystemUi--Notification", sb.toString());
                        return;
                    }
                    return;
                }
                return;
            }
        }
    }

    public void notificationGroupExpansionInterpolator(AnimationProperties animationProperties, boolean z, boolean z2, OplusPhysicsInterpolator oplusPhysicsInterpolator) {
        GroupExpansionAnimParams.InterpolatorSet interpolatorSet = GroupExpansionAnimParams.INSTANCE.getInterpolatorSet(z, z2);
        animationProperties.duration = GroupExpansionAnimParams.getDuration(z);
        OplusExpandableViewStateExImpl.Companion companion = OplusExpandableViewStateExImpl.Companion;
        animationProperties.setCustomInterpolator(companion.getGROUP_EXPANSION_PROGRESS_PROPERTY().getProperty(), interpolatorSet.getGroupExpansionProgressInterpolator());
        animationProperties.setCustomInterpolator(companion.getGROUP_EXPAND_HEADER_ALPHA_PROPERTY().getProperty(), interpolatorSet.getGroupHeaderAlphaInterpolator());
        animationProperties.setCustomInterpolator(companion.getGROUP_EXPAND_CONTENT_ALPHA_PROPERTY().getProperty(), interpolatorSet.getGroupContentAlphaInterpolator());
        if (oplusPhysicsInterpolator != null) {
            animationProperties.setCustomInterpolator(View.TRANSLATION_Y, oplusPhysicsInterpolator);
        } else {
            animationProperties.setCustomInterpolator(View.TRANSLATION_Y, interpolatorSet.getChildTranslationInterpolator());
        }
        animationProperties.setCustomInterpolator(OplusPhysicsPropertyAnimator.HEIGHT_PROPERTY.getProperty(), interpolatorSet.getGroupHeightInterpolator());
        animationProperties.setCustomInterpolator(View.SCALE_X, interpolatorSet.getChildScaleInterpolator());
        animationProperties.setCustomInterpolator(View.SCALE_Y, interpolatorSet.getChildScaleInterpolator());
    }

    public void onChildrenExpanded(boolean z, ExpandableNotificationRow expandableNotificationRow) {
        NotificationStackScrollLayout notificationStackScrollLayout;
        Object obj;
        if (expandableNotificationRow.mIsSummaryWithChildren && (notificationStackScrollLayout = this.notificationStackScrollLayout) != null) {
            try {
                int iIndexOfChild = notificationStackScrollLayout.indexOfChild(expandableNotificationRow);
                ((NotificationGroupSplitEx) this).expandRowIndex = iIndexOfChild;
                LogUtil.i$default(LogUtil.TAG_NOTIFICATION, TAG, "onChildrenExpanded to " + z + ", expandRowIndex " + iIndexOfChild, null, 8);
                obj = Result.constructor-impl(Unit.INSTANCE);
            } catch (Throwable th) {
                obj = Result.constructor-impl(ResultKt.createFailure(th));
            }
            Throwable th2 = Result.exceptionOrNull-impl(obj);
            if (th2 != null) {
                OplusAODRefreshRateController$RequestRefreshRateRunnable$.ExternalSyntheticOutline0.m(8, "onChildrenExpanded error ", th2.getMessage(), LogUtil.TAG_NOTIFICATION, TAG);
            }
        }
    }

    public void recordExpansionAnimState(ArrayList<NotificationStackScrollLayout.AnimationEvent> arrayList) {
        NotificationChildrenContainer childrenContainer;
        NotificationChildrenContainerOverlayEx notificationChildrenContainerOverlayEx;
        int size = arrayList.size();
        for (int i = 0; i < size; i++) {
            NotificationStackScrollLayout.AnimationEvent animationEvent = arrayList.get(i);
            ExpandableNotificationRow expandableNotificationRow = animationEvent.mChangingView;
            if (expandableNotificationRow != null && expandableNotificationRow.isSummaryWithChildren() && animationEvent.getAnimationType() == 10) {
                ExpandableNotificationRow expandableNotificationRow2 = expandableNotificationRow instanceof ExpandableNotificationRow ? expandableNotificationRow : null;
                if (expandableNotificationRow2 == null || (childrenContainer = expandableNotificationRow2.getChildrenContainer()) == null || (notificationChildrenContainerOverlayEx = childrenContainer.mOverlayEx) == null) {
                    return;
                }
                notificationChildrenContainerOverlayEx.recordExpansionAnimState(new NotificationGroupSplitEx.ExpansionFinishedState(true, expandableNotificationRow.areChildrenExpanded()));
                return;
            }
        }
    }
}
