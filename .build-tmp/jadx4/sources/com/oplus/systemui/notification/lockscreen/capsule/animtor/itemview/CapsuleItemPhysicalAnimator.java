package com.oplus.systemui.notification.lockscreen.capsule.animtor.itemview;

import android.content.Context;
import android.util.Log;
import android.view.View;
import android.widget.FrameLayout;
import android.widget.TextView;
import com.android.systemui.application.impl.SystemUIApplicationImpl$;
import com.coui.appcompat.animation.dynamicanimation.COUIDynamicAnimation;
import com.coui.appcompat.animation.dynamicanimation.COUISpringAnimation;
import com.oplus.systemui.notification.lockscreen.capsule.CapsuleItemView;
import com.oplus.systemui.notification.lockscreen.capsule.NotificationCapsuleContainer;
import com.oplus.systemui.notification.lockscreen.capsule.utils.CapsuleViewExpandKtKt;
import com.oplus.systemui.notification.lockscreen.capsule.utils.ViewExpandKtKt;
import com.oplus.systemui.notification.lockscreen.config.CapsuleConfig;
import com.oplus.systemui.notification.lockscreen.config.CapsuleDebug;
import com.oplus.systemui.notification.lockscreen.effect.CapsuleAnimUtilsKt;
import com.oplus.systemui.notification.lockscreen.notification.CapsuleNotificationCardView;
import com.oplus.systemui.notification.lockscreen.notification.CapsuleNotificationContainerView;
import com.oplus.systemui.notification.lockscreen.olivelink.CapsuleInternalAnimParams;
import com.oplus.systemui.notification.lockscreen.olivelink.CapsuleSpringAnimParams;
import com.oplusos.systemui.common.olivelink.Spring;
import com.oplusos.systemui.common.util.LogUtil;
import java.lang.invoke.VarHandle;
import kotlin.Pair;
import kotlin.TuplesKt;
import kotlin.jvm.functions.Function0;

/* JADX INFO: compiled from: go/retraceme fd74bf9514dd0739b92d67a6d48a51eb10aaf8df760d83496b8dcfcf83e5f698 */
/* JADX INFO: loaded from: /home/starlight/Projects/Starlight Ports/.build-tmp/classes4.dex */
public final class CapsuleItemPhysicalAnimator extends CapsuleItemBaseAnimator {
    public static final Companion Companion = new Companion();
    public static final String TAG = "CapsuleItemAnimator";

    /* JADX INFO: compiled from: go/retraceme fd74bf9514dd0739b92d67a6d48a51eb10aaf8df760d83496b8dcfcf83e5f698 */
    public final class Companion {
    }

    public CapsuleItemPhysicalAnimator(NotificationCapsuleContainer notificationCapsuleContainer, Function0 function0) {
        super(notificationCapsuleContainer, function0);
    }

    public static COUISpringAnimation createIconBounceAnimation(CapsuleNotificationCardView capsuleNotificationCardView, int i, Spring spring, float f) {
        FrameLayout iconTouchArea = capsuleNotificationCardView.getIconTouchArea();
        if (iconTouchArea.getWidth() == 0 || capsuleNotificationCardView.getWidth() == 0) {
            return null;
        }
        int[] iArr = new int[2];
        int[] iArr2 = new int[2];
        iconTouchArea.getLocationInWindow(iArr);
        capsuleNotificationCardView.getLocationInWindow(iArr2);
        float width = ((capsuleNotificationCardView.getWidth() / 2.0f) + iArr2[0]) - ((iconTouchArea.getWidth() / 2.0f) + iArr[0]);
        COUIDynamicAnimation.1 r2 = COUIDynamicAnimation.TRANSLATION_X;
        if (i == 1) {
            COUISpringAnimation cOUISpringAnimationViewAnim = CapsuleAnimUtilsKt.viewAnim(spring, iconTouchArea, r2, 0.0f, width, 0.1f);
            cOUISpringAnimationViewAnim.setStartVelocity(f);
            return cOUISpringAnimationViewAnim;
        }
        if (i != 2) {
            return null;
        }
        iconTouchArea.setTranslationX(width);
        COUISpringAnimation cOUISpringAnimationViewAnim2 = CapsuleAnimUtilsKt.viewAnim(spring, iconTouchArea, r2, width, 0.0f, 0.1f);
        cOUISpringAnimationViewAnim2.setStartVelocity(f);
        return cOUISpringAnimationViewAnim2;
    }

    public static void logUpdater(CapsuleItemView capsuleItemView, String str) {
        if (CapsuleDebug.INSTANCE.getLogSwitchItemViewUpdate() && LogUtil.getNormalDebug() && LogUtil.isTagValid(LogUtil.TAG_NOTIFICATION)) {
            Log.d("SystemUi--Notification", "CapsuleItemAnimator-->".concat("PhysicalAnimUpdater(" + capsuleItemView + "): " + str));
        }
    }

    /* JADX WARN: Code duplicated, block: B:15:0x0068  */
    /* JADX WARN: Multi-variable type inference failed */
    @Override // com.oplus.systemui.notification.lockscreen.capsule.animtor.itemview.CapsuleItemBaseAnimator
    public void startAnimationImpl(CapsuleItemView capsuleItemView, CapsuleItemView capsuleItemView2, boolean z) {
        String str;
        WidthAnimController widthAnimController;
        CapsuleNotificationCardView currentView;
        CapsuleNotificationCardView currentView2;
        Pair pair;
        MultiWidthAnimController multiWidthAnimController;
        COUISpringAnimation switchSpring;
        COUISpringAnimation disappearSpring;
        COUISpringAnimation appearSpring;
        int i = 1;
        if (capsuleItemView == 0 || capsuleItemView2 == 0) {
            str = LogUtil.TAG_NOTIFICATION;
            if (capsuleItemView != 0) {
                CapsuleSpringAnimParams.AppearParams appearParams = CapsuleSpringAnimParams.AppearParams.INSTANCE;
                CapsuleSpringAnimParams.CapsuleItemSpringData capsuleItemSpringDataBuildCapsuleItemSpringData$default = CapsuleSpringAnimParams.CapsuleItemSpringDataBuilder.buildCapsuleItemSpringData$default(appearParams, 0.0f, CapsuleViewExpandKtKt.getCurrentCardShowWidth(getHostLayout()), 0.0f, CapsuleConfig.INSTANCE.getItemHeight(), 5);
                CapsuleItemPhysicalAnimator$$ExternalSyntheticLambda0 capsuleItemPhysicalAnimator$$ExternalSyntheticLambda0 = new CapsuleItemPhysicalAnimator$$ExternalSyntheticLambda0(4);
                capsuleItemPhysicalAnimator$$ExternalSyntheticLambda0.f$0 = this;
                capsuleItemPhysicalAnimator$$ExternalSyntheticLambda0.f$1 = capsuleItemView;
                VarHandle.storeStoreFence();
                CapsuleItemPhysicalAnimator$$ExternalSyntheticLambda0 capsuleItemPhysicalAnimator$$ExternalSyntheticLambda1 = new CapsuleItemPhysicalAnimator$$ExternalSyntheticLambda0(5);
                capsuleItemPhysicalAnimator$$ExternalSyntheticLambda1.f$0 = this;
                capsuleItemPhysicalAnimator$$ExternalSyntheticLambda1.f$1 = capsuleItemView;
                VarHandle.storeStoreFence();
                Context context = getContext();
                CapsuleItemPhysicalAnimator$.ExternalSyntheticLambda2 externalSyntheticLambda2 = new CapsuleItemPhysicalAnimator$.ExternalSyntheticLambda2(1);
                externalSyntheticLambda2.f$1 = capsuleItemView;
                externalSyntheticLambda2.f$0 = this;
                VarHandle.storeStoreFence();
                SingleWidthAnimController singleWidthAnimController = new SingleWidthAnimController(capsuleItemSpringDataBuildCapsuleItemSpringData$default, capsuleItemPhysicalAnimator$$ExternalSyntheticLambda0, capsuleItemPhysicalAnimator$$ExternalSyntheticLambda1, new CapsuleItemBaseAnimator.ItemAnimatorUpdater(context, externalSyntheticLambda2));
                singleWidthAnimController.getChildAnims().add(CapsuleAnimUtilsKt.viewAlphaAnim(appearParams.getAlphaSpring(), capsuleItemView, capsuleItemSpringDataBuildCapsuleItemSpringData$default.getMinAlpha(), 1.0f, 1.0E-4f));
                singleWidthAnimController.getChildAnims().add(CapsuleAnimUtilsKt.viewTransYAnim(appearParams.getTransYSpring(), capsuleItemView.getContentContainer(), capsuleItemSpringDataBuildCapsuleItemSpringData$default.getMaxTransY(), 0.0f, 1.0E-4f));
                widthAnimController = singleWidthAnimController;
            } else if (capsuleItemView2 != 0) {
                CapsuleSpringAnimParams.DisappearParams disappearParams = CapsuleSpringAnimParams.DisappearParams.INSTANCE;
                CapsuleSpringAnimParams.CapsuleItemSpringData capsuleItemSpringDataBuildCapsuleItemSpringData$default2 = CapsuleSpringAnimParams.CapsuleItemSpringDataBuilder.buildCapsuleItemSpringData$default(disappearParams, capsuleItemView2.getActualWidth$vendor__oplus__packages__apps__systemui__oplus_systemui__android_common__Oplus_SystemUI_application_domestic(), 0.0f, CapsuleConfig.INSTANCE.getItemHeight(), 0.0f, 10);
                CapsuleItemPhysicalAnimator$$ExternalSyntheticLambda0 capsuleItemPhysicalAnimator$$ExternalSyntheticLambda2 = new CapsuleItemPhysicalAnimator$$ExternalSyntheticLambda0(0);
                capsuleItemPhysicalAnimator$$ExternalSyntheticLambda2.f$0 = this;
                capsuleItemPhysicalAnimator$$ExternalSyntheticLambda2.f$1 = capsuleItemView2;
                VarHandle.storeStoreFence();
                CapsuleItemPhysicalAnimator$$ExternalSyntheticLambda0 capsuleItemPhysicalAnimator$$ExternalSyntheticLambda3 = new CapsuleItemPhysicalAnimator$$ExternalSyntheticLambda0(i);
                capsuleItemPhysicalAnimator$$ExternalSyntheticLambda3.f$0 = this;
                capsuleItemPhysicalAnimator$$ExternalSyntheticLambda3.f$1 = capsuleItemView2;
                VarHandle.storeStoreFence();
                Context context2 = getContext();
                CapsuleItemPhysicalAnimator$.ExternalSyntheticLambda2 externalSyntheticLambda3 = new CapsuleItemPhysicalAnimator$.ExternalSyntheticLambda2(0);
                externalSyntheticLambda3.f$0 = this;
                externalSyntheticLambda3.f$1 = capsuleItemView2;
                VarHandle.storeStoreFence();
                SingleWidthAnimController singleWidthAnimController2 = new SingleWidthAnimController(capsuleItemSpringDataBuildCapsuleItemSpringData$default2, capsuleItemPhysicalAnimator$$ExternalSyntheticLambda2, capsuleItemPhysicalAnimator$$ExternalSyntheticLambda3, new CapsuleItemBaseAnimator.ItemAnimatorUpdater(context2, externalSyntheticLambda3));
                singleWidthAnimController2.getChildAnims().add(CapsuleAnimUtilsKt.viewAlphaAnim(disappearParams.getAlphaSpring(), capsuleItemView2, 1.0f, capsuleItemSpringDataBuildCapsuleItemSpringData$default2.getMinAlpha(), 1.0E-4f));
                ViewExpandKtKt.setPivotXY(capsuleItemView2, capsuleItemView2.getContentContainer().getWidth() / 2.0f, capsuleItemView2.getContentContainer().getHeight() / 2.0f);
                singleWidthAnimController2.getChildAnims().add(CapsuleAnimUtilsKt.viewScaleXAnim(disappearParams.getScaleSpring(), capsuleItemView2.getContentContainer(), 1.0f, capsuleItemSpringDataBuildCapsuleItemSpringData$default2.getMinScale(), 1.0E-4f));
                singleWidthAnimController2.getChildAnims().add(CapsuleAnimUtilsKt.viewScaleYAnim(disappearParams.getScaleSpring(), capsuleItemView2.getContentContainer(), 1.0f, capsuleItemSpringDataBuildCapsuleItemSpringData$default2.getMinScale(), 1.0E-4f));
                widthAnimController = singleWidthAnimController2;
            } else {
                widthAnimController = null;
            }
        } else {
            CapsuleSpringAnimParams.SwitchParams switchParams = CapsuleSpringAnimParams.SwitchParams.INSTANCE;
            float actualWidth$vendor__oplus__packages__apps__systemui__oplus_systemui__android_common__Oplus_SystemUI_application_domestic = capsuleItemView2.getActualWidth$vendor__oplus__packages__apps__systemui__oplus_systemui__android_common__Oplus_SystemUI_application_domestic();
            float currentCardShowWidth = CapsuleViewExpandKtKt.getCurrentCardShowWidth(getHostLayout());
            CapsuleConfig capsuleConfig = CapsuleConfig.INSTANCE;
            CapsuleSpringAnimParams.CapsuleItemSpringData capsuleItemSpringDataBuildCapsuleItemSpringData = switchParams.buildCapsuleItemSpringData(actualWidth$vendor__oplus__packages__apps__systemui__oplus_systemui__android_common__Oplus_SystemUI_application_domestic, currentCardShowWidth, capsuleConfig.getItemHeight(), capsuleConfig.getItemHeight());
            if (LogUtil.getNormalDebug() && LogUtil.isTagValid(LogUtil.TAG_NOTIFICATION)) {
                Log.d("SystemUi--Notification", "CapsuleItemAnimator-->".concat("MultiWidthUpdaterSpringData:" + capsuleItemSpringDataBuildCapsuleItemSpringData));
            }
            View contentView = capsuleItemView.getEntry().getContentView();
            CapsuleNotificationContainerView capsuleNotificationContainerView = contentView instanceof CapsuleNotificationContainerView ? (CapsuleNotificationContainerView) contentView : null;
            if (capsuleNotificationContainerView != null && capsuleNotificationContainerView.getHasNewNotificationForCurrentApp()) {
                View contentView2 = capsuleItemView2.getEntry().getContentView();
                CapsuleNotificationContainerView capsuleNotificationContainerView2 = contentView2 instanceof CapsuleNotificationContainerView ? (CapsuleNotificationContainerView) contentView2 : null;
                if (capsuleNotificationContainerView2 == null || (currentView = capsuleNotificationContainerView2.getCurrentView()) == null || (currentView2 = capsuleNotificationContainerView.getCurrentView()) == null) {
                    pair = null;
                } else {
                    pair = TuplesKt.to(currentView, currentView2);
                }
            } else {
                pair = null;
            }
            Context context3 = getContext();
            CapsuleItemPhysicalAnimator$$ExternalSyntheticLambda3 capsuleItemPhysicalAnimator$$ExternalSyntheticLambda4 = new CapsuleItemPhysicalAnimator$$ExternalSyntheticLambda3();
            capsuleItemPhysicalAnimator$$ExternalSyntheticLambda4.f$0 = this;
            capsuleItemPhysicalAnimator$$ExternalSyntheticLambda4.f$1 = capsuleItemView;
            capsuleItemPhysicalAnimator$$ExternalSyntheticLambda4.f$2 = capsuleItemView2;
            capsuleItemPhysicalAnimator$$ExternalSyntheticLambda4.f$3 = z;
            VarHandle.storeStoreFence();
            CapsuleItemBaseAnimator.ItemAnimatorUpdater itemAnimatorUpdater = new CapsuleItemBaseAnimator.ItemAnimatorUpdater(context3, capsuleItemPhysicalAnimator$$ExternalSyntheticLambda4);
            boolean z2 = pair == null;
            CapsuleItemPhysicalAnimator$$ExternalSyntheticLambda4 capsuleItemPhysicalAnimator$$ExternalSyntheticLambda5 = new CapsuleItemPhysicalAnimator$$ExternalSyntheticLambda4();
            capsuleItemPhysicalAnimator$$ExternalSyntheticLambda5.f$0 = this;
            capsuleItemPhysicalAnimator$$ExternalSyntheticLambda5.f$1 = capsuleItemView;
            capsuleItemPhysicalAnimator$$ExternalSyntheticLambda5.f$2 = z;
            VarHandle.storeStoreFence();
            CapsuleItemPhysicalAnimator$$ExternalSyntheticLambda0 capsuleItemPhysicalAnimator$$ExternalSyntheticLambda6 = new CapsuleItemPhysicalAnimator$$ExternalSyntheticLambda0(2);
            capsuleItemPhysicalAnimator$$ExternalSyntheticLambda6.f$0 = this;
            capsuleItemPhysicalAnimator$$ExternalSyntheticLambda6.f$1 = capsuleItemView;
            VarHandle.storeStoreFence();
            CapsuleItemPhysicalAnimator$$ExternalSyntheticLambda6 capsuleItemPhysicalAnimator$$ExternalSyntheticLambda7 = new CapsuleItemPhysicalAnimator$$ExternalSyntheticLambda6();
            capsuleItemPhysicalAnimator$$ExternalSyntheticLambda7.f$0 = this;
            capsuleItemPhysicalAnimator$$ExternalSyntheticLambda7.f$1 = capsuleItemView;
            capsuleItemPhysicalAnimator$$ExternalSyntheticLambda7.f$2 = capsuleItemView2;
            VarHandle.storeStoreFence();
            CapsuleItemPhysicalAnimator$$ExternalSyntheticLambda0 capsuleItemPhysicalAnimator$$ExternalSyntheticLambda8 = new CapsuleItemPhysicalAnimator$$ExternalSyntheticLambda0(3);
            capsuleItemPhysicalAnimator$$ExternalSyntheticLambda8.f$0 = this;
            capsuleItemPhysicalAnimator$$ExternalSyntheticLambda8.f$1 = capsuleItemView2;
            VarHandle.storeStoreFence();
            multiWidthAnimController = new MultiWidthAnimController(capsuleItemSpringDataBuildCapsuleItemSpringData, capsuleItemPhysicalAnimator$$ExternalSyntheticLambda5, capsuleItemPhysicalAnimator$$ExternalSyntheticLambda6, capsuleItemPhysicalAnimator$$ExternalSyntheticLambda7, capsuleItemPhysicalAnimator$$ExternalSyntheticLambda8, itemAnimatorUpdater, z2);
            if (pair != null) {
                CapsuleNotificationCardView capsuleNotificationCardView = (CapsuleNotificationCardView) pair.component1();
                CapsuleNotificationCardView capsuleNotificationCardView2 = (CapsuleNotificationCardView) pair.component2();
                FrameLayout numViewContainer = capsuleNotificationCardView.getNumViewContainer();
                boolean z3 = numViewContainer != null && numViewContainer.getVisibility() == 0;
                FrameLayout numViewContainer2 = capsuleNotificationCardView2.getNumViewContainer();
                boolean z4 = numViewContainer2 != null && numViewContainer2.getVisibility() == 0;
                if (LogUtil.getNormalDebug() && LogUtil.isTagValid(LogUtil.TAG_NOTIFICATION)) {
                    Log.d("SystemUi--Notification", SystemUIApplicationImpl$.ExternalSyntheticOutline0.m("newStyleAnim: oldHasBadge: ", ", newHasBadge: ", "CapsuleItemAnimator-->", z3, z4));
                }
                CapsuleInternalAnimParams.SameAppNotificationParams.OldCardInternal oldCardInternal = CapsuleInternalAnimParams.SameAppNotificationParams.OldCardInternal.INSTANCE;
                COUISpringAnimation cOUISpringAnimationCreateIconBounceAnimation = createIconBounceAnimation(capsuleNotificationCardView, 1, oldCardInternal.getIconSpring(), oldCardInternal.getIconVelocity().get().floatValue());
                if (cOUISpringAnimationCreateIconBounceAnimation != null) {
                    multiWidthAnimController.getP1AnimSet().add(cOUISpringAnimationCreateIconBounceAnimation);
                }
                FrameLayout numViewContainer3 = capsuleNotificationCardView.getNumViewContainer();
                if (numViewContainer3 != null && (!z3 || !z4)) {
                    numViewContainer3.setVisibility(8);
                }
                TextView titleView = capsuleNotificationCardView.getTitleView();
                TextView textView = capsuleNotificationCardView.getTextView();
                ViewExpandKtKt.setPivotXY(titleView, titleView.getWidth() / 2.0f, titleView.getHeight() / 2.0f);
                ViewExpandKtKt.setPivotXY(textView, textView.getWidth() / 2.0f, textView.getHeight() / 2.0f);
                float fFloatValue = oldCardInternal.getTextMaxBlur().get().floatValue();
                boolean z5 = z3;
                boolean z6 = z4;
                multiWidthAnimController.getP1AnimSet().add(CapsuleAnimUtilsKt.viewScaleXAnim(oldCardInternal.getTextScaleSpring(), titleView, 1.0f, 0.0f, 1.0E-4f));
                multiWidthAnimController.getP1AnimSet().add(CapsuleAnimUtilsKt.viewScaleYAnim(oldCardInternal.getTextScaleSpring(), titleView, 1.0f, 0.0f, 1.0E-4f));
                multiWidthAnimController.getP1AnimSet().add(CapsuleAnimUtilsKt.viewScaleXAnim(oldCardInternal.getTextScaleSpring(), textView, 1.0f, 0.0f, 1.0E-4f));
                multiWidthAnimController.getP1AnimSet().add(CapsuleAnimUtilsKt.viewScaleYAnim(oldCardInternal.getTextScaleSpring(), textView, 1.0f, 0.0f, 1.0E-4f));
                multiWidthAnimController.getP1AnimSet().add(CapsuleAnimUtilsKt.viewAlphaAnim(oldCardInternal.getTextAlphaSpring(), titleView, 1.0f, 0.0f, 1.0E-4f));
                multiWidthAnimController.getP1AnimSet().add(CapsuleAnimUtilsKt.viewAlphaAnim(oldCardInternal.getTextAlphaSpring(), textView, 1.0f, 0.0f, 1.0E-4f));
                multiWidthAnimController.getP1AnimSet().add(CapsuleAnimUtilsKt.viewBlurAnim(oldCardInternal.getTextBlurSpring(), titleView, 0.0f, fFloatValue, 0.1f));
                multiWidthAnimController.getP1AnimSet().add(CapsuleAnimUtilsKt.viewBlurAnim(oldCardInternal.getTextBlurSpring(), textView, 0.0f, fFloatValue, 0.1f));
                CapsuleInternalAnimParams.SameAppNotificationParams.NewCardInternal newCardInternal = CapsuleInternalAnimParams.SameAppNotificationParams.NewCardInternal.INSTANCE;
                COUISpringAnimation cOUISpringAnimationCreateIconBounceAnimation2 = createIconBounceAnimation(capsuleNotificationCardView2, 2, newCardInternal.getIconSpring(), newCardInternal.getIconVelocity().get().floatValue());
                if (cOUISpringAnimationCreateIconBounceAnimation2 != null) {
                    multiWidthAnimController.getP2AnimSet().add(cOUISpringAnimationCreateIconBounceAnimation2);
                }
                FrameLayout numViewContainer4 = capsuleNotificationCardView2.getNumViewContainer();
                if (numViewContainer4 != null && !z5 && z6) {
                    float fFloatValue2 = newCardInternal.getBadgeMaxBlur().get().floatValue();
                    ViewExpandKtKt.setPivotXY(numViewContainer4, numViewContainer4.getWidth() / 2.0f, numViewContainer4.getHeight() / 2.0f);
                    numViewContainer4.setScaleX(0.0f);
                    numViewContainer4.setScaleY(0.0f);
                    numViewContainer4.setAlpha(0.0f);
                    CapsuleAnimUtilsKt.applyBlurToView(numViewContainer4, fFloatValue2);
                    multiWidthAnimController.getP2AnimSet().add(CapsuleAnimUtilsKt.viewScaleXAnim(newCardInternal.getBadgeScaleSpring(), numViewContainer4, 0.0f, 1.0f, 1.0E-4f));
                    multiWidthAnimController.getP2AnimSet().add(CapsuleAnimUtilsKt.viewScaleYAnim(newCardInternal.getBadgeScaleSpring(), numViewContainer4, 0.0f, 1.0f, 1.0E-4f));
                    multiWidthAnimController.getP2AnimSet().add(CapsuleAnimUtilsKt.viewAlphaAnim(newCardInternal.getBadgeAlphaSpring(), numViewContainer4, 0.0f, 1.0f, 1.0E-4f));
                    multiWidthAnimController.getP2AnimSet().add(CapsuleAnimUtilsKt.viewBlurAnim(newCardInternal.getBadgeBlurSpring(), numViewContainer4, fFloatValue2, 0.0f, 0.1f));
                }
                TextView titleView2 = capsuleNotificationCardView2.getTitleView();
                TextView textView2 = capsuleNotificationCardView2.getTextView();
                float fFloatValue3 = newCardInternal.getTextMaxBlur().get().floatValue();
                float fFloatValue4 = newCardInternal.getTextStartScale().get().floatValue();
                ViewExpandKtKt.setPivotXY(titleView2, titleView2.getWidth() / 2.0f, titleView2.getHeight() / 2.0f);
                ViewExpandKtKt.setPivotXY(textView2, textView2.getWidth() / 2.0f, textView2.getHeight() / 2.0f);
                titleView2.setScaleX(fFloatValue4);
                titleView2.setScaleY(fFloatValue4);
                titleView2.setAlpha(0.0f);
                textView2.setScaleX(fFloatValue4);
                textView2.setScaleY(fFloatValue4);
                textView2.setAlpha(0.0f);
                CapsuleAnimUtilsKt.applyBlurToView(titleView2, fFloatValue3);
                CapsuleAnimUtilsKt.applyBlurToView(textView2, fFloatValue3);
                multiWidthAnimController.getP2AnimSet().add(CapsuleAnimUtilsKt.viewScaleXAnim(newCardInternal.getTextScaleSpring(), titleView2, fFloatValue4, 1.0f, 1.0E-4f));
                multiWidthAnimController.getP2AnimSet().add(CapsuleAnimUtilsKt.viewScaleYAnim(newCardInternal.getTextScaleSpring(), titleView2, fFloatValue4, 1.0f, 1.0E-4f));
                multiWidthAnimController.getP2AnimSet().add(CapsuleAnimUtilsKt.viewScaleXAnim(newCardInternal.getTextScaleSpring(), textView2, fFloatValue4, 1.0f, 1.0E-4f));
                multiWidthAnimController.getP2AnimSet().add(CapsuleAnimUtilsKt.viewScaleYAnim(newCardInternal.getTextScaleSpring(), textView2, fFloatValue4, 1.0f, 1.0E-4f));
                multiWidthAnimController.getP2AnimSet().add(CapsuleAnimUtilsKt.viewAlphaAnim(newCardInternal.getTextAlphaSpring(), titleView2, 0.0f, 1.0f, 1.0E-4f));
                multiWidthAnimController.getP2AnimSet().add(CapsuleAnimUtilsKt.viewAlphaAnim(newCardInternal.getTextAlphaSpring(), textView2, 0.0f, 1.0f, 1.0E-4f));
                multiWidthAnimController.getP2AnimSet().add(CapsuleAnimUtilsKt.viewBlurAnim(newCardInternal.getTextBlurSpring(), titleView2, fFloatValue3, 0.0f, 0.1f));
                multiWidthAnimController.getP2AnimSet().add(CapsuleAnimUtilsKt.viewBlurAnim(newCardInternal.getTextBlurSpring(), textView2, fFloatValue3, 0.0f, 0.1f));
            } else {
                CapsuleSpringAnimParams.SwitchParams.OldCard oldCard = CapsuleSpringAnimParams.SwitchParams.OldCard.INSTANCE;
                multiWidthAnimController.getP1AnimSet().add(CapsuleAnimUtilsKt.viewScaleXAnim(oldCard.getScaleSpring(), capsuleItemView2.getContentContainer(), 1.0f, capsuleItemSpringDataBuildCapsuleItemSpringData.getMinScale(), 1.0E-4f));
                multiWidthAnimController.getP1AnimSet().add(CapsuleAnimUtilsKt.viewScaleYAnim(oldCard.getScaleSpring(), capsuleItemView2.getContentContainer(), 1.0f, capsuleItemSpringDataBuildCapsuleItemSpringData.getMinScale(), 1.0E-4f));
                multiWidthAnimController.getP1AnimSet().add(CapsuleAnimUtilsKt.viewAlphaAnim(oldCard.getAlphaSpring(), capsuleItemView2.getContentContainer(), 1.0f, capsuleItemSpringDataBuildCapsuleItemSpringData.getMinAlpha(), 1.0E-4f));
                multiWidthAnimController.getP2AnimSet().add(CapsuleAnimUtilsKt.viewTransYAnim(CapsuleSpringAnimParams.SwitchParams.NewCard.INSTANCE.getTransYSpring(), capsuleItemView.getContentContainer(), capsuleItemSpringDataBuildCapsuleItemSpringData.getMaxTransY(), 0.0f, 1.0E-4f));
            }
            int actualWidth$vendor__oplus__packages__apps__systemui__oplus_systemui__android_common__Oplus_SystemUI_application_domestic2 = capsuleItemView2.getActualWidth$vendor__oplus__packages__apps__systemui__oplus_systemui__android_common__Oplus_SystemUI_application_domestic();
            int currentCardShowWidth2 = CapsuleViewExpandKtKt.getCurrentCardShowWidth(getHostLayout());
            if (actualWidth$vendor__oplus__packages__apps__systemui__oplus_systemui__android_common__Oplus_SystemUI_application_domestic2 == capsuleConfig.getItemWidth() && currentCardShowWidth2 == capsuleConfig.getItemWidthMultiCard()) {
                if (LogUtil.getNormalDebug() && LogUtil.isTagValid(LogUtil.TAG_NOTIFICATION)) {
                    Log.d("SystemUi--Notification", "CapsuleItemAnimator-->setupEarAnim: 1-->2");
                }
                multiWidthAnimController.initEarTransX(Float.MIN_VALUE);
                CapsuleSpringAnimParams.EarTransXSpringData earTransXSpringData = capsuleItemSpringDataBuildCapsuleItemSpringData.getEarTransXSpringData();
                widthAnimController = multiWidthAnimController;
                if (earTransXSpringData != null && (appearSpring = earTransXSpringData.getAppearSpring()) != null) {
                    widthAnimController = multiWidthAnimController;
                    multiWidthAnimController.getP2AnimSet().add(appearSpring);
                    widthAnimController = multiWidthAnimController;
                }
            } else if (actualWidth$vendor__oplus__packages__apps__systemui__oplus_systemui__android_common__Oplus_SystemUI_application_domestic2 == capsuleConfig.getItemWidthMultiCard() && currentCardShowWidth2 == capsuleConfig.getItemWidth()) {
                if (LogUtil.getNormalDebug() && LogUtil.isTagValid(str)) {
                    Log.d("SystemUi--Notification", "CapsuleItemAnimator-->setupEarAnim: 2-->1");
                }
                multiWidthAnimController.initEarTransX(0.0f);
                CapsuleSpringAnimParams.EarTransXSpringData earTransXSpringData2 = capsuleItemSpringDataBuildCapsuleItemSpringData.getEarTransXSpringData();
                widthAnimController = multiWidthAnimController;
                if (earTransXSpringData2 != null && (disappearSpring = earTransXSpringData2.getDisappearSpring()) != null) {
                    widthAnimController = multiWidthAnimController;
                    multiWidthAnimController.getP1AnimSet().add(disappearSpring);
                    widthAnimController = multiWidthAnimController;
                }
            } else if (actualWidth$vendor__oplus__packages__apps__systemui__oplus_systemui__android_common__Oplus_SystemUI_application_domestic2 == capsuleConfig.getItemWidthMultiCard() && currentCardShowWidth2 == capsuleConfig.getItemWidthMultiCard()) {
                if (LogUtil.getNormalDebug() && LogUtil.isTagValid(str)) {
                    Log.d("SystemUi--Notification", "CapsuleItemAnimator-->setupEarAnim: N-->N");
                }
                multiWidthAnimController.initEarTransX(0.0f);
                CapsuleSpringAnimParams.EarTransXSpringData earTransXSpringData3 = capsuleItemSpringDataBuildCapsuleItemSpringData.getEarTransXSpringData();
                widthAnimController = multiWidthAnimController;
                if (earTransXSpringData3 != null && (switchSpring = earTransXSpringData3.getSwitchSpring()) != null) {
                    widthAnimController = multiWidthAnimController;
                    multiWidthAnimController.getP1p2AnimSet().add(switchSpring);
                    widthAnimController = multiWidthAnimController;
                }
            } else {
                widthAnimController = multiWidthAnimController;
                if (LogUtil.getNormalDebug() && LogUtil.isTagValid(str)) {
                    widthAnimController = multiWidthAnimController;
                    Log.d("SystemUi--Notification", "CapsuleItemAnimator-->setupEarAnim: Error...");
                    widthAnimController = multiWidthAnimController;
                }
            }
        }
        if (widthAnimController == null) {
            return;
        }
        if (LogUtil.getNormalDebug() && LogUtil.isTagValid(str)) {
            Log.d("SystemUi--Notification", "CapsuleItemAnimator-->CapsuleItemAnimatorStart");
        }
        widthAnimController.startAnim(this, z);
    }
}
