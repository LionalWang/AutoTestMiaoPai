package com.miaopai.test;

import android.os.RemoteException;
import com.android.uiautomator.core.*;
import com.android.uiautomator.testrunner.UiAutomatorTestCase;

import java.io.File;


public class Test extends UiAutomatorTestCase{
    public void testRecord() throws RemoteException{
        Configurator.getInstance().setWaitForSelectorTimeout(60000);

        /**
         * //准备
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
         **/

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


        //特效循环测试
        for (int i=0; i<8; i++) {

            //选择推荐特效
            if (i==0) {
                for (int j=0; j<9; j++) {

                    int layout = 0;

                    if (j>=0 && j<3) {
                        layout = 0;
                    } else if (j>=3 && j<6) {
                        layout = 1;
                    } else if (j>=6 && j<9) {
                        layout = 2;
                    }

                    //调用录制方法
                    record();

                    //点击更多特效
                    clickMoreSpecial();

                    System.out.println("进入推荐特效详情页成功");

                    //选择特效
                    selectSpecial("推荐", layout, j + 1);

                    //使用或下载特效
                    useSpecial();

                    //截屏
                    shootScreen(i,j);

                    //调用发布方法
                    publish();
                }
            }

            /**选择明星特效**/
            else if (i==1) {
                //选择明星特效
                for (int j=0; j<9; j++) {

                    int layout = 0;

                    if (j>=0 && j<3) {
                        layout = 0;
                    } else if (j>=3 && j<6) {
                        layout = 1;
                    } else if (j>=6 && j<9) {
                        layout = 2;
                    }

                    //调用录制方法
                    record();

                    //点击更多特效
                    clickMoreSpecial();

                    //选择明星
                    UiObject changeSpecialButton = new UiObject(new UiSelector().resourceId("com.yixia.videoeditor:id/radio_button2"));
                    try {
                        changeSpecialButton.click();
                    } catch (UiObjectNotFoundException e) {
                        e.printStackTrace();
                    }
                    System.out.println("进入明星特效详情页成功");

                    //选择特效
                    selectSpecial("明星", layout, j+1);

                    //使用或下载特效
                    useSpecial();

                    //截屏
                    shootScreen(i,j);

                    //调用发布方法
                    publish();
                }
            }

            /**选择美颜特效**/
            else if (i==2) {
                //选择美颜特效
                for (int j=0; j<8; j++) {

                    int layout = 0;

                    if (j>=0 && j<3) {
                        layout = 0;
                    } else if (j>=3 && j<6) {
                        layout = 1;
                    } else if (j>=6 && j<9) {
                        layout = 2;
                    }

                    //调用录制方法
                    record();

                    //点击更多特效
                    clickMoreSpecial();

                    //选择美艳
                    UiObject changeSpecialButton = new UiObject(new UiSelector().resourceId("com.yixia.videoeditor:id/radio_button3"));
                    try {
                        changeSpecialButton.click();
                    } catch (UiObjectNotFoundException e) {
                        e.printStackTrace();
                    }
                    System.out.println("进入美颜特效详情页成功");

                    //选择特效
                    selectSpecial("美颜", layout, j+1);

                    //使用或下载特效
                    useSpecial();

                    //截屏
                    shootScreen(i,j);

                    //调用发布方法
                    publish();
                }
            }

            /**选择可爱特效**/
            else if (i==3) {
                //选择可爱特效
                for (int j=0; j<7; j++) {

                    int layout = 0;

                    if (j>=0 && j<3) {
                        layout = 0;
                    } else if (j>=3 && j<6) {
                        layout = 1;
                    } else if (j>=6 && j<9) {
                        layout = 2;
                    }

                    //调用录制方法
                    record();

                    //点击更多特效
                    clickMoreSpecial();

                    //选择可爱
                    UiObject changeSpecialButton = new UiObject(new UiSelector().resourceId("com.yixia.videoeditor:id/radio_button4"));
                    try {
                        changeSpecialButton.click();
                    } catch (UiObjectNotFoundException e) {
                        e.printStackTrace();
                    }
                    System.out.println("进入可爱特效详情页成功");

                    //选择特效
                    selectSpecial("可爱", layout, j+1);

                    //使用或下载特效
                    useSpecial();

                    //截屏
                    shootScreen(i,j);

                    //调用发布方法
                    publish();
                }
            }

            /**选择动感特效**/
            else if (i==4) {
                //选择动感特效
                for (int j=0; j<3; j++) {

                    int layout = 0;

                    if (j>=0 && j<3) {
                        layout = 0;
                    } else if (j>=3 && j<6) {
                        layout = 1;
                    } else if (j>=6 && j<9) {
                        layout = 2;
                    }

                    //调用录制方法
                    record();

                    //点击更多特效
                    clickMoreSpecial();

                    //选择动感
                    UiObject changeSpecialButton = new UiObject(new UiSelector().resourceId("com.yixia.videoeditor:id/radio_button5"));
                    try {
                        changeSpecialButton.click();
                    } catch (UiObjectNotFoundException e) {
                        e.printStackTrace();
                    }
                    System.out.println("进入动感特效详情页成功");

                    //选择特效
                    selectSpecial("动感", layout, j+1);

                    //使用或下载特效
                    useSpecial();

                    //截屏
                    shootScreen(i,j);

                    //调用发布方法
                    publish();
                }
            }

            /**选择清新特效**/
            else if (i==5) {
                //选择清新特效
                for (int j=0; j<7; j++) {

                    int layout = 0;

                    if (j>=0 && j<3) {
                        layout = 0;
                    } else if (j>=3 && j<6) {
                        layout = 1;
                    } else if (j>=6 && j<9) {
                        layout = 2;
                    }

                    //调用录制方法
                    record();

                    //点击更多特效
                    clickMoreSpecial();

                    //选择清新
                    UiObject changeSpecialButton = new UiObject(new UiSelector().resourceId("com.yixia.videoeditor:id/radio_button6"));
                    try {
                        changeSpecialButton.click();
                    } catch (UiObjectNotFoundException e) {
                        e.printStackTrace();
                    }
                    System.out.println("进入清新特效详情页成功");

                    //选择特效
                    selectSpecial("清新", layout, j+1);

                    //使用或下载特效
                    useSpecial();

                    //截屏
                    shootScreen(i,j);

                    //调用发布方法
                    publish();
                }
            }

            /**选择节日特效**/
            else if (i==6) {
                //选择节日特效
                for (int j=0; j<1; j++) {

                    int layout = 0;

                    if (j>=0 && j<3) {
                        layout = 0;
                    } else if (j>=3 && j<6) {
                        layout = 1;
                    } else if (j>=6 && j<8) {
                        layout = 2;
                    }

                    //调用录制方法
                    record();

                    //点击更多特效
                    clickMoreSpecial();

                    //选择节日
                    UiObject changeSpecialButton1 = new UiObject(new UiSelector().resourceId("com.yixia.videoeditor:id/radio_button6"));
                    try {
                        changeSpecialButton1.click();
                    } catch (UiObjectNotFoundException e) {
                        e.printStackTrace();
                    }
                    UiObject changeSpecialButton2 = new UiObject(new UiSelector().resourceId("com.yixia.videoeditor:id/radio_button7"));
                    try {
                        changeSpecialButton2.click();
                    } catch (UiObjectNotFoundException e) {
                        e.printStackTrace();
                    }
                    System.out.println("进入节日特效详情页成功");

                    //选择特效
                    selectSpecial("节日", layout, j+1);

                    //使用或下载特效
                    useSpecial();

                    //截屏
                    shootScreen(i,j);

                    //调用发布方法
                    publish();
                }
            }

            /**选择其他特效**/
            else if (i==7) {
                //选择其他特效
                for (int j=0; j<10; j++) {

                    int layout = 0;

                    if (j>=0 && j<3) {
                        layout = 0;
                    } else if (j>=3 && j<6) {
                        layout = 1;
                    } else if (j>=6 && j<9) {
                        layout = 2;
                    } else if (j>=9 && j<12) {
                        layout = 3;
                    }

                    //调用录制方法
                    record();

                    //点击更多特效
                    clickMoreSpecial();

                    //选择其他
                    UiObject changeSpecialButton1 = new UiObject(new UiSelector().resourceId("com.yixia.videoeditor:id/radio_button6"));
                    try {
                        changeSpecialButton1.click();
                    } catch (UiObjectNotFoundException e) {
                        e.printStackTrace();
                    }
                    UiObject changeSpecialButton2 = new UiObject(new UiSelector().resourceId("com.yixia.videoeditor:id/radio_button8"));
                    try {
                        changeSpecialButton2.click();
                    } catch (UiObjectNotFoundException e) {
                        e.printStackTrace();
                    }
                    System.out.println("进入其他特效详情页成功");

                    //选择特效
                    selectSpecial("其他", layout, j+1);

                    //使用或下载特效
                    useSpecial();

                    //截屏
                    shootScreen(i,j);

                    //调用发布方法
                    publish();
                }
            }
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
        System.out.println("进入录制界面成功");
        sleep(2000);

        //按住开始录制
        UiObject recordStartButton = new UiObject(new UiSelector().resourceId("com.yixia.videoeditor:id/record_controller"));
        try {
            recordStartButton.swipeUp(600);
        } catch (UiObjectNotFoundException e) {
            System.out.println("错误:无法点击开始录制!!!");
        }
        System.out.println("录制10s视频成功");
        sleep(3000);
    }


    /**点击更多特效**/
    public void clickMoreSpecial() {

        //录制完成,点击更多特效
        UiObject moreSpecialButton = new UiObject(new UiSelector().text("更多特效"));
        try {
            moreSpecialButton.clickAndWaitForNewWindow();
        } catch (UiObjectNotFoundException e) {
            System.out.println("错误:无法点击更多特效!!!");
        }
        System.out.println("进入更多特效成功");
        sleep(2000);

    }



    /**选择特效,传入特效名字,行号和列号**/
    public void selectSpecial(String name, int layout, int num) {

        UiObject specialLayout = new UiObject(new UiSelector().className("android.widget.LinearLayout").index(layout));

        if (num==1) {
            num = 1;
        } else if (num==2) {
            num = 2;
        } else if (num==3) {
            num = 3;
        } else if (num==4) {
            num = 1;
        } else if (num==5) {
            num = 2;
        } else if (num==6) {
            num = 3;
        } else if (num==7) {
            num = 1;
        } else if (num==8) {
            num = 2;
        } else if (num==9) {
            num = 3;
        } else if (num==10) {
            num = 1;
        }

        boolean isHave = specialLayout.exists();

        if (isHave){
            System.out.println("选择行(hang)成功");
        } else {
            System.out.println("选择行(hang)失败");
        }


        try {
            UiObject useSpecialButton = specialLayout.getChild(new UiSelector().resourceId("com.yixia.videoeditor:id/play"+num));
            boolean isThere = useSpecialButton.exists();
            sleep(1000);
            if (isThere) {
                System.out.println("选择"+name+"中的,第"+(layout+1)+"行,第"+num+"列成功");
            } else {
                System.out.println("选择"+name+"中的,第"+(layout+1)+"行,第"+num+"列失败");
            }
            useSpecialButton.click();
            sleep(2000);
        } catch (UiObjectNotFoundException e) {
            System.out.println("错误:选择特效时出错!!!");
        }
    }


    /**下载或使用特效**/
    public void useSpecial() {

        //使用或先下载再使用
        UiObject useNowButton = new UiObject(new UiSelector().resourceId("com.yixia.videoeditor:id/imgProgress"));
        try {
            useNowButton.click();
            sleep(1000);
        } catch (UiObjectNotFoundException e) {
            System.out.println("错误:使用特效时出错!!!");
        }
    }


    /**合成视频,发布,关闭分享弹框**/
    public void publish() {
        Configurator.getInstance().setWaitForSelectorTimeout(6000000);

        //视频合成
        UiObject videoFinishButton = new UiObject(new UiSelector().resourceId("com.yixia.videoeditor:id/titleRight"));
        try {
            videoFinishButton.clickAndWaitForNewWindow();
        } catch (UiObjectNotFoundException e) {
            System.out.println("错误:视频合成出错!!!或可能是下载特效失败!!!");
        }

        Configurator.getInstance().setWaitForSelectorTimeout(60000);

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
        UiObject closeShareButton = new UiObject(new UiSelector().resourceId("com.yixia.videoeditor:id/upload_suc_dialog_close"));
        try {
            closeShareButton.click();
        } catch (UiObjectNotFoundException e) {
            System.out.println("错误:关闭分享窗出错!!!");
        }
        System.out.println("关闭发布框成功");
    }


    /**截图**/
    public void shootScreen(int i, int j) {
        //截图
        UiDevice.getInstance().takeScreenshot(new File("/local/tmp/"+i+"_"+j+".png"));
        sleep(1000);
    }

}
