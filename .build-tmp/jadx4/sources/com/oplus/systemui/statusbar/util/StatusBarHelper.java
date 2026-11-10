package com.oplus.systemui.statusbar.util;

import android.content.Context;
import android.database.ContentObserver;
import android.os.Handler;
import android.provider.Settings;
import android.text.TextUtils;
import androidx.core.app.NotificationCompat$Builder$;
import com.oplusos.systemui.common.feature.FlavorOneFeatureOption;
import com.oplusos.systemui.common.observer.ObserverDispatcher;
import java.util.ArrayList;
import java.util.Iterator;

/* JADX INFO: compiled from: go/retraceme fd74bf9514dd0739b92d67a6d48a51eb10aaf8df760d83496b8dcfcf83e5f698 */
/* JADX INFO: loaded from: /home/starlight/Projects/Starlight Ports/.build-tmp/classes4.dex */
public final class StatusBarHelper {
    public static final Companion Companion = new Companion();
    private static final String DEFAULT_SYSTEM_FONT = "com.monotype.android.font.system.default.font";
    private static final String DEFAULT_SYSTEM_FONT_ONE_SANS = "One Sans";
    private static final String DEFAULT_SYSTEM_FONT_OPPO_SANS = "OPPO Sans";
    private static final String DEFAULT_SYSTEM_FONT_REALME_SANS = "realme Sans";
    private static final String DEFAULT_SYSTEM_FONT_REAL_CHOICE = "Real Choice";
    private static final String DEFAULT_SYSTEM_FONT_REAL_FONT = "真选字体";
    private static final String DEFAULT_SYSTEM_FONT_ROBOTO = "Roboto";
    private static final String KEY_CURRENT_TYPEFACE = "current_typeface_name";
    private static final String STATUS_BAR_FONT_VARIATION = "'wght' 700";
    private static final String SYSTEM_DEFAULT_TYPEFACE = "sys-sans-en";
    private static volatile StatusBarHelper sInstance;
    private final Context mContext;
    private String mCurrentTypeface;
    private final ContentObserver mCurrentTypefaceObserver;
    public boolean mIsObserverRegistered;
    private final ArrayList<TypefaceChangedListener> mTypefacelisteners = new ArrayList<>();

    /* JADX INFO: compiled from: go/retraceme fd74bf9514dd0739b92d67a6d48a51eb10aaf8df760d83496b8dcfcf83e5f698 */
    public final class Companion {
        public final StatusBarHelper getInstance(Context context) {
            StatusBarHelper statusBarHelper;
            StatusBarHelper statusBarHelper2 = StatusBarHelper.sInstance;
            if (statusBarHelper2 != null) {
                return statusBarHelper2;
            }
            synchronized (StatusBarHelper.class) {
                statusBarHelper = StatusBarHelper.sInstance;
                if (statusBarHelper == null) {
                    statusBarHelper = new StatusBarHelper(context.getApplicationContext());
                    StatusBarHelper.sInstance = statusBarHelper;
                }
            }
            return statusBarHelper;
        }
    }

    /* JADX INFO: compiled from: go/retraceme fd74bf9514dd0739b92d67a6d48a51eb10aaf8df760d83496b8dcfcf83e5f698 */
    public interface TypefaceChangedListener {
    }

    public StatusBarHelper(Context context) {
        this.mContext = context.getApplicationContext();
        final Handler handler = new Handler();
        this.mCurrentTypefaceObserver = new ContentObserver(handler) { // from class: com.oplus.systemui.statusbar.util.StatusBarHelper$mCurrentTypefaceObserver$1
            @Override // android.database.ContentObserver
            public final void onChange(boolean z) {
                super.onChange(z);
                StatusBarHelper statusBarHelper = this.this$0;
                statusBarHelper.updateCurrentTypeface(statusBarHelper.mContext);
                StatusBarHelper.access$notifyListener(this.this$0);
            }
        };
    }

    public static final void access$notifyListener(StatusBarHelper statusBarHelper) {
        Iterator<TypefaceChangedListener> it = statusBarHelper.mTypefacelisteners.iterator();
        if (it.hasNext()) {
            throw NotificationCompat$Builder$.ExternalSyntheticOutline0.m(it);
        }
    }

    public final void addListener(TypefaceChangedListener typefaceChangedListener) {
        this.mTypefacelisteners.add(typefaceChangedListener);
    }

    public final String getCurrentTypeface() {
        if (!this.mIsObserverRegistered) {
            updateCurrentTypeface(this.mContext);
        }
        return this.mCurrentTypeface;
    }

    public final boolean isDefaultTypeface() {
        FlavorOneFeatureOption.INSTANCE.getClass();
        if (!FlavorOneFeatureOption.isFlavorOneDevice()) {
            return isDefaultTypeface(getCurrentTypeface());
        }
        String currentTypeface = getCurrentTypeface();
        return isDefaultTypeface(currentTypeface) || DEFAULT_SYSTEM_FONT_REAL_CHOICE.equals(currentTypeface) || DEFAULT_SYSTEM_FONT_REAL_FONT.equals(currentTypeface) || DEFAULT_SYSTEM_FONT_REALME_SANS.equals(currentTypeface);
    }

    public final void registerCurrentTypefaceObserver() {
        if (this.mIsObserverRegistered) {
            return;
        }
        updateCurrentTypeface(this.mContext);
        ObserverDispatcher.getInstance().registerObserver(Settings.System.getUriFor(KEY_CURRENT_TYPEFACE), this.mCurrentTypefaceObserver, -1);
        this.mIsObserverRegistered = true;
    }

    public final void removeListener(TypefaceChangedListener typefaceChangedListener) {
        this.mTypefacelisteners.remove(typefaceChangedListener);
    }

    public final void updateCurrentTypeface(Context context) {
        this.mCurrentTypeface = Settings.System.getStringForUser(context.getContentResolver(), KEY_CURRENT_TYPEFACE, -2);
    }

    public static boolean isDefaultTypeface(String str) {
        return TextUtils.isEmpty(str) || DEFAULT_SYSTEM_FONT.equals(str) || DEFAULT_SYSTEM_FONT_ROBOTO.equals(str) || DEFAULT_SYSTEM_FONT_OPPO_SANS.equals(str) || DEFAULT_SYSTEM_FONT_ONE_SANS.equals(str);
    }
}
