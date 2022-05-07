package com.save.controller;
import com.alibaba.fastjson.JSONObject;
import com.arcsoft.face.*;
import com.arcsoft.face.enums.*;
import com.arcsoft.face.toolkit.ImageInfo;
import java.io.File;
import java.io.IOException;
import java.net.URL;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.UUID;

import static com.arcsoft.face.toolkit.ImageFactory.getRGBData;
import com.save.entity.Task;
import com.save.entity.TaskGroup;
import com.save.entity.UploadImage;
import com.save.entity.Volunteer;
import com.save.service.TaskGroupService;
import com.save.service.TaskService;
import com.save.service.UploadImageService;
import com.save.service.VolunteerService;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;
import sun.rmi.runtime.Log;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;

@RestController
@RequestMapping("/app")
public class ImageController {

    @Resource
    TaskGroupService taskGroupService;

    @Resource
    TaskService taskService;

    @Resource
    UploadImageService uploadImageService;

    @Resource
    VolunteerService volunteerService;

    @RequestMapping("/imageCompare")
    public JSONObject imageCompare(HttpServletRequest request, @RequestParam("file")MultipartFile uploadFile) {
        String url = null;//上传的图片
        Integer userId = (Integer) request.getAttribute("userId");
        JSONObject json = new JSONObject();
        //判断图片是否为空
        if (uploadFile.isEmpty()) {
            json.put("isok", false);
            json.put("msg", "没有图片上传");
            return json;
        }
        //上传图片到服务器
        try {
            //取图片扩展名
            String originalFilename = uploadFile.getOriginalFilename();
            //取扩展名不要“.”
            String extName = originalFilename.substring(originalFilename.lastIndexOf(".") + 1);
            //   后台上传文件路径/home/ruoyi/uploadPath/
           String filePath = "/www/wwwroot/ruoyi-vue/home/ruoyi/uploadPath/uploadToCompare/";

//            /**
//             *window测试
//             */
//            String filePath = "D:\\javaEE";

            //重命名
            url=filePath + String.valueOf(UUID.randomUUID())+"."+extName;
            File dest = new File(url);
            uploadFile.transferTo(dest);
            UploadImage image = new UploadImage();
            image.setUrl(url);
            image.setCreateTime(new Date());
            uploadImageService.insert(image);
        } catch (Exception e) {
            e.printStackTrace();
        }

        Volunteer volunteer = volunteerService.queryById(userId);
        Integer status = volunteer.getStatus();
        if(status==0){
            json.put("isok", false);
            json.put("msg", "您当前未接受任务");
        }else {
            TaskGroup taskGroup = taskGroupService.queryById(status);
            Integer taskId = taskGroup.getTaskId();
            Task task = taskService.queryById(taskId);
            String imageUrl = task.getImageUrl();
            //转数据库的变成文件系统能识别的路径
            String image_target=imageUrl.replace("http://139.224.208.92/","/www/wwwroot/ruoyi-vue/");
            System.out.println("目标图片路径："+image_target);
            System.out.println("上传图片路径："+url);
            double score = CompareTwoImage(image_target, url);
            json.put("isok", true);
            json.put("msg", "识别成功");
            json.put("score", score);
        }
        return json;
    }

    public static double CompareTwoImage(String path1,String path2){

        //从官网获取
        String appId = "J7LYrdxeuwH6kn5c4QCC5BSAk4gDDnSaWTE7d7QLRywo";
        String sdkKey = "2HgtRUdSVejb7ePKK2MCZzDyCMmQzEc5axMgGc81NJ12";

//        /**
//         *window测试
//         */
//        FaceEngine faceEngine = new FaceEngine("D:\\javaEE\\arcsoft_lib");

        FaceEngine faceEngine = new FaceEngine("/usr/local/lib/arcsoft_lib");

        //激活引擎
        int errorCode = faceEngine.activeOnline(appId, sdkKey);

        if (errorCode != ErrorInfo.MOK.getValue() && errorCode != ErrorInfo.MERR_ASF_ALREADY_ACTIVATED.getValue()) {
            System.out.println("引擎激活失败");
        }


        ActiveFileInfo activeFileInfo=new ActiveFileInfo();
        errorCode = faceEngine.getActiveFileInfo(activeFileInfo);
        if (errorCode != ErrorInfo.MOK.getValue() && errorCode != ErrorInfo.MERR_ASF_ALREADY_ACTIVATED.getValue()) {
            System.out.println("获取激活文件信息失败");
        }

        //引擎配置
        EngineConfiguration engineConfiguration = new EngineConfiguration();
        engineConfiguration.setDetectMode(DetectMode.ASF_DETECT_MODE_IMAGE);
        engineConfiguration.setDetectFaceOrientPriority(DetectOrient.ASF_OP_ALL_OUT);
        engineConfiguration.setDetectFaceMaxNum(10);
        engineConfiguration.setDetectFaceScaleVal(16);
        //功能配置
        FunctionConfiguration functionConfiguration = new FunctionConfiguration();
        functionConfiguration.setSupportAge(true);
        functionConfiguration.setSupportFace3dAngle(true);
        functionConfiguration.setSupportFaceDetect(true);
        functionConfiguration.setSupportFaceRecognition(true);
        functionConfiguration.setSupportGender(true);
        functionConfiguration.setSupportLiveness(true);
        functionConfiguration.setSupportIRLiveness(true);
        engineConfiguration.setFunctionConfiguration(functionConfiguration);


        //初始化引擎
        errorCode = faceEngine.init(engineConfiguration);

        if (errorCode != ErrorInfo.MOK.getValue()) {
            System.out.println("初始化引擎失败");
        }


        //人脸检测
        ImageInfo imageInfo = getRGBData(new File(path1));
        List<FaceInfo> faceInfoList = new ArrayList<FaceInfo>();
        errorCode = faceEngine.detectFaces(imageInfo.getImageData(), imageInfo.getWidth(), imageInfo.getHeight(), imageInfo.getImageFormat(), faceInfoList);
        System.out.println(faceInfoList);

        //特征提取
        FaceFeature faceFeature = new FaceFeature();
        errorCode = faceEngine.extractFaceFeature(imageInfo.getImageData(), imageInfo.getWidth(), imageInfo.getHeight(), imageInfo.getImageFormat(), faceInfoList.get(0), faceFeature);
        System.out.println("特征值大小：" + faceFeature.getFeatureData().length);

        //人脸检测2
        ImageInfo imageInfo2 = getRGBData(new File(path2));
        List<FaceInfo> faceInfoList2 = new ArrayList<FaceInfo>();
        errorCode = faceEngine.detectFaces(imageInfo2.getImageData(), imageInfo2.getWidth(), imageInfo2.getHeight(),imageInfo.getImageFormat(), faceInfoList2);
        System.out.println(faceInfoList);

        //特征提取2
        FaceFeature faceFeature2 = new FaceFeature();
        errorCode = faceEngine.extractFaceFeature(imageInfo2.getImageData(), imageInfo2.getWidth(), imageInfo2.getHeight(), imageInfo.getImageFormat(), faceInfoList2.get(0), faceFeature2);
        System.out.println("特征值大小：" + faceFeature.getFeatureData().length);

        //特征比对
        FaceFeature targetFaceFeature = new FaceFeature();
        targetFaceFeature.setFeatureData(faceFeature.getFeatureData());
        FaceFeature sourceFaceFeature = new FaceFeature();
        sourceFaceFeature.setFeatureData(faceFeature2.getFeatureData());
        FaceSimilar faceSimilar = new FaceSimilar();

        errorCode = faceEngine.compareFaceFeature(targetFaceFeature, sourceFaceFeature, faceSimilar);

        System.out.println("相似度：" + faceSimilar.getScore());


        //引擎卸载
        errorCode = faceEngine.unInit();



        return faceSimilar.getScore();
    }

}
