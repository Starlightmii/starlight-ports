package com.oplus.systemui.media.seedling.rus;

import android.content.ContentProviderClient;
import android.content.ContentResolver;
import android.content.Context;
import android.content.SharedPreferences;
import android.database.Cursor;
import android.net.Uri;
import android.os.Handler;
import android.os.RemoteException;
import android.provider.Settings;
import com.android.systemui.DependencyEx;
import com.android.systemui.media.controls.models.OplusMediaDataModel;
import com.oplus.systemui.media.controls.pipeline.MediaActionPrioritySelector;
import com.oplus.systemui.media.controls.pipeline.MediaActionPrioritySelectorImpl;
import com.oplus.systemui.media.controls.pipeline.OplusActionConfig;
import com.oplus.systemui.media.seedling.OplusMediaSeedlingController$mediaDataUpdateListener$1$$ExternalSyntheticOutline0;
import com.oplus.systemui.preventmode.OplusPreventModeData;
import com.oplus.systemui.qs.slider.detail.data.EarphoneController;
import com.oplus.systemui.receiver.RomUpdateReceiver;
import com.oplusos.systemui.common.receiver.IReceiverListener;
import com.oplusos.systemui.common.util.SystemUIThreadUtil;
import com.oplusos.systemui.notification.logger.OplusNotificationLogger;
import java.io.StringReader;
import java.lang.invoke.VarHandle;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.HashSet;
import java.util.Iterator;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;
import java.util.Set;
import kotlin.collections.CollectionsKt__CollectionsKt;
import kotlin.collections.CollectionsKt__MutableCollectionsKt;
import kotlin.collections.MapsKt__MapsKt;
import kotlin.jvm.internal.Intrinsics;
import kotlin.text.StringsKt__StringsJVMKt;
import kotlin.text.StringsKt__StringsKt;
import org.json.JSONException;
import org.json.JSONObject;
import org.xmlpull.v1.XmlPullParser;
import org.xmlpull.v1.XmlPullParserFactory;

/* JADX INFO: compiled from: go/retraceme fd74bf9514dd0739b92d67a6d48a51eb10aaf8df760d83496b8dcfcf83e5f698 */
/* JADX INFO: loaded from: /home/starlight/Projects/Starlight Ports/.build-tmp/classes4.dex */
public final class OplusMediaRusUpdateManager {
    private static final String ACTION_ROM_UPDATE_CONFIG_SUCCESS = "oplus.intent.action.ROM_UPDATE_CONFIG_SUCCESS";
    private static final String COLUMN_NAME_VERSION = "version";
    private static final String COLUMN_NAME_XML = "xml";
    private static final String COMPONENT_SAFE_PERMISSION = "oplus.permission.OPLUS_COMPONENT_SAFE";
    private static final String DEFAULT_XML_VALUE = "";
    private static final String KEY_MEDIA_SEEDLING_SPECTRUM_DISABLE_ANIM = "media_fluid_disable_anim";
    public static final String OPLUS_MEDIA_CONTROLLER_CONFIG = "app_systemui_oplus_media_controller_config";
    private static final String OPLUS_MEDIA_CONTROLLER_CONFIG_SP = "oplus_media_controller_config_sp";
    private static final String ROM_UPDATE_CONFIG_LIST = "ROM_UPDATE_CONFIG_LIST";
    private static final String RUS_ACTION_INDEX_KEY = "rus_action_index_key";
    private static final String RUS_ACTION_RULE_KEY = "rus_action_rule_key";
    private static final String RUS_ACTION_RULE_QS_KEY = "rus_action_rule_qs_key";
    private static final String RUS_BLACKLIST_KEY = "rus_blacklist_key";
    private static final String RUS_LOCATION_VERSION_KEY = "rus_location_version_key";
    private static final String RUS_LYRIC_ENABLE_KEY = "rus_lyric_enable_key";
    private static final String RUS_LYRIC_ENTRANCE_KEY = "rus_lyric_entrance_key";
    private static final String RUS_OPLUS_ACTION_CONFIG_KEY = "rus_oplus_action_config_key";
    private static final String RUS_WHITELIST_KEY = "rus_content_key";
    public static final String TAG = "OplusMediaRusUpdateManager";
    private static final String TAG_BLACKLIST = "excludeList";
    private static final String TAG_CONFIG = "config";
    private static final String TAG_PACKAGENAME = "packageName";
    private static final String TAG_POLICY = "policy";
    private static final String TAG_VERSION = "version";
    private static final String TAG_WHITELIST = "whitelist";
    private static volatile OplusMediaRusUpdateManager instance;
    private Handler mBgHandler = SystemUIThreadUtil.getWorkHandler();
    private final IReceiverListener mRomUpdateReceiver;
    public static final Companion Companion = new Companion();
    public static final Uri ROM_UPDATE_CONTENT_URI = Uri.parse("content://com.oplus.romupdate.provider.db/update_list");

    /* JADX INFO: compiled from: go/retraceme fd74bf9514dd0739b92d67a6d48a51eb10aaf8df760d83496b8dcfcf83e5f698 */
    public final class Companion {
        public final OplusMediaRusUpdateManager getInstance() {
            OplusMediaRusUpdateManager oplusMediaRusUpdateManager;
            OplusMediaRusUpdateManager oplusMediaRusUpdateManager2 = OplusMediaRusUpdateManager.instance;
            if (oplusMediaRusUpdateManager2 != null) {
                return oplusMediaRusUpdateManager2;
            }
            synchronized (this) {
                oplusMediaRusUpdateManager = OplusMediaRusUpdateManager.instance;
                if (oplusMediaRusUpdateManager == null) {
                    oplusMediaRusUpdateManager = new OplusMediaRusUpdateManager();
                    OplusMediaRusUpdateManager.instance = oplusMediaRusUpdateManager;
                }
            }
            return oplusMediaRusUpdateManager;
        }
    }

    public OplusMediaRusUpdateManager() {
        OplusMediaRusUpdateManager$mRomUpdateReceiver$1 oplusMediaRusUpdateManager$mRomUpdateReceiver$1 = new OplusMediaRusUpdateManager$mRomUpdateReceiver$1();
        oplusMediaRusUpdateManager$mRomUpdateReceiver$1.this$0 = this;
        VarHandle.storeStoreFence();
        this.mRomUpdateReceiver = oplusMediaRusUpdateManager$mRomUpdateReceiver$1;
    }

    public static final String access$getDataFromProvider(OplusMediaRusUpdateManager oplusMediaRusUpdateManager, Context context) {
        String[] strArr = {"version", COLUMN_NAME_XML};
        String[] strArr2 = {OPLUS_MEDIA_CONTROLLER_CONFIG};
        Cursor cursorQuery = null;
        try {
            try {
                ContentResolver contentResolver = context.getContentResolver();
                Uri uri = ROM_UPDATE_CONTENT_URI;
                ContentProviderClient contentProviderClientAcquireUnstableContentProviderClient = contentResolver.acquireUnstableContentProviderClient(uri);
                cursorQuery = contentProviderClientAcquireUnstableContentProviderClient != null ? contentProviderClientAcquireUnstableContentProviderClient.query(uri, strArr, "filtername=?", strArr2, null) : null;
                if (cursorQuery == null || cursorQuery.getCount() <= 0) {
                    OplusNotificationLogger.logMediaSeedling(TAG, "The Filtrate app cursor is null !!!");
                    if (cursorQuery != null) {
                        cursorQuery.close();
                        return DEFAULT_XML_VALUE;
                    }
                    return DEFAULT_XML_VALUE;
                }
                int columnIndex = cursorQuery.getColumnIndex(COLUMN_NAME_XML);
                cursorQuery.moveToNext();
                String string = cursorQuery.getString(columnIndex);
                cursorQuery.close();
                return string;
            } catch (RemoteException e) {
                OplusNotificationLogger.logMediaSeedling(TAG, "We can not get Filtrate app data from provider,because of " + e.getMessage());
                if (0 != 0) {
                    cursorQuery.close();
                }
            }
        } catch (Throwable th) {
            if (0 == 0) {
                throw th;
            }
            cursorQuery.close();
            throw th;
        }
    }

    /* JADX WARN: Code duplicated, block: B:125:0x023f  */
    /* JADX WARN: Code duplicated, block: B:128:0x0246  */
    /* JADX WARN: Code duplicated, block: B:129:0x0248  */
    /* JADX WARN: Code duplicated, block: B:132:0x0267  */
    /* JADX WARN: Code duplicated, block: B:135:0x026d  */
    /* JADX WARN: Code duplicated, block: B:161:? A[RETURN, SYNTHETIC] */
    public static final void access$parseAndPostApply(OplusMediaRusUpdateManager oplusMediaRusUpdateManager, Context context, int i, String str, String str2) throws Throwable {
        StringReader stringReader;
        MediaRusConfig mediaRusConfig;
        StringReader stringReader2;
        MediaRusConfig mediaRusConfig2;
        boolean z;
        StringReader stringReader3;
        HashSet hashSet;
        HashSet hashSet2;
        String str3;
        HashMap map;
        MediaRusConfig mediaRusConfig3;
        String str4;
        HashMap map2;
        HashSet hashSet3;
        HashSet hashSet4;
        MediaRusConfig mediaRusConfig4;
        String str5 = TAG;
        try {
            XmlPullParser xmlPullParserNewPullParser = XmlPullParserFactory.newInstance().newPullParser();
            stringReader2 = new StringReader(str);
            try {
                try {
                    xmlPullParserNewPullParser.setInput(stringReader2);
                    HashSet hashSet5 = new HashSet();
                    HashSet hashSet6 = new HashSet();
                    ArrayList arrayList = new ArrayList();
                    ArrayList arrayList2 = new ArrayList();
                    HashMap map3 = new HashMap();
                    HashMap map4 = new HashMap();
                    HashMap map5 = new HashMap();
                    HashMap map6 = new HashMap();
                    HashMap map7 = new HashMap();
                    HashMap map8 = new HashMap();
                    String str6 = DEFAULT_XML_VALUE;
                    int next = -1;
                    try {
                        try {
                            for (int i2 = 1; next != i2; i2 = 1) {
                                if (next != 2) {
                                    if (next != 3) {
                                        str5 = str5;
                                        stringReader3 = stringReader2;
                                        str4 = str6;
                                        hashSet3 = hashSet5;
                                        hashSet4 = hashSet6;
                                        map2 = map7;
                                    } else {
                                        str4 = str6;
                                        try {
                                            try {
                                                HashMap map9 = map3;
                                                HashMap map10 = map7;
                                                dealEndTag(xmlPullParserNewPullParser.getName(), hashSet5, hashSet6, arrayList, arrayList2, map9, map4, map5, map6, map10);
                                                map2 = map10;
                                                map3 = map9;
                                                stringReader3 = stringReader2;
                                                hashSet3 = hashSet5;
                                                hashSet4 = hashSet6;
                                            } catch (Exception e) {
                                                e = e;
                                                mediaRusConfig = null;
                                                OplusNotificationLogger.logMediaSeedling(str5, "parseXML exception: " + e.getMessage());
                                                if (stringReader2 != null) {
                                                    stringReader2.close();
                                                }
                                                mediaRusConfig2 = mediaRusConfig;
                                                if (mediaRusConfig2 != null) {
                                                    z = true;
                                                } else {
                                                    z = false;
                                                }
                                                OplusNotificationLogger.logMediaSeedling(str5, "parse " + str2 + ", hasUpdated=" + z);
                                                if (mediaRusConfig2 != null) {
                                                    applyConfig(mediaRusConfig2);
                                                }
                                            }
                                        } catch (Throwable th) {
                                            th = th;
                                            stringReader = stringReader2;
                                            if (stringReader != null) {
                                                stringReader.close();
                                            }
                                            throw th;
                                        }
                                    }
                                    mediaRusConfig4 = null;
                                    try {
                                        try {
                                            next = xmlPullParserNewPullParser.next();
                                            hashSet5 = hashSet3;
                                            map7 = map2;
                                            hashSet6 = hashSet4;
                                            stringReader2 = stringReader3;
                                            str5 = str5;
                                            str6 = str4;
                                        } catch (Throwable th2) {
                                            th = th2;
                                            stringReader = stringReader3;
                                            if (stringReader != null) {
                                                stringReader.close();
                                            }
                                            throw th;
                                        }
                                    } catch (Exception e2) {
                                        e = e2;
                                        mediaRusConfig = mediaRusConfig4;
                                        stringReader2 = stringReader3;
                                        str5 = str5;
                                        OplusNotificationLogger.logMediaSeedling(str5, "parseXML exception: " + e.getMessage());
                                        if (stringReader2 != null) {
                                            stringReader2.close();
                                        }
                                        mediaRusConfig2 = mediaRusConfig;
                                        if (mediaRusConfig2 != null) {
                                            z = true;
                                        } else {
                                            z = false;
                                        }
                                        OplusNotificationLogger.logMediaSeedling(str5, "parse " + str2 + ", hasUpdated=" + z);
                                        if (mediaRusConfig2 != null) {
                                            applyConfig(mediaRusConfig2);
                                        }
                                    }
                                } else {
                                    str4 = str6;
                                    map2 = map7;
                                    try {
                                        String name = xmlPullParserNewPullParser.getName();
                                        if (name != null) {
                                            stringReader3 = stringReader2;
                                            hashSet3 = hashSet5;
                                            switch (name.hashCode()) {
                                                case -1653850041:
                                                    str5 = str5;
                                                    hashSet4 = hashSet6;
                                                    mediaRusConfig4 = null;
                                                    if (name.equals(TAG_WHITELIST)) {
                                                        str4 = TAG_WHITELIST;
                                                    }
                                                    next = xmlPullParserNewPullParser.next();
                                                    hashSet5 = hashSet3;
                                                    map7 = map2;
                                                    hashSet6 = hashSet4;
                                                    stringReader2 = stringReader3;
                                                    str5 = str5;
                                                    str6 = str4;
                                                    break;
                                                case -1354792126:
                                                    str5 = str5;
                                                    hashSet4 = hashSet6;
                                                    mediaRusConfig4 = null;
                                                    if (name.equals(TAG_CONFIG)) {
                                                        saveDisableanimFromXml(context, xmlPullParserNewPullParser);
                                                    }
                                                    next = xmlPullParserNewPullParser.next();
                                                    hashSet5 = hashSet3;
                                                    map7 = map2;
                                                    hashSet6 = hashSet4;
                                                    stringReader2 = stringReader3;
                                                    str5 = str5;
                                                    str6 = str4;
                                                    break;
                                                case -982670030:
                                                    str5 = str5;
                                                    hashSet4 = hashSet6;
                                                    mediaRusConfig4 = null;
                                                    if (name.equals(TAG_POLICY)) {
                                                        str4 = TAG_POLICY;
                                                    }
                                                    next = xmlPullParserNewPullParser.next();
                                                    hashSet5 = hashSet3;
                                                    map7 = map2;
                                                    hashSet6 = hashSet4;
                                                    stringReader2 = stringReader3;
                                                    str5 = str5;
                                                    str6 = str4;
                                                    break;
                                                case 908759025:
                                                    hashSet4 = hashSet6;
                                                    try {
                                                        if (name.equals("packageName")) {
                                                            int iHashCode = str4.hashCode();
                                                            str5 = str5;
                                                            if (iHashCode == -1653850041) {
                                                                try {
                                                                    if (str4.equals(TAG_WHITELIST)) {
                                                                        mediaRusConfig4 = null;
                                                                        arrayList.add(xmlPullParserNewPullParser.getAttributeValue(null, EarphoneController.COLUMN_NAME));
                                                                    }
                                                                    next = xmlPullParserNewPullParser.next();
                                                                    hashSet5 = hashSet3;
                                                                    map7 = map2;
                                                                    hashSet6 = hashSet4;
                                                                    stringReader2 = stringReader3;
                                                                    str5 = str5;
                                                                    str6 = str4;
                                                                } catch (Exception e3) {
                                                                    e = e3;
                                                                    mediaRusConfig4 = null;
                                                                    mediaRusConfig = mediaRusConfig4;
                                                                    stringReader2 = stringReader3;
                                                                    str5 = str5;
                                                                    OplusNotificationLogger.logMediaSeedling(str5, "parseXML exception: " + e.getMessage());
                                                                    if (stringReader2 != null) {
                                                                        stringReader2.close();
                                                                    }
                                                                    mediaRusConfig2 = mediaRusConfig;
                                                                    if (mediaRusConfig2 != null) {
                                                                        z = true;
                                                                    } else {
                                                                        z = false;
                                                                    }
                                                                    OplusNotificationLogger.logMediaSeedling(str5, "parse " + str2 + ", hasUpdated=" + z);
                                                                    if (mediaRusConfig2 != null) {
                                                                        applyConfig(mediaRusConfig2);
                                                                    }
                                                                }
                                                                break;
                                                            } else if (iHashCode != -982670030) {
                                                                if (iHashCode == 1202619800) {
                                                                    try {
                                                                        if (str4.equals(TAG_BLACKLIST)) {
                                                                            arrayList2.add(xmlPullParserNewPullParser.getAttributeValue(null, EarphoneController.COLUMN_NAME));
                                                                        }
                                                                    } catch (Exception e4) {
                                                                        e = e4;
                                                                        stringReader2 = stringReader3;
                                                                        str5 = str5;
                                                                        mediaRusConfig = null;
                                                                        OplusNotificationLogger.logMediaSeedling(str5, "parseXML exception: " + e.getMessage());
                                                                        if (stringReader2 != null) {
                                                                            stringReader2.close();
                                                                        }
                                                                        mediaRusConfig2 = mediaRusConfig;
                                                                        if (mediaRusConfig2 != null) {
                                                                            z = true;
                                                                        } else {
                                                                            z = false;
                                                                        }
                                                                        OplusNotificationLogger.logMediaSeedling(str5, "parse " + str2 + ", hasUpdated=" + z);
                                                                        if (mediaRusConfig2 != null) {
                                                                            applyConfig(mediaRusConfig2);
                                                                        }
                                                                    }
                                                                }
                                                            } else if (str4.equals(TAG_POLICY)) {
                                                                String attributeValue = xmlPullParserNewPullParser.getAttributeValue(null, EarphoneController.COLUMN_NAME);
                                                                String attributeValue2 = xmlPullParserNewPullParser.getAttributeValue(null, "actionPriority");
                                                                if (attributeValue2 != null) {
                                                                    map3.put(attributeValue, attributeValue2);
                                                                }
                                                                String attributeValue3 = xmlPullParserNewPullParser.getAttributeValue(null, "actionPriorityQs");
                                                                if (attributeValue3 != null) {
                                                                    map4.put(attributeValue, attributeValue3);
                                                                }
                                                                String attributeValue4 = xmlPullParserNewPullParser.getAttributeValue(null, "actionIndex");
                                                                if (attributeValue4 != null) {
                                                                    map5.put(attributeValue, attributeValue4);
                                                                }
                                                                String attributeValue5 = xmlPullParserNewPullParser.getAttributeValue(null, "lyricEntrance");
                                                                if (attributeValue5 != null) {
                                                                    map6.put(attributeValue, attributeValue5);
                                                                }
                                                                String attributeValue6 = xmlPullParserNewPullParser.getAttributeValue(null, "lyricEnable");
                                                                if (attributeValue6 != null) {
                                                                    map2.put(attributeValue, attributeValue6);
                                                                }
                                                                String attributeValue7 = xmlPullParserNewPullParser.getAttributeValue(null, OplusActionConfig.METADATA_KEY_OPLUS_ACTION_CONFIG);
                                                                if (attributeValue7 != null) {
                                                                    map8.put(attributeValue, attributeValue7);
                                                                }
                                                            }
                                                            mediaRusConfig4 = null;
                                                            next = xmlPullParserNewPullParser.next();
                                                            hashSet5 = hashSet3;
                                                            map7 = map2;
                                                            hashSet6 = hashSet4;
                                                            stringReader2 = stringReader3;
                                                            str5 = str5;
                                                            str6 = str4;
                                                        } else {
                                                            str5 = str5;
                                                            mediaRusConfig4 = null;
                                                            next = xmlPullParserNewPullParser.next();
                                                            hashSet5 = hashSet3;
                                                            map7 = map2;
                                                            hashSet6 = hashSet4;
                                                            stringReader2 = stringReader3;
                                                            str5 = str5;
                                                            str6 = str4;
                                                        }
                                                    } catch (Exception e5) {
                                                        e = e5;
                                                        mediaRusConfig = null;
                                                        stringReader2 = stringReader3;
                                                        OplusNotificationLogger.logMediaSeedling(str5, "parseXML exception: " + e.getMessage());
                                                        if (stringReader2 != null) {
                                                            stringReader2.close();
                                                        }
                                                        mediaRusConfig2 = mediaRusConfig;
                                                        if (mediaRusConfig2 != null) {
                                                            z = true;
                                                        } else {
                                                            z = false;
                                                        }
                                                        OplusNotificationLogger.logMediaSeedling(str5, "parse " + str2 + ", hasUpdated=" + z);
                                                        if (mediaRusConfig2 != null) {
                                                            applyConfig(mediaRusConfig2);
                                                        }
                                                    }
                                                    break;
                                                case 1202619800:
                                                    try {
                                                        if (name.equals(TAG_BLACKLIST)) {
                                                            str4 = TAG_BLACKLIST;
                                                            break;
                                                        }
                                                    } catch (Exception e6) {
                                                        e = e6;
                                                        stringReader2 = stringReader3;
                                                        mediaRusConfig = null;
                                                        OplusNotificationLogger.logMediaSeedling(str5, "parseXML exception: " + e.getMessage());
                                                        if (stringReader2 != null) {
                                                            stringReader2.close();
                                                        }
                                                        mediaRusConfig2 = mediaRusConfig;
                                                        if (mediaRusConfig2 != null) {
                                                            z = true;
                                                        } else {
                                                            z = false;
                                                        }
                                                        OplusNotificationLogger.logMediaSeedling(str5, "parse " + str2 + ", hasUpdated=" + z);
                                                        if (mediaRusConfig2 != null) {
                                                            applyConfig(mediaRusConfig2);
                                                        }
                                                    }
                                                default:
                                                    break;
                                            }
                                        } else {
                                            stringReader3 = stringReader2;
                                            hashSet3 = hashSet5;
                                        }
                                        hashSet4 = hashSet6;
                                        mediaRusConfig4 = null;
                                        next = xmlPullParserNewPullParser.next();
                                        hashSet5 = hashSet3;
                                        map7 = map2;
                                        hashSet6 = hashSet4;
                                        stringReader2 = stringReader3;
                                        str5 = str5;
                                        str6 = str4;
                                    } catch (Exception e7) {
                                        e = e7;
                                        mediaRusConfig = null;
                                    }
                                }
                                OplusNotificationLogger.logMediaSeedling(str5, "parseXML exception: " + e.getMessage());
                                if (stringReader2 != null) {
                                    stringReader2.close();
                                }
                                mediaRusConfig2 = mediaRusConfig;
                                if (mediaRusConfig2 != null) {
                                    z = true;
                                } else {
                                    z = false;
                                }
                                OplusNotificationLogger.logMediaSeedling(str5, "parse " + str2 + ", hasUpdated=" + z);
                                if (mediaRusConfig2 != null) {
                                    applyConfig(mediaRusConfig2);
                                }
                            }
                            OplusNotificationLogger.logMediaSeedling(str5, "parseSaveXmlValue success");
                            stringReader3.close();
                            mediaRusConfig2 = mediaRusConfig3;
                        } catch (Exception e8) {
                            e = e8;
                            stringReader2 = stringReader3;
                            OplusNotificationLogger.logMediaSeedling(str5, "parseXML exception: " + e.getMessage());
                            if (stringReader2 != null) {
                                stringReader2.close();
                            }
                            mediaRusConfig2 = mediaRusConfig;
                        }
                        saveListToSP(context, hashSet, hashSet2, map, map4, map5, map6, map7, map8, i);
                        mediaRusConfig3 = new MediaRusConfig(i, parsePackageListFromStringSet(hashSet), parsePackageListFromStringSet(hashSet2), map, map4, map5, map6, map7, map8);
                        str5 = str3;
                    } catch (Exception e9) {
                        e = e9;
                        str5 = str3;
                    }
                    hashSet = hashSet6;
                    hashSet2 = hashSet5;
                    str3 = str5;
                    stringReader3 = stringReader2;
                    map = map3;
                    mediaRusConfig = null;
                } catch (Throwable th3) {
                    th = th3;
                    stringReader3 = stringReader2;
                }
            } catch (Exception e10) {
                e = e10;
            }
        } catch (Exception e11) {
            e = e11;
            mediaRusConfig = null;
            stringReader2 = null;
        } catch (Throwable th4) {
            th = th4;
            stringReader = null;
        }
        if (mediaRusConfig2 != null) {
            z = true;
        } else {
            z = false;
        }
        OplusNotificationLogger.logMediaSeedling(str5, "parse " + str2 + ", hasUpdated=" + z);
        if (mediaRusConfig2 != null) {
            applyConfig(mediaRusConfig2);
        }
    }

    public static final int access$parseVersion(OplusMediaRusUpdateManager oplusMediaRusUpdateManager, String str) throws Throwable {
        int i = 0;
        OplusNotificationLogger.logMediaSeedling(TAG, "parseVersion " + (str == null || str.length() == 0));
        if (str == null || str.length() == 0) {
            return 0;
        }
        StringReader stringReader = null;
        try {
            try {
                XmlPullParser xmlPullParserNewPullParser = XmlPullParserFactory.newInstance().newPullParser();
                StringReader stringReader2 = new StringReader(str);
                try {
                    xmlPullParserNewPullParser.setInput(stringReader2);
                    for (int next = -1; next != 1; next = xmlPullParserNewPullParser.next()) {
                        if (next == 2 && Intrinsics.areEqual(xmlPullParserNewPullParser.getName(), "version")) {
                            i = Integer.parseInt(xmlPullParserNewPullParser.nextText());
                            break;
                        }
                    }
                    stringReader2.close();
                } catch (Exception e) {
                    e = e;
                    stringReader = stringReader2;
                    OplusNotificationLogger.logMediaSeedling(TAG, "parseXML exception: " + e.getMessage());
                    if (stringReader != null) {
                        stringReader.close();
                    }
                } catch (Throwable th) {
                    th = th;
                    stringReader = stringReader2;
                    if (stringReader != null) {
                        stringReader.close();
                    }
                    throw th;
                }
            } catch (Exception e2) {
                e = e2;
            }
            OplusNotificationLogger.logMediaSeedling(TAG, "parseVersion result: " + i);
            return i;
        } catch (Throwable th2) {
            th = th2;
        }
    }

    public static final void access$updateRusData(OplusMediaRusUpdateManager oplusMediaRusUpdateManager, Context context, List list) {
        Handler handler = oplusMediaRusUpdateManager.mBgHandler;
        if (handler != null) {
            OplusMediaRusUpdateManager$updateRusData$1 oplusMediaRusUpdateManager$updateRusData$1 = new OplusMediaRusUpdateManager$updateRusData$1();
            oplusMediaRusUpdateManager$updateRusData$1.$configList = list;
            oplusMediaRusUpdateManager$updateRusData$1.this$0 = oplusMediaRusUpdateManager;
            oplusMediaRusUpdateManager$updateRusData$1.$context = context;
            VarHandle.storeStoreFence();
            handler.post(oplusMediaRusUpdateManager$updateRusData$1);
        }
    }

    public static void applyConfig(MediaRusConfig mediaRusConfig) {
        if (!mediaRusConfig.getBlackList().isEmpty()) {
            ((OplusMediaDataModel) DependencyEx.sDependency.getDependency(OplusMediaDataModel.class)).setMediaControlBlackList(mediaRusConfig.getBlackList());
        }
        ((MediaActionPrioritySelectorImpl) MediaActionPrioritySelector.Companion.getInstance()).updatePkgActionsRule(mediaRusConfig.getPkgRuleMap(), mediaRusConfig.getPkgRuleQsMap(), mediaRusConfig.getActionIndexMap(), mediaRusConfig.getLyricEntranceMap(), mediaRusConfig.getActionConfigMap(), mediaRusConfig.getLyricEnableMap());
        OplusNotificationLogger.logMediaSeedling(TAG, "applyConfig version=" + mediaRusConfig.getVersion());
    }

    public static void dealEndTag(String str, Set set, Set set2, List list, List list2, Map map, Map map2, Map map3, Map map4, Map map5) {
        if (TAG_BLACKLIST.equals(str) && !list2.isEmpty()) {
            set.add(list2.toString());
            OplusNotificationLogger.logMediaSeedling(TAG, "parseSaveXmlValue blackList: " + list2);
            return;
        }
        if (TAG_WHITELIST.equals(str) && !list.isEmpty()) {
            OplusNotificationLogger.logMediaSeedling(TAG, "parseSaveXmlValue whiteList: " + list);
            set2.add(list.toString());
            return;
        }
        if (TAG_POLICY.equals(str)) {
            OplusNotificationLogger.logMediaSeedling(TAG, "parseSaveXmlValue pkgRuleMap: " + map + ", pkgRuleQsMap: " + map2 + " pkgIndexMap: " + map3 + " pkgLyricEntranceMap: " + map4 + " pkgLyricEnableMap:" + map5);
        }
    }

    public static Map getMapJSONFromSP(SharedPreferences sharedPreferences, String str) {
        String string = sharedPreferences.getString(str, DEFAULT_XML_VALUE);
        if (string == null) {
            return MapsKt__MapsKt.emptyMap();
        }
        try {
            JSONObject jSONObject = new JSONObject(string);
            LinkedHashMap linkedHashMap = new LinkedHashMap();
            Iterator<String> itKeys = jSONObject.keys();
            while (itKeys.hasNext()) {
                String next = itKeys.next();
                linkedHashMap.put(next, jSONObject.getString(next));
            }
            return linkedHashMap;
        } catch (JSONException e) {
            OplusNotificationLogger.logMediaSeedling(TAG, "getMapJSONFromSP error key:" + str + ": " + e.getMessage());
            return MapsKt__MapsKt.emptyMap();
        }
    }

    public static List parsePackageListFromStringSet(Set set) {
        int i;
        if (set == null) {
            return CollectionsKt__CollectionsKt.emptyList();
        }
        ArrayList arrayList = new ArrayList();
        Iterator it = set.iterator();
        while (true) {
            i = 0;
            if (!it.hasNext()) {
                break;
            }
            CollectionsKt__MutableCollectionsKt.addAll(arrayList, StringsKt__StringsKt.split$default(StringsKt__StringsJVMKt.replace(StringsKt__StringsJVMKt.replace(StringsKt__StringsJVMKt.replace((String) it.next(), "[", DEFAULT_XML_VALUE, false), "]", DEFAULT_XML_VALUE, false), " ", DEFAULT_XML_VALUE, false), new String[]{OplusPreventModeData.SEPARATOR}, 0, 6));
        }
        ArrayList arrayList2 = new ArrayList();
        int size = arrayList.size();
        while (i < size) {
            Object obj = arrayList.get(i);
            i++;
            if (((String) obj).length() > 0) {
                arrayList2.add(obj);
            }
        }
        return arrayList2;
    }

    public static void saveDisableanimFromXml(Context context, XmlPullParser xmlPullParser) {
        int i;
        String attributeValue = xmlPullParser.getAttributeValue(null, "disableAnim");
        OplusMediaSeedlingController$mediaDataUpdateListener$1$$ExternalSyntheticOutline0.m("disableAnim: ", attributeValue, TAG);
        try {
            attributeValue.getClass();
            i = Integer.parseInt(attributeValue);
        } catch (NumberFormatException e) {
            OplusMediaSeedlingController$mediaDataUpdateListener$1$$ExternalSyntheticOutline0.m("parse config fail ", e.getMessage(), TAG);
            i = 0;
        }
        Settings.Global.putInt(context.getContentResolver(), KEY_MEDIA_SEEDLING_SPECTRUM_DISABLE_ANIM, i);
    }

    public static void saveListToSP(Context context, Set set, Set set2, Map map, Map map2, Map map3, Map map4, Map map5, Map map6, int i) {
        SharedPreferences.Editor editorEdit = context.getSharedPreferences(OPLUS_MEDIA_CONTROLLER_CONFIG_SP, 0).edit();
        editorEdit.putStringSet(RUS_WHITELIST_KEY, set);
        editorEdit.putStringSet(RUS_BLACKLIST_KEY, set2);
        editorEdit.putString(RUS_ACTION_RULE_KEY, new JSONObject(map).toString());
        editorEdit.putString(RUS_ACTION_RULE_QS_KEY, new JSONObject(map2).toString());
        editorEdit.putString(RUS_ACTION_INDEX_KEY, new JSONObject(map3).toString());
        editorEdit.putString(RUS_LYRIC_ENTRANCE_KEY, new JSONObject(map4).toString());
        editorEdit.putString(RUS_LYRIC_ENABLE_KEY, new JSONObject(map5).toString());
        editorEdit.putString(RUS_OPLUS_ACTION_CONFIG_KEY, new JSONObject(map6).toString());
        editorEdit.putInt(RUS_LOCATION_VERSION_KEY, i);
        editorEdit.apply();
    }

    public final void initWhiteAndBlackListData(Context context) {
        OplusNotificationLogger.logMediaSeedling(TAG, "initWhiteListData");
        Handler handler = this.mBgHandler;
        if (handler != null) {
            OplusMediaRusUpdateManager$checkAndInitLocalData$1 oplusMediaRusUpdateManager$checkAndInitLocalData$1 = new OplusMediaRusUpdateManager$checkAndInitLocalData$1();
            oplusMediaRusUpdateManager$checkAndInitLocalData$1.this$0 = this;
            oplusMediaRusUpdateManager$checkAndInitLocalData$1.$context = context;
            VarHandle.storeStoreFence();
            handler.post(oplusMediaRusUpdateManager$checkAndInitLocalData$1);
        }
        RomUpdateReceiver companion = RomUpdateReceiver.Companion.getInstance();
        if (companion != null) {
            companion.addListener(this.mRomUpdateReceiver);
        }
        OplusNotificationLogger.logMediaSeedling(TAG, "the rusBroadcastReceiver Registered");
    }
}
