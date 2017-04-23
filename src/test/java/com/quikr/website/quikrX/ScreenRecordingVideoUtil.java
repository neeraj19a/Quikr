package com.quikr.website.quikrX;

import org.monte.media.Format;
import org.monte.media.Registry;
import org.monte.screenrecorder.ScreenRecorder;

import java.awt.*;
import java.io.File;
import java.io.IOException;
import java.text.SimpleDateFormat;
import java.util.Date;

/**
 * Created by francis.squikr on 28/7/16.
 */
public class ScreenRecordingVideoUtil extends ScreenRecorder {

    private String name;

    public ScreenRecordingVideoUtil(GraphicsConfiguration cfg,
                                    Rectangle captureArea, Format fileFormat, Format screenFormat,
                                    Format mouseFormat, Format audioFormat, File movieFolder,
                                    String name) throws IOException, AWTException {
        super(cfg, captureArea, fileFormat, screenFormat, mouseFormat,
                audioFormat, movieFolder);
        this.name = name;
    }


    @Override
    protected File createMovieFile(Format fileFormat) throws IOException {
        if (!movieFolder.exists()) {
            movieFolder.mkdirs();
        } else if (!movieFolder.isDirectory()) {
            throw new IOException("\"" + movieFolder + "\" is not a directory.");
        }

        SimpleDateFormat dateFormat = new SimpleDateFormat(
                "yyyy-MM-dd HH.mm.ss");

        return new File(movieFolder, name + "-" + dateFormat.format(new Date()) + "."
                + Registry.getInstance().getExtension(fileFormat));
    }

}



