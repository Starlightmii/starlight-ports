package com.oplus.systemui.notification.lockscreen.capsule.animtor;

import android.animation.Animator;
import android.animation.AnimatorListenerAdapter;
import android.content.Context;
import android.util.Log;
import android.util.Property;
import android.view.View;
import androidx.collection.ArraySet;
import com.android.internal.dynamicanimation.animation.DynamicAnimation;
import com.android.systemui.statusbar.notification.row.ExpandableView;
import com.android.systemui.statusbar.notification.row.OplusExpandableViewEx;
import com.android.systemui.statusbar.notification.stack.AnimationFilter;
import com.android.systemui.statusbar.notification.stack.AnimationProperties;
import com.android.systemui.statusbar.notification.stack.ExpandableViewState;
import com.android.systemui.statusbar.notification.stack.StackStateLogger;
import com.android.systemui.statusbar.notification.stack.ViewState;
import com.coui.appcompat.animation.COUISpringInterpolator;
import com.coui.appcompat.animation.dynamicanimation.COUIDynamicAnimation;
import com.oplus.systemui.notification.lockscreen.capsule.CapsuleItemView;
import com.oplus.systemui.notification.lockscreen.capsule.CapsuleViewState;
import com.oplus.systemui.notification.lockscreen.capsule.NotificationCapsuleContainer;
import com.oplus.systemui.notification.lockscreen.capsule.animtor.itemview.CapsuleItemBaseAnimator;
import com.oplus.systemui.notification.lockscreen.capsule.animtor.itemview.CapsuleItemPhysicalAnimator;
import com.oplus.systemui.notification.lockscreen.capsule.modle.CapsuleItemData;
import com.oplus.systemui.notification.lockscreen.config.CapsuleConfig;
import com.oplus.systemui.notification.lockscreen.effect.ExtStateAnimParams;
import com.oplus.systemui.notification.lockscreen.seedling.CapsuleSeedingData;
import com.oplus.systemui.statusbar.notification.stack.OplusExpandableViewStateExImpl;
import com.oplusos.systemui.common.util.LogUtil;
import java.lang.invoke.VarHandle;
import java.util.ArrayList;
import java.util.HashSet;
import java.util.Stack;
import java.util.function.Consumer;
import kotlin.collections.CollectionsKt__CollectionsKt;
import kotlin.collections.CollectionsKt__MutableCollectionsKt;
import kotlin.jvm.internal.Intrinsics;

/* JADX INFO: compiled from: go/retraceme fd74bf9514dd0739b92d67a6d48a51eb10aaf8df760d83496b8dcfcf83e5f698 */
/* JADX INFO: loaded from: /home/starlight/Projects/Starlight Ports/.build-tmp/classes4.dex */
public final class ContainerStateAnimator {
    private static final String TAG = "ContainerStateAnimator";
    private final OplusAnimationProperties animationProperties;
    private final NotificationCapsuleContainer hostLayout;
    private final ContainerAnimatorViewHelper hostLayoutHelper;
    public long mCurrentAdditionalDelay;
    public long mCurrentLength;
    private StackStateLogger mLogger;
    private final CapsuleItemBaseAnimator springItemAnimator;
    public static final Companion Companion = new Companion();
    private static final COUISpringInterpolator CAPSULE_TO_STACK_INTERPOLATOR = new COUISpringInterpolator(0.8d, 0.15d);
    private final HashSet<Object> animatorSet = new HashSet<>();
    private final OplusAnimationFilter animationFilter = new OplusAnimationFilter();
    private final ArrayList<AnimationEvent> mNewEvents = new ArrayList<>();
    private final ArrayList<View> mNewAddChildren = new ArrayList<>();
    private final Stack<AnimatorListenerAdapter> mAnimationListenerPool = new Stack<>();
    private final Stack<DynamicAnimation.OnAnimationEndListener> mAnimationEndPool = new Stack<>();
    private final Stack<COUIDynamicAnimation.OnAnimationEndListener> mCouiAnimationEndPool = new Stack<>();
    public final boolean mShadeExpanded = true;
    private final ArrayList<ExpandableView> mTransientViewsToRemove = new ArrayList<>();
    private final ArrayList<ExpandableView> withOutAnimation = new ArrayList<>();

    /* JADX INFO: renamed from: com.oplus.systemui.notification.lockscreen.capsule.animtor.ContainerStateAnimator$1, reason: invalid class name */
    /* JADX INFO: compiled from: go/retraceme fd74bf9514dd0739b92d67a6d48a51eb10aaf8df760d83496b8dcfcf83e5f698 */
    public final class AnonymousClass1 extends OplusAnimationProperties {
        public ContainerStateAnimator$1$mDynamicAnimationConsumer$1 couiDynamicAnimationConsumer;
        public ContainerStateAnimator$1$mDynamicAnimationConsumer$1 mDynamicAnimationConsumer;
        public /* synthetic */ ContainerStateAnimator this$0;

        public final DynamicAnimation.OnAnimationEndListener getAnimationEndListener(Property property) {
            return this.this$0.getGlobalAnimationEndListener();
        }

        @Override // com.oplus.systemui.notification.lockscreen.capsule.animtor.OplusAnimationProperties
        public OplusAnimationFilter getAnimationFilter() {
            return this.this$0.getAnimationFilter();
        }

        public final AnimatorListenerAdapter getAnimationFinishListener(Property property) {
            return this.this$0.getGlobalAnimationFinishedListener();
        }

        public final Consumer getAnimationStartListener() {
            return this.mDynamicAnimationConsumer;
        }

        public final COUIDynamicAnimation.OnAnimationEndListener getCouiAnimationEndListener(Property property) {
            return this.this$0.getCouiGlobalAnimationEndListener();
        }

        public final Consumer getCouiAnimationStartListener() {
            return this.couiDynamicAnimationConsumer;
        }

        public final boolean wasAdded(View view) {
            return this.this$0.mNewAddChildren.contains(view);
        }
    }

    /* JADX INFO: compiled from: go/retraceme fd74bf9514dd0739b92d67a6d48a51eb10aaf8df760d83496b8dcfcf83e5f698 */
    public final class Companion {
    }

    public ContainerStateAnimator(Context context, NotificationCapsuleContainer notificationCapsuleContainer, ContainerAnimatorViewHelper containerAnimatorViewHelper) {
        this.hostLayout = notificationCapsuleContainer;
        this.hostLayoutHelper = containerAnimatorViewHelper;
        this.springItemAnimator = new CapsuleItemPhysicalAnimator(notificationCapsuleContainer, new ContainerStateAnimator$springItemAnimator$1(0, this, ContainerStateAnimator.class, "onAnimationFinished", "onAnimationFinished()V", 0));
        initView(context);
        AnonymousClass1 anonymousClass1 = new AnonymousClass1();
        anonymousClass1.this$0 = this;
        ContainerStateAnimator$1$mDynamicAnimationConsumer$1 containerStateAnimator$1$mDynamicAnimationConsumer$1 = new ContainerStateAnimator$1$mDynamicAnimationConsumer$1(0);
        containerStateAnimator$1$mDynamicAnimationConsumer$1.this$0 = this;
        VarHandle.storeStoreFence();
        anonymousClass1.mDynamicAnimationConsumer = containerStateAnimator$1$mDynamicAnimationConsumer$1;
        ContainerStateAnimator$1$mDynamicAnimationConsumer$1 containerStateAnimator$1$mDynamicAnimationConsumer$2 = new ContainerStateAnimator$1$mDynamicAnimationConsumer$1(1);
        containerStateAnimator$1$mDynamicAnimationConsumer$2.this$0 = this;
        VarHandle.storeStoreFence();
        anonymousClass1.couiDynamicAnimationConsumer = containerStateAnimator$1$mDynamicAnimationConsumer$2;
        VarHandle.storeStoreFence();
        this.animationProperties = anonymousClass1;
    }

    /* JADX INFO: Access modifiers changed from: private */
    public final COUIDynamicAnimation.OnAnimationEndListener getCouiGlobalAnimationEndListener() {
        if (!this.mCouiAnimationEndPool.empty()) {
            return this.mCouiAnimationEndPool.pop();
        }
        ContainerStateAnimator$couiGlobalAnimationEndListener$1 containerStateAnimator$couiGlobalAnimationEndListener$1 = new ContainerStateAnimator$couiGlobalAnimationEndListener$1();
        containerStateAnimator$couiGlobalAnimationEndListener$1.this$0 = this;
        VarHandle.storeStoreFence();
        return containerStateAnimator$couiGlobalAnimationEndListener$1;
    }

    /* JADX INFO: Access modifiers changed from: private */
    public final DynamicAnimation.OnAnimationEndListener getGlobalAnimationEndListener() {
        if (!this.mAnimationEndPool.empty()) {
            return this.mAnimationEndPool.pop();
        }
        globalAnimationEndListener.1 r0 = new globalAnimationEndListener.1();
        r0.this$0 = this;
        VarHandle.storeStoreFence();
        return r0;
    }

    /* JADX INFO: Access modifiers changed from: private */
    public final AnimatorListenerAdapter getGlobalAnimationFinishedListener() {
        return !this.mAnimationListenerPool.empty() ? this.mAnimationListenerPool.pop() : new AnimatorListenerAdapter() { // from class: com.oplus.systemui.notification.lockscreen.capsule.animtor.ContainerStateAnimator$globalAnimationFinishedListener$1
            public boolean mWasCancelled;

            @Override // android.animation.AnimatorListenerAdapter, android.animation.Animator.AnimatorListener
            public final void onAnimationCancel(Animator animator) {
                this.mWasCancelled = true;
            }

            @Override // android.animation.AnimatorListenerAdapter, android.animation.Animator.AnimatorListener
            public final void onAnimationEnd(Animator animator) {
                this.this$0.animatorSet.remove(animator);
                StackStateLogger stackStateLogger = this.this$0.mLogger;
                if (stackStateLogger != null) {
                    stackStateLogger.logHUNViewAppearingWithAddEvent("mWasCancelled = " + this.mWasCancelled);
                }
                if (this.this$0.animatorSet.isEmpty() && !this.mWasCancelled) {
                    this.this$0.onAnimationFinished();
                }
                this.this$0.mAnimationListenerPool.push(this);
            }

            @Override // android.animation.AnimatorListenerAdapter, android.animation.Animator.AnimatorListener
            public final void onAnimationStart(Animator animator) {
                this.mWasCancelled = false;
                this.this$0.animatorSet.add(animator);
            }
        };
    }

    public final OplusAnimationFilter getAnimationFilter() {
        return this.animationFilter;
    }

    public final OplusAnimationProperties getAnimationProperties() {
        return this.animationProperties;
    }

    public final CapsuleItemBaseAnimator getItemAnimator() {
        return this.springItemAnimator;
    }

    public final void initView(Context context) {
    }

    public final boolean isRunning() {
        return !this.animatorSet.isEmpty();
    }

    public final void onAnimationFinished() {
        if (LogUtil.getNormalDebug() && LogUtil.isTagValid(LogUtil.TAG_NOTIFICATION)) {
            Log.d("SystemUi--Notification", "ContainerStateAnimator-->onAnimationFinished");
        }
        this.hostLayoutHelper.onChildAnimationFinished();
        ArrayList<ExpandableView> arrayList = this.mTransientViewsToRemove;
        int size = arrayList.size();
        int i = 0;
        while (i < size) {
            ExpandableView expandableView = arrayList.get(i);
            i++;
            expandableView.removeFromTransientContainer();
        }
        this.mTransientViewsToRemove.clear();
    }

    public final void setLogger(StackStateLogger stackStateLogger) {
        this.mLogger = stackStateLogger;
    }

    /* JADX WARN: Code duplicated, block: B:120:0x02b6  */
    /* JADX WARN: Code duplicated, block: B:122:0x02bd  */
    /* JADX WARN: Code duplicated, block: B:124:0x02cb  */
    /* JADX WARN: Code duplicated, block: B:135:0x035c  */
    /* JADX WARN: Code duplicated, block: B:136:0x0360  */
    /* JADX WARN: Code duplicated, block: B:139:0x0369  */
    /* JADX WARN: Code duplicated, block: B:144:0x0374  */
    /* JADX WARN: Code duplicated, block: B:175:0x041d  */
    /* JADX WARN: Code duplicated, block: B:180:0x0428  */
    /* JADX WARN: Code duplicated, block: B:183:0x0437  */
    /* JADX WARN: Code duplicated, block: B:185:0x0442  */
    /* JADX WARN: Code duplicated, block: B:187:0x0450 A[DONT_INVERT] */
    /* JADX WARN: Code duplicated, block: B:189:0x0453  */
    /* JADX WARN: Code duplicated, block: B:191:0x0463  */
    /* JADX WARN: Code duplicated, block: B:199:0x0479  */
    /* JADX WARN: Code duplicated, block: B:201:0x0480  */
    /* JADX WARN: Code duplicated, block: B:228:0x0477 A[SYNTHETIC] */
    /* JADX WARN: Code duplicated, block: B:229:0x048f A[SYNTHETIC] */
    /* JADX WARN: Code duplicated, block: B:232:0x047e A[ADDED_TO_REGION, REMOVE, SYNTHETIC] */
    /* JADX WARN: Code duplicated, block: B:237:0x0461 A[SYNTHETIC] */
    /* JADX WARN: Code duplicated, block: B:239:0x02d1 A[ADDED_TO_REGION, REMOVE, SYNTHETIC] */
    /* JADX WARN: Code duplicated, block: B:80:0x0156  */
    /* JADX WARN: Multi-variable type inference failed */
    /* JADX WARN: Type inference failed for: r6v23, types: [android.widget.FrameLayout, com.android.systemui.statusbar.notification.row.ExpandableView, com.oplus.systemui.notification.lockscreen.capsule.CapsuleItemView] */
    /* JADX WARN: Type inference failed for: r8v8, types: [android.view.View, android.widget.FrameLayout, com.android.systemui.statusbar.notification.row.ExpandableView, com.oplus.systemui.notification.lockscreen.capsule.CapsuleItemView, java.lang.Object] */
    public final void startAnimationForEvents(ArrayList<AnimationEvent> arrayList, long j, LockScreenAmbientState lockScreenAmbientState) {
        char c;
        int i;
        float f;
        boolean z;
        float f2;
        char c2;
        boolean z2;
        boolean zWasAdded;
        boolean z3;
        long j2;
        ArrayList<AnimationEvent> arrayList2;
        int size;
        int i2;
        long jLongValue;
        int animationType;
        ArrayList<AnimationEvent> arrayList3;
        int size2;
        int i3;
        AnimationEvent animationEvent;
        int size3;
        int i4;
        AnimationEvent animationEvent2;
        CapsuleItemView otherView;
        CapsuleViewState capsuleViewStateM74getViewState;
        boolean z4;
        OplusExpandableViewEx oplusExpandableViewEx;
        this.hostLayout.getCapsuleEarController().setHoldBaseTransitionY(null);
        int size4 = arrayList.size();
        boolean z5 = false;
        int i5 = 0;
        while (true) {
            c = 7;
            i = 5;
            f = 0.0f;
            z = true;
            if (i5 >= size4) {
                break;
            }
            AnimationEvent animationEvent3 = arrayList.get(i5);
            i5++;
            AnimationEvent animationEvent4 = animationEvent3;
            if (animationEvent4.getAnimationType() == 7) {
                this.mNewEvents.add(animationEvent4);
            } else {
                CapsuleItemView view = animationEvent4.getView();
                if (view != null) {
                    if (animationEvent4.getAnimationType() == 0) {
                        this.mNewAddChildren.add(view);
                        this.withOutAnimation.add(view);
                        CapsuleItemView otherView2 = animationEvent4.getOtherView();
                        if (otherView2 != null) {
                            this.withOutAnimation.add(otherView2);
                        }
                        getItemAnimator().doAddAnimation(view, animationEvent4.getOtherView());
                    } else {
                        if (animationEvent4.getAnimationType() == 1) {
                            this.withOutAnimation.add(view);
                            CapsuleItemView otherView3 = animationEvent4.getOtherView();
                            if (otherView3 != null) {
                                this.withOutAnimation.add(otherView3);
                            }
                            getItemAnimator().doRemoveAnimation(view, animationEvent4.getOtherView());
                        } else if (animationEvent4.getAnimationType() == 2 || animationEvent4.getAnimationType() == 4) {
                            ExpandableView referNotificationView = animationEvent4.getReferNotificationView();
                            if (referNotificationView != null) {
                                boolean z6 = animationEvent4.getAnimationType() == 2;
                                if (z6) {
                                    z4 = false;
                                } else {
                                    CapsuleItemView immersiveCard = lockScreenAmbientState.getImmersiveCard();
                                    CapsuleItemData entry = immersiveCard != null ? immersiveCard.getEntry() : null;
                                    CapsuleSeedingData capsuleSeedingData = entry instanceof CapsuleSeedingData ? (CapsuleSeedingData) entry : null;
                                    if (capsuleSeedingData == null || capsuleSeedingData.getBgType() != 1) {
                                        z4 = false;
                                    } else {
                                        z4 = true;
                                    }
                                }
                                view.bindToStackView(referNotificationView, true, z6, z4);
                            }
                            if (animationEvent4.getAnimationType() == 2 && (otherView = animationEvent4.getOtherView()) != null) {
                                if (otherView.isBindToStackView()) {
                                    otherView.unBindStackView("handleLastCardWhenExitImmersive", false);
                                }
                                CapsuleViewState capsuleViewStateM74getViewState2 = otherView.getViewState();
                                capsuleViewStateM74getViewState2.setContentScale(ExtStateAnimParams.ImmerToCapsule.INSTANCE.getLAST_CARD_START_SCALE().get().floatValue());
                                ((ExpandableViewState) capsuleViewStateM74getViewState2).mExpandableViewStateEx.setBlurRadio(70.0f);
                                capsuleViewStateM74getViewState2.setAlpha(0.0f, "ContainerStateAnimator.handleLastCardWhenExitImmersive");
                                CapsuleItemView baseCard = lockScreenAmbientState.getBaseCard();
                                if (baseCard != null && (capsuleViewStateM74getViewState = baseCard.getViewState()) != null && capsuleViewStateM74getViewState.getContentWidth() == CapsuleConfig.INSTANCE.getItemWidthMultiCard() && this.hostLayout.getCapsuleEarController().getEarVisible()) {
                                    this.hostLayout.getCapsuleEarController().setHoldBaseTransitionY(otherView.getKey());
                                }
                            }
                        } else if (animationEvent4.getAnimationType() == 3 || animationEvent4.getAnimationType() == 5) {
                            ExpandableView referNotificationView2 = animationEvent4.getReferNotificationView();
                            if (referNotificationView2 != null) {
                                z = animationEvent4.getAnimationType() == 3;
                                CapsuleItemView.Companion companion = CapsuleItemView.Companion;
                                view.bindToStackView(referNotificationView2, false, z, false);
                            }
                            if (animationEvent4.getAnimationType() == 3) {
                                if (referNotificationView2 != null && (oplusExpandableViewEx = referNotificationView2.mExpandableViewEx) != null) {
                                    oplusExpandableViewEx.setImmersiveProgress(1.0f);
                                }
                                ?? otherView4 = animationEvent4.getOtherView();
                                if (otherView4 != 0) {
                                    if (otherView4.isBindToStackView()) {
                                        otherView4.unBindStackView("handleNextCardWhenEnterImmersive", false);
                                    }
                                    if (otherView4.getVisibility() == 4 && !((ViewState) otherView4.getViewState()).hidden) {
                                        float fFloatValue = ExtStateAnimParams.CapsuleToImmer.INSTANCE.getNEXT_CARD_START_SCALE().get().floatValue();
                                        otherView4.setAlpha(0.0f);
                                        ((ExpandableView) otherView4).mExpandableViewEx.setBlurRadio(70.0f);
                                        otherView4.setContentScale(fFloatValue);
                                    }
                                    if (otherView4.getViewState().getContentWidth() == CapsuleConfig.INSTANCE.getItemWidthMultiCard() && this.hostLayout.getCapsuleEarController().getEarVisible()) {
                                        this.hostLayout.getCapsuleEarController().setHoldBaseTransitionY(otherView4.getKey());
                                    }
                                }
                            }
                        }
                        this.mNewEvents.add(animationEvent4);
                    }
                    z5 = true;
                    this.mNewEvents.add(animationEvent4);
                }
            }
        }
        int childCount = this.hostLayout.getChildCount();
        this.animationFilter.applyCombination(this.mNewEvents);
        ArrayList<AnimationEvent> arrayList4 = this.mNewEvents;
        ArrayList arrayList5 = new ArrayList();
        int size5 = arrayList4.size();
        int i6 = 0;
        while (i6 < size5) {
            AnimationEvent animationEvent5 = arrayList4.get(i6);
            i6++;
            AnimationEvent animationEvent6 = animationEvent5;
            CollectionsKt__MutableCollectionsKt.addAll(arrayList5, CollectionsKt__CollectionsKt.listOfNotNull(new CapsuleItemView[]{animationEvent6.getView(), animationEvent6.getOtherView()}));
        }
        this.mCurrentAdditionalDelay = j;
        this.mCurrentLength = AnimationEvent.Companion.combineLength(this.mNewEvents);
        OplusAnimationProperties oplusAnimationProperties = this.animationProperties;
        ArrayList<AnimationEvent> arrayList6 = this.mNewEvents;
        if (arrayList6 != null && arrayList6.isEmpty()) {
            f2 = f;
            if (arrayList6 == null) {
                size3 = arrayList6.size();
                i4 = 0;
                while (i4 < size3) {
                    AnimationEvent animationEvent7 = arrayList6.get(i4);
                    i4++;
                    animationEvent2 = animationEvent7;
                    if (animationEvent2.getAnimationType() != 2) {
                    }
                    CapsuleViewState.Companion companion2 = CapsuleViewState.Companion;
                    oplusAnimationProperties.setCustomInterpolator(companion2.getCONTENT_SCALE_PROPERTY().getProperty(), ExtStateAnimParams.INSTANCE.toInterpolator(ExtStateAnimParams.ImmerToCapsule.INSTANCE.getLAST_CARD_START_SPRING()));
                    Property property = View.SCALE_X;
                    COUISpringInterpolator cOUISpringInterpolator = CAPSULE_TO_STACK_INTERPOLATOR;
                    oplusAnimationProperties.setCustomInterpolator(property, cOUISpringInterpolator);
                    oplusAnimationProperties.setCustomInterpolator(View.SCALE_Y, cOUISpringInterpolator);
                    oplusAnimationProperties.setCustomInterpolator(View.TRANSLATION_Y, cOUISpringInterpolator);
                    oplusAnimationProperties.setCustomInterpolator(companion2.getWIDTH_PROPERTY().getProperty(), cOUISpringInterpolator);
                    oplusAnimationProperties.setCustomInterpolator(companion2.getCONTENT_WIDTH_PROPERTY().getProperty(), cOUISpringInterpolator);
                    OplusExpandableViewStateExImpl.Companion companion3 = OplusExpandableViewStateExImpl.Companion;
                    oplusAnimationProperties.setCustomInterpolator(companion3.getCLIP_WIDTH_PROPERTY().getProperty(), cOUISpringInterpolator);
                    oplusAnimationProperties.setCustomInterpolator(companion3.getBLUR_RADIO_PROPERTY().getProperty(), cOUISpringInterpolator);
                    oplusAnimationProperties.setCustomInterpolator(companion3.getANIMATION_CORNER_RADIUS_PROPERTY().getProperty(), cOUISpringInterpolator);
                    oplusAnimationProperties.setCustomInterpolator(View.ALPHA, cOUISpringInterpolator);
                    break;
                }
            } else {
                size3 = arrayList6.size();
                i4 = 0;
                while (i4 < size3) {
                    AnimationEvent animationEvent8 = arrayList6.get(i4);
                    i4++;
                    animationEvent2 = animationEvent8;
                    if (animationEvent2.getAnimationType() != 2) {
                    }
                    CapsuleViewState.Companion companion4 = CapsuleViewState.Companion;
                    oplusAnimationProperties.setCustomInterpolator(companion4.getCONTENT_SCALE_PROPERTY().getProperty(), ExtStateAnimParams.INSTANCE.toInterpolator(ExtStateAnimParams.ImmerToCapsule.INSTANCE.getLAST_CARD_START_SPRING()));
                    Property property2 = View.SCALE_X;
                    COUISpringInterpolator cOUISpringInterpolator2 = CAPSULE_TO_STACK_INTERPOLATOR;
                    oplusAnimationProperties.setCustomInterpolator(property2, cOUISpringInterpolator2);
                    oplusAnimationProperties.setCustomInterpolator(View.SCALE_Y, cOUISpringInterpolator2);
                    oplusAnimationProperties.setCustomInterpolator(View.TRANSLATION_Y, cOUISpringInterpolator2);
                    oplusAnimationProperties.setCustomInterpolator(companion4.getWIDTH_PROPERTY().getProperty(), cOUISpringInterpolator2);
                    oplusAnimationProperties.setCustomInterpolator(companion4.getCONTENT_WIDTH_PROPERTY().getProperty(), cOUISpringInterpolator2);
                    OplusExpandableViewStateExImpl.Companion companion5 = OplusExpandableViewStateExImpl.Companion;
                    oplusAnimationProperties.setCustomInterpolator(companion5.getCLIP_WIDTH_PROPERTY().getProperty(), cOUISpringInterpolator2);
                    oplusAnimationProperties.setCustomInterpolator(companion5.getBLUR_RADIO_PROPERTY().getProperty(), cOUISpringInterpolator2);
                    oplusAnimationProperties.setCustomInterpolator(companion5.getANIMATION_CORNER_RADIUS_PROPERTY().getProperty(), cOUISpringInterpolator2);
                    oplusAnimationProperties.setCustomInterpolator(View.ALPHA, cOUISpringInterpolator2);
                    break;
                }
            }
        } else {
            int size6 = arrayList6.size();
            int i7 = 0;
            while (true) {
                if (i7 < size6) {
                    AnimationEvent animationEvent9 = arrayList6.get(i7);
                    i7++;
                    AnimationEvent animationEvent10 = animationEvent9;
                    f2 = f;
                    if (animationEvent10.getAnimationType() == 3 || animationEvent10.getAnimationType() == 5) {
                        Property property3 = View.SCALE_X;
                        COUISpringInterpolator cOUISpringInterpolator3 = CAPSULE_TO_STACK_INTERPOLATOR;
                        oplusAnimationProperties.setCustomInterpolator(property3, cOUISpringInterpolator3);
                        oplusAnimationProperties.setCustomInterpolator(View.SCALE_Y, cOUISpringInterpolator3);
                        CapsuleViewState.Companion companion6 = CapsuleViewState.Companion;
                        oplusAnimationProperties.setCustomInterpolator(companion6.getCONTENT_SCALE_PROPERTY().getProperty(), ExtStateAnimParams.INSTANCE.toInterpolator(ExtStateAnimParams.CapsuleToImmer.INSTANCE.getNEXT_CARD_START_SPRING()));
                        oplusAnimationProperties.setCustomInterpolator(View.TRANSLATION_Y, cOUISpringInterpolator3);
                        oplusAnimationProperties.setCustomInterpolator(companion6.getWIDTH_PROPERTY().getProperty(), cOUISpringInterpolator3);
                        oplusAnimationProperties.setCustomInterpolator(companion6.getCONTENT_WIDTH_PROPERTY().getProperty(), cOUISpringInterpolator3);
                        OplusExpandableViewStateExImpl.Companion companion7 = OplusExpandableViewStateExImpl.Companion;
                        oplusAnimationProperties.setCustomInterpolator(companion7.getCLIP_WIDTH_PROPERTY().getProperty(), cOUISpringInterpolator3);
                        oplusAnimationProperties.setCustomInterpolator(companion7.getBLUR_RADIO_PROPERTY().getProperty(), cOUISpringInterpolator3);
                        oplusAnimationProperties.setCustomInterpolator(companion7.getANIMATION_CORNER_RADIUS_PROPERTY().getProperty(), cOUISpringInterpolator3);
                        oplusAnimationProperties.setCustomInterpolator(View.ALPHA, cOUISpringInterpolator3);
                    } else {
                        f = f2;
                    }
                } else {
                    f2 = f;
                    if (arrayList6 == null || !arrayList6.isEmpty()) {
                        size3 = arrayList6.size();
                        i4 = 0;
                        while (i4 < size3) {
                            AnimationEvent animationEvent11 = arrayList6.get(i4);
                            i4++;
                            animationEvent2 = animationEvent11;
                            if (animationEvent2.getAnimationType() != 2 || animationEvent2.getAnimationType() == 4) {
                                CapsuleViewState.Companion companion8 = CapsuleViewState.Companion;
                                oplusAnimationProperties.setCustomInterpolator(companion8.getCONTENT_SCALE_PROPERTY().getProperty(), ExtStateAnimParams.INSTANCE.toInterpolator(ExtStateAnimParams.ImmerToCapsule.INSTANCE.getLAST_CARD_START_SPRING()));
                                Property property4 = View.SCALE_X;
                                COUISpringInterpolator cOUISpringInterpolator4 = CAPSULE_TO_STACK_INTERPOLATOR;
                                oplusAnimationProperties.setCustomInterpolator(property4, cOUISpringInterpolator4);
                                oplusAnimationProperties.setCustomInterpolator(View.SCALE_Y, cOUISpringInterpolator4);
                                oplusAnimationProperties.setCustomInterpolator(View.TRANSLATION_Y, cOUISpringInterpolator4);
                                oplusAnimationProperties.setCustomInterpolator(companion8.getWIDTH_PROPERTY().getProperty(), cOUISpringInterpolator4);
                                oplusAnimationProperties.setCustomInterpolator(companion8.getCONTENT_WIDTH_PROPERTY().getProperty(), cOUISpringInterpolator4);
                                OplusExpandableViewStateExImpl.Companion companion9 = OplusExpandableViewStateExImpl.Companion;
                                oplusAnimationProperties.setCustomInterpolator(companion9.getCLIP_WIDTH_PROPERTY().getProperty(), cOUISpringInterpolator4);
                                oplusAnimationProperties.setCustomInterpolator(companion9.getBLUR_RADIO_PROPERTY().getProperty(), cOUISpringInterpolator4);
                                oplusAnimationProperties.setCustomInterpolator(companion9.getANIMATION_CORNER_RADIUS_PROPERTY().getProperty(), cOUISpringInterpolator4);
                                oplusAnimationProperties.setCustomInterpolator(View.ALPHA, cOUISpringInterpolator4);
                                break;
                            }
                        }
                    }
                }
            }
        }
        int i8 = 0;
        int i9 = 0;
        while (i8 < childCount) {
            ?? r8 = (CapsuleItemView) this.hostLayout.getChildAt(i8);
            CapsuleViewState capsuleViewStateM74getViewState3 = r8.getViewState();
            if (((ViewState) capsuleViewStateM74getViewState3).gone) {
                c2 = c;
                z2 = z;
            } else if (r8.isBindToStackView()) {
                CapsuleConfig.INSTANCE.getClass();
                if (!CapsuleConfig.isLightOs) {
                    c2 = c;
                    z2 = z;
                } else if (!this.withOutAnimation.contains(r8)) {
                    c2 = c;
                    z2 = z;
                } else if (this.mShadeExpanded && !ViewState.isAnimatingY((View) r8)) {
                    c2 = c;
                    z2 = z;
                    capsuleViewStateM74getViewState3.applyToView(r8);
                } else if (r8.getVisibility() == 0 || (!(((ViewState) r8.getViewState()).hidden || r8.getViewState().getAlpha() == f2) || arrayList5.contains(r8))) {
                    if (this.animationProperties.wasAdded((View) r8) && i9 < i) {
                        i9++;
                    }
                    zWasAdded = this.animationProperties.wasAdded((View) r8);
                    OplusAnimationProperties oplusAnimationProperties2 = this.animationProperties;
                    ((AnimationProperties) oplusAnimationProperties2).duration = this.mCurrentLength;
                    ((AnimationProperties) oplusAnimationProperties2).delay = 0L;
                    if (capsuleViewStateM74getViewState3.getYTranslation() != r8.getTranslationY() && capsuleViewStateM74getViewState3.getZTranslation() == r8.getTranslationZ() && capsuleViewStateM74getViewState3.getAlpha() == r8.getAlpha() && ((ExpandableViewState) capsuleViewStateM74getViewState3).height == r8.getActualHeight() && ((ExpandableViewState) capsuleViewStateM74getViewState3).clipTopAmount == r8.getClipTopAmount() && capsuleViewStateM74getViewState3.getContentScale() == r8.getContentScale()) {
                        z3 = false;
                    } else {
                        z3 = z;
                    }
                    if (zWasAdded || (((AnimationFilter) this.animationFilter).hasDelays && z3)) {
                        OplusAnimationProperties oplusAnimationProperties3 = this.animationProperties;
                        long j3 = this.mCurrentAdditionalDelay;
                        j2 = ((AnimationFilter) this.animationFilter).customDelay;
                        if (j2 == -1) {
                            arrayList2 = this.mNewEvents;
                            size = arrayList2.size();
                            i2 = 0;
                            jLongValue = 0;
                            while (i2 < size) {
                                AnimationEvent animationEvent12 = arrayList2.get(i2);
                                i2++;
                                animationType = animationEvent12.getAnimationType();
                                if (animationType == 0) {
                                    jLongValue = 384;
                                } else if (animationType == 3) {
                                    jLongValue = ExtStateAnimParams.CapsuleToImmer.INSTANCE.getNEXT_CARD_DELAY().get().longValue();
                                }
                            }
                            j2 = jLongValue;
                        }
                        ((AnimationProperties) oplusAnimationProperties3).delay = j3 + j2;
                    }
                    OplusAnimationProperties oplusAnimationProperties4 = this.animationProperties;
                    arrayList3 = this.mNewEvents;
                    if (arrayList3 == null || !arrayList3.isEmpty()) {
                        size2 = arrayList3.size();
                        i3 = 0;
                        while (true) {
                            if (i3 >= size2) {
                                c2 = 7;
                                break;
                            }
                            AnimationEvent animationEvent13 = arrayList3.get(i3);
                            i3++;
                            animationEvent = animationEvent13;
                            c2 = 7;
                            if (animationEvent.getAnimationType() != 7 && Intrinsics.areEqual(animationEvent.getCommonTag(), AnimationEvent.COMMON_ANIMATION_BOTTOM_CHANGED)) {
                                oplusAnimationProperties4.setCustomInterpolator(View.TRANSLATION_Y, ExtStateAnimParams.INSTANCE.toInterpolator(ExtStateAnimParams.CommonParams.INSTANCE.getCAPSULE_BOTTOM()));
                                break;
                            }
                        }
                    } else {
                        c2 = 7;
                        break;
                    }
                    OplusAnimationFilter oplusAnimationFilter = this.animationFilter;
                    z2 = true;
                    ((AnimationFilter) oplusAnimationFilter).animateX = true;
                    ArraySet arraySet = ((AnimationFilter) oplusAnimationFilter).mAnimatedProperties;
                    ((AnimationFilter) oplusAnimationFilter).animateAlpha = true;
                    ((AnimationFilter) oplusAnimationFilter).animateHeight = true;
                    ((AnimationFilter) oplusAnimationFilter).animateY = true;
                    oplusAnimationFilter.animateScale();
                    CapsuleViewState.Companion companion10 = CapsuleViewState.Companion;
                    arraySet.add(companion10.getWIDTH_PROPERTY().getProperty());
                    arraySet.add(companion10.getCONTENT_SCALE_PROPERTY().getProperty());
                    arraySet.add(companion10.getCONTENT_WIDTH_PROPERTY().getProperty());
                    OplusExpandableViewStateExImpl.Companion companion11 = OplusExpandableViewStateExImpl.Companion;
                    arraySet.add(companion11.getBLUR_RADIO_PROPERTY().getProperty());
                    arraySet.add(companion11.getCLIP_WIDTH_PROPERTY().getProperty());
                    arraySet.add(companion11.getANIMATION_CORNER_RADIUS_PROPERTY().getProperty());
                    capsuleViewStateM74getViewState3.animateTo(r8, this.animationProperties);
                } else {
                    if (LogUtil.getNormalDebug() && LogUtil.isTagValid(LogUtil.TAG_NOTIFICATION)) {
                        Log.d("SystemUi--Notification", "ContainerStateAnimator-->".concat("immediatelyApplyToView:" + ((Object) r8)));
                    }
                    capsuleViewStateM74getViewState3.applyToView(r8);
                    c2 = c;
                    z2 = z;
                }
            } else if (!this.withOutAnimation.contains(r8)) {
                c2 = c;
                z2 = z;
            } else if (this.mShadeExpanded) {
                if (r8.getVisibility() == 0) {
                }
                if (this.animationProperties.wasAdded((View) r8)) {
                    i9++;
                }
                zWasAdded = this.animationProperties.wasAdded((View) r8);
                OplusAnimationProperties oplusAnimationProperties5 = this.animationProperties;
                ((AnimationProperties) oplusAnimationProperties5).duration = this.mCurrentLength;
                ((AnimationProperties) oplusAnimationProperties5).delay = 0L;
                if (capsuleViewStateM74getViewState3.getYTranslation() != r8.getTranslationY()) {
                    z3 = z;
                } else {
                    z3 = z;
                }
                if (zWasAdded) {
                    OplusAnimationProperties oplusAnimationProperties6 = this.animationProperties;
                    long j4 = this.mCurrentAdditionalDelay;
                    j2 = ((AnimationFilter) this.animationFilter).customDelay;
                    if (j2 == -1) {
                        arrayList2 = this.mNewEvents;
                        size = arrayList2.size();
                        i2 = 0;
                        jLongValue = 0;
                        while (i2 < size) {
                            AnimationEvent animationEvent14 = arrayList2.get(i2);
                            i2++;
                            animationType = animationEvent14.getAnimationType();
                            if (animationType == 0) {
                                jLongValue = 384;
                            } else if (animationType == 3) {
                                jLongValue = ExtStateAnimParams.CapsuleToImmer.INSTANCE.getNEXT_CARD_DELAY().get().longValue();
                            }
                        }
                        j2 = jLongValue;
                    }
                    ((AnimationProperties) oplusAnimationProperties6).delay = j4 + j2;
                } else {
                    OplusAnimationProperties oplusAnimationProperties7 = this.animationProperties;
                    long j5 = this.mCurrentAdditionalDelay;
                    j2 = ((AnimationFilter) this.animationFilter).customDelay;
                    if (j2 == -1) {
                        arrayList2 = this.mNewEvents;
                        size = arrayList2.size();
                        i2 = 0;
                        jLongValue = 0;
                        while (i2 < size) {
                            AnimationEvent animationEvent15 = arrayList2.get(i2);
                            i2++;
                            animationType = animationEvent15.getAnimationType();
                            if (animationType == 0) {
                                jLongValue = 384;
                            } else if (animationType == 3) {
                                jLongValue = ExtStateAnimParams.CapsuleToImmer.INSTANCE.getNEXT_CARD_DELAY().get().longValue();
                            }
                        }
                        j2 = jLongValue;
                    }
                    ((AnimationProperties) oplusAnimationProperties7).delay = j5 + j2;
                }
                OplusAnimationProperties oplusAnimationProperties8 = this.animationProperties;
                arrayList3 = this.mNewEvents;
                if (arrayList3 == null) {
                    size2 = arrayList3.size();
                    i3 = 0;
                    while (true) {
                        if (i3 >= size2) {
                            c2 = 7;
                            break;
                        }
                        AnimationEvent animationEvent16 = arrayList3.get(i3);
                        i3++;
                        animationEvent = animationEvent16;
                        c2 = 7;
                        if (animationEvent.getAnimationType() != 7) {
                        }
                    }
                } else {
                    size2 = arrayList3.size();
                    i3 = 0;
                    while (true) {
                        if (i3 >= size2) {
                            c2 = 7;
                            break;
                        }
                        AnimationEvent animationEvent17 = arrayList3.get(i3);
                        i3++;
                        animationEvent = animationEvent17;
                        c2 = 7;
                        if (animationEvent.getAnimationType() != 7) {
                        }
                    }
                }
                OplusAnimationFilter oplusAnimationFilter2 = this.animationFilter;
                z2 = true;
                ((AnimationFilter) oplusAnimationFilter2).animateX = true;
                ArraySet arraySet2 = ((AnimationFilter) oplusAnimationFilter2).mAnimatedProperties;
                ((AnimationFilter) oplusAnimationFilter2).animateAlpha = true;
                ((AnimationFilter) oplusAnimationFilter2).animateHeight = true;
                ((AnimationFilter) oplusAnimationFilter2).animateY = true;
                oplusAnimationFilter2.animateScale();
                CapsuleViewState.Companion companion12 = CapsuleViewState.Companion;
                arraySet2.add(companion12.getWIDTH_PROPERTY().getProperty());
                arraySet2.add(companion12.getCONTENT_SCALE_PROPERTY().getProperty());
                arraySet2.add(companion12.getCONTENT_WIDTH_PROPERTY().getProperty());
                OplusExpandableViewStateExImpl.Companion companion13 = OplusExpandableViewStateExImpl.Companion;
                arraySet2.add(companion13.getBLUR_RADIO_PROPERTY().getProperty());
                arraySet2.add(companion13.getCLIP_WIDTH_PROPERTY().getProperty());
                arraySet2.add(companion13.getANIMATION_CORNER_RADIUS_PROPERTY().getProperty());
                capsuleViewStateM74getViewState3.animateTo(r8, this.animationProperties);
            } else {
                c2 = c;
                z2 = z;
                capsuleViewStateM74getViewState3.applyToView(r8);
            }
            i8++;
            z = z2;
            c = c2;
            i = 5;
        }
        if (!isRunning() && !z5) {
            onAnimationFinished();
        }
        this.withOutAnimation.clear();
        this.mNewEvents.clear();
        this.mNewAddChildren.clear();
        ((AnimationProperties) this.animationProperties).mInterpolatorMap = null;
    }
}
