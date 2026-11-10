package com.oplusos.systemui.common.observer;

import android.content.ComponentName;
import android.content.Context;
import android.net.Uri;
import android.provider.Settings;
import android.text.TextUtils;
import com.oplus.systemui.notification.row.click.NotificationClickEffectControllerImpl;
import com.oplusos.systemui.common.multiuser.MultiUserContentHelper;
import java.lang.invoke.VarHandle;
import java.util.Collections;
import java.util.HashSet;
import java.util.Iterator;
import java.util.Set;

/* JADX INFO: compiled from: go/retraceme fd74bf9514dd0739b92d67a6d48a51eb10aaf8df760d83496b8dcfcf83e5f698 */
/* JADX INFO: loaded from: /home/starlight/Projects/Starlight Ports/.build-tmp/classes4.dex */
public final class TalkbackObserver extends AbstractObserver {
    public static final String ACTION_TALKBACK_SWITCH_CHAGNED = "app.intent.action.TALKBACK_SWITCH_CHAGNED";
    public static final Companion Companion = new Companion();
    public static final String EXTRA_TALKBACK_ENABLED = "talkback_enabled";
    private static final String TAG = "TalkbackObserver";
    private static final String TALKBACK_PACKAGE = "com.google.android.marvin.talkback";
    public static volatile TalkbackObserver sInstance;
    private final IObserverListener listener;

    /* JADX INFO: compiled from: go/retraceme fd74bf9514dd0739b92d67a6d48a51eb10aaf8df760d83496b8dcfcf83e5f698 */
    public final class Companion {
        public final TalkbackObserver getInstance() {
            if (TalkbackObserver.sInstance == null) {
                synchronized (TalkbackObserver.class) {
                    if (TalkbackObserver.sInstance == null) {
                        TalkbackObserver.sInstance = new TalkbackObserver();
                    }
                }
            }
            TalkbackObserver talkbackObserver = TalkbackObserver.sInstance;
            if (talkbackObserver == null) {
                return null;
            }
            return talkbackObserver;
        }

        public final boolean isTalkbackEnabled(Context context) {
            Set set;
            String stringForUser = Settings.Secure.getStringForUser(context.getContentResolver(), NotificationClickEffectControllerImpl.TALKBACK, context.getUserId());
            if (TextUtils.isEmpty(stringForUser)) {
                set = Collections.EMPTY_SET;
            } else {
                HashSet hashSet = new HashSet();
                TextUtils.SimpleStringSplitter simpleStringSplitter = new TextUtils.SimpleStringSplitter(':');
                simpleStringSplitter.setString(stringForUser);
                Iterator<String> it = simpleStringSplitter.iterator();
                while (it.hasNext()) {
                    ComponentName componentNameUnflattenFromString = ComponentName.unflattenFromString(it.next());
                    if (componentNameUnflattenFromString != null) {
                        hashSet.add(componentNameUnflattenFromString);
                    }
                }
                set = hashSet;
            }
            if (set != null && !set.isEmpty()) {
                Iterator it2 = set.iterator();
                while (it2.hasNext()) {
                    if (TextUtils.equals(((ComponentName) it2.next()).getPackageName(), TalkbackObserver.TALKBACK_PACKAGE)) {
                        return true;
                    }
                }
            }
            return false;
        }
    }

    public TalkbackObserver() {
        TalkbackObserver$listener$1 talkbackObserver$listener$1 = new TalkbackObserver$listener$1();
        talkbackObserver$listener$1.this$0 = this;
        VarHandle.storeStoreFence();
        this.listener = talkbackObserver$listener$1;
        addListener(talkbackObserver$listener$1);
    }

    @Override // com.oplusos.systemui.common.observer.AbstractObserver
    public String getKey() {
        return NotificationClickEffectControllerImpl.TALKBACK;
    }

    @Override // com.oplusos.systemui.common.observer.AbstractObserver
    public Uri getUri(Context context) {
        return MultiUserContentHelper.Companion.getSecureUri(getKey());
    }
}
