package com.oplus.systemui.notification.row;

import android.content.Context;
import android.graphics.drawable.Drawable;
import android.util.Log;
import android.view.View;
import android.view.ViewGroup;
import androidx.appcompat.widget.ListPopupWindow$;
import androidx.collection.FloatList$;
import androidx.core.view.insets.Protection$;
import androidx.dynamicanimation.animation.FloatValueHolder;
import com.android.systemui.statusbar.notification.row.ExpandableView;
import com.android.systemui.statusbar.notification.stack.ExpandableViewState;
import com.android.systemui.statusbar.notification.stack.OplusExpandableViewStateEx;
import com.coui.appcompat.animation.dynamicanimation.COUISpringAnimation;
import com.oplus.systemui.keyguard.clock.WeatherInfoParseHelper$;
import com.oplusos.systemui.common.olivelink.Spring;
import com.oplusos.systemui.common.util.LogUtil;
import com.oplusos.systemui.common.utils.DeviceUtil;
import java.lang.invoke.VarHandle;
import java.util.LinkedHashMap;
import java.util.Map;
import kotlin.Lazy;
import kotlin.LazyKt__LazyJVMKt;
import kotlin.ranges.RangesKt___RangesKt;

/* JADX INFO: compiled from: go/retraceme fd74bf9514dd0739b92d67a6d48a51eb10aaf8df760d83496b8dcfcf83e5f698 */
/* JADX INFO: loaded from: /home/starlight/Projects/Starlight Ports/.build-tmp/classes4.dex */
public final class NotificationMenuRowItemAnimatorStage2 {
    public static final Companion Companion = new Companion();
    private static final String TAG = "NotificationMenuRowItemAnimatorStage2";
    private final Map<View, COUISpringAnimation> animMap = new LinkedHashMap();
    private final Context context;
    public float deleteProgress;
    private final Spring deleteSpring;
    private final Spring deleteSpringBack;
    private final NotificationMenuViewMetaBallDeviceParams deviceParams;
    public boolean isDismiss;
    public boolean isOne;
    public float lastTranslation;
    private ViewGroup mcontainer;
    private final Lazy metaBallController$delegate;
    public final float minimumVisibleChangeTwo;
    public float settingProgress;
    private final Spring settingSpring;
    private final Spring settingSpringBack;
    public float translationDelete;
    private final MenuViewInfoProvider viewInfo;

    /* JADX INFO: compiled from: go/retraceme fd74bf9514dd0739b92d67a6d48a51eb10aaf8df760d83496b8dcfcf83e5f698 */
    public final class Companion {
    }

    public static NotificationMenuRowMetaBallController2 $r8$lambda$eGmc8v_bJY_rdeDI1VsRW_fQjyQ(NotificationMenuRowItemAnimatorStage2 notificationMenuRowItemAnimatorStage2) {
        return new NotificationMenuRowMetaBallController2(notificationMenuRowItemAnimatorStage2.context);
    }

    public NotificationMenuRowItemAnimatorStage2(MenuViewInfoProvider menuViewInfoProvider, Context context, NotificationMenuViewMetaBallDeviceParams notificationMenuViewMetaBallDeviceParams) {
        this.viewInfo = menuViewInfoProvider;
        this.context = context;
        this.deviceParams = notificationMenuViewMetaBallDeviceParams;
        NotificationMenuRowItemAnimatorStage2$$ExternalSyntheticLambda0 notificationMenuRowItemAnimatorStage2$$ExternalSyntheticLambda0 = new NotificationMenuRowItemAnimatorStage2$$ExternalSyntheticLambda0();
        notificationMenuRowItemAnimatorStage2$$ExternalSyntheticLambda0.f$0 = this;
        VarHandle.storeStoreFence();
        this.metaBallController$delegate = LazyKt__LazyJVMKt.lazy(notificationMenuRowItemAnimatorStage2$$ExternalSyntheticLambda0);
        NotificationMenuRowParams.AnimateParamTwo animateParamTwo = NotificationMenuRowParams.AnimateParamTwo.INSTANCE;
        this.minimumVisibleChangeTwo = animateParamTwo.getMinimumVisibleChange_TWO().get().floatValue();
        this.deleteSpring = animateParamTwo.getTwoAnimatedelete();
        this.settingSpring = animateParamTwo.getTwoAnimateSetting();
        this.deleteSpringBack = animateParamTwo.getTwoAnimateBackdelete();
        this.settingSpringBack = animateParamTwo.getTwoAnimateBackSetting();
    }

    private final float getMaxTranslationDelta() {
        return DeviceUtil.dp2px(this.context, NotificationMenuRowParams.AnimateParamTwo.INSTANCE.getTWO_ANIMATE_THRESHOLD().get().floatValue());
    }

    /* JADX INFO: Access modifiers changed from: private */
    public final NotificationMenuRowMetaBallController2 getMetaBallController() {
        return (NotificationMenuRowMetaBallController2) this.metaBallController$delegate.getValue();
    }

    /* JADX INFO: Access modifiers changed from: private */
    public final float getScaleProgress() {
        return Math.max(1.0f - (this.settingProgress * 0.8f), 0.2f);
    }

    /* JADX WARN: Multi-variable type inference failed */
    public static void updateDrawableAlpha(float f, View view) {
        IOplusMenuRowItemView iOplusMenuRowItemView = view instanceof IOplusMenuRowItemView ? (IOplusMenuRowItemView) view : null;
        if (iOplusMenuRowItemView != null) {
            iOplusMenuRowItemView.updateAlpha(f);
        }
    }

    /* JADX WARN: Multi-variable type inference failed */
    public static void updateIconScale(float f, View view) {
        IOplusMenuRowItemView iOplusMenuRowItemView = view instanceof IOplusMenuRowItemView ? (IOplusMenuRowItemView) view : null;
        if (iOplusMenuRowItemView != null) {
            iOplusMenuRowItemView.updateScale(f);
        }
    }

    public final void animClear() {
        for (Map.Entry<View, COUISpringAnimation> entry : this.animMap.entrySet()) {
            entry.getKey();
            entry.getValue().skipToEnd();
        }
        if (this.animMap.isEmpty()) {
            return;
        }
        this.animMap.clear();
        if (LogUtil.getNormalDebug() && LogUtil.isTagValid(LogUtil.TAG_NOTIFICATION)) {
            Log.d("SystemUi--Notification", "NotificationMenuRowMetaBall-->".concat("animatorCharge-animClear: 清理动画"));
        }
    }

    public final void animateBackToOneDelet(ViewGroup viewGroup, float f, View view, float f2) {
        View childAt = viewGroup.getChildAt(0);
        if (childAt == null) {
            return;
        }
        COUISpringAnimation cOUISpringAnimation = this.animMap.get(childAt);
        if (cOUISpringAnimation != null) {
            cOUISpringAnimation.cancel();
        }
        COUISpringAnimation cOUISpringAnimation2 = new COUISpringAnimation(new FloatValueHolder());
        cOUISpringAnimation2.setMinimumVisibleChange(this.minimumVisibleChangeTwo);
        NotificationMenuRowParams.INSTANCE.loadOLiveLinkParam(cOUISpringAnimation2, this.deleteSpringBack);
        cOUISpringAnimation2.setStartValue(f2);
        animateToOneDelet.animation.1.1 r7 = new animateToOneDelet.animation.1.1(1);
        r7.this$0 = this;
        r7.$parent = view;
        r7.$child = childAt;
        VarHandle.storeStoreFence();
        cOUISpringAnimation2.addUpdateListener(r7);
        animateBackToOneDelet.animation.1.2 r8 = new animateBackToOneDelet.animation.1.2(0);
        r8.this$0 = this;
        r8.$child = childAt;
        r8.$parent = view;
        VarHandle.storeStoreFence();
        cOUISpringAnimation2.addEndListener(r8);
        cOUISpringAnimation2.animateToFinalPosition(0.0f);
        this.animMap.put(childAt, cOUISpringAnimation2);
        Log.d(TAG, "animateBackToOneDelet: 启动动画");
    }

    public final void animateBackToOneSetting(ViewGroup viewGroup, float f, View view, float f2) {
        View childAt = viewGroup.getChildAt(1);
        if (childAt == null) {
            return;
        }
        float fResolveDefaultTranslationX = this.deviceParams.resolveDefaultTranslationX(view.getWidth(), 1, getGridWidth(), getChildrenWidth(), getSpaceExtra());
        COUISpringAnimation cOUISpringAnimation = this.animMap.get(childAt);
        if (cOUISpringAnimation != null) {
            cOUISpringAnimation.cancel();
        }
        childAt.setAlpha(1.0f);
        Drawable background = childAt.getBackground();
        if (background == null) {
            return;
        }
        COUISpringAnimation cOUISpringAnimation2 = new COUISpringAnimation(new FloatValueHolder());
        cOUISpringAnimation2.setMinimumVisibleChange(this.minimumVisibleChangeTwo);
        NotificationMenuRowParams.INSTANCE.loadOLiveLinkParam(cOUISpringAnimation2, this.settingSpringBack);
        cOUISpringAnimation2.setStartValue(f2);
        animateBackToOneSetting.animation.1.1 r11 = new animateBackToOneSetting.animation.1.1();
        r11.this$0 = this;
        r11.$child = childAt;
        r11.$defaultTranslationDelta = fResolveDefaultTranslationX;
        r11.$background = background;
        r11.$container = viewGroup;
        VarHandle.storeStoreFence();
        cOUISpringAnimation2.addUpdateListener(r11);
        animateBackToOneDelet.animation.1.2 r10 = new animateBackToOneDelet.animation.1.2(1);
        r10.this$0 = this;
        r10.$child = childAt;
        r10.$parent = viewGroup;
        VarHandle.storeStoreFence();
        cOUISpringAnimation2.addEndListener(r10);
        cOUISpringAnimation2.animateToFinalPosition(0.0f);
        this.animMap.put(childAt, cOUISpringAnimation2);
        Log.d(TAG, "animateBackToOneSetting: 启动动画");
    }

    public final void animateToOneDelet(ViewGroup viewGroup, float f, View view, float f2) {
        View childAt = viewGroup.getChildAt(0);
        if (childAt == null) {
            return;
        }
        COUISpringAnimation cOUISpringAnimation = this.animMap.get(childAt);
        if (cOUISpringAnimation != null) {
            cOUISpringAnimation.cancel();
        }
        COUISpringAnimation cOUISpringAnimation2 = new COUISpringAnimation(new FloatValueHolder());
        cOUISpringAnimation2.setMinimumVisibleChange(this.minimumVisibleChangeTwo);
        NotificationMenuRowParams.INSTANCE.loadOLiveLinkParam(cOUISpringAnimation2, this.deleteSpring);
        cOUISpringAnimation2.setStartValue(f2);
        animateToOneDelet.animation.1.1 r8 = new animateToOneDelet.animation.1.1(0);
        r8.this$0 = this;
        r8.$parent = view;
        r8.$child = childAt;
        VarHandle.storeStoreFence();
        cOUISpringAnimation2.addUpdateListener(r8);
        animateToOneDelet.animation.1.2 r6 = new animateToOneDelet.animation.1.2();
        r6.this$0 = this;
        r6.$child = childAt;
        r6.$parent = view;
        r6.$container = viewGroup;
        VarHandle.storeStoreFence();
        cOUISpringAnimation2.addEndListener(r6);
        cOUISpringAnimation2.animateToFinalPosition(100.0f);
        this.animMap.put(childAt, cOUISpringAnimation2);
        Log.d(TAG, "animateToOneDelet: 启动动画");
    }

    public final void animateToOneSetting(ViewGroup viewGroup, float f, View view, float f2) {
        View childAt = viewGroup.getChildAt(1);
        if (childAt == null) {
            return;
        }
        COUISpringAnimation cOUISpringAnimation = this.animMap.get(childAt);
        if (cOUISpringAnimation != null) {
            cOUISpringAnimation.cancel();
        }
        Drawable background = childAt.getBackground();
        if (background == null) {
            return;
        }
        COUISpringAnimation cOUISpringAnimation2 = new COUISpringAnimation(new FloatValueHolder());
        cOUISpringAnimation2.setMinimumVisibleChange(this.minimumVisibleChangeTwo);
        NotificationMenuRowParams.INSTANCE.loadOLiveLinkParam(cOUISpringAnimation2, this.settingSpring);
        cOUISpringAnimation2.setStartValue(f2);
        animateToOneSetting.animation.1.1 r7 = new animateToOneSetting.animation.1.1();
        r7.this$0 = this;
        r7.$child = childAt;
        r7.$background = background;
        r7.$container = viewGroup;
        VarHandle.storeStoreFence();
        cOUISpringAnimation2.addUpdateListener(r7);
        animateToOneSetting.animation.1.2 r4 = new animateToOneSetting.animation.1.2();
        r4.this$0 = this;
        r4.$child = childAt;
        VarHandle.storeStoreFence();
        cOUISpringAnimation2.addEndListener(r4);
        cOUISpringAnimation2.animateToFinalPosition(100.0f);
        this.animMap.put(childAt, cOUISpringAnimation2);
        Log.d(TAG, "animateToOneSetting: 启动动画");
    }

    public final int getChildrenHeight() {
        return this.viewInfo.getIconSize(false).getHeight();
    }

    public final int getChildrenMargin() {
        return this.viewInfo.getIconMargin();
    }

    public final int getChildrenWidth() {
        return this.viewInfo.getIconSize(false).getWidth();
    }

    public final float getDeleteDistance() {
        return DeviceUtil.dp2px(this.context, 12.8f);
    }

    public final int getGridWidth() {
        return getChildrenMargin() + getChildrenWidth();
    }

    public final ViewGroup getMcontainer() {
        return this.mcontainer;
    }

    public final int getSpaceExtra() {
        return this.viewInfo.getIconMarginExtra();
    }

    public final float getTranslationDelete() {
        return this.translationDelete;
    }

    public final boolean isAnimMapEmpty() {
        float f = this.deleteProgress;
        boolean z = f == 0.0f || f == 1.0f;
        float f2 = this.settingProgress;
        return this.animMap.isEmpty() && z && ((f2 > 0.0f ? 1 : (f2 == 0.0f ? 0 : -1)) == 0 || (f2 > 1.0f ? 1 : (f2 == 1.0f ? 0 : -1)) == 0);
    }

    public final void levelTwoDeleteViewFellow(View view, float f, ViewGroup viewGroup) {
        float fAbs;
        int spaceExtra;
        if (LogUtil.getNormalDebug() && LogUtil.isTagValid(LogUtil.TAG_NOTIFICATION)) {
            WeatherInfoParseHelper$.ExternalSyntheticOutline0.m("animatorCharge-", ListPopupWindow$.ExternalSyntheticOutline0.m(f, "levelTwo_DeleteViewFellow: translationX: ", " "), "NotificationMenuRowMetaBall-->", "SystemUi--Notification");
        }
        if (Math.abs(f) > getChildrenMargin() + view.getWidth()) {
            fAbs = view.getWidth();
            spaceExtra = getSpaceExtra();
        } else {
            fAbs = Math.abs(f) - getChildrenMargin();
            spaceExtra = getSpaceExtra();
        }
        float f2 = fAbs - spaceExtra;
        View childAt = viewGroup.getChildAt(0);
        if (childAt != null) {
            float fAbs2 = Math.abs(f2);
            NotificationMenuViewHorizontalState notificationMenuViewHorizontalStateResolveWidthState = this.deviceParams.resolveWidthState(view.getWidth(), childAt.getRight(), fAbs2, getSpaceExtra());
            childAt.setTranslationX(notificationMenuViewHorizontalStateResolveWidthState.getTranslationX());
            int top = childAt.getTop();
            int bottom = childAt.getBottom();
            if (LogUtil.getNormalDebug() && LogUtil.isTagValid(LogUtil.TAG_NOTIFICATION)) {
                StringBuilder sbM = FloatList$.ExternalSyntheticOutline0.m("animateMenuItems2: left=", notificationMenuViewHorizontalStateResolveWidthState.getLeft(), notificationMenuViewHorizontalStateResolveWidthState.getRight(), ", right=", ", width=");
                sbM.append(fAbs2);
                WeatherInfoParseHelper$.ExternalSyntheticOutline0.m("widthCharge-", sbM.toString(), "NotificationMenuRowMetaBall-->", "SystemUi--Notification");
            }
            childAt.layout(notificationMenuViewHorizontalStateResolveWidthState.getLeft(), top, notificationMenuViewHorizontalStateResolveWidthState.getRight(), bottom);
        }
    }

    public final void levelTwoItemViewFellow(View view, float f, float f2, ViewGroup viewGroup, int i) {
        if (LogUtil.getNormalDebug() && LogUtil.isTagValid(LogUtil.TAG_NOTIFICATION)) {
            WeatherInfoParseHelper$.ExternalSyntheticOutline0.m("animatorCharge-", ListPopupWindow$.ExternalSyntheticOutline0.m(f2, "animateMenuItems1: ", " "), "NotificationMenuRowMetaBall-->", "SystemUi--Notification");
        }
        View childAt = viewGroup.getChildAt(0);
        if (childAt != null) {
            NotificationMenuViewHorizontalState notificationMenuViewHorizontalStateResolveWidthState = this.deviceParams.resolveWidthState(view.getWidth(), childAt.getRight(), getChildrenWidth() + f2, getSpaceExtra());
            childAt.setTranslationX(notificationMenuViewHorizontalStateResolveWidthState.getTranslationX());
            childAt.layout(notificationMenuViewHorizontalStateResolveWidthState.getLeft(), childAt.getTop(), notificationMenuViewHorizontalStateResolveWidthState.getRight(), childAt.getBottom());
        }
        if (i <= 1) {
            if (LogUtil.getNormalDebug() && LogUtil.isTagValid(LogUtil.TAG_NOTIFICATION)) {
                Log.d("SystemUi--Notification", "NotificationMenuRowMetaBall-->".concat("animatorCharge-levelTwo_ItemViewFellow：没有删除按钮"));
                return;
            }
            return;
        }
        float fResolveDefaultTranslationX = this.deviceParams.resolveDefaultTranslationX(view.getWidth(), 1, getGridWidth(), getChildrenWidth(), getSpaceExtra());
        float f3 = this.deviceParams.isRtl ? fResolveDefaultTranslationX + f2 : fResolveDefaultTranslationX - f2;
        View childAt2 = viewGroup.getChildAt(1);
        if (childAt2 != null) {
            childAt2.setTranslationX(f3);
        }
    }

    public final boolean onParentTranslationUpdated(View view, float f, float f2, ViewGroup viewGroup, float f3) {
        int childCount = viewGroup.getChildCount();
        float spaceExtra = getSpaceExtra() + (getGridWidth() * childCount);
        boolean zIsAnimMapEmpty = isAnimMapEmpty();
        this.mcontainer = viewGroup;
        if (this.isDismiss && childCount > 1) {
            viewGroup.getChildAt(1).setAlpha(0.0f);
            if (!this.animMap.isEmpty()) {
                this.animMap.clear();
                this.deleteProgress = 1.0f;
                this.settingProgress = 1.0f;
            }
            levelTwoDeleteViewFellow(view, f, viewGroup);
            return true;
        }
        if (f2 < 0.0f && this.isOne && zIsAnimMapEmpty) {
            resetAll(viewGroup);
            this.lastTranslation = f;
            updateLottie(viewGroup, 0.0f);
            return true;
        }
        if (Math.abs(this.lastTranslation) > getMaxTranslationDelta() + spaceExtra && Math.abs(f) < getMaxTranslationDelta() + spaceExtra && this.isOne && !this.animMap.isEmpty()) {
            this.isOne = false;
            this.translationDelete = Math.max(0.0f, f2);
            animClear();
            startBackMenuItemsAnimation(view, f2, viewGroup, this.deleteProgress, this.settingProgress);
        } else if (Math.abs(this.lastTranslation) < getMaxTranslationDelta() + spaceExtra && Math.abs(f) > spaceExtra + getMaxTranslationDelta() && !this.isOne && !this.animMap.isEmpty()) {
            this.isOne = true;
            this.translationDelete = Math.max(0.0f, f2);
            animClear();
            startMenuItemsAnimation(view, f2, viewGroup, this.deleteProgress, this.settingProgress);
        }
        if (f2 > 0.0f) {
            if (this.isOne) {
                if (zIsAnimMapEmpty) {
                    if (f2 < getMaxTranslationDelta()) {
                        this.isOne = false;
                        startBackMenuItemsAnimation(view, f2, viewGroup, 1.0f, 1.0f);
                    } else if (childCount > 1) {
                        levelTwoDeleteViewFellow(view, f, viewGroup);
                    } else if (LogUtil.getNormalDebug() && LogUtil.isTagValid(LogUtil.TAG_NOTIFICATION)) {
                        Log.d("SystemUi--Notification", "NotificationMenuRowMetaBall-->".concat("animatorCharge-menuItemDeletAnimator ,没有删除按钮不需要合二为一"));
                    }
                } else if (LogUtil.getNormalDebug() && LogUtil.isTagValid(LogUtil.TAG_NOTIFICATION)) {
                    Log.d("SystemUi--Notification", "NotificationMenuRowMetaBall-->".concat("ERROR-menuItemDeletAnimator ,独显删除状态有没考虑到的场景"));
                }
            } else if (zIsAnimMapEmpty) {
                if (f2 >= getMaxTranslationDelta() && childCount > 1) {
                    this.isOne = true;
                    startMenuItemsAnimation(view, f2, viewGroup, 0.0f, 0.0f);
                } else {
                    levelTwoItemViewFellow(view, f, f2, viewGroup, childCount);
                }
            } else if (LogUtil.getNormalDebug() && LogUtil.isTagValid(LogUtil.TAG_NOTIFICATION)) {
                Log.d("SystemUi--Notification", "NotificationMenuRowMetaBall-->".concat("ERROR-menuItemDeletAnimator ,按钮分离状态有没考虑到的场景"));
            }
            if (childCount <= 1 || f2 <= getMaxTranslationDelta()) {
                updateLottie(viewGroup, 0.0f);
            } else {
                updateLottie(viewGroup, (f2 - getMaxTranslationDelta()) / getDeleteDistance());
            }
        } else if (LogUtil.getNormalDebug() && LogUtil.isTagValid(LogUtil.TAG_NOTIFICATION)) {
            Log.d("SystemUi--Notification", "NotificationMenuRowMetaBall-->".concat("animatorCharge-menuItemDeletAnimator ,非正常动效"));
        }
        this.translationDelete = Math.max(0.0f, f2);
        this.lastTranslation = f;
        return false;
    }

    public final void reset() {
        if (LogUtil.getNormalDebug() && LogUtil.isTagValid(LogUtil.TAG_NOTIFICATION)) {
            Log.d("SystemUi--Notification", "NotificationMenuRowMetaBall-->animatorCharge-reset");
        }
        animClear();
        this.lastTranslation = 0.0f;
        this.isDismiss = false;
        ViewGroup viewGroup = this.mcontainer;
        if (viewGroup != null) {
            resetAll(viewGroup);
        }
    }

    public final void resetAll(ViewGroup viewGroup) {
        this.isOne = false;
        this.translationDelete = 0.0f;
        this.settingProgress = 0.0f;
        this.deleteProgress = 0.0f;
        Object parent = viewGroup.getParent();
        View view = parent instanceof View ? (View) parent : null;
        if (view == null) {
            return;
        }
        int childCount = viewGroup.getChildCount();
        for (int i = 0; i < childCount; i++) {
            float fResolveDefaultTranslationX = this.deviceParams.resolveDefaultTranslationX(view.getWidth(), i, getGridWidth(), getChildrenWidth(), getSpaceExtra());
            View childAt = viewGroup.getChildAt(i);
            if (childAt != null) {
                childAt.setTranslationX(fResolveDefaultTranslationX);
            }
            View childAt2 = viewGroup.getChildAt(i);
            if (childAt2 != null) {
                resetChild(childAt2, i);
            }
        }
        getMetaBallController().clearMetaBall(viewGroup);
    }

    /* JADX WARN: Multi-variable type inference failed */
    public final void resetChild(View view, int i) {
        if (i == 0) {
            if (view.getWidth() != getChildrenWidth()) {
                NotificationMenuViewHorizontalState notificationMenuViewHorizontalStateResolveWidthState = this.deviceParams.resolveWidthState(0, view.getRight(), getChildrenWidth(), 0);
                view.layout(notificationMenuViewHorizontalStateResolveWidthState.getLeft(), view.getTop(), notificationMenuViewHorizontalStateResolveWidthState.getRight(), view.getBottom());
                return;
            } else {
                if (LogUtil.getNormalDebug() && LogUtil.isTagValid(LogUtil.TAG_NOTIFICATION)) {
                    Log.d("SystemUi--Notification", "NotificationMenuRowMetaBall-->".concat("animatorCharge-不更新按钮"));
                    return;
                }
                return;
            }
        }
        updateIconScale(getScaleProgress(), view);
        updateDrawableAlpha(1.0f, view);
        IOplusMenuRowItemView iOplusMenuRowItemView = view instanceof IOplusMenuRowItemView ? (IOplusMenuRowItemView) view : null;
        if (iOplusMenuRowItemView != null) {
            iOplusMenuRowItemView.updateBlurRatio(0.0f);
        }
        Drawable background = view.getBackground();
        float childrenWidth = this.settingProgress * 0.4f * getChildrenWidth();
        float childrenWidth2 = (1.0f - (this.settingProgress * 0.4f)) * getChildrenWidth();
        float childrenHeight = this.settingProgress * 0.4f * getChildrenHeight();
        float childrenHeight2 = (1.0f - (this.settingProgress * 0.4f)) * getChildrenHeight();
        if (background != null) {
            background.setBounds((int) childrenWidth, (int) childrenHeight, (int) childrenWidth2, (int) childrenHeight2);
        }
    }

    public final void setDismiss(boolean z) {
        this.isDismiss = z;
    }

    public final void setMcontainer(ViewGroup viewGroup) {
        this.mcontainer = viewGroup;
    }

    public final void setTranslationDelete(float f) {
        this.translationDelete = f;
    }

    public final void startBackMenuItemsAnimation(View view, float f, ViewGroup viewGroup, float f2, float f3) {
        animateBackToOneDelet(viewGroup, f, view, f2 * 100.0f);
        animateBackToOneSetting(viewGroup, f, view, f3 * 100.0f);
    }

    public final void startMenuItemsAnimation(View view, float f, ViewGroup viewGroup, float f2, float f3) {
        OplusExpandableViewStateEx oplusExpandableViewStateEx;
        ExpandableViewState viewState = ((ExpandableView) view).getViewState();
        if (((viewState == null || (oplusExpandableViewStateEx = viewState.mExpandableViewStateEx) == null) ? 0.0f : oplusExpandableViewStateEx.getNotificationStackedProgress()) == 0.0f) {
            getMetaBallController().bindMeta(viewGroup);
        }
        animateToOneDelet(viewGroup, f, view, f2 * 100.0f);
        animateToOneSetting(viewGroup, f, view, f3 * 100.0f);
    }

    public final void updateLottie(ViewGroup viewGroup, float f) {
        float fCoerceIn = RangesKt___RangesKt.coerceIn(f, 0.0f, 1.0f) / 2.0f;
        View childAt = viewGroup.getChildAt(0);
        if (childAt != null) {
            NotificationMenuRowItemLottieView notificationMenuRowItemLottieView = childAt instanceof NotificationMenuRowItemLottieView ? (NotificationMenuRowItemLottieView) childAt : null;
            if (notificationMenuRowItemLottieView != null) {
                notificationMenuRowItemLottieView.updateLottieProgress(fCoerceIn);
            }
            if (LogUtil.getNormalDebug() && LogUtil.isTagValid(LogUtil.TAG_NOTIFICATION)) {
                WeatherInfoParseHelper$.ExternalSyntheticOutline0.m("侧滑动效管理-", Protection$.ExternalSyntheticOutline0.m(fCoerceIn, "menuItemDeletAnimator ,垃圾桶动效 "), "NotificationMenuRowMetaBall-->", "SystemUi--Notification");
            }
        }
    }
}
