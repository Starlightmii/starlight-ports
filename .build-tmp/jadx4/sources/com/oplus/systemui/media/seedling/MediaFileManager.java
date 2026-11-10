package com.oplus.systemui.media.seedling;

import android.content.Context;
import com.oplus.pantanal.seedling.file.FileShareHelper;
import com.oplusos.systemui.notification.logger.OplusNotificationLogger;
import java.io.File;

/* JADX INFO: compiled from: go/retraceme fd74bf9514dd0739b92d67a6d48a51eb10aaf8df760d83496b8dcfcf83e5f698 */
/* JADX INFO: loaded from: /home/starlight/Projects/Starlight Ports/.build-tmp/classes4.dex */
public final class MediaFileManager {
    public static final Companion Companion = new Companion();
    private static final String TAG = "MediaFileManager";
    private static volatile MediaFileManager instance;

    /* JADX INFO: compiled from: go/retraceme fd74bf9514dd0739b92d67a6d48a51eb10aaf8df760d83496b8dcfcf83e5f698 */
    public final class Companion {
        public final MediaFileManager getInstance() {
            MediaFileManager mediaFileManager;
            MediaFileManager mediaFileManager2 = MediaFileManager.instance;
            if (mediaFileManager2 != null) {
                return mediaFileManager2;
            }
            synchronized (this) {
                mediaFileManager = MediaFileManager.instance;
                if (mediaFileManager == null) {
                    mediaFileManager = new MediaFileManager();
                    MediaFileManager.instance = mediaFileManager;
                }
            }
            return mediaFileManager;
        }
    }

    public static void deleteAllFileInner(File file) {
        File[] fileArrListFiles = file.listFiles();
        fileArrListFiles.getClass();
        for (File file2 : fileArrListFiles) {
            if (file2.isDirectory()) {
                deleteAllFileInner(file2);
            } else {
                file2.delete();
            }
        }
    }

    public final void deleteAllFile(Context context) {
        File defaultShareFileDir = FileShareHelper.INSTANCE.getDefaultShareFileDir(context);
        OplusNotificationLogger.logMediaSeedlingDebug(TAG, "remove all files. path = " + defaultShareFileDir);
        if (defaultShareFileDir.exists()) {
            deleteAllFileInner(defaultShareFileDir);
            OplusNotificationLogger.logMediaSeedlingDebug(TAG, "remove all files seccess");
        } else {
            OplusNotificationLogger.logMediaSeedlingDebug(TAG, "path = " + defaultShareFileDir + " is not exists");
        }
    }
}
