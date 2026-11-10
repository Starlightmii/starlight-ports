package com.oplus.systemui.notification.row;

import android.content.Context;
import android.util.Log;
import android.view.View;
import android.view.ViewGroup;
import androidx.car.app.serialization.Bundler$;
import androidx.collection.FloatList$;
import androidx.compose.animation.core.MonoSpline$;
import androidx.compose.foundation.text.input.TextFieldBuffer$;
import androidx.compose.ui.spatial.RectList$;
import androidx.constraintlayout.widget.ConstraintSet$;
import androidx.dynamicanimation.animation.FloatValueHolder;
import com.android.launcher3.icons.IconProvider$;
import com.android.mechanics.effects.VerticalTactileSurfaceRevealEffect$;
import com.android.systemui.biometrics.udfps.NormalizedTouchData$;
import com.coui.appcompat.animation.dynamicanimation.COUISpringAnimation;
import com.oplus.systemui.keyguard.clock.WeatherInfoParseHelper$;
import com.oplusos.systemui.common.olivelink.Spring;
import com.oplusos.systemui.common.util.LogUtil;
import com.oplusos.systemui.common.utils.DeviceUtil;
import java.lang.invoke.VarHandle;
import java.util.LinkedHashMap;
import java.util.Map;
import kotlin.ranges.RangesKt___RangesKt;

/* JADX INFO: compiled from: go/retraceme fd74bf9514dd0739b92d67a6d48a51eb10aaf8df760d83496b8dcfcf83e5f698 */
/* JADX INFO: loaded from: /home/starlight/Projects/Starlight Ports/.build-tmp/classes4.dex */
public final class NotificationMenuRowItemAnimatorStage1 {
    private final Map<View, COUISpringAnimation> animMap = new LinkedHashMap();
    private final Context context;
    public final float deleteDistance;
    private final NotificationMenuViewMetaBallDeviceParams deviceParams;
    public final float fadeEnd;
    public final float fadeStart;
    public float lastCursor;
    public float lastTranslation;
    public final float minimumVisibleChange;
    private final Spring oneSpring;
    public final float settingDistance;
    public float translationDelete;
    private final MenuViewInfoProvider viewInfo;

    public NotificationMenuRowItemAnimatorStage1(MenuViewInfoProvider menuViewInfoProvider, Context context, NotificationMenuViewMetaBallDeviceParams notificationMenuViewMetaBallDeviceParams) {
        this.viewInfo = menuViewInfoProvider;
        this.context = context;
        this.deviceParams = notificationMenuViewMetaBallDeviceParams;
        NotificationMenuRowParams.AnimateParamOne animateParamOne = NotificationMenuRowParams.AnimateParamOne.INSTANCE;
        this.oneSpring = animateParamOne.getOneAnimate();
        this.fadeStart = animateParamOne.getFADE_START().get().floatValue();
        this.fadeEnd = animateParamOne.getFADE_END().get().floatValue();
        this.minimumVisibleChange = animateParamOne.getMinimumVisibleChange_ONE().get().floatValue();
        this.deleteDistance = DeviceUtil.dp2px(context, animateParamOne.getDeleteDistance().get().floatValue());
        this.settingDistance = DeviceUtil.dp2px(context, animateParamOne.getSettingDistance().get().floatValue());
    }

    public static void logAndSetTranslationX(View view, float f, String str) {
        if (LogUtil.getNormalDebug() && LogUtil.isTagValid(LogUtil.TAG_NOTIFICATION)) {
            WeatherInfoParseHelper$.ExternalSyntheticOutline0.m("stage1-", NotificationMenuRowLog.simpleNameAndHash(view) + " [" + str + "] setTranslationX = " + view.getTranslationX(), "NotificationMenuRowMetaBall-->", "SystemUi--Notification");
        }
        view.setTranslationX(f);
    }

    /* JADX WARN: Multi-variable type inference failed */
    public static void updateDrawableAlpha(float f, View view) {
        if (LogUtil.getNormalDebug() && LogUtil.isTagValid(LogUtil.TAG_NOTIFICATION)) {
            WeatherInfoParseHelper$.ExternalSyntheticOutline0.m("stage1-", "NotificationMenuRowMetaBall updateDrawableAlpha " + NotificationMenuRowLog.simpleNameAndHash(view) + "  alpha = " + f, "NotificationMenuRowMetaBall-->", "SystemUi--Notification");
        }
        IOplusMenuRowItemView iOplusMenuRowItemView = view instanceof IOplusMenuRowItemView ? (IOplusMenuRowItemView) view : null;
        if (iOplusMenuRowItemView != null) {
            iOplusMenuRowItemView.updateAlpha(f);
        }
    }

    /* JADX WARN: Multi-variable type inference failed */
    public static void updateViewBlur(float f, View view) {
        IOplusMenuRowItemView iOplusMenuRowItemView = view instanceof IOplusMenuRowItemView ? (IOplusMenuRowItemView) view : null;
        if (iOplusMenuRowItemView != null) {
            iOplusMenuRowItemView.updateBlurRatio(f);
        }
    }

    public final void animClear() {
        if (LogUtil.getNormalDebug() && LogUtil.isTagValid(LogUtil.TAG_NOTIFICATION)) {
            Log.d("SystemUi--Notification", "NotificationMenuRowMetaBall-->".concat("stage1-animClear"));
        }
        for (Map.Entry<View, COUISpringAnimation> entry : this.animMap.entrySet()) {
            entry.getKey();
            entry.getValue().skipToEnd();
        }
        if (this.animMap.isEmpty()) {
            return;
        }
        this.animMap.clear();
    }

    public final void childChangeShape(ViewGroup viewGroup, View view, View view2) {
        int width = view.getWidth();
        int width2 = this.viewInfo.getIconSize(false).getWidth();
        int iconMargin = this.viewInfo.getIconMargin() + width2;
        int iconMarginExtra = this.viewInfo.getIconMarginExtra();
        int childCount = viewGroup.getChildCount();
        NotificationMenuViewHorizontalState notificationMenuViewHorizontalStateResolveWidthState = this.deviceParams.resolveWidthState(width, view2.getRight(), width2 + this.translationDelete, iconMarginExtra);
        if (!this.animMap.containsKey(view2)) {
            view2.setTranslationX(notificationMenuViewHorizontalStateResolveWidthState.getTranslationX());
        }
        view2.layout(notificationMenuViewHorizontalStateResolveWidthState.getLeft(), view2.getTop(), notificationMenuViewHorizontalStateResolveWidthState.getRight(), view2.getBottom());
        if (LogUtil.getNormalDebug() && LogUtil.isTagValid(LogUtil.TAG_NOTIFICATION)) {
            WeatherInfoParseHelper$.ExternalSyntheticOutline0.m("stage1-", TextFieldBuffer$.ExternalSyntheticOutline0.m(view2.getLeft(), view2.getRight(), "delete left:", " right:"), "NotificationMenuRowMetaBall-->", "SystemUi--Notification");
        }
        if (childCount > 1) {
            View childAt = viewGroup.getChildAt(1);
            if (this.animMap.containsKey(childAt)) {
                return;
            }
            float fResolveDefaultTranslationX = this.deviceParams.resolveDefaultTranslationX(view.getWidth(), 1, iconMargin, width2, iconMarginExtra);
            NotificationMenuViewMetaBallDeviceParams notificationMenuViewMetaBallDeviceParams = this.deviceParams;
            float f = this.translationDelete;
            childAt.setTranslationX(notificationMenuViewMetaBallDeviceParams.isRtl ? fResolveDefaultTranslationX + f : fResolveDefaultTranslationX - f);
        }
    }

    public final float getLastTranslation() {
        return this.lastTranslation;
    }

    public final float getTranslationDelete() {
        return this.translationDelete;
    }

    public final boolean isRunningAnimation() {
        return !this.animMap.isEmpty();
    }

    /* JADX WARN: Code duplicated, block: B:9:0x004f  */
    public final void onParentTranslationUpdated(View view, float f, ViewGroup viewGroup) {
        int i;
        float f2;
        boolean z;
        float f3;
        float f4;
        ViewGroup viewGroup2 = viewGroup;
        float f5 = f - this.lastTranslation;
        int width = view.getWidth();
        int width2 = this.viewInfo.getIconSize(false).getWidth();
        int iconMargin = this.viewInfo.getIconMargin();
        int iconMarginExtra = this.viewInfo.getIconMarginExtra();
        int i2 = width2 + iconMargin;
        int childCount = viewGroup2.getChildCount();
        float fAbs = Math.abs(f);
        boolean zIsLayoutRtl = view.isLayoutRtl();
        float f6 = (i2 * childCount) + iconMarginExtra;
        if (!this.animMap.isEmpty() || (Math.abs(this.lastTranslation) > f6 && this.translationDelete == 0.0f)) {
            if (childCount < 1) {
                return;
            }
            View childAt = viewGroup2.getChildAt(0);
            if (!this.animMap.containsKey(childAt)) {
                childAt.getClass();
                childChangeShape(viewGroup2, view, childAt);
            }
        }
        float f7 = this.deviceParams.isRtl ? f - width : width + f;
        String str = "SystemUi--Notification";
        if (LogUtil.getNormalDebug() && LogUtil.isTagValid(LogUtil.TAG_NOTIFICATION)) {
            float f8 = this.translationDelete;
            StringBuilder sbM = FloatList$.ExternalSyntheticOutline0.m("START---- \n parentWidth = ", width, width2, ", \n childrenWidth = ", ", \n childrenMargin = ");
            RectList$.ExternalSyntheticOutline0.m(iconMargin, i2, "\n gridWidth = ", "\n childCount = ", sbM);
            IconProvider$.ExternalSyntheticOutline0.m(fAbs, childCount, "\n movedDistance = ", "\n isRtl = ", sbM);
            sbM.append(zIsLayoutRtl);
            sbM.append("\n translationDelete = ");
            sbM.append(f8);
            sbM.append("\n cursor = ");
            sbM.append(f7);
            WeatherInfoParseHelper$.ExternalSyntheticOutline0.m("stage1-", sbM.toString(), "NotificationMenuRowMetaBall-->", "SystemUi--Notification");
        }
        int i3 = 0;
        while (i3 < childCount) {
            View childAt2 = viewGroup2.getChildAt(i3);
            float f9 = i3 == 0 ? this.deleteDistance : this.settingDistance;
            float f10 = width2;
            float f11 = f9 / (f10 + f9);
            boolean z2 = this.deviceParams.isRtl;
            float f12 = z2 ? ((i3 * i2) + iconMarginExtra) - width : (width - (i3 * i2)) - iconMarginExtra;
            float f13 = z2 != 0 ? f12 + f10 + f9 : (f12 - f10) - f9;
            float f14 = z2 ? f13 - f9 : f13 + f9;
            int i4 = width;
            float translationX = childAt2.getTranslationX();
            int i5 = width2;
            float f15 = f5 * f11;
            int i6 = i2;
            float f16 = translationX + f15;
            if (LogUtil.getNormalDebug() && LogUtil.isTagValid(LogUtil.TAG_NOTIFICATION)) {
                StringBuilder sbM2 = MonoSpline$.ExternalSyntheticOutline0.m("child move = ---- \n diff = ", f9, ", \n followStartX = ", f12, ", \n followEndX = ");
                VerticalTactileSurfaceRevealEffect$.ExternalSyntheticOutline0.m(sbM2, f13, "\n animStartX = ", f13, "\n animEndX = ");
                VerticalTactileSurfaceRevealEffect$.ExternalSyntheticOutline0.m(sbM2, f14, "\n currentX = ", translationX, "\n moveDiff = ");
                sbM2.append(f15);
                sbM2.append("\n newX = ");
                sbM2.append(f16);
                WeatherInfoParseHelper$.ExternalSyntheticOutline0.m("stage1-", sbM2.toString(), "NotificationMenuRowMetaBall-->", str);
            }
            if ((f7 <= f12 || zIsLayoutRtl) && (f7 > f12 || zIsLayoutRtl == 0)) {
                if ((f7 > f13 || zIsLayoutRtl != 0) && (f7 < f13 || zIsLayoutRtl == 0)) {
                    String str2 = str;
                    i = i3;
                    str = str2;
                    f2 = f5;
                    z = zIsLayoutRtl;
                    if (!this.deviceParams.isRtl ? f16 > f13 : f16 < f13) {
                        updateDrawableAlpha(1.0f, childAt2);
                        updateViewBlur(0.0f, childAt2);
                        this.deviceParams.getClass();
                        if (!NotificationMenuViewMetaBallDeviceParams.isDragBack(f2, z)) {
                            startReboundAnimation(childAt2, i, translationX, f14, view, viewGroup);
                        }
                    } else {
                        float fAbs2 = Math.abs(f7 - f12) / Math.abs(f13 - f12);
                        float fCoerceIn = RangesKt___RangesKt.coerceIn(fAbs2, 0.0f, 1.0f);
                        float f17 = this.fadeStart;
                        float f18 = this.fadeEnd;
                        if (fCoerceIn <= f17) {
                            f3 = 0.0f;
                        } else {
                            f3 = fCoerceIn >= f18 ? 1.0f : (fCoerceIn - f17) / (f18 - f17);
                        }
                        updateDrawableAlpha(f3, childAt2);
                        float fCoerceIn2 = RangesKt___RangesKt.coerceIn(fAbs2, 0.0f, 1.0f);
                        if (fCoerceIn2 <= f17) {
                            f4 = 15.0f;
                        } else {
                            f4 = fCoerceIn2 >= f18 ? 0.0f : (1.0f - ((fCoerceIn2 - f17) / (f18 - f17))) * 15.0f;
                        }
                        updateViewBlur(f4, childAt2);
                        this.deviceParams.getClass();
                        if (!NotificationMenuViewMetaBallDeviceParams.isDragBack(f2, z) && !this.animMap.containsKey(childAt2)) {
                            logAndSetTranslationX(childAt2, f16, "follow");
                        }
                    }
                    viewGroup2 = viewGroup;
                    i3 = i + 1;
                    str = str;
                    f5 = f2;
                    zIsLayoutRtl = z;
                    width = i4;
                    width2 = i5;
                    i2 = i6;
                    childCount = childCount;
                } else {
                    float f19 = this.lastCursor;
                    if ((f19 <= f13 || zIsLayoutRtl != 0) && (f19 >= f13 || zIsLayoutRtl == 0)) {
                        str = str;
                        f2 = f5;
                        z = zIsLayoutRtl;
                        i = i3;
                        if (LogUtil.getNormalDebug() && LogUtil.isTagValid(LogUtil.TAG_NOTIFICATION)) {
                            WeatherInfoParseHelper$.ExternalSyntheticOutline0.m("stage1-", NotificationMenuRowLog.simpleNameAndHash(childAt2) + " skip update overRange,cursor = " + f7 + ",lastCursor = " + this.lastCursor + ",followEndX X= " + f13, "NotificationMenuRowMetaBall-->", str);
                        }
                    } else {
                        if (LogUtil.getNormalDebug() && LogUtil.isTagValid(LogUtil.TAG_NOTIFICATION)) {
                            float f20 = this.lastCursor;
                            StringBuilder sbM3 = NormalizedTouchData$.ExternalSyntheticOutline0.m(f7, i3, "child [", "] animation start ", ", ");
                            VerticalTactileSurfaceRevealEffect$.ExternalSyntheticOutline0.m(sbM3, f20, ", ", f13, ", ");
                            sbM3.append(f12);
                            WeatherInfoParseHelper$.ExternalSyntheticOutline0.m("stage1-", sbM3.toString(), "NotificationMenuRowMetaBall-->", str);
                        }
                        updateDrawableAlpha(1.0f, childAt2);
                        updateViewBlur(0.0f, childAt2);
                        this.deviceParams.getClass();
                        f2 = f5;
                        z = zIsLayoutRtl;
                        if (!NotificationMenuViewMetaBallDeviceParams.isDragBack(f2, z)) {
                            str = str;
                            i = i3;
                            startReboundAnimation(childAt2, i, translationX, f14, view, viewGroup);
                        }
                    }
                }
                viewGroup2 = viewGroup;
                i3 = i + 1;
                str = str;
                f5 = f2;
                zIsLayoutRtl = z;
                width = i4;
                width2 = i5;
                i2 = i6;
                childCount = childCount;
            } else {
                if (LogUtil.getNormalDebug() && LogUtil.isTagValid(LogUtil.TAG_NOTIFICATION)) {
                    WeatherInfoParseHelper$.ExternalSyntheticOutline0.m("stage1-", NotificationMenuRowLog.simpleNameAndHash(childAt2) + " skip update underRange cursor = " + f7 + ",followEndX = " + f13 + ",followStart X= " + f12, "NotificationMenuRowMetaBall-->", str);
                }
                f2 = f5;
                z = zIsLayoutRtl;
            }
            i = i3;
            viewGroup2 = viewGroup;
            i3 = i + 1;
            str = str;
            f5 = f2;
            zIsLayoutRtl = z;
            width = i4;
            width2 = i5;
            i2 = i6;
            childCount = childCount;
        }
        this.lastCursor = f7;
    }

    public final void setLastTranslation(float f) {
        this.lastTranslation = f;
    }

    public final void setTranslationDelete(float f) {
        this.translationDelete = f;
    }

    public final void startReboundAnimation(View view, int i, float f, float f2, View view2, ViewGroup viewGroup) {
        if (LogUtil.getNormalDebug() && LogUtil.isTagValid(LogUtil.TAG_NOTIFICATION)) {
            StringBuilder sbM = ConstraintSet$.ExternalSyntheticOutline0.m(i, "startReboundAnimation --- ", NotificationMenuRowLog.simpleNameAndHash(view), ", ", ", ");
            sbM.append(f);
            sbM.append(" to ");
            sbM.append(f2);
            WeatherInfoParseHelper$.ExternalSyntheticOutline0.m("stage1-", sbM.toString(), "NotificationMenuRowMetaBall-->", "SystemUi--Notification");
        }
        if (this.animMap.containsKey(view)) {
            return;
        }
        logAndSetTranslationX(view, f, "start animate");
        float fAbs = Math.abs(f - f2);
        COUISpringAnimation cOUISpringAnimation = new COUISpringAnimation(new FloatValueHolder());
        cOUISpringAnimation.setMinimumVisibleChange(this.minimumVisibleChange);
        NotificationMenuRowParams.INSTANCE.loadOLiveLinkParam(cOUISpringAnimation, this.oneSpring);
        cOUISpringAnimation.setStartValue(fAbs);
        animateToOriginalPosition.1.1 r10 = new animateToOriginalPosition.1.1();
        r10.this$0 = this;
        r10.$toX = f2;
        r10.$child = view;
        r10.$childIndex = i;
        r10.$container = viewGroup;
        r10.$parent = view2;
        VarHandle.storeStoreFence();
        cOUISpringAnimation.addUpdateListener(r10);
        animateToOriginalPosition.1.2 r11 = new animateToOriginalPosition.1.2();
        r11.this$0 = this;
        r11.$child = view;
        r11.$toX = f2;
        r11.$childIndex = i;
        r11.$container = viewGroup;
        r11.$parent = view2;
        VarHandle.storeStoreFence();
        cOUISpringAnimation.addEndListener(r11);
        this.animMap.put(view, cOUISpringAnimation);
        cOUISpringAnimation.animateToFinalPosition(0.0f);
        if (LogUtil.getNormalDebug() && LogUtil.isTagValid(LogUtil.TAG_NOTIFICATION)) {
            WeatherInfoParseHelper$.ExternalSyntheticOutline0.m("stage1-", Bundler$.ExternalSyntheticOutline0.m("child ", NotificationMenuRowLog.simpleNameAndHash(view), " over Range start animation"), "NotificationMenuRowMetaBall-->", "SystemUi--Notification");
        }
    }

    public final void updateChildState(ViewGroup viewGroup) {
        int childCount = viewGroup.getChildCount();
        for (int i = 0; i < childCount; i++) {
            View childAt = viewGroup.getChildAt(i);
            childAt.getClass();
            updateDrawableAlpha(1.0f, childAt);
            updateViewBlur(0.0f, childAt);
        }
    }
}
