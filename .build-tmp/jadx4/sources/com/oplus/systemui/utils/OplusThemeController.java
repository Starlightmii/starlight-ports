package com.oplus.systemui.utils;

import android.content.Context;
import android.content.res.Configuration;
import android.content.res.Resources;
import android.os.Trace;
import androidx.collection.FloatList$;
import com.android.systemui.statusbar.policy.ConfigurationController;
import java.io.PrintWriter;
import java.lang.invoke.VarHandle;
import java.util.ArrayList;
import java.util.List;

/* JADX INFO: compiled from: go/retraceme fd74bf9514dd0739b92d67a6d48a51eb10aaf8df760d83496b8dcfcf83e5f698 */
/* JADX INFO: loaded from: /home/starlight/Projects/Starlight Ports/.build-tmp/classes4.dex */
public final class OplusThemeController {
    public static final Companion Companion = new Companion();
    private static final String TAG = "OplusThemeController";
    private final ConfigurationController.ConfigurationListener configurationListener;
    private final Context context;
    public boolean isThirdTheme;
    private final List<ThemeListener> listeners = new ArrayList();
    public int themeChanged;
    public int uiMode;

    /* JADX INFO: compiled from: go/retraceme fd74bf9514dd0739b92d67a6d48a51eb10aaf8df760d83496b8dcfcf83e5f698 */
    public final class Companion {
    }

    /* JADX INFO: compiled from: go/retraceme fd74bf9514dd0739b92d67a6d48a51eb10aaf8df760d83496b8dcfcf83e5f698 */
    public interface ThemeListener {
        void onThemeChanged();
    }

    public OplusThemeController(Context context, ConfigurationController configurationController) {
        this.context = context;
        OplusThemeController$configurationListener$1 oplusThemeController$configurationListener$1 = new OplusThemeController$configurationListener$1();
        oplusThemeController$configurationListener$1.this$0 = this;
        VarHandle.storeStoreFence();
        this.configurationListener = oplusThemeController$configurationListener$1;
        configurationController.addCallback(oplusThemeController$configurationListener$1);
        this.themeChanged = getThemeChanged(context);
        this.uiMode = context.getResources().getConfiguration().uiMode & 48;
        this.isThirdTheme = isCustomTheme(context);
    }

    public final void addCallback(ThemeListener themeListener) {
        this.listeners.add(themeListener);
    }

    public final void dump(PrintWriter printWriter, String[] strArr) {
        Resources resources;
        Configuration configuration;
        if (printWriter != null) {
            printWriter.println(TAG);
        }
        if (printWriter != null) {
            int i = this.themeChanged;
            int i2 = this.uiMode;
            Context context = this.context;
            long jOplusThirdThemeChangedFlag = (context == null || (resources = context.getResources()) == null || (configuration = resources.getConfiguration()) == null) ? -1L : OplusThemeControllerKt.oplusThirdThemeChangedFlag(configuration);
            boolean z = this.isThirdTheme;
            StringBuilder sbM = FloatList$.ExternalSyntheticOutline0.m("themeChanged:", i, i2, " uiMode:", "  theme flag:");
            sbM.append(jOplusThirdThemeChangedFlag);
            sbM.append(" isThirdTheme:");
            sbM.append(z);
            printWriter.println(sbM.toString());
        }
    }

    public final int getThemeChanged(Context context) {
        Resources resources;
        Configuration configuration;
        if (context == null || (resources = context.getResources()) == null || (configuration = resources.getConfiguration()) == null) {
            return -1;
        }
        return OplusThemeControllerKt.oplusThirdThemeChange(configuration);
    }

    public final int getUiMode() {
        return this.uiMode;
    }

    public final boolean isCustomTheme(Context context) {
        Resources resources;
        Configuration configuration;
        return (context == null || (resources = context.getResources()) == null || (configuration = resources.getConfiguration()) == null || !OplusThemeControllerKt.isOplusCustomTheme(configuration)) ? false : true;
    }

    public final void notifyThemeChanged() {
        Trace.traceBegin(8L, "OplusThemeController#notifyThemeChanged");
        ArrayList arrayList = new ArrayList(this.listeners);
        int size = arrayList.size();
        int i = 0;
        while (i < size) {
            Object obj = arrayList.get(i);
            i++;
            ThemeListener themeListener = (ThemeListener) obj;
            if (this.listeners.contains(themeListener)) {
                Trace.traceBegin(8L, (themeListener != null ? themeListener.toString() : null) + "_onThemeChanged");
                if (themeListener != null) {
                    themeListener.onThemeChanged();
                }
                Trace.traceEnd(8L);
            }
        }
        Trace.traceEnd(8L);
    }

    public final void removeCallback(ThemeListener themeListener) {
        this.listeners.remove(themeListener);
    }

    public final void setThemeChanged(int i) {
        this.themeChanged = i;
    }

    public final void setThirdTheme(boolean z) {
        this.isThirdTheme = z;
    }

    public final void setUiMode(int i) {
        this.uiMode = i;
    }

    public final int getThemeChanged() {
        return this.themeChanged;
    }
}
