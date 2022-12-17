package com.itheima.pattern.bridge;

/**
 * 扩展抽象化角色（Windows）
 */
public class Windows extends OpratingSystem {
    public Windows(VideoFile videoFile) {
        super(videoFile);
    }

    @Override
    public void play(String fileName) {
        videoFile.decode(fileName);
    }
}
