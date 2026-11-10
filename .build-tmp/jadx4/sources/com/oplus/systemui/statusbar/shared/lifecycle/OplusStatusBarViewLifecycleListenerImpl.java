package com.oplus.systemui.statusbar.shared.lifecycle;

import android.graphics.Rect;
import android.util.Log;
import android.view.ViewGroup;
import androidx.lifecycle.LifecycleOwner;
import com.android.keyguard.AlphaOptimizedLinearLayout;
import com.android.systemui.plugins.DarkIconDispatcher;
import com.android.systemui.shade.domain.interactor.ShadeAnimationInteractor;
import com.android.systemui.statusbar.AlphaOptimizedFrameLayout;
import com.android.systemui.statusbar.StatusBarIconView;
import com.android.systemui.statusbar.core.StatusBarInitializer;
import com.android.systemui.statusbar.phone.NotificationIconContainer;
import com.android.systemui.statusbar.phone.PhoneStatusBarView;
import com.android.systemui.statusbar.phone.StatusBarLocation;
import com.android.systemui.statusbar.phone.StatusIconContainer;
import com.android.systemui.statusbar.phone.fragment.dagger.HomeStatusBarComponent;
import com.android.systemui.statusbar.policy.KeyguardStateController;
import com.oplus.systemui.dagger.OplusHomeStatusBarComponent;
import com.oplus.systemui.seamless.FakeBinder;
import com.oplus.systemui.seamless.FakeType;
import com.oplus.systemui.seamless.OplusFakeViewProvider;
import com.oplus.systemui.seamless.widget.StatusBarFakeFrame;
import com.oplus.systemui.statusbar.SysUiStateControllerImpl;
import com.oplus.systemui.statusbar.phone.OplusIconController;
import com.oplus.systemui.statusbar.phone.StartSideContainerController;
import com.oplus.systemui.statusbar.phone.dynamic.DynamicIconAnimationController;
import com.oplus.systemui.statusbar.phone.dynamic.pipeline.ui.binder.DynamicIconGroupViewBinder;
import com.oplus.systemui.statusbar.phone.dynamic.pipeline.ui.viewmodel.DynamicIconSetViewModel;
import com.oplus.systemui.statusbar.pipeline.battery.ui.view.StatBatteryMeterView;
import com.oplus.systemui.statusbar.seeding.FluidReplaceNotificationManager;
import com.oplus.systemui.statusbar.seeding.HiddenIconDispatcher;
import com.oplus.systemui.statusbar.seeding.ILocationCalculatorKt;
import com.oplus.systemui.statusbar.seeding.IScaledAncestorView;
import com.oplus.systemui.statusbar.seeding.OplusSeedlingCardController;
import com.oplus.systemui.statusbar.seeding.anim.StatusBarCoordAnimationController;
import com.oplus.systemui.statusbar.widget.EndSideContentLayout;
import com.oplus.systemui.statusbar.widget.HiddenReceiverFrameLayout;
import com.oplus.systemui.statusbar.widget.NotificationBadgeView;
import com.oplus.systemui.statusbar.widget.StartSideExceptHeadsUpLayout;
import com.oplus.systemui.statusbar.widget.SysuiLifecycle;
import com.oplusos.systemui.common.util.LogUtil;
import dagger.Lazy;
import java.lang.invoke.VarHandle;
import java.util.ArrayList;
import java.util.function.Function;
import kotlin.Pair;
import kotlin.jvm.internal.Ref;

/* JADX INFO: compiled from: go/retraceme fd74bf9514dd0739b92d67a6d48a51eb10aaf8df760d83496b8dcfcf83e5f698 */
/* JADX INFO: loaded from: /home/starlight/Projects/Starlight Ports/.build-tmp/classes4.dex */
public final class OplusStatusBarViewLifecycleListenerImpl implements StatusBarInitializer.StatusBarViewLifecycleListener {
    public static final Companion Companion = new Companion();
    private static final String TAG = "OplusStatusBarViewLifecycleListenerImpl";
    private final DynamicIconSetViewModel dynamicIconsViewModel;
    private final Lazy fakeBinder;
    private final FluidReplaceNotificationManager fluidReplaceNotificationManager;
    private final HiddenIconDispatcher hiddenIconDispatcher;
    private final KeyguardStateController keyguardStateController;
    private final Lazy oplusFakeViewProvider;
    private final OplusIconController oplusIconController;
    private final OplusSeedlingCardController oplusSeedlingCardController;
    private final Lazy separateNotificationAndQSEx;
    private final ShadeAnimationInteractor shadeAnimationInteractor;
    private final StatusBarCoordAnimationController statusBarCoordAnimationController;
    private final Lazy statusBarDependencyEx;
    private final SysUiStateControllerImpl sysUiStateControllerImpl;

    /* JADX INFO: compiled from: go/retraceme fd74bf9514dd0739b92d67a6d48a51eb10aaf8df760d83496b8dcfcf83e5f698 */
    public final class Companion {
    }

    /* JADX INFO: renamed from: com.oplus.systemui.statusbar.shared.lifecycle.OplusStatusBarViewLifecycleListenerImpl$onStatusBarViewInitialized$3, reason: invalid class name */
    /* JADX INFO: compiled from: go/retraceme fd74bf9514dd0739b92d67a6d48a51eb10aaf8df760d83496b8dcfcf83e5f698 */
    public final class AnonymousClass3 implements Function {
        public /* synthetic */ OplusStatusBarViewLifecycleListenerImpl$onStatusBarViewInitialized$hiddenReceiver$1 $hiddenReceiver;
        public /* synthetic */ Ref.BooleanRef $shouldScale;

        @Override // java.util.function.Function
        public final Object apply(Object obj) {
            Pair<Integer, Integer> locationByDefault;
            float scaleFraction;
            Boolean bool = Boolean.TRUE;
            NotificationIconContainer notificationIconContainer = (NotificationIconContainer) obj;
            ArrayList arrayList = this.$hiddenReceiver.hiddenAreas;
            int size = arrayList.size();
            int i = 0;
            while (i < size) {
                Object obj2 = arrayList.get(i);
                i++;
                Rect rect = (Rect) obj2;
                if (rect.right > rect.left) {
                    notificationIconContainer.getClass();
                    IScaledAncestorView scaledAncestorViewOrNull = ILocationCalculatorKt.getScaledAncestorViewOrNull(notificationIconContainer);
                    if (scaledAncestorViewOrNull != null) {
                        scaleFraction = scaledAncestorViewOrNull.getScaleFraction(this.$shouldScale.element);
                        locationByDefault = ILocationCalculatorKt.getLocationByScaledAncestorView(notificationIconContainer, scaledAncestorViewOrNull.getScaledAncestorView(), scaleFraction, scaledAncestorViewOrNull.getPivotXDirection());
                    } else {
                        locationByDefault = ILocationCalculatorKt.getLocationByDefault(notificationIconContainer);
                        scaleFraction = 1.0f;
                    }
                    int childCount = notificationIconContainer.getChildCount();
                    for (int i2 = 0; i2 < childCount; i2++) {
                        StatusBarIconView childAt = notificationIconContainer.getChildAt(i2);
                        if (childAt instanceof StatusBarIconView) {
                            StatusBarIconView statusBarIconView = childAt;
                            NotificationIconContainer.IconState iconState = (NotificationIconContainer.IconState) notificationIconContainer.mIconStates.get(statusBarIconView);
                            if (iconState.visibleState != 2) {
                                float xTranslation = (iconState.getXTranslation() * scaleFraction) + ((Number) locationByDefault.getFirst()).floatValue();
                                OplusStatusBarViewLifecycleListenerImpl$onStatusBarViewInitialized$hiddenReceiver$1 oplusStatusBarViewLifecycleListenerImpl$onStatusBarViewInitialized$hiddenReceiver$1 = this.$hiddenReceiver;
                                int i3 = (int) xTranslation;
                                int width = (int) ((statusBarIconView.getWidth() * scaleFraction) + xTranslation);
                                ArrayList arrayList2 = oplusStatusBarViewLifecycleListenerImpl$onStatusBarViewInitialized$hiddenReceiver$1.hiddenAreas;
                                int size2 = arrayList2.size();
                                int i4 = 0;
                                while (i4 < size2) {
                                    Object obj3 = arrayList2.get(i4);
                                    i4++;
                                    Rect rect2 = (Rect) obj3;
                                    if (rect2.left < width && i3 < rect2.right) {
                                        iconState.setAlpha(0.0f, "hiddenReceiver");
                                        break;
                                    }
                                }
                            }
                        }
                    }
                    break;
                }
            }
            return bool;
        }
    }

    public OplusStatusBarViewLifecycleListenerImpl(DynamicIconSetViewModel dynamicIconSetViewModel, OplusIconController oplusIconController, HiddenIconDispatcher hiddenIconDispatcher, OplusSeedlingCardController oplusSeedlingCardController, FluidReplaceNotificationManager fluidReplaceNotificationManager, StatusBarCoordAnimationController statusBarCoordAnimationController, Lazy lazy, Lazy lazy2, Lazy lazy3, SysUiStateControllerImpl sysUiStateControllerImpl, ShadeAnimationInteractor shadeAnimationInteractor, KeyguardStateController keyguardStateController, Lazy lazy4) {
        this.dynamicIconsViewModel = dynamicIconSetViewModel;
        this.oplusIconController = oplusIconController;
        this.hiddenIconDispatcher = hiddenIconDispatcher;
        this.oplusSeedlingCardController = oplusSeedlingCardController;
        this.fluidReplaceNotificationManager = fluidReplaceNotificationManager;
        this.statusBarCoordAnimationController = statusBarCoordAnimationController;
        this.separateNotificationAndQSEx = lazy;
        this.oplusFakeViewProvider = lazy2;
        this.fakeBinder = lazy3;
        this.sysUiStateControllerImpl = sysUiStateControllerImpl;
        this.shadeAnimationInteractor = shadeAnimationInteractor;
        this.keyguardStateController = keyguardStateController;
        this.statusBarDependencyEx = lazy4;
    }

    public void onStatusBarViewDestroyed(HomeStatusBarComponent homeStatusBarComponent) {
    }

    /* JADX WARN: Type inference fix 'apply assigned field type' failed
    java.lang.UnsupportedOperationException: ArgType.getObject(), call class: class jadx.core.dex.instructions.args.ArgType$UnknownArg
    	at jadx.core.dex.instructions.args.ArgType.getObject(ArgType.java:596)
    	at jadx.core.dex.attributes.nodes.ClassTypeVarsAttr.getTypeVarsMapFor(ClassTypeVarsAttr.java:35)
    	at jadx.core.dex.nodes.utils.TypeUtils.replaceClassGenerics(TypeUtils.java:177)
    	at jadx.core.dex.visitors.typeinference.FixTypesVisitor.insertExplicitUseCast(FixTypesVisitor.java:397)
    	at jadx.core.dex.visitors.typeinference.FixTypesVisitor.tryFieldTypeWithNewCasts(FixTypesVisitor.java:359)
    	at jadx.core.dex.visitors.typeinference.FixTypesVisitor.applyFieldType(FixTypesVisitor.java:309)
    	at jadx.core.dex.visitors.typeinference.FixTypesVisitor.visit(FixTypesVisitor.java:94)
     */
    public void onStatusBarViewInitialized(HomeStatusBarComponent homeStatusBarComponent) {
        ArrayList<StatusBarFakeFrame> fakeViews;
        ArrayList<StatusBarFakeFrame> fakeViews2;
        StartSideContainerController startSideContainerController;
        if (homeStatusBarComponent.getDisplayId() == 0) {
            this.fluidReplaceNotificationManager.init();
            NotificationIconContainer notificationIconContainerRequireViewById = homeStatusBarComponent.getPhoneStatusBarView().requireViewById(2131364175);
            NotificationBadgeView notificationBadgeView = (NotificationBadgeView) homeStatusBarComponent.getPhoneStatusBarView().requireViewById(2131364200);
            boolean z = homeStatusBarComponent instanceof OplusHomeStatusBarComponent;
            OplusHomeStatusBarComponent oplusHomeStatusBarComponent = z ? (OplusHomeStatusBarComponent) homeStatusBarComponent : null;
            if (oplusHomeStatusBarComponent != null && (startSideContainerController = oplusHomeStatusBarComponent.getStartSideContainerController()) != null) {
                startSideContainerController.setNotificationIConContainer(notificationIconContainerRequireViewById);
            }
            Ref.BooleanRef booleanRef = new Ref.BooleanRef();
            OplusStatusBarViewLifecycleListenerImpl$onStatusBarViewInitialized$hiddenReceiver$1 oplusStatusBarViewLifecycleListenerImpl$onStatusBarViewInitialized$hiddenReceiver$1 = new OplusStatusBarViewLifecycleListenerImpl$onStatusBarViewInitialized$hiddenReceiver$1();
            oplusStatusBarViewLifecycleListenerImpl$onStatusBarViewInitialized$hiddenReceiver$1.$shouldScale = booleanRef;
            oplusStatusBarViewLifecycleListenerImpl$onStatusBarViewInitialized$hiddenReceiver$1.$notificationIconContainer = notificationIconContainerRequireViewById;
            oplusStatusBarViewLifecycleListenerImpl$onStatusBarViewInitialized$hiddenReceiver$1.hiddenAreas = new ArrayList();
            VarHandle.storeStoreFence();
            HiddenIconDispatcher hiddenIconDispatcher = this.hiddenIconDispatcher;
            StatusBarLocation statusBarLocation = StatusBarLocation.HOME;
            if (!hiddenIconDispatcher.isRegistered(statusBarLocation, oplusStatusBarViewLifecycleListenerImpl$onStatusBarViewInitialized$hiddenReceiver$1)) {
                if (LogUtil.getNormalDebug() && LogUtil.isTagValid(LogUtil.TAG_STATUSBAR)) {
                    Log.d("SystemUi--Statusbar", "OplusStatusBarViewLifecycleListenerImpl-->".concat("addReceiver: notificationBadgeView: " + notificationBadgeView));
                }
                this.hiddenIconDispatcher.addHiddenReceiver(statusBarLocation, oplusStatusBarViewLifecycleListenerImpl$onStatusBarViewInitialized$hiddenReceiver$1);
            }
            if (!this.hiddenIconDispatcher.isRegistered(statusBarLocation, notificationBadgeView)) {
                if (LogUtil.getNormalDebug() && LogUtil.isTagValid(LogUtil.TAG_STATUSBAR)) {
                    Log.d("SystemUi--Statusbar", "OplusStatusBarViewLifecycleListenerImpl-->".concat("addReceiver: hiddenReceiver: " + oplusStatusBarViewLifecycleListenerImpl$onStatusBarViewInitialized$hiddenReceiver$1));
                }
                this.hiddenIconDispatcher.addHiddenReceiver(statusBarLocation, notificationBadgeView);
            }
            AnonymousClass3 anonymousClass3 = new AnonymousClass3();
            anonymousClass3.$hiddenReceiver = oplusStatusBarViewLifecycleListenerImpl$onStatusBarViewInitialized$hiddenReceiver$1;
            anonymousClass3.$shouldScale = booleanRef;
            VarHandle.storeStoreFence();
            notificationIconContainerRequireViewById.setExtraViewStateProcessor(anonymousClass3);
            PhoneStatusBarView phoneStatusBarView = homeStatusBarComponent.getPhoneStatusBarView();
            StartSideExceptHeadsUpLayout startSideExceptHeadsUpLayout = (StartSideExceptHeadsUpLayout) phoneStatusBarView.findViewById(2131365373);
            int i = 0;
            if (startSideExceptHeadsUpLayout != null) {
                startSideExceptHeadsUpLayout.setScaleAnimateEnable(false);
                this.hiddenIconDispatcher.observe(SysuiLifecycle.viewAttachLifecycle(startSideExceptHeadsUpLayout), statusBarLocation, startSideExceptHeadsUpLayout);
                startSideExceptHeadsUpLayout.setScaleAnimateEnable(true);
            }
            AlphaOptimizedLinearLayout alphaOptimizedLinearLayout = (EndSideContentLayout) phoneStatusBarView.findViewById(2131365367);
            if (alphaOptimizedLinearLayout != null) {
                OplusStatusBarViewLifecycleListenerImpl$onStatusBarViewInitialized$4$2$1 oplusStatusBarViewLifecycleListenerImpl$onStatusBarViewInitialized$4$2$1 = new OplusStatusBarViewLifecycleListenerImpl$onStatusBarViewInitialized$4$2$1();
                oplusStatusBarViewLifecycleListenerImpl$onStatusBarViewInitialized$4$2$1.this$0 = this;
                VarHandle.storeStoreFence();
                alphaOptimizedLinearLayout.addScaleChangedListener(oplusStatusBarViewLifecycleListenerImpl$onStatusBarViewInitialized$4$2$1);
                alphaOptimizedLinearLayout.setScaleAnimateEnable(false);
                this.hiddenIconDispatcher.observe((LifecycleOwner) SysuiLifecycle.viewAttachLifecycle(alphaOptimizedLinearLayout), statusBarLocation, (HiddenIconDispatcher.HiddenReceiver) alphaOptimizedLinearLayout);
                alphaOptimizedLinearLayout.setScaleAnimateEnable(true);
            }
            DarkIconDispatcher darkIconDispatcher = homeStatusBarComponent.getDarkIconDispatcher();
            ViewGroup viewGroup = (ViewGroup) phoneStatusBarView.findViewById(2131362994);
            ViewGroup viewGroup2 = (ViewGroup) phoneStatusBarView.findViewById(2131365365);
            if (viewGroup != null && viewGroup2 != null) {
                DynamicIconAnimationController dynamicIconAnimationController = new DynamicIconAnimationController(viewGroup, viewGroup2, darkIconDispatcher);
                dynamicIconAnimationController.init();
                DynamicIconGroupViewBinder.INSTANCE.bind(viewGroup, this.dynamicIconsViewModel, this.oplusIconController, dynamicIconAnimationController);
            }
            StatusIconContainer statusIconContainerFindViewById = phoneStatusBarView.findViewById(2131365358);
            if (statusIconContainerFindViewById != null) {
                OplusStatusBarViewLifecycleListenerImpl$bindSICHiddenDispatcher$hiddenReceiver$1 oplusStatusBarViewLifecycleListenerImpl$bindSICHiddenDispatcher$hiddenReceiver$1 = new OplusStatusBarViewLifecycleListenerImpl$bindSICHiddenDispatcher$hiddenReceiver$1();
                oplusStatusBarViewLifecycleListenerImpl$bindSICHiddenDispatcher$hiddenReceiver$1.$statusIconContainer = statusIconContainerFindViewById;
                VarHandle.storeStoreFence();
                statusIconContainerFindViewById.setHiddenDispatcherRegisterLocation(statusBarLocation);
                this.hiddenIconDispatcher.observe(SysuiLifecycle.viewAttachLifecycle(statusIconContainerFindViewById), statusBarLocation, oplusStatusBarViewLifecycleListenerImpl$bindSICHiddenDispatcher$hiddenReceiver$1);
                oplusStatusBarViewLifecycleListenerImpl$bindSICHiddenDispatcher$hiddenReceiver$1.setAllowUpdate(true);
            }
            StatBatteryMeterView statBatteryMeterView = (StatBatteryMeterView) phoneStatusBarView.findViewById(2131362233);
            if (statBatteryMeterView != null) {
                this.hiddenIconDispatcher.addHiddenReceiver(statusBarLocation, statBatteryMeterView);
            }
            AlphaOptimizedFrameLayout alphaOptimizedFrameLayout = (HiddenReceiverFrameLayout) phoneStatusBarView.findViewById(2131364693);
            if (alphaOptimizedFrameLayout != null) {
                this.hiddenIconDispatcher.observe((LifecycleOwner) SysuiLifecycle.viewAttachLifecycle(alphaOptimizedFrameLayout), statusBarLocation, (HiddenIconDispatcher.HiddenReceiver) alphaOptimizedFrameLayout);
            }
            this.statusBarCoordAnimationController.onStatusBarViewCreated(phoneStatusBarView);
            OplusHomeStatusBarComponent oplusHomeStatusBarComponent2 = z ? (OplusHomeStatusBarComponent) homeStatusBarComponent : null;
            if (oplusHomeStatusBarComponent2 != null) {
                PhoneStatusBarView phoneStatusBarView2 = oplusHomeStatusBarComponent2.getPhoneStatusBarView();
                Ref.ObjectRef objectRef = new Ref.ObjectRef();
                OplusStatusBarViewLifecycleListenerImpl$expandStatusBarInit$sysStateControllerListener$1 oplusStatusBarViewLifecycleListenerImpl$expandStatusBarInit$sysStateControllerListener$1 = new OplusStatusBarViewLifecycleListenerImpl$expandStatusBarInit$sysStateControllerListener$1();
                oplusStatusBarViewLifecycleListenerImpl$expandStatusBarInit$sysStateControllerListener$1.this$0 = this;
                oplusStatusBarViewLifecycleListenerImpl$expandStatusBarInit$sysStateControllerListener$1.$phoneStatusBarView = phoneStatusBarView2;
                oplusStatusBarViewLifecycleListenerImpl$expandStatusBarInit$sysStateControllerListener$1.$phoneStatusBarViewShownAnimator = objectRef;
                oplusStatusBarViewLifecycleListenerImpl$expandStatusBarInit$sysStateControllerListener$1.$component = oplusHomeStatusBarComponent2;
                VarHandle.storeStoreFence();
                StartSideExceptHeadsUpLayout startSideExceptHeadsUpLayout2 = (StartSideExceptHeadsUpLayout) phoneStatusBarView2.findViewById(2131365373);
                ArrayList<FakeType> fakeStartSideArray = ((OplusFakeViewProvider) this.oplusFakeViewProvider.get()).getFakeStartSideArray();
                int size = fakeStartSideArray.size();
                while (i < size) {
                    FakeType fakeType = fakeStartSideArray.get(i);
                    i++;
                    FakeType fakeType2 = fakeType;
                    if (startSideExceptHeadsUpLayout2 != null && (fakeViews2 = startSideExceptHeadsUpLayout2.getFakeViews()) != null) {
                        fakeViews2.add(((OplusFakeViewProvider) this.oplusFakeViewProvider.get()).getQsFakeView(fakeType2));
                    }
                    if (startSideExceptHeadsUpLayout2 != null && (fakeViews = startSideExceptHeadsUpLayout2.getFakeViews()) != null) {
                        fakeViews.add(((OplusFakeViewProvider) this.oplusFakeViewProvider.get()).getSimpleFakeView(fakeType2));
                    }
                }
                this.sysUiStateControllerImpl.addListener(oplusStatusBarViewLifecycleListenerImpl$expandStatusBarInit$sysStateControllerListener$1);
                ((FakeBinder) this.fakeBinder.get()).setFakeView(phoneStatusBarView2);
            }
        }
    }
}
