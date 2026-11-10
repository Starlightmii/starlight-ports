package com.oplus.systemui.notification.lockscreen.capsule.utils;

import androidx.core.view.ViewGroupKt;
import com.android.systemui.plugins.custom.CustomRowEntry;
import com.oplus.systemui.notification.lockscreen.capsule.CapsuleItemView;
import com.oplus.systemui.notification.lockscreen.capsule.NotificationCapsuleContainer;
import com.oplus.systemui.notification.lockscreen.capsule.animtor.ContainerAnimatorViewHelper;
import com.oplus.systemui.notification.lockscreen.capsule.animtor.LockScreenAmbientState;
import com.oplus.systemui.notification.lockscreen.capsule.modle.CapsuleItemData;
import com.oplus.systemui.notification.lockscreen.config.CapsuleConfig;
import com.oplus.systemui.notification.lockscreen.notification.CapsuleNotificationData;
import com.oplus.systemui.notification.lockscreen.seedling.CapsuleSeedingData;
import java.util.ArrayList;
import java.util.Collection;
import java.util.Iterator;
import java.util.List;
import kotlin.collections.CollectionsKt__CollectionsKt;
import kotlin.collections.CollectionsKt___CollectionsKt;
import kotlin.sequences.SequencesKt___SequencesKt;

/* JADX INFO: compiled from: go/retraceme fd74bf9514dd0739b92d67a6d48a51eb10aaf8df760d83496b8dcfcf83e5f698 */
/* JADX INFO: loaded from: /home/starlight/Projects/Starlight Ports/.build-tmp/classes4.dex */
public abstract class CapsuleViewExpandKtKt {
    public static final int afterExcludeSize(NotificationCapsuleContainer notificationCapsuleContainer, List<CapsuleItemView> list) {
        ArrayList arrayList = new ArrayList();
        for (CapsuleItemView capsuleItemView : list) {
            if (capsuleItemView != null) {
                arrayList.add(capsuleItemView);
            }
        }
        return CollectionsKt___CollectionsKt.minus(getCapsuleChildren(notificationCapsuleContainer), arrayList).size();
    }

    public static final int childSizeExcludeImmersiveCard(NotificationCapsuleContainer notificationCapsuleContainer) {
        return afterExcludeSize(notificationCapsuleContainer, CollectionsKt__CollectionsKt.listOfNotNull(notificationCapsuleContainer.getAnimatorHelper().getAmbientState().getImmersiveCard()));
    }

    public static final void enableHorizontalScroll(NotificationCapsuleContainer notificationCapsuleContainer, boolean z, String str) {
        if (notificationCapsuleContainer == null) {
            return;
        }
        notificationCapsuleContainer.getScroller().enableHorizontalScroll(z, str);
    }

    public static final boolean existCard(NotificationCapsuleContainer notificationCapsuleContainer, CapsuleItemView capsuleItemView) {
        if (capsuleItemView == null) {
            return false;
        }
        return getCapsuleChildren(notificationCapsuleContainer).contains(capsuleItemView);
    }

    public static final CapsuleItemView findNotifCard(NotificationCapsuleContainer notificationCapsuleContainer) {
        Object next;
        Iterator<T> it = getCapsuleChildren(notificationCapsuleContainer).iterator();
        while (it.hasNext()) {
            next = it.next();
            if (isNotifCard((CapsuleItemView) next)) {
                return (CapsuleItemView) next;
            }
        }
        next = null;
        return (CapsuleItemView) next;
    }

    public static final CapsuleItemView findRemindCard(NotificationCapsuleContainer notificationCapsuleContainer) {
        Object obj;
        List<CapsuleItemView> capsuleVisibleChildren = getCapsuleVisibleChildren(notificationCapsuleContainer);
        ArrayList arrayList = new ArrayList();
        for (Object obj2 : capsuleVisibleChildren) {
            if (((CapsuleItemView) obj2).getEntry().getCardLevel() == 0) {
                arrayList.add(obj2);
            }
        }
        Iterator it = arrayList.iterator();
        if (it.hasNext()) {
            Object next = it.next();
            if (it.hasNext()) {
                int orderPriority = ((CapsuleItemView) next).getEntry().getOrderPriority();
                do {
                    Object next2 = it.next();
                    int orderPriority2 = ((CapsuleItemView) next2).getEntry().getOrderPriority();
                    if (orderPriority > orderPriority2) {
                        next = next2;
                        orderPriority = orderPriority2;
                    }
                } while (it.hasNext());
            }
            obj = next;
        } else {
            obj = null;
        }
        return (CapsuleItemView) obj;
    }

    public static final List<CapsuleItemView> getCapsuleChildren(NotificationCapsuleContainer notificationCapsuleContainer) {
        List<Object> list = SequencesKt___SequencesKt.toList(ViewGroupKt.getChildren(notificationCapsuleContainer));
        ArrayList arrayList = new ArrayList();
        for (Object obj : list) {
            CapsuleItemView capsuleItemView = obj instanceof CapsuleItemView ? (CapsuleItemView) obj : null;
            if (capsuleItemView != null) {
                arrayList.add(capsuleItemView);
            }
        }
        return arrayList;
    }

    public static final List<CapsuleItemView> getCapsuleVisibleChildren(NotificationCapsuleContainer notificationCapsuleContainer) {
        return notificationCapsuleContainer.getAnimatorHelper().getContainerScrollAlgorithm().getTempAlgorithmState().getVisibleChildren();
    }

    public static final int getCurrentCardShowWidth(NotificationCapsuleContainer notificationCapsuleContainer) {
        return showOnlyOneCard(notificationCapsuleContainer) ? CapsuleConfig.INSTANCE.getItemWidth() : CapsuleConfig.INSTANCE.getItemWidthMultiCard();
    }

    public static final List<CapsuleItemView> getRemovingCapsuleChildren(NotificationCapsuleContainer notificationCapsuleContainer) {
        ArrayList arrayList = new ArrayList();
        int transientViewCount = notificationCapsuleContainer.getTransientViewCount();
        if (transientViewCount >= 0) {
            int i = 0;
            while (true) {
                Object transientView = notificationCapsuleContainer.getTransientView(i);
                arrayList.add(transientView instanceof CapsuleItemView ? (CapsuleItemView) transientView : null);
                if (i == transientViewCount) {
                    break;
                }
                i++;
            }
        }
        return CollectionsKt___CollectionsKt.filterNotNull(arrayList);
    }

    public static final CustomRowEntry getSeedingEntry(CapsuleItemView capsuleItemView) {
        CapsuleItemData entry = capsuleItemView != null ? capsuleItemView.getEntry() : null;
        CapsuleSeedingData capsuleSeedingData = entry instanceof CapsuleSeedingData ? (CapsuleSeedingData) entry : null;
        if (capsuleSeedingData != null) {
            return capsuleSeedingData.getCustomRowEntry();
        }
        return null;
    }

    public static final CapsuleItemView highestPriorityCard(NotificationCapsuleContainer notificationCapsuleContainer) {
        Object obj;
        Iterator<T> it = getCapsuleVisibleChildren(notificationCapsuleContainer).iterator();
        if (it.hasNext()) {
            Object next = it.next();
            if (it.hasNext()) {
                int orderPriority = ((CapsuleItemView) next).getEntry().getOrderPriority();
                do {
                    Object next2 = it.next();
                    int orderPriority2 = ((CapsuleItemView) next2).getEntry().getOrderPriority();
                    if (orderPriority > orderPriority2) {
                        next = next2;
                        orderPriority = orderPriority2;
                    }
                } while (it.hasNext());
            }
            obj = next;
        } else {
            obj = null;
        }
        return (CapsuleItemView) obj;
    }

    public static final boolean isAppearAnimating(NotificationCapsuleContainer notificationCapsuleContainer) {
        List<CapsuleItemView> capsuleChildren = getCapsuleChildren(notificationCapsuleContainer);
        if (!(capsuleChildren instanceof Collection) || !capsuleChildren.isEmpty()) {
            Iterator<T> it = capsuleChildren.iterator();
            while (it.hasNext()) {
                if (((CapsuleItemView) it.next()).isAppearAnimating().get()) {
                    return true;
                }
            }
        }
        List<CapsuleItemView> removingCapsuleChildren = getRemovingCapsuleChildren(notificationCapsuleContainer);
        if ((removingCapsuleChildren instanceof Collection) && removingCapsuleChildren.isEmpty()) {
            return false;
        }
        Iterator<T> it2 = removingCapsuleChildren.iterator();
        while (it2.hasNext()) {
            if (((CapsuleItemView) it2.next()).isAppearAnimating().get()) {
                return true;
            }
        }
        return false;
    }

    public static final boolean isBaseCard(CapsuleItemView capsuleItemView) {
        ContainerAnimatorViewHelper animatorHelper;
        LockScreenAmbientState ambientState;
        if (capsuleItemView == null) {
            return false;
        }
        NotificationCapsuleContainer capsuleContainer = capsuleItemView.getCapsuleContainer();
        return capsuleItemView == ((capsuleContainer == null || (animatorHelper = capsuleContainer.getAnimatorHelper()) == null || (ambientState = animatorHelper.getAmbientState()) == null) ? null : ambientState.getBaseCard());
    }

    public static final boolean isNotifCard(CapsuleItemView capsuleItemView) {
        return (capsuleItemView != null ? capsuleItemView.getEntry() : null) instanceof CapsuleNotificationData;
    }

    public static final boolean isSeedlingCard(CapsuleItemView capsuleItemView) {
        return (capsuleItemView != null ? capsuleItemView.getEntry() : null) instanceof CapsuleSeedingData;
    }

    public static final boolean showMultiCardIfExclude(NotificationCapsuleContainer notificationCapsuleContainer, CapsuleItemView capsuleItemView) {
        return showMultiCardIfExclude(notificationCapsuleContainer, (List<CapsuleItemView>) CollectionsKt__CollectionsKt.listOf(new CapsuleItemView[]{capsuleItemView, notificationCapsuleContainer.getAnimatorHelper().getAmbientState().getImmersiveCard()}));
    }

    public static final boolean showOnlyOneCard(NotificationCapsuleContainer notificationCapsuleContainer) {
        return afterExcludeSize(notificationCapsuleContainer, CollectionsKt___CollectionsKt.plus(notificationCapsuleContainer.getAnimatorHelper().getIgnoreViews(), notificationCapsuleContainer.getAnimatorHelper().getAmbientState().getImmersiveCard())) <= 1;
    }

    public static final boolean showMultiCardIfExclude(NotificationCapsuleContainer notificationCapsuleContainer, List<CapsuleItemView> list) {
        return afterExcludeSize(notificationCapsuleContainer, list) >= 2;
    }
}
