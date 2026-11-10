package com.oplus.systemui.navigationbar.gesture.otherbusiness;

import android.content.Context;
import android.util.Log;
import com.android.systemui.keyguard.OplusBlackScreenGestureCallback$$ExternalSyntheticOutline0;
import com.oplus.systemui.biometrics.finger.udfps.fpquicklaunch.quicklaunch.helper.ActivityStartedHelper;
import com.oplus.systemui.navigationbar.observer.SpeedChassistMainObserver;
import com.oplus.systemui.navigationbar.utils.LauncherCompatVersionUtil;
import com.oplusos.systemui.common.observer.IObserverListener;
import com.oplusos.systemui.common.util.LogUtil;
import java.lang.invoke.VarHandle;
import kotlin.ResultKt;
import kotlin.Unit;
import kotlin.coroutines.Continuation;
import kotlin.coroutines.intrinsics.IntrinsicsKt__IntrinsicsKt;
import kotlin.coroutines.jvm.internal.SuspendLambda;
import kotlin.jvm.functions.Function2;
import kotlinx.coroutines.BuildersKt;
import kotlinx.coroutines.CoroutineScope;
import kotlinx.coroutines.CoroutineScopeKt;
import kotlinx.coroutines.Dispatchers;
import kotlinx.coroutines.MainCoroutineDispatcher;
import okio.Path$$ExternalSyntheticBUOutline0;

/* JADX INFO: compiled from: go/retraceme fd74bf9514dd0739b92d67a6d48a51eb10aaf8df760d83496b8dcfcf83e5f698 */
/* JADX INFO: loaded from: classes4.dex */
public final class SpeedChassistMainBusiness implements IOtherBusiness, GestureHomeHandleEventController.HomeHandleTouchEvent {
    public static final Companion Companion = new Companion();
    public static final String TAG = "SpeedChassistMainBusiness";
    private final Context mContext;
    private final IObserverListener speedChassistListener;

    /* JADX INFO: compiled from: go/retraceme fd74bf9514dd0739b92d67a6d48a51eb10aaf8df760d83496b8dcfcf83e5f698 */
    public final class Companion {
    }

    /* JADX INFO: renamed from: com.oplus.systemui.navigationbar.gesture.otherbusiness.SpeedChassistMainBusiness$init$1, reason: invalid class name */
    /* JADX INFO: compiled from: go/retraceme fd74bf9514dd0739b92d67a6d48a51eb10aaf8df760d83496b8dcfcf83e5f698 */
    final class AnonymousClass1 extends SuspendLambda implements Function2 {
        int I$0;
        int label;

        /* JADX INFO: renamed from: com.oplus.systemui.navigationbar.gesture.otherbusiness.SpeedChassistMainBusiness$init$1$2, reason: invalid class name */
        /* JADX INFO: compiled from: go/retraceme fd74bf9514dd0739b92d67a6d48a51eb10aaf8df760d83496b8dcfcf83e5f698 */
        final class AnonymousClass2 extends SuspendLambda implements Function2 {
            int label;
            final /* synthetic */ SpeedChassistMainBusiness this$0;

            /* JADX WARN: 'super' call moved to the top of the method (can break code semantics) */
            public AnonymousClass2(SpeedChassistMainBusiness speedChassistMainBusiness, Continuation continuation) {
                super(2, continuation);
                this.this$0 = speedChassistMainBusiness;
            }

            @Override // kotlin.coroutines.jvm.internal.BaseContinuationImpl
            public final Continuation create(Object obj, Continuation continuation) {
                return new AnonymousClass2(this.this$0, continuation);
            }

            @Override // kotlin.jvm.functions.Function2
            public final Object invoke(Object obj, Object obj2) {
                return ((AnonymousClass2) create((CoroutineScope) obj, (Continuation) obj2)).invokeSuspend(Unit.INSTANCE);
            }

            @Override // kotlin.coroutines.jvm.internal.BaseContinuationImpl
            public final Object invokeSuspend(Object obj) throws Throwable {
                IntrinsicsKt__IntrinsicsKt.getCOROUTINE_SUSPENDED();
                if (this.label != 0) {
                    Path$$ExternalSyntheticBUOutline0.m$1("call to 'resume' before 'invoke' with coroutine");
                    return null;
                }
                ResultKt.throwOnFailure(obj);
                SpeedChassistMainObserver speedChassistMainObserver = SpeedChassistMainObserver.INSTANCE;
                speedChassistMainObserver.registerForUserSwitch(this.this$0.getMContext(), false);
                speedChassistMainObserver.addListener(this.this$0.speedChassistListener);
                if (speedChassistMainObserver.getSpeedChassistEnableStatus()) {
                    if (LogUtil.getNormalDebug() && LogUtil.isTagValid(LogUtil.TAG_NAVBAR)) {
                        Log.d("SystemUi--NavBar", "SpeedChassistMainBusiness-->initSpeedChassist addHomeHandlerLongClick  ");
                    }
                    GestureHomeHandleEventController.INSTANCE.addHomeHandlerLongClickListener(this.this$0);
                }
                return Unit.INSTANCE;
            }
        }

        public AnonymousClass1(Continuation continuation) {
            super(2, continuation);
        }

        @Override // kotlin.coroutines.jvm.internal.BaseContinuationImpl
        public final Continuation create(Object obj, Continuation continuation) {
            return SpeedChassistMainBusiness.this.new AnonymousClass1(continuation);
        }

        @Override // kotlin.jvm.functions.Function2
        public final Object invoke(Object obj, Object obj2) {
            return ((AnonymousClass1) create((CoroutineScope) obj, (Continuation) obj2)).invokeSuspend(Unit.INSTANCE);
        }

        @Override // kotlin.coroutines.jvm.internal.BaseContinuationImpl
        public final Object invokeSuspend(Object obj) throws Throwable {
            Object coroutine_suspended = IntrinsicsKt__IntrinsicsKt.getCOROUTINE_SUSPENDED();
            int i = this.label;
            if (i == 0) {
                ResultKt.throwOnFailure(obj);
                int launcherCompatVersion = LauncherCompatVersionUtil.getLauncherCompatVersion(SpeedChassistMainBusiness.this.getMContext());
                if (LogUtil.getNormalDebug() && LogUtil.isTagValid(LogUtil.TAG_NAVBAR)) {
                    OplusBlackScreenGestureCallback$$ExternalSyntheticOutline0.m(launcherCompatVersion, "launcher_version =  ", "SpeedChassistMainBusiness-->", "SystemUi--NavBar");
                }
                if (launcherCompatVersion >= 3) {
                    MainCoroutineDispatcher main = Dispatchers.getMain();
                    AnonymousClass2 anonymousClass2 = new AnonymousClass2(SpeedChassistMainBusiness.this, null);
                    this.I$0 = launcherCompatVersion;
                    this.label = 1;
                    if (BuildersKt.withContext(main, anonymousClass2, this) == coroutine_suspended) {
                        return coroutine_suspended;
                    }
                }
            } else {
                if (i != 1) {
                    Path$$ExternalSyntheticBUOutline0.m$1("call to 'resume' before 'invoke' with coroutine");
                    return null;
                }
                ResultKt.throwOnFailure(obj);
            }
            return Unit.INSTANCE;
        }
    }

    public SpeedChassistMainBusiness(Context context) {
        this.mContext = context;
        SpeedChassistMainBusiness$speedChassistListener$1 speedChassistMainBusiness$speedChassistListener$1 = new SpeedChassistMainBusiness$speedChassistListener$1();
        speedChassistMainBusiness$speedChassistListener$1.this$0 = this;
        VarHandle.storeStoreFence();
        this.speedChassistListener = speedChassistMainBusiness$speedChassistListener$1;
    }

    public final Context getMContext() {
        return this.mContext;
    }

    @Override // com.oplus.systemui.navigationbar.gesture.otherbusiness.IOtherBusiness
    public final void init() {
        BuildersKt.launch$default(CoroutineScopeKt.CoroutineScope(Dispatchers.getIO()), null, null, new AnonymousClass1(null), 3);
    }

    @Override // com.oplus.systemui.navigationbar.gesture.otherbusiness.GestureHomeHandleEventController.HomeHandleTouchEvent
    public final void onLongPressed() {
        if (LogUtil.getNormalDebug() && LogUtil.isTagValid(LogUtil.TAG_NAVBAR)) {
            Log.d("SystemUi--NavBar", "SpeedChassistMainBusiness-->SpeedChassistMainBusiness  onHomeHandlerLongPress");
        }
        ActivityStartedHelper.INSTANCE.startBreenoService(this.mContext, 91);
    }

    @Override // com.oplus.systemui.navigationbar.gesture.otherbusiness.IOtherBusiness
    public final void release() {
        GestureHomeHandleEventController.INSTANCE.removeHomeHandlerLongClickListener(this);
        SpeedChassistMainObserver speedChassistMainObserver = SpeedChassistMainObserver.INSTANCE;
        speedChassistMainObserver.unregister(this.mContext);
        speedChassistMainObserver.removeListener(this.speedChassistListener);
    }
}
