package com.oplus.systemui.util;

import android.content.Context;
import android.util.IndentingPrintWriter;
import android.util.Log;
import androidx.room.util.CursorUtil$;
import com.android.systemui.Dumpable;
import com.android.systemui.dump.DumpManager;
import com.android.systemui.util.MessageQueueDumpUtilEx;
import java.io.PrintWriter;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;
import kotlin.jvm.internal.Intrinsics;

/* JADX INFO: compiled from: go/retraceme fd74bf9514dd0739b92d67a6d48a51eb10aaf8df760d83496b8dcfcf83e5f698 */
/* JADX INFO: loaded from: /home/starlight/Projects/Starlight Ports/.build-tmp/classes4.dex */
public final class MessageQueueDumpUtilExImpl implements MessageQueueDumpUtilEx, Dumpable {
    public static final Companion Companion = new Companion();
    private static final String TAG = "MessageQueueDumpUtilExImpl";
    private final Context context;
    private final DumpManager dumpManager;
    private List<MessageData> messageDataList = new ArrayList();
    private final SimpleDateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss.SSS");

    /* JADX INFO: compiled from: go/retraceme fd74bf9514dd0739b92d67a6d48a51eb10aaf8df760d83496b8dcfcf83e5f698 */
    public final class Companion {
    }

    /* JADX INFO: compiled from: go/retraceme fd74bf9514dd0739b92d67a6d48a51eb10aaf8df760d83496b8dcfcf83e5f698 */
    public final class MessageData {
        private final String stackTrace;
        private final String timeStamp;

        public MessageData(String str, String str2) {
            this.timeStamp = str;
            this.stackTrace = str2;
        }

        public final String component1() {
            return this.timeStamp;
        }

        public final String component2() {
            return this.stackTrace;
        }

        public final MessageData copy(String str, String str2) {
            return new MessageData(str, str2);
        }

        public boolean equals(Object obj) {
            if (this == obj) {
                return true;
            }
            if (!(obj instanceof MessageData)) {
                return false;
            }
            MessageData messageData = (MessageData) obj;
            return Intrinsics.areEqual(this.timeStamp, messageData.timeStamp) && Intrinsics.areEqual(this.stackTrace, messageData.stackTrace);
        }

        public final String getStackTrace() {
            return this.stackTrace;
        }

        public final String getTimeStamp() {
            return this.timeStamp;
        }

        public final int hashCode() {
            String str = this.timeStamp;
            int iHashCode = (str == null ? 0 : str.hashCode()) * 31;
            String str2 = this.stackTrace;
            return iHashCode + (str2 != null ? str2.hashCode() : 0);
        }

        public String toString() {
            return CursorUtil$.ExternalSyntheticOutline0.m("timeStamp:", this.timeStamp, "\nstackTrace:\n", this.stackTrace);
        }
    }

    public MessageQueueDumpUtilExImpl(Context context, DumpManager dumpManager) {
        this.context = context;
        this.dumpManager = dumpManager;
        dumpManager.registerDumpable(TAG, this);
    }

    public void dump(PrintWriter printWriter, String[] strArr) {
        if (this.messageDataList.size() > 0) {
            if (printWriter instanceof IndentingPrintWriter) {
                ((IndentingPrintWriter) printWriter).increaseIndent();
            }
            printWriter.println("MessageQueueDumpUtilExImpl Dump start");
            Log.w(TAG, "MessageQueueDumpUtilExImpl Dump start");
            Iterator<T> it = this.messageDataList.iterator();
            while (it.hasNext()) {
                String string = ((MessageData) it.next()).toString();
                printWriter.println(string);
                Log.w(TAG, string);
            }
            printWriter.println("MessageQueueDumpUtilExImpl Dump end");
            Log.w(TAG, "MessageQueueDumpUtilExImpl Dump end");
            if (printWriter instanceof IndentingPrintWriter) {
                ((IndentingPrintWriter) printWriter).decreaseIndent();
            }
            this.messageDataList.clear();
        }
    }

    public final Context getContext() {
        return this.context;
    }

    public final DumpManager getDumpManager() {
        return this.dumpManager;
    }

    public void printStackTrace(String str) {
        MessageData messageData = new MessageData(this.dateFormat.format(Long.valueOf(System.currentTimeMillis())), Log.getStackTraceString(new Throwable(CursorUtil$.ExternalSyntheticOutline0.m("Only the original thread that created a view hierarchy can touch its views. Expected: ", str, " Calling: ", Thread.currentThread().getName()))));
        Log.e(TAG, messageData.toString());
        synchronized (MessageQueueDumpUtilExImpl.class) {
            try {
                if (this.messageDataList.size() >= 10) {
                    this.messageDataList.remove(0);
                }
                this.messageDataList.add(messageData);
            } catch (Throwable th) {
                throw th;
            }
        }
    }
}
