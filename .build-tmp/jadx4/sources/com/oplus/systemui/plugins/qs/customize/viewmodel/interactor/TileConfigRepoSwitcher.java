package com.oplus.systemui.plugins.qs.customize.viewmodel.interactor;

import android.os.Handler;
import android.os.Process;
import androidx.compose.runtime.collection.MutableVectorKt$;
import com.android.app.tracing.coroutines.CoroutineTracingKt;
import com.android.app.tracing.coroutines.flow.FlowExtKt;
import com.oplus.systemui.plugins.qs.Dumpable;
import com.oplus.systemui.plugins.qs.customize.data.repo.ISepTileConfigRepository;
import com.oplus.systemui.plugins.qs.customize.data.repo.SepTileConfigMapperRepository;
import com.oplus.systemui.plugins.qs.customize.data.repo.SepTileConfigRepository;
import java.io.PrintWriter;
import java.lang.invoke.VarHandle;
import kotlin.Unit;
import kotlin.coroutines.Continuation;
import kotlin.coroutines.intrinsics.IntrinsicsKt__IntrinsicsKt;
import kotlin.coroutines.jvm.internal.Boxing;
import kotlinx.coroutines.CoroutineDispatcher;
import kotlinx.coroutines.CoroutineScope;
import kotlinx.coroutines.CoroutineStart;
import kotlinx.coroutines.flow.Flow;
import kotlinx.coroutines.flow.FlowKt;
import kotlinx.coroutines.flow.MutableStateFlow;
import kotlinx.coroutines.flow.StateFlow;
import kotlinx.coroutines.flow.StateFlowKt;

/* JADX INFO: compiled from: go/retraceme fd74bf9514dd0739b92d67a6d48a51eb10aaf8df760d83496b8dcfcf83e5f698 */
/* JADX INFO: loaded from: /home/starlight/Projects/Starlight Ports/.build-tmp/classes4.dex */
public final class TileConfigRepoSwitcher implements Dumpable {
    public static final Companion Companion = new Companion();
    private static final String TAG = "TileConfigRepoSwitcher";
    private final MutableStateFlow _scene;
    private final MutableStateFlow _userId;
    private final CoroutineDispatcher bgDispatcher;
    private ISepTileConfigRepository editTileConfigRepo;
    private final Flow flowWrapped;
    private SepTileConfigMapperRepository landscapeRepo;
    private final Handler mainHandler;
    private final CoroutineScope mainScope;
    private final Runnable resetLandscapeRepoTask;
    private final StateFlow scene;
    private final SepTileConfigRepository tileConfigRepository;
    private final StateFlow userId;

    /* JADX INFO: compiled from: go/retraceme fd74bf9514dd0739b92d67a6d48a51eb10aaf8df760d83496b8dcfcf83e5f698 */
    public final class Companion {
    }

    /* JADX INFO: compiled from: go/retraceme fd74bf9514dd0739b92d67a6d48a51eb10aaf8df760d83496b8dcfcf83e5f698 */
    public final class UserAndScene {
        public final int scene;
        public final int userId;

        public UserAndScene(int i, int i2) {
            this.userId = i;
            this.scene = i2;
        }

        public final UserAndScene copy(int i, int i2) {
            return new UserAndScene(i, i2);
        }

        public boolean equals(Object obj) {
            if (this == obj) {
                return true;
            }
            if (!(obj instanceof UserAndScene)) {
                return false;
            }
            UserAndScene userAndScene = (UserAndScene) obj;
            return this.userId == userAndScene.userId && this.scene == userAndScene.scene;
        }

        public final int getScene() {
            return this.scene;
        }

        public final int getUserId() {
            return this.userId;
        }

        public final int hashCode() {
            return Integer.hashCode(this.scene) + (Integer.hashCode(this.userId) * 31);
        }

        public String toString() {
            return MutableVectorKt$.ExternalSyntheticOutline0.m("UserAndScene(userId=", this.userId, this.scene, ", scene=", ")");
        }
    }

    public TileConfigRepoSwitcher(SepTileConfigRepository sepTileConfigRepository, Handler handler, CoroutineScope coroutineScope, CoroutineDispatcher coroutineDispatcher) {
        this.tileConfigRepository = sepTileConfigRepository;
        this.mainHandler = handler;
        this.mainScope = coroutineScope;
        this.bgDispatcher = coroutineDispatcher;
        MutableStateFlow mutableStateFlowTraceAs = FlowExtKt.traceAs(StateFlowKt.MutableStateFlow(1), "TileConfigRepoSwitcher-flow-scene");
        this._scene = mutableStateFlowTraceAs;
        this.scene = FlowKt.asStateFlow(mutableStateFlowTraceAs);
        MutableStateFlow mutableStateFlowTraceAs2 = FlowExtKt.traceAs(StateFlowKt.MutableStateFlow(Integer.valueOf(Process.myUserHandle().getIdentifier())), "TileConfigRepoSwitcher-flow-userId");
        this._userId = mutableStateFlowTraceAs2;
        this.userId = FlowKt.asStateFlow(mutableStateFlowTraceAs2);
        TileConfigRepoSwitcher$resetLandscapeRepoTask$1 tileConfigRepoSwitcher$resetLandscapeRepoTask$1 = new TileConfigRepoSwitcher$resetLandscapeRepoTask$1();
        tileConfigRepoSwitcher$resetLandscapeRepoTask$1.this$0 = this;
        VarHandle.storeStoreFence();
        this.resetLandscapeRepoTask = tileConfigRepoSwitcher$resetLandscapeRepoTask$1;
        this.flowWrapped = FlowExtKt.traceAs(FlowKt.onEach(FlowKt.transformLatest(FlowKt.combine(mutableStateFlowTraceAs2, mutableStateFlowTraceAs, new TileConfigRepoSwitcher$flowWrapped$1(3, null)), new TileConfigRepoSwitcher$special$$inlined$flatMapLatest$1(this, null)), new TileConfigRepoSwitcher$flowWrapped$3(2, null)), "TileConfigRepoSwitcher-flowWrapped");
        CoroutineTracingKt.launchTraced$default(coroutineScope, "TileConfigRepoSwitcher-launch-startSetListeningTileConfigChange", coroutineDispatcher, (CoroutineStart) null, new TileConfigRepoSwitcher$startSetListeningTileConfigChange$1(this, null), 4);
    }

    @Override // com.oplus.systemui.plugins.qs.Dumpable
    public void dump(PrintWriter printWriter, String[] strArr) {
        this.tileConfigRepository.dump(printWriter, strArr);
    }

    public final ISepTileConfigRepository getEditTileConfigRepo() {
        ISepTileConfigRepository iSepTileConfigRepository = this.editTileConfigRepo;
        return iSepTileConfigRepository == null ? this.tileConfigRepository.ensureUserTileConfigRepository(((Number) this._userId.getValue()).intValue()) : iSepTileConfigRepository;
    }

    public final Flow getFlowWrapped() {
        return this.flowWrapped;
    }

    public final StateFlow getScene() {
        return this.scene;
    }

    public final ISepTileConfigRepository getSrcTileConfigRepo() {
        return this.tileConfigRepository.ensureUserTileConfigRepository(((Number) this._userId.getValue()).intValue());
    }

    public final StateFlow getUserId() {
        return this.userId;
    }

    public final Object setCurrentScene(int i, Continuation continuation) {
        if (i == ((Number) this._scene.getValue()).intValue()) {
            return Unit.INSTANCE;
        }
        if (((Number) this._scene.getValue()).intValue() == 3) {
            this.mainHandler.postDelayed(this.resetLandscapeRepoTask, 60000L);
        } else if (i == 3) {
            this.mainHandler.removeCallbacks(this.resetLandscapeRepoTask);
        } else if (i == 2) {
            this.resetLandscapeRepoTask.run();
        } else if (((Number) this._scene.getValue()).intValue() == 2) {
            this.editTileConfigRepo = null;
        }
        Object objEmit = this._scene.emit(Boxing.boxInt(i), continuation);
        return objEmit == IntrinsicsKt__IntrinsicsKt.getCOROUTINE_SUSPENDED() ? objEmit : Unit.INSTANCE;
    }

    public final Object setLandGridInfo(int i, int i2, Continuation continuation) {
        Object mappedGridInfo;
        SepTileConfigMapperRepository sepTileConfigMapperRepository = this.landscapeRepo;
        return (sepTileConfigMapperRepository == null || (mappedGridInfo = sepTileConfigMapperRepository.setMappedGridInfo(i, i2, continuation)) != IntrinsicsKt__IntrinsicsKt.getCOROUTINE_SUSPENDED()) ? Unit.INSTANCE : mappedGridInfo;
    }

    public final Object setUserId(int i, Continuation continuation) {
        Object objEmit = this._userId.emit(Boxing.boxInt(i), continuation);
        return objEmit == IntrinsicsKt__IntrinsicsKt.getCOROUTINE_SUSPENDED() ? objEmit : Unit.INSTANCE;
    }
}
