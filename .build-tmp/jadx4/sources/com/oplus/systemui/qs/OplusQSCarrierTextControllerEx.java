package com.oplus.systemui.qs;

import android.content.Context;
import android.telephony.CarrierConfigManager;
import android.util.SparseArray;
import java.util.Objects;

/* JADX INFO: compiled from: go/retraceme fd74bf9514dd0739b92d67a6d48a51eb10aaf8df760d83496b8dcfcf83e5f698 */
/* JADX INFO: loaded from: /home/starlight/Projects/Starlight Ports/.build-tmp/classes4.dex */
public class OplusQSCarrierTextControllerEx {
    public static volatile OplusQSCarrierTextControllerEx sInstance;
    public final CarrierConfigManager mConfigMgr;
    public boolean mListening;
    public final SparseArray mConfigMap = new SparseArray(2);
    public final SparseArray mLastConfigMap = new SparseArray(2);

    /* JADX INFO: compiled from: go/retraceme fd74bf9514dd0739b92d67a6d48a51eb10aaf8df760d83496b8dcfcf83e5f698 */
    public final class Config {
        public String mWfcSpnCustomize;

        public final boolean equals(Object obj) {
            if (this == obj) {
                return true;
            }
            if (obj == null || Config.class != obj.getClass()) {
                return false;
            }
            return Objects.equals(this.mWfcSpnCustomize, ((Config) obj).mWfcSpnCustomize);
        }

        public final int hashCode() {
            return Objects.hash(this.mWfcSpnCustomize);
        }

        public final String toString() {
            return "wfcSpnCustomize=" + this.mWfcSpnCustomize;
        }
    }

    public OplusQSCarrierTextControllerEx(Context context) {
        this.mConfigMgr = (CarrierConfigManager) context.getApplicationContext().getSystemService("carrier_config");
    }

    public static Config getConfig(SparseArray sparseArray, int i) {
        return sparseArray.contains(i) ? (Config) sparseArray.get(i) : new Config();
    }

    public static OplusQSCarrierTextControllerEx getInstance(Context context) {
        if (sInstance == null) {
            synchronized (OplusQSCarrierTextControllerEx.class) {
                try {
                    if (sInstance == null) {
                        sInstance = new OplusQSCarrierTextControllerEx(context);
                    }
                } catch (Throwable th) {
                    throw th;
                }
            }
        }
        return sInstance;
    }

    public void setListening(boolean z) {
        this.mListening = z;
    }
}
