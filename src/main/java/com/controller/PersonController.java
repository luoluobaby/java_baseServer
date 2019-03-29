package com.controller;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;

import com.google.gson.*;
import com.google.gson.reflect.TypeToken;
//import com.model.po.TbTradesOne;
//import com.model.vo.DataOne;
//import com.model.vo.TradeDataToData;
import com.model.po.TbTradesOne;
import com.model.vo.DataOne;
import com.model.vo.DataTwo;
import com.service.TradeDataService;
import com.service.model.impl.TbTradesOneServiceImpl;
import net.sf.json.JSONArray;
import net.sf.json.JSONObject;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.servlet.ModelAndView;

import java.io.*;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

// 将action作为控制类
@Controller
@RequestMapping("/person")
public class PersonController {

    @Resource
    private TradeDataService tradeDataService ;

    @Resource
    private TbTradesOneServiceImpl tbTradesOneImpl ;

    //
    @RequestMapping(value = "get")
    @ResponseBody
    public String Get(HttpServletRequest request) {

        TbTradesOne tb= tbTradesOneImpl.queryByKey(1);
        return  tb.toString();
    }

    @RequestMapping("/center")
    public ModelAndView show() {
        ModelAndView mv = new ModelAndView("center");
        return mv;
    }


    @RequestMapping(value = "/test",produces = "application/json;charset=utf-8")
    @ResponseBody
    public String Test(HttpServletRequest request) throws Exception {

        String jsondata="";
        File file = new File("C:/Users/15937/Desktop/新建文件夹/商品/one.txt");
        InputStreamReader isr = new InputStreamReader(new FileInputStream(file), "UTF-8");
        BufferedReader reader = new BufferedReader(isr);
        String tempString = null;
        while ((tempString = reader.readLine()) != null) {
            jsondata+=tempString;
        }
        Gson json = new GsonBuilder().setDateFormat("MM/dd/yyyy HH:mm:ss").create();
        List<DataOne> datas =json.fromJson(jsondata,new TypeToken<List<DataOne>>(){}.getType());
        File file2 = new File("C:/Users/15937/Desktop/新建文件夹/商品/two.txt");

        reader.close();
        isr = new InputStreamReader((new FileInputStream(file2)) ,"UTF-8" );
        reader = new BufferedReader(isr);
        tempString="";
        jsondata = "";
        while ((tempString = reader.readLine()) != null) {
            jsondata+=tempString;
        }
        JSONObject object = JSONObject.fromObject(jsondata);
        List<DataTwo> twoData = ChangeDataStruct(object);
        tradeDataService.InsertGoodsData(datas,twoData);
        return  "";
    }

    private List<DataTwo> ChangeDataStruct(JSONObject object )
    {
        List<DataTwo> twoDatas=null ;
        if (null != object && 0< object.size())
        {
            twoDatas = new ArrayList<DataTwo>();
            Iterator all_items = (Iterator) object.keys();
            while (all_items.hasNext())
            {
                DataTwo two = null ;
                String key =(String)all_items.next() ;
                JSONObject value = JSONObject.fromObject(object.getString(key));
                if ( value.has("itemcats_get_response") &&
                        value.getJSONObject("itemcats_get_response").has("item_cats")
                        && value.getJSONObject("itemcats_get_response").getJSONObject("item_cats").has("item_cat") ) {
                    JSONArray cats = value.getJSONObject("itemcats_get_response").getJSONObject("item_cats").getJSONArray("item_cat");
                    for (int i = 0; i < cats.size(); i++) {
                        JSONObject cat = cats.getJSONObject(i);
                        if (null == two)
                            two = new DataTwo(cat.getString("parent_cid"));
                        two.getItems().add(cat.getString("name"));
                    }
                    twoDatas.add(two);
                }
            }
        }
        return  twoDatas;
    }

    @RequestMapping("/edit")
    public ModelAndView Edit() {
        ModelAndView mv = new ModelAndView("edit");
        return mv;
    }

    @RequestMapping("/service")
    public ModelAndView Service() {
        ModelAndView mv = new ModelAndView("service");
        return mv;
    }
}
