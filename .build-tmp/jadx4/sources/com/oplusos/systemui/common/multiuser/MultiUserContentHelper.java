package com.oplusos.systemui.common.multiuser;

import android.content.Context;
import android.content.pm.PackageManager;
import android.database.ContentObserver;
import android.net.Uri;
import android.os.UserHandle;
import android.os.UserManager;
import android.provider.Settings;
import com.android.keyguard.KeyguardUpdateMonitor;
import com.oplusos.systemui.common.observer.ObserverDispatcher;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

/* JADX INFO: compiled from: go/retraceme fd74bf9514dd0739b92d67a6d48a51eb10aaf8df760d83496b8dcfcf83e5f698 */
/* JADX INFO: loaded from: /home/starlight/Projects/Starlight Ports/.build-tmp/classes4.dex */
public abstract class MultiUserContentHelper {
    private static final String TAG = "MultiUserContentHelper";
    public static volatile boolean sTraversing;
    public static final Companion Companion = new Companion();
    private static final Object sLock = new Object();
    private static final List<MultiUserContentHolder> HOLDERS_PENDING_ADD = new ArrayList();
    private static final List<MultiUserContentHolder> HOLDERS_PENDING_DELETE = new ArrayList();
    private static final List<MultiUserContentHolder> HOLDERS_REGISTERED = new ArrayList();
    private static final IMultiUserListener LISTENER = new MultiUserContentHelper$Companion$LISTENER$1();

    public static final Context getCurrentUserContext(Context context) {
        return Companion.getCurrentUserContext(context);
    }

    public static final int getCurrentUserId() {
        return Companion.getCurrentUserId();
    }

    public static final int getGlobalIntValue(Context context, String str, int i) {
        return Companion.getGlobalIntValue(context, str, i);
    }

    public static final String getGlobalStringValue(Context context, String str) {
        return Companion.getGlobalStringValue(context, str);
    }

    public static final String getGlobalStringValueForCurUser(Context context, String str) {
        return Companion.getGlobalStringValueForCurUser(context, str);
    }

    public static final Uri getGlobalUri(String str) {
        return Companion.getGlobalUri(str);
    }

    public static final int getSecureIntValue(Context context, String str, int i) {
        return Companion.getSecureIntValue(context, str, i);
    }

    public static final String getSecureStringValue(Context context, String str) {
        return Companion.getSecureStringValue(context, str);
    }

    public static final Uri getSecureUri(String str) {
        return Companion.getSecureUri(str);
    }

    public static final float getSystemFloatValue(Context context, String str, float f, int i) {
        return Companion.getSystemFloatValue(context, str, f, i);
    }

    public static final int getSystemIntValue(Context context, String str, int i) {
        return Companion.getSystemIntValue(context, str, i);
    }

    public static final String getSystemStringValue(Context context, String str) {
        return Companion.getSystemStringValue(context, str);
    }

    public static final Uri getSystemUri(String str) {
        return Companion.getSystemUri(str);
    }

    public static final boolean isCurrentUserUnlocked(Context context) {
        return Companion.isCurrentUserUnlocked(context);
    }

    public static final void registerForUserSwitch(Context context, Uri uri, boolean z, ContentObserver contentObserver, int i) {
        Companion.registerForUserSwitch(context, uri, z, contentObserver, i);
    }

    public static final void setGlobalIntValue(Context context, String str, int i) {
        Companion.setGlobalIntValue(context, str, i);
    }

    public static final void setGlobalStringValue(Context context, String str, String str2) {
        Companion.setGlobalStringValue(context, str, str2);
    }

    public static final void setSecureIntValue(Context context, String str, int i) {
        Companion.setSecureIntValue(context, str, i);
    }

    public static final boolean setSecureIntValueWithResult(Context context, String str, int i, int i2) {
        return Companion.setSecureIntValueWithResult(context, str, i, i2);
    }

    public static final void setSecureStringValue(Context context, String str, String str2) {
        Companion.setSecureStringValue(context, str, str2);
    }

    public static final void setSystemFloatValue(Context context, String str, float f, int i) {
        Companion.setSystemFloatValue(context, str, f, i);
    }

    public static final void setSystemIntValue(Context context, String str, int i) {
        Companion.setSystemIntValue(context, str, i);
    }

    public static final void setSystemStringValue(Context context, String str, String str2) {
        Companion.setSystemStringValue(context, str, str2);
    }

    public static final void unregister(Context context, ContentObserver contentObserver) {
        Companion.unregister(context, contentObserver);
    }

    public static final void setSecureIntValue(Context context, String str, int i, int i2) {
        Companion.setSecureIntValue(context, str, i, i2);
    }

    public static final void setSecureStringValue(Context context, String str, String str2, int i) {
        Companion.setSecureStringValue(context, str, str2, i);
    }

    public static final void setSystemIntValue(Context context, String str, int i, int i2) {
        Companion.setSystemIntValue(context, str, i, i2);
    }

    public static final void setSystemStringValue(Context context, String str, String str2, int i) {
        Companion.setSystemStringValue(context, str, str2, i);
    }

    /* JADX INFO: compiled from: go/retraceme fd74bf9514dd0739b92d67a6d48a51eb10aaf8df760d83496b8dcfcf83e5f698 */
    public final class Companion {
        public final Context getCurrentUserContext(Context context) {
            int currentUserId = getCurrentUserId();
            if (context.getUserId() == currentUserId) {
                return context;
            }
            try {
                return context.createPackageContextAsUser(context.getPackageName(), 0, UserHandle.of(currentUserId));
            } catch (PackageManager.NameNotFoundException unused) {
                return null;
            }
        }

        public final int getCurrentUserId() {
            return KeyguardUpdateMonitor.getCurrentUser();
        }

        public final int getGlobalIntValue(Context context, String str, int i) {
            return Settings.Global.getInt(context.getContentResolver(), str, i);
        }

        public final String getGlobalStringValue(Context context, String str) {
            return Settings.Global.getString(context.getContentResolver(), str);
        }

        public final String getGlobalStringValueForCurUser(Context context, String str) {
            return Settings.Global.getStringForUser(context.getContentResolver(), str, -2);
        }

        public final Uri getGlobalUri(String str) {
            return Settings.Global.getUriFor(str);
        }

        public final int getSecureIntValue(Context context, String str, int i, int i2) {
            return Settings.Secure.getIntForUser(context.getContentResolver(), str, i, i2);
        }

        public final String getSecureStringValue(Context context, String str) {
            return Settings.Secure.getStringForUser(context.getContentResolver(), str, -2);
        }

        public final Uri getSecureUri(String str) {
            return Settings.Secure.getUriFor(str);
        }

        public final float getSystemFloatValue(Context context, String str, float f, int i) {
            return Settings.System.getFloatForUser(context.getContentResolver(), str, f, i);
        }

        public final int getSystemIntValue(Context context, String str, int i, int i2) {
            return Settings.System.getIntForUser(context.getContentResolver(), str, i, i2);
        }

        public final String getSystemStringValue(Context context, String str, int i) {
            return Settings.System.getStringForUser(context.getContentResolver(), str, i);
        }

        public final Uri getSystemUri(String str) {
            return Settings.System.getUriFor(str);
        }

        public final boolean isCurrentUserUnlocked(Context context) {
            return UserManager.get(context).isUserUnlocked(getCurrentUserId());
        }

        public final void registerForUserSwitch(Context context, Uri uri, boolean z, ContentObserver contentObserver) {
            unregister(context, contentObserver);
            contentObserver.onChange(false);
            ObserverDispatcher.getInstance().registerObserver(uri, contentObserver, -1);
            MultiUserContentHolder multiUserContentHolder = new MultiUserContentHolder(contentObserver, uri);
            synchronized (MultiUserContentHelper.sLock) {
                try {
                    if (MultiUserContentHelper.sTraversing) {
                        MultiUserContentHelper.HOLDERS_PENDING_ADD.add(multiUserContentHolder);
                    } else {
                        MultiUserContentHelper.HOLDERS_REGISTERED.add(multiUserContentHolder);
                    }
                } catch (Throwable th) {
                    throw th;
                }
            }
        }

        public final void setGlobalIntValue(Context context, String str, int i) {
            Settings.Global.putInt(context.getContentResolver(), str, i);
        }

        public final void setGlobalStringValue(Context context, String str, String str2) {
            Settings.Global.putString(context.getContentResolver(), str, str2);
        }

        public final void setSecureIntValue(Context context, String str, int i, int i2) {
            Settings.Secure.putIntForUser(context.getContentResolver(), str, i, i2);
        }

        public final boolean setSecureIntValueWithResult(Context context, String str, int i, int i2) {
            return Settings.Secure.putIntForUser(context.getContentResolver(), str, i, i2);
        }

        public final void setSecureStringValue(Context context, String str, String str2) {
            Settings.Secure.putStringForUser(context.getContentResolver(), str, str2, -2);
        }

        public final void setSystemFloatValue(Context context, String str, float f, int i) {
            Settings.System.putFloatForUser(context.getContentResolver(), str, f, i);
        }

        public final void setSystemIntValue(Context context, String str, int i, int i2) {
            Settings.System.putIntForUser(context.getContentResolver(), str, i, i2);
        }

        public final void setSystemStringValue(Context context, String str, String str2, int i) {
            Settings.System.putStringForUser(context.getContentResolver(), str, str2, i);
        }

        public final void unregister(Context context, ContentObserver contentObserver) {
            MultiUserContentHolder multiUserContentHolder;
            ObserverDispatcher.getInstance().unregisterObserver(contentObserver);
            synchronized (MultiUserContentHelper.sLock) {
                try {
                    Iterator it = MultiUserContentHelper.HOLDERS_REGISTERED.iterator();
                    do {
                        if (!it.hasNext()) {
                            multiUserContentHolder = null;
                            break;
                        }
                        multiUserContentHolder = (MultiUserContentHolder) it.next();
                    } while (!multiUserContentHolder.isTarget(contentObserver));
                    if (multiUserContentHolder != null) {
                        if (MultiUserContentHelper.sTraversing) {
                            MultiUserContentHelper.HOLDERS_PENDING_DELETE.add(multiUserContentHolder);
                        } else {
                            MultiUserContentHelper.HOLDERS_REGISTERED.remove(multiUserContentHolder);
                        }
                    }
                } catch (Throwable th) {
                    throw th;
                }
            }
        }

        public final void setSecureIntValue(Context context, String str, int i) {
            setSecureIntValue(context, str, i, -2);
        }

        public final void setSystemIntValue(Context context, String str, int i) {
            setSystemIntValue(context, str, i, -2);
        }

        public final void setSystemStringValue(Context context, String str, String str2) {
            setSystemStringValue(context, str, str2, -2);
        }

        public final int getSecureIntValue(Context context, String str, int i) {
            return getSecureIntValue(context, str, i, -2);
        }

        public final int getSystemIntValue(Context context, String str, int i) {
            return getSystemIntValue(context, str, i, -2);
        }

        public final String getSystemStringValue(Context context, String str) {
            return getSystemStringValue(context, str, -2);
        }

        public final void setSecureStringValue(Context context, String str, String str2, int i) {
            Settings.Secure.putStringForUser(context.getContentResolver(), str, str2, i);
        }

        public final String getSecureStringValue(Context context, String str, int i) {
            return Settings.Secure.getStringForUser(context.getContentResolver(), str, i);
        }

        public final void registerForUserSwitch(Context context, Uri uri, boolean z, ContentObserver contentObserver, int i) {
            registerForUserSwitch(context, uri, z, contentObserver);
        }
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

    public static final void registerForUserSwitch(Context context, Uri uri, boolean z, ContentObserver contentObserver) {
        Companion.registerForUserSwitch(context, uri, z, contentObserver);
    }
}
