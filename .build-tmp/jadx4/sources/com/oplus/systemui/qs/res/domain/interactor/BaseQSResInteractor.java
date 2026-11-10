package com.oplus.systemui.qs.res.domain.interactor;

import android.content.Context;
import com.android.systemui.qs.pipeline.data.repository.SuperPowerSaveRepository;
import com.oplus.systemui.qs.res.data.repository.QSConfigurationRepository;
import com.oplus.systemui.qs.res.data.repository.QSPersonalityRepository;
import com.oplus.systemui.qs.res.data.repository.QSThemeConfigRepository;
import java.util.concurrent.atomic.AtomicBoolean;
import kotlin.ResultKt;
import kotlin.Unit;
import kotlin.coroutines.Continuation;
import kotlin.coroutines.CoroutineContext;
import kotlin.coroutines.intrinsics.IntrinsicsKt__IntrinsicsKt;
import kotlin.coroutines.jvm.internal.SuspendLambda;
import kotlin.jvm.functions.Function2;
import kotlinx.coroutines.BuildersKt;
import kotlinx.coroutines.CoroutineScope;
import kotlinx.coroutines.CoroutineStart;
import kotlinx.coroutines.flow.Flow;
import kotlinx.coroutines.flow.SharedFlow;
import kotlinx.coroutines.flow.StateFlow;
import okio.Path$;

/* JADX INFO: compiled from: go/retraceme fd74bf9514dd0739b92d67a6d48a51eb10aaf8df760d83496b8dcfcf83e5f698 */
/* JADX INFO: loaded from: /home/starlight/Projects/Starlight Ports/.build-tmp/classes4.dex */
public final class BaseQSResInteractor {
    private final SharedFlow configuration;
    private final QSConfigurationRepository configurationRepository;
    private final Context context;
    private final AtomicBoolean initialized = new AtomicBoolean(false);
    private final StateFlow isGlobalThemeApplied;
    private final StateFlow isLowGaussianLevel;
    private final Flow isOneHandedEnable;
    private final QSPersonalityRepository personalityRepository;
    private final QSThemeConfigRepository qsThemeConfigRepository;
    private final CoroutineScope scope;
    private final StateFlow superPowerSaveMode;

    /* JADX INFO: renamed from: com.oplus.systemui.qs.res.domain.interactor.BaseQSResInteractor$init$1, reason: invalid class name */
    /* JADX INFO: compiled from: go/retraceme fd74bf9514dd0739b92d67a6d48a51eb10aaf8df760d83496b8dcfcf83e5f698 */
    final class AnonymousClass1 extends SuspendLambda implements Function2 {
        private /* synthetic */ Object L$0;
        int label;

        public AnonymousClass1(Continuation continuation) {
            super(2, continuation);
        }

        public final Continuation create(Object obj, Continuation continuation) {
            AnonymousClass1 anonymousClass1 = BaseQSResInteractor.this.new AnonymousClass1(continuation);
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
            BaseQSResInteractor baseQSResInteractor = BaseQSResInteractor.this;
            baseQSResInteractor.getClass();
            BuildersKt.launch$default(coroutineScope, (CoroutineContext) null, (CoroutineStart) null, new BaseQSResInteractor$startConfigurationCollection$1(baseQSResInteractor, null), 3);
            BaseQSResInteractor baseQSResInteractor2 = BaseQSResInteractor.this;
            baseQSResInteractor2.getClass();
            BuildersKt.launch$default(coroutineScope, (CoroutineContext) null, (CoroutineStart) null, new BaseQSResInteractor$startThemeResCollection$1(baseQSResInteractor2, null), 3);
            BaseQSResInteractor baseQSResInteractor3 = BaseQSResInteractor.this;
            baseQSResInteractor3.getClass();
            BuildersKt.launch$default(coroutineScope, (CoroutineContext) null, (CoroutineStart) null, new BaseQSResInteractor$startMixColorCollection$1(baseQSResInteractor3, null), 3);
            BaseQSResInteractor baseQSResInteractor4 = BaseQSResInteractor.this;
            baseQSResInteractor4.getClass();
            BuildersKt.launch$default(coroutineScope, (CoroutineContext) null, (CoroutineStart) null, new BaseQSResInteractor$startDetailPanelOutlineCollection$1(baseQSResInteractor4, null), 3);
            BaseQSResInteractor baseQSResInteractor5 = BaseQSResInteractor.this;
            baseQSResInteractor5.getClass();
            BuildersKt.launch$default(coroutineScope, (CoroutineContext) null, (CoroutineStart) null, new BaseQSResInteractor$startOneHandedEnableCollection$1(baseQSResInteractor5, null), 3);
            return Unit.INSTANCE;
        }
    }

    public BaseQSResInteractor(CoroutineScope coroutineScope, Context context, QSConfigurationRepository qSConfigurationRepository, QSThemeConfigRepository qSThemeConfigRepository, QSPersonalityRepository qSPersonalityRepository, SuperPowerSaveRepository superPowerSaveRepository, QSThemeConfigRepository qSThemeConfigRepository2) {
        this.scope = coroutineScope;
        this.context = context;
        this.configurationRepository = qSConfigurationRepository;
        this.qsThemeConfigRepository = qSThemeConfigRepository;
        this.personalityRepository = qSPersonalityRepository;
        this.configuration = qSConfigurationRepository.getConfiguration();
        this.isGlobalThemeApplied = qSThemeConfigRepository.isGlobalThemeApplied();
        this.superPowerSaveMode = superPowerSaveRepository.getSuperPowerSaveMode();
        this.isLowGaussianLevel = qSThemeConfigRepository2.isLowGaussianLevel();
        this.isOneHandedEnable = qSPersonalityRepository.isOneHandedEnable();
    }

    public final void init() {
        if (this.initialized.compareAndSet(false, true)) {
            BuildersKt.launch$default(this.scope, (CoroutineContext) null, (CoroutineStart) null, new AnonymousClass1(null), 3);
        }
    }
}
