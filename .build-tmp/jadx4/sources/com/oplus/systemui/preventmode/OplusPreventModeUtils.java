package com.oplus.systemui.preventmode;

import android.content.Context;
import android.content.Intent;
import android.content.pm.ActivityInfo;
import android.content.pm.ResolveInfo;
import android.database.Cursor;
import android.text.TextUtils;
import android.util.Xml;
import com.android.keyguard.KeyguardUpdateMonitor;
import com.oplus.transition.OplusAnimationController;
import com.oplusos.keyguard.utils.KgdLogUtil;
import com.oplusos.systemui.common.utils.Util;
import java.io.IOException;
import java.io.StringReader;
import java.util.ArrayList;
import org.xmlpull.v1.XmlPullParser;
import org.xmlpull.v1.XmlPullParserException;

/* JADX INFO: compiled from: go/retraceme fd74bf9514dd0739b92d67a6d48a51eb10aaf8df760d83496b8dcfcf83e5f698 */
/* JADX INFO: loaded from: /home/starlight/Projects/Starlight Ports/.build-tmp/classes4.dex */
public final class OplusPreventModeUtils {
    public static final OplusPreventModeUtils INSTANCE = new OplusPreventModeUtils();
    public static final String PREVENT_MODE_SETTINGS_TILE_KEY = "sys_prevent_mode_settings";
    private static final String TAG = "OplusPreventModeUtils";
    private static final String TAG_PACKAGE = "package";
    private static final String TAG_PACKAGE_ATTRIBUTE = "name";
    private static final String TAG_SWITCH = "switch";
    private static final String TAG_SWITCH_ATTRIBUTE = "on";
    private static final String TAG_VERSION = "version";

    /* JADX WARN: Code duplicated, block: B:124:? A[RETURN, SYNTHETIC] */
    /* JADX WARN: Code duplicated, block: B:127:? A[SYNTHETIC] */
    /* JADX WARN: Code duplicated, block: B:15:0x0049  */
    /* JADX WARN: Code duplicated, block: B:26:0x006b A[PHI: r13
      0x006b: PHI (r13v4 android.database.Cursor) = (r13v3 android.database.Cursor), (r13v5 android.database.Cursor) binds: [B:28:0x007e, B:25:0x0069] A[DONT_GENERATE, DONT_INLINE]] */
    /* JADX WARN: Code duplicated, block: B:88:0x0145 A[PHI: r4 r5 r9
      0x0145: PHI (r4v7 java.io.StringReader) = (r4v5 java.io.StringReader), (r4v8 java.io.StringReader) binds: [B:90:0x015b, B:87:0x0143] A[DONT_GENERATE, DONT_INLINE]
      0x0145: PHI (r5v3 long) = (r5v1 long), (r5v4 long) binds: [B:90:0x015b, B:87:0x0143] A[DONT_GENERATE, DONT_INLINE]
      0x0145: PHI (r9v4 boolean) = (r9v2 boolean), (r9v5 boolean) binds: [B:90:0x015b, B:87:0x0143] A[DONT_GENERATE, DONT_INLINE]] */
    /* JADX WARN: Code duplicated, block: B:93:0x0160  */
    /* JADX WARN: Code duplicated, block: B:99:0x0172  */
    /* JADX WARN: Multi-variable type inference failed */
    /* JADX WARN: Type inference failed for: r4v0 */
    /* JADX WARN: Type inference failed for: r4v1, types: [android.database.Cursor] */
    /* JADX WARN: Type inference failed for: r4v2 */
    public static final void downloadPreventModeDataFromFile(Context context, String str) throws Throwable {
        Throwable th;
        Cursor cursorQuery;
        String string;
        boolean z;
        Throwable th2;
        ?? r4 = 0;
        stringReader = null;
        stringReader = null;
        stringReader = null;
        StringReader stringReader = null;
        try {
            try {
                cursorQuery = context.getContentResolver().query(Util.ROM_UPDATE_CONTENT_URI, new String[]{"xml"}, "filtername=\"" + str + "\"", null, null);
                if (cursorQuery != null) {
                    try {
                        if (cursorQuery.getCount() > 0) {
                            int columnIndex = cursorQuery.getColumnIndex("xml");
                            cursorQuery.moveToNext();
                            string = cursorQuery.getString(columnIndex);
                        } else {
                            string = null;
                        }
                    } catch (IOException e) {
                        e = e;
                        KgdLogUtil.e(TAG, "queryDataFromUpdateProvider: e=" + e);
                        if (cursorQuery != null) {
                            cursorQuery.close();
                        }
                        string = null;
                    } catch (XmlPullParserException e2) {
                        e = e2;
                        KgdLogUtil.e(TAG, "queryDataFromUpdateProvider: e=" + e);
                        if (cursorQuery != null) {
                            cursorQuery.close();
                        }
                        string = null;
                    }
                } else {
                    string = null;
                }
                if (cursorQuery != null) {
                    cursorQuery.close();
                }
            } catch (Throwable th3) {
                th = th3;
                r4 = str;
                if (r4 != 0) {
                    throw th;
                }
                r4.close();
                throw th;
            }
        } catch (IOException e3) {
            e = e3;
            cursorQuery = null;
        } catch (XmlPullParserException e4) {
            e = e4;
            cursorQuery = null;
        } catch (Throwable th4) {
            th = th4;
            if (r4 != 0) {
                throw th;
            }
            r4.close();
            throw th;
        }
        if (TextUtils.isEmpty(string)) {
            return;
        }
        ArrayList<String> arrayList = new ArrayList<>();
        boolean z2 = true;
        long j = 0;
        try {
            try {
                XmlPullParser xmlPullParserNewPullParser = Xml.newPullParser();
                StringReader stringReader2 = new StringReader(string);
                try {
                    try {
                        xmlPullParserNewPullParser.setInput(stringReader2);
                        z = true;
                        for (int eventType = xmlPullParserNewPullParser.getEventType(); eventType != 1; eventType = xmlPullParserNewPullParser.next()) {
                            if (eventType == 2) {
                                try {
                                    String name = xmlPullParserNewPullParser.getName();
                                    if (name != null) {
                                        int iHashCode = name.hashCode();
                                        if (iHashCode != -889473228) {
                                            if (iHashCode != -807062458) {
                                                if (iHashCode == 351608024 && name.equals(TAG_VERSION)) {
                                                    j = Long.parseLong(xmlPullParserNewPullParser.nextText());
                                                }
                                            } else if (name.equals(TAG_PACKAGE)) {
                                                String attributeValue = xmlPullParserNewPullParser.getAttributeValue(null, "name");
                                                if (!TextUtils.isEmpty(attributeValue)) {
                                                    arrayList.add(attributeValue);
                                                }
                                            }
                                        } else if (name.equals(TAG_SWITCH)) {
                                            String attributeValue2 = xmlPullParserNewPullParser.getAttributeValue(null, TAG_SWITCH_ATTRIBUTE);
                                            z = attributeValue2 != null ? Boolean.parseBoolean(attributeValue2) : true;
                                        }
                                    }
                                } catch (IOException e5) {
                                    e = e5;
                                    stringReader = stringReader2;
                                    KgdLogUtil.e(TAG, "parserXmlAndSave: e=" + e);
                                    if (stringReader != null) {
                                        stringReader.close();
                                    }
                                    if (z2) {
                                    }
                                    OplusPreventModeData.Companion.getInstance(context).savePreventModeData(j, z, arrayList);
                                } catch (XmlPullParserException e6) {
                                    e = e6;
                                    stringReader = stringReader2;
                                    KgdLogUtil.e(TAG, "parserXmlAndSave: e=" + e);
                                    if (stringReader != null) {
                                        stringReader.close();
                                    }
                                    if (z2) {
                                        OplusPreventModeData.Companion.getInstance(context).savePreventModeData(j, z, arrayList);
                                    }
                                }
                            }
                        }
                        stringReader2.close();
                        z2 = false;
                    } catch (Throwable th5) {
                        th2 = th5;
                        stringReader = stringReader2;
                        if (stringReader == null) {
                            throw th2;
                        }
                        stringReader.close();
                        throw th2;
                    }
                } catch (IOException e7) {
                    e = e7;
                    z = true;
                } catch (XmlPullParserException e8) {
                    e = e8;
                    z = true;
                }
            } catch (Throwable th6) {
                th2 = th6;
            }
        } catch (IOException e9) {
            e = e9;
            z = true;
        } catch (XmlPullParserException e10) {
            e = e10;
            z = true;
        }
        if (z2) {
            OplusPreventModeData.Companion.getInstance(context).savePreventModeData(j, z, arrayList);
        }
    }

    public static final String getEmergencyPhonePackageName(Context context) {
        ActivityInfo activityInfo;
        ResolveInfo resolveInfoResolveActivityAsUser = context.getPackageManager().resolveActivityAsUser(new Intent("android.intent.action.DIAL_EMERGENCY"), OplusAnimationController.FLAG_ALLOW_LIGHTOS_CORNER_RADIUS, KeyguardUpdateMonitor.getCurrentUser());
        if (resolveInfoResolveActivityAsUser == null || (activityInfo = resolveInfoResolveActivityAsUser.activityInfo) == null) {
            return null;
        }
        return activityInfo.packageName;
    }

    public static final String getPhonePackageName(Context context) {
        ActivityInfo activityInfo;
        ResolveInfo resolveInfoResolveActivityAsUser = context.getPackageManager().resolveActivityAsUser(new Intent("android.intent.action.DIAL"), OplusAnimationController.FLAG_ALLOW_LIGHTOS_CORNER_RADIUS, KeyguardUpdateMonitor.getCurrentUser());
        if (resolveInfoResolveActivityAsUser == null || (activityInfo = resolveInfoResolveActivityAsUser.activityInfo) == null) {
            return null;
        }
        return activityInfo.packageName;
    }

    public static final String getSosPackageName(Context context) {
        ActivityInfo activityInfo;
        ResolveInfo resolveInfoResolveActivityAsUser = context.getPackageManager().resolveActivityAsUser(new Intent("oplus.intent.action.LAUNCH_SOS_HELPER"), OplusAnimationController.FLAG_ALLOW_LIGHTOS_CORNER_RADIUS, KeyguardUpdateMonitor.getCurrentUser());
        if (resolveInfoResolveActivityAsUser == null || (activityInfo = resolveInfoResolveActivityAsUser.activityInfo) == null) {
            return null;
        }
        return activityInfo.packageName;
    }
}
