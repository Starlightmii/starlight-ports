package com.oplus.systemui.notification.material;

import android.graphics.Rect;
import android.util.Log;
import android.view.MotionEvent;
import android.view.View;
import android.view.ViewGroup;
import androidx.collection.FloatList$;
import androidx.compose.ui.spatial.RectList$;
import com.android.mechanics.effects.VerticalTactileSurfaceRevealEffect$;
import com.android.systemui.biometrics.udfps.NormalizedTouchData$;
import com.android.systemui.media.controls.domain.pipeline.MediaTimeoutListener$PlaybackStateListener$;
import com.android.systemui.plugins.statusbar.NotificationMenuRowPlugin;
import com.android.systemui.statusbar.SysuiStatusBarStateController;
import com.android.systemui.statusbar.notification.row.ExpandableNotificationRow;
import com.android.systemui.statusbar.notification.row.ExpandableView;
import com.android.systemui.statusbar.notification.row.NotificationMenuRow;
import com.android.systemui.statusbar.notification.stack.NotificationChildrenContainer;
import com.android.systemui.statusbar.notification.stack.NotificationChildrenContainerExt;
import com.android.systemui.statusbar.notification.stack.NotificationStackScrollLayoutController;
import com.android.systemui.statusbar.pipeline.mobile.data.repository.prod.MobileConnectionRepositoryImpl$callbackEvents$1$1$;
import com.oplus.keyguard.VFXController$stopAnim$1$;
import com.oplus.systemui.notification.lockscreen.capsule.CapsuleListContainer;
import com.oplus.systemui.notification.lockscreen.capsule.NotificationCapsuleContainer;
import com.oplus.systemui.notification.lockscreen.capsule.NotificationCapsuleContainerControllerImpl;
import com.oplus.systemui.statusbar.notification.customcard.OplusCustomRow;
import com.oplus.systemui.statusbar.seeding.OneHandedObserver;
import com.oplusos.systemui.common.util.GlobalUtils;
import com.oplusos.systemui.common.util.LogUtil;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

/* JADX INFO: compiled from: go/retraceme fd74bf9514dd0739b92d67a6d48a51eb10aaf8df760d83496b8dcfcf83e5f698 */
/* JADX INFO: loaded from: /home/starlight/Projects/Starlight Ports/.build-tmp/classes4.dex */
public final class NotificationSpotLightTouchBridge {
    private static final String TAG = "NotificationSpotLightTouchBridge";
    public static final NotificationSpotLightTouchBridge INSTANCE = new NotificationSpotLightTouchBridge();
    private static final int[] reusableLoc = new int[2];
    private static final Rect reusableRect = new Rect();
    private static final int[] reusableHLoc = new int[2];

    public static void dispatchPointerDownToSpotLightHost(MotionEvent motionEvent, View view) {
        MotionEvent motionEventTransformPanelEventToHostLocal = transformPanelEventToHostLocal(view, motionEvent, false);
        if (motionEventTransformPanelEventToHostLocal == null) {
            return;
        }
        try {
            NotificationSpotLightDelegate.Companion.onTouchEvent(view, motionEventTransformPanelEventToHostLocal);
        } finally {
            motionEventTransformPanelEventToHostLocal.recycle();
        }
    }

    public static View findSpotlightHostWithParamsKind(float f, float f2, View view) {
        View viewFindSpotlightHostWithParamsKind;
        if (view.getVisibility() != 0 || view.getAlpha() == 0.0f || !isRawTouchInsideView(f, f2, view)) {
            return null;
        }
        if (view instanceof ViewGroup) {
            ViewGroup viewGroup = (ViewGroup) view;
            int childCount = viewGroup.getChildCount();
            do {
                childCount--;
                if (-1 < childCount) {
                    View childAt = viewGroup.getChildAt(childCount);
                    childAt.getClass();
                    viewFindSpotlightHostWithParamsKind = findSpotlightHostWithParamsKind(f, f2, childAt);
                }
            } while (viewFindSpotlightHostWithParamsKind == null);
            return viewFindSpotlightHostWithParamsKind;
        }
        if (view.getTag(2131364205) != null) {
            return view;
        }
        return null;
    }

    public static final void forwardHeadsUpDispatchTouch(View view, MotionEvent motionEvent) {
        float rawX;
        float rawY;
        if (NotificationSpotLightEffectManager.spotLightEnabled) {
            int actionMasked = motionEvent.getActionMasked();
            if (actionMasked != 0 && actionMasked != 5) {
                INSTANCE.getClass();
                forwardTrackedSpotLightMotion(motionEvent);
                return;
            }
            INSTANCE.getClass();
            int actionIndex = motionEvent.getActionMasked() == 5 ? motionEvent.getActionIndex() : 0;
            if (actionIndex < 0 || actionIndex >= motionEvent.getPointerCount()) {
                return;
            }
            if (OneHandedObserver.Companion.getInstance().getOneHandedEnable()) {
                rawX = motionEvent.getRawX(actionIndex) - motionEvent.getX(actionIndex);
                rawY = motionEvent.getRawY(actionIndex) - motionEvent.getY(actionIndex);
            } else {
                rawX = 0.0f;
                rawY = 0.0f;
            }
            float rawX2 = motionEvent.getRawX(actionIndex) - rawX;
            float rawY2 = motionEvent.getRawY(actionIndex) - rawY;
            ViewGroup viewGroup = view instanceof ViewGroup ? (ViewGroup) view : null;
            if (viewGroup != null && viewGroup.getChildCount() > 0) {
                View childAt = viewGroup.getChildAt(0);
                View view2 = childAt != null ? childAt : null;
                if (view2 == null) {
                    return;
                }
                if (((view2 instanceof ExpandableNotificationRow) || (view2 instanceof OplusCustomRow)) && isRawTouchInsideView(rawX2, rawY2, view2)) {
                    if (LogUtil.getNormalDebug() && LogUtil.isTagValid(LogUtil.TAG_NOTIFICATION)) {
                        MediaTimeoutListener$PlaybackStateListener$.ExternalSyntheticOutline0.m("[HeadsUp-SpotLight] ACTION_DOWN host:", NotificationSpotLightGestureCoordinator.INSTANCE.getLogKey(view2), "NotificationSpotLightTouchBridge-->", "SystemUi--Notification");
                    }
                    dispatchPointerDownToSpotLightHost(motionEvent, view2);
                }
            }
        }
    }

    /* JADX WARN: Multi-variable type inference failed */
    /* JADX WARN: Type inference failed for: r3v20 */
    /* JADX WARN: Type inference failed for: r3v22 */
    /* JADX WARN: Type inference failed for: r3v43 */
    /* JADX WARN: Type inference failed for: r3v44 */
    public static final void forwardPanelDispatchTouch(View view, MotionEvent motionEvent, NotificationStackScrollLayoutController notificationStackScrollLayoutController, NotificationCapsuleContainerControllerImpl notificationCapsuleContainerControllerImpl, SysuiStatusBarStateController sysuiStatusBarStateController) {
        float rawX;
        float rawY;
        View viewFindViewById;
        View viewFindViewById2;
        int i;
        ExpandableView expandableView;
        ExpandableView notificationParent;
        ArrayList menuItems;
        boolean zIsTouchInView;
        ?? r3;
        boolean zIsRawTouchInsideView;
        ExpandableNotificationRow expandableNotificationRow;
        NotificationChildrenContainer childrenContainer;
        List attachedChildren;
        NotificationChildrenContainer childrenContainer2;
        NotificationChildrenContainerExt notificationChildrenContainerExt;
        ViewGroup oplusExpandGroupHeaderView;
        View viewFindViewById3;
        ExpandableView expandableViewFindViewById;
        NotificationChildrenContainer childrenContainer3;
        NotificationChildrenContainerExt notificationChildrenContainerExt2;
        ViewGroup oplusExpandGroupHeaderView2;
        NotificationCapsuleContainer view2;
        CapsuleListContainer capsuleListContainer;
        int actionMasked = motionEvent.getActionMasked();
        boolean z = view.getVisibility() != 0 || view.getAlpha() == 0.0f;
        boolean z2 = NotificationSpotLightGestureCoordinator.INSTANCE.findHostIfPointerTracked(motionEvent) != null && (actionMasked == 1 || actionMasked == 3);
        if (z && !z2) {
            if ((actionMasked == 0 || actionMasked == 5) && LogUtil.getNormalDebug() && LogUtil.isTagValid(LogUtil.TAG_NOTIFICATION)) {
                Log.d("SystemUi--Notification", "NotificationSpotLightTouchBridge-->[NotifCard-SpotLight] panel is not visible");
                return;
            }
            return;
        }
        boolean z3 = sysuiStatusBarStateController != null && sysuiStatusBarStateController.getState() == 1;
        int containerChildCount = (notificationCapsuleContainerControllerImpl == null || (capsuleListContainer = notificationCapsuleContainerControllerImpl.getCapsuleListContainer()) == null) ? 0 : capsuleListContainer.getContainerChildCount();
        if (z3 && containerChildCount <= 0 && !z2) {
            if ((actionMasked == 0 || actionMasked == 5) && LogUtil.getNormalDebug() && LogUtil.isTagValid(LogUtil.TAG_NOTIFICATION)) {
                Log.d("SystemUi--Notification", "NotificationSpotLightTouchBridge-->[NotifCard-SpotLight] skip host search on keyguard: no visible notification or fluid cloud card");
                return;
            }
            return;
        }
        if (NotificationSpotLightEffectManager.spotLightEnabled) {
            int actionMasked2 = motionEvent.getActionMasked();
            if (actionMasked2 != 0 && actionMasked2 != 5) {
                INSTANCE.getClass();
                forwardTrackedSpotLightMotion(motionEvent);
                return;
            }
            if (notificationStackScrollLayoutController != null) {
                INSTANCE.getClass();
                int actionIndex = motionEvent.getActionMasked() == 5 ? motionEvent.getActionIndex() : 0;
                if (actionIndex < 0 || actionIndex >= motionEvent.getPointerCount()) {
                    return;
                }
                if (OneHandedObserver.Companion.getInstance().getOneHandedEnable()) {
                    rawX = motionEvent.getRawX(actionIndex) - motionEvent.getX(actionIndex);
                    rawY = motionEvent.getRawY(actionIndex) - motionEvent.getY(actionIndex);
                } else {
                    rawX = 0.0f;
                    rawY = 0.0f;
                }
                float rawX2 = motionEvent.getRawX(actionIndex) - rawX;
                float rawY2 = motionEvent.getRawY(actionIndex) - rawY;
                float x = motionEvent.getX(actionIndex);
                float y = motionEvent.getY(actionIndex);
                ExpandableView childAtPosition = (notificationCapsuleContainerControllerImpl == null || (view2 = notificationCapsuleContainerControllerImpl.getView()) == null) ? null : view2.getChildAtPosition(rawX2, rawY2);
                if (childAtPosition != null) {
                    dispatchPointerDownToSpotLightHost(motionEvent, childAtPosition);
                    return;
                }
                View viewFindViewById4 = view.findViewById(2131362577);
                if (viewFindViewById4 == null || viewFindViewById4.getVisibility() != 0 || !viewFindViewById4.isEnabled() || viewFindViewById4.getWidth() <= 0 || viewFindViewById4.getHeight() <= 0 || !isRawTouchInsideView(rawX2, rawY2, viewFindViewById4)) {
                    viewFindViewById4 = null;
                }
                if (viewFindViewById4 != null) {
                    dispatchPointerDownToSpotLightHost(motionEvent, viewFindViewById4);
                    return;
                }
                View viewFindViewById5 = view.findViewById(2131362545);
                if (viewFindViewById5 == null || viewFindViewById5.getVisibility() != 0 || !viewFindViewById5.isEnabled() || viewFindViewById5.getWidth() <= 0 || viewFindViewById5.getHeight() <= 0 || !isRawTouchInsideView(rawX2, rawY2, viewFindViewById5)) {
                    viewFindViewById5 = null;
                }
                if (viewFindViewById5 != null) {
                    dispatchPointerDownToSpotLightHost(motionEvent, viewFindViewById5);
                    return;
                }
                View viewFindViewById6 = view.findViewById(2131364768);
                if (viewFindViewById6 == null || viewFindViewById6.getVisibility() != 0 || (viewFindViewById = viewFindViewById6.findViewById(2131365157)) == null || viewFindViewById.getVisibility() != 0 || (viewFindViewById2 = viewFindViewById6.findViewById(2131365156)) == null || viewFindViewById2.getVisibility() != 0 || !viewFindViewById2.isEnabled() || viewFindViewById2.getWidth() <= 0 || viewFindViewById2.getHeight() <= 0 || !isRawTouchInsideView(rawX2, rawY2, viewFindViewById2)) {
                    viewFindViewById2 = null;
                }
                if (viewFindViewById2 != null) {
                    dispatchPointerDownToSpotLightHost(motionEvent, viewFindViewById2);
                    return;
                }
                int actionMasked3 = motionEvent.getActionMasked();
                long jCurrentTimeMillis = System.currentTimeMillis();
                if (LogUtil.getNormalDebug() && LogUtil.isTagValid(LogUtil.TAG_NOTIFICATION)) {
                    i = 0;
                    StringBuilder sbM = NormalizedTouchData$.ExternalSyntheticOutline0.m(rawX2, actionMasked3, "[NotifCard-SpotLight] event=", " raw ", " - ");
                    VerticalTactileSurfaceRevealEffect$.ExternalSyntheticOutline0.m(sbM, rawY2, " x:", x, " - ");
                    VFXController$stopAnim$1$.ExternalSyntheticOutline0.m(sbM, y, "NotificationSpotLightTouchBridge-->", "SystemUi--Notification");
                } else {
                    i = 0;
                }
                ExpandableView childAtRawPosition = notificationStackScrollLayoutController.mView.getChildAtRawPosition(rawX2, rawY2);
                if (childAtRawPosition == null) {
                    return;
                }
                ExpandableView expandableViewFindSpotlightHostWithParamsKind = findSpotlightHostWithParamsKind(rawX2, rawY2, childAtRawPosition);
                if (expandableViewFindSpotlightHostWithParamsKind == null) {
                    if (!(childAtRawPosition instanceof OplusCustomRow) && !(childAtRawPosition instanceof ExpandableNotificationRow)) {
                        childAtRawPosition = null;
                    }
                    if (childAtRawPosition == null) {
                        return;
                    } else {
                        expandableViewFindSpotlightHostWithParamsKind = childAtRawPosition;
                    }
                }
                ExpandableNotificationRow expandableNotificationRow2 = expandableViewFindSpotlightHostWithParamsKind instanceof ExpandableNotificationRow ? (ExpandableNotificationRow) expandableViewFindSpotlightHostWithParamsKind : null;
                if (expandableNotificationRow2 != null) {
                    if (!expandableNotificationRow2.mIsSummaryWithChildren || !expandableNotificationRow2.isGroupExpanded() || (childrenContainer3 = expandableNotificationRow2.getChildrenContainer()) == null || (notificationChildrenContainerExt2 = childrenContainer3.mExt) == null || (oplusExpandGroupHeaderView2 = notificationChildrenContainerExt2.getOplusExpandGroupHeaderView()) == null || oplusExpandGroupHeaderView2.getVisibility() != 0 || oplusExpandGroupHeaderView2.getAlpha() == 0.0f || (expandableViewFindViewById = oplusExpandGroupHeaderView2.findViewById(2131364385)) == null || expandableViewFindViewById.getVisibility() != 0 || expandableViewFindViewById.getWidth() <= 0 || expandableViewFindViewById.getHeight() <= 0 || !isRawTouchInsideView(rawX2, rawY2, expandableViewFindViewById)) {
                        expandableViewFindViewById = null;
                    }
                    if (expandableViewFindViewById != null) {
                        expandableViewFindSpotlightHostWithParamsKind = expandableViewFindViewById;
                    }
                }
                if (expandableViewFindSpotlightHostWithParamsKind instanceof ExpandableNotificationRow) {
                    ExpandableNotificationRow expandableNotificationRow3 = (ExpandableNotificationRow) expandableViewFindSpotlightHostWithParamsKind;
                    if (expandableNotificationRow3.mIsSummaryWithChildren && expandableNotificationRow3.isGroupExpanded() && (childrenContainer2 = expandableNotificationRow3.getChildrenContainer()) != null && (notificationChildrenContainerExt = childrenContainer2.mExt) != null && (oplusExpandGroupHeaderView = notificationChildrenContainerExt.getOplusExpandGroupHeaderView()) != null && oplusExpandGroupHeaderView.getVisibility() == 0 && oplusExpandGroupHeaderView.getWidth() > 0 && oplusExpandGroupHeaderView.getHeight() > 0 && isRawTouchInsideView(rawX2, rawY2, oplusExpandGroupHeaderView) && ((viewFindViewById3 = oplusExpandGroupHeaderView.findViewById(2131364385)) == null || viewFindViewById3.getVisibility() != 0 || viewFindViewById3.getWidth() <= 0 || viewFindViewById3.getHeight() <= 0 || !isRawTouchInsideView(rawX2, rawY2, viewFindViewById3))) {
                        if (LogUtil.getNormalDebug() && LogUtil.isTagValid(LogUtil.TAG_NOTIFICATION)) {
                            MobileConnectionRepositoryImpl$callbackEvents$1$1$.ExternalSyntheticOutline0.m(actionMasked3, "[NotifCard-SpotLight] event=", " in expand header", "NotificationSpotLightTouchBridge-->", "SystemUi--Notification");
                            return;
                        }
                        return;
                    }
                    NotificationMenuRow provider = expandableNotificationRow3.getProvider();
                    NotificationMenuRow notificationMenuRow = provider instanceof NotificationMenuRow ? provider : null;
                    if (notificationMenuRow != null && notificationMenuRow.isMenuVisible() && expandableNotificationRow3.getTranslation() != 0.0f && (menuItems = notificationMenuRow.getMenuItems(expandableNotificationRow3.getContext())) != null) {
                        int size = menuItems.size();
                        int i2 = i;
                        while (i2 < size) {
                            Object obj = menuItems.get(i2);
                            i2++;
                            View menuView = ((NotificationMenuRowPlugin.MenuItem) obj).getMenuView();
                            if (menuView != null) {
                                if (menuView.getVisibility() == 0 && menuView.getWidth() > 0 && menuView.getHeight() > 0) {
                                    if (motionEvent.getActionMasked() == 5) {
                                        int actionIndex2 = motionEvent.getActionIndex();
                                        if (actionIndex2 < 0 || actionIndex2 >= motionEvent.getPointerCount()) {
                                            r3 = i;
                                        } else {
                                            zIsRawTouchInsideView = isRawTouchInsideView(motionEvent.getRawX(actionIndex2), motionEvent.getRawY(actionIndex2), menuView);
                                        }
                                    } else {
                                        zIsTouchInView = GlobalUtils.isTouchInView(motionEvent, menuView);
                                    }
                                    if (r3 != 0) {
                                        r3 = zIsTouchInView;
                                        r3 = zIsRawTouchInsideView;
                                        if (LogUtil.getNormalDebug() && LogUtil.isTagValid(LogUtil.TAG_NOTIFICATION)) {
                                            MobileConnectionRepositoryImpl$callbackEvents$1$1$.ExternalSyntheticOutline0.m(actionMasked3, "[NotifCard-SpotLight] event=", " in menu row", "NotificationSpotLightTouchBridge-->", "SystemUi--Notification");
                                            return;
                                        }
                                        return;
                                    }
                                }
                                r3 = zIsTouchInView;
                                r3 = zIsRawTouchInsideView;
                                menuItems = menuItems;
                                expandableViewFindSpotlightHostWithParamsKind = expandableViewFindSpotlightHostWithParamsKind;
                            }
                        }
                    }
                    expandableView = expandableViewFindSpotlightHostWithParamsKind;
                    INSTANCE.getClass();
                    if (expandableNotificationRow3.mIsSummaryWithChildren && expandableNotificationRow3.mChildrenExpanded && (childrenContainer = expandableNotificationRow3.getChildrenContainer()) != null && isRawTouchInsideView(rawX2, rawY2, childrenContainer) && (attachedChildren = expandableNotificationRow3.getAttachedChildren()) != null) {
                        Iterator it = attachedChildren.iterator();
                        while (true) {
                            if (!it.hasNext()) {
                                if (LogUtil.getNormalDebug() && LogUtil.isTagValid(LogUtil.TAG_NOTIFICATION)) {
                                    MobileConnectionRepositoryImpl$callbackEvents$1$1$.ExternalSyntheticOutline0.m(actionMasked3, "[NotifCard-SpotLight] event=", " in expanded group gap", "NotificationSpotLightTouchBridge-->", "SystemUi--Notification");
                                    return;
                                }
                                return;
                            }
                            ExpandableNotificationRow expandableNotificationRow4 = (ExpandableNotificationRow) it.next();
                            if (expandableNotificationRow4.getVisibility() != 0 || expandableNotificationRow4.getWidth() <= 0 || expandableNotificationRow4.getActualHeight() <= 0) {
                                expandableNotificationRow = expandableNotificationRow3;
                            } else {
                                int[] iArr = reusableLoc;
                                expandableNotificationRow4.getLocationOnScreen(iArr);
                                Rect rect = reusableRect;
                                int i3 = iArr[i];
                                expandableNotificationRow = expandableNotificationRow3;
                                rect.set(i3, iArr[1], expandableNotificationRow4.getWidth() + i3, expandableNotificationRow4.getActualHeight() + iArr[1]);
                                if (rect.contains((int) rawX2, (int) rawY2)) {
                                    break;
                                }
                            }
                            expandableNotificationRow3 = expandableNotificationRow;
                            it = it;
                        }
                    } else {
                        expandableNotificationRow = expandableNotificationRow3;
                    }
                    if (expandableNotificationRow.isChildInGroup() && !expandableNotificationRow.getNotificationParent().mChildrenExpanded) {
                        notificationParent = expandableNotificationRow.getNotificationParent();
                    }
                    if (LogUtil.getNormalDebug() && LogUtil.isTagValid(LogUtil.TAG_NOTIFICATION)) {
                        Log.d("SystemUi--Notification", "NotificationSpotLightTouchBridge-->".concat("[NotifCard-SpotLight] event=" + actionMasked3 + " find host:" + NotificationSpotLightGestureCoordinator.INSTANCE.getLogKey(notificationParent)));
                    }
                    INSTANCE.getClass();
                    dispatchPointerDownToSpotLightHost(motionEvent, notificationParent);
                    LogUtil.i$default(LogUtil.TAG_NOTIFICATION, TAG, "[NotifCard-SpotLight] event=" + actionMasked3 + " logic time cost:" + (System.currentTimeMillis() - jCurrentTimeMillis), null, 8);
                }
                expandableView = expandableViewFindSpotlightHostWithParamsKind;
                notificationParent = expandableView;
                if (LogUtil.getNormalDebug()) {
                    Log.d("SystemUi--Notification", "NotificationSpotLightTouchBridge-->".concat("[NotifCard-SpotLight] event=" + actionMasked3 + " find host:" + NotificationSpotLightGestureCoordinator.INSTANCE.getLogKey(notificationParent)));
                }
                INSTANCE.getClass();
                dispatchPointerDownToSpotLightHost(motionEvent, notificationParent);
                LogUtil.i$default(LogUtil.TAG_NOTIFICATION, TAG, "[NotifCard-SpotLight] event=" + actionMasked3 + " logic time cost:" + (System.currentTimeMillis() - jCurrentTimeMillis), null, 8);
            }
        }
    }

    public static /* synthetic */ void forwardPanelDispatchTouch$default(View view, MotionEvent motionEvent, NotificationStackScrollLayoutController notificationStackScrollLayoutController, NotificationCapsuleContainerControllerImpl notificationCapsuleContainerControllerImpl, int i) {
        if ((i & 4) != 0) {
            notificationStackScrollLayoutController = null;
        }
        if ((i & 8) != 0) {
            notificationCapsuleContainerControllerImpl = null;
        }
        forwardPanelDispatchTouch(view, motionEvent, notificationStackScrollLayoutController, notificationCapsuleContainerControllerImpl, null);
    }

    public static void forwardTrackedSpotLightMotion(MotionEvent motionEvent) {
        MotionEvent motionEventTransformPanelEventToHostLocal;
        View viewFindHostIfPointerTracked = NotificationSpotLightGestureCoordinator.INSTANCE.findHostIfPointerTracked(motionEvent);
        if (viewFindHostIfPointerTracked == null || (motionEventTransformPanelEventToHostLocal = transformPanelEventToHostLocal(viewFindHostIfPointerTracked, motionEvent, true)) == null) {
            return;
        }
        try {
            NotificationSpotLightDelegate.Companion.onTouchEvent(viewFindHostIfPointerTracked, motionEventTransformPanelEventToHostLocal);
        } finally {
            motionEventTransformPanelEventToHostLocal.recycle();
        }
    }

    public static boolean isRawTouchInsideView(float f, float f2, View view) {
        int[] iArr = reusableLoc;
        view.getLocationOnScreen(iArr);
        Rect rect = reusableRect;
        int i = iArr[0];
        rect.set(i, iArr[1], view.getWidth() + i, view.getHeight() + iArr[1]);
        return rect.contains((int) f, (int) f2);
    }

    public static MotionEvent transformPanelEventToHostLocal(View view, MotionEvent motionEvent, boolean z) {
        int actionIndex;
        float rawX;
        float rawY;
        if (z) {
            actionIndex = NotificationSpotLightGestureCoordinator.INSTANCE.findOwnedPointerIndex(view, motionEvent);
            if (actionIndex < 0) {
                return null;
            }
        } else {
            actionIndex = motionEvent.getActionMasked() == 5 ? motionEvent.getActionIndex() : 0;
        }
        if (actionIndex < 0 || actionIndex >= motionEvent.getPointerCount()) {
            return null;
        }
        MotionEvent motionEventObtain = MotionEvent.obtain(motionEvent);
        int[] iArr = reusableHLoc;
        view.getLocationOnScreen(iArr);
        if (OneHandedObserver.Companion.getInstance().getOneHandedEnable()) {
            rawX = motionEvent.getRawX(actionIndex) - motionEvent.getX(actionIndex);
            rawY = motionEvent.getRawY(actionIndex) - motionEvent.getY(actionIndex);
        } else {
            rawX = 0.0f;
            rawY = 0.0f;
        }
        float rawX2 = (motionEventObtain.getRawX(actionIndex) - rawX) - iArr[0];
        float rawY2 = (motionEventObtain.getRawY(actionIndex) - rawY) - iArr[1];
        motionEventObtain.setLocation(rawX2, rawY2);
        if ((motionEventObtain.getActionMasked() == 0 || motionEventObtain.getActionMasked() == 5 || motionEventObtain.getActionMasked() == 1 || motionEventObtain.getActionMasked() == 6 || motionEventObtain.getActionMasked() == 3) && LogUtil.getNormalDebug() && LogUtil.isTagValid(LogUtil.TAG_NOTIFICATION)) {
            int actionMasked = motionEventObtain.getActionMasked();
            float rawX3 = motionEventObtain.getRawX(actionIndex);
            float rawY3 = motionEventObtain.getRawY(actionIndex);
            int i = iArr[0];
            int i2 = iArr[1];
            StringBuilder sbM = FloatList$.ExternalSyntheticOutline0.m("[NotifCard-SpotLight] transformPanelEventToHostLocal ev=", actionMasked, actionIndex, ", refIndex=", " raw pos=");
            VerticalTactileSurfaceRevealEffect$.ExternalSyntheticOutline0.m(sbM, rawX3, " - ", rawY3, " offsetX=");
            VerticalTactileSurfaceRevealEffect$.ExternalSyntheticOutline0.m(sbM, rawX, ", offsetY=", rawY, ", host loc=");
            RectList$.ExternalSyntheticOutline0.m(i, i2, " - ", " relative pos=", sbM);
            Log.d("SystemUi--Notification", VFXController$stopAnim$1$.ExternalSyntheticOutline0.m(sbM, rawX2, " - ", rawY2, "NotificationSpotLightTouchBridge-->"));
        }
        return motionEventObtain;
    }

    public static final void forwardPanelDispatchTouch(View view, MotionEvent motionEvent, NotificationStackScrollLayoutController notificationStackScrollLayoutController) {
        forwardPanelDispatchTouch$default(view, motionEvent, notificationStackScrollLayoutController, null, 24);
    }

    public static final void forwardPanelDispatchTouch(View view, MotionEvent motionEvent, NotificationStackScrollLayoutController notificationStackScrollLayoutController, NotificationCapsuleContainerControllerImpl notificationCapsuleContainerControllerImpl) {
        forwardPanelDispatchTouch$default(view, motionEvent, notificationStackScrollLayoutController, notificationCapsuleContainerControllerImpl, 16);
    }

    public static final void forwardPanelDispatchTouch(View view, MotionEvent motionEvent) {
        forwardPanelDispatchTouch$default(view, motionEvent, null, null, 28);
    }
}
