package com.oplus.systemui.qs.pipeline.domain.interactor;

import android.content.ComponentName;
import android.util.Log;
import android.util.SparseArray;
import com.android.keyguard.logging.KeyguardLogger$;
import com.android.systemui.process.ProcessWrapper;
import com.android.systemui.qs.pipeline.data.repository.TilesSettingConverter;
import com.android.systemui.qs.pipeline.domain.interactor.CurrentTilesInteractorEx;
import com.android.systemui.qs.pipeline.shared.TileSpec;
import com.android.systemui.util.settings.SecureSettings;
import com.oplusos.systemui.common.util.LogUtil;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;
import java.util.Map;
import java.util.Set;
import javax.inject.Provider;
import kotlin.ResultKt;
import kotlin.Unit;
import kotlin.collections.CollectionsKt__IterablesKt;
import kotlin.coroutines.Continuation;
import kotlin.coroutines.CoroutineContext;
import kotlin.coroutines.intrinsics.IntrinsicsKt__IntrinsicsKt;
import kotlin.coroutines.jvm.internal.SuspendLambda;
import kotlin.jvm.functions.Function2;
import kotlin.jvm.internal.Intrinsics;
import kotlinx.coroutines.BuildersKt;
import kotlinx.coroutines.CoroutineScope;
import kotlinx.coroutines.CoroutineStart;
import okio.Path$;

/* JADX INFO: compiled from: go/retraceme fd74bf9514dd0739b92d67a6d48a51eb10aaf8df760d83496b8dcfcf83e5f698 */
/* JADX INFO: loaded from: /home/starlight/Projects/Starlight Ports/.build-tmp/classes4.dex */
public final class CurrentTilesInteractorExImpl implements CurrentTilesInteractorEx {
    public static final Companion Companion = new Companion();
    private static final String OPLUS_SETTING = "oplus_sysui_qs_tiles";
    private static final String TAG = "CurrentTilesInteractorExImpl";
    private final Map<String, Provider> filters;
    private final Map<String, Provider> mappers;
    private final SparseArray<String> oplusQSTileSettings = new SparseArray<>();
    private final ProcessWrapper processWrapper;
    private final SecureSettings secureSettings;

    /* JADX INFO: compiled from: go/retraceme fd74bf9514dd0739b92d67a6d48a51eb10aaf8df760d83496b8dcfcf83e5f698 */
    public final class Companion {
    }

    /* JADX INFO: renamed from: com.oplus.systemui.qs.pipeline.domain.interactor.CurrentTilesInteractorExImpl$storeTileSpecs$1, reason: invalid class name */
    /* JADX INFO: compiled from: go/retraceme fd74bf9514dd0739b92d67a6d48a51eb10aaf8df760d83496b8dcfcf83e5f698 */
    final class AnonymousClass1 extends SuspendLambda implements Function2 {
        final /* synthetic */ String $specString;
        final /* synthetic */ int $userId;
        int label;

        /* JADX WARN: 'super' call moved to the top of the method (can break code semantics) */
        public AnonymousClass1(String str, int i, Continuation continuation) {
            super(2, continuation);
            this.$specString = str;
            this.$userId = i;
        }

        public final Continuation create(Object obj, Continuation continuation) {
            return CurrentTilesInteractorExImpl.this.new AnonymousClass1(this.$specString, this.$userId, continuation);
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
            CurrentTilesInteractorExImpl.this.secureSettings.putStringForUser(CurrentTilesInteractorExImpl.OPLUS_SETTING, this.$specString, (String) null, false, this.$userId, true);
            return Unit.INSTANCE;
        }
    }

    public CurrentTilesInteractorExImpl(Map<String, Provider> map, Map<String, Provider> map2, ProcessWrapper processWrapper, SecureSettings secureSettings) {
        this.filters = map;
        this.mappers = map2;
        this.processWrapper = processWrapper;
        this.secureSettings = secureSettings;
    }

    public boolean filterTileSpec(TileSpec tileSpec) {
        if (tileSpec instanceof TileSpec.CustomTileSpec) {
            Provider provider = this.filters.get(((TileSpec.CustomTileSpec) tileSpec).getSpec());
            if (provider != null ? Intrinsics.areEqual(provider.get(), Boolean.TRUE) : false) {
                if (!LogUtil.getNormalDebug() || !LogUtil.isTagValid(LogUtil.TAG_QS)) {
                    return true;
                }
                Log.d("SystemUi--QuickSettings", "CurrentTilesInteractorExImpl-->".concat("filterTileSpec: " + tileSpec + " is filtered"));
                return true;
            }
        }
        return false;
    }

    public final boolean interceptPrependDefault() {
        return true;
    }

    public final boolean isNeedTileCollection() {
        this.processWrapper.getClass();
        boolean zIsSystemUser = ProcessWrapper.isSystemUser();
        LogUtil.i$default(LogUtil.TAG_QS, TAG, KeyguardLogger$.ExternalSyntheticOutline0.m("isNeedTileCollection: isSystemUser= ", zIsSystemUser), null, 8);
        return zIsSystemUser;
    }

    public boolean keepNotInstalledTileSpecs(TileSpec.CustomTileSpec customTileSpec, Set<ComponentName> set) {
        return set.isEmpty();
    }

    public List<TileSpec> mapperTileSpecs(TileSpec tileSpec) {
        ArrayList arrayList;
        List list;
        Provider provider = this.mappers.get(tileSpec.getSpec());
        if (provider == null || (list = (List) provider.get()) == null) {
            arrayList = null;
        } else {
            List list2 = list;
            arrayList = new ArrayList(CollectionsKt__IterablesKt.collectionSizeOrDefault(list2, 10));
            Iterator it = list2.iterator();
            while (it.hasNext()) {
                arrayList.add(TileSpec.Companion.create((String) it.next()));
            }
        }
        if (arrayList == null) {
            return null;
        }
        if (LogUtil.getNormalDebug() && LogUtil.isTagValid(LogUtil.TAG_QS)) {
            Log.d("SystemUi--QuickSettings", "CurrentTilesInteractorExImpl-->".concat("mapperTileSpecs: " + tileSpec + " is mapped to " + arrayList));
        }
        return arrayList;
    }

    public void storeTileSpecs(List<? extends TileSpec> list, int i) {
        String specString = TilesSettingConverter.INSTANCE.toSpecString(list);
        if (Intrinsics.areEqual(this.oplusQSTileSettings.get(i), specString)) {
            return;
        }
        this.oplusQSTileSettings.set(i, specString);
        BuildersKt.launch$default(this.secureSettings.getSettingsScope(), (CoroutineContext) null, (CoroutineStart) null, new AnonymousClass1(specString, i, null), 3);
    }
}
