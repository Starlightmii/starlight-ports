package com.oplusos.systemui.common.settingsvalue;

import android.content.Context;
import com.oplusos.systemui.common.multiuser.MultiUserContentHelper;

/* JADX INFO: compiled from: go/retraceme fd74bf9514dd0739b92d67a6d48a51eb10aaf8df760d83496b8dcfcf83e5f698 */
/* JADX INFO: loaded from: /home/starlight/Projects/Starlight Ports/.build-tmp/classes4.dex */
public abstract class AbsSettingsValueProxy {
    public static final Companion Companion = new Companion();
    public final String TAG = getClass().getSimpleName();

    public static final int getGlobalIntValue(Context context, String str, int i) {
        return Companion.getGlobalIntValue(context, str, i);
    }

    public static final int getSecureIntValue(Context context, String str, int i) {
        return Companion.getSecureIntValue(context, str, i);
    }

    public static final String getSecureStringValue(Context context, String str) {
        return Companion.getSecureStringValue(context, str);
    }

    public static final float getSystemFloatValue(Context context, String str, float f) {
        return Companion.getSystemFloatValue(context, str, f);
    }

    public static final int getSystemIntValue(Context context, String str, int i) {
        return Companion.getSystemIntValue(context, str, i);
    }

    public static final String getSystemStringValue(Context context, String str) {
        return Companion.getSystemStringValue(context, str);
    }

    public static final String getSystemStringValueForCurUser(Context context, String str) {
        return Companion.getSystemStringValueForCurUser(context, str);
    }

    public static final void setGlobalIntValue(Context context, String str, int i) {
        Companion.setGlobalIntValue(context, str, i);
    }

    public static final void setSecureIntValue(Context context, String str, int i) {
        Companion.setSecureIntValue(context, str, i);
    }

    public static final boolean setSecureIntValueWithResult(Context context, String str, int i) {
        return Companion.setSecureIntValueWithResult(context, str, i);
    }

    public static final void setSecureStringValue(Context context, String str, String str2) {
        Companion.setSecureStringValue(context, str, str2);
    }

    public static final void setSystemIntValue(Context context, String str, float f) {
        Companion.setSystemIntValue(context, str, f);
    }

    public static final void setSystemStringValue(Context context, String str, String str2) {
        Companion.setSystemStringValue(context, str, str2);
    }

    public abstract void checkValidity(Context context);

    public final String getTAG() {
        return this.TAG;
    }

    /* JADX INFO: compiled from: go/retraceme fd74bf9514dd0739b92d67a6d48a51eb10aaf8df760d83496b8dcfcf83e5f698 */
    public final class Companion {
        public final int getGlobalIntValue(Context context, String str, int i) {
            return MultiUserContentHelper.Companion.getGlobalIntValue(context, str, i);
        }

        public final int getSecureIntValue(Context context, String str, int i) {
            return MultiUserContentHelper.Companion.getSecureIntValue(context, str, i, -2);
        }

        public final String getSecureStringValue(Context context, String str) {
            return MultiUserContentHelper.Companion.getSecureStringValue(context, str, -2);
        }

        public final float getSystemFloatValue(Context context, String str, float f) {
            return MultiUserContentHelper.Companion.getSystemFloatValue(context, str, f, -2);
        }

        public final int getSystemIntValue(Context context, String str, int i) {
            return MultiUserContentHelper.Companion.getSystemIntValue(context, str, i, -2);
        }

        public final String getSystemStringValue(Context context, String str) {
            return MultiUserContentHelper.Companion.getSystemStringValue(context, str, -2);
        }

        public final String getSystemStringValueForCurUser(Context context, String str) {
            return MultiUserContentHelper.Companion.getGlobalStringValueForCurUser(context, str);
        }

        public final void setGlobalIntValue(Context context, String str, int i) {
            MultiUserContentHelper.Companion.setGlobalIntValue(context, str, i);
        }

        public final void setSecureIntValue(Context context, String str, int i) {
            MultiUserContentHelper.Companion.setSecureIntValue(context, str, i, -2);
        }

        public final boolean setSecureIntValueWithResult(Context context, String str, int i) {
            return MultiUserContentHelper.Companion.setSecureIntValueWithResult(context, str, i, -2);
        }

        public final void setSecureStringValue(Context context, String str, String str2) {
            MultiUserContentHelper.Companion.setSecureStringValue(context, str, str2, -2);
        }

        public final void setSystemIntValue(Context context, String str, int i) {
            MultiUserContentHelper.Companion.setSystemIntValue(context, str, i, -2);
        }

        public final void setSystemStringValue(Context context, String str, String str2) {
            MultiUserContentHelper.Companion.setSystemStringValue(context, str, str2);
        }

        public final void setSystemStringValue(Context context, String str, String str2, int i) {
            MultiUserContentHelper.Companion.setSystemStringValue(context, str, str2, i);
        }

        public final void setSecureIntValue(Context context, String str, int i, int i2) {
            MultiUserContentHelper.Companion.setSecureIntValue(context, str, i, i2);
        }

        public final void setSecureStringValue(Context context, String str, String str2, int i) {
            MultiUserContentHelper.Companion.setSecureStringValue(context, str, str2, i);
        }

        public final void setSystemIntValue(Context context, String str, float f) {
            MultiUserContentHelper.Companion.setSystemFloatValue(context, str, f, -2);
        }

        public final int getSecureIntValue(Context context, String str, int i, int i2) {
            return MultiUserContentHelper.Companion.getSecureIntValue(context, str, i, i2);
        }

        public final String getSecureStringValue(Context context, String str, int i) {
            return MultiUserContentHelper.Companion.getSecureStringValue(context, str, i);
        }

        public final int getSystemIntValue(Context context, String str, int i, int i2) {
            return MultiUserContentHelper.Companion.getSystemIntValue(context, str, i, i2);
        }

        public final String getSystemStringValue(Context context, String str, int i) {
            return MultiUserContentHelper.Companion.getSystemStringValue(context, str, i);
        }

        public final void setSystemIntValue(Context context, String str, int i, int i2) {
            MultiUserContentHelper.Companion.setSystemIntValue(context, str, i, i2);
        }
    }

    public static final void setSecureIntValue(Context context, String str, int i, int i2) {
        Companion.setSecureIntValue(context, str, i, i2);
    }

    public static final void setSecureStringValue(Context context, String str, String str2, int i) {
        Companion.setSecureStringValue(context, str, str2, i);
    }

    public static final void setSystemIntValue(Context context, String str, int i) {
        Companion.setSystemIntValue(context, str, i);
    }

    public static final void setSystemStringValue(Context context, String str, String str2, int i) {
        Companion.setSystemStringValue(context, str, str2, i);
    }

    public static final int getSecureIntValue(Context context, String str, int i, int i2) {
        return Companion.getSecureIntValue(context, str, i, i2);
    }

    public static final String getSecureStringValue(Context context, String str, int i) {
        return Companion.getSecureStringValue(context, str, i);
    }

    public static final int getSystemIntValue(Context context, String str, int i, int i2) {
        return Companion.getSystemIntValue(context, str, i, i2);
    }

    public static final String getSystemStringValue(Context context, String str, int i) {
        return Companion.getSystemStringValue(context, str, i);
    }

    public static final void setSystemIntValue(Context context, String str, int i, int i2) {
        Companion.setSystemIntValue(context, str, i, i2);
    }
}
