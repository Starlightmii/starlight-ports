package com.oplus.systemui.notification.notificationcenter.antivoyeur;

import android.content.Context;
import android.net.Uri;
import android.provider.Settings;
import kotlin.jvm.functions.Function1;

/* JADX INFO: compiled from: go/retraceme fd74bf9514dd0739b92d67a6d48a51eb10aaf8df760d83496b8dcfcf83e5f698 */
/* JADX INFO: loaded from: /home/starlight/Projects/Starlight Ports/.build-tmp/classes4.dex */
public interface UriProviderObserver {
    public static final Companion Companion = Companion.$$INSTANCE;

    /* JADX INFO: compiled from: go/retraceme fd74bf9514dd0739b92d67a6d48a51eb10aaf8df760d83496b8dcfcf83e5f698 */
    public final class Companion {
        public static final /* synthetic */ Companion $$INSTANCE = new Companion();
        private static final String KEY_FACE_ENROLL = "key_face_templates_enrolled";
        private static final String KEY_FACE_FOR_ENCRYPT_APPS_UNLOCK = "oplus_customize_face_unlock_app_encryption_switch";
        private static final String URI_ENCRYPT_APPS_OBSERVER = "content://com.oplus.provider.SafeProvider/app_encrypt_data_changed";

        public final UriProviderObserver createEncryptAppsObserver(Function1 function1) {
            return new UriProviderObserverImpl(Uri.parse(URI_ENCRYPT_APPS_OBSERVER), function1);
        }

        public final UriProviderObserver createFaceEnrollObserver(Function1 function1) {
            return new UriProviderObserverImpl(Settings.Secure.getUriFor(KEY_FACE_ENROLL), function1);
        }

        public final UriProviderObserver createFaceForEncryptAppObserver(Function1 function1) {
            return new UriProviderObserverImpl(Settings.Secure.getUriFor(KEY_FACE_FOR_ENCRYPT_APPS_UNLOCK), function1);
        }
    }

    void register(Context context);

    void unRegister(Context context);
}
