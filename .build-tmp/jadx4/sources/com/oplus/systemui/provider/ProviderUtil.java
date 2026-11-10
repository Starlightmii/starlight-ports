package com.oplus.systemui.provider;

import android.content.Context;
import android.content.SharedPreferences;
import android.database.Cursor;
import android.net.Uri;
import android.text.TextUtils;
import android.util.Log;
import com.android.systemui.DependencyEx;
import com.android.systemui.application.impl.SystemUIApplicationImpl$;
import com.android.systemui.media.controls.domain.pipeline.MediaTimeoutListener$PlaybackStateListener$;
import com.android.systemui.qs.OplusQuickSettingsEx;
import com.android.systemui.statusbar.policy.FlashlightControllerImpl$2$;
import com.oplus.systemui.keyguard.wallpaper.pictorial.PictorialRomUpdateUtils;
import com.oplus.systemui.preventmode.OplusPreventModeData;
import com.oplus.systemui.qs.helper.QSTileHostHelper;
import com.oplus.systemui.qs.utils.AppQsTileBlackList;
import com.oplus.systemui.seedlingservice.utils.SeedlingMediaDataHandleUtils;
import com.oplusos.systemui.common.feature.KeyguardFeatureOption;
import com.oplusos.systemui.common.util.LogUtil;
import com.oplusos.systemui.common.utils.Util;
import com.oplusos.systemui.qs.util.QsSettingsValueProxy;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.InputStreamReader;
import java.nio.charset.Charset;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import kotlin.jvm.internal.Intrinsics;
import org.xmlpull.v1.XmlPullParser;
import org.xmlpull.v1.XmlPullParserException;
import org.xmlpull.v1.XmlPullParserFactory;

/* JADX INFO: compiled from: go/retraceme fd74bf9514dd0739b92d67a6d48a51eb10aaf8df760d83496b8dcfcf83e5f698 */
/* JADX INFO: loaded from: /home/starlight/Projects/Starlight Ports/.build-tmp/classes4.dex */
public final class ProviderUtil {
    public static final String AIR_VIEW_CONFIGS_KEY = "apps_airview_configs";
    public static final String DISABLE_STAT_ANIM_NAME = "disable_status_bar_anim";
    public static final ProviderUtil INSTANCE = new ProviderUtil();
    public static final String LIST_ENABLE_TOGGLE = "isOpen";
    public static final String OPLUS_COMPONENT_SAFE = "oplus.permission.OPLUS_COMPONENT_SAFE";
    public static final String PKG_NAME = "pkg";
    public static final String QUICK_SETTINGS_TILE_KEY = "sys_systemui_quicksettingstile_blacklist";
    public static final String QUICK_SETTINGS_TILE_NAME = "sys_systemui_quicksettingstile_blacklist.xml";
    public static final String TAG = "SystemUI.ProviderUtil";

    public static final void downloadDataFromFile(Context context, String str) throws Throwable {
        String string;
        Uri uri = Util.ROM_UPDATE_CONTENT_URI;
        String[] strArr = {"version"};
        Cursor cursorQuery = null;
        try {
            cursorQuery = context.getContentResolver().query(Util.ROM_UPDATE_CONTENT_URI, strArr, "filtername=\"" + str + "\"", null, null);
            if (cursorQuery == null || cursorQuery.getCount() <= 0) {
                LogUtil.d(LogUtil.TAG_COMMON, "Util", "Config " + str + " cursor is null !!!");
                string = "";
            } else {
                int columnIndex = cursorQuery.getColumnIndex("version");
                cursorQuery.moveToNext();
                string = cursorQuery.getString(columnIndex);
            }
            if (cursorQuery != null) {
                try {
                    cursorQuery.close();
                } catch (Exception unused) {
                    if (cursorQuery != null) {
                        cursorQuery.close();
                    }
                    LogUtil.d(LogUtil.TAG_COMMON, "Util", "We can not get data version with " + str + " from provider");
                }
            }
        } catch (Exception unused2) {
            string = "";
        }
        String string2 = context.getSharedPreferences("systembar_config_version", 0).getString("romupdate_version_" + str, "");
        if (LogUtil.getNormalDebug() && LogUtil.isTagValid(LogUtil.TAG_COMMON)) {
            Log.d("SystemUi--Common", SystemUIApplicationImpl$.ExternalSyntheticOutline0.m("downloadDataFromDb remoteConfigVersion:", string, " localConfigVersion:", string2, "SystemUI.ProviderUtil-->"));
        }
        string2.getClass();
        if (string.compareTo(string2) >= 0) {
            String dataFromProvider = Util.getDataFromProvider(context, str);
            if (LogUtil.getNormalDebug() && LogUtil.isTagValid(LogUtil.TAG_COMMON)) {
                MediaTimeoutListener$PlaybackStateListener$.ExternalSyntheticOutline0.m("xmlValue = ", dataFromProvider, "SystemUI.ProviderUtil-->", "SystemUi--Common");
            }
            if (dataFromProvider != null) {
                if (LogUtil.getNormalDebug() && LogUtil.isTagValid(LogUtil.TAG_AV)) {
                    MediaTimeoutListener$PlaybackStateListener$.ExternalSyntheticOutline0.m("downloadDataFromFile: fileKey ", str, "SystemUI.ProviderUtil-->", "SystemUi--AirView");
                }
                if (Intrinsics.areEqual(str, QUICK_SETTINGS_TILE_KEY)) {
                    if (LogUtil.getNormalDebug() && LogUtil.isTagValid(LogUtil.TAG_COMMON)) {
                        Log.d("SystemUi--Common", "SystemUI.ProviderUtil-->downloadDataFromFile: QUICK_SETTINGS_TILE_KEY");
                    }
                    Util.saveStrToFile(context, QUICK_SETTINGS_TILE_NAME, dataFromProvider);
                    List<String> listFromLocalRomUpdateFile = getListFromLocalRomUpdateFile(context, QUICK_SETTINGS_TILE_KEY);
                    AppQsTileBlackList.setAppQuickSettingsBlackList(listFromLocalRomUpdateFile);
                    int i = QSTileHostHelper.FIRST_API_LEVEL;
                    String tileSettings = QsSettingsValueProxy.getTileSettings(context);
                    FlashlightControllerImpl$2$.ExternalSyntheticOutline0.m("check Black list, settingString=", tileSettings, LogUtil.TAG_QS, "QSTileHostHelper");
                    List defaultTileSpecs = tileSettings == null ? ((OplusQuickSettingsEx) DependencyEx.sDependency.getDependency(OplusQuickSettingsEx.class)).getQsTileHostHelperEx().getDefaultTileSpecs(context) : new ArrayList(Arrays.asList(tileSettings.split(OplusPreventModeData.SEPARATOR)));
                    boolean z = false;
                    for (String str2 : listFromLocalRomUpdateFile) {
                        if (defaultTileSpecs.contains(str2)) {
                            defaultTileSpecs.remove(str2);
                            z = true;
                        }
                    }
                    if (z) {
                        QsSettingsValueProxy.setTileSettings(context, TextUtils.join(OplusPreventModeData.SEPARATOR, defaultTileSpecs));
                    }
                } else {
                    KeyguardFeatureOption.INSTANCE.getClass();
                    if (KeyguardFeatureOption.isSupportOtherPictorial && Intrinsics.areEqual(str, PictorialRomUpdateUtils.KGD_PICTORIAL_CONFIG_LIST)) {
                        Util.saveStrToFile(context, PictorialRomUpdateUtils.KGD_PICTORIAL_CONFIG_NAME, dataFromProvider);
                        if (LogUtil.getNormalDebug() && LogUtil.isTagValid(LogUtil.TAG_AV)) {
                            Log.d("SystemUi--AirView", "SystemUI.ProviderUtil-->downloadDataFromFile: KGD_PICTORIAL_CONFIG_LIST");
                        }
                        PictorialRomUpdateUtils.parsePictorialConfiguration(context, dataFromProvider);
                    }
                }
            }
            SharedPreferences.Editor editorEdit = context.getSharedPreferences("systembar_config_version", 0).edit();
            editorEdit.putString("romupdate_version_" + str, string);
            editorEdit.commit();
        }
    }

    public static final synchronized List<String> getListFromLocalRomUpdateFile(Context context, String str) {
        ArrayList arrayList = new ArrayList();
        if (!Intrinsics.areEqual(str, QUICK_SETTINGS_TILE_KEY)) {
            return arrayList;
        }
        File file = new File(context.getFilesDir(), QUICK_SETTINGS_TILE_NAME);
        if (!file.exists()) {
            return AppQsTileBlackList.getDefaultQuickSettingsBlackList();
        }
        try {
            getServicesList(arrayList, new InputStreamReader(new FileInputStream(file), Charset.defaultCharset()));
            return arrayList;
        } catch (FileNotFoundException unused) {
            LogUtil.e$default(8, LogUtil.TAG_COMMON, TAG, "Couldn't find or open alarm_filter_packages file " + file);
            return null;
        }
    }

    public static final void getServicesList(List list, InputStreamReader inputStreamReader) {
        String message;
        StringBuilder sb;
        try {
            try {
                XmlPullParser xmlPullParserNewPullParser = XmlPullParserFactory.newInstance().newPullParser();
                xmlPullParserNewPullParser.setInput(inputStreamReader);
                for (int eventType = xmlPullParserNewPullParser.getEventType(); eventType != 1; eventType = xmlPullParserNewPullParser.next()) {
                    if (eventType == 2) {
                        if (Intrinsics.areEqual(xmlPullParserNewPullParser.getName(), LIST_ENABLE_TOGGLE)) {
                            String strNextText = xmlPullParserNewPullParser.nextText();
                            if (LogUtil.getNormalDebug() && LogUtil.isTagValid(LogUtil.TAG_COMMON)) {
                                String str = LogUtil.TAG + LogUtil.TAG_COMMON;
                                StringBuilder sb2 = new StringBuilder();
                                sb2.append(TAG);
                                sb2.append(LogUtil.SEPARATOR);
                                sb2.append("isOpen = " + strNextText);
                                Log.d(str, sb2.toString());
                            }
                        } else if (Intrinsics.areEqual(xmlPullParserNewPullParser.getName(), "pkg") || Intrinsics.areEqual(xmlPullParserNewPullParser.getName(), DISABLE_STAT_ANIM_NAME)) {
                            xmlPullParserNewPullParser.next();
                            String attributeValue = xmlPullParserNewPullParser.getAttributeValue(null, SeedlingMediaDataHandleUtils.PARAM_SLIDER_VALUE);
                            if (LogUtil.getNormalDebug() && LogUtil.isTagValid(LogUtil.TAG_COMMON)) {
                                String str2 = LogUtil.TAG + LogUtil.TAG_COMMON;
                                StringBuilder sb3 = new StringBuilder();
                                sb3.append(TAG);
                                sb3.append(LogUtil.SEPARATOR);
                                sb3.append("getListFromLocalRomUpdateFile_value = " + attributeValue);
                                Log.d(str2, sb3.toString());
                            }
                            if (attributeValue != null && !attributeValue.equals("")) {
                                list.add(attributeValue);
                            }
                        }
                    }
                }
                try {
                    inputStreamReader.close();
                } catch (IOException e) {
                    e = e;
                    message = e.getMessage();
                    sb = new StringBuilder("Got execption close permReader.");
                    sb.append(message);
                    LogUtil.e(LogUtil.TAG_COMMON, TAG, sb.toString(), e);
                }
            } catch (Throwable th) {
                try {
                    inputStreamReader.close();
                } catch (IOException e2) {
                    LogUtil.e(LogUtil.TAG_COMMON, TAG, "Got execption close permReader." + e2.getMessage(), e2);
                }
                throw th;
            }
        } catch (XmlPullParserException e3) {
            LogUtil.e(LogUtil.TAG_COMMON, TAG, "Got execption parsing permissions." + e3.getMessage(), e3);
            try {
                inputStreamReader.close();
            } catch (IOException e4) {
                e = e4;
                message = e.getMessage();
                sb = new StringBuilder("Got execption close permReader.");
                sb.append(message);
                LogUtil.e(LogUtil.TAG_COMMON, TAG, sb.toString(), e);
            }
        }
    }
}
