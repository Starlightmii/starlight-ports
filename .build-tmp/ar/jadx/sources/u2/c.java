package u2;

import android.util.Base64;
import java.util.List;

/* JADX INFO: compiled from: r8-map-id-864ffde9d59ed35bc4209251fe295afa616a17016f049d977cb0fda4927bbf7c */
/* JADX INFO: loaded from: classes.dex */
public final class c {

    /* JADX INFO: renamed from: a, reason: collision with root package name */
    public final String f5826a;

    /* JADX INFO: renamed from: b, reason: collision with root package name */
    public final String f5827b;

    /* JADX INFO: renamed from: c, reason: collision with root package name */
    public final String f5828c;

    /* JADX INFO: renamed from: d, reason: collision with root package name */
    public final List f5829d;

    /* JADX INFO: renamed from: e, reason: collision with root package name */
    public final String f5830e;

    /* JADX INFO: renamed from: f, reason: collision with root package name */
    public final String f5831f;

    public c(String str, String str2, String str3, List list, String str4, String str5) {
        str.getClass();
        this.f5826a = str;
        str2.getClass();
        this.f5827b = str2;
        this.f5828c = str3;
        list.getClass();
        this.f5829d = list;
        this.f5830e = str4;
        this.f5831f = str5;
        StringBuilder sb = new StringBuilder();
        sb.append(str);
        sb.append("-");
        sb.append(str2);
        sb.append("-");
        sb.append(str3);
        sb.append("-");
        sb.append(str4);
        sb.append("-");
        sb.append(str5);
    }

    public final String toString() {
        StringBuilder sb = new StringBuilder();
        sb.append("FontRequest {mProviderAuthority: " + this.f5826a + ", mProviderPackage: " + this.f5827b + ", mQuery: " + this.f5828c + ", mSystemFont: " + this.f5830e + ", mVariationSettings: " + this.f5831f + ", mCertificates:");
        int i5 = 0;
        while (true) {
            List list = this.f5829d;
            if (i5 >= list.size()) {
                sb.append("}mCertificatesArray: 0");
                return sb.toString();
            }
            sb.append(" [");
            List list2 = (List) list.get(i5);
            for (int i6 = 0; i6 < list2.size(); i6++) {
                sb.append(" \"");
                sb.append(Base64.encodeToString((byte[]) list2.get(i6), 0));
                sb.append("\"");
            }
            sb.append(" ]");
            i5++;
        }
    }
}
