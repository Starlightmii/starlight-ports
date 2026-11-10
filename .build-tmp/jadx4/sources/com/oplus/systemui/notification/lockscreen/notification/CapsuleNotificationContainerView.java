package com.oplus.systemui.notification.lockscreen.notification;

import android.animation.ValueAnimator;
import android.content.Context;
import android.util.ArraySet;
import android.util.Log;
import android.view.View;
import android.view.animation.Interpolator;
import android.widget.FrameLayout;
import android.widget.TextView;
import androidx.core.view.OneShotPreDrawListener;
import com.android.keyguard.logging.KeyguardLogger$;
import com.android.systemui.application.impl.SystemUIApplicationImpl$;
import com.android.systemui.statusbar.CrossFadeHelper;
import com.android.systemui.statusbar.TransformableView;
import com.android.systemui.statusbar.ViewTransformationHelper;
import com.android.systemui.statusbar.notification.TransformState;
import com.android.systemui.statusbar.notification.row.ExpandableView;
import com.oplus.systemui.notification.lockscreen.capsule.CapsuleItemView;
import com.oplus.systemui.notification.lockscreen.capsule.utils.CapsuleViewExpandKtKt;
import com.oplus.systemui.notification.row.NotificationMenuRowLog;
import com.oplusos.systemui.common.util.LogUtil;
import java.lang.invoke.VarHandle;
import kotlin.LazyKt__LazyJVMKt$;
import kotlin.Pair;
import kotlin.TuplesKt;
import kotlin.jvm.functions.Function0;
import kotlin.jvm.internal.Intrinsics;
import kotlin.ranges.RangesKt___RangesKt;

/* JADX INFO: compiled from: go/retraceme fd74bf9514dd0739b92d67a6d48a51eb10aaf8df760d83496b8dcfcf83e5f698 */
/* JADX INFO: loaded from: /home/starlight/Projects/Starlight Ports/.build-tmp/classes4.dex */
public final class CapsuleNotificationContainerView extends FrameLayout {
    public static final Companion Companion = new Companion();
    public static final String TAG = "CapsuleNotificationContainerView";
    private CapsuleNotificationCardView currentView;
    private final CapsuleNotificationDataController dataController;
    public boolean hasNewNotificationForCurrentApp;
    public boolean isPreparingTransform;
    public ViewTransformationHelper newHelper;
    public ViewTransformationHelper oldHelper;
    private CapsuleItemView parentItemView;
    private Function0 pendingShowTask;

    /* JADX INFO: compiled from: go/retraceme fd74bf9514dd0739b92d67a6d48a51eb10aaf8df760d83496b8dcfcf83e5f698 */
    public class BaseCustomTransformation extends ViewTransformationHelper.CustomTransformation {
        private final Interpolator alphaInterpolator;
        private final Interpolator scaleInterpolator;
        public final long startDelayMs;
        private final Interpolator translationInterpolator;
        public final int viewType;

        public BaseCustomTransformation(Interpolator interpolator, Interpolator interpolator2, Interpolator interpolator3, int i, long j) {
            this.alphaInterpolator = interpolator;
            this.scaleInterpolator = interpolator2;
            this.translationInterpolator = interpolator3;
            this.viewType = i;
            this.startDelayMs = j;
        }

        public final float applyDelay(float f) {
            long j = this.startDelayMs;
            if (j <= 0) {
                return f;
            }
            float animateDuration = j / CapsuleInternalAnimHelper.INSTANCE.getAnimateDuration();
            return RangesKt___RangesKt.coerceIn((f - animateDuration) / (1.0f - animateDuration), 0.0f, 1.0f);
        }

        public final Interpolator getAlphaInterpolator() {
            return this.alphaInterpolator;
        }

        public Interpolator getCustomInterpolator(int i, boolean z) {
            if (i == 1 || i == 16) {
                return this.translationInterpolator;
            }
            if (i == 256 || i == 4096) {
                return this.scaleInterpolator;
            }
            return null;
        }

        public final Interpolator getScaleInterpolator() {
            return this.scaleInterpolator;
        }

        public final long getStartDelayMs() {
            return this.startDelayMs;
        }

        public final Interpolator getTranslationInterpolator() {
            return this.translationInterpolator;
        }

        public final int getViewType() {
            return this.viewType;
        }

        /* JADX WARN: Code duplicated, block: B:16:0x006b  */
        public boolean initTransformation(TransformState transformState, TransformState transformState2) {
            float height;
            float width;
            View transformedView = transformState.getTransformedView();
            View transformedView2 = transformState2.getTransformedView();
            Interpolator interpolator = this.scaleInterpolator;
            int i = this.viewType;
            if (interpolator == null) {
                height = 1.0f;
                width = 1.0f;
            } else {
                if (i == 1006) {
                    transformedView.setPivotX(0.0f);
                    transformedView.setPivotY(0.0f);
                } else {
                    transformedView.setPivotX(transformedView.getWidth() / 2.0f);
                    transformedView.setPivotY(transformedView.getHeight() / 2.0f);
                }
                if (transformedView.getWidth() <= 0 || transformedView.getHeight() <= 0 || transformedView2.getWidth() <= 0 || transformedView2.getHeight() <= 0) {
                    height = 1.0f;
                    width = 1.0f;
                } else {
                    width = transformedView2.getWidth() / transformedView.getWidth();
                    height = transformedView2.getHeight() / transformedView.getHeight();
                    transformedView.setScaleX(width);
                    transformedView.setScaleY(height);
                    transformState.setTransformationStartScaleX(width);
                    transformState.setTransformationStartScaleY(height);
                }
            }
            if (this.translationInterpolator != null) {
                int[] laidOutLocationOnScreen = transformState.getLaidOutLocationOnScreen();
                int[] laidOutLocationOnScreen2 = transformState2.getLaidOutLocationOnScreen();
                int i2 = laidOutLocationOnScreen2[0] - laidOutLocationOnScreen[0];
                int pivotY = laidOutLocationOnScreen2[1] - laidOutLocationOnScreen[1];
                if (this.scaleInterpolator != null && i != 1006) {
                    float pivotX = transformedView.getPivotX();
                    i2 = (int) (i2 + (pivotX * (width - 1.0f)));
                    pivotY = (int) (pivotY + (transformedView.getPivotY() * (height - 1.0f)));
                }
                transformState.setTransformationStartX(i2);
                transformState.setTransformationStartY(pivotY);
            }
            return true;
        }

        public boolean transformFrom(TransformState transformState, TransformableView transformableView, float f) {
            TransformState currentState = transformableView.getCurrentState(this.viewType);
            if (currentState == null) {
                return false;
            }
            View transformedView = transformState.getTransformedView();
            float fApplyDelay = applyDelay(f);
            Interpolator interpolator = this.alphaInterpolator;
            if (interpolator != null) {
                transformedView.animate().cancel();
                if (transformedView.getVisibility() == 4) {
                    transformedView.setVisibility(0);
                }
                float interpolation = interpolator.getInterpolation(fApplyDelay);
                transformedView.setAlpha(interpolation);
                CrossFadeHelper.updateLayerType(interpolation, transformedView);
            } else {
                transformState.ensureVisible();
            }
            transformState.transformViewFrom(currentState, 17, this, fApplyDelay);
            currentState.recycle();
            return true;
        }

        public boolean transformTo(TransformState transformState, TransformableView transformableView, float f) {
            TransformState currentState = transformableView.getCurrentState(this.viewType);
            if (currentState == null) {
                return false;
            }
            View transformedView = transformState.getTransformedView();
            float fApplyDelay = applyDelay(f);
            Interpolator interpolator = this.alphaInterpolator;
            if (interpolator != null) {
                CrossFadeHelper.fadeOutWithInterpolator(transformedView, fApplyDelay, interpolator);
            } else if (transformState.getTransformedView().getVisibility() == 0) {
                transformState.getTransformedView().setAlpha(0.0f);
                transformState.getTransformedView().setVisibility(4);
            }
            transformState.transformViewTo(currentState, 17, this, fApplyDelay);
            currentState.recycle();
            return true;
        }
    }

    /* JADX INFO: compiled from: go/retraceme fd74bf9514dd0739b92d67a6d48a51eb10aaf8df760d83496b8dcfcf83e5f698 */
    public final class Companion {
    }

    public CapsuleNotificationContainerView(Context context, CapsuleNotificationDataController capsuleNotificationDataController) {
        super(context);
        this.dataController = capsuleNotificationDataController;
    }

    public static /* synthetic */ void registerTransformedViews$default(CapsuleNotificationContainerView capsuleNotificationContainerView, CapsuleInternalAnimHelper$CapsuleAnimScene capsuleInternalAnimHelper$CapsuleAnimScene, View view, View view2, CapsuleInternalAnimHelper$AnimationAction capsuleInternalAnimHelper$AnimationAction, int i, int i2) {
        CapsuleInternalAnimHelper$AnimationAction capsuleInternalAnimHelper$AnimationAction2 = CapsuleInternalAnimHelper$AnimationAction.TOP_RIGHT_BADGE;
        if ((i2 & 2) != 0) {
            view = null;
        }
        if ((i2 & 4) != 0) {
            capsuleInternalAnimHelper$AnimationAction2 = null;
        }
        if ((i2 & 8) != 0) {
            view2 = null;
        }
        capsuleNotificationContainerView.registerTransformedViews(capsuleInternalAnimHelper$CapsuleAnimScene, view, capsuleInternalAnimHelper$AnimationAction2, view2, (i2 & 16) != 0 ? null : capsuleInternalAnimHelper$AnimationAction, i);
    }

    public final void bindItemView(CapsuleItemView capsuleItemView) {
        this.parentItemView = capsuleItemView;
    }

    public final CapsuleNotificationCardView createAndAddNewCard(CapsuleNotificationDataController.CapsuleNotificationInnerData capsuleNotificationInnerData) {
        CapsuleNotificationCardView capsuleNotificationCardViewCreateNewCard = createNewCard();
        capsuleNotificationCardViewCreateNewCard.bind(capsuleNotificationInnerData);
        capsuleNotificationCardViewCreateNewCard.setVisibility(0);
        addView(capsuleNotificationCardViewCreateNewCard);
        this.currentView = capsuleNotificationCardViewCreateNewCard;
        return capsuleNotificationCardViewCreateNewCard;
    }

    public final CapsuleNotificationCardView createNewCard() {
        CapsuleNotificationCardView capsuleNotificationCardView = new CapsuleNotificationCardView(getContext());
        capsuleNotificationCardView.setVisibility(8);
        CapsuleNotificationDataController capsuleNotificationDataController = this.dataController;
        if (capsuleNotificationDataController != null) {
            capsuleNotificationCardView.bindDataController(capsuleNotificationDataController);
        }
        return capsuleNotificationCardView;
    }

    public final CapsuleNotificationCardView getCurrentView() {
        return this.currentView;
    }

    public final boolean getHasNewNotificationForCurrentApp() {
        return this.hasNewNotificationForCurrentApp;
    }

    public final void handleGroupBadge(CapsuleNotificationCardView capsuleNotificationCardView, CapsuleNotificationCardView capsuleNotificationCardView2, CapsuleInternalAnimHelper$CapsuleAnimScene capsuleInternalAnimHelper$CapsuleAnimScene) {
        if (capsuleInternalAnimHelper$CapsuleAnimScene == CapsuleInternalAnimHelper$CapsuleAnimScene.GROUP_TO_GROUP) {
            TextView numView = capsuleNotificationCardView.getNumView();
            if (numView != null) {
                numView.setVisibility(4);
            }
            TextView numView2 = capsuleNotificationCardView2.getNumView();
            if (numView2 != null) {
                numView2.setVisibility(0);
                return;
            }
            return;
        }
        FrameLayout numViewContainer = capsuleNotificationCardView.getNumViewContainer();
        if (numViewContainer != null) {
            CapsuleInternalAnimHelper$AnimationAction capsuleInternalAnimHelper$AnimationAction = CapsuleInternalAnimHelper$AnimationAction.OLD_CONTENT;
            registerTransformedViews$default(this, capsuleInternalAnimHelper$CapsuleAnimScene, numViewContainer, null, null, 1005, 24);
        }
        FrameLayout numViewContainer2 = capsuleNotificationCardView2.getNumViewContainer();
        if (numViewContainer2 != null) {
            registerTransformedViews$default(this, capsuleInternalAnimHelper$CapsuleAnimScene, null, numViewContainer2, CapsuleInternalAnimHelper$AnimationAction.TOP_RIGHT_BADGE, 1005, 6);
        }
    }

    public final void handlePendingTaskIfNeed() {
        Function0 function0 = this.pendingShowTask;
        if (function0 == null) {
            return;
        }
        this.pendingShowTask = null;
        if (LogUtil.getNormalDebug() && LogUtil.isTagValid(LogUtil.TAG_NOTIFICATION)) {
            Log.d("SystemUi--Notification", "CapsuleNotificationContainerView-->handle pending show task");
        }
        function0.invoke();
    }

    public final void handleText(CapsuleNotificationCardView capsuleNotificationCardView, CapsuleNotificationCardView capsuleNotificationCardView2, CapsuleInternalAnimHelper$CapsuleAnimScene capsuleInternalAnimHelper$CapsuleAnimScene) {
        registerTransformedViews(capsuleInternalAnimHelper$CapsuleAnimScene, capsuleNotificationCardView.getTitleView(), CapsuleInternalAnimHelper$AnimationAction.OLD_TITLE, capsuleNotificationCardView2.getTitleView(), CapsuleInternalAnimHelper$AnimationAction.NEW_TITLE, 1);
        registerTransformedViews(capsuleInternalAnimHelper$CapsuleAnimScene, capsuleNotificationCardView.getTextView(), CapsuleInternalAnimHelper$AnimationAction.OLD_TEXT, capsuleNotificationCardView2.getTextView(), CapsuleInternalAnimHelper$AnimationAction.NEW_TEXT, 2);
    }

    public final void onConfigChanged() {
        CapsuleNotificationCardView capsuleNotificationCardView = this.currentView;
        if (capsuleNotificationCardView != null) {
            capsuleNotificationCardView.reInflateViews();
        }
    }

    public final void registerTransformedViews(CapsuleInternalAnimHelper$CapsuleAnimScene capsuleInternalAnimHelper$CapsuleAnimScene, View view, CapsuleInternalAnimHelper$AnimationAction capsuleInternalAnimHelper$AnimationAction, View view2, CapsuleInternalAnimHelper$AnimationAction capsuleInternalAnimHelper$AnimationAction2, int i) {
        BaseCustomTransformation baseCustomTransformationCreate;
        BaseCustomTransformation baseCustomTransformationCreate2;
        if (view != null) {
            ViewTransformationHelper viewTransformationHelper = this.oldHelper;
            if (viewTransformationHelper == null) {
                viewTransformationHelper = null;
            }
            viewTransformationHelper.addTransformedView(i, view);
            if (capsuleInternalAnimHelper$AnimationAction != null && (baseCustomTransformationCreate2 = CapsuleInternalAnimHelper.INSTANCE.create(capsuleInternalAnimHelper$CapsuleAnimScene, capsuleInternalAnimHelper$AnimationAction, i)) != null) {
                ViewTransformationHelper viewTransformationHelper2 = this.oldHelper;
                if (viewTransformationHelper2 == null) {
                    viewTransformationHelper2 = null;
                }
                viewTransformationHelper2.setCustomTransformation(baseCustomTransformationCreate2, i);
            }
        }
        if (view2 != null) {
            ViewTransformationHelper viewTransformationHelper3 = this.newHelper;
            if (viewTransformationHelper3 == null) {
                viewTransformationHelper3 = null;
            }
            viewTransformationHelper3.addTransformedView(i, view2);
            if (capsuleInternalAnimHelper$AnimationAction2 == null || (baseCustomTransformationCreate = CapsuleInternalAnimHelper.INSTANCE.create(capsuleInternalAnimHelper$CapsuleAnimScene, capsuleInternalAnimHelper$AnimationAction2, i)) == null) {
                return;
            }
            ViewTransformationHelper viewTransformationHelper4 = this.newHelper;
            (viewTransformationHelper4 != null ? viewTransformationHelper4 : null).setCustomTransformation(baseCustomTransformationCreate, i);
        }
    }

    public final void setHasNewNotificationForCurrentApp(boolean z) {
        this.hasNewNotificationForCurrentApp = z;
    }

    public final void showAggregate(CapsuleNotificationDataController.CapsuleNotificationInnerData capsuleNotificationInnerData, boolean z) {
        showCapsule(capsuleNotificationInnerData, z);
    }

    /* JADX WARN: Code duplicated, block: B:98:0x021f  */
    public final void showCapsule(CapsuleNotificationDataController.CapsuleNotificationInnerData capsuleNotificationInnerData, boolean z) {
        CapsuleInternalAnimHelper$CapsuleAnimScene capsuleInternalAnimHelper$CapsuleAnimScene;
        ViewTransformationHelper mTransformationHelper;
        ValueAnimator valueAnimator;
        ExpandableView expandableView;
        CapsuleNotificationDataController.CapsuleNotificationInnerData innerData;
        CapsuleNotificationContainerView capsuleNotificationContainerView = this;
        CapsuleNotificationCardView capsuleNotificationCardView = capsuleNotificationContainerView.currentView;
        if (capsuleNotificationCardView != null && (innerData = capsuleNotificationCardView.getInnerData()) != null && innerData.hasSameContentAs(capsuleNotificationInnerData)) {
            if (LogUtil.getNormalDebug() && LogUtil.isTagValid(LogUtil.TAG_NOTIFICATION)) {
                Log.d("SystemUi--Notification", "CapsuleNotificationContainerView-->content same, skip showCapsule animal");
                return;
            }
            return;
        }
        boolean z2 = z && CapsuleViewExpandKtKt.isBaseCard(capsuleNotificationContainerView.parentItemView) && (expandableView = capsuleNotificationContainerView.parentItemView) != null && expandableView.getVisibility() == 0;
        boolean z3 = (capsuleNotificationCardView == null || (mTransformationHelper = capsuleNotificationCardView.getMTransformationHelper()) == null || (valueAnimator = mTransformationHelper.mViewTransformationAnimation) == null || !valueAnimator.isRunning()) ? false : true;
        if (LogUtil.getNormalDebug() && LogUtil.isTagValid(LogUtil.TAG_NOTIFICATION)) {
            String strSimpleNameAndHash = NotificationMenuRowLog.simpleNameAndHash(capsuleNotificationCardView);
            boolean z4 = capsuleNotificationContainerView.isPreparingTransform;
            boolean z5 = capsuleNotificationContainerView.getParent() != null;
            StringBuilder sbM = KeyguardLogger$.ExternalSyntheticOutline0.m("showCapsule:hiddenView = ", strSimpleNameAndHash, ", animate:", z, ", realAnimate:");
            KeyguardLogger$.ExternalSyntheticOutline0.m(", isAnimating:", ", isPreparingTransform:", sbM, z2, z3);
            Log.d("SystemUi--Notification", SystemUIApplicationImpl$.ExternalSyntheticOutline0.m(", parent != null: ", "CapsuleNotificationContainerView-->", sbM, z4, z5));
        }
        if (z3 || capsuleNotificationContainerView.isPreparingTransform) {
            CapsuleNotificationContainerView$$ExternalSyntheticLambda0 capsuleNotificationContainerView$$ExternalSyntheticLambda0 = new CapsuleNotificationContainerView$$ExternalSyntheticLambda0();
            capsuleNotificationContainerView$$ExternalSyntheticLambda0.f$0 = capsuleNotificationContainerView;
            capsuleNotificationContainerView$$ExternalSyntheticLambda0.f$1 = capsuleNotificationInnerData;
            capsuleNotificationContainerView$$ExternalSyntheticLambda0.f$2 = z;
            VarHandle.storeStoreFence();
            capsuleNotificationContainerView.pendingShowTask = capsuleNotificationContainerView$$ExternalSyntheticLambda0;
            if (LogUtil.getNormalDebug() && LogUtil.isTagValid(LogUtil.TAG_NOTIFICATION)) {
                Log.d("SystemUi--Notification", "CapsuleNotificationContainerView-->isAnimating, override pending task");
                return;
            }
            return;
        }
        if (!z2 || capsuleNotificationCardView == null) {
            if (capsuleNotificationCardView != null) {
                capsuleNotificationCardView.bind(capsuleNotificationInnerData);
                capsuleNotificationCardView.setVisible(true);
                capsuleNotificationContainerView.handlePendingTaskIfNeed();
                return;
            } else {
                CapsuleNotificationCardView capsuleNotificationCardViewCreateNewCard = capsuleNotificationContainerView.createNewCard();
                capsuleNotificationContainerView.addView(capsuleNotificationCardViewCreateNewCard);
                capsuleNotificationCardViewCreateNewCard.bind(capsuleNotificationInnerData);
                capsuleNotificationCardViewCreateNewCard.setVisible(true);
                capsuleNotificationContainerView.currentView = capsuleNotificationCardViewCreateNewCard;
                capsuleNotificationContainerView.handlePendingTaskIfNeed();
                return;
            }
        }
        CapsuleNotificationCardView capsuleNotificationCardViewCreateNewCard2 = capsuleNotificationContainerView.createNewCard();
        capsuleNotificationContainerView.addView(capsuleNotificationCardViewCreateNewCard2);
        capsuleNotificationCardViewCreateNewCard2.bind(capsuleNotificationInnerData);
        capsuleNotificationContainerView.isPreparingTransform = true;
        if (LogUtil.getNormalDebug() && LogUtil.isTagValid(LogUtil.TAG_NOTIFICATION)) {
            Log.d("SystemUi--Notification", "CapsuleNotificationContainerView-->".concat("showCapsule step1 startPrepareTransform:" + capsuleNotificationContainerView.parentItemView));
        }
        capsuleNotificationContainerView.oldHelper = capsuleNotificationCardView.getMTransformationHelper();
        capsuleNotificationContainerView.newHelper = capsuleNotificationCardViewCreateNewCard2.getMTransformationHelper();
        ViewTransformationHelper viewTransformationHelper = capsuleNotificationContainerView.oldHelper;
        if (viewTransformationHelper == null) {
            viewTransformationHelper = null;
        }
        CapsuleInternalAnimHelper capsuleInternalAnimHelper = CapsuleInternalAnimHelper.INSTANCE;
        viewTransformationHelper.setCustomAnimationDuration(capsuleInternalAnimHelper.getAnimateDuration());
        ViewTransformationHelper viewTransformationHelper2 = capsuleNotificationContainerView.newHelper;
        if (viewTransformationHelper2 == null) {
            viewTransformationHelper2 = null;
        }
        viewTransformationHelper2.setCustomAnimationDuration(capsuleInternalAnimHelper.getAnimateDuration());
        ArraySet allTransformingViews = capsuleNotificationCardView.getMTransformationHelper().getAllTransformingViews();
        ViewTransformationHelper mTransformationHelper2 = capsuleNotificationCardView.getMTransformationHelper();
        mTransformationHelper2.mTransformedViews.clear();
        mTransformationHelper2.mKeysTransformingToSimilar.clear();
        capsuleNotificationCardView.getMTransformationHelper().mCustomTransformations.clear();
        allTransformingViews.getClass();
        ArraySet allTransformingViews2 = capsuleNotificationCardViewCreateNewCard2.getMTransformationHelper().getAllTransformingViews();
        ViewTransformationHelper mTransformationHelper3 = capsuleNotificationCardViewCreateNewCard2.getMTransformationHelper();
        mTransformationHelper3.mTransformedViews.clear();
        mTransformationHelper3.mKeysTransformingToSimilar.clear();
        capsuleNotificationCardViewCreateNewCard2.getMTransformationHelper().mCustomTransformations.clear();
        allTransformingViews2.getClass();
        Pair pair = TuplesKt.to(capsuleNotificationCardView.getCardType(), capsuleNotificationCardViewCreateNewCard2.getCardType());
        CapsuleNotificationDataController.CardType cardType = CapsuleNotificationDataController.CardType.GROUP;
        if (Intrinsics.areEqual(pair, TuplesKt.to(cardType, CapsuleNotificationDataController.CardType.SINGLE))) {
            capsuleInternalAnimHelper$CapsuleAnimScene = CapsuleInternalAnimHelper$CapsuleAnimScene.GROUP_TO_SINGLE;
        } else if (Intrinsics.areEqual(pair, TuplesKt.to(cardType, CapsuleNotificationDataController.CardType.SINGLE_MESSAGE))) {
            capsuleInternalAnimHelper$CapsuleAnimScene = CapsuleInternalAnimHelper$CapsuleAnimScene.GROUP_TO_MESSAGE_SINGLE;
        } else {
            CapsuleNotificationDataController.CardType cardType2 = CapsuleNotificationDataController.CardType.AGGREGATE;
            if (Intrinsics.areEqual(pair, TuplesKt.to(cardType, cardType2))) {
                capsuleInternalAnimHelper$CapsuleAnimScene = CapsuleInternalAnimHelper$CapsuleAnimScene.COMMON_TRANSFORMATION;
            } else if (Intrinsics.areEqual(pair, TuplesKt.to(cardType, cardType))) {
                capsuleInternalAnimHelper$CapsuleAnimScene = CapsuleInternalAnimHelper$CapsuleAnimScene.GROUP_TO_GROUP;
            } else {
                capsuleInternalAnimHelper$CapsuleAnimScene = Intrinsics.areEqual(pair, TuplesKt.to(cardType2, cardType2)) ? CapsuleInternalAnimHelper$CapsuleAnimScene.AGGREGATE_TO_AGGREGATE : CapsuleInternalAnimHelper$CapsuleAnimScene.COMMON_TRANSFORMATION;
            }
        }
        if (LogUtil.getNormalDebug() && LogUtil.isTagValid(LogUtil.TAG_NOTIFICATION)) {
            Log.d("SystemUi--Notification", "CapsuleNotificationContainerView-->".concat("resolveScene: " + capsuleInternalAnimHelper$CapsuleAnimScene));
        }
        capsuleInternalAnimHelper.requestScreenState(capsuleInternalAnimHelper$CapsuleAnimScene.toString(), capsuleNotificationContainerView.getContext());
        if (capsuleInternalAnimHelper$CapsuleAnimScene == CapsuleInternalAnimHelper$CapsuleAnimScene.GROUP_TO_MESSAGE_SINGLE || capsuleInternalAnimHelper$CapsuleAnimScene == CapsuleInternalAnimHelper$CapsuleAnimScene.GROUP_TO_SINGLE) {
            capsuleNotificationCardView.setElevation(capsuleNotificationCardViewCreateNewCard2.getElevation() + 10.0f);
        }
        int iOrdinal = capsuleInternalAnimHelper$CapsuleAnimScene.ordinal();
        if (iOrdinal == 0) {
            capsuleNotificationContainerView.handleGroupBadge(capsuleNotificationCardView, capsuleNotificationCardViewCreateNewCard2, capsuleInternalAnimHelper$CapsuleAnimScene);
            capsuleNotificationContainerView.handleText(capsuleNotificationCardView, capsuleNotificationCardViewCreateNewCard2, capsuleInternalAnimHelper$CapsuleAnimScene);
        } else if (iOrdinal == 1) {
            registerTransformedViews$default(capsuleNotificationContainerView, capsuleInternalAnimHelper$CapsuleAnimScene, null, capsuleNotificationCardViewCreateNewCard2.getIcon(), CapsuleInternalAnimHelper$AnimationAction.CONTACT_ICON, 1001, 6);
            capsuleNotificationContainerView = this;
            registerTransformedViews$default(capsuleNotificationContainerView, capsuleInternalAnimHelper$CapsuleAnimScene, capsuleNotificationCardView.getIcon(), capsuleNotificationCardViewCreateNewCard2.getBottomRightBadge(), CapsuleInternalAnimHelper$AnimationAction.BOTTOM_RIGHT_BADGE, 1006, 4);
            capsuleNotificationContainerView.handleGroupBadge(capsuleNotificationCardView, capsuleNotificationCardViewCreateNewCard2, capsuleInternalAnimHelper$CapsuleAnimScene);
            capsuleNotificationContainerView.handleText(capsuleNotificationCardView, capsuleNotificationCardViewCreateNewCard2, capsuleInternalAnimHelper$CapsuleAnimScene);
        } else if (iOrdinal == 2) {
            capsuleNotificationContainerView.handleGroupBadge(capsuleNotificationCardView, capsuleNotificationCardViewCreateNewCard2, capsuleInternalAnimHelper$CapsuleAnimScene);
            capsuleNotificationContainerView.handleText(capsuleNotificationCardView, capsuleNotificationCardViewCreateNewCard2, capsuleInternalAnimHelper$CapsuleAnimScene);
        } else if (iOrdinal == 3) {
            capsuleNotificationContainerView.handleText(capsuleNotificationCardView, capsuleNotificationCardViewCreateNewCard2, capsuleInternalAnimHelper$CapsuleAnimScene);
        } else {
            if (iOrdinal != 4) {
                LazyKt__LazyJVMKt$.ExternalSyntheticBUOutline0.m();
                return;
            }
            capsuleNotificationContainerView.registerTransformedViews(capsuleInternalAnimHelper$CapsuleAnimScene, capsuleNotificationCardView.getContentLayout(), CapsuleInternalAnimHelper$AnimationAction.OLD_CONTENT, capsuleNotificationCardViewCreateNewCard2.getContentLayout(), CapsuleInternalAnimHelper$AnimationAction.NEW_CONTENT, 1001);
        }
        ViewTransformationHelper viewTransformationHelper3 = capsuleNotificationContainerView.oldHelper;
        if (viewTransformationHelper3 == null) {
            viewTransformationHelper3 = null;
        }
        ArraySet allTransformingViews3 = viewTransformationHelper3.getAllTransformingViews();
        ViewTransformationHelper viewTransformationHelper4 = capsuleNotificationContainerView.oldHelper;
        if (viewTransformationHelper4 == null) {
            viewTransformationHelper4 = null;
        }
        int size = allTransformingViews.size();
        for (int i = 0; i < size; i++) {
            View view = (View) allTransformingViews.valueAt(i);
            if (!allTransformingViews3.contains(view)) {
                viewTransformationHelper4.resetTransformedView(view);
            }
        }
        ViewTransformationHelper viewTransformationHelper5 = capsuleNotificationContainerView.newHelper;
        if (viewTransformationHelper5 == null) {
            viewTransformationHelper5 = null;
        }
        ArraySet allTransformingViews4 = viewTransformationHelper5.getAllTransformingViews();
        ViewTransformationHelper viewTransformationHelper6 = capsuleNotificationContainerView.newHelper;
        ViewTransformationHelper viewTransformationHelper7 = viewTransformationHelper6 != null ? viewTransformationHelper6 : null;
        int size2 = allTransformingViews2.size();
        for (int i2 = 0; i2 < size2; i2++) {
            View view2 = (View) allTransformingViews2.valueAt(i2);
            if (!allTransformingViews4.contains(view2)) {
                viewTransformationHelper7.resetTransformedView(view2);
            }
        }
        capsuleNotificationCardViewCreateNewCard2.setVisibility(0);
        CapsuleNotificationContainerView$showCapsule$.inlined.doOnPreDraw.1 r1 = new CapsuleNotificationContainerView$showCapsule$.inlined.doOnPreDraw.1();
        r1.$this_doOnPreDraw = capsuleNotificationCardViewCreateNewCard2;
        r1.$shownView$inlined = capsuleNotificationCardViewCreateNewCard2;
        r1.$hiddenView$inlined = capsuleNotificationCardView;
        r1.this$0 = capsuleNotificationContainerView;
        VarHandle.storeStoreFence();
        OneShotPreDrawListener.add(capsuleNotificationCardViewCreateNewCard2, r1);
    }

    public final void showSingle(CapsuleNotificationDataController.CapsuleNotificationInnerData capsuleNotificationInnerData, boolean z) {
        showCapsule(capsuleNotificationInnerData, z);
    }

    public CapsuleNotificationContainerView(Context context) {
        this(context, null);
    }
}
