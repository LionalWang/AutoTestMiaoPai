package com.miaopai.test;

import android.os.RemoteException;
import com.android.uiautomator.core.*;
import com.android.uiautomator.testrunner.UiAutomatorTestCase;

import java.io.File;


public class Test extends UiAutomatorTestCase{
    public void testRecord() throws RemoteException{
        Configurator.getInstance().setWaitForSelectorTimeout(120000);


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

        //回到首页,打开秒拍
        UiDevice.getInstance().pressHome();
        UiObject miaoPaiIcon = new UiObject(new UiSelector().text("秒拍"));
        try {
            miaoPaiIcon.click();
        } catch (UiObjectNotFoundException e) {
            System.out.println("错误:首页没有秒拍!!!");
        }
        sleep(5000);


        //特效循环测试
        for (int i=0; i<54; i++) {
            //调用录制方法
            record();

            //选择推荐特效
            if (i>=0 && i<3) {
                for (int j=1; j<4; j++) {
                    useSpecial(j);
                    shootScreen(i,j);
                }
            } else if (i>=3 && i<6) {
                //选择明星特效
                for (int j=1; j<4; j++) {
                    UiObject selectSpecialItemButton = new UiObject(new UiSelector().text("com.yixia.videoeditor:id/radio_button2"));
                    try {
                        selectSpecialItemButton.click();
                    } catch (UiObjectNotFoundException e) {
                        System.out.println("错误:选择特效类别时出错");
                    }
                    useSpecial(j);
                    shootScreen(i,j);
                }
            }

            //调用发布方法
            publish();

        }
    }


    /**秒拍首页开始,点击底部中间按钮,然后录制10s**/
    public void record() {
        //点击录制按钮
        UiObject recordButton = new UiObject(new UiSelector().resourceId("com.yixia.videoeditor:id/bottom_record"));
        try {
            recordButton.click();
        } catch (UiObjectNotFoundException e) {
            System.out.println("错误:进入录制界面失败!!!");
        }
        sleep(2000);

        //按住开始录制
        UiObject recordStartButton = new UiObject(new UiSelector().resourceId("com.yixia.videoeditor:id/record_controller"));
        try {
            recordStartButton.swipeUp(600);
        } catch (UiObjectNotFoundException e) {
            System.out.println("错误:无法点击开始录制!!!");
        }
        sleep(3000);
    }


    /**合成视频,发布,关闭分享弹框**/
    public void publish() {
        //视频合成
        UiObject videoFinishButton = new UiObject(new UiSelector().resourceId("com.yixia.videoeditor:id/titleRight"));
        try {
            videoFinishButton.clickAndWaitForNewWindow();
        } catch (UiObjectNotFoundException e) {
            System.out.println("错误:视频合成出错!!!");
        }
        sleep(3000);

        //发布
        UiObject videoSendButton = new UiObject(new UiSelector().resourceId("com.yixia.videoeditor:id/share_video"));
        try {
            videoSendButton.clickAndWaitForNewWindow();
        } catch (UiObjectNotFoundException e) {
            System.out.println("错误:视频发布出错!!!");
        }

        //关闭分享窗
        UiObject closeShareButton = new UiObject(new UiSelector().resourceId("com.yixia.videoeditor:id/upload_suc_dialog_close"));
        try {
            closeShareButton.click();
        } catch (UiObjectNotFoundException e) {
            System.out.println("错误:关闭分享窗出错!!!");
        }
    }


    /**选择特效,下载或使用**/
    public void useSpecial(int num) {
        //录制完成,点击更多特效
        UiObject moreSpecialButton = new UiObject(new UiSelector().text("更多特效"));
        try {
            moreSpecialButton.clickAndWaitForNewWindow();
        } catch (UiObjectNotFoundException e) {
            System.out.println("错误:无法点击更多特效!!!");
        }
        sleep(2000);

        UiObject useSpecialButton = new UiObject(new UiSelector().resourceId("com.yixia.videoeditor:id/play"+num));
        try {
            useSpecialButton.click();
        } catch (UiObjectNotFoundException e) {
            System.out.println("错误:选择特效时出错!!!");
        }
        sleep(2000);

        //使用或先下载再使用
        UiObject useNowButton = new UiObject(new UiSelector().text("立即使用"));
        try {
            useNowButton.clickAndWaitForNewWindow();
        } catch (UiObjectNotFoundException e) {
            System.out.println("错误:使用特效时出错!!!");
        }
        sleep(2000);
    }


    /**截图**/
    public void shootScreen(int i, int j) {
        //截图
        UiDevice.getInstance().takeScreenshot(new File("/data/local/tmp/"+i+"_"+j+".png"));
        sleep(1000);
    }
}
