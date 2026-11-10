package i3;

import java.io.DataInputStream;
import java.io.DataOutputStream;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.util.Objects;

/* JADX INFO: compiled from: r8-map-id-864ffde9d59ed35bc4209251fe295afa616a17016f049d977cb0fda4927bbf7c */
/* JADX INFO: loaded from: classes.dex */
public final class i {

    /* JADX INFO: renamed from: a, reason: collision with root package name */
    public final int f2703a;

    /* JADX INFO: renamed from: b, reason: collision with root package name */
    public final int f2704b;

    /* JADX INFO: renamed from: c, reason: collision with root package name */
    public final long f2705c;

    /* JADX INFO: renamed from: d, reason: collision with root package name */
    public final long f2706d;

    public i(int i5, int i6, long j5, long j6) {
        this.f2703a = i5;
        this.f2704b = i6;
        this.f2705c = j5;
        this.f2706d = j6;
    }

    public static i a(File file) throws IOException {
        DataInputStream dataInputStream = new DataInputStream(new FileInputStream(file));
        try {
            i iVar = new i(dataInputStream.readInt(), dataInputStream.readInt(), dataInputStream.readLong(), dataInputStream.readLong());
            dataInputStream.close();
            return iVar;
        } catch (Throwable th) {
            try {
                dataInputStream.close();
                throw th;
            } catch (Throwable th2) {
                th.addSuppressed(th2);
                throw th;
            }
        }
    }

    public final void b(File file) throws IOException {
        file.delete();
        DataOutputStream dataOutputStream = new DataOutputStream(new FileOutputStream(file));
        try {
            dataOutputStream.writeInt(this.f2703a);
            dataOutputStream.writeInt(this.f2704b);
            dataOutputStream.writeLong(this.f2705c);
            dataOutputStream.writeLong(this.f2706d);
            dataOutputStream.close();
        } catch (Throwable th) {
            try {
                dataOutputStream.close();
            } catch (Throwable th2) {
                th.addSuppressed(th2);
            }
            throw th;
        }
    }

    public final boolean equals(Object obj) {
        if (this == obj) {
            return true;
        }
        if (obj != null && (obj instanceof i)) {
            i iVar = (i) obj;
            if (this.f2704b == iVar.f2704b && this.f2705c == iVar.f2705c && this.f2703a == iVar.f2703a && this.f2706d == iVar.f2706d) {
                return true;
            }
        }
        return false;
    }

    public final int hashCode() {
        return Objects.hash(Integer.valueOf(this.f2704b), Long.valueOf(this.f2705c), Integer.valueOf(this.f2703a), Long.valueOf(this.f2706d));
    }
}
