package com.oplus.systemui.plugins.qs.customize;

import com.android.app.tracing.coroutines.CoroutineTracingKt;
import com.android.systemui.plugins.qs.SepTileSpecRepository;
import com.oplus.systemui.plugins.qs.ContextInstance;
import com.oplus.systemui.plugins.qs.CoreStartable;
import com.oplus.systemui.plugins.qs.customize.data.repo.SepTileConfigRepository;
import com.oplus.systemui.plugins.qs.customize.data.roomdatabase.SepTileRoomDatabase;
import com.oplus.systemui.plugins.qs.customize.viewmodel.SepQSHostAdapter;
import com.oplus.systemui.plugins.qs.customize.viewmodel.interactor.TileBackupInteractor;
import com.oplus.systemui.plugins.qs.customize.viewmodel.interactor.TileResetInteractor;
import com.oplus.systemui.plugins.qs.customize.viewmodel.interactor.TileRestoreInteractor;
import com.oplusos.systemui.common.olivelink.OliveLinkConnector;
import com.oplusos.systemui.common.util.LogUtil;
import java.io.PrintWriter;
import kotlin.ResultKt;
import kotlin.Unit;
import kotlin.coroutines.Continuation;
import kotlin.coroutines.intrinsics.IntrinsicsKt__IntrinsicsKt;
import kotlin.coroutines.jvm.internal.SuspendLambda;
import kotlin.jvm.functions.Function2;
import kotlinx.coroutines.CoroutineDispatcher;
import kotlinx.coroutines.CoroutineScope;
import kotlinx.coroutines.CoroutineStart;
import okio.Path$;

/* JADX INFO: compiled from: go/retraceme fd74bf9514dd0739b92d67a6d48a51eb10aaf8df760d83496b8dcfcf83e5f698 */
/* JADX INFO: loaded from: /home/starlight/Projects/Starlight Ports/.build-tmp/classes4.dex */
public final class SepQSPipelineCoreStartable implements CoreStartable {
    public static final Companion Companion = new Companion();
    private static final String TAG = "SepQSPipelineCoreStartable";
    private final TileBackupInteractor backupInteractor;
    private final CoroutineDispatcher bgDispatcher;
    private final ContextInstance contextInstance;
    private final CoroutineScope mainScope;
    private final TileResetInteractor resetInteractor;
    private final TileRestoreInteractor restoreInteractor;
    private final SepQSHostAdapter sepQsHost;
    private final SepTileConfigRepository sepTileRepository;

    /* JADX INFO: compiled from: go/retraceme fd74bf9514dd0739b92d67a6d48a51eb10aaf8df760d83496b8dcfcf83e5f698 */
    public final class Companion {
    }

    /* JADX INFO: renamed from: com.oplus.systemui.plugins.qs.customize.SepQSPipelineCoreStartable$start$1, reason: invalid class name */
    /* JADX INFO: compiled from: go/retraceme fd74bf9514dd0739b92d67a6d48a51eb10aaf8df760d83496b8dcfcf83e5f698 */
    final class AnonymousClass1 extends SuspendLambda implements Function2 {
        int label;

        public AnonymousClass1(Continuation continuation) {
            super(2, continuation);
        }

        public final Continuation create(Object obj, Continuation continuation) {
            return SepQSPipelineCoreStartable.this.new AnonymousClass1(continuation);
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
            SepTileRoomDatabase.Companion.getDatabase(SepQSPipelineCoreStartable.this.contextInstance.getSysUiContext());
            return Unit.INSTANCE;
        }
    }

    public SepQSPipelineCoreStartable(ContextInstance contextInstance, SepQSHostAdapter sepQSHostAdapter, SepTileConfigRepository sepTileConfigRepository, TileResetInteractor tileResetInteractor, TileBackupInteractor tileBackupInteractor, TileRestoreInteractor tileRestoreInteractor, CoroutineScope coroutineScope, CoroutineDispatcher coroutineDispatcher) {
        this.contextInstance = contextInstance;
        this.sepQsHost = sepQSHostAdapter;
        this.sepTileRepository = sepTileConfigRepository;
        this.resetInteractor = tileResetInteractor;
        this.backupInteractor = tileBackupInteractor;
        this.restoreInteractor = tileRestoreInteractor;
        this.mainScope = coroutineScope;
        this.bgDispatcher = coroutineDispatcher;
    }

    public final String backupTiles() {
        return this.backupInteractor.backup();
    }

    public final void destroy() {
        LogUtil.i$default(LogUtil.TAG_QS, TAG, "destroy", null, 8);
        this.resetInteractor.destroy();
        OliveLinkConnector.INSTANCE.stop(OliveLinkConnector.SEP_QS_SERVER_NAME);
        this.backupInteractor.destroy();
        this.restoreInteractor.destroy();
    }

    @Override // com.oplus.systemui.plugins.qs.CoreStartable, com.oplus.systemui.plugins.qs.Dumpable
    public void dump(PrintWriter printWriter, String[] strArr) {
        this.sepQsHost.dump(printWriter, strArr);
    }

    public final SepTileSpecRepository getSepTileSpecRepository() {
        return this.sepTileRepository.getInnerTileSpecRepository();
    }

    public final void restoreTiles() {
        this.restoreInteractor.restore();
    }

    public final void start() {
        LogUtil.i$default(LogUtil.TAG_QS, TAG, "start", null, 8);
        CoroutineTracingKt.launchTraced$default(this.mainScope, "SepQSPipelineCoreStartable-launch-getDatabase", this.bgDispatcher, (CoroutineStart) null, new AnonymousClass1(null), 4);
        this.resetInteractor.start();
        OliveLinkConnector.INSTANCE.start(this.contextInstance.getPluginContext(), OliveLinkConnector.SEP_QS_SERVER_NAME);
        this.backupInteractor.start(this.sepTileRepository);
        this.restoreInteractor.start(this.sepTileRepository);
    }
}
