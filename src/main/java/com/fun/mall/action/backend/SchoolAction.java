package com.fun.mall.action.backend;

import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.TypeReference;
import com.fun.mall.common.GetLatAndLngByBaidu;
import com.fun.mall.entity.School;
import com.fun.mall.service.ISchoolService;
import com.fun.mall.util.ExportUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;

/**
 * @ author     ：CZP.
 * @ Date       ：Created in 8:50 2018/12/19
 * @ Description：处理学校数据
 * @ Modified By：
 * @ Version    : 1.0$
 */
@Controller(value = "SchoolAction")
@RequestMapping(value = "/school")
public class SchoolAction {

    @Autowired
    private ISchoolService schoolService;

    /*
     * create by: CZP
     * description:导出excel
     * create time: 9:11 2018/12/19
     * @return
     */
    // 1.导出excel
    @RequestMapping(value = "/export.do", method = RequestMethod.POST)
    @ResponseBody
    public void export(@RequestParam("data") String data) throws IOException {
        schoolService.del();
        List<School> dataList = JSON.parseObject(data, new TypeReference<List<School>>() {
        });
        for (int i = 0; i < dataList.size(); i++) {
            GetLatAndLngByBaidu getLatAndLngByBaidu = new GetLatAndLngByBaidu();
            Object[] o = getLatAndLngByBaidu.getCoordinate(dataList.get(i).getSchool());
            //经度
            System.out.println(o[0]);
            //纬度
            System.out.println(o[1]);
            School school = new School();
            school.setAddress(dataList.get(i).getAddress());
            school.setPost(dataList.get(i).getPost());
            school.setTel(dataList.get(i).getTel());
            school.setSchool(dataList.get(i).getSchool());
            school.setLongitude((String) o[0]);
            school.setLatitude((String) o[1]);
            schoolService.insert(school);
        }

    }

    @RequestMapping(value = "/view.do",method = RequestMethod.GET)
    @ResponseBody
    public String view(HttpServletResponse response) {
        List<School> schoolList = schoolService.getList();
        String sheetName = "杭州市中学表";
        SimpleDateFormat sdf = new SimpleDateFormat("yyyyMMddHHmmss");
        String prefix = sdf.format(new Date());
        String fileName = prefix + sheetName;
        ExportUtil.exportExcel(response, schoolList, sheetName, fileName);
        return "aaa";
    }


}
