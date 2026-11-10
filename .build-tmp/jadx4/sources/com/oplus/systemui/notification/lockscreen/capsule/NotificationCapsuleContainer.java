package com.oplus.systemui.notification.lockscreen.capsule;

import android.content.Context;
import android.content.res.Configuration;
import android.graphics.Rect;
import android.graphics.RectF;
import android.graphics.RenderEffect;
import android.graphics.Shader;
import android.os.Trace;
import android.util.AttributeSet;
import android.util.Log;
import android.view.MotionEvent;
import android.view.View;
import android.view.ViewGroup;
import android.view.ViewParent;
import android.widget.ImageView;
import androidx.core.view.ViewGroupKt;
import androidx.room.util.CursorUtil$;
import com.android.keyguard.OplusLockIconView$;
import com.android.systemui.Dumpable;
import com.android.systemui.media.controls.domain.pipeline.MediaTimeoutListener$PlaybackStateListener$;
import com.android.systemui.plugins.custom.CustomRowEntry;
import com.android.systemui.statusbar.notification.AnimatableProperty;
import com.android.systemui.statusbar.notification.OplusPhysicsPropertyAnimator$;
import com.android.systemui.statusbar.notification.row.ExpandableView;
import com.android.systemui.statusbar.notification.stack.ViewState;
import com.oplus.keyguard.security.view.OplusKeyguardSimInputViewController$;
import com.oplus.keyguard.utils.KeyguardUtils;
import com.oplus.systemui.aod.display.AODDisplayUtil;
import com.oplus.systemui.aod.display.SmoothTransitionController;
import com.oplus.systemui.notification.lockscreen.capsule.animtor.ContainerAnimatorViewHelper;
import com.oplus.systemui.notification.lockscreen.capsule.background.CapsuleShardBackgroundDrawableManager;
import com.oplus.systemui.notification.lockscreen.capsule.modle.CapsuleItemData;
import com.oplus.systemui.notification.lockscreen.capsule.modle.datahandler.CapsuleDataChangeInterceptor;
import com.oplus.systemui.notification.lockscreen.capsule.utils.CapsuleViewExpandKtKt;
import com.oplus.systemui.notification.lockscreen.config.CapsuleConfig;
import com.oplus.systemui.notification.lockscreen.effect.ExtStateAnimParams;
import com.oplus.systemui.notification.lockscreen.effect.OplusPhysicsPropertyAnimatorExtKt;
import com.oplus.systemui.notification.lockscreen.lightos.CapsuleLightOSController;
import com.oplus.systemui.notification.lockscreen.seedling.CapsuleSeedingData;
import com.oplusos.systemui.common.util.LogUtil;
import java.io.PrintWriter;
import java.lang.invoke.VarHandle;
import java.util.Collection;
import java.util.Iterator;
import java.util.List;
import kotlin.Lazy;
import kotlin.LazyKt__LazyJVMKt;
import kotlin.jvm.functions.Function1;
import kotlin.jvm.internal.DefaultConstructorMarker;
import kotlin.jvm.internal.Intrinsics;
import kotlin.sequences.SequencesKt___SequencesKt;
import kotlinx.coroutines.flow.StateFlow;
import okio.Path$;

/* JADX INFO: compiled from: go/retraceme fd74bf9514dd0739b92d67a6d48a51eb10aaf8df760d83496b8dcfcf83e5f698 */
/* JADX INFO: loaded from: /home/starlight/Projects/Starlight Ports/.build-tmp/classes4.dex */
public final class NotificationCapsuleContainer extends ViewGroup implements CapsuleListContainer, Dumpable {
    public static final Companion Companion = new Companion();
    private static final String TAG = "NotificationCapsuleContainer";
    private final Lazy animatorHelper$delegate;
    public float aodProgress;
    public float blurRatio;
    private final CapsuleEarController capsuleEarController;
    public boolean changePositionInProgress;
    private final Lazy childCountFlow$delegate;
    public NotificationCapsuleContainerControllerImpl containerControllerImpl;
    private final Lazy dataInterceptor$delegate;
    public float dragScaleTranslationX;
    private final CapsuleEarView leftEarView;
    public CapsuleLightOSController lightOSController;
    public float mTranslationX;
    public int ownScrollX;
    private final Lazy preChildCountFlow$delegate;
    private final CapsuleEarView rightEarView;
    public NotificationCapsuleContainerTouchHandler scroller;
    private final CapsuleShardBackgroundDrawableManager sharedBgManager;
    private final View sharedHostView1;
    private final View sharedHostView2;
    public float stackY;
    private Function1 stackYChangeListener;
    private final AnimatableProperty touchProperty;

    /* JADX INFO: compiled from: go/retraceme fd74bf9514dd0739b92d67a6d48a51eb10aaf8df760d83496b8dcfcf83e5f698 */
    public final class Companion {
    }

    /* JADX INFO: renamed from: $r8$lambda$45_gOOb31NfaY9oZE-aBxIAZ3b8, reason: not valid java name */
    public static ContainerAnimatorViewHelper m75$r8$lambda$45_gOOb31NfaY9oZEaBxIAZ3b8(NotificationCapsuleContainer notificationCapsuleContainer) {
        return new ContainerAnimatorViewHelper(((ViewGroup) notificationCapsuleContainer).mContext, notificationCapsuleContainer, TAG);
    }

    /* JADX WARN: Multi-variable type inference failed */
    /* JADX WARN: Type inference failed for: r11v8, types: [android.view.View, android.widget.ImageView, com.oplus.systemui.notification.lockscreen.capsule.CapsuleEarView] */
    /* JADX WARN: Type inference failed for: r5v1, types: [android.view.View, android.widget.ImageView, com.oplus.systemui.notification.lockscreen.capsule.CapsuleEarView] */
    public NotificationCapsuleContainer(Context context, AttributeSet attributeSet, int i) {
        super(KeyguardUtils.Companion.updateDisplayConfiguration$default(KeyguardUtils.Companion, context, true, 4), attributeSet, i);
        this.dataInterceptor$delegate = LazyKt__LazyJVMKt.lazy(new NotificationCapsuleContainer$$ExternalSyntheticLambda1());
        CapsuleShardBackgroundDrawableManager capsuleShardBackgroundDrawableManager = new CapsuleShardBackgroundDrawableManager();
        this.sharedBgManager = capsuleShardBackgroundDrawableManager;
        NotificationCapsuleContainer$touchProperty$1 notificationCapsuleContainer$touchProperty$1 = new NotificationCapsuleContainer$touchProperty$1();
        notificationCapsuleContainer$touchProperty$1.this$0 = this;
        VarHandle.storeStoreFence();
        NotificationCapsuleContainer$touchProperty$2 notificationCapsuleContainer$touchProperty$2 = new NotificationCapsuleContainer$touchProperty$2();
        notificationCapsuleContainer$touchProperty$2.this$0 = this;
        VarHandle.storeStoreFence();
        this.touchProperty = AnimatableProperty.from("press_anim", notificationCapsuleContainer$touchProperty$1, notificationCapsuleContainer$touchProperty$2, 2131364619, 2131364618, 2131364617);
        NotificationCapsuleContainer$$ExternalSyntheticLambda2 notificationCapsuleContainer$$ExternalSyntheticLambda2 = new NotificationCapsuleContainer$$ExternalSyntheticLambda2(0);
        notificationCapsuleContainer$$ExternalSyntheticLambda2.f$0 = this;
        VarHandle.storeStoreFence();
        this.animatorHelper$delegate = LazyKt__LazyJVMKt.lazy(notificationCapsuleContainer$$ExternalSyntheticLambda2);
        NotificationCapsuleContainer$$ExternalSyntheticLambda2 notificationCapsuleContainer$$ExternalSyntheticLambda3 = new NotificationCapsuleContainer$$ExternalSyntheticLambda2(1);
        notificationCapsuleContainer$$ExternalSyntheticLambda3.f$0 = this;
        VarHandle.storeStoreFence();
        this.childCountFlow$delegate = LazyKt__LazyJVMKt.lazy(notificationCapsuleContainer$$ExternalSyntheticLambda3);
        int i2 = 2;
        NotificationCapsuleContainer$$ExternalSyntheticLambda2 notificationCapsuleContainer$$ExternalSyntheticLambda4 = new NotificationCapsuleContainer$$ExternalSyntheticLambda2(i2);
        notificationCapsuleContainer$$ExternalSyntheticLambda4.f$0 = this;
        VarHandle.storeStoreFence();
        this.preChildCountFlow$delegate = LazyKt__LazyJVMKt.lazy(notificationCapsuleContainer$$ExternalSyntheticLambda4);
        AttributeSet attributeSet2 = null;
        ?? capsuleEarView = new CapsuleEarView(i2, ((ViewGroup) this).mContext, attributeSet2, 1 == true ? 1 : 0);
        capsuleEarView.setLayoutParams(new ViewGroup.LayoutParams(getEarWidth(), -2));
        ImageView.ScaleType scaleType = ImageView.ScaleType.CENTER;
        capsuleEarView.setScaleType(scaleType);
        capsuleEarView.setTranslationZ(-1.0f);
        this.leftEarView = capsuleEarView;
        ?? capsuleEarView2 = new CapsuleEarView(i2, ((ViewGroup) this).mContext, attributeSet2, 0 == true ? 1 : 0);
        capsuleEarView2.setLayoutParams(new ViewGroup.LayoutParams(getEarWidth(), -2));
        capsuleEarView2.setScaleType(scaleType);
        capsuleEarView2.setTranslationZ(-1.0f);
        this.rightEarView = capsuleEarView2;
        this.capsuleEarController = new CapsuleEarController(capsuleEarView, capsuleEarView2);
        getAnimatorHelper().initView();
        addTransientView(capsuleEarView, 0);
        addTransientView(capsuleEarView2, 0);
        getDataInterceptor().register();
        View view = new View(((ViewGroup) this).mContext);
        CapsuleConfig capsuleConfig = CapsuleConfig.INSTANCE;
        view.setLayoutParams(new ViewGroup.LayoutParams(capsuleConfig.getItemWidth(), capsuleConfig.getItemHeight()));
        this.sharedHostView1 = view;
        View view2 = new View(((ViewGroup) this).mContext);
        view2.setLayoutParams(new ViewGroup.LayoutParams(capsuleConfig.getItemWidth(), capsuleConfig.getItemHeight()));
        this.sharedHostView2 = view2;
        addTransientView(view, 0);
        addTransientView(view2, 0);
        capsuleShardBackgroundDrawableManager.initManager(((ViewGroup) this).mContext, view, view2);
    }

    private final int getEarMargin() {
        return CapsuleConfig.INSTANCE.getEarMargin();
    }

    private final int getEarWidth() {
        return CapsuleConfig.INSTANCE.getEarWidth();
    }

    private final void setOwnScrollX(int i) {
        if (i != this.ownScrollX) {
            if (LogUtil.getNormalDebug() && LogUtil.isTagValid(LogUtil.TAG_NOTIFICATION)) {
                Log.d("SystemUi--Notification", OplusKeyguardSimInputViewController$.ExternalSyntheticOutline0.m("setOwnScrollX: ambientState.scrollX: ", getAnimatorHelper().getAmbientState().getScrollX(), i, LogUtil.SEPARATOR, "NotificationCapsuleContainer-->"));
            }
            this.ownScrollX = i;
            if (getAnimatorHelper().getAmbientState().getScrollX() != i) {
                getAnimatorHelper().getAmbientState().setScrollX(i);
                getAnimatorHelper().requestChildrenUpdate("ownScrollX:" + i);
            }
        }
    }

    /* JADX INFO: Access modifiers changed from: private */
    /* JADX WARN: Multi-variable type inference failed */
    public static final CapsuleItemView setScrimAlpha$lambda$15(View view) {
        if (view instanceof CapsuleItemView) {
            return (CapsuleItemView) view;
        }
        return null;
    }

    private final void setStackY(float f) {
        if (f == this.stackY) {
            return;
        }
        if (LogUtil.getNormalDebug() && LogUtil.isTagValid(LogUtil.TAG_NOTIFICATION)) {
            OplusPhysicsPropertyAnimator$.ExternalSyntheticOutline0.m("setOwnScrollY: ", f, "NotificationCapsuleContainer-->", "SystemUi--Notification");
        }
        this.stackY = f;
        getAnimatorHelper().getAmbientState().setStackY(f);
        getAnimatorHelper().requestChildrenUpdate("stackY");
        stackY.2 r0 = new stackY.2();
        r0.this$0 = this;
        r0.$value = f;
        VarHandle.storeStoreFence();
        post(r0);
    }

    @Override // com.oplus.systemui.notification.lockscreen.capsule.CapsuleListContainer
    public void addContainerView(View view) {
        addView(view);
    }

    @Override // com.oplus.systemui.notification.lockscreen.capsule.CapsuleListContainer
    public void addContainerViewAt(View view, int i) {
        if (view.getParent() != null && (view instanceof ExpandableView)) {
            ((ExpandableView) view).removeFromTransientContainerForAdditionTo(this);
        }
        addView(view, i);
    }

    @Override // com.oplus.systemui.notification.lockscreen.capsule.CapsuleListContainer
    public void changeViewPosition(View view, int i) {
        if (this.changePositionInProgress) {
            Path$.ExternalSyntheticBUOutline0.m$1("Reentrant call to changeViewPosition");
            return;
        }
        int iIndexOfChild = indexOfChild(view);
        if (view.getParent() != this || iIndexOfChild == i) {
            return;
        }
        this.changePositionInProgress = true;
        boolean z = view instanceof ExpandableView;
        ExpandableView expandableView = z ? (ExpandableView) view : null;
        if (expandableView != null) {
            expandableView.setChangingPosition(true);
        }
        removeView(view);
        addView(view, i);
        ExpandableView expandableView2 = z ? (ExpandableView) view : null;
        if (expandableView2 != null) {
            expandableView2.setChangingPosition(false);
        }
        this.changePositionInProgress = false;
    }

    public final void checkIfNeedCancelHScroll(String str) {
        if (this.ownScrollX != 0) {
            if (LogUtil.getNormalDebug() && LogUtil.isTagValid(LogUtil.TAG_NOTIFICATION)) {
                MediaTimeoutListener$PlaybackStateListener$.ExternalSyntheticOutline0.m("CancelHScroll:reason:", str, "NotificationCapsuleContainer-->", "SystemUi--Notification");
            }
            getScroller().getHScroller().cancelScrollByImmersive();
            setOwnScrollX(0);
        }
    }

    public final void customOverScrollToX(int i) {
        setOwnScrollX(i);
    }

    public final void customOverScrollToY(float f) {
        if (getAnimatorHelper().getAmbientState().isStackState) {
            if (LogUtil.getNormalDebug() && LogUtil.isTagValid(LogUtil.TAG_NOTIFICATION)) {
                Log.d("SystemUi--Notification", "NotificationCapsuleContainer-->customOverScrollToY: skip");
                return;
            }
            return;
        }
        setStackY(f);
        getAnimatorHelper().requestChildrenUpdate("customOverScrollToY:" + f);
    }

    public void dump(PrintWriter printWriter, String[] strArr) {
        printWriter.println("=== NotificationCapsuleContainerDumpInfo ===");
        printWriter.println("visibility: " + getVisibility());
        printWriter.println("alpha: " + getAlpha());
        getAnimatorHelper().getAmbientState().dump(printWriter, strArr);
        Iterator<T> it = CapsuleViewExpandKtKt.getCapsuleChildren(this).iterator();
        while (it.hasNext()) {
            ((CapsuleItemView) it.next()).dump(printWriter, strArr);
        }
    }

    public final CapsuleItemView findCapsuleSeedingView(String str) {
        Object obj = null;
        if (str == null) {
            return null;
        }
        for (Object obj2 : SequencesKt___SequencesKt.mapNotNull(ViewGroupKt.getChildren(this), new NotificationCapsuleContainer$$ExternalSyntheticLambda0(3))) {
            if (str.equals(((CapsuleItemView) obj2).getEntry().getKey())) {
                obj = obj2;
                break;
            }
        }
        return (CapsuleItemView) obj;
    }

    public final CapsuleItemView findCapsuleSeedingViewByServiceId(String str) {
        Object serviceId;
        Object next;
        CustomRowEntry customRowEntry;
        Iterator it = SequencesKt___SequencesKt.mapNotNull(ViewGroupKt.getChildren(this), new NotificationCapsuleContainer$$ExternalSyntheticLambda0(0)).iterator();
        do {
            serviceId = null;
            if (it.hasNext()) {
                next = it.next();
                CapsuleItemData entry = ((CapsuleItemView) next).getEntry();
                CapsuleSeedingData capsuleSeedingData = entry instanceof CapsuleSeedingData ? (CapsuleSeedingData) entry : null;
                if (capsuleSeedingData != null && (customRowEntry = capsuleSeedingData.getCustomRowEntry()) != null) {
                    serviceId = customRowEntry.getServiceId();
                }
            }
            return (CapsuleItemView) serviceId;
        } while (!Intrinsics.areEqual(serviceId, str));
        serviceId = next;
        return (CapsuleItemView) serviceId;
    }

    public final void generateImmersiveAnimation(CapsuleItemView capsuleItemView, ExpandableView expandableView, boolean z, boolean z2) {
        getAnimatorHelper().generateImmersiveAnimation(capsuleItemView, expandableView, z, z2);
    }

    public final void generateStackAnimation(CapsuleItemView capsuleItemView, ExpandableView expandableView, boolean z, boolean z2) {
        getAnimatorHelper().generateStackAnimation(capsuleItemView, expandableView, z, z2);
    }

    public final void generateStackViewRemove(ExpandableView expandableView, CapsuleItemView capsuleItemView) {
        boolean zAreEqual = Intrinsics.areEqual(getBaseCard(), capsuleItemView);
        if (LogUtil.getNormalDebug() && LogUtil.isTagValid(LogUtil.TAG_NOTIFICATION)) {
            OplusLockIconView$.ExternalSyntheticOutline0.m("generateStackViewRemove: ", "NotificationCapsuleContainer-->", "SystemUi--Notification", zAreEqual);
        }
        if (zAreEqual) {
            getAnimatorHelper().requestChildrenUpdate("generateNewBaseCardAnimation");
        }
    }

    public final ContainerAnimatorViewHelper getAnimatorHelper() {
        return (ContainerAnimatorViewHelper) this.animatorHelper$delegate.getValue();
    }

    public final CapsuleItemView getBaseCard() {
        return getAnimatorHelper().getAmbientState().getBaseCard();
    }

    public final float getBlurRatio() {
        return this.blurRatio;
    }

    public final CapsuleEarController getCapsuleEarController() {
        return this.capsuleEarController;
    }

    public final CapsuleItemView getChildAtPosition(float f, float f2) {
        return getAnimatorHelper().getChildAtPosition(f, f2);
    }

    public final StateFlow getChildCountFlow() {
        return (StateFlow) this.childCountFlow$delegate.getValue();
    }

    @Override // com.oplus.systemui.notification.lockscreen.capsule.CapsuleListContainer
    public View getContainerChildAt(int i) {
        return getChildAt(i);
    }

    @Override // com.oplus.systemui.notification.lockscreen.capsule.CapsuleListContainer
    public int getContainerChildCount() {
        return getChildCount();
    }

    public final NotificationCapsuleContainerControllerImpl getContainerControllerImpl() {
        NotificationCapsuleContainerControllerImpl notificationCapsuleContainerControllerImpl = this.containerControllerImpl;
        if (notificationCapsuleContainerControllerImpl != null) {
            return notificationCapsuleContainerControllerImpl;
        }
        return null;
    }

    /* JADX WARN: Type inference failed for: r0v2, types: [android.view.View, android.widget.FrameLayout, com.oplus.systemui.notification.lockscreen.capsule.CapsuleItemView] */
    public final CapsuleItemView getCurrentShowingCard() {
        Object next;
        ?? baseCard = getAnimatorHelper().getAmbientState().getBaseCard();
        if (baseCard != 0 && baseCard.getVisibility() == 0 && baseCard.getAlpha() == 1.0f) {
            return baseCard;
        }
        Iterator it = SequencesKt___SequencesKt.mapNotNull(ViewGroupKt.getChildren(this), new NotificationCapsuleContainer$$ExternalSyntheticLambda0(1)).iterator();
        while (true) {
            if (!it.hasNext()) {
                next = null;
                break;
            }
            next = it.next();
            ExpandableView expandableView = (CapsuleItemView) next;
            if (expandableView.getVisibility() == 0 && expandableView.getAlpha() == 1.0f) {
                break;
            }
        }
        CapsuleItemView capsuleItemView = (CapsuleItemView) next;
        return capsuleItemView == null ? getBaseCard() : capsuleItemView;
    }

    public final CapsuleDataChangeInterceptor getDataInterceptor() {
        return (CapsuleDataChangeInterceptor) this.dataInterceptor$delegate.getValue();
    }

    public final CapsuleLightOSController getLightOSController() {
        CapsuleLightOSController capsuleLightOSController = this.lightOSController;
        if (capsuleLightOSController != null) {
            return capsuleLightOSController;
        }
        return null;
    }

    public final int getMarginBottom() {
        return getAnimatorHelper().getAmbientState().getMarginBottom();
    }

    public final float getNextScrollX(Float f, float f2) {
        return getAnimatorHelper().getBackScrollX((f != null ? f.floatValue() : this.ownScrollX) + (f2 * 0.03f));
    }

    public final int getOwnScrollX() {
        return this.ownScrollX;
    }

    public final RectF getPositionRegion() {
        RectF rectF = new RectF();
        int[] iArr = new int[2];
        getLocationInWindow(iArr);
        rectF.set(iArr[0], getTranslationY() + iArr[1], iArr[0] + getWidth(), getTranslationY() + iArr[1] + getHeight());
        return rectF;
    }

    public final StateFlow getPreChildCountFlow() {
        return (StateFlow) this.preChildCountFlow$delegate.getValue();
    }

    public final NotificationCapsuleContainerTouchHandler getScroller() {
        NotificationCapsuleContainerTouchHandler notificationCapsuleContainerTouchHandler = this.scroller;
        if (notificationCapsuleContainerTouchHandler != null) {
            return notificationCapsuleContainerTouchHandler;
        }
        return null;
    }

    public final CapsuleShardBackgroundDrawableManager getSharedBgManager() {
        return this.sharedBgManager;
    }

    public final float getStackY() {
        return this.stackY;
    }

    public final Function1 getStackYChangeListener() {
        return this.stackYChangeListener;
    }

    @Override // android.view.View
    public final boolean hasOverlappingRendering() {
        return false;
    }

    @Override // android.view.ViewGroup, android.view.ViewParent
    public ViewParent invalidateChildInParent(int[] iArr, Rect rect) {
        requestAODScreenState();
        return super.invalidateChildInParent(iArr, rect);
    }

    public final boolean isBindToStackView() {
        List<CapsuleItemView> capsuleChildren = CapsuleViewExpandKtKt.getCapsuleChildren(this);
        if ((capsuleChildren instanceof Collection) && capsuleChildren.isEmpty()) {
            return false;
        }
        Iterator<T> it = capsuleChildren.iterator();
        while (it.hasNext()) {
            if (((CapsuleItemView) it.next()).isBindToStackView()) {
                return true;
            }
        }
        return false;
    }

    @Override // android.view.View
    public void onConfigurationChanged(Configuration configuration) {
        super.onConfigurationChanged(configuration);
        this.leftEarView.reloadResource();
        this.rightEarView.reloadResource();
    }

    @Override // android.view.ViewGroup, android.view.ViewParent
    public void onDescendantInvalidated(View view, View view2) {
        requestAODScreenState();
        super.onDescendantInvalidated(view, view2);
    }

    @Override // android.view.ViewGroup
    public boolean onInterceptTouchEvent(MotionEvent motionEvent) {
        if (getAnimatorHelper().getAmbientState().isStackState && motionEvent.getAction() == 0) {
            return false;
        }
        return getScroller().onInterceptTouchEvent(motionEvent) || super.onInterceptTouchEvent(motionEvent);
    }

    /* JADX WARN: Type inference fix 'apply assigned field type' failed
    java.lang.UnsupportedOperationException: ArgType.getObject(), call class: class jadx.core.dex.instructions.args.ArgType$PrimitiveArg
    	at jadx.core.dex.instructions.args.ArgType.getObject(ArgType.java:596)
    	at jadx.core.dex.attributes.nodes.ClassTypeVarsAttr.getTypeVarsMapFor(ClassTypeVarsAttr.java:35)
    	at jadx.core.dex.nodes.utils.TypeUtils.replaceClassGenerics(TypeUtils.java:177)
    	at jadx.core.dex.visitors.typeinference.FixTypesVisitor.insertExplicitUseCast(FixTypesVisitor.java:397)
    	at jadx.core.dex.visitors.typeinference.FixTypesVisitor.tryFieldTypeWithNewCasts(FixTypesVisitor.java:359)
    	at jadx.core.dex.visitors.typeinference.FixTypesVisitor.applyFieldType(FixTypesVisitor.java:309)
    	at jadx.core.dex.visitors.typeinference.FixTypesVisitor.visit(FixTypesVisitor.java:94)
     */
    @Override // android.view.ViewGroup, android.view.View
    public final void onLayout(boolean z, int i, int i2, int i3, int i4) {
        float width = getWidth() / 2.0f;
        int childCount = getChildCount();
        for (int i5 = 0; i5 < childCount; i5++) {
            getChildAt(i5).layout(0, 0, getWidth(), getHeight());
            if (i5 == 0) {
                CapsuleConfig capsuleConfig = CapsuleConfig.INSTANCE;
                int itemWidthMultiCard = (int) (width - (capsuleConfig.getItemWidthMultiCard() / 2.0f));
                int itemWidthMultiCard2 = (int) ((capsuleConfig.getItemWidthMultiCard() / 2.0f) + width);
                this.leftEarView.layout((itemWidthMultiCard - getEarWidth()) - getEarMargin(), 0, itemWidthMultiCard - getEarMargin(), capsuleConfig.getItemHeight());
                this.rightEarView.layout(getEarMargin() + itemWidthMultiCard2, 0, itemWidthMultiCard2 + getEarWidth() + getEarMargin(), capsuleConfig.getItemHeight());
            }
        }
        int width2 = getWidth();
        CapsuleConfig capsuleConfig2 = CapsuleConfig.INSTANCE;
        int itemWidth = (width2 - capsuleConfig2.getItemWidth()) / 2;
        int height = (getHeight() - capsuleConfig2.getItemHeight()) - getAnimatorHelper().getAmbientState().getMarginBottom();
        int itemWidth2 = capsuleConfig2.getItemWidth() + itemWidth;
        int itemHeight = capsuleConfig2.getItemHeight() + height;
        this.sharedHostView1.layout(itemWidth, height, itemWidth2, itemHeight);
        this.sharedHostView2.layout(itemWidth, height, itemWidth2, itemHeight);
    }

    @Override // android.view.View
    public final void onMeasure(int i, int i2) {
        Trace.beginSection("NotificationCapsuleContainer#onMeasure");
        super.onMeasure(i, i2);
        int iMakeMeasureSpec = View.MeasureSpec.makeMeasureSpec(getWidth(), View.MeasureSpec.getMode(i));
        int iMakeMeasureSpec2 = View.MeasureSpec.makeMeasureSpec(View.MeasureSpec.getSize(i2), 0);
        int childCount = getChildCount();
        for (int i3 = 0; i3 < childCount; i3++) {
            if (getChildAt(i3) instanceof CapsuleItemView) {
                measureChild(getChildAt(i3), i, i2);
            } else {
                measureChild(getChildAt(i3), iMakeMeasureSpec, iMakeMeasureSpec2);
            }
        }
        int iMakeMeasureSpec3 = View.MeasureSpec.makeMeasureSpec(getEarWidth(), 1073741824);
        measureChild(this.leftEarView, iMakeMeasureSpec3, iMakeMeasureSpec2);
        measureChild(this.rightEarView, iMakeMeasureSpec3, iMakeMeasureSpec2);
        Trace.endSection();
    }

    public final void onNsslAnimationEnd() {
        Iterator<T> it = CapsuleViewExpandKtKt.getCapsuleChildren(this).iterator();
        while (it.hasNext()) {
            ((CapsuleItemView) it.next()).onNsslAnimationEnd();
        }
        this.capsuleEarController.checkAndClearAllTransitionY();
        if (this.capsuleEarController.getHoldBaseTransitionY() != null) {
            this.capsuleEarController.setHoldBaseTransitionY(null);
        }
    }

    @Override // android.view.View
    public boolean onTouchEvent(MotionEvent motionEvent) {
        if (getAnimatorHelper().getAmbientState().isStackState && motionEvent.getAction() == 0) {
            return false;
        }
        boolean z = getScroller().onTouchEvent(motionEvent) || super.onTouchEvent(motionEvent);
        if (LogUtil.getNormalDebug() && LogUtil.isTagValid(LogUtil.TAG_NOTIFICATION)) {
            Log.d("SystemUi--Notification", "NotificationCapsuleContainer-->".concat("onTouchEvent: s= " + z + " ,event=" + motionEvent.getAction() + " "));
        }
        return z;
    }

    /* JADX WARN: Multi-variable type inference failed */
    @Override // android.view.ViewGroup
    public void onViewAdded(View view) {
        super.onViewAdded(view);
        getAnimatorHelper().onViewAddedInternal(view, this);
        float f = this.aodProgress;
        if (f != 0.0f && (view instanceof CapsuleItemView)) {
            ((CapsuleItemView) view).setScrimAlpha(f);
        }
    }

    @Override // android.view.ViewGroup
    public void onViewRemoved(View view) {
        super.onViewRemoved(view);
        getAnimatorHelper().onViewRemovedInternal(view, this);
    }

    @Override // com.oplus.systemui.notification.lockscreen.capsule.CapsuleListContainer
    public void reTopContainerView(View view, boolean z) {
        getAnimatorHelper().onViewAddedInternal(view, this);
    }

    @Override // com.oplus.systemui.notification.lockscreen.capsule.CapsuleListContainer
    public void removeContainerView(View view) {
        removeView(view);
    }

    public final void requestAODScreenState() {
        if (getVisibility() == 0 && AODDisplayUtil.getInstance(getContext()).isPanoramicProcessType()) {
            SmoothTransitionController.Companion.getInstance(getContext()).requestScreenStateForSysUiComponent(TAG, 500);
        }
    }

    /* JADX WARN: Type inference failed for: r6v1, types: [android.view.View, com.oplus.systemui.notification.lockscreen.capsule.CapsuleItemView, java.lang.Object] */
    public final void requestCancelTouchFeedback(boolean z) {
        Float fValueOf = Float.valueOf(0.0f);
        ExtStateAnimParams.CommonParams commonParams = ExtStateAnimParams.CommonParams.INSTANCE;
        if (commonParams.getTOUCH_TYPE().get().intValue() == 0) {
            return;
        }
        if (LogUtil.getNormalDebug() && LogUtil.isTagValid(LogUtil.TAG_NOTIFICATION)) {
            OplusLockIconView$.ExternalSyntheticOutline0.m("requestCancelTouchFeedback: ", "NotificationCapsuleContainer-->", "SystemUi--Notification", z);
        }
        CapsuleItemView capsuleItemView = getAnimatorHelper().getAmbientState().getTouchView().get();
        if (capsuleItemView != 0) {
            if (!Intrinsics.areEqual(this.touchProperty.getProperty().get(capsuleItemView), fValueOf)) {
                if (z) {
                    OplusPhysicsPropertyAnimatorExtKt.animatePropertyIfChanged(capsuleItemView.getViewState(), capsuleItemView, 0.0f, this.touchProperty, true, ExtStateAnimParams.INSTANCE.toInterpolator(commonParams.getTOUCH_SCALE_INTERRTV_EXIT()), (64 & 32) != 0 ? 400L : (long) commonParams.getTOUCH_SCALE_DURATION().get().floatValue(), (64 & 64) != 0 ? 0.1f : 1.0f);
                    return;
                }
                CapsuleViewState viewState = capsuleItemView.getViewState();
                int animatorTag = this.touchProperty.getAnimatorTag();
                viewState.getClass();
                ViewState.abortAnimation(capsuleItemView, animatorTag);
                this.touchProperty.getProperty().set(capsuleItemView, fValueOf);
                return;
            }
            if (LogUtil.getNormalDebug() && LogUtil.isTagValid(LogUtil.TAG_NOTIFICATION)) {
                Log.d("SystemUi--Notification", "NotificationCapsuleContainer-->requestCancelTouchFeedback: same value, return");
            }
            if (ViewState.isAnimating(capsuleItemView, this.touchProperty)) {
                CapsuleViewState viewState2 = capsuleItemView.getViewState();
                int animatorTag2 = this.touchProperty.getAnimatorTag();
                viewState2.getClass();
                ViewState.abortAnimation(capsuleItemView, animatorTag2);
                this.touchProperty.getProperty().set(capsuleItemView, fValueOf);
            }
        }
    }

    public final void requestChildrenUpdate(String str) {
        getAnimatorHelper().requestChildrenUpdate(str);
    }

    /* JADX WARN: Multi-variable type inference failed */
    public final void requestTouchFeedback(CapsuleItemView capsuleItemView) {
        ExtStateAnimParams.CommonParams commonParams = ExtStateAnimParams.CommonParams.INSTANCE;
        if (commonParams.getTOUCH_TYPE().get().intValue() == 0) {
            return;
        }
        if (LogUtil.getNormalDebug() && LogUtil.isTagValid(LogUtil.TAG_NOTIFICATION)) {
            Log.d("SystemUi--Notification", "NotificationCapsuleContainer-->requestTouchFeedback: ");
        }
        OplusPhysicsPropertyAnimatorExtKt.animatePropertyIfChanged(capsuleItemView.getViewState(), capsuleItemView, 100.0f, this.touchProperty, true, ExtStateAnimParams.INSTANCE.toInterpolator(commonParams.getTOUCH_SCALE_INTERRTV_ENTER()), (64 & 32) != 0 ? 400L : (long) commonParams.getTOUCH_SCALE_DURATION().get().floatValue(), (64 & 64) != 0 ? 0.1f : 1.0f);
    }

    public final void resetDate(String str) {
        if (LogUtil.getNormalDebug() && LogUtil.isTagValid(LogUtil.TAG_NOTIFICATION)) {
            MediaTimeoutListener$PlaybackStateListener$.ExternalSyntheticOutline0.m("resetDate: ", str, "NotificationCapsuleContainer-->", "SystemUi--Notification");
        }
        setStackY(0.0f);
        setOwnScrollX(0);
        getAnimatorHelper().resetDate();
    }

    public final void resetScrollByY() {
        setStackY(0.0f);
        getAnimatorHelper().requestChildrenUpdate("resetScrollByY");
    }

    public final void setBlurRatio(float f) {
        this.blurRatio = f;
        KeyguardUtils.Companion.getClass();
        boolean z = f <= 1.0E-6f;
        if (KeyguardUtils.Companion.isFloatEqual(f, this.blurRatio)) {
            if (z) {
                setRenderEffect(null);
            } else {
                setRenderEffect(RenderEffect.createBlurEffect(f, f, Shader.TileMode.CLAMP));
            }
        }
    }

    public final void setContainerControllerImpl(NotificationCapsuleContainerControllerImpl notificationCapsuleContainerControllerImpl) {
        this.containerControllerImpl = notificationCapsuleContainerControllerImpl;
    }

    public final void setDragScaleTranslationX(float f) {
        this.dragScaleTranslationX = f;
        super.setTranslationX(this.mTranslationX + f);
    }

    public final void setLightOSController(CapsuleLightOSController capsuleLightOSController) {
        this.lightOSController = capsuleLightOSController;
    }

    public final void setScrimAlpha(float f) {
        this.aodProgress = f;
        Iterator it = SequencesKt___SequencesKt.mapNotNull(ViewGroupKt.getChildren(this), new NotificationCapsuleContainer$$ExternalSyntheticLambda0(2)).iterator();
        while (it.hasNext()) {
            ((CapsuleItemView) it.next()).setScrimAlpha(f);
        }
        this.capsuleEarController.setScrimAlpha(f);
    }

    public final void setScroller(NotificationCapsuleContainerTouchHandler notificationCapsuleContainerTouchHandler) {
        this.scroller = notificationCapsuleContainerTouchHandler;
    }

    public final void setShowing(boolean z) {
        if ((!getAnimatorHelper().getAmbientState().isStackState) != z) {
            if (LogUtil.getNormalDebug() && LogUtil.isTagValid(LogUtil.TAG_NOTIFICATION)) {
                OplusLockIconView$.ExternalSyntheticOutline0.m("updateShowing: ", "NotificationCapsuleContainer-->", "SystemUi--Notification", z);
            }
            getAnimatorHelper().getAmbientState().setStackState(!z);
            if (getAnimatorHelper().getAmbientState().isStackState) {
                resetScrollByY();
            }
            getAnimatorHelper().requestChildrenUpdate("isShowing:" + z);
        }
    }

    public final void setStackYChangeListener(Function1 function1) {
        this.stackYChangeListener = function1;
    }

    @Override // android.view.View
    public void setTranslationX(float f) {
        this.mTranslationX = f;
        super.setTranslationX(f + this.dragScaleTranslationX);
    }

    @Override // android.view.View
    public void setVisibility(int i) {
        super.setVisibility(i);
        if (i == 0) {
            getAnimatorHelper().setAnimationsEnabled(true);
            getAnimatorHelper().requestChildrenUpdate("setVisibility:VISIBLE");
        } else {
            getAnimatorHelper().setAnimationsEnabled(false);
            setOwnScrollX(0);
        }
    }

    public final void unBindToStackView(CapsuleItemView capsuleItemView, String str) {
        for (CapsuleItemView capsuleItemView2 : CapsuleViewExpandKtKt.getCapsuleChildren(this)) {
            if (capsuleItemView == null || !Intrinsics.areEqual(capsuleItemView2, capsuleItemView)) {
                if (capsuleItemView2.isBindToStackView()) {
                    capsuleItemView2.unBindStackView(CursorUtil$.ExternalSyntheticOutline0.m("clear bind stack for ", capsuleItemView2.getKey(), ", reason=", str), true);
                }
            }
        }
    }

    public final void updateCapsuleContentColor(boolean z) {
        if (LogUtil.getNormalDebug() && LogUtil.isTagValid(LogUtil.TAG_NOTIFICATION)) {
            OplusLockIconView$.ExternalSyntheticOutline0.m("updateCapsuleContentColor: ", "NotificationCapsuleContainer-->", "SystemUi--Notification", z);
        }
        getAnimatorHelper().getAmbientState().setLightWallpaper(z);
        requestChildrenUpdate("wallpaper color changed");
    }

    public final void updateImmersiveCard(CapsuleItemView capsuleItemView, String str) {
        getAnimatorHelper().updateImmersiveCard(capsuleItemView, str);
    }

    public NotificationCapsuleContainer(Context context, AttributeSet attributeSet) {
        this(context, attributeSet, 0, 4, null);
    }

    public /* synthetic */ NotificationCapsuleContainer(Context context, AttributeSet attributeSet, int i, int i2, DefaultConstructorMarker defaultConstructorMarker) {
        this(context, (i2 & 2) != 0 ? null : attributeSet, (i2 & 4) != 0 ? 0 : i);
    }

    public NotificationCapsuleContainer(Context context) {
        this(context, null, 0, 6, null);
    }
}
