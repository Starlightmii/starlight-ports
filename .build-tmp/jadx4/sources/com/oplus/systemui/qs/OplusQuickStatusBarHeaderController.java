package com.oplus.systemui.qs;

import android.R;
import android.content.res.Configuration;
import android.content.res.Resources;
import android.view.View;
import android.view.ViewGroup;
import android.widget.LinearLayout;
import androidx.appcompat.widget.AppCompatTextView;
import androidx.appcompat.widget.ListPopupWindow$;
import androidx.lifecycle.Lifecycle;
import androidx.lifecycle.LifecycleOwner;
import androidx.lifecycle.RepeatOnLifecycleKt;
import com.android.app.displaylib.PerDisplayRepository;
import com.android.keyguard.OplusLockIconView$;
import com.android.systemui.biometrics.BiometricMessageDeferral$;
import com.android.systemui.display.dagger.SystemUIDisplaySubcomponent;
import com.android.systemui.flags.FeatureFlags;
import com.android.systemui.flags.Flags;
import com.android.systemui.lifecycle.RepeatWhenAttachedKt;
import com.android.systemui.privacy.OngoingPrivacyChip;
import com.android.systemui.qs.ChipVisibilityListener;
import com.android.systemui.qs.ExpandableNode;
import com.android.systemui.qs.HeaderPrivacyIconsController;
import com.android.systemui.qs.QSNode;
import com.android.systemui.qs.deprecated.BaseQuickStatusBarHeader;
import com.android.systemui.shade.ShadeExpansionChangeEvent;
import com.android.systemui.shade.ShadeExpansionListener;
import com.android.systemui.shade.ShadeExpansionStateManager;
import com.android.systemui.shade.carrier.ShadeCarrierGroup;
import com.android.systemui.shade.carrier.ShadeCarrierGroupController;
import com.android.systemui.statusbar.phone.StatusBarLocation;
import com.android.systemui.statusbar.phone.StatusIconContainer;
import com.android.systemui.statusbar.phone.ui.StatusBarIconController;
import com.android.systemui.statusbar.phone.ui.TintedIconManager;
import com.android.systemui.util.LargeScreenUtils;
import com.android.systemui.util.ViewController;
import com.oplus.systemui.qs.base.temporarilyinfo.TemporarilyInfoManager;
import com.oplus.systemui.qs.privacy.HeaderPrivacyIconsControllerFactory;
import com.oplus.systemui.qs.utils.SpringOverScroller;
import com.oplus.systemui.qs.widget.OplusSecondCarrierText;
import com.oplus.systemui.separate.OplusSeparateNotificationAndQSState;
import com.oplus.systemui.statusbar.pipeline.battery.StatBatteryMeterViewController;
import com.oplus.systemui.statusbar.pipeline.mobile.domain.interactor.OplusMobileIconsInteractor;
import com.oplus.systemui.statusbar.util.OplusStatusBarUtils;
import com.oplusos.systemui.common.util.LogUtil;
import com.oplusos.systemui.common.util.SystemUIThreadUtil;
import java.io.PrintWriter;
import java.lang.invoke.VarHandle;
import java.util.ArrayList;
import java.util.List;
import kotlin.ResultKt;
import kotlin.Unit;
import kotlin.collections.CollectionsKt__MutableCollectionsKt;
import kotlin.coroutines.Continuation;
import kotlin.coroutines.CoroutineContext;
import kotlin.coroutines.intrinsics.IntrinsicsKt__IntrinsicsKt;
import kotlin.coroutines.jvm.internal.SpillingKt;
import kotlin.coroutines.jvm.internal.SuspendLambda;
import kotlin.jvm.functions.Function1;
import kotlin.jvm.functions.Function2;
import kotlin.jvm.functions.Function3;
import kotlin.text.Regex$;
import kotlinx.coroutines.BuildersKt;
import kotlinx.coroutines.CoroutineScope;
import kotlinx.coroutines.CoroutineStart;
import kotlinx.coroutines.flow.FlowCollector;
import kotlinx.coroutines.flow.StateFlow;
import okio.Path$;

/* JADX INFO: compiled from: go/retraceme fd74bf9514dd0739b92d67a6d48a51eb10aaf8df760d83496b8dcfcf83e5f698 */
/* JADX INFO: loaded from: /home/starlight/Projects/Starlight Ports/.build-tmp/classes4.dex */
public final class OplusQuickStatusBarHeaderController extends ViewController implements QSNode, ChipVisibilityListener {
    public static final Companion Companion = new Companion();
    private static final String TAG = "OplusQuickStatusBarHeaderController";
    private final StatBatteryMeterViewController mBatteryMeterViewController;
    private ViewGroup mClockContainer;
    private ExpandableNode.OnStateListener mContainer;
    public float mExpandTopGap;
    public boolean mExpanded;
    public float mExpansion;
    private final FeatureFlags mFeatureFlags;
    private final StatusIconContainer mIconContainer;
    private final TintedIconManager mIconManager;
    public boolean mIsRemoved;
    public final boolean mIsSeparateNotificationAndQs;
    public boolean mLastCarrierMarqueeAllowedByPanel;
    public boolean mListening;
    public boolean mPanelExpanded;
    public float mPanelFraction;
    public boolean mPanelListenersRegistered;
    public boolean mPanelTracking;
    private final PerDisplayRepository mPerDisplaySubcomponentRepo;
    private HeaderPrivacyIconsController mPrivacyIconsController;
    private final ShadeCarrierGroupController mQSCarrierGroupController;
    private OplusSecondCarrierText mQsHeaderCarrierText;
    private final ShadeExpansionListener mShadeExpansionListener;
    private final StatusBarIconController mStatusBarIconController;
    private ViewGroup mStatusIconsView;
    private final OplusMobileIconsInteractor oplusMobileIconsInteractor;
    private final OplusSeparateNotificationAndQSState separateNotificationAndQSState;
    private final ShadeExpansionStateManager shadeExpansionStateManager;
    private final View view;

    /* JADX INFO: compiled from: go/retraceme fd74bf9514dd0739b92d67a6d48a51eb10aaf8df760d83496b8dcfcf83e5f698 */
    public final class Companion {
    }

    /* JADX INFO: renamed from: com.oplus.systemui.qs.OplusQuickStatusBarHeaderController$onInit$1, reason: invalid class name */
    /* JADX INFO: compiled from: go/retraceme fd74bf9514dd0739b92d67a6d48a51eb10aaf8df760d83496b8dcfcf83e5f698 */
    public final class AnonymousClass1 implements ViewController.OnConfigurationChangedListener, ShadeCarrierGroupController.OnSingleCarrierChangedListener {
        public /* synthetic */ OplusQuickStatusBarHeaderController this$0;

        public void onConfigurationChange(Configuration configuration) {
            this.this$0.updateResources$6();
        }

        public void onSingleCarrierChanged(boolean z) {
            ((ViewController) this.this$0).mView.setIsSingleCarrier(z);
        }
    }

    /* JADX INFO: renamed from: com.oplus.systemui.qs.OplusQuickStatusBarHeaderController$onInit$2, reason: invalid class name */
    /* JADX INFO: compiled from: go/retraceme fd74bf9514dd0739b92d67a6d48a51eb10aaf8df760d83496b8dcfcf83e5f698 */
    final class AnonymousClass2 extends SuspendLambda implements Function3 {
        private /* synthetic */ Object L$0;
        int label;

        /* JADX INFO: renamed from: com.oplus.systemui.qs.OplusQuickStatusBarHeaderController$onInit$2$1, reason: invalid class name */
        /* JADX INFO: compiled from: go/retraceme fd74bf9514dd0739b92d67a6d48a51eb10aaf8df760d83496b8dcfcf83e5f698 */
        final class AnonymousClass1 extends SuspendLambda implements Function2 {
            private /* synthetic */ Object L$0;
            int label;
            final /* synthetic */ OplusQuickStatusBarHeaderController this$0;

            /* JADX INFO: renamed from: com.oplus.systemui.qs.OplusQuickStatusBarHeaderController$onInit$2$1$1, reason: invalid class name and collision with other inner class name */
            /* JADX INFO: compiled from: go/retraceme fd74bf9514dd0739b92d67a6d48a51eb10aaf8df760d83496b8dcfcf83e5f698 */
            final class C00661 extends SuspendLambda implements Function2 {
                int label;
                final /* synthetic */ OplusQuickStatusBarHeaderController this$0;

                /* JADX INFO: renamed from: com.oplus.systemui.qs.OplusQuickStatusBarHeaderController$onInit$2$1$1$1, reason: invalid class name and collision with other inner class name */
                /* JADX INFO: compiled from: go/retraceme fd74bf9514dd0739b92d67a6d48a51eb10aaf8df760d83496b8dcfcf83e5f698 */
                public final class C00671 implements FlowCollector {
                    public /* synthetic */ OplusQuickStatusBarHeaderController this$0;

                    public final Object emit(Object obj, Continuation continuation) {
                        boolean zBooleanValue = ((Boolean) obj).booleanValue();
                        Object obj2 = ((ViewController) this.this$0).mView;
                        OplusQuickStatusBarHeader oplusQuickStatusBarHeader = obj2 instanceof OplusQuickStatusBarHeader ? (OplusQuickStatusBarHeader) obj2 : null;
                        if (oplusQuickStatusBarHeader != null) {
                            oplusQuickStatusBarHeader.setDualSim(zBooleanValue);
                        }
                        return Unit.INSTANCE;
                    }
                }

                /* JADX WARN: 'super' call moved to the top of the method (can break code semantics) */
                public C00661(OplusQuickStatusBarHeaderController oplusQuickStatusBarHeaderController, Continuation continuation) {
                    super(2, continuation);
                    this.this$0 = oplusQuickStatusBarHeaderController;
                }

                public final Continuation create(Object obj, Continuation continuation) {
                    return new C00661(this.this$0, continuation);
                }

                public final Object invoke(Object obj, Object obj2) {
                    return create((CoroutineScope) obj, (Continuation) obj2).invokeSuspend(Unit.INSTANCE);
                }

                public final Object invokeSuspend(Object obj) {
                    Object coroutine_suspended = IntrinsicsKt__IntrinsicsKt.getCOROUTINE_SUSPENDED();
                    int i = this.label;
                    if (i == 0) {
                        ResultKt.throwOnFailure(obj);
                        StateFlow stateFlowIsDualSim = this.this$0.oplusMobileIconsInteractor.isDualSim();
                        OplusQuickStatusBarHeaderController oplusQuickStatusBarHeaderController = this.this$0;
                        C00671 c00671 = new C00671();
                        c00671.this$0 = oplusQuickStatusBarHeaderController;
                        VarHandle.storeStoreFence();
                        this.label = 1;
                        if (stateFlowIsDualSim.collect(c00671, this) == coroutine_suspended) {
                            return coroutine_suspended;
                        }
                    } else {
                        if (i != 1) {
                            Path$.ExternalSyntheticBUOutline0.m$1("call to 'resume' before 'invoke' with coroutine");
                            return null;
                        }
                        ResultKt.throwOnFailure(obj);
                    }
                    Regex$.ExternalSyntheticBUOutline0.m$1();
                    return null;
                }
            }

            /* JADX WARN: 'super' call moved to the top of the method (can break code semantics) */
            public AnonymousClass1(OplusQuickStatusBarHeaderController oplusQuickStatusBarHeaderController, Continuation continuation) {
                super(2, continuation);
                this.this$0 = oplusQuickStatusBarHeaderController;
            }

            public final Continuation create(Object obj, Continuation continuation) {
                AnonymousClass1 anonymousClass1 = new AnonymousClass1(this.this$0, continuation);
                anonymousClass1.L$0 = obj;
                return anonymousClass1;
            }

            public final Object invoke(Object obj, Object obj2) {
                return create((CoroutineScope) obj, (Continuation) obj2).invokeSuspend(Unit.INSTANCE);
            }

            public final Object invokeSuspend(Object obj) {
                CoroutineScope coroutineScope = (CoroutineScope) this.L$0;
                IntrinsicsKt__IntrinsicsKt.getCOROUTINE_SUSPENDED();
                if (this.label != 0) {
                    Path$.ExternalSyntheticBUOutline0.m$1("call to 'resume' before 'invoke' with coroutine");
                    return null;
                }
                ResultKt.throwOnFailure(obj);
                BuildersKt.launch$default(coroutineScope, (CoroutineContext) null, (CoroutineStart) null, new C00661(this.this$0, null), 3);
                return Unit.INSTANCE;
            }
        }

        public AnonymousClass2(Continuation continuation) {
            super(3, continuation);
        }

        public final Object invoke(Object obj, Object obj2, Object obj3) {
            AnonymousClass2 anonymousClass2 = OplusQuickStatusBarHeaderController.this.new AnonymousClass2((Continuation) obj3);
            anonymousClass2.L$0 = (LifecycleOwner) obj;
            return anonymousClass2.invokeSuspend(Unit.INSTANCE);
        }

        public final Object invokeSuspend(Object obj) {
            LifecycleOwner lifecycleOwner = (LifecycleOwner) this.L$0;
            Object coroutine_suspended = IntrinsicsKt__IntrinsicsKt.getCOROUTINE_SUSPENDED();
            int i = this.label;
            if (i == 0) {
                ResultKt.throwOnFailure(obj);
                Lifecycle.State state = Lifecycle.State.CREATED;
                AnonymousClass1 anonymousClass1 = new AnonymousClass1(OplusQuickStatusBarHeaderController.this, null);
                this.L$0 = SpillingKt.nullOutSpilledVariable(lifecycleOwner);
                this.label = 1;
                if (RepeatOnLifecycleKt.repeatOnLifecycle(lifecycleOwner, state, anonymousClass1, this) == coroutine_suspended) {
                    return coroutine_suspended;
                }
            } else {
                if (i != 1) {
                    Path$.ExternalSyntheticBUOutline0.m$1("call to 'resume' before 'invoke' with coroutine");
                    return null;
                }
                ResultKt.throwOnFailure(obj);
            }
            return Unit.INSTANCE;
        }
    }

    /* JADX INFO: renamed from: com.oplus.systemui.qs.OplusQuickStatusBarHeaderController$updateColor$1, reason: invalid class name and case insensitive filesystem */
    /* JADX INFO: compiled from: go/retraceme fd74bf9514dd0739b92d67a6d48a51eb10aaf8df760d83496b8dcfcf83e5f698 */
    public final class RunnableC02491 implements Runnable {
        public /* synthetic */ OplusQuickStatusBarHeaderController this$0;

        @Override // java.lang.Runnable
        public final void run() {
            OplusQuickStatusBarHeaderController oplusQuickStatusBarHeaderController = this.this$0;
            Object obj = ((ViewController) oplusQuickStatusBarHeaderController).mView;
            OplusQuickStatusBarHeader oplusQuickStatusBarHeader = obj instanceof OplusQuickStatusBarHeader ? (OplusQuickStatusBarHeader) obj : null;
            if (oplusQuickStatusBarHeader != null) {
                oplusQuickStatusBarHeader.applyIconDarkness(oplusQuickStatusBarHeaderController.mIconManager);
            }
        }
    }

    public OplusQuickStatusBarHeaderController(BaseQuickStatusBarHeader baseQuickStatusBarHeader, HeaderPrivacyIconsControllerFactory headerPrivacyIconsControllerFactory, OngoingPrivacyChip ongoingPrivacyChip, StatusBarIconController statusBarIconController, ShadeCarrierGroupController.Builder builder, FeatureFlags featureFlags, StatBatteryMeterViewController statBatteryMeterViewController, PerDisplayRepository perDisplayRepository, ShadeExpansionStateManager shadeExpansionStateManager, TintedIconManager.Factory factory, OplusSeparateNotificationAndQSState oplusSeparateNotificationAndQSState, OplusMobileIconsInteractor oplusMobileIconsInteractor) {
        super(baseQuickStatusBarHeader);
        this.mStatusBarIconController = statusBarIconController;
        this.mFeatureFlags = featureFlags;
        this.mBatteryMeterViewController = statBatteryMeterViewController;
        this.mPerDisplaySubcomponentRepo = perDisplayRepository;
        this.shadeExpansionStateManager = shadeExpansionStateManager;
        this.separateNotificationAndQSState = oplusSeparateNotificationAndQSState;
        this.oplusMobileIconsInteractor = oplusMobileIconsInteractor;
        OplusQuickStatusBarHeaderController$mShadeExpansionListener$1 oplusQuickStatusBarHeaderController$mShadeExpansionListener$1 = new OplusQuickStatusBarHeaderController$mShadeExpansionListener$1();
        oplusQuickStatusBarHeaderController$mShadeExpansionListener$1.this$0 = this;
        VarHandle.storeStoreFence();
        this.mShadeExpansionListener = oplusQuickStatusBarHeaderController$mShadeExpansionListener$1;
        boolean zBooleanValue = ((Boolean) oplusSeparateNotificationAndQSState.getEnableSeparateNotificationAndQS().getValue()).booleanValue();
        this.mIsSeparateNotificationAndQs = zBooleanValue;
        this.mQSCarrierGroupController = builder.setShadeCarrierGroup(baseQuickStatusBarHeader != null ? (ShadeCarrierGroup) baseQuickStatusBarHeader.findViewById(2131362491) : null).build();
        builder.setShadeCarrierGroup((ShadeCarrierGroup) null);
        this.mStatusIconsView = (ViewGroup) baseQuickStatusBarHeader.findViewById(2131364818);
        StatusIconContainer statusIconContainer = (StatusIconContainer) baseQuickStatusBarHeader.requireViewById(2131365358);
        this.mIconContainer = statusIconContainer;
        this.mIconManager = factory.create(statusIconContainer, StatusBarLocation.QS);
        this.mClockContainer = (ViewGroup) baseQuickStatusBarHeader.requireViewById(2131364721);
        statBatteryMeterViewController.ignoreTunerUpdates();
        if (!zBooleanValue) {
            this.mPrivacyIconsController = headerPrivacyIconsControllerFactory.createHeaderPrivacyIconsController(ongoingPrivacyChip, statusIconContainer);
        }
        this.mQsHeaderCarrierText = (OplusSecondCarrierText) baseQuickStatusBarHeader.findViewById(2131364716);
        this.view = baseQuickStatusBarHeader;
    }

    public final void addIgnoredSlot(String str) {
        this.mIconContainer.addIgnoredSlot(str);
    }

    public void dump(PrintWriter printWriter, String[] strArr) {
        SpringOverScroller springOverScroller;
        printWriter.println("QuickStatusBarHeaderController: ");
        OplusLockIconView$.ExternalSyntheticOutline0.m(printWriter, "    mListening: ", this.mListening);
        BiometricMessageDeferral$.ExternalSyntheticOutline0.m("    mExpansion: ", this.mExpansion, printWriter);
        OplusLockIconView$.ExternalSyntheticOutline0.m(printWriter, "    mIsRemoved: ", this.mIsRemoved);
        printWriter.println("    mExpandTopGap: " + this.mExpandTopGap);
        printWriter.println("    bottomGap: 0.0");
        BiometricMessageDeferral$.ExternalSyntheticOutline0.m("    translationY: ", ((ViewController) this).mView.getTranslationY(), printWriter);
        Object obj = ((ViewController) this).mView;
        Integer numValueOf = null;
        OplusQuickStatusBarHeader oplusQuickStatusBarHeader = obj instanceof OplusQuickStatusBarHeader ? (OplusQuickStatusBarHeader) obj : null;
        if (oplusQuickStatusBarHeader != null && (springOverScroller = oplusQuickStatusBarHeader.getSpringOverScroller()) != null) {
            numValueOf = Integer.valueOf(springOverScroller.getCOUICurrY());
        }
        printWriter.println("    couiCurrY: " + numValueOf);
        ((ViewController) this).mView.dump(printWriter);
    }

    public /* bridge */ /* synthetic */ View getAnimatorView() {
        return super.getAnimatorView();
    }

    public int getCollapseHeight() {
        BaseQuickStatusBarHeader baseQuickStatusBarHeader = ((ViewController) this).mView;
        if (baseQuickStatusBarHeader != null) {
            return baseQuickStatusBarHeader.getHeight();
        }
        return 0;
    }

    public /* bridge */ /* synthetic */ int getExpandHeight() {
        return super/*com.android.systemui.qs.ExpandableNode*/.getExpandHeight();
    }

    public /* bridge */ /* synthetic */ int getFloorCount() {
        return super.getFloorCount();
    }

    public final ViewGroup getMClockContainer() {
        return this.mClockContainer;
    }

    public final OplusSecondCarrierText getMQsHeaderCarrierText() {
        return this.mQsHeaderCarrierText;
    }

    public final ViewGroup getMStatusIconsView() {
        return this.mStatusIconsView;
    }

    public final float getTopGap(float f) {
        return this.mExpandTopGap * f;
    }

    public View getView() {
        return this.view;
    }

    public final boolean isRemoved() {
        return this.mIsRemoved;
    }

    public final void onChipVisibilityRefreshed(boolean z) {
        BaseQuickStatusBarHeader baseQuickStatusBarHeader = ((ViewController) this).mView;
        if (baseQuickStatusBarHeader != null) {
            baseQuickStatusBarHeader.setChipVisibility(z);
        }
    }

    public final void onInit() {
        this.mBatteryMeterViewController.init();
        updateResources$6();
        Object obj = ((ViewController) this).mView;
        if (obj instanceof OplusQuickStatusBarHeader) {
            AnonymousClass1 anonymousClass1 = new AnonymousClass1();
            anonymousClass1.this$0 = this;
            VarHandle.storeStoreFence();
            ((OplusQuickStatusBarHeader) obj).setConfigurationChangeListener(anonymousClass1);
            ((OplusQuickStatusBarHeader) ((ViewController) this).mView).setDualSim(((Boolean) this.oplusMobileIconsInteractor.isDualSim().getValue()).booleanValue());
        }
        RepeatWhenAttachedKt.repeatWhenAttached$default(((ViewController) this).mView, (CoroutineContext) null, new AnonymousClass2(null), 3);
    }

    public final void onQsColorStateChanged(int i) {
        LogUtil.i$default(LogUtil.TAG_QS, TAG, ListPopupWindow$.ExternalSyntheticOutline0.m(i, "onQsColorStateChanged updateTextColor qsColorState: "), null, 8);
        updateColor();
        OplusSecondCarrierText oplusSecondCarrierText = this.mQsHeaderCarrierText;
        if (oplusSecondCarrierText != null) {
            oplusSecondCarrierText.updateTextColor();
        }
    }

    public final void onViewAttached() {
        LogUtil.i$default(LogUtil.TAG_QS, TAG, "onViewAttached", null, 8);
        if (!this.mPanelListenersRegistered) {
            ShadeExpansionChangeEvent shadeExpansionChangeEventAddExpansionListener = this.shadeExpansionStateManager.addExpansionListener(this.mShadeExpansionListener);
            this.mPanelTracking = shadeExpansionChangeEventAddExpansionListener.getTracking();
            this.mPanelFraction = shadeExpansionChangeEventAddExpansionListener.getFraction();
            this.mPanelExpanded = shadeExpansionChangeEventAddExpansionListener.getExpanded();
            this.mPanelListenersRegistered = true;
        }
        HeaderPrivacyIconsController headerPrivacyIconsController = this.mPrivacyIconsController;
        if (headerPrivacyIconsController != null) {
            headerPrivacyIconsController.onParentVisible();
        }
        HeaderPrivacyIconsController headerPrivacyIconsController2 = this.mPrivacyIconsController;
        if (headerPrivacyIconsController2 != null) {
            headerPrivacyIconsController2.setChipVisibilityListener(this);
        }
        this.mIconContainer.setShouldRestrictIcons(false);
        ArrayList arrayList = new ArrayList();
        CollectionsKt__MutableCollectionsKt.addAll(arrayList, getResources().getStringArray(2130903157));
        this.mIconManager.setBlockList(arrayList);
        this.mStatusBarIconController.addIconGroup(this.mIconManager);
        BaseQuickStatusBarHeader baseQuickStatusBarHeader = ((ViewController) this).mView;
        baseQuickStatusBarHeader.getClass();
        baseQuickStatusBarHeader.setIsSingleCarrier(this.mQSCarrierGroupController.mIsSingleCarrier);
        ShadeCarrierGroupController shadeCarrierGroupController = this.mQSCarrierGroupController;
        AnonymousClass1 anonymousClass1 = new AnonymousClass1();
        anonymousClass1.this$0 = this;
        VarHandle.storeStoreFence();
        shadeCarrierGroupController.setOnSingleCarrierChangedListener(anonymousClass1);
        List listOf = List.of(getResources().getString(R.string.write_fail_reason_cancelled));
        BaseQuickStatusBarHeader baseQuickStatusBarHeader2 = ((ViewController) this).mView;
        TintedIconManager tintedIconManager = this.mIconManager;
        listOf.getClass();
        baseQuickStatusBarHeader2.onAttach(tintedIconManager, listOf, ((SystemUIDisplaySubcomponent) this.mPerDisplaySubcomponentRepo.getOrDefault(getContext().getDisplayId())).getStatusBarContentInsetsProvider(), this.mFeatureFlags.isEnabled(Flags.COMBINED_QS_HEADERS), ((Boolean) this.separateNotificationAndQSState.getEnableSeparateNotificationAndQS().getValue()).booleanValue());
        if (((Boolean) this.separateNotificationAndQSState.getEnableSeparateNotificationAndQS().getValue()).booleanValue()) {
            ViewGroup viewGroup = this.mStatusIconsView;
            ViewGroup.LayoutParams layoutParams = viewGroup != null ? viewGroup.getLayoutParams() : null;
            LinearLayout.LayoutParams layoutParams2 = layoutParams instanceof LinearLayout.LayoutParams ? (LinearLayout.LayoutParams) layoutParams : null;
            if (layoutParams2 != null) {
                layoutParams2.setMarginStart(((ViewController) this).mView.getResources().getDimensionPixelSize(2131172240));
                layoutParams2.setMarginEnd(0);
                ViewGroup viewGroup2 = this.mStatusIconsView;
                if (viewGroup2 != null) {
                    viewGroup2.setLayoutParams(layoutParams2);
                }
            }
            BaseQuickStatusBarHeader baseQuickStatusBarHeader3 = ((ViewController) this).mView;
            baseQuickStatusBarHeader3.setPadding(baseQuickStatusBarHeader3.getPaddingLeft(), 0, ((ViewController) this).mView.getPaddingRight(), ((ViewController) this).mView.getPaddingBottom());
            AppCompatTextView appCompatTextView = this.mQsHeaderCarrierText;
            ViewGroup.LayoutParams layoutParams3 = appCompatTextView != null ? appCompatTextView.getLayoutParams() : null;
            ViewGroup.MarginLayoutParams marginLayoutParams = layoutParams3 instanceof ViewGroup.MarginLayoutParams ? (ViewGroup.MarginLayoutParams) layoutParams3 : null;
            if (marginLayoutParams != null) {
                marginLayoutParams.setMarginStart(0);
            }
        }
        OplusSecondCarrierText oplusSecondCarrierText = this.mQsHeaderCarrierText;
        if (oplusSecondCarrierText != null) {
            oplusSecondCarrierText.updateTextColor();
        }
        updateCarrierMarqueeByPanelState();
    }

    public final void onViewDetached() {
        LogUtil.i$default(LogUtil.TAG_QS, TAG, "onViewDetached", null, 8);
        this.mLastCarrierMarqueeAllowedByPanel = false;
        OplusSecondCarrierText oplusSecondCarrierText = this.mQsHeaderCarrierText;
        if (oplusSecondCarrierText != null) {
            oplusSecondCarrierText.setMarqueeAllowedByPanel(false);
        }
        HeaderPrivacyIconsController headerPrivacyIconsController = this.mPrivacyIconsController;
        if (headerPrivacyIconsController != null) {
            headerPrivacyIconsController.onParentInvisible();
        }
        this.mStatusBarIconController.removeIconGroup(this.mIconManager);
        this.mQSCarrierGroupController.setOnSingleCarrierChangedListener((ShadeCarrierGroupController.OnSingleCarrierChangedListener) null);
        if (this.mPanelListenersRegistered) {
            this.shadeExpansionStateManager.removeExpansionListener(this.mShadeExpansionListener);
            this.mPanelListenersRegistered = false;
        }
        setListening(false);
    }

    public void setContainer(ExpandableNode.OnStateListener onStateListener) {
        this.mContainer = onStateListener;
    }

    public void setCustomizing(boolean z) {
        Object obj = ((ViewController) this).mView;
        if (obj instanceof OplusQuickStatusBarHeader) {
            ((OplusQuickStatusBarHeader) obj).setCustomizing(z);
        }
    }

    public void setExpanded(boolean z) {
        this.mExpanded = z;
        updateCarrierMarqueeByPanelState();
    }

    public void setExpansion(float f) {
        this.mExpansion = f;
        Object obj = ((ViewController) this).mView;
        if (obj instanceof OplusQuickStatusBarHeader) {
            ((OplusQuickStatusBarHeader) obj).setExpansion(f);
        }
        updateCarrierMarqueeByPanelState();
    }

    public void setListening(boolean z) {
        this.mQSCarrierGroupController.setListening(z);
        OplusSecondCarrierText oplusSecondCarrierText = this.mQsHeaderCarrierText;
        if (oplusSecondCarrierText != null) {
            oplusSecondCarrierText.setListening(z);
        }
        if (z == this.mListening) {
            return;
        }
        this.mListening = z;
        HeaderPrivacyIconsController headerPrivacyIconsController = this.mPrivacyIconsController;
        if (z) {
            if (headerPrivacyIconsController != null) {
                headerPrivacyIconsController.startListening();
            }
        } else if (headerPrivacyIconsController != null) {
            headerPrivacyIconsController.stopListening();
        }
        updateCarrierMarqueeByPanelState();
    }

    public float setTranslationY(float f, int i, Function1 function1) {
        return 0.0f;
    }

    public final boolean shouldHideInSplitMode() {
        return true;
    }

    public final void updateCarrierMarqueeByPanelState() {
        boolean z;
        AppCompatTextView appCompatTextView;
        boolean z2 = false;
        boolean z3 = this.mListening && !this.mPanelTracking && this.mPanelFraction == 1.0f;
        if (!this.mIsSeparateNotificationAndQs ? !(!z3 || ((!(z = this.mExpanded) || this.mExpansion != 1.0f) && (z || this.mExpansion != 0.0f))) : !(!z3 || !this.mPanelExpanded)) {
            z2 = true;
        }
        if (z2 && z2 == this.mLastCarrierMarqueeAllowedByPanel) {
            return;
        }
        if (z2 || ((appCompatTextView = this.mQsHeaderCarrierText) != null && appCompatTextView.isSelected())) {
            this.mLastCarrierMarqueeAllowedByPanel = z2;
            OplusSecondCarrierText oplusSecondCarrierText = this.mQsHeaderCarrierText;
            if (oplusSecondCarrierText != null) {
                oplusSecondCarrierText.setMarqueeAllowedByPanel(z2);
            }
        }
    }

    public final void updateColor() {
        RunnableC02491 runnableC02491 = new RunnableC02491();
        runnableC02491.this$0 = this;
        VarHandle.storeStoreFence();
        SystemUIThreadUtil.runOnUiThread(runnableC02491);
    }

    public final void updateResources$6() {
        BaseQuickStatusBarHeader baseQuickStatusBarHeader = ((ViewController) this).mView;
        baseQuickStatusBarHeader.getClass();
        Resources resources = baseQuickStatusBarHeader.getResources();
        float dimension = resources.getDimension(2131171921);
        this.mExpandTopGap = dimension;
        if (OplusStatusBarUtils.isCenterHole()) {
            float dimension2 = resources.getDimension(2131172238) / 2.0f;
            float screenHoleParam = OplusStatusBarUtils.getScreenHoleParam(((ViewController) this).mView.getContext(), 3);
            if (screenHoleParam > dimension + dimension2) {
                this.mExpandTopGap = screenHoleParam - dimension2;
            }
        }
        BaseQuickStatusBarHeader baseQuickStatusBarHeader2 = ((ViewController) this).mView;
        baseQuickStatusBarHeader2.getClass();
        boolean zShouldUseSplitNotificationShade = LargeScreenUtils.shouldUseSplitNotificationShade(baseQuickStatusBarHeader2.getResources());
        if (this.mIsRemoved == zShouldUseSplitNotificationShade) {
            return;
        }
        this.mIsRemoved = zShouldUseSplitNotificationShade;
        ExpandableNode.OnStateListener onStateListener = this.mContainer;
        if (onStateListener != null) {
            ((OplusQSContainerImplController) onStateListener).updatePosition$2();
        }
    }

    public final void updateViewState(float f, float f2) {
        if (TemporarilyInfoManager.Companion.getInstance().isShowing) {
            return;
        }
        BaseQuickStatusBarHeader baseQuickStatusBarHeader = ((ViewController) this).mView;
        ViewGroup viewGroup = baseQuickStatusBarHeader != null ? (ViewGroup) baseQuickStatusBarHeader.findViewById(2131364818) : null;
        if (viewGroup != null) {
            viewGroup.setTransitionAlpha(f);
        }
        BaseQuickStatusBarHeader baseQuickStatusBarHeader2 = ((ViewController) this).mView;
        ViewGroup viewGroup2 = baseQuickStatusBarHeader2 != null ? (ViewGroup) baseQuickStatusBarHeader2.findViewById(2131364721) : null;
        if (viewGroup2 == null || viewGroup2.getChildCount() <= 0) {
            return;
        }
        int childCount = viewGroup2.getChildCount();
        for (int i = 0; i < childCount; i++) {
            viewGroup2.getChildAt(i).setTransitionAlpha(f);
        }
    }

    public final float getTopGap() {
        return this.mExpandTopGap;
    }
}
