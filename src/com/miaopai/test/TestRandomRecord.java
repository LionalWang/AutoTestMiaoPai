package com.miaopai.test;

import android.os.RemoteException;
import com.android.uiautomator.core.*;
import com.android.uiautomator.testrunner.UiAutomatorTestCase;


public class TestRandomRecord extends UiAutomatorTestCase {
    public void testRecordRandom() throws RemoteException {
        //准备
        UiDevice.getInstance().sleep();
        sleep(2000);

        //判断是否亮屏,若没有则点亮屏幕
        if (!UiDevice.getInstance().isScreenOn()) {
            UiDevice.getInstance().wakeUp();
        }
        sleep(2000);

        //上划解锁
        UiDevice.getInstance().swipe(500, 1000, 500, 200, 10);
        sleep(1000);

        //回到首页,打开秒拍
        UiDevice.getInstance().pressHome();
        UiObject miaoPaiIcon = new UiObject(new UiSelector().text("秒拍"));
        try {
            miaoPaiIcon.click();
        } catch (UiObjectNotFoundException e) {
            System.out.println("错误:首页没有秒拍!!!");
        }
        System.out.println("打开秒拍成功");

        while (true) {
            //进入录制页
            enterRecord();

            boolean isCaremaBack = true;
            boolean isLightOn = false;

            //切换到60s
            UiObject set60s = new UiObject(new UiSelector().resourceId("com.yixia.videoeditor:id/record_60s"));
            try {
                set60s.click();
            } catch (UiObjectNotFoundException e) {
                System.out.println("切换到60s失败");
            }
            System.out.println("切换到60s成功");

            //随机本次录制的总时长录制
            int length = (int) (Math.random()*57+4);
            int times = (int) (Math.random()*10+1);
            int step = (((length*1000)/times)/20);
            System.out.println("本次要分"+times+"次录制"+length + "s的视频");
            for (int i=0; i<times; i++) {

                int camera = (int) (Math.random()*2);
                int light = (int) (Math.random()*2);

                if (camera>0) {
                    if (isCaremaBack) {
                        clickCamera();
                        isCaremaBack = false;
                        System.out.println("转换成前置摄像头");
                        isLightOn = false;
                        System.out.println("关闭闪光灯");
                    } else {
                        clickCamera();
                        isCaremaBack = true;
                        System.out.println("转换成后置摄像头");
                    }
                }

                if (light>0) {
                    if (isCaremaBack) {
                        if (isLightOn) {
                            clickLight();
                            isLightOn = false;
                            System.out.println("关闭闪光灯");
                        } else {
                            clickLight();
                            isLightOn = true;
                            System.out.println("开启闪光灯");
                        }
                    }
                }
                longPress(step);
            }

            //点击下一步
            UiObject next = new UiObject(new UiSelector().resourceId("com.yixia.videoeditor:id/title_next"));
            try {
                next.click();
            } catch (UiObjectNotFoundException e) {
                System.out.println("点击下一步失败");
            }

            //发布
            publish();

        }

    }


    /**进入录制页面**/
    public void enterRecord() {
        //点击录制按钮
        UiObject recordButton = new UiObject(new UiSelector().resourceId("com.yixia.videoeditor:id/bottom_record"));
        try {
            recordButton.click();
        } catch (UiObjectNotFoundException e) {
            System.out.println("错误:进入录制界面失败!!!");
        }
        System.out.println("进入录制界面成功");
        sleep(2000);
    }


    /**录制一段视频
     * 每录制1s,需要172step**/
    public void longPress(int step) {
        //按住开始录制
        UiObject recordStartButton = new UiObject(new UiSelector().resourceId("com.yixia.videoeditor:id/record_controller"));
        try {
            recordStartButton.swipeUp(step);
        } catch (UiObjectNotFoundException e) {
            System.out.println("错误:无法点击开始录制!!!");
        }
        System.out.println("录制" + (step * 20) + "ms视频成功");
    }


    /**点击摄像头**/
    public void clickCamera() {
        UiObject pressCaremaButton = new UiObject(new UiSelector().resourceId("com.yixia.videoeditor:id/record_camera_switcher"));
        try {
            pressCaremaButton.click();
        } catch (UiObjectNotFoundException e) {
            System.out.println("点击摄像头失败");
        }
    }


    /**点击闪光灯**/
    public void clickLight() {
        UiObject pressLightButton = new UiObject(new UiSelector().resourceId("com.yixia.videoeditor:id/record_camera_led"));
        try {
            pressLightButton.click();
        } catch (UiObjectNotFoundException e) {
            System.out.println("点击闪关灯失败");
        }
    }


    /**合成视频,发布,关闭分享弹框**/
    public void publish() {

        //视频合成
        UiObject videoFinishButton = new UiObject(new UiSelector().resourceId("com.yixia.videoeditor:id/titleRight"));
        try {
            videoFinishButton.clickAndWaitForNewWindow();
        } catch (UiObjectNotFoundException e) {
            System.out.println("错误:视频合成出错!!!或可能是下载特效失败!!!");
        }

        //判断视频是否在处理中,若在处理则等待
        UiObject isHandling = new UiObject(new UiSelector().textContains("视频处理中"));
        while (isHandling.exists()){
            System.out.println("处理中...");
            sleep(3000);
        }

        System.out.println("点击保存视频成功");
        sleep(3000);

        //发布
        UiObject videoSendButton = new UiObject(new UiSelector().resourceId("com.yixia.videoeditor:id/share_video"));
        try {
            videoSendButton.clickAndWaitForNewWindow();
        } catch (UiObjectNotFoundException e) {
            System.out.println("错误:视频发布出错!!!或可能是视频合成失败");
        }
        System.out.println("发布视频成功");

        //关闭分享窗
        Configurator.getInstance().setWaitForSelectorTimeout(120000);
        UiObject closeShareButton = new UiObject(new UiSelector().resourceId("com.yixia.videoeditor:id/upload_suc_dialog_close"));
        try {
            closeShareButton.click();
        } catch (UiObjectNotFoundException e) {
            System.out.println("错误:关闭分享窗出错!!!");
        }
        System.out.println("关闭发布框成功");
        Configurator.getInstance().setWaitForSelectorTimeout(60000);
    }

}
