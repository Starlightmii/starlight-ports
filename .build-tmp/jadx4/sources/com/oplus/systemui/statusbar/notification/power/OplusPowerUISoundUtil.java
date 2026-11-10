package com.oplus.systemui.statusbar.notification.power;

import android.content.ContentResolver;
import android.content.Context;
import android.database.Cursor;
import android.media.AudioAttributes;
import android.media.SoundPool;
import android.net.Uri;
import android.os.Handler;
import android.provider.MediaStore;
import android.provider.Settings;
import android.text.TextUtils;
import android.util.Log;
import com.android.keyguard.OplusLockIconView$;
import com.android.systemui.media.NotificationPlayer;
import com.oplus.systemui.statusbar.notification.util.SoundUtil;
import com.oplusos.systemui.common.feature.CustomizeFeatureOption;
import com.oplusos.systemui.common.util.LogUtil;
import java.lang.invoke.VarHandle;
import java.util.LinkedHashMap;
import java.util.Map;

/* JADX INFO: compiled from: go/retraceme fd74bf9514dd0739b92d67a6d48a51eb10aaf8df760d83496b8dcfcf83e5f698 */
/* JADX INFO: loaded from: /home/starlight/Projects/Starlight Ports/.build-tmp/classes4.dex */
public final class OplusPowerUISoundUtil {
    private static final String CRITICAL_LOW_BATTERY_SOUND_FILE = "critical_lowbattery.ogg";
    public static final Companion Companion = new Companion();
    private static final String FULL_BATTERY_SOUND_FILE = "full_battery.ogg";
    private static final String LOL_USB_SOUND_FILE = "lol_charging.ogg";
    private static final String NORMAL_LOW_BATTERY_SOUND_FILE = "normal_lowbattery.ogg";
    private static final String ORIGIN_CRITICAL_LOW_BATTERY_SOUND_PATH = "/my_stock/media/audio/ui/critical_lowbattery.ogg";
    private static final String ORIGIN_FULL_BATTERY_SOUND_PATH = "/my_stock/media/audio/ui/full_battery.ogg";
    private static final String ORIGIN_NORMAL_LOW_BATTERY_SOUND_PATH = "/my_stock/media/audio/ui/normal_lowbattery.ogg";
    private static final String ORIGIN_USB_SOUND_PATH = "/my_stock/media/audio/ui/Usb.ogg";
    private static final String STRING_SETTINGS_CRITICAL_LOW_BATTERY_SOUND_PATH = "systemui_critical_low_battery_sound_path";
    private static final String STRING_SETTINGS_FULL_BATTERY_SOUND_PATH = "systemui_full_battery_sound_path";
    private static final String STRING_SETTINGS_NORMAL_LOW_BATTERY_SOUND_PATH = "systemui_normal_low_battery_sound_path";
    private static final String STRING_SETTINGS_USB_SOUND_PATH = "systemui_usb_sound_path";
    private static final String USB_SOUND_FILE = "Usb.ogg";
    private final Handler bgHandler;
    private final Context context;
    private SoundUtil soundUtil;
    private final Map<String, Integer> soundIdMap = new LinkedHashMap();
    private final Map<Integer, Boolean> soundPreparedMap = new LinkedHashMap();
    private final NotificationPlayer notificationPlayer = new NotificationPlayer("PowerUI");

    /* JADX INFO: compiled from: go/retraceme fd74bf9514dd0739b92d67a6d48a51eb10aaf8df760d83496b8dcfcf83e5f698 */
    public final class Companion {
        public static String getOggNameForFlag(int i) {
            if (i == 0) {
                return OplusPowerUISoundUtil.NORMAL_LOW_BATTERY_SOUND_FILE;
            }
            if (i == 1) {
                return OplusPowerUISoundUtil.FULL_BATTERY_SOUND_FILE;
            }
            if (i == 2) {
                return OplusPowerUISoundUtil.CRITICAL_LOW_BATTERY_SOUND_FILE;
            }
            if (i != 3) {
                return "";
            }
            return CustomizeFeatureOption.sLOLFeature ? OplusPowerUISoundUtil.LOL_USB_SOUND_FILE : OplusPowerUISoundUtil.USB_SOUND_FILE;
        }

        public static void initSoundPath(int i, Context context) {
            String string;
            String str;
            String str2 = OplusPowerUISoundUtil.STRING_SETTINGS_NORMAL_LOW_BATTERY_SOUND_PATH;
            Cursor cursorQuery = null;
            if (context != null) {
                ContentResolver contentResolver = context.getContentResolver();
                OplusPowerUISoundUtil.Companion.getClass();
                if (i == 0) {
                    str = OplusPowerUISoundUtil.STRING_SETTINGS_NORMAL_LOW_BATTERY_SOUND_PATH;
                } else if (i == 1) {
                    str = OplusPowerUISoundUtil.STRING_SETTINGS_FULL_BATTERY_SOUND_PATH;
                } else if (i != 2) {
                    str = i != 3 ? "" : OplusPowerUISoundUtil.STRING_SETTINGS_USB_SOUND_PATH;
                } else {
                    str = OplusPowerUISoundUtil.STRING_SETTINGS_CRITICAL_LOW_BATTERY_SOUND_PATH;
                }
                string = Settings.System.getStringForUser(contentResolver, str, 0);
            } else {
                string = null;
            }
            if (TextUtils.isEmpty(string)) {
                String oggNameForFlag = getOggNameForFlag(i);
                if (context != null) {
                    try {
                        ContentResolver contentResolver2 = context.getContentResolver();
                        if (contentResolver2 != null) {
                            cursorQuery = contentResolver2.query(MediaStore.Audio.Media.INTERNAL_CONTENT_URI, new String[]{"_data"}, "_display_name like ?", new String[]{oggNameForFlag}, null);
                        }
                    } catch (Exception unused) {
                        if (cursorQuery != null) {
                        }
                        if (TextUtils.isEmpty(string)) {
                            return;
                        } else {
                            return;
                        }
                    } catch (Throwable th) {
                        if (cursorQuery != null) {
                            cursorQuery.close();
                        }
                        throw th;
                    }
                }
                if (cursorQuery != null && cursorQuery.moveToFirst()) {
                    string = cursorQuery.getString(0);
                }
                if (cursorQuery != null) {
                    cursorQuery.close();
                }
                if (TextUtils.isEmpty(string) || context == null) {
                    return;
                }
                ContentResolver contentResolver3 = context.getContentResolver();
                OplusPowerUISoundUtil.Companion.getClass();
                if (i != 0) {
                    if (i == 1) {
                        str2 = OplusPowerUISoundUtil.STRING_SETTINGS_FULL_BATTERY_SOUND_PATH;
                    } else if (i != 2) {
                        str2 = i != 3 ? "" : OplusPowerUISoundUtil.STRING_SETTINGS_USB_SOUND_PATH;
                    } else {
                        str2 = OplusPowerUISoundUtil.STRING_SETTINGS_CRITICAL_LOW_BATTERY_SOUND_PATH;
                    }
                }
                Settings.System.putStringForUser(contentResolver3, str2, string, 0);
            }
        }
    }

    /* JADX INFO: renamed from: com.oplus.systemui.statusbar.notification.power.OplusPowerUISoundUtil$init$1, reason: invalid class name */
    /* JADX INFO: compiled from: go/retraceme fd74bf9514dd0739b92d67a6d48a51eb10aaf8df760d83496b8dcfcf83e5f698 */
    public final class AnonymousClass1 implements Runnable {
        public /* synthetic */ OplusPowerUISoundUtil this$0;

        @Override // java.lang.Runnable
        public final void run() {
            Companion companion = OplusPowerUISoundUtil.Companion;
            Context context = this.this$0.context;
            companion.getClass();
            Companion.initSoundPath(0, context);
            Companion.initSoundPath(2, context);
            Companion.initSoundPath(1, context);
            Companion.initSoundPath(3, context);
        }
    }

    /* JADX INFO: renamed from: com.oplus.systemui.statusbar.notification.power.OplusPowerUISoundUtil$playPowerUISound$1, reason: invalid class name and case insensitive filesystem */
    /* JADX INFO: compiled from: go/retraceme fd74bf9514dd0739b92d67a6d48a51eb10aaf8df760d83496b8dcfcf83e5f698 */
    public final class RunnableC02871 implements Runnable {
        public /* synthetic */ int $soundFlag;
        public /* synthetic */ OplusPowerUISoundUtil this$0;

        /* JADX WARN: Code duplicated, block: B:100:0x0168  */
        /* JADX WARN: Code duplicated, block: B:101:0x016a  */
        /* JADX WARN: Code duplicated, block: B:102:0x016c  */
        /* JADX WARN: Code duplicated, block: B:106:0x0178  */
        /* JADX WARN: Code duplicated, block: B:107:0x017a  */
        /* JADX WARN: Code duplicated, block: B:109:0x017d  */
        /* JADX WARN: Code duplicated, block: B:111:0x0180  */
        /* JADX WARN: Code duplicated, block: B:113:0x0183  */
        /* JADX WARN: Code duplicated, block: B:114:0x0185  */
        /* JADX WARN: Code duplicated, block: B:115:0x0187  */
        /* JADX WARN: Code duplicated, block: B:116:0x0189  */
        /* JADX WARN: Code duplicated, block: B:120:0x019f  */
        /* JADX WARN: Code duplicated, block: B:124:0x0101 A[EXC_TOP_SPLITTER, SYNTHETIC] */
        /* JADX WARN: Code duplicated, block: B:130:0x0123 A[EXC_TOP_SPLITTER, SYNTHETIC] */
        /* JADX WARN: Code duplicated, block: B:132:? A[RETURN, SYNTHETIC] */
        /* JADX WARN: Code duplicated, block: B:133:? A[RETURN, SYNTHETIC] */
        /* JADX WARN: Code duplicated, block: B:13:0x003d  */
        /* JADX WARN: Code duplicated, block: B:22:0x0052  */
        /* JADX WARN: Code duplicated, block: B:25:0x0058 A[PHI: r1
          0x0058: PHI (r1v5 android.database.Cursor) = (r1v4 android.database.Cursor), (r1v6 android.database.Cursor) binds: [B:24:0x0056, B:26:0x005c] A[DONT_GENERATE, DONT_INLINE]] */
        /* JADX WARN: Code duplicated, block: B:30:0x007d A[DONT_INVERT] */
        /* JADX WARN: Code duplicated, block: B:31:0x007f  */
        /* JADX WARN: Code duplicated, block: B:33:0x008a A[DONT_INVERT] */
        /* JADX WARN: Code duplicated, block: B:34:0x008c A[DONT_INVERT] */
        /* JADX WARN: Code duplicated, block: B:35:0x008e  */
        /* JADX WARN: Code duplicated, block: B:37:0x0091  */
        /* JADX WARN: Code duplicated, block: B:39:0x0094  */
        /* JADX WARN: Code duplicated, block: B:40:0x0096  */
        /* JADX WARN: Code duplicated, block: B:41:0x0098  */
        /* JADX WARN: Code duplicated, block: B:42:0x009a  */
        /* JADX WARN: Code duplicated, block: B:44:0x00a2  */
        /* JADX WARN: Code duplicated, block: B:47:0x00a9 A[DONT_INVERT] */
        /* JADX WARN: Code duplicated, block: B:48:0x00ab A[DONT_INVERT] */
        /* JADX WARN: Code duplicated, block: B:49:0x00ad A[DONT_INVERT] */
        /* JADX WARN: Code duplicated, block: B:50:0x00af  */
        /* JADX WARN: Code duplicated, block: B:52:0x00b2  */
        /* JADX WARN: Code duplicated, block: B:53:0x00b4  */
        /* JADX WARN: Code duplicated, block: B:54:0x00b6  */
        /* JADX WARN: Code duplicated, block: B:55:0x00b8  */
        /* JADX WARN: Code duplicated, block: B:56:0x00ba  */
        /* JADX WARN: Code duplicated, block: B:58:0x00bd  */
        /* JADX WARN: Code duplicated, block: B:59:0x00c5  */
        /* JADX WARN: Code duplicated, block: B:62:0x00e6  */
        /* JADX WARN: Code duplicated, block: B:64:0x00fd  */
        /* JADX WARN: Code duplicated, block: B:65:0x00ff A[DONT_INVERT] */
        /* JADX WARN: Code duplicated, block: B:68:0x0107 A[Catch: all -> 0x011c, Exception -> 0x011e, TRY_LEAVE, TryCatch #8 {Exception -> 0x011e, all -> 0x011c, blocks: (B:66:0x0101, B:68:0x0107), top: B:124:0x0101 }] */
        /* JADX WARN: Code duplicated, block: B:73:0x0120  */
        /* JADX WARN: Code duplicated, block: B:77:0x0129 A[Catch: all -> 0x013c, Exception -> 0x0144, TRY_LEAVE, TryCatch #5 {Exception -> 0x0144, all -> 0x013c, blocks: (B:75:0x0123, B:77:0x0129), top: B:130:0x0123 }] */
        /* JADX WARN: Code duplicated, block: B:82:0x0140  */
        /* JADX WARN: Code duplicated, block: B:85:0x0146 A[PHI: r1
          0x0146: PHI (r1v16 android.database.Cursor) = (r1v15 android.database.Cursor), (r1v17 android.database.Cursor) binds: [B:84:0x0144, B:86:0x014a] A[DONT_GENERATE, DONT_INLINE]] */
        /* JADX WARN: Code duplicated, block: B:89:0x014f A[DONT_INVERT] */
        /* JADX WARN: Code duplicated, block: B:90:0x0151  */
        /* JADX WARN: Code duplicated, block: B:92:0x015c  */
        /* JADX WARN: Code duplicated, block: B:94:0x015f  */
        /* JADX WARN: Code duplicated, block: B:96:0x0162  */
        /* JADX WARN: Code duplicated, block: B:98:0x0165  */
        @Override // java.lang.Runnable
        public final void run() throws Throwable {
            Cursor cursor;
            Cursor cursorQuery;
            String string;
            boolean zIsEmpty;
            String str;
            String str2;
            boolean z;
            int i;
            Context context;
            String oggNameForFlag;
            Cursor cursorQuery2;
            ContentResolver contentResolver;
            Uri uriWithAppendedPath;
            String stringForUser;
            String str3;
            Companion companion = OplusPowerUISoundUtil.Companion;
            int i2 = this.$soundFlag;
            Context context2 = this.this$0.context;
            companion.getClass();
            String oggNameForFlag2 = Companion.getOggNameForFlag(i2);
            if (TextUtils.isEmpty(oggNameForFlag2)) {
                string = null;
            } else {
                if (context2 != null) {
                    try {
                        ContentResolver contentResolver2 = context2.getContentResolver();
                        if (contentResolver2 != null) {
                            cursorQuery = contentResolver2.query(MediaStore.Audio.Media.INTERNAL_CONTENT_URI, new String[]{"_data"}, "_display_name like ?", new String[]{oggNameForFlag2}, null);
                        } else {
                            cursorQuery = null;
                        }
                    } catch (Exception unused) {
                        cursorQuery = null;
                        if (cursorQuery != null) {
                            cursorQuery.close();
                        }
                        string = null;
                        zIsEmpty = TextUtils.isEmpty(string);
                        str = OplusPowerUISoundUtil.ORIGIN_NORMAL_LOW_BATTERY_SOUND_PATH;
                        str2 = OplusPowerUISoundUtil.STRING_SETTINGS_NORMAL_LOW_BATTERY_SOUND_PATH;
                        if (zIsEmpty) {
                            if (context2 != null) {
                                ContentResolver contentResolver3 = context2.getContentResolver();
                                OplusPowerUISoundUtil.Companion.getClass();
                                if (i2 != 0) {
                                    str3 = OplusPowerUISoundUtil.STRING_SETTINGS_NORMAL_LOW_BATTERY_SOUND_PATH;
                                } else if (i2 != 1) {
                                    str3 = OplusPowerUISoundUtil.STRING_SETTINGS_FULL_BATTERY_SOUND_PATH;
                                } else if (i2 != 2) {
                                    str3 = OplusPowerUISoundUtil.STRING_SETTINGS_CRITICAL_LOW_BATTERY_SOUND_PATH;
                                } else if (i2 != 3) {
                                    str3 = "";
                                } else {
                                    str3 = OplusPowerUISoundUtil.STRING_SETTINGS_USB_SOUND_PATH;
                                }
                                string = Settings.System.getStringForUser(contentResolver3, str3, 0);
                            } else {
                                string = null;
                            }
                            if (TextUtils.isEmpty(string)) {
                                if (i2 != 0) {
                                    string = OplusPowerUISoundUtil.ORIGIN_NORMAL_LOW_BATTERY_SOUND_PATH;
                                } else if (i2 != 1) {
                                    string = OplusPowerUISoundUtil.ORIGIN_FULL_BATTERY_SOUND_PATH;
                                } else if (i2 != 2) {
                                    string = OplusPowerUISoundUtil.ORIGIN_CRITICAL_LOW_BATTERY_SOUND_PATH;
                                } else if (i2 != 3) {
                                    string = "";
                                } else {
                                    string = OplusPowerUISoundUtil.ORIGIN_USB_SOUND_PATH;
                                }
                            }
                        }
                        if (string != null) {
                            z = !OplusPowerUISoundUtil.access$playSound(this.this$0, string);
                        } else {
                            z = true;
                            string = null;
                        }
                        StringBuilder sbM = OplusLockIconView$.ExternalSyntheticOutline0.m("Play PowerUI sound applyUri(", this.$soundFlag, ") for ", ", soundPath: ", z);
                        sbM.append((Object) string);
                        Cursor cursor2 = null;
                        stringForUser = null;
                        LogUtil.i$default(LogUtil.TAG_NOTIFICATION, "Oplus_PowerUI", sbM.toString(), null, 8);
                        if (z) {
                            Companion companion2 = OplusPowerUISoundUtil.Companion;
                            i = this.$soundFlag;
                            context = this.this$0.context;
                            companion2.getClass();
                            oggNameForFlag = Companion.getOggNameForFlag(i);
                            if (TextUtils.isEmpty(oggNameForFlag)) {
                                uriWithAppendedPath = null;
                            } else {
                                if (context != null) {
                                    try {
                                        contentResolver = context.getContentResolver();
                                        if (contentResolver != null) {
                                            cursorQuery2 = contentResolver.query(MediaStore.Audio.Media.INTERNAL_CONTENT_URI, new String[]{"_id"}, "_display_name like ?", new String[]{oggNameForFlag}, null);
                                        } else {
                                            cursorQuery2 = null;
                                        }
                                    } catch (Exception unused2) {
                                        cursorQuery2 = null;
                                        if (cursorQuery2 != null) {
                                            cursorQuery2.close();
                                        }
                                        uriWithAppendedPath = null;
                                        if (uriWithAppendedPath == null) {
                                            if (context != null) {
                                                ContentResolver contentResolver4 = context.getContentResolver();
                                                OplusPowerUISoundUtil.Companion.getClass();
                                                if (i != 0) {
                                                    if (i == 1) {
                                                        str2 = OplusPowerUISoundUtil.STRING_SETTINGS_FULL_BATTERY_SOUND_PATH;
                                                    } else if (i == 2) {
                                                        str2 = OplusPowerUISoundUtil.STRING_SETTINGS_CRITICAL_LOW_BATTERY_SOUND_PATH;
                                                    } else if (i != 3) {
                                                        str2 = "";
                                                    } else {
                                                        str2 = OplusPowerUISoundUtil.STRING_SETTINGS_USB_SOUND_PATH;
                                                    }
                                                }
                                                stringForUser = Settings.System.getStringForUser(contentResolver4, str2, 0);
                                            }
                                            if (TextUtils.isEmpty(stringForUser)) {
                                                if (i != 0) {
                                                    if (i == 1) {
                                                        str = OplusPowerUISoundUtil.ORIGIN_FULL_BATTERY_SOUND_PATH;
                                                    } else if (i == 2) {
                                                        str = OplusPowerUISoundUtil.ORIGIN_CRITICAL_LOW_BATTERY_SOUND_PATH;
                                                    } else if (i != 3) {
                                                        str = "";
                                                    } else {
                                                        str = OplusPowerUISoundUtil.ORIGIN_USB_SOUND_PATH;
                                                    }
                                                }
                                                stringForUser = str;
                                            }
                                            uriWithAppendedPath = Uri.parse("file://" + stringForUser);
                                        }
                                        if (uriWithAppendedPath != null) {
                                            OplusPowerUISoundUtil.access$playSound(this.this$0, uriWithAppendedPath);
                                        }
                                    } catch (Throwable th) {
                                        th = th;
                                        if (cursor2 != null) {
                                            cursor2.close();
                                        }
                                        throw th;
                                    }
                                } else {
                                    cursorQuery2 = null;
                                }
                                if (cursorQuery2 != null) {
                                    try {
                                        if (cursorQuery2.moveToFirst()) {
                                            uriWithAppendedPath = Uri.withAppendedPath(MediaStore.Audio.Media.INTERNAL_CONTENT_URI, String.valueOf(cursorQuery2.getInt(0)));
                                            cursorQuery2.close();
                                        }
                                    } catch (Exception unused3) {
                                        if (cursorQuery2 != null) {
                                            cursorQuery2.close();
                                        }
                                    } catch (Throwable th2) {
                                        th = th2;
                                        cursor2 = cursorQuery2;
                                        if (cursor2 != null) {
                                            cursor2.close();
                                        }
                                        throw th;
                                    }
                                }
                                if (cursorQuery2 != null) {
                                    cursorQuery2.close();
                                }
                                uriWithAppendedPath = null;
                            }
                            if (uriWithAppendedPath == null) {
                                if (context != null) {
                                    ContentResolver contentResolver5 = context.getContentResolver();
                                    OplusPowerUISoundUtil.Companion.getClass();
                                    if (i != 0) {
                                        if (i == 1) {
                                            str2 = OplusPowerUISoundUtil.STRING_SETTINGS_FULL_BATTERY_SOUND_PATH;
                                        } else if (i == 2) {
                                            str2 = OplusPowerUISoundUtil.STRING_SETTINGS_CRITICAL_LOW_BATTERY_SOUND_PATH;
                                        } else if (i != 3) {
                                            str2 = "";
                                        } else {
                                            str2 = OplusPowerUISoundUtil.STRING_SETTINGS_USB_SOUND_PATH;
                                        }
                                    }
                                    stringForUser = Settings.System.getStringForUser(contentResolver5, str2, 0);
                                }
                                if (TextUtils.isEmpty(stringForUser)) {
                                    if (i != 0) {
                                        if (i == 1) {
                                            str = OplusPowerUISoundUtil.ORIGIN_FULL_BATTERY_SOUND_PATH;
                                        } else if (i == 2) {
                                            str = OplusPowerUISoundUtil.ORIGIN_CRITICAL_LOW_BATTERY_SOUND_PATH;
                                        } else if (i != 3) {
                                            str = "";
                                        } else {
                                            str = OplusPowerUISoundUtil.ORIGIN_USB_SOUND_PATH;
                                        }
                                    }
                                    stringForUser = str;
                                }
                                uriWithAppendedPath = Uri.parse("file://" + stringForUser);
                            }
                            if (uriWithAppendedPath != null) {
                                OplusPowerUISoundUtil.access$playSound(this.this$0, uriWithAppendedPath);
                            }
                        }
                    } catch (Throwable th3) {
                        th = th3;
                        cursor = null;
                        if (cursor != null) {
                            cursor.close();
                        }
                        throw th;
                    }
                } else {
                    cursorQuery = null;
                }
                if (cursorQuery != null) {
                    try {
                        if (cursorQuery.moveToFirst()) {
                            string = cursorQuery.getString(0);
                            cursorQuery.close();
                        }
                    } catch (Exception unused4) {
                        if (cursorQuery != null) {
                            cursorQuery.close();
                        }
                    } catch (Throwable th4) {
                        th = th4;
                        cursor = cursorQuery;
                        if (cursor != null) {
                            cursor.close();
                        }
                        throw th;
                    }
                }
                if (cursorQuery != null) {
                    cursorQuery.close();
                }
                string = null;
            }
            zIsEmpty = TextUtils.isEmpty(string);
            str = OplusPowerUISoundUtil.ORIGIN_NORMAL_LOW_BATTERY_SOUND_PATH;
            str2 = OplusPowerUISoundUtil.STRING_SETTINGS_NORMAL_LOW_BATTERY_SOUND_PATH;
            if (zIsEmpty) {
                if (context2 != null) {
                    ContentResolver contentResolver6 = context2.getContentResolver();
                    OplusPowerUISoundUtil.Companion.getClass();
                    if (i2 != 0) {
                        str3 = OplusPowerUISoundUtil.STRING_SETTINGS_NORMAL_LOW_BATTERY_SOUND_PATH;
                    } else if (i2 != 1) {
                        str3 = OplusPowerUISoundUtil.STRING_SETTINGS_FULL_BATTERY_SOUND_PATH;
                    } else if (i2 != 2) {
                        str3 = OplusPowerUISoundUtil.STRING_SETTINGS_CRITICAL_LOW_BATTERY_SOUND_PATH;
                    } else if (i2 != 3) {
                        str3 = "";
                    } else {
                        str3 = OplusPowerUISoundUtil.STRING_SETTINGS_USB_SOUND_PATH;
                    }
                    string = Settings.System.getStringForUser(contentResolver6, str3, 0);
                } else {
                    string = null;
                }
                if (TextUtils.isEmpty(string)) {
                    if (i2 != 0) {
                        string = OplusPowerUISoundUtil.ORIGIN_NORMAL_LOW_BATTERY_SOUND_PATH;
                    } else if (i2 != 1) {
                        string = OplusPowerUISoundUtil.ORIGIN_FULL_BATTERY_SOUND_PATH;
                    } else if (i2 != 2) {
                        string = OplusPowerUISoundUtil.ORIGIN_CRITICAL_LOW_BATTERY_SOUND_PATH;
                    } else if (i2 != 3) {
                        string = "";
                    } else {
                        string = OplusPowerUISoundUtil.ORIGIN_USB_SOUND_PATH;
                    }
                }
            }
            if (string != null) {
                z = !OplusPowerUISoundUtil.access$playSound(this.this$0, string);
            } else {
                z = true;
                string = null;
            }
            StringBuilder sbM2 = OplusLockIconView$.ExternalSyntheticOutline0.m("Play PowerUI sound applyUri(", this.$soundFlag, ") for ", ", soundPath: ", z);
            sbM2.append((Object) string);
            Cursor cursor3 = null;
            stringForUser = null;
            LogUtil.i$default(LogUtil.TAG_NOTIFICATION, "Oplus_PowerUI", sbM2.toString(), null, 8);
            if (z) {
                Companion companion3 = OplusPowerUISoundUtil.Companion;
                i = this.$soundFlag;
                context = this.this$0.context;
                companion3.getClass();
                oggNameForFlag = Companion.getOggNameForFlag(i);
                if (TextUtils.isEmpty(oggNameForFlag)) {
                    if (context != null) {
                        contentResolver = context.getContentResolver();
                        if (contentResolver != null) {
                            cursorQuery2 = contentResolver.query(MediaStore.Audio.Media.INTERNAL_CONTENT_URI, new String[]{"_id"}, "_display_name like ?", new String[]{oggNameForFlag}, null);
                        } else {
                            cursorQuery2 = null;
                        }
                    } else {
                        cursorQuery2 = null;
                    }
                    if (cursorQuery2 != null) {
                        if (cursorQuery2.moveToFirst()) {
                            uriWithAppendedPath = Uri.withAppendedPath(MediaStore.Audio.Media.INTERNAL_CONTENT_URI, String.valueOf(cursorQuery2.getInt(0)));
                            cursorQuery2.close();
                        }
                    }
                    if (cursorQuery2 != null) {
                        cursorQuery2.close();
                    }
                    uriWithAppendedPath = null;
                } else {
                    uriWithAppendedPath = null;
                }
                if (uriWithAppendedPath == null) {
                    if (context != null) {
                        ContentResolver contentResolver7 = context.getContentResolver();
                        OplusPowerUISoundUtil.Companion.getClass();
                        if (i != 0) {
                            if (i == 1) {
                                str2 = OplusPowerUISoundUtil.STRING_SETTINGS_FULL_BATTERY_SOUND_PATH;
                            } else if (i == 2) {
                                str2 = OplusPowerUISoundUtil.STRING_SETTINGS_CRITICAL_LOW_BATTERY_SOUND_PATH;
                            } else if (i != 3) {
                                str2 = "";
                            } else {
                                str2 = OplusPowerUISoundUtil.STRING_SETTINGS_USB_SOUND_PATH;
                            }
                        }
                        stringForUser = Settings.System.getStringForUser(contentResolver7, str2, 0);
                    }
                    if (TextUtils.isEmpty(stringForUser)) {
                        if (i != 0) {
                            if (i == 1) {
                                str = OplusPowerUISoundUtil.ORIGIN_FULL_BATTERY_SOUND_PATH;
                            } else if (i == 2) {
                                str = OplusPowerUISoundUtil.ORIGIN_CRITICAL_LOW_BATTERY_SOUND_PATH;
                            } else if (i != 3) {
                                str = "";
                            } else {
                                str = OplusPowerUISoundUtil.ORIGIN_USB_SOUND_PATH;
                            }
                        }
                        stringForUser = str;
                    }
                    uriWithAppendedPath = Uri.parse("file://" + stringForUser);
                }
                if (uriWithAppendedPath != null) {
                    OplusPowerUISoundUtil.access$playSound(this.this$0, uriWithAppendedPath);
                }
            }
        }
    }

    public OplusPowerUISoundUtil(Context context, Handler handler) {
        this.context = context;
        this.bgHandler = handler;
    }

    public static final boolean access$playSound(OplusPowerUISoundUtil oplusPowerUISoundUtil, String str) {
        if (oplusPowerUISoundUtil.soundUtil == null) {
            SoundUtil soundUtil = new SoundUtil();
            oplusPowerUISoundUtil.soundUtil = soundUtil;
            OplusPowerUISoundUtil$playSound$1 oplusPowerUISoundUtil$playSound$1 = new OplusPowerUISoundUtil$playSound$1();
            oplusPowerUISoundUtil$playSound$1.this$0 = oplusPowerUISoundUtil;
            VarHandle.storeStoreFence();
            soundUtil.setCompleteListener(oplusPowerUISoundUtil$playSound$1);
        }
        if (oplusPowerUISoundUtil.soundUtil != null && !TextUtils.isEmpty(str)) {
            int iIntValue = oplusPowerUISoundUtil.soundIdMap.getOrDefault(str, 0).intValue();
            if (iIntValue == 0) {
                SoundUtil soundUtil2 = oplusPowerUISoundUtil.soundUtil;
                soundUtil2.getClass();
                iIntValue = soundUtil2.mSoundPool.load(str, 1);
                if (iIntValue != 0) {
                    oplusPowerUISoundUtil.soundIdMap.put(str, Integer.valueOf(iIntValue));
                }
            }
            if (iIntValue != 0) {
                oplusPowerUISoundUtil.playSoundId(iIntValue, oplusPowerUISoundUtil.soundPreparedMap.getOrDefault(Integer.valueOf(iIntValue), Boolean.FALSE).booleanValue());
                return true;
            }
        }
        return false;
    }

    public final void init() {
        Handler handler = this.bgHandler;
        if (handler != null) {
            AnonymousClass1 anonymousClass1 = new AnonymousClass1();
            anonymousClass1.this$0 = this;
            VarHandle.storeStoreFence();
            handler.post(anonymousClass1);
        }
    }

    public final void playPowerUISound(int i) {
        Handler handler = this.bgHandler;
        if (handler != null) {
            RunnableC02871 runnableC02871 = new RunnableC02871();
            runnableC02871.$soundFlag = i;
            runnableC02871.this$0 = this;
            VarHandle.storeStoreFence();
            handler.post(runnableC02871);
        }
    }

    public final void playSoundId(int i, boolean z) {
        SoundPool soundPool;
        if (i == 0 || !z) {
            return;
        }
        SoundUtil soundUtil = this.soundUtil;
        Integer numValueOf = soundUtil != null ? Integer.valueOf(soundUtil.mSoundPool.play(i, 1.0f, 1.0f, 1, 0, 1.0f)) : null;
        if (numValueOf != null && numValueOf.intValue() == 0) {
            this.soundIdMap.clear();
            this.soundPreparedMap.clear();
            SoundUtil soundUtil2 = this.soundUtil;
            if (soundUtil2 != null && (soundPool = soundUtil2.mSoundPool) != null) {
                soundPool.release();
                soundUtil2.mSoundMap.clear();
            }
            this.soundUtil = null;
        }
    }

    public static final void access$playSound(OplusPowerUISoundUtil oplusPowerUISoundUtil, Uri uri) {
        Context context = oplusPowerUISoundUtil.context;
        if (context != null) {
            try {
                oplusPowerUISoundUtil.notificationPlayer.play(context, uri, false, new AudioAttributes.Builder().setUsage(0).setContentType(0).setLegacyStreamType(1).build(), 1.0f);
            } catch (Exception e) {
                if (LogUtil.getNormalDebug() && LogUtil.isTagValid(LogUtil.TAG_COMMON)) {
                    Log.d("SystemUi--Common", "Oplus_PowerUI-->".concat("MediaPlayer Exception: " + e));
                }
            }
        }
    }
}
