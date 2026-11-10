package com.oplus.vfxsdk.common;

import com.coe.expr.Expressions;
import java.math.BigDecimal;
import java.util.HashMap;
import java.util.Map;
import kotlin.text.StringsKt__StringsKt;

/* JADX INFO: compiled from: go/retraceme fd74bf9514dd0739b92d67a6d48a51eb10aaf8df760d83496b8dcfcf83e5f698 */
/* JADX INFO: loaded from: /home/starlight/Projects/Starlight Ports/.build-tmp/classes4.dex */
public final class COEExpressions {
    private final HashMap<String, String[]> exprRecord;
    private final Expressions expressions = new Expressions();

    public COEExpressions(HashMap<String, String[]> map) {
        this.exprRecord = map;
        if (map != null) {
            for (Map.Entry<String, String[]> entry : map.entrySet()) {
                this.expressions.define(entry.getKey(), entry.getValue()[0]);
            }
        }
    }

    public final void define(String str, long j) {
        this.expressions.define(str, j);
    }

    public final BigDecimal eval(String str) {
        return this.expressions.evalWithCache(str);
    }

    public final HashMap<String, String[]> getExprRecord() {
        return this.exprRecord;
    }

    public final BigDecimal getValue(String str, float f, float f2) {
        if (StringsKt__StringsKt.contains(str, "origin", false)) {
            this.expressions.define("origin", f2);
        }
        return this.expressions.evalWithCache(str);
    }

    public final void switchAnimIndex(int i) {
        HashMap<String, String[]> map = this.exprRecord;
        if (map != null) {
            for (Map.Entry<String, String[]> entry : map.entrySet()) {
                this.expressions.define(entry.getKey(), entry.getValue()[i]);
            }
        }
    }

    public final void define(String str, double d) {
        this.expressions.define(str, d);
    }

    public final void define(String str, BigDecimal bigDecimal) {
        this.expressions.define(str, bigDecimal);
    }

    public final void define(String str, String str2) {
        this.expressions.define(str, str2);
    }
}
