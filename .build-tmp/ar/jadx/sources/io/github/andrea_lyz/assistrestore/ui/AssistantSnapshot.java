package io.github.andrea_lyz.assistrestore.ui;

import android.content.ComponentName;
import android.content.Context;
import android.content.Intent;
import android.content.pm.ActivityInfo;
import android.content.pm.ApplicationInfo;
import android.content.pm.PackageManager;
import android.content.pm.ResolveInfo;
import android.content.pm.ServiceInfo;
import android.graphics.drawable.Drawable;
import android.provider.Settings;
import java.util.Collection;
import java.util.Iterator;
import java.util.LinkedHashMap;
import java.util.List;

/* JADX INFO: compiled from: r8-map-id-864ffde9d59ed35bc4209251fe295afa616a17016f049d977cb0fda4927bbf7c */
/* JADX INFO: loaded from: classes.dex */
public final class AssistantSnapshot {
    private static final String ASSIST_ACTION = "android.intent.action.ASSIST";
    private static final String VIS_ACTION = "android.service.voice.VoiceInteractionService";
    private final List<AssistantApp> apps;
    private final y0.c0 defaultIcon;
    private final String defaultLabel;
    private final String defaultPackage;
    public static final Companion Companion = new Companion(null);
    public static final int $stable = 8;

    /* JADX INFO: compiled from: r8-map-id-864ffde9d59ed35bc4209251fe295afa616a17016f049d977cb0fda4927bbf7c */
    public static final class Companion {
        private Companion() {
        }

        public /* synthetic */ Companion(b4.g gVar) {
            this();
        }

        private static final void load$add(Context context, LinkedHashMap<String, AssistantApp> linkedHashMap, PackageManager packageManager, int i5, String str, String str2) {
            Object objR;
            Object objR2;
            Object objR3;
            if (b4.l.a(str, context.getPackageName()) || linkedHashMap.containsKey(str)) {
                return;
            }
            Companion companion = AssistantSnapshot.Companion;
            try {
                objR = packageManager.getApplicationInfo(str, 0);
            } catch (Throwable th) {
                objR = p3.j.r(th);
            }
            if (objR instanceof p3.h) {
                objR = null;
            }
            ApplicationInfo applicationInfo = (ApplicationInfo) objR;
            if (applicationInfo == null) {
                return;
            }
            Companion companion2 = AssistantSnapshot.Companion;
            try {
                objR2 = packageManager.getApplicationLabel(applicationInfo).toString();
            } catch (Throwable th2) {
                objR2 = p3.j.r(th2);
            }
            if (objR2 instanceof p3.h) {
                objR2 = str;
            }
            String str3 = (String) objR2;
            Companion companion3 = AssistantSnapshot.Companion;
            try {
                Drawable drawableLoadIcon = applicationInfo.loadIcon(packageManager);
                b4.l.d("loadIcon(...)", drawableLoadIcon);
                objR3 = AssistDataKt.rasterize(drawableLoadIcon, i5);
            } catch (Throwable th3) {
                objR3 = p3.j.r(th3);
            }
            linkedHashMap.put(str, new AssistantApp(str, str3, str2, (y0.c0) (objR3 instanceof p3.h ? null : objR3)));
        }

        public static /* synthetic */ AssistantSnapshot load$default(Companion companion, Context context, int i5, int i6, Object obj) {
            if ((i6 & 2) != 0) {
                i5 = 144;
            }
            return companion.load(context, i5);
        }

        /* JADX WARN: Code duplicated, block: B:25:0x006b A[Catch: all -> 0x007f, TryCatch #4 {all -> 0x007f, blocks: (B:22:0x0051, B:23:0x0065, B:25:0x006b, B:27:0x0075, B:29:0x0079), top: B:85:0x0051 }] */
        /* JADX WARN: Code duplicated, block: B:39:0x009a  */
        /* JADX WARN: Code duplicated, block: B:42:0x009f  */
        /* JADX WARN: Code duplicated, block: B:45:0x00a6  */
        /* JADX WARN: Code duplicated, block: B:47:0x00a9  */
        /* JADX WARN: Code duplicated, block: B:48:0x00ae  */
        /* JADX WARN: Code duplicated, block: B:50:0x00b1  */
        /* JADX WARN: Code duplicated, block: B:51:0x00b6  */
        /* JADX WARN: Code duplicated, block: B:53:0x00b9  */
        /* JADX WARN: Code duplicated, block: B:60:0x00d2  */
        /* JADX WARN: Code duplicated, block: B:63:0x00d7  */
        /* JADX WARN: Code duplicated, block: B:65:0x00db  */
        /* JADX WARN: Code duplicated, block: B:73:0x00f5  */
        public final AssistantSnapshot load(Context context, int i5) {
            Context context2;
            int i6;
            Object objR;
            boolean z2;
            y0.c0 c0Var;
            Object obj;
            String str;
            ComponentName componentNameUnflattenFromString;
            String packageName;
            String str2;
            Object objR2;
            Object objR3;
            boolean z3;
            Object obj2;
            Iterator<T> it;
            ActivityInfo activityInfo;
            String str3;
            String str4;
            b4.l.e("context", context);
            PackageManager packageManager = context.getPackageManager();
            LinkedHashMap linkedHashMap = new LinkedHashMap();
            try {
                List<ResolveInfo> listQueryIntentServices = packageManager.queryIntentServices(new Intent(AssistantSnapshot.VIS_ACTION), 0);
                b4.l.d("queryIntentServices(...)", listQueryIntentServices);
                Iterator<T> it2 = listQueryIntentServices.iterator();
                while (it2.hasNext()) {
                    ServiceInfo serviceInfo = ((ResolveInfo) it2.next()).serviceInfo;
                    if (serviceInfo == null || (str4 = serviceInfo.packageName) == null) {
                        context2 = context;
                        i6 = i5;
                    } else {
                        context2 = context;
                        i6 = i5;
                        try {
                            load$add(context2, linkedHashMap, packageManager, i6, str4, "支持助理 · 语音交互服务");
                        } catch (Throwable th) {
                            th = th;
                            p3.j.r(th);
                            List<ResolveInfo> listQueryIntentActivities = packageManager.queryIntentActivities(new Intent(AssistantSnapshot.ASSIST_ACTION), 0);
                            b4.l.d("queryIntentActivities(...)", listQueryIntentActivities);
                            it = listQueryIntentActivities.iterator();
                            while (it.hasNext()) {
                                activityInfo = ((ResolveInfo) it.next()).activityInfo;
                                if (activityInfo == null) {
                                }
                            }
                            objR = Settings.Secure.getString(context2.getContentResolver(), "assistant");
                            z2 = objR instanceof p3.h;
                            c0Var = null;
                            obj = objR;
                            if (z2) {
                                obj = null;
                            }
                            str = (String) obj;
                            if (str == null) {
                                componentNameUnflattenFromString = null;
                            } else {
                                if (h4.g.T(str)) {
                                    str = null;
                                }
                                if (str != null) {
                                    componentNameUnflattenFromString = ComponentName.unflattenFromString(str);
                                } else {
                                    componentNameUnflattenFromString = null;
                                }
                            }
                            if (componentNameUnflattenFromString != null) {
                                packageName = componentNameUnflattenFromString.getPackageName();
                            } else {
                                packageName = null;
                            }
                            if (packageName != null) {
                                Companion companion = AssistantSnapshot.Companion;
                                try {
                                    objR3 = packageManager.getApplicationLabel(packageManager.getApplicationInfo(packageName, 0)).toString();
                                } catch (Throwable th2) {
                                    objR3 = p3.j.r(th2);
                                }
                                z3 = objR3 instanceof p3.h;
                                obj2 = objR3;
                                if (z3) {
                                    obj2 = null;
                                }
                                str2 = (String) obj2;
                                if (str2 == null) {
                                    str2 = "未设置";
                                }
                            } else {
                                str2 = "未设置";
                            }
                            if (packageName != null) {
                                Companion companion2 = AssistantSnapshot.Companion;
                                try {
                                    Drawable applicationIcon = packageManager.getApplicationIcon(packageName);
                                    b4.l.d("getApplicationIcon(...)", applicationIcon);
                                    objR2 = AssistDataKt.rasterize(applicationIcon, i6);
                                } catch (Throwable th3) {
                                    objR2 = p3.j.r(th3);
                                }
                                c0Var = (y0.c0) (objR2 instanceof p3.h ? null : objR2);
                            }
                            Collection collectionValues = linkedHashMap.values();
                            b4.l.d("<get-values>(...)", collectionValues);
                            return new AssistantSnapshot(q3.l.f0(collectionValues), str2, packageName, c0Var);
                        }
                    }
                    context = context2;
                    i5 = i6;
                }
                context2 = context;
                i6 = i5;
            } catch (Throwable th4) {
                th = th4;
                context2 = context;
                i6 = i5;
            }
            try {
                List<ResolveInfo> listQueryIntentActivities2 = packageManager.queryIntentActivities(new Intent(AssistantSnapshot.ASSIST_ACTION), 0);
                b4.l.d("queryIntentActivities(...)", listQueryIntentActivities2);
                it = listQueryIntentActivities2.iterator();
                while (it.hasNext()) {
                    activityInfo = ((ResolveInfo) it.next()).activityInfo;
                    if (activityInfo == null && (str3 = activityInfo.packageName) != null) {
                        load$add(context2, linkedHashMap, packageManager, i6, str3, "只有 ACTION_ASSIST");
                    }
                }
            } catch (Throwable th5) {
                p3.j.r(th5);
            }
            try {
                objR = Settings.Secure.getString(context2.getContentResolver(), "assistant");
            } catch (Throwable th6) {
                objR = p3.j.r(th6);
            }
            z2 = objR instanceof p3.h;
            c0Var = null;
            obj = objR;
            if (z2) {
                obj = null;
            }
            str = (String) obj;
            if (str == null) {
                componentNameUnflattenFromString = null;
            } else {
                if (h4.g.T(str)) {
                    str = null;
                }
                if (str != null) {
                    componentNameUnflattenFromString = ComponentName.unflattenFromString(str);
                } else {
                    componentNameUnflattenFromString = null;
                }
            }
            if (componentNameUnflattenFromString != null) {
                packageName = componentNameUnflattenFromString.getPackageName();
            } else {
                packageName = null;
            }
            if (packageName != null) {
                Companion companion3 = AssistantSnapshot.Companion;
                objR3 = packageManager.getApplicationLabel(packageManager.getApplicationInfo(packageName, 0)).toString();
                z3 = objR3 instanceof p3.h;
                obj2 = objR3;
                if (z3) {
                    obj2 = null;
                }
                str2 = (String) obj2;
                if (str2 == null) {
                    str2 = "未设置";
                }
            } else {
                str2 = "未设置";
            }
            if (packageName != null) {
                Companion companion4 = AssistantSnapshot.Companion;
                Drawable applicationIcon2 = packageManager.getApplicationIcon(packageName);
                b4.l.d("getApplicationIcon(...)", applicationIcon2);
                objR2 = AssistDataKt.rasterize(applicationIcon2, i6);
                c0Var = (y0.c0) (objR2 instanceof p3.h ? null : objR2);
            }
            Collection collectionValues2 = linkedHashMap.values();
            b4.l.d("<get-values>(...)", collectionValues2);
            return new AssistantSnapshot(q3.l.f0(collectionValues2), str2, packageName, c0Var);
        }
    }

    public AssistantSnapshot(List<AssistantApp> list, String str, String str2, y0.c0 c0Var) {
        b4.l.e("apps", list);
        b4.l.e("defaultLabel", str);
        this.apps = list;
        this.defaultLabel = str;
        this.defaultPackage = str2;
        this.defaultIcon = c0Var;
    }

    /* JADX WARN: Multi-variable type inference failed */
    public static /* synthetic */ AssistantSnapshot copy$default(AssistantSnapshot assistantSnapshot, List list, String str, String str2, y0.c0 c0Var, int i5, Object obj) {
        if ((i5 & 1) != 0) {
            list = assistantSnapshot.apps;
        }
        if ((i5 & 2) != 0) {
            str = assistantSnapshot.defaultLabel;
        }
        if ((i5 & 4) != 0) {
            str2 = assistantSnapshot.defaultPackage;
        }
        if ((i5 & 8) != 0) {
            c0Var = assistantSnapshot.defaultIcon;
        }
        return assistantSnapshot.copy(list, str, str2, c0Var);
    }

    public final List<AssistantApp> component1() {
        return this.apps;
    }

    public final String component2() {
        return this.defaultLabel;
    }

    public final String component3() {
        return this.defaultPackage;
    }

    public final y0.c0 component4() {
        return this.defaultIcon;
    }

    public final AssistantSnapshot copy(List<AssistantApp> list, String str, String str2, y0.c0 c0Var) {
        b4.l.e("apps", list);
        b4.l.e("defaultLabel", str);
        return new AssistantSnapshot(list, str, str2, c0Var);
    }

    public boolean equals(Object obj) {
        if (this == obj) {
            return true;
        }
        if (!(obj instanceof AssistantSnapshot)) {
            return false;
        }
        AssistantSnapshot assistantSnapshot = (AssistantSnapshot) obj;
        return b4.l.a(this.apps, assistantSnapshot.apps) && b4.l.a(this.defaultLabel, assistantSnapshot.defaultLabel) && b4.l.a(this.defaultPackage, assistantSnapshot.defaultPackage) && b4.l.a(this.defaultIcon, assistantSnapshot.defaultIcon);
    }

    public final List<AssistantApp> getApps() {
        return this.apps;
    }

    public final y0.c0 getDefaultIcon() {
        return this.defaultIcon;
    }

    public final String getDefaultLabel() {
        return this.defaultLabel;
    }

    public final String getDefaultPackage() {
        return this.defaultPackage;
    }

    public int hashCode() {
        int iHashCode = (this.defaultLabel.hashCode() + (this.apps.hashCode() * 31)) * 31;
        String str = this.defaultPackage;
        int iHashCode2 = (iHashCode + (str == null ? 0 : str.hashCode())) * 31;
        y0.c0 c0Var = this.defaultIcon;
        return iHashCode2 + (c0Var != null ? c0Var.hashCode() : 0);
    }

    public String toString() {
        return "AssistantSnapshot(apps=" + this.apps + ", defaultLabel=" + this.defaultLabel + ", defaultPackage=" + this.defaultPackage + ", defaultIcon=" + this.defaultIcon + ")";
    }
}
