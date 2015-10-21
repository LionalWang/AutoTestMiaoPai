package com.miaopai.test;

import com.android.uiautomator.core.*;
import com.android.uiautomator.testrunner.UiAutomatorTestCase;

public class TestFilter extends UiAutomatorTestCase {

    public void testFilter() {
        //回到首页,打开秒拍
        UiDevice.getInstance().pressHome();
        UiObject miaoPaiIcon = new UiObject(new UiSelector().text("秒拍"));
        try {
            miaoPaiIcon.click();
        } catch (UiObjectNotFoundException e) {
            System.out.println("错误:首页没有秒拍!!!");
        }
        System.out.println("打开秒拍成功");
        sleep(5000);

        String[] name = new String[]{"无", "美颜", "碧空", "日系", "清透", "文艺",
                "白夜", "橄榄树", "复古", "冰川", "暖阳", "森林", "栗子", "Lomo"};

        for (int i=0; i<14; i++) {
            //录制
            record();

            //点击滤镜选项
            UiObject filterOption = new UiObject(new UiSelector().resourceId("com.yixia.videoeditor:id/tab_filter"));
            try {
                filterOption.click();
            } catch (UiObjectNotFoundException e) {
                System.out.println("点击滤镜选项失败");
            }


            //选择滤镜
            UiObject filter = new UiObject(new UiSelector().text(name[i]));
            while (!filter.exists()) {
                swipeToLeft();
            }
            try {
                filter.click();
                System.out.println("选择"+name[i]);
            } catch (UiObjectNotFoundException e) {
                System.out.println("点击"+name[i]+"滤镜失败");
            }

            //发布
            publish();
        }
    }


    /**向左滑动**/
    public void swipeToLeft() {
        UiObject swipeToL = new UiObject(new UiSelector().resourceId("com.yixia.videoeditor:id/filter_layout"));
        try {
            swipeToL.swipeLeft(200);
        } catch (UiObjectNotFoundException e) {
            e.printStackTrace();
        }
    }


    /**秒拍首页开始,点击底部中间按钮,切换到60s,然后录制10s**/
    public void record() {
        //点击录制按钮
        UiObject recordButton = new UiObject(new UiSelector().resourceId("com.yixia.videoeditor:id/bottom_record"));
        try {
            recordButton.click();
        } catch (UiObjectNotFoundException e) {
            System.out.println("错误:进入录制界面失败!!!");
        }
        System.out.println("进入录制界面成功");
        sleep(2000);

        //切换到60s
        UiObject set60s = new UiObject(new UiSelector().resourceId("com.yixia.videoeditor:id/record_60s"));
        try {
            set60s.click();
        } catch (UiObjectNotFoundException e) {
            System.out.println("切换到60s失败");
        }
        System.out.println("切换到60s成功");

        //按住开始录制
        UiObject recordStartButton = new UiObject(new UiSelector().resourceId("com.yixia.videoeditor:id/record_controller"));
        try {
            recordStartButton.swipeUp(300);
        } catch (UiObjectNotFoundException e) {
            System.out.println("错误:无法点击开始录制!!!");
        }
        System.out.println("录制10s视频成功");

        //点击下一步
        UiObject next = new UiObject(new UiSelector().resourceId("com.yixia.videoeditor:id/title_next"));
        try {
            next.click();
        } catch (UiObjectNotFoundException e) {
            System.out.println("点击下一步失败");
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
