package com.oplus.utrace.lib;

import android.os.Parcel;
import android.os.Parcelable;
import com.oplus.utrace.utils.Logs;
import kotlin.Lazy;
import kotlin.LazyKt__LazyJVMKt;
import kotlin.Result;
import kotlin.ResultKt;
import kotlin.Unit;
import kotlin.jvm.functions.Function0;
import kotlin.jvm.internal.DefaultConstructorMarker;
import kotlin.jvm.internal.Intrinsics;
import org.json.JSONException;
import org.json.JSONObject;

/* JADX INFO: compiled from: go/retraceme fd74bf9514dd0739b92d67a6d48a51eb10aaf8df760d83496b8dcfcf83e5f698 */
/* JADX INFO: loaded from: /home/starlight/Projects/Starlight Ports/.build-tmp/classes4.dex */
public final class NodeID implements Parcelable {
    public static final CREATOR CREATOR = new CREATOR();
    private final Lazy composedSpanId$delegate;
    private int sequence;
    private String spanID;

    public NodeID(String str) {
        Object obj;
        this();
        if (str.length() == 0) {
            this.spanID = "";
            this.sequence = -1;
            Logs.INSTANCE.e("UTrace.Sdk.NodeID", "create NodeId from json error: jsonString is empty.");
            return;
        }
        try {
            JSONObject jSONObject = new JSONObject(str);
            this.spanID = jSONObject.optString("spanID", "");
            this.sequence = jSONObject.optInt("sequence", -1);
            obj = Result.constructor-impl(Unit.INSTANCE);
        } catch (Throwable th) {
            obj = Result.constructor-impl(ResultKt.createFailure(th));
        }
        Throwable th2 = Result.exceptionOrNull-impl(obj);
        if (th2 == null) {
            return;
        }
        this.spanID = "";
        this.sequence = -1;
        Logs.INSTANCE.e("UTrace.Sdk.NodeID", Intrinsics.stringPlus(th2.getMessage(), "create NodeId from json error: "), th2);
    }

    private final String getComposedSpanId() {
        return (String) this.composedSpanId$delegate.getValue();
    }

    public static /* synthetic */ String getSpanID$default(NodeID nodeID, boolean z, int i, Object obj) {
        if ((i & 1) != 0) {
            z = false;
        }
        return nodeID.getSpanID(z);
    }

    @Override // android.os.Parcelable
    public int describeContents() {
        return 0;
    }

    public boolean equals(Object obj) {
        if (obj == null) {
            return false;
        }
        if (this == obj) {
            return true;
        }
        if (!(obj instanceof NodeID)) {
            return false;
        }
        NodeID nodeID = (NodeID) obj;
        return Intrinsics.areEqual(nodeID.spanID, this.spanID) && nodeID.sequence == this.sequence;
    }

    public final String getSpanID(boolean z) {
        return z ? getComposedSpanId() : this.spanID;
    }

    public int hashCode() {
        return (this.spanID.hashCode() * 31) + this.sequence;
    }

    public final String toJsonString() throws JSONException {
        JSONObject jSONObject = new JSONObject();
        jSONObject.put("spanID", this.spanID);
        jSONObject.put("sequence", this.sequence);
        return jSONObject.toString();
    }

    public String toString() {
        return getComposedSpanId();
    }

    @Override // android.os.Parcelable
    public void writeToParcel(Parcel parcel, int i) {
        parcel.writeString(this.spanID);
        parcel.writeInt(this.sequence);
    }

    public NodeID(String str, int i) {
        this();
        this.spanID = str;
        this.sequence = i;
    }

    public /* synthetic */ NodeID(String str, int i, int i2, DefaultConstructorMarker defaultConstructorMarker) {
        this((i2 & 1) != 0 ? "" : str, (i2 & 2) != 0 ? -1 : i);
    }

    public NodeID(Parcel parcel) {
        Object obj;
        this();
        String str = "";
        if (parcel != null) {
            try {
                String string = parcel.readString();
                if (string != null) {
                    str = string;
                }
            } catch (Throwable th) {
                obj = Result.constructor-impl(ResultKt.createFailure(th));
            }
        }
        this.spanID = str;
        this.sequence = parcel == null ? -1 : parcel.readInt();
        obj = Result.constructor-impl(Unit.INSTANCE);
        Throwable th2 = Result.exceptionOrNull-impl(obj);
        if (th2 == null) {
            return;
        }
        Logs.INSTANCE.w("UTrace.Sdk.NodeID", "parcel parse exception: " + ((Object) th2.getMessage()) + ' ');
    }

    public NodeID() {
        this.spanID = "";
        this.sequence = -1;
        this.composedSpanId$delegate = LazyKt__LazyJVMKt.lazy(new Function0() { // from class: com.oplus.utrace.lib.NodeID$composedSpanId$2
            {
                super(0);
            }

            public final String invoke() {
                return this.this$0.spanID + '@' + this.this$0.sequence;
            }
        });
    }
}
