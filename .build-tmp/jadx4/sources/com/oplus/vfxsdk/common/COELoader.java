package com.oplus.vfxsdk.common;

import android.content.Context;
import android.util.Log;
import androidx.car.app.serialization.Bundler$;
import com.oplus.vfxsdk.common.api.ICOELoader;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.InputStream;
import java.security.MessageDigest;
import kotlin.Unit;
import kotlin.collections.ArraysKt___ArraysKt;
import kotlin.io.ByteStreamsKt;
import kotlin.io.CloseableKt;
import kotlin.text.StringsKt__StringsJVMKt;
import org.json.JSONException;

/* JADX INFO: compiled from: go/retraceme fd74bf9514dd0739b92d67a6d48a51eb10aaf8df760d83496b8dcfcf83e5f698 */
/* JADX INFO: loaded from: /home/starlight/Projects/Starlight Ports/.build-tmp/classes4.dex */
public abstract class COELoader implements ICOELoader {
    private final String TAG = "VFX:COELoader";
    private final Context context;

    public COELoader(Context context) {
        this.context = context;
    }

    @Override // com.oplus.vfxsdk.common.api.ICOELoader
    public void load(String str, boolean z, boolean z2) throws IOException {
        COEData cache = z2 ? COEDataCache.INSTANCE.getCache(str) : null;
        if (cache != null) {
            loadScene(cache, z, z2);
            return;
        }
        InputStream inputStreamOpen = this.context.getAssets().open(str);
        try {
            Log.i(this.TAG, "load assetFile:" + str);
            inputStreamOpen.getClass();
            loadCOEData(str, ByteStreamsKt.readBytes(inputStreamOpen), z, z2);
            CloseableKt.closeFinally(inputStreamOpen, (Throwable) null);
        } catch (Throwable th) {
            try {
                throw th;
            } catch (Throwable th2) {
                CloseableKt.closeFinally(inputStreamOpen, th);
                throw th2;
            }
        }
    }

    public final COEData loadCOEData(String str, byte[] bArr, boolean z, boolean z2) throws JSONException {
        COEDataCache cOEDataCache = COEDataCache.INSTANCE;
        COEData cache = cOEDataCache.getCache(str);
        if (cache == null) {
            cache = new COEParse().parse(bArr, StringsKt__StringsJVMKt.endsWith(str, ".coz", false));
        }
        if (z2) {
            cOEDataCache.putCache(str, cache);
        }
        loadScene(cache, z, z2);
        return cache;
    }

    @Override // com.oplus.vfxsdk.common.api.ICOELoader
    public void loadConfig(byte[] bArr, int i, int i2, boolean z) throws JSONException {
        loadCOEData(Bundler$.ExternalSyntheticOutline0.m(ArraysKt___ArraysKt.joinToString$default(MessageDigest.getInstance("SHA-256").digest(bArr), "", loadConfig.hashString.1.INSTANCE, 30), ".coz"), bArr, true, false);
    }

    public final void loadFile(String str, boolean z, boolean z2) throws IOException {
        COEData cache = z2 ? COEDataCache.INSTANCE.getCache(str) : null;
        if (cache != null) {
            loadScene(cache, z, z2);
            return;
        }
        try {
            FileInputStream fileInputStream = new FileInputStream(str);
            try {
                Log.i(this.TAG, "load file from path: " + str);
                loadCOEData(str, ByteStreamsKt.readBytes(fileInputStream), z, z2);
                CloseableKt.closeFinally(fileInputStream, (Throwable) null);
            } catch (Throwable th) {
                try {
                    throw th;
                } catch (Throwable th2) {
                    CloseableKt.closeFinally(fileInputStream, th);
                    throw th2;
                }
            }
        } catch (FileNotFoundException e) {
            Log.e(this.TAG, "File not found: " + str, e);
            throw e;
        } catch (IOException e2) {
            Log.e(this.TAG, "Error reading file: " + str, e2);
            throw e2;
        }
    }

    @Override // com.oplus.vfxsdk.common.api.ICOELoader
    public void loadPath(String str) {
        try {
            FileInputStream fileInputStream = new FileInputStream(str);
            try {
                Log.i(this.TAG, "load assetFile:" + str);
                loadCOEData(str, ByteStreamsKt.readBytes(fileInputStream), true, false);
                CloseableKt.closeFinally(fileInputStream, (Throwable) null);
            } catch (Throwable th) {
                try {
                    throw th;
                } catch (Throwable th2) {
                    CloseableKt.closeFinally(fileInputStream, th);
                    throw th2;
                }
            }
        } catch (FileNotFoundException e) {
            String str2 = this.TAG;
            e.printStackTrace();
            Log.e(str2, Unit.INSTANCE.toString());
        } catch (IOException e2) {
            String str3 = this.TAG;
            e2.printStackTrace();
            Log.e(str3, Unit.INSTANCE.toString());
        }
    }

    public abstract void loadScene(COEData cOEData, boolean z, boolean z2);
}
