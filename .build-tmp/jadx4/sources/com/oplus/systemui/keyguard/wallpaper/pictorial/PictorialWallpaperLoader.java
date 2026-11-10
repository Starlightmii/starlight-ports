package com.oplus.systemui.keyguard.wallpaper.pictorial;

import android.content.Context;
import android.content.res.Resources;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.graphics.drawable.BitmapDrawable;
import android.graphics.drawable.Drawable;
import android.net.Uri;
import android.os.ParcelFileDescriptor;
import android.os.Trace;
import android.text.TextUtils;
import com.android.systemui.DependencyEx;
import com.oplus.charge.viewmodel.OplusChargeAnimController$;
import com.oplus.systemui.keyguard.wallpaper.PubResource;
import com.oplusos.keyguard.utils.FileUtils;
import com.oplusos.keyguard.utils.KgdLogUtil;
import com.oplusos.systemui.common.feature.FlavorOneFeatureOption;
import com.oplusos.systemui.common.feature.KeyguardFeatureOption;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.lang.invoke.VarHandle;
import okio.Path$;

/* JADX INFO: compiled from: go/retraceme fd74bf9514dd0739b92d67a6d48a51eb10aaf8df760d83496b8dcfcf83e5f698 */
/* JADX INFO: loaded from: /home/starlight/Projects/Starlight Ports/.build-tmp/classes4.dex */
public final class PictorialWallpaperLoader {
    public boolean mIsForceUpdate;

    /* JADX INFO: compiled from: go/retraceme fd74bf9514dd0739b92d67a6d48a51eb10aaf8df760d83496b8dcfcf83e5f698 */
    public abstract class HolderInnerClass {
        public static final PictorialWallpaperLoader INSTANCE;

        static {
            PictorialWallpaperLoader pictorialWallpaperLoader = new PictorialWallpaperLoader();
            pictorialWallpaperLoader.mIsForceUpdate = true;
            INSTANCE = pictorialWallpaperLoader;
        }
    }

    /* JADX INFO: compiled from: go/retraceme fd74bf9514dd0739b92d67a6d48a51eb10aaf8df760d83496b8dcfcf83e5f698 */
    public final class WallpaperALTask implements Runnable {
        public final /* synthetic */ int $r8$classId;
        public Context mContext;
        public WallpaperLoadListener mListener;
        public WallpaperLoadRequest mRequest;
        public /* synthetic */ PictorialWallpaperLoader this$0;

        public /* synthetic */ WallpaperALTask(int i) {
            this.$r8$classId = i;
        }

        @Override // java.lang.Runnable
        public final void run() throws Throwable {
            switch (this.$r8$classId) {
                case 0:
                    Trace.traceBegin(8L, "WallpaperALTask");
                    PictorialWallpaperLoader pictorialWallpaperLoader = this.this$0;
                    Context context = this.mContext;
                    WallpaperLoadRequest wallpaperLoadRequest = this.mRequest;
                    pictorialWallpaperLoader.getClass();
                    this.mListener.onLoadWallpaperFinished(this.mRequest, PictorialWallpaperLoader.processLoadWallpaperRequest(context, wallpaperLoadRequest));
                    Trace.traceEnd(8L);
                    break;
                default:
                    Trace.traceBegin(8L, "WallpaperWelcomeTask");
                    Context context2 = this.mContext;
                    BitmapDrawable bitmapDrawable = null;
                    if (this.mRequest != null) {
                        KgdLogUtil.i("PictorialWallpaperLoader", "processLoadWallpaperWelcomeReq");
                        BitmapFactory.Options options = new BitmapFactory.Options();
                        options.inJustDecodeBounds = true;
                        WallpaperLoadRequest.getWelcomeWallpaper(options);
                        int i = options.outWidth;
                        int i2 = options.outHeight;
                        Bitmap bitmapFetchOneFromRecycleBin = PubResource.fetchOneFromRecycleBin();
                        if (bitmapFetchOneFromRecycleBin != null && bitmapFetchOneFromRecycleBin.getWidth() >= i && bitmapFetchOneFromRecycleBin.getHeight() >= i2) {
                            options.inMutable = true;
                            options.inBitmap = bitmapFetchOneFromRecycleBin;
                        }
                        options.inJustDecodeBounds = false;
                        Bitmap welcomeWallpaper = WallpaperLoadRequest.getWelcomeWallpaper(options);
                        if (welcomeWallpaper != null) {
                            KgdLogUtil.i("PictorialWallpaperLoader", "processLoadWallpaperReq,src is not null!");
                            bitmapDrawable = new BitmapDrawable(context2.getResources(), welcomeWallpaper);
                        }
                    }
                    this.mListener.onLoadWallpaperFinished(this.mRequest, bitmapDrawable);
                    Trace.traceEnd(8L);
                    break;
            }
        }
    }

    /* JADX INFO: compiled from: go/retraceme fd74bf9514dd0739b92d67a6d48a51eb10aaf8df760d83496b8dcfcf83e5f698 */
    public interface WallpaperLoadListener {
        void onLoadWallpaperFinished(WallpaperLoadRequest wallpaperLoadRequest, Drawable drawable);
    }

    /* JADX INFO: compiled from: go/retraceme fd74bf9514dd0739b92d67a6d48a51eb10aaf8df760d83496b8dcfcf83e5f698 */
    public final class WallpaperLoadRequest {
        public boolean mSync;
        public Uri mUri;
        public ParcelFileDescriptor mWallPFD;
        public PictorialWallpaperInfo mWallpaperInfo;

        public static Bitmap getWelcomeWallpaper(BitmapFactory.Options options) {
            Bitmap bitmapFromFile;
            Resources pictorialResources = ((PictorialMediator) DependencyEx.sDependency.getDependency(PictorialMediator.class)).getPictorialResources();
            if (pictorialResources == null || (bitmapFromFile = FileUtils.getBitmapFromFile(pictorialResources, ((PictorialMediator) DependencyEx.sDependency.getDependency(PictorialMediator.class)).getWelcomeResourcesId(), options)) == null) {
                return null;
            }
            return bitmapFromFile.copy(Bitmap.Config.RGB_565, true);
        }

        public String getCacheKey() {
            FlavorOneFeatureOption.INSTANCE.getClass();
            if (FlavorOneFeatureOption.isFlavorOneDevice()) {
                KeyguardFeatureOption.INSTANCE.getClass();
                if (KeyguardFeatureOption.isSupportOtherPictorial) {
                    PictorialWallpaperInfo pictorialWallpaperInfo = this.mWallpaperInfo;
                    return pictorialWallpaperInfo != null ? pictorialWallpaperInfo.getFilePath() : "null";
                }
            }
            Uri uri = this.mUri;
            return uri == null ? "null" : uri.toString();
        }

        public ParcelFileDescriptor getParcelFileDescriptor() {
            return this.mWallPFD;
        }

        public PictorialWallpaperInfo getPictorialWallpaperInfo() {
            return this.mWallpaperInfo;
        }

        public Uri getUri() {
            return this.mUri;
        }

        public WallpaperLoadRequest setGauss(boolean z) {
            return this;
        }

        public WallpaperLoadRequest setPictorialWallpaperInfo(PictorialWallpaperInfo pictorialWallpaperInfo) {
            this.mWallpaperInfo = pictorialWallpaperInfo;
            return this;
        }

        public WallpaperLoadRequest setSync(boolean z) {
            this.mSync = z;
            return this;
        }

        public WallpaperLoadRequest setUri(Uri uri) {
            this.mUri = uri;
            return this;
        }

        public WallpaperLoadRequest setWallPFD(ParcelFileDescriptor parcelFileDescriptor) {
            this.mWallPFD = parcelFileDescriptor;
            return this;
        }
    }

    public static PictorialWallpaperLoader getInstance() {
        return HolderInnerClass.INSTANCE;
    }

    public static Drawable loadWallpaperSync(Context context, PictorialWallpaperInfo pictorialWallpaperInfo, ParcelFileDescriptor parcelFileDescriptor, boolean z) {
        if (context == null) {
            Path$.ExternalSyntheticBUOutline0.m$1("why context is null? why? why? check it quickly!");
            return null;
        }
        if (pictorialWallpaperInfo == null) {
            return null;
        }
        return processLoadWallpaperRequest(context, new WallpaperLoadRequest().setGauss(z).setSync(true).setWallPFD(parcelFileDescriptor).setPictorialWallpaperInfo(pictorialWallpaperInfo).setUri(pictorialWallpaperInfo.getFileUri()));
    }

    /* JADX WARN: Code duplicated, block: B:113:? A[RETURN, SYNTHETIC] */
    /* JADX WARN: Code duplicated, block: B:14:0x0044  */
    /* JADX WARN: Code duplicated, block: B:95:0x019a  */
    /* JADX WARN: Code duplicated, block: B:99:0x01b2  */
    public static Drawable processLoadWallpaperRequest(Context context, WallpaperLoadRequest wallpaperLoadRequest) throws Throwable {
        int i;
        FileInputStream fileInputStream;
        FileInputStream fileInputStream2;
        ParcelFileDescriptor parcelFileDescriptorOpenFileDescriptor;
        FileInputStream fileInputStream3;
        Bitmap bitmapDecodeStream;
        Bitmap bitmapCreateCenterCropBitmapIfNeedForPhone;
        Bitmap bitmapFromFile;
        ParcelFileDescriptor parcelFileDescriptor = null;
        if (wallpaperLoadRequest == null) {
            return null;
        }
        KgdLogUtil.i("PictorialWallpaperLoader", "processLoadWallpaperReq Filepath " + wallpaperLoadRequest.getCacheKey());
        BitmapFactory.Options options = new BitmapFactory.Options();
        options.inJustDecodeBounds = true;
        int i2 = options.outWidth;
        int i3 = options.outHeight;
        Bitmap bitmapFetchOneFromRecycleBin = PubResource.fetchOneFromRecycleBin();
        if (bitmapFetchOneFromRecycleBin != null && bitmapFetchOneFromRecycleBin.getWidth() >= i2 && bitmapFetchOneFromRecycleBin.getHeight() >= i3) {
            options.inMutable = true;
            options.inBitmap = bitmapFetchOneFromRecycleBin;
        }
        options.inJustDecodeBounds = false;
        if (context != null) {
            PictorialWallpaperInfo pictorialWallpaperInfo = wallpaperLoadRequest.mWallpaperInfo;
            if (pictorialWallpaperInfo != null) {
                wallpaperLoadRequest.mUri = pictorialWallpaperInfo.getFileUri();
            }
            Uri uri = wallpaperLoadRequest.mUri;
            if (uri == null) {
                bitmapCreateCenterCropBitmapIfNeedForPhone = null;
            } else {
                String strValueOf = String.valueOf(uri);
                if (TextUtils.isEmpty(strValueOf) || "null".equals(strValueOf)) {
                    bitmapCreateCenterCropBitmapIfNeedForPhone = null;
                } else {
                    if (strValueOf.startsWith("pictorial-inlay-resID:")) {
                        try {
                            i = Integer.parseInt(strValueOf.replace("pictorial-inlay-resID:", ""));
                        } catch (NumberFormatException unused) {
                            i = -1;
                        }
                    } else {
                        i = -1;
                    }
                    if (i != -1) {
                        Resources pictorialResources = ((PictorialMediator) DependencyEx.sDependency.getDependency(PictorialMediator.class)).getPictorialResources();
                        if (pictorialResources == null || (bitmapFromFile = FileUtils.getBitmapFromFile(pictorialResources, i, options)) == null) {
                            bitmapCreateCenterCropBitmapIfNeedForPhone = null;
                        } else {
                            bitmapCreateCenterCropBitmapIfNeedForPhone = PictorialUtils.createCenterCropBitmapIfNeedForPhone(context, bitmapFromFile, Bitmap.Config.RGB_565);
                        }
                    } else {
                        Uri uri2 = wallpaperLoadRequest.mUri;
                        ParcelFileDescriptor parcelFileDescriptor2 = wallpaperLoadRequest.mWallPFD;
                        if (uri2 == null) {
                            KgdLogUtil.d("FileUtils", "getBitmapFromUri, uri is null and return null");
                        } else {
                            try {
                                if (parcelFileDescriptor2 != null) {
                                    try {
                                        fileInputStream2 = new FileInputStream(parcelFileDescriptor2.getFileDescriptor());
                                        try {
                                            bitmapDecodeStream = BitmapFactory.decodeStream(null, null, options);
                                            if (bitmapDecodeStream != null) {
                                                FileUtils.closeFd(parcelFileDescriptor2);
                                                FileUtils.closeInputStream(fileInputStream2);
                                            }
                                        } catch (FileNotFoundException e) {
                                            e = e;
                                            parcelFileDescriptorOpenFileDescriptor = null;
                                            fileInputStream3 = null;
                                            KgdLogUtil.w("FileUtils", "getBitmapFromUri, couldn't open pictorial " + uri2 + "; " + e);
                                            FileUtils.closeFd(parcelFileDescriptor2);
                                            FileUtils.closeFd(parcelFileDescriptorOpenFileDescriptor);
                                            FileUtils.closeInputStream(fileInputStream2);
                                            FileUtils.closeInputStream(fileInputStream3);
                                            bitmapDecodeStream = null;
                                            if (bitmapDecodeStream == null) {
                                                bitmapCreateCenterCropBitmapIfNeedForPhone = null;
                                            } else {
                                                bitmapCreateCenterCropBitmapIfNeedForPhone = PictorialUtils.createCenterCropBitmapIfNeedForPhone(context, bitmapDecodeStream, bitmapDecodeStream.getConfig());
                                            }
                                            if (bitmapCreateCenterCropBitmapIfNeedForPhone == null) {
                                                return null;
                                            }
                                            KgdLogUtil.i("PictorialWallpaperLoader", "processLoadWallpaperReq,src is not null!");
                                            return new BitmapDrawable(context.getResources(), bitmapCreateCenterCropBitmapIfNeedForPhone);
                                        } catch (Exception e2) {
                                            e = e2;
                                            parcelFileDescriptorOpenFileDescriptor = null;
                                            fileInputStream3 = null;
                                            KgdLogUtil.w("FileUtils", "getBitmapFromUri, exception when get " + uri2 + "; " + e);
                                            FileUtils.closeFd(parcelFileDescriptor2);
                                            FileUtils.closeFd(parcelFileDescriptorOpenFileDescriptor);
                                            FileUtils.closeInputStream(fileInputStream2);
                                            FileUtils.closeInputStream(fileInputStream3);
                                            bitmapDecodeStream = null;
                                            if (bitmapDecodeStream == null) {
                                                bitmapCreateCenterCropBitmapIfNeedForPhone = null;
                                            } else {
                                                bitmapCreateCenterCropBitmapIfNeedForPhone = PictorialUtils.createCenterCropBitmapIfNeedForPhone(context, bitmapDecodeStream, bitmapDecodeStream.getConfig());
                                            }
                                            if (bitmapCreateCenterCropBitmapIfNeedForPhone == null) {
                                                return null;
                                            }
                                            KgdLogUtil.i("PictorialWallpaperLoader", "processLoadWallpaperReq,src is not null!");
                                            return new BitmapDrawable(context.getResources(), bitmapCreateCenterCropBitmapIfNeedForPhone);
                                        } catch (OutOfMemoryError e3) {
                                            e = e3;
                                            parcelFileDescriptorOpenFileDescriptor = null;
                                            fileInputStream3 = null;
                                            KgdLogUtil.w("FileUtils", "getBitmapFromUri, failed to allocate memory for pictorial " + uri2 + "; " + e);
                                            FileUtils.closeFd(parcelFileDescriptor2);
                                            FileUtils.closeFd(parcelFileDescriptorOpenFileDescriptor);
                                            FileUtils.closeInputStream(fileInputStream2);
                                            FileUtils.closeInputStream(fileInputStream3);
                                            bitmapDecodeStream = null;
                                            if (bitmapDecodeStream == null) {
                                                bitmapCreateCenterCropBitmapIfNeedForPhone = null;
                                            } else {
                                                bitmapCreateCenterCropBitmapIfNeedForPhone = PictorialUtils.createCenterCropBitmapIfNeedForPhone(context, bitmapDecodeStream, bitmapDecodeStream.getConfig());
                                            }
                                            if (bitmapCreateCenterCropBitmapIfNeedForPhone == null) {
                                                return null;
                                            }
                                            KgdLogUtil.i("PictorialWallpaperLoader", "processLoadWallpaperReq,src is not null!");
                                            return new BitmapDrawable(context.getResources(), bitmapCreateCenterCropBitmapIfNeedForPhone);
                                        } catch (Throwable th) {
                                            th = th;
                                            fileInputStream = null;
                                            FileUtils.closeFd(parcelFileDescriptor2);
                                            FileUtils.closeFd(parcelFileDescriptor);
                                            FileUtils.closeInputStream(fileInputStream2);
                                            FileUtils.closeInputStream(fileInputStream);
                                            throw th;
                                        }
                                    } catch (FileNotFoundException e4) {
                                        e = e4;
                                        parcelFileDescriptorOpenFileDescriptor = null;
                                        fileInputStream2 = null;
                                        fileInputStream3 = null;
                                        KgdLogUtil.w("FileUtils", "getBitmapFromUri, couldn't open pictorial " + uri2 + "; " + e);
                                        FileUtils.closeFd(parcelFileDescriptor2);
                                        FileUtils.closeFd(parcelFileDescriptorOpenFileDescriptor);
                                        FileUtils.closeInputStream(fileInputStream2);
                                        FileUtils.closeInputStream(fileInputStream3);
                                        bitmapDecodeStream = null;
                                        if (bitmapDecodeStream == null) {
                                            bitmapCreateCenterCropBitmapIfNeedForPhone = null;
                                        } else {
                                            bitmapCreateCenterCropBitmapIfNeedForPhone = PictorialUtils.createCenterCropBitmapIfNeedForPhone(context, bitmapDecodeStream, bitmapDecodeStream.getConfig());
                                        }
                                        if (bitmapCreateCenterCropBitmapIfNeedForPhone == null) {
                                            return null;
                                        }
                                        KgdLogUtil.i("PictorialWallpaperLoader", "processLoadWallpaperReq,src is not null!");
                                        return new BitmapDrawable(context.getResources(), bitmapCreateCenterCropBitmapIfNeedForPhone);
                                    } catch (Exception e5) {
                                        e = e5;
                                        parcelFileDescriptorOpenFileDescriptor = null;
                                        fileInputStream2 = null;
                                        fileInputStream3 = null;
                                        KgdLogUtil.w("FileUtils", "getBitmapFromUri, exception when get " + uri2 + "; " + e);
                                        FileUtils.closeFd(parcelFileDescriptor2);
                                        FileUtils.closeFd(parcelFileDescriptorOpenFileDescriptor);
                                        FileUtils.closeInputStream(fileInputStream2);
                                        FileUtils.closeInputStream(fileInputStream3);
                                        bitmapDecodeStream = null;
                                        if (bitmapDecodeStream == null) {
                                            bitmapCreateCenterCropBitmapIfNeedForPhone = null;
                                        } else {
                                            bitmapCreateCenterCropBitmapIfNeedForPhone = PictorialUtils.createCenterCropBitmapIfNeedForPhone(context, bitmapDecodeStream, bitmapDecodeStream.getConfig());
                                        }
                                        if (bitmapCreateCenterCropBitmapIfNeedForPhone == null) {
                                            return null;
                                        }
                                        KgdLogUtil.i("PictorialWallpaperLoader", "processLoadWallpaperReq,src is not null!");
                                        return new BitmapDrawable(context.getResources(), bitmapCreateCenterCropBitmapIfNeedForPhone);
                                    } catch (OutOfMemoryError e6) {
                                        e = e6;
                                        parcelFileDescriptorOpenFileDescriptor = null;
                                        fileInputStream2 = null;
                                        fileInputStream3 = null;
                                        KgdLogUtil.w("FileUtils", "getBitmapFromUri, failed to allocate memory for pictorial " + uri2 + "; " + e);
                                        FileUtils.closeFd(parcelFileDescriptor2);
                                        FileUtils.closeFd(parcelFileDescriptorOpenFileDescriptor);
                                        FileUtils.closeInputStream(fileInputStream2);
                                        FileUtils.closeInputStream(fileInputStream3);
                                        bitmapDecodeStream = null;
                                        if (bitmapDecodeStream == null) {
                                            bitmapCreateCenterCropBitmapIfNeedForPhone = null;
                                        } else {
                                            bitmapCreateCenterCropBitmapIfNeedForPhone = PictorialUtils.createCenterCropBitmapIfNeedForPhone(context, bitmapDecodeStream, bitmapDecodeStream.getConfig());
                                        }
                                        if (bitmapCreateCenterCropBitmapIfNeedForPhone == null) {
                                            return null;
                                        }
                                        KgdLogUtil.i("PictorialWallpaperLoader", "processLoadWallpaperReq,src is not null!");
                                        return new BitmapDrawable(context.getResources(), bitmapCreateCenterCropBitmapIfNeedForPhone);
                                    } catch (Throwable th2) {
                                        th = th2;
                                        fileInputStream2 = null;
                                        fileInputStream = null;
                                        FileUtils.closeFd(parcelFileDescriptor2);
                                        FileUtils.closeFd(parcelFileDescriptor);
                                        FileUtils.closeInputStream(fileInputStream2);
                                        FileUtils.closeInputStream(fileInputStream);
                                        throw th;
                                    }
                                    if (bitmapDecodeStream == null) {
                                        bitmapCreateCenterCropBitmapIfNeedForPhone = null;
                                    } else {
                                        bitmapCreateCenterCropBitmapIfNeedForPhone = PictorialUtils.createCenterCropBitmapIfNeedForPhone(context, bitmapDecodeStream, bitmapDecodeStream.getConfig());
                                    }
                                } else {
                                    fileInputStream2 = null;
                                }
                                parcelFileDescriptorOpenFileDescriptor = context.getContentResolver().openFileDescriptor(uri2, "r");
                                if (parcelFileDescriptorOpenFileDescriptor != null) {
                                    try {
                                        fileInputStream3 = new FileInputStream(parcelFileDescriptorOpenFileDescriptor.getFileDescriptor());
                                        try {
                                            Bitmap bitmapDecodeStream2 = BitmapFactory.decodeStream(fileInputStream3, null, options);
                                            FileUtils.closeFd(parcelFileDescriptor2);
                                            FileUtils.closeFd(parcelFileDescriptorOpenFileDescriptor);
                                            FileUtils.closeInputStream(fileInputStream2);
                                            FileUtils.closeInputStream(fileInputStream3);
                                            bitmapDecodeStream = bitmapDecodeStream2;
                                        } catch (FileNotFoundException e7) {
                                            e = e7;
                                            KgdLogUtil.w("FileUtils", "getBitmapFromUri, couldn't open pictorial " + uri2 + "; " + e);
                                            FileUtils.closeFd(parcelFileDescriptor2);
                                            FileUtils.closeFd(parcelFileDescriptorOpenFileDescriptor);
                                            FileUtils.closeInputStream(fileInputStream2);
                                            FileUtils.closeInputStream(fileInputStream3);
                                            bitmapDecodeStream = null;
                                        } catch (Exception e8) {
                                            e = e8;
                                            KgdLogUtil.w("FileUtils", "getBitmapFromUri, exception when get " + uri2 + "; " + e);
                                            FileUtils.closeFd(parcelFileDescriptor2);
                                            FileUtils.closeFd(parcelFileDescriptorOpenFileDescriptor);
                                            FileUtils.closeInputStream(fileInputStream2);
                                            FileUtils.closeInputStream(fileInputStream3);
                                            bitmapDecodeStream = null;
                                        } catch (OutOfMemoryError e9) {
                                            e = e9;
                                            KgdLogUtil.w("FileUtils", "getBitmapFromUri, failed to allocate memory for pictorial " + uri2 + "; " + e);
                                            FileUtils.closeFd(parcelFileDescriptor2);
                                            FileUtils.closeFd(parcelFileDescriptorOpenFileDescriptor);
                                            FileUtils.closeInputStream(fileInputStream2);
                                            FileUtils.closeInputStream(fileInputStream3);
                                            bitmapDecodeStream = null;
                                        }
                                    } catch (FileNotFoundException e10) {
                                        e = e10;
                                        fileInputStream3 = null;
                                    } catch (Exception e11) {
                                        e = e11;
                                        fileInputStream3 = null;
                                    } catch (OutOfMemoryError e12) {
                                        e = e12;
                                        fileInputStream3 = null;
                                    } catch (Throwable th3) {
                                        th = th3;
                                        fileInputStream = null;
                                        parcelFileDescriptor = parcelFileDescriptorOpenFileDescriptor;
                                        FileUtils.closeFd(parcelFileDescriptor2);
                                        FileUtils.closeFd(parcelFileDescriptor);
                                        FileUtils.closeInputStream(fileInputStream2);
                                        FileUtils.closeInputStream(fileInputStream);
                                        throw th;
                                    }
                                } else {
                                    FileUtils.closeFd(parcelFileDescriptor2);
                                    FileUtils.closeFd(parcelFileDescriptorOpenFileDescriptor);
                                    FileUtils.closeInputStream(fileInputStream2);
                                }
                                if (bitmapDecodeStream == null) {
                                    bitmapCreateCenterCropBitmapIfNeedForPhone = null;
                                } else {
                                    bitmapCreateCenterCropBitmapIfNeedForPhone = PictorialUtils.createCenterCropBitmapIfNeedForPhone(context, bitmapDecodeStream, bitmapDecodeStream.getConfig());
                                }
                            } catch (Throwable th4) {
                                th = th4;
                            }
                        }
                        bitmapDecodeStream = null;
                        if (bitmapDecodeStream == null) {
                            bitmapCreateCenterCropBitmapIfNeedForPhone = null;
                        } else {
                            bitmapCreateCenterCropBitmapIfNeedForPhone = PictorialUtils.createCenterCropBitmapIfNeedForPhone(context, bitmapDecodeStream, bitmapDecodeStream.getConfig());
                        }
                    }
                }
            }
        } else {
            bitmapCreateCenterCropBitmapIfNeedForPhone = null;
        }
        if (bitmapCreateCenterCropBitmapIfNeedForPhone == null) {
            return null;
        }
        KgdLogUtil.i("PictorialWallpaperLoader", "processLoadWallpaperReq,src is not null!");
        return new BitmapDrawable(context.getResources(), bitmapCreateCenterCropBitmapIfNeedForPhone);
    }

    /* JADX WARN: Multi-variable type inference failed */
    public final void loadWallpaper(Context context, WallpaperLoadRequest wallpaperLoadRequest, WallpaperLoadListener wallpaperLoadListener) throws Throwable {
        if (context == null) {
            KgdLogUtil.w("PictorialWallpaperLoader", "loadWallpaper, context = null");
            return;
        }
        if (wallpaperLoadRequest == null) {
            KgdLogUtil.w("PictorialWallpaperLoader", "loadWallpaper, req = null");
            return;
        }
        Object[] objArr = 0;
        if (wallpaperLoadRequest.mSync) {
            Drawable drawableProcessLoadWallpaperRequest = processLoadWallpaperRequest(context, wallpaperLoadRequest);
            OplusChargeAnimController$.ExternalSyntheticOutline0.m("PictorialWallpaperLoader", new StringBuilder("loadWallpaper, isSuccess = "), drawableProcessLoadWallpaperRequest != null);
            wallpaperLoadListener.onLoadWallpaperFinished(wallpaperLoadRequest, drawableProcessLoadWallpaperRequest);
            return;
        }
        WallpaperALTask wallpaperALTask = new WallpaperALTask(objArr == true ? 1 : 0);
        wallpaperALTask.this$0 = this;
        wallpaperALTask.mContext = context;
        wallpaperALTask.mRequest = wallpaperLoadRequest;
        wallpaperALTask.mListener = wallpaperLoadListener;
        VarHandle.storeStoreFence();
        PictorialUtils.runOnWorkThread(wallpaperALTask);
    }

    public final void loadWallpaperWelcome(Context context, WallpaperLoadRequest wallpaperLoadRequest, WallpaperLoadListener wallpaperLoadListener) {
        if (context == null) {
            KgdLogUtil.w("PictorialWallpaperLoader", "loadWallpaperWelcome, context is null");
            return;
        }
        if (wallpaperLoadRequest == null) {
            KgdLogUtil.w("PictorialWallpaperLoader", "loadWallpaperWelcome, req is null");
            return;
        }
        WallpaperALTask wallpaperALTask = new WallpaperALTask(1);
        wallpaperALTask.this$0 = this;
        wallpaperALTask.mContext = context;
        wallpaperALTask.mRequest = wallpaperLoadRequest;
        wallpaperALTask.mListener = wallpaperLoadListener;
        VarHandle.storeStoreFence();
        PictorialUtils.runOnWorkThread(wallpaperALTask);
    }

    public void setForceUpdate(boolean z) {
        this.mIsForceUpdate = z;
    }
}
