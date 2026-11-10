package com.oplus.systemui.media;

import android.app.Notification;
import android.media.session.MediaSession;
import android.os.Bundle;
import android.os.Parcelable;
import android.support.v4.media.session.MediaSessionCompat;
import com.oplus.systemui.media.controls.pipeline.OplusMediaDataManagerExImpl;

/* JADX INFO: compiled from: go/retraceme fd74bf9514dd0739b92d67a6d48a51eb10aaf8df760d83496b8dcfcf83e5f698 */
/* JADX INFO: loaded from: /home/starlight/Projects/Starlight Ports/.build-tmp/classes4.dex */
public abstract class MediaNotificationExtKt {
    public static final MediaSession.Token getMediaToken(Notification notification) {
        MediaSessionCompat.Token parcelable = notification.extras.getParcelable("android.mediaSession");
        if (parcelable instanceof MediaSession.Token) {
            return (MediaSession.Token) parcelable;
        }
        MediaSessionCompat.Token token = parcelable instanceof MediaSessionCompat.Token ? parcelable : null;
        Object token2 = token != null ? token.getToken() : null;
        if (token2 instanceof MediaSession.Token) {
            return (MediaSession.Token) token2;
        }
        return null;
    }

    public static final MediaSession.Token getOplusCustomMediaToken(Notification notification) {
        Bundle bundle = notification.extras;
        Parcelable parcelable = bundle != null ? bundle.getParcelable(OplusMediaDataManagerExImpl.OPLUS_SESSION_KEY) : null;
        if (parcelable == null) {
            parcelable = null;
        }
        MediaSession.Token token = parcelable instanceof MediaSession.Token ? (MediaSession.Token) parcelable : null;
        if (token != null) {
            return token;
        }
        MediaSessionCompat.Token token2 = parcelable instanceof MediaSessionCompat.Token ? (MediaSessionCompat.Token) parcelable : null;
        Object token3 = token2 != null ? token2.getToken() : null;
        if (token3 instanceof MediaSession.Token) {
            return (MediaSession.Token) token3;
        }
        return null;
    }
}
