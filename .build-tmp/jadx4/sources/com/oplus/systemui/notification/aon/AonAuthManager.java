package com.oplus.systemui.notification.aon;

import android.content.Context;
import android.os.Handler;
import android.os.Looper;
import android.os.Message;
import android.os.RemoteException;
import android.os.UserHandle;
import android.util.Log;
import com.aiunit.aon.AON.AntiPeeping;
import com.aiunit.aon.common.AONUnit;
import com.aiunit.aon.utils.IAONService;
import com.aiunit.aon.utils.common.CVLog;
import com.android.systemui.keyguard.OplusBlackScreenGestureCallback$;
import com.android.systemui.navigationbar.gestural.domain.GestureInteractor$getTopActivity$2$;
import com.android.systemui.power.domain.interactor.PowerInteractor;
import com.android.systemui.power.shared.model.ScreenPowerState;
import com.android.systemui.qs.pipeline.data.repository.SuperPowerSaveRepository;
import com.android.systemui.qs.pipeline.data.repository.WellBeingAssistantRepository;
import com.oplusos.systemui.common.feature.NotificationFeatureOption;
import com.oplusos.systemui.common.multiuser.IMultiUserListener;
import com.oplusos.systemui.common.multiuser.MultiUserManager;
import com.oplusos.systemui.common.util.LogUtil;
import java.io.FileDescriptor;
import java.io.PrintWriter;
import java.lang.invoke.VarHandle;
import java.util.concurrent.CopyOnWriteArraySet;
import kotlin.Lazy;
import kotlin.LazyKt__LazyJVMKt;
import kotlin.Result;
import kotlin.ResultKt;
import kotlin.Unit;
import kotlin.coroutines.Continuation;
import kotlin.coroutines.CoroutineContext;
import kotlin.coroutines.intrinsics.IntrinsicsKt__IntrinsicsKt;
import kotlin.coroutines.jvm.internal.SuspendLambda;
import kotlin.jvm.functions.Function2;
import kotlin.jvm.internal.Intrinsics;
import kotlinx.coroutines.BuildersKt;
import kotlinx.coroutines.CoroutineScope;
import kotlinx.coroutines.CoroutineStart;
import kotlinx.coroutines.flow.FlowKt;
import kotlinx.coroutines.flow.StateFlow;
import okio.Path$;

/* JADX INFO: compiled from: go/retraceme fd74bf9514dd0739b92d67a6d48a51eb10aaf8df760d83496b8dcfcf83e5f698 */
/* JADX INFO: loaded from: /home/starlight/Projects/Starlight Ports/.build-tmp/classes4.dex */
public final class AonAuthManager {
    public static final Companion Companion = new Companion();
    private static final String TAG = "AonAuthManager";
    private final AntiPeeping antiPeeping;
    private volatile Boolean aonAuthResult;
    private final Lazy aonBgHandler$delegate;
    private final AonEnableObserver aonEnableObserver;
    private final AonAuthManager$aonEventCallback$1 aonEventCallback;
    private final AonAuthManager$aonHandler$1 aonHandler;
    private final Lazy aonRuleManager$delegate;
    private final CopyOnWriteArraySet<IAonAuthStateListener> authStateListeners;
    public volatile boolean cameraOpening;
    private Context context;
    private final Lazy faceAuth$delegate;
    private final dagger.Lazy mainHandler;
    private final Runnable notifyListenersTask;
    private final PowerInteractor powerInteractor;
    private final CoroutineScope scope;
    private final SuperPowerSaveRepository superPowerRepository;
    private final WellBeingAssistantRepository wellBeingRepository;

    /* JADX INFO: compiled from: go/retraceme fd74bf9514dd0739b92d67a6d48a51eb10aaf8df760d83496b8dcfcf83e5f698 */
    public final class Companion {
    }

    /* JADX INFO: compiled from: go/retraceme fd74bf9514dd0739b92d67a6d48a51eb10aaf8df760d83496b8dcfcf83e5f698 */
    public interface IAonAuthStateListener {
        void onAuthStateChange(Boolean bool);
    }

    /* JADX INFO: renamed from: com.oplus.systemui.notification.aon.AonAuthManager$init$1, reason: invalid class name */
    /* JADX INFO: compiled from: go/retraceme fd74bf9514dd0739b92d67a6d48a51eb10aaf8df760d83496b8dcfcf83e5f698 */
    public final class AnonymousClass1 implements IMultiUserListener {
        public /* synthetic */ AonAuthManager this$0;

        @Override // com.oplusos.systemui.common.multiuser.IMultiUserListener
        public final void onSwitched(int i) {
            if (LogUtil.getNormalDebug() && LogUtil.isTagValid(LogUtil.TAG_NOTIFICATION)) {
                OplusBlackScreenGestureCallback$.ExternalSyntheticOutline0.m(i, "[onSwitched] newUserId: ", "AonAuthManager-->", "SystemUi--Notification");
            }
            AonAuthManager aonAuthManager = this.this$0;
            try {
                aonAuthManager.antiPeeping.destroy();
                aonAuthManager.context = aonAuthManager.context.createPackageContextAsUser(aonAuthManager.context.getPackageName(), 0, UserHandle.of(i));
                AonAuthManager.access$disableAon(aonAuthManager, "onUserSwitched");
                Result.constructor-impl(Unit.INSTANCE);
            } catch (Throwable th) {
                Result.constructor-impl(ResultKt.createFailure(th));
            }
        }
    }

    /* JADX INFO: renamed from: com.oplus.systemui.notification.aon.AonAuthManager$init$2, reason: invalid class name */
    /* JADX INFO: compiled from: go/retraceme fd74bf9514dd0739b92d67a6d48a51eb10aaf8df760d83496b8dcfcf83e5f698 */
    final class AnonymousClass2 extends SuspendLambda implements Function2 {
        private /* synthetic */ Object L$0;
        int label;

        /* JADX INFO: renamed from: com.oplus.systemui.notification.aon.AonAuthManager$init$2$1, reason: invalid class name */
        /* JADX INFO: compiled from: go/retraceme fd74bf9514dd0739b92d67a6d48a51eb10aaf8df760d83496b8dcfcf83e5f698 */
        final class AnonymousClass1 extends SuspendLambda implements Function2 {
            int label;
            final /* synthetic */ AonAuthManager this$0;

            /* JADX INFO: renamed from: com.oplus.systemui.notification.aon.AonAuthManager$init$2$1$1, reason: invalid class name and collision with other inner class name */
            /* JADX INFO: compiled from: go/retraceme fd74bf9514dd0739b92d67a6d48a51eb10aaf8df760d83496b8dcfcf83e5f698 */
            final class C00041 extends SuspendLambda implements Function2 {
                /* synthetic */ Object L$0;
                int label;
                final /* synthetic */ AonAuthManager this$0;

                /* JADX WARN: 'super' call moved to the top of the method (can break code semantics) */
                public C00041(AonAuthManager aonAuthManager, Continuation continuation) {
                    super(2, continuation);
                    this.this$0 = aonAuthManager;
                }

                public final Continuation create(Object obj, Continuation continuation) {
                    C00041 c00041 = new C00041(this.this$0, continuation);
                    c00041.L$0 = obj;
                    return c00041;
                }

                public final Object invoke(Object obj, Object obj2) {
                    return create((ScreenPowerState) obj, (Continuation) obj2).invokeSuspend(Unit.INSTANCE);
                }

                public final Object invokeSuspend(Object obj) {
                    ScreenPowerState screenPowerState = (ScreenPowerState) this.L$0;
                    IntrinsicsKt__IntrinsicsKt.getCOROUTINE_SUSPENDED();
                    if (this.label != 0) {
                        Path$.ExternalSyntheticBUOutline0.m$1("call to 'resume' before 'invoke' with coroutine");
                        return null;
                    }
                    ResultKt.throwOnFailure(obj);
                    if (ScreenPowerState.SCREEN_OFF == screenPowerState) {
                        this.this$0.closeCamera(true);
                    }
                    return Unit.INSTANCE;
                }
            }

            /* JADX WARN: 'super' call moved to the top of the method (can break code semantics) */
            public AnonymousClass1(AonAuthManager aonAuthManager, Continuation continuation) {
                super(2, continuation);
                this.this$0 = aonAuthManager;
            }

            public final Continuation create(Object obj, Continuation continuation) {
                return new AnonymousClass1(this.this$0, continuation);
            }

            public final Object invoke(Object obj, Object obj2) {
                return create((CoroutineScope) obj, (Continuation) obj2).invokeSuspend(Unit.INSTANCE);
            }

            public final Object invokeSuspend(Object obj) {
                Object coroutine_suspended = IntrinsicsKt__IntrinsicsKt.getCOROUTINE_SUSPENDED();
                int i = this.label;
                if (i == 0) {
                    ResultKt.throwOnFailure(obj);
                    StateFlow screenPowerState = this.this$0.powerInteractor.getScreenPowerState();
                    C00041 c00041 = new C00041(this.this$0, null);
                    this.label = 1;
                    if (FlowKt.collectLatest(screenPowerState, c00041, this) == coroutine_suspended) {
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

        /* JADX INFO: renamed from: com.oplus.systemui.notification.aon.AonAuthManager$init$2$2, reason: invalid class name and collision with other inner class name */
        /* JADX INFO: compiled from: go/retraceme fd74bf9514dd0739b92d67a6d48a51eb10aaf8df760d83496b8dcfcf83e5f698 */
        final class C00052 extends SuspendLambda implements Function2 {
            int label;
            final /* synthetic */ AonAuthManager this$0;

            /* JADX INFO: renamed from: com.oplus.systemui.notification.aon.AonAuthManager$init$2$2$1, reason: invalid class name */
            /* JADX INFO: compiled from: go/retraceme fd74bf9514dd0739b92d67a6d48a51eb10aaf8df760d83496b8dcfcf83e5f698 */
            final class AnonymousClass1 extends SuspendLambda implements Function2 {
                /* synthetic */ boolean Z$0;
                int label;
                final /* synthetic */ AonAuthManager this$0;

                /* JADX WARN: 'super' call moved to the top of the method (can break code semantics) */
                public AnonymousClass1(AonAuthManager aonAuthManager, Continuation continuation) {
                    super(2, continuation);
                    this.this$0 = aonAuthManager;
                }

                public final Continuation create(Object obj, Continuation continuation) {
                    AnonymousClass1 anonymousClass1 = new AnonymousClass1(this.this$0, continuation);
                    anonymousClass1.Z$0 = ((Boolean) obj).booleanValue();
                    return anonymousClass1;
                }

                public final Object invoke(Object obj, Object obj2) {
                    Boolean bool = (Boolean) obj;
                    bool.booleanValue();
                    return ((AnonymousClass1) create(bool, (Continuation) obj2)).invokeSuspend(Unit.INSTANCE);
                }

                public final Object invokeSuspend(Object obj) {
                    boolean z = this.Z$0;
                    IntrinsicsKt__IntrinsicsKt.getCOROUTINE_SUSPENDED();
                    if (this.label != 0) {
                        Path$.ExternalSyntheticBUOutline0.m$1("call to 'resume' before 'invoke' with coroutine");
                        return null;
                    }
                    ResultKt.throwOnFailure(obj);
                    if (z) {
                        if (LogUtil.getNormalDebug() && LogUtil.isTagValid(LogUtil.TAG_QS)) {
                            Log.d("SystemUi--QuickSettings", "AonAuthManager-->superPowerSaveMode: AON closeCamera");
                        }
                        AonAuthManager.access$disableAon(this.this$0, "superPowerMode");
                    }
                    return Unit.INSTANCE;
                }
            }

            /* JADX WARN: 'super' call moved to the top of the method (can break code semantics) */
            public C00052(AonAuthManager aonAuthManager, Continuation continuation) {
                super(2, continuation);
                this.this$0 = aonAuthManager;
            }

            public final Continuation create(Object obj, Continuation continuation) {
                return new C00052(this.this$0, continuation);
            }

            public final Object invoke(Object obj, Object obj2) {
                return create((CoroutineScope) obj, (Continuation) obj2).invokeSuspend(Unit.INSTANCE);
            }

            public final Object invokeSuspend(Object obj) {
                Object coroutine_suspended = IntrinsicsKt__IntrinsicsKt.getCOROUTINE_SUSPENDED();
                int i = this.label;
                if (i == 0) {
                    ResultKt.throwOnFailure(obj);
                    StateFlow superPowerSaveMode = this.this$0.superPowerRepository.getSuperPowerSaveMode();
                    AnonymousClass1 anonymousClass1 = new AnonymousClass1(this.this$0, null);
                    this.label = 1;
                    if (FlowKt.collectLatest(superPowerSaveMode, anonymousClass1, this) == coroutine_suspended) {
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

        /* JADX INFO: renamed from: com.oplus.systemui.notification.aon.AonAuthManager$init$2$3, reason: invalid class name */
        /* JADX INFO: compiled from: go/retraceme fd74bf9514dd0739b92d67a6d48a51eb10aaf8df760d83496b8dcfcf83e5f698 */
        final class AnonymousClass3 extends SuspendLambda implements Function2 {
            int label;
            final /* synthetic */ AonAuthManager this$0;

            /* JADX INFO: renamed from: com.oplus.systemui.notification.aon.AonAuthManager$init$2$3$1, reason: invalid class name */
            /* JADX INFO: compiled from: go/retraceme fd74bf9514dd0739b92d67a6d48a51eb10aaf8df760d83496b8dcfcf83e5f698 */
            final class AnonymousClass1 extends SuspendLambda implements Function2 {
                /* synthetic */ boolean Z$0;
                int label;
                final /* synthetic */ AonAuthManager this$0;

                /* JADX WARN: 'super' call moved to the top of the method (can break code semantics) */
                public AnonymousClass1(AonAuthManager aonAuthManager, Continuation continuation) {
                    super(2, continuation);
                    this.this$0 = aonAuthManager;
                }

                public final Continuation create(Object obj, Continuation continuation) {
                    AnonymousClass1 anonymousClass1 = new AnonymousClass1(this.this$0, continuation);
                    anonymousClass1.Z$0 = ((Boolean) obj).booleanValue();
                    return anonymousClass1;
                }

                public final Object invoke(Object obj, Object obj2) {
                    Boolean bool = (Boolean) obj;
                    bool.booleanValue();
                    return ((AnonymousClass1) create(bool, (Continuation) obj2)).invokeSuspend(Unit.INSTANCE);
                }

                public final Object invokeSuspend(Object obj) {
                    boolean z = this.Z$0;
                    IntrinsicsKt__IntrinsicsKt.getCOROUTINE_SUSPENDED();
                    if (this.label != 0) {
                        Path$.ExternalSyntheticBUOutline0.m$1("call to 'resume' before 'invoke' with coroutine");
                        return null;
                    }
                    ResultKt.throwOnFailure(obj);
                    if (z) {
                        if (LogUtil.getNormalDebug() && LogUtil.isTagValid(LogUtil.TAG_QS)) {
                            Log.d("SystemUi--QuickSettings", "AonAuthManager-->wellBeingMode: AON closeCamera");
                        }
                        AonAuthManager.access$disableAon(this.this$0, "wellBeingMode");
                    }
                    return Unit.INSTANCE;
                }
            }

            /* JADX WARN: 'super' call moved to the top of the method (can break code semantics) */
            public AnonymousClass3(AonAuthManager aonAuthManager, Continuation continuation) {
                super(2, continuation);
                this.this$0 = aonAuthManager;
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
                    StateFlow wellBeingAssistantMode = this.this$0.wellBeingRepository.getWellBeingAssistantMode();
                    AnonymousClass1 anonymousClass1 = new AnonymousClass1(this.this$0, null);
                    this.label = 1;
                    if (FlowKt.collectLatest(wellBeingAssistantMode, anonymousClass1, this) == coroutine_suspended) {
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

        public AnonymousClass2(Continuation continuation) {
            super(2, continuation);
        }

        public final Continuation create(Object obj, Continuation continuation) {
            AnonymousClass2 anonymousClass2 = AonAuthManager.this.new AnonymousClass2(continuation);
            anonymousClass2.L$0 = obj;
            return anonymousClass2;
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
            BuildersKt.launch$default(coroutineScope, (CoroutineContext) null, (CoroutineStart) null, new AnonymousClass1(AonAuthManager.this, null), 3);
            BuildersKt.launch$default(coroutineScope, (CoroutineContext) null, (CoroutineStart) null, new C00052(AonAuthManager.this, null), 3);
            BuildersKt.launch$default(coroutineScope, (CoroutineContext) null, (CoroutineStart) null, new AnonymousClass3(AonAuthManager.this, null), 3);
            return Unit.INSTANCE;
        }
    }

    public static AonRuleManager $r8$lambda$8Zx_j0GFnDZn7S7eKCEic1BdQe4(AonAuthManager aonAuthManager) {
        Context context = aonAuthManager.context;
        Handler handler = (Handler) aonAuthManager.mainHandler.get();
        AonAuthManager$aonHandler$1 aonAuthManager$aonHandler$1 = aonAuthManager.aonHandler;
        AonAuthManager$$ExternalSyntheticLambda3 aonAuthManager$$ExternalSyntheticLambda3 = new AonAuthManager$$ExternalSyntheticLambda3();
        aonAuthManager$$ExternalSyntheticLambda3.f$0 = aonAuthManager;
        VarHandle.storeStoreFence();
        return new AonRuleManager(context, handler, aonAuthManager$aonHandler$1, aonAuthManager$$ExternalSyntheticLambda3);
    }

    /* JADX INFO: renamed from: $r8$lambda$Ko2Uduz1ISZzdsFDoy_YzhUmU-Q, reason: not valid java name */
    public static Unit m51$r8$lambda$Ko2Uduz1ISZzdsFDoy_YzhUmUQ(AonAuthManager aonAuthManager, boolean z) {
        synchronized (aonAuthManager) {
            if (!z) {
                if (!aonAuthManager.aonHandler.getCameraIntentState()) {
                    aonAuthManager.setAuthState(null, false);
                }
            }
        }
        return Unit.INSTANCE;
    }

    /* JADX INFO: renamed from: $r8$lambda$XGifQ-e7873HQw0ayLsd82hnUVk, reason: not valid java name */
    public static FaceAuth m52$r8$lambda$XGifQe7873HQw0ayLsd82hnUVk(AonAuthManager aonAuthManager) {
        FaceAuth faceAuth = new FaceAuth(aonAuthManager.context);
        AonAuthManager$$ExternalSyntheticLambda0 aonAuthManager$$ExternalSyntheticLambda0 = new AonAuthManager$$ExternalSyntheticLambda0(3);
        aonAuthManager$$ExternalSyntheticLambda0.f$0 = aonAuthManager;
        VarHandle.storeStoreFence();
        faceAuth.setFaceAuthSuccessAction(aonAuthManager$$ExternalSyntheticLambda0);
        return faceAuth;
    }

    public static Unit $r8$lambda$a5fsIOMgQVzpFgI9HPjkVpOukZU(AonAuthManager aonAuthManager) {
        synchronized (aonAuthManager) {
            NotificationFeatureOption.INSTANCE.getClass();
            if (NotificationFeatureOption.isSoftwareAonSubCamEnable() && aonAuthManager.aonEventCallback.getLastAuthStateFromAonEvent()) {
                aonAuthManager.setAuthState(Boolean.TRUE, true);
            }
        }
        return Unit.INSTANCE;
    }

    public AonAuthManager(Context context, dagger.Lazy lazy, SuperPowerSaveRepository superPowerSaveRepository, WellBeingAssistantRepository wellBeingAssistantRepository, PowerInteractor powerInteractor, CoroutineScope coroutineScope) {
        this.context = context;
        this.mainHandler = lazy;
        this.superPowerRepository = superPowerSaveRepository;
        this.wellBeingRepository = wellBeingAssistantRepository;
        this.powerInteractor = powerInteractor;
        this.scope = coroutineScope;
        this.antiPeeping = new AntiPeeping(context);
        AonAuthManager$$ExternalSyntheticLambda0 aonAuthManager$$ExternalSyntheticLambda0 = new AonAuthManager$$ExternalSyntheticLambda0(0);
        aonAuthManager$$ExternalSyntheticLambda0.f$0 = this;
        VarHandle.storeStoreFence();
        this.aonBgHandler$delegate = LazyKt__LazyJVMKt.lazy(aonAuthManager$$ExternalSyntheticLambda0);
        AonAuthManager$$ExternalSyntheticLambda0 aonAuthManager$$ExternalSyntheticLambda1 = new AonAuthManager$$ExternalSyntheticLambda0(1);
        aonAuthManager$$ExternalSyntheticLambda1.f$0 = this;
        VarHandle.storeStoreFence();
        this.faceAuth$delegate = LazyKt__LazyJVMKt.lazy(aonAuthManager$$ExternalSyntheticLambda1);
        AonAuthManager$$ExternalSyntheticLambda0 aonAuthManager$$ExternalSyntheticLambda2 = new AonAuthManager$$ExternalSyntheticLambda0(2);
        aonAuthManager$$ExternalSyntheticLambda2.f$0 = this;
        VarHandle.storeStoreFence();
        this.aonRuleManager$delegate = LazyKt__LazyJVMKt.lazy(aonAuthManager$$ExternalSyntheticLambda2);
        this.authStateListeners = new CopyOnWriteArraySet<>();
        AonAuthManager$notifyListenersTask$1 aonAuthManager$notifyListenersTask$1 = new AonAuthManager$notifyListenersTask$1();
        aonAuthManager$notifyListenersTask$1.this$0 = this;
        VarHandle.storeStoreFence();
        this.notifyListenersTask = aonAuthManager$notifyListenersTask$1;
        this.aonEnableObserver = new AonEnableObserver() { // from class: com.oplus.systemui.notification.aon.AonAuthManager$aonEnableObserver$1
            @Override // com.oplus.systemui.notification.aon.AonEnableObserver, com.oplusos.systemui.common.observer.AbstractObserver
            public final void onChange(boolean z) {
                super.onChange(z);
                if (this.isAonEnabled) {
                    return;
                }
                AonAuthManager.access$disableAon(this.this$0, "switchOff");
            }
        };
        this.aonHandler = new AonAuthManager$aonHandler$1(this, getFaceAuth().getFaceAuthHandler$vendor__oplus__packages__apps__systemui__oplus_systemui__android_common__Oplus_SystemUI_application_domestic().getLooper());
        AonAuthManager$aonEventCallback$1 aonAuthManager$aonEventCallback$1 = new AonAuthManager$aonEventCallback$1();
        aonAuthManager$aonEventCallback$1.this$0 = this;
        aonAuthManager$aonEventCallback$1.eventOld = -1;
        VarHandle.storeStoreFence();
        this.aonEventCallback = aonAuthManager$aonEventCallback$1;
    }

    public static final void access$disableAon(AonAuthManager aonAuthManager, String str) {
        aonAuthManager.getClass();
        LogUtil.i$default(LogUtil.TAG_NOTIFICATION, TAG, "[disableAon] reason=".concat(str), null, 8);
        aonAuthManager.closeCamera(true);
        aonAuthManager.getAonRuleManager().reset();
    }

    public static final void access$startAntiPeeping(AonAuthManager aonAuthManager) {
        Object obj;
        int iRegisterListener;
        try {
            if (aonAuthManager.antiPeeping.prepareService() == 0) {
                AntiPeeping antiPeeping = aonAuthManager.antiPeeping;
                AonAuthManager$aonEventCallback$1 aonAuthManager$aonEventCallback$1 = aonAuthManager.aonEventCallback;
                int iStart = 4100;
                if (aonAuthManager$aonEventCallback$1 == null) {
                    antiPeeping.getClass();
                    iRegisterListener = 8193;
                } else if (((AONUnit) antiPeeping).service == null) {
                    iRegisterListener = 4099;
                } else {
                    antiPeeping.mAONEventCallback = aonAuthManager$aonEventCallback$1;
                    CVLog.i("AntiPeeping", "register");
                    try {
                        try {
                            iRegisterListener = ((AONUnit) antiPeeping).service.registerListener(antiPeeping.aonlistener, 393219);
                        } catch (RemoteException e) {
                            e.printStackTrace();
                            iRegisterListener = 4100;
                        }
                    } catch (Exception e2) {
                        e2.printStackTrace();
                        iRegisterListener = 4100;
                    }
                }
                if (LogUtil.getNormalDebug() && LogUtil.isTagValid(LogUtil.TAG_NOTIFICATION)) {
                    Log.d("SystemUi--Notification", "AonAuthManager-->".concat("[registerAntiPeeping] register result: " + iRegisterListener));
                }
                if (iRegisterListener == 0) {
                    AntiPeeping antiPeeping2 = aonAuthManager.antiPeeping;
                    antiPeeping2.getClass();
                    CVLog.i("AntiPeeping", "start");
                    IAONService iAONService = ((AONUnit) antiPeeping2).service;
                    if (iAONService == null) {
                        iStart = 4099;
                    } else {
                        try {
                            iStart = iAONService.start(393219);
                        } catch (RemoteException e3) {
                            e3.printStackTrace();
                        } catch (Exception e4) {
                            e4.printStackTrace();
                        }
                    }
                    if (LogUtil.getNormalDebug() && LogUtil.isTagValid(LogUtil.TAG_NOTIFICATION)) {
                        Log.d("SystemUi--Notification", "AonAuthManager-->".concat("[registerAntiPeeping] start result: " + iStart));
                    }
                    aonAuthManager.cameraOpening = iStart == 0;
                    NotificationFeatureOption.INSTANCE.getClass();
                    if (NotificationFeatureOption.isSoftwareAonSubCamEnable() && aonAuthManager.cameraOpening) {
                        FaceAuth faceAuth = aonAuthManager.getFaceAuth();
                        faceAuth.getClass();
                        if (LogUtil.getNormalDebug() && LogUtil.isTagValid(LogUtil.TAG_NOTIFICATION)) {
                            Log.d("SystemUi--Notification", "FaceAuth-->[startFaceAuth]");
                        }
                        faceAuth.getFaceAuthHandler$vendor__oplus__packages__apps__systemui__oplus_systemui__android_common__Oplus_SystemUI_application_domestic().removeMessages(0);
                        faceAuth.getFaceAuthHandler$vendor__oplus__packages__apps__systemui__oplus_systemui__android_common__Oplus_SystemUI_application_domestic().removeMessages(1);
                        faceAuth.getFaceAuthHandler$vendor__oplus__packages__apps__systemui__oplus_systemui__android_common__Oplus_SystemUI_application_domestic().obtainMessage(0).sendToTarget();
                    }
                }
            } else {
                AonAuthManager$aonHandler$1 aonAuthManager$aonHandler$1 = aonAuthManager.aonHandler;
                aonAuthManager$aonHandler$1.sendMessageAtFrontOfQueue(aonAuthManager$aonHandler$1.obtainMessage(0));
                AonAuthManager$aonHandler$1 aonAuthManager$aonHandler$2 = aonAuthManager.aonHandler;
                aonAuthManager$aonHandler$2.sendMessageAtFrontOfQueue(aonAuthManager$aonHandler$2.obtainMessage(-1));
                if (LogUtil.getNormalDebug() && LogUtil.isTagValid(LogUtil.TAG_NOTIFICATION)) {
                    Log.d("SystemUi--Notification", "AonAuthManager-->[registerAntiPeeping] service is not ready");
                }
            }
            obj = Result.constructor-impl(Unit.INSTANCE);
        } catch (Throwable th) {
            obj = Result.constructor-impl(ResultKt.createFailure(th));
        }
        Throwable th2 = Result.exceptionOrNull-impl(obj);
        if (th2 != null) {
            GestureInteractor$getTopActivity$2$.ExternalSyntheticOutline0.m(8, "[registerAntiPeeping] ", th2.getMessage(), LogUtil.TAG_NOTIFICATION, TAG);
        }
    }

    public static final void access$stopAntiPeeping(AonAuthManager aonAuthManager, Message message) {
        Object obj;
        Unit unitValueOf;
        int iStop;
        try {
            if (aonAuthManager.antiPeeping.prepareService() == 0) {
                AntiPeeping antiPeeping = aonAuthManager.antiPeeping;
                antiPeeping.getClass();
                CVLog.i("AntiPeeping", "stop");
                IAONService iAONService = ((AONUnit) antiPeeping).service;
                int iUnRegisterListener = 4100;
                if (iAONService == null) {
                    iStop = 4099;
                } else {
                    try {
                        try {
                            iStop = iAONService.stop(393219);
                        } catch (RemoteException e) {
                            e.printStackTrace();
                            iStop = 4100;
                        }
                    } catch (Exception e2) {
                        e2.printStackTrace();
                        iStop = 4100;
                    }
                }
                if (LogUtil.getNormalDebug() && LogUtil.isTagValid(LogUtil.TAG_NOTIFICATION)) {
                    Log.d("SystemUi--Notification", "AonAuthManager-->".concat("[unRegisterAntiPeeping] stop result: " + iStop));
                }
                AntiPeeping antiPeeping2 = aonAuthManager.antiPeeping;
                antiPeeping2.getClass();
                CVLog.i("AntiPeeping", "unRegister");
                IAONService iAONService2 = ((AONUnit) antiPeeping2).service;
                if (iAONService2 == null) {
                    iUnRegisterListener = 4099;
                } else {
                    try {
                        try {
                            iUnRegisterListener = iAONService2.unRegisterListener(antiPeeping2.aonlistener, 393219);
                        } catch (Exception e3) {
                            e3.printStackTrace();
                        }
                    } catch (RemoteException e4) {
                        e4.printStackTrace();
                    }
                }
                if (LogUtil.getNormalDebug() && LogUtil.isTagValid(LogUtil.TAG_NOTIFICATION)) {
                    Log.d("SystemUi--Notification", "AonAuthManager-->".concat("[unRegisterAntiPeeping] unregister result: " + iUnRegisterListener));
                }
                unitValueOf = Unit.INSTANCE;
            } else {
                if (LogUtil.getNormalDebug() && LogUtil.isTagValid(LogUtil.TAG_NOTIFICATION)) {
                    Log.d("SystemUi--Notification", "AonAuthManager-->unRegisterAntiPeeping service not prepare");
                }
                aonAuthManager.aonHandler.sendMessageAtFrontOfQueue(Message.obtain(message));
                AonAuthManager$aonHandler$1 aonAuthManager$aonHandler$1 = aonAuthManager.aonHandler;
                unitValueOf = Boolean.valueOf(aonAuthManager$aonHandler$1.sendMessageAtFrontOfQueue(aonAuthManager$aonHandler$1.obtainMessage(-1)));
            }
            obj = Result.constructor-impl(unitValueOf);
        } catch (Throwable th) {
            obj = Result.constructor-impl(ResultKt.createFailure(th));
        }
        if (Result.exceptionOrNull-impl(obj) != null) {
            LogUtil.e$default(8, LogUtil.TAG_NOTIFICATION, TAG, "unRegisterAntiPeeping Exception");
        }
        aonAuthManager.cameraOpening = false;
        aonAuthManager.aonEventCallback.setLastAuthStateFromAonEvent(false);
        aonAuthManager.aonEventCallback.setEventOld(-1);
        NotificationFeatureOption.INSTANCE.getClass();
        if (NotificationFeatureOption.isSoftwareAonSubCamEnable()) {
            aonAuthManager.getFaceAuth().stopFaceAuth();
        }
    }

    private final AonRuleManager getAonRuleManager() {
        return (AonRuleManager) this.aonRuleManager$delegate.getValue();
    }

    /* JADX INFO: Access modifiers changed from: private */
    public final FaceAuth getFaceAuth() {
        return (FaceAuth) this.faceAuth$delegate.getValue();
    }

    private final void setAonAuthResult(Boolean bool) {
        if (Intrinsics.areEqual(this.aonAuthResult, bool)) {
            return;
        }
        if (LogUtil.getNormalDebug() && LogUtil.isTagValid(LogUtil.TAG_NOTIFICATION)) {
            Log.d("SystemUi--Notification", "AonAuthManager-->".concat("[setAuthState]" + bool + ", lastState=" + this.aonAuthResult));
        }
        this.aonAuthResult = bool;
        ((Handler) this.mainHandler.get()).removeCallbacks(this.notifyListenersTask);
        if (Intrinsics.areEqual(Looper.myLooper(), Looper.getMainLooper())) {
            this.notifyListenersTask.run();
        } else {
            ((Handler) this.mainHandler.get()).post(this.notifyListenersTask);
        }
    }

    public final void addAonAuthStateListener(IAonAuthStateListener iAonAuthStateListener) {
        this.authStateListeners.add(iAonAuthStateListener);
    }

    public final synchronized void closeCamera(boolean z) {
        try {
            if (LogUtil.getNormalDebug() && LogUtil.isTagValid(LogUtil.TAG_NOTIFICATION)) {
                Log.d("SystemUi--Notification", "AonAuthManager-->[closeCamera]");
            }
            this.aonHandler.removeMessages(0);
            this.aonHandler.removeMessages(1);
            AonAuthManager$aonHandler$1 aonAuthManager$aonHandler$1 = this.aonHandler;
            aonAuthManager$aonHandler$1.sendMessage(aonAuthManager$aonHandler$1.obtainMessage(1, z ? 1 : 0, 0));
        } catch (Throwable th) {
            throw th;
        }
    }

    public final void dump(FileDescriptor fileDescriptor, PrintWriter printWriter) {
        getAonRuleManager().dump(fileDescriptor, printWriter);
    }

    public final Boolean getAonAuthResult() {
        return this.aonAuthResult;
    }

    public final Handler getAonBgHandler() {
        return (Handler) this.aonBgHandler$delegate.getValue();
    }

    public final boolean getCameraOpening() {
        return this.cameraOpening;
    }

    public final void init() {
        this.aonEnableObserver.registerForUserSwitch(this.context, false);
        NotificationFeatureOption.INSTANCE.getClass();
        if (NotificationFeatureOption.isHardwareAonEnable()) {
            MultiUserManager companion = MultiUserManager.Companion.getInstance();
            AnonymousClass1 anonymousClass1 = new AnonymousClass1();
            anonymousClass1.this$0 = this;
            VarHandle.storeStoreFence();
            companion.addListener(anonymousClass1);
        }
        BuildersKt.launch$default(this.scope, (CoroutineContext) null, (CoroutineStart) null, new AnonymousClass2(null), 3);
    }

    public final boolean isAonEnable() {
        if (!this.aonEnableObserver.isAonEnabled) {
            return false;
        }
        NotificationFeatureOption.INSTANCE.getClass();
        return (!NotificationFeatureOption.isHardwareAonEnable() || NotificationFeatureOption.isAntPeepingDisable() || this.superPowerRepository.getInSuperPowerSaveMode() || this.wellBeingRepository.getInWellBeingAssistantMode()) ? false : true;
    }

    public final void markNotificationIncreased() {
        if (isAonEnable()) {
            AonRuleManager aonRuleManager = getAonRuleManager();
            synchronized (aonRuleManager) {
                if (aonRuleManager.isEnabled) {
                    aonRuleManager.notificationCount++;
                    if (LogUtil.getNormalDebug() && LogUtil.isTagValid(LogUtil.TAG_NOTIFICATION)) {
                        Log.d("SystemUi--Notification", "AonRuleManager-->".concat("[markNotificationIncreased] " + aonRuleManager.notificationCount));
                    }
                }
            }
        }
    }

    public final synchronized void openCamera() {
        try {
            if (isAonEnable()) {
                if (LogUtil.getNormalDebug() && LogUtil.isTagValid(LogUtil.TAG_NOTIFICATION)) {
                    Log.d("SystemUi--Notification", "AonAuthManager-->[openCamera]");
                }
                this.aonHandler.removeMessages(0);
                this.aonHandler.removeMessages(1);
                this.aonHandler.sendEmptyMessage(0);
            }
        } catch (Throwable th) {
            throw th;
        }
    }

    public final void removeAonAuthStateListener(IAonAuthStateListener iAonAuthStateListener) {
        this.authStateListeners.remove(iAonAuthStateListener);
    }

    public final synchronized void setAuthState(Boolean bool, boolean z) {
        if (bool == null && z) {
            try {
                getAonRuleManager().trySetCooling();
            } catch (Throwable th) {
                throw th;
            }
        }
        if (bool == null) {
            bool = getAonRuleManager().getCooling() ? this.aonAuthResult : null;
        }
        setAonAuthResult(bool);
    }
}
