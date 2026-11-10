package com.oplus.systemui.keyguard.wallpaper.pictorial;

import android.content.Context;
import android.os.SystemClock;
import android.util.Log;
import com.android.systemui.DependencyEx;
import com.oplus.systemui.aod.utils.NoneLocalHbmAodRusManager$;
import com.oplus.systemui.keyguard.wallpaper.pictorial.app.PictorialAppInfo;
import com.oplusos.systemui.common.util.LogUtil;
import java.io.File;
import java.io.FileReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.StringReader;
import java.util.ArrayList;
import java.util.List;
import kotlin.io.CloseableKt;
import kotlin.jvm.internal.Intrinsics;
import org.xmlpull.v1.XmlPullParser;
import org.xmlpull.v1.XmlPullParserException;
import org.xmlpull.v1.XmlPullParserFactory;

/* JADX INFO: compiled from: go/retraceme fd74bf9514dd0739b92d67a6d48a51eb10aaf8df760d83496b8dcfcf83e5f698 */
/* JADX INFO: loaded from: /home/starlight/Projects/Starlight Ports/.build-tmp/classes4.dex */
public final class PictorialRomUpdateUtils {
    public static final PictorialRomUpdateUtils INSTANCE = new PictorialRomUpdateUtils();
    public static final String KGD_PICTORIAL_CONFIG_LIST = "sys_systemui_pictorial_config_list";
    public static final String KGD_PICTORIAL_CONFIG_NAME = "sys_systemui_pictorial_config_list.xml";
    public static final String TAG = "PictorialRomUpdateUtils";

    public static final List<PictorialAppInfo> loadLocalConfiguration(Context context) {
        List<PictorialAppInfo> xml;
        long jElapsedRealtime = SystemClock.elapsedRealtime();
        File file = new File(context.getFilesDir(), KGD_PICTORIAL_CONFIG_NAME);
        List<PictorialAppInfo> list = null;
        if (file.exists()) {
            FileReader fileReader = new FileReader(file);
            try {
                XmlPullParser xmlPullParserNewPullParser = XmlPullParserFactory.newInstance().newPullParser();
                xmlPullParserNewPullParser.setInput(fileReader);
                xml = parseXml(context, xmlPullParserNewPullParser);
                CloseableKt.closeFinally(fileReader, (Throwable) null);
            } catch (Throwable th) {
                try {
                    throw th;
                } catch (Throwable th2) {
                    CloseableKt.closeFinally(fileReader, th);
                    throw th2;
                }
            }
        } else {
            try {
                InputStream inputStreamOpen = context.getResources().getAssets().open(KGD_PICTORIAL_CONFIG_NAME);
                try {
                    InputStreamReader inputStreamReader = new InputStreamReader(inputStreamOpen);
                    XmlPullParser xmlPullParserNewPullParser2 = XmlPullParserFactory.newInstance().newPullParser();
                    xmlPullParserNewPullParser2.setInput(inputStreamReader);
                    List<PictorialAppInfo> xml2 = parseXml(context, xmlPullParserNewPullParser2);
                    CloseableKt.closeFinally(inputStreamOpen, (Throwable) null);
                    list = xml2;
                } catch (Throwable th3) {
                    try {
                        throw th3;
                    } catch (Throwable th4) {
                        CloseableKt.closeFinally(inputStreamOpen, th3);
                        throw th4;
                    }
                }
            } catch (IOException unused) {
            }
            xml = list;
        }
        if (LogUtil.getNormalDebug() && LogUtil.isTagValid(LogUtil.TAG_KEYGUARD)) {
            Log.d("SystemUi--Keyguard", NoneLocalHbmAodRusManager$.ExternalSyntheticOutline0.m(SystemClock.elapsedRealtime() - jElapsedRealtime, "load duration = ", "ms", "PictorialRomUpdateUtils-->"));
        }
        return xml;
    }

    public static final void parsePictorialConfiguration(Context context, String str) {
        StringReader stringReader = new StringReader(str);
        try {
            try {
                XmlPullParser xmlPullParserNewPullParser = XmlPullParserFactory.newInstance().newPullParser();
                xmlPullParserNewPullParser.setInput(stringReader);
                List<PictorialAppInfo> xml = parseXml(context, xmlPullParserNewPullParser);
                stringReader.close();
                ((PictorialMediator) DependencyEx.sDependency.getDependency(PictorialMediator.class)).updateRomConfigForRus(xml);
            } finally {
                try {
                    stringReader.close();
                } catch (IOException e) {
                    LogUtil.e(LogUtil.TAG_AV, TAG, "Got execption parsing permissions:", e);
                }
            }
        } catch (XmlPullParserException e2) {
            LogUtil.e(LogUtil.TAG_AV, TAG, "Got execption parsing permissions:", e2);
        }
    }

    /* JADX WARN: Failed to restore switch over string. Please report as a decompilation issue */
    public static final List parseXml(Context context, XmlPullParser xmlPullParser) throws XmlPullParserException, IOException {
        int eventType = xmlPullParser.getEventType();
        ArrayList arrayList = new ArrayList();
        PictorialAppInfo pictorialAppInfo = null;
        while (eventType != 1) {
            if (eventType == 2) {
                String name = xmlPullParser.getName();
                if (name != null) {
                    switch (name.hashCode()) {
                        case -1377758231:
                            if (name.equals(PictorialAppInfo.BURIED) && pictorialAppInfo != null) {
                                pictorialAppInfo.setBuried(Boolean.parseBoolean(xmlPullParser.nextText()));
                            }
                            break;
                        case -1298848381:
                            if (name.equals(PictorialAppInfo.TAG_ENABLE) && pictorialAppInfo != null) {
                                try {
                                    pictorialAppInfo.setEnable(Boolean.parseBoolean(xmlPullParser.nextText()));
                                } catch (IOException unused) {
                                    pictorialAppInfo.setEnable(false);
                                } catch (XmlPullParserException unused2) {
                                    pictorialAppInfo.setEnable(false);
                                }
                            }
                            break;
                        case 3355:
                            if (name.equals(PictorialAppInfo.ID) && pictorialAppInfo != null) {
                                pictorialAppInfo.setId(Integer.parseInt(xmlPullParser.nextText()));
                            }
                            break;
                        case 96801:
                            if (name.equals(PictorialAppInfo.TAG_APP)) {
                                pictorialAppInfo = new PictorialAppInfo("", context);
                            }
                            break;
                        case 111052:
                            if (name.equals("pkg") && pictorialAppInfo != null) {
                                pictorialAppInfo.setPkgName(xmlPullParser.nextText());
                            }
                            break;
                        case 3560248:
                            if (name.equals(PictorialAppInfo.TIPS) && pictorialAppInfo != null) {
                                pictorialAppInfo.setShowTipsFeatherSupport(Boolean.parseBoolean(xmlPullParser.nextText()));
                            }
                            break;
                        case 94746189:
                            if (name.equals("clear") && pictorialAppInfo != null) {
                                pictorialAppInfo.setClearable(Boolean.parseBoolean(xmlPullParser.nextText()));
                            }
                            break;
                        case 109780401:
                            if (name.equals(PictorialAppInfo.STYLE) && pictorialAppInfo != null) {
                                pictorialAppInfo.setStyle(Integer.parseInt(xmlPullParser.nextText()));
                            }
                            break;
                    }
                }
            } else if (eventType == 3 && Intrinsics.areEqual(xmlPullParser.getName(), PictorialAppInfo.TAG_APP)) {
                if (pictorialAppInfo != null) {
                    arrayList.add(pictorialAppInfo);
                }
                pictorialAppInfo = null;
            }
            eventType = xmlPullParser.next();
        }
        return arrayList;
    }
}
