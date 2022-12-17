package com.itheima.pattern.bridge;

/**
 * 扩展抽象化角色（Mac）
 */
public class Mac extends OpratingSystem {
    public Mac(VideoFile videoFile) {
        super(videoFile);
    }

    @Override
    public void play(String fileName) {
        videoFile.decode(fileName);
    }
}
