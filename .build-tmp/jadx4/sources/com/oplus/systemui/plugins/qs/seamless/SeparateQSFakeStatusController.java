package com.oplus.systemui.plugins.qs.seamless;

import android.animation.Animator;
import android.content.Context;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.view.ViewGroup;
import android.view.ViewParent;
import android.widget.FrameLayout;
import androidx.appcompat.widget.ListPopupWindow$;
import androidx.collection.FloatList$;
import androidx.compose.animation.core.MonoSpline$;
import androidx.compose.ui.spatial.RectList$;
import com.android.systemui.plugins.qs.OplusSeparateQSPlugin;
import com.android.systemui.plugins.qs.QSElement;
import com.android.systemui.plugins.qs.QSFakeStatusElement;
import com.android.systemui.plugins.qs.QSSpecialModeUpdateMonitor;
import com.android.systemui.plugins.qs.SpanSizeInPlugin;
import com.android.systemui.shade.NotificationPanelViewController$;
import com.android.systemui.statusbar.pipeline.mobile.data.MobileInputLogger$;
import com.oplus.systemui.plugins.qs.CellAreaConfig$$ExternalSyntheticOutline0;
import com.oplus.systemui.plugins.qs.CellAreaProvider;
import com.oplus.systemui.plugins.qs.IComponentHostAbility;
import com.oplus.systemui.plugins.qs.IQsComponent;
import com.oplus.systemui.plugins.qs.OplusQSSpecialModeProvider;
import com.oplus.systemui.plugins.qs.animator.AnimatorUtils;
import com.oplus.systemui.plugins.qs.animator.IQSExpandOrCollapseAnimation;
import com.oplus.systemui.plugins.qs.animator.QSPanelAnimatorManager;
import com.oplus.systemui.plugins.qs.animator.QSPanelTranslationY;
import com.oplus.systemui.plugins.qs.privacy.ui.view.OplusQSPrivacyContainerView;
import com.oplus.systemui.plugins.qs.quickentrance.OplusQSQuickEntranceComponent;
import com.oplus.systemui.preventmode.OplusPreventModeData;
import com.oplus.systemui.qs.base.springtransition.SpringTransition;
import com.oplus.systemui.qs.base.util.DeviceUtil;
import com.oplus.systemui.qs.base.util.ViewController;
import com.oplus.zoom.common.ZoomParameterHelper;
import com.oplusos.systemui.common.feature.FeatureOption;
import com.oplusos.systemui.common.model.Loc;
import com.oplusos.systemui.common.model.SpanSize;
import com.oplusos.systemui.common.rendercache.RenderBooster;
import com.oplusos.systemui.common.util.LogUtil;
import java.io.PrintWriter;
import java.lang.invoke.VarHandle;
import java.util.List;
import java.util.Set;
import kotlin.Unit;
import kotlin.jvm.functions.Function0;
import kotlin.jvm.functions.Function1;
import kotlin.jvm.internal.CallableReference;
import kotlin.jvm.internal.FunctionReferenceImpl;

/* JADX INFO: compiled from: go/retraceme fd74bf9514dd0739b92d67a6d48a51eb10aaf8df760d83496b8dcfcf83e5f698 */
/* JADX INFO: loaded from: /home/starlight/Projects/Starlight Ports/.build-tmp/classes4.dex */
public final class SeparateQSFakeStatusController extends ViewController implements IQsComponent {
    public static final Companion Companion = new Companion();
    private static final String TAG = "SeparateQSFakeStatusController";
    private ViewGroup carrierContainer;
    private final CellAreaProvider cellAreaProvider;
    public float clockTranslationX;
    public FrameLayout fakeCarrierContainer;
    public FrameLayout fakeClockContainer;
    public FrameLayout fakeKeyguardCarrierContainer;
    public FrameLayout fakeKeyguardStatusIconContainer;
    public FrameLayout fakeNotificationIconContainer;
    public FrameLayout fakePrivacyIconContainer;
    public FrameLayout fakeStatusIconContainer;
    public float fakeStatusIconHeight;
    public boolean isSmallScreen;
    public float keyguardCarrierTranslationX;
    public boolean needUpdatePosition;
    public float qSPanelViewTranslationY;
    private final SeparateQSFakeStatusController$qsElementShowingChangeListener$1 qsElementShowingChangeListener;
    private final QSFakeStatusElement qsFakeCarrier;
    private final QSFakeStatusElement qsFakeClock;
    private final QSFakeStatusElement qsFakeKeyguardCarrier;
    private final QSFakeStatusElement qsFakeNotificationIcon;
    private final QSFakeStatusElement qsFakePrivacyIcon;
    private final QSFakeStatusElement qsFakeStatusIcon;
    private final QSFakeStatusElement qsKeyguardFakeStatusIcon;
    private final QSPanelAnimatorManager qsPanelAnimatorManager;
    private final SeparateQSFakeStatusController$qsPanelExpandFractionListener$1 qsPanelExpandFractionListener;
    private final QSPanelTranslationY.PanelTranslationYChangeListener qsPanelTranslationYListener;
    private final OplusQSQuickEntranceComponent quickEntranceContainerViewController;
    public int quickEntranceHeight;
    private final OplusQSPrivacyContainerView separateQSPrivacyView;
    private final FrameLayout separateQsFakeLayout;
    private final ViewGroup statusBarContainer;
    private final QSElement statusBarHeader;
    public float statusBarTopMargin;
    public float statusIconTranslationX;
    private final View.OnLayoutChangeListener statusIconsLayoutListener;
    private ViewGroup statusIconsView;
    private final OplusSeparateQSPlugin.IStub stub;
    public float tempRawFraction;
    private Animator transitionXAnimator;
    private final Runnable updateElementTranslationRunnable;

    /* JADX INFO: compiled from: go/retraceme fd74bf9514dd0739b92d67a6d48a51eb10aaf8df760d83496b8dcfcf83e5f698 */
    public final class Companion {
    }

    /* JADX INFO: renamed from: com.oplus.systemui.plugins.qs.seamless.SeparateQSFakeStatusController$onViewAttached$2, reason: invalid class name */
    /* JADX INFO: compiled from: go/retraceme fd74bf9514dd0739b92d67a6d48a51eb10aaf8df760d83496b8dcfcf83e5f698 */
    final /* synthetic */ class AnonymousClass2 extends FunctionReferenceImpl implements Function1 {
        public final Object invoke(Object obj) {
            ((SeparateQSFakeStatusController) ((CallableReference) this).receiver).setStatusBarAlpha(((Number) obj).floatValue());
            return Unit.INSTANCE;
        }
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
    public static Unit $r8$lambda$d4RsmOdVPSBUyc2Ct8XPh8GMlug(SeparateQSFakeStatusController separateQSFakeStatusController, float f) {
        FrameLayout frameLayout = separateQSFakeStatusController.fakeKeyguardStatusIconContainer;
        if (frameLayout == null) {
            frameLayout = null;
        }
        setFakeTransitionAlpha(f, frameLayout);
        float f2 = 1.0f - f;
        separateQSFakeStatusController.separateQSPrivacyView.setTransitionAlpha(f2);
        ViewGroup viewGroup = separateQSFakeStatusController.statusIconsView;
        if (viewGroup != null) {
            viewGroup.setTransitionAlpha(f2);
        }
        ViewGroup viewGroup2 = separateQSFakeStatusController.carrierContainer;
        if (viewGroup2 != null) {
            viewGroup2.setTransitionAlpha(f2);
        }
        if (separateQSFakeStatusController.isSmallScreen) {
            FrameLayout frameLayout2 = separateQSFakeStatusController.fakeCarrierContainer;
            if (frameLayout2 == null) {
                frameLayout2 = null;
            }
            setFakeTransitionAlpha(f, frameLayout2);
            FrameLayout frameLayout3 = separateQSFakeStatusController.fakeKeyguardCarrierContainer;
            setFakeTransitionAlpha(f, frameLayout3 != null ? frameLayout3 : null);
        }
        return Unit.INSTANCE;
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
    public static Unit $r8$lambda$fL516UCUDKaXF3UXoHCvlNK89Ak(SeparateQSFakeStatusController separateQSFakeStatusController, ViewGroup viewGroup, float f) {
        separateQSFakeStatusController.separateQSPrivacyView.setTransitionAlpha(f);
        ViewGroup viewGroup2 = separateQSFakeStatusController.statusIconsView;
        if (viewGroup2 != null) {
            viewGroup2.setTransitionAlpha(f);
        }
        viewGroup.setTransitionAlpha(f);
        return Unit.INSTANCE;
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
    public static Unit $r8$lambda$sWakutcQwggJXmnyGQk43cw4Wxk(SeparateQSFakeStatusController separateQSFakeStatusController, float f) {
        FrameLayout frameLayout = separateQSFakeStatusController.fakePrivacyIconContainer;
        if (frameLayout == null) {
            frameLayout = null;
        }
        float f2 = 1.0f - f;
        setFakeTransitionAlpha(f2, frameLayout);
        FrameLayout frameLayout2 = separateQSFakeStatusController.fakeStatusIconContainer;
        if (frameLayout2 == null) {
            frameLayout2 = null;
        }
        setFakeTransitionAlpha(f2, frameLayout2);
        ViewGroup viewGroup = separateQSFakeStatusController.carrierContainer;
        if (viewGroup != null) {
            viewGroup.setTransitionAlpha(f);
        }
        separateQSFakeStatusController.separateQSPrivacyView.setTransitionAlpha(f);
        ViewGroup viewGroup2 = separateQSFakeStatusController.statusIconsView;
        if (viewGroup2 != null) {
            viewGroup2.setTransitionAlpha(f);
        }
        if (separateQSFakeStatusController.isSmallScreen) {
            FrameLayout frameLayout3 = separateQSFakeStatusController.fakeClockContainer;
            if (frameLayout3 == null) {
                frameLayout3 = null;
            }
            setFakeTransitionAlpha(f2, frameLayout3);
            FrameLayout frameLayout4 = separateQSFakeStatusController.fakeNotificationIconContainer;
            setFakeTransitionAlpha(f2, frameLayout4 != null ? frameLayout4 : null);
        }
        return Unit.INSTANCE;
    }

    public SeparateQSFakeStatusController(QSPanelAnimatorManager qSPanelAnimatorManager, FrameLayout frameLayout, OplusSeparateQSPlugin.IStub iStub, OplusQSQuickEntranceComponent oplusQSQuickEntranceComponent, OplusQSPrivacyContainerView oplusQSPrivacyContainerView, ViewGroup viewGroup, CellAreaProvider cellAreaProvider, QSElement qSElement, QSFakeStatusElement qSFakeStatusElement, QSFakeStatusElement qSFakeStatusElement2, QSFakeStatusElement qSFakeStatusElement3, QSFakeStatusElement qSFakeStatusElement4, QSFakeStatusElement qSFakeStatusElement5, QSFakeStatusElement qSFakeStatusElement6, QSFakeStatusElement qSFakeStatusElement7) {
        super(frameLayout);
        this.qsPanelAnimatorManager = qSPanelAnimatorManager;
        this.separateQsFakeLayout = frameLayout;
        this.stub = iStub;
        this.quickEntranceContainerViewController = oplusQSQuickEntranceComponent;
        this.separateQSPrivacyView = oplusQSPrivacyContainerView;
        this.statusBarContainer = viewGroup;
        this.cellAreaProvider = cellAreaProvider;
        this.statusBarHeader = qSElement;
        this.qsFakeStatusIcon = qSFakeStatusElement;
        this.qsFakePrivacyIcon = qSFakeStatusElement2;
        this.qsKeyguardFakeStatusIcon = qSFakeStatusElement3;
        this.qsFakeNotificationIcon = qSFakeStatusElement4;
        this.qsFakeClock = qSFakeStatusElement5;
        this.qsFakeCarrier = qSFakeStatusElement6;
        this.qsFakeKeyguardCarrier = qSFakeStatusElement7;
        this.needUpdatePosition = true;
        SeparateQSFakeStatusController$qsElementShowingChangeListener$1 separateQSFakeStatusController$qsElementShowingChangeListener$1 = new SeparateQSFakeStatusController$qsElementShowingChangeListener$1();
        separateQSFakeStatusController$qsElementShowingChangeListener$1.this$0 = this;
        VarHandle.storeStoreFence();
        this.qsElementShowingChangeListener = separateQSFakeStatusController$qsElementShowingChangeListener$1;
        SeparateQSFakeStatusController$qsPanelExpandFractionListener$1 separateQSFakeStatusController$qsPanelExpandFractionListener$1 = new SeparateQSFakeStatusController$qsPanelExpandFractionListener$1();
        separateQSFakeStatusController$qsPanelExpandFractionListener$1.this$0 = this;
        VarHandle.storeStoreFence();
        this.qsPanelExpandFractionListener = separateQSFakeStatusController$qsPanelExpandFractionListener$1;
        SeparateQSFakeStatusController$statusIconsLayoutListener$1 separateQSFakeStatusController$statusIconsLayoutListener$1 = new SeparateQSFakeStatusController$statusIconsLayoutListener$1();
        separateQSFakeStatusController$statusIconsLayoutListener$1.this$0 = this;
        VarHandle.storeStoreFence();
        this.statusIconsLayoutListener = separateQSFakeStatusController$statusIconsLayoutListener$1;
        SeparateQSFakeStatusController$updateElementTranslationRunnable$1 separateQSFakeStatusController$updateElementTranslationRunnable$1 = new SeparateQSFakeStatusController$updateElementTranslationRunnable$1(0);
        separateQSFakeStatusController$updateElementTranslationRunnable$1.this$0 = this;
        VarHandle.storeStoreFence();
        this.updateElementTranslationRunnable = separateQSFakeStatusController$updateElementTranslationRunnable$1;
        SeparateQSFakeStatusController$qsPanelTranslationYListener$1 separateQSFakeStatusController$qsPanelTranslationYListener$1 = new SeparateQSFakeStatusController$qsPanelTranslationYListener$1();
        separateQSFakeStatusController$qsPanelTranslationYListener$1.this$0 = this;
        VarHandle.storeStoreFence();
        this.qsPanelTranslationYListener = separateQSFakeStatusController$qsPanelTranslationYListener$1;
    }

    public static final void access$showOrHideFixedView(SeparateQSFakeStatusController separateQSFakeStatusController, boolean z, boolean z2) {
        int i = 3;
        int i2 = 1;
        if (!separateQSFakeStatusController.isKeyguardVisible()) {
            QSPanelAnimatorManager qSPanelAnimatorManager = separateQSFakeStatusController.qsPanelAnimatorManager;
            FrameLayout frameLayout = separateQSFakeStatusController.fakeCarrierContainer;
            FrameLayout frameLayout2 = frameLayout == null ? null : frameLayout;
            SeparateQSFakeStatusController$$ExternalSyntheticLambda1 separateQSFakeStatusController$$ExternalSyntheticLambda1 = new SeparateQSFakeStatusController$$ExternalSyntheticLambda1(i);
            separateQSFakeStatusController$$ExternalSyntheticLambda1.f$0 = separateQSFakeStatusController;
            VarHandle.storeStoreFence();
            SeparateQSFakeStatusController$$ExternalSyntheticLambda0 separateQSFakeStatusController$$ExternalSyntheticLambda0 = new SeparateQSFakeStatusController$$ExternalSyntheticLambda0(i2);
            separateQSFakeStatusController$$ExternalSyntheticLambda0.f$0 = separateQSFakeStatusController;
            VarHandle.storeStoreFence();
            QSPanelAnimatorManager.animateToWithProxyLogic$default(qSPanelAnimatorManager, frameLayout2, !z, z2, separateQSFakeStatusController$$ExternalSyntheticLambda1, separateQSFakeStatusController$$ExternalSyntheticLambda0, 0.0f, 0.0f, null, ZoomParameterHelper.SMALL_SCREEN_HEIGHT_THRESHOLD_DP);
            return;
        }
        if (separateQSFakeStatusController.isSmallScreen) {
            return;
        }
        QSPanelAnimatorManager qSPanelAnimatorManager2 = separateQSFakeStatusController.qsPanelAnimatorManager;
        FrameLayout frameLayout3 = separateQSFakeStatusController.fakeCarrierContainer;
        FrameLayout frameLayout4 = frameLayout3 == null ? null : frameLayout3;
        SeparateQSFakeStatusController$$ExternalSyntheticLambda1 separateQSFakeStatusController$$ExternalSyntheticLambda2 = new SeparateQSFakeStatusController$$ExternalSyntheticLambda1(2);
        separateQSFakeStatusController$$ExternalSyntheticLambda2.f$0 = separateQSFakeStatusController;
        VarHandle.storeStoreFence();
        SeparateQSFakeStatusController$$ExternalSyntheticLambda0 separateQSFakeStatusController$$ExternalSyntheticLambda3 = new SeparateQSFakeStatusController$$ExternalSyntheticLambda0(i);
        separateQSFakeStatusController$$ExternalSyntheticLambda3.f$0 = separateQSFakeStatusController;
        VarHandle.storeStoreFence();
        QSPanelAnimatorManager.animateToWithProxyLogic$default(qSPanelAnimatorManager2, frameLayout4, !z, z2, separateQSFakeStatusController$$ExternalSyntheticLambda2, separateQSFakeStatusController$$ExternalSyntheticLambda3, 0.0f, 0.0f, null, ZoomParameterHelper.SMALL_SCREEN_HEIGHT_THRESHOLD_DP);
    }

    public static final void access$showOrHideView(SeparateQSFakeStatusController separateQSFakeStatusController, boolean z, boolean z2) {
        AnimatorUtils.INSTANCE.setExtraFakeTranslationY(z ? 0.0f : separateQSFakeStatusController.separateQsFakeLayout.getTranslationY());
        int i = 1;
        int i2 = 0;
        if (separateQSFakeStatusController.isKeyguardVisible()) {
            separateQSFakeStatusController.separateQsFakeLayout.setVisibility(0);
            QSPanelAnimatorManager qSPanelAnimatorManager = separateQSFakeStatusController.qsPanelAnimatorManager;
            FrameLayout frameLayout = separateQSFakeStatusController.fakeKeyguardStatusIconContainer;
            if (frameLayout == null) {
                frameLayout = null;
            }
            SeparateQSFakeStatusController$$ExternalSyntheticLambda1 separateQSFakeStatusController$$ExternalSyntheticLambda1 = new SeparateQSFakeStatusController$$ExternalSyntheticLambda1(i2);
            separateQSFakeStatusController$$ExternalSyntheticLambda1.f$0 = separateQSFakeStatusController;
            VarHandle.storeStoreFence();
            SeparateQSFakeStatusController$$ExternalSyntheticLambda0 separateQSFakeStatusController$$ExternalSyntheticLambda0 = new SeparateQSFakeStatusController$$ExternalSyntheticLambda0(2);
            separateQSFakeStatusController$$ExternalSyntheticLambda0.f$0 = separateQSFakeStatusController;
            VarHandle.storeStoreFence();
            QSPanelAnimatorManager.animateToWithProxyLogic$default(qSPanelAnimatorManager, frameLayout, !z, z2, separateQSFakeStatusController$$ExternalSyntheticLambda1, separateQSFakeStatusController$$ExternalSyntheticLambda0, 0.0f, 0.0f, null, ZoomParameterHelper.SMALL_SCREEN_HEIGHT_THRESHOLD_DP);
            return;
        }
        if (separateQSFakeStatusController.isKeyguardVisible() || separateQSFakeStatusController.stub.getNtIsFullCollapsed()) {
            separateQSFakeStatusController.separateQsFakeLayout.setVisibility(0);
            ViewGroup viewGroup = separateQSFakeStatusController.carrierContainer;
            if (viewGroup != null) {
                QSPanelAnimatorManager qSPanelAnimatorManager2 = separateQSFakeStatusController.qsPanelAnimatorManager;
                SeparateQSFakeStatusController$$ExternalSyntheticLambda1 separateQSFakeStatusController$$ExternalSyntheticLambda2 = new SeparateQSFakeStatusController$$ExternalSyntheticLambda1(i);
                separateQSFakeStatusController$$ExternalSyntheticLambda2.f$0 = separateQSFakeStatusController;
                VarHandle.storeStoreFence();
                SeparateQSFakeStatusController$$ExternalSyntheticLambda4 separateQSFakeStatusController$$ExternalSyntheticLambda4 = new SeparateQSFakeStatusController$$ExternalSyntheticLambda4(i);
                separateQSFakeStatusController$$ExternalSyntheticLambda4.f$0 = viewGroup;
                VarHandle.storeStoreFence();
                QSPanelAnimatorManager.animateToWithProxyLogic$default(qSPanelAnimatorManager2, viewGroup, z, z2, separateQSFakeStatusController$$ExternalSyntheticLambda2, separateQSFakeStatusController$$ExternalSyntheticLambda4, 0.0f, 0.0f, null, ZoomParameterHelper.SMALL_SCREEN_HEIGHT_THRESHOLD_DP);
                return;
            }
            return;
        }
        separateQSFakeStatusController.separateQsFakeLayout.setVisibility(4);
        ViewGroup viewGroup2 = separateQSFakeStatusController.carrierContainer;
        if (viewGroup2 != null) {
            QSPanelAnimatorManager qSPanelAnimatorManager3 = separateQSFakeStatusController.qsPanelAnimatorManager;
            SeparateQSFakeStatusController$$ExternalSyntheticLambda3 separateQSFakeStatusController$$ExternalSyntheticLambda3 = new SeparateQSFakeStatusController$$ExternalSyntheticLambda3();
            separateQSFakeStatusController$$ExternalSyntheticLambda3.f$0 = separateQSFakeStatusController;
            separateQSFakeStatusController$$ExternalSyntheticLambda3.f$1 = viewGroup2;
            VarHandle.storeStoreFence();
            SeparateQSFakeStatusController$$ExternalSyntheticLambda4 separateQSFakeStatusController$$ExternalSyntheticLambda5 = new SeparateQSFakeStatusController$$ExternalSyntheticLambda4(i2);
            separateQSFakeStatusController$$ExternalSyntheticLambda5.f$0 = viewGroup2;
            VarHandle.storeStoreFence();
            QSPanelAnimatorManager.animateToWithProxyLogic$default(qSPanelAnimatorManager3, viewGroup2, z, z2, separateQSFakeStatusController$$ExternalSyntheticLambda3, separateQSFakeStatusController$$ExternalSyntheticLambda5, 0.0f, 0.0f, null, ZoomParameterHelper.SMALL_SCREEN_HEIGHT_THRESHOLD_DP);
        }
    }

    private final float getFakeClockTranslationX() {
        int left;
        int left2;
        float realStatusElementLocX;
        float realStatusElementLocX2;
        ViewGroup viewGroup = this.carrierContainer;
        if (viewGroup == null) {
            return 0.0f;
        }
        boolean zIsRtl = DeviceUtil.isRtl();
        QSElement qSElement = this.statusBarHeader;
        if (zIsRtl) {
            left = viewGroup.getWidth() + viewGroup.getLeft() + QSElement.createView$default(qSElement, (SpanSizeInPlugin) null, false, false, 7, (Object) null).getLeft();
            left2 = this.statusBarContainer.getLeft();
        } else {
            left = viewGroup.getLeft() + QSElement.createView$default(qSElement, (SpanSizeInPlugin) null, false, false, 7, (Object) null).getLeft();
            left2 = this.statusBarContainer.getLeft();
        }
        int i = left2 + left;
        if (isKeyguardVisible()) {
            float dimension = getContext().getResources().getDimension(2131173049);
            boolean zIsRtl2 = DeviceUtil.isRtl();
            QSFakeStatusElement qSFakeStatusElement = this.qsFakeKeyguardCarrier;
            if (zIsRtl2) {
                realStatusElementLocX2 = (((Number) this.qsFakeKeyguardCarrier.getRealStatusElementParams().getFirst()).floatValue() + qSFakeStatusElement.getRealStatusElementLocX()) - dimension;
            } else {
                realStatusElementLocX2 = qSFakeStatusElement.getRealStatusElementLocX() + dimension;
            }
        } else {
            float dimension2 = getContext().getResources().getDimension(2131173051);
            boolean zIsRtl3 = DeviceUtil.isRtl();
            QSFakeStatusElement qSFakeStatusElement2 = this.qsFakeClock;
            if (zIsRtl3) {
                realStatusElementLocX = (((Number) this.qsFakeClock.getRealStatusElementParams().getFirst()).floatValue() + qSFakeStatusElement2.getRealStatusElementLocX()) - dimension2;
            } else {
                realStatusElementLocX = qSFakeStatusElement2.getRealStatusElementLocX() + dimension2;
            }
            realStatusElementLocX2 = realStatusElementLocX + 0.0f;
        }
        if (LogUtil.getNormalDebug() && LogUtil.isTagValid(LogUtil.TAG_QS)) {
            Log.d("SystemUi--QuickSettings", "SeparateQSFakeStatusController-->".concat("clockLocX: " + i + ",statusBarClockXLocation:" + realStatusElementLocX2));
        }
        return i - realStatusElementLocX2;
    }

    private final float getFakeKeyguardCarrierTx() {
        float realStatusElementPaddingStart;
        int left;
        boolean zIsRtl = DeviceUtil.isRtl();
        QSFakeStatusElement qSFakeStatusElement = this.qsFakeKeyguardCarrier;
        if (zIsRtl) {
            realStatusElementPaddingStart = (((Number) this.qsFakeKeyguardCarrier.getRealStatusElementParams().getFirst()).floatValue() + qSFakeStatusElement.getRealStatusElementLocX()) - this.qsFakeKeyguardCarrier.getRealStatusElementPaddingStart();
        } else {
            realStatusElementPaddingStart = this.qsFakeKeyguardCarrier.getRealStatusElementPaddingStart() + qSFakeStatusElement.getRealStatusElementLocX();
        }
        boolean zIsRtl2 = DeviceUtil.isRtl();
        ViewGroup viewGroup = this.statusBarContainer;
        int left2 = 0;
        if (zIsRtl2) {
            int left3 = QSElement.createView$default(this.statusBarHeader, (SpanSizeInPlugin) null, false, false, 7, (Object) null).getLeft() + viewGroup.getLeft();
            ViewGroup viewGroup2 = this.carrierContainer;
            left = left3 + (viewGroup2 != null ? viewGroup2.getLeft() : 0);
            ViewGroup viewGroup3 = this.carrierContainer;
            if (viewGroup3 != null) {
                left2 = viewGroup3.getWidth();
            }
        } else {
            left = QSElement.createView$default(this.statusBarHeader, (SpanSizeInPlugin) null, false, false, 7, (Object) null).getLeft() + viewGroup.getLeft();
            ViewGroup viewGroup4 = this.carrierContainer;
            if (viewGroup4 != null) {
                left2 = viewGroup4.getLeft();
            }
        }
        int i = left + left2;
        if (LogUtil.getNormalDebug() && LogUtil.isTagValid(LogUtil.TAG_QS)) {
            Log.d("SystemUi--QuickSettings", "SeparateQSFakeStatusController-->".concat("keyguardCarrierLeftLoc: " + realStatusElementPaddingStart + ",qsCarrierLeftLoc:" + i));
        }
        return i - realStatusElementPaddingStart;
    }

    private final float getFakeStatusIconTranslationX() {
        float fFloatValue;
        int left;
        QSFakeStatusElement fakeStatusIconView = getFakeStatusIconView();
        if (DeviceUtil.isRtl()) {
            fFloatValue = fakeStatusIconView.getRealStatusElementLocX();
        } else {
            fFloatValue = ((Number) fakeStatusIconView.getRealStatusElementParams().getFirst()).floatValue() + fakeStatusIconView.getRealStatusElementLocX();
        }
        boolean zIsRtl = DeviceUtil.isRtl();
        ViewGroup viewGroup = this.statusBarContainer;
        int width = 0;
        if (zIsRtl) {
            left = QSElement.createView$default(this.statusBarHeader, (SpanSizeInPlugin) null, false, false, 7, (Object) null).getLeft() + viewGroup.getLeft();
            ViewGroup viewGroup2 = this.statusIconsView;
            if (viewGroup2 != null) {
                width = viewGroup2.getLeft();
            }
        } else {
            int left2 = QSElement.createView$default(this.statusBarHeader, (SpanSizeInPlugin) null, false, false, 7, (Object) null).getLeft() + viewGroup.getLeft();
            ViewGroup viewGroup3 = this.statusIconsView;
            left = left2 + (viewGroup3 != null ? viewGroup3.getLeft() : 0);
            ViewGroup viewGroup4 = this.statusIconsView;
            if (viewGroup4 != null) {
                width = viewGroup4.getWidth();
            }
        }
        int i = left + width;
        if (LogUtil.getNormalDebug() && LogUtil.isTagValid(LogUtil.TAG_QS)) {
            Log.d("SystemUi--QuickSettings", "SeparateQSFakeStatusController-->".concat("statusIconRightLoc: " + fFloatValue + ",qsStatusIconRightLoc:" + i));
        }
        return i - fFloatValue;
    }

    /* JADX INFO: Access modifiers changed from: private */
    public final QSFakeStatusElement getFakeStatusIconView() {
        return isKeyguardVisible() ? this.qsKeyguardFakeStatusIcon : this.qsFakeStatusIcon;
    }

    /* JADX INFO: Access modifiers changed from: private */
    public final float getPanelDestTranslationY() {
        return ((this.statusBarTopMargin + (this.cellAreaProvider.getCellAreaConfig().getStatusBarRealHeight() / 2)) - ((this.fakeStatusIconHeight / 2.0f) + getFakeStatusIconView().getRealStatusElementLocY())) + this.quickEntranceHeight;
    }

    public static void setFakeTransitionAlpha(float f, View view) {
        view.setTransitionAlpha(f);
        view.setVisibility(f == 0.0f ? 4 : 0);
    }

    @Override // com.oplus.systemui.plugins.qs.IQsComponent, com.oplus.systemui.plugins.qs.editmodeswitch.IEditModeSwitcher
    public void bindTargetProperties(Set<? extends SpringTransition> set) {
        super.bindTargetProperties(set);
    }

    @Override // com.oplus.systemui.plugins.qs.IQsComponent
    public final boolean checkEditModeAbnormal() {
        return false;
    }

    @Override // com.oplus.systemui.plugins.qs.IQsComponent, com.oplus.systemui.plugins.qs.editmodeswitch.IEditModeSwitcher
    public SpringTransition[] createSpringTransition(String str) {
        return super.createSpringTransition(str);
    }

    @Override // com.oplus.systemui.plugins.qs.IQsComponent
    public final void destroyComponent() {
    }

    @Override // com.oplus.systemui.plugins.qs.IQsComponent
    public void dump(PrintWriter printWriter, String[] strArr) {
        super.dump(printWriter, strArr);
    }

    @Override // com.oplus.systemui.plugins.qs.IQsComponent, com.oplus.systemui.plugins.qs.animator.IQSExpandOrCollapseAnimation
    public View getAnimatorView(int i) {
        return super.getAnimatorView(i);
    }

    public final View getFakeLayout() {
        return this.separateQsFakeLayout;
    }

    public final ViewGroup getStatusBarContainer() {
        return this.statusBarContainer;
    }

    @Override // com.oplus.systemui.plugins.qs.IQsComponent, com.oplus.systemui.plugins.qs.animator.IQSExpandOrCollapseAnimation
    public int getSubSize() {
        return super.getSubSize();
    }

    public final float getTempRawFraction() {
        return this.tempRawFraction;
    }

    @Override // com.oplus.systemui.plugins.qs.IQsComponent
    public void injectHostAbility(IComponentHostAbility iComponentHostAbility) {
        super.injectHostAbility(iComponentHostAbility);
    }

    public final boolean isKeyguardVisible() {
        return this.stub.isKeyguardShowing() && !this.stub.isKeyguardOccluded();
    }

    @Override // com.oplus.systemui.plugins.qs.animator.IQSExpandOrCollapseAnimation
    public final boolean isNeedAddResponse() {
        return true;
    }

    @Override // com.oplus.systemui.plugins.qs.IQsComponent
    public final void onCollapseQSPanelStart(boolean z) {
    }

    @Override // com.oplus.systemui.plugins.qs.IQsComponent
    public void onEnterAddingMode(boolean z, boolean z2, Bundle bundle) {
        super.onEnterAddingMode(z, z2, bundle);
    }

    @Override // com.oplus.systemui.plugins.qs.IQsComponent
    public final void onEnterDragDisableMode() {
    }

    @Override // com.oplus.systemui.plugins.qs.IQsComponent
    public void onEnterDragMode(boolean z, Bundle bundle) {
        super.onEnterDragMode(z, bundle);
    }

    @Override // com.oplus.systemui.plugins.qs.IQsComponent
    public final void onEnterEditMode(boolean z) {
    }

    @Override // com.oplus.systemui.plugins.qs.IQsComponent
    public final void onExitAddingMode(boolean z) {
    }

    @Override // com.oplus.systemui.plugins.qs.IQsComponent
    public final void onExitDragDisableMode() {
    }

    @Override // com.oplus.systemui.plugins.qs.IQsComponent
    public final void onExitDragMode() {
    }

    @Override // com.oplus.systemui.plugins.qs.IQsComponent
    public final void onExitEditMode(boolean z) {
    }

    @Override // com.oplus.systemui.qs.base.util.ViewController
    public final void onInit() {
        this.fakeClockContainer = (FrameLayout) this.separateQsFakeLayout.requireViewById(2131364747);
        this.fakeKeyguardStatusIconContainer = (FrameLayout) this.separateQsFakeLayout.requireViewById(2131364777);
        this.fakeCarrierContainer = (FrameLayout) this.separateQsFakeLayout.requireViewById(2131364746);
        this.fakeNotificationIconContainer = (FrameLayout) this.separateQsFakeLayout.requireViewById(2131364750);
        this.fakeKeyguardCarrierContainer = (FrameLayout) this.separateQsFakeLayout.requireViewById(2131364748);
        this.fakeStatusIconContainer = (FrameLayout) this.separateQsFakeLayout.requireViewById(2131364754);
        this.fakePrivacyIconContainer = (FrameLayout) this.separateQsFakeLayout.requireViewById(2131364751);
        this.carrierContainer = this.quickEntranceContainerViewController.getCarrierTextContainer();
        this.statusIconsView = this.statusBarHeader.getQsStatusIconsView();
        ViewParent parent = this.qsFakeClock.getElementView().getParent();
        ViewGroup viewGroup = parent instanceof ViewGroup ? (ViewGroup) parent : null;
        if (viewGroup != null) {
            viewGroup.removeView(this.qsFakeClock.getElementView());
        }
        ViewParent parent2 = this.qsFakeCarrier.getElementView().getParent();
        ViewGroup viewGroup2 = parent2 instanceof ViewGroup ? (ViewGroup) parent2 : null;
        if (viewGroup2 != null) {
            viewGroup2.removeView(this.qsFakeCarrier.getElementView());
        }
        ViewParent parent3 = this.qsFakeKeyguardCarrier.getElementView().getParent();
        ViewGroup viewGroup3 = parent3 instanceof ViewGroup ? (ViewGroup) parent3 : null;
        if (viewGroup3 != null) {
            viewGroup3.removeView(this.qsFakeKeyguardCarrier.getElementView());
        }
        ViewParent parent4 = this.qsKeyguardFakeStatusIcon.getElementView().getParent();
        ViewGroup viewGroup4 = parent4 instanceof ViewGroup ? (ViewGroup) parent4 : null;
        if (viewGroup4 != null) {
            viewGroup4.removeView(this.qsKeyguardFakeStatusIcon.getElementView());
        }
        ViewParent parent5 = this.qsFakeNotificationIcon.getElementView().getParent();
        ViewGroup viewGroup5 = parent5 instanceof ViewGroup ? (ViewGroup) parent5 : null;
        if (viewGroup5 != null) {
            viewGroup5.removeView(this.qsFakeNotificationIcon.getElementView());
        }
        ViewParent parent6 = this.qsFakeStatusIcon.getElementView().getParent();
        ViewGroup viewGroup6 = parent6 instanceof ViewGroup ? (ViewGroup) parent6 : null;
        if (viewGroup6 != null) {
            viewGroup6.removeView(this.qsFakeStatusIcon.getElementView());
        }
        ViewParent parent7 = this.qsFakePrivacyIcon.getElementView().getParent();
        ViewGroup viewGroup7 = parent7 instanceof ViewGroup ? (ViewGroup) parent7 : null;
        if (viewGroup7 != null) {
            viewGroup7.removeView(this.qsFakePrivacyIcon.getElementView());
        }
        ViewParent parent8 = this.qsFakeClock.getElementView().getParent();
        if ((parent8 instanceof ViewGroup ? (ViewGroup) parent8 : null) == null) {
            FrameLayout frameLayout = this.fakeClockContainer;
            if (frameLayout == null) {
                frameLayout = null;
            }
            frameLayout.addView(this.qsFakeClock.getElementView());
        }
        ViewParent parent9 = this.qsFakeCarrier.getElementView().getParent();
        if ((parent9 instanceof ViewGroup ? (ViewGroup) parent9 : null) == null) {
            FrameLayout frameLayout2 = this.fakeCarrierContainer;
            if (frameLayout2 == null) {
                frameLayout2 = null;
            }
            frameLayout2.addView(this.qsFakeCarrier.getElementView());
        }
        ViewParent parent10 = this.qsFakeNotificationIcon.getElementView().getParent();
        if ((parent10 instanceof ViewGroup ? (ViewGroup) parent10 : null) == null) {
            FrameLayout frameLayout3 = this.fakeNotificationIconContainer;
            if (frameLayout3 == null) {
                frameLayout3 = null;
            }
            frameLayout3.addView(this.qsFakeNotificationIcon.getElementView());
        }
        ViewParent parent11 = this.qsFakeStatusIcon.getElementView().getParent();
        if ((parent11 instanceof ViewGroup ? (ViewGroup) parent11 : null) == null) {
            FrameLayout frameLayout4 = this.fakeStatusIconContainer;
            if (frameLayout4 == null) {
                frameLayout4 = null;
            }
            frameLayout4.addView(this.qsFakeStatusIcon.getElementView());
        }
        ViewParent parent12 = this.qsKeyguardFakeStatusIcon.getElementView().getParent();
        if ((parent12 instanceof ViewGroup ? (ViewGroup) parent12 : null) == null) {
            FrameLayout frameLayout5 = this.fakeKeyguardStatusIconContainer;
            if (frameLayout5 == null) {
                frameLayout5 = null;
            }
            frameLayout5.addView(this.qsKeyguardFakeStatusIcon.getElementView());
        }
        ViewParent parent13 = this.qsFakeKeyguardCarrier.getElementView().getParent();
        if ((parent13 instanceof ViewGroup ? (ViewGroup) parent13 : null) == null) {
            FrameLayout frameLayout6 = this.fakeKeyguardCarrierContainer;
            if (frameLayout6 == null) {
                frameLayout6 = null;
            }
            frameLayout6.addView(this.qsFakeKeyguardCarrier.getElementView());
        }
        ViewParent parent14 = this.qsFakePrivacyIcon.getElementView().getParent();
        if ((parent14 instanceof ViewGroup ? (ViewGroup) parent14 : null) == null) {
            FrameLayout frameLayout7 = this.fakePrivacyIconContainer;
            (frameLayout7 != null ? frameLayout7 : null).addView(this.qsFakePrivacyIcon.getElementView());
        }
    }

    public final void onSystemIconScaleChanged(float f) {
        ViewGroup qsStatusIconsView = this.statusBarHeader.getQsStatusIconsView();
        if (qsStatusIconsView != null) {
            qsStatusIconsView.setScaleX(f);
            qsStatusIconsView.setScaleY(f);
        }
    }

    @Override // com.oplus.systemui.plugins.qs.IQsComponent
    public final void onThemeApplied(boolean z) {
    }

    @Override // com.oplus.systemui.qs.base.util.ViewController
    public final void onViewAttached() {
        this.qsPanelAnimatorManager.addElementVisibleChangeListener(this.qsElementShowingChangeListener);
        this.qsPanelAnimatorManager.addExpandFractionCallback(this.qsPanelExpandFractionListener);
        this.qsPanelAnimatorManager.addTranslationYCallback(this.qsPanelTranslationYListener);
        ViewGroup qsStatusIconsView = this.statusBarHeader.getQsStatusIconsView();
        if (qsStatusIconsView != null) {
            qsStatusIconsView.addOnLayoutChangeListener(this.statusIconsLayoutListener);
        }
        resetState(this.qsPanelAnimatorManager.getRawFraction());
        OplusSeparateQSPlugin.IStub iStub = this.stub;
        SeparateQSFakeStatusController$$ExternalSyntheticLambda0 separateQSFakeStatusController$$ExternalSyntheticLambda0 = new SeparateQSFakeStatusController$$ExternalSyntheticLambda0(0);
        separateQSFakeStatusController$$ExternalSyntheticLambda0.f$0 = this;
        VarHandle.storeStoreFence();
        iStub.addKeyguardShowingChangedCallback(separateQSFakeStatusController$$ExternalSyntheticLambda0);
        this.quickEntranceContainerViewController.setStatusBarAlphaUpdater(new AnonymousClass2(1, this, SeparateQSFakeStatusController.class, "setStatusBarAlpha", "setStatusBarAlpha(F)V", 0));
    }

    @Override // com.oplus.systemui.qs.base.util.ViewController
    public final void onViewDetached() {
        this.qsPanelAnimatorManager.removeElementVisibleChangeListener(this.qsElementShowingChangeListener);
        this.qsPanelAnimatorManager.removeExpandFractionCallback(this.qsPanelExpandFractionListener);
        this.qsPanelAnimatorManager.removeTranslationYCallback(this.qsPanelTranslationYListener);
        ViewGroup qsStatusIconsView = this.statusBarHeader.getQsStatusIconsView();
        if (qsStatusIconsView != null) {
            qsStatusIconsView.removeOnLayoutChangeListener(this.statusIconsLayoutListener);
        }
        this.stub.removeKeyguardShowingChangedCallback();
        this.quickEntranceContainerViewController.setStatusBarAlphaUpdater(null);
        this.transitionXAnimator = null;
        this.separateQsFakeLayout.removeCallbacks(this.updateElementTranslationRunnable);
    }

    @Override // com.oplus.systemui.plugins.qs.animator.IQSExpandOrCollapseAnimation
    public final void resetNonCurrPageToExpanded() {
    }

    public final void resetState(float f) {
        updateExtraTranslationY(f);
        updateLeftAreaState(f);
        updateRightArea(f);
        boolean z = this.stub.getNtIsFullCollapsed() || isKeyguardVisible();
        if (f == 0.0f && z) {
            FrameLayout frameLayout = this.fakeClockContainer;
            if (frameLayout == null) {
                frameLayout = null;
            }
            setFakeTransitionAlpha(1.0f, frameLayout);
            FrameLayout frameLayout2 = this.fakeNotificationIconContainer;
            if (frameLayout2 == null) {
                frameLayout2 = null;
            }
            setFakeTransitionAlpha(1.0f, frameLayout2);
            FrameLayout frameLayout3 = this.fakePrivacyIconContainer;
            if (frameLayout3 == null) {
                frameLayout3 = null;
            }
            setFakeTransitionAlpha(1.0f, frameLayout3);
            FrameLayout frameLayout4 = this.fakeStatusIconContainer;
            if (frameLayout4 == null) {
                frameLayout4 = null;
            }
            setFakeTransitionAlpha(1.0f, frameLayout4);
            FrameLayout frameLayout5 = this.fakeKeyguardStatusIconContainer;
            if (frameLayout5 == null) {
                frameLayout5 = null;
            }
            setFakeTransitionAlpha(1.0f, frameLayout5);
            FrameLayout frameLayout6 = this.fakeCarrierContainer;
            if (frameLayout6 == null) {
                frameLayout6 = null;
            }
            setFakeTransitionAlpha(1.0f, frameLayout6);
            FrameLayout frameLayout7 = this.fakeKeyguardCarrierContainer;
            setFakeTransitionAlpha(1.0f, frameLayout7 != null ? frameLayout7 : null);
            this.separateQSPrivacyView.setTransitionAlpha(0.0f);
            ViewGroup viewGroup = this.statusIconsView;
            if (viewGroup != null) {
                viewGroup.setTransitionAlpha(0.0f);
            }
            ViewGroup viewGroup2 = this.carrierContainer;
            if (viewGroup2 != null) {
                viewGroup2.setTransitionAlpha(0.0f);
            }
        } else if (f == 1.0f) {
            FrameLayout frameLayout8 = this.fakeClockContainer;
            if (frameLayout8 == null) {
                frameLayout8 = null;
            }
            setFakeTransitionAlpha(0.0f, frameLayout8);
            FrameLayout frameLayout9 = this.fakeNotificationIconContainer;
            if (frameLayout9 == null) {
                frameLayout9 = null;
            }
            setFakeTransitionAlpha(0.0f, frameLayout9);
            FrameLayout frameLayout10 = this.fakePrivacyIconContainer;
            if (frameLayout10 == null) {
                frameLayout10 = null;
            }
            setFakeTransitionAlpha(0.0f, frameLayout10);
            FrameLayout frameLayout11 = this.fakeStatusIconContainer;
            if (frameLayout11 == null) {
                frameLayout11 = null;
            }
            setFakeTransitionAlpha(0.0f, frameLayout11);
            FrameLayout frameLayout12 = this.fakeKeyguardStatusIconContainer;
            if (frameLayout12 == null) {
                frameLayout12 = null;
            }
            setFakeTransitionAlpha(0.0f, frameLayout12);
            FrameLayout frameLayout13 = this.fakeCarrierContainer;
            if (frameLayout13 == null) {
                frameLayout13 = null;
            }
            setFakeTransitionAlpha(0.0f, frameLayout13);
            FrameLayout frameLayout14 = this.fakeKeyguardCarrierContainer;
            setFakeTransitionAlpha(0.0f, frameLayout14 != null ? frameLayout14 : null);
            this.separateQSPrivacyView.setTransitionAlpha(1.0f);
            ViewGroup viewGroup3 = this.statusIconsView;
            if (viewGroup3 != null) {
                viewGroup3.setTransitionAlpha(1.0f);
            }
            ViewGroup viewGroup4 = this.carrierContainer;
            if (viewGroup4 != null) {
                viewGroup4.setTransitionAlpha(1.0f);
            }
        }
        AnimatorUtils.INSTANCE.setExtraFakeTranslationY(0.0f);
    }

    @Override // com.oplus.systemui.plugins.qs.IQsComponent, com.oplus.systemui.plugins.qs.animator.IQSExpandOrCollapseAnimation
    public void saveLatestNoEditModeLocList(List<Loc> list) {
        super.saveLatestNoEditModeLocList(list);
    }

    @Override // com.oplus.systemui.plugins.qs.animator.IQSExpandOrCollapseAnimation
    public final void setCusTranslationY(float f, int i, int i2) {
    }

    @Override // com.oplus.systemui.plugins.qs.IQsComponent
    public void setEmptyTouchListener(Function1 function1) {
        super.setEmptyTouchListener(function1);
    }

    @Override // com.oplus.systemui.plugins.qs.IQsComponent
    public void setLayerType(boolean z) {
        RenderBooster renderBoosterEnsureRenderBooster$default;
        RenderBooster renderBoosterEnsureRenderBooster$default2;
        QSElement qSElement = this.statusBarHeader;
        if (z) {
            ViewGroup clockContainer = qSElement.getClockContainer();
            if (clockContainer == null || (renderBoosterEnsureRenderBooster$default2 = RenderBooster.Companion.ensureRenderBooster$default(RenderBooster.Companion, clockContainer, null, 3)) == null) {
                return;
            }
            RenderBooster.requestBooster$default(renderBoosterEnsureRenderBooster$default2, 4, false, 6);
            return;
        }
        ViewGroup clockContainer2 = qSElement.getClockContainer();
        if (clockContainer2 == null || (renderBoosterEnsureRenderBooster$default = RenderBooster.Companion.ensureRenderBooster$default(RenderBooster.Companion, clockContainer2, null, 3)) == null) {
            return;
        }
        renderBoosterEnsureRenderBooster$default.requestStop(0);
    }

    @Override // com.oplus.systemui.plugins.qs.IQsComponent
    public void setListening(boolean z) {
        super.setListening(z);
    }

    @Override // com.oplus.systemui.plugins.qs.IQsComponent, com.oplus.systemui.plugins.qs.animator.IQSExpandOrCollapseAnimation
    public void setNodeNumChange(IQSExpandOrCollapseAnimation.QSNodeChangeListener qSNodeChangeListener) {
        super.setNodeNumChange(qSNodeChangeListener);
    }

    @Override // com.oplus.systemui.plugins.qs.IQsComponent, com.oplus.systemui.plugins.qs.animator.IQSExpandOrCollapseAnimation
    public void setPageSelectedListener(IQSExpandOrCollapseAnimation.QSNodePageSelectedListener qSNodePageSelectedListener) {
        super.setPageSelectedListener(qSNodePageSelectedListener);
    }

    public final void setStatusBarAlpha(float f) {
        ViewGroup viewGroup = this.statusIconsView;
        if (viewGroup != null) {
            viewGroup.setTransitionAlpha(f);
        }
        ViewGroup viewGroup2 = this.carrierContainer;
        if (viewGroup2 != null) {
            viewGroup2.setTransitionAlpha(f);
        }
    }

    public final void setTempRawFraction(float f) {
        this.tempRawFraction = f;
    }

    @Override // com.oplus.systemui.qs.base.util.ViewController, com.oplus.systemui.plugins.qs.IQsComponent
    public final void startComponent() {
    }

    @Override // com.oplus.systemui.plugins.qs.IQsComponent
    public void updateColor(QSSpecialModeUpdateMonitor.ColorType colorType) {
        super.updateColor(colorType);
    }

    @Override // com.oplus.systemui.plugins.qs.IQsComponent
    public void updateDrawable(List<SpanSize> list, boolean z) {
        super.updateDrawable(list, z);
    }

    public final void updateElementLoc$1() {
        int i = getResources().getDisplayMetrics().widthPixels;
        FrameLayout frameLayout = this.fakeClockContainer;
        if (frameLayout == null) {
            frameLayout = null;
        }
        FrameLayout.LayoutParams layoutParams = (FrameLayout.LayoutParams) frameLayout.getLayoutParams();
        FrameLayout frameLayout2 = this.fakeClockContainer;
        if (frameLayout2 == null) {
            frameLayout2 = null;
        }
        ViewGroup.LayoutParams layoutParams2 = frameLayout2.getLayoutParams();
        ViewGroup.MarginLayoutParams marginLayoutParams = layoutParams2 instanceof ViewGroup.MarginLayoutParams ? (ViewGroup.MarginLayoutParams) layoutParams2 : null;
        if (marginLayoutParams != null) {
            boolean zIsRtl = DeviceUtil.isRtl();
            QSFakeStatusElement qSFakeStatusElement = this.qsFakeClock;
            int realStatusElementLocX = zIsRtl ? (int) ((i - qSFakeStatusElement.getRealStatusElementLocX()) - ((Number) this.qsFakeClock.getRealStatusElementParams().getFirst()).floatValue()) : ((int) qSFakeStatusElement.getRealStatusElementLocX()) - ((FrameLayout) this.mView).getLeft();
            if (marginLayoutParams.getMarginStart() != realStatusElementLocX) {
                marginLayoutParams.setMarginStart(realStatusElementLocX);
                frameLayout2.setLayoutParams(marginLayoutParams);
            }
        }
        FrameLayout frameLayout3 = this.fakeCarrierContainer;
        if (frameLayout3 == null) {
            frameLayout3 = null;
        }
        FrameLayout.LayoutParams layoutParams3 = (FrameLayout.LayoutParams) frameLayout3.getLayoutParams();
        FrameLayout frameLayout4 = this.fakeCarrierContainer;
        if (frameLayout4 == null) {
            frameLayout4 = null;
        }
        ViewGroup.LayoutParams layoutParams4 = frameLayout4.getLayoutParams();
        ViewGroup.MarginLayoutParams marginLayoutParams2 = layoutParams4 instanceof ViewGroup.MarginLayoutParams ? (ViewGroup.MarginLayoutParams) layoutParams4 : null;
        if (marginLayoutParams2 != null) {
            boolean zIsRtl2 = DeviceUtil.isRtl();
            QSFakeStatusElement qSFakeStatusElement2 = this.qsFakeCarrier;
            int realStatusElementLocX2 = (int) (zIsRtl2 ? (i - qSFakeStatusElement2.getRealStatusElementLocX()) - ((Number) this.qsFakeCarrier.getRealStatusElementParams().getFirst()).floatValue() : qSFakeStatusElement2.getRealStatusElementLocX());
            if (marginLayoutParams2.getMarginStart() != realStatusElementLocX2) {
                marginLayoutParams2.setMarginStart(realStatusElementLocX2);
                frameLayout4.setLayoutParams(marginLayoutParams2);
            }
        }
        FrameLayout frameLayout5 = this.fakeNotificationIconContainer;
        if (frameLayout5 == null) {
            frameLayout5 = null;
        }
        FrameLayout.LayoutParams layoutParams5 = (FrameLayout.LayoutParams) frameLayout5.getLayoutParams();
        FrameLayout frameLayout6 = this.fakeNotificationIconContainer;
        if (frameLayout6 == null) {
            frameLayout6 = null;
        }
        ViewGroup.LayoutParams layoutParams6 = frameLayout6.getLayoutParams();
        ViewGroup.MarginLayoutParams marginLayoutParams3 = layoutParams6 instanceof ViewGroup.MarginLayoutParams ? (ViewGroup.MarginLayoutParams) layoutParams6 : null;
        if (marginLayoutParams3 != null) {
            boolean zIsRtl3 = DeviceUtil.isRtl();
            QSFakeStatusElement qSFakeStatusElement3 = this.qsFakeNotificationIcon;
            int realStatusElementLocX3 = zIsRtl3 ? (int) ((((i - qSFakeStatusElement3.getRealStatusElementLocX()) - ((Number) this.qsFakeNotificationIcon.getRealStatusElementParams().getFirst()).floatValue()) - this.qsFakeNotificationIcon.getOffsetX()) - ((FrameLayout) this.mView).getLeft()) : (((int) qSFakeStatusElement3.getRealStatusElementLocX()) - this.qsFakeNotificationIcon.getOffsetX()) - ((FrameLayout) this.mView).getLeft();
            if (marginLayoutParams3.getMarginStart() != realStatusElementLocX3) {
                marginLayoutParams3.setMarginStart(realStatusElementLocX3);
                frameLayout6.setLayoutParams(marginLayoutParams3);
            }
        }
        FrameLayout frameLayout7 = this.fakeStatusIconContainer;
        if (frameLayout7 == null) {
            frameLayout7 = null;
        }
        FrameLayout.LayoutParams layoutParams7 = (FrameLayout.LayoutParams) frameLayout7.getLayoutParams();
        FrameLayout frameLayout8 = this.fakeStatusIconContainer;
        if (frameLayout8 == null) {
            frameLayout8 = null;
        }
        ViewGroup.LayoutParams layoutParams8 = frameLayout8.getLayoutParams();
        ViewGroup.MarginLayoutParams marginLayoutParams4 = layoutParams8 instanceof ViewGroup.MarginLayoutParams ? (ViewGroup.MarginLayoutParams) layoutParams8 : null;
        if (marginLayoutParams4 != null) {
            boolean zIsRtl4 = DeviceUtil.isRtl();
            QSFakeStatusElement qSFakeStatusElement4 = this.qsFakeStatusIcon;
            int realStatusElementLocX4 = (int) (zIsRtl4 ? qSFakeStatusElement4.getRealStatusElementLocX() : (i - qSFakeStatusElement4.getRealStatusElementLocX()) - ((Number) this.qsFakeStatusIcon.getRealStatusElementParams().getFirst()).floatValue());
            if (marginLayoutParams4.getMarginEnd() != realStatusElementLocX4) {
                marginLayoutParams4.setMarginEnd(realStatusElementLocX4);
                frameLayout8.setLayoutParams(marginLayoutParams4);
            }
        }
        FrameLayout frameLayout9 = this.fakeKeyguardStatusIconContainer;
        if (frameLayout9 == null) {
            frameLayout9 = null;
        }
        FrameLayout.LayoutParams layoutParams9 = (FrameLayout.LayoutParams) frameLayout9.getLayoutParams();
        FrameLayout frameLayout10 = this.fakeKeyguardStatusIconContainer;
        if (frameLayout10 == null) {
            frameLayout10 = null;
        }
        ViewGroup.LayoutParams layoutParams10 = frameLayout10.getLayoutParams();
        ViewGroup.MarginLayoutParams marginLayoutParams5 = layoutParams10 instanceof ViewGroup.MarginLayoutParams ? (ViewGroup.MarginLayoutParams) layoutParams10 : null;
        if (marginLayoutParams5 != null) {
            boolean zIsRtl5 = DeviceUtil.isRtl();
            QSFakeStatusElement qSFakeStatusElement5 = this.qsKeyguardFakeStatusIcon;
            int realStatusElementLocX5 = (int) (zIsRtl5 ? qSFakeStatusElement5.getRealStatusElementLocX() : (i - qSFakeStatusElement5.getRealStatusElementLocX()) - ((Number) this.qsKeyguardFakeStatusIcon.getRealStatusElementParams().getFirst()).floatValue());
            if (marginLayoutParams5.getMarginEnd() != realStatusElementLocX5) {
                marginLayoutParams5.setMarginEnd(realStatusElementLocX5);
                frameLayout10.setLayoutParams(marginLayoutParams5);
            }
        }
        FrameLayout frameLayout11 = this.fakeKeyguardCarrierContainer;
        if (frameLayout11 == null) {
            frameLayout11 = null;
        }
        FrameLayout.LayoutParams layoutParams11 = (FrameLayout.LayoutParams) frameLayout11.getLayoutParams();
        FrameLayout frameLayout12 = this.fakeKeyguardCarrierContainer;
        if (frameLayout12 == null) {
            frameLayout12 = null;
        }
        ViewGroup.LayoutParams layoutParams12 = frameLayout12.getLayoutParams();
        ViewGroup.MarginLayoutParams marginLayoutParams6 = layoutParams12 instanceof ViewGroup.MarginLayoutParams ? (ViewGroup.MarginLayoutParams) layoutParams12 : null;
        if (marginLayoutParams6 != null) {
            boolean zIsRtl6 = DeviceUtil.isRtl();
            QSFakeStatusElement qSFakeStatusElement6 = this.qsFakeKeyguardCarrier;
            int realStatusElementLocX6 = (int) (zIsRtl6 ? (i - qSFakeStatusElement6.getRealStatusElementLocX()) - ((Number) this.qsFakeKeyguardCarrier.getRealStatusElementParams().getFirst()).floatValue() : qSFakeStatusElement6.getRealStatusElementLocX());
            if (marginLayoutParams6.getMarginStart() != realStatusElementLocX6) {
                marginLayoutParams6.setMarginStart(realStatusElementLocX6);
                frameLayout12.setLayoutParams(marginLayoutParams6);
            }
        }
        FrameLayout frameLayout13 = this.fakePrivacyIconContainer;
        if (frameLayout13 == null) {
            frameLayout13 = null;
        }
        FrameLayout.LayoutParams layoutParams13 = (FrameLayout.LayoutParams) frameLayout13.getLayoutParams();
        FrameLayout frameLayout14 = this.fakePrivacyIconContainer;
        if (frameLayout14 == null) {
            frameLayout14 = null;
        }
        ViewGroup.LayoutParams layoutParams14 = frameLayout14.getLayoutParams();
        ViewGroup.MarginLayoutParams marginLayoutParams7 = layoutParams14 instanceof ViewGroup.MarginLayoutParams ? (ViewGroup.MarginLayoutParams) layoutParams14 : null;
        if (marginLayoutParams7 != null) {
            boolean zIsRtl7 = DeviceUtil.isRtl();
            QSFakeStatusElement qSFakeStatusElement7 = this.qsFakePrivacyIcon;
            int realStatusElementLocX7 = zIsRtl7 ? (int) qSFakeStatusElement7.getRealStatusElementLocX() : (int) ((i - qSFakeStatusElement7.getRealStatusElementLocX()) - ((Number) this.qsFakePrivacyIcon.getRealStatusElementParams().getFirst()).floatValue());
            if (marginLayoutParams7.getMarginEnd() != realStatusElementLocX7) {
                marginLayoutParams7.setMarginEnd(realStatusElementLocX7);
                frameLayout14.setLayoutParams(marginLayoutParams7);
            }
        }
        if (LogUtil.getNormalDebug() && LogUtil.isTagValid(LogUtil.TAG_QS)) {
            int marginStart = layoutParams.getMarginStart();
            int marginStart2 = layoutParams3.getMarginStart();
            int marginStart3 = layoutParams5.getMarginStart();
            int marginEnd = layoutParams7.getMarginEnd();
            int marginEnd2 = layoutParams9.getMarginEnd();
            int marginStart4 = layoutParams11.getMarginStart();
            int marginEnd3 = layoutParams13.getMarginEnd();
            StringBuilder sbM = FloatList$.ExternalSyntheticOutline0.m("screenWidth ", i, marginStart, ",fakeClockLayoutParam.marginStart:", ",fakeCarrierLayoutParam.marginStart:");
            RectList$.ExternalSyntheticOutline0.m(marginStart2, marginStart3, ",fakeNotificationIconLayoutParam.marginStart:", ",fakeStatusIconLayoutParam.marginEnd:", sbM);
            RectList$.ExternalSyntheticOutline0.m(marginEnd, marginEnd2, ",keyguardFakeStatusIconLayoutParam.marginEnd:", ",keyguardFakeCarrierLayoutParam.marginStart:", sbM);
            Log.d("SystemUi--QuickSettings", NotificationPanelViewController$.ExternalSyntheticOutline0.m(marginStart4, marginEnd3, "fakePrivacyLayoutParam.marginEnd:", "SeparateQSFakeStatusController-->", sbM));
        }
    }

    /* JADX WARN: Code duplicated, block: B:7:0x0018  */
    public final void updateElementTranslation$1() {
        boolean z;
        if (OplusQSSpecialModeProvider.Companion.getInstance().isLargeFoldDeviceUnFoldingCache) {
            z = false;
        } else {
            FeatureOption.INSTANCE.getClass();
            if (FeatureOption.isTablet()) {
                z = false;
            } else {
                z = true;
            }
        }
        this.isSmallScreen = z;
        this.fakeStatusIconHeight = ((Number) getFakeStatusIconView().getRealStatusElementParams().getSecond()).intValue();
        this.qsPanelAnimatorManager.setPanelDestTranslationY(getPanelDestTranslationY());
        boolean zIsKeyguardVisible = isKeyguardVisible();
        FrameLayout frameLayout = this.fakeClockContainer;
        if (zIsKeyguardVisible) {
            if (frameLayout == null) {
                frameLayout = null;
            }
            frameLayout.setVisibility(4);
            FrameLayout frameLayout2 = this.fakeCarrierContainer;
            if (frameLayout2 == null) {
                frameLayout2 = null;
            }
            frameLayout2.setVisibility(4);
            FrameLayout frameLayout3 = this.fakeKeyguardCarrierContainer;
            if (frameLayout3 == null) {
                frameLayout3 = null;
            }
            frameLayout3.setVisibility(0);
            FrameLayout frameLayout4 = this.fakeKeyguardStatusIconContainer;
            if (frameLayout4 == null) {
                frameLayout4 = null;
            }
            frameLayout4.setVisibility(0);
            FrameLayout frameLayout5 = this.fakeStatusIconContainer;
            (frameLayout5 != null ? frameLayout5 : null).setVisibility(4);
        } else {
            if (frameLayout == null) {
                frameLayout = null;
            }
            frameLayout.setVisibility(0);
            FrameLayout frameLayout6 = this.fakeCarrierContainer;
            if (frameLayout6 == null) {
                frameLayout6 = null;
            }
            frameLayout6.setVisibility(0);
            FrameLayout frameLayout7 = this.fakeStatusIconContainer;
            if (frameLayout7 == null) {
                frameLayout7 = null;
            }
            frameLayout7.setVisibility(0);
            FrameLayout frameLayout8 = this.fakeKeyguardCarrierContainer;
            if (frameLayout8 == null) {
                frameLayout8 = null;
            }
            frameLayout8.setVisibility(4);
            FrameLayout frameLayout9 = this.fakeKeyguardStatusIconContainer;
            (frameLayout9 != null ? frameLayout9 : null).setVisibility(4);
        }
        updateElementLoc$1();
        this.clockTranslationX = getFakeClockTranslationX();
        this.statusIconTranslationX = getFakeStatusIconTranslationX();
        this.keyguardCarrierTranslationX = getFakeKeyguardCarrierTx();
        if (LogUtil.getNormalDebug() && LogUtil.isTagValid(LogUtil.TAG_QS)) {
            Log.d("SystemUi--QuickSettings", "SeparateQSFakeStatusController-->".concat(ListPopupWindow$.ExternalSyntheticOutline0.m("clockTranslationX: ", this.clockTranslationX, ",statusIconTranslationX:", this.statusIconTranslationX, OplusPreventModeData.SEPARATOR)));
        }
    }

    public final void updateExtraTranslationY(float f) {
        AnimatorUtils animatorUtils = AnimatorUtils.INSTANCE;
        if (animatorUtils.getExtraFakeTranslationY() > 0.0f) {
            if (this.tempRawFraction == 0.0f) {
                this.tempRawFraction = f;
            }
            float f2 = this.tempRawFraction;
            if (f2 == 0.0f) {
                return;
            }
            float extraFakeTranslationY = animatorUtils.getExtraFakeTranslationY() * (f / f2);
            this.separateQsFakeLayout.setTranslationY(extraFakeTranslationY);
            this.statusBarContainer.setTranslationY(extraFakeTranslationY);
            this.quickEntranceContainerViewController.getView().setTranslationY(extraFakeTranslationY);
            updateTranslationY$1(extraFakeTranslationY);
        }
    }

    @Override // com.oplus.systemui.plugins.qs.IQsComponent
    public final void updateIconDrawable(boolean z) {
    }

    @Override // com.oplus.systemui.plugins.qs.IQsComponent
    public final void updateIconTint() {
    }

    public final void updateLeftAreaState(float f) {
        ViewGroup viewGroup;
        boolean z = this.isSmallScreen;
        FrameLayout frameLayout = this.fakeClockContainer;
        if (z) {
            if (frameLayout == null) {
                frameLayout = null;
            }
            frameLayout.setTranslationX(this.clockTranslationX * f);
        } else {
            if (frameLayout == null) {
                frameLayout = null;
            }
            frameLayout.setTranslationX(0.0f);
        }
        boolean z2 = this.isSmallScreen;
        FrameLayout frameLayout2 = this.fakeNotificationIconContainer;
        if (z2) {
            if (frameLayout2 == null) {
                frameLayout2 = null;
            }
            frameLayout2.setTranslationX(this.clockTranslationX * f);
        } else {
            if (frameLayout2 == null) {
                frameLayout2 = null;
            }
            frameLayout2.setTranslationX(0.0f);
        }
        updateQSClockAndDateState(f);
        FrameLayout frameLayout3 = this.fakeCarrierContainer;
        if (frameLayout3 == null) {
            frameLayout3 = null;
        }
        frameLayout3.setTranslationX(0.0f);
        boolean z3 = this.isSmallScreen;
        FrameLayout frameLayout4 = this.fakeKeyguardCarrierContainer;
        if (z3) {
            (frameLayout4 != null ? frameLayout4 : null).setTranslationX(this.keyguardCarrierTranslationX * f);
            if (!isKeyguardVisible() || (viewGroup = this.carrierContainer) == null) {
                return;
            }
            viewGroup.setTranslationX((f - 1.0f) * this.keyguardCarrierTranslationX);
            return;
        }
        (frameLayout4 != null ? frameLayout4 : null).setTranslationX(0.0f);
        ViewGroup viewGroup2 = this.carrierContainer;
        if (viewGroup2 != null) {
            viewGroup2.setTranslationX(0.0f);
        }
    }

    public final void updateMarginEnd(View view, Function0 function0) {
        int iIntValue;
        ViewGroup.LayoutParams layoutParams = view.getLayoutParams();
        ViewGroup.MarginLayoutParams marginLayoutParams = layoutParams instanceof ViewGroup.MarginLayoutParams ? (ViewGroup.MarginLayoutParams) layoutParams : null;
        if (marginLayoutParams == null || marginLayoutParams.getMarginEnd() == (iIntValue = ((Number) function0.invoke()).intValue())) {
            return;
        }
        marginLayoutParams.setMarginEnd(iIntValue);
        view.setLayoutParams(marginLayoutParams);
    }

    public final void updateMarginStart(View view, Function0 function0) {
        int iIntValue;
        ViewGroup.LayoutParams layoutParams = view.getLayoutParams();
        ViewGroup.MarginLayoutParams marginLayoutParams = layoutParams instanceof ViewGroup.MarginLayoutParams ? (ViewGroup.MarginLayoutParams) layoutParams : null;
        if (marginLayoutParams == null || marginLayoutParams.getMarginStart() == (iIntValue = ((Number) function0.invoke()).intValue())) {
            return;
        }
        marginLayoutParams.setMarginStart(iIntValue);
        view.setLayoutParams(marginLayoutParams);
    }

    @Override // com.oplus.systemui.plugins.qs.IQsComponent
    public final void updateNodeTranslationY() {
    }

    @Override // com.oplus.systemui.plugins.qs.IQsComponent
    public void updateOutline(List<SpanSize> list) {
        super.updateOutline(list);
    }

    @Override // com.oplus.systemui.plugins.qs.animator.IQSExpandOrCollapseAnimation
    public final void updatePivotXY(int i, int i2, boolean z) {
    }

    public final void updateQSClockAndDateState(float f) {
        boolean z = this.isSmallScreen;
        ViewGroup viewGroup = this.carrierContainer;
        if (!z) {
            if (viewGroup != null) {
                viewGroup.setTranslationX(0.0f);
            }
        } else {
            if (viewGroup != null) {
                viewGroup.setTranslationX((f - 1.0f) * this.clockTranslationX);
            }
            ViewGroup viewGroup2 = this.carrierContainer;
            if (viewGroup2 != null) {
                viewGroup2.setTranslationY(0.0f);
            }
        }
    }

    @Override // com.oplus.systemui.plugins.qs.IQsComponent
    public final void updateQuickEntranceDrawable() {
    }

    @Override // com.oplus.systemui.plugins.qs.IQsComponent
    public void updateResource(boolean z, Context context) {
        super.updateResource(z, context);
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
    public final void updateRightArea(float f) {
        ViewGroup viewGroup = this.statusIconsView;
        if (viewGroup != null) {
            viewGroup.setTranslationX((f - 1.0f) * this.statusIconTranslationX);
        }
        FrameLayout frameLayout = this.fakeStatusIconContainer;
        if (frameLayout == null) {
            frameLayout = null;
        }
        frameLayout.setTranslationX(this.statusIconTranslationX * f);
        FrameLayout frameLayout2 = this.fakePrivacyIconContainer;
        if (frameLayout2 == null) {
            frameLayout2 = null;
        }
        frameLayout2.setTranslationX(this.statusIconTranslationX * f);
        FrameLayout frameLayout3 = this.fakeKeyguardStatusIconContainer;
        (frameLayout3 != null ? frameLayout3 : null).setTranslationX(this.statusIconTranslationX * f);
        this.separateQSPrivacyView.setTranslationX(((Boolean) CellAreaConfig$$ExternalSyntheticOutline0.m(OplusQSSpecialModeProvider.Companion)).booleanValue() ? (f - 1.0f) * this.statusIconTranslationX : 0.0f);
    }

    @Override // com.oplus.systemui.plugins.qs.animator.IQSExpandOrCollapseAnimation
    public final void updateState(int i, int i2, float f, float f2, float f3) {
    }

    /* JADX WARN: Code duplicated, block: B:11:0x003d  */
    /* JADX WARN: Code duplicated, block: B:12:0x0041  */
    /* JADX WARN: Code duplicated, block: B:9:0x002d  */
    public final void updateTopSpace(int i, int i2) {
        this.statusBarTopMargin = i;
        OplusQSSpecialModeProvider.Companion companion = OplusQSSpecialModeProvider.Companion;
        OplusQSSpecialModeProvider companion2 = companion.getInstance();
        if (((Boolean) companion2.isLandSplitMode().getValue()).booleanValue() && !companion.getInstance().isLargeFoldDeviceUnFoldingCache) {
            FeatureOption.INSTANCE.getClass();
            if (!FeatureOption.isTablet()) {
                this.quickEntranceHeight = 0;
            } else if (((Boolean) companion2.getSuperPowerSaveMode().getValue()).booleanValue()) {
                this.quickEntranceHeight = 0;
            } else {
                this.quickEntranceHeight = this.cellAreaProvider.getCellAreaConfig().getButtonZoneHeight() + this.cellAreaProvider.getCellAreaConfig().getAHeight();
            }
        } else if (((Boolean) companion2.getSuperPowerSaveMode().getValue()).booleanValue()) {
            this.quickEntranceHeight = 0;
        } else {
            this.quickEntranceHeight = this.cellAreaProvider.getCellAreaConfig().getButtonZoneHeight() + this.cellAreaProvider.getCellAreaConfig().getAHeight();
        }
        FrameLayout frameLayout = this.separateQsFakeLayout;
        ViewGroup.LayoutParams layoutParams = frameLayout.getLayoutParams();
        ViewGroup.MarginLayoutParams marginLayoutParams = layoutParams instanceof ViewGroup.MarginLayoutParams ? (ViewGroup.MarginLayoutParams) layoutParams : null;
        if (marginLayoutParams != null) {
            marginLayoutParams.topMargin = i;
        }
        ViewGroup.LayoutParams layoutParams2 = frameLayout.getLayoutParams();
        if (layoutParams2 != null) {
            layoutParams2.height = i2;
        }
        frameLayout.setLayoutParams(frameLayout.getLayoutParams());
        this.fakeStatusIconHeight = ((Number) getFakeStatusIconView().getRealStatusElementParams().getSecond()).intValue();
        this.qsPanelAnimatorManager.setPanelDestTranslationY(getPanelDestTranslationY());
        if (LogUtil.getNormalDebug() && LogUtil.isTagValid(LogUtil.TAG_QS)) {
            float f = this.statusBarTopMargin;
            float f2 = this.fakeStatusIconHeight;
            int i3 = this.quickEntranceHeight;
            int aHeight = this.cellAreaProvider.getCellAreaConfig().getAHeight();
            int buttonZoneHeight = this.cellAreaProvider.getCellAreaConfig().getButtonZoneHeight();
            StringBuilder sbM = MonoSpline$.ExternalSyntheticOutline0.m("statusBarTopMargin: ", f, ", fakeStatusIconHeight: ", f2, " quickEntranceHeight = ");
            RectList$.ExternalSyntheticOutline0.m(i3, aHeight, "cellAreaProvider.cellAreaConfig.aHeight = ", "cellAreaProvider.cellAreaConfig.buttonZoneHeight = ", sbM);
            MobileInputLogger$.ExternalSyntheticOutline0.m(buttonZoneHeight, "SeparateQSFakeStatusController-->", "SystemUi--QuickSettings", sbM);
        }
        this.separateQsFakeLayout.removeCallbacks(this.updateElementTranslationRunnable);
        this.separateQsFakeLayout.postDelayed(this.updateElementTranslationRunnable, 200L);
    }

    public final void updateTranslationY$1(float f) {
        float panelDestTranslationY = ((-this.qSPanelViewTranslationY) - this.qsPanelAnimatorManager.getPanelDestTranslationY()) - f;
        FrameLayout frameLayout = this.fakeCarrierContainer;
        if (frameLayout == null) {
            frameLayout = null;
        }
        frameLayout.setTranslationY(panelDestTranslationY);
        if (this.isSmallScreen) {
            return;
        }
        FrameLayout frameLayout2 = this.fakeClockContainer;
        if (frameLayout2 == null) {
            frameLayout2 = null;
        }
        frameLayout2.setTranslationY(panelDestTranslationY);
        FrameLayout frameLayout3 = this.fakeNotificationIconContainer;
        if (frameLayout3 == null) {
            frameLayout3 = null;
        }
        frameLayout3.setTranslationY(panelDestTranslationY);
        FrameLayout frameLayout4 = this.fakeKeyguardCarrierContainer;
        (frameLayout4 != null ? frameLayout4 : null).setTranslationY(panelDestTranslationY);
    }
}
