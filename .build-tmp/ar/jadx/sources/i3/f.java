package i3;

import android.content.Context;
import android.content.pm.ApplicationInfo;
import android.content.pm.PackageInfo;
import android.content.pm.PackageManager;
import android.content.res.AssetManager;
import android.os.Build;
import android.util.Log;
import androidx.lifecycle.k0;
import java.io.ByteArrayInputStream;
import java.io.ByteArrayOutputStream;
import java.io.DataInputStream;
import java.io.DataOutputStream;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.nio.channels.FileChannel;
import java.nio.channels.FileLock;
import java.nio.charset.Charset;
import java.nio.charset.StandardCharsets;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.BitSet;
import java.util.Iterator;
import java.util.Map;
import java.util.TreeMap;
import java.util.concurrent.Executor;
import java.util.zip.DataFormatException;
import java.util.zip.Deflater;
import java.util.zip.DeflaterOutputStream;
import java.util.zip.Inflater;

/* JADX INFO: compiled from: r8-map-id-864ffde9d59ed35bc4209251fe295afa616a17016f049d977cb0fda4927bbf7c */
/* JADX INFO: loaded from: classes.dex */
public abstract class f {

    /* JADX INFO: renamed from: a, reason: collision with root package name */
    public static final k0 f2691a = new k0(19);

    /* JADX INFO: renamed from: b, reason: collision with root package name */
    public static final byte[] f2692b = {112, 114, 111, 0};

    /* JADX INFO: renamed from: c, reason: collision with root package name */
    public static final byte[] f2693c = {112, 114, 109, 0};

    /* JADX INFO: renamed from: d, reason: collision with root package name */
    public static final byte[] f2694d = {48, 49, 53, 0};

    /* JADX INFO: renamed from: e, reason: collision with root package name */
    public static final byte[] f2695e = {48, 49, 48, 0};

    /* JADX INFO: renamed from: f, reason: collision with root package name */
    public static final byte[] f2696f = {48, 48, 57, 0};

    /* JADX INFO: renamed from: g, reason: collision with root package name */
    public static final byte[] f2697g = {48, 48, 53, 0};

    /* JADX INFO: renamed from: h, reason: collision with root package name */
    public static final byte[] f2698h = {48, 48, 49, 0};

    /* JADX INFO: renamed from: i, reason: collision with root package name */
    public static final byte[] f2699i = {48, 48, 49, 0};

    /* JADX INFO: renamed from: j, reason: collision with root package name */
    public static final byte[] f2700j = {48, 48, 50, 0};

    public static byte[] a(byte[] bArr) {
        Deflater deflater = new Deflater(1);
        ByteArrayOutputStream byteArrayOutputStream = new ByteArrayOutputStream();
        try {
            DeflaterOutputStream deflaterOutputStream = new DeflaterOutputStream(byteArrayOutputStream, deflater);
            try {
                deflaterOutputStream.write(bArr);
                deflaterOutputStream.close();
                deflater.end();
                return byteArrayOutputStream.toByteArray();
            } catch (Throwable th) {
                try {
                    deflaterOutputStream.close();
                } catch (Throwable th2) {
                    th.addSuppressed(th2);
                }
                throw th;
            }
        } catch (Throwable th3) {
            deflater.end();
            throw th3;
        }
    }

    public static byte[] b(c[] cVarArr, byte[] bArr) throws IOException {
        int i5 = 0;
        int length = 0;
        for (c cVar : cVarArr) {
            length += ((((cVar.f2688g * 2) + 7) & (-8)) / 8) + (cVar.f2686e * 2) + d(cVar.f2682a, cVar.f2683b, bArr).getBytes(StandardCharsets.UTF_8).length + 16 + cVar.f2687f;
        }
        ByteArrayOutputStream byteArrayOutputStream = new ByteArrayOutputStream(length);
        if (Arrays.equals(bArr, f2696f)) {
            int length2 = cVarArr.length;
            while (i5 < length2) {
                c cVar2 = cVarArr[i5];
                q(byteArrayOutputStream, cVar2, d(cVar2.f2682a, cVar2.f2683b, bArr));
                p(byteArrayOutputStream, cVar2);
                i5++;
            }
        } else {
            for (c cVar3 : cVarArr) {
                q(byteArrayOutputStream, cVar3, d(cVar3.f2682a, cVar3.f2683b, bArr));
            }
            int length3 = cVarArr.length;
            while (i5 < length3) {
                p(byteArrayOutputStream, cVarArr[i5]);
                i5++;
            }
        }
        if (byteArrayOutputStream.size() == length) {
            return byteArrayOutputStream.toByteArray();
        }
        throw new IllegalStateException("The bytes saved do not match expectation. actual=" + byteArrayOutputStream.size() + " expected=" + length);
    }

    public static boolean c(File file) {
        if (!file.isDirectory()) {
            file.delete();
            return true;
        }
        File[] fileArrListFiles = file.listFiles();
        if (fileArrListFiles == null) {
            return false;
        }
        boolean z2 = true;
        for (File file2 : fileArrListFiles) {
            z2 = c(file2) && z2;
        }
        return z2;
    }

    public static String d(String str, String str2, byte[] bArr) {
        byte[] bArr2 = f2698h;
        boolean zEquals = Arrays.equals(bArr, bArr2);
        byte[] bArr3 = f2697g;
        Object obj = (zEquals || Arrays.equals(bArr, bArr3)) ? ":" : "!";
        if (str.length() <= 0) {
            if ("!".equals(obj)) {
                return str2.replace(":", "!");
            }
            if (":".equals(obj)) {
                return str2.replace("!", ":");
            }
        } else {
            if (str2.equals("classes.dex")) {
                return str;
            }
            if (str2.contains("!") || str2.contains(":")) {
                if ("!".equals(obj)) {
                    return str2.replace(":", "!");
                }
                if (":".equals(obj)) {
                    return str2.replace("!", ":");
                }
            } else if (!str2.endsWith(".apk")) {
                StringBuilder sb = new StringBuilder();
                sb.append(str);
                sb.append((Arrays.equals(bArr, bArr2) || Arrays.equals(bArr, bArr3)) ? ":" : "!");
                sb.append(str2);
                return sb.toString();
            }
        }
        return str2;
    }

    public static void e(PackageInfo packageInfo, File file) {
        try {
            DataOutputStream dataOutputStream = new DataOutputStream(new FileOutputStream(new File(file, "profileinstaller_profileWrittenFor_lastUpdateTime.dat")));
            try {
                dataOutputStream.writeLong(packageInfo.lastUpdateTime);
                dataOutputStream.close();
            } catch (Throwable th) {
                try {
                    dataOutputStream.close();
                } catch (Throwable th2) {
                    th.addSuppressed(th2);
                }
                throw th;
            }
        } catch (IOException unused) {
        }
    }

    public static byte[] f(InputStream inputStream, int i5) throws IOException {
        byte[] bArr = new byte[i5];
        int i6 = 0;
        while (i6 < i5) {
            int i7 = inputStream.read(bArr, i6, i5 - i6);
            if (i7 < 0) {
                throw new IllegalStateException("Not enough bytes to read: " + i5);
            }
            i6 += i7;
        }
        return bArr;
    }

    public static int[] g(ByteArrayInputStream byteArrayInputStream, int i5) {
        int[] iArr = new int[i5];
        int iM = 0;
        for (int i6 = 0; i6 < i5; i6++) {
            iM += (int) m(byteArrayInputStream, 2);
            iArr[i6] = iM;
        }
        return iArr;
    }

    public static byte[] h(FileInputStream fileInputStream, int i5, int i6) {
        Inflater inflater = new Inflater();
        try {
            byte[] bArr = new byte[i6];
            byte[] bArr2 = new byte[2048];
            int i7 = 0;
            int iInflate = 0;
            while (!inflater.finished() && !inflater.needsDictionary() && i7 < i5) {
                int i8 = fileInputStream.read(bArr2);
                if (i8 < 0) {
                    throw new IllegalStateException("Invalid zip data. Stream ended after $totalBytesRead bytes. Expected " + i5 + " bytes");
                }
                inflater.setInput(bArr2, 0, i8);
                try {
                    iInflate += inflater.inflate(bArr, iInflate, i6 - iInflate);
                    i7 += i8;
                } catch (DataFormatException e5) {
                    throw new IllegalStateException(e5.getMessage());
                }
            }
            if (i7 == i5) {
                if (!inflater.finished()) {
                    throw new IllegalStateException("Inflater did not finish");
                }
                inflater.end();
                return bArr;
            }
            throw new IllegalStateException("Didn't read enough bytes during decompression. expected=" + i5 + " actual=" + i7);
        } catch (Throwable th) {
            inflater.end();
            throw th;
        }
    }

    public static c[] i(FileInputStream fileInputStream, byte[] bArr, byte[] bArr2, c[] cVarArr) throws IOException {
        byte[] bArr3 = f2699i;
        if (!Arrays.equals(bArr, bArr3)) {
            if (!Arrays.equals(bArr, f2700j)) {
                throw new IllegalStateException("Unsupported meta version");
            }
            int iM = (int) m(fileInputStream, 2);
            byte[] bArrH = h(fileInputStream, (int) m(fileInputStream, 4), (int) m(fileInputStream, 4));
            if (fileInputStream.read() > 0) {
                throw new IllegalStateException("Content found after the end of file");
            }
            ByteArrayInputStream byteArrayInputStream = new ByteArrayInputStream(bArrH);
            try {
                c[] cVarArrK = k(byteArrayInputStream, bArr2, iM, cVarArr);
                byteArrayInputStream.close();
                return cVarArrK;
            } catch (Throwable th) {
                try {
                    byteArrayInputStream.close();
                } catch (Throwable th2) {
                    th.addSuppressed(th2);
                }
                throw th;
            }
        }
        if (Arrays.equals(f2694d, bArr2)) {
            throw new IllegalStateException("Requires new Baseline Profile Metadata. Please rebuild the APK with Android Gradle Plugin 7.2 Canary 7 or higher");
        }
        if (!Arrays.equals(bArr, bArr3)) {
            throw new IllegalStateException("Unsupported meta version");
        }
        int iM2 = (int) m(fileInputStream, 1);
        byte[] bArrH2 = h(fileInputStream, (int) m(fileInputStream, 4), (int) m(fileInputStream, 4));
        if (fileInputStream.read() > 0) {
            throw new IllegalStateException("Content found after the end of file");
        }
        ByteArrayInputStream byteArrayInputStream2 = new ByteArrayInputStream(bArrH2);
        try {
            c[] cVarArrJ = j(byteArrayInputStream2, iM2, cVarArr);
            byteArrayInputStream2.close();
            return cVarArrJ;
        } catch (Throwable th3) {
            try {
                byteArrayInputStream2.close();
            } catch (Throwable th4) {
                th3.addSuppressed(th4);
            }
            throw th3;
        }
    }

    public static c[] j(ByteArrayInputStream byteArrayInputStream, int i5, c[] cVarArr) {
        if (byteArrayInputStream.available() == 0) {
            return new c[0];
        }
        if (i5 != cVarArr.length) {
            throw new IllegalStateException("Mismatched number of dex files found in metadata");
        }
        String[] strArr = new String[i5];
        int[] iArr = new int[i5];
        for (int i6 = 0; i6 < i5; i6++) {
            int iM = (int) m(byteArrayInputStream, 2);
            iArr[i6] = (int) m(byteArrayInputStream, 2);
            strArr[i6] = new String(f(byteArrayInputStream, iM), StandardCharsets.UTF_8);
        }
        for (int i7 = 0; i7 < i5; i7++) {
            c cVar = cVarArr[i7];
            if (!cVar.f2683b.equals(strArr[i7])) {
                throw new IllegalStateException("Order of dexfiles in metadata did not match baseline");
            }
            int i8 = iArr[i7];
            cVar.f2686e = i8;
            cVar.f2689h = g(byteArrayInputStream, i8);
        }
        return cVarArr;
    }

    public static c[] k(ByteArrayInputStream byteArrayInputStream, byte[] bArr, int i5, c[] cVarArr) throws IOException {
        if (byteArrayInputStream.available() == 0) {
            return new c[0];
        }
        if (i5 != cVarArr.length) {
            throw new IllegalStateException("Mismatched number of dex files found in metadata");
        }
        for (int i6 = 0; i6 < i5; i6++) {
            m(byteArrayInputStream, 2);
            String str = new String(f(byteArrayInputStream, (int) m(byteArrayInputStream, 2)), StandardCharsets.UTF_8);
            long jM = m(byteArrayInputStream, 4);
            int iM = (int) m(byteArrayInputStream, 2);
            c cVar = null;
            if (cVarArr.length > 0) {
                int iIndexOf = str.indexOf("!");
                if (iIndexOf < 0) {
                    iIndexOf = str.indexOf(":");
                }
                String strSubstring = iIndexOf > 0 ? str.substring(iIndexOf + 1) : str;
                for (int i7 = 0; i7 < cVarArr.length; i7++) {
                    if (cVarArr[i7].f2683b.equals(strSubstring)) {
                        cVar = cVarArr[i7];
                        break;
                    }
                }
            }
            if (cVar == null) {
                throw new IllegalStateException("Missing profile key: ".concat(str));
            }
            cVar.f2685d = jM;
            int[] iArrG = g(byteArrayInputStream, iM);
            if (Arrays.equals(bArr, f2698h)) {
                cVar.f2686e = iM;
                cVar.f2689h = iArrG;
            }
        }
        return cVarArr;
    }

    public static c[] l(FileInputStream fileInputStream, byte[] bArr, String str) throws IOException {
        if (!Arrays.equals(bArr, f2695e)) {
            throw new IllegalStateException("Unsupported version");
        }
        int iM = (int) m(fileInputStream, 1);
        byte[] bArrH = h(fileInputStream, (int) m(fileInputStream, 4), (int) m(fileInputStream, 4));
        if (fileInputStream.read() > 0) {
            throw new IllegalStateException("Content found after the end of file");
        }
        ByteArrayInputStream byteArrayInputStream = new ByteArrayInputStream(bArrH);
        try {
            c[] cVarArrN = n(byteArrayInputStream, str, iM);
            byteArrayInputStream.close();
            return cVarArrN;
        } catch (Throwable th) {
            try {
                byteArrayInputStream.close();
            } catch (Throwable th2) {
                th.addSuppressed(th2);
            }
            throw th;
        }
    }

    public static long m(InputStream inputStream, int i5) throws IOException {
        byte[] bArrF = f(inputStream, i5);
        long j5 = 0;
        for (int i6 = 0; i6 < i5; i6++) {
            j5 += ((long) (bArrF[i6] & 255)) << (i6 * 8);
        }
        return j5;
    }

    public static c[] n(ByteArrayInputStream byteArrayInputStream, String str, int i5) throws IOException {
        int i6 = 0;
        if (byteArrayInputStream.available() == 0) {
            return new c[0];
        }
        c[] cVarArr = new c[i5];
        for (int i7 = 0; i7 < i5; i7++) {
            int iM = (int) m(byteArrayInputStream, 2);
            int iM2 = (int) m(byteArrayInputStream, 2);
            cVarArr[i7] = new c(str, new String(f(byteArrayInputStream, iM), StandardCharsets.UTF_8), m(byteArrayInputStream, 4), iM2, (int) m(byteArrayInputStream, 4), (int) m(byteArrayInputStream, 4), new int[iM2], new TreeMap());
        }
        int i8 = 0;
        while (i8 < i5) {
            c cVar = cVarArr[i8];
            int iAvailable = byteArrayInputStream.available();
            int i9 = cVar.f2687f;
            int i10 = cVar.f2688g;
            TreeMap treeMap = cVar.f2690i;
            int i11 = iAvailable - i9;
            int iM3 = i6;
            while (byteArrayInputStream.available() > i11) {
                iM3 += (int) m(byteArrayInputStream, 2);
                treeMap.put(Integer.valueOf(iM3), 1);
                int iM4 = (int) m(byteArrayInputStream, 2);
                while (iM4 > 0) {
                    m(byteArrayInputStream, 2);
                    int iM5 = (int) m(byteArrayInputStream, 1);
                    if (iM5 != 6 && iM5 != 7) {
                        while (iM5 > 0) {
                            m(byteArrayInputStream, 1);
                            int i12 = i6;
                            int i13 = i8;
                            for (int iM6 = (int) m(byteArrayInputStream, 1); iM6 > 0; iM6--) {
                                m(byteArrayInputStream, 2);
                            }
                            iM5--;
                            i6 = i12;
                            i8 = i13;
                        }
                    }
                    iM4--;
                    i6 = i6;
                    i8 = i8;
                }
            }
            int i14 = i6;
            int i15 = i8;
            if (byteArrayInputStream.available() != i11) {
                throw new IllegalStateException("Read too much data during profile line parse");
            }
            cVar.f2689h = g(byteArrayInputStream, cVar.f2686e);
            BitSet bitSetValueOf = BitSet.valueOf(f(byteArrayInputStream, (((i10 * 2) + 7) & (-8)) / 8));
            for (int i16 = i14; i16 < i10; i16++) {
                int i17 = bitSetValueOf.get(i16) ? 2 : i14;
                if (bitSetValueOf.get(i16 + i10)) {
                    i17 |= 4;
                }
                if (i17 != 0) {
                    Integer numValueOf = (Integer) treeMap.get(Integer.valueOf(i16));
                    if (numValueOf == null) {
                        numValueOf = Integer.valueOf(i14);
                    }
                    treeMap.put(Integer.valueOf(i16), Integer.valueOf(i17 | numValueOf.intValue()));
                }
            }
            i8 = i15 + 1;
            i6 = i14;
        }
        return cVarArr;
    }

    public static boolean o(ByteArrayOutputStream byteArrayOutputStream, byte[] bArr, c[] cVarArr) throws IOException {
        long j5;
        ArrayList arrayList;
        int length;
        byte[] bArr2 = f2694d;
        int i5 = 0;
        if (!Arrays.equals(bArr, bArr2)) {
            byte[] bArr3 = f2695e;
            if (Arrays.equals(bArr, bArr3)) {
                byte[] bArrB = b(cVarArr, bArr3);
                u(byteArrayOutputStream, cVarArr.length, 1);
                u(byteArrayOutputStream, bArrB.length, 4);
                byte[] bArrA = a(bArrB);
                u(byteArrayOutputStream, bArrA.length, 4);
                byteArrayOutputStream.write(bArrA);
                return true;
            }
            byte[] bArr4 = f2697g;
            if (Arrays.equals(bArr, bArr4)) {
                u(byteArrayOutputStream, cVarArr.length, 1);
                for (c cVar : cVarArr) {
                    int size = cVar.f2690i.size() * 4;
                    String strD = d(cVar.f2682a, cVar.f2683b, bArr4);
                    Charset charset = StandardCharsets.UTF_8;
                    v(byteArrayOutputStream, strD.getBytes(charset).length);
                    v(byteArrayOutputStream, cVar.f2689h.length);
                    u(byteArrayOutputStream, size, 4);
                    u(byteArrayOutputStream, cVar.f2684c, 4);
                    byteArrayOutputStream.write(strD.getBytes(charset));
                    Iterator it = cVar.f2690i.keySet().iterator();
                    while (it.hasNext()) {
                        v(byteArrayOutputStream, ((Integer) it.next()).intValue());
                        v(byteArrayOutputStream, 0);
                    }
                    for (int i6 : cVar.f2689h) {
                        v(byteArrayOutputStream, i6);
                    }
                }
                return true;
            }
            byte[] bArr5 = f2696f;
            if (Arrays.equals(bArr, bArr5)) {
                byte[] bArrB2 = b(cVarArr, bArr5);
                u(byteArrayOutputStream, cVarArr.length, 1);
                u(byteArrayOutputStream, bArrB2.length, 4);
                byte[] bArrA2 = a(bArrB2);
                u(byteArrayOutputStream, bArrA2.length, 4);
                byteArrayOutputStream.write(bArrA2);
                return true;
            }
            byte[] bArr6 = f2698h;
            if (!Arrays.equals(bArr, bArr6)) {
                return false;
            }
            v(byteArrayOutputStream, cVarArr.length);
            for (c cVar2 : cVarArr) {
                String str = cVar2.f2682a;
                TreeMap treeMap = cVar2.f2690i;
                String strD2 = d(str, cVar2.f2683b, bArr6);
                Charset charset2 = StandardCharsets.UTF_8;
                v(byteArrayOutputStream, strD2.getBytes(charset2).length);
                v(byteArrayOutputStream, treeMap.size());
                v(byteArrayOutputStream, cVar2.f2689h.length);
                u(byteArrayOutputStream, cVar2.f2684c, 4);
                byteArrayOutputStream.write(strD2.getBytes(charset2));
                Iterator it2 = treeMap.keySet().iterator();
                while (it2.hasNext()) {
                    v(byteArrayOutputStream, ((Integer) it2.next()).intValue());
                }
                for (int i7 : cVar2.f2689h) {
                    v(byteArrayOutputStream, i7);
                }
            }
            return true;
        }
        ArrayList arrayList2 = new ArrayList(3);
        ArrayList arrayList3 = new ArrayList(3);
        ByteArrayOutputStream byteArrayOutputStream2 = new ByteArrayOutputStream();
        try {
            v(byteArrayOutputStream2, cVarArr.length);
            int i8 = 2;
            int i9 = 2;
            for (c cVar3 : cVarArr) {
                u(byteArrayOutputStream2, cVar3.f2684c, 4);
                u(byteArrayOutputStream2, cVar3.f2685d, 4);
                u(byteArrayOutputStream2, cVar3.f2688g, 4);
                String strD3 = d(cVar3.f2682a, cVar3.f2683b, bArr2);
                Charset charset3 = StandardCharsets.UTF_8;
                int length2 = strD3.getBytes(charset3).length;
                v(byteArrayOutputStream2, length2);
                i9 = i9 + 14 + length2;
                byteArrayOutputStream2.write(strD3.getBytes(charset3));
            }
            byte[] byteArray = byteArrayOutputStream2.toByteArray();
            if (i9 != byteArray.length) {
                throw new IllegalStateException("Expected size " + i9 + ", does not match actual size " + byteArray.length);
            }
            k kVar = new k(1, byteArray, false);
            byteArrayOutputStream2.close();
            arrayList2.add(kVar);
            ByteArrayOutputStream byteArrayOutputStream3 = new ByteArrayOutputStream();
            int i10 = 0;
            int i11 = 0;
            while (i10 < cVarArr.length) {
                try {
                    c cVar4 = cVarArr[i10];
                    v(byteArrayOutputStream3, i10);
                    v(byteArrayOutputStream3, cVar4.f2686e);
                    i11 = i11 + 4 + (cVar4.f2686e * i8);
                    int[] iArr = cVar4.f2689h;
                    int length3 = iArr.length;
                    int i12 = i5;
                    int i13 = i8;
                    int i14 = i12;
                    while (i14 < length3) {
                        int i15 = iArr[i14];
                        v(byteArrayOutputStream3, i15 - i12);
                        i14++;
                        i12 = i15;
                    }
                    i10++;
                    i8 = i13;
                    i5 = 0;
                } catch (Throwable th) {
                    try {
                        byteArrayOutputStream3.close();
                        throw th;
                    } catch (Throwable th2) {
                        th.addSuppressed(th2);
                        throw th;
                    }
                }
            }
            byte[] byteArray2 = byteArrayOutputStream3.toByteArray();
            if (i11 != byteArray2.length) {
                throw new IllegalStateException("Expected size " + i11 + ", does not match actual size " + byteArray2.length);
            }
            k kVar2 = new k(3, byteArray2, true);
            byteArrayOutputStream3.close();
            arrayList2.add(kVar2);
            ByteArrayOutputStream byteArrayOutputStream4 = new ByteArrayOutputStream();
            int i16 = 0;
            int i17 = 0;
            while (i16 < cVarArr.length) {
                try {
                    c cVar5 = cVarArr[i16];
                    Iterator it3 = cVar5.f2690i.entrySet().iterator();
                    int iIntValue = 0;
                    while (it3.hasNext()) {
                        iIntValue |= ((Integer) ((Map.Entry) it3.next()).getValue()).intValue();
                    }
                    ByteArrayOutputStream byteArrayOutputStream5 = new ByteArrayOutputStream();
                    try {
                        r(byteArrayOutputStream5, iIntValue, cVar5);
                        byte[] byteArray3 = byteArrayOutputStream5.toByteArray();
                        byteArrayOutputStream5.close();
                        ByteArrayOutputStream byteArrayOutputStream6 = new ByteArrayOutputStream();
                        try {
                            s(byteArrayOutputStream6, cVar5);
                            byte[] byteArray4 = byteArrayOutputStream6.toByteArray();
                            byteArrayOutputStream6.close();
                            v(byteArrayOutputStream4, i16);
                            int length4 = byteArray3.length + 2 + byteArray4.length;
                            int i18 = i17 + 6;
                            ArrayList arrayList4 = arrayList3;
                            u(byteArrayOutputStream4, length4, 4);
                            v(byteArrayOutputStream4, iIntValue);
                            byteArrayOutputStream4.write(byteArray3);
                            byteArrayOutputStream4.write(byteArray4);
                            i17 = i18 + length4;
                            i16++;
                            arrayList3 = arrayList4;
                        } catch (Throwable th3) {
                            try {
                                byteArrayOutputStream6.close();
                                throw th3;
                            } catch (Throwable th4) {
                                th3.addSuppressed(th4);
                                throw th3;
                            }
                        }
                    } catch (Throwable th5) {
                        try {
                            byteArrayOutputStream5.close();
                            throw th5;
                        } catch (Throwable th6) {
                            th5.addSuppressed(th6);
                            throw th5;
                        }
                    }
                } catch (Throwable th7) {
                    try {
                        byteArrayOutputStream4.close();
                        throw th7;
                    } catch (Throwable th8) {
                        th7.addSuppressed(th8);
                        throw th7;
                    }
                }
            }
            ArrayList arrayList5 = arrayList3;
            byte[] byteArray5 = byteArrayOutputStream4.toByteArray();
            if (i17 != byteArray5.length) {
                throw new IllegalStateException("Expected size " + i17 + ", does not match actual size " + byteArray5.length);
            }
            k kVar3 = new k(4, byteArray5, true);
            byteArrayOutputStream4.close();
            arrayList2.add(kVar3);
            long j6 = 4;
            long size2 = j6 + j6 + 4 + ((long) (arrayList2.size() * 16));
            u(byteArrayOutputStream, arrayList2.size(), 4);
            int i19 = 0;
            while (i19 < arrayList2.size()) {
                k kVar4 = (k) arrayList2.get(i19);
                int i20 = kVar4.f2710a;
                byte[] bArr7 = kVar4.f2711b;
                if (i20 == 1) {
                    j5 = 0;
                } else if (i20 == 2) {
                    j5 = 1;
                } else if (i20 == 3) {
                    j5 = 2;
                } else if (i20 == 4) {
                    j5 = 3;
                } else {
                    if (i20 != 5) {
                        throw null;
                    }
                    j5 = 4;
                }
                u(byteArrayOutputStream, j5, 4);
                u(byteArrayOutputStream, size2, 4);
                if (kVar4.f2712c) {
                    long length5 = bArr7.length;
                    byte[] bArrA3 = a(bArr7);
                    arrayList = arrayList5;
                    arrayList.add(bArrA3);
                    u(byteArrayOutputStream, bArrA3.length, 4);
                    u(byteArrayOutputStream, length5, 4);
                    length = bArrA3.length;
                } else {
                    arrayList = arrayList5;
                    arrayList.add(bArr7);
                    u(byteArrayOutputStream, bArr7.length, 4);
                    u(byteArrayOutputStream, 0L, 4);
                    length = bArr7.length;
                }
                size2 += (long) length;
                i19++;
                arrayList5 = arrayList;
            }
            ArrayList arrayList6 = arrayList5;
            for (int i21 = 0; i21 < arrayList6.size(); i21++) {
                byteArrayOutputStream.write((byte[]) arrayList6.get(i21));
            }
            return true;
        } catch (Throwable th9) {
            try {
                byteArrayOutputStream2.close();
                throw th9;
            } catch (Throwable th10) {
                th9.addSuppressed(th10);
                throw th9;
            }
        }
    }

    public static void p(ByteArrayOutputStream byteArrayOutputStream, c cVar) throws IOException {
        s(byteArrayOutputStream, cVar);
        int i5 = cVar.f2688g;
        int[] iArr = cVar.f2689h;
        int length = iArr.length;
        int i6 = 0;
        int i7 = 0;
        while (i6 < length) {
            int i8 = iArr[i6];
            v(byteArrayOutputStream, i8 - i7);
            i6++;
            i7 = i8;
        }
        byte[] bArr = new byte[(((i5 * 2) + 7) & (-8)) / 8];
        for (Map.Entry entry : cVar.f2690i.entrySet()) {
            int iIntValue = ((Integer) entry.getKey()).intValue();
            int iIntValue2 = ((Integer) entry.getValue()).intValue();
            if ((iIntValue2 & 2) != 0) {
                int i9 = iIntValue / 8;
                bArr[i9] = (byte) (bArr[i9] | (1 << (iIntValue % 8)));
            }
            if ((iIntValue2 & 4) != 0) {
                int i10 = iIntValue + i5;
                int i11 = i10 / 8;
                bArr[i11] = (byte) ((1 << (i10 % 8)) | bArr[i11]);
            }
        }
        byteArrayOutputStream.write(bArr);
    }

    public static void q(ByteArrayOutputStream byteArrayOutputStream, c cVar, String str) throws IOException {
        Charset charset = StandardCharsets.UTF_8;
        v(byteArrayOutputStream, str.getBytes(charset).length);
        v(byteArrayOutputStream, cVar.f2686e);
        u(byteArrayOutputStream, cVar.f2687f, 4);
        u(byteArrayOutputStream, cVar.f2684c, 4);
        u(byteArrayOutputStream, cVar.f2688g, 4);
        byteArrayOutputStream.write(str.getBytes(charset));
    }

    public static void r(ByteArrayOutputStream byteArrayOutputStream, int i5, c cVar) throws IOException {
        int i6 = cVar.f2688g;
        byte[] bArr = new byte[(((Integer.bitCount(i5 & (-2)) * i6) + 7) & (-8)) / 8];
        for (Map.Entry entry : cVar.f2690i.entrySet()) {
            int iIntValue = ((Integer) entry.getKey()).intValue();
            int iIntValue2 = ((Integer) entry.getValue()).intValue();
            int i7 = 0;
            for (int i8 = 1; i8 <= 4; i8 <<= 1) {
                if (i8 != 1 && (i8 & i5) != 0) {
                    if ((i8 & iIntValue2) == i8) {
                        int i9 = (i7 * i6) + iIntValue;
                        int i10 = i9 / 8;
                        bArr[i10] = (byte) ((1 << (i9 % 8)) | bArr[i10]);
                    }
                    i7++;
                }
            }
        }
        byteArrayOutputStream.write(bArr);
    }

    public static void s(ByteArrayOutputStream byteArrayOutputStream, c cVar) throws IOException {
        int i5 = 0;
        for (Map.Entry entry : cVar.f2690i.entrySet()) {
            int iIntValue = ((Integer) entry.getKey()).intValue();
            if ((((Integer) entry.getValue()).intValue() & 1) != 0) {
                v(byteArrayOutputStream, iIntValue - i5);
                v(byteArrayOutputStream, 0);
                i5 = iIntValue;
            }
        }
    }

    /* JADX WARN: Code duplicated, block: B:101:0x0182 A[Catch: all -> 0x017f, TRY_ENTER, TryCatch #18 {all -> 0x017f, blocks: (B:88:0x015e, B:90:0x016a, B:101:0x0182, B:102:0x0187), top: B:269:0x015e }] */
    /* JADX WARN: Code duplicated, block: B:108:0x0191 A[DONT_INVERT] */
    /* JADX WARN: Code duplicated, block: B:109:0x0193 A[Catch: IllegalStateException -> 0x0179, IOException -> 0x017b, FileNotFoundException -> 0x017d, TRY_LEAVE, TryCatch #34 {FileNotFoundException -> 0x017d, IOException -> 0x017b, IllegalStateException -> 0x0179, blocks: (B:86:0x0156, B:91:0x0174, B:109:0x0193, B:107:0x0190, B:106:0x018d), top: B:293:0x0156 }] */
    /* JADX WARN: Code duplicated, block: B:116:0x01a9  */
    /* JADX WARN: Code duplicated, block: B:126:0x01cc A[Catch: all -> 0x01da, TRY_LEAVE, TryCatch #34 {all -> 0x01da, blocks: (B:124:0x01c0, B:126:0x01cc, B:135:0x01dd), top: B:283:0x01c0 }] */
    /* JADX WARN: Code duplicated, block: B:135:0x01dd A[Catch: all -> 0x01da, TRY_ENTER, TRY_LEAVE, TryCatch #34 {all -> 0x01da, blocks: (B:124:0x01c0, B:126:0x01cc, B:135:0x01dd), top: B:283:0x01c0 }] */
    /* JADX WARN: Code duplicated, block: B:146:0x01fa  */
    /* JADX WARN: Code duplicated, block: B:150:0x0204  */
    /* JADX WARN: Code duplicated, block: B:151:0x0208  */
    /* JADX WARN: Code duplicated, block: B:160:0x0228 A[Catch: all -> 0x0266, TryCatch #5 {all -> 0x0266, blocks: (B:158:0x0222, B:160:0x0228, B:161:0x022c, B:163:0x0232), top: B:253:0x0222 }] */
    /* JADX WARN: Code duplicated, block: B:163:0x0232 A[Catch: all -> 0x0266, TRY_LEAVE, TryCatch #5 {all -> 0x0266, blocks: (B:158:0x0222, B:160:0x0228, B:161:0x022c, B:163:0x0232), top: B:253:0x0222 }] */
    /* JADX WARN: Code duplicated, block: B:229:0x02b7  */
    /* JADX WARN: Code duplicated, block: B:233:0x02c1  */
    /* JADX WARN: Code duplicated, block: B:240:0x02d1  */
    /* JADX WARN: Code duplicated, block: B:253:0x0222 A[EXC_TOP_SPLITTER, SYNTHETIC] */
    /* JADX WARN: Code duplicated, block: B:265:0x0105 A[EXC_TOP_SPLITTER, SYNTHETIC] */
    /* JADX WARN: Code duplicated, block: B:269:0x015e A[EXC_TOP_SPLITTER, SYNTHETIC] */
    /* JADX WARN: Code duplicated, block: B:288:0x020c A[EXC_TOP_SPLITTER, SYNTHETIC] */
    /* JADX WARN: Code duplicated, block: B:292:0x01bb A[EXC_TOP_SPLITTER, SYNTHETIC] */
    /* JADX WARN: Code duplicated, block: B:294:0x0237 A[EDGE_INSN: B:294:0x0237->B:165:0x0237 BREAK  A[LOOP:0: B:161:0x022c->B:295:?], SYNTHETIC] */
    /* JADX WARN: Code duplicated, block: B:37:0x00db  */
    /* JADX WARN: Code duplicated, block: B:55:0x010f A[Catch: all -> 0x0122, IllegalStateException -> 0x0125, IOException -> 0x0127, TRY_LEAVE, TryCatch #14 {IllegalStateException -> 0x0125, blocks: (B:53:0x0105, B:55:0x010f, B:66:0x0129, B:67:0x012e), top: B:265:0x0105, outer: #1 }] */
    /* JADX WARN: Code duplicated, block: B:66:0x0129 A[Catch: all -> 0x0122, IllegalStateException -> 0x0125, IOException -> 0x0127, TRY_ENTER, TryCatch #14 {IllegalStateException -> 0x0125, blocks: (B:53:0x0105, B:55:0x010f, B:66:0x0129, B:67:0x012e), top: B:265:0x0105, outer: #1 }] */
    /* JADX WARN: Code duplicated, block: B:90:0x016a A[Catch: all -> 0x017f, TRY_LEAVE, TryCatch #18 {all -> 0x017f, blocks: (B:88:0x015e, B:90:0x016a, B:101:0x0182, B:102:0x0187), top: B:269:0x015e }] */
    /* JADX WARN: Multi-variable type inference failed */
    /* JADX WARN: Type inference failed for: r7v10 */
    /* JADX WARN: Type inference failed for: r7v11 */
    /* JADX WARN: Type inference failed for: r7v12 */
    /* JADX WARN: Type inference failed for: r7v18 */
    /* JADX WARN: Type inference failed for: r7v20 */
    /* JADX WARN: Type inference failed for: r7v21 */
    /* JADX WARN: Type inference failed for: r7v23 */
    /* JADX WARN: Type inference failed for: r7v25, types: [int] */
    /* JADX WARN: Type inference failed for: r7v26 */
    /* JADX WARN: Type inference failed for: r7v27 */
    /* JADX WARN: Type inference failed for: r7v32 */
    /* JADX WARN: Type inference failed for: r7v33 */
    /* JADX WARN: Type inference failed for: r7v39 */
    /* JADX WARN: Type inference failed for: r7v4 */
    /* JADX WARN: Type inference failed for: r7v40 */
    /* JADX WARN: Type inference failed for: r7v41 */
    /* JADX WARN: Type inference failed for: r7v42 */
    /* JADX WARN: Type inference failed for: r7v43 */
    /* JADX WARN: Type inference failed for: r7v44 */
    /* JADX WARN: Type inference failed for: r7v45 */
    /* JADX WARN: Type inference failed for: r7v46 */
    /* JADX WARN: Type inference failed for: r7v47 */
    /* JADX WARN: Type inference failed for: r7v48 */
    /* JADX WARN: Type inference failed for: r7v49 */
    /* JADX WARN: Type inference failed for: r7v5, types: [java.io.FileInputStream, java.io.InputStream] */
    /* JADX WARN: Type inference failed for: r7v50 */
    /* JADX WARN: Type inference failed for: r7v51 */
    /* JADX WARN: Type inference failed for: r7v52 */
    /* JADX WARN: Type inference failed for: r7v53 */
    /* JADX WARN: Type inference failed for: r7v54 */
    /* JADX WARN: Type inference failed for: r7v55 */
    /* JADX WARN: Type inference failed for: r7v56 */
    /* JADX WARN: Type inference failed for: r7v57 */
    /* JADX WARN: Type inference failed for: r7v6 */
    /* JADX WARN: Type inference failed for: r7v7 */
    /* JADX WARN: Type inference failed for: r7v8 */
    /* JADX WARN: Type inference failed for: r7v9 */
    /* JADX WARN: Type inference failed for: r9v13 */
    /* JADX WARN: Type inference failed for: r9v14, types: [boolean] */
    /* JADX WARN: Type inference failed for: r9v15 */
    public static void t(Context context, Executor executor, e eVar, boolean z2) {
        boolean z3;
        ?? A;
        byte[] bArr;
        c[] cVarArrL;
        c[] cVarArr;
        e eVar2;
        c[] cVarArr2;
        byte[] bArr2;
        ?? r7;
        byte[] bArr3;
        ?? r8;
        boolean z4;
        ByteArrayInputStream byteArrayInputStream;
        Throwable th;
        FileOutputStream fileOutputStream;
        Throwable th2;
        FileChannel channel;
        FileLock fileLockTryLock;
        byte[] bArr4;
        int i5;
        ?? r9;
        boolean z5;
        boolean z6;
        ?? r10;
        ByteArrayOutputStream byteArrayOutputStream;
        b bVar;
        ?? r11;
        String str;
        FileInputStream fileInputStreamA;
        ?? r12;
        ?? r13;
        boolean z7;
        Context applicationContext = context.getApplicationContext();
        String packageName = applicationContext.getPackageName();
        ApplicationInfo applicationInfo = applicationContext.getApplicationInfo();
        AssetManager assets = applicationContext.getAssets();
        String name = new File(applicationInfo.sourceDir).getName();
        try {
            PackageInfo packageInfo = context.getPackageManager().getPackageInfo(packageName, 0);
            File filesDir = context.getFilesDir();
            if (!z2) {
                File file = new File(filesDir, "profileinstaller_profileWrittenFor_lastUpdateTime.dat");
                if (file.exists()) {
                    try {
                        DataInputStream dataInputStream = new DataInputStream(new FileInputStream(file));
                        try {
                            long j5 = dataInputStream.readLong();
                            dataInputStream.close();
                            z7 = j5 == packageInfo.lastUpdateTime;
                            if (z7) {
                                eVar.i(2, null);
                            }
                        } catch (Throwable th3) {
                            try {
                                dataInputStream.close();
                                throw th3;
                            } catch (Throwable th4) {
                                th3.addSuppressed(th4);
                                throw th3;
                            }
                        }
                    } catch (IOException unused) {
                        z7 = false;
                    }
                } else {
                    z7 = false;
                }
                if (z7) {
                    Log.d("ProfileInstaller", "Skipping profile installation for " + context.getPackageName());
                    j.c(context, false);
                    return;
                }
            }
            Log.d("ProfileInstaller", "Installing profile for " + context.getPackageName());
            File file2 = new File(new File("/data/misc/profiles/cur/0", packageName), "primary.prof");
            b bVar2 = new b(assets, executor, eVar, name, file2);
            byte[] bArr5 = bVar2.f2676c;
            if (bArr5 != null) {
                if (!file2.exists()) {
                    try {
                        if (file2.createNewFile()) {
                            bVar2.f2679f = true;
                            A = bVar2.a(assets, "dexopt/baseline.prof");
                            bArr = f2692b;
                            if (A != 0) {
                                if (Arrays.equals(bArr, f(A, 4))) {
                                    throw new IllegalStateException("Invalid magic");
                                }
                                cVarArrL = l(A, f(A, 4), bVar2.f2678e);
                                A.close();
                                bVar2.f2680g = cVarArrL;
                            }
                            cVarArr = bVar2.f2680g;
                            if (cVarArr != null) {
                                str = "dexopt/baseline.profm";
                                fileInputStreamA = bVar2.a(assets, "dexopt/baseline.profm");
                                r11 = str;
                                if (fileInputStreamA == null) {
                                    if (fileInputStreamA != null) {
                                        fileInputStreamA.close();
                                        r11 = str;
                                    }
                                    bVar = null;
                                    A = r11;
                                } else {
                                    if (Arrays.equals(f2693c, f(fileInputStreamA, 4))) {
                                        throw new IllegalStateException("Invalid magic");
                                    }
                                    byte[] bArrF = f(fileInputStreamA, 4);
                                    bVar2.f2680g = i(fileInputStreamA, bArrF, bArr5, cVarArr);
                                    fileInputStreamA.close();
                                    bVar = bVar2;
                                    A = bArrF;
                                }
                                if (bVar != null) {
                                    bVar2 = bVar;
                                }
                            }
                            eVar2 = bVar2.f2675b;
                            cVarArr2 = bVar2.f2680g;
                            bArr2 = bVar2.f2676c;
                            r7 = A;
                            r7 = A;
                            if (cVarArr2 != null) {
                                z6 = bVar2.f2679f;
                                if (z6) {
                                    throw new IllegalStateException("This device doesn't support aot. Did you call deviceSupportsAotProfile()?");
                                }
                                byteArrayOutputStream = new ByteArrayOutputStream();
                                byteArrayOutputStream.write(bArr);
                                byteArrayOutputStream.write(bArr2);
                                if (o(byteArrayOutputStream, bArr2, cVarArr2)) {
                                    bVar2.f2681h = byteArrayOutputStream.toByteArray();
                                    byteArrayOutputStream.close();
                                    r10 = byteArrayOutputStream;
                                    bVar2.f2680g = null;
                                    r7 = r10;
                                } else {
                                    eVar2.i(5, null);
                                    bVar2.f2680g = null;
                                    byteArrayOutputStream.close();
                                    r7 = byteArrayOutputStream;
                                }
                            }
                            bArr3 = bVar2.f2681h;
                            if (bArr3 != null) {
                                if (bVar2.f2679f) {
                                    throw new IllegalStateException("This device doesn't support aot. Did you call deviceSupportsAotProfile()?");
                                }
                                byteArrayInputStream = new ByteArrayInputStream(bArr3);
                                fileOutputStream = new FileOutputStream(bVar2.f2677d);
                                channel = fileOutputStream.getChannel();
                                fileLockTryLock = channel.tryLock();
                                if (fileLockTryLock != null) {
                                    if (fileLockTryLock.isValid()) {
                                        bArr4 = new byte[512];
                                        while (true) {
                                            i5 = byteArrayInputStream.read(bArr4);
                                            if (i5 > 0) {
                                                break;
                                                break;
                                            }
                                            fileOutputStream.write(bArr4, 0, i5);
                                        }
                                        r9 = 1;
                                        bVar2.b(1, null);
                                        fileLockTryLock.close();
                                        channel.close();
                                        fileOutputStream.close();
                                        byteArrayInputStream.close();
                                        bVar2.f2681h = null;
                                        bVar2.f2680g = null;
                                        z4 = true;
                                    }
                                }
                                throw new IOException("Unable to acquire a lock on the underlying file channel.");
                            }
                            z4 = false;
                            r9 = 1;
                            if (z4) {
                                e(packageInfo, filesDir);
                            }
                            z5 = z4;
                            r12 = r9;
                        } else {
                            bVar2.b(4, null);
                        }
                    } catch (IOException unused2) {
                        z3 = true;
                        bVar2.b(4, null);
                    }
                } else if (file2.canWrite()) {
                    bVar2.f2679f = true;
                    try {
                        A = bVar2.a(assets, "dexopt/baseline.prof");
                    } catch (FileNotFoundException e5) {
                        eVar.i(6, e5);
                        A = 0;
                    } catch (IOException e6) {
                        eVar.i(7, e6);
                        A = 0;
                    }
                    bArr = f2692b;
                    try {
                        if (A != 0) {
                            try {
                                try {
                                    if (Arrays.equals(bArr, f(A, 4))) {
                                        throw new IllegalStateException("Invalid magic");
                                    }
                                    cVarArrL = l(A, f(A, 4), bVar2.f2678e);
                                    try {
                                        A.close();
                                    } catch (IOException e7) {
                                        eVar.i(7, e7);
                                    }
                                    bVar2.f2680g = cVarArrL;
                                } catch (IllegalStateException e8) {
                                    eVar.i(8, e8);
                                    try {
                                        A.close();
                                    } catch (IOException e9) {
                                        eVar.i(7, e9);
                                    }
                                    cVarArrL = null;
                                }
                            } catch (IOException e10) {
                                eVar.i(7, e10);
                                A.close();
                                cVarArrL = null;
                            }
                        }
                        cVarArr = bVar2.f2680g;
                        if (cVarArr != null && (A = Build.VERSION.SDK_INT) >= 31) {
                            try {
                                str = "dexopt/baseline.profm";
                                fileInputStreamA = bVar2.a(assets, "dexopt/baseline.profm");
                                r11 = str;
                                if (fileInputStreamA == null) {
                                    try {
                                        if (Arrays.equals(f2693c, f(fileInputStreamA, 4))) {
                                            throw new IllegalStateException("Invalid magic");
                                        }
                                        byte[] bArrF2 = f(fileInputStreamA, 4);
                                        bVar2.f2680g = i(fileInputStreamA, bArrF2, bArr5, cVarArr);
                                        fileInputStreamA.close();
                                        bVar = bVar2;
                                        A = bArrF2;
                                    } catch (Throwable th5) {
                                        try {
                                            fileInputStreamA.close();
                                            throw th5;
                                        } catch (Throwable th6) {
                                            th5.addSuppressed(th6);
                                            throw th5;
                                        }
                                    }
                                } else {
                                    if (fileInputStreamA != null) {
                                        fileInputStreamA.close();
                                        r11 = str;
                                    }
                                    bVar = null;
                                    A = r11;
                                }
                            } catch (FileNotFoundException e11) {
                                eVar.i(9, e11);
                                r11 = A;
                            } catch (IOException e12) {
                                eVar.i(7, e12);
                                r11 = A;
                            } catch (IllegalStateException e13) {
                                bVar2.f2680g = null;
                                eVar.i(8, e13);
                                r11 = A;
                            }
                            if (bVar != null) {
                                bVar2 = bVar;
                            }
                        }
                        eVar2 = bVar2.f2675b;
                        cVarArr2 = bVar2.f2680g;
                        bArr2 = bVar2.f2676c;
                        r7 = A;
                        r7 = A;
                        if (cVarArr2 != null && bArr2 != null) {
                            z6 = bVar2.f2679f;
                            if (z6) {
                                throw new IllegalStateException("This device doesn't support aot. Did you call deviceSupportsAotProfile()?");
                            }
                            try {
                                byteArrayOutputStream = new ByteArrayOutputStream();
                                try {
                                    byteArrayOutputStream.write(bArr);
                                    byteArrayOutputStream.write(bArr2);
                                    if (o(byteArrayOutputStream, bArr2, cVarArr2)) {
                                        eVar2.i(5, null);
                                        bVar2.f2680g = null;
                                        byteArrayOutputStream.close();
                                        r7 = byteArrayOutputStream;
                                    } else {
                                        bVar2.f2681h = byteArrayOutputStream.toByteArray();
                                        byteArrayOutputStream.close();
                                        r10 = byteArrayOutputStream;
                                        bVar2.f2680g = null;
                                        r7 = r10;
                                    }
                                } catch (Throwable th7) {
                                    try {
                                        byteArrayOutputStream.close();
                                        throw th7;
                                    } catch (Throwable th8) {
                                        th7.addSuppressed(th8);
                                        throw th7;
                                    }
                                }
                            } catch (IOException e14) {
                                eVar2.i(7, e14);
                                r10 = z6;
                            } catch (IllegalStateException e15) {
                                eVar2.i(8, e15);
                                r10 = z6;
                            }
                        }
                        bArr3 = bVar2.f2681h;
                        if (bArr3 != null) {
                            z4 = false;
                            r9 = 1;
                        } else {
                            try {
                                if (bVar2.f2679f) {
                                    throw new IllegalStateException("This device doesn't support aot. Did you call deviceSupportsAotProfile()?");
                                }
                                try {
                                    try {
                                        byteArrayInputStream = new ByteArrayInputStream(bArr3);
                                        try {
                                            try {
                                                fileOutputStream = new FileOutputStream(bVar2.f2677d);
                                                try {
                                                    try {
                                                        channel = fileOutputStream.getChannel();
                                                        try {
                                                            fileLockTryLock = channel.tryLock();
                                                            try {
                                                                try {
                                                                    if (fileLockTryLock != null) {
                                                                        try {
                                                                            if (fileLockTryLock.isValid()) {
                                                                                bArr4 = new byte[512];
                                                                                while (true) {
                                                                                    i5 = byteArrayInputStream.read(bArr4);
                                                                                    if (i5 > 0) {
                                                                                        break;
                                                                                    } else {
                                                                                        fileOutputStream.write(bArr4, 0, i5);
                                                                                    }
                                                                                }
                                                                                r9 = 1;
                                                                                bVar2.b(1, null);
                                                                                fileLockTryLock.close();
                                                                                channel.close();
                                                                                fileOutputStream.close();
                                                                                byteArrayInputStream.close();
                                                                                bVar2.f2681h = null;
                                                                                bVar2.f2680g = null;
                                                                                z4 = true;
                                                                            }
                                                                        } catch (Throwable th9) {
                                                                            th = th9;
                                                                            Throwable th10 = th;
                                                                            if (fileLockTryLock == null) {
                                                                                throw th10;
                                                                            }
                                                                            try {
                                                                                fileLockTryLock.close();
                                                                                throw th10;
                                                                            } catch (Throwable th11) {
                                                                                th10.addSuppressed(th11);
                                                                                throw th10;
                                                                            }
                                                                        }
                                                                    }
                                                                    throw new IOException("Unable to acquire a lock on the underlying file channel.");
                                                                } catch (Throwable th12) {
                                                                    th = th12;
                                                                }
                                                            } catch (Throwable th13) {
                                                                th = th13;
                                                                Throwable th14 = th;
                                                                if (channel == null) {
                                                                    throw th14;
                                                                }
                                                                try {
                                                                    channel.close();
                                                                    throw th14;
                                                                } catch (Throwable th15) {
                                                                    th14.addSuppressed(th15);
                                                                    throw th14;
                                                                }
                                                            }
                                                        } catch (Throwable th16) {
                                                            th = th16;
                                                        }
                                                    } catch (Throwable th17) {
                                                        th = th17;
                                                        th2 = th;
                                                        try {
                                                            fileOutputStream.close();
                                                            throw th2;
                                                        } catch (Throwable th18) {
                                                            th2.addSuppressed(th18);
                                                            throw th2;
                                                        }
                                                    }
                                                } catch (Throwable th19) {
                                                    th = th19;
                                                    th2 = th;
                                                    fileOutputStream.close();
                                                    throw th2;
                                                }
                                            } catch (Throwable th20) {
                                                th = th20;
                                                th = th;
                                                try {
                                                    byteArrayInputStream.close();
                                                    throw th;
                                                } catch (Throwable th21) {
                                                    th.addSuppressed(th21);
                                                    throw th;
                                                }
                                            }
                                        } catch (Throwable th22) {
                                            th = th22;
                                            th = th;
                                            byteArrayInputStream.close();
                                            throw th;
                                        }
                                    } catch (FileNotFoundException e16) {
                                        e = e16;
                                        r7 = 1;
                                        bVar2.b(6, e);
                                        r8 = r7;
                                        bVar2.f2681h = null;
                                        bVar2.f2680g = null;
                                        z4 = false;
                                        r9 = r8;
                                    } catch (IOException e17) {
                                        e = e17;
                                        r7 = 1;
                                        bVar2.b(7, e);
                                        r8 = r7;
                                        bVar2.f2681h = null;
                                        bVar2.f2680g = null;
                                        z4 = false;
                                        r9 = r8;
                                    }
                                } catch (FileNotFoundException e18) {
                                    e = e18;
                                    bVar2.b(6, e);
                                    r8 = r7;
                                    bVar2.f2681h = null;
                                    bVar2.f2680g = null;
                                    z4 = false;
                                    r9 = r8;
                                } catch (IOException e19) {
                                    e = e19;
                                    bVar2.b(7, e);
                                    r8 = r7;
                                    bVar2.f2681h = null;
                                    bVar2.f2680g = null;
                                    z4 = false;
                                    r9 = r8;
                                }
                            } catch (Throwable th23) {
                                bVar2.f2681h = null;
                                bVar2.f2680g = null;
                                throw th23;
                            }
                        }
                        if (z4) {
                            e(packageInfo, filesDir);
                        }
                        z5 = z4;
                        r12 = r9;
                    } catch (Throwable th24) {
                        try {
                            A.close();
                            throw th24;
                        } catch (IOException e20) {
                            eVar.i(7, e20);
                            throw th24;
                        }
                    }
                } else {
                    bVar2.b(4, null);
                }
                if (z5 || !z2) {
                    r13 = 0;
                } else {
                    r13 = r12;
                }
                j.c(context, r13);
            }
            bVar2.b(3, Integer.valueOf(Build.VERSION.SDK_INT));
            z3 = true;
            z5 = false;
            r12 = z3;
            if (z5) {
                r13 = 0;
            } else {
                r13 = 0;
            }
            j.c(context, r13);
        } catch (PackageManager.NameNotFoundException e21) {
            eVar.i(7, e21);
            j.c(context, false);
        }
    }

    public static void u(ByteArrayOutputStream byteArrayOutputStream, long j5, int i5) throws IOException {
        byte[] bArr = new byte[i5];
        for (int i6 = 0; i6 < i5; i6++) {
            bArr[i6] = (byte) ((j5 >> (i6 * 8)) & 255);
        }
        byteArrayOutputStream.write(bArr);
    }

    public static void v(ByteArrayOutputStream byteArrayOutputStream, int i5) throws IOException {
        u(byteArrayOutputStream, i5, 2);
    }
}
