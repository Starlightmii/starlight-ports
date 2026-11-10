package com.oplus.utrace.sdk;

import android.os.Parcel;
import android.os.Parcelable;
import com.oplus.utrace.lib.NodeID;
import com.oplus.utrace.utils.ExceptionProtectUtilKt;
import com.oplus.utrace.utils.TraceUtil;
import kotlin.Deprecated;
import kotlin.jvm.internal.DefaultConstructorMarker;
import kotlin.jvm.internal.Intrinsics;

/* JADX INFO: compiled from: go/retraceme fd74bf9514dd0739b92d67a6d48a51eb10aaf8df760d83496b8dcfcf83e5f698 */
/* JADX INFO: loaded from: /home/starlight/Projects/Starlight Ports/.build-tmp/classes4.dex */
public class UTraceContext implements Parcelable {
    public static final CREATOR CREATOR = new CREATOR(null);
    private NodeID current;
    private NodeID parent;
    private String traceID;

    /* JADX WARN: Illegal instructions before constructor call */
    public UTraceContext(Parcel parcel) {
        String string;
        String str = "";
        if (parcel != null && (string = parcel.readString()) != null) {
            str = string;
        }
        NodeID nodeID = parcel == null ? null : (NodeID) ExceptionProtectUtilKt.readParcelableSafe(parcel, NodeID.class.getClassLoader());
        this(str, nodeID == null ? new NodeID() : nodeID, parcel != null ? (NodeID) ExceptionProtectUtilKt.readParcelableSafe(parcel, NodeID.class.getClassLoader()) : null);
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
        if (!(obj instanceof UTraceContext)) {
            return false;
        }
        UTraceContext uTraceContext = (UTraceContext) obj;
        return Intrinsics.areEqual(this.traceID, uTraceContext.traceID) && Intrinsics.areEqual(this.current, uTraceContext.current) && Intrinsics.areEqual(this.parent, uTraceContext.parent);
    }

    public final NodeID getCurrent$utrace_sdk_liteRelease() {
        return this.current;
    }

    public final NodeID getParent$utrace_sdk_liteRelease() {
        return this.parent;
    }

    public final String getTraceID$utrace_sdk_liteRelease() {
        return this.traceID;
    }

    public int hashCode() {
        int iHashCode = (this.current.hashCode() + (this.traceID.hashCode() * 31)) * 31;
        NodeID nodeID = this.parent;
        return iHashCode + (nodeID == null ? 0 : nodeID.hashCode());
    }

    @Deprecated
    public final <T> T serialize() {
        Intrinsics.throwUndefinedForReified();
        throw null;
    }

    @Deprecated
    public final byte[] serializeToBytes() {
        return TraceUtil.marshall(this);
    }

    @Deprecated
    public final String serializeToString() {
        return TraceUtil.encodeToString(TraceUtil.marshall(this));
    }

    public final void setCurrent$utrace_sdk_liteRelease(NodeID nodeID) {
        this.current = nodeID;
    }

    public final void setParent$utrace_sdk_liteRelease(NodeID nodeID) {
        this.parent = nodeID;
    }

    public final void setTraceID$utrace_sdk_liteRelease(String str) {
        this.traceID = str;
    }

    public String toString() {
        return "UTraceContext(traceID='" + this.traceID + "', current=" + this.current + ", parent=" + this.parent + ')';
    }

    @Override // android.os.Parcelable
    public void writeToParcel(Parcel parcel, int i) {
        parcel.writeString(this.traceID);
        parcel.writeParcelable(this.current, i);
        parcel.writeParcelable(this.parent, i);
    }

    /* JADX INFO: compiled from: go/retraceme fd74bf9514dd0739b92d67a6d48a51eb10aaf8df760d83496b8dcfcf83e5f698 */
    public final class CREATOR implements Parcelable.Creator<UTraceContext> {
        public CREATOR(DefaultConstructorMarker defaultConstructorMarker) {
        }

        /* JADX WARN: Can't rename method to resolve collision */
        @Override // android.os.Parcelable.Creator
        public UTraceContext createFromParcel(Parcel parcel) {
            return new UTraceContext(parcel);
        }

        @Deprecated
        public final UTraceContext unserialize(String str) {
            return (UTraceContext) TraceUtil.unmarshall(this, TraceUtil.decodeToBytes(str));
        }

        /* JADX WARN: Can't rename method to resolve collision */
        @Override // android.os.Parcelable.Creator
        public UTraceContext[] newArray(int i) {
            return new UTraceContext[i];
        }

        @Deprecated
        public final UTraceContext unserialize(byte[] bArr) {
            return (UTraceContext) TraceUtil.unmarshall(this, bArr);
        }
    }

    public UTraceContext(String str, NodeID nodeID, NodeID nodeID2) {
        this();
        this.traceID = str;
        this.current = nodeID;
        this.parent = nodeID2;
    }

    public /* synthetic */ UTraceContext(String str, NodeID nodeID, NodeID nodeID2, int i, DefaultConstructorMarker defaultConstructorMarker) {
        this(str, nodeID, (i & 4) != 0 ? null : nodeID2);
    }

    public UTraceContext() {
        this.traceID = "";
        this.current = new NodeID();
    }
}
