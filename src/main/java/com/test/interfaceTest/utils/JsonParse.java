package com.test.interfaceTest.utils;


import com.alibaba.fastjson.JSONObject;
import com.alibaba.fastjson2.JSONArray;

public class JsonParse {

    /**
     *
     * @param responseJson:返回对象转换为json格式
     * @param jpath: 查询路径，对输入的该参数进行解析，然后匹配json串中对应的参数的值
     *             如：输入page,则匹配参数page，并返回对应的值1；
     *             如输入data[0]/id，则匹配data中的第一条数据中的id，并返回对应的值1
     *     {
     *     "page": 1,
     *     "per_page": 6,
     *     "total": 12,
     *     "total_pages": 2,
     *     "data": [
     *         {
     *             "id": 1,
     *             "name": "cerulean",
     *             "year": 2000,
     *             "color": "#98B2D1",
     *             "pantone_value": "15-4020"
     *         },
     *         {
     *             "id": 2,
     *             "name": "fuchsia rose",
     *             "year": 2001,
     *             "color": "#C74375",
     *             "pantone_value": "17-2031"
     *         }
     *       }
     * @return: 返回对应参数的值
     */
    public static String getValueByJPath(JSONObject responseJson, String jpath){

        Object obj = responseJson;
        for (String s : jpath.split("/")) {
            if (!s.isEmpty()) {
                if (!(s.contains("[") || s.contains("]"))) {
                    obj = ((JSONObject) obj).get(s);
                } else if (s.contains("[") || s.contains("]")) {
                    JSONArray objArray = ((JSONArray) ((JSONObject) obj).get(s.split("\\[")[0]));
                    if (!objArray.isEmpty())
                        obj = objArray.get(Integer.parseInt(s.split("\\[")[1].replaceAll("\\]", "")));
                    System.out.println(obj.toString());
                }
            }
        }
        return obj.toString();
    }
}
