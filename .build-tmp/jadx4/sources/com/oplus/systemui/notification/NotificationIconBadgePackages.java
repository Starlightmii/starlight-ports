package com.oplus.systemui.notification;

import android.content.Context;
import android.net.Uri;
import android.os.Handler;
import android.provider.Settings;
import com.oplusos.systemui.common.feature.FeatureOption;
import com.oplusos.systemui.common.observer.AbstractObserver;
import com.oplusos.systemui.common.util.LogUtil;
import com.oplusos.systemui.common.util.SystemUIThreadUtil;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;
import java.util.Set;
import java.util.function.Function;
import kotlin.Lazy;
import kotlin.LazyKt__LazyJVMKt;
import kotlin.LazyThreadSafetyMode;
import kotlin.collections.CollectionsKt__IterablesKt;
import kotlin.collections.CollectionsKt___CollectionsKt;
import kotlin.collections.SetsKt__SetsKt;
import kotlin.text.StringsKt__StringsKt;

/* JADX INFO: compiled from: go/retraceme fd74bf9514dd0739b92d67a6d48a51eb10aaf8df760d83496b8dcfcf83e5f698 */
/* JADX INFO: loaded from: /home/starlight/Projects/Starlight Ports/.build-tmp/classes4.dex */
public final class NotificationIconBadgePackages extends AbstractObserver {
    public static final String SETTINGS_SYSTEMUI_ICON_BADGE_PACKAGES = "systemui_icon_badge_packages";
    private static final String TAG = "NotificationIconBadgePackages";
    private volatile Set<String> cachedPackages;
    public static final Companion Companion = new Companion();
    private static final Lazy instance$delegate = LazyKt__LazyJVMKt.lazy(LazyThreadSafetyMode.SYNCHRONIZED, new NotificationIconBadgePackages$$ExternalSyntheticLambda0());

    /* JADX INFO: compiled from: go/retraceme fd74bf9514dd0739b92d67a6d48a51eb10aaf8df760d83496b8dcfcf83e5f698 */
    public final class Companion {
        public static /* synthetic */ void getInstance$annotations() {
        }

        public final NotificationIconBadgePackages getInstance() {
            return (NotificationIconBadgePackages) NotificationIconBadgePackages.instance$delegate.getValue();
        }
    }

    public static NotificationIconBadgePackages $r8$lambda$9rcGgBXjabkAYpgJvnFGMmXbs2Q() {
        NotificationIconBadgePackages notificationIconBadgePackages = new NotificationIconBadgePackages();
        notificationIconBadgePackages.cachedPackages = SetsKt__SetsKt.emptySet();
        return notificationIconBadgePackages;
    }

    public static final NotificationIconBadgePackages getInstance() {
        return Companion.getInstance();
    }

    public final boolean contains(String str) {
        FeatureOption.INSTANCE.getClass();
        return (FeatureOption.isExpRegion() || str == null || !this.cachedPackages.contains(str)) ? false : true;
    }

    @Override // com.oplusos.systemui.common.observer.AbstractObserver
    public Handler getHandler() {
        return SystemUIThreadUtil.getWorkHandler();
    }

    @Override // com.oplusos.systemui.common.observer.AbstractObserver
    public String getKey() {
        return SETTINGS_SYSTEMUI_ICON_BADGE_PACKAGES;
    }

    @Override // com.oplusos.systemui.common.observer.AbstractObserver
    public Function<String, Uri> getUri() {
        return AbstractObserver.Companion.toGlobalUri();
    }

    @Override // com.oplusos.systemui.common.observer.AbstractObserver
    public final void onChange(boolean z) {
        super.onChange(z);
        Context context = getContext();
        if (context != null) {
            reloadFromSettings(context);
        }
    }

    @Override // com.oplusos.systemui.common.observer.AbstractObserver
    public void onRegister(Context context) {
        reloadFromSettings(context);
    }

    /* JADX WARN: Code duplicated, block: B:22:0x0083  */
    public final void reloadFromSettings(Context context) {
        Set<String> setEmptySet;
        FeatureOption.INSTANCE.getClass();
        if (FeatureOption.isExpRegion()) {
            return;
        }
        String string = Settings.Global.getString(context.getContentResolver(), SETTINGS_SYSTEMUI_ICON_BADGE_PACKAGES);
        if (string == null || StringsKt__StringsKt.isBlank(string)) {
            setEmptySet = null;
        } else {
            int i = 0;
            List listSplit$default = StringsKt__StringsKt.split$default(string, new char[]{','}, 6);
            ArrayList arrayList = new ArrayList(CollectionsKt__IterablesKt.collectionSizeOrDefault(listSplit$default, 10));
            Iterator it = listSplit$default.iterator();
            while (it.hasNext()) {
                arrayList.add(StringsKt__StringsKt.trim((String) it.next()).toString());
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
            setEmptySet = CollectionsKt___CollectionsKt.toSet(arrayList2);
            if (setEmptySet.isEmpty()) {
                setEmptySet = null;
            }
        }
        if (setEmptySet == null) {
            setEmptySet = SetsKt__SetsKt.emptySet();
        }
        this.cachedPackages = setEmptySet;
        LogUtil.i$default(LogUtil.TAG_NOTIFICATION, TAG, "reloadFromSettings packages=" + this.cachedPackages, null, 8);
    }
}
