package com.oplus.systemui.notification.row;

import com.android.systemui.statusbar.notification.row.ExpandableView;
import com.android.systemui.statusbar.notification.stack.NotificationStackScrollLayout;
import java.util.Iterator;
import kotlin.jvm.internal.markers.KMappedMarker;

/* JADX INFO: compiled from: go/retraceme fd74bf9514dd0739b92d67a6d48a51eb10aaf8df760d83496b8dcfcf83e5f698 */
/* JADX INFO: loaded from: /home/starlight/Projects/Starlight Ports/.build-tmp/classes4.dex */
public final class OplusShelfExImpl$transientExpandable$iterator$1 implements Iterator, KMappedMarker {
    public /* synthetic */ NotificationStackScrollLayout $nssl;
    public int currIndex;

    public final int getCurrIndex() {
        return this.currIndex;
    }

    @Override // java.util.Iterator
    public final boolean hasNext() {
        int transientViewCount = this.$nssl.getTransientViewCount();
        int i = this.currIndex + 1;
        this.currIndex = i;
        return i >= 0 && i < transientViewCount;
    }

    @Override // java.util.Iterator
    public final Object next() {
        ExpandableView transientView = this.$nssl.getTransientView(this.currIndex);
        if (transientView instanceof ExpandableView) {
            return transientView;
        }
        return null;
    }

    @Override // java.util.Iterator
    public final void remove() {
        throw new UnsupportedOperationException("Operation is not supported for read-only collection");
    }

    public final void setCurrIndex(int i) {
        this.currIndex = i;
    }
}
