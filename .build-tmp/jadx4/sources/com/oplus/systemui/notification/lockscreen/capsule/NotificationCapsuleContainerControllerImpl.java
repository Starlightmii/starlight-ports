package com.oplus.systemui.notification.lockscreen.capsule;

import android.content.Context;
import android.content.res.Configuration;
import android.content.res.Resources;
import android.graphics.Rect;
import android.graphics.RectF;
import android.os.Bundle;
import android.util.Log;
import android.view.MotionEvent;
import android.view.View;
import androidx.lifecycle.Lifecycle;
import androidx.lifecycle.LifecycleOwner;
import androidx.lifecycle.RepeatOnLifecycleKt;
import com.android.keyguard.OplusKeyguardDependencyEx;
import com.android.systemui.DependencyEx;
import com.android.systemui.dump.DumpManager;
import com.android.systemui.keyguard.KeyguardNotificationCapsuleViewControllerEx;
import com.android.systemui.keyguard.KeyguardNotificationCapsuleViewObserver;
import com.android.systemui.keyguard.OplusBlackScreenGestureCallback$;
import com.android.systemui.keyguard.OplusKeyguardBottomAreaControllerEx;
import com.android.systemui.lifecycle.RepeatWhenAttachedKt;
import com.android.systemui.statusbar.LockScreenNotificationDispatcher;
import com.android.systemui.statusbar.notification.OplusPhysicsPropertyAnimator$;
import com.android.systemui.statusbar.notification.row.ExpandableView;
import com.android.systemui.statusbar.notification.row.OplusExpandableViewEx;
import com.android.systemui.statusbar.notification.stack.NotificationStackScrollLayout;
import com.android.systemui.statusbar.notification.stack.NotificationStackScrollLayoutController;
import com.android.systemui.statusbar.policy.ConfigurationController;
import com.android.systemui.util.ConvenienceExtensionsKt;
import com.android.systemui.util.LockScreenNotificationIconData;
import com.android.systemui.util.ViewController;
import com.oplus.keyguard.utils.KeyguardUtils;
import com.oplus.systemui.aod.display.OplusAODRefreshRateController$RequestRefreshRateRunnable$;
import com.oplus.systemui.common.manager.OplusKeyguardOrientationManager;
import com.oplus.systemui.keyguard.wallpaper.OplusWallpaperColorManager;
import com.oplus.systemui.keyguard.wallpaper.OplusWallpaperColorManagerCallback;
import com.oplus.systemui.notification.lockscreen.ILockScreenExtStateController;
import com.oplus.systemui.notification.lockscreen.OplusNotificationCapsuleInteractorImpl;
import com.oplus.systemui.notification.lockscreen.base.CapsuleTextColorController;
import com.oplus.systemui.notification.lockscreen.capsule.animtor.ContainerAnimatorViewHelper;
import com.oplus.systemui.notification.lockscreen.capsule.modle.CapsuleItemData;
import com.oplus.systemui.notification.lockscreen.capsule.modle.CapsuleViewModel;
import com.oplus.systemui.notification.lockscreen.capsule.modle.datahandler.CapsuleDataDiffer;
import com.oplus.systemui.notification.lockscreen.capsule.modle.datahandler.CapsuleDataOrderHandler;
import com.oplus.systemui.notification.lockscreen.capsule.modle.datahandler.CapsuleDataPinHandler;
import com.oplus.systemui.notification.lockscreen.capsule.modle.datahandler.CapsuleDataScreenMonitor;
import com.oplus.systemui.notification.lockscreen.capsule.utils.CapsuleViewExpandKtKt;
import com.oplus.systemui.notification.lockscreen.capsule.utils.ViewExpandKtKt;
import com.oplus.systemui.notification.lockscreen.config.CapsuleConfig;
import com.oplus.systemui.notification.lockscreen.effect.NotificationEffectRepository;
import com.oplus.systemui.notification.lockscreen.immersive.OplusImmersiveViewManager;
import com.oplus.systemui.notification.lockscreen.lightos.CapsuleLightOSController;
import com.oplus.systemui.notification.lockscreen.notification.CapsuleNotificationDataController;
import com.oplus.systemui.notification.lockscreen.statemanager.ICardControlKt;
import com.oplus.systemui.notification.lockscreen.statemanager.LockScreenNotificationExtStateDecider;
import com.oplus.systemui.notification.lockscreen.statemanager.LockScreenNotificationViewBinder;
import com.oplus.systemui.notification.lockscreen.statemanager.LockScreenNotificationViewBinder$initCapsule$1;
import com.oplus.systemui.statusbar.notification.stack.NotificationStackScrollLayoutExtImpl;
import com.oplusos.systemui.common.util.LogUtil;
import dagger.Lazy;
import java.lang.invoke.VarHandle;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;
import java.util.concurrent.CopyOnWriteArrayList;
import kotlin.ResultKt;
import kotlin.Unit;
import kotlin.coroutines.Continuation;
import kotlin.coroutines.CoroutineContext;
import kotlin.coroutines.intrinsics.IntrinsicsKt__IntrinsicsKt;
import kotlin.coroutines.jvm.internal.SpillingKt;
import kotlin.coroutines.jvm.internal.SuspendLambda;
import kotlin.jvm.functions.Function2;
import kotlin.jvm.functions.Function3;
import kotlin.sequences.SequencesKt___SequencesKt;
import kotlin.text.Regex$;
import kotlinx.coroutines.BuildersKt;
import kotlinx.coroutines.CoroutineScope;
import kotlinx.coroutines.CoroutineStart;
import kotlinx.coroutines.Dispatchers;
import kotlinx.coroutines.flow.FlowCollector;
import kotlinx.coroutines.flow.MutableStateFlow;
import kotlinx.coroutines.flow.StateFlow;
import kotlinx.coroutines.flow.StateFlowKt;
import okio.Path$;

/* JADX INFO: compiled from: go/retraceme fd74bf9514dd0739b92d67a6d48a51eb10aaf8df760d83496b8dcfcf83e5f698 */
/* JADX INFO: loaded from: /home/starlight/Projects/Starlight Ports/.build-tmp/classes4.dex */
public final class NotificationCapsuleContainerControllerImpl extends ViewController implements KeyguardNotificationCapsuleViewControllerEx, ConfigurationController.ConfigurationListener {
    public static final Companion Companion = new Companion();
    private static final String TAG = "NotificationCapsuleContainerControllerImpl";
    public static float lastKeyguardTraceAlpha;
    private MutableStateFlow _isKeyguardLightColor;
    private final Context appContext;
    private final CapsuleDataDiffer capsuleDataDiffer;
    private final CapsuleDataScreenMonitor capsuleDataScreenMonitor;
    private final CapsuleListContainer capsuleListContainer;
    private final CapsuleNotificationDataController capsuleNotifDataController;
    private final CapsuleViewModel capsuleViewModel;
    private final OplusWallpaperColorManagerCallback colorManagerCallback;
    private final Lazy dumpManager;
    private final Lazy extNssl;
    private final LockScreenNotificationExtStateDecider extStateDecider;
    private final Lazy immersiveViewManager;
    public boolean isLightColor;
    private final MutableStateFlow keyguardAlpha;
    private final CopyOnWriteArrayList<KeyguardNotificationCapsuleViewObserver> keyguardNotificationCapsuleViewObservers;
    private final NotificationCapsuleContainerControllerImpl$keyguardOrientationAnimationListener$1 keyguardOrientationAnimationListener;
    private final MutableStateFlow lightOSAnimaAlpha;
    private final Lazy lightOSController;
    private final Lazy lockScreenExtStateController;
    private final Lazy notificationCapsuleInteractor;
    private final OplusNotificationCapsuleInteractorImpl notificationCapsuleInteractorImpl;
    private final ArrayList<LockScreenNotificationIconData> notificationDataList;
    private final Lazy notificationEffectRepository;
    private final Lazy nsslController;
    private final Lazy oplusBiometricAuthControllerLazy;
    private OplusKeyguardBottomAreaControllerEx oplusKeyguardBottomAreaController;
    private final Lazy oplusKeyguardOrientationManager;
    private final MutableStateFlow orientationAlpha;
    private OutActionListener outActionListener;
    private final Lazy textColorController;
    private final NotificationCapsuleContainer view;

    /* JADX INFO: compiled from: go/retraceme fd74bf9514dd0739b92d67a6d48a51eb10aaf8df760d83496b8dcfcf83e5f698 */
    public final class CapsuleListContainerImpl implements CapsuleListContainer {
        public /* synthetic */ NotificationCapsuleContainerControllerImpl this$0;

        @Override // com.oplus.systemui.notification.lockscreen.capsule.CapsuleListContainer
        public void addContainerView(View view) {
            if (LogUtil.getNormalDebug() && LogUtil.isTagValid(LogUtil.TAG_NOTIFICATION)) {
                Log.d("SystemUi--Notification", "NotificationCapsuleContainerControllerImpl-->".concat("addContainerView:: v = " + view));
            }
            this.this$0.getView().addContainerView(view);
        }

        @Override // com.oplus.systemui.notification.lockscreen.capsule.CapsuleListContainer
        public void addContainerViewAt(View view, int i) {
            if (LogUtil.getNormalDebug() && LogUtil.isTagValid(LogUtil.TAG_NOTIFICATION)) {
                Log.d("SystemUi--Notification", "NotificationCapsuleContainerControllerImpl-->".concat("addContainerViewAt:: v = " + view + ", index = " + i));
            }
            this.this$0.getView().addContainerViewAt(view, i);
        }

        @Override // com.oplus.systemui.notification.lockscreen.capsule.CapsuleListContainer
        public void changeViewPosition(View view, int i) {
            if (LogUtil.getNormalDebug() && LogUtil.isTagValid(LogUtil.TAG_NOTIFICATION)) {
                Log.d("SystemUi--Notification", "NotificationCapsuleContainerControllerImpl-->".concat("changeViewPosition:: child = " + view + ", newIndex = " + i));
            }
            this.this$0.getView().changeViewPosition(view, i);
        }

        @Override // com.oplus.systemui.notification.lockscreen.capsule.CapsuleListContainer
        public View getContainerChildAt(int i) {
            return this.this$0.getView().getContainerChildAt(i);
        }

        @Override // com.oplus.systemui.notification.lockscreen.capsule.CapsuleListContainer
        public int getContainerChildCount() {
            return this.this$0.getView().getContainerChildCount();
        }

        @Override // com.oplus.systemui.notification.lockscreen.capsule.CapsuleListContainer
        public void reTopContainerView(View view, boolean z) {
            if (LogUtil.getNormalDebug() && LogUtil.isTagValid(LogUtil.TAG_NOTIFICATION)) {
                Log.d("SystemUi--Notification", "NotificationCapsuleContainerControllerImpl-->".concat("reTopContainerView:: v = " + view + ", needAnim = " + z));
            }
            this.this$0.getView().reTopContainerView(view, z);
        }

        @Override // com.oplus.systemui.notification.lockscreen.capsule.CapsuleListContainer
        public void removeContainerView(View view) {
            if (LogUtil.getNormalDebug() && LogUtil.isTagValid(LogUtil.TAG_NOTIFICATION)) {
                Log.d("SystemUi--Notification", "NotificationCapsuleContainerControllerImpl-->".concat("removeContainerView:: v = " + view));
            }
            this.this$0.getView().removeContainerView(view);
        }
    }

    /* JADX INFO: compiled from: go/retraceme fd74bf9514dd0739b92d67a6d48a51eb10aaf8df760d83496b8dcfcf83e5f698 */
    public final class Companion {
        public static final void access$traceAlphaChange(Companion companion, String str, float f) {
            companion.getClass();
            if (LogUtil.getNormalDebug()) {
                if ((f == 0.0f || f == 1.0f || Math.abs(f - 0.5f) <= 0.01d) && LogUtil.getNormalDebug() && LogUtil.isTagValid(LogUtil.TAG_NOTIFICATION)) {
                    Log.d("SystemUi--Notification", "NotificationCapsuleContainerControllerImpl-->".concat("CapsuleContainerAlphaTrace: " + str + ": curAlpha=" + f));
                }
            }
        }
    }

    /* JADX INFO: compiled from: go/retraceme fd74bf9514dd0739b92d67a6d48a51eb10aaf8df760d83496b8dcfcf83e5f698 */
    public interface OutActionListener {
        void onImmerToImmerClick(CapsuleItemData capsuleItemData);

        void onItemClick(CapsuleItemData capsuleItemData);
    }

    /* JADX INFO: renamed from: com.oplus.systemui.notification.lockscreen.capsule.NotificationCapsuleContainerControllerImpl$onInit$1, reason: invalid class name */
    /* JADX INFO: compiled from: go/retraceme fd74bf9514dd0739b92d67a6d48a51eb10aaf8df760d83496b8dcfcf83e5f698 */
    final class AnonymousClass1 extends SuspendLambda implements Function3 {
        private /* synthetic */ Object L$0;
        int label;

        /* JADX INFO: renamed from: com.oplus.systemui.notification.lockscreen.capsule.NotificationCapsuleContainerControllerImpl$onInit$1$1, reason: invalid class name and collision with other inner class name */
        /* JADX INFO: compiled from: go/retraceme fd74bf9514dd0739b92d67a6d48a51eb10aaf8df760d83496b8dcfcf83e5f698 */
        final class C00211 extends SuspendLambda implements Function2 {
            private /* synthetic */ Object L$0;
            int label;
            final /* synthetic */ NotificationCapsuleContainerControllerImpl this$0;

            /* JADX INFO: renamed from: com.oplus.systemui.notification.lockscreen.capsule.NotificationCapsuleContainerControllerImpl$onInit$1$1$1, reason: invalid class name and collision with other inner class name */
            /* JADX INFO: compiled from: go/retraceme fd74bf9514dd0739b92d67a6d48a51eb10aaf8df760d83496b8dcfcf83e5f698 */
            final class C00221 extends SuspendLambda implements Function2 {
                int label;
                final /* synthetic */ NotificationCapsuleContainerControllerImpl this$0;

                /* JADX INFO: renamed from: com.oplus.systemui.notification.lockscreen.capsule.NotificationCapsuleContainerControllerImpl$onInit$1$1$1$1, reason: invalid class name and collision with other inner class name */
                /* JADX INFO: compiled from: go/retraceme fd74bf9514dd0739b92d67a6d48a51eb10aaf8df760d83496b8dcfcf83e5f698 */
                public final class C00231 implements FlowCollector {
                    public final /* synthetic */ int $r8$classId;
                    public /* synthetic */ NotificationCapsuleContainerControllerImpl this$0;

                    public /* synthetic */ C00231(int i) {
                        this.$r8$classId = i;
                    }

                    public final Object emit(Object obj, Continuation continuation) {
                        switch (this.$r8$classId) {
                            case 0:
                                NotificationCapsuleContainerControllerImpl notificationCapsuleContainerControllerImpl = this.this$0;
                                notificationCapsuleContainerControllerImpl.notificationDataList.clear();
                                notificationCapsuleContainerControllerImpl.notificationDataList.addAll((List) obj);
                                break;
                            case 1:
                                this.this$0.updateKeyguardCapsuleViewAlpha(((Number) obj).floatValue());
                                break;
                            case 2:
                                NotificationCapsuleContainerControllerImpl notificationCapsuleContainerControllerImpl2 = this.this$0;
                                List<CapsuleItemView> capsuleChildren = CapsuleViewExpandKtKt.getCapsuleChildren(notificationCapsuleContainerControllerImpl2.getView());
                                notificationCapsuleContainerControllerImpl2.capsuleDataDiffer.dispatchUpdatesToContainer(notificationCapsuleContainerControllerImpl2.getCapsuleListContainer(), capsuleChildren, (List) obj);
                                break;
                            default:
                                boolean zBooleanValue = ((Boolean) obj).booleanValue();
                                Iterator<T> it = CapsuleViewExpandKtKt.getCapsuleChildren((NotificationCapsuleContainer) ((ViewController) this.this$0).mView).iterator();
                                while (it.hasNext()) {
                                    ((CapsuleItemView) it.next()).getBgView().capsuleHelper.setStrokeState(zBooleanValue);
                                }
                                break;
                        }
                        return Unit.INSTANCE;
                    }
                }

                /* JADX WARN: 'super' call moved to the top of the method (can break code semantics) */
                public C00221(NotificationCapsuleContainerControllerImpl notificationCapsuleContainerControllerImpl, Continuation continuation) {
                    super(2, continuation);
                    this.this$0 = notificationCapsuleContainerControllerImpl;
                }

                public final Continuation create(Object obj, Continuation continuation) {
                    return new C00221(this.this$0, continuation);
                }

                public final Object invoke(Object obj, Object obj2) {
                    return create((CoroutineScope) obj, (Continuation) obj2).invokeSuspend(Unit.INSTANCE);
                }

                public final Object invokeSuspend(Object obj) {
                    Object coroutine_suspended = IntrinsicsKt__IntrinsicsKt.getCOROUTINE_SUSPENDED();
                    int i = this.label;
                    if (i == 0) {
                        ResultKt.throwOnFailure(obj);
                        StateFlow lockScreenNotificationIconData = ((LockScreenNotificationDispatcher) DependencyEx.sDependency.getDependency(LockScreenNotificationDispatcher.class)).getLockScreenNotificationIconData();
                        NotificationCapsuleContainerControllerImpl notificationCapsuleContainerControllerImpl = this.this$0;
                        C00231 c00231 = new C00231(0);
                        c00231.this$0 = notificationCapsuleContainerControllerImpl;
                        VarHandle.storeStoreFence();
                        this.label = 1;
                        if (lockScreenNotificationIconData.collect(c00231, this) == coroutine_suspended) {
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

            /* JADX INFO: renamed from: com.oplus.systemui.notification.lockscreen.capsule.NotificationCapsuleContainerControllerImpl$onInit$1$1$2, reason: invalid class name */
            /* JADX INFO: compiled from: go/retraceme fd74bf9514dd0739b92d67a6d48a51eb10aaf8df760d83496b8dcfcf83e5f698 */
            final class AnonymousClass2 extends SuspendLambda implements Function2 {
                int label;
                final /* synthetic */ NotificationCapsuleContainerControllerImpl this$0;

                /* JADX WARN: 'super' call moved to the top of the method (can break code semantics) */
                public AnonymousClass2(NotificationCapsuleContainerControllerImpl notificationCapsuleContainerControllerImpl, Continuation continuation) {
                    super(2, continuation);
                    this.this$0 = notificationCapsuleContainerControllerImpl;
                }

                public final Continuation create(Object obj, Continuation continuation) {
                    return new AnonymousClass2(this.this$0, continuation);
                }

                public final Object invoke(Object obj, Object obj2) {
                    return create((CoroutineScope) obj, (Continuation) obj2).invokeSuspend(Unit.INSTANCE);
                }

                public final Object invokeSuspend(Object obj) {
                    Object coroutine_suspended = IntrinsicsKt__IntrinsicsKt.getCOROUTINE_SUSPENDED();
                    int i = this.label;
                    if (i == 0) {
                        ResultKt.throwOnFailure(obj);
                        StateFlow capsuleData = this.this$0.capsuleViewModel.getCapsuleData();
                        NotificationCapsuleContainerControllerImpl notificationCapsuleContainerControllerImpl = this.this$0;
                        C00221.C00231 c00231 = new C00221.C00231(2);
                        c00231.this$0 = notificationCapsuleContainerControllerImpl;
                        VarHandle.storeStoreFence();
                        this.label = 1;
                        if (capsuleData.collect(c00231, this) == coroutine_suspended) {
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

            /* JADX INFO: renamed from: com.oplus.systemui.notification.lockscreen.capsule.NotificationCapsuleContainerControllerImpl$onInit$1$1$3, reason: invalid class name */
            /* JADX INFO: compiled from: go/retraceme fd74bf9514dd0739b92d67a6d48a51eb10aaf8df760d83496b8dcfcf83e5f698 */
            final class AnonymousClass3 extends SuspendLambda implements Function2 {
                int label;
                final /* synthetic */ NotificationCapsuleContainerControllerImpl this$0;

                /* JADX WARN: 'super' call moved to the top of the method (can break code semantics) */
                public AnonymousClass3(NotificationCapsuleContainerControllerImpl notificationCapsuleContainerControllerImpl, Continuation continuation) {
                    super(2, continuation);
                    this.this$0 = notificationCapsuleContainerControllerImpl;
                }

                public final Continuation create(Object obj, Continuation continuation) {
                    return new AnonymousClass3(this.this$0, continuation);
                }

                public final Object invoke(Object obj, Object obj2) {
                    return create((CoroutineScope) obj, (Continuation) obj2).invokeSuspend(Unit.INSTANCE);
                }

                public final Object invokeSuspend(Object obj) {
                    Object coroutine_suspended = IntrinsicsKt__IntrinsicsKt.getCOROUTINE_SUSPENDED();
                    int i = this.label;
                    if (i == 0) {
                        ResultKt.throwOnFailure(obj);
                        StateFlow stateFlowIsStrokeLineEnable = ((NotificationEffectRepository) this.this$0.notificationEffectRepository.get()).isStrokeLineEnable();
                        NotificationCapsuleContainerControllerImpl notificationCapsuleContainerControllerImpl = this.this$0;
                        C00221.C00231 c00231 = new C00221.C00231(3);
                        c00231.this$0 = notificationCapsuleContainerControllerImpl;
                        VarHandle.storeStoreFence();
                        this.label = 1;
                        if (stateFlowIsStrokeLineEnable.collect(c00231, this) == coroutine_suspended) {
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
            public C00211(NotificationCapsuleContainerControllerImpl notificationCapsuleContainerControllerImpl, Continuation continuation) {
                super(2, continuation);
                this.this$0 = notificationCapsuleContainerControllerImpl;
            }

            public final Continuation create(Object obj, Continuation continuation) {
                C00211 c00211 = new C00211(this.this$0, continuation);
                c00211.L$0 = obj;
                return c00211;
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
                BuildersKt.launch$default(coroutineScope, (CoroutineContext) null, (CoroutineStart) null, new C00221(this.this$0, null), 3);
                BuildersKt.launch$default(coroutineScope, (CoroutineContext) null, (CoroutineStart) null, new AnonymousClass2(this.this$0, null), 3);
                BuildersKt.launch$default(coroutineScope, Dispatchers.getMain(), (CoroutineStart) null, new AnonymousClass3(this.this$0, null), 2);
                NotificationCapsuleContainerControllerImpl notificationCapsuleContainerControllerImpl = this.this$0;
                notificationCapsuleContainerControllerImpl.getClass();
                BuildersKt.launch$default(coroutineScope, Dispatchers.getMain().getImmediate(), (CoroutineStart) null, new NotificationCapsuleContainerControllerImpl$collectAlphaChange$1(notificationCapsuleContainerControllerImpl, null), 2);
                return Unit.INSTANCE;
            }
        }

        public AnonymousClass1(Continuation continuation) {
            super(3, continuation);
        }

        public final Object invoke(Object obj, Object obj2, Object obj3) {
            AnonymousClass1 anonymousClass1 = NotificationCapsuleContainerControllerImpl.this.new AnonymousClass1((Continuation) obj3);
            anonymousClass1.L$0 = (LifecycleOwner) obj;
            return anonymousClass1.invokeSuspend(Unit.INSTANCE);
        }

        public final Object invokeSuspend(Object obj) {
            LifecycleOwner lifecycleOwner = (LifecycleOwner) this.L$0;
            Object coroutine_suspended = IntrinsicsKt__IntrinsicsKt.getCOROUTINE_SUSPENDED();
            int i = this.label;
            if (i == 0) {
                ResultKt.throwOnFailure(obj);
                Lifecycle.State state = Lifecycle.State.CREATED;
                C00211 c00211 = new C00211(NotificationCapsuleContainerControllerImpl.this, null);
                this.L$0 = SpillingKt.nullOutSpilledVariable(lifecycleOwner);
                this.label = 1;
                if (RepeatOnLifecycleKt.repeatOnLifecycle(lifecycleOwner, state, c00211, this) == coroutine_suspended) {
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

    /* JADX INFO: renamed from: com.oplus.systemui.notification.lockscreen.capsule.NotificationCapsuleContainerControllerImpl$onInit$4, reason: invalid class name */
    /* JADX INFO: compiled from: go/retraceme fd74bf9514dd0739b92d67a6d48a51eb10aaf8df760d83496b8dcfcf83e5f698 */
    public final class AnonymousClass4 implements View.OnLayoutChangeListener {
        public /* synthetic */ NotificationCapsuleContainerControllerImpl this$0;

        @Override // android.view.View.OnLayoutChangeListener
        public final void onLayoutChange(View view, int i, int i2, int i3, int i4, int i5, int i6, int i7, int i8) {
            ((OplusImmersiveViewManager) this.this$0.immersiveViewManager.get()).notifyCapsuleLayoutChanged();
        }
    }

    /* JADX INFO: renamed from: $r8$lambda$GZ-cg3uIk2piEN09mX6AibNkvK4, reason: not valid java name */
    public static Unit m76$r8$lambda$GZcg3uIk2piEN09mX6AibNkvK4(NotificationCapsuleContainerControllerImpl notificationCapsuleContainerControllerImpl) {
        notificationCapsuleContainerControllerImpl.notificationCapsuleInteractorImpl.onCapsulePositionChange();
        return Unit.INSTANCE;
    }

    /* JADX INFO: renamed from: $r8$lambda$NAORo-zIZEEnoGqAnMpExjxhuy8, reason: not valid java name */
    public static Unit m77$r8$lambda$NAORozIZEEnoGqAnMpExjxhuy8(NotificationCapsuleContainerControllerImpl notificationCapsuleContainerControllerImpl, float f) {
        notificationCapsuleContainerControllerImpl.notificationCapsuleInteractorImpl.onCapsulePositionTranslationYChange(f);
        return Unit.INSTANCE;
    }

    public static Unit $r8$lambda$cw2qMwVdELNdM7TbB3fct_Sf2mU(NotificationCapsuleContainerControllerImpl notificationCapsuleContainerControllerImpl, boolean z) {
        notificationCapsuleContainerControllerImpl._isKeyguardLightColor.setValue(Boolean.valueOf(z));
        return Unit.INSTANCE;
    }

    public static Unit $r8$lambda$jp_RCz2Uhg2v7V7E4E1NPLlJsk4(NotificationCapsuleContainerControllerImpl notificationCapsuleContainerControllerImpl, int i) {
        NotificationCapsuleContainer notificationCapsuleContainer = notificationCapsuleContainerControllerImpl.view;
        notificationCapsuleContainer.getClass();
        if (LogUtil.getNormalDebug() && LogUtil.isTagValid(LogUtil.TAG_NOTIFICATION)) {
            OplusBlackScreenGestureCallback$.ExternalSyntheticOutline0.m(i, "NotificationCapsuleContainer.updateItemWidth: ", "NotificationCapsuleContainer-->", "SystemUi--Notification");
        }
        notificationCapsuleContainer.getAnimatorHelper().requestChildrenUpdate("updateItemWidth");
        notificationCapsuleContainerControllerImpl.notificationCapsuleInteractorImpl.onCapsulePositionChange();
        return Unit.INSTANCE;
    }

    public NotificationCapsuleContainerControllerImpl(Context context, NotificationCapsuleContainer notificationCapsuleContainer, CapsuleViewModel capsuleViewModel, Lazy lazy, Lazy lazy2, Lazy lazy3, OplusNotificationCapsuleInteractorImpl oplusNotificationCapsuleInteractorImpl, CapsuleDataScreenMonitor capsuleDataScreenMonitor, CapsuleNotificationDataController capsuleNotificationDataController, CapsuleDataDiffer capsuleDataDiffer, Lazy lazy4, Lazy lazy5, Lazy lazy6, Lazy lazy7, Lazy lazy8, Lazy lazy9, Lazy lazy10, Lazy lazy11, LockScreenNotificationExtStateDecider lockScreenNotificationExtStateDecider) {
        super(notificationCapsuleContainer);
        this.appContext = context;
        this.view = notificationCapsuleContainer;
        this.capsuleViewModel = capsuleViewModel;
        this.lockScreenExtStateController = lazy;
        this.extNssl = lazy2;
        this.immersiveViewManager = lazy3;
        this.notificationCapsuleInteractorImpl = oplusNotificationCapsuleInteractorImpl;
        this.capsuleDataScreenMonitor = capsuleDataScreenMonitor;
        this.capsuleNotifDataController = capsuleNotificationDataController;
        this.capsuleDataDiffer = capsuleDataDiffer;
        this.oplusKeyguardOrientationManager = lazy4;
        this.dumpManager = lazy5;
        this.textColorController = lazy6;
        this.oplusBiometricAuthControllerLazy = lazy7;
        this.notificationEffectRepository = lazy8;
        this.notificationCapsuleInteractor = lazy9;
        this.nsslController = lazy10;
        this.lightOSController = lazy11;
        this.extStateDecider = lockScreenNotificationExtStateDecider;
        CapsuleListContainerImpl capsuleListContainerImpl = new CapsuleListContainerImpl();
        capsuleListContainerImpl.this$0 = this;
        VarHandle.storeStoreFence();
        this.capsuleListContainer = capsuleListContainerImpl;
        this.keyguardNotificationCapsuleViewObservers = new CopyOnWriteArrayList<>();
        this.notificationDataList = new ArrayList<>();
        NotificationCapsuleContainerControllerImpl$colorManagerCallback$1 notificationCapsuleContainerControllerImpl$colorManagerCallback$1 = new NotificationCapsuleContainerControllerImpl$colorManagerCallback$1();
        notificationCapsuleContainerControllerImpl$colorManagerCallback$1.this$0 = this;
        VarHandle.storeStoreFence();
        this.colorManagerCallback = notificationCapsuleContainerControllerImpl$colorManagerCallback$1;
        this._isKeyguardLightColor = StateFlowKt.MutableStateFlow(Boolean.FALSE);
        Float fValueOf = Float.valueOf(1.0f);
        this.keyguardAlpha = StateFlowKt.MutableStateFlow(fValueOf);
        this.orientationAlpha = StateFlowKt.MutableStateFlow(fValueOf);
        this.lightOSAnimaAlpha = StateFlowKt.MutableStateFlow(fValueOf);
        NotificationCapsuleContainerControllerImpl$keyguardOrientationAnimationListener$1 notificationCapsuleContainerControllerImpl$keyguardOrientationAnimationListener$1 = new NotificationCapsuleContainerControllerImpl$keyguardOrientationAnimationListener$1();
        notificationCapsuleContainerControllerImpl$keyguardOrientationAnimationListener$1.this$0 = this;
        VarHandle.storeStoreFence();
        this.keyguardOrientationAnimationListener = notificationCapsuleContainerControllerImpl$keyguardOrientationAnimationListener$1;
        capsuleDataScreenMonitor.init();
        CapsuleDataPinHandler.INSTANCE.bindContainer(notificationCapsuleContainer, capsuleNotificationDataController);
        CapsuleDataOrderHandler.INSTANCE.bindContainer(notificationCapsuleContainer, capsuleNotificationDataController);
        CapsuleTextColorController capsuleTextColorController = (CapsuleTextColorController) lazy6.get();
        NotificationCapsuleContainerControllerImpl$$ExternalSyntheticLambda0 notificationCapsuleContainerControllerImpl$$ExternalSyntheticLambda0 = new NotificationCapsuleContainerControllerImpl$$ExternalSyntheticLambda0(3);
        notificationCapsuleContainerControllerImpl$$ExternalSyntheticLambda0.f$0 = this;
        VarHandle.storeStoreFence();
        capsuleTextColorController.setTextLightColorChangeListener(notificationCapsuleContainerControllerImpl$$ExternalSyntheticLambda0);
    }

    /* JADX INFO: Access modifiers changed from: private */
    public final Rect getRectForColorChanged() {
        OplusKeyguardBottomAreaControllerEx oplusKeyguardBottomAreaControllerEx = this.oplusKeyguardBottomAreaController;
        if ((oplusKeyguardBottomAreaControllerEx != null ? oplusKeyguardBottomAreaControllerEx.getFpType() : 3) == 3) {
            Rect rect = new Rect();
            ((NotificationCapsuleContainer) ((ViewController) this).mView).getGlobalVisibleRect(rect);
            rect.inset(((NotificationCapsuleContainer) ((ViewController) this).mView).getPaddingLeft(), ((NotificationCapsuleContainer) ((ViewController) this).mView).getPaddingTop(), ((NotificationCapsuleContainer) ((ViewController) this).mView).getPaddingRight(), ((NotificationCapsuleContainer) ((ViewController) this).mView).getPaddingBottom());
            return rect;
        }
        Resources resources = ((NotificationCapsuleContainer) ((ViewController) this).mView).getResources();
        KeyguardUtils.Companion companion = KeyguardUtils.Companion;
        int realScreenWidth = companion.getRealScreenWidth();
        int realScreenHeight = companion.getRealScreenHeight();
        return new Rect(0, resources.getDimensionPixelSize(2131168558) + (realScreenHeight - resources.getDimensionPixelSize(2131168554)), realScreenWidth, realScreenHeight - resources.getDimensionPixelSize(2131168558));
    }

    public final void changeBindDirection(boolean z) {
        ((CapsuleLightOSController) this.lightOSController.get()).changeBindDirection(z);
    }

    public final void customOverScrollToY(float f) {
        this.view.customOverScrollToY(f);
        if (((ILockScreenExtStateController) this.lockScreenExtStateController.get()).isImmersiveState()) {
            ((NotificationStackScrollLayoutController) this.nsslController.get()).getView().requestChildrenUpdate$1();
        }
    }

    public final void generateImmersiveAnimation(CapsuleItemView capsuleItemView, ExpandableView expandableView, boolean z, boolean z2) {
        this.view.generateImmersiveAnimation(capsuleItemView, expandableView, z, z2);
    }

    public final void generateStackAnimation(CapsuleItemView capsuleItemView, ExpandableView expandableView, boolean z, boolean z2) {
        this.view.generateStackAnimation(capsuleItemView, expandableView, z, z2);
    }

    public final CapsuleListContainer getCapsuleListContainer() {
        return this.capsuleListContainer;
    }

    public RectF getPositionRegion() {
        return ((NotificationCapsuleContainer) ((ViewController) this).mView).getPositionRegion();
    }

    public final NotificationCapsuleContainer getView() {
        return this.view;
    }

    public boolean handleTouchEvent(MotionEvent motionEvent) {
        return this.view.getScroller().onTouchEvent(motionEvent);
    }

    public final StateFlow isKeyguardLightColor() {
        return this._isKeyguardLightColor;
    }

    public final void onCapsuleClick(CapsuleItemView capsuleItemView) {
        OutActionListener outActionListener = this.outActionListener;
        if (outActionListener != null) {
            ((LockScreenNotificationViewBinder$initCapsule$1) outActionListener).onItemClick(capsuleItemView.getEntry());
        }
    }

    public void onConfigChanged(Configuration configuration) {
        CapsuleConfig capsuleConfig = CapsuleConfig.INSTANCE;
        Context context = getContext();
        NotificationCapsuleContainerControllerImpl$$ExternalSyntheticLambda0 notificationCapsuleContainerControllerImpl$$ExternalSyntheticLambda0 = new NotificationCapsuleContainerControllerImpl$$ExternalSyntheticLambda0(0);
        notificationCapsuleContainerControllerImpl$$ExternalSyntheticLambda0.f$0 = this;
        VarHandle.storeStoreFence();
        capsuleConfig.onConfigurationChanged(context, notificationCapsuleContainerControllerImpl$$ExternalSyntheticLambda0);
    }

    public final void onDensityOrFontScaleChanged() {
        reloadNotificationCapsuleViewContent();
    }

    public void onFontChanged(Configuration configuration) {
        reloadNotificationCapsuleViewContent();
    }

    public final void onFontWeightChanged() {
        reloadNotificationCapsuleViewContent();
    }

    public final void onImmerToImmerClick(CapsuleItemView capsuleItemView) {
        OutActionListener outActionListener = this.outActionListener;
        if (outActionListener != null) {
            ((LockScreenNotificationViewBinder$initCapsule$1) outActionListener).onImmerToImmerClick(capsuleItemView.getEntry());
        }
    }

    public final void onImmersiveViewRemoved() {
        OutActionListener outActionListener = this.outActionListener;
        if (outActionListener != null) {
            LockScreenNotificationViewBinder lockScreenNotificationViewBinder = ((LockScreenNotificationViewBinder$initCapsule$1) outActionListener).this$0;
            lockScreenNotificationViewBinder.lockScreenExtStateController.goToExtState(true, lockScreenNotificationViewBinder.lockScreenExtStateController.getLastExtState(), "ImmersiveItemRemove", 124, null);
        }
    }

    public final void onInit() {
        ((DumpManager) this.dumpManager.get()).registerDumpable(((ViewController) this).mView);
        ((NotificationCapsuleContainer) ((ViewController) this).mView).setVisibility(0);
        RepeatWhenAttachedKt.repeatWhenAttached$default(((ViewController) this).mView, (CoroutineContext) null, new AnonymousClass1(null), 3);
        ((NotificationCapsuleContainer) ((ViewController) this).mView).setContainerControllerImpl(this);
        ((NotificationCapsuleContainer) ((ViewController) this).mView).setLightOSController((CapsuleLightOSController) this.lightOSController.get());
        NotificationCapsuleContainer notificationCapsuleContainer = (NotificationCapsuleContainer) ((ViewController) this).mView;
        notificationCapsuleContainer.setScroller(new NotificationCapsuleContainerTouchHandler(this.appContext, notificationCapsuleContainer, this, this.lockScreenExtStateController, this.oplusBiometricAuthControllerLazy, this.notificationCapsuleInteractor, this.extStateDecider));
        ((NotificationCapsuleContainer) ((ViewController) this).mView).getAnimatorHelper().setLockScreenExtStateController((ILockScreenExtStateController) this.lockScreenExtStateController.get());
        ContainerAnimatorViewHelper animatorHelper = ((NotificationCapsuleContainer) ((ViewController) this).mView).getAnimatorHelper();
        NotificationCapsuleContainerControllerImpl$$ExternalSyntheticLambda0 notificationCapsuleContainerControllerImpl$$ExternalSyntheticLambda0 = new NotificationCapsuleContainerControllerImpl$$ExternalSyntheticLambda0(1);
        notificationCapsuleContainerControllerImpl$$ExternalSyntheticLambda0.f$0 = this;
        VarHandle.storeStoreFence();
        animatorHelper.setupContainerHeightChangeListener(notificationCapsuleContainerControllerImpl$$ExternalSyntheticLambda0);
        NotificationCapsuleContainer notificationCapsuleContainer2 = (NotificationCapsuleContainer) ((ViewController) this).mView;
        NotificationCapsuleContainerControllerImpl$$ExternalSyntheticLambda0 notificationCapsuleContainerControllerImpl$$ExternalSyntheticLambda1 = new NotificationCapsuleContainerControllerImpl$$ExternalSyntheticLambda0(2);
        notificationCapsuleContainerControllerImpl$$ExternalSyntheticLambda1.f$0 = this;
        VarHandle.storeStoreFence();
        notificationCapsuleContainer2.setStackYChangeListener(notificationCapsuleContainerControllerImpl$$ExternalSyntheticLambda1);
        NotificationStackScrollLayout view = ((NotificationStackScrollLayoutController) this.nsslController.get()).getView();
        AnonymousClass4 anonymousClass4 = new AnonymousClass4();
        anonymousClass4.this$0 = this;
        VarHandle.storeStoreFence();
        view.addOnLayoutChangeListener(anonymousClass4);
    }

    public boolean onIntercept(MotionEvent motionEvent) {
        OplusAODRefreshRateController$RequestRefreshRateRunnable$.ExternalSyntheticOutline0.m(8, "PanelViewOnInterceptTouchEvent action: ", ViewExpandKtKt.getActionName(motionEvent), LogUtil.TAG_NOTIFICATION, TAG);
        return this.view.getScroller().onInterceptTouchEvent(motionEvent);
    }

    public final void onLayoutDirectionChanged(boolean z) {
        reloadNotificationCapsuleViewContent();
    }

    public final void onLightOsAnimEnd() {
        ((CapsuleLightOSController) this.lightOSController.get()).onAnimEnd();
    }

    public final void onLightOsAnimStart(boolean z) {
        ((CapsuleLightOSController) this.lightOSController.get()).onAnimStart(z);
    }

    public final void onLocaleListChanged() {
        reloadNotificationCapsuleViewContent();
    }

    public final void onOrientationChanged(int i) {
        reloadNotificationCapsuleViewContent();
    }

    public final void onTopStackMax(float f) {
        OutActionListener outActionListener;
        if (((ILockScreenExtStateController) this.lockScreenExtStateController.get()).isStackState() || (outActionListener = this.outActionListener) == null) {
            return;
        }
        LockScreenNotificationViewBinder lockScreenNotificationViewBinder = ((LockScreenNotificationViewBinder$initCapsule$1) outActionListener).this$0;
        lockScreenNotificationViewBinder.lockScreenExtStateController.goToExtState(true, lockScreenNotificationViewBinder.extStateDecider.onCapsuleSlideToUp(), "onTopStackMax", 111, ILockScreenExtStateController.ExtStateEvent.Companion.withStartVelocity(new Bundle(), f));
    }

    public final void onTouchStart() {
        if (((ILockScreenExtStateController) this.lockScreenExtStateController.get()).isStackState()) {
            return;
        }
        this.view.getClass();
    }

    public final void onViewAttached() {
        OplusWallpaperColorManager.INSTANCE.addCallback(getContext(), this.colorManagerCallback);
        ((OplusKeyguardDependencyEx) DependencyEx.sDependency.getDependency(OplusKeyguardDependencyEx.class)).getConfigurationController().addCallback(this);
        ((OplusKeyguardOrientationManager) this.oplusKeyguardOrientationManager.get()).registerCallback(this.keyguardOrientationAnimationListener);
    }

    public final void onViewDetached() {
        OplusWallpaperColorManager.INSTANCE.removeCallback(this.colorManagerCallback);
        ((OplusKeyguardDependencyEx) DependencyEx.sDependency.getDependency(OplusKeyguardDependencyEx.class)).getConfigurationController().removeCallback(this);
        ((OplusKeyguardOrientationManager) this.oplusKeyguardOrientationManager.get()).unregisterCallback(this.keyguardOrientationAnimationListener);
    }

    public boolean registerObserver(KeyguardNotificationCapsuleViewObserver keyguardNotificationCapsuleViewObserver) {
        if (this.keyguardNotificationCapsuleViewObservers.contains(keyguardNotificationCapsuleViewObserver)) {
            return false;
        }
        this.keyguardNotificationCapsuleViewObservers.add(keyguardNotificationCapsuleViewObserver);
        return true;
    }

    public final void reloadNotificationCapsuleViewContent() {
        ((NotificationCapsuleContainer) ((ViewController) this).mView).updateCapsuleContentColor(this.isLightColor);
        if (((NotificationCapsuleContainer) ((ViewController) this).mView).getBlurRatio() == 15.0f) {
            View view = ((ViewController) this).mView;
            ((NotificationCapsuleContainer) view).setBlurRatio(((NotificationCapsuleContainer) view).getBlurRatio());
        }
    }

    public final void requestNsslChildrenUpdateWithAnimation() {
        NotificationStackScrollLayout notificationStackScrollLayout = ((NotificationStackScrollLayoutController) this.nsslController.get()).mView;
        if (notificationStackScrollLayout.mIsExpanded && notificationStackScrollLayout.mAnimationsEnabled) {
            notificationStackScrollLayout.mEverythingNeedsAnimation = true;
            notificationStackScrollLayout.mNeedsAnimation = true;
            notificationStackScrollLayout.requestChildrenUpdate$1();
        }
    }

    public final void resetAllTargetShowHeight() {
        Iterator it = SequencesKt___SequencesKt.filter(ConvenienceExtensionsKt.getChildren(((NotificationStackScrollLayoutController) this.nsslController.get()).getView()), NotificationCapsuleContainerControllerImpl$resetAllTargetShowHeight$.inlined.filterIsInstance.1.INSTANCE).iterator();
        while (it.hasNext()) {
            OplusExpandableViewEx oplusExpandableViewEx = ((ExpandableView) it.next()).mExpandableViewEx;
            if (oplusExpandableViewEx != null) {
                oplusExpandableViewEx.setTargetShowHeight(0);
            }
        }
    }

    public final void setLightOSAnimAlpha(float f) {
        Companion.access$traceAlphaChange(Companion, "lightOSAnimAlpha:", f);
        this.lightOSAnimaAlpha.setValue(Float.valueOf(f));
    }

    public void setup(OplusKeyguardBottomAreaControllerEx oplusKeyguardBottomAreaControllerEx) {
        this.oplusKeyguardBottomAreaController = oplusKeyguardBottomAreaControllerEx;
    }

    public final void setupOutActionListener(OutActionListener outActionListener) {
        this.outActionListener = outActionListener;
    }

    public boolean unRegisterObserver(KeyguardNotificationCapsuleViewObserver keyguardNotificationCapsuleViewObserver) {
        if (!this.keyguardNotificationCapsuleViewObservers.contains(keyguardNotificationCapsuleViewObserver)) {
            return false;
        }
        this.keyguardNotificationCapsuleViewObservers.remove(keyguardNotificationCapsuleViewObserver);
        return true;
    }

    public final void updateKeyguardAlpha(float f) {
        Companion.getClass();
        if (LogUtil.getNormalDebug() && (f == 0.0f || f == 1.0f || Math.abs(lastKeyguardTraceAlpha - f) >= 0.05f)) {
            lastKeyguardTraceAlpha = f;
            if (LogUtil.getNormalDebug() && LogUtil.isTagValid(LogUtil.TAG_NOTIFICATION)) {
                OplusPhysicsPropertyAnimator$.ExternalSyntheticOutline0.m("CapsuleContainerAlphaTrace: capsuleKeyguardAlpha: curAlpha=", f, "NotificationCapsuleContainerControllerImpl-->", "SystemUi--Notification");
            }
        }
        this.keyguardAlpha.setValue(Float.valueOf(f));
    }

    public final void updateKeyguardCapsuleViewAlpha(float f) {
        Companion.access$traceAlphaChange(Companion, "capsuleViewAlpha:", f);
        this.view.setAlpha(f);
    }

    public Object updateKeyguardNotificationCapsuleView(Continuation continuation) {
        return Unit.INSTANCE;
    }

    public Object updateNotificationCapsuleGotoState(Continuation continuation) {
        return Unit.INSTANCE;
    }

    public final void updatePosition(int i) {
        this.view.getAnimatorHelper().updatePosition(i);
        if (((ILockScreenExtStateController) this.lockScreenExtStateController.get()).isImmersiveState()) {
            if (LogUtil.getNormalDebug() && LogUtil.isTagValid(LogUtil.TAG_NOTIFICATION)) {
                Log.d("SystemUi--Notification", "NotificationCapsuleContainerControllerImpl-->updatePosition");
            }
            ((NotificationStackScrollLayoutExtImpl) this.extNssl.get()).requestAnimateKgNotificationStateTransform(19, ICardControlKt.getFilter());
        }
        this.notificationCapsuleInteractorImpl.onCapsulePositionBottomChange();
    }

    public final void updateScaleWithPivot(float f, float f2, float f3) {
        ViewExpandKtKt.setPivotXY(this.view, f2, f3);
        ViewExpandKtKt.setScaleXY(this.view, f, f);
    }
}
