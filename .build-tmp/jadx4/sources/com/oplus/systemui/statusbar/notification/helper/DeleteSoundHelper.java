package com.oplus.systemui.statusbar.notification.helper;

import android.content.ContentResolver;
import android.content.Context;
import android.database.Cursor;
import android.media.SoundPool;
import android.provider.MediaStore;
import android.text.TextUtils;
import com.android.systemui.ExpandHelper$;
import com.oplus.systemui.statusbar.notification.util.SoundUtil;
import com.oplus.systemui.statusbar.observer.GlobalDeleteSoundObserver;
import com.oplusos.systemui.common.util.LogUtil;
import java.lang.invoke.VarHandle;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

/* JADX INFO: compiled from: go/retraceme fd74bf9514dd0739b92d67a6d48a51eb10aaf8df760d83496b8dcfcf83e5f698 */
/* JADX INFO: loaded from: /home/starlight/Projects/Starlight Ports/.build-tmp/classes4.dex */
public class DeleteSoundHelper {
    public SoundUtil mColorSoundUtil;
    public Context mContext;
    public int mDeleteSoundId;
    public boolean mDeleteSoundSwitch;
    public final AnonymousClass1 mGlobalDeleteSoundObserver = new GlobalDeleteSoundObserver() { // from class: com.oplus.systemui.statusbar.notification.helper.DeleteSoundHelper.1
        @Override // com.oplusos.systemui.common.observer.AbstractObserver
        public final void onChange(boolean z) {
            super.onChange(z);
            DeleteSoundHelper deleteSoundHelper = DeleteSoundHelper.this;
            ExecutorService executorService = deleteSoundHelper.mSoundEexecutor;
            DeleteSoundHelper$$ExternalSyntheticLambda2 deleteSoundHelper$$ExternalSyntheticLambda2 = new DeleteSoundHelper$$ExternalSyntheticLambda2(1);
            deleteSoundHelper$$ExternalSyntheticLambda2.f$0 = deleteSoundHelper;
            VarHandle.storeStoreFence();
            executorService.execute(deleteSoundHelper$$ExternalSyntheticLambda2);
        }
    };
    public boolean mPrepared;
    public ContentResolver mResolver;
    public final ExecutorService mSoundEexecutor;

    /* JADX WARN: Type inference failed for: r0v0, types: [com.oplus.systemui.statusbar.notification.helper.DeleteSoundHelper$1] */
    public DeleteSoundHelper(Context context) {
        ExecutorService executorServiceNewFixedThreadPool = Executors.newFixedThreadPool(1);
        this.mSoundEexecutor = executorServiceNewFixedThreadPool;
        DeleteSoundHelper$$ExternalSyntheticLambda0 deleteSoundHelper$$ExternalSyntheticLambda0 = new DeleteSoundHelper$$ExternalSyntheticLambda0();
        deleteSoundHelper$$ExternalSyntheticLambda0.f$0 = this;
        deleteSoundHelper$$ExternalSyntheticLambda0.f$1 = context;
        VarHandle.storeStoreFence();
        executorServiceNewFixedThreadPool.execute(deleteSoundHelper$$ExternalSyntheticLambda0);
    }

    /* JADX WARN: Code duplicated, block: B:12:0x0059 A[PHI: r4 r6
      0x0059: PHI (r4v4 android.database.Cursor) = (r4v3 android.database.Cursor), (r4v5 android.database.Cursor), (r4v5 android.database.Cursor) binds: [B:23:0x006f, B:11:0x0055, B:15:0x0061] A[DONT_GENERATE, DONT_INLINE]
      0x0059: PHI (r6v4 java.lang.String) = (r6v11 java.lang.String), (r6v7 java.lang.String), (r6v13 java.lang.String) binds: [B:23:0x006f, B:11:0x0055, B:15:0x0061] A[DONT_GENERATE, DONT_INLINE]] */
    /* JADX WARN: Code duplicated, block: B:15:0x0061 A[DONT_INVERT] */
    public final void play() {
        int i;
        Throwable th;
        Cursor cursorQuery;
        StringBuilder sb = new StringBuilder("play: mDeleteSoundSwitch ");
        sb.append(this.mDeleteSoundSwitch);
        sb.append(" mDeleteSoundId: ");
        sb.append(this.mDeleteSoundId);
        sb.append(" mPrepared：");
        ExpandHelper$.ExternalSyntheticOutline0.m(LogUtil.TAG_COMMON, "DeleteSoundManager", sb, this.mPrepared);
        if (this.mDeleteSoundId == 0) {
            Context context = this.mContext;
            String string = null;
            string = null;
            string = null;
            string = null;
            string = null;
            Cursor cursor = null;
            if (!TextUtils.isEmpty("global_delete.ogg")) {
                try {
                    cursorQuery = context.getContentResolver().query(MediaStore.Audio.Media.INTERNAL_CONTENT_URI, new String[]{"_data"}, "_display_name like ?", new String[]{"global_delete.ogg"}, null);
                    if (cursorQuery != null) {
                        try {
                            if (cursorQuery.moveToFirst()) {
                                string = cursorQuery.getString(0);
                            } else if (cursorQuery != null) {
                            }
                        } catch (Exception unused) {
                            if (cursorQuery != null) {
                                cursorQuery.close();
                            }
                        } catch (Throwable th2) {
                            th = th2;
                            cursor = cursorQuery;
                            if (cursor == null) {
                                throw th;
                            }
                            cursor.close();
                            throw th;
                        }
                        cursorQuery.close();
                    } else if (cursorQuery != null) {
                        cursorQuery.close();
                    }
                } catch (Exception unused2) {
                    cursorQuery = null;
                } catch (Throwable th3) {
                    th = th3;
                }
            }
            if (string != null) {
                this.mDeleteSoundId = this.mColorSoundUtil.mSoundPool.load(string, 1);
            }
        }
        if (this.mDeleteSoundSwitch && (i = this.mDeleteSoundId) != 0 && this.mPrepared && this.mColorSoundUtil.mSoundPool.play(i, 1.0f, 1.0f, 1, 0, 1.0f) == 0) {
            LogUtil.d(LogUtil.TAG_COMMON, "DeleteSoundManager", "release");
            this.mPrepared = false;
            this.mDeleteSoundId = 0;
            SoundUtil soundUtil = this.mColorSoundUtil;
            SoundPool soundPool = soundUtil.mSoundPool;
            if (soundPool != null) {
                soundPool.release();
                soundUtil.mSoundMap.clear();
            }
        }
    }
}
