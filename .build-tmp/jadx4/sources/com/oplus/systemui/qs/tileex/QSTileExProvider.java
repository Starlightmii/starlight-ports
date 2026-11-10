package com.oplus.systemui.qs.tileex;

import com.android.systemui.qs.tile.QSTileEx;
import java.lang.invoke.VarHandle;
import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;
import java.util.function.Function;
import javax.inject.Provider;
import kotlin.text.StringsKt__StringsJVMKt;
import kotlin.text.StringsKt__StringsKt;

/* JADX INFO: compiled from: go/retraceme fd74bf9514dd0739b92d67a6d48a51eb10aaf8df760d83496b8dcfcf83e5f698 */
/* JADX INFO: loaded from: /home/starlight/Projects/Starlight Ports/.build-tmp/classes4.dex */
public final class QSTileExProvider implements QSTileEx.Provider {
    private final CustomTileEx customTileEx;

    /* JADX INFO: renamed from: default, reason: not valid java name */
    private final QSTileExImpl f1default;
    private final ConcurrentHashMap<String, QSTileEx> instances = new ConcurrentHashMap<>();
    private final Map<String, Provider> tileExProvider;

    /* JADX INFO: renamed from: com.oplus.systemui.qs.tileex.QSTileExProvider$get$1, reason: invalid class name */
    /* JADX INFO: compiled from: go/retraceme fd74bf9514dd0739b92d67a6d48a51eb10aaf8df760d83496b8dcfcf83e5f698 */
    public final class AnonymousClass1 implements Function {
        public /* synthetic */ QSTileExProvider this$0;

        @Override // java.util.function.Function
        public final Object apply(Object obj) {
            return QSTileExProvider.access$create(this.this$0, (String) obj);
        }
    }

    public QSTileExProvider(Map<String, Provider> map, QSTileExImpl qSTileExImpl, CustomTileEx customTileEx) {
        this.tileExProvider = map;
        this.f1default = qSTileExImpl;
        this.customTileEx = customTileEx;
    }

    public static final QSTileEx access$create(QSTileExProvider qSTileExProvider, String str) {
        Provider provider = qSTileExProvider.tileExProvider.get(str);
        if (provider != null) {
            return (QSTileEx) provider.get();
        }
        return StringsKt__StringsJVMKt.startsWith(str, "custom(", false) ? qSTileExProvider.customTileEx : qSTileExProvider.f1default;
    }

    public QSTileEx get(String str) {
        if (str == null || StringsKt__StringsKt.isBlank(str)) {
            return this.f1default;
        }
        ConcurrentHashMap<String, QSTileEx> concurrentHashMap = this.instances;
        AnonymousClass1 anonymousClass1 = new AnonymousClass1();
        anonymousClass1.this$0 = this;
        VarHandle.storeStoreFence();
        return concurrentHashMap.computeIfAbsent(str, anonymousClass1);
    }
}
