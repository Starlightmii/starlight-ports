package com.oplus.systemui.statusbar.pipeline.mobile.ui;

import android.content.Context;
import android.provider.Settings;
import androidx.compose.material3.internal.TextFieldImplKt$;
import com.android.keyguard.logging.KeyguardLogger$;
import com.oplus.systemui.statusbar.policy.MobileIconSets;
import com.oplus.systemui.utils.OplusThemeController;
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
import kotlinx.coroutines.flow.FlowKt;
import kotlinx.coroutines.flow.SharingStarted;
import kotlinx.coroutines.flow.StateFlow;
import okio.Path$;

/* JADX INFO: compiled from: go/retraceme fd74bf9514dd0739b92d67a6d48a51eb10aaf8df760d83496b8dcfcf83e5f698 */
/* JADX INFO: loaded from: /home/starlight/Projects/Starlight Ports/.build-tmp/classes4.dex */
public final class OplusMobileThemeManagerRepositoryImpl implements OplusMobileThemeManagerRepository {
    public static final Companion Companion = new Companion();
    private static final String KEY_THEME_ADAPTER_STACKED_SIGNAL_MODE = "theme_adapter_stacked_signal_display_mode";
    private static final String TAG = "OplusMobileThemeManagerRepo";
    private final Context context;
    private final OplusThemeController oplusThemeController;
    private final StateFlow themeAdapterState;

    /* JADX INFO: renamed from: com.oplus.systemui.statusbar.pipeline.mobile.ui.OplusMobileThemeManagerRepositoryImpl$1, reason: invalid class name */
    /* JADX INFO: compiled from: go/retraceme fd74bf9514dd0739b92d67a6d48a51eb10aaf8df760d83496b8dcfcf83e5f698 */
    final class AnonymousClass1 extends SuspendLambda implements Function2 {
        int label;

        /* JADX INFO: renamed from: com.oplus.systemui.statusbar.pipeline.mobile.ui.OplusMobileThemeManagerRepositoryImpl$1$1, reason: invalid class name and collision with other inner class name */
        /* JADX INFO: compiled from: go/retraceme fd74bf9514dd0739b92d67a6d48a51eb10aaf8df760d83496b8dcfcf83e5f698 */
        public final class C01581 implements FlowCollector {
            public /* synthetic */ OplusMobileThemeManagerRepositoryImpl this$0;

            public final Object emit(Object obj, Continuation continuation) {
                MobileThemeAdapterState mobileThemeAdapterState = (MobileThemeAdapterState) obj;
                LogUtil.i$default(LogUtil.TAG_STATUSBAR, OplusMobileThemeManagerRepositoryImpl.TAG, "themeAdapterState changed, write Settings state:" + mobileThemeAdapterState, null, 8);
                Settings.System.putIntForUser(this.this$0.context.getContentResolver(), OplusMobileThemeManagerRepositoryImpl.KEY_THEME_ADAPTER_STACKED_SIGNAL_MODE, (mobileThemeAdapterState.isAdaptOs17Stacked || !mobileThemeAdapterState.isAdaptOs16Independent) ? 1 : 0, -2);
                return Unit.INSTANCE;
            }
        }

        public AnonymousClass1(Continuation continuation) {
            super(2, continuation);
        }

        public final Continuation create(Object obj, Continuation continuation) {
            return OplusMobileThemeManagerRepositoryImpl.this.new AnonymousClass1(continuation);
        }

        public final Object invoke(Object obj, Object obj2) {
            return create((CoroutineScope) obj, (Continuation) obj2).invokeSuspend(Unit.INSTANCE);
        }

        public final Object invokeSuspend(Object obj) {
            Object coroutine_suspended = IntrinsicsKt__IntrinsicsKt.getCOROUTINE_SUSPENDED();
            int i = this.label;
            if (i == 0) {
                ResultKt.throwOnFailure(obj);
                StateFlow themeAdapterState = OplusMobileThemeManagerRepositoryImpl.this.getThemeAdapterState();
                OplusMobileThemeManagerRepositoryImpl oplusMobileThemeManagerRepositoryImpl = OplusMobileThemeManagerRepositoryImpl.this;
                C01581 c01581 = new C01581();
                c01581.this$0 = oplusMobileThemeManagerRepositoryImpl;
                VarHandle.storeStoreFence();
                this.label = 1;
                if (themeAdapterState.collect(c01581, this) == coroutine_suspended) {
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

    /* JADX INFO: compiled from: go/retraceme fd74bf9514dd0739b92d67a6d48a51eb10aaf8df760d83496b8dcfcf83e5f698 */
    public final class Companion {
    }

    public OplusMobileThemeManagerRepositoryImpl(Context context, CoroutineScope coroutineScope, OplusThemeController oplusThemeController) {
        this.context = context;
        this.oplusThemeController = oplusThemeController;
        this.themeAdapterState = FlowKt.stateIn(FlowKt.distinctUntilChanged(FlowKt.callbackFlow(new OplusMobileThemeManagerRepositoryImpl$themeAdapterState$1(this, null))), coroutineScope, SharingStarted.Companion.WhileSubscribed$default(SharingStarted.Companion, 0L, 3), calculateAdapterState(oplusThemeController.isThirdTheme));
        BuildersKt.launch$default(coroutineScope, (CoroutineContext) null, (CoroutineStart) null, new AnonymousClass1(null), 3);
    }

    public final MobileThemeAdapterState calculateAdapterState(boolean z) {
        boolean z2 = !z || MobileIconSets.Companion.isAdaptOs17IndependentSignal(this.context);
        boolean z3 = !z || MobileIconSets.Companion.isAdaptOs17StackedSignal(this.context);
        boolean z4 = z && MobileIconSets.Companion.isAdaptOs16IndependentSignal(this.context);
        MobileThemeAdapterState mobileThemeAdapterState = new MobileThemeAdapterState(z2, z3, z4);
        LogUtil.i$default(LogUtil.TAG_STATUSBAR, TAG, KeyguardLogger$.ExternalSyntheticOutline0.m(TextFieldImplKt$.ExternalSyntheticOutline0.m("calculateAdapterState isThirdTheme:", " isAdaptOs17Independent:", " isAdaptOs17Stacked:", z, z2), z3, " isAdaptOs16Independent:", z4), null, 8);
        return mobileThemeAdapterState;
    }

    @Override // com.oplus.systemui.statusbar.pipeline.mobile.ui.OplusMobileThemeManagerRepository
    public StateFlow getThemeAdapterState() {
        return this.themeAdapterState;
    }
}
