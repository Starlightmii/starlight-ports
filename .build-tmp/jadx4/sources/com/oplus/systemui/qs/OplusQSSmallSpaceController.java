package com.oplus.systemui.qs;

import android.content.Context;
import com.android.compose.nestedscroll.PriorityNestedScrollConnection$;
import com.android.keyguard.logging.KeyguardLogger$;
import com.android.systemui.qs.logging.QSLogger;
import com.android.systemui.statusbar.policy.ConfigurationController;
import com.android.systemui.util.LargeScreenUtils;
import com.oplus.systemui.separate.OplusSeparateNotificationAndQSState;
import com.oplusos.systemui.common.feature.FeatureOption;
import com.oplusos.systemui.common.util.LogUtil;
import java.lang.invoke.VarHandle;
import kotlin.ResultKt;
import kotlin.Unit;
import kotlin.coroutines.Continuation;
import kotlin.coroutines.CoroutineContext;
import kotlin.coroutines.intrinsics.IntrinsicsKt__IntrinsicsKt;
import kotlin.coroutines.jvm.internal.SuspendLambda;
import kotlin.jvm.functions.Function2;
import kotlin.text.Regex$;
import kotlinx.coroutines.BuildersKt;
import kotlinx.coroutines.CoroutineScope;
import kotlinx.coroutines.CoroutineStart;
import kotlinx.coroutines.flow.FlowCollector;
import kotlinx.coroutines.flow.StateFlow;
import okio.Path$;

/* JADX INFO: compiled from: go/retraceme fd74bf9514dd0739b92d67a6d48a51eb10aaf8df760d83496b8dcfcf83e5f698 */
/* JADX INFO: loaded from: /home/starlight/Projects/Starlight Ports/.build-tmp/classes4.dex */
public final class OplusQSSmallSpaceController {
    public static final Companion Companion = new Companion();
    private static final String TAG = "OplusQSSmallSpaceController";
    public static boolean isInit;
    public static boolean isSmallSpace;
    private final CoroutineScope applicationScope;
    private final OplusQSSmallSpaceController$configurationCallback$1 configurationCallback;
    private final ConfigurationController configurationController;
    private final Context context;
    private final QSLogger qsLogger;
    private final OplusSeparateNotificationAndQSState separateNotificationAndQSState;

    /* JADX INFO: renamed from: com.oplus.systemui.qs.OplusQSSmallSpaceController$init$1, reason: invalid class name */
    /* JADX INFO: compiled from: go/retraceme fd74bf9514dd0739b92d67a6d48a51eb10aaf8df760d83496b8dcfcf83e5f698 */
    final class AnonymousClass1 extends SuspendLambda implements Function2 {
        int label;

        /* JADX INFO: renamed from: com.oplus.systemui.qs.OplusQSSmallSpaceController$init$1$1, reason: invalid class name and collision with other inner class name */
        /* JADX INFO: compiled from: go/retraceme fd74bf9514dd0739b92d67a6d48a51eb10aaf8df760d83496b8dcfcf83e5f698 */
        public final class C00651 implements FlowCollector {
            public /* synthetic */ OplusQSSmallSpaceController this$0;

            public final Object emit(Object obj, Continuation continuation) {
                boolean zBooleanValue = ((Boolean) obj).booleanValue();
                OplusQSSmallSpaceController oplusQSSmallSpaceController = this.this$0;
                boolean zIsSmallSpace = zBooleanValue ? false : OplusQSSmallSpaceController.Companion.isSmallSpace(oplusQSSmallSpaceController.context, false);
                LogUtil.i$default(LogUtil.TAG_QS, OplusQSSmallSpaceController.TAG, PriorityNestedScrollConnection$.ExternalSyntheticOutline0.m("separate mode change: isSmallSpace= ", " -> ", OplusQSSmallSpaceController.isSmallSpace, zIsSmallSpace), null, 8);
                oplusQSSmallSpaceController.qsLogger.i(OplusQSSmallSpaceController.TAG, PriorityNestedScrollConnection$.ExternalSyntheticOutline0.m("separate mode change: isSmallSpace= ", " -> ", OplusQSSmallSpaceController.isSmallSpace, zIsSmallSpace));
                OplusQSSmallSpaceController.isSmallSpace = zIsSmallSpace;
                return Unit.INSTANCE;
            }
        }

        public AnonymousClass1(Continuation continuation) {
            super(2, continuation);
        }

        public final Continuation create(Object obj, Continuation continuation) {
            return OplusQSSmallSpaceController.this.new AnonymousClass1(continuation);
        }

        public final Object invoke(Object obj, Object obj2) {
            return create((CoroutineScope) obj, (Continuation) obj2).invokeSuspend(Unit.INSTANCE);
        }

        public final Object invokeSuspend(Object obj) {
            Object coroutine_suspended = IntrinsicsKt__IntrinsicsKt.getCOROUTINE_SUSPENDED();
            int i = this.label;
            if (i == 0) {
                ResultKt.throwOnFailure(obj);
                StateFlow enableSeparateNotificationAndQS = OplusQSSmallSpaceController.this.separateNotificationAndQSState.getEnableSeparateNotificationAndQS();
                OplusQSSmallSpaceController oplusQSSmallSpaceController = OplusQSSmallSpaceController.this;
                C00651 c00651 = new C00651();
                c00651.this$0 = oplusQSSmallSpaceController;
                VarHandle.storeStoreFence();
                this.label = 1;
                if (enableSeparateNotificationAndQS.collect(c00651, this) == coroutine_suspended) {
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

    public OplusQSSmallSpaceController(Context context, OplusSeparateNotificationAndQSState oplusSeparateNotificationAndQSState, ConfigurationController configurationController, CoroutineScope coroutineScope, QSLogger qSLogger) {
        this.context = context;
        this.separateNotificationAndQSState = oplusSeparateNotificationAndQSState;
        this.configurationController = configurationController;
        this.applicationScope = coroutineScope;
        this.qsLogger = qSLogger;
        OplusQSSmallSpaceController$configurationCallback$1 oplusQSSmallSpaceController$configurationCallback$1 = new OplusQSSmallSpaceController$configurationCallback$1();
        oplusQSSmallSpaceController$configurationCallback$1.this$0 = this;
        VarHandle.storeStoreFence();
        this.configurationCallback = oplusQSSmallSpaceController$configurationCallback$1;
    }

    public static final boolean isSmallSpace(Context context) {
        return Companion.isSmallSpace(context);
    }

    public final void init() {
        this.configurationController.addCallback(this.configurationCallback);
        BuildersKt.launch$default(this.applicationScope, (CoroutineContext) null, (CoroutineStart) null, new AnonymousClass1(null), 3);
        boolean zIsSmallSpace = Companion.isSmallSpace(this.context, false);
        isSmallSpace = zIsSmallSpace;
        isInit = true;
        LogUtil.i$default(LogUtil.TAG_QS, TAG, KeyguardLogger$.ExternalSyntheticOutline0.m("init: isSmallSpace = ", zIsSmallSpace), null, 8);
        this.qsLogger.i(TAG, "init: isSmallSpace = " + isSmallSpace);
    }

    public static final boolean isSmallSpace(Context context, boolean z) {
        return Companion.isSmallSpace(context, z);
    }

    /* JADX INFO: compiled from: go/retraceme fd74bf9514dd0739b92d67a6d48a51eb10aaf8df760d83496b8dcfcf83e5f698 */
    public final class Companion {
        public final boolean isSmallSpace(Context context, boolean z) {
            if (z && OplusQSSmallSpaceController.isInit) {
                return OplusQSSmallSpaceController.isSmallSpace;
            }
            boolean z2 = false;
            if (context != null) {
                FeatureOption.INSTANCE.getClass();
                if (!FeatureOption.isTablet() && LargeScreenUtils.shouldUseSplitNotificationShade(context.getResources())) {
                    z2 = true;
                }
            }
            OplusQSSmallSpaceController.isSmallSpace = z2;
            LogUtil.i$default(LogUtil.TAG_QS, OplusQSSmallSpaceController.TAG, KeyguardLogger$.ExternalSyntheticOutline0.m("isSmallSpace: ", z2), null, 8);
            return z2;
        }

        public final boolean isSmallSpace(Context context) {
            return isSmallSpace(context, false);
        }
    }
}
