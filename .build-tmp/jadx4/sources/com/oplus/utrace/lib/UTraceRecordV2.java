package com.oplus.utrace.lib;

import com.oplus.utrace.utils.Logs;
import java.util.Map;
import kotlin.collections.MapsKt__MapsKt;
import kotlin.jvm.internal.Intrinsics;
import org.json.JSONException;
import org.json.JSONObject;

/* JADX INFO: compiled from: go/retraceme fd74bf9514dd0739b92d67a6d48a51eb10aaf8df760d83496b8dcfcf83e5f698 */
/* JADX INFO: loaded from: /home/starlight/Projects/Starlight Ports/.build-tmp/classes4.dex */
public final class UTraceRecordV2 {
    public static final Companion Companion = new Companion();
    private static final String KEY_CURRENT = "current";
    private static final String KEY_END_TIME = "endTime";
    private static final String KEY_HAS_ERROR = "hasError";
    private static final String KEY_INFO = "info";
    private static final String KEY_PARENT = "parent";
    private static final String KEY_SPAN_NAME = "spanName";
    private static final String KEY_SPAN_TYPE = "spanType";
    private static final String KEY_START_TIME = "startTime";
    private static final String KEY_STATUS = "status";
    private static final String KEY_STATUS_CODE = "statusCode";
    private static final String KEY_TAGS = "tags";
    private static final String KEY_TRACE_ID = "traceID";
    private static final String KEY_TYPE = "type";
    private NodeID current;
    public long endTime;
    public int hasError;
    private String info;
    private NodeID parent;
    private String spanName;
    public int spanType;
    public long startTime;
    public int status;
    public int statusCode;
    private Map<String, String> tags;
    private String traceID;
    public int type;

    /* JADX INFO: compiled from: go/retraceme fd74bf9514dd0739b92d67a6d48a51eb10aaf8df760d83496b8dcfcf83e5f698 */
    public enum RecordType {
        NONE(0),
        START(1),
        END(2),
        ERROR(3),
        SPAN_TAG(4),
        TRACE_TAG(5),
        TRACE_FLAG(6);

        private int value;

        RecordType(int i) {
            this.value = i;
        }

        public final int getValue() {
            return this.value;
        }

        public final void setValue(int i) {
            this.value = i;
        }
    }

    /* JADX INFO: compiled from: go/retraceme fd74bf9514dd0739b92d67a6d48a51eb10aaf8df760d83496b8dcfcf83e5f698 */
    public enum Status {
        START(0),
        END_GO_AHEAD(1),
        END_COMPLETE(2),
        END_RETURN(3);

        private int value;

        Status(int i) {
            this.value = i;
        }

        public final int getValue() {
            return this.value;
        }

        public final void setValue(int i) {
            this.value = i;
        }
    }

    /* JADX INFO: compiled from: go/retraceme fd74bf9514dd0739b92d67a6d48a51eb10aaf8df760d83496b8dcfcf83e5f698 */
    public enum StatusError {
        NO_ERROR(0),
        ERROR(1);

        private int value;

        StatusError(int i) {
            this.value = i;
        }

        public final int getValue() {
            return this.value;
        }

        public final void setValue(int i) {
            this.value = i;
        }
    }

    public UTraceRecordV2(String str, NodeID nodeID, NodeID nodeID2, String str2, long j, long j2, int i, String str3, int i2, int i3, int i4, int i5, Map<String, String> map) {
        this.traceID = "";
        this.current = new NodeID();
        this.spanName = "";
        this.info = "";
        this.hasError = StatusError.NO_ERROR.getValue();
        this.type = RecordType.NONE.getValue();
        this.spanType = SpanType.CodeSpans.getValue();
        MapsKt__MapsKt.emptyMap();
        this.traceID = str;
        this.current = nodeID;
        this.parent = nodeID2;
        this.spanName = str2;
        this.startTime = j;
        this.endTime = j2;
        this.status = i;
        this.info = str3;
        this.hasError = i2;
        this.statusCode = i3;
        this.type = i4;
        this.spanType = i5;
        this.tags = map;
    }

    public final NodeID getCurrent() {
        return this.current;
    }

    public final long getEndTime() {
        return this.endTime;
    }

    public final int getHasError() {
        return this.hasError;
    }

    public final String getInfo() {
        return this.info;
    }

    public final NodeID getParent() {
        return this.parent;
    }

    public final String getSpanName() {
        return this.spanName;
    }

    public final int getSpanType() {
        return this.spanType;
    }

    public final long getStartTime() {
        return this.startTime;
    }

    public final int getStatus() {
        return this.status;
    }

    public final int getStatusCode() {
        return this.statusCode;
    }

    public final Map<String, String> getTags() {
        return this.tags;
    }

    public final String getTraceID() {
        return this.traceID;
    }

    public final int getType() {
        return this.type;
    }

    public final void setCurrent(NodeID nodeID) {
        this.current = nodeID;
    }

    public final void setEndTime(long j) {
        this.endTime = j;
    }

    public final void setHasError(int i) {
        this.hasError = i;
    }

    public final void setInfo(String str) {
        this.info = str;
    }

    public final void setParent(NodeID nodeID) {
        this.parent = nodeID;
    }

    public final void setSpanName(String str) {
        this.spanName = str;
    }

    public final void setSpanType(int i) {
        this.spanType = i;
    }

    public final void setStartTime(long j) {
        this.startTime = j;
    }

    public final void setStatus(int i) {
        this.status = i;
    }

    public final void setStatusCode(int i) {
        this.statusCode = i;
    }

    public final void setTags(Map<String, String> map) {
        this.tags = map;
    }

    public final void setTraceID(String str) {
        this.traceID = str;
    }

    public final void setType(int i) {
        this.type = i;
    }

    public final String toJsonString() throws JSONException {
        JSONObject jSONObject = new JSONObject();
        jSONObject.put(KEY_TRACE_ID, getTraceID());
        jSONObject.put(KEY_CURRENT, getCurrent().toJsonString());
        NodeID parent = getParent();
        jSONObject.put(KEY_PARENT, parent == null ? null : parent.toJsonString());
        jSONObject.put(KEY_SPAN_NAME, getSpanName());
        jSONObject.put("startTime", getStartTime());
        jSONObject.put("endTime", getEndTime());
        jSONObject.put("status", getStatus());
        jSONObject.put(KEY_INFO, getInfo());
        jSONObject.put(KEY_HAS_ERROR, getHasError());
        jSONObject.put(KEY_STATUS_CODE, getStatusCode());
        jSONObject.put("type", getType());
        jSONObject.put(KEY_SPAN_TYPE, getSpanType());
        jSONObject.put(KEY_TAGS, new JSONObject(getTags()));
        Logs.INSTANCE.d("UTraceRecordV2", Intrinsics.stringPlus(jSONObject, "toJsonString, "));
        return jSONObject.toString();
    }

    public final UTraceRecord toLegacyObj() {
        return new UTraceRecord(this.traceID, this.current, this.parent, this.spanName, this.startTime, this.endTime, this.status, this.info, this.hasError);
    }

    public String toString() {
        StringBuilder sb = new StringBuilder("UTraceRecord(traceID='");
        sb.append(this.traceID);
        sb.append("', current=");
        sb.append(this.current.getSpanID(true));
        sb.append(", parent=");
        NodeID nodeID = this.parent;
        sb.append((Object) (nodeID == null ? null : nodeID.getSpanID(true)));
        sb.append(", spanName='");
        sb.append(this.spanName);
        sb.append("', status=");
        sb.append(this.status);
        sb.append(", info='");
        sb.append(this.info);
        sb.append("', statusCode=");
        sb.append(this.statusCode);
        sb.append(", hasError=");
        sb.append(this.hasError);
        sb.append(", type=");
        sb.append(this.type);
        sb.append(", spanType=");
        sb.append(this.spanType);
        sb.append(", tags=");
        sb.append(this.tags);
        sb.append(')');
        return sb.toString();
    }
}
