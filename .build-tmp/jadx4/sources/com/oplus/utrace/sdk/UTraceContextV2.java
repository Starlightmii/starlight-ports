package com.oplus.utrace.sdk;

import androidx.compose.animation.core.MonoSpline$;
import com.oplus.base.ocrscanner.oseesdk.utils.ApplicationUtil$;
import com.oplus.utrace.lib.NodeID;
import com.oplus.utrace.lib.SpanType;
import com.oplus.utrace.utils.Logs;
import kotlin.jvm.internal.DefaultConstructorMarker;
import kotlin.jvm.internal.Intrinsics;
import org.json.JSONException;
import org.json.JSONObject;

/* JADX INFO: compiled from: go/retraceme fd74bf9514dd0739b92d67a6d48a51eb10aaf8df760d83496b8dcfcf83e5f698 */
/* JADX INFO: loaded from: /home/starlight/Projects/Starlight Ports/.build-tmp/classes4.dex */
public final class UTraceContextV2 extends UTraceContext {
    public static final Companion Companion = new Companion(null);
    private static final String KEY_CURRENT = "current";
    private static final String KEY_PARENT = "parent";
    private static final String KEY_SPAN_TYPE = "spanType";
    private static final String KEY_TAG = "tag";
    private static final String KEY_TRACE_ID = "traceID";
    private static final String KEY_VALID = "valid";
    private int spanType;
    private String tags;
    private int valid;

    /* JADX INFO: compiled from: go/retraceme fd74bf9514dd0739b92d67a6d48a51eb10aaf8df760d83496b8dcfcf83e5f698 */
    public final class Companion {
        public Companion(DefaultConstructorMarker defaultConstructorMarker) {
        }

        public final UTraceContextV2 fromJSONString(String str) {
            try {
                JSONObject jSONObject = new JSONObject(str);
                return new UTraceContextV2(jSONObject.optString(UTraceContextV2.KEY_TRACE_ID, ""), new NodeID(jSONObject.optString(UTraceContextV2.KEY_CURRENT, "")), jSONObject.has(UTraceContextV2.KEY_PARENT) ? new NodeID(jSONObject.optString(UTraceContextV2.KEY_PARENT, "")) : null, jSONObject.optInt(UTraceContextV2.KEY_SPAN_TYPE, SpanType.CodeSpans.getValue()), jSONObject.optInt(UTraceContextV2.KEY_VALID, Valid.VALID.getValue()), jSONObject.optString(UTraceContextV2.KEY_TAG, null));
            } catch (Throwable th) {
                Throwable thM = ApplicationUtil$.ExternalSyntheticOutline0.m(th);
                if (thM != null) {
                    Logs.INSTANCE.e("UTraceContext", Intrinsics.stringPlus(thM, "jsonString parse to json error: "), thM);
                }
                return null;
            }
        }

        public final UTraceContextV2 fromLegacyObj$utrace_sdk_liteRelease(UTraceContext uTraceContext) {
            return new UTraceContextV2(uTraceContext.getTraceID$utrace_sdk_liteRelease(), uTraceContext.getCurrent$utrace_sdk_liteRelease(), uTraceContext.getParent$utrace_sdk_liteRelease(), SpanType.CodeSpans.getValue(), Valid.VALID.getValue(), null);
        }
    }

    /* JADX INFO: compiled from: go/retraceme fd74bf9514dd0739b92d67a6d48a51eb10aaf8df760d83496b8dcfcf83e5f698 */
    public enum Valid {
        VALID(1),
        INVALID(0);

        private int value;

        Valid(int i) {
            this.value = i;
        }

        public final int getValue() {
            return this.value;
        }

        public final void setValue(int i) {
            this.value = i;
        }
    }

    public /* synthetic */ UTraceContextV2(String str, NodeID nodeID, NodeID nodeID2, int i, int i2, String str2, int i3, DefaultConstructorMarker defaultConstructorMarker) {
        this(str, nodeID, (i3 & 4) != 0 ? null : nodeID2, (i3 & 8) != 0 ? SpanType.CodeSpans.getValue() : i, (i3 & 16) != 0 ? Valid.VALID.getValue() : i2, (i3 & 32) != 0 ? null : str2);
    }

    @Override // com.oplus.utrace.sdk.UTraceContext
    public boolean equals(Object obj) {
        if (obj == null) {
            return false;
        }
        if (this == obj) {
            return true;
        }
        if (!(obj instanceof UTraceContextV2)) {
            return false;
        }
        UTraceContextV2 uTraceContextV2 = (UTraceContextV2) obj;
        return Intrinsics.areEqual(getTraceID$utrace_sdk_liteRelease(), uTraceContextV2.getTraceID$utrace_sdk_liteRelease()) && Intrinsics.areEqual(getCurrent$utrace_sdk_liteRelease(), uTraceContextV2.getCurrent$utrace_sdk_liteRelease()) && Intrinsics.areEqual(getParent$utrace_sdk_liteRelease(), uTraceContextV2.getParent$utrace_sdk_liteRelease()) && this.spanType == uTraceContextV2.spanType && this.valid == uTraceContextV2.valid && Intrinsics.areEqual(this.tags, uTraceContextV2.tags);
    }

    public final int getSpanType$utrace_sdk_liteRelease() {
        return this.spanType;
    }

    public final String getTag() {
        return this.tags;
    }

    public final int getValid$utrace_sdk_liteRelease() {
        return this.valid;
    }

    @Override // com.oplus.utrace.sdk.UTraceContext
    public int hashCode() {
        int iHashCode = (getCurrent$utrace_sdk_liteRelease().hashCode() + (getTraceID$utrace_sdk_liteRelease().hashCode() * 31)) * 31;
        NodeID parent$utrace_sdk_liteRelease = getParent$utrace_sdk_liteRelease();
        int iM = MonoSpline$.ExternalSyntheticOutline0.m(this.valid, MonoSpline$.ExternalSyntheticOutline0.m(this.spanType, (iHashCode + (parent$utrace_sdk_liteRelease == null ? 0 : parent$utrace_sdk_liteRelease.hashCode())) * 31, 31), 31);
        String str = this.tags;
        return iM + (str != null ? str.hashCode() : 0);
    }

    public final void setSpanType$utrace_sdk_liteRelease(int i) {
        this.spanType = i;
    }

    public final void setTag(String str) {
        this.tags = str;
    }

    public final void setValid$utrace_sdk_liteRelease(int i) {
        this.valid = i;
    }

    public final String toJsonString() throws JSONException {
        JSONObject jSONObject = new JSONObject();
        jSONObject.put(KEY_TRACE_ID, getTraceID$utrace_sdk_liteRelease());
        jSONObject.put(KEY_CURRENT, getCurrent$utrace_sdk_liteRelease().toJsonString());
        NodeID parent$utrace_sdk_liteRelease = getParent$utrace_sdk_liteRelease();
        jSONObject.put(KEY_PARENT, parent$utrace_sdk_liteRelease == null ? null : parent$utrace_sdk_liteRelease.toJsonString());
        jSONObject.put(KEY_SPAN_TYPE, getSpanType$utrace_sdk_liteRelease());
        jSONObject.put(KEY_VALID, getValid$utrace_sdk_liteRelease());
        jSONObject.put(KEY_TAG, this.tags);
        Logs.INSTANCE.d("UTraceContext", Intrinsics.stringPlus(jSONObject, "toJsonString, "));
        return jSONObject.toString();
    }

    public final UTraceContext toLegacyObj() {
        return new UTraceContext(getTraceID$utrace_sdk_liteRelease(), getCurrent$utrace_sdk_liteRelease(), getParent$utrace_sdk_liteRelease());
    }

    @Override // com.oplus.utrace.sdk.UTraceContext
    public String toString() {
        return "UTraceContext(traceID=" + getTraceID$utrace_sdk_liteRelease() + ", current=" + getCurrent$utrace_sdk_liteRelease() + ", parent=" + getParent$utrace_sdk_liteRelease() + ", spanType=" + this.spanType + ", valid=" + this.valid + ", tag=" + ((Object) this.tags) + ')';
    }

    public UTraceContextV2() {
        this.spanType = SpanType.CodeSpans.getValue();
        this.valid = Valid.VALID.getValue();
    }

    public UTraceContextV2(String str, NodeID nodeID, NodeID nodeID2, int i, int i2, String str2) {
        this();
        setTraceID$utrace_sdk_liteRelease(str);
        setCurrent$utrace_sdk_liteRelease(nodeID);
        setParent$utrace_sdk_liteRelease(nodeID2);
        this.spanType = i;
        this.valid = i2;
        this.tags = str2;
    }
}
