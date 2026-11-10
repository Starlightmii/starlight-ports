package com.oplusos.systemui.common.multiuser;

import android.content.Context;
import android.os.Handler;
import android.os.UserHandle;
import androidx.room.util.CursorUtil$;
import com.oplusos.systemui.common.util.LogUtil;
import java.util.ArrayList;
import java.util.ConcurrentModificationException;
import java.util.concurrent.CopyOnWriteArrayList;
import kotlin.jvm.functions.Function1;

/* JADX INFO: compiled from: go/retraceme fd74bf9514dd0739b92d67a6d48a51eb10aaf8df760d83496b8dcfcf83e5f698 */
/* JADX INFO: loaded from: /home/starlight/Projects/Starlight Ports/.build-tmp/classes4.dex */
public final class MultiUserManager {
    public static final Companion Companion = new Companion();
    private static volatile MultiUserManager sInstance;
    private final CopyOnWriteArrayList<IMultiUserListener> mListeners = new CopyOnWriteArrayList<>();

    /* JADX INFO: compiled from: go/retraceme fd74bf9514dd0739b92d67a6d48a51eb10aaf8df760d83496b8dcfcf83e5f698 */
    public final class Companion {
        public final MultiUserManager getInstance() {
            if (MultiUserManager.sInstance == null) {
                synchronized (MultiUserManager.class) {
                    if (MultiUserManager.sInstance == null) {
                        MultiUserManager.sInstance = new MultiUserManager();
                    }
                }
            }
            MultiUserManager multiUserManager = MultiUserManager.sInstance;
            multiUserManager.getClass();
            return multiUserManager;
        }
    }

    public static final MultiUserManager getInstance() {
        return Companion.getInstance();
    }

    public final void addListener(IMultiUserListener iMultiUserListener) {
        this.mListeners.add(iMultiUserListener);
    }

    public final void dispatchToListeners(String str, Function1 function1) {
        ArrayList arrayList = new ArrayList(this.mListeners);
        int size = arrayList.size();
        int i = 0;
        while (i < size) {
            Object obj = arrayList.get(i);
            i++;
            IMultiUserListener iMultiUserListener = (IMultiUserListener) obj;
            try {
                iMultiUserListener.getClass();
                function1.invoke(iMultiUserListener);
            } catch (ConcurrentModificationException e) {
                LogUtil.e(LogUtil.TAG_COMMON, "MultiUserManager", CursorUtil$.ExternalSyntheticOutline0.m("ConcurrentModificationException in ", str, ": ", iMultiUserListener.getClass().getName()), e);
            }
        }
    }

    public final void init(Context context) {
        MultiUserReceiver.Companion.getInstance().register(context, UserHandle.ALL, (Handler) null);
        MultiUserContentHelper.Companion.getClass();
        Companion.getInstance().addListener(MultiUserContentHelper.LISTENER);
    }

    public final void removeListener(IMultiUserListener iMultiUserListener) {
        this.mListeners.remove(iMultiUserListener);
    }
}
