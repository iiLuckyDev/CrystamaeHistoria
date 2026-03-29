/*
 * Decompiled with CFR 0.152.
 */
package io.github.sefiraat.crystamaehistoria.slikey.effectlib.util;

import io.github.sefiraat.crystamaehistoria.slikey.effectlib.EffectManager;
import io.github.sefiraat.crystamaehistoria.slikey.effectlib.util.ImageLoadCallback;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.FileOutputStream;
import java.net.HttpURLConnection;
import java.net.URL;
import java.net.URLEncoder;
import java.util.logging.Level;
import javax.imageio.ImageIO;

public class ImageLoadTask
implements Runnable {
    private static final int REQUEST_TIMEOUT = 30000;
    private static final int BUFFER_SIZE = 10240;
    private static boolean dirsMade = false;
    private final String fileName;
    private final ImageLoadCallback callback;
    private final EffectManager effectManager;

    public ImageLoadTask(EffectManager manager, String fileName, ImageLoadCallback callback) {
        this.fileName = fileName;
        this.callback = callback;
        this.effectManager = manager;
    }

    /*
     * Unable to fully structure code
     */
    @Override
    public void run() {
        if (this.fileName.startsWith("http")) {
            try {
                cacheFolder = this.effectManager.getImageCacheFolder();
                if (cacheFolder == null) {
                    this.effectManager.getLogger().log(Level.WARNING, "Can't load from URL because no cache folder has been set by the owning plugin: " + this.fileName);
                    this.callback.loaded(new BufferedImage[0]);
                    return;
                }
                if (!ImageLoadTask.dirsMade) {
                    ImageLoadTask.dirsMade = true;
                    if (!cacheFolder.exists() && !cacheFolder.mkdirs()) {
                        this.effectManager.onError("Could not create cache folder: " + cacheFolder.getAbsolutePath());
                    }
                }
                if ((imageFile = new File(cacheFolder, cacheFileName = URLEncoder.encode(this.fileName, "UTF-8"))).exists()) ** GOTO lbl36
                imageUrl = new URL(this.fileName);
                conn = (HttpURLConnection)imageUrl.openConnection();
                conn.setConnectTimeout(30000);
                conn.setReadTimeout(30000);
                conn.setInstanceFollowRedirects(true);
                in = conn.getInputStream();
                out = new FileOutputStream(imageFile);
                buffer = new byte[10240];
                while ((len = in.read(buffer)) != -1) {
                    out.write(buffer, 0, len);
                }
                out.close();
            }
            catch (Exception ex) {
                this.effectManager.getLogger().log(Level.WARNING, "Failed to load file " + this.fileName, ex);
                this.callback.loaded(new BufferedImage[0]);
                return;
            }
        } else if (!this.fileName.startsWith(File.pathSeparator)) {
            imageFile = new File(this.effectManager.getOwningPlugin().getDataFolder(), this.fileName);
            if (!imageFile.exists()) {
                imageFile = new File(this.fileName);
            }
        } else {
            imageFile = new File(this.fileName);
        }
lbl36:
        // 4 sources

        if (!imageFile.exists()) {
            this.effectManager.getLogger().log(Level.WARNING, "Failed to find file " + this.fileName);
            images = new BufferedImage[]{};
            this.callback.loaded(images);
            return;
        }
        try {
            if (this.fileName.endsWith(".gif")) {
                reader = ImageIO.getImageReadersBySuffix("GIF").next();
                in = ImageIO.createImageInputStream(imageFile);
                reader.setInput(in);
                numImages = reader.getNumImages(true);
                images = new BufferedImage[numImages];
                count = numImages;
                for (i = 0; i < count; ++i) {
                    images[i] = reader.read(i);
                }
            } else {
                images = new BufferedImage[]{ImageIO.read(imageFile)};
            }
        }
        catch (Exception ex) {
            this.effectManager.getLogger().log(Level.WARNING, "Failed to load file " + this.fileName, ex);
            images = new BufferedImage[]{};
        }
        this.callback.loaded(images);
    }
}

