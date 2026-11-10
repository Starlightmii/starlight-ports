package com.oplus.systemui.statusbar;

import android.content.Context;
import android.media.session.ISessionController;
import android.media.session.MediaController;
import android.media.session.MediaSession;
import android.media.session.MediaSessionManager;
import android.media.session.PlaybackState;
import android.os.RemoteException;
import android.os.UserHandle;
import android.service.notification.StatusBarNotification;
import android.support.v4.media.session.MediaSessionCompat;
import android.util.Log;
import com.android.systemui.keyguard.KeyguardSliceProvider;
import com.android.systemui.media.NotificationMediaManager;
import com.android.systemui.media.controls.domain.pipeline.MediaDataManager;
import com.android.systemui.statusbar.OplusNotificationMediaManagerExt;
import com.android.systemui.statusbar.notification.collection.NotifPipeline;
import com.android.systemui.statusbar.notification.collection.NotificationEntry;
import com.oplus.systemui.aod.display.OplusAODRefreshRateController$RequestRefreshRateRunnable$;
import com.oplusos.systemui.common.util.LogUtil;
import com.oplusos.systemui.common.util.ThreadManager;
import com.oplusos.systemui.notification.logger.OplusNotificationLogger;
import dagger.Lazy;
import java.io.PrintWriter;
import java.util.ArrayList;
import java.util.Collection;
import java.util.Iterator;
import java.util.List;
import kotlin.Pair;
import kotlin.collections.CollectionsKt__CollectionsKt;
import kotlin.jvm.internal.Intrinsics;

/* JADX INFO: compiled from: go/retraceme fd74bf9514dd0739b92d67a6d48a51eb10aaf8df760d83496b8dcfcf83e5f698 */
/* JADX INFO: loaded from: /home/starlight/Projects/Starlight Ports/.build-tmp/classes4.dex */
public final class OplusNotificationMediaManagerExtImpl extends OplusNotificationMediaManagerExt {
    public static final Companion Companion = new Companion();
    public static final String TAG = "OplusNotificationMediaManagerExtImpl";
    private final Context context;
    private PlaybackState currentPlaybackState;
    private final MediaDataManager mediaDataManager;
    private final MediaSessionManager mediaSessionManager;
    private final NotifPipeline notifPipeline;
    private final Lazy notificationMediaManagerLazy;
    public final boolean isOplusLogic = true;
    private final List<String> mediaNotificationKeys = new ArrayList();

    /* JADX INFO: compiled from: go/retraceme fd74bf9514dd0739b92d67a6d48a51eb10aaf8df760d83496b8dcfcf83e5f698 */
    public final class Companion {
    }

    public OplusNotificationMediaManagerExtImpl(Context context, MediaDataManager mediaDataManager, MediaSessionManager mediaSessionManager, NotifPipeline notifPipeline, Lazy lazy) {
        this.context = context;
        this.mediaDataManager = mediaDataManager;
        this.mediaSessionManager = mediaSessionManager;
        this.notifPipeline = notifPipeline;
        this.notificationMediaManagerLazy = lazy;
    }

    public static boolean isPlayableState$default(OplusNotificationMediaManagerExtImpl oplusNotificationMediaManagerExtImpl, Integer num) {
        return 3 == num.intValue() || 6 == num.intValue();
    }

    public void dump(PrintWriter printWriter) {
    }

    public void findAndUpdateMediaNotifications(ArrayList<KeyguardSliceProvider> arrayList) {
        Pair pair;
        List<NotificationEntry> listEmptyList;
        StatusBarNotification sbn;
        MediaSession.Token token;
        Collection allNotifs = this.notifPipeline.getAllNotifs();
        ArrayList arrayList2 = new ArrayList();
        for (Object obj : allNotifs) {
            if (((NotificationEntry) obj).getSbn().getNotification().isMediaNotification()) {
                arrayList2.add(obj);
            }
        }
        int size = arrayList2.size();
        boolean zControlsSameSession = false;
        int i = 0;
        while (true) {
            if (i >= size) {
                pair = new Pair((Object) null, (Object) null);
                break;
            }
            Object obj2 = arrayList2.get(i);
            i++;
            NotificationEntry notificationEntry = (NotificationEntry) obj2;
            MediaSessionCompat.Token parcelable = notificationEntry.getSbn().getNotification().extras.getParcelable("android.mediaSession");
            if (parcelable instanceof MediaSession.Token) {
                token = (MediaSession.Token) parcelable;
            } else {
                MediaSessionCompat.Token token2 = parcelable instanceof MediaSessionCompat.Token ? parcelable : null;
                Object token3 = token2 != null ? token2.getToken() : null;
                token = token3 instanceof MediaSession.Token ? (MediaSession.Token) token3 : null;
            }
            if (token != null) {
                MediaController mediaController = new MediaController(this.context, token);
                if (isPlayableState$default(this, Integer.valueOf(getMediaControllerPlaybackState(mediaController, token, notificationEntry)))) {
                    pair = new Pair(mediaController, notificationEntry);
                    break;
                }
            }
        }
        Object objComponent1 = pair.component1();
        Object objComponent2 = pair.component2();
        for (MediaController mediaController2 : getActiveSessionsForUser(this.mediaSessionManager)) {
            NotificationEntry notificationEntry2 = (NotificationEntry) objComponent2;
            if (!Intrinsics.areEqual((notificationEntry2 == null || (sbn = notificationEntry2.getSbn()) == null) ? null : sbn.getPackageName(), mediaController2.getPackageName())) {
                if (isPlayableState$default(this, Integer.valueOf(((NotificationMediaManager) this.notificationMediaManagerLazy.get()).mOplusNotificationMediaManagerExt.getMediaControllerPlaybackState(mediaController2)))) {
                    Collection allNotifs2 = this.notifPipeline.getAllNotifs();
                    ArrayList arrayList3 = new ArrayList();
                    for (Object obj3 : allNotifs2) {
                        NotificationEntry notificationEntry3 = (NotificationEntry) obj3;
                        if (Intrinsics.areEqual(notificationEntry3.getSbn().getPackageName(), mediaController2.getPackageName()) && notificationEntry3.getSbn().getNotification().contentView != null) {
                            arrayList3.add(obj3);
                        }
                    }
                    listEmptyList = arrayList3;
                } else {
                    listEmptyList = CollectionsKt__CollectionsKt.emptyList();
                }
                for (NotificationEntry notificationEntry4 : listEmptyList) {
                    if (objComponent2 == null) {
                        objComponent1 = mediaController2;
                        objComponent2 = notificationEntry4;
                    }
                    if (!this.mediaNotificationKeys.contains(notificationEntry4.getSbn().getKey())) {
                        this.mediaNotificationKeys.add(notificationEntry4.getSbn().getKey());
                    }
                }
            }
        }
        NotificationEntry notificationEntry5 = (NotificationEntry) objComponent2;
        if (notificationEntry5 != null && !Intrinsics.areEqual(notificationEntry5.getSbn().getKey(), ((NotificationMediaManager) this.notificationMediaManagerLazy.get()).getMediaNotificationKey())) {
            ((NotificationMediaManager) this.notificationMediaManagerLazy.get()).mMediaNotificationKey = notificationEntry5.getSbn().getKey();
        }
        MediaController mediaController3 = (MediaController) objComponent1;
        if (mediaController3 != null) {
            NotificationMediaManager notificationMediaManager = (NotificationMediaManager) this.notificationMediaManagerLazy.get();
            MediaController mediaController4 = ((NotificationMediaManager) this.notificationMediaManagerLazy.get()).mMediaController;
            notificationMediaManager.getClass();
            if (mediaController4 == mediaController3) {
                zControlsSameSession = true;
            } else if (mediaController4 != null) {
                zControlsSameSession = mediaController4.controlsSameSession(mediaController3);
            }
            if (!zControlsSameSession) {
                NotificationMediaManager notificationMediaManager2 = (NotificationMediaManager) this.notificationMediaManagerLazy.get();
                notificationMediaManager2.clearCurrentMediaNotificationSession();
                notificationMediaManager2.mMediaController = mediaController3;
                notificationMediaManager2.mMediaController.registerCallback(((NotificationMediaManager) this.notificationMediaManagerLazy.get()).mMediaListener);
                MediaController mediaController5 = ((NotificationMediaManager) this.notificationMediaManagerLazy.get()).mMediaController;
                notificationMediaManager2.mMediaMetadata = mediaController5 != null ? mediaController5.getMetadata() : null;
            }
            ((NotificationMediaManager) this.notificationMediaManagerLazy.get()).dispatchUpdateMediaMetaData();
        }
    }

    public List<MediaController> getActiveSessionsForUser(MediaSessionManager mediaSessionManager) {
        try {
            List<MediaController> activeSessionsForUser = mediaSessionManager.getActiveSessionsForUser(null, UserHandle.ALL);
            OplusNotificationLogger.logMediaPanelNormal(TAG, "DEBUG_MEDIA: ".concat("getActiveSessionsForUser: " + activeSessionsForUser.size()));
            return activeSessionsForUser;
        } catch (Exception e) {
            ((NotificationMediaManager) this.notificationMediaManagerLazy.get()).clearCurrentMediaNotification();
            if (LogUtil.getNormalDebug()) {
                OplusAODRefreshRateController$RequestRefreshRateRunnable$.ExternalSyntheticOutline0.m(8, "getActiveSessionsForUser fail:", e.getMessage(), LogUtil.TAG, TAG);
            }
            return new ArrayList();
        }
    }

    public int getMediaControllerPlaybackState(MediaController mediaController, MediaSession.Token token, NotificationEntry notificationEntry) {
        PlaybackState playbackState;
        if (mediaController != null && token != null) {
            try {
                ISessionController binder = token.getBinder();
                if (binder == null || (playbackState = binder.getPlaybackState()) == null) {
                    return 0;
                }
                return playbackState.getState();
            } catch (RemoteException e) {
                Log.wtf(TAG, "Error calling getPlaybackState.", e);
                ((NotificationMediaManager) this.notificationMediaManagerLazy.get()).clearCurrentMediaNotification();
                ThreadManager.Companion.getInstance().postInMainThread(getMediaControllerPlaybackState.1.INSTANCE);
            }
        }
        return 0;
    }

    public boolean isMediaNotification(NotificationEntry notificationEntry) {
        if (notificationEntry == null) {
            return false;
        }
        if (notificationEntry.getSbn().getNotification().isMediaNotification() || this.mediaNotificationKeys.contains(notificationEntry.getSbn().getKey())) {
            return true;
        }
        if (notificationEntry.getAttachedNotifChildren() == null) {
            return false;
        }
        List attachedNotifChildren = notificationEntry.getAttachedNotifChildren();
        attachedNotifChildren.getClass();
        Iterator it = attachedNotifChildren.iterator();
        while (it.hasNext()) {
            if (isMediaNotification((NotificationEntry) it.next())) {
                return true;
            }
        }
        return false;
    }

    public boolean isOplusCustomMediaNotification(NotificationEntry notificationEntry) {
        return this.mediaDataManager.isOplusCustomMediaNotification(isMediaNotification(notificationEntry), notificationEntry);
    }

    public final boolean isOplusLogic() {
        return this.isOplusLogic;
    }

    public void setPlaybackState(PlaybackState playbackState) {
        this.currentPlaybackState = playbackState;
    }

    public int getMediaControllerPlaybackState(MediaController mediaController) {
        PlaybackState playbackState;
        if (mediaController == ((NotificationMediaManager) this.notificationMediaManagerLazy.get()).mMediaController) {
            playbackState = this.currentPlaybackState;
        } else {
            playbackState = mediaController != null ? mediaController.getPlaybackState() : null;
        }
        if (playbackState == null) {
            return 0;
        }
        return playbackState.getState();
    }

    public void findAndUpdateMediaNotifications(ArrayList<KeyguardSliceProvider> arrayList, PlaybackState playbackState) {
        PlaybackState playbackState2 = this.currentPlaybackState;
        if (playbackState2 == null || playbackState.getState() != playbackState2.getState()) {
            findAndUpdateMediaNotifications(arrayList);
        }
        this.currentPlaybackState = playbackState;
    }
}
