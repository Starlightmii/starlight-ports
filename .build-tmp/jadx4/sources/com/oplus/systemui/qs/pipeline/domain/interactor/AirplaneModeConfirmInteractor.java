package com.oplus.systemui.qs.pipeline.domain.interactor;

import android.content.Context;
import android.content.DialogInterface;
import androidx.appcompat.app.AlertDialog;
import com.android.keyguard.logging.KeyguardLogger$;
import com.android.systemui.keyguard.domain.interactor.KeyguardInteractor;
import com.android.systemui.statusbar.phone.SystemUIDialog;
import com.android.systemui.statusbar.pipeline.airplane.data.repository.AirplaneModeRepository;
import com.coui.appcompat.dialog.COUIAlertDialogBuilder;
import com.oplus.systemui.qs.pipeline.data.repository.AirplaneModeConfirmRepository;
import com.oplusos.systemui.common.util.LogUtil;
import java.lang.invoke.VarHandle;
import java.util.concurrent.atomic.AtomicBoolean;
import kotlin.ResultKt;
import kotlin.Unit;
import kotlin.coroutines.Continuation;
import kotlin.coroutines.CoroutineContext;
import kotlin.coroutines.intrinsics.IntrinsicsKt__IntrinsicsKt;
import kotlin.coroutines.jvm.internal.ContinuationImpl;
import kotlin.coroutines.jvm.internal.SuspendLambda;
import kotlin.jvm.functions.Function2;
import kotlin.text.Regex$;
import kotlinx.coroutines.BuildersKt;
import kotlinx.coroutines.CoroutineScope;
import kotlinx.coroutines.CoroutineStart;
import kotlinx.coroutines.flow.SharedFlow;
import okio.Path$;

/* JADX INFO: compiled from: go/retraceme fd74bf9514dd0739b92d67a6d48a51eb10aaf8df760d83496b8dcfcf83e5f698 */
/* JADX INFO: loaded from: /home/starlight/Projects/Starlight Ports/.build-tmp/classes4.dex */
public final class AirplaneModeConfirmInteractor implements DialogInterface.OnClickListener {
    public static final Companion Companion = new Companion();
    private static final String TAG = "AirplaneModeConfirmInteractor";
    private AlertDialog airplaneConfirmDialog;
    private final AirplaneModeConfirmRepository airplaneModeConfirmRepository;
    private final AirplaneModeRepository airplaneModeRepository;
    private final Context context;
    private final AtomicBoolean initialized = new AtomicBoolean(false);
    private final KeyguardInteractor keyguardInteractor;
    private final CoroutineContext mainCoroutineContext;
    private final CoroutineScope scope;

    /* JADX INFO: compiled from: go/retraceme fd74bf9514dd0739b92d67a6d48a51eb10aaf8df760d83496b8dcfcf83e5f698 */
    public final class Companion {
    }

    /* JADX INFO: renamed from: com.oplus.systemui.qs.pipeline.domain.interactor.AirplaneModeConfirmInteractor$init$1, reason: invalid class name */
    /* JADX INFO: compiled from: go/retraceme fd74bf9514dd0739b92d67a6d48a51eb10aaf8df760d83496b8dcfcf83e5f698 */
    final class AnonymousClass1 extends SuspendLambda implements Function2 {
        private /* synthetic */ Object L$0;
        int label;

        /* JADX INFO: renamed from: com.oplus.systemui.qs.pipeline.domain.interactor.AirplaneModeConfirmInteractor$init$1$1, reason: invalid class name and collision with other inner class name */
        /* JADX INFO: compiled from: go/retraceme fd74bf9514dd0739b92d67a6d48a51eb10aaf8df760d83496b8dcfcf83e5f698 */
        final class C00841 extends SuspendLambda implements Function2 {
            int label;
            final /* synthetic */ AirplaneModeConfirmInteractor this$0;

            /* JADX WARN: 'super' call moved to the top of the method (can break code semantics) */
            public C00841(AirplaneModeConfirmInteractor airplaneModeConfirmInteractor, Continuation continuation) {
                super(2, continuation);
                this.this$0 = airplaneModeConfirmInteractor;
            }

            public final Continuation create(Object obj, Continuation continuation) {
                return new C00841(this.this$0, continuation);
            }

            public final Object invoke(Object obj, Object obj2) {
                return create((CoroutineScope) obj, (Continuation) obj2).invokeSuspend(Unit.INSTANCE);
            }

            public final Object invokeSuspend(Object obj) {
                Object coroutine_suspended = IntrinsicsKt__IntrinsicsKt.getCOROUTINE_SUSPENDED();
                int i = this.label;
                if (i == 0) {
                    ResultKt.throwOnFailure(obj);
                    AirplaneModeConfirmInteractor airplaneModeConfirmInteractor = this.this$0;
                    this.label = 1;
                    if (AirplaneModeConfirmInteractor.access$collectAirplaneModeConfirmEvent(airplaneModeConfirmInteractor, this) == coroutine_suspended) {
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

        public AnonymousClass1(Continuation continuation) {
            super(2, continuation);
        }

        public final Continuation create(Object obj, Continuation continuation) {
            AnonymousClass1 anonymousClass1 = AirplaneModeConfirmInteractor.this.new AnonymousClass1(continuation);
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
            BuildersKt.launch$default(coroutineScope, AirplaneModeConfirmInteractor.this.mainCoroutineContext, (CoroutineStart) null, new C00841(AirplaneModeConfirmInteractor.this, null), 2);
            return Unit.INSTANCE;
        }
    }

    public AirplaneModeConfirmInteractor(Context context, CoroutineScope coroutineScope, CoroutineContext coroutineContext, KeyguardInteractor keyguardInteractor, AirplaneModeRepository airplaneModeRepository, AirplaneModeConfirmRepository airplaneModeConfirmRepository) {
        this.context = context;
        this.scope = coroutineScope;
        this.mainCoroutineContext = coroutineContext;
        this.keyguardInteractor = keyguardInteractor;
        this.airplaneModeRepository = airplaneModeRepository;
        this.airplaneModeConfirmRepository = airplaneModeConfirmRepository;
    }

    /* JADX WARN: Code duplicated, block: B:7:0x0016  */
    public static final Object access$collectAirplaneModeConfirmEvent(AirplaneModeConfirmInteractor airplaneModeConfirmInteractor, ContinuationImpl continuationImpl) {
        AirplaneModeConfirmInteractor$collectAirplaneModeConfirmEvent$1 airplaneModeConfirmInteractor$collectAirplaneModeConfirmEvent$1;
        airplaneModeConfirmInteractor.getClass();
        if (continuationImpl instanceof AirplaneModeConfirmInteractor$collectAirplaneModeConfirmEvent$1) {
            airplaneModeConfirmInteractor$collectAirplaneModeConfirmEvent$1 = (AirplaneModeConfirmInteractor$collectAirplaneModeConfirmEvent$1) continuationImpl;
            int i = airplaneModeConfirmInteractor$collectAirplaneModeConfirmEvent$1.label;
            if ((i & Integer.MIN_VALUE) != 0) {
                airplaneModeConfirmInteractor$collectAirplaneModeConfirmEvent$1.label = i - Integer.MIN_VALUE;
            } else {
                airplaneModeConfirmInteractor$collectAirplaneModeConfirmEvent$1 = new AirplaneModeConfirmInteractor$collectAirplaneModeConfirmEvent$1(airplaneModeConfirmInteractor, continuationImpl);
            }
        } else {
            airplaneModeConfirmInteractor$collectAirplaneModeConfirmEvent$1 = new AirplaneModeConfirmInteractor$collectAirplaneModeConfirmEvent$1(airplaneModeConfirmInteractor, continuationImpl);
        }
        Object obj = airplaneModeConfirmInteractor$collectAirplaneModeConfirmEvent$1.result;
        Object coroutine_suspended = IntrinsicsKt__IntrinsicsKt.getCOROUTINE_SUSPENDED();
        int i2 = airplaneModeConfirmInteractor$collectAirplaneModeConfirmEvent$1.label;
        if (i2 == 0) {
            ResultKt.throwOnFailure(obj);
            SharedFlow airplaneModeConfirmEvent = airplaneModeConfirmInteractor.airplaneModeConfirmRepository.getAirplaneModeConfirmEvent();
            AirplaneModeConfirmInteractor$collectAirplaneModeConfirmEvent$2 airplaneModeConfirmInteractor$collectAirplaneModeConfirmEvent$2 = new AirplaneModeConfirmInteractor$collectAirplaneModeConfirmEvent$2();
            airplaneModeConfirmInteractor$collectAirplaneModeConfirmEvent$2.this$0 = airplaneModeConfirmInteractor;
            VarHandle.storeStoreFence();
            airplaneModeConfirmInteractor$collectAirplaneModeConfirmEvent$1.label = 1;
            if (airplaneModeConfirmEvent.collect(airplaneModeConfirmInteractor$collectAirplaneModeConfirmEvent$2, airplaneModeConfirmInteractor$collectAirplaneModeConfirmEvent$1) == coroutine_suspended) {
                return coroutine_suspended;
            }
        } else {
            if (i2 != 1) {
                Path$.ExternalSyntheticBUOutline0.m$1("call to 'resume' before 'invoke' with coroutine");
                return null;
            }
            ResultKt.throwOnFailure(obj);
        }
        Regex$.ExternalSyntheticBUOutline0.m$1();
        return null;
    }

    public static final void access$showDialog(AirplaneModeConfirmInteractor airplaneModeConfirmInteractor) {
        airplaneModeConfirmInteractor.getClass();
        LogUtil.i$default(LogUtil.TAG_QS, TAG, "showDialog: ", null, 8);
        AlertDialog alertDialogCreate = airplaneModeConfirmInteractor.airplaneConfirmDialog;
        if (alertDialogCreate == null) {
            COUIAlertDialogBuilder cOUIAlertDialogBuilder = new COUIAlertDialogBuilder(airplaneModeConfirmInteractor.context, 2132017612);
            cOUIAlertDialogBuilder.setPositiveButton(2131955317, airplaneModeConfirmInteractor);
            cOUIAlertDialogBuilder.setNegativeButton(2131955316, airplaneModeConfirmInteractor);
            cOUIAlertDialogBuilder.setMessage(2131955315).setTitle(2131955047);
            alertDialogCreate = cOUIAlertDialogBuilder.create();
            alertDialogCreate.setCancelable(false);
            alertDialogCreate.setCanceledOnTouchOutside(false);
            if (alertDialogCreate.getWindow() != null) {
                SystemUIDialog.setWindowOnTop(alertDialogCreate, airplaneModeConfirmInteractor.keyguardInteractor.isKeyguardCurrentlyShowing());
            }
        }
        alertDialogCreate.show();
        airplaneModeConfirmInteractor.airplaneConfirmDialog = alertDialogCreate;
    }

    private final void setEnabled(boolean z) {
        LogUtil.i$default(LogUtil.TAG_QS, TAG, KeyguardLogger$.ExternalSyntheticOutline0.m("setEnabled: ", z), null, 8);
        BuildersKt.launch$default(this.scope, (CoroutineContext) null, (CoroutineStart) null, new setEnabled.1(this, z, (Continuation) null), 3);
    }

    public final void init() {
        if (this.initialized.compareAndSet(false, true)) {
            this.airplaneModeConfirmRepository.init();
            BuildersKt.launch$default(this.scope, (CoroutineContext) null, (CoroutineStart) null, new AnonymousClass1(null), 3);
        }
    }

    @Override // android.content.DialogInterface.OnClickListener
    public void onClick(DialogInterface dialogInterface, int i) {
        setEnabled(-1 == i);
        LogUtil.i$default(LogUtil.TAG_QS, TAG, "dismissDialog: ", null, 8);
        AlertDialog alertDialog = this.airplaneConfirmDialog;
        if (alertDialog != null) {
            alertDialog.dismiss();
        }
        this.airplaneConfirmDialog = null;
    }
}
