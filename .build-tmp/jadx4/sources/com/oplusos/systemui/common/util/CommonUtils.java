package com.oplusos.systemui.common.util;

import android.graphics.Rect;
import android.graphics.Region;
import android.graphics.RegionIterator;
import android.os.SystemClock;
import android.text.TextUtils;
import android.view.View;
import com.oplus.systemui.preventmode.OplusPreventModeData;
import java.io.File;
import java.lang.invoke.VarHandle;
import java.util.Collection;
import kotlin.collections.CollectionsKt___CollectionsKt;
import kotlin.jvm.internal.Intrinsics;
import kotlin.jvm.internal.Ref;

/* JADX INFO: compiled from: go/retraceme fd74bf9514dd0739b92d67a6d48a51eb10aaf8df760d83496b8dcfcf83e5f698 */
/* JADX INFO: loaded from: /home/starlight/Projects/Starlight Ports/.build-tmp/classes4.dex */
public final class CommonUtils {
    public static final CommonUtils INSTANCE = new CommonUtils();

    /* JADX INFO: renamed from: com.oplusos.systemui.common.util.CommonUtils$setDebounceClickListener$1, reason: invalid class name */
    /* JADX INFO: compiled from: go/retraceme fd74bf9514dd0739b92d67a6d48a51eb10aaf8df760d83496b8dcfcf83e5f698 */
    public final class AnonymousClass1 implements View.OnClickListener {
        public /* synthetic */ View.OnClickListener $listener;
        public /* synthetic */ Ref.LongRef $sLastClickTime;

        @Override // android.view.View.OnClickListener
        public final void onClick(View view) {
            long jUptimeMillis = SystemClock.uptimeMillis();
            if (Math.abs(jUptimeMillis - this.$sLastClickTime.element) < 500) {
                return;
            }
            this.$sLastClickTime.element = jUptimeMillis;
            this.$listener.onClick(view);
        }
    }

    public static final boolean exit(String str) {
        if (TextUtils.isEmpty(str)) {
            return false;
        }
        return new File(str).exists();
    }

    public static final <T extends Comparable<? super T>> boolean isDiffContent(Collection<? extends T> collection, Collection<? extends T> collection2) {
        if (collection.size() != collection2.size()) {
            return true;
        }
        if (collection.size() <= 1000) {
            return (collection.containsAll(collection2) && collection2.containsAll(collection)) ? false : true;
        }
        return !Intrinsics.areEqual(CollectionsKt___CollectionsKt.sorted(collection), CollectionsKt___CollectionsKt.sorted(collection2));
    }

    public static final String safeRegionToString(Region region) {
        if (region == null) {
            return "null";
        }
        StringBuilder sb = new StringBuilder("SkRegion(");
        RegionIterator regionIterator = new RegionIterator(region);
        Rect rect = new Rect();
        while (regionIterator.next(rect)) {
            sb.append("(");
            sb.append(rect.left);
            sb.append(OplusPreventModeData.SEPARATOR);
            sb.append(rect.top);
            sb.append(OplusPreventModeData.SEPARATOR);
            sb.append(rect.right);
            sb.append(OplusPreventModeData.SEPARATOR);
            sb.append(rect.bottom);
            sb.append(")");
        }
        sb.append(")");
        return sb.toString();
    }

    public static final void setDebounceClickListener(View view, View.OnClickListener onClickListener) {
        Ref.LongRef longRef = new Ref.LongRef();
        AnonymousClass1 anonymousClass1 = new AnonymousClass1();
        anonymousClass1.$sLastClickTime = longRef;
        anonymousClass1.$listener = onClickListener;
        VarHandle.storeStoreFence();
        view.setOnClickListener(anonymousClass1);
    }
}
