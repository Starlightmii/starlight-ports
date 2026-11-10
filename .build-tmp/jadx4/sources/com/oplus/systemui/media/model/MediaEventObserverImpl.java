package com.oplus.systemui.media.model;

import android.os.Handler;
import android.os.Looper;
import android.os.Message;
import com.android.systemui.media.controls.pipeline.OplusMediaDataManagerEx;
import com.oplus.media.OplusMediaEvent;
import com.oplus.media.OplusMediaEventObserver;
import com.oplus.media.OplusMediaPlayerInfoCollection;
import com.oplusos.systemui.notification.logger.OplusNotificationLogger;
import java.lang.invoke.VarHandle;

/* JADX INFO: compiled from: go/retraceme fd74bf9514dd0739b92d67a6d48a51eb10aaf8df760d83496b8dcfcf83e5f698 */
/* JADX INFO: loaded from: /home/starlight/Projects/Starlight Ports/.build-tmp/classes4.dex */
public final class MediaEventObserverImpl extends OplusMediaEventObserver {
    public static final Companion Companion = new Companion();
    private static final int MEDIA_PLAYER_INFO_CHANGED = 16384;
    private static final String TAG = "MediaEventObserverImpl";
    private final OplusMediaDataManagerEx.OplusMediaDataCallBack callBack;
    private final Handler handler;

    /* JADX INFO: compiled from: go/retraceme fd74bf9514dd0739b92d67a6d48a51eb10aaf8df760d83496b8dcfcf83e5f698 */
    public final class Companion {
    }

    public MediaEventObserverImpl(OplusMediaDataManagerEx.OplusMediaDataCallBack oplusMediaDataCallBack) {
        this.callBack = oplusMediaDataCallBack;
        Looper mainLooper = Looper.getMainLooper();
        MediaEventObserverImpl$handler$1 mediaEventObserverImpl$handler$1 = new MediaEventObserverImpl$handler$1();
        mediaEventObserverImpl$handler$1.this$0 = this;
        VarHandle.storeStoreFence();
        this.handler = new Handler(mainLooper, mediaEventObserverImpl$handler$1);
    }

    private final void dealHandlerMsg(Message message, OplusMediaDataManagerEx.OplusMediaDataCallBack oplusMediaDataCallBack) {
        if (message.what == MEDIA_PLAYER_INFO_CHANGED) {
            Object obj = message.obj;
            if (obj instanceof OplusMediaPlayerInfoCollection) {
                oplusMediaDataCallBack.onPlayerInfoChange(((OplusMediaPlayerInfoCollection) obj).getMediaPlayerInfos());
            }
        }
    }

    public void onEvent(OplusMediaEvent oplusMediaEvent) {
        OplusNotificationLogger.logMediaPanelNormal(TAG, "onEvent: EventType: " + oplusMediaEvent.getEventType());
        if (oplusMediaEvent.getEventType() == 6) {
            Message messageObtain = Message.obtain();
            messageObtain.what = MEDIA_PLAYER_INFO_CHANGED;
            messageObtain.obj = oplusMediaEvent.getData();
            this.handler.sendMessage(messageObtain);
        }
    }

    /* JADX INFO: Access modifiers changed from: private */
    public final boolean dealHandlerMsg(Message message) {
        dealHandlerMsg(message, this.callBack);
        return true;
    }
}
