package com.oplusos.systemui.common.utils;

import android.app.ActivityManager;
import android.app.OplusActivityManager;
import android.content.ComponentName;
import android.content.Context;
import android.database.Cursor;
import android.net.Uri;
import android.os.RemoteException;
import android.view.OplusWindowManager;
import com.android.systemui.statusbar.notification.helper.StudyCenterHelper;
import com.oplusos.systemui.common.observer.ChildrenModeObserver;
import com.oplusos.systemui.common.util.LogUtil;
import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.nio.charset.StandardCharsets;

/* JADX INFO: compiled from: go/retraceme fd74bf9514dd0739b92d67a6d48a51eb10aaf8df760d83496b8dcfcf83e5f698 */
/* JADX INFO: loaded from: /home/starlight/Projects/Starlight Ports/.build-tmp/classes4.dex */
public abstract class Util {
    public static final Uri ROM_UPDATE_CONTENT_URI = Uri.parse("content://com.oplus.romupdate.provider.db/update_list");

    public static String getCurrentTopActivityNameInActivityStack() {
        ComponentName topActivityComponentName;
        try {
            topActivityComponentName = new OplusActivityManager().getTopActivityComponentName();
        } catch (RemoteException e) {
            LogUtil.d(LogUtil.TAG, "Util", "getCurrentTopActivityNameInActivityStack: " + e.getMessage());
            topActivityComponentName = null;
        }
        return topActivityComponentName != null ? topActivityComponentName.getClassName() : "";
    }

    public static String getCurrentTopAppPackageName() {
        ComponentName topActivityComponentName;
        try {
            topActivityComponentName = new OplusActivityManager().getTopActivityComponentName();
        } catch (RemoteException e) {
            LogUtil.d(LogUtil.TAG, "Util", "getCurrentTopAppPackageName: " + e.getMessage());
            topActivityComponentName = null;
        }
        return topActivityComponentName != null ? topActivityComponentName.getPackageName() : "";
    }

    /* JADX WARN: Code duplicated, block: B:8:0x003d A[Catch: Exception -> 0x004c, TryCatch #0 {Exception -> 0x004c, blocks: (B:5:0x002b, B:7:0x0031, B:10:0x0047, B:8:0x003d), top: B:18:0x002b }] */
    public static String getDataFromProvider(Context context, String str) {
        Cursor cursorQuery;
        String string;
        String[] strArr = {"xml"};
        try {
            cursorQuery = context.getContentResolver().query(ROM_UPDATE_CONTENT_URI, strArr, "filtername=\"" + str + "\"", null, null);
            if (cursorQuery != null) {
                try {
                    if (cursorQuery.getCount() > 0) {
                        int columnIndex = cursorQuery.getColumnIndex("xml");
                        cursorQuery.moveToNext();
                        string = cursorQuery.getString(columnIndex);
                    } else {
                        LogUtil.d(LogUtil.TAG_NAVBAR, "Util", "The Filtrate app cursor is null !!!");
                        string = null;
                    }
                } catch (Exception unused) {
                    if (cursorQuery != null) {
                        cursorQuery.close();
                    }
                    LogUtil.d(LogUtil.TAG_COMMON, "Util", "We can not get Filtrate app data from provider");
                    return null;
                }
            } else {
                LogUtil.d(LogUtil.TAG_NAVBAR, "Util", "The Filtrate app cursor is null !!!");
                string = null;
            }
            if (cursorQuery != null) {
                cursorQuery.close();
            }
            return string;
        } catch (Exception unused2) {
            cursorQuery = null;
        }
    }

    public static String getFocusWindowPkgName() {
        try {
            return new OplusWindowManager().getCurrentFocus();
        } catch (RemoteException e) {
            LogUtil.e(LogUtil.TAG_COMMON, "Util", "getFocusWindowPkgName error : " + e.getMessage(), e);
            return "";
        }
    }

    public static int getPid(Context context, String str) {
        for (ActivityManager.RunningAppProcessInfo runningAppProcessInfo : ((ActivityManager) context.getSystemService("activity")).getRunningAppProcesses()) {
            if (runningAppProcessInfo.processName.equals(str)) {
                return runningAppProcessInfo.pid;
            }
        }
        return -1;
    }

    public static ComponentName getTopActivityComponentName() {
        try {
            return new OplusActivityManager().getTopActivityComponentName();
        } catch (RemoteException e) {
            LogUtil.d(LogUtil.TAG, "Util", "getTopActivityComponentName: " + e.getMessage());
            return null;
        }
    }

    public static boolean isActivityAtTop(String str) {
        String currentTopActivityNameInActivityStack = getCurrentTopActivityNameInActivityStack();
        return currentTopActivityNameInActivityStack != null && currentTopActivityNameInActivityStack.equals(str);
    }

    public static boolean isChildrenOrStudyCenterMode() {
        return ChildrenModeObserver.getInstance().mIsChildrenMode || StudyCenterHelper.Companion.getInstance().isStudyCenterMode;
    }

    public static boolean isLandscape(Context context) {
        return context != null && context.getResources().getConfiguration().orientation == 2;
    }

    /* JADX WARN: Code duplicated, block: B:77:0x0108 A[EXC_TOP_SPLITTER, SYNTHETIC] */
    /* JADX WARN: Multi-variable type inference failed */
    /* JADX WARN: Type inference failed for: r3v10, types: [java.io.FileOutputStream] */
    /* JADX WARN: Type inference failed for: r3v11 */
    /* JADX WARN: Type inference failed for: r3v16 */
    /* JADX WARN: Type inference failed for: r3v18 */
    /* JADX WARN: Type inference failed for: r3v19 */
    /* JADX WARN: Type inference failed for: r3v20 */
    /* JADX WARN: Type inference failed for: r3v21 */
    /* JADX WARN: Type inference failed for: r3v22 */
    /* JADX WARN: Type inference failed for: r3v23 */
    /* JADX WARN: Type inference failed for: r3v24 */
    /* JADX WARN: Type inference failed for: r3v25 */
    /* JADX WARN: Type inference failed for: r3v26 */
    /* JADX WARN: Type inference failed for: r3v27 */
    /* JADX WARN: Type inference failed for: r3v28 */
    /* JADX WARN: Type inference failed for: r3v29 */
    /* JADX WARN: Type inference failed for: r3v4 */
    /* JADX WARN: Type inference failed for: r3v5 */
    public static void saveStrToFile(Context context, String str, String str2) throws Throwable {
        String str3;
        Throwable th;
        FileOutputStream fileOutputStream;
        Exception e;
        IllegalStateException e2;
        IllegalArgumentException e3;
        IOException e4;
        StringBuilder sb;
        File filesDir = context.getFilesDir();
        if (!filesDir.exists()) {
            try {
                filesDir.mkdirs();
            } catch (Exception e5) {
                str3 = "failed create path, e = " + e5.getMessage();
                LogUtil.e(LogUtil.TAG_COMMON, "Util", str3, e5);
            }
        }
        File file = new File(filesDir, str);
        ?? r3 = str3;
        if (!file.exists()) {
            try {
                file.createNewFile();
                r3 = str3;
            } catch (IOException e6) {
                StringBuilder sb2 = new StringBuilder("failed write file ");
                sb2.append(e6);
                LogUtil.d(LogUtil.TAG_COMMON, "Util", sb2.toString());
                r3 = sb2;
            }
        }
        try {
            try {
                fileOutputStream = new FileOutputStream(file);
                try {
                    fileOutputStream.write(str2.getBytes(StandardCharsets.UTF_8));
                    fileOutputStream.flush();
                    try {
                        fileOutputStream.close();
                        r3 = fileOutputStream;
                    } catch (IOException e7) {
                        e = e7;
                        sb = new StringBuilder("failed write file ");
                        sb.append(e);
                        LogUtil.d(LogUtil.TAG_COMMON, "Util", sb.toString());
                    }
                } catch (IOException e8) {
                    e4 = e8;
                    LogUtil.d(LogUtil.TAG_COMMON, "Util", "failed write file " + e4);
                    r3 = fileOutputStream;
                    if (fileOutputStream != null) {
                        try {
                            fileOutputStream.close();
                            r3 = fileOutputStream;
                        } catch (IOException e9) {
                            e = e9;
                            sb = new StringBuilder("failed write file ");
                            sb.append(e);
                            LogUtil.d(LogUtil.TAG_COMMON, "Util", sb.toString());
                        }
                    }
                } catch (IllegalArgumentException e10) {
                    e3 = e10;
                    LogUtil.d(LogUtil.TAG_COMMON, "Util", "failed write file " + e3);
                    r3 = fileOutputStream;
                    if (fileOutputStream != null) {
                        try {
                            fileOutputStream.close();
                            r3 = fileOutputStream;
                        } catch (IOException e11) {
                            e = e11;
                            sb = new StringBuilder("failed write file ");
                            sb.append(e);
                            LogUtil.d(LogUtil.TAG_COMMON, "Util", sb.toString());
                        }
                    }
                } catch (IllegalStateException e12) {
                    e2 = e12;
                    LogUtil.d(LogUtil.TAG_COMMON, "Util", "failed write file " + e2);
                    r3 = fileOutputStream;
                    if (fileOutputStream != null) {
                        try {
                            fileOutputStream.close();
                            r3 = fileOutputStream;
                        } catch (IOException e13) {
                            e = e13;
                            sb = new StringBuilder("failed write file ");
                            sb.append(e);
                            LogUtil.d(LogUtil.TAG_COMMON, "Util", sb.toString());
                        }
                    }
                } catch (Exception e14) {
                    e = e14;
                    LogUtil.d(LogUtil.TAG_COMMON, "Util", "failed write file " + e);
                    r3 = fileOutputStream;
                    if (fileOutputStream != null) {
                        try {
                            fileOutputStream.close();
                            r3 = fileOutputStream;
                        } catch (IOException e15) {
                            e = e15;
                            sb = new StringBuilder("failed write file ");
                            sb.append(e);
                            LogUtil.d(LogUtil.TAG_COMMON, "Util", sb.toString());
                        }
                    }
                }
            } catch (Throwable th2) {
                th = th2;
                if (r3 != 0) {
                    try {
                        r3.close();
                    } catch (IOException e16) {
                        LogUtil.d(LogUtil.TAG_COMMON, "Util", "failed write file " + e16);
                    }
                }
                throw th;
            }
        } catch (IOException e17) {
            fileOutputStream = null;
            e4 = e17;
        } catch (IllegalArgumentException e18) {
            fileOutputStream = null;
            e3 = e18;
        } catch (IllegalStateException e19) {
            fileOutputStream = null;
            e2 = e19;
        } catch (Exception e20) {
            fileOutputStream = null;
            e = e20;
        } catch (Throwable th3) {
            r3 = 0;
            th = th3;
            if (r3 != 0) {
                r3.close();
            }
            throw th;
        }
    }
}
