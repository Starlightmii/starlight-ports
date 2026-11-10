package com.oplus.systemui.plugins.qs.customize.data.repo;

import com.oplus.systemui.plugins.qs.ContextInstance;
import com.oplus.systemui.plugins.qs.customize.data.model.TileStaticConfig;
import com.oplusos.systemui.common.model.SpanCache;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import kotlin.ResultKt;
import kotlin.Unit;
import kotlin.coroutines.Continuation;
import kotlin.coroutines.intrinsics.IntrinsicsKt__IntrinsicsKt;
import kotlin.coroutines.jvm.internal.ContinuationImpl;
import kotlinx.coroutines.BuildersKt;
import kotlinx.coroutines.CoroutineDispatcher;
import kotlinx.coroutines.sync.Mutex;
import kotlinx.coroutines.sync.MutexKt;
import okio.Path$;
import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

/* JADX INFO: compiled from: go/retraceme fd74bf9514dd0739b92d67a6d48a51eb10aaf8df760d83496b8dcfcf83e5f698 */
/* JADX INFO: loaded from: /home/starlight/Projects/Starlight Ports/.build-tmp/classes4.dex */
public final class SepStaticTileConfigRepository implements ISepStaticTileConfigRepository {
    private static final String CONFIG_FILENAME = "septiles_contain_allsystem_config.json";
    public static final Companion Companion = new Companion();
    private static final String KEY_DEFAULT_SIZE = "defaultSize";
    private static final String KEY_SPEC = "spec";
    private static final String KEY_SUPPORT_SIZES = "supportSizes";
    private static final String TAG = "SepStaticTileConfigRepository";
    private final CoroutineDispatcher bgDispatcher;
    private final ContextInstance contextInstance;
    private final Mutex mutex = MutexKt.Mutex(false);
    public Map tileConfigs;

    /* JADX INFO: compiled from: go/retraceme fd74bf9514dd0739b92d67a6d48a51eb10aaf8df760d83496b8dcfcf83e5f698 */
    public final class Companion {
    }

    /* JADX INFO: renamed from: com.oplus.systemui.plugins.qs.customize.data.repo.SepStaticTileConfigRepository$getTileConfig$1, reason: invalid class name */
    /* JADX INFO: compiled from: go/retraceme fd74bf9514dd0739b92d67a6d48a51eb10aaf8df760d83496b8dcfcf83e5f698 */
    final class AnonymousClass1 extends ContinuationImpl {
        Object L$0;
        int label;
        /* synthetic */ Object result;

        public AnonymousClass1(Continuation continuation) {
            super(continuation);
        }

        public final Object invokeSuspend(Object obj) {
            this.result = obj;
            this.label |= Integer.MIN_VALUE;
            return SepStaticTileConfigRepository.this.getTileConfig(null, this);
        }
    }

    /* JADX INFO: renamed from: com.oplus.systemui.plugins.qs.customize.data.repo.SepStaticTileConfigRepository$getTileConfigs$1, reason: invalid class name and case insensitive filesystem */
    /* JADX INFO: compiled from: go/retraceme fd74bf9514dd0739b92d67a6d48a51eb10aaf8df760d83496b8dcfcf83e5f698 */
    final class C02131 extends ContinuationImpl {
        int label;
        /* synthetic */ Object result;

        public C02131(Continuation continuation) {
            super(continuation);
        }

        public final Object invokeSuspend(Object obj) {
            this.result = obj;
            this.label |= Integer.MIN_VALUE;
            return SepStaticTileConfigRepository.this.getTileConfigs(this);
        }
    }

    /* JADX INFO: renamed from: com.oplus.systemui.plugins.qs.customize.data.repo.SepStaticTileConfigRepository$loadAndCachedTilesIfNeeded$1, reason: invalid class name and case insensitive filesystem */
    /* JADX INFO: compiled from: go/retraceme fd74bf9514dd0739b92d67a6d48a51eb10aaf8df760d83496b8dcfcf83e5f698 */
    final class C02141 extends ContinuationImpl {
        int I$0;
        int I$1;
        Object L$0;
        Object L$1;
        int label;
        /* synthetic */ Object result;

        public C02141(ContinuationImpl continuationImpl) {
            super(continuationImpl);
        }

        public final Object invokeSuspend(Object obj) {
            this.result = obj;
            this.label |= Integer.MIN_VALUE;
            return SepStaticTileConfigRepository.this.loadAndCachedTilesIfNeeded(this);
        }
    }

    public SepStaticTileConfigRepository(ContextInstance contextInstance, CoroutineDispatcher coroutineDispatcher) {
        this.contextInstance = contextInstance;
        this.bgDispatcher = coroutineDispatcher;
    }

    public static final List access$getSupportSizesFromJson(SepStaticTileConfigRepository sepStaticTileConfigRepository, JSONObject jSONObject) throws JSONException {
        sepStaticTileConfigRepository.getClass();
        JSONArray jSONArrayOptJSONArray = jSONObject.optJSONArray(KEY_SUPPORT_SIZES);
        ArrayList arrayList = new ArrayList();
        if (jSONArrayOptJSONArray == null || jSONArrayOptJSONArray.length() <= 0) {
            arrayList.addAll(TileStaticConfig.Companion.getDEFAULT_SUPPORT_SIZES());
            return arrayList;
        }
        int length = jSONArrayOptJSONArray.length();
        for (int i = 0; i < length; i++) {
            JSONArray jSONArray = jSONArrayOptJSONArray.getJSONArray(i);
            arrayList.add(SpanCache.INSTANCE.spanSize(jSONArray.getInt(0), jSONArray.getInt(1)));
        }
        return arrayList;
    }

    /* JADX WARN: Code duplicated, block: B:7:0x0013  */
    @Override // com.oplus.systemui.plugins.qs.customize.data.repo.ISepStaticTileConfigRepository
    public Object getTileConfig(String str, Continuation continuation) throws Throwable {
        AnonymousClass1 anonymousClass1;
        if (continuation instanceof AnonymousClass1) {
            anonymousClass1 = (AnonymousClass1) continuation;
            int i = anonymousClass1.label;
            if ((i & Integer.MIN_VALUE) != 0) {
                anonymousClass1.label = i - Integer.MIN_VALUE;
            } else {
                anonymousClass1 = new AnonymousClass1(continuation);
            }
        } else {
            anonymousClass1 = new AnonymousClass1(continuation);
        }
        Object obj = anonymousClass1.result;
        Object coroutine_suspended = IntrinsicsKt__IntrinsicsKt.getCOROUTINE_SUSPENDED();
        int i2 = anonymousClass1.label;
        if (i2 == 0) {
            ResultKt.throwOnFailure(obj);
            anonymousClass1.L$0 = str;
            anonymousClass1.label = 1;
            if (loadAndCachedTilesIfNeeded(anonymousClass1) == coroutine_suspended) {
                return coroutine_suspended;
            }
        } else {
            if (i2 != 1) {
                Path$.ExternalSyntheticBUOutline0.m$1("call to 'resume' before 'invoke' with coroutine");
                return null;
            }
            str = (String) anonymousClass1.L$0;
            ResultKt.throwOnFailure(obj);
        }
        Map map = this.tileConfigs;
        TileStaticConfig tileStaticConfig = (TileStaticConfig) (map != null ? map : null).get(str);
        return tileStaticConfig == null ? TileStaticConfig.Companion.createDefault(str) : tileStaticConfig;
    }

    /* JADX WARN: Code duplicated, block: B:7:0x0013  */
    @Override // com.oplus.systemui.plugins.qs.customize.data.repo.ISepStaticTileConfigRepository
    public Object getTileConfigs(Continuation continuation) {
        C02131 c02131;
        if (continuation instanceof C02131) {
            c02131 = (C02131) continuation;
            int i = c02131.label;
            if ((i & Integer.MIN_VALUE) != 0) {
                c02131.label = i - Integer.MIN_VALUE;
            } else {
                c02131 = new C02131(continuation);
            }
        } else {
            c02131 = new C02131(continuation);
        }
        Object obj = c02131.result;
        Object coroutine_suspended = IntrinsicsKt__IntrinsicsKt.getCOROUTINE_SUSPENDED();
        int i2 = c02131.label;
        if (i2 == 0) {
            ResultKt.throwOnFailure(obj);
            c02131.label = 1;
            if (loadAndCachedTilesIfNeeded(c02131) == coroutine_suspended) {
                return coroutine_suspended;
            }
        } else {
            if (i2 != 1) {
                Path$.ExternalSyntheticBUOutline0.m$1("call to 'resume' before 'invoke' with coroutine");
                return null;
            }
            ResultKt.throwOnFailure(obj);
        }
        Map map = this.tileConfigs;
        if (map == null) {
            return null;
        }
        return map;
    }

    /* JADX WARN: Code duplicated, block: B:7:0x0013  */
    public final Object loadAndCachedTilesIfNeeded(ContinuationImpl continuationImpl) throws Throwable {
        C02141 c02141;
        Mutex mutex;
        int i;
        Mutex mutex2;
        if (continuationImpl instanceof C02141) {
            c02141 = (C02141) continuationImpl;
            int i2 = c02141.label;
            if ((i2 & Integer.MIN_VALUE) != 0) {
                c02141.label = i2 - Integer.MIN_VALUE;
            } else {
                c02141 = new C02141(continuationImpl);
            }
        } else {
            c02141 = new C02141(continuationImpl);
        }
        Object obj = c02141.result;
        Object coroutine_suspended = IntrinsicsKt__IntrinsicsKt.getCOROUTINE_SUSPENDED();
        int i3 = c02141.label;
        try {
            if (i3 == 0) {
                ResultKt.throwOnFailure(obj);
                if (this.tileConfigs == null) {
                    mutex = this.mutex;
                    c02141.L$0 = mutex;
                    c02141.I$0 = 0;
                    c02141.label = 1;
                    if (mutex.lock((Object) null, c02141) != coroutine_suspended) {
                        i = 0;
                    }
                    return coroutine_suspended;
                }
                return Unit.INSTANCE;
            }
            if (i3 != 1) {
                if (i3 != 2) {
                    Path$.ExternalSyntheticBUOutline0.m$1("call to 'resume' before 'invoke' with coroutine");
                    return null;
                }
                this = (SepStaticTileConfigRepository) c02141.L$1;
                mutex2 = (Mutex) c02141.L$0;
                try {
                    ResultKt.throwOnFailure(obj);
                    this.tileConfigs = (Map) obj;
                    mutex = mutex2;
                    mutex.unlock((Object) null);
                    return Unit.INSTANCE;
                } catch (Throwable th) {
                    th = th;
                    mutex2.unlock((Object) null);
                    throw th;
                }
            }
            i = c02141.I$0;
            Mutex mutex3 = (Mutex) c02141.L$0;
            ResultKt.throwOnFailure(obj);
            mutex = mutex3;
            if (this.tileConfigs == null) {
                c02141.L$0 = mutex;
                c02141.L$1 = this;
                c02141.I$0 = i;
                c02141.I$1 = 0;
                c02141.label = 2;
                Object objWithContext = BuildersKt.withContext(this.bgDispatcher, new SepStaticTileConfigRepository$loadTilesLocked$2(this, null), c02141);
                if (objWithContext != coroutine_suspended) {
                    mutex2 = mutex;
                    obj = objWithContext;
                    this.tileConfigs = (Map) obj;
                    mutex = mutex2;
                }
                return coroutine_suspended;
            }
            mutex.unlock((Object) null);
            return Unit.INSTANCE;
        } catch (Throwable th2) {
            th = th2;
            mutex2 = mutex;
            mutex2.unlock((Object) null);
            throw th;
        }
    }
}
