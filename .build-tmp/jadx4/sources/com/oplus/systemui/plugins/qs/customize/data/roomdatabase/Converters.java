package com.oplus.systemui.plugins.qs.customize.data.roomdatabase;

import com.oplusos.systemui.common.model.Loc;
import com.oplusos.systemui.common.model.LocCache;
import com.oplusos.systemui.common.model.SpanCache;
import com.oplusos.systemui.common.model.SpanSize;
import kotlin.Result;
import kotlin.ResultKt;
import kotlin.text.StringsKt__StringsKt;

/* JADX INFO: compiled from: go/retraceme fd74bf9514dd0739b92d67a6d48a51eb10aaf8df760d83496b8dcfcf83e5f698 */
/* JADX INFO: loaded from: /home/starlight/Projects/Starlight Ports/.build-tmp/classes4.dex */
public final class Converters {
    public static final Companion Companion = new Companion();
    private static final String SEPARATOR = ",";

    /* JADX INFO: compiled from: go/retraceme fd74bf9514dd0739b92d67a6d48a51eb10aaf8df760d83496b8dcfcf83e5f698 */
    public final class Companion {
    }

    public final String locToString(Loc loc) {
        return loc.getPageIndex() + "," + loc.getStartCol() + "," + loc.getStartRow();
    }

    public final String spanSizeToString(SpanSize spanSize) {
        return spanSize.getColSpan() + "," + spanSize.getRowSpan();
    }

    public final Loc stringToLoc(String str) {
        Object objLoc$default;
        try {
            int iIndexOf$default = StringsKt__StringsKt.indexOf$default(str, ",", 0, false, 6);
            int iLastIndexOf$default = StringsKt__StringsKt.lastIndexOf$default(str, ",", 0, 6);
            objLoc$default = Result.constructor-impl(LocCache.INSTANCE.loc(Integer.parseInt(str.substring(0, iIndexOf$default)), Integer.parseInt(str.substring(iIndexOf$default + 1, iLastIndexOf$default)), Integer.parseInt(str.substring(iLastIndexOf$default + 1, str.length()))));
        } catch (Throwable th) {
            objLoc$default = Result.constructor-impl(ResultKt.createFailure(th));
        }
        if (Result.exceptionOrNull-impl(objLoc$default) != null) {
            objLoc$default = LocCache.loc$default(LocCache.INSTANCE, 0, 0, 7);
        }
        return (Loc) objLoc$default;
    }

    public final SpanSize stringToSpanSize(String str) {
        Object objSpanSize;
        try {
            int iIndexOf$default = StringsKt__StringsKt.indexOf$default(str, ",", 0, false, 6);
            objSpanSize = Result.constructor-impl(SpanCache.INSTANCE.spanSize(Integer.parseInt(str.substring(0, iIndexOf$default)), Integer.parseInt(str.substring(iIndexOf$default + 1, str.length()))));
        } catch (Throwable th) {
            objSpanSize = Result.constructor-impl(ResultKt.createFailure(th));
        }
        if (Result.exceptionOrNull-impl(objSpanSize) != null) {
            objSpanSize = SpanCache.INSTANCE.spanSize(1, 1);
        }
        return (SpanSize) objSpanSize;
    }
}
