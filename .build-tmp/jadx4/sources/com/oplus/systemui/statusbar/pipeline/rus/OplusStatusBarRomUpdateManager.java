package com.oplus.systemui.statusbar.pipeline.rus;

import android.content.Context;
import android.content.SharedPreferences;
import com.android.keyguard.OplusLockIconView$;
import com.oplusos.systemui.common.util.LogUtil;
import com.oplusos.systemui.common.utils.Util;
import java.io.IOException;
import java.io.StringReader;
import kotlin.jvm.internal.Intrinsics;
import org.xmlpull.v1.XmlPullParser;
import org.xmlpull.v1.XmlPullParserException;
import org.xmlpull.v1.XmlPullParserFactory;

/* JADX INFO: compiled from: go/retraceme fd74bf9514dd0739b92d67a6d48a51eb10aaf8df760d83496b8dcfcf83e5f698 */
/* JADX INFO: loaded from: /home/starlight/Projects/Starlight Ports/.build-tmp/classes4.dex */
public final class OplusStatusBarRomUpdateManager {
    public static final Companion Companion = new Companion();
    public static final String OPLUS_WIFI_INDICATOR_PREFERENCE = "wifi_indicator_preference";
    public static final String TAG = "OplusStatusBarRomUpdateUtils";
    public static final String TAG_WIFI_INDICATOR_ENABLED = "showWifiIndicatorWhenEnabled";
    private static volatile OplusStatusBarRomUpdateManager sInstance;
    private Context context;
    public boolean mShowWifiIndicatorWhenEnabled;

    /* JADX INFO: compiled from: go/retraceme fd74bf9514dd0739b92d67a6d48a51eb10aaf8df760d83496b8dcfcf83e5f698 */
    public final class Companion {
        public final OplusStatusBarRomUpdateManager getInstance(Context context) {
            if (OplusStatusBarRomUpdateManager.sInstance == null) {
                synchronized (OplusStatusBarRomUpdateManager.class) {
                    if (OplusStatusBarRomUpdateManager.sInstance == null) {
                        OplusStatusBarRomUpdateManager.sInstance = new OplusStatusBarRomUpdateManager(context.getApplicationContext());
                    }
                }
            }
            return OplusStatusBarRomUpdateManager.sInstance;
        }
    }

    public OplusStatusBarRomUpdateManager(Context context) {
        this.context = context;
        this.mShowWifiIndicatorWhenEnabled = true;
        SharedPreferences sharedPreferences = context.getSharedPreferences(OPLUS_WIFI_INDICATOR_PREFERENCE, 0);
        boolean z = sharedPreferences != null ? sharedPreferences.getBoolean(TAG_WIFI_INDICATOR_ENABLED, true) : true;
        this.mShowWifiIndicatorWhenEnabled = z;
        OplusLockIconView$.ExternalSyntheticOutline0.m("init wifi indicator enabled: ", TAG, z);
    }

    public final void downloadStatusBarTintDataFromFile(String str) {
        String dataFromProvider = Util.getDataFromProvider(this.context, str);
        dataFromProvider.getClass();
        StringReader stringReader = new StringReader(dataFromProvider);
        try {
            try {
                XmlPullParser xmlPullParserNewPullParser = XmlPullParserFactory.newInstance().newPullParser();
                xmlPullParserNewPullParser.setInput(stringReader);
                parseXml(xmlPullParserNewPullParser);
                stringReader.close();
            } catch (XmlPullParserException e) {
                LogUtil.e(LogUtil.TAG_AV, TAG, "Got execption parsing permissions:", e);
            }
        } finally {
            try {
                stringReader.close();
            } catch (IOException e2) {
                LogUtil.e(LogUtil.TAG_AV, TAG, "Got execption parsing permissions:", e2);
            }
        }
    }

    public final Context getContext() {
        return this.context;
    }

    public final boolean getShowWifiIndicatorWhenEnabled() {
        return this.mShowWifiIndicatorWhenEnabled;
    }

    public final void parseXml(XmlPullParser xmlPullParser) throws XmlPullParserException, IOException {
        int eventType = xmlPullParser.getEventType();
        boolean z = true;
        while (eventType != 1) {
            if (eventType == 2 && Intrinsics.areEqual(xmlPullParser.getName(), TAG_WIFI_INDICATOR_ENABLED)) {
                z = Boolean.parseBoolean(xmlPullParser.nextText());
            }
            eventType = xmlPullParser.next();
        }
        SharedPreferences sharedPreferences = this.context.getSharedPreferences(OPLUS_WIFI_INDICATOR_PREFERENCE, 0);
        if (sharedPreferences != null) {
            SharedPreferences.Editor editorEdit = sharedPreferences.edit();
            editorEdit.putBoolean(TAG_WIFI_INDICATOR_ENABLED, z);
            editorEdit.apply();
        }
        this.mShowWifiIndicatorWhenEnabled = z;
        OplusLockIconView$.ExternalSyntheticOutline0.m("rus update wifi indicator enabled: ", TAG, z);
    }

    public final void setContext(Context context) {
        this.context = context;
    }
}
