package com.oplusos.systemui.common.observer;

import android.content.ContentResolver;
import android.database.ContentObserver;
import android.net.Uri;
import android.os.UserHandle;
import android.util.Log;
import java.util.ArrayList;

/* JADX INFO: compiled from: go/retraceme fd74bf9514dd0739b92d67a6d48a51eb10aaf8df760d83496b8dcfcf83e5f698 */
/* JADX INFO: loaded from: /home/starlight/Projects/Starlight Ports/.build-tmp/classes4.dex */
public final class UriObserver {
    public ArrayList mClients;
    public AnonymousClass1 mRealObserver;
    public boolean mRegister;
    public Uri mUri;

    /* JADX INFO: compiled from: go/retraceme fd74bf9514dd0739b92d67a6d48a51eb10aaf8df760d83496b8dcfcf83e5f698 */
    public final class UserUriObserver {
        public ArrayList mObservers;
        public int mUid;
    }

    public final String toString() {
        int i;
        StringBuilder sb = new StringBuilder();
        synchronized (this.mClients) {
            try {
                ArrayList arrayList = this.mClients;
                int size = arrayList.size();
                i = 0;
                int i2 = 0;
                while (i2 < size) {
                    Object obj = arrayList.get(i2);
                    i2++;
                    UserUriObserver userUriObserver = (UserUriObserver) obj;
                    sb.append(" >>>> uid=" + userUriObserver.mUid);
                    ArrayList arrayList2 = userUriObserver.mObservers;
                    int size2 = arrayList2.size();
                    int i3 = 0;
                    while (i3 < size2) {
                        Object obj2 = arrayList2.get(i3);
                        i3++;
                        sb.append("[" + ((ContentObserver) obj2).toString() + "]");
                        i++;
                    }
                }
            } catch (Throwable th) {
                throw th;
            }
        }
        return "UriObserver{register=" + this.mRegister + ", uri=" + this.mUri + ", clientAmount=" + i + sb.toString() + '}';
    }

    public final boolean unregisterObserver(ContentResolver contentResolver, ContentObserver contentObserver) {
        boolean z = true;
        boolean z2 = UserHandle.myUserId() == 0;
        synchronized (this.mClients) {
            try {
                ArrayList arrayList = new ArrayList();
                ArrayList arrayList2 = this.mClients;
                int size = arrayList2.size();
                int i = 0;
                while (i < size) {
                    Object obj = arrayList2.get(i);
                    i++;
                    UserUriObserver userUriObserver = (UserUriObserver) obj;
                    userUriObserver.mObservers.remove(contentObserver);
                    if (userUriObserver.mObservers.size() <= 0) {
                        arrayList.add(userUriObserver);
                    }
                }
                this.mClients.removeAll(arrayList);
                if (this.mClients.isEmpty()) {
                    z = z2;
                }
            } catch (Throwable th) {
                throw th;
            }
        }
        if (!z && this.mRegister) {
            synchronized (this) {
                try {
                    contentResolver.unregisterContentObserver(this.mRealObserver);
                    this.mRegister = false;
                } catch (Exception e) {
                    Log.d("UriObserver", "unregisterObserver Exception uri" + this.mUri + "   " + e.toString());
                }
            }
        }
        return this.mRegister;
    }
}
