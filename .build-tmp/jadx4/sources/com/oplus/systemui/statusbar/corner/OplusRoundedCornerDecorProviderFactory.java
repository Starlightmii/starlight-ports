package com.oplus.systemui.statusbar.corner;

import com.android.systemui.decor.DecorProvider;
import com.android.systemui.decor.DecorProviderFactory;
import com.android.systemui.decor.RoundedCornerResDelegate;
import java.util.List;
import kotlin.collections.CollectionsKt__CollectionsKt;

/* JADX INFO: compiled from: go/retraceme fd74bf9514dd0739b92d67a6d48a51eb10aaf8df760d83496b8dcfcf83e5f698 */
/* JADX INFO: loaded from: /home/starlight/Projects/Starlight Ports/.build-tmp/classes4.dex */
public final class OplusRoundedCornerDecorProviderFactory implements DecorProviderFactory {
    private final RoundedCornerResDelegate roundedCornerResDelegate;

    public OplusRoundedCornerDecorProviderFactory(RoundedCornerResDelegate roundedCornerResDelegate) {
        this.roundedCornerResDelegate = roundedCornerResDelegate;
    }

    public boolean getHasProviders() {
        RoundedCornerResDelegate roundedCornerResDelegate = this.roundedCornerResDelegate;
        return roundedCornerResDelegate.getHasTop() || roundedCornerResDelegate.getHasBottom();
    }

    public List<DecorProvider> getProviders() {
        boolean hasTop = this.roundedCornerResDelegate.getHasTop();
        boolean hasBottom = this.roundedCornerResDelegate.getHasBottom();
        if (hasTop && hasBottom) {
            return CollectionsKt__CollectionsKt.listOf(new OplusRoundedCornerDecorProviderImpl[]{new OplusRoundedCornerDecorProviderImpl(2131364955, 1, 0, this.roundedCornerResDelegate), new OplusRoundedCornerDecorProviderImpl(2131364956, 1, 2, this.roundedCornerResDelegate), new OplusRoundedCornerDecorProviderImpl(2131364953, 3, 0, this.roundedCornerResDelegate), new OplusRoundedCornerDecorProviderImpl(2131364954, 3, 2, this.roundedCornerResDelegate)});
        }
        if (hasTop) {
            return CollectionsKt__CollectionsKt.listOf(new OplusRoundedCornerDecorProviderImpl[]{new OplusRoundedCornerDecorProviderImpl(2131364955, 1, 0, this.roundedCornerResDelegate), new OplusRoundedCornerDecorProviderImpl(2131364956, 1, 2, this.roundedCornerResDelegate)});
        }
        return hasBottom ? CollectionsKt__CollectionsKt.listOf(new OplusRoundedCornerDecorProviderImpl[]{new OplusRoundedCornerDecorProviderImpl(2131364953, 3, 0, this.roundedCornerResDelegate), new OplusRoundedCornerDecorProviderImpl(2131364954, 3, 2, this.roundedCornerResDelegate)}) : CollectionsKt__CollectionsKt.emptyList();
    }
}
