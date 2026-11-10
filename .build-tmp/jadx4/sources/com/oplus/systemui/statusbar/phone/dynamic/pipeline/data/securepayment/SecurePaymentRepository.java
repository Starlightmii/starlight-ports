package com.oplus.systemui.statusbar.phone.dynamic.pipeline.data.securepayment;

import android.content.Context;
import android.content.Intent;
import android.os.SystemProperties;
import androidx.appcompat.widget.ListPopupWindow$;
import androidx.car.app.model.constraints.ActionsConstraints$;
import com.android.systemui.util.settings.GlobalSettings;
import com.oplus.systemui.provider.ProviderUtil;
import com.oplus.systemui.statusbar.CommonProcessObserver;
import com.oplusos.systemui.common.feature.FeatureOption;
import com.oplusos.systemui.common.util.BrandUtils;
import com.oplusos.systemui.common.util.LogUtil;
import kotlin.ResultKt;
import kotlin.Unit;
import kotlin.coroutines.Continuation;
import kotlin.coroutines.CoroutineContext;
import kotlin.coroutines.intrinsics.IntrinsicsKt__IntrinsicsKt;
import kotlin.coroutines.jvm.internal.SuspendLambda;
import kotlin.jvm.functions.Function2;
import kotlinx.coroutines.BuildersKt;
import kotlinx.coroutines.CoroutineDispatcher;
import kotlinx.coroutines.CoroutineScope;
import kotlinx.coroutines.CoroutineStart;
import kotlinx.coroutines.flow.MutableStateFlow;
import kotlinx.coroutines.flow.StateFlow;
import kotlinx.coroutines.flow.StateFlowKt;
import okio.Path$;

/* JADX INFO: compiled from: go/retraceme fd74bf9514dd0739b92d67a6d48a51eb10aaf8df760d83496b8dcfcf83e5f698 */
/* JADX INFO: loaded from: /home/starlight/Projects/Starlight Ports/.build-tmp/classes4.dex */
public final class SecurePaymentRepository {
    private static final String ACTION_NOTIFY_SECURE_PAYMENT = "com.oplus.systemui.notify.secure.payment";
    private static final String ACTION_SECURE_PAYMENT_PACKAGE_NAME_OPLUS = "com.oplus.securepay";
    public static final Companion Companion = new Companion();
    private static final String DEBUG_KEY = "debug.systemui_secure_payment";
    private static final String TAG = "SecurePaymentRepository";
    private final MutableStateFlow _paymentCheckState;
    private final CoroutineDispatcher bgDispatcher;
    private final CommonProcessObserver commonProcessObserver;
    private final Context context;
    private final GlobalSettings globalSettings;
    public final boolean isDebug = SystemProperties.getBoolean(DEBUG_KEY, false);
    private final StateFlow paymentCheckState;
    private final String securePayPkgName;

    /* JADX INFO: renamed from: com.oplus.systemui.statusbar.phone.dynamic.pipeline.data.securepayment.SecurePaymentRepository$1, reason: invalid class name */
    /* JADX INFO: compiled from: go/retraceme fd74bf9514dd0739b92d67a6d48a51eb10aaf8df760d83496b8dcfcf83e5f698 */
    final class AnonymousClass1 extends SuspendLambda implements Function2 {
        Object L$0;
        Object L$1;
        int label;

        /* JADX INFO: renamed from: com.oplus.systemui.statusbar.phone.dynamic.pipeline.data.securepayment.SecurePaymentRepository$1$2, reason: invalid class name */
        /* JADX INFO: compiled from: go/retraceme fd74bf9514dd0739b92d67a6d48a51eb10aaf8df760d83496b8dcfcf83e5f698 */
        final class AnonymousClass2 extends SuspendLambda implements Function2 {
            int label;
            final /* synthetic */ SecurePaymentRepository this$0;

            /* JADX WARN: 'super' call moved to the top of the method (can break code semantics) */
            public AnonymousClass2(SecurePaymentRepository securePaymentRepository, Continuation continuation) {
                super(2, continuation);
                this.this$0 = securePaymentRepository;
            }

            public final Continuation create(Object obj, Continuation continuation) {
                return new AnonymousClass2(this.this$0, continuation);
            }

            public final Object invoke(Object obj, Object obj2) {
                return create((CoroutineScope) obj, (Continuation) obj2).invokeSuspend(Unit.INSTANCE);
            }

            public final Object invokeSuspend(Object obj) {
                IntrinsicsKt__IntrinsicsKt.getCOROUTINE_SUSPENDED();
                if (this.label != 0) {
                    Path$.ExternalSyntheticBUOutline0.m$1("call to 'resume' before 'invoke' with coroutine");
                    return null;
                }
                ResultKt.throwOnFailure(obj);
                Intent intent = new Intent(SecurePaymentRepository.ACTION_NOTIFY_SECURE_PAYMENT);
                intent.setPackage(this.this$0.getSecurePayPkg());
                this.this$0.context.sendBroadcast(intent, ProviderUtil.OPLUS_COMPONENT_SAFE);
                return Unit.INSTANCE;
            }
        }

        public AnonymousClass1(Continuation continuation) {
            super(2, continuation);
        }

        public final Continuation create(Object obj, Continuation continuation) {
            return SecurePaymentRepository.this.new AnonymousClass1(continuation);
        }

        public final Object invoke(Object obj, Object obj2) {
            return create((CoroutineScope) obj, (Continuation) obj2).invokeSuspend(Unit.INSTANCE);
        }

        /* JADX WARN: Code restructure failed: missing block: B:17:0x0099, code lost:
        
            if (com.android.systemui.kairos.BuildScopeKt.awaitClose(r5, r7) == r0) goto L18;
         */
        /*
            Code decompiled incorrectly, please refer to instructions dump.
            To view partially-correct add '--show-bad-code' argument
        */
        public final java.lang.Object invokeSuspend(java.lang.Object r8) {
            /*
                r7 = this;
                java.lang.Object r0 = kotlin.coroutines.intrinsics.IntrinsicsKt__IntrinsicsKt.getCOROUTINE_SUSPENDED()
                int r1 = r7.label
                r2 = 0
                r3 = 2
                r4 = 1
                if (r1 == 0) goto L2a
                if (r1 == r4) goto L22
                if (r1 == r3) goto L15
                java.lang.String r7 = "call to 'resume' before 'invoke' with coroutine"
                okio.Path$.ExternalSyntheticBUOutline0.m$1(r7)
                return r2
            L15:
                java.lang.Object r0 = r7.L$1
                com.android.systemui.statusbar.CommonProcessObserverEx$ICommonProcessObserver r0 = (com.android.systemui.statusbar.CommonProcessObserverEx.ICommonProcessObserver) r0
                java.lang.Object r7 = r7.L$0
                kotlin.jvm.internal.Ref$ObjectRef r7 = (kotlin.jvm.internal.Ref.ObjectRef) r7
                kotlin.ResultKt.throwOnFailure(r8)
                goto L9c
            L22:
                java.lang.Object r1 = r7.L$0
                kotlin.jvm.internal.Ref$ObjectRef r1 = (kotlin.jvm.internal.Ref.ObjectRef) r1
                kotlin.ResultKt.throwOnFailure(r8)
                goto L64
            L2a:
                kotlin.jvm.internal.Ref$ObjectRef r1 = androidx.compose.foundation.gestures.DefaultDraggableAnchors$.ExternalSyntheticOutline0.m(r8)
                com.oplus.systemui.statusbar.phone.dynamic.pipeline.data.securepayment.SecurePaymentRepository r8 = com.oplus.systemui.statusbar.phone.dynamic.pipeline.data.securepayment.SecurePaymentRepository.this
                boolean r8 = r8.isDebug
                if (r8 == 0) goto L4c
                com.oplus.systemui.statusbar.phone.dynamic.pipeline.data.securepayment.SecurePaymentRepository$1$1 r8 = new com.oplus.systemui.statusbar.phone.dynamic.pipeline.data.securepayment.SecurePaymentRepository$1$1
                com.oplus.systemui.statusbar.phone.dynamic.pipeline.data.securepayment.SecurePaymentRepository r5 = com.oplus.systemui.statusbar.phone.dynamic.pipeline.data.securepayment.SecurePaymentRepository.this
                r8.<init>(r5)
                r1.element = r8
                com.oplus.systemui.statusbar.phone.dynamic.pipeline.data.securepayment.SecurePaymentRepository r8 = com.oplus.systemui.statusbar.phone.dynamic.pipeline.data.securepayment.SecurePaymentRepository.this
                com.android.systemui.util.settings.GlobalSettings r8 = com.oplus.systemui.statusbar.phone.dynamic.pipeline.data.securepayment.SecurePaymentRepository.access$getGlobalSettings$p(r8)
                java.lang.Object r5 = r1.element
                android.database.ContentObserver r5 = (android.database.ContentObserver) r5
                java.lang.String r6 = "debug.systemui_secure_payment"
                r8.registerContentObserverSync(r6, r5)
            L4c:
                com.oplus.systemui.statusbar.phone.dynamic.pipeline.data.securepayment.SecurePaymentRepository r8 = com.oplus.systemui.statusbar.phone.dynamic.pipeline.data.securepayment.SecurePaymentRepository.this
                kotlinx.coroutines.CoroutineDispatcher r8 = com.oplus.systemui.statusbar.phone.dynamic.pipeline.data.securepayment.SecurePaymentRepository.access$getBgDispatcher$p(r8)
                com.oplus.systemui.statusbar.phone.dynamic.pipeline.data.securepayment.SecurePaymentRepository$1$2 r5 = new com.oplus.systemui.statusbar.phone.dynamic.pipeline.data.securepayment.SecurePaymentRepository$1$2
                com.oplus.systemui.statusbar.phone.dynamic.pipeline.data.securepayment.SecurePaymentRepository r6 = com.oplus.systemui.statusbar.phone.dynamic.pipeline.data.securepayment.SecurePaymentRepository.this
                r5.<init>(r6, r2)
                r7.L$0 = r1
                r7.label = r4
                java.lang.Object r8 = kotlinx.coroutines.BuildersKt.withContext(r8, r5, r7)
                if (r8 != r0) goto L64
                goto L9b
            L64:
                com.oplus.systemui.statusbar.phone.dynamic.pipeline.data.securepayment.SecurePaymentRepository$1$processObserver$1 r8 = new com.oplus.systemui.statusbar.phone.dynamic.pipeline.data.securepayment.SecurePaymentRepository$1$processObserver$1
                com.oplus.systemui.statusbar.phone.dynamic.pipeline.data.securepayment.SecurePaymentRepository r4 = com.oplus.systemui.statusbar.phone.dynamic.pipeline.data.securepayment.SecurePaymentRepository.this
                r8.<init>()
                r8.this$0 = r4
                java.lang.invoke.VarHandle.storeStoreFence()
                com.oplus.systemui.statusbar.CommonProcessObserver r4 = com.oplus.systemui.statusbar.phone.dynamic.pipeline.data.securepayment.SecurePaymentRepository.access$getCommonProcessObserver$p(r4)
                r4.addProcessObserver(r8)
                com.oplus.systemui.statusbar.phone.dynamic.pipeline.data.securepayment.SecurePaymentRepository r4 = com.oplus.systemui.statusbar.phone.dynamic.pipeline.data.securepayment.SecurePaymentRepository.this
                com.oplus.systemui.statusbar.phone.dynamic.pipeline.data.securepayment.SecurePaymentRepository$1$$ExternalSyntheticLambda0 r5 = new com.oplus.systemui.statusbar.phone.dynamic.pipeline.data.securepayment.SecurePaymentRepository$1$$ExternalSyntheticLambda0
                r5.<init>()
                r5.f$0 = r4
                r5.f$1 = r8
                r5.f$2 = r1
                java.lang.invoke.VarHandle.storeStoreFence()
                java.lang.Object r1 = kotlin.coroutines.jvm.internal.SpillingKt.nullOutSpilledVariable(r1)
                r7.L$0 = r1
                java.lang.Object r8 = kotlin.coroutines.jvm.internal.SpillingKt.nullOutSpilledVariable(r8)
                r7.L$1 = r8
                r7.label = r3
                java.lang.Object r7 = com.android.systemui.kairos.BuildScopeKt.awaitClose(r5, r7)
                if (r7 != r0) goto L9c
            L9b:
                return r0
            L9c:
                kotlin.text.Regex$.ExternalSyntheticBUOutline0.m$1()
                return r2
            */
            throw new UnsupportedOperationException("Method not decompiled: com.oplus.systemui.statusbar.phone.dynamic.pipeline.data.securepayment.SecurePaymentRepository.AnonymousClass1.invokeSuspend(java.lang.Object):java.lang.Object");
        }
    }

    /* JADX INFO: compiled from: go/retraceme fd74bf9514dd0739b92d67a6d48a51eb10aaf8df760d83496b8dcfcf83e5f698 */
    public final class Companion {
    }

    public SecurePaymentRepository(Context context, CoroutineScope coroutineScope, CoroutineDispatcher coroutineDispatcher, CommonProcessObserver commonProcessObserver, GlobalSettings globalSettings) {
        this.context = context;
        this.bgDispatcher = coroutineDispatcher;
        this.commonProcessObserver = commonProcessObserver;
        this.globalSettings = globalSettings;
        MutableStateFlow MutableStateFlow = StateFlowKt.MutableStateFlow(1005);
        this._paymentCheckState = MutableStateFlow;
        this.paymentCheckState = MutableStateFlow;
        this.securePayPkgName = context.getResources().getString(2131956171);
        BuildersKt.launch$default(coroutineScope, (CoroutineContext) null, (CoroutineStart) null, new AnonymousClass1(null), 3);
    }

    /* JADX INFO: Access modifiers changed from: private */
    public final String getSecurePayPkg() {
        BrandUtils.INSTANCE.getClass();
        if (BrandUtils.isOneplus) {
            FeatureOption.INSTANCE.getClass();
            if (FeatureOption.isExpRegion()) {
                return ACTION_SECURE_PAYMENT_PACKAGE_NAME_OPLUS;
            }
        }
        return this.securePayPkgName;
    }

    public final StateFlow getPaymentCheckState() {
        return this.paymentCheckState;
    }

    public final void updateDetectionState(int i) {
        if (i != 1000 && i != 1001 && i != 1002 && i != 1003 && i != 1004 && i != 1005) {
            LogUtil.w$default(8, LogUtil.TAG_STATUSBAR, TAG, ActionsConstraints$.ExternalSyntheticOutline0.m(i, "updateDetectionState: ", " is invalidate!"));
        } else {
            LogUtil.i$default(LogUtil.TAG_STATUSBAR, TAG, ListPopupWindow$.ExternalSyntheticOutline0.m(i, "updateDetectionState: "), null, 8);
            this._paymentCheckState.setValue(Integer.valueOf(i));
        }
    }
}
